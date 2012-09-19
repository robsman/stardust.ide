/*
 * $Id: Data.java 7281 2005-01-20 19:09:01Z rsauer $
 * (C) 2000 - 2005 CARNOT AG
 */
package org.eclipse.stardust.modeling.debug.interpreter;

import javax.swing.ImageIcon;

import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.common.reflect.Reflect;

/**
 * @author mgille
 * @version $Revision: 7281 $
 */
public class Data extends MetadataPropertyHolder
{
   private static ImageIcon icon;

   private DataGroup group;
   private String id;
   private String typeName;
   private short length;
   private byte row;
   private byte column;
   private boolean mandatory;
   private boolean readonly;
   private String label;
   private String toolTip;

   /**
    *
    */
   public Data(DataGroup group, String id, Class type, String label, boolean mandatory,
         boolean readonly, String toolTip)
   {
      this.id = id;
      this.group = group;
      this.typeName = type.getName();
      this.mandatory = mandatory;
      this.readonly = readonly;
      this.label = label;
      this.id = label;
      this.toolTip = toolTip;
   }

   /**
    *
    */
   public String getID()
   {
      return id;
   }

   /**
    *
    */
   public void setID(String id)
   {
      this.id = id;
   }

   /**
    *
    */
   public Class getType()
   {
      return Reflect.getClassFromClassName(typeName);
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
   public void setMandatory(boolean mandatory)
   {
      this.mandatory = mandatory;
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
   public void setReadonly(boolean readonly)
   {
      this.readonly = readonly;
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
   public void setLabel(String label)
   {
      this.label = label;
   }

   /**
    *
    */
   public short getLength()
   {
      return length;
   }

   /**
    *
    */
   public void setLength(short length)
   {
      this.length = length;
   }

   /**
    *
    */
   public byte getRow()
   {
      return row;
   }

   /**
    *
    */
   public void setRow(byte row)
   {
      this.row = row;
   }

   /**
    *
    */
   public byte getColumn()
   {
      return column;
   }

   /**
    *
    */
   public void setColumn(byte column)
   {
      this.column = column;
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
   public void setToolTip(String toolTip)
   {
      this.toolTip = toolTip;
   }

   /**
    *
    */
   public DataGroup getDataGroup()
   {
      return group;
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
            icon = new ImageIcon(Data.class.getResource("images/data.gif")); //$NON-NLS-1$
         }
         catch (Exception x)
         {
            throw new PublicException("Cannnot load resource \"images/data.gif\""); //$NON-NLS-1$
         }
      }

      return icon;
   }
}
