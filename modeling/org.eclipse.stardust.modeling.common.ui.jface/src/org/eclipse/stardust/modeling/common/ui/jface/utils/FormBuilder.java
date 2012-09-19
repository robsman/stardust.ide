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
package org.eclipse.stardust.modeling.common.ui.jface.utils;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.StructuredViewer;

import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;


public class FormBuilder
{
   private static final ArrayContentProvider ARRAY_CONTENT_PROVIDER = new ArrayContentProvider();

   public static final int DEFAULT_TEXT_FIELD_CHARS = 50;

   /**
    * Creates a default 'label to the left of control' composite.
    *
    * @param parent
    * @return
    */
   public static Composite createLabeledControlsComposite(Composite parent)
   {
      return createLabeledControlsComposite(parent, 1);
   }

   /**
    * Creates a default 'label to the left of control' composite.
    *
    * @param parent
    * @param nLabeledControlsColumns the number of 'labeled controls' columns (usually 1).
    * @return
    */
   public static Composite createLabeledControlsComposite(Composite parent,
         int nLabeledControlsColumns)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout();
      layout.numColumns = 2 * nLabeledControlsColumns;
      // TODO rsauer: why not set to zero, but for the variant with span != 1?
      // TODO rsauer: adjust spacing to optimize appearance wrt. right aligned validation
      // status indicators
//      layout.horizontalSpacing = 0;
      composite.setLayout(layout);

      composite.setLayoutData(createDefaultMultiLineWidgetGridData());
      return composite;
   }

   public static Composite createComposite(Composite parent, int numColumns)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout();
      layout.numColumns = numColumns;
      // TODO rsauer: why not set to zero, but for the variant with span != 1?
