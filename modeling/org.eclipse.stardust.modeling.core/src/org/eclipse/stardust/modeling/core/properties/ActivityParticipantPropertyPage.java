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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteConnectionSymbolCmd;
import org.eclipse.stardust.modeling.core.editors.parts.properties.LaneParticipantCommandFactory;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.utils.IdentifiableViewerSorter;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ActivityParticipantPropertyPage extends AbstractModelElementPropertyPage
{
   public static final String PARTICIPANT_ID = "_cwm_participant_"; //$NON-NLS-1$

   public static final String PARTICIPANT_LABEL = Diagram_Messages.PARTICIPANT_LABEL;

   private LabeledViewer labeledWidget;

   private IModelParticipant originalPerformer;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ActivityType activity = (ActivityType) element;
      ModelType model = (ModelType) activity.eContainer().eContainer();
      TableViewer viewer = (TableViewer) labeledWidget.getViewer();
      viewer.getTable().removeAll();
      List<IModelParticipant> participants = new ArrayList<IModelParticipant>();
      participants.addAll(model.getRole());
      participants.addAll(model.getOrganization());
      participants.addAll(model.getConditionalPerformer());
      viewer.add(participants.toArray());

      WidgetBindingManager wBndMgr = getWidgetBindingManager();
      wBndMgr.bind(labeledWidget, element, PKG_CWM.getActivityType_Performer());
      wBndMgr.getModelBindingManager().updateWidgets(element);
      originalPerformer = activity.getPerformer();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ActivityType activity = (ActivityType) element;
      IModelParticipant newPerformer = activity.getPerformer();

      CompoundCommand command = new CompoundCommand();
      LaneParticipantCommandFactory.addSetPerformerCommands(command, activity,
            newPerformer, originalPerformer, true, null);
      if ((newPerformer != null) && (newPerformer != originalPerformer))
      {         
         if (symbol != null)
         {
            ActivitySymbolType activitySymbol = (ActivitySymbolType) symbol;
            for (PerformsConnectionType performsConnection : activitySymbol.getPerformsConnections())
            {
               if (performsConnection.getParticipantSymbol()
                     .getModelElement().equals(originalPerformer))
               {
                  command.add(new DeleteConnectionSymbolCmd(performsConnection));
               }
            }
         }
      }
      command.execute();
      originalPerformer = activity.getPerformer();
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
      labeledWidget = new LabeledViewer(tableViewer, label);

      return composite;
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
               if(modelElement instanceof ActivityType)
               {
                  ActivityType activity = (ActivityType) modelElement;
                  activity.setPerformer(null);
                  originalPerformer = null;
               }
            }
         }
      );
   }
}