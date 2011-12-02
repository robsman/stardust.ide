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
package org.eclipse.stardust.modeling.templates.defaulttemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.Coordinates;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.RoutingType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.repository.common.ImportCancelledException;
import org.eclipse.stardust.modeling.templates.emf.template.DocumentationType;
import org.eclipse.stardust.modeling.templates.emf.template.ReferenceType;
import org.eclipse.stardust.modeling.templates.emf.template.RootsType;
import org.eclipse.stardust.modeling.templates.emf.template.StyleType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateType;
import org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatesTypeImpl;
import org.eclipse.stardust.modeling.templates.spi.ITemplate;
import org.eclipse.stardust.modeling.templates.spi.ITemplateFactory;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xsd.XSDSchema;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import ag.carnot.workflow.model.IDataType;

public class TemplateAdapter implements ITemplate
{
   private TemplateType template;
   private Copier copier;
   private ITemplateFactory parentTemplateFactory;
   private ModelType targetModel;
   private EditPart editPart;
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;  

   public TemplateAdapter(TemplateType template, ITemplateFactory templateFactory)
   {
      this.template = template;
      this.parentTemplateFactory = templateFactory;
   }

   public String getId()
   {
      return template.getId();
   }

   public String getName()
   {
      return template.getName();
   }

   public String getDescription()
   {
      if (template != null)
      {
         DocumentationType documentation = template.getDocumentation();
         if (documentation != null)
         {
            return documentation.getAsText();
         }
      }
      return null;
   }

   public String getCategory()
   {
      return template.getCategory();
   }

