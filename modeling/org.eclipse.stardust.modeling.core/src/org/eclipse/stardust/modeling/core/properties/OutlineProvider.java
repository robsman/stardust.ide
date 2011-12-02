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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;


/**
 * @author fherinean
 * @version $Revision$
 */
public interface OutlineProvider
{
   EStructuralFeature getElementListFeature();

   void updateVisuals();

   IAdaptable getAdaptable();

   PreferenceManager getPreferenceManager();

   public String getId(IModelElement element);

   String getParentNodeId();

   void addNodeTo(String parentNodeId, CarnotPreferenceNode node);

   void removeNode(String node);

   void removeChildrenNodes(String node);

   ConfigurationElement createPageConfiguration(IModelElement element);

   IFilter getFilter();

   void addNodesFor(String parentNodeId, IModelElement element, ModelElementAdaptable adaptable, int index);
}
