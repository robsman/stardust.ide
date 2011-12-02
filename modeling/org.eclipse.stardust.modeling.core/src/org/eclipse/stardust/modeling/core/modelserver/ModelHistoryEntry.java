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
package org.eclipse.stardust.modeling.core.modelserver;

import java.util.Date;
import java.util.List;

public class ModelHistoryEntry
{
   private String revision;
   private Date timestamp;
   private String author;
   private String comment;
   private List /*<ElementReference>*/ changedElementReferences;
   
   public ModelHistoryEntry(String revision, Date timestamp, String author, String comment, List /*<String>*/ changedElementReferences)
   {
      this.revision = revision;
      this.timestamp = timestamp;
      this.author = author;
      this.comment = comment;
      this.changedElementReferences = changedElementReferences;
   }
   
   public String getRevision()
   {
      return revision;
   }
   
   public Date getTimestamp()
   {
      return timestamp;
   }
   
   public String getAuthor()
   {
      return author;
   }
   
   public String getComment()
   {
      return comment;
   }

   public List /*<ElementReference>*/ getChangedElementReferences()
   {
      return changedElementReferences;
   }

}
