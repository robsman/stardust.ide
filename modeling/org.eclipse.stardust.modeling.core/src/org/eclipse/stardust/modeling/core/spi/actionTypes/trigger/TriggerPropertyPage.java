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
package org.eclipse.stardust.modeling.core.spi.actionTypes.trigger;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IActionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.ui.ProcessDefinitionSelectionViewer;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class TriggerPropertyPage extends AbstractModelElementPropertyPage
      implements IActionPropertyPage
{
   private LabeledViewer labeledViewer;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ModelType model = ModelUtils.findContainingModel(element);
      ProcessDefinitionSelectionViewer viewer = (ProcessDefinitionSelectionViewer) labeledViewer.getViewer();
      WidgetBindingManager wBndMgr = getWidgetBindingManager();
      wBndMgr.bind(labeledViewer, (IExtensibleElement) element,
            PredefinedConstants.TRIGGER_ACTION_PROCESS_ATT, viewer.reset(model));
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1, 2);
      ((GridLayout) composite.getLayout()).marginWidth = 0;
      LabelWithStatus label = FormBuilder.createLabelWithRightAlignedStatus(composite, Diagram_Messages.TRIGGER);

      final ProcessDefinitionSelectionViewer tableViewer = new ProcessDefinitionSelectionViewer(composite, getEditor());
      labeledViewer = new LabeledViewer(tableViewer, label);

      final Button groupingCheckbox = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_GroupModelElements);
      groupingCheckbox.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {}

         public void widgetSelected(SelectionEvent e)
         {
            ((ProcessDefinitionSelectionViewer) labeledViewer.getViewer()).setGrouped(groupingCheckbox.getSelection());
         }
      });

      return composite;
   }
}
