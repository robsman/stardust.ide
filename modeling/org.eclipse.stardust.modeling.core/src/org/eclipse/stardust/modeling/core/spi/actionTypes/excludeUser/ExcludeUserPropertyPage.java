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
package org.eclipse.stardust.modeling.core.spi.actionTypes.excludeUser;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IActionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.ActionTypeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathModelAdapter2;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathWidgetAdapter2;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class ExcludeUserPropertyPage extends AbstractModelElementPropertyPage
      implements IActionPropertyPage
{
   private static final String EXCLUDE_USER_TYPE = "excludeUser"; //$NON-NLS-1$

   private LabeledViewer dataLabel;
   private AccessPathBrowserComposite dataPathBrowser;
   private LabeledText dataPathLabel;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (ActionTypeUtil.getActionType(element).getId().equals(EXCLUDE_USER_TYPE))
      {
         ModelType model = ModelUtils.findContainingModel(element);
         WidgetBindingManager binding = getWidgetBindingManager();

         binding.getModelBindingManager().bind(
            new Data2DataPathModelAdapter2(model, model.getData()),
            new Data2DataPathWidgetAdapter2(dataLabel.getViewer(), dataPathBrowser, DirectionType.IN_LITERAL));

         binding.bind(dataLabel, (IExtensibleElement) element,
            CarnotConstants.EXCLUDED_PERFORMER_DATA_ATT,
            ModelUtils.findContainingModel(element),
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_Data());
         binding.bind(dataPathLabel, (IExtensibleElement) element,
            CarnotConstants.EXCLUDED_PERFORMER_DATA_PATH_ATT);
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (ActionTypeUtil.getActionType(element).getId().equals(EXCLUDE_USER_TYPE))
      {
         AttributeUtil.clearExcept((IExtensibleElement) element, new String[] {
            CarnotConstants.EXCLUDED_PERFORMER_DATA_ATT,
            CarnotConstants.EXCLUDED_PERFORMER_DATA_PATH_ATT
         });
      }
   }

   public Control createBody(Composite parent)
   {
      FormBuilder.createLabel(parent, "", 2); //$NON-NLS-1$

      LabelWithStatus label = FormBuilder.createLabelWithRightAlignedStatus(parent, Diagram_Messages.LB_Data);
      ComboViewer dataText = new ComboViewer(FormBuilder.createCombo(parent));
      dataText.setSorter(new ViewerSorter());
      dataText.setContentProvider(new ArrayContentProvider());
      dataText.setLabelProvider(new EObjectLabelProvider(getEditor()));
      dataLabel = new LabeledViewer(dataText, label);

      label = FormBuilder.createLabelWithRightAlignedStatus(parent, Diagram_Messages.LB_DataPath);
      dataPathBrowser = new AccessPathBrowserComposite(getEditor(), parent, Diagram_Messages.LB_DataPath);
      dataPathLabel = new LabeledText(dataPathBrowser.getMethodText(), label);

      return parent;
   }
}
