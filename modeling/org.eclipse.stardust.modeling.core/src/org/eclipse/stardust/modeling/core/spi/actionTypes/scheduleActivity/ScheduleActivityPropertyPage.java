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
package org.eclipse.stardust.modeling.core.spi.actionTypes.scheduleActivity;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IActionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.ActionTypeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;


public class ScheduleActivityPropertyPage extends AbstractModelElementPropertyPage
      implements IActionPropertyPage
{
   private static final String HIBERNATED = Diagram_Messages.HIBERNATED; 

   private static final String SUSPENDED = Diagram_Messages.SUSPENDED; 

   private static final String INTENDED_STATE_CHANGE = Diagram_Messages.INTENDED_STATE_CHANGE; 

   private static final String HIBERNATED_VAL = "7";  //$NON-NLS-1$

   private static final String SUSPENDED_VAL = "5";  //$NON-NLS-1$

   private static final String SCHEDULE_ACTIVITY_TYPE = "scheduleActivity";  //$NON-NLS-1$

   private Button suspendedButton;

   private Button hibernatedButton;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (ActionTypeUtil.getActionType(element).getId().equals(SCHEDULE_ACTIVITY_TYPE))
      {
         String targetStateAtt = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, CarnotConstants.TARGET_STATE_ATT);
         if ((targetStateAtt != null) && (suspendedButton != null))
         {
            suspendedButton.setSelection(targetStateAtt.equals(SUSPENDED_VAL));
            hibernatedButton.setSelection(targetStateAtt.equals(HIBERNATED_VAL));
         }
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (ActionTypeUtil.getActionType(element).getId().equals(SCHEDULE_ACTIVITY_TYPE))
      {
         if (suspendedButton != null)
         {
            ((IExtensibleElement) element).getAttribute().clear();
            AttributeUtil.setAttribute(((IExtensibleElement) element), CarnotConstants.TARGET_STATE_ATT,
            CarnotConstants.ACTIVITY_INSTANCE_STATE_ATT, suspendedButton.getSelection() ? SUSPENDED_VAL : HIBERNATED_VAL);
         }
      }
   }

   public Control createBody(Composite parent)
   {
      Group intendedStateChangeGroup = FormBuilder.createGroup(parent,
            INTENDED_STATE_CHANGE, 3);
      ((GridLayout) intendedStateChangeGroup.getLayout()).marginHeight = 10;
      intendedStateChangeGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true,
            false, 1, 1));

      suspendedButton = FormBuilder
            .createRadioButton(intendedStateChangeGroup, SUSPENDED);
      suspendedButton.setSelection(true);
      hibernatedButton = FormBuilder.createRadioButton(intendedStateChangeGroup,
            HIBERNATED);
      return parent;
   }
}
