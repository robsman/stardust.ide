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
package org.eclipse.stardust.modeling.core.modelserver.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.CurrentModelFinder;
import org.eclipse.stardust.modeling.core.modelserver.ModelHistoryEntry;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.modelserver.RMSException;
import org.eclipse.stardust.modeling.core.modelserver.ui.vcsfeedtable.VcsFeedTable;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;


public class VcsFeedView extends ViewPart 
   implements IPartListener
{
   private Composite parent;
   private CurrentModelFinder modelFinder;
   private VcsFeedTable vcsFeedTable;
   private Action refreshAction;
   private ModelType currentModel;
   
   public void init(IViewSite site, IMemento memento) throws PartInitException
   {
      super.init(site, memento);
      modelFinder = CurrentModelFinder.getInstance();
      modelFinder.addListener(this);
   }

   public void dispose()
   {
      modelFinder.removeListener(this);
      super.dispose();
   }

   public void replaceData(List<ModelHistoryEntry> modelHistoryEntries)
   {
      if(vcsFeedTable != null)
      {
         vcsFeedTable.replaceData(modelHistoryEntries);
      }
   }   
   
   public void createPartControl(Composite parent)
   {
      this.parent = parent;
      
      GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 1;
      parent.setLayout(gridLayout);
      
      IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();

      refreshAction = new Action(Diagram_Messages.LB_Refresh, DiagramPlugin.getImageDescriptor("icons/full/obj16/refresh.gif")) //$NON-NLS-1$
      {
         public void run()
         {
            final WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(currentModel);
            if (editor == null)
            {
               return;
            }
            final ModelServer modelServer = editor.getModelServer();
            if (modelServer != null && modelServer.isModelShared())
            {
               final List<ModelHistoryEntry>[] history = new List[1];
               IRunnableWithProgress commitOperation = new IRunnableWithProgress()
               {
                  public void run(IProgressMonitor monitor) throws InvocationTargetException,
                        InterruptedException
                  {
                     try
                     {
                        history[0] = modelServer.getModelHistory(currentModel, monitor);
                     }
                     catch (RMSException e)
                     {
                        throw new InvocationTargetException(e);
                     }
                  }
               };
               try
               {
                  new ProgressMonitorDialog(editor.getSite().getShell()).run(true, true, commitOperation);
                  replaceData(history[0]);
               }
               catch (InvocationTargetException e)
               {
                  Throwable t = e.getCause();
                  ModelServerUtils.showMessageBox(t.getMessage());
                  // TODO: update status
               }
               catch (InterruptedException e)
               {
                  // TODO handle cancellation
                  e.printStackTrace();
               }
            }            
         }
      };
      refreshAction.setToolTipText(""); //$NON-NLS-1$
      tbm.add(refreshAction);
      
      getViewSite().getActionBars().updateActionBars();

      TabFolder tabFolder = new TabFolder(parent,SWT.NONE);
      tabFolder.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

      TabItem processDefinitionStatisticsTabItem = new TabItem(tabFolder,SWT.NONE);
      // processDefinitionStatisticsTabItem.setImage(StatisticsImages.PROCESS_DEFINITION);
      processDefinitionStatisticsTabItem.setText(Diagram_Messages.TXT_COLLABORATIVE_MODELING_FEED); 
      vcsFeedTable = new VcsFeedTable(tabFolder);
      // this.vcsFeedTable.getTable().addMouseListener(new OpenPropertyPageListener());
      processDefinitionStatisticsTabItem.setControl(this.vcsFeedTable.getTable());
      
      replaceData(Collections.EMPTY_LIST);
      updateAction(false);
  }

   public void setFocus()
   {
      this.parent.setFocus();
   }

   public void updateAction(boolean forceUpdate)
   {
      ModelType model = modelFinder.getModel();
      if(!forceUpdate)
      {
         if(currentModel != null && model != null && currentModel.equals(model))
         {
            return;
         }
      }
      currentModel = model;

      boolean isShared = false;
      if (model != null)         
      {
         WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
         if (editor != null)
         {
            isShared = editor.getModelServer().isModelShared();
         }
      }
      
      if (model == null || !isShared)
      {
         replaceData(Collections.EMPTY_LIST);
         refreshAction.setEnabled(false);         
      }
      else
      {
         replaceData(Collections.EMPTY_LIST);            
         refreshAction.setEnabled(true);                  
      }
   }

   public void partActivated(IWorkbenchPart part)
   {
      updateAction(false);
   }   
   
   public void partBroughtToTop(IWorkbenchPart part)
   {
   }

   public void partClosed(IWorkbenchPart part)
   {
      updateAction(false);
   }

   public void partDeactivated(IWorkbenchPart part)
   {
      updateAction(false);
   }

   public void partOpened(IWorkbenchPart part)
   {
   }
}