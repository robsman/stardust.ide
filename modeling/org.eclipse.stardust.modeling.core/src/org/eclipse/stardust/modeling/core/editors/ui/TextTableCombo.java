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

import org.eclipse.stardust.modeling.common.platform.utils.ItemConsumer;
import org.eclipse.stardust.modeling.common.platform.utils.ItemProducer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.accessibility.AccessibleTextAdapter;
import org.eclipse.swt.accessibility.AccessibleTextEvent;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.swt.widgets.Widget;


public class TextTableCombo extends Composite
{
   private int minItemCount = 5;
   private int maxItemCount = 10;

   private Text text;
   private Control table;
   private Shell popup;
   private Button arrow;

   private boolean hasFocus;

   private Listener listener;
   private Listener filter;

   private TableManager manager;
   private ItemProducer producer;
   private ItemConsumer consumer;

   private String prefix = ""; //$NON-NLS-1$

   /**
    * Constructs a new instance of this class given its parent
    * and a style value describing its behavior and appearance.
    * <p>
    * The style value is either one of the style constants defined in
    * class <code>SWT</code> which is applicable to instances of this
    * class, or must be built by <em>bitwise OR</em>'ing together
    * (that is, using the <code>int</code> "|" operator) two or more
    * of those <code>SWT</code> style constants. The class description
    * lists the style constants that are applicable to the class.
    * Style bits are also inherited from superclasses.
    * </p>
    *
    * @param parent a widget which will be the parent of the new instance (cannot be null)
    * @param style the style of widget to construct
    *
    * @exception IllegalArgumentException <ul>
    *    <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
    * </ul>
    * @exception SWTException <ul>
    *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
    * </ul>
    *
    * @see SWT#BORDER
    * @see SWT#FLAT
    * @see Widget#getStyle()
    */
   public TextTableCombo(Composite parent, int style)
   {
      super(parent, style = checkStyle(style));

      int textStyle = SWT.SINGLE;
      if ((style & SWT.FLAT) != 0)
      {
         textStyle |= SWT.FLAT;
      }
      text = new Text(this, textStyle);

      int arrowStyle = SWT.ARROW | SWT.DOWN;
      if ((style & SWT.FLAT) != 0)
      {
         arrowStyle |= SWT.FLAT;
      }
      arrow = new Button(this, arrowStyle);

      listener = new Listener()
      {
         public void handleEvent(Event event)
         {
            if (popup == event.widget)
            {
               popupEvent(event);
               return;
            }
            if (text == event.widget)
            {
               textEvent(event);
               return;
            }
            if (table == event.widget)
            {
               listEvent(event);
               return;
            }
            if (getShell() == event.widget)
            {
               handleFocus(SWT.FocusOut);
            }
         }
      };

      filter = new Listener()
      {
         public void handleEvent(Event event)
         {
            Shell shell = ((Control) event.widget).getShell();
            if (shell == TextTableCombo.this.getShell())
            {
               handleFocus(SWT.FocusOut);
            }
         }
      };

      addDisposeListener(new DisposeListener()
      {
         public void widgetDisposed(DisposeEvent e)
         {
            if (popup != null && !popup.isDisposed())
            {
               table.removeListener(SWT.Dispose, listener);
               popup.dispose();
            }
            Shell shell = getShell();
            shell.removeListener(SWT.Deactivate, listener);
            Display display = getDisplay();
            display.removeFilter(SWT.FocusIn, filter);

            popup = null;
            text = null;
            table = null;
            arrow = null;
         }
      });

      addControlListener(new ControlAdapter()
      {
         public void controlMoved(ControlEvent e)
         {
            dropDown(false);
         }

         public void controlResized(ControlEvent e)
         {
            internalLayout(false);
         }
      });

      int[] textEvents = {SWT.KeyDown, SWT.KeyUp, SWT.FocusIn};
      for (int i = 0; i < textEvents.length; i++)
      {
         text.addListener(textEvents[i], listener);
      }

      arrow.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            dropDown(!isDropped());
         }
      });

      createPopup(new int[] {-1});
      initAccessible();
   }

   private static int checkStyle(int style)
   {
      int mask = SWT.BORDER | SWT.READ_ONLY | SWT.FLAT | SWT.LEFT_TO_RIGHT
            | SWT.RIGHT_TO_LEFT;
      return style & mask;
   }

   public void addModifyListener(ModifyListener listener)
   {
      checkWidget();
      if (listener == null)
      {
         SWT.error(SWT.ERROR_NULL_ARGUMENT);
      }
      text.addModifyListener(listener);
   }

   public void addSelectionListener(SelectionListener listener)
   {
      checkWidget();
      if (listener == null)
      {
         SWT.error(SWT.ERROR_NULL_ARGUMENT);
      }
      TypedListener typedListener = new TypedListener(listener);
      addListener(SWT.Selection, typedListener);
      addListener(SWT.DefaultSelection, typedListener);
   }

