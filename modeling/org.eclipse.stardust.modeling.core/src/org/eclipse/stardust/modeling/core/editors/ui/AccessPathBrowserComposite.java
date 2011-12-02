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
package org.eclipse.stardust.modeling.core.editors.ui;

import org.eclipse.jface.window.Window;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.ui.AccessPathWizard;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import ag.carnot.workflow.model.PredefinedConstants;

public class AccessPathBrowserComposite
{
   private Text methodText;
   private IDirectionProvider directionProvider;
   private DirectionType staticDirection = DirectionType.OUT_LITERAL;
   private ITypedElement accessPoint;
   private Button browseButton;
   private WorkflowModelEditor editor;
   private boolean browsePrimitiveAllowed = true;

   public AccessPathBrowserComposite(WorkflowModelEditor editor, Composite parent, String title)
   {
      init(editor, parent, title, 1);
   }

   public AccessPathBrowserComposite(WorkflowModelEditor editor, Composite parent, String title, int span)
   {
      init(editor, parent, title, span);
   }
   
   public AccessPathBrowserComposite(WorkflowModelEditor editor, Composite parent,
         String title, boolean browsePrimAllowed)
   {
      this.browsePrimitiveAllowed = browsePrimAllowed;
      init(editor, parent, title, 1);
   }

   private void init(final WorkflowModelEditor editor, Composite parent, final String title, int span)
   {
      this.editor = editor;
      final Composite composite = FormBuilder.createComposite(parent, 2);
      GridData gd = FormBuilder.createDefaultSingleLineWidgetGridData();
      gd.horizontalSpan = span;
      ((GridLayout) composite.getLayout()).marginHeight = 0;
      ((GridLayout) composite.getLayout()).marginWidth = 0;
      composite.setLayoutData(gd);

      methodText = FormBuilder.createText(composite);
      browseButton = FormBuilder.createButton(composite,
            Diagram_Messages.Btn_Browse, new SelectionListener()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  AccessPathWizard browser = new AccessPathWizard(editor,
                        title, accessPoint, getDirection());
                  browser.setMethod(getMethod());
                  if (browser.open() == Window.OK)
                  {
                     methodText.setText(browser.getSelectedMethod() == null
                           ? "" : browser.getSelectedMethod()); //$NON-NLS-1$
                  }
               }

               public void widgetDefaultSelected(SelectionEvent e)
               {}
            });
   }

   public String getMethod()
   {
      return methodText.isEnabled() ? methodText.getText().trim() : ""; //$NON-NLS-1$
   }

   public void setMethod(String methodName)
   {
      methodText.setText(methodName);
   }

   public Text getMethodText()
   {
      return methodText;
   }
   
   public Button getBrowseButton()
   {
      return browseButton;
   }
   
   public void setDirectionProvider(IDirectionProvider provider)
   {
      this.directionProvider = provider;
   }

   public void setAccessPoint(ITypedElement element, DirectionType direction)
   {
      this.staticDirection = direction;
      accessPoint = element;
      boolean enable = (accessPoint instanceof IExtensibleElement)
         && ( !DirectionType.IN_LITERAL.equals(getDirection())
         || AttributeUtil.getBooleanValue((IExtensibleElement) accessPoint,
            CarnotConstants.BROWSABLE_ATT));
      
      if ((element instanceof DataType) && (null != ((DataType) element).getType()))
      {
         enable = true;
      }

      if(enable && !editor.getModelServer().isLockedByUser())
      {
         enable = false;
      }
      
      methodText.setEnabled(enable && element != null && dataTypeSupportsBrowsing(element.getMetaType()));
      browseButton.setEnabled(enable && element != null && dataTypeSupportsBrowsing(element.getMetaType()));
      if (!enable)
      {
         methodText.setText(""); //$NON-NLS-1$
      }
      if (element != null && !dataTypeSupportsBrowsing(element.getMetaType())) {
    	  methodText.setText(""); //$NON-NLS-1$
      }
   }
   
   private DirectionType getDirection()
   {
      return (null != directionProvider)
            ? directionProvider.getDirection()
            : staticDirection;
   }

   private boolean dataTypeSupportsBrowsing(IMetaType type) {
		if (type instanceof DataTypeType) {
			DataTypeType dataType = (DataTypeType) type;
			if (PredefinedConstants.PRIMITIVE_DATA.equals(type.getId()) && !browsePrimitiveAllowed) {
				return false;
			}
			IAccessPathEditor editor = AccessPointUtil
					.getSPIAccessPathEditor(dataType);
			return editor != null && editor.supportsBrowsing();
		}
		return false;
	}
   
   public static interface IDirectionProvider
   {
      DirectionType getDirection();
   }

   public void setEnabled(boolean enabled)
   {
      methodText.setEnabled(enabled);
      browseButton.setEnabled(enabled);
   }
}