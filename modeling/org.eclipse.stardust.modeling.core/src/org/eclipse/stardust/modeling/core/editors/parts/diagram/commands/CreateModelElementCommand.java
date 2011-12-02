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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.createUtils.CreateModelElementUtil;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CreateModelElementCommand extends ContainedElementCommand
{
   // definition fields
   private EClass eClass;
   private IdFactory idFactory;
   private IModelElement modelElement;

   public CreateModelElementCommand(int parentLevel, IdFactory idFactory, EClass eClass)
   {
      super(parentLevel);
      this.idFactory = idFactory;
      this.eClass = eClass;
   }

   public void execute()
   {
      modelElement = createModelElement();
      redo();
   }

   public boolean canExecute()
   {
      return null != getContainingFeature();
   }

   public IdFactory getIdFactory()
   {
      return idFactory;
   }

   public IModelElement getModelElement()
   {
      return modelElement;
   }

   public EClass getEClass()
   {
      return eClass;
   }

   protected IModelElement createModelElement()
   {
      return CreateModelElementUtil.createModelElement(idFactory, eClass, getContainer(), getModel());
   }

   public void redo()
   {
      if (modelElement != null)
      {
         EList list = (EList) getContainer().eGet(getContainingFeature());
         list.add(modelElement);

         // Workaround to quickly fix OID assignment of child elements
         if (modelElement instanceof ProcessDefinitionType)
         {
            for (Iterator i = ((ProcessDefinitionType) modelElement).getDiagram()
                  .iterator(); i.hasNext();)
            {
               DiagramType diagram = (DiagramType) i.next();
               diagram.setElementOid(ModelUtils.getElementOid(diagram, getModel()));
            }
         }
         else if (modelElement instanceof ApplicationType)
         {
            for (Iterator i = ((ApplicationType) modelElement).getContext().iterator(); i
                  .hasNext();)
            {
               ContextType context = (ContextType) i.next();
               context.setElementOid(ModelUtils.getElementOid(context, getModel()));
            }
         }
         else if (modelElement instanceof ActivityType)
         {
            AttributeUtil.setBooleanAttribute((IExtensibleElement) modelElement,
                  "carnot:pwh:" + "includeTime", ActivityUtil //$NON-NLS-1$ //$NON-NLS-2$
                        .isInteractive((ActivityType) modelElement) ? true : false);
         }
      }
   }

   public void undo()
   {
      if (modelElement != null)
      {
         EList list = (EList) getContainer().eGet(getContainingFeature());
         list.remove(modelElement);         
      }
   }

   public void dispose()
   {
      modelElement = null;
      super.dispose();
   }

   public EStructuralFeature getContainingFeature()
   {
      return getContainer() == null ? null : CommandUtils.findContainmentFeature(
            getContainingFeatureList(), eClass);
   }

   protected List getContainingFeatureList()
   {
      return getContainer().eClass().getEStructuralFeatures();
   }

   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (!(o instanceof CreateModelElementCommand))
         return false;

      final CreateModelElementCommand createModelElementCommand = (CreateModelElementCommand) o;

      if (!eClass.equals(createModelElementCommand.eClass))
         return false;
      if (!idFactory.equals(createModelElementCommand.idFactory))
         return false;

      return true;
   }

   public int hashCode()
   {
      int result;
      result = eClass.hashCode();
      result = 29 * result + idFactory.hashCode();
      return result;
   }
}