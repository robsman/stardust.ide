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
package org.eclipse.stardust.modeling.core.editors.ui;

import org.eclipse.emf.common.util.AbstractEnumerator;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * @author fherinean
 * @version $Revision$
 */
public class EEnumeratorLabelProvider
   extends LabelProvider
{
   private String[] labels;

   public EEnumeratorLabelProvider(String[] labels)
   {
      this.labels = labels;
   }

   public String getText(Object element)
   {
      if (element instanceof AbstractEnumerator)
      {
         int value = ((AbstractEnumerator) element).getValue();
         if (labels != null && value >= 0 && value < labels.length && labels[value] != null)
         {
            return labels[value];
         }
      }
      // fall back to default implementation
      return super.getText(element);
   }
}
