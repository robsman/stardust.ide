/*
 * $Id: DataGroup.java 7281 2005-01-20 19:09:01Z rsauer $
 * (C) 2000 - 2005 CARNOT AG
 */
package org.eclipse.stardust.modeling.debug.interpreter;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;

import org.eclipse.stardust.common.error.PublicException;

/**
 * @author mgille
 * @version $Revision: 7281 $
 */
public class DataGroup extends MetadataPropertyHolder
{
   private static ImageIcon icon;

   private DataGroup superGroup;
   private Vector data;
   private Vector subGroups;
   private String id;
   private String label;
   private String type;
   private byte row;
   private byte column;

   /**
    *
    */
   public DataGroup(String label)
   {
      this.label = label;

      data = new Vector();
      subGroups = new Vector();
   }

   /**
    *
    */
   public Data createData(Class type, String label, boolean mandatory,
         boolean readonly)
   {
      return createData(type, label, mandatory, readonly, null);
   }

   /**
    *
    */
   public Data createData(Class type, String label, boolean mandatory,
         boolean readonly, String toolTip)
   {
      Data newData = new Data(this, null, type, label, mandatory, readonly, toolTip);

      data.add(newData);

      return newData;
   }

   /**
    *
    */
   public TableData createTableData(String label, boolean mandatory,
         boolean readonly)
   {
      return createTableData(label, mandatory, readonly, null);
   }

   /**
    *
    */
   public TableData createTableData(String label, boolean mandatory,
         boolean readonly, String toolTip)
   {
      TableData newData = new TableData(this, label, mandatory, readonly, toolTip);

      data.add(newData);

      return newData;
   }

   /**
    *
    */
   public Iterator getAllData()
   {
      return data.iterator();
   }

   /**
    *
    */
   public DataGroup createSubGroup(String label)
   {
      DataGroup subGroup = new DataGroup(label);

      subGroups.add(subGroup);
      subGroup.superGroup = this;

      return subGroup;
   }

   /**
    *
    */
   public DataGroup getSuperGroup()
   {
      return superGroup;
   }

   /**
    *
    */
   public Iterator getAllSubGroups()
   {
      return subGroups.iterator();
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
   public String getType()
   {
      return type;
   }

   /**
    *
    */
   public void setType(String type)
   {
      this.type = type;
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
    *	@return The icon used for displaying data objects in the GUI.
    */
   public ImageIcon getIcon()
   {
      if (icon == null)
      {
         try
         {
            icon = new ImageIcon(Data.class.getResource("images/data_group.gif"));
         }
         catch (Exception x)
         {
            throw new PublicException("Cannnot load resource \"images/data_group.gif\"");
         }
      }

      return icon;
   }
}


