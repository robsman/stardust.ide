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
package org.eclipse.stardust.modeling.core.spi.actionTypes.setData;

import java.util.Collections;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.spi.IActionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
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


public class SetDataPropertyPage extends AbstractModelElementPropertyPage
      implements IActionPropertyPage
{
   private static final String DATA_TYPE = "setData"; //$NON-NLS-1$

   private static final String DATA = "carnot:engine:dataId"; //$NON-NLS-1$
   private static final String DATA_PATH = "carnot:engine:dataPath"; //$NON-NLS-1$

   private static final String ACCESS_POINT = "carnot:engine:attributeName"; //$NON-NLS-1$
   private static final String ACCESS_POINT_PATH = "carnot:engine:attributePath"; //$NON-NLS-1$

   private LabeledViewer dataLabel;
   private LabeledText dataPathLabel;
   private AccessPathBrowserComposite dataPathBrowser;

   private LabeledViewer accessPointLabel;
   private LabeledText accessPathLabel;
   private AccessPathBrowserComposite accessPathBrowser;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (ActionTypeUtil.getActionType(element).getId().equals(DATA_TYPE))
      {
         WidgetBindingManager binding = getWidgetBindingManager();

         // bug: (fh) for unknown reasons, this method is invoked twice. Since the
         // intrinsic access points are created new each time and the data-dataPath
         // binding is preserving the selection, we must explicitly unbind here.
         binding.unbind(element);
         // end bug

         ModelType model = ModelUtils.findContainingModel(element);
         binding.getModelBindingManager().bind(
            new Data2DataPathModelAdapter2(element, model.getData()),
            new Data2DataPathWidgetAdapter2(dataLabel.getViewer(), dataPathBrowser,
               DirectionType.OUT_LITERAL));

         binding.bind(dataLabel, (IExtensibleElement) element, DATA,
            ModelUtils.findContainingModel(element),
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_Data());

         binding.bind(dataPathLabel, (IExtensibleElement) element, DATA_PATH);

         Map extensions = SpiExtensionRegistry.instance().getExtensions(
               CarnotConstants.CONDITION_TYPES_EXTENSION_POINT_ID);
         IConfigurationElement config = (IConfigurationElement) extensions.get(ModelUtils
               .findContainingEventHandlerType(element).getType().getId());
         if (config.getAttribute("accessPointProvider") != null) //$NON-NLS-1$
         {
            try
            {
               Object extension = config
                     .createExecutableExtension("accessPointProvider"); //$NON-NLS-1$

               final java.util.List accessPoints = extension != null
                  && extension instanceof IAccessPointProvider
                  ? ((IAccessPointProvider) extension).createIntrinsicAccessPoint(element)
                  : Collections.EMPTY_LIST;

               binding.getModelBindingManager().bind(
                  new Data2DataPathModelAdapter2(element, accessPoints),
                  new Data2DataPathWidgetAdapter2(accessPointLabel.getViewer(),
                     accessPathBrowser, DirectionType.IN_LITERAL));

               binding.bind(accessPointLabel, (IExtensibleElement) element,
                  ACCESS_POINT, accessPoints);

               binding.bind(accessPathLabel, (IExtensibleElement) element,
                  ACCESS_POINT_PATH);
            }
            catch (CoreException e)
            {
               e.printStackTrace();
            }
         }
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if ((ActionTypeUtil.getActionType(element).getId().equals(DATA_TYPE)))
      {
         AttributeUtil.clearExcept((IExtensibleElement) element, new String[]
            {DATA, DATA_PATH, ACCESS_POINT, ACCESS_POINT_PATH});
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

      label = FormBuilder.createLabelWithRightAlignedStatus(parent, Diagram_Messages.LB_AccessPoint);
      ComboViewer accessPointText = new ComboViewer(FormBuilder.createCombo(parent));
      accessPointText.setSorter(new ViewerSorter());
      accessPointText.setContentProvider(new ArrayContentProvider());
      accessPointText.setLabelProvider(new EObjectLabelProvider(getEditor()));
      accessPointLabel = new LabeledViewer(accessPointText, label);

      label = FormBuilder.createLabelWithRightAlignedStatus(parent, Diagram_Messages.LB_AccessPath);
      accessPathBrowser = new AccessPathBrowserComposite(getEditor(), parent, Diagram_Messages.LB_AccessPath);
      accessPathLabel = new LabeledText(accessPathBrowser.getMethodText(), label);

      return parent;
   }
}