   public void applyTemplate(WorkflowModelEditor editor, ModelType targetModel,
         DiagramType targetDiagram, EditPart editPart, int xHint, int yHint)
   {      
      this.targetModel = targetModel;
      this.editPart = editPart;
      if (StyleType.EMBEDDED.equals(template.getStyle()))
      {
         embedTemplate(targetModel, targetDiagram, xHint, yHint);
      }
      else
      {
         // TODO
      }
      ModelUtils.resolve(targetModel, targetModel);
      
      if (template.getRoots() != null)
      {
         final TemplateParameterWizard wizard = new TemplateParameterWizard(targetModel, template, copier);
         WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard) {
            protected void cancelPressed()
            {
               // TODO Auto-generated method stub
               super.cancelPressed();
               setReturnCode(Window.CANCEL);
            }
            protected void finishPressed()
            {
               super.finishPressed();       
               TemplateParameterWizard w = (TemplateParameterWizard)wizard;
               if (w.isKilled()) {
                  this.setReturnCode(Window.CANCEL);
               }
            }            
         };
         if (dialog.open() == Window.CANCEL) {
            throw new ImportCancelledException();
         }  
      }
   }

   private void embedTemplate(ModelType targetModel, DiagramType targetDiagram, int x, int y)
   {
      RootsType roots = template.getRoots();
      if (roots != null)
      {
         Collection refs = copyAll(getRoots(roots.getRoot()));
         refs = replacePredefinedTypes(targetModel, refs); 
         refs = handleRoleTypes(targetModel, refs);
         for (Iterator i = refs.iterator(); i.hasNext();)
         {
            Object root = i.next();
            if (root instanceof ProcessDefinitionType)
            {
               embedProcessDefinition((ProcessDefinitionType) root, targetDiagram, x, y);
            }
            else if (root instanceof TypeDeclarationType)
            {
               targetModel.getTypeDeclarations().getTypeDeclaration().add(
                     (TypeDeclarationType) root);
            }
            else if (root instanceof DataType)
            {                                            
               DataType dataType = (DataType)root;
               DataTypeType dataTypeType =  (DataTypeType) ModelUtils.findElementById(targetModel, PKG.getModelType_DataType(), dataType.getType().getId());
               dataType.setType(dataTypeType);
               addObjectTo(targetModel, (IModelElement) root);
            }
            else if (root instanceof IModelElement)
            {                                            
               addObjectTo(targetModel, (IModelElement) root);
            }
         }
      }
   }

   private Collection replacePredefinedTypes(ModelType targetModel, Collection refs)
   {
      List result = new ArrayList();
      for (Iterator i = refs.iterator(); i.hasNext();)
      {
         EObject object = (EObject) i.next();
         if (object instanceof IIdentifiableElement)
         {
            IIdentifiableElement identifiable = (IIdentifiableElement) object;
            EStructuralFeature feature = getContainingFeature(targetModel, identifiable);
            if (feature != null && isReusable(identifiable))
            {
               EObject original = ModelUtils.findElementById(targetModel, feature, identifiable.getId());
               if (original != null && original.eClass() == identifiable.eClass())
               {
                  MergeUtils.replace(identifiable, original, true);
                  continue;
               }
            }
         }
         result.add(object);
      }
      return result;
   }
   
   private Collection handleRoleTypes(ModelType targetModel, Collection refs)
   {
      List result = new ArrayList();
      for (Iterator i = refs.iterator(); i.hasNext();)
      {
         EObject object = (EObject) i.next();
         if (object instanceof RoleType)
         {
            addObjectTo(targetModel, object);
         }
         result.add(object);
      }
      return result;
   }

   private boolean isReusable(IIdentifiableElement identifiable)
   {
      // we consider to be reusable all meta types, all model participant and predefined data objects
      if (identifiable instanceof IMetaType)
      {
         return true;
      }
      if (identifiable instanceof IModelParticipant)
      {
         // TODO: really ?
         return true;
      }
      if (identifiable instanceof IDataType)
      {
         IDataType data = (IDataType) identifiable;
         if (data.isPredefined())
         {
            return true;
         }
      }
      return false;
   }

   public Collection copyAll(Collection eObjects)
   {
      copier = new EcoreUtil.Copier()
      {
         private static final long serialVersionUID = 1L;
         
         public EObject copy(EObject object)
         {
            if(object instanceof XSDSchema)
            {
               XSDSchema original = (XSDSchema) object;
               XSDSchema clone = (XSDSchema) original.cloneConcreteComponent(true, false);
               Document doc = clone.updateDocument();
               if(original.getElement() != null)
               {            
                  Element clonedElement = (Element) doc.importNode(original.getElement(), true);
                  doc.appendChild(clonedElement);
                  clone.setElement(clonedElement);
               }
               return clone;                     
            }
            return super.copy(object);
         }               
      };
      Collection result = copier.copyAll(eObjects);
      copier.copyReferences();
      return result;
   }

   private List getRoots(List roots)
   {
      List result = new ArrayList();
      TemplatesTypeImpl templates = (TemplatesTypeImpl) template.eContainer();
      TemplateLibraryType library = (TemplateLibraryType) templates.eContainer();
      result.add(library.getModel());
      for (int i = 0; i < roots.size(); i++)
      {
         ReferenceType ref = (ReferenceType) roots.get(i);
         result.add(ref.getReference());
      }
      return result;
   }

   private void embedProcessDefinition(ProcessDefinitionType root, DiagramType targetDiagram, int x, int y)
   {
      EObject targetContainer = targetDiagram.eContainer();
      if (targetContainer instanceof ProcessDefinitionType)
      {
         ProcessDefinitionType targetProcessDefinition = (ProcessDefinitionType) targetContainer;
         targetProcessDefinition.getActivity().addAll(root.getActivity());
         targetProcessDefinition.getTransition().addAll(root.getTransition());
         List diagrams = root.getDiagram();
         if (!diagrams.isEmpty())
         {
            embedDiagram(targetDiagram, (DiagramType) diagrams.get(0), x, y);
         }
      }
      else
      {
         System.err.println("Incompatible container.");
      }
   }

   private void embedDiagram(DiagramType targetDiagram, DiagramType sourceDiagram, int x, int y)
   {
      ISymbolContainer container = targetDiagram;
      if (editPart.getModel() != null && editPart.getModel() instanceof ISymbolContainer) {
         container = (ISymbolContainer) editPart.getModel();
         x = 0;
         y = 0;
      } else {
         List pools = targetDiagram.getPoolSymbols();
         if (!pools.isEmpty())
         {
            for (int i = 0; i < pools.size(); i++)
            {
               PoolSymbol pool = (PoolSymbol) pools.get(i);
               if (contains(pool, x, y))
               {
                  container = pool;
                  break;
               }
            }
         }         
      }
      PoolSymbol sourcePool = (PoolSymbol) sourceDiagram.getPoolSymbols().get(0);
      List copy = new ArrayList();
      copy.addAll(sourcePool.eContents());
      for (int i = 0; i < copy.size(); i++)
      {
         // TODO: lanes ?
         EObject object = (EObject) copy.get(i);
         if(object instanceof IModelElementNodeSymbol)
         {
            if (object instanceof RoleSymbolType) {
               CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;                    
               EObject eObject = ModelUtils.findElementById(targetModel, CWM_PKG.getModelType_Role(), ((RoleSymbolType)object).getModelElement().getId());
               ((IModelElementNodeSymbol) object).setModelElement((IIdentifiableModelElement) eObject);
            }
         }
         addObjectTo(container, object);  
         if (object instanceof INodeSymbol)
         {
            INodeSymbol symbol = (INodeSymbol) object;
            symbol.setXPos(symbol.getXPos() + x);
            symbol.setYPos(symbol.getYPos() + y);
         }
         else if (object instanceof TransitionConnectionType)
         {
            TransitionConnectionType tc = (TransitionConnectionType) object;
            if (RoutingType.EXPLICIT_LITERAL == tc.getRouting())
            {
               List coordinates = tc.getCoordinates();
               for (int j = 0; j < coordinates.size(); j++)
               {
                  Coordinates coordinate = (Coordinates) coordinates.get(j);
                  coordinate.setXPos(coordinate.getXPos() + x);
                  coordinate.setYPos(coordinate.getYPos() + y);
               }
            }
         }
      }
   }

   private void addObjectTo(EObject container, EObject object)
   {
      EStructuralFeature feature = getContainingFeature(container, object);
      if (feature != null)
      {
         if (feature.isMany())
         {
            List list = (List) container.eGet(feature);
            list.add(object);
         }
         else
         {
            container.eSet(feature, object);
         }
      }
      else
      {
         System.err.println("Containment feature not found for: " + object);
      }
   }

   // TODO: move it into a utility class
   public static EStructuralFeature getContainingFeature(EObject container, EObject object)
   {
      EStructuralFeature feature = object.eContainingFeature();
      if (feature != null)
      {
         return feature;
      }
      List containments = container.eClass().getEAllContainments();
      for (int i = 0; i < containments.size(); i++)
      {
         feature = (EStructuralFeature) containments.get(i);
         if (feature.getEType().isInstance(object))
         {
            return feature;
         }
      }
      return null;
   }

   private boolean contains(PoolSymbol pool, int x, int y)
   {
      long px = pool.getXPos();
      if (x < px)
      {
         return false;
      }
      long py = pool.getYPos();
      if (y < py)
      {
         return false;
      }
      long w = pool.getWidth();
      if (w >= 0 && px + w < x)
      {
         return false;
      }
      long h = pool.getHeight();
      if (h >= 0 && py + h < x)
      {
         return false;
      }
      return true;
   }

   public ITemplateFactory getParentFactory()
   {
      return parentTemplateFactory;
   }
}
