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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPropertyPageContributor;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;


public class DefaultOutlineProvider implements OutlineProvider
{
   AbstractModelElementPropertyPage page;
   private EStructuralFeature elementListFeature;
   private EStructuralFeature idFeature;
   private EStructuralFeature nameFeature;
   private String parentNodeId;
   private String pageClassName;
   private Class<?> pageClass;
   private IFilter filter;

   public DefaultOutlineProvider(AbstractModelElementPropertyPage page, 
         EStructuralFeature elementListFeature, EStructuralFeature idFeature, 
         EStructuralFeature nameFeature, String parentNodeId, String pageClassName)
   {
      this(page, elementListFeature, idFeature, nameFeature, parentNodeId, 
            pageClassName, null);
   }

   public DefaultOutlineProvider(AbstractModelElementPropertyPage page, 
         EStructuralFeature elementListFeature, EStructuralFeature idFeature, 
         EStructuralFeature nameFeature, String parentNodeId, Class<?> pageClass)
   {
      this(page, elementListFeature, idFeature, nameFeature, parentNodeId, 
            pageClass.getName(), null);
      this.pageClass = pageClass;
   }

   public DefaultOutlineProvider(AbstractModelElementPropertyPage page, 
         EStructuralFeature elementListFeature, EStructuralFeature idFeature, 
         EStructuralFeature nameFeature, String parentNodeId, String pageClassName,
         IFilter filter)
   {
      this.page = page;
      this.elementListFeature = elementListFeature;
      this.idFeature = idFeature;
      this.nameFeature = nameFeature;
      this.parentNodeId = parentNodeId;
      this.pageClassName = pageClassName;
      this.filter = filter;
   }

   public void addNodeTo(String parentNodeId, CarnotPreferenceNode node)
   {
      page.addNodeTo(parentNodeId, node, new EObjectLabelProvider(getEditor()));
   }

   public IAdaptable getAdaptable()
   {
      return new ModelElementAdaptable(
            new Class[] {IButtonManager.class, IModelElementNodeSymbol.class},
            new Object[] {page},
            page.getElement());
   }

   public WorkflowModelEditor getEditor()
   {
      return page.getEditor();
   }

   public EStructuralFeature getElementListFeature()
   {
      return elementListFeature;
   }

   public String getParentNodeId()
   {
      return parentNodeId;
   }

   public PreferenceManager getPreferenceManager()
   {
      return page.getPreferenceManager();
   }

   public void removeChildrenNodes(String node)
   {
      page.removePreferenceNodes(node, false);
   }

   public void removeNode(String node)
   {
      page.removePreferenceNodes(node, true);
   }

   public void updateVisuals()
   {
      page.refreshTree();
   }

   public ConfigurationElement createPageConfiguration(IModelElement element)
   {
      if (pageClass == null)
      {
         return ConfigurationElement.createPageConfiguration(
               getId(element),
               getName(element),
               getEditor().getIconFactory().getIconFor(element),
               pageClassName);
      }
      else
      {
         return ConfigurationElement.createPageConfiguration(
               getId(element),
               getName(element),
               getEditor().getIconFactory().getIconFor(element),
               pageClass);
      }
   }

   public String getName(IModelElement element)
   {
      Object value = element.eGet(nameFeature);
      return value == null ? "" : value.toString(); //$NON-NLS-1$
   }

   public String getId(IModelElement element)
   {
      Object value = element.eGet(idFeature);
      return value == null ? null : value.toString();
   }

   public IFilter getFilter()
   {
      return filter;
   }

   public void addNodesFor(String parentNodeId, IModelElement element,
         ModelElementAdaptable adaptable, int index)
   {
      List<CarnotPreferenceNode> nodes = retrievePagesFor(adaptable);
      if (nodes.isEmpty())
      {
         return;
      }
      // (fh) Filter out nodes with duplicate ids. Keep first node that has a category matching the
      // parentNodeId, or if none matches then keep first node that has a category at all, or if no
      // node has a category, keep the first one.
      if (nodes.size() > 1)
      {
         HashMap<String, CarnotPreferenceNode> map = new HashMap<String, CarnotPreferenceNode>();
         List<CarnotPreferenceNode> toRemove = new ArrayList<CarnotPreferenceNode>();
         for (CarnotPreferenceNode node : nodes)
         {
            CarnotPreferenceNode other = map.get(node.getId());
            if (other == null)
            {
               map.put(node.getId(), node);
            }
            else
            {
               if (other.category == null && node.category != null || CompareHelper.areEqual(parentNodeId, node.category))
               {
                  toRemove.add(other);
                  map.put(node.getId(), node);
               }
               else
               {
                  toRemove.add(node);
               }
            }
         }
         nodes.removeAll(toRemove);
      }
      CarnotPreferenceNode general = null;
      if (nodes.size() == 1)
      {
         general = nodes.get(0);
      }
      else
      {
         for (CarnotPreferenceNode node : nodes)
         {
            if ("_cwm_general_".equals(node.getId())) //$NON-NLS-1$
            {
               general = node;
               break;
            }
         }
         if (general == null)
         {
            general = nodes.get(0);
         }
         nodes.remove(general);
         for (CarnotPreferenceNode node : nodes)
         {
            general.add(node);
         }
      }
      general.setId(getId(element));
      general.setSortOrder(index);
      addNodeTo(parentNodeId, general);
   }

   protected List<CarnotPreferenceNode> retrievePagesFor(ModelElementAdaptable adaptable)
   {
      return CarnotPropertyPageContributor.instance().contributePropertyPages(adaptable);
   }
}
