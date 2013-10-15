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
package org.eclipse.stardust.modeling.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.engine.extensions.ejb.SessionBeanConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.BpmUiActivator;
import org.eclipse.stardust.modeling.common.ui.IWorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.*;
import org.eclipse.stardust.modeling.javascript.editor.EditorUtils;
import org.eclipse.ui.PlatformUI;

public class GenericUtils
{
   private static final String[] EMPTY = {};
   
   public static String getLocationRelativeToClasspath(IFile file)
   {
      String fileName = file.toString().substring(1); // strip resource type identifier
      try
      {
         IProject project = file.getProject();
         if (project != null && project.hasNature(JavaCore.NATURE_ID))
         {
            IJavaProject rootJavaProject = JavaCore.create(project);
            //also search in required project of the javaproject
            List<IJavaProject> javaProjectsToScan = new ArrayList<IJavaProject>();
            javaProjectsToScan.add(rootJavaProject);
            javaProjectsToScan.addAll(GenericUtils.getRequiredProjects(rootJavaProject));
            
            for(IJavaProject javaProject: javaProjectsToScan)
            {
               IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();
               for (int i = 0; i < roots.length; i++)
               {
                  IResource resource = roots[i].getCorrespondingResource();
                  if (resource instanceof IFolder)
                  {
                     String parent = resource.toString().substring(1); // strip resource type identifier
                     if (fileName.startsWith(parent))
                     {
                        return fileName.substring(parent.length());
                     }
                  }
               }
            }
         }
      }
      catch(Exception e)
      {}
      
      return fileName;
   }
   
   public static IFile cleanFileStructure(EObject modelElement, String filename)
   {
      ModelType model = ModelUtils.findContainingModel(modelElement);
      IProject project = ModelUtils.getProjectFromEObject(modelElement);

      EditorUtils.deleteFileStructure(project, model);      
      try
      {
         EditorUtils.addJSSupport(project, model);
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }

      return EditorUtils.createFileStructure(project, model, filename); //$NON-NLS-1$
   }   
   
   public static WorkflowModelEditor getWorkflowModelEditor(ModelType model)
   {
      IWorkflowModelEditor editor = BpmUiActivator.findWorkflowModelEditor(model);
      return editor instanceof WorkflowModelEditor ? (WorkflowModelEditor) editor : null;
   }
   
   public static String getElementId(EObject eObject)
   {
      if (eObject instanceof TypeDeclarationType)
      {
         return ((TypeDeclarationType)eObject).getId();
      }
      else if (eObject instanceof IIdentifiableElement)
      {
         return ((IIdentifiableElement)eObject).getId();
      }
      else if (eObject instanceof DiagramType)
      {
         return ((DiagramType)eObject).getName();
      }      
      return null;
   }
   
   public static IModelParticipant getLanePerformerForActivity(ActivityType activity)
   {
      IModelParticipant performer = null;
      
      List<ActivitySymbolType> symbols = activity.getActivitySymbols();
      for(ActivitySymbolType symbol : symbols)
      {
         EObject container = symbol.eContainer();
         if(container instanceof LaneSymbol)
         {
            IModelParticipant laneParticpant = ((LaneSymbol) container).getParticipantReference();
            if(laneParticpant != null)
            {
               if(performer == null)
               {
                  performer = laneParticpant;
               }
               else
               {
                  // not unique
                  if(!performer.equals(laneParticpant))
                  {
                     performer = null;
                     break;
                  }
               }
            }
         }            
      }      
      return performer;
   }
   
