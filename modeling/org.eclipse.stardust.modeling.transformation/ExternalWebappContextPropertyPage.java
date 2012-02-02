/*
 */
package com.infinity.bpm.modeling.externalwebapp.spi.context;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.infinity.bpm.messaging.modeling.Modeling_Messages;

import ag.carnot.eclipse.ui.utils.FormBuilder;
import ag.carnot.eclipse.ui.utils.LabeledText;
import ag.carnot.workflow.model.PredefinedConstants;
import ag.carnot.workflow.model.carnot.IExtensibleElement;
import ag.carnot.workflow.model.carnot.IModelElement;
import ag.carnot.workflow.model.carnot.IModelElementNodeSymbol;
import ag.carnot.workflow.model.carnot.spi.IContextPropertyPage;
import ag.carnot.workflow.model.diagram.editors.ui.CarnotPreferenceNode;
import ag.carnot.workflow.model.diagram.properties.AbstractModelElementPropertyPage;
import ag.carnot.workflow.model.diagram.spi.ConfigurationElement;
import ag.carnot.workflow.model.diagram.utils.WidgetBindingManager;

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

      String iconName = "{com.infinity.bpm.modeling.externalwebapp}icons/message_transformation_application_icon.gif";       //$NON-NLS-1$
      ConfigurationElement element = ConfigurationElement.createPageConfiguration("com.infinity.bpm.modeling.externalwebapp.spi.context.InputOutputApplicationPropertyPage", Modeling_Messages.LBL_TYPED_ACCESS_POINTS, iconName, com.infinity.bpm.modeling.externalwebapp.spi.context.InputOutputApplicationPropertyPage.class); //$NON-NLS-1$ //$NON-NLS-2$
      CarnotPreferenceNode actualNode = (CarnotPreferenceNode) getNode("_cwm_interactive_.externalWebApp");       //$NON-NLS-1$
      //ModelElementPropertyDialog propDialog = (ModelElementPropertyDialog) getContainer();
      //CarnotPreferenceNode newNode = new CarnotPreferenceNode(element, propDialog.getElement(), 0);
      CarnotPreferenceNode newNode = new CarnotPreferenceNode(element, getElement(), 0);
      actualNode.add(newNode);
      
      return composite;
   }
}
