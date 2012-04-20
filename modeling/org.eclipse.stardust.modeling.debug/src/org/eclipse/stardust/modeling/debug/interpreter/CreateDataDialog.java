/*
 * $Id: CreateDataDialog.java 7281 2005-01-20 19:09:01Z rsauer $
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
public class CreateDataDialog extends AbstractDialog
{
   protected static CreateDataDialog singleton = null;

   private DataGroup dataGroup;
   private Data data;
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
   protected CreateDataDialog()
   {
      this(null);
   }

   /** */
   protected CreateDataDialog(Frame parent)
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
   public void getData(Data data)
   {
      data.setID(idEntry.getText());
      data.setLabel(labelEntry.getText());
      data.setLength(lengthEntry.getValue());
      data.setRow(rowEntry.getValue());
      data.setColumn(columnEntry.getValue());
   }

   /**
    *
    */
   public void onOK()
   {
      if (dataGroup != null)
      {
         dataGroup.createData(Integer.TYPE, labelEntry.getText(), mandatoryBox.isSelected(),
               readonlyBox.isSelected(), toolTipEntry.getText());
      }
      else
      {
         getData(data);
      }
   }

   public void validateSettings() throws ValidationException
   {
   }

   /**
    *
    */
   public void setData(Data data)
   {
      this.data = data;

      idEntry.setText(data.getID());
      labelEntry.setText(data.getLabel());
      lengthEntry.setValue(data.getLength());
      rowEntry.setValue(data.getRow());
      columnEntry.setValue(data.getColumn());
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, Data data)
   {
      return showDialog(editor, data, null);
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, Data data, Frame parent)
   {
      if (singleton == null)
      {
         singleton = new CreateDataDialog(parent);
      }

      singleton.setData(data);

      return showDialog("Modify Data", singleton, editor);
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, DataGroup dataGroup)
   {
      return showDialog(editor, dataGroup, null);
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, DataGroup dataGroup, Frame parent)
   {
      if (singleton == null)
      {
         singleton = new CreateDataDialog(parent);
      }

      singleton.dataGroup = dataGroup;

      return showDialog("Create Data", singleton, editor);
   }
}
