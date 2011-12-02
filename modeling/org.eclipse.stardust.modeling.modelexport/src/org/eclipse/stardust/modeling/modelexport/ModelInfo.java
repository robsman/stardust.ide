/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.modelexport;

import java.util.Date;
import java.text.ParseException;

import ag.carnot.base.DateUtils;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ModelInfo
{
   private String name;
   private String id;
   private long oid;
   private Date validFrom;
   private Date validTo;
   private static final String UNSPECIFIED = ExportMessages.STR_Unspecified;
   private boolean active;

   public void setName(String name)
   {
      this.name = name;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public void setOID(String oid)
   {
      this.oid = Long.parseLong(oid);
   }

   public void setValidFrom(String validFrom)
   {
      this.validFrom = parseDate(validFrom);
   }

   public void setValidTo(String validTo)
   {
      this.validTo = parseDate(validTo);
   }

   private Date parseDate(String date)
   {
      try
      {
         if (!UNSPECIFIED.equals(date))
         {
            return DateUtils.getNoninteractiveDateFormat().parse(date);
         }
      }
      catch (ParseException e)
      {
         //e.printStackTrace();
      }
      return null;
   }

   public String getId()
   {
      return id;
   }

   public String getName()
   {
      return name;
   }

   public long getOID()
   {
      return oid;
   }

   public String getValidFromString()
   {
      return format(validFrom);
   }

   public String getValidToString()
   {
      return format(validTo);
   }

   private String format(Date date)
   {
      return date == null ? UNSPECIFIED :
            DateUtils.getNoninteractiveDateFormat().format(date);
   }

   public void setActive()
   {
      active = true;
   }

   public boolean isActive()
   {
      return active;
   }
}
