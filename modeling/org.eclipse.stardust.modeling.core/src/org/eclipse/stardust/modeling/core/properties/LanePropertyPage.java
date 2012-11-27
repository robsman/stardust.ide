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

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.properties.DefaultPropSheetCmdFactory;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.utils.HierarchyUtils;
import org.eclipse.stardust.modeling.core.utils.IdentifiableViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;


public class LanePropertyPage extends IdentifiablePropertyPage
{
   private LabeledViewer labeledWidget;
   private IModelParticipant originalPerformer;
   private TableViewer viewer;
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadFieldsFromElement(symbol, element);
      
      LaneSymbol lane = (LaneSymbol) element;
      ModelType model = ModelUtils.findContainingModel(lane);
      viewer = (TableViewer) labeledWidget.getViewer();
      viewer.getTable().removeAll();
      // get all particpants, depends on assigned participants of parent lanes
      List participants = HierarchyUtils.getParticipants(lane, model);
      originalPerformer = lane.getParticipantReference();      
      if(HierarchyUtils.hasChildLanesParticipant(lane))
      {
         setMessage(Diagram_Messages.LanePropertyPage_SetParticipantInformation, INFORMATION);
         if(originalPerformer != null)
         {
            viewer.add(new Object[] {originalPerformer});            
         }
      }
      else
      {
         viewer.add(participants.toArray());               
      }      
      viewer.setSelection(originalPerformer == null ? null
            : new StructuredSelection(originalPerformer));
   }

   protected void performDefaults()
   {
      super.performDefaults();
      viewer.setSelection(originalPerformer == null ? null
            : new StructuredSelection(originalPerformer));      
   }   
   
   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      LaneSymbol lane = (LaneSymbol) element;
      originalPerformer = lane.getParticipantReference();
      TableViewer viewer = (TableViewer) labeledWidget.getViewer();
      IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
      IModelParticipant newPerformer = (IModelParticipant)
         (selection.isEmpty() ? null : selection.getFirstElement());

      if (newPerformer != originalPerformer)
      {
         DefaultPropSheetCmdFactory.INSTANCE.getSetCommand((EditPart) getElement(),
            (LaneSymbol) element,
            PKG_CWM.getISwimlaneSymbol_ParticipantReference(), newPerformer).execute();
      }
   }

   public void contributeExtraControls(Composite composite)
   {
      LabelWithStatus label = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.PARTICIPANT_LABEL, 3);

      GridLayout layout = new GridLayout(2, false);
      layout.horizontalSpacing = 5;      
      
      Composite tableComposite = FormBuilder.createComposite(composite, 2, 3);
      tableComposite.setLayout(layout);
      
      Table table = new Table(tableComposite, SWT.BORDER);
      table.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      TableViewer tableViewer = new TableViewer(table);
      tableViewer.setLabelProvider(new EObjectLabelProvider(getEditor()));
      tableViewer.setSorter(new IdentifiableViewerSorter());
      labeledWidget = new LabeledViewer(tableViewer, label);
            
      Button button = FormBuilder.createButton(tableComposite,
            Diagram_Messages.UncheckButton,
               new SelectionAdapter()
               {
                  public void widgetSelected(SelectionEvent e)
                  {
                     IModelElement modelElement = (IModelElement) getModelElement();
                     if(modelElement instanceof LaneSymbol)
                     {
                        LaneSymbol symbol = (LaneSymbol) modelElement;
                        symbol.setParticipantReference(null);
                        viewer.setSelection(null);
                     }
                  }
               });
      GridData data = (GridData) button.getLayoutData();
      data.verticalAlignment = SWT.TOP;
   }   
}