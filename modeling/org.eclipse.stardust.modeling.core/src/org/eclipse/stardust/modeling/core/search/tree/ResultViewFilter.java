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
package org.eclipse.stardust.modeling.core.search.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;


public class ResultViewFilter
{
   private ModelType model;
   
   private int numberMatchedElements;
   private Map matchedElements;
   
   private List typeDeclarations;
   private List applications;
   private List datas;
   private List participants;
   private List linkTypes;
   private List modelDiagrams;
   private List processes;

   private List diagramProxies;  
   private Map diagramChildren;
   private Map currentDiagramChildren;
   
   // disable delete because it is only a container for unused model elements
   private List processProxies;  
   // can be deleted, is not referenced, children also are not referenced 
   private Map processChildren;
   private Map currentProcessChildren;
      
   // cache
   private List modelChildren;   
   
   public ResultViewFilter(ModelType model)
   {
      this.model = model;
      init(true);
   }

   public void setMatchedElements(Map matchedElements)
   {
      init(true);
      this.matchedElements = matchedElements;
      if(matchedElements == null 
            || matchedElements.isEmpty())
      {
         return;
      }      
      setContent();
      setChildren(false);
   }

   private void init(boolean clear)
   {
      numberMatchedElements = 0;
      
      typeDeclarations = new ArrayList();
      applications = new ArrayList();
      datas = new ArrayList();
      participants = new ArrayList();
      linkTypes = new ArrayList();
      modelDiagrams = new ArrayList();      
      
      processes = new ArrayList();                     
      currentProcessChildren = new HashMap();   
      currentDiagramChildren = new HashMap();
      
      if(clear)
      {
         modelChildren = new ArrayList();    
         diagramProxies = new ArrayList();      
         diagramChildren = new HashMap();
         processProxies = new ArrayList();      
         processChildren = new HashMap();            
      }
   }
   
   // from matched elements we fill our lists
   private void setContent()
   {      
      Iterator it = matchedElements.entrySet().iterator(); 
      while (it.hasNext()) 
      {
         Map.Entry entry = (Map.Entry) it.next();
         EObject container = (EObject) entry.getKey();
         List children = (List) entry.getValue();
      
         if(container instanceof ModelType)
         {
            modelChildren.addAll(children);
         }
         else if(container instanceof DiagramType)
         {
            diagramProxies.add(container);
            diagramChildren.put(container, children);
         }                  
         else if(container instanceof ProcessDefinitionType)
         {
            processProxies.add(container);            
            processChildren.put(container, children);
         }                  
      }
      it = diagramProxies.iterator();
      while (it.hasNext()) 
      {
         DiagramType diagram = (DiagramType) it.next();                   
         ProcessDefinitionType diagramProcess = org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils.findContainingProcess(diagram);
         if(diagramProcess == null)
         {
            modelChildren.add(diagram);
         }
         else
         {
            if(processProxies.contains(diagramProcess))
            {
               List containerChildren = (List) processChildren.get(diagramProcess);
               containerChildren.add(diagram);
               processChildren.put(diagramProcess, containerChildren);
            }
            else
            {
               processProxies.add(diagramProcess);            
               List containerChildren = new ArrayList();
               containerChildren.add(diagram);
               processChildren.put(diagramProcess, containerChildren);
            }            
         }
      }      
   }
   
// cache if model or matched elements have not changed   
   private void setChildren(boolean clear)
   {
      if(clear)
      {
         init(false);         
      }
      setModelChildren();
      setProcessChildren();      
      setDiagramChildren();
   }   
   
   // we need this only for type declarations, applications, participants, data
   private void setModelChildren()
   {
      for (Iterator i = modelChildren.iterator(); i.hasNext();)
      {
         EObject element = (EObject) i.next();                   
         if(element instanceof TypeDeclarationType
               && model.getTypeDeclarations().getTypeDeclaration().contains(element))
         {
            typeDeclarations.add(element);  
            numberMatchedElements++;
         }               
         else if(element instanceof ApplicationType
            && model.getApplication().contains(element))
         {
            applications.add(element);
            numberMatchedElements++;
         }
         else if(element instanceof DataType
               && model.getData().contains(element))
         {
            datas.add(element);            
            numberMatchedElements++;
         }
         else if(element instanceof RoleType
               && model.getRole().contains(element))
         {
            participants.add(element);            
            numberMatchedElements++;
         }
         else if(element instanceof OrganizationType
               && model.getOrganization().contains(element))
         {
            participants.add(element);            
            numberMatchedElements++;
         }
         else if(element instanceof ConditionalPerformerType
               && model.getConditionalPerformer().contains(element))
         {
            participants.add(element);            
            numberMatchedElements++;
         }
         else if(element instanceof LinkTypeType
               && model.getLinkType().contains(element))
         {
            linkTypes.add(element);            
            numberMatchedElements++;
         }
         else if(element instanceof DiagramType
               && model.getDiagram().contains(element))
         {
            modelDiagrams.add(element);
            numberMatchedElements++;
         }
         else if(element instanceof ProcessDefinitionType
               && model.getProcessDefinition().contains(element))
         {
            processes.add(element);
            numberMatchedElements++;
         }         
      }
   }

