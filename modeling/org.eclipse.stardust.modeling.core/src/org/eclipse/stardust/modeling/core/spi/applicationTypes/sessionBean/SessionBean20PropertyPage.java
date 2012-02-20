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
package org.eclipse.stardust.modeling.core.spi.applicationTypes.sessionBean;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;

import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.DereferencePathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;


public class SessionBean20PropertyPage extends AbstractModelElementPropertyPage
{
   private TypeSelectionComposite remoteBrowser;
   private TypeSelectionComposite homeBrowser;
   private DereferencePathBrowserComposite completionBrowser;
   private DereferencePathBrowserComposite creationBrowser;

   private LabeledText remoteText;
   private LabeledText homeText;
   private LabeledText jndiText;
   private Button localBinding;
   private LabeledText completionText;
   private LabeledText creationText;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (remoteBrowser != null) 
      {
         TypeFinder finder = new TypeFinder(element);
         remoteBrowser.setTypeFinder(finder);
         remoteBrowser.setFilter(EJBObject.class);
         homeBrowser.setTypeFinder(finder);
         homeBrowser.setFilter(EJBHome.class);
         completionBrowser.setTypeFinder(finder);
         creationBrowser.setTypeFinder(finder);

         WidgetBindingManager wBndMgr = getWidgetBindingManager();
         wBndMgr.bind(remoteText, (IExtensibleElement) element, CarnotConstants.REMOTE_INTERFACE_ATT);
         wBndMgr.bind(homeText, (IExtensibleElement) element, CarnotConstants.HOME_INTERFACE_ATT);
         wBndMgr.bind(jndiText, (IExtensibleElement) element, CarnotConstants.JNDI_PATH_ATT);
         wBndMgr.bind(localBinding, (IExtensibleElement) element, CarnotConstants.IS_LOCAL_ATT);
         wBndMgr.bind(completionText, (IExtensibleElement) element, CarnotConstants.METHOD_NAME_ATT);
         wBndMgr.bind(creationText, (IExtensibleElement) element, CarnotConstants.CREATE_METHOD_NAME_ATT);

         updateMethodBrowserStyle();
         
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public Control createBody(final Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      // remote interface
      LabelWithStatus remoteLabel = FormBuilder.createLabelWithRightAlignedStatus(
         composite, Diagram_Messages.LB_RemoteInterface);
      remoteBrowser = new TypeSelectionComposite(composite,
            Diagram_Messages.SessionBeanPropertyPage_Remote_IF);
      remoteText = new LabeledText(remoteBrowser.getText(), remoteLabel);

      // home interface
      LabelWithStatus homeLabel = FormBuilder.createLabelWithRightAlignedStatus(
         composite, Diagram_Messages.LB_HomeInterface);
      homeBrowser = new TypeSelectionComposite(composite,
            Diagram_Messages.SessionBeanPropertyPage_Home_IF);
      homeText = new LabeledText(homeBrowser.getText(), homeLabel);

      // jndi path
      jndiText = FormBuilder.createLabeledText(composite,
    		  Diagram_Messages.LB_HomeInterfaceJNDIPath);

      // local binding
      new Label(composite, SWT.NONE);
      localBinding = FormBuilder.createCheckBox(composite,
         Diagram_Messages.CHECKBOX_LocalBinding);
      localBinding.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateMethodBrowserStyle();
         }

         public void widgetDefaultSelected(SelectionEvent e)
         {
         }
      });

      // completion method
      LabelWithStatus completionLabel = FormBuilder.createLabelWithRightAlignedStatus(
         composite, Diagram_Messages.LB_CompletionMethod);
      completionBrowser = new DereferencePathBrowserComposite(composite,
            Diagram_Messages.SessionBeanPropertyPage_Completion_Method);
      completionBrowser.setDirection(DirectionType.INOUT_LITERAL);
      completionBrowser.setDeep(false);
      completionText = new LabeledText(completionBrowser.getMethodText(), completionLabel);
      remoteBrowser.setDereferencePathBrowser(
         new DereferencePathBrowserComposite[] {completionBrowser});

      // creation method
      LabelWithStatus creationLabel = FormBuilder.createLabelWithRightAlignedStatus(
         composite, Diagram_Messages.LB_CreationMethod);
      creationBrowser = new DereferencePathBrowserComposite(composite,
         Diagram_Messages.SessionBeanPropertyPage_Create_Method);
      creationBrowser.setDeep(false);
      creationText = new LabeledText(creationBrowser.getMethodText(), creationLabel);
      homeBrowser.setDereferencePathBrowser(
         new DereferencePathBrowserComposite[] {creationBrowser});

      return composite;
   }

   private void updateMethodBrowserStyle()
   {
      if (localBinding.getSelection())
      {
         remoteBrowser.setStyle(IJavaElementSearchConstants.CONSIDER_ALL_TYPES);
         homeBrowser.setStyle(IJavaElementSearchConstants.CONSIDER_ALL_TYPES);
      }
      else
      {
         remoteBrowser.setStyle(IJavaElementSearchConstants.CONSIDER_INTERFACES);
         homeBrowser.setStyle(IJavaElementSearchConstants.CONSIDER_INTERFACES);
      }
   }
}
