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
package org.eclipse.stardust.modeling.core.jobs;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor.EditorChangeTracker;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationPlugin;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.ValidatorRegistry;

public class ModelValidationJob extends WorkspaceJob
{
   private static final String EMF_RES_PREFIX = "/resource/"; //$NON-NLS-1$

   private final ModelType model;

   private final IResource modelFile;

   private Map<String, String> filters;
   
   private static final String LOCK = "ModelValidationJob.LOCK"; //$NON-NLS-1$
   
   private final WorkflowModelEditor editor;

   public ModelValidationJob(WorkflowModelEditor workflowModelEditor, ModelType model, Map<String, String> filters)
   {
      super(Diagram_Messages.TXT_WorkflowModelValidation); 

      this.model = model;
      this.editor = workflowModelEditor;
      
      Resource eResModel = model.eResource();
      IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
      String uriEResModel = eResModel.getURI().path();
      if (uriEResModel.startsWith(EMF_RES_PREFIX))
      {
         uriEResModel = uriEResModel.substring(EMF_RES_PREFIX.length());
      }
      this.modelFile = wsRoot.findMember(uriEResModel);
      this.filters = filters;
   }

   public ModelType getModel()
   {
      return model;
   }

   public IResource getModelFile()
   {
      return modelFile;
   }

   public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException
   {
      // todo: check the implications of locking from the performance point of view
      synchronized (LOCK)
      {
         ValidationPlugin plugin = ValidationPlugin.getDefault();
         if (plugin == null)
         {
            return Status.OK_STATUS;
         }
         
         ValidationService vs = ValidationService.getInstance();

         try
         {
            ValidatorRegistry.setFilters(filters);
            vs.setProgressMonitor(monitor);
            
            editor.getEditorChangeTracker().setEnabled(false);
            Issue[] issues = vs.validateModel(model);
            if (null != modelFile)
            {
               try
               {
                  // TODO try to perform update to existing markers to reduce change set size
      
                  modelFile.deleteMarkers(ValidationPlugin.VALIDATION_MARKER_ID, true,
                        IResource.DEPTH_INFINITE);
                  plugin = ValidationPlugin.getDefault();
                  if (plugin == null)
                  {
                     return Status.OK_STATUS;
                  }
                  vs.removeMappings(modelFile);
      
                  Map<String, Object> attr = CollectionUtils.newMap();
                  for (int i = 0; i < issues.length; i++)
                  {
                     attr.clear();
                     attr.put(IMarker.SEVERITY, new Integer(issues[i].isError()
                           ? IMarker.SEVERITY_ERROR
                           : issues[i].isWarning()
                                 ? IMarker.SEVERITY_WARNING
                                 : IMarker.SEVERITY_INFO));
      
                     attr.put(IMarker.MESSAGE, getLocations(issues[i].getMessage(), issues[i]
                           .getModelElement()));
                     
                     attr.put(ValidationPlugin.MARKER_ELEMENT,issues[i]
                                                      .getModelElement() );
      
                     if (null != issues[i].getModelElement())
                     {
                        if(issues[i].getModelElement() instanceof IModelElement)
                        {
                           attr.put(ValidationPlugin.VALIDATION_MARKER_ATTR_ELEMENT_OID,
                                 Long.toString(((IModelElement) issues[i].getModelElement()).getElementOid()));                           
                        }
                        else if(issues[i].getModelElement() instanceof TypeDeclarationType)
                        {
                           attr.put(ValidationPlugin.VALIDATION_MARKER_ATTR_ELEMENT_OID,
                                 ((TypeDeclarationType) issues[i].getModelElement()).getId());                           
                        }
                        else if(issues[i].getModelElement() instanceof ExternalPackage)
                        {
                           attr.put(ValidationPlugin.VALIDATION_MARKER_ATTR_ELEMENT_OID,
                                 ((ExternalPackage) issues[i].getModelElement()).getId());                           
                        }
                        String location = getLocation(issues[i].getModelElement());
                        if (location != null)
                        {
                           attr.put(IMarker.LOCATION, location);
                        }
                     }
                     IMarker marker = modelFile
                           .createMarker(ValidationPlugin.VALIDATION_MARKER_ID);
                     marker.setAttributes(attr);
      
                     vs.createMapping(marker,
                           issues[i]);
                  }
               }
               catch (CoreException e)
               {
                  // TODO: handle exception
               }
            }
         }
         finally
         {
            ValidatorRegistry.setFilters(null);
            vs.setProgressMonitor(null);
            if (editor != null)
            {
               EditorChangeTracker tracker = editor.getEditorChangeTracker();
               if (tracker != null)
               {
                  tracker.setEnabled(true);
               }
            }
         }
      }

      return Status.OK_STATUS;
   }

