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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CreateTypedModelElementCommand extends CreateModelElementCommand
{
   private String typeId;
   private EStructuralFeature typeFeature;

   public CreateTypedModelElementCommand(int parentLevel, IdFactory id, String type,
      EStructuralFeature typeFeature, EClass eClass)
   {
      super(parentLevel, id, eClass);
      this.typeId = type;
      this.typeFeature = typeFeature;
   }

   protected IModelElement createModelElement()
   {
      IModelElement modelElement = super.createModelElement();
      EStructuralFeature attributeFeature = modelElement.eClass().getEStructuralFeature("type"); //$NON-NLS-1$
      IMetaType metaType = (IMetaType)
         ModelUtils.findIdentifiableElement(getModel(), typeFeature, typeId);
      modelElement.eSet(attributeFeature, metaType);
      return modelElement;
   }
}
