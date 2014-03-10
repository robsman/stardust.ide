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
package org.eclipse.stardust.modeling.core.editors.parts.diagram;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RelativeLocator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.decoration.DecorationUtils;
import org.eclipse.stardust.modeling.core.decoration.IDecoratablePart;
import org.eclipse.stardust.modeling.core.decoration.IDecorationProvider;
import org.eclipse.stardust.modeling.core.editors.IDiagramEditorConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.AbstractSwimlaneFigure;
import org.eclipse.stardust.modeling.core.editors.figures.EditableFigure;
import org.eclipse.stardust.modeling.core.editors.figures.IFeedbackFigureFactory;
import org.eclipse.stardust.modeling.core.editors.figures.IGraphicalObjectFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.MoveNodeSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.DragEditPartsTrackerWithExplicitBendpoints;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.NodeSymbolComponentEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.NodeSymbolGraphicalNodeEditPolicy;
import org.eclipse.stardust.modeling.core.editors.parts.properties.DefaultPropSheetCmdFactory;
import org.eclipse.stardust.modeling.core.editors.tools.SnapCenterToGrid;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.SnapGridUtils;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractNodeSymbolEditPart extends AbstractNodeEditPart
      implements IHighliteableGraphicalObject, IDecoratablePart, IFeedbackFigureFactory
{
   private boolean showingConnections = true;

   private Color highliteBorderColor;
   private Color highliteFillColor;

   private Map decorations = new HashMap();

   private ConnectionAnchor anchor;
   private DirectEditManager manager;

   protected HashSet decorators = new HashSet();

   public AbstractNodeSymbolEditPart(WorkflowModelEditor editor, INodeSymbol model)
   {
      super(editor, model);
   }

   protected EStructuralFeature getDirectEditFeature()
   {
      return null;
   }

   public boolean isShowingConnections()
   {
      return showingConnections;
   }

   public void setShowingConnections(boolean showingConnections)
   {
      this.showingConnections = showingConnections;

      if (null != getParent())
      {
         refreshSourceConnections();
         refreshTargetConnections();
      }
   }

   public void setHighliteBorderColor(Color color)
   {
      this.highliteBorderColor = color;
      refreshVisuals();
   }

   public void resetHighliteBorderColor()
   {
      setHighliteBorderColor(null);
   }

   public void setHighliteFillColor(Color color)
   {
      this.highliteFillColor = color;
      refreshVisuals();
   }

   public void resetHighliteFillColor()
   {
      setHighliteFillColor(null);
   }

   public void applyDecoration(IDecorationProvider decoration)
   {
      // decorate this
      IFigure decorationFigure = (IFigure) decorations.get(decoration.getId());
      if (null != decorationFigure)
      {
         if (null != decorationFigure.getParent())
         {
            decorationFigure.getParent().remove(decorationFigure);
         }
         decorations.remove(decoration.getId());
      }
      decorationFigure = decoration.createDecoration(getCastedModel());
      if (null != decorationFigure)
      {
         decorations.put(decoration.getId(), decorationFigure);
         LayerManager manager = (LayerManager) getViewer().getEditPartRegistry().get(
               LayerManager.ID);
         IFigure decorationLayer = manager.getLayer(IDiagramEditorConstants.DECORATION_LAYER);
         if ((null != decorationLayer) && (null != getFigure()))
         {
            Locator locator = decoration.createDecorationLocator(getCastedModel(),
                  getFigure(), decorationFigure);
            if (null == locator)
            {
               locator = new RelativeLocator(getFigure(), PositionConstants.SOUTH);
            }
            decorationLayer.add(decorationFigure, locator);

            // TODO move into createFigure
            final IFigure theDecorationFigure = decorationFigure;
            getFigure().addFigureListener(new FigureListener()
            {
               public void figureMoved(IFigure source)
               {
                  theDecorationFigure.revalidate();
               }
            });
         }
      }

      // decorate children
      DecorationUtils.applyDecoration(decoration, getChildren());
      DecorationUtils.applyDecoration(decoration, getSourceConnections());
      decorators.add(decoration);
   }

   public void removeDecoration(IDecorationProvider decoration)
   {
      // remove decoration from children
      DecorationUtils.removeDecoration(decoration, getChildren());
      DecorationUtils.removeDecoration(decoration, getSourceConnections());

      // remove decoration from this
      IFigure decorationFigure = (IFigure) decorations.get(decoration.getId());
      if (null != decorationFigure)
      {
         if (null != decorationFigure.getParent())
         {
            decorationFigure.getParent().remove(decorationFigure);
         }
         decorations.remove(decoration.getId());
         decoration.decorationRemoved(getCastedModel(), decorationFigure);
      }
      decorators.remove(decoration);
   }

   public IFigure createFeedbackFigure()
   {
      IFigure figure = createFigure();
      refreshFigure(figure);
      return figure;
   }

   protected void createEditPolicies()
   {
      // handles constraint changes (e.g. moving and/or resizing) of model elements
      // and creation of new model elements
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeSymbolComponentEditPolicy());
      //installEditPolicy(EditPolicy.LAYOUT_ROLE, new NodeSymbolXYLayoutEditPolicy());
      installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
            new NodeSymbolGraphicalNodeEditPolicy(getEditor()));
      if (getDirectEditFeature() != null)
      {
         installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DirectEditPolicy()
         {
            protected Command getDirectEditCommand(DirectEditRequest request)
            {
               MoveNodeSymbolCommand move = new MoveNodeSymbolCommand();
               move.setPart(getCastedModel());
               move.setBounds(getFigure().getBounds());
               return DefaultPropSheetCmdFactory.INSTANCE.getSetCommand(
                  AbstractNodeSymbolEditPart.this,
                  (EObject) getModel(), getDirectEditFeature(),
                  request.getCellEditor().getValue().toString()).chain(move);
            }

            protected void showCurrentEditValue(DirectEditRequest request)
            {
               String value = (String) request.getCellEditor().getValue();
               ((EditableFigure) getFigure()).setText(value);
            }
         });
      }
   }

   public void performRequest(Request req)
   {
      if (req.getType() == REQ_DIRECT_EDIT)
      {
         if (getDirectEditFeature() != null && canDirectEdit(req))
         {
            performDirectEdit();
            return;
         }
      }
      super.performRequest(req);
   }

   private boolean canDirectEdit(Request req)
   {
      if (req instanceof DirectEditRequest)
      {
         return getEditingBounds().contains(((DirectEditRequest) req).getLocation());
      }
      return true;
   }

   private Rectangle getEditingBounds()
   {
      IFigure figure = getFigure();
      Rectangle bounds = new Rectangle(figure instanceof EditableFigure ?
            ((EditableFigure) getFigure()).getEditingBounds() : figure.getBounds());
      while (figure.getParent() instanceof AbstractSwimlaneFigure)
      {
         figure.getParent().translateToParent(bounds);
         figure = figure.getParent();
      }
      ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) getRoot();
      Point viewLocation = root.getZoomManager().getViewport().getViewLocation();
      bounds.scale(root.getZoomManager().getZoom());
      bounds.translate(-viewLocation.x, -viewLocation.y);
      return bounds;
   }

   private double getZoom()
   {
      ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) getRoot();
      return root.getZoomManager().getZoom();
   }

   private void performDirectEdit()
   {
      if (manager == null)
      {
         final CellEditorLocator locator = new CellEditorLocator()
         {
            public void relocate(CellEditor editor)
            {
               Rectangle bounds = getEditingBounds();
               org.eclipse.swt.graphics.Rectangle trim = ((Text) editor.getControl())
                  .computeTrim(bounds.x, bounds.y, bounds.width, bounds.height);
               editor.getControl().setBounds(trim.x, trim.y, trim.width, trim.height);
            }
         };
         manager = new DirectEditManager(this, TextCellEditor.class, locator)
         {
            private boolean snapToGrid = false;
            private Rectangle symbolRect;
            private Dimension oldSize;
            private Point oldPos;
            private Font font;
            private Font defaultFont;

            protected CellEditor createCellEditorOn(Composite composite)
            {
               EditableFigure figure = (EditableFigure) getFigure();
               TextCellEditor editor = new TextCellEditor(composite, figure.getEditingStyle());
               defaultFont = getFigure().getFont();
               editor.getControl().addDisposeListener(new DisposeListener()
               {
                  public void widgetDisposed(DisposeEvent e)
                  {
                     if (font != null)
                     {
                        font.dispose();
                     }
                  }
               });
               symbolRect = GenericUtils.getSymbolRectangle(this.getEditPart());
               oldSize = symbolRect.getSize();
               oldPos = symbolRect.getLocation();
               if(SnapGridUtils.getSnapToHelper((AbstractGraphicalEditPart) this.getEditPart()) != null)
               {
                  snapToGrid = true;
               }
               return editor;
            }

            protected void initCellEditor()
            {
               EditableFigure figure = (EditableFigure) getFigure();
               getCellEditor().setValue(figure.getText());
               FontData data = defaultFont.getFontData()[0];
               double zoom = getZoom();
               if (zoom == 1)
               {
                  getCellEditor().getControl().setFont(defaultFont);
               }
               else
               {
                  font = new Font(null, data.getName(),
                     (int) (data.getHeight() * getZoom()), data.getStyle());
                  getCellEditor().getControl().setFont(font);
               }
               ((Text) getCellEditor().getControl()).selectAll();
            }

            public void showFeedback()
            {
               super.showFeedback();

               INodeSymbol symbol = getCastedModel();
               Rectangle figureBounds = symbolRect.getCopy();
               Dimension after = getFigure().getPreferredSize().getCopy();
               // should not become smaller than before
               if(after.width < figureBounds.width)
               {
                  after.width = figureBounds.width;
               }

               int deltaX = (after.width - oldSize.width) / 2;
               if (deltaX != 0)
               {
                  figureBounds.x = oldPos.x - deltaX;
                  figureBounds.width = after.width;
               }
               Dimension size;
               // size to snap to grid
               if(snapToGrid)
               {
                  size = SnapGridUtils.getSnapDimension(new Dimension(figureBounds.width, figureBounds.height), AbstractNodeSymbolEditPart.this, 2, false);
                  double difference = new Double((size.width - oldSize.width)).doubleValue();
                  int widthDifference = (int) Math.round(difference/SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE);
                  if(widthDifference != 0)
                  {
                     // one less, because size changed by 2N
                     widthDifference /= 2;
                     if(deltaX > 0)
                     {
                        figureBounds.x = oldPos.x - SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE * widthDifference;
                     }
                  }
               }
               else
               {
                  size = new Dimension(figureBounds.width, figureBounds.height);
               }

               // do not change height
               if(symbol.getHeight() != -1)
               {
                  size.height = symbol.getHeight();
               }
               else
               {
                  size.height = figureBounds.height;
               }

               Point newLocation = new Point(figureBounds.x, figureBounds.y);
               Rectangle newBounds = new Rectangle(newLocation, size);

               // new location if snaptogrid is enabled
               Point setLocation = SnapGridUtils.getSnapLocation(AbstractNodeSymbolEditPart.this, AbstractNodeSymbolEditPart.this,
                     new PrecisionRectangle(newBounds), null, null);

               if (deltaX != 0)
               {
                  getFigure().setBounds(new Rectangle(setLocation, size));
                  if (null != getParent())
                  {
                     ((GraphicalEditPart) getParent()).setLayoutConstraint(
                           AbstractNodeSymbolEditPart.this, getFigure(), new Rectangle(setLocation, size));
                  }
               }
            }

            protected void eraseFeedback()
            {
               super.eraseFeedback();
            }
         };
      }
      manager.show();
   }

   public DragTracker getDragTracker(Request request)
   {
      return new DragEditPartsTrackerWithExplicitBendpoints(this)
      {
         protected boolean handleButtonDown(int button)
         {
            if (button == 1
               && getSourceEditPart().getSelected() != EditPart.SELECTED_NONE)
            {
               DirectEditRequest request = new DirectEditRequest();
               request.setLocation(getLocation());
               performRequest(request);
            }
            return super.handleButtonDown(button);
         }
      };
   }

   public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection)
   {
      // TODO Auto-generated method stub
      return getConnectionAnchor();
   }

   public ConnectionAnchor getSourceConnectionAnchor(Request request)
   {
      // TODO Auto-generated method stub
      return getConnectionAnchor();
   }

   public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection)
   {
      // TODO Auto-generated method stub
      return getConnectionAnchor();
   }

   public ConnectionAnchor getTargetConnectionAnchor(Request request)
   {
      // TODO Auto-generated method stub
      return getConnectionAnchor();
   }

   protected void refreshFigure(IFigure figure)
   {
      if (figure instanceof IGraphicalObjectFigure)
      {
         IGraphicalObjectFigure goFigure = (IGraphicalObjectFigure) figure;
         goFigure.setBorderColor(highliteBorderColor);
         goFigure.setFillColor(highliteFillColor);
      }
   }

   protected void refreshVisuals()
   {
      super.refreshVisuals();

      Point loc = new Point(getCastedModel().getXPos(), getCastedModel().getYPos());

      Dimension dim = new Dimension(-1, -1);

      if (getCastedModel().isSetWidth())
      {
         dim.width = getCastedModel().getWidth();
      }
      if (getCastedModel().isSetHeight())
      {
         dim.height = getCastedModel().getHeight();
      }

      Rectangle r = new Rectangle(loc, dim);

      if (null != getParent())
      {
         ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);
      }

      refreshFigure(getFigure());
   }

   public void handleNotification(Notification notification)
   {
      switch (notification.getFeatureID(INodeSymbol.class))
      {
      case CarnotWorkflowModelPackage.INODE_SYMBOL__REFERING_FROM_CONNECTIONS:
         refreshSourceConnections();
         break;

      case CarnotWorkflowModelPackage.INODE_SYMBOL__REFERING_TO_CONNECTIONS:
         refreshTargetConnections();
         break;
      }

      super.handleNotification(notification);
   }

   public Object getAdapter(Class key)
   {
      if (INodeSymbol.class == key || IModelElement.class == key)
      {
         return getCastedModel();
      }
      else
      {
         return super.getAdapter(key);
      }
   }

   protected ConnectionAnchor getConnectionAnchor()
   {
      if (null == anchor)
      {
         this.anchor = new ChopboxAnchor(getFigure());
      }
      return anchor;
   }

   private INodeSymbol getCastedModel()
   {
      return (INodeSymbol) getModel();
   }

   protected List getModelSourceConnections()
   {
      List result;
      if (isShowingConnections())
      {
         result = getCastedModel().getReferingFromConnections();
      }
      else
      {
         result = Collections.EMPTY_LIST;
      }
      return result;
   }

   protected List getModelTargetConnections()
   {
      List result;
      if (isShowingConnections())
      {
         result = getCastedModel().getReferingToConnections();
      }
      else
      {
         result = Collections.EMPTY_LIST;
      }
      return result;
   }
}