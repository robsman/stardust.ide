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

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.modeling.common.ui.IdFactory;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CreateXpdlElementCommand extends ContainedElementCommand
{
   // definition fields
   private EClass eClass;

   private IdFactory idFactory;

   private EObject xpdlElement;

   public CreateXpdlElementCommand(int parentLevel, IdFactory idFactory, EClass eClass)
   {
      super(parentLevel);
      this.idFactory = idFactory;
      this.eClass = eClass;
   }

   public void execute()
   {
      xpdlElement = createXpdlElement();
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

   public EObject getXpdlElement()
   {
      return xpdlElement;
   }

   public EClass getEClass()
   {
      return eClass;
   }

   protected EObject createXpdlElement()
   {
      XpdlFactory factory = XpdlFactory.eINSTANCE;      
      EObject xpdlElement = factory.create(eClass);
      if (xpdlElement instanceof TypeDeclarationType && idFactory != null)
      {
         List list = (List) getContainer().eGet(getContainingFeature());
         idFactory.computeNames(list);
         ((TypeDeclarationType) xpdlElement).setId(idFactory.getId());
         ((TypeDeclarationType) xpdlElement).setName(idFactory.getName());
      }
      return xpdlElement;
   }

   public void redo()
   {
      if (xpdlElement != null)
      {
         EList list = (EList) getContainer().eGet(getContainingFeature());
         list.add(xpdlElement);
      }
   }

   public void undo()
   {
      if (xpdlElement != null)
      {
         EList list = (EList) getContainer().eGet(getContainingFeature());
         list.remove(xpdlElement);
      }
   }

   public void dispose()
   {
      xpdlElement = null;
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
      if (!(o instanceof CreateXpdlElementCommand))
         return false;

      final CreateXpdlElementCommand createXpdlElementCommand = (CreateXpdlElementCommand) o;

      if (!eClass.equals(createXpdlElementCommand.eClass))
         return false;
      if (!idFactory.equals(createXpdlElementCommand.idFactory))
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