   private String getLocation(EObject modelElement)
   {
      List<String> path = CollectionUtils.newList();
      while (modelElement != null)
      {
         if (modelElement instanceof IIdentifiableElement
            && ((IIdentifiableElement) modelElement).getId() != null)
         {
            path.add(((IIdentifiableElement) modelElement).getId());
         }
         else if(modelElement instanceof IModelElement)
         {
            path.add(modelElement.eClass().getName() +
               "[" + ((IModelElement) modelElement).getElementOid() + "]"); //$NON-NLS-1$ //$NON-NLS-2$
         }
         else if(modelElement instanceof TypeDeclarationType)
         {
            path.add(modelElement.eClass().getName() +
               "[" + ((TypeDeclarationType) modelElement).getId() + "]"); //$NON-NLS-1$ //$NON-NLS-2$
         }
         else if(modelElement instanceof ExternalPackage)
         {
            path.add(modelElement.eClass().getName() +
               "[" + ((ExternalPackage) modelElement).getId() + "]"); //$NON-NLS-1$ //$NON-NLS-2$
         }
         if (modelElement.eContainer() instanceof IModelElement)
         {
            modelElement = (IModelElement) modelElement.eContainer();
         }
         else
         {
            break;
         }
      }
      if (!path.isEmpty())
      {
         StringBuffer sb = new StringBuffer();
         for (int i = path.size() - 1; i >= 0; i--)
         {
            String s = (String) path.get(i);
            sb.append(s);
            if (i > 0)
            {
               sb.append('.');
            }
         }
         return sb.toString();
      }
      return null;
   }

   private String getLocations(String message, EObject element)
   {
      String locations = ""; //$NON-NLS-1$

      if (null != element)
      {
         ProcessDefinitionType proc = ModelUtils.findContainingProcess(element);
         if (proc != null)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_ProcessDefinition, new Object[] { 
                  locations, proc.getName()});
         }
         
         ActivityType activity = ModelUtils.findContainingActivity(element);
         if (activity != null)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_Activity, new Object[] { 
                  locations, activity.getName()});
         }
         if (element instanceof DataPathType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_DataPath, new Object[] { 
                  locations, ((DataPathType) element).getName()});
         }
         
         EventHandlerType eventHandler = ModelUtils.findContainingEventHandlerType(element);
         if (eventHandler != null)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_EventHandler, new Object[] { 
                  locations, eventHandler.getName()});
         }
         
         if (element instanceof TriggerType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_Trigger, new Object[] { 
                  locations, ((TriggerType) element).getName()});
         }
         if (element instanceof TransitionType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_Transition, new Object[] { 
                  locations, ((TransitionType) element).getName()});
         }
         if (element instanceof EventActionType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_EventAction, new Object[] { 
                  locations, ((EventActionType) element).getName()});
         }
         if (element instanceof BindActionType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_BindAction, new Object[] { 
                  locations, ((BindActionType) element).getName()});
         }
         if (element instanceof UnbindActionType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_UnbindAction, new Object[] { 
                  locations, ((UnbindActionType) element).getName()});
         }
         if (element instanceof DataMappingType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_DataMapping, new Object[] { 
                  locations, ((DataMappingType) element).getId()});
         }
         
         // todo: add location for context types
         if (element instanceof ApplicationType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_Application, 
                  new Object[] {((ApplicationType) element).getName()});
         }
         else if (element instanceof ConditionalPerformerType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_ConditionalPerformer, 
                  new Object[] {((ConditionalPerformerType) element).getName()});
         }
         else if (element instanceof ModelerType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_Modeler, 
                  new Object[] {((ModelerType) element).getName()});
         }
         else if (element instanceof OrganizationType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_Organization, 
                  new Object[] {((OrganizationType) element).getName()});
         }
         else if (element instanceof RoleType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_Role, new Object[] {((RoleType) element) 
                  .getName()});
         }
         else if (element instanceof DataType)
         {
            locations = MessageFormat.format(Diagram_Messages.MSG_Data, new Object[] {((DataType) element) 
                  .getName()});
         }
      }

      String locationMessage = ""; //$NON-NLS-1$
      if (locations.length() == 0)
      {
         locationMessage = MessageFormat.format("{0}", new Object[] {message}); //$NON-NLS-1$
      }
      else
      {
         locationMessage = MessageFormat.format("{0} ({1})", new Object[] { //$NON-NLS-1$
               message, locations});
      }

      return locationMessage;
   }
}