   // is valid for paste and dnd, returns the real target or null
   public static EditPart isValidTargetEditPart(EditPart targetEP)
   {
      if(targetEP != null 
            && (targetEP instanceof LaneEditPart
            || targetEP instanceof PoolEditPart
            || targetEP instanceof DiagramEditPart
            || targetEP instanceof DiagramRootEditPart))
      {      
         if(targetEP instanceof DiagramRootEditPart)
         {
            targetEP = (DiagramEditPart) ((DiagramRootEditPart) targetEP).getChildren().get(0);
         }
         
         EditPart iterateEP = targetEP;
         while(!(iterateEP instanceof DiagramEditPart))
         {
            iterateEP = iterateEP.getParent();         
         }
         DiagramEditPart diagramEP = (DiagramEditPart) iterateEP;
         DiagramType diagram = (DiagramType) diagramEP.getModel();      
         
         if(ModelUtils.findContainingProcess(diagram) != null)
         {
            if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL))
            {
               if(targetEP instanceof AbstractSwimlaneEditPart)
               {
                  if(PoolLaneUtils.containsLanes(targetEP))
                  {
                     return null;
                  }                  
                  if(targetEP instanceof LaneEditPart)
                  {
                     if(((LaneEditPart) targetEP).getLaneFigure().isCollapsed())
                     {
                        return null;
                     }
                  }
               }               
               // in BPMN Mode do not paste outside pool
               if(targetEP instanceof DiagramEditPart)
               {
                  return null;
               }                                             
            }
            // if target is diagram EP in Classic Mode then target Object is the default Pool
            else
            {
               if(targetEP instanceof DiagramEditPart)
               {
                  targetEP = ((DiagramEditPart) targetEP).getPoolDelegate();
               }
            }
         }
         return targetEP;
      }
      return null;
   }
   
   public static EditPart getTargetEditPart(WorkflowModelEditor editor)
   {
      // find real target EP
      EditPart targetEP = PoolLaneUtils.findTargetEditPart(editor);
      return isValidTargetEditPart(targetEP);
   }
   
   
   public static IFile getFile(List<IJavaProject> projectsToScan, String url)
   {
      IFile file = null;
      try
      {         
         for(IJavaProject projectToScan: projectsToScan)
         {
            IPackageFragmentRoot[] roots = projectToScan.getPackageFragmentRoots();
            for (int i = 0; i < roots.length; i++)
            {
               IResource resource = roots[i].getCorrespondingResource();
               if (resource instanceof IFolder)
               {
                  IFolder folder = (IFolder) resource;
                  file = folder.getFile(url);
                  if (file.exists())
                  {
                     return file;
                  }
                  file = null;
               }
            }
         }         
      }
      catch(Exception e)
      {
         file = null;         
      }
      return file;
   }
   
   public static IFile getFile(IJavaProject javaProject, String url, boolean scanRequiredProjects)
   {
      List<IJavaProject> projectsToScan = new ArrayList<IJavaProject>();
      projectsToScan.add(javaProject);
      if(scanRequiredProjects)
      {
         projectsToScan.addAll(getRequiredProjects(javaProject));
      }
      
      return getFile(projectsToScan, url);
   }
   
   // check if xsd file exists
   public static IFile getFile(IProject targetProject, String url)
   {
      IFile file = null;
      try
      {         
         if (targetProject.hasNature(JavaCore.NATURE_ID))
         {
            IJavaProject javaProject = JavaCore.create(targetProject);
            return getFile(javaProject, url, false);
         }
      }
      catch(Exception e)
      {
         file = null;         
      }
      return file;
   }   
   
   // get rectangle and check if we have values -1 inside
   public static Rectangle getSymbolRectangle(EditPart editPart)
   {
      if(editPart instanceof DiagramEditPart)
      {
         IFigure figure = (Figure) ((GraphicalEditPart) editPart).getFigure();
         return figure.getBounds().getCopy();
      }
      
      INodeSymbol symbol = (INodeSymbol) editPart.getModel();
      IFigure figure = (Figure) ((GraphicalEditPart) editPart).getFigure();
      
      Rectangle symbolRectangle = new Rectangle(new Long(symbol.getXPos()).intValue(), 
            new Long(symbol.getYPos()).intValue(), 
            symbol.getWidth(), symbol.getHeight());
      
      if(symbolRectangle.height == -1 || symbolRectangle.width == -1)
      {
         Dimension preferenceSize = figure.getPreferredSize();
         if(symbolRectangle.height == -1)
         {
            symbolRectangle.height = preferenceSize.height;
         }
         if(symbolRectangle.width == -1)
         {
            symbolRectangle.width = preferenceSize.width;
         }
      }      
      return symbolRectangle;
   }
   
   public static boolean isXMLDataType(DataType data)
   {
      // XML
      if (data.getType().getId().equals(PredefinedConstants.PLAIN_XML_DATA))
      {
         return true;
      }
      return false;
   }
   
   public static boolean isDMSDataType(DataType data)
   {
      // DMS
      if (data.getType().getId().equals(org.eclipse.stardust.engine.core.compatibility.extensions.dms.DmsConstants.DATA_TYPE_ID_DOCUMENT)
            || data.getType().getId().equals(org.eclipse.stardust.engine.core.compatibility.extensions.dms.DmsConstants.DATA_TYPE_ID_DOCUMENT_SET)
            || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_DOCUMENT)
            || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST)
            || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_FOLDER)
            || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_FOLDER_LIST))
      {
         return true;         
      }   
      return false;
   }   

   public static boolean isStructuredDataType(DataType data)
   {
      return data.getType().getId().equals(PredefinedConstants.STRUCTURED_DATA);
   }
   
   // return class name that reflects the data
   public static String getReferenceClassName(DataType data)
   {
      String[] names = getReferenceClassNames(data);
      return names.length == 0 ? null : names[0];
   }
   
   // return class name that reflects the data
   public static String[] getReferenceClassNames(DataType data)
   {
      // DMS
      if (data.getType().getId().equals(org.eclipse.stardust.engine.core.compatibility.extensions.dms.DmsConstants.DATA_TYPE_ID_DOCUMENT)
            || data.getType().getId().equals(org.eclipse.stardust.engine.core.compatibility.extensions.dms.DmsConstants.DATA_TYPE_ID_DOCUMENT_SET)
            || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_DOCUMENT)
            || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST)
            || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_FOLDER)
            || data.getType().getId().equals(DmsConstants.DATA_TYPE_DMS_FOLDER_LIST))
      {
         return new String[] {AttributeUtil.getAttributeValue(data, PredefinedConstants.CLASS_NAME_ATT)};         
      }   
      else if (data.getType().getId().equals(PredefinedConstants.PRIMITIVE_DATA))
      {
         String type = AttributeUtil.getAttributeValue(data, PredefinedConstants.TYPE_ATT);
         if (Type.Enumeration.getId().equals(type))
         {
            String typeDeclarationId = AttributeUtil.getAttributeValue(data, StructuredDataConstants.TYPE_DECLARATION_ATT);
            if (typeDeclarationId != null)
            {
               TypeDeclarationType typeDeclaration = StructuredTypeUtils.getTypeDeclaration(data);
               if (typeDeclaration != null)
               {
                  String enumClass = ExtendedAttributeUtil.getAttributeValue(typeDeclaration, PredefinedConstants.CLASS_NAME_ATT);
                  if (enumClass != null)
                  {
                     return new String[] {enumClass, String.class.getName()};
                  }
               }
            }
            
            // defaults to String
            return new String[] {String.class.getName()};
         }
         return new String[] {Reflect.getClassFromAbbreviatedName(type).getName()};
      }
      else if (data.getType().getId().equals(PredefinedConstants.HIBERNATE_DATA))
      {
         return new String[] {AttributeUtil.getAttributeValue(data, PredefinedConstants.CLASS_NAME_ATT)};         
      }      
      else if (data.getType().getId().equals(PredefinedConstants.SERIALIZABLE_DATA))
      {
         return new String[] {AttributeUtil.getAttributeValue(data, PredefinedConstants.CLASS_NAME_ATT)};
      }
      else if (data.getType().getId().equals(PredefinedConstants.ENTITY_BEAN_DATA))
      {
         // depends on implementation (EJB 2 or 3)
         String version = AttributeUtil.getAttributeValue(data, SessionBeanConstants.VERSION_ATT);
         if (version == null || version.equals(SessionBeanConstants.VERSION_2_X))
         {
            return new String[] {AttributeUtil.getAttributeValue(data, PredefinedConstants.REMOTE_INTERFACE_ATT)};            
         }
         return new String[] {AttributeUtil.getAttributeValue(data, PredefinedConstants.CLASS_NAME_ATT)}; 
      }
      else if (data.getType().getId().equals(PredefinedConstants.STRUCTURED_DATA))
      {
         String id = AttributeUtil.getAttributeValue(data, StructuredDataConstants.TYPE_DECLARATION_ATT);
         if (!StringUtils.isEmpty(id))
         {
            return new String[] {id};            
         }
      }
      return EMPTY;
   }
   
   // check if accessPoint is already connected
   public static boolean isConnected(ActivityType processActivity, String accessPointId)
   {
      ProcessDefinitionType process = ModelUtils.findContainingProcess(processActivity);
      for (ActivityType activity : process.getActivity())
      {
         for (DataMappingType dm : activity.getDataMapping())
         {
            String accessPoint = dm.getApplicationAccessPoint();
            if(accessPoint != null && accessPoint.equals(accessPointId))
            {
               return true;
            }
         }
      }
      return false;
   }

   // check if we can for this data try to use a TypeFinder
   public static boolean dataHasClassAssigned(DataType data)
   {
      if (data.getType().getId().equals(PredefinedConstants.PRIMITIVE_DATA)
            || data.getType().getId().equals(PredefinedConstants.HIBERNATE_DATA)
            || data.getType().getId().equals(PredefinedConstants.SERIALIZABLE_DATA)
            || data.getType().getId().equals(PredefinedConstants.ENTITY_BEAN_DATA))
      {
         return true;
      }
      return false;
   }      
   
   public static List<IJavaProject> getRequiredProjects(IJavaProject javaProject)
   {
      List<IJavaProject> requiredProjects = new ArrayList<IJavaProject>();
      try
      {
         String[] requiredProjectNames = javaProject.getRequiredProjectNames();
         IWorkspace workSpace = ResourcesPlugin.getWorkspace();
         IWorkspaceRoot workSpaceRoot = workSpace.getRoot();
         for(String requiredProjectName: requiredProjectNames)
         {
            IProject project = workSpaceRoot.getProject(requiredProjectName);
            if(project.hasNature(JavaCore.NATURE_ID) && project.exists())
            {
               IJavaProject requiredProject = JavaCore.create(project);
               requiredProjects.add(requiredProject);
            }
         }         
      }
      catch(JavaModelException e1)
      {
         
      }
      catch(CoreException e2)
      {
         
      }
      
      return requiredProjects;
   }
      
   public static boolean getAutoIdValue()
   {
      return PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION);      
   }
}