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
package org.eclipse.stardust.modeling.core.editors.figures;

import org.eclipse.draw2d.Label;
import org.eclipse.stardust.common.CompareHelper;

/**
 * @author rsauer
 * @version $Revision$
 */
public class EventFigure extends AbstractLabeledIconFigure
{
   public static final int EVENT_FLOW_START = 1;

   public static final int EVENT_FLOW_INTERMEDIATE = 2;

   public static final int EVENT_FLOW_END = 3;

   public static final int EVENT_TYPE_NONE = 0;

   public static final int EVENT_TYPE_MESSAGE = 1;

   public static final int EVENT_TYPE_TIMER = 2;

   public static final int EVENT_TYPE_ERROR = 3;

   public static final int EVENT_TYPE_CANCEL = 4;

   public static final int EVENT_TYPE_COMPENSATION = 5;

   public static final int EVENT_TYPE_RULE = 6;

   public static final int EVENT_TYPE_LINK = 7;

   public static final int EVENT_TYPE_MULTIPLE = 8;

   public static final int EVENT_TYPE_TERMINATE = 9;

   private String iconPath;

   private IconFigure typeIndicator;

   private int kind;

   public EventFigure(int kind, String iconPath)
   {
      // super(new EventSymbolFigure(kind, iconPath));

      // setOutline(false);
      super(getIconFromKind(kind, iconPath)); //$NON-NLS-1$
      this.kind = kind;
      getShape().setLayoutManager(new MyBorderLayout());
      setIconPath(iconPath);

      setOutline(false);
   }

   public void setText(String text)
   {
      boolean empty = text == null || text.length() == 0;
      if (empty)
      {
         Label label = getLabel();
         if (label.getParent() != null)
         {
            doRemoveLabel(label);
         }
      }
      else
      {
         Label label = getLabel();
         if (label.getParent() == null)
         {
            label.setParent(this);
            doAddLabel(label);
         }
         super.setText(text);
      }
   }

   public void setIconPath(String iconPath)
   {
      if (!CompareHelper.areEqual(this.iconPath, iconPath))
      {
         if (typeIndicator != null)
         {
            getShape().remove(typeIndicator);
            typeIndicator = null;
         }
         if (iconPath != null)
         {
            super.setIconPath(getIconFromKind(kind, iconPath));
            typeIndicator = new IconFigure(iconPath);
            if(getShape().getLayoutManager() == null)
            {
               getShape().setLayoutManager(new MyBorderLayout());               
            }            
            getShape().add(typeIndicator, MyBorderLayout.CENTER);
         }
         this.iconPath = iconPath;
      }
   }

   private static String getIconFromKind(int kind, String iconPath)
   {
      if (kind == EVENT_FLOW_END)
      {
         return "icons/full/obj16/end_event_with_border.gif"; //$NON-NLS-1$
      }
      else if (iconPath == null)
      {
         return "icons/full/obj16/start_event_with_border.gif"; //$NON-NLS-1$
      }
      else
      {
         return "icons/full/obj16/start_event_border.gif"; //$NON-NLS-1$
      }
   }

