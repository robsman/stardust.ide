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

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.UnresolvedReferenceException;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.data.structured.StructContentProvider;
import org.eclipse.stardust.modeling.data.structured.StructLabelProvider;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaContent;
import org.eclipse.xsd.XSDTypeDefinition;

public class XSDTypesSelectionPage extends WizardPage implements ISelectionChangedListener
{
   private static String[] COLUMNS = {Structured_Messages.NameColumnLabel, Structured_Messages.TypeColumnLabel};

   private final ImportFromSchemaWizard importFromSchemaWizard;
   
   private TreeViewer viewer;
   private boolean saveSchema;
   private boolean multiSelection = true;

   private IStructuredSelection selection;

   private Button saveButton;

   private HashSet<XSDSchema> selectedSchemas = new HashSet<XSDSchema>();

   private boolean hasCreateParserButton;

   protected boolean createParser;

   protected XSDTypesSelectionPage(ImportFromSchemaWizard importFromSchemaWizard, boolean multiSelection, boolean hasCreateParserButton)
   {
      super("TypesPage"); //$NON-NLS-1$
      this.importFromSchemaWizard = importFromSchemaWizard;
      this.multiSelection = multiSelection;
      this.hasCreateParserButton = hasCreateParserButton;
      setPageComplete(false);
   }

