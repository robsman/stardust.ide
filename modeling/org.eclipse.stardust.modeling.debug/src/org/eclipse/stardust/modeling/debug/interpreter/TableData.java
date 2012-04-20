/**
 * @author Mark Gille, j.talk() GmbH
 * @version 	%I%, %G%
 */

package org.eclipse.stardust.modeling.debug.interpreter;

import java.util.Vector;

import javax.swing.*;

import org.eclipse.stardust.common.error.PublicException;

/**
 *
 */
public class TableData extends Data
{
   private static ImageIcon icon;

   private Vector columns;

   /**
    *
    */
   public TableData(DataGroup group, String label, boolean mandatory,
         boolean readonly, String toolTip)
   {
      super(group, null, null, label, mandatory, readonly, toolTip);

      columns = new Vector();
   }

   /**
    *
    */
   public Column createColumn(Class type, String label, boolean mandatory,
         boolean readonly)
   {
      return createColumn(type, label, mandatory, readonly, null);
   }

   /**
    *
    */
   public Column createColumn(Class type, String label, boolean mandatory,
         boolean readonly, String toolTip)
   {
      Column column = new Column(type, label, mandatory,
            readonly, toolTip);
      columns.add(column);

      return column;
   }

   /**
    *
    */
   public java.util.Iterator getAllColumns()
   {
      return columns.iterator();
   }

   /**
    *
    */
   public int getColumnsCount()
   {
      return columns.size();
   }

   /**
    *	@return The icon used for displaying data objects in the GUI.
    */
   public ImageIcon getIcon()
   {
      if (icon == null)
      {
         try
         {
            icon = new ImageIcon(Data.class.getResource("images/table.gif"));
         }
         catch (Exception x)
         {
            throw new PublicException("Cannnot load resource \"images/table.gif\"");
         }
      }

      return icon;
   }
}
