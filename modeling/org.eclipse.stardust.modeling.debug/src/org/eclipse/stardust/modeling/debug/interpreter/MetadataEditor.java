/*
 * $Id: MetadataEditor.java 7281 2005-01-20 19:09:01Z rsauer $
 * (C) 2000 - 2005 CARNOT AG
 */
package org.eclipse.stardust.modeling.debug.interpreter;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.eclipse.stardust.common.error.ValidationException;
import org.eclipse.stardust.engine.core.compatibility.gui.AbstractDialog;
import org.eclipse.stardust.engine.core.compatibility.gui.GUI;
import org.eclipse.stardust.engine.core.compatibility.gui.GenericTree;

/**
 * @author mgille
 * @version $Revision: 7281 $
 */
public class MetadataEditor extends AbstractDialog
{
   private static Class[] treeClasses = new Class[]{DataGroup.class, TableData.class, Data.class, Column.class};
   private static String[][] treeAssociations = new String[][]{{"SubGroups", "Data"}, {"Columns"}, null, null};
   private static String[] treeNames = new String[]{"Label", "Label", "Label", "Label"};

   private static MetadataEditor instance;

   private GenericTree tree;
   private JMenuItem dataGroupPropertiesItem;
   private JMenuItem addDataItem;
   private JMenuItem addSubGroupItem;
   private JMenuItem dataPropertiesItem;
   private JMenuItem tableDataPropertiesItem;
   private JMenuItem columnPropertiesItem;
   private DataGroup topGroup;
   private DefaultInterpreter interpreter;
   private JPanel viewPanel;

   /**
    *
    */
   protected MetadataEditor()
   {
      this(null);
   }

   /**
    *
    */
   protected MetadataEditor(Frame parent)
   {
      super(parent);

      setResizable(true);

      interpreter = new DefaultInterpreter();
   }

   /**
    * ActionListener protocol.
    */
   public void actionPerformed(ActionEvent event)
   {
      if (event.getSource() == addDataItem)
      {
         CreateDataDialog.showDialog(this, (DataGroup) tree.getPopupObject(), JOptionPane.getFrameForComponent(this));
         tree.reload(tree.getPopupObject());
         viewPanel.removeAll();
         viewPanel.add(interpreter.createGroupComponent(topGroup));
         viewPanel.revalidate();
         viewPanel.repaint();
      }
      else if (event.getSource() == addSubGroupItem)
      {
         CreateSubGroupDialog.showDialog(this, (DataGroup) tree.getPopupObject(), true
               , JOptionPane.getFrameForComponent(this));
         tree.reload(tree.getPopupObject());
         viewPanel.removeAll();
         viewPanel.add(interpreter.createGroupComponent(topGroup));
         viewPanel.revalidate();
         viewPanel.repaint();
      }
      else if (event.getSource() == dataGroupPropertiesItem)
      {
         CreateSubGroupDialog.showDialog(this, (DataGroup) tree.getPopupObject());
         tree.objectChanged(tree.getPopupObject());
         viewPanel.removeAll();
         viewPanel.add(interpreter.createGroupComponent(topGroup));
         viewPanel.revalidate();
         viewPanel.repaint();
      }
      else if (event.getSource() == dataPropertiesItem)
      {
         CreateDataDialog.showDialog(this, (Data) tree.getPopupObject());
         tree.objectChanged(tree.getPopupObject());
         viewPanel.removeAll();
         viewPanel.add(interpreter.createGroupComponent(topGroup));
         viewPanel.revalidate();
         viewPanel.repaint();
      }
      else if (event.getSource() == tableDataPropertiesItem)
      {
         CreateOrModifyTableDataDialog.showDialog(this, (TableData) tree.getPopupObject()
               , JOptionPane.getFrameForComponent(this));
         tree.objectChanged(tree.getPopupObject());
         viewPanel.removeAll();
         viewPanel.add(interpreter.createGroupComponent(topGroup));
         viewPanel.revalidate();
         viewPanel.repaint();
      }
      else if (event.getSource() == columnPropertiesItem)
      {
         CreateOrModifyColumnDialog.showDialog(this, (Column) tree.getPopupObject()
               , JOptionPane.getFrameForComponent(this));
         tree.objectChanged(tree.getPopupObject());
         viewPanel.removeAll();
         viewPanel.add(interpreter.createGroupComponent(topGroup));
         viewPanel.revalidate();
         viewPanel.repaint();
      }
      else
      {
         super.actionPerformed(event);
      }
   }

