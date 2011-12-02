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
package org.eclipse.stardust.modeling.core.spi.conditionTypes.exception;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IConditionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.DefaultModelElementPropertyPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class ExceptionPropertyPage extends DefaultModelElementPropertyPage
      implements IConditionPropertyPage
{
   private TypeSelectionComposite exceptionBrowser;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      String exceptionName = AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, CarnotConstants.EXCEPTION_NAME_ATT);
      exceptionBrowser.setTypeText(exceptionName == null ? "" : exceptionName); //$NON-NLS-1$
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      AttributeUtil.setAttribute((IExtensibleElement) element,
            CarnotConstants.EXCEPTION_NAME_ATT, exceptionBrowser.getTypeText());
   }

   public Control createBody(final Composite parent)
   {
      FormBuilder.createLabel(parent, Diagram_Messages.LB_Exception);
      exceptionBrowser = new TypeSelectionComposite(parent, Diagram_Messages.ExceptionPropertyPage_Class);
      return parent;
   }
   
   public void setDelegateContainer(AbstractModelElementPropertyPage page)
   {
   }   
}