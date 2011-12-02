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
package org.eclipse.stardust.modeling.core.spi.contextTypes.jfc;

import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IContextPropertyPage;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class JfcPropertyPage extends AbstractModelElementPropertyPage
      implements IContextPropertyPage
{
   private TypeSelectionComposite classBrowser;
   private DereferencePathBrowserComposite methodBrowser;

   private LabeledText classText;
   private LabeledText methodText;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      TypeFinder finder = new TypeFinder(element);
      classBrowser.setTypeFinder(finder);
      classBrowser.setModel((ModelType)element.eContainer().eContainer());
      methodBrowser.setTypeFinder(finder);

      WidgetBindingManager wBndMgr = getWidgetBindingManager();
      wBndMgr.bind(classText, (IExtensibleElement) element, CarnotConstants.CLASS_NAME_ATT);
      wBndMgr.bind(methodText, (IExtensibleElement) element, CarnotConstants.METHOD_NAME_ATT);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public Control createBody(final Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      LabelWithStatus classLabel = FormBuilder.createLabelWithRightAlignedStatus(
         composite, Diagram_Messages.LB_Class);
      classBrowser = new TypeSelectionComposite(composite,
            Diagram_Messages.JfcPropertyPage_Class);
      classText = new LabeledText(classBrowser.getText(), classLabel);

      LabelWithStatus methodLabel = FormBuilder.createLabelWithRightAlignedStatus(
         composite, Diagram_Messages.LB_CompletionMethod);
      methodBrowser = new DereferencePathBrowserComposite(composite,
            Diagram_Messages.JfcPropertyPage_Method);
      methodBrowser.setDirection(DirectionType.INOUT_LITERAL);
      methodBrowser.setDeep(false);
      methodText = new LabeledText(methodBrowser.getMethodText(), methodLabel);

      classBrowser
            .setDereferencePathBrowser(new DereferencePathBrowserComposite[] {methodBrowser});

      return composite;
   }
}
