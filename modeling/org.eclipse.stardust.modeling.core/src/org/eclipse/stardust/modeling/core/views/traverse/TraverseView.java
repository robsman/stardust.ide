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
package org.eclipse.stardust.modeling.core.views.traverse;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.dnd.ModelElementTransfer;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;


public class TraverseView extends ViewPart
{
   private IIdentifiableModelElement currentElement;

   private IIdentifiableModelElement element;

   private TableViewer traversalPathViewer;

   private TableViewer linkedObjectViewer;

   private java.util.List linkedObjectList = new ArrayList();

   private java.util.List traversalPathList = new ArrayList();

   private ISelectionListener selectionListener;

   public TraverseView()
   {
      super();
   }

   public void createPartControl(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 3);
      createLinkedObjectGroup(composite);
      createTraversalPathGroup(composite);

      Button popButton = FormBuilder.createButton(composite,
            Diagram_Messages.LB_TraverseView_Pop, new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  if (!traversalPathList.isEmpty())
                  {
                     currentElement = (IIdentifiableModelElement) traversalPathList
                           .get(traversalPathList.size() - 1);
                     resetTraversalPath();
                     traversalPathList.remove(traversalPathList.size() - 1);
                     IIdentifiableModelElement previousElement = (IIdentifiableModelElement) (traversalPathList
                           .isEmpty() ? element : traversalPathList.get(traversalPathList
                           .size() - 1));
                     traversalPathViewer.add(traversalPathList.toArray());
                     resetLinkedObjects();
                     addLinkedObjects(getLinkedElements(previousElement));
                  }
               }
            });
      ((GridData) popButton.getLayoutData()).verticalAlignment = SWT.NONE;

      getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(
            getSelectionListener());
   }

   public void dispose()
   {
      if (null != selectionListener)
      {
         getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(
               selectionListener);
         
         this.selectionListener = null;
      }

      super.dispose();
   }
   
   private ISelectionListener getSelectionListener()
   {
      if (null == selectionListener)
      {
         this.selectionListener = new ISelectionListener()
         {
            public void selectionChanged(IWorkbenchPart part, ISelection selection)
            {
               reset();
               if (selection instanceof IStructuredSelection)
               {
                  Object obj = ((IStructuredSelection) selection).getFirstElement();
                  if (obj instanceof AbstractEObjectTreeEditPart)
                  {
                     Object model = ((AbstractEObjectTreeEditPart) obj).getModel();
                     if ((model instanceof ActivityType) || (model instanceof OrganizationType)
                           || (model instanceof RoleType))
                     {
                        element = (IIdentifiableModelElement) model;
                     }
                  }
                  else if (obj instanceof AbstractNodeSymbolEditPart)
                  {
                     INodeSymbol nodeSymbol = (INodeSymbol) ((AbstractNodeSymbolEditPart) obj).getModel();
                     if (nodeSymbol instanceof IModelElementNodeSymbol)
                     {
                        IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) nodeSymbol;
                        if ((symbol.getModelElement() instanceof OrganizationType)
                              || (symbol.getModelElement() instanceof RoleType)
                              || (symbol.getModelElement() instanceof ActivityType))
                        {
                           element = symbol.getModelElement();
                        }
                     }
                  }
                  if (element != null)
                  {
                     currentElement = element;
                     addLinkedObjects(getLinkedElements(currentElement));
                  }
               }
            }

            private void reset()
            {
               element = null;
               resetLinkedObjects();
               resetTraversalPath();
               if ( !traversalPathViewer.getControl().isDisposed())
               {
                  traversalPathList.clear();
               }
            }
         };
      }
      
      return selectionListener;
   }

   private void createTraversalPathGroup(Composite composite)
   {
      Composite comp = FormBuilder.createComposite(composite, 1);
      FormBuilder.createLabel(comp, Diagram_Messages.LB_TraverseView_Path);
      Table table = new Table(comp, SWT.BORDER);
      table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      traversalPathViewer = new TableViewer(table);
      traversalPathViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            return ((IIdentifiableModelElement) element).getName();
         }

         public Image getImage(Object element)
         {
            return DiagramPlugin.getImage(IconFactory.getDefault().getIconFor((EObject) element));
         }
      });
      traversalPathViewer.setContentProvider(new ArrayContentProvider());
      traversalPathViewer.addDragSupport(DND.DROP_COPY,
            new Transfer[] {ModelElementTransfer.getInstance()},
            new TraverseDragSourceListener(traversalPathViewer));
   }

   private void createLinkedObjectGroup(Composite composite)
   {
      Composite comp = FormBuilder.createComposite(composite, 1);
      FormBuilder.createLabel(comp, Diagram_Messages.LB_TraverseView_LinkedObjects);
      Table table = new Table(comp, SWT.BORDER);
      table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      linkedObjectViewer = new TableViewer(table);
      linkedObjectViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            element = element instanceof ParticipantType ? ((ParticipantType) element)
                  .getParticipant() : element;
            return ((IIdentifiableModelElement) element).getName();
         }

         public Image getImage(Object element)
         {
            return DiagramPlugin.getImage(IconFactory.getDefault().getIconFor((EObject) element));
         }
      });
      linkedObjectViewer.setContentProvider(new ArrayContentProvider());

      linkedObjectViewer.addDoubleClickListener(new IDoubleClickListener()
      {
         public void doubleClick(DoubleClickEvent event)
         {
            if (event.getSelection() instanceof IStructuredSelection)
            {
               resetTraversalPath();
               resetLinkedObjects();
               currentElement = (IIdentifiableModelElement) ((IStructuredSelection) event
                     .getSelection()).getFirstElement();
               addTraversalPath();
               addLinkedObjects(getLinkedElements(currentElement));
            }
         }
      });
      linkedObjectViewer.addDragSupport(DND.DROP_COPY,
            new Transfer[] {ModelElementTransfer.getInstance()},
            new TraverseDragSourceListener(linkedObjectViewer));
   }

   public void setFocus()
   {}

   private void addLinkedObjects(java.util.List list)
   {
      if ( !linkedObjectViewer.getControl().isDisposed())
      {
         linkedObjectList.addAll(list);
         linkedObjectViewer.add(linkedObjectList.toArray());
      }
   }

   private void addTraversalPath()
   {
      if ( !traversalPathViewer.getControl().isDisposed())
      {
         traversalPathList.add(currentElement);
         traversalPathViewer.add(traversalPathList.toArray());
      }
   }

   private java.util.List getLinkedElements(IModelElement modelElement)
   {
      java.util.List list = new ArrayList();

      if (modelElement instanceof ActivityType)
      {
         list = ActivityUtil.getOutActivities((ActivityType) modelElement);
         if (((ActivityType) modelElement).getApplication() != null)
         {
            list.add(((ActivityType) modelElement).getApplication());
         }
         if (((ActivityType) modelElement).getPerformer() != null)
         {
            list.add(((ActivityType) modelElement).getPerformer());
         }
         list.addAll(getDataTypes(((ActivityType) modelElement).getDataMapping()));
      }
      else if (modelElement instanceof OrganizationType)
      {
         list = getOrganizationLinkedObj(modelElement);
      }
      else
      {
         list = getRoleLinkedObj(modelElement);
      }
      return list;
   }

   private java.util.List getDataTypes(EList dataMappingList)
   {
      java.util.List list = new ArrayList();
      for (Iterator iter = dataMappingList.iterator(); iter.hasNext();)
      {
         DataMappingType dataMapping = (DataMappingType) iter.next();
         if (dataMapping.getData() != null)
         {
            list.add(dataMapping.getData());
         }
      }
      return list;
   }

   private java.util.List getOrganizationLinkedObj(IModelElement modelElement)
   {
      java.util.List list = new ArrayList();
      for (Iterator iter = ((OrganizationType) modelElement).getParticipant().iterator(); iter
            .hasNext();)
      {
         IModelParticipant element = ((ParticipantType) iter.next()).getParticipant();
         if ((element instanceof RoleType) || (element instanceof OrganizationType))
         {
            list.add(element);
         }
      }
      return list;
   }

   private java.util.List getRoleLinkedObj(IModelElement modelElement)
   {
      java.util.List list = new ArrayList();
      ModelType model = ModelUtils.findContainingModel(modelElement);
      for (Iterator iter = model.getOrganization().iterator(); iter.hasNext();)
      {
         OrganizationType organization = (OrganizationType) iter.next();
         for (Iterator iterator = organization.getParticipant().iterator(); iterator
               .hasNext();)
         {
            if (((ParticipantType) iterator.next()).getParticipant().equals(modelElement))
            {
               list.add(organization);
            }
         }
      }
      return list;
   }

   private void resetTraversalPath()
   {
      if ( !traversalPathViewer.getControl().isDisposed())
      {
         traversalPathViewer.remove(traversalPathList.toArray());
         traversalPathViewer.refresh();
      }
   }

   private void resetLinkedObjects()
   {
      currentElement = null;

      if ( !linkedObjectViewer.getControl().isDisposed())
      {
         linkedObjectViewer.remove(linkedObjectList.toArray());
         linkedObjectList.clear();
         linkedObjectViewer.refresh();
      }
   }

}
