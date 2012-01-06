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
package org.eclipse.stardust.modeling.core.spi.triggerTypes.manual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ScopeUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.properties.LaneParticipantCommandFactory;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.SpiPropertyPage;
import org.eclipse.stardust.modeling.core.utils.IdentifiableViewerSorter;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;

public class ManualPropertyPage extends AbstractModelElementPropertyPage
{
   private LabeledViewer labeledWidget;
   private WidgetBindingManager wBndMgr = null;
   
   private IModelParticipant originalPerformer = null;
   private TriggerType trigger = null;
   
   private SpiPropertyPage spiPage;
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      trigger = (TriggerType) element;
      ModelType model = ModelUtils.findContainingModel(element);
      TableViewer viewer = (TableViewer) labeledWidget.getViewer();
      viewer.getTable().removeAll();
      List<IModelParticipant> participants = new ArrayList<IModelParticipant>();
      participants.addAll(model.getRole());
      participants.addAll(model.getOrganization());
      viewer.add(participants.toArray());

      wBndMgr = getWidgetBindingManager();
      wBndMgr.bind(labeledWidget, (IExtensibleElement) element,
            PredefinedConstants.PARTICIPANT_ATT, participants);
      wBndMgr.getModelBindingManager().updateWidgets(element);      
      
      TriggerType trigger = (TriggerType) element;
      AttributeType attribute = AttributeUtil.getAttribute(trigger,
            PredefinedConstants.PARTICIPANT_ATT);
      if (attribute != null)
      {
         originalPerformer = (IModelParticipant) AttributeUtil.getReferenceElement(attribute);
      }
      validate(originalPerformer);
   }

   public void loadElementFromFields(final IModelElementNodeSymbol symbol, IModelElement element)
   {
      CompoundCommand command = new CompoundCommand();
      
      IModelParticipant newPerformer = null;
      TriggerType trigger = (TriggerType) element;
      AttributeType attribute = AttributeUtil.getAttribute(trigger,
            PredefinedConstants.PARTICIPANT_ATT);
      if (attribute != null)
      {
         newPerformer = (IModelParticipant) AttributeUtil.getReferenceElement(attribute);         
      }      
      LaneParticipantCommandFactory.addSetPerformerCommands(command, trigger, newPerformer, originalPerformer, attribute, true, null);
      command.execute();
   }
   
   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      LabelWithStatus label = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.FORMBUILDER_LB_Participants);

      Table table = new Table(composite, SWT.BORDER);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      TableViewer tableViewer = new TableViewer(table);
      tableViewer.setLabelProvider(new EObjectLabelProvider(getEditor()));
      tableViewer.setSorter(new IdentifiableViewerSorter());
      tableViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            if (event.getSelection() instanceof IStructuredSelection)
            {
               IModelParticipant participant = (IModelParticipant) ((IStructuredSelection) event.getSelection()).getFirstElement();
               validate(participant);
            }            
         }         
      });
      
      labeledWidget = new LabeledViewer(tableViewer, label);

      return composite;
   }

   public boolean performCancel()
   {
      AttributeUtil.setReference(trigger, PredefinedConstants.PARTICIPANT_ATT, originalPerformer);
      return super.performCancel();
   }
   
   public void setDelegateContainer(AbstractModelElementPropertyPage page)
   {
      spiPage = (SpiPropertyPage) page;
   }      
   
   private void validate(IModelParticipant performer)
   {
      boolean isValid = true;
      if(performer != null)
      {
         ModelType model = ModelUtils.findContainingModel(performer);
         HashSet<IModelParticipant> scoped = ScopeUtils.findScopedParticipants(model);            
         // is scoped participant?
         if(scoped.contains(performer))
         {
            isValid = ScopeUtils.isValidScopedParticipantForManualTrigger(performer);
         }
      }      

      if(isValid)
      {
         spiPage.setMessage(null);
      }
      else
      {
         spiPage.setMessage(Diagram_Messages.MSG_INVALID_SCOPED_PARTICIPANT, WARNING);
      }      
   }   
   
   /**
    * new Button to Uncheck the assigned participant
    */
   public void contributeVerticalButtons(Composite parent)
   {
      FormBuilder.createButton(parent, Diagram_Messages.UncheckButton,
         new SelectionAdapter()
         {
            public void widgetSelected(SelectionEvent e)
            {
               IModelElement modelElement = (IModelElement) getModelElement();
               if(modelElement instanceof TriggerType)
               {
                  AttributeUtil.setReference(trigger, PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT, null);                  
                  wBndMgr.getModelBindingManager().updateWidgets(trigger);      
               }
            }
         }
      );
   } 
}