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
package org.eclipse.stardust.modeling.audittrail.ui;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.modeling.audittrail.AuditTrailDbManager;
import org.eclipse.stardust.modeling.audittrail.AuditTrailManagementException;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * @author rsauer
 * @version $Revision$
 */
public class AuditTrailPreferencePage extends PreferencePage
      implements IWorkbenchPreferencePage
{
   private ListViewer vwDbs;
   
   public AuditTrailPreferencePage()
   {
      // TODO Auto-generated constructor stub
   }

   public AuditTrailPreferencePage(String title)
   {
      super(title);
      // TODO Auto-generated constructor stub
   }

   public AuditTrailPreferencePage(String title, ImageDescriptor image)
   {
      super(title, image);
      // TODO Auto-generated constructor stub
   }

   protected Control createContents(Composite parent)
   {
      Composite page = FormBuilder.createComposite(parent, 3);

      FormBuilder.createLabel(page, Audittrail_UI_Messages.LBL_DERBY_AUDITTRAIL_DBS, 3);

      this.vwDbs = new ListViewer(FormBuilder.createList(page, 2));
      GridData gd = (GridData) vwDbs.getList().getLayoutData();
      gd.verticalSpan = 5;

      FormBuilder.createButton(page, Audittrail_UI_Messages.BUT_NEW, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            createNewDb();
         }
      });
      // FormBuilder.createButton(page, "Settings", null);
      final Button btnReset = FormBuilder.createButton(page, Audittrail_UI_Messages.BUT_RESET, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            resetDb(getSelectedDb(vwDbs));
         }
      });
      final Button btnDelete = FormBuilder.createButton(page, Audittrail_UI_Messages.BUT_DELETE, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            deleteDb(getSelectedDb(vwDbs));
         }
      });
      vwDbs.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            // TODO Auto-generated method stub
            IFolder dbFolder = getSelectedDb(vwDbs);
            btnReset.setEnabled(null != dbFolder);
            btnDelete.setEnabled(null != dbFolder);
         }
      });

      vwDbs.setLabelProvider(new DbLocationLabelProvider());
      vwDbs.setContentProvider(new ArrayContentProvider());
      vwDbs.setInput(AuditTrailDbManager.listDbs());
      
      vwDbs.setSelection(StructuredSelection.EMPTY);
      
      return page;
   }

   public void init(IWorkbench workbench)
   {
      // TODO Auto-generated method stub

   }

   public void createNewDb()
   {
      // TODO

      IFolder newDb = AuditTrailUtils.createNewDb(vwDbs.getList().getShell());

      if (null != newDb)
      {
         vwDbs.setInput(AuditTrailDbManager.listDbs());
         vwDbs.setSelection(new StructuredSelection(newDb), true);
      }
   }

   private void resetDb(IFolder dbFolder)
   {
      // TODO
      try
      {
         AuditTrailDbManager.resetDb(dbFolder.getFullPath().lastSegment());
      }
      catch (AuditTrailManagementException atme)
      {
         MessageDialog.openError(getShell(), Audittrail_UI_Messages.MSG_ERR_FAILED_RESETTING_AUDITTRAIL_DB,
               atme.getMessage());
      }

      vwDbs.setInput(AuditTrailDbManager.listDbs());
      vwDbs.setSelection(new StructuredSelection(dbFolder), true);
   }

   private void deleteDb(IFolder dbFolder)
   {
      // TODO
      try
      {
         AuditTrailDbManager.deleteDb(dbFolder.getFullPath().lastSegment());
      }
      catch (AuditTrailManagementException atme)
      {
         MessageDialog.openError(getShell(), Audittrail_UI_Messages.MSG_ERR_FAILED_RESETTING_AUDITTRAIL_DB,
               atme.getMessage());
      }

      vwDbs.setInput(AuditTrailDbManager.listDbs());
      vwDbs.setSelection(new StructuredSelection(dbFolder), true);
   }

   private IFolder getSelectedDb(ListViewer vwDbs)
   {
      IFolder dbFolder = null;

      ISelection sel = vwDbs.getSelection();
      if (sel instanceof IStructuredSelection)
      {
         Object element = ((IStructuredSelection) sel).getFirstElement();
         dbFolder = (IFolder) element;
      }
      
      return dbFolder;
   }
}
