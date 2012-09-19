/*
 * $Id: DefaultInterpreter.java 8581 2005-09-19 09:00:28Z rsauer $
 * (C) 2000 - 2005 CARNOT AG
 */
package org.eclipse.stardust.modeling.debug.interpreter;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.eclipse.stardust.common.NamedValues;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.engine.core.compatibility.gui.*;

/*
import ag.carnot.base.NamedValues;
import ag.carnot.base.log.LogManager;
import ag.carnot.base.log.Logger;
import ag.carnot.gui.Entry;
import ag.carnot.gui.GUI;
import ag.carnot.gui.GenericTable;
import ag.carnot.gui.GuiMapper;
import ag.carnot.gui.LabeledComponentsPanel;
*/

/**
 * @author mgille
 */
public class DefaultInterpreter extends Interpreter
      implements NamedValues
{
   private DataController controller;

   public DefaultInterpreter()
   {
      controller = new DataController();
   }

   public DataController getController()
   {
      return controller;
   }

   public void setData(Object object)
   {
      controller.clear();
   }

   public Object get(String name)
   {
      return controller.get(name);
   }

   public void set(String name, Object value)
   {
      controller.set(name, value);
   }

   public JPanel createGroupPanel(DataGroup dataGroup)
   {
      return (JPanel) createGroupComponent(dataGroup, 1);
   }

   public JComponent createGroupComponent(DataGroup dataGroup)
   {
      return createGroupComponent(dataGroup, 1);
   }

   private JComponent createGroupComponent(DataGroup dataGroup, int level)
   {
      JPanel panel = new JPanel();

      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      panel.setBorder(GUI.getEmptyPanelBorder());

      // @todo/belgium (ub): cheap hack to emulate inout mappings
      Set writeableData = new HashSet();
      Iterator allData = dataGroup.getAllData();
      while (allData.hasNext())
      {
         Data data = (Data) allData.next();
         if (!data.getReadonly())
         {
            writeableData.add(data.getID());
         }
      }

      Iterator iterator = dataGroup.getAllData();

      Box box = Box.createVerticalBox();

      LabeledComponentsPanel components = new LabeledComponentsPanel();
      GenericTable table = null;
      Vector _processedData = new Vector();
      while (iterator.hasNext())
      {
         Data data = (Data) iterator.next();
         if (_processedData.contains(data.getID()))
         {
            continue;
         }
         _processedData.add(data.getID());
         if (data instanceof TableData)
         {
            table = new GenericTable(Data.class, new String[]{"Label", "Label", "Label"}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                  new String[]{"Column1", "Column2", "Column3"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            table.setMaximumSize(new Dimension(table.getMaximumSize().width, 100));
            table.setPreferredSize(new Dimension(100, 100));
         }
         else
         {
            JComponent component = GuiMapper.getComponentForClass(data.getType());

            components.add(component, data.getLabel());

            if (component instanceof Entry)
            {
               ((Entry) component).setReadonly(!writeableData.contains(data.getID()));
               ((Entry) component).setMandatory(data.getMandatory());

               controller.add((Entry) component, data.getID());
            }
         }
      }

      components.pack();
      components.setMinimumSize(components.getPreferredSize());
      components.setMaximumSize(components.getPreferredSize());

      box.add(components);

      if (table != null)
      {
         box.add(Box.createVerticalStrut(10));
         box.add(table);
      }

      box.add(Box.createVerticalGlue());

      box.add(Box.createVerticalGlue());
      panel.add(box);
      panel.add(Box.createHorizontalStrut(10));

      iterator = dataGroup.getAllSubGroups();

      if (iterator.hasNext())
      {
         DataGroup subGroup;

         JPanel grid = new JPanel();

         //				grid.setBorder(new EtchedBorder());
         grid.setLayout(new GridBagLayout());

         iterator = dataGroup.getAllSubGroups();

         while (iterator.hasNext())
         {
            subGroup = (DataGroup) iterator.next();

            JPanel subGroupPanel = new JPanel();

            subGroupPanel.add(createGroupComponent(subGroup, level + 1));
            subGroupPanel.setBorder(new TitledBorder(new EtchedBorder(), subGroup.getLabel()));

            // Determine grid bag constraints

            GridBagConstraints constraints = new GridBagConstraints();

            constraints.fill = GridBagConstraints.BOTH;
            constraints.weightx = 100;
            constraints.weighty = 100;

            constraints.gridx = subGroup.getColumn() - 1;
            constraints.gridy = subGroup.getRow() - 1;
            constraints.gridwidth = 1;
            constraints.gridheight = 1;

            grid.add(subGroupPanel, constraints);
         }

         grid.setMaximumSize(new Dimension(grid.getPreferredSize().width,
               grid.getMaximumSize().height));

         panel.add(grid);
      }

      return panel;
   }

   public Map getValues()
   {
      return controller.getValues();
   }
}

class DataController
{
   private static final Logger trace = LogManager.getLogger(DataController.class);

   private HashMap entries = new HashMap();

   public DataController()
   {
   }

   public void add(Entry entry, String name)
   {
      entries.put(name, entry);
   }

   public void clear()
   {
      entries.clear();
   }

   public Object get(String name)
   {
      return ((Entry) entries.get(name)).getObjectValue();
   }

   public void set(String name, Object value)
   {
      ((Entry) entries.get(name)).setObjectValue(value);
   }

   public Map getValues()
   {
      HashMap result = new HashMap();
      Iterator i = entries.entrySet().iterator();
      while (i.hasNext())
      {
         Map.Entry entry = (Map.Entry) i.next();
         result.put(entry.getKey(), ((Entry) entry.getValue()).getObjectValue());
      }
      return result;
   }
}