//      layout.horizontalSpacing = 0;
      composite.setLayout(layout);

      composite.setLayoutData(createDefaultMultiLineWidgetGridData());
      return composite;
   }

   public static Composite createComposite(Composite parent, int numColumns, int span)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout();
      layout.numColumns = numColumns;
      layout.horizontalSpacing = 0;
      composite.setLayout(layout);

      composite.setLayoutData(createDefaultMultiLineWidgetGridData(span));
      return composite;
   }

   public static Combo createCombo(Composite composite)
   {
      return createCombo(composite, 1);
   }

   public static Combo createCombo(Composite composite, int span)
   {
      return createCombo(composite, SWT.READ_ONLY, span);
   }

   public static Combo createCombo(Composite composite, int style, int span)
   {
      Combo combo = new Combo(composite, style);
      combo.setLayoutData(createDefaultSingleLineWidgetGridData(span));
      return combo;
   }

   public static Button createRadioButton(Composite composite, String label)
   {
      return createRadioButton(composite, label, 1);
   }

   public static Button createRadioButton(Composite composite, String label, int span)
   {
      Button button = new Button(composite, SWT.RADIO);
      button.setText(label);
      button.setLayoutData(createDefaultSingleLineWidgetGridData(span));
      return button;
   }

   public static Group createGroup(Composite composite, String title, int numColumns)
   {
      return createGroup(composite, title, numColumns, 1);
   }

   public static Group createGroup(Composite composite, String title, int numColumns,
         int span)
   {
      Group group = new Group(composite, SWT.NONE);

      GridLayout groupLayout = new GridLayout();
      groupLayout.horizontalSpacing = 0;
      groupLayout.numColumns = numColumns;
      group.setLayout(groupLayout);

      group.setText(title);
      group.setLayoutData(createDefaultMultiLineWidgetGridData(span));

      return group;
   }

   public static org.eclipse.swt.widgets.List createList(Composite composite)
   {
      return createList(composite, 1);
   }

   public static org.eclipse.swt.widgets.List createList(Composite composite, int span)
   {
      org.eclipse.swt.widgets.List list = new org.eclipse.swt.widgets.List(composite,
            SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
      list.setLayoutData(createDefaultMultiLineWidgetGridData(span));
      return list;
   }

   public static Label createHorizontalSeparator(Composite composite, int span)
   {
      Label separator = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
      separator.setLayoutData(createDefaultSingleLineWidgetGridData(span));
      return separator;
   }

   public static Button createCheckBox(Composite composite, String label)
   {
      return createCheckBox(composite, label, 1);
   }

   public static Button createCheckBox(Composite composite, String label, int span)
   {
      return createCheckBox(composite, label, createDefaultButtonGridData(span));
   }

   public static Button createCheckBox(Composite composite, String label, GridData gd)
   {
      Button checkBox = new Button(composite, SWT.CHECK);
      checkBox.setText(label);
      checkBox.setLayoutData(gd);
      return checkBox;
   }

   public static Button createButton(Composite parent, String label,
         SelectionListener listener)
   {
      Button button = new Button(parent, SWT.PUSH);

      if (null != label)
      {
         button.setText(label);
      }

      Dialog.applyDialogFont(button);
      GridData data = new GridData(SWT.FILL, SWT.FILL, false, false);
      button.setLayoutData(data);
      applyDefaultButtonWidth(button);

      if (listener != null)
      {
         button.addSelectionListener(listener);
      }

      return button;
   }

   public static Text createText(Composite composite)
   {
      return createText(composite, 1);
   }

   public static Text createText(Composite composite, boolean password)
   {
      return createText(composite, 1, password);
   }

   public static Text createText(Composite composite, int span)
   {
      return createText(composite, span, false);
   }

   public static Text createText(Composite composite, int span, boolean password)
   {
      Text text = new Text(composite, password ? SWT.BORDER | SWT.PASSWORD : SWT.BORDER);
      text.setLayoutData(createDefaultSingleLineWidgetGridData(span));
      applyDefaultTextControlWidth(text);
      return text;
   }

   public static Text createTextArea(Composite composite, int span)
   {
      Text text = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
      text.setLayoutData(createDefaultMultiLineWidgetGridData(span));
      applyDefaultTextControlWidth(text);
      return text;
   }

   public static Tree createTree(Composite composite, int style)
   {
      Tree tree = new Tree(composite,style);
      tree.setLayoutData(createDefaultMultiLineWidgetGridData());
      return tree;
   }

   public static Tree createTree(Composite composite, int style, String[] columnNames,
         final int[] columnSizes, int span)
   {
      final Tree tree = new Tree(composite,style);
      tree.setLayoutData(createDefaultMultiLineWidgetGridData());
//    table.setLinesVisible(true);
      tree.setLayoutData(createDefaultMultiLineWidgetGridData(span));
      if (columnNames != null)
      {
         tree.setHeaderVisible(true);
         for (int i = 0; i < columnSizes.length; i++)
         {
            TreeColumn column = new TreeColumn(tree, SWT.None);
            column.setText(columnNames[i]);
         }
         tree.addControlListener(new ControlListener()
         {
            private boolean realized;

            public void controlMoved(ControlEvent e)
            {
               // ignore
            }

            public void controlResized(ControlEvent e)
            {
               if (!realized)
               {
                  Point tableSize = tree.getSize();
                  realized = true;
                  for (int i = 0; i < columnSizes.length; i++)
                  {
                     TreeColumn column = tree.getColumn(i);
                     column.setWidth(tableSize.x * columnSizes[i] / 100);
                  }
               }
            }
         });
      }
      return tree;
   }

   /**
    * Creates a table with column sizes relative to table size.
    *
    * @see #createTable(Composite, int, String[], int[], int, boolean)
    * @return
    */
   public static Table createTable(Composite composite, int style, String[] columnNames,
         final int[] columnSizes, int span)
   {
      return createTable(composite, style, columnNames, columnSizes, span, false);
   }

   /**
    * Creates a table with absolute column sizes.
    *
    * @param composite
    * @param style
    * @param columnNames
    * @param columnSizes
    * @param span
    * @param absoluteColumnSizes true if columnSizes are meant as absolute values, false if meant as relative values (%) of table size.
    * @return
    */
   public static Table createTable(Composite composite, int style, String[] columnNames,
         final int[] columnSizes, int span, final boolean absoluteColumnSizes)
   {
      final Table table = new Table(composite, style);
//      table.setLinesVisible(true);
      table.setLayoutData(createDefaultMultiLineWidgetGridData(span));
      if (columnNames != null)
      {
         table.setHeaderVisible(true);
         for (int i = 0; i < columnSizes.length; i++)
         {
            TableColumn column = new TableColumn(table, SWT.None);
            column.setText(columnNames[i]);
         }
         table.addControlListener(new ControlListener()
         {
            private boolean realized;

            public void controlMoved(ControlEvent e)
            {
               // ignore
            }

            public void controlResized(ControlEvent e)
            {
               if ( !realized)
               {
                  Point tableSize = table.getSize();
                  realized = true;
                  for (int i = 0; i < columnSizes.length; i++)
                  {
                     TableColumn column = table.getColumn(i);
                     if (absoluteColumnSizes)
                     {
                        column.setWidth(columnSizes[i]);
                     }
                     else
                     {
                        // column sizes in percent of table size.
                        column.setWidth(tableSize.x * columnSizes[i] / 100);
                     }
                  }
               }
            }
         });
      }
      return table;
   }

   public static Table createTable(Composite composite, int style, String title)
   {
      final Table table = new Table(composite, style);
//      table.setLinesVisible(true);
      table.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      table.setHeaderVisible(true);
      TableColumn column = new TableColumn(table, SWT.CENTER);
      column.setText(title);
      return table;
   }

   public static Link createLink(Composite composite, String text)
   {
      return createLink(composite, text, 1);
   }

   public static Link createLink(Composite composite, String text, int span)
   {
      Link link = new Link(composite, SWT.NONE);
      link.setText(text);
      link.setLayoutData(createDefaultLabelGridData(span));
      return link;
   }

   public static Label createLabel(Composite composite, String text)
   {
      return createLabel(composite, text, 1);
   }

   public static Label createLabel(Composite composite, String text, int span)
   {
      Label label = new Label(composite, SWT.NONE);
      label.setText(text);
      label.setLayoutData(createDefaultLabelGridData(span));
      return label;
   }

   public static Label createLabel(Composite composite, String text, int span, int style)
   {
      Label label = new Label(composite, style);
      label.setText(text);
      label.setLayoutData(createDefaultLabelGridData(span));
      return label;
   }

   public static LabelWithStatus createLabelWithLeftAlignedStatus(Composite composite,
         String name)
   {
      return createLabelWithLeftAlignedStatus(composite, name, 1);
   }

   public static LabelWithStatus createLabelWithLeftAlignedStatus(Composite composite,
         String name, int span)
   {
      LabelWithStatus label = new LabelWithStatus(composite, name, SWT.LEAD);
      label.setLayoutData(createDefaultLabelGridData(span));
      return label;
   }

   public static LabelWithStatus createLabelWithRightAlignedStatus(Composite composite,
         String name)
   {
      return createLabelWithRightAlignedStatus(composite, name, 1);
   }

   public static LabelWithStatus createLabelWithRightAlignedStatus(Composite composite,
         String name, int span)
   {
      LabelWithStatus label = new LabelWithStatus(composite, name, SWT.TRAIL);
      label.setLayoutData(createDefaultLabelGridData(span));
      return label;
   }

   /**
    * Creates a label with right aligned status to the left of a single line text control.
    * <br />
    * The parent is assumed contain a grid layout with standard two column layout.
    *
    * @param composite
    * @param txtLabel
    * @return
    */
   public static LabeledText createLabeledText(Composite composite, String txtLabel)
   {
      LabelWithStatus label = createLabelWithRightAlignedStatus(composite, txtLabel);
      Text text = createText(composite);
      return new LabeledText(text, label);
   }

   public static LabeledText createLabeledText(Composite composite, String txtLabel,
         boolean password)
   {
      LabelWithStatus label = createLabelWithRightAlignedStatus(composite, txtLabel);
      Text text = createText(composite, password);
      return new LabeledText(text, label);
   }

   public static LabeledText createLabeledTextLeftAlignedStatus(Composite composite, String txtLabel)
   {
      LabelWithStatus label = createLabelWithLeftAlignedStatus(composite, txtLabel);
      Text text = createText(composite);
      return new LabeledText(text, label);
   }

   /**
    * Creates a label with right aligned status to the left of a single line text control
    * with password style.
    * <br />
    * The parent is assumed contain a grid layout with standard two column layout.
    *
    * @param composite
    * @param txtLabel
    * @return
    */
   public static LabeledText createPasswordText(Composite composite, String txtLabel)
   {
      LabelWithStatus label = createLabelWithRightAlignedStatus(composite, txtLabel);
      Text text = createText(composite, true);
      return new LabeledText(text, label);
   }

   /**
    * Creates a label with right aligned status to the left of a combo box control. <br />
    * The parent is assumed contain a grid layout with standard two column layout.
    *
    * @param composite
    * @param txtLabel
    * @return
    */
   public static LabeledCombo createLabeledCombo(Composite composite, String txtLabel)
   {
      LabelWithStatus label = createLabelWithRightAlignedStatus(composite, txtLabel);
      Combo combo = createCombo(composite);
      return new LabeledCombo(combo, label);
   }

   /**
    * Creates a label with left aligned status above a multi line text control. <br />
    * The parent is assumed contain a grid layout with standard two column layout.
    *
    * @param composite
    * @param txtLabel
    * @return
    */
   public static LabeledText createLabeledTextArea(Composite composite, String txtLabel)
   {
      LabelWithStatus label = createLabelWithLeftAlignedStatus(composite, txtLabel, 2);
      Text text = createTextArea(composite, 2);
      return new LabeledText(text, label);
   }

   public static GridData createDefaultLabelGridData()
   {
      return new GridData(SWT.FILL, SWT.CENTER, false, false);
   }

   public static GridData createDefaultLabelGridData(int horizonalSpan)
   {
      GridData gd = createDefaultLabelGridData();
      gd.horizontalSpan = horizonalSpan;
      return gd;
   }

   public static GridData createDefaultSingleLineWidgetGridData()
   {
      return new GridData(SWT.FILL, SWT.CENTER, true, false);
   }

   public static GridData createDefaultSingleLineWidgetGridData(int horizonalSpan)
   {
      GridData gd = createDefaultSingleLineWidgetGridData();
      gd.horizontalSpan = horizonalSpan;
      return gd;
   }

   public static GridData createDefaultMultiLineWidgetGridData()
   {
      return new GridData(SWT.FILL, SWT.FILL, true, true);
   }

   public static GridData createDefaultLimitedMultiLineWidgetGridData(int hHint)
   {
      GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
      gd.heightHint = hHint;
      return gd;
   }

   public static GridData createDefaultMultiLineWidgetGridData(int horizonalSpan)
   {
      GridData gd = createDefaultMultiLineWidgetGridData();
      gd.horizontalSpan = horizonalSpan;
      return gd;
   }

   public static GridData createDefaultButtonGridData()
   {
      return new GridData(SWT.LEAD, SWT.CENTER, false, false);
   }

   public static GridData createDefaultButtonGridData(int horizonalSpan)
   {
      GridData gd = createDefaultButtonGridData();
      gd.horizontalSpan = horizonalSpan;
      return gd;
   }

   public static void applyDefaultTextControlWidth(Control control)
   {
      Object layoutData = control.getLayoutData();
      if (layoutData instanceof GridData)
      {
         ((GridData) layoutData).widthHint = FormBuilder.getDefaultTextSize(control);
      }
   }

   public static void applyDefaultButtonWidth(Button button)
   {
      Object layoutData = button.getLayoutData();
      if (layoutData instanceof GridData)
      {
         Point minButtonSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
         ((GridData) layoutData).widthHint = Math.max(
               FormBuilder.getDefaultButtonSize(button.getParent()), minButtonSize.x);
      }
   }

   public static int getDefaultTextSize(Control parent)
   {
      return getTextSize(parent, DEFAULT_TEXT_FIELD_CHARS);
   }

   public static int getTextSize(Control parent, int numChars)
   {
      return Dialog.convertWidthInCharsToPixels(getFontMetrics(parent), numChars);
   }

   public static int getDefaultButtonSize(Control parent)
   {
      return Dialog.convertHorizontalDLUsToPixels(getFontMetrics(parent),
            IDialogConstants.BUTTON_WIDTH);
   }

   public static FontMetrics getFontMetrics(Control parent)
   {
      GC gc = new GC(parent);
      gc.setFont(parent.getFont());
      FontMetrics fontMetrics = gc.getFontMetrics();
      gc.dispose();
      return fontMetrics;
   }

   /**
    * @deprecated Use {@link #createDefaultSingleLineWidgetGridData()} and
    *             {@link #applyDefaultTextControlWidth(Control)} directly.
    */
   public static void defaultControlLayout(Control control)
   {
      control.setLayoutData(createDefaultSingleLineWidgetGridData());
      applyDefaultTextControlWidth(control);
   }

   public static LabeledViewer createComboViewer(Composite parent, String label, List<?> values)
   {
      LabeledCombo targetCombo = createLabeledCombo(parent, label);
      ComboViewer targetViewer = new ComboViewer(targetCombo.getCombo());
      targetViewer.setContentProvider(ARRAY_CONTENT_PROVIDER);
      targetViewer.setInput(values);
      return new LabeledViewer(targetViewer, targetCombo.getLabel());
   }

   public static ComboViewer createComboViewer(Composite parent, List<?> values)
   {
      Combo targetCombo = createCombo(parent);
      ComboViewer targetViewer = new ComboViewer(targetCombo);
      targetViewer.setContentProvider(ARRAY_CONTENT_PROVIDER);
      targetViewer.setInput(values);
      return targetViewer;
   }
}
