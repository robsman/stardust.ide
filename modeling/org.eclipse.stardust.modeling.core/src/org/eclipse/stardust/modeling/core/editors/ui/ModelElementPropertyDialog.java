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
package org.eclipse.stardust.modeling.core.editors.ui;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContext;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.model.xpdl.util.IConnection;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.Extensible;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.BindingManager;
import org.eclipse.stardust.modeling.core.editors.IValidationEventListener;
import org.eclipse.stardust.modeling.core.editors.ValidationIssueManager;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.dialog.ApplyUpdatesCommand;
import org.eclipse.stardust.modeling.core.editors.ui.validation.PageValidationManager;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.DataPathPropertyPage;
import org.eclipse.stardust.modeling.core.properties.VariablesConfigurationPage;
import org.eclipse.stardust.modeling.core.ui.PreferenceNodeBinding;
import org.eclipse.stardust.modeling.core.utils.CompositeUtils;
import org.eclipse.stardust.modeling.core.utils.VerifyingChangeRecorder;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.descriptors.EObjectDescriptor;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ModelElementPropertyDialog extends PreferenceDialog
{
   public static final char NODE_PATH_SEPARATOR = '.';

   private BindingManager bindings = new BindingManager();

   private Set<CarnotPreferenceNode> validationNodes = new HashSet<CarnotPreferenceNode>();

   private final ValidationIssueManager issueManager = new ValidationIssueManager();

   private final ChangeRecorder changeRecorder;

   private final IAdaptable element;

   private WorkflowModelEditor editor;
   private ArrayList<Command> dependentCommands = new ArrayList<Command>();

   public ModelElementPropertyDialog(WorkflowModelEditor editor, Shell shell,
         PreferenceManager pageManager, IAdaptable element, ChangeRecorder recorder)
   {
      super(shell, pageManager);
      this.editor = editor;
      this.element = element;
      this.changeRecorder = recorder == null
         ? new VerifyingChangeRecorder(editor, issueManager) : recorder;
      startRecording();
   }

   /*
    * (non-Javadoc)
    *
    * @see org.eclipse.jface.window.Window#createBody(org.eclipse.swt.widgets.Composite)
    */
   protected Control createContents(Composite parent)
   {
      Control control = super.createContents(parent);
      getTreeViewer().setSorter(new ViewerSorter()
      {
         public int compare(Viewer viewer, Object e1, Object e2)
         {
            if (e1 instanceof CarnotPreferenceNode)
            {
               // if both objects are of type CarnotPreferenceNode, we compare by category
               if (e2 instanceof CarnotPreferenceNode)
               {
                  String cat1 = ((CarnotPreferenceNode) e1).category;
                  String cat2 = ((CarnotPreferenceNode) e2).category;
                  String id1 = ((CarnotPreferenceNode) e1).getId();
                  String id2 = ((CarnotPreferenceNode) e2).getId();

                  // if empty we compare by getSortOrder()
                  if (!StringUtils.isEmpty(cat2) && !StringUtils.isEmpty(cat1))
                  {
                     if(id1.equals("_cwm_general_")) //$NON-NLS-1$
                     {
                        return -1;
                     }
                     if(id2.equals("_cwm_general_")) //$NON-NLS-1$
                     {
                        return 1;
                     }

                     if(cat1.equals(cat2))
                     {
                        return id1.compareTo(id2);
                     }

                     for (int i = 0; i < CarnotPropertyPageContributor.CATEGORIES.length; i++)
                     {
                        if (CarnotPropertyPageContributor.CATEGORIES[i].equals(cat1))
                        {
                           return -1;
                        }
                        if (CarnotPropertyPageContributor.CATEGORIES[i].equals(cat2))
                        {
                           return 1;
                        }
                     }
                     return cat1.compareTo(cat2);
                  }

                  int c1 = ((CarnotPreferenceNode) e1).getSortOrder();
                  int c2 = ((CarnotPreferenceNode) e2).getSortOrder();
                  if (c1 >= 0)
                  {
                     if (c2 >= 0)
                     {
                        if (c1 != c2)
                        {
                           // use explicit priority
                           return c1 - c2;
                        }
                        // fall back to LABEL_ORDER since both priorities are the same
                     }
                     else
                     {
                        // explicit priority always comes before
                        return -1;
                     }
                  }
                  else if (c2 >= 0)
                  {
                     // explicit priority always comes before
                     return 1;
                  }
                  else if (c1 == -2 && c2 == -2)
                  {
                     // keep insertion order
                     return -1;
                  }
               }
               else
               {
                  // CarnotPreferenceNodes always comes before
                  return -1;
               }
            }
            else if (e2 instanceof CarnotPreferenceNode)
            {
               // CarnotPreferenceNodes always comes before
               return 1;
            }
            // fall back to LABEL_ORDER
            return super.compare(viewer, e1, e2);
         }
      });
      notifyPages(false);
      return control;
   }

   public IAdaptable getElement()
   {
      return element;
   }

   protected void okPressed()
   {
      super.okPressed();
      if (!(getReturnCode() == FAILED))
      {
         if (changeRecorder.isRecording())
         {
            executeCommands();
            if (!(getCurrentPage() instanceof VariablesConfigurationPage))
            {
               VariableContext context = VariableContextHelper.getInstance().getContext(editor.getWorkflowModel());
               if(context != null)
               {
                  context.refreshVariables(editor.getWorkflowModel());
               }
            }
         }
      }
   }

   private void executeCommands()
   {
      ChangeDescription recording = changeRecorder.endRecording();
      ApplyUpdatesCommand command = new ApplyUpdatesCommand(recording);
      editor.getEditDomain().getCommandStack().execute(command);
      for (int i = 0; i < dependentCommands.size(); i++)
      {
         Command cmd = (Command) dependentCommands.get(i);
         editor.getEditDomain().getCommandStack().execute(cmd);
      }
      dependentCommands.clear();
   }

   /*
    * (non-Javadoc)
    *
    * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
    */
   protected void cancelPressed()
   {
      super.cancelPressed();
      undo();
   }

   public void performApply()
   {
      if (changeRecorder.isRecording())
      {
         apply();
         executeCommands();
         startRecording();
      }
      notifyPages(false);
      if (!(getCurrentPage() instanceof VariablesConfigurationPage))
      {
         VariableContext context = VariableContextHelper.getInstance().getContext(editor.getWorkflowModel());
         if(context != null)
         {
            context.refreshVariables(editor.getWorkflowModel());
         }
      }
   }

   private void apply()
   {
      PreferenceManager manager = getPreferenceManager();
      @SuppressWarnings("unchecked")
      List<IPreferenceNode> elements = manager.getElements(PreferenceManager.PRE_ORDER);
      for (int i = 0; i < elements.size(); i++)
      {
         IPreferenceNode node = elements.get(i);
         IPreferencePage page = node.getPage();
         if (page instanceof IPropertyPage)
         {
            ((IPropertyPage) page).apply();
         }
      }
   }

   public void performDefaults()
   {
      undo();
      startRecording();
      notifyPages(true);
   }

   private void undo()
   {
      if (changeRecorder.isRecording())
      {
         ChangeDescription changes = changeRecorder.endRecording();
         changes.apply();
      }
   }

   private void notifyPages(boolean force)
   {
      HashSet<CarnotPreferenceNode> visited = new HashSet<CarnotPreferenceNode>();
      PreferenceManager manager = getPreferenceManager();
      @SuppressWarnings("unchecked")
      List<IPreferenceNode> elements = manager.getElements(PreferenceManager.PRE_ORDER);
      IPreferenceNode[] nodes = elements.toArray(new IPreferenceNode[elements.size()]);
      notifyPages(nodes, visited, force);
   }

   private void notifyPages(IPreferenceNode[] nodes, HashSet<CarnotPreferenceNode> visited, boolean force)
   {
      for (int i = 0; i < nodes.length; i++)
      {
         CarnotPreferenceNode node = (CarnotPreferenceNode) nodes[i];
         if (!visited.contains(node))
         {
            IPreferencePage page = getCreatePage(node);
            if (page instanceof IPropertyPage && (force || !(page instanceof AbstractModelElementPropertyPage)))
            {
               ((IPropertyPage) page).elementChanged();
            }
            visited.add(node);
            notifyPages(node.getSubNodes(), visited, force);
         }
      }
   }

   private IPreferencePage getCreatePage(CarnotPreferenceNode node)
   {
      if (node == null)
      {
         return null;
      }
      // Create the page if necessary
      if (node.getPage() == null)
      {
         createPage(node);
      }
      if (node.getPage() == null)
      {
         return null;
      }
      return initializePageControl(node);
   }

   private void enablePageControl(IPreferencePage page)
   {
      if(page instanceof PropertyPage)
      {
         boolean isExternalReference = isExternalReference(getElement());

         if(isExternalReference)
         {
            boolean enablePage = false;

            if(page instanceof AbstractModelElementPropertyPage)
            {
               if(page instanceof DataPathPropertyPage)
               {
                  if(((DataPathPropertyPage) page).isEditable())
                  {
                     ((AbstractModelElementPropertyPage) page).setEnablePage(enablePage);
                  }
               }
               else
               {
                  ((AbstractModelElementPropertyPage) page).setEnablePage(enablePage);
               }
            }
            else if(page instanceof PropertyPage)
            {
               Control control = page.getControl();
               if(control != null)
               {
                  control.setEnabled(true);
                  if(control instanceof Composite)
                  {
                     CompositeUtils.enableComposite((Composite) control, enablePage);
                  }
               }
            }

            Button okButton = getButton(IDialogConstants.OK_ID);
            if(okButton != null)
            {
               okButton.setEnabled(enablePage);
            }
         }
      }
   }

   private boolean isExternalReference(IAdaptable adaptable)
   {
      EObject modelElement = getModelElement(adaptable);
      if (modelElement != null)
      {
         if (modelElement.eIsProxy())
         {
            return true;
         }
         if (modelElement instanceof ActivityType)
         {
            return false;
         }
         if (modelElement instanceof DataType)
         {
            DataType dataType = (DataType) modelElement;
            if (dataType.getType().getId().equals(PredefinedConstants.STRUCTURED_DATA))
            {
               if (dataType.getExternalReference() == null)
               {
                  if (AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,
                        IConnectionManager.URI_ATTRIBUTE_NAME) != null)
                  {
                     return true;
                  }
               }
               else
               {
                  return false;
               }
            }
            if (dataType.getType().getId().equals(PredefinedConstants.PRIMITIVE_DATA))
            {
               if (dataType.getExternalReference() == null)
               {
                  if (AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,
                        IConnectionManager.URI_ATTRIBUTE_NAME) != null)
                  {
                     return true;
                  }
               }
               else
               {
                  return false;
               }
            }
         }
         String uri = null;
         if (modelElement instanceof IExtensibleElement)
         {
            uri = AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,
                     IConnectionManager.URI_ATTRIBUTE_NAME);
               }
         else if (modelElement instanceof Extensible)
               {
            uri = ExtendedAttributeUtil.getAttributeValue((Extensible) modelElement,
                  IConnectionManager.URI_ATTRIBUTE_NAME);
                  }
         if (uri != null)
         {
               ModelType model = ModelUtils.findContainingModel(modelElement);
               if (model == null)
               {
                  return false;
               }
            Connection connection = (Connection) model.getConnectionManager().findConnection(uri);
               if (connection != null)
               {
                  String importString = connection.getAttribute("importByReference"); //$NON-NLS-1$
                  if (importString != null && importString.equalsIgnoreCase("false")) //$NON-NLS-1$
                  {
                     return false;
                  }
               }
               return true;
            }
         }
      return false;
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
      if (model instanceof IConnection)
      {
         return (EObject) model;
      }
      if (model instanceof ExternalPackage)
      {
         return null;
      }
      if (model instanceof EObject)
      {
         return (EObject) model;
      }

      return null;
   }

   private IPreferencePage initializePageControl(CarnotPreferenceNode node)
   {
      final IPreferencePage page = getPage(node);
      page.setContainer(this);

      registerValidation(node, page);
      // init
      enablePageControl(page);

      // Ensure that the page control has been created
      // (this allows lazy page control creation)
      if (page.getControl() == null)
      {
         final boolean[] failed = {false};
         SafeRunnable.run(new ISafeRunnable()
         {
            public void handleException(Throwable e)
            {
               e.printStackTrace();
               failed[0] = true;
            }

            public void run()
            {
               createPageControl(page, getPageContainer());
            }
         });
         if (failed[0])
         {
            return null;
         }
         // the page is responsible for ensuring the created control is
         // accessable
         // via getWidget.
         Assert.isNotNull(page.getControl());
      }

      enablePageControl(page);

      return page;
   }

   private void registerValidation(final CarnotPreferenceNode node,
         final IPreferencePage page)
   {
      if (page instanceof IAdaptable && !validationNodes.contains(node))
      {
         // register node first, so initial validation state will be propagated to node
         PageValidationManager vldMgr = (PageValidationManager) ((IAdaptable) page)
               .getAdapter(PageValidationManager.class);
         if (null != vldMgr)
         {
            // TODO register node as listener
            vldMgr.addPageValidationEventListener(new IPageValidationEventListener()
            {
               public void pageStatusUpdated(IPreferencePage page,
                     IQuickValidationStatus status)
               {
                  node.updatePageStatus(status);

                  if (!getTreeViewer().getControl().isDisposed())
                  {
                     getTreeViewer().refresh(node);
                  }
               }
            });
         }
         IValidationEventListener vel = (IValidationEventListener) ((IAdaptable) page)
               .getAdapter(IValidationEventListener.class);
         if (null != vel)
         {
            issueManager.addValidationEventListener(vel);
            issueManager.pushCurrentStatus(vel);
         }
         validationNodes.add(node);
      }
      if (changeRecorder instanceof VerifyingChangeRecorder)
      {
         ((VerifyingChangeRecorder) changeRecorder).performElementValidation(true);
      }
   }

   public void registerValidation(IAdaptable adaptable)
   {
      IValidationEventListener vel = (IValidationEventListener) adaptable
            .getAdapter(IValidationEventListener.class);
      if (null != vel)
      {
         issueManager.addValidationEventListener(vel);
         issueManager.pushCurrentStatus(vel);
         if (changeRecorder instanceof VerifyingChangeRecorder)
         {
            ((VerifyingChangeRecorder) changeRecorder).performElementValidation(true);
         }
      }
   }

   private void startRecording()
   {
      if (!changeRecorder.isRecording())
      {
         if (null != getElement())
         {
            IModelElement modelElement = (IModelElement) getElement().getAdapter(
                  IModelElement.class);
            if (null != modelElement)
            {
               ModelType model = ModelUtils.findContainingModel(modelElement);
               if (changeRecorder instanceof VerifyingChangeRecorder)
               {
                  ((VerifyingChangeRecorder) changeRecorder).beginRecording(model, modelElement);
                  return;
               }
            }
            EObject eobj = (EObject) getElement().getAdapter(EObject.class);
            if (eobj != null)
            {
               // get the root
               while (eobj.eContainer() != null)
               {
                  eobj = eobj.eContainer();
               }
               changeRecorder.beginRecording(Collections.singleton(eobj));
            }
         }
      }
   }

   public void selectPage(IPreferenceNode node)
   {
      getTreeViewer().setSelection(new StructuredSelection(node));
   }

   public void addNodeTo(String path, CarnotPreferenceNode node,
         EObjectLabelProvider labelProvider)
   {
      PreferenceManager manager = getPreferenceManager();
      if (path == null)
      {
         manager.addToRoot(node);
      }
      else
      {
         manager.addTo(path, node);
      }
      if (labelProvider != null)
      {
         IModelElement model = (IModelElement) node.getAdaptable().getAdapter(
               IModelElement.class);
         bindings.bind(model, new PreferenceNodeBinding(getTreeViewer(), model, node,
               labelProvider));
      }
      getCreatePage(node);
   }

   public void refreshTree()
   {
      if ( !getTreeViewer().getTree().isDisposed())
      {
         getTreeViewer().refresh();
      }
   }

   public void selectPage(String path)
   {
      PreferenceManager manager = getPreferenceManager();
      selectPage(manager.find(path));
   }

   public void selectPageForObject(Object selection)
   {
      if (selection == null)
      {
         return;
      }
      PreferenceManager manager = getPreferenceManager();
      @SuppressWarnings("unchecked")
      List<IPreferenceNode> list = manager.getElements(PreferenceManager.PRE_ORDER);
      for (int i = 0; i < list.size(); i++)
      {
         IPreferenceNode node = list.get(i);
         if (node instanceof CarnotPreferenceNode)
         {
            CarnotPreferenceNode cpn = (CarnotPreferenceNode) node;
            if (selection.equals(cpn.getAdaptable().getAdapter(IModelElement.class)))
            {
               selectPage(node);;
               break;
            }
            if (cpn.getAdaptable().getAdapter(IModelElement.class) instanceof Proxy) {
            	Proxy proxy = (Proxy) cpn.getAdaptable().getAdapter(IModelElement.class);
            	if (proxy.equals(selection)) {
                    selectPage(node);
                    break;
            	}
            }
         }
      }
   }

   public void expandTree()
   {
      getTreeViewer().expandAll();
   }

   public void removePreferenceNodes(String parentNodeId, boolean removeParent)
   {
      PreferenceManager manager = getPreferenceManager();
      IPreferenceNode node = manager.find(parentNodeId);
      if (node != null)
      {
         IPreferenceNode[] subNodes = node.getSubNodes();
         for (int i = 0; i < subNodes.length; i++)
         {
            removeBindings(subNodes[i]);
            node.remove(subNodes[i]);
            disposeNode(subNodes[i]);
         }
         if (removeParent)
         {
            bindings.unbind(node);
            manager.remove(parentNodeId);
            disposeNode(node);
         }
      }
   }

   public void removePreferenceNode(IPreferenceNode parentNode, IPreferenceNode node)
   {
      bindings.unbind(node);
      parentNode.remove(node);
      disposeNode(node);
   }

   private void disposeNode(IPreferenceNode node)
   {
      if (CompareHelper.areEqual(getCurrentPage(), node.getPage()))
      {
         // (fh) page flipping does not work if current page is null, so just put there
         // anything
         // (fh) important: don't delete all the nodes!!!
         IPreferenceNode firstNode = (IPreferenceNode) getPreferenceManager()
               .getElements(PreferenceManager.PRE_ORDER).get(0);
         setCurrentPage(firstNode.getPage());
      }
      node.disposeResources();
   }

   private void removeBindings(IPreferenceNode node)
   {
      IPreferenceNode[] subNodes = node.getSubNodes();
      for (int i = 0; i < subNodes.length; i++)
      {
         removeBindings(subNodes[i]);
         validationNodes.remove(node);
         bindings.unbind(node);
      }
   }

   public WorkflowModelEditor getEditor()
   {
      return editor;
   }

   public IPreferencePage getPage(String pageId)
   {
      IPreferenceNode node = getNode(pageId);
      return node == null ? null : node.getPage();
   }

   public IPreferenceNode getNode(String pageId)
   {
      return getPreferenceManager().find(pageId);
   }

   public static String composePageId(String parentId, String id)
   {
      return parentId + NODE_PATH_SEPARATOR + (id == null ? "" : convertId(id));
   }

   public static String convertId(String id)
   {
      return id.replace('.', '~');
   }

   public void addDependentCommand(Command command)
   {
      dependentCommands.add(command);
   }

   public void setReadOnly()
   {
      Button okButton = getButton(IDialogConstants.OK_ID);
      okButton.setVisible(false);
      Button cancelButton = getButton(IDialogConstants.CANCEL_ID);
      cancelButton.setText(okButton.getText());
   }

   protected void createButtonsForButtonBar(Composite parent)
   {
      // create OK and Cancel buttons by default
      super.createButtonsForButtonBar(parent);
   }

   public void updateButtons()
   {
      super.updateButtons();
      if (isExternalReference(getElement())) {
         Button okButton = getButton(IDialogConstants.OK_ID);
         if(okButton != null)
         {
            okButton.setEnabled(false);
         }
      }
   }

   protected void buttonPressed(int buttonId)
   {
      super.buttonPressed(buttonId);
   }
}