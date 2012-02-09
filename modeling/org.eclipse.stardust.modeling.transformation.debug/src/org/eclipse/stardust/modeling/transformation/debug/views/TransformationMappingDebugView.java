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
package org.eclipse.stardust.modeling.transformation.debug.views;


import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.internal.ui.InstructionPointerManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IInstructionPointerPresentation;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationApplicationControlsManager;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.MultipleAccessPathBrowserContentProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class TransformationMappingDebugView extends ViewPart
{
   public static final String VIEW_ID = "org.eclipse.stardust.modeling.transformation.debug.views.TransformationMappingDebugView"; //$NON-NLS-1$

   private final MessageTransformationApplicationControlsManager controlsManager;
   private Composite parent;
   private Composite baseComposite;

   private TableViewer viewer;
   private Action action1;
   private Action action2;
   private Action doubleClickAction;
   
   private IModelElement modelElement;

   /*
    * The content provider class is responsible for
    * providing objects to the view. It can wrap
    * existing objects in adapters or simply return
    * objects as-is. These objects may be sensitive
    * to the current input of the view, or ignore
    * it and always show the same content 
    * (like Task List, for example).
    */

   class ViewContentProvider implements IStructuredContentProvider
   {
      public void inputChanged(Viewer v, Object oldInput, Object newInput)
      {
      }

      public void dispose()
      {
      }

      public Object[] getElements(Object parent)
      {
         return new String[] { "One", "Two", "Three" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
   }

   class ViewLabelProvider extends LabelProvider implements ITableLabelProvider
   {
      public String getColumnText(Object obj, int index)
      {
         return getText(obj);
      }

      public Image getColumnImage(Object obj, int index)
      {
         return getImage(obj);
      }

      public Image getImage(Object obj)
      {
         return PlatformUI.getWorkbench().getSharedImages().getImage(
               ISharedImages.IMG_OBJ_ELEMENT);
      }
   }

   class NameSorter extends ViewerSorter
   {
   }

   /**
    * The constructor.
    */
   public TransformationMappingDebugView()
   {
      controlsManager = new MessageTransformationApplicationControlsManager();
      registerLaunchListener();
   }
   
   public void initWithModelElemet(IModelElement modelElement)
   {
      if(this.modelElement == modelElement)
      {
         return;
      }
      
      this.modelElement = modelElement;
      if (null != baseComposite)
      {
         baseComposite.dispose();
         baseComposite = null;
      }
      
      baseComposite = FormBuilder.createComposite(parent, 1);
      
      if (null == modelElement)
      {
         Text text = FormBuilder.createText(baseComposite);
         text.setText("Works only in message transformation debug context."); //$NON-NLS-1$
      }
      else
      {
         controlsManager.create(baseComposite, modelElement, false);
         
         controlsManager.getController().intializeModel(
               ModelUtils.findContainingModel(modelElement), null, modelElement);
         
         controlsManager.refreshModel();
         controlsManager.getController().initializeMappings(modelElement);
         controlsManager.getSourceMessageTreeViewer().refresh(true);
         controlsManager.getTargetMessageTreeViewer().refresh(true);
         controlsManager.refreshDocument();
      }
      
      parent.layout();
   }
   
   public void highlightLine(IStackFrame stackFrame)
   {
      JSCompilationUnitEditor expressionsEditor = controlsManager.getExpressionsEditor();
      IStackFrame delegatingStackFrame = new LineCorrectionStackFrame(stackFrame,
            expressionsEditor.getLineOffset());
      try
      {
         int lineNumber = delegatingStackFrame.getLineNumber();
         lineNumber--; // Document line numbers are 0-based. Debug line numbers are 1-based.

         IRegion region = getLineInformation(expressionsEditor, lineNumber);
         if (region != null)
         {
            expressionsEditor.selectAndReveal(region.getOffset(), 0);

            IInstructionPointerPresentation fPresentation = (IInstructionPointerPresentation) DebugUITools
                  .newDebugModelPresentation();

            Annotation annotation = fPresentation.getInstructionPointerAnnotation(
                  expressionsEditor, delegatingStackFrame);
            InstructionPointerManager.getDefault().addAnnotation(expressionsEditor,
                  delegatingStackFrame, annotation);

         }
      }
      catch (DebugException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   private static IRegion getLineInformation(ITextEditor editor, int lineNumber)
   {
      IDocumentProvider provider = editor.getDocumentProvider();
      IEditorInput input = editor.getEditorInput();
      try
      {
         provider.connect(input);
      }
      catch (CoreException e)
      {
         return null;
      }
      try
      {
         IDocument document = provider.getDocument(input);
         if (document != null)
            return document.getLineInformation(lineNumber);
      }
      catch (BadLocationException e)
      {
      }
      finally
      {
         provider.disconnect(input);
      }
      return null;
   }
   
   public void highlightOutMessageNode(String fieldPath)
   {
      // This counter should be incremented on each iter.next() call which succeeds.
      int usedFieldPathSegmentCounter = 0;
      Iterator iter = StringUtils.split(fieldPath, "/"); //$NON-NLS-1$
      // if no path at all -> return
      if ( !iter.hasNext())
      {
         // TODO: log this situation
         return;
      }
      
      TreeViewer target = controlsManager.getTargetMessageTreeViewer();
      MultipleAccessPathBrowserContentProvider contentProvider = (MultipleAccessPathBrowserContentProvider) target
            .getContentProvider();

      TreeItem[] items = target.getTree().getItems();

      String rootItemName = (String) iter.next();
      ++usedFieldPathSegmentCounter;
      
      TreeItem rootItem = null;
      for (int idx = 0; idx < items.length; idx++)
      {
         rootItem = items[idx];
         if (rootItemName.equals(rootItem.getText()))
         {
            break;
         }
      }

      // if no root item with this name exists -> return
      if (null == rootItem)
      {
         // TODO: log this situation
         return;
      }

      List itemData = CollectionUtils.newArrayList();
      StructAccessPointType sap = (StructAccessPointType) rootItem.getData();
      itemData.add(sap);

      // search for all children selected by field path elements
      boolean hasChildren = contentProvider.hasChildren(sap);
      while (hasChildren && iter.hasNext())
      {
         hasChildren = false;
         String nextItemName = (String) iter.next();
         ++usedFieldPathSegmentCounter;

         Object[] children = contentProvider.getChildren(sap);
         for (int idx = 0; idx < children.length; idx++)
         {
            sap = (StructAccessPointType) children[idx];
            if (sap.getId().equals(nextItemName))
            {
               itemData.add(sap);
               hasChildren = contentProvider.hasChildren(sap);
               break;
            }
         }
      }

      if (usedFieldPathSegmentCounter != itemData.size())
      {
         // TODO: log this situation
         return;
      }
      
      // only set new selection if current selection is the same.
      TreeSelection selection = (TreeSelection) target.getSelection();
      TreePath[] selectedPath = selection.getPathsFor(itemData.get(itemData.size() - 1));
      if (selectedPath.length == 0)
      {
         target.setSelection(new TreeSelection(new TreePath(itemData.toArray())), true);
      }
   }
   
   /**
    * This is a callback that will allow us
    * to create the viewer and initialize it.
    */
   public void createPartControl(Composite parent)
   {
      this.parent = parent;
      initWithModelElemet(null);
      
      /*viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
      viewer.setContentProvider(new ViewContentProvider());
      viewer.setLabelProvider(new ViewLabelProvider());
      viewer.setSorter(new NameSorter());
      viewer.setInput(getViewSite());

      // Create the help context id for the viewer's control
      PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(),
            "org.eclipse.stardust.modeling.transformation.debug.viewer");
      makeActions();
      hookContextMenu();
      hookDoubleClickAction();
      contributeToActionBars();*/
   }

   /**
    * Passing the focus request to the viewer's control.
    */
   public void setFocus()
   {
      //viewer.getControl().setFocus();
   }

   private void hookContextMenu()
   {
      MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
      menuMgr.setRemoveAllWhenShown(true);
      menuMgr.addMenuListener(new IMenuListener()
      {
         public void menuAboutToShow(IMenuManager manager)
         {
            TransformationMappingDebugView.this.fillContextMenu(manager);
         }
      });
      Menu menu = menuMgr.createContextMenu(viewer.getControl());
      viewer.getControl().setMenu(menu);
      getSite().registerContextMenu(menuMgr, viewer);
   }

   private void contributeToActionBars()
   {
      IActionBars bars = getViewSite().getActionBars();
      fillLocalPullDown(bars.getMenuManager());
      fillLocalToolBar(bars.getToolBarManager());
   }

   private void fillLocalPullDown(IMenuManager manager)
   {
      manager.add(action1);
      manager.add(new Separator());
      manager.add(action2);
   }

   private void fillContextMenu(IMenuManager manager)
   {
      manager.add(action1);
      manager.add(action2);
      // Other plug-ins can contribute there actions here
      manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
   }

   private void fillLocalToolBar(IToolBarManager manager)
   {
      manager.add(action1);
      manager.add(action2);
   }

   private void makeActions()
   {
      action1 = new Action()
      {
         public void run()
         {
            showMessage("Action 1 executed"); //$NON-NLS-1$
         }
      };
      action1.setText("Action 1"); //$NON-NLS-1$
      action1.setToolTipText("Action 1 tooltip"); //$NON-NLS-1$
      action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
            .getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

      action2 = new Action()
      {
         public void run()
         {
            showMessage("Action 2 executed"); //$NON-NLS-1$
         }
      };
      action2.setText("Action 2"); //$NON-NLS-1$
      action2.setToolTipText("Action 2 tooltip"); //$NON-NLS-1$
      action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
            .getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
      doubleClickAction = new Action()
      {
         public void run()
         {
            ISelection selection = viewer.getSelection();
            Object obj = ((IStructuredSelection) selection).getFirstElement();
            showMessage("Double-click detected on " + obj.toString()); //$NON-NLS-1$
         }
      };
   }

   private void hookDoubleClickAction()
   {
      viewer.addDoubleClickListener(new IDoubleClickListener()
      {
         public void doubleClick(DoubleClickEvent event)
         {
            doubleClickAction.run();
         }
      });
   }

   private void showMessage(String message)
   {
      MessageDialog.openInformation(viewer.getControl().getShell(), "Sample View", //$NON-NLS-1$
            message);
   }
   
   private void registerLaunchListener()
   {
      ILaunchManager launchManager = org.eclipse.debug.core.DebugPlugin.getDefault().getLaunchManager();
      if (null != launchManager)
      {
         launchManager.addLaunchListener(new ILaunchListener()
         {
            public void launchAdded(ILaunch launch)
            {
            }

            public void launchChanged(ILaunch launch)
            {
            }

            public void launchRemoved(ILaunch launch)
            {
               initWithModelElemet(null);
            }
         });
      }
   }
   
   private static class LineCorrectionStackFrame implements IStackFrame
   {
      private final IStackFrame stackFrame;
      private final int lineOffset; 

      public LineCorrectionStackFrame(IStackFrame stackFrame, int lineOffset)
      {
         super();
         this.stackFrame = stackFrame;
         this.lineOffset = lineOffset;
      }

      public boolean canResume()
      {
         return stackFrame.canResume();
      }

      public boolean canStepInto()
      {
         return stackFrame.canStepInto();
      }

      public boolean canStepOver()
      {
         return stackFrame.canStepOver();
      }

      public boolean canStepReturn()
      {
         return stackFrame.canStepReturn();
      }

      public boolean canSuspend()
      {
         return stackFrame.canSuspend();
      }

      public boolean canTerminate()
      {
         return stackFrame.canTerminate();
      }

      public Object getAdapter(Class adapter)
      {
         return stackFrame.getAdapter(adapter);
      }

      public int getCharEnd() throws DebugException
      {
         return stackFrame.getCharEnd();
      }

      public int getCharStart() throws DebugException
      {
         return stackFrame.getCharStart();
      }

      public IDebugTarget getDebugTarget()
      {
         return stackFrame.getDebugTarget();
      }

      public ILaunch getLaunch()
      {
         return stackFrame.getLaunch();
      }

      public int getLineNumber() throws DebugException
      {
         return stackFrame.getLineNumber() + lineOffset;
      }

      public String getModelIdentifier()
      {
         return stackFrame.getModelIdentifier();
      }

      public String getName() throws DebugException
      {
         return stackFrame.getName();
      }

      public IRegisterGroup[] getRegisterGroups() throws DebugException
      {
         return stackFrame.getRegisterGroups();
      }

      public IThread getThread()
      {
         return stackFrame.getThread();
      }

      public IVariable[] getVariables() throws DebugException
      {
         return stackFrame.getVariables();
      }

      public boolean hasRegisterGroups() throws DebugException
      {
         return stackFrame.hasRegisterGroups();
      }

      public boolean hasVariables() throws DebugException
      {
         return stackFrame.hasVariables();
      }

      public boolean isStepping()
      {
         return stackFrame.isStepping();
      }

      public boolean isSuspended()
      {
         return stackFrame.isSuspended();
      }

      public boolean isTerminated()
      {
         return stackFrame.isTerminated();
      }

      public void resume() throws DebugException
      {
         stackFrame.resume();
      }

      public void stepInto() throws DebugException
      {
         stackFrame.stepInto();
      }

      public void stepOver() throws DebugException
      {
         stackFrame.stepOver();
      }

      public void stepReturn() throws DebugException
      {
         stackFrame.stepReturn();
      }

      public void suspend() throws DebugException
      {
         stackFrame.suspend();
      }

      public void terminate() throws DebugException
      {
         stackFrame.terminate();
      }
   }
}