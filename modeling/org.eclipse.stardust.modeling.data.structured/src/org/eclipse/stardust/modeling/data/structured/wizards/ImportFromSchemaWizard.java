/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.data.structured.wizards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.model.beans.QNameUtil;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.SchemaLocatorAdapter;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.parts.dialog.ApplyUpdatesCommand;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;

import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xsd.*;
import org.eclipse.xsd.util.XSDResourceImpl;
import org.xml.sax.InputSource;

/**
 * Extend the base wizard to select a file from the project or outside the workbench and
 * add error handling
 */
public class ImportFromSchemaWizard extends Wizard implements INewWizard
{
   private List<XSDSchema> externalSchemaList = new ArrayList<XSDSchema>();

   private SchemaLocationPage choicePage;
   private XSDSelectSingleFilePage filePage;
   private XSDURLPage urlPage;
   private XSDTypesSelectionPage typesPage;

   private IFile resultFile;
   private String resultURL;

   private TypeDeclarationsType typeDeclarations;

   private ApplyUpdatesCommand command;

   private InputSource resource;

   private XSDResourceImpl emfResource;

   private List<TypeDeclarationType> declarations;

   static final String HTTP = "http://"; //$NON-NLS-1$

   public ImportFromSchemaWizard(TypeDeclarationsType typeDeclarations)
   {
      this(typeDeclarations, null, null, false);
   }

   public ImportFromSchemaWizard(TypeDeclarationsType typeDeclarations, String location,
         InputSource resource, boolean hasCreateParserButton)
   {
      this.typeDeclarations = typeDeclarations;
      this.resource = resource;

      String title = Structured_Messages.ImportFromSchemaWizardTitle;
      setWindowTitle(title);
      setDefaultPageImageDescriptor(DiagramPlugin.imageDescriptorFromPlugin(
            "org.eclipse.xsd.editor", "icons/full/wizban/NewXSD.gif")); //$NON-NLS-1$ //$NON-NLS-2$

      setNeedsProgressMonitor(true);

      // Choice Page
      choicePage = new SchemaLocationPage(location);

      // Select File Page
      filePage = new XSDSelectSingleFilePage(this, PlatformUI.getWorkbench(), null, true);
      filePage.setTitle(title);
      filePage.setDescription(Structured_Messages.SelectFilePageDescription);
      filePage.addFilterExtensions(new String[] {".xsd", ".wsdl"}); //$NON-NLS-1$ //$NON-NLS-2$

      // URL Page
      urlPage = new XSDURLPage(this, location);
      urlPage.setTitle(title);
      urlPage.setDescription(Structured_Messages.URLPageDescription);

      // Types Page
      typesPage = new XSDTypesSelectionPage(this, true, hasCreateParserButton);
      typesPage.setTitle(title);
      typesPage.setDescription(Structured_Messages.XSDTypesPageDescription);
   }

   public void init(IWorkbench aWorkbench, IStructuredSelection aSelection)
   {}

   public void addPages()
   {
      addPage(choicePage);
      addPage(filePage);
      addPage(urlPage);
      addPage(typesPage);
   }

   public IWizardPage getNextPage(IWizardPage currentPage)
   {
      WizardPage nextPage = null;

      if (currentPage == choicePage)
      {
         if (choicePage.isURL())
         {
            nextPage = urlPage;
         }
         else
         {
            nextPage = filePage;
         }
      }
      else if (currentPage == urlPage || currentPage == filePage)
      {
         nextPage = typesPage;
      }

      return nextPage;
   }

   public boolean canFinish()
   {
      return typesPage.isPageComplete();
   }

