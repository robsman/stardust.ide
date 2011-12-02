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
package org.eclipse.stardust.modeling.core.spi.applicationTypes.jms;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.DefaultOutlineProvider;
import org.eclipse.stardust.modeling.core.properties.IButtonManager;
import org.eclipse.stardust.modeling.core.properties.ModelElementAdaptable;
import org.eclipse.stardust.modeling.core.properties.ModelElementsOutlineSynchronizer;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;

import ag.carnot.workflow.spi.providers.applications.jms.JMSDirection;

public class JmsPropertyPage extends AbstractModelElementPropertyPage
      implements IButtonManager
{
   private static final String INOUT = JMSDirection.INOUT.getId();
   private static final String OUT = JMSDirection.OUT.getId();
   private static final String IN = JMSDirection.IN.getId();
   static final String EMPTY_STRING = ""; //$NON-NLS-1$
   static final String RESPONSE = "Response"; //$NON-NLS-1$
   static final String REQUEST = "Request"; //$NON-NLS-1$
   private static final String BOOLEAN_TYPE = "boolean"; //$NON-NLS-1$
   private static final String REQUEST_NODE = "_cwm_request_"; //$NON-NLS-1$
   private static final String RESPONSE_NODE = "_cwm_response_"; //$NON-NLS-1$
   private static final String SPI_NODE = "_cwm_spi_application_"; //$NON-NLS-1$

   private static final String[] labelProperties = {"name"}; //$NON-NLS-1$

   private Button[] buttons;
   private Object selection;

   private TreeViewer viewer;
   private ModelElementsOutlineSynchronizer requestOutlineSynchronizer;
   private ModelElementsOutlineSynchronizer responseOutlineSynchronizer;

   private Button requestButton;
   private Button responseButton;
   private EObjectLabelProvider labelProvider;
   
   public void dispose()
   {
      requestOutlineSynchronizer.dispose();
      responseOutlineSynchronizer.dispose();
      super.dispose();
   }

   private Object getSelectedItem()
   {
      IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
      Object selection = sel.getFirstElement();
      return selection;
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ApplicationType application = getApplication();
      String type = AttributeUtil.getAttributeValue(application, CarnotConstants.TYPE_ATT);
      DirectionType direction = getDirection(type);
      requestButton.setSelection(direction != null && AccessPointUtil.isOut(direction));
      responseButton.setSelection(direction != null && AccessPointUtil.isIn(direction));
      updateCreateRequestResponseNodes(application);

      viewer.setInput(application);
      requestOutlineSynchronizer.init(application);
      responseOutlineSynchronizer.init(application);
   }

   private void updateCreateRequestResponseNodes(ApplicationType application)
   {
      if (requestButton.getSelection())
      {
         
         addJmsType(REQUEST_NODE, REQUEST,
              RequestPropertyPage.class.getName(), application);
      }
      else
      {
         removePreferenceNodes(composePageId(SPI_NODE, REQUEST_NODE), true);
      }

      if (responseButton.getSelection())
      {
         addJmsType(RESPONSE_NODE, RESPONSE,
              ResponsePropertyPage.class.getName(), application);
      }
      else
      {
         removePreferenceNodes(composePageId(SPI_NODE, RESPONSE_NODE), true);
      }
   }

   private void addJmsType(String id, String name, String className, ApplicationType application)
   {
      // do not add the page if already present
      if (getNode(composePageId(SPI_NODE, id)) == null)
      {
         // todo: icon
         ConfigurationElement config = ConfigurationElement.createPageConfiguration(
               id, name, null, className); //IconFactory.getIconFor(ctxType)
         CarnotPreferenceNode node = new CarnotPreferenceNode(config,
            new ModelElementAdaptable(
               new Class[] {IButtonManager.class, IModelElement.class,
                            IModelElementNodeSymbol.class},
               new Object[] {this, application},
               getElement()),
            // always after the spi element, default label sorted
            CarnotPreferenceNode.SPI_ELEMENT + 1);
         addNodeTo(SPI_NODE, node, null);
      }
   }

   private DirectionType getDirection(String type)
   {
      if (JMSDirection.IN.getId().equals(type))
      {
         return DirectionType.IN_LITERAL;
      }
      else if (JMSDirection.OUT.getId().equals(type))
      {
         return DirectionType.OUT_LITERAL;
      }
      else if (JMSDirection.INOUT.getId().equals(type))
      {
         return DirectionType.INOUT_LITERAL;
      }
      return null;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol,
                                        IModelElement element)
   {
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 3);

      FormBuilder.createLabel(composite, Diagram_Messages.LB_SPI_Type);
      SelectionListener listener = new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setJmsType();
         }
      };
      requestButton = FormBuilder.createCheckBox(composite, REQUEST);
      requestButton.addSelectionListener(listener);

      responseButton = FormBuilder.createCheckBox(composite, RESPONSE);
      responseButton.addSelectionListener(listener);

      Tree tree = new Tree(composite, SWT.BORDER | SWT.FULL_SELECTION);
      tree.setHeaderVisible(true);
      tree.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData(3));
      FormBuilder.applyDefaultTextControlWidth(tree);