/*   private void arrowEvent(Event event)
   {
      switch (event.type)
      {
         case SWT.FocusIn:
            {
               handleFocus(SWT.FocusIn);
               break;
            }
         case SWT.Selection:
            {
               dropDown(!isDropped());
               break;
            }
      }
   }*/

   public void clearSelection()
   {
      checkWidget();
      text.clearSelection();
      manager.getSelectedItem();
      manager.deselectAll();
   }

/*   private void comboEvent(Event event)
   {
      switch (event.type)
      {
         case SWT.Dispose:
            if (popup != null && !popup.isDisposed())
            {
               table.removeListener(SWT.Dispose, listener);
               popup.unbind();
            }
            Shell shell = getShell();
            shell.removeListener(SWT.Deactivate, listener);
            Display display = getDisplay();
            display.removeFilter(SWT.FocusIn, filter);
            popup = null;
            text = null;
            table = null;
            arrow = null;
            break;
         case SWT.Move:
            dropDown(false);
            break;
         case SWT.Resize:
            internalLayout(false);
            break;
      }
   }*/

   void shellEvent(Event event)
   {
      switch (event.type)
      {
         case SWT.Move:
            dropDown(false);
            break;
         case SWT.Resize:
            internalLayout(false);
            break;
      }
   }

   public Point computeSize(int wHint, int hHint, boolean changed)
   {
      checkWidget();
      int width = 0;
      int height = 0;
//      int textWidth = 0;
      GC gc = new GC(text);
      int spacer = gc.stringExtent(" ").x; //$NON-NLS-1$
/*      for (int i = 0; i < manager.getRowCount(); i++)
      {
         Object item = manager.getElementAt(i);
         // !!! not correct, table may have more than 1 column
         textWidth = Math.max(gc.stringExtent(manager.getColumnText(item)[0]).x, textWidth);
      }*/
      gc.dispose();
      Point textSize = text.computeSize(SWT.DEFAULT, SWT.DEFAULT, changed);
      Point arrowSize = arrow.computeSize(SWT.DEFAULT, SWT.DEFAULT, changed);
      Point listSize = table.computeSize(wHint, SWT.DEFAULT, changed);
      int borderWidth = getBorderWidth();

      height = Math.max(hHint, Math.max(textSize.y, arrowSize.y) + 2
            * borderWidth);
//      width = Math.max(wHint, Math.max(textWidth + 2 * spacer + arrowSize.x
      width = Math.max(wHint, Math.max(textSize.x + 2 * spacer + arrowSize.x
            + 2 * borderWidth, listSize.x));
      return new Point(width, height);
   }

   void createPopup(int[] selectionIndex)
   {
      // create shell and table
      popup = new Shell(getShell(), SWT.NO_TRIM | SWT.ON_TOP);
      int style = getStyle();

      int tableStyle = SWT.SINGLE | SWT.V_SCROLL | SWT.FULL_SELECTION;
      if ((style & SWT.FLAT) != 0)
      {
         tableStyle |= SWT.FLAT;
      }
      if ((style & SWT.RIGHT_TO_LEFT) != 0)
      {
         tableStyle |= SWT.RIGHT_TO_LEFT;
      }
      if ((style & SWT.LEFT_TO_RIGHT) != 0)
      {
         tableStyle |= SWT.LEFT_TO_RIGHT;
      }

      //todo: depends upon the style if it creates a tree or a table
      table = new Table(popup, tableStyle);
      manager = new TableManager((Table) table);
      // defaults to 1 column
      manager.setColumnNames(new String[] {Diagram_Messages.TABLE_Column_Name});
      manager.setColumnSizes(new int[] {100});

/*      if (foreground != null)
      {
         table.setForeground(foreground);
      }*/
/*      if (background != null)
      {
         table.setBackground(background);
      }*/

      int[] popupEvents = {SWT.Close, SWT.Paint, SWT.Deactivate};
      for (int i = 0; i < popupEvents.length; i++)
      {
         popup.addListener(popupEvents[i], listener);
      }
      int[] listEvents = {SWT.MouseUp, SWT.Selection, SWT.Traverse,
                          SWT.KeyDown, SWT.KeyUp, SWT.FocusIn, SWT.Dispose};
      for (int i = 0; i < listEvents.length; i++)
      {
         table.addListener(listEvents[i], listener);
      }

      if (producer != null)
      {
         if (consumer != null)
         {
            consumer.produceComplete();
         }
         producer.produce(text.getText(), consumer = newConsumer(true));
      }

      if (selectionIndex[0] != -1)
      {
         manager.setSelection(selectionIndex);
      }
   }

   /**
    *
    */
   public void deselect(int index)
   {
      checkWidget();
      manager.deselect(index);
   }

   /**
    *
    */
   public void deselectAll()
   {
      checkWidget();
      manager.deselectAll();
   }

   void dropDown(boolean drop)
   {
      if (drop == isDropped())
         return;
      if (!drop)
      {
         popup.setVisible(false);
         if (!isDisposed())
         {
            // text.setFocus();
         }
         return;
      }

      if (getShell() != popup.getParent())
      {
         int[] selectionIndex = manager.getSelectionIndex();
         table.removeListener(SWT.Dispose, listener);
         popup.dispose();
         popup = null;
         table = null;
         createPopup(selectionIndex);
      }

      Point size = getSize();
      int itemCount = Math.min(Math.max(manager.getRowCount(), minItemCount), maxItemCount);
      Point listSize = manager.computeSize(SWT.DEFAULT, itemCount, false);
      table.setBounds(1, 1, size.x - 2, listSize.y);

      int[] index = manager.getSelectionIndex();
      if (index[0] != -1)
      {
         manager.setTopIndex(index);
      }
      Display display = getDisplay();
      Rectangle listRect = table.getBounds();
      Rectangle parentRect = display.map(getParent(), null, getBounds());
      Point comboSize = getSize();
      Rectangle displayRect = getMonitor().getClientArea();
      int width = Math.max(comboSize.x, listRect.width + 2);
      int height = listRect.height + 2;
      int x = parentRect.x;
      int y = parentRect.y + comboSize.y;
      if (y + height > displayRect.y + displayRect.height)
         y = parentRect.y - height;
      popup.setBounds(x, y, width, height);
      popup.setVisible(true);
   }

   Label getAssociatedLabel()
   {
      Control[] siblings = getParent().getChildren();
      for (int i = 0; i < siblings.length; i++)
      {
         if (siblings[i] == TextTableCombo.this)
         {
            if (i > 0 && siblings[i - 1] instanceof Label)
            {
               return (Label) siblings[i - 1];
            }
         }
      }
      return null;
   }

   public Control[] getChildren()
   {
      checkWidget();
      return new Control[0];
   }

   public boolean getEditable()
   {
      checkWidget();
      return text.getEditable();
   }

   public Object getItem(int[] index)
   {
      checkWidget();
      return manager.getElementAt(index);
   }

   public int getItemCount()
   {
      checkWidget();
      return manager.getRowCount();
   }

