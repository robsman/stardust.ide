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
package org.eclipse.stardust.modeling.core.editors;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.dnd.ModelElementTransferDropTargetListener;
import org.eclipse.stardust.modeling.core.editors.figures.routers.DiagramShortestPathConnectionRouter;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.WorkflowModelDiagramEditPartFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.ActionFactory;

import ag.carnot.base.StringUtils;

public class DiagramEditorPage extends AbstractGraphicalEditorPage
      implements EditPartRegistry, IShellProvider
{
   private GraphicalViewer viewer;

   final WorkflowModelEditor cwmEditor;

   private final DiagramType diagram;

   private PaletteRoot paletteRoot;

   private Point mouseLocation;

   public DiagramEditorPage(WorkflowModelEditor cwmEditor, DiagramType diagram)
   {
      super(cwmEditor);

      this.cwmEditor = cwmEditor;
      this.diagram = diagram;
   }

   public PaletteRoot getPaletteRoot()
   {
      if (null == paletteRoot)
      {
         paletteRoot = WorkflowModelEditorPaletteFactory.createPaletteForDiagram(this);
         WorkflowModelEditorPaletteFactory.updatePalette(this);
      }
      return paletteRoot;
   }

   public String getPageName()
   {
      if (null != diagram)
      {
         boolean trailingBrace = false;

         StringBuffer buffer = new StringBuffer(100);
         ProcessDefinitionType process = ModelUtils.findContainingProcess(diagram);
         if (null != process)
         {
            if (!StringUtils.isEmpty(process.getName()))
            {
               buffer.append(process.getName());
            }
            else
            {
               buffer.append(Diagram_Messages.DiagramEditor_PAGENAME_UnnamedProcess);
            }
            buffer.append(" ("); //$NON-NLS-1$
            trailingBrace = true;
         }

         if (!StringUtils.isEmpty(diagram.getName()))
         {
            buffer.append(diagram.getName());
         }
         else
         {
            buffer.append(Diagram_Messages.DiagramEditor_PAGENAME_UnnamedDiagram);
         }

         if (trailingBrace)
         {
            buffer.append(")"); //$NON-NLS-1$
         }

         return buffer.toString();
      }
      else
      {
         return Diagram_Messages.DiagramEditor_PAGENAME_UnnamedDiagram;
      }
   }

   public GraphicalViewer getGraphicalViewer()
   {
      return viewer;
   }

   protected void createPageControl(Composite parent)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      composite.setBackground(parent.getBackground());
      composite.setLayout(new GridLayout(2, false));
      GridData cdata = new GridData(); 
      cdata.verticalIndent = 0;
      cdata.horizontalIndent = 0;
      cdata.widthHint = 0;
      cdata.heightHint = 0;
      //composite.setLayoutData(cdata);

      createPaletteViewer(composite);
      GridData gd2 = new GridData(GridData.FILL_VERTICAL);
      gd2.widthHint = 130;
      gd2.heightHint = 0;
      gd2.horizontalIndent = 0;
      gd2.verticalIndent = 0;
      
      getPaletteViewer().getControl().setLayoutData(gd2);

      createGraphicalViewer(composite);
      GridData gd1 = new GridData(GridData.FILL_BOTH);      
      gd1.widthHint = 275;
      getGraphicalViewer().getControl().setLayoutData(gd1);
   }

   protected void initializeGraphicalViewer()
   {
      GraphicalViewer viewer = getGraphicalViewer();

      // add the ShortestPathConnectionRouter
      ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer
            .getRootEditPart();
      IFigure nodeLayer = root.getLayer(LayerConstants.PRIMARY_LAYER);
      ConnectionLayer connLayer = (ConnectionLayer) root
            .getLayer(LayerConstants.CONNECTION_LAYER);
      // GraphicalEditPart contentEditPart = (GraphicalEditPart) root.getContents();
      DiagramShortestPathConnectionRouter router = new DiagramShortestPathConnectionRouter(
            nodeLayer);
      // contentEditPart.getFigure());
      connLayer.setConnectionRouter(router);
      // contentEditPart.getContentPane().addLayoutListener(router.getLayoutListener());

      // by default, disable snap to geometry, enable snap to grid
      ToggleSnapToGeometryAction toggleSnapToGeometry = new ToggleSnapToGeometryAction(
            viewer);
      if (toggleSnapToGeometry.isChecked())
      {
         toggleSnapToGeometry.run();
      }
      ToggleGridAction toggleSnapToGrid = new ToggleGridAction(viewer);
      if (!toggleSnapToGrid.isChecked())
      {
         toggleSnapToGrid.run();
      }

      viewer.setContents(diagram); // set the contents of this editor
      viewer.addDropTargetListener(new ModelElementTransferDropTargetListener(cwmEditor,
            viewer));
      // TODO listen for dropped parts
      // viewer.addDropTargetListener(createTransferDropTargetListener());
      viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1),
            MouseWheelZoomHandler.SINGLETON);
   }

   private void createGraphicalViewer(Composite parent)
   {
      this.viewer = new DiagramScrollingGraphicalViewer();
      viewer.createControl(parent);

      // configure the viewer
      viewer.getControl().setBackground(parent.getBackground());
      viewer.setRootEditPart(new DiagramRootEditPart());
      viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));

      ContextMenuProvider provider = new WorkflowModelEditorContextMenuProvider(this,
            cwmEditor.getActionRegistry());
      viewer.setContextMenu(provider);
      // skip registration to prevent injection of weird platform provided menu entries
      // getSite().registerContextMenu(
      // "org.eclipse.stardust.modeling.core.editor.contextmenu", //$NON-NLS-1$
      // provider, viewer);

      // hook the viewer into the editor
      registerEditPartViewer(viewer);

      // configure the viewer with drag and drop
      configureEditPartViewer(viewer);

      viewer.setEditPartFactory(new WorkflowModelDiagramEditPartFactory(cwmEditor));

      // initialize the viewer with input
      initializeGraphicalViewer();

      getGraphicalViewer().getControl().addMouseListener(new MouseAdapter()
      {
         public void mouseDoubleClick(MouseEvent e)
         {
            IAction action = cwmEditor.getActionRegistry().getAction(
                  ActionFactory.PROPERTIES.getId());
            action.run();
         }

         public void mouseDown(MouseEvent e)
         {
            mouseLocation = new Point(e.x, e.y);
         }

      });
   }

   public DiagramType getDiagram()
   {
      return diagram;
   }

   public EditPart findEditPart(Object model)
   {
      return (EditPart) getGraphicalViewer().getEditPartRegistry().get(model);
   }

   public ModelType getWorkflowModel()
   {
      return ModelUtils.findContainingModel(diagram);
   }

   public WorkflowModelEditor getWorkflowModelEditor()
   {
      return cwmEditor;
   }

   public Point getMouseLocation()
   {
      return mouseLocation;
   }

   public Shell getShell()
   {
      return getSite().getShell();
   }

   public void setMouseLocation(Point mouseLocation)
   {
      this.mouseLocation = mouseLocation;
   }
}
