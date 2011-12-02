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
package org.eclipse.stardust.modeling.core.spi.dataTypes.entity;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;

import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;


/**
 * @author fherinean
 * @version $Revision$
 */
public class EntityBean20PropertyPage extends AbstractModelElementPropertyPage
      implements IDataPropertyPage
{
   private TypeSelectionComposite remoteBrowser;

   private TypeSelectionComposite homeBrowser;

   private TypeSelectionComposite pkBrowser;

   private LabeledText remoteText;

   private LabeledText homeText;

   private LabeledText pkText;

   private LabeledText jndiText;

   private Button localBinding;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      TypeFinder finder = new TypeFinder(element);
      remoteBrowser.setTypeFinder(finder);
      remoteBrowser.setFilter(EJBObject.class);
      homeBrowser.setTypeFinder(finder);
      homeBrowser.setFilter(EJBHome.class);
      pkBrowser.setTypeFinder(finder);

      WidgetBindingManager wBndMgr = getWidgetBindingManager();
      wBndMgr.bind(remoteText, (IExtensibleElement) element,
            CarnotConstants.REMOTE_INTERFACE_ATT);
      wBndMgr.bind(homeText, (IExtensibleElement) element,
            CarnotConstants.HOME_INTERFACE_ATT);
      wBndMgr.bind(pkText, (IExtensibleElement) element, CarnotConstants.PRIMARY_KEY_ATT);
      wBndMgr.bind(jndiText, (IExtensibleElement) element, CarnotConstants.JNDI_PATH_ATT);
      wBndMgr.bind(localBinding, (IExtensibleElement) element,
            CarnotConstants.IS_LOCAL_ATT);

      if (isPredefined(element))
      {
         disableControls();
      }
   }

   private void disableControls()
   {
      remoteBrowser.setEnabled(false);
      homeBrowser.setEnabled(false);
      pkBrowser.setEnabled(false);
      remoteText.getText().setEditable(false);
      homeText.getText().setEditable(false);
      pkText.getText().setEditable(false);
      jndiText.getText().setEditable(false);
      localBinding.setEnabled(false);
   }

   private boolean isPredefined(IModelElement element)
   {
      return ((DataType) element).isPredefined();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(final Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      // remote interface
      LabelWithStatus remoteLabel = FormBuilder.createLabelWithRightAlignedStatus(
            composite, Diagram_Messages.LB_RemoteInterfaceClass);
      remoteBrowser = new TypeSelectionComposite(composite,
            Diagram_Messages.EntityBeanPropertyPage_RemoteIF);
      remoteText = new LabeledText(remoteBrowser.getText(), remoteLabel);

      // home interface
      LabelWithStatus homeLabel = FormBuilder.createLabelWithRightAlignedStatus(
            composite, Diagram_Messages.LB_HomeInterfaceClass);
      homeBrowser = new TypeSelectionComposite(composite,
            Diagram_Messages.EntityBeanPropertyPage_HomeIF);
      homeText = new LabeledText(homeBrowser.getText(), homeLabel);

      // primary key
      LabelWithStatus pkLabel = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.LB_PrimaryKeyClass);
      pkBrowser = new TypeSelectionComposite(composite,
            Diagram_Messages.EntityBeanPropertyPage_PK);
      pkText = new LabeledText(pkBrowser.getText(), pkLabel);

      // jndi path
      jndiText = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_JNDIPath);

      // local binding
      new Label(composite, SWT.NONE);
      localBinding = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_SPI_LocalBinding);

      return composite;
   }
}
