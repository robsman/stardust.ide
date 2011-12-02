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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.StringUtils;

public class ProcessDefinitionImplementationPropertyPage
      extends AbstractModelElementPropertyPage
{
   private boolean isImplementing = false;

   private ProcessDefinitionType process = null;

   private Button implementsButton;

   private Table implementsTable;

   private TableViewer viewer;

   private List<ProcessDefinitionType> processes;

   protected void performDefaults()
   {
      super.performDefaults();
   }

   protected void performApply()
   {
      super.performApply();
      if (!isImplementing)
      {
         process.setExternalRef(null);
      }
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      process = (ProcessDefinitionType) element;
      initializeImplementsPanel();
   }

   private void initializeImplementsPanel()
   {
      IdRef externalReference = process.getExternalRef();
      implementsButton.setSelection(externalReference != null);
      implementsTable.setEnabled(externalReference != null);
      isImplementing = (externalReference != null);
      if (externalReference != null)
      {
         ProcessDefinitionType selectedProcess = findProcess(externalReference);
         if (selectedProcess != null)
         {
            viewer.getTable().select(processes.indexOf(selectedProcess));
            ;
         }
      }
   }

   private ProcessDefinitionType findProcess(IdRef externalReference)
   {
      for (Iterator<ProcessDefinitionType> i = processes.iterator(); i.hasNext();)
      {
         ProcessDefinitionType proc = i.next();
         ModelType refModel = (ModelType) proc.eContainer();
         String pid = externalReference.getRef();
         String mid = externalReference.getPackageRef().getId();
         if (refModel.getId().equalsIgnoreCase(mid) && proc.getId().equalsIgnoreCase(pid))
         {
            return proc;
         }
      }
      return null;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      implementsButton = FormBuilder.createCheckBox(composite, Diagram_Messages.BOX_PROCESS_IMPLEMENTS,
            2);
      implementsButton.addSelectionListener(new SelectionListener()
      {

         public void widgetDefaultSelected(SelectionEvent e)
         {

         }

         public void widgetSelected(SelectionEvent e)
         {
            isImplementing = !isImplementing;
            implementsTable.setEnabled(isImplementing);
            process.setExternalRef(null);
         }

      });
      implementsTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      implementsTable.setHeaderVisible(true);
      implementsTable.setLayoutData(FormBuilder
            .createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(implementsTable);
      viewer = new TableViewer(implementsTable);
      TableUtil.createColumns(implementsTable, new String[] {Diagram_Messages.COL_MD, Diagram_Messages.COL_PROCESS});
      TableUtil.setInitialColumnSizes(implementsTable, new int[] {35, 35});
      processes = collectProcessDefinitions(this.getEditor().getWorkflowModel());
      viewer.setContentProvider(new ArrayContentProvider());
      viewer.setLabelProvider(new ProcessDefinitionImplemetationLabelProvider());
      viewer.setInput(processes);
      implementsTable.setEnabled(isImplementing);

      implementsTable.addSelectionListener(new SelectionListener()
      {

         public void widgetDefaultSelected(SelectionEvent e)
         {
         // TODO Auto-generated method stub

         }

         public void widgetSelected(SelectionEvent e)
         {
            if (e.item != null)
            {
               TableItem tableItem = (TableItem) e.item;
               if (tableItem.getData() != null)
               {
                  ProcessDefinitionType processDefinition = (ProcessDefinitionType) tableItem
                        .getData();
                  ModelType referencedModel = (ModelType) processDefinition.eContainer();
                  ExternalPackage packageRef = getEditor().getWorkflowModel()
                        .getExternalPackages()
                        .getExternalPackage(referencedModel.getId());
                  IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
                  idRef.setRef(processDefinition.getId());
                  idRef.setPackageRef(packageRef);
                  process.setExternalRef(idRef);
               }
            }

         }

      });

      return composite;
   }

   private java.util.List<ProcessDefinitionType> collectProcessDefinitions(ModelType model)
   {
      java.util.List<ProcessDefinitionType> processesList = CollectionUtils.newList();
      ExternalPackages packages = model.getExternalPackages();
      if (packages != null)
      {
         for (ExternalPackage pkg : packages.getExternalPackage())
         {
            String uri = ExtendedAttributeUtil.getAttributeValue(pkg,
                  IConnectionManager.URI_ATTRIBUTE_NAME);
            if (!StringUtils.isEmpty(uri))
            {
               IConnectionManager manager = model.getConnectionManager();
               if (manager != null)
               {
                  EObject externalModel = manager.find(uri);
                  if (externalModel instanceof IObjectReference)
                  {
                     externalModel = ((IObjectReference) externalModel).getEObject();
                  }
                  if (externalModel instanceof ModelType)
                  {
                     java.util.List<ProcessDefinitionType> externalDeclarations = ((ModelType) externalModel)
                           .getProcessDefinition();
                     if (externalDeclarations != null)
                     {
                        processesList.addAll(externalDeclarations);
                     }
                  }
               }
            }
         }
      }
      return processesList;
   }

}