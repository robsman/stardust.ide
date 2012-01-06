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
package org.eclipse.stardust.modeling.core.spi.actionTypes.abort;

import java.util.List;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.runtime.beans.AbortScope;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IActionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

public class AbortActionPropertyPage extends AbstractModelElementPropertyPage
      implements IActionPropertyPage
{
   private Group scopeGroup;

   public Control createBody(Composite parent)
   {
      scopeGroup = FormBuilder.createGroup(parent, Diagram_Messages.ScopeLabel, 4);
      ((GridLayout) scopeGroup.getLayout()).marginHeight = 10;
      scopeGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
      List scopes = AbortScope.getKeys(AbortScope.class);
      for (int scopeCounter = 0; scopeCounter < scopes.size(); scopeCounter++)
      {
         final AbortScope scope = (AbortScope) scopes.get(scopeCounter); 
         final Button button = FormBuilder.createRadioButton(scopeGroup, scope.getName());
         button.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e) {/* do nothing */}

            public void widgetSelected(SelectionEvent e)
            {
               if (button.getSelection())
               {
                  AttributeUtil.setAttribute((EventActionType) getModelElement(),
                        PredefinedConstants.ABORT_ACTION_SCOPE_ATT, scope.getId());
               }
            }
         });
      }
      return null;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // nothing to do here
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      int index = 0;
      String scopeId = AttributeUtil.getAttributeValue((EventActionType) getModelElement(),
            PredefinedConstants.ABORT_ACTION_SCOPE_ATT);
      if (scopeId == null)
      {
         scopeId = AbortScope.ROOT_HIERARCHY;
         AttributeUtil.setAttribute((EventActionType) getModelElement(),
               PredefinedConstants.ABORT_ACTION_SCOPE_ATT, scopeId);
      }
      List scopes = AbortScope.getKeys(AbortScope.class);
      for (int scopeCounter = 0; scopeCounter < scopes.size(); scopeCounter++)
      {
         AbortScope scope = (AbortScope) scopes.get(scopeCounter);
         if (scope.getId().equals(scopeId))
         {
            index = scopeCounter;
            break;
         }
      }
      Control[] children = scopeGroup.getChildren();
      for (int childCounter = 0; childCounter < children.length; childCounter++)
      {
         Button button = (Button) children[childCounter];
         button.setSelection(childCounter == index);
      }
   }
}
