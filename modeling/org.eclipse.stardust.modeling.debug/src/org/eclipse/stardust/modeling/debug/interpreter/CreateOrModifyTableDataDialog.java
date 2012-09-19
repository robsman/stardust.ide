/*
 * $Id: CreateOrModifyTableDataDialog.java 7281 2005-01-20 19:09:01Z rsauer $
 * (C) 2000 - 2005 CARNOT AG
 */
package org.eclipse.stardust.modeling.debug.interpreter;

import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.eclipse.stardust.common.error.ValidationException;
import org.eclipse.stardust.engine.core.compatibility.gui.*;

/** */
public class CreateOrModifyTableDataDialog extends AbstractDialog
{
   protected static CreateOrModifyTableDataDialog singleton = null;

   private DataGroup dataGroup;
   private TextEntry idEntry;
   private TextEntry labelEntry;
   private JCheckBox mandatoryBox;
   private JCheckBox readonlyBox;
   private ByteEntry columnEntry;
   private ByteEntry rowEntry;
   private TextEntry toolTipEntry;

   /**
    * @todo Insert the method's description here.
    */
   protected CreateOrModifyTableDataDialog()
   {
      this(null);
   }

   /**
    * @todo Insert the method's description here.
    */
   protected CreateOrModifyTableDataDialog(Frame parent)
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

      components.add(idEntry = new TextEntry(10), "ID:", 'i'); //$NON-NLS-1$
      idEntry.setMandatory(true);
      components.add(labelEntry = new TextEntry(20), "Label:", 'l'); //$NON-NLS-1$
      components.add(new JComponent[]{mandatoryBox = new JCheckBox("Mandatory"), readonlyBox = new JCheckBox("Readonly")}, //$NON-NLS-1$ //$NON-NLS-2$
            new String[]{"", ""}, new int[]{'m', 'r'}); //$NON-NLS-1$ //$NON-NLS-2$
      components.add(new JComponent[]{rowEntry = new ByteEntry(), columnEntry = new ByteEntry()},
            new String[]{"Row:", "Column:"}, new int[]{'r', 'c'}); //$NON-NLS-1$ //$NON-NLS-2$
      components.add(toolTipEntry = new TextEntry(20), "Tooltip Text: ", 't'); //$NON-NLS-1$
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
    */
   public void setData(TableData table)
   {
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
         singleton = new CreateOrModifyTableDataDialog(parent);
      }

      singleton.dataGroup = dataGroup;

      return showDialog("Create Data", singleton, editor); //$NON-NLS-1$
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, TableData tableData)
   {
      return showDialog(editor, tableData, null);
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, TableData tableData, Frame parent)
   {
      if (singleton == null)
      {
         singleton = new CreateOrModifyTableDataDialog(parent);
      }

      return showDialog("Modify Table Data", singleton, editor); //$NON-NLS-1$
   }
}
