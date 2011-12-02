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

import java.io.InputStream;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CommandUtils;
import org.eclipse.stardust.modeling.templates.spi.ITemplateFactory;



public class TemplateHelper
{
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;   
   private static CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;   
   private ModelType model;
   private DiagramType diagram;
   private ProcessDefinitionType process;
   private EditPart editPart;
      
   public TemplateHelper(ModelType model, DiagramType diagram,
         ProcessDefinitionType process, EditPart editPart)
   {
      super();
      this.model = model;
      this.diagram = diagram;
      this.process = process;
      this.editPart = editPart;
   }   
   
   public TransitionType createTransition(String name)
   {
      IdFactory idFactory = new IdFactory(Diagram_Messages.ID_TransitionConnection, Diagram_Messages.BASENAME_Transition);
      EClass eClass = PKG.getTransitionType();         
      TransitionType transition = (TransitionType) createModelElement(idFactory, eClass, process);
      process.getTransition().add(transition);
      addModelElement(getContainingFeature(eClass, process), transition, process);
      return transition;
   }   
   
   public ActivityType createActivity(String name, String kind)
   {
      IdFactory idFactory = null;
      ActivityImplementationType implType = null;        
      //Quick and dirty ;-)
      if (kind.startsWith("Rou")) {
         implType = ActivityImplementationType.ROUTE_LITERAL;                
      } 
      if (kind.startsWith("App")) {
         implType = ActivityImplementationType.APPLICATION_LITERAL;             
      } 
      if (kind.startsWith("Man")) {
         implType = ActivityImplementationType.MANUAL_LITERAL;             
      } 
      idFactory = new IdFactory(Diagram_Messages.ID_Activity, 
            implType + Diagram_Messages.BASENAME_Activity);
      EClass eClass = PKG.getActivityType();         
      ActivityType activity = (ActivityType) createModelElement(idFactory, eClass, process);
      activity.setImplementation(implType); 
      process.getActivity().add(activity);
      addModelElement(getContainingFeature(eClass, process), activity, process);
      return activity;
   }   
   
   public IModelElement createModelElement(IdFactory idFactory, EClass eClass, 
         EObject container)
   {
      IModelElement modelElement = (IModelElement) factory.create(eClass);
      modelElement.setElementOid(ModelUtils.getElementOid(modelElement, model));
      if (modelElement instanceof IIdentifiableModelElement && idFactory != null)
      {
         List list = (List) container.eGet(getContainingFeature(eClass, container));
         idFactory.computeNames(list);
         ((IIdentifiableElement) modelElement).setId(idFactory.getId());
         ((IIdentifiableElement) modelElement).setName(idFactory.getName());
         if (idFactory.getReferingElement() == null)
         {
            idFactory.setReferingElement((IIdentifiableModelElement) modelElement);
         }
      }
      return modelElement;
   }
   
   public EStructuralFeature getContainingFeature(EClass eClass, EObject container)
   {
      List containingFeatureList = container.eClass().getEStructuralFeatures();
      return CommandUtils.findContainmentFeature(containingFeatureList, eClass);   
   }
   
   public void addModelElement(EStructuralFeature feature, 
         IModelElement modelElement, EObject container)
   {
      List list = (List) container.eGet(feature);
      list.add(modelElement);
   }
   
   public ActivitySymbolType createActivitySymbol(ActivityType activity, int x, int y)
   {
      EClass eClass = PKG.getActivitySymbolType();      
      EObject container = DiagramUtil.getDefaultPool(diagram);                
      ActivitySymbolType symbol = (ActivitySymbolType) createModelElement(null, eClass, container);
      symbol.setActivity(activity);
      addSymbol((ISymbolContainer) container, symbol);       
      symbol.setXPos(x);
      symbol.setYPos(y);          
      return symbol;
   }
   
   public TransitionConnectionType createTransitionSymbol(TransitionType transition)
   {
      EClass eClass = PKG.getTransitionConnectionType();      
      EObject container = DiagramUtil.getDefaultPool(diagram);                 
      TransitionConnectionType symbol = (TransitionConnectionType) createModelElement(null, eClass, container);    
      if (transition != null) {
         symbol.setTransition(transition);         
      }
      addTransitionSymbol((ISymbolContainer) container, symbol);
      return symbol;
   }
      
   public GatewaySymbol createGatewaySymbol(ActivitySymbolType activity, FlowControlType flowControlType)
   {
      EClass eClass = PKG.getGatewaySymbol();   
      EObject container = DiagramUtil.getDefaultPool(diagram);                 
      GatewaySymbol symbol = (GatewaySymbol) createModelElement(null, eClass, container);
      //symbol.setFlowKind(FlowControlType.SPLIT_LITERAL);
      symbol.setFlowKind(flowControlType);
      symbol.setActivitySymbol(activity);
      addSymbol((ISymbolContainer) container, symbol);  
      return symbol;
   }       
   
   public void addSymbol(ISymbolContainer container, IGraphicalObject symbol)
   {          
      if (editPart.getModel() != null && editPart.getModel() instanceof ISymbolContainer) {
         container = (ISymbolContainer) editPart.getModel();
      }
      EStructuralFeature feature = CommandUtils.findContainmentFeature(
            ((ISymbolContainer) container).getNodeContainingFeatures(), symbol);
      EList symbolContainment = (EList) container.eGet(feature);
      if (!symbol.isSetElementOid())
      {
         symbol.setElementOid(ModelUtils.getElementOid(symbol, model));         
      }
      symbolContainment.add(symbol);      
   }

   public void addTransitionSymbol(ISymbolContainer container, IGraphicalObject symbol)
   {
      EStructuralFeature feature = CommandUtils.findContainmentFeature(
            ((ISymbolContainer) container).getConnectionContainingFeatures(), symbol);
      EList symbolContainment = (EList) container.eGet(feature);
      if (!symbol.isSetElementOid())
      {
         symbol.setElementOid(ModelUtils.getElementOid(symbol, model));         
      }
      symbolContainment.add(symbol);      
   }
   
   public static String readResourceToString (String path, ITemplateFactory templateFactory) throws Throwable {
      InputStream in = (InputStream) templateFactory.getClass().getResourceAsStream(path);
      StringBuffer out = new StringBuffer();
      byte[] b = new byte[4096];
      for (int n; (n = in.read(b)) != -1;) {
          out.append(new String(b, 0, n));
      }
      return out.toString();
  }
}