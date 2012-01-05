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
package org.eclipse.stardust.modeling.repository.file.search;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.file.File_Messages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SearchDialog extends Dialog
{
   private Text searchText;
   private String searchString;

   List<IObjectDescriptor> matchedElements = CollectionUtils.newList();

   public SearchDialog(Shell parentShell)
   {
      super(parentShell);
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite control = (Composite) super.createDialogArea(parent);
      Composite composite = FormBuilder.createComposite(control, 2);
      FormBuilder.createLabel(composite, File_Messages.LBL_FIND);
      searchText = FormBuilder.createText(composite);
      return control;
   }

   protected Control createContents(Composite parent)
   {
      Control control = super.createContents(parent);
      getButton(IDialogConstants.OK_ID).setText(File_Messages.BUT_TXT_SEARCH);
      return control;
   }

   protected void configureShell(Shell newShell)
   {
      super.configureShell(newShell);
      newShell.setText(File_Messages.TXT_SEARCH);
   }

   protected void okPressed()
   {
      searchString = searchText.getText().trim();
      super.okPressed();
   }

   public String getSearchString()
   {
      return searchString;
   }

   public List<IObjectDescriptor> searchEntries(List<IObjectDescriptor> cache)
   {
      IObjectDescriptor[] descriptors = cache.toArray(new IObjectDescriptor[cache.size()]);
      
      String[] searchStrings = searchString.split(" "); //$NON-NLS-1$
      if (searchStrings.length > 1)
      {
         for (int i = 0; i < searchStrings.length; i++)
         {
            search(searchStrings[i].toLowerCase(), descriptors);
         }
      }
      else
      {
         searchString = searchString.toLowerCase();
         search(searchString, descriptors);
      }   
      return matchedElements.size() == 0 ? null : matchedElements;      
   }
   
   private void search(String searchString, IObjectDescriptor[] descriptors)
   {
      for (int i = 0; i < descriptors.length; i++) 
      {
         IObjectDescriptor entry = descriptors[i];
         if (normalize(entry.getDescription()).indexOf(searchString) >= 0
            // || 
               || normalize(entry.getLabel()).indexOf(searchString) >= 0)
         {
            matchedElements.add(entry);            
         }
         else if (entry instanceof IObjectReference
               && normalize(((IObjectReference) entry).getId()).indexOf(searchString) >= 0)
         {
            matchedElements.add(entry);            
         }
         if (entry.hasChildren())
         {
            search(searchString, entry.getChildren());
         }
      }
   }

   private String normalize(String description)
   {
      String result = description == null ? "" : description; //$NON-NLS-1$
      return result.toLowerCase();
   }
}
