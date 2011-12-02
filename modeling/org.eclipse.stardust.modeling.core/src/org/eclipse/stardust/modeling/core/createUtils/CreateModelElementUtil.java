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
package org.eclipse.stardust.modeling.core.createUtils;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CommandUtils;


public class CreateModelElementUtil
{
   private static CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;   
   
   public static EStructuralFeature getContainingFeature(EClass eClass, EObject container)
   {
      List containingFeatureList = container.eClass().getEStructuralFeatures();
      return CommandUtils.findContainmentFeature(containingFeatureList, eClass);   
   }   
   
   public static IModelElement createModelElement(IdFactory idFactory, EClass eClass, 
         EObject container, ModelType model)
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
      else if(modelElement instanceof ISwimlaneSymbol && idFactory != null)
      {
         List list = (List) container.eGet(getContainingFeature(eClass, container));
         idFactory.computeNames(list);
         ((IIdentifiableElement) modelElement).setId(idFactory.getId());
         ((IIdentifiableElement) modelElement).setName(idFactory.getName());
      }
      else if (modelElement instanceof ViewType)
      {
         ((ViewType) modelElement).setName(idFactory.getName());
      }
      return modelElement;
   }
   
   public static void addModelElement(EStructuralFeature feature, 
         IModelElement modelElement, EObject container)
   {
      List list = (List) container.eGet(feature);
      list.add(modelElement);
   }   
   
   public static void addSymbol(ISymbolContainer container, IGraphicalObject symbol, 
         ModelType model)
   {
      EStructuralFeature feature = CommandUtils.findContainmentFeature(
            ((ISymbolContainer) container).getNodeContainingFeatures(), symbol);
      EList symbolContainment = (EList) container.eGet(feature);
      if (!symbol.isSetElementOid())
      {
         symbol.setElementOid(ModelUtils.getElementOid(symbol, model));         
      }
      symbolContainment.add(symbol);      
   }

/*   
   public static void addConnection(ISymbolContainer container, IGraphicalObject symbol, 
         ModelType model)
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
*/      
}