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
package org.eclipse.stardust.modeling.integration.ejb30.session;

import java.io.Externalizable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledCombo;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.integration.ejb30.EJB30_Messages;
import org.eclipse.stardust.modeling.integration.ejb30.MethodSelector;
import org.eclipse.stardust.modeling.integration.ejb30.TypeSelector;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;



/**
 * 
 * @author herinean
 * @version $Revision$
 */
public class SessionBean30PropertyPage extends AbstractModelElementPropertyPage
{
   private static final String BLANK_STRING = " "; //$NON-NLS-1$
   
   private static final String[] FILTERABLES = {
      Serializable.class.getName(),
      Externalizable.class.getName(),
   };
   private static final List<String> FILTERABLES_LIST = Arrays.asList(FILTERABLES);
   
   private TypeSelector.TextSelector beanBrowser;
   private TypeSelector.ComboSelector interfaceBrowser;
   private MethodSelector creationBrowser;
   private MethodSelector completionBrowser;

   private LabeledText beanText;
   private LabeledCombo interfaceCombo;
   private LabeledText jndiText;

//   private LabeledCombo creationCombo;
//   private LabeledCombo completionCombo;

   private LabelWithStatus creationLabel;
   private LabelWithStatus completionLabel;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager wBndMgr = getWidgetBindingManager();
      wBndMgr.bind(beanText, (IExtensibleElement) element, CarnotConstants.CLASS_NAME_ATT);
      wBndMgr.bind(interfaceCombo, (IExtensibleElement) element, CarnotConstants.REMOTE_INTERFACE_ATT);
      wBndMgr.bind(jndiText, (IExtensibleElement) element, CarnotConstants.JNDI_PATH_ATT);
      
//      wBndMgr.bind(creationCombo, (IExtensibleElement) element, CarnotConstants.CREATE_METHOD_NAME_ATT);
//      wBndMgr.bind(completionCombo, (IExtensibleElement) element, CarnotConstants.METHOD_NAME_ATT);
      wBndMgr.getValidationBindingManager().bind(element, CarnotConstants.CREATE_METHOD_NAME_ATT, creationLabel);
      wBndMgr.getValidationBindingManager().bind(element, CarnotConstants.METHOD_NAME_ATT, completionLabel);
      
      creationBrowser.setMethodName(AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, CarnotConstants.CREATE_METHOD_NAME_ATT));
      completionBrowser.setMethodName(AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, CarnotConstants.METHOD_NAME_ATT)); 
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // TODO: push in a change listener
      AttributeUtil.setAttribute((IExtensibleElement) element, CarnotConstants.CREATE_METHOD_NAME_ATT,
            creationBrowser.getMethodName());
      AttributeUtil.setAttribute((IExtensibleElement) element, CarnotConstants.METHOD_NAME_ATT,
            completionBrowser.getMethodName());
   }

   public Control createBody(final Composite parent)
   {
      TypeFinder finder = new TypeFinder(getModelElement());
      
      Composite composite = FormBuilder.createComposite(parent, 2);

      // bean class
      LabelWithStatus beanLabel = FormBuilder.createLabelWithRightAlignedStatus(
         composite, EJB30_Messages.SESSION_BEAN30_PROPERTY_PAGE_LABEL_BEAN_CLASS); //$NON-NLS-1$
      beanBrowser = new TypeSelector.TextSelector(finder, composite,
            EJB30_Messages.SESSION_BEAN30_PROPERTY_PAGE_TITLE_BEAN_CLASS, //$NON-NLS-1$
            IJavaElementSearchConstants.CONSIDER_CLASSES);
      beanText = new LabeledText(beanBrowser.getTextControl(), beanLabel);

      // remote interface
      LabelWithStatus remoteLabel = FormBuilder.createLabelWithRightAlignedStatus(
         composite, EJB30_Messages.SESSION_BEAN30_PROPERTY_PAGE_LABEL_BUSINESS_INTERFACE); //$NON-NLS-1$      
      interfaceBrowser = new TypeSelector.ComboSelector(finder, composite,
            EJB30_Messages.SESSION_BEAN30_PROPERTY_PAGE_TITLE_BUSINESS_INTERFACE, //$NON-NLS-1$
            IJavaElementSearchConstants.CONSIDER_INTERFACES);
      
      interfaceCombo = new LabeledCombo(interfaceBrowser.getComboControl(), remoteLabel);
      
      // jndi path
      jndiText = FormBuilder.createLabeledText(composite,
         EJB30_Messages.SESSION_BEAN30_PROPERTY_PAGE_LABEL_JNDI_PATH); //$NON-NLS-1$

      // spacer
      FormBuilder.createLabel(composite, BLANK_STRING, 2);

      // creation method
      creationLabel = FormBuilder.createLabelWithRightAlignedStatus(
         composite, EJB30_Messages.SESSION_BEAN30_PROPERTY_PAGE_LABEL_INITIALIZATION_METHOD); //$NON-NLS-1$
      creationBrowser = new MethodSelector(composite);

      // completion method
      completionLabel = FormBuilder.createLabelWithRightAlignedStatus(
         composite, EJB30_Messages.SESSION_BEAN30_PROPERTY_PAGE_LABEL_COMPLETION_METHOD); //$NON-NLS-1$
      completionBrowser = new MethodSelector(composite);

      beanBrowser.addListener(new TypeSelector.TypeListener()
      {
         public void typeChanged(TypeInfo type)
         {
            updateInterfaces(type);
         }
      });

      interfaceBrowser.addListener(new TypeSelector.TypeListener()
      {
         public void typeChanged(TypeInfo type)
         {
            updateMethods(type);
         }
      });

      return composite;
   }

   @SuppressWarnings("unchecked")
   protected void updateMethods(TypeInfo type)
   {
      List<MethodInfo> allMethods = Collections.emptyList();
      try
      {
         allMethods = type.getMethods();
      }
      catch (Exception exception)
      {
         // ignore
      }
      
      List<MethodInfo> copy = new ArrayList<MethodInfo>(allMethods);
      filter(copy, DirectionType.IN_LITERAL);
      creationBrowser.setMethodsList(copy);

      copy = new ArrayList<MethodInfo>(allMethods);
      filter(copy, DirectionType.INOUT_LITERAL);
      completionBrowser.setMethodsList(copy);
   }

   @SuppressWarnings("unchecked")
   protected void updateInterfaces(TypeInfo type)
   {
      List<TypeInfo> interfaces = (List<TypeInfo>) (type == null ? Collections.emptyList() : type.getInterfaces());
      filter(interfaces);
      interfaceBrowser.setTypesList(interfaces);
   }

   private void filter(List<MethodInfo> methods, DirectionType direction)
   {
      for (int i = methods.size() - 1; i >= 0; i--)
      {
         MethodInfo method = methods.get(i);
         if (!method.isAccessible())
         {
            methods.remove(method);
         }
         else if (DirectionType.IN_LITERAL.equals(direction))
         {
            if (method.getParameterCount() == 0 && method.hasReturn())
            {
               methods.remove(method);
            }
         }
      }
   }

   private void filter(List<TypeInfo> interfaces)
   {
      for (int i = interfaces.size() - 1; i >= 0; i--)
      {
         TypeInfo iface = interfaces.get(i);
         if (FILTERABLES_LIST.contains(iface.getFullName()))
         {
            interfaces.remove(iface);
         }
      }
   }
}