   /**
    * Populates the center content of the dialog.
    */
   public JComponent createContent()
   {
      JPanel panel = new JPanel();

      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      panel.setBorder(GUI.getEmptyPanelBorder());

      viewPanel = new JPanel();

      viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
      viewPanel.setBorder(new TitledBorder(new EtchedBorder(), "Activity Dialog"));

      panel.add(viewPanel);
      panel.add(Box.createHorizontalStrut(10));

      tree = new GenericTree(treeClasses, treeAssociations, treeNames);

      tree.setPopupMenu(getDataGroupPopupMenu(), DataGroup.class);
      tree.setPopupMenu(getDataPopupMenu(), Data.class);
      tree.setPopupMenu(getTableDataPopupMenu(), TableData.class);
      tree.setPopupMenu(getColumnPopupMenu(), Column.class);
      tree.setIconMethod("getIcon", DataGroup.class);
      tree.setIconMethod("getIcon", Data.class);
      tree.setIconMethod("getIcon", TableData.class);
      tree.setIconMethod("getIcon", Column.class);
      tree.setLoadIncrement(100000);
      tree.setDragAndDropEnabled(true);

      JScrollPane scrollPane = new JScrollPane(tree);

      panel.add(scrollPane);
      scrollPane.setMinimumSize(new Dimension(200, 400));
      scrollPane.setPreferredSize(new Dimension(200, 400));
      scrollPane.setMaximumSize(new Dimension(1000, 1000));

      return panel;
   }

   public void validateSettings() throws ValidationException
   {
   }

   /** */
   private JPopupMenu getColumnPopupMenu()
   {
      JPopupMenu popupMenu = new JPopupMenu();

      columnPropertiesItem = new JMenuItem("Properties...");

      columnPropertiesItem.addActionListener(this);
      columnPropertiesItem.setMnemonic('e');
      popupMenu.add(columnPropertiesItem);

      return popupMenu;
   }

   /** */
   private JPopupMenu getDataGroupPopupMenu()
   {
      JPopupMenu popupMenu = new JPopupMenu();

      dataGroupPropertiesItem = new JMenuItem("Properties...");

      dataGroupPropertiesItem.addActionListener(this);
      dataGroupPropertiesItem.setMnemonic('e');
      popupMenu.add(dataGroupPropertiesItem);
      popupMenu.addSeparator();

      addDataItem = new JMenuItem("Add Data...");

      addDataItem.addActionListener(this);
      addDataItem.setMnemonic('e');
      popupMenu.add(addDataItem);

      addSubGroupItem = new JMenuItem("Add Subgroup...");

      addSubGroupItem.addActionListener(this);
      addSubGroupItem.setMnemonic('e');
      popupMenu.add(addSubGroupItem);
      popupMenu.addSeparator();

      return popupMenu;
   }

   /** */
   private JPopupMenu getDataPopupMenu()
   {
      JPopupMenu popupMenu = new JPopupMenu();

      dataPropertiesItem = new JMenuItem("Properties...");

      dataPropertiesItem.addActionListener(this);
      dataPropertiesItem.setMnemonic('e');
      popupMenu.add(dataPropertiesItem);

      return popupMenu;
   }

   /** */
   private JPopupMenu getTableDataPopupMenu()
   {
      JPopupMenu popupMenu = new JPopupMenu();

      tableDataPropertiesItem = new JMenuItem("Properties...");

      tableDataPropertiesItem.addActionListener(this);
      tableDataPropertiesItem.setMnemonic('p');
      popupMenu.add(tableDataPropertiesItem);

      return popupMenu;
   }

   /**
    *
    */
   public void setData(DataGroup topGroup)
   {
      this.topGroup = topGroup;
      tree.setRootObject(topGroup);
      viewPanel.add(interpreter.createGroupComponent(topGroup));
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(JDialog dialog, DataGroup topGroup)
   {
      return showDialog(dialog, topGroup, null);
   }

   /**
    *
    * @return boolean The flag "closedWithOk"
    */
   public static boolean showDialog(JDialog dialog, DataGroup topGroup, Frame parent)
   {
      if (instance == null)
      {
         instance = new MetadataEditor(parent);
      }

      instance.setData(topGroup);
      return showDialog("Manual Activity Dialog Editor", instance, dialog);
   }
}