/*   public int getItemHeight()
   {
      checkWidget();
      return manager.getItemHeight();
   }*/

   char getMnemonic(String string)
   {
      int index = 0;
      int length = string.length();
      do
      {
         while ((index < length) && (string.charAt(index) != '&'))
            index++;
         if (++index >= length)
            return '\0';
         if (string.charAt(index) != '&')
            return string.charAt(index);
         index++;
      }
      while (index < length);
      return '\0';
   }

   public Point getSelection()
   {
      checkWidget();
      return text.getSelection();
   }

   public int[] getSelectionIndex()
   {
      checkWidget();
      return manager.getSelectionIndex();
   }

   public int getStyle()
   {
      int style = super.getStyle();
      return style;
   }

   public String getText()
   {
      checkWidget();
      return text.getText();
   }

   public int getTextHeight()
   {
      checkWidget();
      return text.getLineHeight();
   }

   public int getTextLimit()
   {
      checkWidget();
      return text.getTextLimit();
   }

   public int getMaxItemCount()
   {
      checkWidget();
      return maxItemCount;
   }

   public int getMinItemCount()
   {
      checkWidget();
      return minItemCount;
   }

   void handleFocus(int type)
   {
      if (isDisposed())
      {
         return;
      }
      Shell shell = getShell();
      Display display = getDisplay();
      switch (type)
      {
         case SWT.FocusIn:
            if (hasFocus)
               return;
            hasFocus = true;
            shell.removeListener(SWT.Deactivate, listener);
            shell.addListener(SWT.Deactivate, listener);
            display.removeFilter(SWT.FocusIn, filter);
            display.addFilter(SWT.FocusIn, filter);
            break;
         case SWT.FocusOut:
            if (!hasFocus)
               return;
            Control focusControl = getDisplay().getFocusControl();
            if (focusControl == arrow || focusControl == table
                  || focusControl == text)
               return;
            hasFocus = false;
            dropDown(false);
            shell.removeListener(SWT.Deactivate, listener);
            display.removeFilter(SWT.FocusIn, filter);
            // Event e = new Event();
            // notifyListeners(SWT.FocusOut, e);
            break;
      }
   }

   void initAccessible()
   {
      AccessibleAdapter accessibleAdapter = new AccessibleAdapter()
      {
         public void getName(AccessibleEvent e)
         {
            String name = null;
            Label label = getAssociatedLabel();
            if (label != null)
            {
               name = stripMnemonic(label.getText());
            }
            e.result = name;
         }

         public void getKeyboardShortcut(AccessibleEvent e)
         {
            String shortcut = null;
            Label label = getAssociatedLabel();
            if (label != null)
            {
               String text = label.getText();
               if (text != null)
               {
                  char mnemonic = getMnemonic(text);
                  if (mnemonic != '\0')
                  {
                     shortcut = "Alt+" + mnemonic; //$NON-NLS-1$
                  }
               }
            }
            e.result = shortcut;
         }

         public void getHelp(AccessibleEvent e)
         {
            e.result = getToolTipText();
         }
      };
      getAccessible().addAccessibleListener(accessibleAdapter);
      text.getAccessible().addAccessibleListener(accessibleAdapter);
      table.getAccessible().addAccessibleListener(accessibleAdapter);

      arrow.getAccessible().addAccessibleListener(new AccessibleAdapter()
      {
         public void getName(AccessibleEvent e)
         {
            e.result = isDropped() ? SWT.getMessage("SWT_Close") : SWT.getMessage("SWT_Open"); //$NON-NLS-1$ //$NON-NLS-2$
         }

         public void getKeyboardShortcut(AccessibleEvent e)
         {
            e.result = "Alt+Down Arrow"; //$NON-NLS-1$
         }

         public void getHelp(AccessibleEvent e)
         {
            e.result = getToolTipText();
         }
      });

      getAccessible().addAccessibleTextListener(new AccessibleTextAdapter()
      {
         public void getCaretOffset(AccessibleTextEvent e)
         {
            e.offset = text.getCaretPosition();
         }
      });

      getAccessible().addAccessibleControlListener(
            new AccessibleControlAdapter()
            {
               public void getChildAtPoint(AccessibleControlEvent e)
               {
                  Point testPoint = toControl(e.x, e.y);
                  if (getBounds().contains(testPoint))
                  {
                     e.childID = ACC.CHILDID_SELF;
                  }
               }

               public void getLocation(AccessibleControlEvent e)
               {
                  Rectangle location = getBounds();
                  Point pt = toDisplay(location.x, location.y);
                  e.x = pt.x;
                  e.y = pt.y;
                  e.width = location.width;
                  e.height = location.height;
               }

               public void getChildCount(AccessibleControlEvent e)
               {
                  e.detail = 0;
               }

               public void getRole(AccessibleControlEvent e)
               {
                  e.detail = ACC.ROLE_COMBOBOX;
               }

               public void getState(AccessibleControlEvent e)
               {
                  e.detail = ACC.STATE_NORMAL;
               }

               public void getValue(AccessibleControlEvent e)
               {
                  e.result = getText();
               }
            });

      text.getAccessible().addAccessibleControlListener(
            new AccessibleControlAdapter()
            {
               public void getRole(AccessibleControlEvent e)
               {
                  e.detail = text.getEditable() ? ACC.ROLE_TEXT
                        : ACC.ROLE_LABEL;
               }
            });

      arrow.getAccessible().addAccessibleControlListener(
            new AccessibleControlAdapter()
            {
               public void getDefaultAction(AccessibleControlEvent e)
               {
                  e.result = isDropped() ? SWT.getMessage("SWT_Close") : SWT.getMessage("SWT_Open"); //$NON-NLS-1$ //$NON-NLS-2$
               }
            });
   }

   boolean isDropped()
   {
      return popup.getVisible();
   }

   public boolean isFocusControl()
   {
      checkWidget();
      if (text.isFocusControl() || arrow.isFocusControl()
            || table.isFocusControl() || popup.isFocusControl())
      {
         return true;
      }
      return super.isFocusControl();
   }

   void internalLayout(boolean changed)
   {
      if (isDropped())
      {
         dropDown(false);
      }
      Rectangle rect = getClientArea();
      int width = rect.width;
      int height = rect.height;
      Point arrowSize = arrow.computeSize(SWT.DEFAULT, height, changed);
      text.setBounds(0, 0, width - arrowSize.x, height);
      arrow.setBounds(width - arrowSize.x, 0, arrowSize.x, arrowSize.y);
   }

   void listEvent(Event event)
   {
      switch (event.type)
      {
         case SWT.Dispose:
            if (getShell() != popup.getParent())
            {
               int[] selectionIndex = manager.getSelectionIndex();
               popup = null;
               table = null;
               createPopup(selectionIndex);
            }
            break;

         case SWT.MouseUp:
            {
               // if (event.button != 1)
               // return;
               internalSetText(getTextAtSelection());

               dropDown(false);
               text.selectAll();
               text.setFocus();
               break;
            }
         case SWT.Selection:
            {
               int[] index = manager.getSelectionIndex();
               if (index[0] == -1)
               {
                  return;
               }
               internalSetText(getTextAt(index));
               text.selectAll();
               manager.setSelection(index);
               Event e = new Event();
               e.time = event.time;
               e.stateMask = event.stateMask;
               e.doit = event.doit;
               notifyListeners(SWT.Selection, e);
               event.doit = e.doit;
               break;
            }

         case SWT.KeyUp:
            {
               if (manager.getRowCount() > 0 && manager.getSelectionIndex()[0] != -1)
               {
                  internalSetText(getTextAtSelection());
                  text.selectAll();
               }
               break;
            }
         case SWT.KeyDown:
            {

               if (event.character == SWT.ESC)
               {
                  // Escape key cancels popup list
                  dropDown(false);
               }
               if (event.character == SWT.CR)
               {
                  dropDown(false);
                  //
               }
               if (event.keyCode == SWT.ARROW_UP && manager.getSelectionIndex()[0] == 0)
               {
                  //dropDown (false);
                  text.setFocus();
               }

            }
      }
   }

   private void internalSetText(String textToSet)
   {
      if (!text.getText().equals(textToSet))
      {
         text.setText(textToSet);
      }
   }

   public String getTextAtSelection()
   {
      return prefix + manager.getColumnText(manager.getSelectedItem())[0];
   }

   void popupEvent(Event event)
   {
      switch (event.type)
      {
         case SWT.Paint:
            // draw black rectangle around list
            Rectangle listRect = table.getBounds();
            Color black = getDisplay().getSystemColor(SWT.COLOR_BLACK);
            event.gc.setForeground(black);
            event.gc.drawRectangle(0, 0, listRect.width + 1,
                  listRect.height + 1);
            break;
         case SWT.Close:
            event.doit = false;
            dropDown(false);
            break;
         case SWT.Deactivate:
            // dropDown(false);
            break;
      }
   }

   public void redraw()
   {
      super.redraw();
      text.redraw();
      arrow.redraw();
      if (popup.isVisible())
      {
         table.redraw();
      }
   }

   public void redraw(int x, int y, int width, int height, boolean all)
   {
      super.redraw(x, y, width, height, true);
   }

   public void removeAll()
   {
      checkWidget();
      internalSetText(""); //$NON-NLS-1$
      manager.removeAll();
   }

   public void removeModifyListener(ModifyListener listener)
   {
      checkWidget();
      if (listener == null)
      {
         SWT.error(SWT.ERROR_NULL_ARGUMENT);
      }
      // todo: this is a workaround!!!
      if (text != null && !text.isDisposed())
      {
         text.removeModifyListener(listener);
      }
   }

   public void removeSelectionListener(SelectionListener listener)
   {
      checkWidget();
      if (listener == null)
         SWT.error(SWT.ERROR_NULL_ARGUMENT);
      removeListener(SWT.Selection, listener);
      removeListener(SWT.DefaultSelection, listener);
   }

   public void select(int[] index)
   {
      checkWidget();
      if (index[0] == -1)
      {
         manager.deselectAll();
         internalSetText(""); //$NON-NLS-1$
         return;
      }
      if (0 <= index[0])
      {
         if (index != getSelectionIndex())
         {
            internalSetText(getTextAt(index));
            text.selectAll();
            manager.setSelection(index);
            manager.showSelection();
//            notifyListeners(SWT.Selection, new Event());
         }
      }
   }

   private String getTextAt(int[] index)
   {
      return prefix + manager.getColumnText(manager.getElementAt(index))[0];
   }

