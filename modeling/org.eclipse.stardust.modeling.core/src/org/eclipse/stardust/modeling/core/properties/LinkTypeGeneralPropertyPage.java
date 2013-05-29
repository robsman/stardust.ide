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

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LinkCardinality;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledCombo;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class LinkTypeGeneralPropertyPage extends AbstractModelElementPropertyPage
{
   public static final String[][] TYPE_LABELS = {
         {"org.eclipse.stardust.model.xpdl.IActivity", Diagram_Messages.LINK_TYPE_LB_Activity}, //$NON-NLS-1$
         {"org.eclipse.stardust.model.xpdl.IData", Diagram_Messages.LINK_TYPE_LB_Data}, //$NON-NLS-1$
         {"org.eclipse.stardust.model.xpdl.IRole", Diagram_Messages.TLINK_TYPE_LB_Role}, //$NON-NLS-1$
         {"org.eclipse.stardust.model.xpdl.IProcessDefinition", Diagram_Messages.LINK_TYPE_LB_Process}, //$NON-NLS-1$
         {"org.eclipse.stardust.model.xpdl.ITransition", Diagram_Messages.LINK_TYPE_LB_Transition}, //$NON-NLS-1$
         {"org.eclipse.stardust.model.xpdl.IOrganization", Diagram_Messages.LINK_TYPE_LB_Organization}, //$NON-NLS-1$
         {"org.eclipse.stardust.model.xpdl.IParticipant", Diagram_Messages.LINK_TYPE_LB_Participant}, //$NON-NLS-1$
   };

   private LabeledText sourceRoleText;

   private LabeledText targetRoleText;

   private LabeledViewer sourceCardinalityViewer;

   private LabeledViewer targetCardinalityViewer;

   private LabeledViewer targetTypeViewer;

   private LabeledViewer sourceTypeViewer;

   private LabeledText txtId;
   private LabeledText txtName;

   private Button autoIdButton;
   
   
   private SelectionListener autoIdListener = new SelectionListener()
   {
      public void widgetDefaultSelected(SelectionEvent e)
      {
      }

      public void widgetSelected(SelectionEvent e)
      {
         boolean selection = ((Button) e.widget).getSelection();
         if(selection)
         {
            txtId.getText().setEditable(false);
            String computedId = NameIdUtils.createIdFromName(null, getModelElement());
            txtId.getText().setText(computedId);            
         }
         else
         {
            txtId.getText().setEditable(true);            
         }         
      }
   };       
   
   private ModifyListener listener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         if (autoIdButton.getSelection())
         {
            String computedId = NameIdUtils.createIdFromName(null, getModelElement());
            txtId.getText().setText(computedId);
         }
      }
   };
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement node)
   {
      txtName.getText().removeModifyListener(listener);      
      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      // ignore oid
      wBndMgr.bind(txtId, node, PKG_CWM.getIIdentifiableElement_Id());      
      wBndMgr.bind(txtName, node, PKG_CWM.getIIdentifiableElement_Name());

      wBndMgr.bind(sourceRoleText, node, PKG_CWM.getLinkTypeType_SourceRole());
      wBndMgr.bind(targetRoleText, node, PKG_CWM.getLinkTypeType_TargetRole());

      wBndMgr.bind(sourceCardinalityViewer, node, PKG_CWM
            .getLinkTypeType_SourceCardinality(), null);
      wBndMgr.bind(targetCardinalityViewer, node, PKG_CWM
            .getLinkTypeType_TargetCardinality(), null);

      wBndMgr.bind(sourceTypeViewer, node, PKG_CWM.getLinkTypeType_SourceClass(), null);
      wBndMgr.bind(targetTypeViewer, node, PKG_CWM.getLinkTypeType_TargetClass(), null);
      
      txtName.getText().addModifyListener(listener);
      
      txtName.getText().selectAll();
      txtName.getText().setFocus();      
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      GenericUtils.setAutoIdValue(getModelElement(), autoIdButton.getSelection());      
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createLabeledControlsComposite(parent);

      // todo: (fh) use a non editable drop down table, to include icons.
      txtName = FormBuilder.createLabeledText(composite, Diagram_Messages.LINK_TYPE_LB_Name);
      txtName.setTextLimit(80);
      this.txtId = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_ID);
      txtId.setTextLimit(80);      

      autoIdButton = FormBuilder.createCheckBox(composite, Diagram_Messages.BTN_AutoId, 2);
      boolean autoIdButtonValue = GenericUtils.getAutoIdValue(getModelElement());
      autoIdButton.setSelection(autoIdButtonValue);
      if(autoIdButtonValue)
      {
         txtId.getText().setEditable(false);
      }
      autoIdButton.addSelectionListener(autoIdListener);
            
      sourceTypeViewer = createTypeViewer(composite,
            Diagram_Messages.LINK_TYPE_LB_SourceType);
      sourceRoleText = FormBuilder.createLabeledText(composite,
            Diagram_Messages.LINK_TYPE_LB_SourceRole);
      sourceCardinalityViewer = FormBuilder.createComboViewer(composite,
            Diagram_Messages.LINK_TYPE_LB_SourceCardinality, LinkCardinality.VALUES);
      FormBuilder.createLabel(composite, " ", 2); //$NON-NLS-1$
      targetTypeViewer = createTypeViewer(composite,
            Diagram_Messages.LINK_TYPE_LB_TargetType);
      targetRoleText = FormBuilder.createLabeledText(composite,
            Diagram_Messages.LINK_TYPE_LB_TargetRole);
      targetCardinalityViewer = FormBuilder.createComboViewer(composite,
            Diagram_Messages.LINK_TYPE_LB_TargetCardinality, LinkCardinality.VALUES);

      return composite;
   }

   private LabeledViewer createTypeViewer(Composite parent, String label)
   {
      LabeledCombo targetCombo = FormBuilder.createLabeledCombo(parent, label);
      ComboViewer targetViewer = new ComboViewer(targetCombo.getCombo());
      targetViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element1)
         {
            for (int i = 0; i < TYPE_LABELS.length; i++)
            {
               if (TYPE_LABELS[i][0].equals(element1))
               {
                  return TYPE_LABELS[i][1];
               }
            }
            return super.getText(element1);
         }
      });
      for (int i = 0; i < TYPE_LABELS.length; i++)
      {
         targetViewer.add(TYPE_LABELS[i][0]);
      }
      LabeledViewer labeledViewer = new LabeledViewer(targetViewer, targetCombo
            .getLabel());
      return labeledViewer;
   }
}