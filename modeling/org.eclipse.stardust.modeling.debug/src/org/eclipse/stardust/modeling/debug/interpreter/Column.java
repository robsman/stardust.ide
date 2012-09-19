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

      buffer.append("<COLUMN"); //$NON-NLS-1$

      buffer.append(" id=\""); //$NON-NLS-1$
      buffer.append("12"); //$NON-NLS-1$
      buffer.append("\""); //$NON-NLS-1$
      buffer.append(" type=\""); //$NON-NLS-1$
      buffer.append("" + getType()); //$NON-NLS-1$
      buffer.append("\""); //$NON-NLS-1$

      if (getLabel() != null)
      {
         buffer.append(" label=\""); //$NON-NLS-1$
         buffer.append(getLabel());
         buffer.append("\""); //$NON-NLS-1$
      }

      buffer.append(" mandatory=\""); //$NON-NLS-1$
      buffer.append("" + getMandatory()); //$NON-NLS-1$
      buffer.append("\""); //$NON-NLS-1$
      buffer.append(" readonly=\""); //$NON-NLS-1$
      buffer.append("" + getReadonly()); //$NON-NLS-1$
      buffer.append("\""); //$NON-NLS-1$

      if (getToolTip() != null)
      {
         buffer.append(" tooltip=\""); //$NON-NLS-1$
         buffer.append(getToolTip());
         buffer.append("\""); //$NON-NLS-1$
      }

      buffer.append(">\n"); //$NON-NLS-1$
      buffer.append("</COLUMN>\n"); //$NON-NLS-1$

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
            icon = new ImageIcon(Data.class.getResource("images/column.gif")); //$NON-NLS-1$
         }
         catch (Exception x)
         {
            throw new PublicException("Cannnot load resource \"images/column.gif\""); //$NON-NLS-1$
         }
      }

      return icon;
   }
}
