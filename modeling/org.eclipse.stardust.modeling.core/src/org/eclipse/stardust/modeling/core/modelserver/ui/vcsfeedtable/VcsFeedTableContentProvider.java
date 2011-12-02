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
package org.eclipse.stardust.modeling.core.modelserver.ui.vcsfeedtable;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.modeling.core.modelserver.ModelHistoryEntry;


public class VcsFeedTableContentProvider implements IStructuredContentProvider
{

   public Object[] getElements(Object inputElement)
   {
      VcsFeedTableModel processDefinitionTreeModel = (VcsFeedTableModel) inputElement;
      List modelHistoryEntries = processDefinitionTreeModel.getModelHistoryEntries();
      return modelHistoryEntries.toArray(new ModelHistoryEntry[modelHistoryEntries.size()]);
   }

   public void dispose()
   {
      // ignore
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
   }

}
