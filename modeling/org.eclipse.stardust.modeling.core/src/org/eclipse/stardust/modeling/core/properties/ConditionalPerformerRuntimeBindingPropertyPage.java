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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathModelAdapter2;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathWidgetAdapter2;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.BridgeObjectProviderRegistry;
import org.eclipse.stardust.modeling.validation.IBridgeObjectProvider;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

public class ConditionalPerformerRuntimeBindingPropertyPage
      extends AbstractModelElementPropertyPage
{
   private static final String USER_REALM = Diagram_Messages.LB_UserRealm;

   private static final String OID_ID = Diagram_Messages.LB_Oid_Id;

   private static final String USER_OID_ID = Diagram_Messages.LB_User_Oid_Id;

   private static final String KIND_ATT = PredefinedConstants.CONDITIONAL_PERFORMER_KIND;

   private static final String[][] labels = {
         {Diagram_Messages.LB_User, PredefinedConstants.CONDITIONAL_PERFORMER_KIND_USER},
         {
               Diagram_Messages.LB_Organization_Role,
               PredefinedConstants.CONDITIONAL_PERFORMER_KIND_MODEL_PARTICIPANT},
         {
               Diagram_Messages.LB_UserGroup,
               PredefinedConstants.CONDITIONAL_PERFORMER_KIND_USER_GROUP},
         {
               Diagram_Messages.LB_OrganizationRoleOrUserGroup,
               PredefinedConstants.CONDITIONAL_PERFORMER_KIND_MODEL_PARTICIPANT_OR_USER_GROUP}};

   private ComboViewer dataText;

   private AccessPathBrowserComposite dataPathBrowser;

   private Combo kindCombo;

   private LabelWithStatus dataLabel;

   private LabelWithStatus dataPathLabel;

   private ComboViewer realmDataText;

   private AccessPathBrowserComposite realmDataPathBrowser;

   private StackLayout stackLayout;

   private Composite stackLayoutComposite;

   private LabeledViewer realmDataLabelViewer;

   private LabeledText realmDataPathLabeledText;

   private Group realmGroup;

   private Group dataGroup;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager binding = getWidgetBindingManager();

      ModelType model = ModelUtils.findContainingModel(element);

      ConditionalPerformerType performer = (ConditionalPerformerType) element;
      AttributeType attribute = AttributeUtil.getAttribute(performer, KIND_ATT);
      if (attribute == null)
      {
         kindCombo.select(performer.isIsUser() ? 0 : 1);
      }
      else
      {
         kindCombo.select(-1);
         for (int i = 0; i < labels.length; i++)
         {
            if (labels[i][1].equals(attribute.getValue()))
            {
               kindCombo.select(i);
               break;
            }
         }
      }

      binding.getValidationBindingManager().bind(performer,
            CarnotWorkflowModelPackage.eINSTANCE.getConditionalPerformerType_Data(),
            dataLabel);

      binding.getValidationBindingManager().bind(performer,
            CarnotWorkflowModelPackage.eINSTANCE.getConditionalPerformerType_DataPath(),
            dataPathLabel);

      binding.getModelBindingManager().bind(
            new Data2DataPathModelAdapter2(model, model.getData()),
            new Data2DataPathWidgetAdapter2(dataText, dataPathBrowser,
                  DirectionType.IN_LITERAL));

      binding.getModelBindingManager().bind(performer,
            CarnotWorkflowModelPackage.eINSTANCE.getConditionalPerformerType_Data(),
            dataText);

      binding.getModelBindingManager().bind(performer,
            CarnotWorkflowModelPackage.eINSTANCE.getConditionalPerformerType_DataPath(),
            dataPathBrowser.getMethodText());

      binding.getModelBindingManager().bind(
            new Data2DataPathModelAdapter2(performer, model.getData()),
            new Data2DataPathWidgetAdapter2(realmDataLabelViewer.getViewer(),
                  realmDataPathBrowser, DirectionType.IN_LITERAL));

      binding.bind(realmDataLabelViewer, performer,
            PredefinedConstants.CONDITIONAL_PERFORMER_REALM_DATA, ModelUtils
                  .findContainingModel(performer), CarnotWorkflowModelPackage.eINSTANCE
                  .getModelType_Data());

      binding.bind(realmDataPathLabeledText, performer,
            PredefinedConstants.CONDITIONAL_PERFORMER_REALM_DATA_PATH);

      enableRealmData();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ConditionalPerformerType performer = (ConditionalPerformerType) element;
      int kind = kindCombo.getSelectionIndex();
      performer.setIsUser(kind == 0);
      AttributeUtil.setAttribute(performer, KIND_ATT, labels[kind][1]);
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      LabelWithStatus kindLabel = FormBuilder.createLabelWithRightAlignedStatus(
            composite, "Kind: "); //$NON-NLS-1$
      ((GridLayout) kindLabel.getLayout()).marginRight = 28;
      kindCombo = FormBuilder.createCombo(composite);
      for (int i = 0; i < labels.length; i++)
      {
         kindCombo.add(labels[i][0]);
      }

      createDataGroup(composite);

      stackLayoutComposite = new Composite(composite, SWT.NONE);
      GridData gridData = new GridData(SWT.FILL, SWT.NONE, false, false);
      gridData.horizontalSpan = 2;
      stackLayoutComposite.setLayoutData(gridData);
      stackLayout = new StackLayout();
      stackLayoutComposite.setLayout(stackLayout);

      createRealmDataGroup();

      kindCombo.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            enableRealmData();
         }
      });

      stackLayout.topControl = realmGroup;
      stackLayoutComposite.layout();
      return composite;
   }

   private void createDataGroup(Composite composite)
   {
      dataGroup = FormBuilder.createGroup(composite, USER_OID_ID, 2, 2);
      GridData gridDataGroup = new GridData(SWT.FILL, SWT.NONE, false, false);
      gridDataGroup.horizontalSpan = 2;
      dataGroup.setLayoutData(gridDataGroup);

      dataLabel = FormBuilder.createLabelWithRightAlignedStatus(dataGroup,
            Diagram_Messages.LB_Data);
      dataText = new ComboViewer(FormBuilder.createCombo(dataGroup));
      dataText.setSorter(new ViewerSorter());
      dataText.setContentProvider(new ArrayContentProvider());
      dataText.setLabelProvider(new EObjectLabelProvider(getEditor()));

      dataText.getCombo().addSelectionListener(new SelectionAdapter()
      {

         public void widgetSelected(SelectionEvent e)
         {
            enableRealmData();
         }
      });

      dataPathLabel = FormBuilder.createLabelWithRightAlignedStatus(dataGroup,
            Diagram_Messages.LB_DataPath);
      dataPathBrowser = new AccessPathBrowserComposite(getEditor(), dataGroup,
            Diagram_Messages.LB_DataPath);
      dataPathBrowser.getMethodText().addModifyListener(new ModifyListener() {

         public void modifyText(ModifyEvent e)
         {
             enableRealmData();
         }
      });
   }

   private void enableRealmData()
   {
      if (PredefinedConstants.CONDITIONAL_PERFORMER_KIND_USER.equals(labels[kindCombo
            .getSelectionIndex()][1])
            && !isReferencedByOid())
      {
         stackLayout.topControl = realmGroup;
         dataGroup.setText(USER_OID_ID);
      }
      else
      {
         stackLayout.topControl = null;
         dataGroup.setText(OID_ID);
         AttributeUtil.setAttribute(getPerformer(),
               PredefinedConstants.CONDITIONAL_PERFORMER_REALM_DATA, null);
      }
      stackLayoutComposite.layout();
   }

   private ConditionalPerformerType getPerformer()
   {
      return (ConditionalPerformerType) getModelElement();
   }

   private boolean isReferencedByOid()
   {
      boolean isReferencedByOid = false;
      DataType data = (DataType) ((IStructuredSelection) dataText.getSelection())
            .getFirstElement();
      if (data != null)
      {
         IBridgeObjectProvider dataBridgeProvider = BridgeObjectProviderRegistry.getBridgeObjectProvider(data);
         if (null != dataBridgeProvider)
         {
            try
            {
               BridgeObject userRefBridge = dataBridgeProvider.getBridgeObject(data, null, DirectionType.OUT_LITERAL);
               BridgeObject oidBridge = JavaDataTypeUtils.getBridgeObject(Long.class
                     .getName(), null, DirectionType.IN_LITERAL);
               if (oidBridge.acceptAssignmentFrom(userRefBridge))
               {
                  isReferencedByOid = true;
               }
            }
            catch (Exception e)
            {
            }
         }
      }
      return isReferencedByOid;
   }

   private void createRealmDataGroup()
   {
      realmGroup = FormBuilder.createGroup(stackLayoutComposite, USER_REALM, 2, 2);
      GridData gridRealmDataGroup = new GridData(SWT.FILL, SWT.NONE, false, false);
      gridRealmDataGroup.horizontalSpan = 2;
      realmGroup.setLayoutData(gridRealmDataGroup);

      LabelWithStatus realmDataLabel = FormBuilder.createLabelWithRightAlignedStatus(
            realmGroup, Diagram_Messages.LB_Data);
      realmDataText = new ComboViewer(FormBuilder.createCombo(realmGroup));
      realmDataText.setSorter(new ViewerSorter());
      realmDataText.setContentProvider(new ArrayContentProvider());
      realmDataText.setLabelProvider(new EObjectLabelProvider(getEditor()));
      realmDataLabelViewer = new LabeledViewer(realmDataText, realmDataLabel);

      LabelWithStatus realmDataPathLabel = FormBuilder.createLabelWithRightAlignedStatus(
            realmGroup, Diagram_Messages.LB_DataPath);
      realmDataPathBrowser = new AccessPathBrowserComposite(getEditor(), realmGroup,
            Diagram_Messages.LB_DataPath);
      realmDataPathLabeledText = new LabeledText(realmDataPathBrowser.getMethodText(),
            realmDataPathLabel);
   }
}