/*   public void setBackground(Color color)
   {
      super.setBackground(color);
      background = color;
      if (text != null)
         text.setBackground(color);
      if (table != null)
         table.setBackground(color);
      if (arrow != null)
         arrow.setBackground(color);
   }*/

   public void setEditable(boolean editable)
   {
      checkWidget();
      text.setEditable(editable);
   }

   public void setEnabled(boolean enabled)
   {
      super.setEnabled(enabled);
      if (popup != null)
         popup.setVisible(false);
      if (text != null)
         text.setEnabled(enabled);
      if (arrow != null)
         arrow.setEnabled(enabled);
   }

   public boolean setFocus()
   {
      checkWidget();
      return text.setFocus();
   }

   public void setFont(Font font)
   {
      super.setFont(font);
      text.setFont(font);
      table.setFont(font);
      internalLayout(true);
   }

/*   public void setForeground(Color color)
   {
      super.setForeground(color);
      foreground = color;
      if (text != null)
         text.setForeground(color);
      if (table != null)
         table.setForeground(color);
      if (arrow != null)
         arrow.setForeground(color);
   }*/

   public void setLayout(Layout layout)
   {
      checkWidget();
      return;
   }

   public void setSelection(Point selection)
   {
      checkWidget();
      if (selection == null)
         SWT.error(SWT.ERROR_NULL_ARGUMENT);
      text.setSelection(selection.x, selection.y);
   }

   public void setTextLimit(int limit)
   {
      checkWidget();
      text.setTextLimit(limit);
   }

   public void setToolTipText(String string)
   {
      checkWidget();
      super.setToolTipText(string);
      arrow.setToolTipText(string);
      text.setToolTipText(string);
   }

   public void setVisible(boolean visible)
   {
      super.setVisible(visible);
      if (!visible)
         popup.setVisible(false);
   }

   public void setMaxItemCount(int count)
   {
      checkWidget();
      if (count < minItemCount)
         return;
      maxItemCount = count;
   }

   public void setMinItemCount(int count)
   {
      checkWidget();
      if (count < 0 || count > maxItemCount)
         return;
      minItemCount = count;
   }

   String stripMnemonic(String string)
   {
      int index = 0;
      int length = string.length();
      do
      {
         while ((index < length) && (string.charAt(index) != '&'))
            index++;
         if (++index >= length)
            return string;
         if (string.charAt(index) != '&')
         {
            return string.substring(0, index - 1)
                  + string.substring(index, length);
         }
         index++;
      }
      while (index < length);
      return string;
   }

   void textEvent(Event event)
   {
      switch (event.type)
      {
         case SWT.Modify:
            {
               Event e = new Event();
               e.time = event.time;
               notifyListeners(SWT.Modify, e);
               break;
            }
         case SWT.FocusIn:
            {
               handleFocus(SWT.FocusIn);
               break;
            }
         case SWT.KeyDown:
            {
               break;
            }
         case SWT.KeyUp:
            {
               if (event.keyCode == SWT.CR)
               {
                  text.selectAll();
                  dropDown(false);
                  return;
               }
               if (event.keyCode == SWT.ARROW_UP)
               {
                  return;
               }
               hasFocus = false;
               manager.removeAll();
               dropDown(true);
               final String hint = text.getText();
               if (consumer != null)
               {
                  consumer.produceComplete();
               }
               if (producer != null)
               {
                  producer.produce(hint, consumer = newConsumer(false));
               }
               text.setFocus();
               handleFocus(SWT.FocusIn);
               if (event.keyCode == SWT.ARROW_DOWN)
               {
                  table.setFocus();
                  if (manager.getRowCount() > 0 && text.getText() != "") //$NON-NLS-1$
                  {
                     manager.setSelection(new int[0]);
                     internalSetText(getTextAtSelection());
                  }
                  return;
               }

               break;
            }

      }
   }

   public TableManager getTableManager()
   {
      return manager;
   }

   public void setText(final String text)
   {
      if (consumer != null)
      {
         consumer.produceComplete();
      }
      internalSetText(text);
      manager.removeAll();
      if (producer != null)
      {
         producer.produce(text, newConsumer(true));
      }
   }

   public ItemProducer getDataProducer()
   {
      return producer;
   }

   public void setDataProducer(ItemProducer producer)
   {
      if (consumer != null)
      {
         consumer.produceComplete();
      }
      this.producer = producer;
      manager.removeAll();
      if (producer != null)
      {
         producer.produce(text.getText(), consumer = newConsumer(false));
      }
   }

   private ItemConsumer newConsumer(final boolean select)
   {
      return new ItemConsumer()
      {
         boolean complete = false;

         public void addData(Object object)
         {
            if (text == null || text.isDisposed())
            {
               produceComplete();
            }
            else if (!complete)
            {
               manager.addElement(object);
            }
         }

         public void produceComplete()
         {
            complete = true;
            if (select && text != null && !text.isDisposed())
            {
               manager.selectByText(text.getText());
            }
         }

         public void clear()
         {
            manager.removeAll();
         }
      };
   }

   public void setUpdatePrefix(String prefix)
   {
      this.prefix = prefix;
   }

   public Text getControl()
   {
      return text;
   }
}
