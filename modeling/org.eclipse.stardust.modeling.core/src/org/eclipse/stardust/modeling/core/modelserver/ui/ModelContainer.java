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
package org.eclipse.stardust.modeling.core.modelserver.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.ui.IModelElementContainer;
import org.eclipse.swt.graphics.Image;


public class ModelContainer 
{
   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;
   
   private Container container = new Container();
   private WorkflowModelEditor editor;

   public ModelContainer(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public Container getContainer()
   {
      return container;
   }      
   
   public class Container implements IModelElementContainer
   {
      private ModelType model;      
      
      private TypeDeclarations typeDeclarations = new TypeDeclarations();
      private Applications applications = new Applications();
      private Datas datas = new Datas();
      private Participants participants = new Participants();
      
      private List processDefinitions;

      private List diagrams;
      private List linkTypes;
      
      public void setModel(ModelType model)
      {
         this.model = model;
      }

      public void setProcessDefinitions(List processDefinitions)
      {
         this.processDefinitions = processDefinitions;
      }

      public void setDiagrams(List diagrams)
      {
         this.diagrams = diagrams;
      }

      public void setLinkTypes(List linkTypes)
      {
         this.linkTypes = linkTypes;
      }
      
      public TypeDeclarations getTypeDeclarations()
      {
         return typeDeclarations;
      }
   
      public Applications getApplications()
      {
         return applications;
      }
   
      public Datas getDatas()
      {
         return datas;
      }
   
      public Participants getParticipants()
      {
         return participants;
      }
      
      /////
      
      public List getContent()
      {
         List content = new ArrayList();
         if(model != null)
         {
            content.add(model);            
         }       
         if(!getTypeDeclarations().getContent().isEmpty())
         {
            content.add(getTypeDeclarations());
         }
         if(!getApplications().getContent().isEmpty())
         {
            content.add(getApplications());
         }
         if(!getDatas().getContent().isEmpty())
         {
            content.add(getDatas());      
         }
         if(!getParticipants().getContent().isEmpty())
         {
            content.add(getParticipants()); 
         }
         if(processDefinitions != null && !processDefinitions.isEmpty())
         {
            Iterator it = processDefinitions.iterator(); 
            while (it.hasNext()) 
            {
               EObject element = (EObject) it.next();
               content.add(element);
            }            
         }
         if(diagrams != null && !diagrams.isEmpty())
         {
            Iterator it = diagrams.iterator(); 
            while (it.hasNext()) 
            {
               EObject element = (EObject) it.next();
               content.add(element);
            }            
         }
         if(linkTypes != null && !linkTypes.isEmpty())
         {
            Iterator it = linkTypes.iterator(); 
            while (it.hasNext()) 
            {
               EObject element = (EObject) it.next();
               content.add(element);
            }            
         }         
         return content;
      }
   
      public Image getImage()
      {
         return null;
      }
   
      public String getLabel()
      {
         return Diagram_Messages.LBL_ALL_ELEMENTS;
      }
   
      public void setChecked(boolean checked)
      {
      }
   
      /////
      
      public class TypeDeclarations implements IModelElementContainer
      {
         List content = new ArrayList();
         
         public List getContent()
         {
            return content;
         }
   
         public Image getImage()
         {
            String iconPath = editor.getIconFactory().getIconFor(XpdlPackage.eINSTANCE.getTypeDeclarationsType());
            return DiagramPlugin.getDefault().getImageManager().getImage(iconPath);
         }
   
         public String getLabel()
         {
            return Diagram_Messages.STRUCTURED_DATA_LABEL;
         }
   
         public void setChecked(boolean checked)
         {
         }
      }   
   
      public class Applications implements IModelElementContainer
      {
         List content = new ArrayList();
         
         public List getContent()
         {
            return content;
         }
   
         public Image getImage()
         {
            String iconPath = editor.getIconFactory().getIconFor(PKG_CWM.getApplicationType());
            return DiagramPlugin.getDefault().getImageManager().getImage(iconPath);
         }
   
         public String getLabel()
         {
            return Diagram_Messages.LB_Applications;
         }
   
         public void setChecked(boolean checked)
         {
         }
      }
      
      public class Datas implements IModelElementContainer
      {
         List content = new ArrayList();
         
         public List getContent()
         {
            return content;
         }
   
         public Image getImage()
         {
            String iconPath = editor.getIconFactory().getIconFor(PKG_CWM.getDataType());
            return DiagramPlugin.getDefault().getImageManager().getImage(iconPath);            
         }
   
         public String getLabel()
         {
            return Diagram_Messages.DATA_LABEL;
         }
   
         public void setChecked(boolean checked)
         {
         }
      }
      
      public class Participants implements IModelElementContainer
      {
         List content = new ArrayList();
         
         public List getContent()
         {
            return content;
         }
   
         public Image getImage()
         {
            String iconPath = editor.getIconFactory().getIconFor(PKG_CWM.getIModelParticipant());
            return DiagramPlugin.getDefault().getImageManager().getImage(iconPath);            
         }
   
         public String getLabel()
         {
            return Diagram_Messages.LB_Participants;
         }
   
         public void setChecked(boolean checked)
         {
         }
      }
   }   
}