//      tree.setLinesVisible(true);
      tree.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateButtons(getSelectedItem(), buttons);
         }
      });
      tree.addMouseListener(new MouseAdapter()
      {
         public void mouseDoubleClick(MouseEvent e)
         {
            Object selection = getSelectedItem();
            if (selection instanceof AccessPointType)
            {
               selectPageForObject(selection);
            }
         }
      });

      viewer = new TreeViewer(tree);
      TableUtil.createColumns(tree, new String[] {Diagram_Messages.COL_NAME_Name});
      TableUtil.setInitialColumnSizes(tree, new int[] {99});
      labelProvider = new EObjectLabelProvider(getEditor())
      {
         public Image getImage(Object element)
         {
            // todo: introduce default image into EObjectLabelProvider
            Image image = element instanceof EObject ? super.getImage(element) : null;
            return image == null ? DiagramPlugin.getImage("icons/full/obj16/data.gif") //$NON-NLS-1$
                  : image;
         }

         public String getText(String name, Object element)
         {
            if (!(element instanceof EObject))
            {
               return getText(element);
            }
            return super.getText(name, element);
         }
      };
      TableUtil.setLabelProvider(viewer, labelProvider, labelProperties);
      viewer.setContentProvider(new JmsAccessPointsTreeContentProvider());
      
      requestOutlineSynchronizer = new ModelElementsOutlineSynchronizer(
            new DefaultOutlineProvider(this,
               CarnotWorkflowModelPackage.eINSTANCE.getIAccessPointOwner_AccessPoint(),
               CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(),
               CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name(),
               composePageId(SPI_NODE, REQUEST_NODE),
               RequestAccessPointPropertyPage.class.getName(),
               new IFilter()
               {
                  public boolean select(Object toTest)
                  {
                     return AccessPointUtil.isDirectionCompatible(
                           (AccessPointType) toTest, true);
                  }
               })
            {
               protected List retrievePagesFor(ModelElementAdaptable adaptable)
               {
                  // Workaround (rsauer): injecting param property pages although not listed as
                  // extension

                  IModelElement accessPoint = (IModelElement) adaptable.getAdapter(IModelElement.class);
                  if (accessPoint instanceof AccessPointType)
                  {
                     CarnotPreferenceNode node = new CarnotPreferenceNode(
                           createPageConfiguration(accessPoint), adaptable,
                           SpiConstants.PROPERTY_PAGE_CLASS, 0);

                     return Collections.singletonList(node);
                  }
                  else
                  {
                     return super.retrievePagesFor(adaptable);
                  }
               }
            });
      addModelElementsOutlineSynchronizer(requestOutlineSynchronizer);
      
      responseOutlineSynchronizer = new ModelElementsOutlineSynchronizer(
            new DefaultOutlineProvider(this,
               CarnotWorkflowModelPackage.eINSTANCE.getIAccessPointOwner_AccessPoint(),
               CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(),
               CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name(),
               composePageId(SPI_NODE, RESPONSE_NODE),
               ResponseAccessPointPropertyPage.class.getName(),
               new IFilter()
               {
                  public boolean select(Object toTest)
                  {
                     return AccessPointUtil.isDirectionCompatible(
                           (AccessPointType) toTest, false);
                  }
               })
            {
               protected List retrievePagesFor(ModelElementAdaptable adaptable)
               {
                  // HACK rsauer: injecting param property pages although not listed as
                  // extension

                  IModelElement accessPoint = (IModelElement) adaptable.getAdapter(IModelElement.class);
                  if (accessPoint instanceof AccessPointType)
                  {
                     CarnotPreferenceNode node = new CarnotPreferenceNode(
                           createPageConfiguration(accessPoint), adaptable,
                           SpiConstants.PROPERTY_PAGE_CLASS, 0);

                     return Collections.singletonList(node);
                  }
                  else
                  {
                     return super.retrievePagesFor(adaptable);
                  }
               }
            });
      addModelElementsOutlineSynchronizer(responseOutlineSynchronizer);

      return composite;
   }

   private void setJmsType()
   {
      boolean request = requestButton.getSelection();
      boolean response = responseButton.getSelection();
      ApplicationType application = getApplication();
      String type = INOUT;
      if ( !request)
      {
         AccessPointUtil.removeAccessPoints(application.getAccessPoint(), true);
         type = response ? IN : EMPTY_STRING;
      }
      if ( !response)
      {
         AccessPointUtil.removeAccessPoints(application.getAccessPoint(), false);
         type = request ? OUT : EMPTY_STRING;
      }
      AttributeUtil.setAttribute(application, CarnotConstants.TYPE_ATT,
              JMSDirection.class.getName(), type);
      updateCreateRequestResponseNodes(application);
      refreshTree();
   }

   private ApplicationType getApplication()
   {
      Object element = getElement();
      if (element instanceof EditPart)
      {
         element = ((EditPart) element).getModel();
      }
      if (element instanceof IModelElementNodeSymbol)
      {
         element = ((IModelElementNodeSymbol) element).getModelElement();
      }
      return element instanceof ApplicationType ? (ApplicationType) element : null;
   }

   public void contributeVerticalButtons(Composite parent)
   {
      buttons = createButtons(parent);
   }

   public void updateButtons(Object selection, Button[] buttons)
   {
      this.selection = selection;

      for (int i = 0; i < buttons.length; i++)
      {
         if (buttons[i].isDisposed())
         {
            return;
         }
      }
      buttons[ADD_BUTTON].setEnabled(selection != null);
      buttons[DELETE_BUTTON].setEnabled(selection instanceof AccessPointType);
   }

   public Button[] createButtons(Composite parent)
   {
      final Button[] buttons = new Button[DELETE_BUTTON + 1];

      buttons[ADD_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Add, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            performAdd(buttons);
         }
      });

      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Delete, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            performDelete(buttons);
         }
      });

      return buttons;
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         updateButtons(getSelectedItem(), buttons);
      }
      super.setVisible(visible);
   }

   public Object getSelection()
   {
      return selection == null ? getSelectedItem() : selection;
   }

   private void performDelete(Button[] buttons)
   {
      AccessPointType ap = (AccessPointType) getSelection();
      if (ap != null)
      {
         ApplicationType application = getApplication();
         application.getAccessPoint().remove(ap);

         updateButtons(null, buttons);
         selectPage(SPI_NODE);
      }
   }

   private void performAdd(Button[] buttons)
   {
      ApplicationType application = getApplication();
      String context = null;
      Object object = getSelection();
      if (object instanceof AccessPointType)
      {
         DirectionType direction = ((AccessPointType) object).getDirection();
         context = AccessPointUtil.isIn(direction) ? REQUEST : RESPONSE;
      }
      else
      {
         context = (String) object;
      }
      boolean isIn = REQUEST.equals(context);
      String baseId = isIn ? IN : OUT;
      String baseName = isIn ? Diagram_Messages.BASENAME_InAccessPoint
            : Diagram_Messages.BASENAME_OutAccessPoint;
      java.util.List points = application.getAccessPoint();
      IdFactory factory = new IdFactory(baseId, baseName);
      factory.computeNames(points);
      AccessPointType ap = AccessPointUtil.createIntrinsicAccessPoint(
         factory.getId(), factory.getName(), null,
         isIn ? DirectionType.IN_LITERAL : DirectionType.OUT_LITERAL, !isIn, null,
         ModelUtils.getDataType(application, CarnotConstants.SERIALIZABLE_DATA_ID));
      
      AttributeUtil.setAttribute(ap, CarnotConstants.BROWSABLE_ATT, BOOLEAN_TYPE,
            Boolean.FALSE.toString());

      ap.setElementOid(ModelUtils.getElementOid(ap,
            ModelUtils.findContainingModel(application)));
      
      application.getAccessPoint().add(ap);
      if (preselect)
      {
         selectPageForObject(ap);
      }
   }
}