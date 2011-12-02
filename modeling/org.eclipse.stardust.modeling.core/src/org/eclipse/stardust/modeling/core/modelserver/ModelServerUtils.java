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
package org.eclipse.stardust.modeling.core.modelserver;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.merge.ShareUtils;
import org.eclipse.stardust.model.xpdl.carnot.merge.UUIDUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.common.ui.jface.IconWithOverlays;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionState;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.xsd.XSDPackage;
import org.w3c.dom.Node;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.CompareHelper;
import ag.carnot.base.StringUtils;

public class ModelServerUtils
{   
   public static int LOCKED_BY_USER = 1;
   public static int LOCKED_BY_OTHER = 2;
   public static int REMOVED = 3;   
   
   public static Image imageAdded = DiagramPlugin.getDefault().getImageManager().getImage("icons/full/obj16/added_ov.gif"); //$NON-NLS-1$   
   public static Image imageEdited = DiagramPlugin.getDefault().getImageManager().getImage("icons/full/obj16/edited_ov.gif"); //$NON-NLS-1$;   
   public static Image imageDeleted = DiagramPlugin.getDefault().getImageManager().getImage("icons/full/obj16/deleted_ov.gif"); //$NON-NLS-1$;
   
   public static Image imageLocked = DiagramPlugin.getDefault().getImageManager().getImage("icons/full/obj16/locked_ov.gif"); //$NON-NLS-1$;
   public static Image imageProtected = DiagramPlugin.getDefault().getImageManager().getImage("icons/full/obj16/protected_ov.gif"); //$NON-NLS-1$;
   public static Image imageVCS = DiagramPlugin.getDefault().getImageManager().getImage("icons/full/obj16/version_controlled.gif"); //$NON-NLS-1$;
      
   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   private static final Map<IProject, Vcs> vcsCache = new HashMap<IProject, Vcs>();
   private static final Set<String> unsupportedProviderIds = new HashSet<String>();

   public static void showMessageBox(String message)
   {
      showMessageBox(message, Diagram_Messages.MSG_BOX_RMS_OPERATION);
   }
   
