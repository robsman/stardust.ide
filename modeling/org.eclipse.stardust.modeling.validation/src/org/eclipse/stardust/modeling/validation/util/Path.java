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
package org.eclipse.stardust.modeling.validation.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;


public class Path
{
   private List<PathEntry> segments = new ArrayList<PathEntry>();
   private String label;
   private String type;
   private int selection;
   private int selectionStart;
   private int selectionLength;
   private boolean matchesDirection;
   
   public Path(PathEntry root)
   {
      segments.add(root);
      label = computeLabel(0);
      computeType();
      setSelection(root);
   }

   public boolean matchesDirection()
   {
      return matchesDirection;
   }

   public void setMethod(String method)
   {     
      if (method.length() > 0)
      {         
         PathEntry entry = segments.get(selection);
         IAccessPathEditor editor = entry.getAccessPathEditor();
         String[] splited = editor.splitAccessPath(method);
         String simpleName = splited[0];
         long index = PathEntry.ALL;
         if (splited[0].endsWith(entry.getIndexEnd()))
         {
            int ix = splited[0].lastIndexOf(entry.getIndexStart());
            if (ix > 0)
            {
               simpleName = splited[0].substring(0, ix);
               String indexString = splited[0].substring(
                     ix + entry.getIndexStart().length(),
                     splited[0].length() - entry.getIndexEnd().length());
               try
               {
                  index = Long.parseLong(indexString);
               }
               catch (Exception ex)
               {
                  // ignored ?
               }
            }
         }
         boolean found = false;
         List<PathEntry> children = entry.getChildren();
         for (PathEntry child : children)
         {
            if (simpleName.equals(child.getId()))
            {
               found = true;
               push(child);
               if (isIndexed())
               {
                  setIndex(index);
               }
               if (splited[1] != null)
               {
                  setMethod(splited[1].trim());
               }
               break;
            }
         }
         if (!found)
         {
            PathEntry root = segments.get(0);
            throw new ValidationException(
                  MessageFormat.format(Validation_Messages.Path_InvalidSegment, new Object[] {simpleName}),
                  root.getElement());
         }
      }
   }

   public String getMethod()
   {
      return computeLabel(1);
   }

   public List<PathEntry> getChildren()
   {
      return segments.get(selection).getChildren();
   }

   public boolean isSingle()
   {
      return segments.get(selection).isSingle();
   }

   public boolean isIndexed()
   {
      return segments.get(selection).isIndexed();
   }

   public long getIndex()
   {
      return segments.get(selection).getIndex();
   }

   public void setIndex(long index)
   {
      PathEntry entry = segments.get(selection);
      entry.setIndex(index);
      if (!entry.isSingle() && entry.isIn())
      {
         for (int i = segments.size() - 1; i > selection; i--)
         {
            segments.remove(i);
         }
         computeType();
      }
      updateSelection();
   }

   public String getType()
   {
      return type;
   }

   public int getSelectionLength()
   {
      return selectionLength;
   }

   public int getSelectionStart()
   {
      return selectionStart;
   }

   public String getLabel()
   {
      return label;
   }

   public boolean isRootSelected()
   {
      return selection == 0;
   }

   public PathEntry selectPathEntry(int offset)
   {
      PathEntry entry = null;
      int pos = 0;
      for (int i = 0; i < segments.size(); i++)
      {
         entry = segments.get(i);
         int len = entry.getFullId().length();
         if (offset >= pos && offset <= pos + len)
         {
            break;
         }
         pos += len;
         if (i < segments.size() - 1)
         {
            pos += entry.getSeparator().length();
         }
      }
      setSelection(entry);
      return selection < segments.size() - 1
         ? segments.get(selection + 1) : null; 
   }

   public PathEntry pop()
   {
      selection--;
      updateSelection();
      return segments.get(selection + 1);
   }

   public PathEntry push(PathEntry entry)
   {
      selection++;
      if (selection < segments.size())
      {
         if (!entry.equals(segments.get(selection)))
         {
            for (int i = segments.size() - 1; i > selection; i--)
            {
               segments.remove(i);
            }
            segments.set(selection, entry);
         }
      }
      else
      {
         segments.add(entry);
      }
      updateSelection();
      return selection < segments.size() - 1
         ? segments.get(selection + 1) : null; 
   }

   private void computeType()
   {
      int dimension = 0;
      PathEntry entry = null;
      for (int i = 0; i < segments.size(); i++)
      {
         entry = segments.get(i);
         if (!entry.isSingle())
         {
            dimension++;
         }
      }
      type = entry.getTypeName(dimension);
      matchesDirection = entry.matchesDirection();
   }

   private String computeLabel(int start)
   {
      StringBuffer sb = new StringBuffer();
      for (int i = start; i < segments.size(); i++)
      {
         PathEntry entry = segments.get(i);
         sb.append(entry.getFullId());
         if (i < segments.size() - 1)
         {
            sb.append(entry.getSeparator());
         }
      }
      return sb.toString();
   }

   private void setSelection(PathEntry entry)
   {
      selection = segments.indexOf(entry);
      if (selection < 0)
      {
         segments.clear();
         segments.add(entry);
         selection = 0;
      }
      updateSelection();
   }

   private void updateSelection()
   {
      label = computeLabel(0);
      computeType();
      selectionStart = 0;
      for (int i = 0; i < selection; i++)
      {
         PathEntry pe = segments.get(i);
         selectionStart += pe.getFullId().length();
         selectionStart += pe.getSeparator().length();
      }
      PathEntry entry = segments.get(selection);
      selectionLength = entry.getFullId().length();
   }

   public PathEntry getSelection()
   {
      return segments.get(selection);
   }
   
   public boolean isLastItem()
   {
      return selection == segments.size() - 1;
   }

   public void cut()
   {
      for (int i = segments.size() - 1; i > selection; i--)
      {
         segments.remove(i);
      }
      updateSelection();
   }

}