   public boolean performFinish()
   {
      // cache all id's
      List<String> idCache = CollectionUtils.newList();
      for (int i = 0; i < typeDeclarations.getTypeDeclaration().size(); i++)
      {
         TypeDeclarationType declaration = (TypeDeclarationType) typeDeclarations.getTypeDeclaration().get(i);
         idCache.add(declaration.getId());
      }

      IFile xsdFileInWorkspace = null;
      if (isURL())
      {
         resultURL = urlPage.getURL();
      }
      else
      {
         resultFile = filePage.getFile();
         xsdFileInWorkspace = resultFile;
      }
      IFile file = null;
      if (isURL() && urlPage.saveToWorkspace && !externalSchemaList.isEmpty())
      {
         file = doSaveExternalModel();
         xsdFileInWorkspace = file;
      }
      declarations = CollectionUtils.newList();
      Map<XSDSchema, String> schema2location = CollectionUtils.newMap();
      IStructuredSelection selection = typesPage.getSelection();
      HashMap<String, String> name2id = new HashMap<String, String>();

      HashMap<String, Object> id2Object = new HashMap<String, Object>();
      List<String> duplicateIds = new ArrayList<String>();

      for (Iterator<?> i = selection.iterator(); i.hasNext();)
      {
         Object item = i.next();
         if (item instanceof XSDTypeDefinition || item instanceof XSDElementDeclaration)
         {
            XSDNamedComponent component = (XSDNamedComponent) item;
            if (component.getContainer() == component.getSchema())
            {
               String id = component.getName();
               Object object = id2Object.get(id);
               if(object == null)
               {
                  id2Object.put(id, component);
               }
               else
               {
                  duplicateIds.add(id);
               }
            }
         }
      }

      id2Object = new HashMap<String, Object>();
      for (Iterator<?> i = selection.iterator(); i.hasNext();)
      {
         Object item = i.next();
         if (item instanceof XSDTypeDefinition || item instanceof XSDElementDeclaration)
         {
            XSDNamedComponent component = (XSDNamedComponent) item;
            if (component.getContainer() == component.getSchema())
            {
               String id = component.getName();
               if(duplicateIds.contains(id))
               {
                  // if we have a XSDTypeDefinition and XSDElementDeclaration with same id,
                  // we import only the XSDTypeDefinition,
                  // because XSDElementDeclaration only points to the same XSDTypeDefinition
                  if(component instanceof XSDTypeDefinition)
                  {
                     id2Object.put(id, component);
                  }
               }
               else
               {
                  id2Object.put(id, component);
               }

               Object object = id2Object.get(id);
               if(object != null)
               {
                  if(idCache.contains(id))
                  {
                     ImportIdDialog importIdDialog = new ImportIdDialog(null, id, idCache);
                     if (Dialog.OK == importIdDialog.open())
                     {
                        String name = importIdDialog.getId();
                        name2id.put(name, id);
                        component.setName(name);
                     }
                     else
                     {
                        return false;
                     }
                  }
               }
            }
         }
      }

      ChangeRecorder recorder = new ChangeRecorder();
      recorder.beginRecording(Collections.singleton(typeDeclarations));

      for (Iterator<?> i = selection.iterator(); i.hasNext();)
      {
         Object item = i.next();
         // preventing duplicates
         if(!id2Object.containsValue(item))
         {
            continue;
         }

         if (item instanceof XSDTypeDefinition || item instanceof XSDElementDeclaration)
         {
            XSDNamedComponent component = (XSDNamedComponent) item;
            if (component.getContainer() == component.getSchema())
            {
               XSDSchema schema = component.getSchema();
               TypeDeclarationType declaration = XpdlFactory.eINSTANCE.createTypeDeclarationType();
               declarations.add(declaration);
               String id = component.getName();
               declaration.setId(id);
               declaration.setName(id);
               if (typesPage.mustSaveSchema() && !schema2location.containsKey(schema))
               {
                  ((InternalEObject) schema).eSetResource((Resource.Internal) typeDeclarations.eResource(), null);
                  schema2location.put(schema, fixSchemaLocation(schema, id));
                  SchemaTypeType schemaType = XpdlFactory.eINSTANCE.createSchemaTypeType();
                  declaration.setSchemaType(schemaType);
                  schemaType.setSchema(schema);

                  resolveImportedSchemasToLocalSchemas(schema);
               }
               else
               {
                  ExternalReferenceType reference = XpdlFactory.eINSTANCE.createExternalReferenceType();
                  String useId = name2id.get(id);
                  if(StringUtils.isEmpty(useId))
                  {
                     useId = id;
                  }

                  reference.setXref(QNameUtil.toString(schema.getTargetNamespace(), useId));
                  reference.setLocation(typesPage.mustSaveSchema() ? ((String) schema2location.get(schema))
                        : isURL() ? resultURL : urlPage.getClasspathResourceName(resultFile));
                  declaration.setExternalReference(reference);
               }
               if (isURL() && urlPage.saveToWorkspace)
               {
                  ExtendedAttributeUtil.setAttribute(declaration,
                        StructuredDataConstants.RESOURCE_MAPPING_LOCAL_FILE,
                        urlPage.getClasspathResourceName(file));
               }

               // write workspace relative path for resolving within eclipse environment
               if (xsdFileInWorkspace != null)
               {
                  ExtendedAttributeUtil.setAttribute(declaration,
                        StructuredDataConstants.RESOURCE_MAPPING_ELIPSE_WORKSPACE_FILE,
                        xsdFileInWorkspace.getFullPath().toString());
               }

            }
         }
      }

      for (int i = 0; i < declarations.size(); i++)
      {
         TypeDeclarationType declaration = (TypeDeclarationType) declarations.get(i);
         TypeDeclarationType oldDeclaration = typeDeclarations.getTypeDeclaration(declaration.getId());
         if (oldDeclaration != null)
         {
            typeDeclarations.getTypeDeclaration().remove(oldDeclaration);
         }
         typeDeclarations.getTypeDeclaration().add(declaration);
      }

      for (Map.Entry<XSDSchema, String> entry : schema2location.entrySet())
      {
         XSDSchema schema = entry.getKey();
         schema.setSchemaLocation(entry.getValue());
         schema.reset();
         resolveLocalSchemasToImportedSchema(schema);
      }

      for (XSDSchema schema : schema2location.keySet())
      {
         Map<String, String> prefixes = schema.getQNamePrefixToNamespaceMap();
         Map<String, String> copy = CollectionUtils.newMap();
         copy.putAll(prefixes);
         // ensures namespace declarations at higher level (wsdl for example)
         // are correctly propagated to the schema
         prefixes.putAll(copy);
      }
      ChangeDescription recording = recorder.endRecording();
      command = new ApplyUpdatesCommand(recording);
      return true;
   }

