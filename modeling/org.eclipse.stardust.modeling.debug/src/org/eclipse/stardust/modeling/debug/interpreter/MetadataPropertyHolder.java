/**
 * @author Mark Gille, j.talk() GmbH
 * @version 	%I%, %G%
 */

package org.eclipse.stardust.modeling.debug.interpreter;

import java.io.Serializable;
import java.util.Hashtable;

import org.eclipse.stardust.common.Unknown;

/**
 *
 */
public abstract class MetadataPropertyHolder implements Serializable
{
   private Hashtable properties;

   /**
    *
    */
   public MetadataPropertyHolder()
   {
      properties = new Hashtable();
   }

   /**
    *
    */
   public String getString(String id)
   {
      return (String) properties.get(id);
   }

   /**
    *
    */
   public byte getByte(String id)
   {
      String string = (String) properties.get(id);

      if (string == null)
      {
         return Unknown.BYTE;
      }

      return Byte.parseByte(string);
   }

   /**
    *
    */
   public short getShort(String id)
   {
      String string = (String) properties.get(id);

      if (string == null)
      {
         return Unknown.SHORT;
      }

      return Short.parseShort(string);
   }

   /**
    *
    */
   public int getInteger(String id)
   {
      String string = (String) properties.get(id);

      if (string == null)
      {
         return Unknown.INT;
      }

      return Integer.parseInt(string);
   }

   /**
    *
    */
   public long getLong(String id)
   {
      String string = (String) properties.get(id);

      if (string == null)
      {
         return Unknown.LONG;
      }

      return Long.parseLong(string);
   }

   /**
    *
    */
   public float getFloat(String id)
   {
      String string = (String) properties.get(id);

      if (string == null)
      {
         return Unknown.FLOAT;
      }

      return Float.parseFloat(string);
   }

   /**
    *
    */
   public double getDouble(String id)
   {
      String string = (String) properties.get(id);

      if (string == null)
      {
         return Unknown.DOUBLE;
      }

      return Double.parseDouble(string);
   }

   /**
    *
    */
   public boolean getBoolean(String id)
   {
      String string = (String) properties.get(id);

      if (string == null || string.compareTo("false") == 0)
      {
         return false;
      }

      return true;
   }

   /**
    *
    */
   public void setString(String id, String value)
   {
      properties.put(id, value);
   }

   /**
    * Sets all properties at once. Overwrites existing values.
    */
   public void setProperties(Hashtable properties)
   {
      this.properties = properties;
   }
}


