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
package org.eclipse.stardust.modeling.core.editors.parts.tree;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.IValidationEventListener;
import org.eclipse.stardust.modeling.core.editors.IValidationStatus;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;


public class ChildCategoryNode extends AbstractEObjectTreeEditPart
{
   private final Spec spec;

   public ChildCategoryNode(WorkflowModelEditor editor, Spec spec)
   {
      super(editor, spec, spec.target, spec.icon, spec.childrenFeatures);
      
      this.spec = spec;
      spec.setEditPart(this);
   }

   public String getLabel()
   {
      return spec.label;
   }

   protected List getModelChildren()
   {
      // sort complete list to override per feature sorting of base class
      List items = super.getModelChildren();
      Collections.sort(items, ModelUtils.IDENTIFIABLE_COMPARATOR);
      return items;
   }

   public Object getAdapter(Class key)
   {
      if (spec.target instanceof IModelElement && (
            IIdentifiableModelElement.class.equals(key)
            || IIdentifiableElement.class.equals(key)
            || IModelElement.class.equals(key)))
      {
         return spec.target;
      }
      else
      {
         return super.getAdapter(key);
      }
   }

   public static class Spec implements IValidationEventListener
   {
      public EObject getTarget()
      {
         return target;
      }

      public AbstractEObjectTreeEditPart editPart;
      
      public final EObject target;

      public final String label;

      public final String icon;

      public final EStructuralFeature[] childrenFeatures;

      public Spec(EObject target, String label, String icon,
            EStructuralFeature[] childrenFeatures)
      {
         this.target = target;
         this.label = label;
         this.icon = icon;
         this.childrenFeatures = childrenFeatures;
      }

      public void setEditPart(ChildCategoryNode node)
      {
         editPart = node;
      }

      public void onIssuesUpdated(EObject element, IValidationStatus validationStatus)
      {
         boolean hasWarnings = !validationStatus.getWarnings().isEmpty()
                                 || !validationStatus.getChildrenWithWarnings().isEmpty();
         boolean hasErrors = !validationStatus.getErrors().isEmpty()
                                 || !validationStatus.getChildrenWithErrors().isEmpty();                  
         
         for(int i = 0; i < childrenFeatures.length; i++)
         {
            EReference ref = (EReference) childrenFeatures[i];
            EClass eClass = ref.getEReferenceType();
            
            if(eClass.equals((EObject) element.eClass()))
            {
               AbstractEObjectTreeEditPart treePart = (AbstractEObjectTreeEditPart) editPart;
               if(treePart != null)
               {
                  treePart.setState(hasErrors
                        ? AbstractEObjectTreeEditPart.STATE_ERRORS
                              : hasWarnings
                                 ? AbstractEObjectTreeEditPart.STATE_WARNINGS
                                       : AbstractEObjectTreeEditPart.STATE_OK);            
               }
            }            
         }
      }
   }
}