   private void resolveLocalSchemasToImportedSchema(XSDSchema schema)
   {
      for (TypeDeclarationType decl : typeDeclarations.getTypeDeclaration())
      {
         XpdlTypeType type = decl.getDataType();
         if (type instanceof SchemaTypeType)
         {
            XSDSchema otherSchema = ((SchemaTypeType) type).getSchema();
            boolean fixed = false;
            for (XSDSchemaContent object : otherSchema.getContents())
            {
               if (object instanceof XSDImport)
               {
                  XSDImport xsdImport = (XSDImport) object;
                  String namespace = xsdImport.getNamespace();
                  if (!StringUtils.isEmpty(namespace))
                  {
                     if (namespace.equals(schema.getTargetNamespace()))
                     {
                        xsdImport.setSchemaLocation(schema.getSchemaLocation());
                        xsdImport.setResolvedSchema(schema);
                        fixed = true;
                     }
                  }
               }
            }
            if (fixed)
            {
               otherSchema.reset();
            }
         }
      }
   }

   private void resolveImportedSchemasToLocalSchemas(XSDSchema schema)
   {
      for (XSDSchemaContent object : schema.getContents())
      {
         if (object instanceof XSDImport)
         {
            XSDImport xsdImport = (XSDImport) object;
            String namespace = xsdImport.getNamespace();
            if (!StringUtils.isEmpty(namespace))
            {
               for (TypeDeclarationType decl : typeDeclarations.getTypeDeclaration())
               {
                  XpdlTypeType type = decl.getDataType();
                  if (type instanceof SchemaTypeType)
                  {
                     XSDSchema otherSchema = ((SchemaTypeType) type).getSchema();
                     if (namespace.equals(otherSchema.getTargetNamespace()))
                     {
                        xsdImport.setSchemaLocation(otherSchema.getSchemaLocation());
                        xsdImport.setResolvedSchema(otherSchema);
                     }
                  }
               }
            }
         }
      }
   }

   private String fixSchemaLocation(XSDSchema schema, String id)
   {
      String location = StructuredDataConstants.URN_INTERNAL_PREFIX + id;
      for (XSDSchemaDirective directive : schema.getReferencingDirectives())
      {
         directive.setSchemaLocation(location);
      }
      return location;
   }