   /*
    * private static class EventSymbolFigure extends Ellipse implements
    * IGraphicalObjectFigure, ILabeledFigure, EditableFigure {
    * 
    * private static final String[] icons = {null, "icons/figures/messageSign.gif",
    * //$NON-NLS-1$ "icons/figures/timerSign.gif", //$NON-NLS-1$
    * "icons/figures/errorSign.gif", //$NON-NLS-1$ "icons/figures/cancelSign.gif",
    * //$NON-NLS-1$ "icons/figures/compensationSign.gif", //$NON-NLS-1$
    * "icons/figures/ruleSign.gif", //$NON-NLS-1$ "icons/figures/linkSign.gif",
    * //$NON-NLS-1$ "icons/figures/multipleSign.gif", //$NON-NLS-1$
    * "icons/figures/terminateSign.gif", //$NON-NLS-1$ };
    * 
    * private static final int START_EVENT_BORDER_LINE_WIDTH = 1;
    * 
    * private static final int END_EVENT_BORDER_LINE_WIDTH = 3;
    * 
    * private static final int BORDER_GAP_WIDTH = 3;
    * 
    * private int kind;
    * 
    * private Figure typeIndicator;
    * 
    * protected ConnectionAnchor incomingConnectionAnchor;
    * 
    * protected ConnectionAnchor outgoingConnectionAnchor;
    * 
    * private Label label;
    * 
    * public EventSymbolFigure(int kind, String iconPath) { this.kind = kind;
    * setOutline(true);
    * 
    * setLayoutManager(new MyBorderLayout()); if (iconPath != null) { typeIndicator = new
    * IconFigure(iconPath); add(typeIndicator, MyBorderLayout.CENTER); }
    * 
    * incomingConnectionAnchor = new EllipseAnchor(this); outgoingConnectionAnchor = new
    * EllipseAnchor(this); }
    * 
    * public void setName(String name) { label.setText(!StringUtils.isEmpty(name) ? name :
    * ""); //$NON-NLS-1$ }
    * 
    * public void setTypeIndicator(int eventType) { if (null != typeIndicator) {
    * remove(typeIndicator); }
    * 
    * typeIndicator = eventType > EVENT_TYPE_NONE && eventType <= EVENT_TYPE_TERMINATE ?
    * new IconFigure(icons[eventType]) : null;
    * 
    * if (null != typeIndicator) { add(typeIndicator, MyBorderLayout.CENTER); } }
    * 
    * public ConnectionAnchor getSourceConnectionAnchor() { return
    * outgoingConnectionAnchor; }
    * 
    * public ConnectionAnchor getTargetConnectionAnchor() { return
    * incomingConnectionAnchor; }
    * 
    * protected void outlineShape(Graphics graphics) { graphics.pushState(); Rectangle
    * bounds = new Rectangle(getBounds()); Point center = bounds.getCenter(); int radius =
    * Math.min(bounds.width, bounds.height) / 2; switch (kind) { case EVENT_FLOW_END:
    * radius -= END_EVENT_BORDER_LINE_WIDTH / 2;
    * graphics.setLineWidth(END_EVENT_BORDER_LINE_WIDTH); graphics.drawOval(center.x -
    * radius, center.y - radius, radius * 2, radius * 2); break; case EVENT_FLOW_START:
    * radius -= START_EVENT_BORDER_LINE_WIDTH / 2;
    * graphics.setLineWidth(START_EVENT_BORDER_LINE_WIDTH); graphics.drawOval(center.x -
    * radius, center.y - radius, radius * 2, radius * 2); break; case
    * EVENT_FLOW_INTERMEDIATE: radius -= START_EVENT_BORDER_LINE_WIDTH / 2;
    * graphics.setLineWidth(START_EVENT_BORDER_LINE_WIDTH); graphics.drawOval(center.x -
    * radius, center.y - radius, radius * 2, radius * 2); radius -= BORDER_GAP_WIDTH;
    * graphics.drawOval(center.x - radius, center.y - radius, radius * 2, radius * 2);
    * break; } graphics.popState(); }
    * 
    * public Dimension getPreferredSize(int wHint, int hHint) { Dimension size =
    * super.getPreferredSize(wHint, hHint); int max = Math.max(29, Math.max(size.width,
    * size.height)); return new Dimension(max, max); }
    * 
    * public void setBorderColor(Color color) { if (color != null) {
    * setForegroundColor(color); } }
    * 
    * public void setFillColor(Color color) { if (color != null) {
    * setBackgroundColor(color); } }
    * 
    * public void setText(String text) { if (label == null) { label = new Label(); if
    * (getParent() != null) { getParent().add(label); } } label.setText(text);
    * figureMoved(); }
    * 
    * public void addNotify() { super.addNotify(); if (label != null) {
    * getParent().add(label); figureMoved(); } }
    * 
    * public void removeNotify() { if (label != null) { getParent().remove(label); }
    * super.removeNotify(); }
    * 
    * public void setBounds(Rectangle rect) { super.setBounds(rect); figureMoved(); }
    * 
    * private void figureMoved() { if (label != null && getParent() != null) { Rectangle
    * bounds = getBounds(); Dimension labelSize = label.getTextBounds().getSize();
    * Rectangle labelBounds = new Rectangle(bounds.getBottom(), labelSize);
    * labelBounds.translate(-(labelBounds.width / 2) + 1, 0);
    * label.setBounds(labelBounds); } }
    * 
    * public String getText() { return label == null ? "" : label.getText(); //$NON-NLS-1$ }
    * 
    * public Rectangle getEditingBounds() { return label == null ? getBounds() :
    * label.getBounds(); }
    * 
    * public int getEditingStyle() { return SWT.SINGLE; } }
    */
}