   private void setDiagramChildren()
   {
      for (Iterator i = diagramProxies.iterator(); i.hasNext();)
      {
         DiagramType diagram = (DiagramType) i.next(); 
         ProcessDefinitionType process = org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils.findContainingProcess(diagram);
         
         boolean foundElement = false;
         if(process != null)
         {
            if(model.getProcessDefinition().contains(process))
            {
               if(process.getDiagram().contains(diagram))
               {
                  foundElement = true;
               }
            }
         }
         else
         {
            if(model.getDiagram().contains(diagram))
            {
               foundElement = true;               
            }            
         }
         if(foundElement)
         {
            List containerChildren = (List) diagramChildren.get(diagram);
            List diagramChildren = new ArrayList();
            for (Iterator it = containerChildren.iterator(); it.hasNext();)
            {
               EObject element = (EObject) it.next();
               if(element instanceof IGraphicalObject)
               {     
                  ISymbolContainer symbolContainer;
                  PoolSymbol pool = DiagramUtil.getDefaultPool(diagram);
                  if(pool != null)
                  {
                     // check also all lanes until 1st match
                     // what if more than one matches?
                     symbolContainer = pool;
                  }
                  else
                  {
                     symbolContainer = diagram;
                  }               
                  List nodes = getSymbols(symbolContainer, (IModelElement) element);
                  if(nodes.contains(element))
                  {
                     diagramChildren.add(element);
                     numberMatchedElements++;                        
                  }                                       
               }
            }
            if(!diagramChildren.isEmpty())
            {
               currentDiagramChildren.put(diagram, diagramChildren);                           
            }
         }                  
      }
   }   
   
   private void setProcessChildren()
   {
      for (Iterator i = processProxies.iterator(); i.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) i.next();                   
         if(model.getProcessDefinition().contains(process))
         {
            List containerChildren = (List) processChildren.get(process);
            List processChildren = new ArrayList();
            for (Iterator it = containerChildren.iterator(); it.hasNext();)
            {
               EObject element = (EObject) it.next();
               if(element instanceof DiagramType
                     && process.getDiagram().contains(element))
               {
                  processChildren.add(element);
                  numberMatchedElements++;
               }               
               else if(element instanceof ActivityType
                     && process.getActivity().contains(element))
               {
                  processChildren.add(element);
                  numberMatchedElements++;
               }
               else if(element instanceof TriggerType
                     && process.getTrigger().contains(element))
               {
                  processChildren.add(element);
                  numberMatchedElements++;
               }
               else if(element instanceof TransitionType
                     && process.getTransition().contains(element))
               {
                  processChildren.add(element);
                  numberMatchedElements++;
               }
               else if(element instanceof DataPathType
                     && process.getDataPath().contains(element))
               {
                  processChildren.add(element);
                  numberMatchedElements++;
               }
            }
            if(!processChildren.isEmpty())
            {
               currentProcessChildren.put(process, processChildren);                           
            }
         }                  
      }
   }   

   public boolean isDiagramProxy(DiagramType diagram)
   {
      setChildren(true);
      if(diagramProxies.contains(diagram))
      {
         return true;
      }
      return false;
   }   
   
   public boolean isProcessProxy(ProcessDefinitionType process)
   {
      setChildren(true);
      if(processProxies.contains(process))
      {
         return true;
      }
      return false;
   }
   
   public List getTypeDeclarations()
   {
      setChildren(true);
      return typeDeclarations;
   }

   public List getApplications()
   {
      setChildren(true);
      return applications;
   }

   public List getDatas()
   {
      setChildren(true);
      return datas;
   }

   public List getParticipants()
   {
      setChildren(true);
      return participants;
   }

   public List getLinkTypes()
   {
      setChildren(true);
      return linkTypes;
   }

   public List getModelDiagrams()
   {
      setChildren(true);
      return modelDiagrams;
   }

   public List getModelChildren()
   {
      setChildren(true);
      List modelChildren = new ArrayList();
      if(!processProxies.isEmpty())
      {
         for (Iterator i = processProxies.iterator(); i.hasNext();)
         {
            ProcessDefinitionType process = (ProcessDefinitionType) i.next();                   
            if(model.getProcessDefinition().contains(process))
            {
               modelChildren.add(process);               
            }
         }         
      }      
      if(!processes.isEmpty())
      {
         modelChildren.addAll(processes);
      }
      if(!linkTypes.isEmpty())
      {
         modelChildren.addAll(linkTypes);
      }
      if(!diagramProxies.isEmpty())
      {
         for (Iterator i = diagramProxies.iterator(); i.hasNext();)
         {
            DiagramType diagram = (DiagramType) i.next();                   
            if(model.getDiagram().contains(diagram))
            {
               modelChildren.add(diagram);               
            }
         }         
      }      
      if(!modelDiagrams.isEmpty())
      {
         modelChildren.addAll(modelDiagrams);
      }      
      return modelChildren;
   }
   
   public List getDiagramChildren(DiagramType diagram)
   {
      setChildren(true);
      List children = (List) currentDiagramChildren.get(diagram);
      if(children == null)
      {
         children = new ArrayList();
      }
      return children;
   }      
   
   public List getProcessChildren(ProcessDefinitionType process)
   {
      setChildren(true);
      List children = (List) currentProcessChildren.get(process);
      if(children == null)
      {
         children = new ArrayList();
      }
      return children;
   }   
   
   private List getSymbols(ISymbolContainer container, IModelElement modelSymbol)
   {
      Iterator itr = container.getNodeContainingFeatures().iterator();       
      while(itr.hasNext())
      {
         EStructuralFeature feature = (EStructuralFeature) itr.next();
         if (feature.getEType().equals(modelSymbol.eClass()))
         {
            List list = (List) container.eGet(feature);
            return list;
         }
      }
      return new ArrayList();
   }   
   
   public int getNumberMatchedElements()
   {
      return numberMatchedElements;
   }
}