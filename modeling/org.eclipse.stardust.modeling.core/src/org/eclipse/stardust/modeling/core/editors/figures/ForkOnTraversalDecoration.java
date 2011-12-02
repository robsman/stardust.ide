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
package org.eclipse.stardust.modeling.core.editors.figures;

import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.swt.graphics.Image;


/**
 * @author rsauer
 * @version $Revision: 12523 $
 */
public class ForkOnTraversalDecoration extends ImageFigure implements RotatableDecoration
{
   private Point location = new Point();
   private Transform transform = new Transform();

   public ForkOnTraversalDecoration()
   {
      super();
      Image image = DiagramPlugin.getImage("icons/full/obj16/forkOnTraversal.gif"); //$NON-NLS-1$
      setImage(image);
      setSize(getPreferredSize());
   }

   public void setLocation(Point p)
   {
      location.setLocation(p);
      transform.setTranslation(p.x, p.y);

      applyRotation();
   }

   public void setReferencePoint(Point ref)
   {
      Point pt = Point.SINGLETON;
      pt.setLocation(ref);
      pt.negate().translate(location);
      
      transform.setRotation(Math.atan2(pt.y, pt.x));
      
      applyRotation();
   }

   private void applyRotation()
   {
      Dimension size = getSize().getNegated();
      Point centerDislocation = new Point(size.width / 2, 0);
      
      Point center = transform.getTransformed(centerDislocation);
      super.setLocation(center.translate(size.width / 2, size.height / 2));
   }
}