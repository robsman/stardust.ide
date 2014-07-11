/*******************************************************************************
 * Copyright (c) 2014 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Florin.Herinean (SunGard CSA LLC) - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.stardust.modeling.core.ui;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.IdentifiableLabelProvider;
import org.eclipse.stardust.modeling.core.properties.ReferencedModelSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ProcessDefinitionSelectionViewer extends TableViewer
{
   private List<ProcessDefinitionType> processes;

   public ProcessDefinitionSelectionViewer(Composite parent, WorkflowModelEditor editor)
   {
      super(parent, SWT.BORDER);
      getTable().setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      setLabelProvider(new IdentifiableLabelProvider(editor));
      setSorter(new ReferencedModelSorter());

      reset(editor.getWorkflowModel());
   }

   public List<ProcessDefinitionType> reset(ModelType model)
   {
      ((IdentifiableLabelProvider) getLabelProvider()).setModel(model);
      getTable().removeAll();
      processes = collectProcessDefinitions(model);
      add(processes.toArray());
      return processes;
   }

   private static List<ProcessDefinitionType> collectProcessDefinitions(ModelType model)
   {
      final List<ProcessDefinitionType> processes = CollectionUtils.newList();
      processes.addAll(model.getProcessDefinition());

      ModelUtils.forEachReferencedModel(model, new Predicate<ModelType>()
      {
         @Override
         public boolean accept(ModelType externalModel)
         {
            for (ProcessDefinitionType extProcess : externalModel.getProcessDefinition())
            {
               if (extProcess.getFormalParameters() != null)
               {
                  processes.add(extProcess);
               }
            }
            return true;
         }
      });
      return processes;
   }

   public void setGrouped(boolean grouped)
   {
      ((ReferencedModelSorter) getSorter()).setGrouped(grouped);
      ((IdentifiableLabelProvider) getLabelProvider()).setShowGroupInfo(grouped);
      ISelection selection = getSelection();
      getTable().removeAll();
      add(processes.toArray());
      setSelection(selection);
   }
}
