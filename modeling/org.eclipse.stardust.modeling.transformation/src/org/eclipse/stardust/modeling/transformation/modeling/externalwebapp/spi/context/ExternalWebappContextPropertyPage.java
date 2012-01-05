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
/*
 */
package org.eclipse.stardust.modeling.transformation.modeling.externalwebapp.spi.context;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IContextPropertyPage;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import ag.carnot.workflow.model.PredefinedConstants;

public class ExternalWebappContextPropertyPage extends AbstractModelElementPropertyPage
      implements IContextPropertyPage
{
   private static final String EXTERNAL_WEB_APP_SCOPE = PredefinedConstants.ENGINE_SCOPE + "ui:externalWebApp:"; //$NON-NLS-1$

   public static final String COMPONENT_URL_ATT = EXTERNAL_WEB_APP_SCOPE + "uri"; //$NON-NLS-1$

/*
   private LabeledViewer componentKind;
*/

   private LabeledText txtComponentUrl;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      wBndMgr.bind(txtComponentUrl, (IExtensibleElement) element, COMPONENT_URL_ATT);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // nothing do be done due to data binding
   }

   public Control createBody(final Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      
      this.txtComponentUrl = FormBuilder.createLabeledText(composite,
            "URL:"); //$NON-NLS-1$

      String iconName = "{org.eclipse.stardust.modeling.transformation.modeling.externalwebapp}icons/message_transformation_application_icon.gif";       //$NON-NLS-1$
      ConfigurationElement element = ConfigurationElement.createPageConfiguration("org.eclipse.stardust.modeling.transformation.modeling.externalwebapp.spi.context.InputOutputApplicationPropertyPage", "Typed Access Points", iconName, org.eclipse.stardust.modeling.transformation.modeling.externalwebapp.spi.context.InputOutputApplicationPropertyPage.class); //$NON-NLS-1$ //$NON-NLS-2$
      CarnotPreferenceNode actualNode = (CarnotPreferenceNode) getNode("_cwm_interactive_.externalWebApp");       //$NON-NLS-1$
      //ModelElementPropertyDialog propDialog = (ModelElementPropertyDialog) getContainer();
      //CarnotPreferenceNode newNode = new CarnotPreferenceNode(element, propDialog.getElement(), 0);
      CarnotPreferenceNode newNode = new CarnotPreferenceNode(element, getElement(), 0);
      actualNode.add(newNode);
      
      return composite;
   }
}