   private IFile doSaveExternalModel()
   {
      IFile file = null;
      if (isURL() && urlPage.saveToWorkspace)
      {
         IFolder folder = urlPage.getSaveFolder();
         int end = resultURL.indexOf('?');
         if (end < 0)
         {
            end = resultURL.length();
         }
         while (end > 0 && resultURL.charAt(end - 1) == '/')
         {
            end--;
         }
         int start = resultURL.lastIndexOf('/', end - 1);
         if (start < 0)
         {
            start = 0;
         }
         else
         {
            start++;
         }
         String localName = resultURL.substring(start, end);
         // TODO: name !
         if (localName.length() == 0)
         {
            localName = "schema"; //$NON-NLS-1$
         }
         if (localName.indexOf('.') < 0)
         {
            localName += ".xsd"; //$NON-NLS-1$
         }
         file = folder.getFile(localName);
         if (file.exists())
         {
            String[] buttons = new String[] {
                  IDialogConstants.YES_LABEL,
                  IDialogConstants.SKIP_LABEL};
            String question = NLS.bind(IDEWorkbenchMessages.SaveAsDialog_overwriteQuestion,
                  file.getFullPath().toOSString());
            MessageDialog dialog = new MessageDialog(getShell(),
                  IDEWorkbenchMessages.Question, null, question,
                  MessageDialog.QUESTION, buttons, 0);
            if (dialog.open() != 0) // 0 == yes
            {
               return file;
            }
         }
         try
         {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            XSDSchema schema = (XSDSchema) externalSchemaList.get(0);
            XSDResourceImpl.serialize(outputStream, schema.getDocument(), "UTF-8"); //$NON-NLS-1$
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            if (file.exists())
            {
               file.setContents(inputStream, IResource.KEEP_HISTORY, null);
            }
            else
            {
               file.create(inputStream, IResource.KEEP_HISTORY, null);
            }
         }
         catch (CoreException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return file;
   }

   /**
    * Get the MOF object that represents the external file
    */
   public List<XSDSchema> getExternalSchemaList()
   {
      return externalSchemaList;
   }

   public IFile getResultFile()
   {
      return resultFile;
   }

   public String getURL()
   {
      return getResultURL();
   }

   /**
    * Create a MOF model for the imported file
    * @throws IOException
    */
   protected String doLoadExternalModel(IProgressMonitor monitor, String xsdModelFile,
         String xsdFileName)
   {
      String errorMessage = null;

      monitor.beginTask(Structured_Messages.LoadingSchemaTaskName, 100);
      monitor.worked(50);

      emfResource = new XSDResourceImpl(URI.createURI(xsdModelFile));
      emfResource.eAdapters().add(new SchemaLocatorAdapter());
      ResourceSetImpl resourceSet = new ResourceSetImpl();
      resourceSet.getResources().add(emfResource);
      InputSource is = resource;
      if (is == null)
      {
         is = new InputSource(xsdModelFile);
      }
      try
      {
         /*Map options = CollectionUtils.newMap();
         options.put("XSD_PROGRESS_MONITOR", monitor);*/
         emfResource.load(is, CollectionUtils.newMap());
         externalSchemaList.clear();
         List<EObject> resourceContents = emfResource.getContents();
         for (int i = 0; i < resourceContents.size(); i++)
         {
            EObject eObject = resourceContents.get(i);
            if (eObject instanceof XSDSchema)
            {
               XSDSchema externalSchema = (XSDSchema) eObject;
               if (externalSchema.getElement() != null
                     && (externalSchema.getDiagnostics() == null
                     || externalSchema.getDiagnostics().isEmpty()))
               {
                  externalSchemaList.add(externalSchema);
               }
               else if (errorMessage == null)
               {
                  errorMessage = Structured_Messages.IncorrectSchemaMessage;
               }
            }
         }
         if (errorMessage == null && externalSchemaList.isEmpty())
         {
            errorMessage = Structured_Messages.IncorrectSchemaMessage;
         }
      }
      catch (IOException e)
      {
         errorMessage = e.getMessage();
      }

      for (int i = 0; i < externalSchemaList.size(); i++)
      {
         XSDSchema schema = (XSDSchema) externalSchemaList.get(i);
         TypeDeclarationUtils.resolveImports(schema);
         XSDTypesSelectionPage.checkNamespaceDeclaration(schema, schema.getSchemaForSchemaNamespace(), "xsd"); //$NON-NLS-1$
      }

      monitor.subTask(Structured_Messages.FinishLoadingTaskName);
      monitor.worked(80);

      typesPage.updateContent();

      return errorMessage;
   }

   public boolean isURL()
   {
      return choicePage.isURL();
   }

   String getResultURL()
   {
      return resultURL;
   }

   public Command getCommand()
   {
      return command;
   }

   public TypeDeclarationsType getTypeDeclarations()
   {
      return typeDeclarations;
   }

   public boolean getCreateParserApplication()
   {
      return typesPage.createParser;
   }

   public List<TypeDeclarationType> getDeclarations()
   {
      return declarations;
   }

   private static final class IDEWorkbenchMessages extends NLS
   {
      private static final String BUNDLE_NAME = "org.eclipse.ui.internal.ide.messages";//$NON-NLS-1$

      public static String SaveAsDialog_overwriteQuestion;
      public static String Question;

      static
      {
         // load message values from bundle file
         NLS.initializeMessages(BUNDLE_NAME, Structured_Messages.class);
      }
   }
}