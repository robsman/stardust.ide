/*
 * $Id: CreateSubGroupDialog.java 7281 2005-01-20 19:09:01Z rsauer $
 * (C) 2000 - 2005 CARNOT AG
 */
package org.eclipse.stardust.modeling.debug.interpreter;

import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.eclipse.stardust.common.error.ValidationException;
import org.eclipse.stardust.engine.core.compatibility.gui.*;

/**
 * @todo set name to CreateOrModifyDataGroupPanel
 */
public class CreateSubGroupDialog extends AbstractDialog
{
   protected static CreateSubGroupDialog singleton = null;

   private DataGroup dataGroup;
   private boolean createSubgroup;
   private TextEntry idEntry;
   private TextEntry labelEntry;
   private ByteEntry columnEntry;
   private ByteEntry rowEntry;
   private TextEntry typeEntry;

   /**
    * @todo Insert the method's description here.
    */
   protected CreateSubGroupDialog()
   {
      this(null);
   }

   /**
    * @todo Insert the method's description here.
    */
   protected CreateSubGroupDialog(Frame parent)
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
      components.add(labelEntry = new TextEntry(20), "Label:", 'l');
      components.add(typeEntry = new TextEntry(20), "Type:", 't');
      components.add(new JComponent[]{rowEntry = new ByteEntry(), columnEntry = new ByteEntry()},
            new String[]{"Row:", "Column:"}, new int[]{'r', 'c'});
      components.pack();

      panel.add(components);

      return panel;
   }

   /**
    *
    */
   public void getData(DataGroup dataGroup)
   {
      dataGroup.setID(idEntry.getText());
      dataGroup.setLabel(labelEntry.getText());
      dataGroup.setType(typeEntry.getText());
      dataGroup.setRow(rowEntry.getValue());
      dataGroup.setColumn(columnEntry.getValue());
   }

   /**
    *
    */
   public void onOK()
   {
      if (createSubgroup)
      {
         dataGroup.createSubGroup(idEntry.getText());
      }
      else
      {
         getData(dataGroup);
      }
   }

   public void validateSettings() throws ValidationException
   {
   }

   /**
    *
    */
   public void setData(DataGroup dataGroup)
   {
      this.dataGroup = dataGroup;

      idEntry.setText(dataGroup.getID());
      labelEntry.setText(dataGroup.getLabel());
      typeEntry.setText(dataGroup.getType());
      rowEntry.setValue(dataGroup.getRow());
      columnEntry.setValue(dataGroup.getColumn());
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
         singleton = new CreateSubGroupDialog(parent);
      }

      singleton.createSubgroup = false;
      singleton.setData(dataGroup);

      return showDialog("Modify Data Group", singleton, editor);
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, DataGroup dataGroup,
         boolean createSubGroup)
   {
      return showDialog(editor, dataGroup, createSubGroup, null);
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(MetadataEditor editor, DataGroup dataGroup,
         boolean createSubGroup, Frame parent)
   {
      if (singleton == null)
      {
         singleton = new CreateSubGroupDialog(parent);
      }

      singleton.createSubgroup = true;
      singleton.dataGroup = dataGroup;

      return showDialog("Create Subgroup", singleton, editor);
   }
}
