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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModelListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.javascript.editor.EditorUtils;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor.RegionWithLineOffset;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.MessageTransformationModelingPlugin;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.ErrorFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.ExpressionFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.NoExpressionFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.SourceHighlightFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.TargetHighlightFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.TextFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.wst.jsdt.internal.ui.JavaPluginImages;

import ag.carnot.base.StringUtils;
import ag.carnot.reflect.Reflect;


public class MessageTransformationApplicationControlsManager
      implements IMessageTransformationApplicationView, KeyListener,
      IAnnotationModelListener

{
   private MTATreeViewer sourceMessageTreeViewer;

   private MTATreeViewer targetMessageTreeViewer;

   private Tree sourceMessageTree;

   private Tree targetMessageTree;

   private JSCompilationUnitEditor expressionsEditor;

   private GridData svcData;

   protected Composite statementViewerComposite;

   private IProject project;

   private IFile tempFileResource;

   private String sourceFieldText;

   private Composite sourceFilterComposite;

   private Composite sourceFilterButtonComposite;

   private Composite targetFilterComposite;

   private Composite targetFilterButtonComposite;

   protected boolean targetNoExpression;

   private Button targetExpressionFilter;

   protected boolean targetExpression;

   private Button targetNoExpressionFilter;

   protected boolean targetHighlight;

   private Button targetHighlightedFilter;

   protected boolean sourceHighlight;

   protected boolean targetError;

   private ExpressionFilter targetExpressionViewerFilter;

   private NoExpressionFilter targetNoExpressionViewerFilter;

   private TargetHighlightFilter targetHighlightViewerFilter;

   private TextFilter targetTextViewerFilter;

   private Text targetFilterText;

   protected String targetFilterTextText;

   private TextFilter sourceTextViewerFilter;

   private Text sourceFilterText;

   private Button sourceHighlightedFilter;

   private MessageTransformationController controller = new MessageTransformationController();

   private SourceHighlightFilter sourceHighlightViewerFilter;

   private ErrorFilter targetErrorViewerFilter;

   private Button btnToggleMappingStyle;

   private Label lblExpressionPrefix;

   private ModelType model;

   private boolean simpleMode;

   private boolean externalReference;

   private Group sourceComposite;

   private Group targetComposite;

   private Group expressionComposite;

   private GridLayout mainLayout;

   private SashForm sashForm;

   private Button btnAddExternalClass;

   public Control create(final Composite parent, final IModelElement modelElement)
   {
      return create(parent, modelElement, true);
   }

   public Control create(final Composite parent, final IModelElement modelElement,
         final boolean enableEditing)
   {
      return create(parent, modelElement, enableEditing && !externalReference, false);
   }

   public Control create(final Composite parent, final IModelElement modelElement,
         final boolean enableEditing, final boolean simpleMode)
   {
      try
      {
         parent.setSize(20, 20);

         this.simpleMode = simpleMode;
         controller.setSimpleMode(simpleMode);
         model = ModelUtils.findContainingModel(modelElement);

         project = ModelUtils.getProjectFromEObject(modelElement);

         FillLayout parentLayout = new FillLayout();
         parent.setLayout(parentLayout);
         // parent.setLayoutData(null);
         sashForm = new SashForm(parent, SWT.VERTICAL)
         {

            @Override
            public void setLayoutData(Object layoutData)
            {
               if (!(layoutData instanceof GridData))
               {
                  super.setLayoutData(layoutData);
               }
            }

         };
         sashForm.setLayoutData(null);

         // composite = new Composite(parent, SWT.NONE);
         mainLayout = new GridLayout();
         mainLayout.numColumns = 2;
         // composite.setLayout(mainLayout);
         GridData mainData = new GridData();
         mainData.grabExcessHorizontalSpace = true;
         mainData.grabExcessVerticalSpace = true;
         mainData.horizontalAlignment = SWT.FILL;
         mainData.verticalAlignment = SWT.FILL;
         // composite.setLayoutData(mainData);*/

         createInputOutputSection(modelElement, enableEditing);

         if (!simpleMode)
         {
            createEditorComposite(modelElement, enableEditing);
         }

         if (enableEditing)
         {
            Transfer[] types = new Transfer[] {TextTransfer.getInstance()};
            DropTarget target = new DropTarget(targetMessageTreeViewer.getControl(),
                  DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
            target.setTransfer(types);
            target.addDropListener(new DropTargetAdapter()
            {
               public void dragEnter(DropTargetEvent event)
               {
                  if (event.detail == DND.DROP_DEFAULT)
                  {
                     event.detail = (event.operations & DND.DROP_COPY) != 0
                           ? DND.DROP_COPY
                           : DND.DROP_NONE;
                  }

                  // Allow dropping text only
                  for (int i = 0, n = event.dataTypes.length; i < n; i++)
                  {
                     if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i]))
                     {
                        event.currentDataType = event.dataTypes[i];
                     }
                  }
               }

               public void dragOver(DropTargetEvent event)
               {
                  event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
               }

               public void drop(DropTargetEvent event)
               {
                  if (TextTransfer.getInstance().isSupportedType(event.currentDataType))
                  {
                     TreeItem item = (TreeItem) event.item;
                     if (item != null)
                     {
                        Object mapTarget = item.getData();
                        if (controller.hasMappingExpression(mapTarget))
                        {
                           MessageBox messageBox = new MessageBox(Display.getDefault()
                                 .getActiveShell(), SWT.ICON_WARNING | SWT.OK
                                 | SWT.CANCEL);
                           messageBox.setText(Modeling_Messages.TXT_WR_LEER);
                           messageBox
                                 .setMessage(Modeling_Messages.MSG_TARGET_FIELD_ALREADY_CONTAINS_EXPR+ "\n\n" + Modeling_Messages.MSG_WANT_OVERWRITE_SEL_MSG); //$NON-NLS-2$
                           if (messageBox.open() == SWT.OK)
                           {
                              performDropMapping((AccessPointType) mapTarget);
                              targetMessageTreeViewer.refreshVisibleItems();
                              refreshDocument();
                           }
                        }
                        else
                        {
                           performDropMapping((AccessPointType) mapTarget);
                           targetMessageTreeViewer.refreshVisibleItems();
                           refreshDocument();
                        }
                     }
                  }
               }
            });
         }

         sashForm.layout();
      }
      catch (Throwable t)
      {
         t.printStackTrace();
      }

      return sashForm;

   }

   private void performDropMapping(AccessPointType mapTarget)
   {
      AccessPointType st = controller.getSelectedSourceField();
      AccessPointType tt = mapTarget;
      MappingConfiguration config = null;
      IndexConfigurationDialog dialog = null;
      if (controller.isEqualOrSimilar(st, tt, true)
            && (controller.isIndexingRequired(st, tt)))
      {
         MultipleAccessPathBrowserContentProvider saveSourceAPB = controller
               .getSourceAPB();
         MultipleAccessPathBrowserContentProvider saveTargetAPB = controller
               .getTargetAPB();
         config = new MappingConfiguration(controller, st, tt);
         dialog = new IndexConfigurationDialog(Display.getCurrent().getActiveShell(),
               controller, config);
         dialog.open();
         config.setIndexMap(dialog.getIndexMap());
         controller.setSourceAPB(saveSourceAPB);
         controller.setTargetAPB(saveTargetAPB);
      }
      if (dialog == null || !dialog.isCanceled())
      {
         controller.performDropMapping(mapTarget, config);
      }
   }

   private void createInputOutputSection(final IModelElement modelElement,
         final boolean enableEditing)
   {
      Composite ioComposite = new Composite(sashForm, SWT.NONE)
      {

         @Override
         public void setEnabled(boolean enabled)
         {
            // TODO Auto-generated method stub
            super.setEnabled(true);
         }

      };
      GridLayout ioLayout = new GridLayout();
      ioLayout.numColumns = 2;
      GridData ioData = new GridData();
      ioData.grabExcessHorizontalSpace = true;
      ioData.grabExcessVerticalSpace = true;
      ioData.horizontalAlignment = SWT.FILL;
      ioComposite.setLayout(ioLayout);
      ioComposite.setLayoutData(ioData);

      sourceComposite = new Group(ioComposite, SWT.NONE)
      {
         protected void checkSubclass()
         {}

         public void setEnabled(boolean enabled)
         {
            super.setEnabled(true);
         }

      };

      GridLayout messageLayout = new GridLayout();
      mainLayout.numColumns = 2;
      sourceComposite.setLayout(mainLayout);
      GridData messageData = new GridData();
      messageData.grabExcessHorizontalSpace = true;
      messageData.grabExcessVerticalSpace = true;
      messageData.horizontalAlignment = SWT.FILL;
      messageData.verticalAlignment = SWT.FILL;
      sourceComposite.setLayoutData(messageData);
      sourceComposite.setText(MessageFormat.format(Modeling_Messages.TXT_SR, new Object[]{controller.getNameString()}));
      targetComposite = new Group(ioComposite, SWT.NONE)
      {
         protected void checkSubclass()
         {}

         public void setEnabled(boolean enabled)
         {
            super.setEnabled(true);
         }
      };
      targetComposite.setLayout(mainLayout);
      targetComposite.setLayoutData(messageData);
      targetComposite.setText(MessageFormat.format(Modeling_Messages.TXT_TARGET, new Object[]{controller.getNameString()}));
      GridData treeData = new GridData();
      treeData.grabExcessHorizontalSpace = true;
      treeData.grabExcessVerticalSpace = true;
      treeData.horizontalAlignment = SWT.FILL;
      treeData.verticalAlignment = SWT.FILL;
      treeData.horizontalSpan = 2;

      GridData addButtonData = new GridData();
      addButtonData.horizontalSpan = 2;
      Button addSourceTypeButton = new Button(sourceComposite, SWT.NONE);
      addSourceTypeButton.setLayoutData(addButtonData);
      Button addTargetTypeButton = new Button(targetComposite, SWT.NONE);
      addTargetTypeButton.setLayoutData(addButtonData);
      addSourceTypeButton.setText(Modeling_Messages.TXT_ADD_IP);
      addTargetTypeButton.setText(Modeling_Messages.TXT_ADD_OP);

      addSourceTypeButton.setEnabled(enableEditing);
      addTargetTypeButton.setEnabled(enableEditing);

      addSourceTypeButton.addListener(SWT.Selection, new Listener()
      {
         public void handleEvent(Event event)
         {
            if (controller.getAvailableMessageTypes().isEmpty())
            {
               MessageBox messageBox = new MessageBox(Display.getDefault()
                     .getActiveShell(), SWT.ICON_WARNING | SWT.CANCEL);
               messageBox.setText(Modeling_Messages.TXT_WR_LEER);
               messageBox
                     .setMessage(Modeling_Messages.MSG_FIRST_NEED_DEFINE_STR_DATA_MD);
               messageBox.open();
            }
            else
            {
               MessageAdditionDialog dialog = new MessageAdditionDialog(Display
                     .getCurrent().getActiveShell(), controller, controller
                     .getSourceMessageTypes(), "", DirectionType.IN_LITERAL); //$NON-NLS-1$
               if (dialog.open() == IDialogConstants.OK_ID)
               {
                  controller.addSourceMessageType(dialog.getMessageType(), dialog
                        .getMessageName());
                  refreshModel();
                  controller.initializeMappings(modelElement);

               }
            }
         }
      });

      addTargetTypeButton.addListener(SWT.Selection, new Listener()
      {

         public void handleEvent(Event event)
         {
            if (controller.getAvailableMessageTypes().isEmpty())
            {
               MessageBox messageBox = new MessageBox(Display.getDefault()
                     .getActiveShell(), SWT.ICON_WARNING | SWT.CANCEL);
               messageBox.setText(Modeling_Messages.TXT_WR_LEER);
               messageBox
                     .setMessage(Modeling_Messages.MSG_FIRST_NEED_DEFINE_STR_DATA_MD);
               messageBox.open();
            }
            else
            {
               MessageAdditionDialog dialog = new MessageAdditionDialog(Display
                     .getCurrent().getActiveShell(), controller, controller
                     .getTargetMessageTypes(), "Out", DirectionType.OUT_LITERAL); //$NON-NLS-1$
               if (dialog.open() == IDialogConstants.OK_ID)
               {
                  controller.addTargetMessageType(dialog.getMessageType(), dialog
                        .getMessageName());
                  refreshModel();
                  controller.initializeMappings(modelElement);
               }
            }
         }

      });

      createFilterComposites();

      sourceMessageTree = new Tree(sourceComposite, SWT.MULTI | SWT.FULL_SELECTION
            | SWT.BORDER)
      {
         public void setEnabled(boolean enabled)
         {
            super.setEnabled(true);
         }

         @Override
         protected void checkSubclass()
         {

         }
      };
      GridData treeGridData = new GridData();
      treeGridData.grabExcessVerticalSpace = true;
      treeGridData.verticalAlignment = SWT.FILL;
      sourceMessageTree.setLayoutData(treeGridData);

      sourceMessageTree.setHeaderVisible(true);
      sourceMessageTreeViewer = new MTATreeViewer(sourceMessageTree);

      String[] sourceColumns = {Modeling_Messages.COL_NAME, Modeling_Messages.COL_TYPE};
      String[] targetColumns = null;
		if (!simpleMode) {
			targetColumns = new String[] { Modeling_Messages.COL_NAME,
					Modeling_Messages.COL_TYPE, Modeling_Messages.COL_MAPPING,
					Modeling_Messages.COL_PROBLEMS }; 
		}
      else
      {
			targetColumns = new String[] { Modeling_Messages.COL_NAME,
					Modeling_Messages.COL_TYPE };
      }

      for (int i = 0; i < sourceColumns.length; i++)
      {
         TreeColumn column = new TreeColumn(sourceMessageTree, SWT.LEFT);
         column.setText(sourceColumns[i]);
         if (i == 0)
         {
            column.setWidth(180);
         }
         else
         {
            column.setWidth(100);
         }
      }
      sourceHighlightViewerFilter = new SourceHighlightFilter(controller);
      sourceTextViewerFilter = new TextFilter(controller);
      sourceMessageTreeViewer.addFilter(sourceTextViewerFilter);
      sourceMessageTreeViewer.addFilter(sourceHighlightViewerFilter);
      sourceMessageTreeViewer.setUseHashlookup(true);
      sourceMessageTreeViewer.getTree().setLayoutData(treeData);

      MenuManager menuMgr = new MenuManager();
      Tree tree = sourceMessageTreeViewer.getTree();
      Menu menu = menuMgr.createContextMenu(tree);
      menuMgr.addMenuListener(new IMenuListener()
      {
         public void menuAboutToShow(IMenuManager manager)
         {
            if (enableEditing && controller.isDeleteSourceMessageAvailable())
            {
               DeleteSourceMessageAction deleteMessageAction = new DeleteSourceMessageAction(
                     MessageTransformationApplicationControlsManager.this, controller);
               manager.add(deleteMessageAction);
            }
            if (controller.isSimpleMode())
            {
               RenameMessageAction renameMessageAction = new RenameMessageAction(
                     MessageTransformationApplicationControlsManager.this, controller, true);
               manager.add(renameMessageAction);
            }
         }
      });
      menuMgr.setRemoveAllWhenShown(true);
      tree.setMenu(menu);

      sourceMessageTreeViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            TreeSelection selection = (TreeSelection) event.getSelection();
            controller.sourceMessageFieldSelected(selection);
            if (!controller.isSimpleMode())
            {
               sourceFieldText = controller.getDraggedText();
               targetMessageTreeViewer.refreshVisibleItems();
            }
         }
      });

      sourceMessageTreeViewer.getTree().addKeyListener(new KeyListener()
      {
         public void keyPressed(KeyEvent e)
         {}

         public void keyReleased(KeyEvent e)
         {
            if (e.keyCode == SWT.DEL
                  && controller.isRoot(controller.getSelectedSourceField()))
            {
               DeleteSourceMessageAction deleteMessageAction = new DeleteSourceMessageAction(
                     MessageTransformationApplicationControlsManager.this, controller);
               deleteMessageAction.run();
            }
         }
      });

      targetMessageTree = new Tree(targetComposite, SWT.FULL_SELECTION | SWT.BORDER)
      {
         public void setEnabled(boolean enabled)
         {
            super.setEnabled(true);
         }

         @Override
         protected void checkSubclass()
         {

         }
      };
      targetMessageTree.setLayoutData(treeGridData);
      targetMessageTree.setHeaderVisible(true);
      targetMessageTreeViewer = new MTATreeViewer(targetMessageTree);
      for (int i = 0; i < targetColumns.length; i++)
      {
         TreeColumn column = new TreeColumn(targetMessageTree, SWT.LEFT);
         column.setText(targetColumns[i]);
         if (i == 0)
         {
            column.setWidth(180);
         }
         else
         {
            column.setWidth(100);
         }
      }
      targetMessageTreeViewer.setUseHashlookup(true);
      targetMessageTreeViewer.setColumnProperties(targetColumns);
      targetMessageTreeViewer.getTree().setLayoutData(treeData);
      targetExpressionViewerFilter = new ExpressionFilter(controller);
      targetNoExpressionViewerFilter = new NoExpressionFilter(controller);
      targetHighlightViewerFilter = new TargetHighlightFilter(controller);
      targetTextViewerFilter = new TextFilter(controller);
      targetErrorViewerFilter = new ErrorFilter(controller);
      targetMessageTreeViewer.addFilter(targetExpressionViewerFilter);
      targetMessageTreeViewer.addFilter(targetNoExpressionViewerFilter);
      targetMessageTreeViewer.addFilter(targetHighlightViewerFilter);
      targetMessageTreeViewer.addFilter(targetTextViewerFilter);
      targetMessageTreeViewer.addFilter(targetErrorViewerFilter);
      
      targetMessageTreeViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            controller.ignoreUpcomingAnnotationChanges();
            TreeSelection selection = (TreeSelection) event.getSelection();
            boolean refreshDocument = controller.targetMessageFieldSelected(selection);
               if (!controller.isSimpleMode())
               {
                  if (null != controller.getSelectedTargetField())
                  {
                     expressionsEditor.getAdaptedSourceViewer().setEditable(
                           !controller.isRoot(controller.getSelectedTargetField())
                                 || controller.isPrimitive(controller
                                       .getSelectedTargetField()));
                     String targetXPath = controller.getXPathFor(controller
                           .getSelectedTargetField());
                     if (null != targetXPath)
                     {
                        lblExpressionPrefix.setText(targetXPath.replaceAll("/", ".") + " ="); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                     }
                     else
                     {
                        lblExpressionPrefix.setText(""); //$NON-NLS-1$
                     }
                  }
                  else
                  {
                     expressionsEditor.getAdaptedSourceViewer().setEditable(false);
                  }

                  updateMappingHeaderSection();

                  if (refreshDocument)
                  {
                     refreshDocument();
                  }
                  sourceMessageTreeViewer.refreshVisibleItems();
               }
         }

      });
      
      targetMessageTreeViewer.getTree().addKeyListener(new KeyListener()
      {
         public void keyPressed(KeyEvent e)
         {}

         public void keyReleased(KeyEvent e)
         {
            if (e.keyCode == SWT.DEL
                  && controller.isRoot(controller.getSelectedTargetField()))
            {
               DeleteTargetMessageAction deleteMessageAction = new DeleteTargetMessageAction(
                     MessageTransformationApplicationControlsManager.this, controller);
               deleteMessageAction.run();
            }
         }
      });

      menuMgr = new MenuManager();
      tree = targetMessageTreeViewer.getTree();
      menu = menuMgr.createContextMenu(tree);
      menuMgr.addMenuListener(new IMenuListener()
      {
         public void menuAboutToShow(IMenuManager manager)
         {
            AccessPointType messageType = controller.getSelectedTargetField();
            if (controller.isToggleBreakpointAvailable()
                  && !controller.isDeleteTargetMessageAvailable())
            {
               ToggleMappingBreakpointAction action = new ToggleMappingBreakpointAction(
                     MessageTransformationApplicationControlsManager.this, controller);
               manager.add(action);
            }
            if (enableEditing)
            {
               if (controller.isDeleteTargetMessageAvailable())
               {
                  DeleteTargetMessageAction deleteMessageAction = new DeleteTargetMessageAction(
                        MessageTransformationApplicationControlsManager.this, controller);
                  manager.add(deleteMessageAction);
               }
               
               // else
               // {
               if (controller.isClearMappingExpressionAvailable())
               {
                  ClearMappingExpressionAction deleteMappingExpressionAction = new ClearMappingExpressionAction(
                        MessageTransformationApplicationControlsManager.this, controller);
                  manager.add(deleteMappingExpressionAction);
               }
               if (controller.isClearMappingStatementAvailable())
               {
                  ClearMappingStatementAction deleteMappingStatementAction = new ClearMappingStatementAction(
                        MessageTransformationApplicationControlsManager.this, controller);
                  manager.add(deleteMappingStatementAction);

               }
            }
            if (controller.isSimpleMode())
            {
               
               RenameMessageAction renameMessageAction = new RenameMessageAction(
                     MessageTransformationApplicationControlsManager.this, controller, false);
               manager.add(renameMessageAction);                                    
            }            
         }
      });
      menuMgr.setRemoveAllWhenShown(true);
      tree.setMenu(menu);
   }

   private void createEditorComposite(final IModelElement modelElement,
         final boolean enableEditing)
   {
      expressionComposite = new Group(sashForm, SWT.NONE);
      GridLayout expressionLayout = new GridLayout();
      expressionLayout.numColumns = 2;
      expressionComposite.setLayout(expressionLayout);
      GridData expressionData = new GridData();
      expressionData.grabExcessHorizontalSpace = true;
      expressionData.grabExcessVerticalSpace = true;
      expressionData.horizontalAlignment = SWT.FILL;
      expressionData.verticalAlignment = SWT.FILL;
      expressionData.horizontalSpan = 2;
      // expressionData.minimumHeight = 150;
      expressionComposite.setLayoutData(expressionData);
      expressionComposite.setText(Modeling_Messages.TXT_MAPPING_EXP);

      Composite buttonComposite = new Composite(expressionComposite, SWT.NONE);
      GridLayout bcLayout = new GridLayout();
      bcLayout.numColumns = 2;

      bcLayout.marginHeight = 0;
      bcLayout.marginBottom = 0;
      bcLayout.marginTop = 5;
      bcLayout.marginLeft = 5;

      bcLayout.verticalSpacing = 0;
      buttonComposite.setLayout(bcLayout);
      GridData bcData = new GridData();
      bcData.grabExcessHorizontalSpace = true;
      bcData.horizontalAlignment = SWT.FILL;
      bcData.horizontalSpan = 2;
      buttonComposite.setLayoutData(bcData);

      btnToggleMappingStyle = new Button(buttonComposite, SWT.CHECK);
      btnToggleMappingStyle.setText(Modeling_Messages.TXT_ADVANCED_MAPPING);

      btnAddExternalClass = new Button(buttonComposite, SWT.PUSH);
      btnAddExternalClass.setText(Modeling_Messages.TXT_EXTERNAL_CL);
      btnAddExternalClass.setEnabled(enableEditing);

      GridData gData = FormBuilder.createDefaultButtonGridData();
      gData.grabExcessHorizontalSpace = true;
      gData.grabExcessVerticalSpace = true;
      gData.horizontalAlignment = SWT.FILL;
      gData.verticalAlignment = SWT.FILL;
      gData = FormBuilder.createDefaultButtonGridData();
      btnAddExternalClass.setLayoutData(gData);
      gData.grabExcessVerticalSpace = true;
      gData.verticalAlignment = SWT.FILL;
      gData.minimumHeight = 22;
      btnToggleMappingStyle.setLayoutData(gData);
      this.lblExpressionPrefix = FormBuilder.createLabel(expressionComposite, "", 1); //$NON-NLS-1$

      btnAddExternalClass.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            ExternalClassesConfigurationDialog dialog = new ExternalClassesConfigurationDialog(
                  Display.getCurrent().getActiveShell(), controller, controller
                        .getExternalClassTypes(), "", DirectionType.IN_LITERAL); //$NON-NLS-1$
            if (dialog.open() == IDialogConstants.OK_ID)
            {
               refreshModel();
               refreshDocument();
               controller.refreshJavaScriptContext();
               controller.initializeMappings(modelElement);
            }
         }
      });

      btnToggleMappingStyle.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            if (controller.isBasicTargetFieldMapping())
            {
               controller.setBasicTargetFieldMapping(false);

               // TODO add prefix to existing expression
            }
            else
            {
               controller.setBasicTargetFieldMapping(true);

               // TODO remove prefix from existing expression, if existing
            }

            updateMappingHeaderSection();
         }
      });
      btnToggleMappingStyle.setEnabled(enableEditing);

      updateMappingHeaderSection();

      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      IEditorSite editorSite = editor.getEditorSite();

      EditorUtils.deleteFileStructure(project, model);
      try
      {
         EditorUtils.addJSSupport(project, model);
      }
      catch (CoreException e)
      {
         // e.printStackTrace();
      }

      expressionsEditor = new JSCompilationUnitEditor();
      MessagingJavaCodeScanner codeScanner = new MessagingJavaCodeScanner(
            expressionsEditor.getUsableColorManager(), expressionsEditor
                  .getUsablePreferenceStore(), controller);
      expressionsEditor = new JSCompilationUnitEditor(codeScanner);

      expressionsEditor.setTheSite(editorSite);
      if (null != project)
      {
         controller.initilizeValidator(project);
         tempFileResource = EditorUtils.createFileStructure(project, model,
               "expressions.js"); //$NON-NLS-1$
         expressionsEditor.setInput(new FileEditorInput(tempFileResource));
      }
      else
      {
         expressionsEditor.setInput(new IEditorInput()
         {

            public boolean exists()
            {
               return false;
            }

            public ImageDescriptor getImageDescriptor()
            {
               return null;
            }

            public String getName()
            {
               return null;
            }

            public IPersistableElement getPersistable()
            {
               return null;
            }

            public String getToolTipText()
            {
               return null;
            }

            public Object getAdapter(Class adapter)
            {
               return null;
            }
         });
      }

      final Composite sourceViewerComposite = new Composite(expressionComposite, SWT.NONE);
      svcData = new GridData();
      svcData.grabExcessHorizontalSpace = true;
      svcData.grabExcessVerticalSpace = true;
      svcData.horizontalSpan = 2;
      svcData.horizontalAlignment = SWT.FILL;
      svcData.verticalAlignment = SWT.FILL;
      sourceViewerComposite.setLayout(new FillLayout());
      sourceViewerComposite.setLayoutData(svcData);
      expressionsEditor.createPartControl(sourceViewerComposite);
      expressionsEditor.getAdaptedSourceViewer().getTextWidget().setText(""); //$NON-NLS-1$
      expressionsEditor.getAdaptedSourceViewer().getTextWidget().addKeyListener(this);
      expressionsEditor.getAdaptedSourceViewer().setEditable(enableEditing);

      expressionsEditor.getAdaptedSourceViewer().getAnnotationModel()
            .addAnnotationModelListener(this);

      expressionsEditor.getAdaptedSourceViewer().addTextListener(new ITextListener()
      {

         public void textChanged(TextEvent event)
         {
            if (controller.getDraggedText() != null && event.getText() != null
                  && event.getText().equalsIgnoreCase(controller.getDraggedText()))
            {
               controller.updateExpressions(expressionsEditor.getAdaptedSourceViewer()
                     .getTextWidget().getText());
               targetMessageTreeViewer.refreshVisibleItems();
            }
         }
      });

      if (enableEditing)
      {
         int operations = DND.DROP_MOVE | DND.DROP_COPY;
         DragSource sourceDragSource = new DragSource(sourceMessageTreeViewer
               .getControl(), operations);
         Transfer[] types = new Transfer[] {TextTransfer.getInstance()};
         sourceDragSource.setTransfer(types);
         sourceDragSource.addDragListener(new DragSourceAdapter()
         {
            public void dragSetData(DragSourceEvent event)
            {
               try
               {
                  event.data = controller.getDraggedText();
               }
               catch (Throwable t)
               {
                  t.printStackTrace();
               }
            }
         });

         DropTarget editorTarget = new DropTarget(this.expressionsEditor
               .getAdaptedSourceViewer().getTextWidget(), DND.DROP_MOVE | DND.DROP_COPY
               | DND.DROP_DEFAULT);
         editorTarget.setTransfer(types);
         editorTarget.addDropListener(new DropTargetAdapter()
         {
            public void dragEnter(DropTargetEvent event)
            {
               if (event.detail == DND.DROP_DEFAULT)
               {
                  event.detail = (event.operations & DND.DROP_COPY) != 0
                        ? DND.DROP_COPY
                        : DND.DROP_NONE;
               }

               // Allow dropping text only
               for (int i = 0, n = event.dataTypes.length; i < n; i++)
               {
                  if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i]))
                  {
                     event.currentDataType = event.dataTypes[i];
                  }
               }
            }

            public void dragOver(DropTargetEvent event)
            {
               event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
            }

            public void drop(DropTargetEvent event)
            {
               if (TextTransfer.getInstance().isSupportedType(event.currentDataType))
               {
                  if (event.data != null)
                  {
                     AccessPointType selectedTarget = controller.getSelectedTargetField();
                     if (selectedTarget != null && !controller.isRoot(selectedTarget))
                     {
                        String textToDrop = event.data.toString();
                        expressionsEditor.getAdaptedSourceViewer().getTextWidget()
                              .insert(" " + textToDrop + " "); //$NON-NLS-1$ //$NON-NLS-2$
                        controller.updateExpressions(expressionsEditor
                              .getAdaptedSourceViewer().getTextWidget().getText());
                        targetMessageTreeViewer.refreshVisibleItems();
                     }
                  }
               }
            }
         });
      }
   }

   public JSCompilationUnitEditor getExpressionsEditor()
   {
      return expressionsEditor;
   }

   public TreeViewer getSourceMessageTreeViewer()
   {
      return sourceMessageTreeViewer;
   }

   public TreeViewer getTargetMessageTreeViewer()
   {
      return targetMessageTreeViewer;
   }

   public MessageTransformationController getController()
   {
      return controller;
   }

   public IFile getWorkingResource()
   {
      if (model != null)
      {
         Resource eResource = model.eResource();
         if (eResource != null)
         {
            URI eUri = eResource.getURI();
            if (eUri.segmentCount() > 1)
            {
               String[] segments = new String[eUri.segmentCount() - 1];
               System.arraycopy(eUri.segments(), 1, segments, 0, segments.length);
               IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(
                     StringUtils.join(Arrays.asList(segments).iterator(), "/")); //$NON-NLS-1$
               if (resource instanceof IFile)
               {
                  return (IFile) resource;
               }
            }
         }
      }
      return null;
   }

   public void keyPressed(KeyEvent arg0)
   {

   }

   public void keyReleased(KeyEvent arg0)
   {
      if (arg0.getSource().equals(
            expressionsEditor.getAdaptedSourceViewer().getTextWidget()))
      {
         controller.updateExpressions(expressionsEditor.getAdaptedSourceViewer()
               .getTextWidget().getText());
         targetMessageTreeViewer.refreshVisibleItems();
      }
   }

   protected void buttonEnablement()
   {
      targetNoExpressionFilter.setSelection(targetNoExpression);
      targetExpressionFilter.setSelection(targetExpression);
      targetHighlightedFilter.setSelection(targetHighlight);
      sourceHighlightedFilter.setSelection(sourceHighlight);
      if (targetHighlight)
      {
         targetHighlightedFilter.setImage(MessageTransformationModelingPlugin
               .getDefault().getImageDescriptor("icons/bulp_on.PNG").createImage()); //$NON-NLS-1$
      }
      else
      {
         targetHighlightedFilter.setImage(MessageTransformationModelingPlugin
               .getDefault().getImageDescriptor("icons/bulp_off.PNG").createImage()); //$NON-NLS-1$
      }
      if (sourceHighlight)
      {
         sourceHighlightedFilter.setImage(MessageTransformationModelingPlugin
               .getDefault().getImageDescriptor("icons/bulp_on.PNG").createImage()); //$NON-NLS-1$
      }
      else
      {
         sourceHighlightedFilter.setImage(MessageTransformationModelingPlugin
               .getDefault().getImageDescriptor("icons/bulp_off.PNG").createImage()); //$NON-NLS-1$
      }
      targetHighlightedFilter.redraw();
      targetNoExpressionViewerFilter.setActive(targetNoExpression && !targetExpression);
      targetExpressionViewerFilter.setActive(!targetNoExpression && targetExpression);
      targetHighlightViewerFilter.setActive(targetHighlight);
      sourceHighlightViewerFilter.setActive(sourceHighlight);
      targetErrorViewerFilter.setActive(targetError);
      targetMessageTreeViewer.refresh();
      sourceMessageTreeViewer.refresh();

      if (targetNoExpression || targetExpression || targetHighlight || targetError)
      {
         targetMessageTreeViewer.expandAll();
      }
      if (sourceHighlight)
      {
         sourceMessageTreeViewer.expandAll();
      }
   }

   public void refreshModel()
   {
      MultipleAccessPathBrowserContentProvider provider = new MultipleAccessPathBrowserContentProvider(
            DirectionType.INOUT_LITERAL, controller);
      sourceMessageTreeViewer.setContentProvider(provider);
      sourceMessageTreeViewer.setLabelProvider(new DelegatingMessageTypeLabelProvider(
            controller));
      sourceMessageTreeViewer.setComparer(new MessageTypeComparer());
      sourceMessageTreeViewer.setInput(controller.getSourceMessageTypes());
      controller.setSourceAPB(provider);

      provider = new MultipleAccessPathBrowserContentProvider(
            DirectionType.INOUT_LITERAL, controller);
      targetMessageTreeViewer.setContentProvider(provider);
      targetMessageTreeViewer.setLabelProvider(new DelegatingMessageTypeLabelProvider(
            controller));
      targetMessageTreeViewer.setComparer(new MessageTypeComparer());
      targetMessageTreeViewer.setInput(controller.getTargetMessageTypes());
      controller.setTargetAPB(provider);

      sourceMessageTreeViewer.refreshVisibleItems();
      targetMessageTreeViewer.refreshVisibleItems();
   }

   public TreeItem getRootTreeItem(TreeItem treeItem)
   {
      if (treeItem.getParentItem() != null)
      {
         return getRootTreeItem(treeItem.getParentItem());
      }
      else
      {
         return treeItem;
      }
   }

   public void refreshDocument()
   {
      if (simpleMode)
      {
         return;
      }
      expressionsEditor.getAdaptedSourceViewer().getDocument().set(
            controller.getMasterDocument());
      controller.recalculateRegions(expressionsEditor.getAdaptedSourceViewer()
            .getDocument());
      setVisibleRegion(expressionsEditor, controller.getExpressionRegion());
      targetMessageTreeViewer.refreshVisibleItems();
      sourceMessageTreeViewer.refreshVisibleItems();
   }

   private void createFilterComposites()
   {
      sourceFilterComposite = new Composite(sourceComposite, SWT.NONE);
      GridLayout sourceFilterComposioteLayout = new GridLayout();
      sourceFilterComposioteLayout.marginWidth = 0;
      sourceFilterComposioteLayout.marginHeight = 0;
      sourceFilterComposioteLayout.numColumns = 2;
      GridData sourceFilterCompositeData = new GridData();
      sourceFilterCompositeData.grabExcessHorizontalSpace = true;
      sourceFilterCompositeData.horizontalAlignment = SWT.FILL;
      sourceFilterCompositeData.horizontalSpan = 2;
      sourceFilterComposite.setLayout(sourceFilterComposioteLayout);
      sourceFilterComposite.setLayoutData(sourceFilterCompositeData);

      targetFilterComposite = new Composite(targetComposite, SWT.NONE);
      GridLayout targetFilterComposioteLayout = new GridLayout();
      targetFilterComposioteLayout.marginWidth = 0;
      targetFilterComposioteLayout.marginHeight = 0;
      targetFilterComposioteLayout.numColumns = 2;
      GridData targetFilterCompositeData = new GridData();
      targetFilterCompositeData.grabExcessHorizontalSpace = true;
      targetFilterCompositeData.horizontalAlignment = SWT.FILL;
      targetFilterCompositeData.horizontalSpan = 2;
      targetFilterComposite.setLayout(targetFilterComposioteLayout);
      targetFilterComposite.setLayoutData(targetFilterCompositeData);

      if (!simpleMode)
      {
         sourceFilterButtonComposite = new Composite(sourceFilterComposite, SWT.NONE);
         RowLayout sourceFilterButtonCompositeLayout = new RowLayout();
         RowData rowData = new RowData();
         rowData.height = 21;
         rowData.width = 21;
         sourceFilterButtonComposite.setLayout(sourceFilterButtonCompositeLayout);
         sourceHighlightedFilter = new Button(sourceFilterButtonComposite, SWT.TOGGLE);
         sourceHighlightedFilter.setImage(MessageTransformationModelingPlugin
               .getDefault().getImageDescriptor("icons/bulp_off.PNG").createImage()); //$NON-NLS-1$
         sourceHighlightedFilter.setToolTipText(Modeling_Messages.TXT__SH_HIGHLIGTED_FIELDS_ONLY);
         sourceHighlightedFilter.setLayoutData(rowData);
         sourceHighlightedFilter.addListener(SWT.Selection, new Listener()
         {
            public void handleEvent(Event event)
            {
               sourceHighlight = !sourceHighlight;
               buttonEnablement();
            }
         });

         targetFilterButtonComposite = new Composite(targetFilterComposite, SWT.NONE);
         RowLayout targetFilterButtonCompositeLayout = new RowLayout();
         targetFilterButtonComposite.setLayout(targetFilterButtonCompositeLayout);
         targetExpressionFilter = new Button(targetFilterButtonComposite, SWT.TOGGLE);
         targetExpressionFilter
               .setToolTipText(Modeling_Messages.TXT_SH_FIELD_MAPPING_EXP_ONLY);
         targetExpressionFilter.setImage(MessageTransformationModelingPlugin.getDefault()
               .getImageDescriptor("icons/mapping.JPG").createImage()); //$NON-NLS-1$
         targetExpressionFilter.setLayoutData(rowData);

         targetExpressionFilter.addListener(SWT.Selection, new Listener()
         {
            public void handleEvent(Event event)
            {
               targetExpression = !targetExpression;
               targetNoExpression = false;
               buttonEnablement();
            }
         });

         targetNoExpressionFilter = new Button(targetFilterButtonComposite, SWT.TOGGLE);
         targetNoExpressionFilter.setLayoutData(rowData);
         targetNoExpressionFilter
               .setToolTipText(Modeling_Messages.TXT_SH_FIELDS_NO_MAPPING_EXP_ONLY);
         targetNoExpressionFilter.setImage(MessageTransformationModelingPlugin
               .getDefault().getImageDescriptor("icons/nomapping.JPG").createImage()); //$NON-NLS-1$
         targetNoExpressionFilter.addListener(SWT.Selection, new Listener()
         {
            public void handleEvent(Event event)
            {
               targetNoExpression = !targetNoExpression;
               targetExpression = false;
               buttonEnablement();
            }
         });

         targetHighlightedFilter = new Button(targetFilterButtonComposite, SWT.TOGGLE);
         targetHighlightedFilter.setLayoutData(rowData);
         targetHighlightedFilter.setImage(MessageTransformationModelingPlugin
               .getDefault().getImageDescriptor("icons/bulp_off.PNG").createImage()); //$NON-NLS-1$
         targetHighlightedFilter.addListener(SWT.Selection, new Listener()
         {
            public void handleEvent(Event event)
            {
               targetHighlight = !targetHighlight;
               buttonEnablement();
            }
         });

         targetHighlightedFilter.setToolTipText(Modeling_Messages.TXT__SH_HIGHLIGTED_FIELDS_ONLY);
         targetHighlightedFilter.setLayoutData(rowData);
         Button targetErrorFilter = new Button(targetFilterButtonComposite, SWT.TOGGLE);
         targetErrorFilter.setLayoutData(rowData);
         targetErrorFilter
               .setToolTipText(Modeling_Messages.TXT_SH_FIELDS_MAPPING_EXP_INVALID);
         targetErrorFilter.setImage(MessageTransformationModelingPlugin.getDefault()
               .getImageDescriptor("icons/error.gif").createImage()); //$NON-NLS-1$
         targetErrorFilter.addListener(SWT.Selection, new Listener()
         {
            public void handleEvent(Event event)
            {
               targetError = !targetError;
               buttonEnablement();
            }
         });
      }

      sourceFilterText = new Text(sourceFilterComposite, SWT.BORDER);
      GridData textFilterData = new GridData();
      textFilterData.grabExcessHorizontalSpace = true;
      textFilterData.horizontalAlignment = SWT.FILL;
      sourceFilterText.setLayoutData(textFilterData);
      sourceFilterText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            String text = sourceFilterText.getText();
            if (text != null && text != "" && !text.startsWith(" ")) //$NON-NLS-1$ //$NON-NLS-2$
            {
               sourceTextViewerFilter.setActive(true);
               sourceTextViewerFilter.setText(text);
            }
            else
            {
               sourceTextViewerFilter.setActive(false);
            }
            sourceMessageTreeViewer.refresh();
         }
      });

      targetFilterText = new Text(targetFilterComposite, SWT.BORDER);
      targetFilterText.setLayoutData(textFilterData);
      targetFilterText.addModifyListener(new ModifyListener()
      {

         public void modifyText(ModifyEvent e)
         {
            String text = targetFilterText.getText();
            if (text != null && text != "" && !text.startsWith(" ")) //$NON-NLS-1$ //$NON-NLS-2$
            {
               targetTextViewerFilter.setActive(true);
               targetTextViewerFilter.setText(text);
            }
            else
            {
               targetTextViewerFilter.setActive(false);
            }
            targetMessageTreeViewer.refresh();
         }
      });
   }

   protected void updateMappingHeaderSection()
   {
      if (controller.isBasicTargetFieldMapping())
      {
         lblExpressionPrefix.setVisible(true);
         btnToggleMappingStyle.setSelection(false);
         ((GridData) lblExpressionPrefix.getLayoutData()).heightHint = lblExpressionPrefix
               .computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
      }
      else
      {
         lblExpressionPrefix.setVisible(false);
         btnToggleMappingStyle.setSelection(true);
         ((GridData) lblExpressionPrefix.getLayoutData()).heightHint = 0;
      }

      ((GridData) btnToggleMappingStyle.getLayoutData()).widthHint = btnToggleMappingStyle
            .computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
      sashForm.layout(true, true);
   }

   private void createToolTipListener()
   {
      final Listener labelListener = new Listener()
      {
         public void handleEvent(Event event)
         {
            Label label = (Label) event.widget;
            Shell shell = label.getShell();
            switch (event.type)
            {
            case SWT.MouseDown:
               Event e = new Event();
               e.item = (TreeItem) label.getData("_TABLEITEM");
               // Assuming table is single select, set the selection as if
               // the mouse down event went through to the table
               targetMessageTreeViewer.getTree().setSelection(
                     new TreeItem[] {(TreeItem) e.item});
               targetMessageTreeViewer.getTree().notifyListeners(SWT.Selection, e);
               shell.dispose();
               targetMessageTreeViewer.getTree().setFocus();
               break;
            case SWT.MouseExit:
               shell.dispose();
               break;
            }
         }
      };

      Listener treeListener = new Listener()
      {
         Shell tip = null;

         Label label = null;

         public void handleEvent(Event event)
         {
            switch (event.type)
            {
            case SWT.Dispose:
            case SWT.KeyDown:
            case SWT.MouseMove:
            {
               if (tip == null)
                  break;
               tip.dispose();
               tip = null;
               label = null;
               break;
            }
            case SWT.MouseHover:
            {
               Point coords = new Point(event.x, event.y);
               TreeItem item = targetMessageTreeViewer.getTree().getItem(coords);
               if (item != null)
               {
                  int columns = targetMessageTreeViewer.getTree().getColumnCount();

                  for (int i = 0; i < columns || i == 0; i++)
                  {
                     if (item.getBounds(i).contains(coords))
                     {
                        if (tip != null && !tip.isDisposed())
                           tip.dispose();
                        tip = new Shell(targetMessageTreeViewer.getTree().getShell(),
                              SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
                        tip.setBackground(targetMessageTreeViewer.getTree().getDisplay()
                              .getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                        FillLayout layout = new FillLayout();
                        layout.marginWidth = 2;
                        tip.setLayout(layout);
                        label = new Label(tip, SWT.NONE);
                        label.setForeground(targetMessageTreeViewer.getTree()
                              .getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
                        label.setBackground(targetMessageTreeViewer.getTree()
                              .getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                        label.setData(Modeling_Messages.LBL_TABLEITEM, item);
                        String text = controller.getToolTip(item, i);
                        if (text != null)
                        {
                           label.setText(text);
                           label.addListener(SWT.MouseExit, labelListener);
                           label.addListener(SWT.MouseDown, labelListener);
                           Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
                           Rectangle rect = item.getBounds(i);
                           Point pt = targetMessageTreeViewer.getTree().toDisplay(rect.x,
                                 rect.y);
                           tip.setBounds(pt.x, pt.y, size.x, size.y);
                           tip.setVisible(true);
                        }
                        break;
                     }
                  }
               }
            }
            }
         }
      };
      targetMessageTreeViewer.getTree().addListener(SWT.Dispose, treeListener);
      targetMessageTreeViewer.getTree().addListener(SWT.KeyDown, treeListener);
      targetMessageTreeViewer.getTree().addListener(SWT.MouseMove, treeListener);
      targetMessageTreeViewer.getTree().addListener(SWT.MouseHover, treeListener);

   }

   private static void setVisibleRegion(JSCompilationUnitEditor editor,
         RegionWithLineOffset region)
   {
      editor.getAdaptedSourceViewer().setVisibleRegion(region.getOffset(),
            region.getLength());
      editor.setLineOffset(region.getLineOffset());
   }

   public void dispose()
   {
      EditorUtils.deleteFileStructure(project, model);
   }

   private static final Image errorImage = JavaPluginImages
         .get(JavaPluginImages.IMG_OBJS_FIXABLE_ERROR);

   public void modelChanged(IAnnotationModel model)
   {
      // (rp): This is a fix for a bug in WTP related to the annotation ruler(CRNT-9659)
      for (Iterator i = model.getAnnotationIterator(); i.hasNext();)
      {
         try
         {
            Object annotation = i.next();
            if (Reflect.getField(annotation.getClass(), "fProblem") != null) //$NON-NLS-1$
            {
               Object problem = Reflect.getFieldValue(annotation, "fProblem"); //$NON-NLS-1$
               if (problem != null)
               {
                  if (Reflect.getField(problem.getClass(), "message") != null) //$NON-NLS-1$
                  {
                     Object message = Reflect.getFieldValue(problem, "message"); //$NON-NLS-1$
                     if (message != null && message.toString().indexOf("resolved") > 0) //$NON-NLS-1$
                     {
                        fixAnnotationImage(annotation);
                        break;
                     }
                  }
               }
            }
            if (Reflect.getField(annotation.getClass(), "fType") != null) //$NON-NLS-1$
            {
               Object type = Reflect.getFieldValue(annotation, "fType"); //$NON-NLS-1$
               if (type != null
                     && !type.toString().equalsIgnoreCase(
                           "org.eclipse.wst.jsdt.ui.warning")) //$NON-NLS-1$
               {
                  fixAnnotationImage(annotation);
               }
            }
         }
         catch (Throwable t)
         {
            // If this fails at least the tool tip of the annotation is displayed
            t.printStackTrace();
         }
      }
   }

   private void fixAnnotationImage(Object annotation)
   {
      if (Reflect.getField(annotation.getClass(), "fImage") != null) //$NON-NLS-1$
      {
         Reflect.setFieldValue(annotation, "fImage", errorImage); //$NON-NLS-1$
      }

   }

   public void setExternalReference(boolean externalReference)
   {
      this.externalReference = externalReference;
   }

}