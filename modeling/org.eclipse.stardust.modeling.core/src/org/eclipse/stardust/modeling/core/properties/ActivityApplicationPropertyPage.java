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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ReloadConnectionsAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegateCommand;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ActivityCommandFactory;
import org.eclipse.stardust.modeling.core.editors.ui.IdentifiableLabelProvider;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ActivityApplicationPropertyPage extends AbstractModelElementPropertyPage
{
   private LabeledViewer labeledWidget;
   private ApplicationType originalApplication;
   private Button groupingCheckbox;
   private List<ApplicationType> applications;
   private ReferencedModelSorter refSorter = new ReferencedModelSorter();
   private IdentifiableLabelProvider labelProvider;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ActivityType activity = (ActivityType) element;
      ModelType model = (ModelType) activity.eContainer().eContainer();
      TableViewer viewer = (TableViewer) labeledWidget.getViewer();
      viewer.getTable().removeAll();
      applications = this.collectApplications(model);
      labelProvider.setModel(model);
      viewer.add(applications.toArray()); 
      viewer.setSorter(refSorter);  
      WidgetBindingManager wBndMgr = getWidgetBindingManager();
      wBndMgr.bind(labeledWidget, element, PKG_CWM.getActivityType_Application());
      wBndMgr.getModelBindingManager().updateWidgets(element);    	  
      originalApplication = activity.getApplication();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // todo: data mappings???
      ActivityType activity = (ActivityType) element;
      ApplicationType newApplication = activity.getApplication();

      CompoundCommand command = new CompoundCommand();
      ActivityCommandFactory.addSetApplicationCommands(command, activity, newApplication,
         originalApplication, true);
      
      if (originalApplication == null && newApplication != null)
      {
         DelegateCommand reloadCommand = new DelegateCommand();
         if (setReloadConnectionsCommand(reloadCommand, newApplication, getEditor()))
         {
            command.add(reloadCommand);
         }
      }
      
      
      command.execute();
   }

   private boolean setReloadConnectionsCommand(DelegateCommand compound,
         final ApplicationType application, final WorkflowModelEditor editor)
   {
      final ReloadConnectionsAction reload = new ReloadConnectionsAction(editor)
      {
         private List<EditPart> editParts = null;

         protected List<EditPart> getSelectedObjects()
         {
            if (editParts == null)
            {
               editParts = CollectionUtils.newList();
               for (INodeSymbol symbol : application.getSymbols())
               {
                  editParts.add(editor.findEditPart(symbol));
               }
            }
            return editParts;
         }
      };
      CompoundCommand command = reload.createReloadCommand();
      if ((command != null) && (!command.isEmpty()))
      {
         compound.setDelegate(command);
         return true;
      }
      return false;
   }
   
   
   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      LabelWithStatus label = FormBuilder.createLabelWithRightAlignedStatus(composite,
         Diagram_Messages.LB_Applications);

      Table table = new Table(composite, SWT.BORDER);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      TableViewer tableViewer = new TableViewer(table);
      labelProvider = new  IdentifiableLabelProvider(getEditor());
      tableViewer.setLabelProvider(labelProvider);
      tableViewer.setSorter(refSorter);
      labeledWidget = new LabeledViewer(tableViewer, label);
      
      groupingCheckbox = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_GroupModelElements);
      groupingCheckbox.addSelectionListener(new SelectionListener(){

		public void widgetDefaultSelected(SelectionEvent e) {
			
		}

		public void widgetSelected(SelectionEvent e) {
			refSorter.setGrouped(groupingCheckbox.getSelection());
			labelProvider.setShowGroupInfo(groupingCheckbox.getSelection());						
			TableViewer viewer = (TableViewer)  labeledWidget.getViewer();						
			ISelection selection = viewer.getSelection();
			viewer.getTable().removeAll();
			viewer.add(applications.toArray());   	        
		    viewer.setSelection(selection);
		}
			    	  
      });

      return composite;
   }
   
   private List<ApplicationType> collectApplications(ModelType model)
   {
      List<ApplicationType> applicationsList = CollectionUtils.newList();
      List<ApplicationType> applicationTypes = model.getApplication();
      if (applicationTypes != null)
      {
         applicationsList.addAll(applicationTypes);
      }
      ExternalPackages packages = model.getExternalPackages();
      if (packages != null)
      {
         for (ExternalPackage pkg : packages.getExternalPackage())
         {
            String uri = ExtendedAttributeUtil.getAttributeValue(pkg, IConnectionManager.URI_ATTRIBUTE_NAME);
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
                     List<ApplicationType> externalDeclarations = ((ModelType) externalModel)
                           .getApplication();
                     if (externalDeclarations != null)
                     {
                        for (Iterator<ApplicationType> i = externalDeclarations
                              .iterator(); i.hasNext();)
                        {
                           ApplicationType application = i.next();
                           AttributeType visibility = AttributeUtil.getAttribute(
                                 (IExtensibleElement) application,
                                 PredefinedConstants.MODELELEMENT_VISIBILITY);
                           if (visibility == null
                                 || visibility.getValue().equalsIgnoreCase("Public")) //$NON-NLS-1$
                           {
                              applicationsList.add(application);
                           } 
                     }
                  }
               }
            }
         }
      }
      }
      return applicationsList;
   }
}