   public static void showMessageBox(String message, String title)
   {
      if (Display.getDefault().getActiveShell() != null)
      {
         MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
               SWT.ICON_WARNING | SWT.OK);
         messageBox.setText(title);
         messageBox.setMessage(message);
         messageBox.open();
      }      
   }      
      
   public static String getUser(IFile file)
   {
      return System.getProperty("user.name") + file.getLocation().toOSString(); //$NON-NLS-1$;
   }
   
   public static String convertCommitString(String input)
   {
      InputStream is = new ByteArrayInputStream(input.getBytes());            
      String data = new String();
      try
      {
         BufferedReader reader = new BufferedReader(new InputStreamReader(
               is, "ISO-8859-1")); //$NON-NLS-1$;
         String line = null;
         while ((line = reader.readLine()) != null)
         {
            data += line + " "; //$NON-NLS-1$;
         }
         reader.close();
      }
      catch (Exception e)
      {
      }
      return data;
   }
   
   public static Boolean isLockedByCurrentUser(EObject modelElement)
   {
      if (modelElement instanceof IModelElementNodeSymbol)
      {
         modelElement = ((IModelElementNodeSymbol) modelElement).getModelElement();
      }
      if (modelElement == null)
      {
         return null;
      }
      
      ProcessDefinitionType process = ModelUtils.findContainingProcess(modelElement);
      if (process != null)
      {
         modelElement = process;
      }
      
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(ModelUtils.findContainingModel((EObject) modelElement));
      if (editor != null && editor.getModelServer().isModelShared()
            && LockFileUtils.getLockFile((EObject) modelElement) != null
            && LockFileUtils.getLockFile((EObject) modelElement).exists())
      {     
         return editor.getModelServer().getStateCache().getState(modelElement).getState() == CollisionState.LOCKED_BY_USER;
      }      
      return null;
   }
   
   public static EObject getLockableElementFromSelection(Object selection)
   {
      if (selection instanceof AbstractEObjectTreeEditPart)
      {
         AbstractEObjectTreeEditPart treeEditPart = (AbstractEObjectTreeEditPart) selection;
         Object modelElement = treeEditPart.getModel();
         if (ShareUtils.isLockableElement(modelElement))
         {
            return (EObject) modelElement;
         }         
      }
      return null;
   }   
      
   public static Vcs getVcs(IProject project) throws RMSException
   {
      Vcs vcs = getVcs2(project);
      if (vcs == null)
      {
         throw new RMSException(Diagram_Messages.EXC_PROJECT_IS_NOT_SHARED, RMSException.DEFAULT);
      }
      return vcs;
   }

   /**
    * Same as getVcs but it will not throw exceptions if the project is not shared.
    * 
    * @param project
    * @return
    */
   public static Vcs getVcs2(IProject project)
   {
      if (RepositoryProvider.isShared(project))
      {
         Vcs vcs = vcsCache.get(project);
         if (vcs != null)
         {
            return vcs;
         }
         
         RepositoryProvider repositoryProvider = RepositoryProvider.getProvider(project);
         String providerId = repositoryProvider.getID();
         if (unsupportedProviderIds.contains(providerId))
         {
            throw new RuntimeException(
                  MessageFormat
                        .format(
                              Diagram_Messages.EXC_UNSUPPORTED_REPOSITORY_PROVIDER_NO_EXTENSION_FOUND_FOR,
                              new Object[] {repositoryProvider.getClass().getName()}));
         }
         
         if (Platform.getExtensionRegistry() != null)
         {
            IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                  .getConfigurationElementsFor("org.eclipse.stardust.modeling.core.vcsConnector"); //$NON-NLS-1$
            for (int j = 0; j < extensions.length; j++)
            {
               if (extensions[j].getAttribute("repositoryProviderId").equals(providerId)) //$NON-NLS-1$
               {
                  try
                  {
                     vcs = (Vcs) extensions[j].createExecutableExtension("vcsConnectorClass"); //$NON-NLS-1$
                     vcs.setRepositoryProvider(repositoryProvider);
                     vcsCache.put(project, vcs);
                     return vcs;
                  }
                  catch (Exception e)
                  {
                     unsupportedProviderIds.add(providerId);
                     //Message_Format
                     throw new RuntimeException(Diagram_Messages.EXC_ERROR_INITIALIZING_REPOSITORY_PROVIDER_FOR
                           + repositoryProvider.getClass().getName(), e);
                  }
               }
            }
         }
         
         unsupportedProviderIds.add(providerId);
         //Message_Format
         throw new RuntimeException(Diagram_Messages.EXC_UNSUPPORTED_REPOSITORY_PROVIDER_NO_EXTENSION_FOUND_FOR
                  + repositoryProvider.getClass().getName());
      }
      vcsCache.remove(project);
      return null;
   }
   
   public static IFolder createLockFiles(IProject project, ModelType model)
   {
      try
      {
         if (UUIDUtils.getUUID(model) == null)
         {
            // (fh) the model MUST have an UUID set in order to compute the lock folder name
            UUIDUtils.setUUID(model);
         }
         IFolder lockFolder = ShareUtils.getLockFolder(model);
         if (lockFolder == null)
         {
            throw new RuntimeException(Diagram_Messages.EXC_UNABLE_TO_FIND_LOCK_FOLDER);
         }
         if (!lockFolder.exists())
         {
            lockFolder.create(true, true, null);
         }

         createLockFileIfNotExists(lockFolder, model, 0);
         IFolder subFolder = LockFileUtils.getLockFolder(XpdlPackage.eINSTANCE.getTypeDeclarationType(), lockFolder);
         if (!subFolder.exists())
         {
            subFolder.create(true, true, null);
         }                  
         createLockFileIfNotExists(lockFolder, model.getTypeDeclarations().getTypeDeclaration());
         
         subFolder = LockFileUtils.getLockFolder(PKG_CWM.getApplicationType(), lockFolder);
         if (!subFolder.exists())
         {
            subFolder.create(true, true, null);
         }                           
         createLockFileIfNotExists(lockFolder, model.getApplication());
         
         subFolder = LockFileUtils.getLockFolder(PKG_CWM.getDataType(), lockFolder);
         if (!subFolder.exists())
         {
            subFolder.create(true, true, null);
         }                           
         createLockFileIfNotExists(lockFolder, model.getData());         
         
         subFolder = LockFileUtils.getLockFolder(PKG_CWM.getRoleType(), lockFolder);
         if (!subFolder.exists())
         {
            subFolder.create(true, true, null);
         }                           
         createLockFileIfNotExists(lockFolder, model.getRole());
         subFolder = LockFileUtils.getLockFolder(PKG_CWM.getConditionalPerformerType(), lockFolder);
         if (!subFolder.exists())
         {
            subFolder.create(true, true, null);
         }                           
         createLockFileIfNotExists(lockFolder, model.getConditionalPerformer());
         subFolder = LockFileUtils.getLockFolder(PKG_CWM.getOrganizationType(), lockFolder);
         if (!subFolder.exists())
         {
            subFolder.create(true, true, null);
         }                           
         createLockFileIfNotExists(lockFolder, model.getOrganization());
         
         subFolder = LockFileUtils.getLockFolder(PKG_CWM.getProcessDefinitionType(), lockFolder);
         if (!subFolder.exists())
         {
            subFolder.create(true, true, null);
         }                           
         createLockFileIfNotExists(lockFolder, model.getProcessDefinition());
         subFolder = LockFileUtils.getLockFolder(PKG_CWM.getDiagramType(), lockFolder);
         if (!subFolder.exists())
         {
            subFolder.create(true, true, null);
         }                           
         createLockFileIfNotExists(lockFolder, model.getDiagram());

         subFolder = LockFileUtils.getLockFolder(PKG_CWM.getLinkTypeType(), lockFolder);
         if (!subFolder.exists())
         {
            subFolder.create(true, true, null);
         }                  
         createLockFileIfNotExists(lockFolder, model.getLinkType());                  
         
         WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
         editor.doSave(new NullProgressMonitor());
         editor.getEditDomain().getCommandStack().flush();
         
         return lockFolder;
      }
      catch (Exception e)
      {
         if (e instanceof RuntimeException)
         {
            throw (RuntimeException) e;
         }
         else
         {
            throw new RuntimeException(e);
         }
      }
   }

   private static void createLockFileIfNotExists(IFolder modelLockFolder, List<? extends EObject> domain) throws Exception
   {
      for (int i = 0; i < domain.size(); ++i)
      {
         EObject me = domain.get(i);
         createLockFileIfNotExists(modelLockFolder, me, 0);
      }
   }

   public static IFile getModelFile(ModelType model)
   {
      Resource eResource = model.eResource();
      if (eResource != null)
      {
         URI eUri = eResource.getURI();
         return ResourcesPlugin.getWorkspace().getRoot().getFile(Path.fromPortableString(eUri.toPlatformString(true)));
      }
      return null;
   }

   public static IFile createLockFileIfNotExists(IFolder lockFolder, EObject element, int value) throws CoreException, IOException
   {
      if (element instanceof DataType && ((DataType) element).isPredefined())
      {
         return null;
      }
      
      String uuid = UUIDUtils.getUUID(element);
      if (uuid == null)
      {
         uuid = UUIDUtils.setUUID(element);
         if (uuid == null)
         {
            // exit if we don't have an UUID
            return null;
         }
      }
      
      IFolder elementLockFolder = LockFileUtils.getLockFolder(element.eClass(), lockFolder);               
      if (!elementLockFolder.exists())
      {
         elementLockFolder.create(true, true, null);
      }         
      
      String fileName = LockFileUtils.getFileName(element, uuid);
      IFile file = elementLockFolder.getFile(fileName);
      if (!file.exists())
      {
         // creates an empty file
         file.create(new ByteArrayInputStream(Integer.toString(value).getBytes("ISO-8859-1")), true, null); //$NON-NLS-1$;
      }
      return file;
   }

   public static Map<String, EObject> createUuidToElementMap(ModelType model)
   {
      Map<String, EObject> map = new HashMap<String, EObject>();
      String uuid = UUIDUtils.getUUID(model);
      map.put(uuid, model);      
      addToMap(map, model.getTypeDeclarations().getTypeDeclaration());
      addToMap(map, model.getApplication());
      addToMap(map, model.getData());
      addToMap(map, model.getRole());
      addToMap(map, model.getConditionalPerformer());
      addToMap(map, model.getOrganization());
      addToMap(map, model.getProcessDefinition());
      addToMap(map, model.getDiagram());
      addToMap(map, model.getLinkType());
      return map;
   }
   
   public static List<EObject> getElementsWithoutUuid(ModelType model)
   {
      List<EObject> list = CollectionUtils.newList();
      String uuid = UUIDUtils.getUUID(model);
      if (uuid == null)
      {
         list.add(model);
      }
      addToList(list, model.getTypeDeclarations().getTypeDeclaration());
      addToList(list, model.getApplication());
      addToList(list, model.getData());
      addToList(list, model.getRole());
      addToList(list, model.getConditionalPerformer());
      addToList(list, model.getOrganization());
      addToList(list, model.getProcessDefinition());
      addToList(list, model.getDiagram());
      addToList(list, model.getLinkType());
      return list;
   }

   private static void addToList(List<EObject> list, List<? extends EObject> domain)
   {
      for (int i = 0; i < domain.size(); ++i)
      {
         EObject me = domain.get(i);
         if (me instanceof DataType && ((DataType) me).isPredefined())
         {
            continue;
         }
         String uuid = UUIDUtils.getUUID(me);
         if (uuid == null)
         {
            list.add(me);
         }
      }
   }

   private static void addToMap(Map<String, EObject> map, List<? extends EObject> domain)
   {
      for (int i = 0; i < domain.size(); ++i)
      {
         EObject me = domain.get(i);
         String uuid = UUIDUtils.getUUID(me);
         if (uuid != null)
         {
            map.put(uuid, me);
         }
      }
   }
         
   public static void mergeElements2(List<EObject> sourceModelElements, ModelType sourceModel, ModelType targetModel)
   {
      Map<String, EObject> map = createUuidToElementMap(targetModel);
      for (EObject sourceElement : sourceModelElements)
      {
         EObject targetElement = map.get(UUIDUtils.getUUID(sourceElement));
         if (targetElement != null)
         {
            if (sourceElement instanceof ModelType)
            {
               ModelCopier copier = new ModelCopier();
               copier.copy((ModelType) sourceElement, (ModelType) targetElement);
            }
            else
            {
               MergeUtils.replace(targetElement, sourceElement);
            }
         }
         // TODO: what if target is null ?
      }      
      if (!sourceModelElements.isEmpty())
      {
         replaceReferences(sourceModelElements, targetModel);
      }
   }

   /**
    * This method replaces the content of targetModelElements with the result of the operation
    * 
    * @param targetModelElements
    * @param sourceModel
    * @param targetModel
    */
   public static void mergeElements3(List<EObject> targetModelElements, ModelType sourceModel, ModelType targetModel)
   {
      Map<String, EObject> sourceMap = createUuidToElementMap(sourceModel);
      for (int i = 0; i < targetModelElements.size(); i++)
      {
         EObject targetElement = (EObject) targetModelElements.get(i);
         EObject sourceElement = sourceMap.get(UUIDUtils.getUUID(targetElement));
         if (sourceElement != null)
         {
            if (sourceElement instanceof ModelType)
            {
               ModelCopier copier = new ModelCopier();
               copier.copy((ModelType) sourceElement, (ModelType) targetElement);
            }
            else
            {
               MergeUtils.replace(targetElement, sourceElement);
               targetModelElements.set(i, sourceElement);
            }
         }
         else
         {
            // TODO: what if source is null ?
            targetModelElements.remove(i);            
         }
      }      
      if (!targetModelElements.isEmpty())
      {
         replaceReferences(targetModelElements, targetModel);
      }
   }

   private static void replaceReferences(List<EObject> targetModelElements, ModelType targetModel)
   {
      Map<String, EObject> targetMap = createUuidToElementMap(targetModel);
      for (int i = 0; i < targetModelElements.size(); i++)
      {
         EObject targetElement = (EObject) targetModelElements.get(i);
         if (!(targetElement instanceof ModelType))
         {
            Iterator<EObject> contents = targetElement.eAllContents();
            while (contents.hasNext())
            {
               EObject item = (EObject) contents.next();
               replaceReferences(targetModel, targetMap, item);
            }
            replaceReferences(targetModel, targetMap, targetElement);            
         }
      }
   }

   private static void replaceReferences(ModelType targetModel,
         Map<String, EObject> targetMap, EObject item)
   {
      EList<EObject> crossReferences = item.eCrossReferences();
      EContentsEList.FeatureIterator<EObject> featureIterator = (EContentsEList.FeatureIterator<EObject>) crossReferences.iterator();
      // make a copy so that processing will not destroy the live iterator
      List<EObject> objects = CollectionUtils.newList();
      List<EStructuralFeature> references = CollectionUtils.newList();
      while (featureIterator.hasNext())
      {
         objects.add(featureIterator.next());
         references.add(featureIterator.feature());
      }
      // now process
      for (int i = 0; i < objects.size(); i++)
      {
         EObject eObject = objects.get(i);
         ModelType externalModel = ModelUtils.findContainingModel(eObject);
		 if (externalModel != targetModel)
         {
            EObject replacement = null;
            if (ShareUtils.isLockableElement(eObject))
            {
               String uuid = UUIDUtils.getUUID(eObject);
               if (uuid != null)
               {
                  replacement = targetMap.get(uuid);
               }
            }
            else if (externalModel == null && XSDPackage.eINSTANCE.equals(eObject.eClass().getEPackage()))
            {
               // (fh) no replacement here since it is a reference to an external schema element 
               continue;
            }
            else if (externalModel != null)
            {
               replacement = getSameModelElement(eObject, targetModel, externalModel);
            }
            
            EStructuralFeature feature = references.get(i);
            if (feature.isChangeable())
            {
               if (replacement == null || objects.contains(replacement))
               {
                  EcoreUtil.remove(item, feature, eObject);
               }
               else
               {
                  EcoreUtil.replace(item, feature, eObject, replacement);
               }
            }
         }
      }
      if (item instanceof AttributeType)
      {
	     AttributeType attribute = (AttributeType) item;
	     IdentifiableReference reference = attribute.getReference();
	     if (reference != null)
	     {
	    	replaceReferences(targetModel, targetMap, reference);
	     }
      }
   }

   public static EObject getSameModelElement(EObject object, ModelType targetModel, ModelType sourceModel)
   {
	  if (object == null)
	  {
		 return null;
	  }
	  if (object == sourceModel)
	  {
		 return targetModel;
	  }
	  EObject targetParent = getSameModelElement(object.eContainer(), targetModel, sourceModel);
	  if (targetParent != null)
	  {
        if (object instanceof ExternalPackage)
        {
           return ((ExternalPackages) targetParent).getExternalPackage(((ExternalPackage) object).getId());
        }
	     
         if (object instanceof TypeDeclarationType)
		 {
		    return ((TypeDeclarationsType) targetParent).getTypeDeclaration(((TypeDeclarationType) object).getId());
		 }
		 EStructuralFeature feature = object.eContainingFeature();
		 Object obj = targetParent.eGet(feature);
		 // (fh) not sure if FeatureMap handling is correct
		 if (obj instanceof FeatureMap)
		 {
			obj = ((FeatureMap) obj).get(object.eContainmentFeature(), true);
		 }
		 if (obj instanceof List)
		 {
		    @SuppressWarnings("unchecked")
			List<? extends EObject> list = (List<? extends EObject>) obj;
            if (object instanceof IIdentifiableElement)
			{
			   return ModelUtils.findElementById(list, ((IIdentifiableElement) object).getId());
			}
			else if (object instanceof IModelElement)
			{
			   return ModelUtils.findElementByOid(list, ((IModelElement) object).getElementOid());
			}
			else if (XSDPackage.eINSTANCE.equals(object.eClass().getEPackage()))
			{
			   return ModelUtils.findElementByFeature(list, object, "name"); //$NON-NLS-1$
			}
			// (fh) other cases ?
		 }
		 else if (obj instanceof EObject)
		 {
			return (EObject) obj;
		 }
	  }
	  return null;
   }

   public static IResource[] removeElements(List<? extends EObject> elements, ModelType model, IProject project, ModelType sameModel)
   {
      List<IFile> lockfiles = new ArrayList<IFile>();
      for (EObject element : elements)
      {
         element = CopyPasteUtil.getSameModelElement(element, model, null);                     
         
         EObject parent = element instanceof TypeDeclarationType
            ? model.getTypeDeclarations() : model;
         
         // commit
         IFolder lockFolder = null;
         if (sameModel != null)
         {
            lockFolder = LockFileUtils.getLockFolder(element, sameModel);            
         }
         else
         {
            lockFolder = LockFileUtils.getLockFolder(element, model);
         }
         
         MergeUtils.deleteElement(element, parent);
         
         IFile file = LockFileUtils.getFile(lockFolder, element);
         if (file != null && file.exists())
         {
            lockfiles.add(file);
         }
      }      
      return lockfiles.toArray(new IResource[lockfiles.size()]);
   }

   public static void unsetUUIDs(List<? extends EObject> domain)
   {
      for (int i = 0; i < domain.size(); ++i)
      {
         EObject me = domain.get(i);
         UUIDUtils.unsetUUID(me);
      }
   }
   
   public static IProject getProject(EObject eObject)
   {
      return ShareUtils.getProject(eObject.eResource());
   }
   
   public static ElementReference extractElementReference(String path)
   {
      try 
      {
         char pathSeparator = '/'; //$NON-NLS-1$;
         if (path.indexOf(pathSeparator) == -1)
         {
            pathSeparator = '\\'; //$NON-NLS-1$;
         }
         
         String lockFileName = path.substring(path.lastIndexOf(pathSeparator)+1);
         String fileName = lockFileName.substring(0, lockFileName.lastIndexOf('.'));
         String elementTypeName = fileName.substring(0, fileName.indexOf("__")); //$NON-NLS-1$;
         EReference elementType = getElementType(elementTypeName);
         String elementId = fileName.substring(fileName.indexOf("__") + 2); //$NON-NLS-1$;
         
         if (elementType != null && elementId != null)
         {
            return new ElementReference(elementType, elementId);
         }
         else
         {
            return null;
         }
      }
      catch (Exception e)
      {
         // ignore if can not extract, since unrelated paths may be contained
         return null;
      }
   }
   
   private static EReference getElementType(String elementTypeName)
   {
      if (PKG_CWM.getModelType_Application().getName().equals(elementTypeName))
      {
         return PKG_CWM.getModelType_Application();
      }
      else if (PKG_CWM.getModelType_ConditionalPerformer().getName().equals(elementTypeName))
      {
         return PKG_CWM.getModelType_ConditionalPerformer();
      }
      else if (PKG_CWM.getModelType_Organization().getName().equals(elementTypeName))
      {
         return PKG_CWM.getModelType_Organization();
      }
      else if (PKG_CWM.getModelType_Role().getName().equals(elementTypeName))
      {
         return PKG_CWM.getModelType_Role();
      }
      else if (PKG_CWM.getModelType_Data().getName().equals(elementTypeName))
      {
         return PKG_CWM.getModelType_Data();
      }
      else if (PKG_CWM.getModelType_ProcessDefinition().getName().equals(elementTypeName))
      {
         return PKG_CWM.getModelType_ProcessDefinition();
      }
      else if (PKG_CWM.getModelType_Diagram().getName().equals(elementTypeName))
      {
         return PKG_CWM.getModelType_Diagram();
      }
      else if (PKG_CWM.getModelType_LinkType().getName().equals(elementTypeName))
      {
         return PKG_CWM.getModelType_LinkType();
      }
      else if (XpdlPackage.Literals.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION.getName().equals(elementTypeName))
      {
         return XpdlPackage.Literals.TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION;
      }
      return null;
   }
   
   public static EObject findByUUID(ModelType model, String uuid)
   {
      Map<String, EObject> map = createUuidToElementMap(model);
      return (EObject) map.get(uuid);
   }
   
   public static boolean isChanged(EObject local, EObject remote)
   {
      EcoreUtil.EqualityHelper eqHelper = new EcoreUtil.EqualityHelper()
      {
         /********** start custom code **********/
         private static final long serialVersionUID = 1L;
         
         private boolean referenceTraversed = false;

		private Stack<EStructuralFeature> currentFeature = new Stack<EStructuralFeature>();
         /********** end custom code **********/

         // (fh) copied and adapted from super class
         public boolean equals(EObject eObject1, EObject eObject2)
         {
            // If the first object is null, the second object must be null.
            if (eObject1 == null)
            {
               return eObject2 == null;
            }
            
            // We know the first object isn't null, so if the second one is, it can't be equal.
            if (eObject2 == null)
            {
               return false;
            }

            // Both eObject1 and eObject2 are not null.
            // If eObject1 has been compared already...
            Object eObject1MappedValue = get(eObject1);
            if (eObject1MappedValue != null)
            {
               // Then eObject2 must be that previous match.
               return eObject1MappedValue == eObject2;
            }

            // If eObject2 has been compared already...
            Object eObject2MappedValue = get(eObject2);
            if (eObject2MappedValue != null)
            {
               // Then eObject1 must be that match.
               return eObject2MappedValue == eObject1;
            }

            // Neither eObject1 nor eObject2 have been compared yet.
            // If eObject1 and eObject2 are the same instance...
            if (eObject1 == eObject2)
            { 
               // Match them and return true.
               put(eObject1, eObject2);
               put(eObject2, eObject1);
               return true;
            }

            // If they don't have the same class, they can't be equal.
            EClass eClass = eObject1.eClass();
            if (eClass != eObject2.eClass())
            {
               return false;
            }

            // Assume from now on that they match.
            put(eObject1, eObject2);
            put(eObject2, eObject1);

            /********** start custom code **********/
            if (referenceTraversed)
            {
               // If they have the same uuid are the same objects :-)
               String uuid1 = UUIDUtils.getUUID(eObject1);
               String uuid2 = UUIDUtils.getUUID(eObject2);
               if (uuid1 != null && uuid1.equals(uuid2))
               {
                  // (fh) referencing the "same" collision element
                  return true;
               }
               // (fh) identifiables are referenced by *id* always
               if (eObject1 instanceof IIdentifiableElement && eObject1.eContainer() instanceof ModelType)
               {
                  return CompareHelper.areEqual(((IIdentifiableElement) eObject1).getId(), ((IIdentifiableElement) eObject2).getId());
               }
            }
            /********** end custom code **********/

            // Check all the values.
            for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i)
            {
               // Ignore derived and transient features.
               EStructuralFeature feature = eClass.getEStructuralFeature(i);
               if (!feature.isDerived() && !feature.isTransient())
               {
                  /********** start custom code **********/
                  // ignore oids in comparisons
                  if (CarnotWorkflowModelPackage.eINSTANCE.getIModelElement_ElementOid().equals(feature))
                  {
                     continue;
                  }
                  
                  // ignore org.w3c.Documents in comparisons because they have identity equals.
                  EClassifier classifier = feature.getEType();
                  Class<?> clazz = classifier.getInstanceClass();
                  if (Node.class.isAssignableFrom(clazz))
                  {
                     continue;
                  }
                  
                  // Workaround (fh): special treatment of ModelTypes: exclude from compared features all extensibles.
                  if (eObject1 instanceof ModelType && classifier instanceof EClass &&
                           (CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement().isSuperTypeOf((EClass) classifier)
                         || XpdlPackage.eINSTANCE.getTypeDeclarationsType().isSuperTypeOf((EClass) classifier)))
                  {
                     continue;
                  }
                  /********** end custom code **********/
                  
                  if (!haveEqualFeature(eObject1, eObject2, feature))
                  {
                     // System.out.println("Difference encountered in feature: '" + feature.getName() + "' of object: '" + eObject1.eClass().getName() + "'. " + feature.getDefaultValue());
                     return false;
                  }
               }
            }
            
            // There's no reason they aren't equal, so they are.
            return true;
         }

         protected boolean haveEqualReference(EObject object1, EObject object2,
               EReference reference)
         {
            Object value1 = object1.eGet(reference);
            Object value2 = object2.eGet(reference);
            
            // special case, referenced object is removed local, but not commited
            if (value1 == null && value2 != null)
            {                  
               String uuid2 = UUIDUtils.getUUID((EObject) value2);
               if(!StringUtils.isEmpty(uuid2))
               {
                  ModelType localModel = ModelUtils.findContainingModel(object1);
                  EObject compareElement = findByUUID(localModel, uuid2);
                  if(compareElement == null)
                  {
                     return true;                     
                  }
               }
               // special case, external references
               else
               {
                  if(object1 instanceof DataType)
                  {
                     if(((DataType) object1).getExternalReference() != null)
                     {
                        return true;                     
                     }
                  }
                  if(object1 instanceof ActivityType)
                  {
                     if(((ActivityType) object1).getExternalRef() != null)
                     {
                        return true;                     
                     }            
                  }
                  if(object1 instanceof ProcessDefinitionType)
                  {
                     if(((ProcessDefinitionType) object1).getExternalRef() != null)
                     {
                        return true;                     
                     }  
                  }                           
               }
            }            
            
            /********** start custom code **********/
            referenceTraversed = true;
            /********** end custom code **********/
            return super.haveEqualReference(object1, object2, reference);
         }

         protected boolean haveEqualFeature(EObject eObject1, EObject eObject2, EStructuralFeature feature)
         {
            /********** start custom code **********/
        	try
        	{
        	   currentFeature .push(feature);
               if (CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement_Attribute().equals(feature))
               {
                  return haveEqualDynamicAttribute(eObject1, eObject2);
               }
               // (fh) overridden to ignore isSet check 
               return feature instanceof EReference ?
                  haveEqualReference(eObject1, eObject2, (EReference)feature) :
                  haveEqualAttribute(eObject1, eObject2, (EAttribute)feature);
        	}
        	finally
        	{
        	   currentFeature.pop();
        	}
            /********** end custom code **********/
         }

         @Override
         protected boolean haveEqualAttribute(EObject object1, EObject object2,
               EAttribute attribute)
         {
            // (fh) ignore value if is a reference dynamic attribute
            if (object1 instanceof AttributeType && object2 instanceof AttributeType
                  && attribute == CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value()
                  && AttributeUtil.isReference((AttributeType) object1)
                  && AttributeUtil.isReference((AttributeType) object2))
            {
               return equals(((AttributeType) object1).getReference().getIdentifiable(),
                     ((AttributeType) object2).getReference().getIdentifiable());
            }
            return super.haveEqualAttribute(object1, object2, attribute);
         }

         @Override
         public boolean equals(List<EObject> list1, List<EObject> list2)
         {
        	/*EStructuralFeature feature = currentFeature.peek();
        	boolean ordered = true;
        	if (XpdlPackage.eINSTANCE.getTypeDeclarationsType_TypeDeclaration().equals(feature))
        	{
        	   ordered = false;
        	}
        	if (CarnotWorkflowModelPackage.eINSTANCE.getModelType().equals(feature.getContainerClass()))
        	{
        	   ordered = false;
        	}*/
        	 
            int size = Math.max(list1.size(), list2.size());
            
            for (int i = 0; i < size; i++)
            {
               EObject eObject1 = i < list1.size() ? list1.get(i) : null;
               EObject eObject2 = i < list2.size() ? list2.get(i) : null;
               
               String uuid1 = UUIDUtils.getUUID(eObject1);
               String uuid2 = UUIDUtils.getUUID(eObject2);
               if (uuid1 != null && uuid2 != null)
               {
            	  if (uuid1.equals(uuid2))
            	  {
            		 // TODO (fh) check what should be here !
            	  }
               }
                  
               if (!equals(eObject1, eObject2))
               {
                  if (eObject1 == null)
                  {
                     // 
                  }              
                  else if (eObject2 == null)
                  {
                     return false;
                  }              
                  else
                  {
                     return false;                    
                  }
               }
            }
            return true;
         }

         /********** start custom code **********/
         @SuppressWarnings("unchecked")
         private boolean haveEqualDynamicAttribute(EObject object1, EObject object2)
         {
            ModelType localModel = ModelUtils.findContainingModel(object1);
            ModelType remoteModel = ModelUtils.findContainingModel(object2);
            
            List<AttributeType> list1 = (List<AttributeType>) object1.eGet(
                  CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement_Attribute());
            List<AttributeType> list2 = (List<AttributeType>) object2.eGet(
                  CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement_Attribute());

            List<AttributeType> outer = list1;
            List<AttributeType> inner = list2;
            int size = list1.size();
            if (size != list2.size())
            {
               if (list2.size() > size)
               {
                  outer = list2;
                  inner = list1;
               }                  
            }

            // order is irrelevant!
            for (AttributeType attribute2 : outer)
            {
               boolean found = false;
               for (AttributeType attribute1 : inner)
               {
                  if (attribute1.getName().equals(attribute2.getName()))
                  {
                     found = true;
                     if (!equals(attribute1, attribute2))
                     {
                        return false;
                     }
                     break;
                  }
               }
               if (!found)
               {
                  if(AttributeUtil.isReference(attribute2))
                  {
                     // special case, referenced object is removed local, but not commited
                     EObject object = AttributeUtil.getReferenceElement(attribute2);
                     String uuid2 = UUIDUtils.getUUID((EObject) object);
                     if(!StringUtils.isEmpty(uuid2))
                     {
                        ModelType model = ModelUtils.findContainingModel(object);
                        if(model.equals(remoteModel))
                        {
                           EObject compareElement = findByUUID(localModel, uuid2);
                           if(compareElement == null)
                           {
                              return true;                     
                           }
                        }
                     }
                  }                  
                  return false;
               }
            }
            return true;
         }
         /********** end custom code **********/
      };
      return !eqHelper.equals(local, remote);
   }
   
   public static void refreshTreeItem(ModelType model)
   {
      Map<String, EObject> localMap = ModelServerUtils.createUuidToElementMap(model);      
      refreshTreeItems(localMap.values());                  
   }

   public static void refreshTreeItems(Collection<EObject> elements)
   {
      for (EObject element : elements)
      {
         CreationUtils.refreshTreeItem(element);
      }
   }
   
   public static Image getIconWithOverlay(IMergeUtils mergeUtils, EObject element, Image baseImage)
   {
      if (mergeUtils.isRemovedElement(element))
      {
         return getIconWithOverlay(baseImage, imageDeleted, IDecoration.TOP_RIGHT);
      }                  
      if (mergeUtils.isNewElement(element))
      {
         return getIconWithOverlay(baseImage, imageAdded, IDecoration.TOP_RIGHT);
      }                  
      if (mergeUtils.hasChanged(element))
      {
         return getIconWithOverlay(baseImage, imageEdited, IDecoration.TOP_RIGHT);
      }
      return null;
   }

   private static Map<Integer, Map<Image, Map<Image, Image>>> imageCache = new HashMap<Integer, Map<Image, Map<Image, Image>>>();
   
   public static Image getIconWithOverlay(Image baseImage, Image overlay, int location)
   {
      if (baseImage != null && overlay != null)
      {
         Map<Image, Map<Image, Image>> locationCache = imageCache.get(location);
         if (locationCache == null)
         {
            locationCache = new HashMap<Image, Map<Image,Image>>();
            imageCache.put(location, locationCache);
         }
         Map<Image, Image> cache = locationCache.get(overlay);
         if (cache == null)
         {
            cache = new HashMap<Image, Image>();
            locationCache.put(overlay, cache);
         }
         Image icon = cache.get(baseImage);
         if (icon == null)
         {
            icon = new IconWithOverlays(baseImage, overlay, location).createImage();  
            cache.put(baseImage, icon);
         }
         return icon;
      }
      return null;
   }
}