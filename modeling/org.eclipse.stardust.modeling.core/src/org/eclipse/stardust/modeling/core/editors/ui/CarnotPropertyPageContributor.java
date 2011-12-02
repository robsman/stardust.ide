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

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.Category;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.core.spi.SpiPropertyPage;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.PlatformUI;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.StringUtils;

/**
 * @author fherinean
 * @version $Revision$
 */
public class CarnotPropertyPageContributor
{
   private static final String CLASS_PROPERTY = "class"; //$NON-NLS-1$

   public static  String[] CATEGORIES = {"core", "spi", "extension"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   
   private static CarnotPropertyPageContributor instance;

   public static CarnotPropertyPageContributor instance()
   {
      if (instance == null)
      {
         instance = new CarnotPropertyPageContributor();
      }
      return instance;
   }

   public boolean contributePropertyPages(PreferenceManager manager, IAdaptable adaptable)
   {
      return contributePropertyPages(manager, adaptable, null);
   }
   
   public boolean contributePropertyPages(PreferenceManager manager, IAdaptable adaptable, String category)
   {
      List<CarnotPreferenceNode> list = contributePropertyPages(adaptable, category);
      for (int i = 0; i < list.size(); i++)
      {
         CarnotPreferenceNode node = list.get(i);
         if (category == null)
         {
            manager.addToRoot(node);
         }
         else
         {
            manager.addTo(category, node);
         }
      }
      return !list.isEmpty();
   }
   
   public List<CarnotPreferenceNode> contributePropertyPages(IAdaptable adaptable)
   {
      return contributePropertyPages(adaptable, null);
   }
   
   public List<CarnotPreferenceNode> contributePropertyPages(IAdaptable adaptable, String category)
   {
      // prepare filters
      IPerspectiveDescriptor perspective = PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow().getActivePage().getPerspective();
      Map<String, String> filters = CollectionUtils.newMap();
      if (perspective != null)
      {
         filters.put("perspectiveType", perspective.getId()); //$NON-NLS-1$
      }
      
      // collect and sort elements
      EObject element = getModelElement(adaptable);
      if (element == null)
      {
         element = (EObject) adaptable.getAdapter(EObject.class);
      }
      List<IConfigurationElement> sorted = CollectionUtils.newList();
      IConfigurationElement[] extensions = Platform.getExtensionRegistry()
            .getConfigurationElementsFor("org.eclipse.ui.propertyPages"); //$NON-NLS-1$
      for (IConfigurationElement cfg : extensions)
      {
         String cls = cfg.getAttribute(CLASS_PROPERTY);
         if (!Category.class.getName().equals(cls) &&
            (category == null || category.equals(cfg.getAttribute("category"))) && //$NON-NLS-1$
             SpiExtensionRegistry.isMatchingElement(element, SpiConstants.OBJECT_CLASS, filters, cfg))
         {
            sorted.add(cfg);
         }
      }
      Collections.sort(sorted, new Comparator<IConfigurationElement>()
      {
         public int compare(IConfigurationElement c1, IConfigurationElement c2)
         {
            String cat1 = c1.getAttribute(SpiConstants.CATEGORY);
            String cat2 = c2.getAttribute(SpiConstants.CATEGORY);
            
            if (StringUtils.isEmpty(cat2))
            {
               return -1;
            }
            if (StringUtils.isEmpty(cat1))
            {
               return 1;
            }

            for (int i = 0; i < CATEGORIES.length; i++)
            {
               if (CATEGORIES[i].equals(cat1))
               {
                  return -1;
               }
               if (CATEGORIES[i].equals(cat2))
               {
                  return 1;
               }
            }
            
            return cat1.compareTo(cat2);
         }
      });
      
      // find children and remove from root
      Map<IConfigurationElement, List<IConfigurationElement>> children = CollectionUtils.newMap();
      for (int i = 0; i < sorted.size(); i++)
      {
         IConfigurationElement c1 = sorted.get(i);
         String cat1 = c1.getAttribute(SpiConstants.CATEGORY);
         if (!StringUtils.isEmpty(cat1))
         {
            for (int j = 0; j < sorted.size(); j++)
            {
               // do not allow a page to be a child of herself
               if (j == i)
               {
                  continue;
               }
               IConfigurationElement c2 = sorted.get(j);
               String id2 = c2.getAttribute(SpiConstants.ID);
               if (cat1.equals(id2))
               {
                  List<IConfigurationElement> list = children.get(c2);
                  if (list == null)
                  {
                     list = CollectionUtils.newList();
                     children.put(c2, list);
                  }
                  list.add(c1);
               }
            }
         }
      }
      Collection<List<IConfigurationElement>> values = children.values();
      for (List<IConfigurationElement> list : values)
      {
         sorted.removeAll(list);
      }

      // add children nodes
      List<CarnotPreferenceNode> result = CollectionUtils.newList();
      for (int i = 0; i < sorted.size(); i++)
      {
         IConfigurationElement c1 = sorted.get(i);
         CarnotPreferenceNode node = new CarnotPreferenceNode(
               new ConfigurationElement(c1),
               adaptable, CLASS_PROPERTY, i); 
         result.add(node);
         addChildren(node, adaptable, c1, children);
      }
      return result;
   }

   private void addChildren(CarnotPreferenceNode parent, IAdaptable adaptable,
         IConfigurationElement cfg, Map<IConfigurationElement, List<IConfigurationElement>> children)
   {
      List<IConfigurationElement> list = children.get(cfg);
      if (list != null)
      {
         for (int i = 0; i < list.size(); i++)
         {
            IConfigurationElement c1 = list.get(i);
            CarnotPreferenceNode node = new CarnotPreferenceNode(
                  new ConfigurationElement(c1),
                  adaptable, CLASS_PROPERTY, i); 
            parent.add(node);
            addChildren(node, adaptable, c1, children);
         }
      }
   }

   public boolean isApplicableTo(IAdaptable adaptable)
   {
      if (!(adaptable instanceof EditPart))
      {
         return false;
      }
      EObject element = getModelElement(adaptable);
      if (element == null)
      {
         element = (EObject) adaptable.getAdapter(EObject.class);
      }
      IPerspectiveDescriptor perspective = PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow().getActivePage().getPerspective();
      Map<String, String> filters = CollectionUtils.newMap();
      if (perspective != null)
      {
         filters.put("perspectiveType", perspective.getId()); //$NON-NLS-1$
      }
      IConfigurationElement[] extensions = Platform.getExtensionRegistry()
            .getConfigurationElementsFor("org.eclipse.ui.propertyPages"); //$NON-NLS-1$
      for (int i = 0; i < extensions.length; i++)
      {
         if (SpiExtensionRegistry.isMatchingElement(element, SpiConstants.OBJECT_CLASS, filters, extensions[i]))
         {
            return true;
         }
      }
      return false;
   }

   protected IModelElement getModelElement(IAdaptable adaptable)
   {
      IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) adaptable
            .getAdapter(IModelElementNodeSymbol.class);
      if (symbol != null && symbol.getModelElement() == null) {
    	  return (IModelElement) adaptable.getAdapter(IModelElement.class);
      }
      return (IModelElement) (symbol == null
            ? adaptable.getAdapter(IModelElement.class)
            : symbol.getModelElement());
   }

