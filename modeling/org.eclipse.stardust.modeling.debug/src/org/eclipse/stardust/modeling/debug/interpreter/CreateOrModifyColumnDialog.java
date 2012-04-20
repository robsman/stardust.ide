/*
 * $Id: CreateOrModifyColumnDialog.java 7281 2005-01-20 19:09:01Z rsauer $
 * (C) 2000 - 2005 CARNOT AG
 */
package org.eclipse.stardust.modeling.debug.interpreter;

import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.eclipse.stardust.common.error.ValidationException;
import org.eclipse.stardust.engine.core.compatibility.gui.*;

/** */
public class CreateOrModifyColumnDialog extends AbstractDialog
{
   protected static CreateOrModifyColumnDialog singleton = null;

   private TableData table;
   private TextEntry idEntry;
   private TextEntry labelEntry;
   private JCheckBox mandatoryBox;
   private JCheckBox readonlyBox;
   private ShortEntry lengthEntry;
   private ByteEntry columnEntry;
   private ByteEntry rowEntry;
   private TextEntry defaultEntry;
   private TextEntry toolTipEntry;

   /** */
   protected CreateOrModifyColumnDialog()
   {
      this(null);
   }

   /** */
   protected CreateOrModifyColumnDialog(Frame parent)
   {
      super(parent);
   }

   /**
    *
    */
   public JComponent createContent()
   {
      JPanel panel = new JPanel();

      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      panel.setBorder(GUI.getEmptyPanelBorder());

      LabeledComponentsPanel components = new LabeledComponentsPanel();

      components.add(idEntry = new TextEntry(10), "ID:", 'i');
      idEntry.setMandatory(true);
      components.add(new JComboBox(new String[]{"Integer", "Money"}), "ID:", 'i');
      components.add(labelEntry = new TextEntry(20), "Label:", 'l');
      components.add(new JComponent[]{mandatoryBox = new JCheckBox("Mandatory"), readonlyBox = new JCheckBox("Readonly")},
            new String[]{"", ""}, new int[]{'m', 'r'});
      components.add(defaultEntry = new TextEntry(30), "Default Value: ", 'd');
      components.add(new JComponent[]{lengthEntry = new ShortEntry(), rowEntry = new ByteEntry(), columnEntry = new ByteEntry()},
            new String[]{"Length:", "Row:", "Column:"}, new int[]{'l', 'r', 'c'});
      components.add(toolTipEntry = new TextEntry(20), "Tooltip Text: ", 't');
      components.pack();

      panel.add(components);

      return panel;
   }

   /**
    *
    */
   public void onOK()
   {
   }

   public void validateSettings() throws ValidationException
   {
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, Column column)
   {
      return showDialog(editor, column, null);
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, Column column, Frame parent)
   {
      if (singleton == null)
      {
         singleton = new CreateOrModifyColumnDialog(parent);
      }

      return showDialog("Modify Column", singleton, editor);
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, TableData table)
   {
      return showDialog(editor, table, null);
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, TableData table, Frame parent)
   {
      if (singleton == null)
      {
         singleton = new CreateOrModifyColumnDialog(parent);
      }

      singleton.table = table;

      return showDialog("Create Column", singleton, editor);
   }
}
