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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.jface.preference.PreferenceManager;

import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IPropertyPage;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.IValidationEventListener;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPropertyPageContributor;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.ModelElementPropertyDialog;
import org.eclipse.stardust.modeling.core.editors.ui.validation.PageValidationManager;
import org.eclipse.stardust.modeling.core.spi.IModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.CompositeUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.repository.common.descriptors.EObjectDescriptor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.progress.UIJob;

public abstract class AbstractModelElementPropertyPage extends PropertyPage
      implements IAdaptable, IPropertyPage, IModelElementPropertyPage
{
   protected static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   public static final boolean preselect = true;

   private final WidgetBindingManager wBndMgr = new WidgetBindingManager(this);
   private Composite buttonBar;
   private Composite pageContent;

   protected boolean enablePage = true;

   private List<ModelElementsOutlineSynchronizer> elements = new ArrayList<ModelElementsOutlineSynchronizer>();

   public List<ModelElementsOutlineSynchronizer> getModelElementsOutlineElements()
   {
      return elements;
   }

   protected void updateApplyButton()
   {
      Button applyButton = getApplyButton();
      if(applyButton != null)
      {
         applyButton.setEnabled(enablePage && isValid());
      }
      Button defaultsButton = getDefaultsButton();
      if(defaultsButton != null)
      {
         defaultsButton.setEnabled(enablePage && isValid());
      }
   }

   public void addModelElementsOutlineSynchronizer(ModelElementsOutlineSynchronizer element)
   {
      elements.add(element);
   }

   public void setEnablePage(boolean enablePage)
   {
      this.enablePage = enablePage;
      CompositeUtils.enableContentComposite(pageContent, enablePage);
      enableContentOutline();
      enableContentButtons();
      Button applyButton = getApplyButton();
      if(applyButton != null)
      {
         applyButton.setEnabled(enablePage);
      }
      Button defaultsButton = getDefaultsButton();
      if(defaultsButton != null)
      {
         defaultsButton.setEnabled(enablePage);
      }
   }

   public WidgetBindingManager getWidgetBindingManager()
   {
      return wBndMgr;
   }

   public void dispose()
   {
      wBndMgr.dispose();

      super.dispose();
      if (null != getControl())
      {
         getControl().dispose();
      }
   }

   public boolean performOk()
   {
      CreationUtils.refreshTreeItem(getModelElement());
      apply();
      return true;
   }

   protected EObject getModelElement()
   {
      IModelElement modelElement = getModelElementFromSymbol(getModelElementNodeSymbol());
      if (modelElement == null)
      {
         EObject eObject = getModelElement(getElement());
         if (eObject != null && eObject instanceof IModelElement)
         {
            return (IModelElement) eObject;
         }
      }
      return modelElement;
   }

   protected EObject getModelElement(IAdaptable adaptable)
   {
      EditPart editPart = (EditPart) adaptable.getAdapter(EditPart.class);
      Object model = editPart.getModel();
      if (model instanceof IModelElementNodeSymbol) {
         IModelElementNodeSymbol nd = (IModelElementNodeSymbol)model;
         return nd.getModelElement();
      }
      if (model instanceof Proxy)
      {
         Proxy proxy = (Proxy) model;
         InvocationHandler ih = Proxy.getInvocationHandler(proxy);
         Object value = Reflect.getFieldValue(ih, "val$desc"); //$NON-NLS-1$
         if (value == null || !(value instanceof EObjectDescriptor))
         {
            return null;
         }
         EObjectDescriptor ed = (EObjectDescriptor)value;
         return ed.getEObject();
      }
      return null;
   }

   protected IModelElement getModelElementFromSymbol(IModelElementNodeSymbol symbol)
   {
      return (IModelElement) (symbol == null ?
            getElement().getAdapter(IModelElement.class) : symbol.getModelElement());
   }

   protected IModelElementNodeSymbol getModelElementNodeSymbol()
   {
      IModelElementNodeSymbol symbol = (IModelElementNodeSymbol)
            getElement().getAdapter(IModelElementNodeSymbol.class);
      return symbol;
   }

   protected void performDefaults()
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ((ModelElementPropertyDialog) container).performDefaults();
      }
      else
      {
         super.performDefaults();
      }
   }

   protected void performApply()
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ((ModelElementPropertyDialog) container).performApply();
      }
      else
      {
         super.performApply();
      }
   }

   public void apply()
   {
      IModelElementNodeSymbol symbol = getModelElementNodeSymbol();
      IModelElement modelElement = getModelElementFromSymbol(symbol);
      loadElementFromFields(symbol, modelElement);
   }

   public void createControl(Composite parent)
   {
      super.createControl(parent);
      new UIJob(Diagram_Messages.LB_PageUpdate)
      {
         public IStatus runInUIThread(IProgressMonitor monitor)
         {
            // (fh) just in case... since the initialization is asynchronous, maybe the
            // user is fast enough to close the dialog before this code is executed.
            if (!getControl().isDisposed())
            {
               try
               {
                  elementChanged();
               }
               catch (NullPointerException e)
               {
                  // do nothing
               }
            }
            return Status.OK_STATUS;
         }
      }.schedule();
   }

   protected Control createContents(Composite parent)
   {
      pageContent = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout(2, false);
      layout.marginWidth = 0;
      layout.marginHeight = 0;
      pageContent.setLayout(layout);

      Control pageBody = createBody(pageContent);
      if (pageBody != null)
      {
         pageBody.setLayoutData(new GridData(GridData.FILL_BOTH));
      }

      buttonBar = new Composite(pageContent, SWT.NONE);
      layout = new GridLayout();
      layout.marginHeight = 0;
      layout.marginWidth = 0;
      buttonBar.setLayout(layout);

      GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
      buttonBar.setLayoutData(gd);

      contributeVerticalButtons(buttonBar);

      if (buttonBar.getChildren().length < 1)
      {
          buttonBar.dispose();
      }
      else
      {
         buttonBar.setEnabled(enablePage);
      }

      pageContent.pack();
      return pageContent;
   }

   private void enableContentButtons()
   {
      if(buttonBar != null && !buttonBar.isDisposed())
      {
         buttonBar.setEnabled(enablePage);
         buttonBar.setCapture(enablePage);
      }
   }

   private void enableContentOutline()
   {
      for(ModelElementsOutlineSynchronizer element : elements)
      {
         List<IPreferenceNode> nodes = element.getNodes();
         for (IPreferenceNode node : nodes)
         {
            if(node instanceof CarnotPreferenceNode)
            {
               IWorkbenchPropertyPage page = (IWorkbenchPropertyPage) node.getPage();
               if(page != null)
               {
                  if(page instanceof AbstractModelElementPropertyPage)
                  {
                     ((AbstractModelElementPropertyPage) page).setEnablePage(enablePage);
                  }
                  else if(page instanceof PropertyPage)
                  {
                     CompositeUtils.enableComposite(pageContent, enablePage);
                  }
               }
            }
         }
      }
   }

   public void contributeButtons(Composite parent)
   {
   }

   public void contributeVerticalButtons(Composite parent)
   {
   }

   public void elementChanged()
   {
      IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) getElement().getAdapter(
            IModelElementNodeSymbol.class);
      IModelElement modelElement = (IModelElement) (symbol == null ? getElement()
            .getAdapter(IModelElement.class) : symbol.getModelElement());
      if (modelElement == null)
      {
         modelElement = (IModelElement) this.getModelElement();
      }
      if (null != modelElement || (symbol != null && symbol.getModelElement() == null))
      {
         loadFieldsFromElement(symbol, modelElement);
         enableContentOutline();
      }
      super.performDefaults();
   }

      protected void removePreferenceNodes(String parentNodeId)
   {
      removePreferenceNodes(parentNodeId, false);
   }

      public void removePreferenceNodes(String parentNodeId, boolean removeParent)
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;
         dialog.removePreferenceNodes(parentNodeId, removeParent);
      }
   }

   protected void removePreferenceNode(CarnotPreferenceNode parentNode,
         CarnotPreferenceNode node)
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;
         dialog.removePreferenceNode(parentNode, node);
      }
   }

   public CarnotPreferenceNode addNodeTo(String pageId, CarnotPreferenceNode node,
                                      EObjectLabelProvider labelProvider)
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;

         dialog.addNodeTo(pageId, node, labelProvider);
         return node;
      }
      return null;
   }

   protected WorkflowModelEditor getEditor()
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;
         return dialog.getEditor();
      }
      return null;
   }

   protected void registerValidation(IAdaptable adaptable)
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;

         dialog.registerValidation(adaptable);
      }
   }

   public void refreshTree()
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;
         dialog.refreshTree();
      }
   }

   protected void expandTree()
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;
         dialog.expandTree();
      }
   }

   protected void addNodesTo(String category)
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;
         CarnotPropertyPageContributor.instance().contributePropertyPages(
               dialog.getPreferenceManager(), getElement(), category);
      }
   }

   protected void addSpiNodes(ITypedElement element)
   {
      IMetaType type = element.getMetaType();
      if (null != type)
      {
         addSpiNodes(type.getExtensionPointId(), type.getId());
      }
   }

   protected void addSpiNodes(String extensionId, String type)
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;

         CarnotPropertyPageContributor.instance().contributeSpiPropertyPages(
               dialog, extensionId, type, getElement());
      }
   }

   protected void selectPage(String path)
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;
         dialog.selectPage(path);
      }
   }

   protected void selectPageForObject(Object selection)
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;
         dialog.selectPageForObject(selection);
      }
   }

   public PreferenceManager getPreferenceManager()
   {
      IPreferencePageContainer container = this.getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) container;
         return dialog.getPreferenceManager();
      }
      return null;
   }

   public Object getAdapter(Class adapter)
   {
      Object result = null;

      if (IValidationEventListener.class.equals(adapter))
      {
         result = getWidgetBindingManager().getValidationBindingManager();
      }
      else if (PageValidationManager.class.equals(adapter))
      {
         result = getWidgetBindingManager().getValidationBindingManager();
      }

      return result;
   }

   public IPreferencePage getPage(String pageId)
   {
      IPreferencePageContainer container = getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
    	 return ((ModelElementPropertyDialog) container).getPage(pageId);
      }
      return null;
   }

   public IPreferenceNode getNode(String pageId)
   {
      IPreferencePageContainer container = getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
    	 return ((ModelElementPropertyDialog) container).getNode(pageId);
      }
      return null;
   }

   public String composePageId(String parentId, String id)
   {
	  return ModelElementPropertyDialog.composePageId(parentId, id);
   }

   public void addDependentCommand(Command command)
   {
      IPreferencePageContainer container = getContainer();
      if (container instanceof ModelElementPropertyDialog)
      {
         ((ModelElementPropertyDialog) container).addDependentCommand(command);
      }
   }

   public void setDelegateContainer(AbstractModelElementPropertyPage page)
   {
   }
}