   public void createControl(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      
      if (multiSelection)
      {
         Composite buttonsPanel = FormBuilder.createComposite(composite, 3);
         buttonsPanel.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(2));
         FormBuilder.createButton(buttonsPanel, Structured_Messages.SelectAllButtonLabel, new SelectionAdapter()
         {
            public void widgetSelected(SelectionEvent e)
            {
               selectAll();
            }
         });
         FormBuilder.createButton(buttonsPanel, Structured_Messages.SelectNoneButtonLabel, new SelectionAdapter()
         {
            public void widgetSelected(SelectionEvent e)
            {
               selectNone();
            }
         });
         Label filler = FormBuilder.createLabel(buttonsPanel, ""); //$NON-NLS-1$
         filler.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData());
      }

      Tree tree = FormBuilder.createTree(composite, getTreeStyle(),
            COLUMNS, new int[] {50, 50}, 2);
      ((GridData) tree.getLayoutData()).heightHint = 400;
      viewer = new TreeViewer(tree);
      viewer.setContentProvider(new StructContentProvider(true));
      viewer.setLabelProvider(new StructLabelProvider());
      saveButton = FormBuilder.createCheckBox(composite, Structured_Messages.SaveOriginalSchemaLabel, 2);
      saveButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            saveSchema = ((Button) e.widget).getSelection();
            checkSelection();
         }
      });
      if (hasCreateParserButton)
      {
         Button createParserButton = FormBuilder.createCheckBox(composite, Structured_Messages.BUT_CREATE_PR_AND_SER_APP, 2);
         createParserButton.addSelectionListener(new SelectionAdapter()
         {
            public void widgetSelected(SelectionEvent e)
            {
               createParser = ((Button) e.widget).getSelection();
            }
         });
      }
      viewer.addSelectionChangedListener(this);
      setControl(composite);
   }

   protected void selectNone()
   {
      viewer.setSelection(StructuredSelection.EMPTY);
   }

   protected void selectAll()
   {
      List<XSDNamedComponent> selection = CollectionUtils.newList();
      for (XSDSchema schema : importFromSchemaWizard.getExternalSchemaList())
      {
         viewer.expandToLevel(schema, 1);
         selection.addAll(schema.getElementDeclarations());
         selection.addAll(schema.getTypeDefinitions());
      }
      viewer.setSelection(new StructuredSelection(selection));
   }

   private int getTreeStyle()
   {
      return (multiSelection ? SWT.MULTI : SWT.SINGLE) | SWT.FULL_SELECTION | SWT.BORDER;
   }

   public void updateContent()
   {
	  Set<XSDSchema> schemas = new HashSet<XSDSchema>();
      for (XSDSchema schema : importFromSchemaWizard.getExternalSchemaList())
      {
    	 addSchema(schemas, schema);
      }
	  viewer.setInput(schemas);
   }

   private void addSchema(Set<XSDSchema> schemas, XSDSchema schema)
   {
	  if (!schemas.contains(schema))
	  {
		 schemas.add(schema);
		 List<XSDSchemaContent> contents = schema.getContents();
		 for (XSDSchemaContent item : contents)
         {
			if (item instanceof XSDImport)
			{
			   XSDSchema ref = ((XSDImport) item).getResolvedSchema();
			   if (ref != null)
			   {
				  addSchema(schemas, ref);
			   }
			}
		 }
	  }
   }

   public void selectionChanged(SelectionChangedEvent event)
   {
      selection = (IStructuredSelection) event.getSelection();
      checkSelection();
   }

   private void checkSelection()
   {
      boolean pageComplete = false;
      boolean canSave = true;
      String errorMessage = null;
      Set<String> unresolvedRefs = CollectionUtils.newSet();
      selectedSchemas.clear();
      if (selection != null && !selection.isEmpty())
      {
         for (Iterator<?> i = selection.iterator(); i.hasNext();)
         {
            Object item = i.next();
            if (item instanceof XSDTypeDefinition || item instanceof XSDElementDeclaration)
            {
               XSDNamedComponent namedComponent = (XSDNamedComponent) item;
               // (fh) consider only root components
               XSDSchema schema = namedComponent.getSchema();
               if (namedComponent.getContainer() == schema)
               {
                  selectedSchemas.add(schema);
               }
            }
         }
         Set<EObject> visited = CollectionUtils.newSet();
         for (Iterator<?> i = selection.iterator(); i.hasNext();)
         {
            Object item = i.next();
            if (item instanceof XSDTypeDefinition || item instanceof XSDElementDeclaration)
            {
               XSDNamedComponent component = (XSDNamedComponent) item;
               // (fh) consider only root components
               XSDSchema target = component.getSchema();
               if (component.getContainer() == target)
               {
                  pageComplete = true;
                  // (fh) it's important to keep canSave last, otherwise unresolvedRefs
                  // will not contain all unresolved references.
                  try
                  {
                     canSave = checkReferences(target, unresolvedRefs, visited, component) && canSave;
                  }
                  catch (UnresolvedReferenceException e)
                  {
                     pageComplete = false;
                     errorMessage = Structured_Messages.ERROR_MSG_UNRESOLVED_RE + e.getReference()
                        + "\nIs \"" + e.getLocation() + Structured_Messages.ERROR_MSG_IMPORTED; //$NON-NLS-1$
                  }
               }
            }
         }
      }
      if (errorMessage == null)
      {
         if (saveSchema && !canSave)
         {
				errorMessage = MessageFormat
						.format(Structured_Messages.ERROR_MSG_SELE_CONTAINS_ELEM_WITH_REF_IN_SCHEMAS_NOT_INCLUDED_IN_SELE,
								new Object[] { unresolvedRefs });
         }
         else if (!pageComplete && selection != null && !selection.isEmpty())
         {
            errorMessage = Structured_Messages.ERROR_MSG_SELE_DOINST_CONTAIN_ANY_ROOT_COMPONENTS;
         }
      }
      setErrorMessage(errorMessage);
      setPageComplete(pageComplete);
   }

   private boolean checkReferences(XSDSchema target, Set<String> unresolvedRefs, Set<EObject> visited, EObject component) throws UnresolvedReferenceException
   {
      if (component == null || visited.contains(component))
      {
         return true;
      }
      visited.add(component);
      if (component instanceof XSDElementDeclaration)
      {
         XSDElementDeclaration decl = (XSDElementDeclaration) component;
         XSDElementDeclaration resolved = decl.getResolvedElementDeclaration();
         if (resolved != decl)
         {
            XSDSchema schema = resolved.getSchema();
            if (schema == null)
            {
               throw new UnresolvedReferenceException(resolved.getName(), resolved.getTargetNamespace(), 0, 0);
            }
            if (!selectedSchemas.contains(schema))
            {
               checkNamespaceDeclaration(target, schema.getTargetNamespace(), null);
               unresolvedRefs.add(resolved.getName());
               return false;
            }
            return checkReferences(target, unresolvedRefs, visited, resolved);
         }
         else
         {
            return checkReferences(target, unresolvedRefs, visited, resolved.getTypeDefinition());
         }
      }
      else if (component instanceof XSDTypeDefinition)
      {
         XSDTypeDefinition type = (XSDTypeDefinition) component;
         XSDSchema schema = type.getSchema();
         if (schema == null)
         {
            throw new UnresolvedReferenceException(type.getName(), type.getTargetNamespace(), 0, 0);
         }
         String targetNamespace = schema.getTargetNamespace();
         String schemaForSchemaNamespace = schema.getSchemaForSchemaNamespace();
         if (!schemaForSchemaNamespace.equals(targetNamespace))
         {
            if (!selectedSchemas.contains(schema))
            {
               checkNamespaceDeclaration(target, schema.getTargetNamespace(), null);
               unresolvedRefs.add(type.getName());
               return false;
            }
            boolean canSave = true;
            Iterator<EObject> contents = type.eAllContents();
            while (contents.hasNext())
            {
               // (fh) it's important to keep canSave last, otherwise unresolvedRefs
               // will not contain all unresolved references.
               canSave = checkReferences(target, unresolvedRefs, visited, contents.next()) && canSave;
            }
            return canSave;
         }
      }
      return true;
   }

   public static void checkNamespaceDeclaration(XSDSchema target, String namespace, String defaultPrefix)
   {
      if (namespace != null && namespace.length() > 0)
      {
         Map<String, String> map = target.getQNamePrefixToNamespaceMap();
         if (!map.containsValue(namespace))
         {
            // (fh) must declare prefix mapping
            if (defaultPrefix == null)
            {
               // compute a default prefix based on the namespace
               defaultPrefix = namespace;
               if (defaultPrefix.charAt(defaultPrefix.length() - 1) == '/')
               {
                  defaultPrefix = defaultPrefix.substring(0, defaultPrefix.length() - 1);
               }
               int ix = defaultPrefix.lastIndexOf('/');
               if (ix >= 0)
               {
                  defaultPrefix = defaultPrefix.substring(ix + 1);
               }
               defaultPrefix = shorten(defaultPrefix, '.');
               defaultPrefix = shorten(defaultPrefix, '?');
               defaultPrefix = shorten(defaultPrefix, '#');
            }
            String prefix = TypeDeclarationUtils.computePrefix(defaultPrefix, map.keySet());
            map.put(prefix, namespace);
         }
      }
   }

   private static String shorten(String namespace, char endCharacter)
   {
      int ix1 = namespace.indexOf(endCharacter);
      if (ix1 >= 0)
      {
         return namespace.substring(0, ix1);
      }
      return namespace;
   }

   public IStructuredSelection getSelection()
   {
      return selection;
   }

   public boolean mustSaveSchema()
   {
      return saveSchema;
   }
}