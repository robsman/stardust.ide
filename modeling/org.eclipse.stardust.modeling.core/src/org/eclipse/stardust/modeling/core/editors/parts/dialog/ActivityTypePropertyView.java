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
package org.eclipse.stardust.modeling.core.editors.parts.dialog;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.impl.ActivitySymbolTypeImpl;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class ActivityTypePropertyView extends AbstractPropertyView
{
   private Label nameLabel = null;

   private Text nameText = null;

   private Text descriptionTextArea = null;

   private Label descriptionLabel = null;

   private Button abortableButton = null;

   private Button initiallyHibernatedButton = null;

   private Label abortableLabel = null;

   private Label initallyHibernatedLabel = null;

   private Button button = null;

   public void createPartControl(Composite parent)
   {
      composite = new Composite(parent, SWT.NONE);
      composite.setBounds(new org.eclipse.swt.graphics.Rectangle(0, 0, 500, 350));

      // item.setControl(this.composite);

      idLabel = new Label(composite, SWT.NONE);
      idLabel.setBounds(new org.eclipse.swt.graphics.Rectangle(5, 15, 60, 13));
      idLabel.setText(Diagram_Messages.LB_ID);

      idText = new Text(composite, SWT.BORDER);
      idText.setBounds(new org.eclipse.swt.graphics.Rectangle(70, 15, 230, 19));

      nameLabel = new Label(composite, SWT.NONE);
      nameLabel.setBounds(new org.eclipse.swt.graphics.Rectangle(5, 45, 60, 13));
      nameLabel.setText(Diagram_Messages.LB_Name);

      nameText = new Text(composite, SWT.BORDER);
      nameText.setBounds(new org.eclipse.swt.graphics.Rectangle(70, 45, 230, 19));

      abortableButton = new Button(composite, SWT.CHECK);
      abortableButton.setBounds(new org.eclipse.swt.graphics.Rectangle(70, 75, 13, 16));
      abortableLabel = new Label(composite, SWT.NONE);
      abortableLabel.setBounds(new org.eclipse.swt.graphics.Rectangle(90, 75, 141, 13));
      abortableLabel.setText(Diagram_Messages.LB_AllowsAbort);

      initiallyHibernatedButton = new Button(composite, SWT.CHECK);
      initiallyHibernatedButton.setBounds(new org.eclipse.swt.graphics.Rectangle(70, 97,
            13, 16));
      initallyHibernatedLabel = new Label(composite, SWT.NONE);
      initallyHibernatedLabel.setBounds(new org.eclipse.swt.graphics.Rectangle(90, 97,
            105, 13));
      initallyHibernatedLabel.setText(Diagram_Messages.LB_InitiallyHibernate);

      descriptionLabel = new Label(composite, SWT.NONE);
      descriptionLabel.setBounds(new org.eclipse.swt.graphics.Rectangle(5, 120, 60, 13));
      descriptionLabel.setText(Diagram_Messages.LB_Description);

      descriptionTextArea = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL
            | SWT.BORDER);
      descriptionTextArea.setBounds(new org.eclipse.swt.graphics.Rectangle(5, 145, 300,
            100));

      button = new Button(composite, SWT.NONE);
      button.setBounds(new org.eclipse.swt.graphics.Rectangle(228, 250, 71, 23));
      button.setText(Diagram_Messages.B_Apply);
      button.addListener(SWT.Selection, this.createApplyButtonListener());
   }

   public void selectionChanged(AbstractEditPart editPart)
   {
      if (editPart != null)
      {
         this.editPart = editPart;

         EObject eObject = (EObject) editPart.getModel();

         if (eObject instanceof ActivitySymbolTypeImpl)
         {
            ActivitySymbolTypeImpl organizationSymbolType = (ActivitySymbolTypeImpl) eObject;

            oldValue = organizationSymbolType.getModelElement();

            this.writeContent(oldValue);

            newValue = EcoreUtil.copy(oldValue);
         }
      }
   }

   public void dispose()
   {
      this.composite.dispose();
   }

   public void writeContent(EObject eObject)
   {
      this.writeContent((ActivityType) eObject);
   }

   public void readContent(EObject eObject)
   {
      this.readContent((ActivityType) eObject);
   }

   private void writeContent(ActivityType activityType)
   {
      this.idText.setText(activityType.getId() != null ? activityType.getId() : ""); //$NON-NLS-1$
      this.nameText.setText(activityType.getName() != null ? activityType.getName() : ""); //$NON-NLS-1$
      this.abortableButton.setSelection(activityType.isAllowsAbortByPerformer());
      this.initiallyHibernatedButton.setSelection(activityType.isHibernateOnCreation());
   }

   private void readContent(ActivityType activityType)
   {
      activityType.setId(this.idText.getText());
      activityType.setName(this.nameText.getText());
      activityType.setAllowsAbortByPerformer(this.abortableButton.getSelection());
      activityType.setHibernateOnCreation(this.initiallyHibernatedButton.getSelection());
   }
}
