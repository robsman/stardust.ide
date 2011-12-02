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
package org.eclipse.stardust.modeling.core.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtWidgetAdapter;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;


/**
 * @author fherinean
 * @version $Revision$
 */
public class PreferenceNodeBinding extends SwtWidgetAdapter
{
   private final EObject model;

   private CarnotPreferenceNode node;

   private EObjectLabelProvider labelProvider;

   private TreeViewer viewer;

   public PreferenceNodeBinding(TreeViewer viewer, EObject model,
         CarnotPreferenceNode node, EObjectLabelProvider labelProvider)
   {
      super(viewer.getControl());

      this.model = model;

      this.viewer = viewer;
      this.node = node;
      this.labelProvider = labelProvider;
   }

   public void updateControl(Object value)
   {
      if (model instanceof IIdentifiableElement)
      {
         node.setId(((IIdentifiableElement) model).getId());
      }
      node.setLabelImage(labelProvider.getImage(model));
      String text = labelProvider.getText("name", model); //$NON-NLS-1$
      if (node.getPage() != null)
      {
         node.getPage().setTitle(text);
      }
      node.setLabelText(text);
      viewer.refresh(node, true);
   }
}
