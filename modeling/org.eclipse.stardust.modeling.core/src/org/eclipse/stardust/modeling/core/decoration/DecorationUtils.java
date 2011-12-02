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
package org.eclipse.stardust.modeling.core.decoration;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.gef.EditPart;

/**
 * @author rsauer
 * @version $Revision$
 */
public class DecorationUtils
{
   public static void applyDecoration(IDecorationProvider decoration, List children)
   {
      // decorate children
      for (Iterator i = children.iterator(); i.hasNext();)
      {
         Object child = i.next();
         if (child instanceof IDecoratablePart)
         {
            ((IDecoratablePart) child).applyDecoration(decoration);
         }
      }
   }

   public static void removeDecoration(IDecorationProvider decoration, List children)
   {
      // remove decoration from children
      for (Iterator i = children.iterator(); i.hasNext();)
      {
         Object child = i.next();
         if (child instanceof IDecoratablePart)
         {
            ((IDecoratablePart) child).removeDecoration(decoration);
         }
      }
   }

   public static void applyDecorations(EditPart part, Set decorations)
   {
      if (part instanceof IDecoratablePart)
      {
         for (Iterator i = decorations.iterator(); i.hasNext();)
         {
            ((IDecoratablePart) part).applyDecoration((IDecorationProvider) i.next());
         }
      }
   }

   public static void removeDecorations(EditPart part, Set decorations)
   {
      if (part instanceof IDecoratablePart)
      {
         for (Iterator i = decorations.iterator(); i.hasNext();)
         {
            ((IDecoratablePart) part).removeDecoration((IDecorationProvider) i.next());
         }
      }
   }
}
