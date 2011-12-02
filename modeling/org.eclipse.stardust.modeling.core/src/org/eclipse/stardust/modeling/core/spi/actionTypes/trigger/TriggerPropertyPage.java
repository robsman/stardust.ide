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

import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IActionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.IdentifiableViewerSorter;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;

import ag.carnot.workflow.model.PredefinedConstants;

public class TriggerPropertyPage extends AbstractModelElementPropertyPage
      implements IActionPropertyPage
{
   private LabeledViewer labeledViewer;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ModelType model = ModelUtils.findContainingModel(element);
      TableViewer viewer = (TableViewer) labeledViewer.getViewer();
      viewer.getTable().removeAll();
      List<ProcessDefinitionType> processes = model.getProcessDefinition();
      viewer.add(processes.toArray());

      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      wBndMgr.bind(labeledViewer, (IExtensibleElement) element,
            PredefinedConstants.TRIGGER_ACTION_PROCESS_ATT, processes);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1, 2);
      ((GridLayout) composite.getLayout()).marginWidth = 0;
      LabelWithStatus label = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.TRIGGER);
      Table table = new Table(composite, SWT.BORDER);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      TableViewer tableViewer = new TableViewer(table);
      tableViewer.setLabelProvider(new EObjectLabelProvider(getEditor()));
      tableViewer.setSorter(new IdentifiableViewerSorter());
      labeledViewer = new LabeledViewer(tableViewer, label);

      return composite;
   }
}