   public boolean contributeSpiPropertyPages(final ModelElementPropertyDialog dialog,
         String extensionPointId, String extensionId, IAdaptable element)
   {
      Map<String, IConfigurationElement> extensions = SpiExtensionRegistry.instance().getExtensions(extensionPointId);
      IConfigurationElement template = (IConfigurationElement) extensions.get(extensionId);
      PreferenceManager manager = dialog.getPreferenceManager();
      if (template != null && manager.find(ModelElementPropertyDialog.convertId(
            template.getAttribute(SpiConstants.ID))) == null)
      {
         final ConfigurationElement cfgTemplate = new ConfigurationElement(template);
         CarnotPreferenceNode node = new CarnotPreferenceNode(cfgTemplate, element,
               CarnotPreferenceNode.SPI_ELEMENT)
         {
            public void createPage()
            {
               try
               {
                  IWorkbenchPropertyPage page = new SpiPropertyPage(cfgTemplate);
                  internalSetPage(page);
               }
               catch (CoreException e)
               {
                  ErrorDialog.openError(dialog.getShell(), Diagram_Messages.ERROR_DIA_EXC, e.getMessage(),
                        e.getStatus()); //$NON-NLS-1$
               }
            }
         };
         dialog.addNodeTo(null, node, null);
         return true;
      }
      return false;
   }
}
