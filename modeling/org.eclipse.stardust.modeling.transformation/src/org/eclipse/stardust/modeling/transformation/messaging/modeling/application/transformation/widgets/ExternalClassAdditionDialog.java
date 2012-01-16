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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.MessageTransformationModelingPlugin;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.PrimitivesFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.SerializableFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.StructuredTypesFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

public class ExternalClassAdditionDialog extends Dialog implements ModifyListener
{
   private static final int RESET_ID = IDialogConstants.NO_TO_ALL_ID + 1;

   private Text messageNameText;

   private String tagName;

   private MessageTransformationController controller;

   private AccessPointType messageType;

   private String messageName;

   private List messageTypes;

   private String preset;

   private List allMessageTypes = new ArrayList();

   private List typeFilters = new ArrayList();

   private Label errorLabel;

   private WorkflowModelEditor wfme;

   private Composite mainComposite;

   private boolean isError = false;

   // private Combo dataTypeCombo;

   private TypeSelectionComposite classBrowser;

   private StackLayout stack;

   private Composite structPrimComposite;

   private Composite messageComposite;

   protected ViewerFilter selectedFilter;

   private DirectionType directionType;

   public ExternalClassAdditionDialog(Shell parentShell,
         MessageTransformationController controller, List messageTypes, String preset,
         DirectionType directionType)
   {
      super(parentShell);
      this.controller = controller;
      this.messageTypes = messageTypes;
      this.directionType = directionType;
      this.preset = preset;
      allMessageTypes.addAll(controller.getSourceMessageTypes());
      allMessageTypes.addAll(controller.getTargetMessageTypes());
      allMessageTypes.addAll(controller.getExternalClassTypes());
      typeFilters.add(new StructuredTypesFilter());
      typeFilters.add(new PrimitivesFilter());
      if (!controller.isSimpleMode())
      {
         typeFilters.add(new SerializableFilter());
      }
   }

   protected Control createDialogArea(Composite parent)
   {
      mainComposite = parent;
      if ((null != PlatformUI.getWorkbench())
            && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow())
            && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage()))
      {
         IEditorPart currentEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
               .getActivePage().getActiveEditor();
         if (currentEditor instanceof WorkflowModelEditor)
         {
            wfme = (WorkflowModelEditor) currentEditor;
         }
      }

      Composite comp = (Composite) super.createDialogArea(parent);
      comp.setLayout(new GridLayout());

      Composite groupComposite = new Group(comp, SWT.NONE);

      GridLayout layout = new GridLayout();
      layout.numColumns = 3;
      groupComposite.setLayout(layout);

      Label tagNameLabel = new Label(groupComposite, SWT.LEFT);
      tagNameLabel.setText(MessageFormat.format(Modeling_Messages.TXT_NAME,
            new Object[] {controller.getNameString()}));
      if (controller.isSimpleMode())
      {

      }
      else
      {
         tagNameLabel.setText(Modeling_Messages.TXT_INSTANCE_NAME);
      }
      messageNameText = new Text(groupComposite, SWT.SINGLE | SWT.BORDER);
      messageNameText.addModifyListener(this);
      GridData data = new GridData(GridData.FILL_HORIZONTAL);
      messageNameText.setLayoutData(data);
      errorLabel = new Label(groupComposite, SWT.NONE);
      errorLabel.setImage(MessageTransformationModelingPlugin.getDefault()
            .getImageDescriptor("icons/error.gif").createImage()); //$NON-NLS-1$
      errorLabel.setVisible(false);

      Label typeLabel = new Label(groupComposite, SWT.LEFT);
      typeLabel.setText(Modeling_Messages.TXT_CLASS);

      classBrowser = new TypeSelectionComposite(groupComposite,
            Modeling_Messages.PlainJavaPropertyPage_LB_Plain_Java);
      classBrowser.getText().addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            serializableTypeModified(e);
         }

      });

      parent.getShell().setMinimumSize(300, 150);
      parent.getShell().setText(Modeling_Messages.TXT_ADD_EXTERNAL_CL);

      return comp;
   }

   protected void buttonEnablement()
   {
      String text = messageNameText.getText();
      this.errorLabel.setVisible(isError);
      if (getButton(IDialogConstants.OK_ID) != null)
      {
         getButton(IDialogConstants.OK_ID).setEnabled(
               !isError && !text.equalsIgnoreCase("") && text.indexOf(" ") == -1); //$NON-NLS-1$ //$NON-NLS-2$
      }
   }

   protected void createButtonsForButtonBar(Composite parent)
   {
      super.createButtonsForButtonBar(parent);
      getButton(IDialogConstants.OK_ID).setEnabled(false);
      this.buttonEnablement();
   }

   protected void buttonPressed(int buttonId)
   {
      if (buttonId == IDialogConstants.OK_ID)
      {
         messageName = messageNameText.getText();
      }
      super.buttonPressed(buttonId);

   }

   public void modifyText(ModifyEvent arg0)
   {
      String text = messageNameText.getText();
      if (!controller.isSimpleMode())
      {
         if (getMessageTypeByName(text) != null)
         {
            isError = true;
            errorLabel.setToolTipText(MessageFormat
                  .format(Modeling_Messages.TXT_ALREADY_EXIST_WITHIN_CONTEXT,
                        new Object[] {text}));
         }
         else
         {
            if (!StringUtils.isValidIdentifier(text))
            {
               isError = true;
               errorLabel.setToolTipText(MessageFormat.format(
                     Modeling_Messages.TXT_NOT_VALID_NAME, new Object[] {text}));
            }
            else
            {
               isError = false;
               errorLabel.setToolTipText(null);
            }
         }
      }
      else
      {
         if (!StringUtils.isValidIdentifier(text))
         {
            isError = true;
            errorLabel.setToolTipText(MessageFormat.format(
                  Modeling_Messages.TXT_NOT_VALID_NAME, new Object[] {text}));
         }
         else
         {
            isError = false;
            errorLabel.setToolTipText(null);
         }
      }
      buttonEnablement();
   }

   private void serializableTypeModified(ModifyEvent e)
   {
      if (classBrowser.getType() != null)
      {
         String messageName = classBrowser.getType().getType().getElementName();
         String text = messageName;
         int n = 1;
         while (isAccessPointIdDefined(text + n))
         {
            n++;
         }
         text = text + n;
         messageNameText.setText(text);
         AccessPointType apt = controller.getMtaUtils().createSerializableAccessPoint(
               classBrowser.getType(), text, directionType);
         this.messageType = apt;
      }
   }

   public AccessPointType getMessageType()
   {
      return messageType;
   }

   public String getMessageName()
   {
      return messageName;
   }

   private boolean isAccessPointIdDefined(String id)
   {
      for (Iterator i = allMessageTypes.iterator(); i.hasNext();)
      {
         AccessPointType messageType = (AccessPointType) i.next();
         if (messageType.getId().equalsIgnoreCase(id))
         {
            return true;
         }
      }
      return false;
   }

   private AccessPointType getMessageTypeByName(String name)
   {
      for (Iterator i = allMessageTypes.iterator(); i.hasNext();)
      {
         AccessPointType messageType = (AccessPointType) i.next();
         if (messageType.getId().equalsIgnoreCase(name))
         {
            return messageType;
         }
      }
      return null;
   }

}
