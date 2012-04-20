/**
 * @author Mark Gille, j.talk() GmbH
 * @version 	%I%, %G%
 */

package org.eclipse.stardust.modeling.debug.interpreter;

import javax.swing.*;

import org.eclipse.stardust.common.error.PublicException;

/**
 *
 */
public class Column extends MetadataPropertyHolder
{
   private static ImageIcon icon;

   private Class type;
   private boolean mandatory;
   private boolean readonly;
   private String label;
   private String toolTip;

   /**
    *
    */
   public Column(Class type, String label, boolean mandatory,
         boolean readonly, String toolTip)
   {
      this.type = type;
      this.mandatory = mandatory;
      this.readonly = readonly;
      this.label = label;
      this.toolTip = toolTip;
   }

   /**
    *
    */
   public Class getType()
   {
      return type;
   }

   /**
    *
    */
   public boolean getMandatory()
   {
      return mandatory;
   }

   /**
    *
    */
   public boolean getReadonly()
   {
      return readonly;
   }

   /**
    *
    */
   public String getLabel()
   {
      return label;
   }

   /**
    *
    */
   public String getToolTip()
   {
      return toolTip;
   }

   /**
    *
    */
   public String toXML()
   {
      StringBuffer buffer = new StringBuffer();

      buffer.append("<COLUMN");

      buffer.append(" id=\"");
      buffer.append("12");
      buffer.append("\"");
      buffer.append(" type=\"");
      buffer.append("" + getType());
      buffer.append("\"");

      if (getLabel() != null)
      {
         buffer.append(" label=\"");
         buffer.append(getLabel());
         buffer.append("\"");
      }

      buffer.append(" mandatory=\"");
      buffer.append("" + getMandatory());
      buffer.append("\"");
      buffer.append(" readonly=\"");
      buffer.append("" + getReadonly());
      buffer.append("\"");

      if (getToolTip() != null)
      {
         buffer.append(" tooltip=\"");
         buffer.append(getToolTip());
         buffer.append("\"");
      }

      buffer.append(">\n");
      buffer.append("</COLUMN>\n");

      return buffer.toString();
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
            icon = new ImageIcon(Data.class.getResource("images/column.gif"));
         }
         catch (Exception x)
         {
            throw new PublicException("Cannnot load resource \"images/column.gif\"");
         }
      }

      return icon;
   }
}
