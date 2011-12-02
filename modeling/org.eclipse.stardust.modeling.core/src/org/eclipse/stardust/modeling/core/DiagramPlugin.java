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
package org.eclipse.stardust.modeling.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.BpmUiConstants;
import org.eclipse.stardust.modeling.common.ui.ICWMDebugTarget;
import org.eclipse.stardust.modeling.common.ui.jface.IImageManager;
import org.eclipse.stardust.modeling.common.ui.jface.ImageManager;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import ag.carnot.base.StringUtils;

/**
 * The main plugin class to be used in the desktop.
 */
public class DiagramPlugin extends AbstractUIPlugin
{
   // The shared instance.
   private static DiagramPlugin plugin;

   private IImageManager imageManager;

   private Map externalImageManagers = new HashMap();
   
   public static final String CARNOT_WORKFLOW_MODEL_EDITOR_ID = "org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor"; //$NON-NLS-1$

   public static final String CONTEXT_MENU_ACTION_EXTENSION_POINT = "org.eclipse.stardust.modeling.core.contextMenuAction"; //$NON-NLS-1$

   public static final String EP_ATTR_ID = "id"; //$NON-NLS-1$

   public static final String EP_ATTR_ACTION_CLASS = "actionClass"; //$NON-NLS-1$

   public static final String EP_ATTR_TARGET_ELEMENT_TYPE = "targetElementType"; //$NON-NLS-1$

   public static final String EP_ATTR_TARGET_SYMBOL_TYPE = "targetSymbolType"; //$NON-NLS-1$

   public static final String EP_ATTR_TARGET_EOBJECT_TYPE = "targetEObjectType"; //$NON-NLS-1$

   public static final String EP_ATTR_GROUP = "group"; //$NON-NLS-1$

   /**
    * The constructor.
    */
   public DiagramPlugin()
   {
      plugin = this;
   }

   /**
    * This method is called upon plug-in activation
    */
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      CarnotAdapterFactory.registerAdapterFactories();
   }

   /**
    * This method is called when the plug-in is stopped
    */
   public void stop(BundleContext context) throws Exception
   {
      super.stop(context);
      CarnotAdapterFactory.unregisterAdapterFactories();
      SpiExtensionRegistry.stop();
      plugin = null;    // todo: why ???
   }

   /**
    * Returns the shared instance.
    */
   public static DiagramPlugin getDefault()
   {
      return plugin;
   }
   
   public IImageManager getImageManager()
   {
      if (null == imageManager)
      {
         imageManager = new SpiAwareImageManager();
      }
      
      return imageManager;
   }
   
   public IImageManager getImageManager(String bundleId)
   {
      IImageManager result = null;
      
      if (getBundle().getSymbolicName().equals(bundleId))
      {
         result = getImageManager();
      }
      else
      {
         result = (IImageManager) externalImageManagers.get(bundleId);
         if (null == result)
         {
            result = new ImageManager(bundleId);
            externalImageManagers.put(bundleId, result);
         }
      }
      
      return result;
   }
   
   public void setImageManager(IImageManager imageManager)
   {
      this.imageManager = imageManager;
   }

   /**
    * Returns an image descriptor for the image file at the given plug-in relative path.
    *
    * @param path
    *           the path
    * @return the image descriptor
    */
   public static ImageDescriptor getImageDescriptor(String path)
   {
      return getDefault().getImageManager().getImageDescriptor(path);
   }

   /**
    * Returns an image descriptor for the image file at the given plug-in relative path.
    *
    * @param path
    *           the path
    * @return the image descriptor
    */
   public static ImageDescriptor getImageDescriptor(IConfigurationElement element)
   {
      return getDefault().getImageManager(element.getNamespace()).getImageDescriptor(
            element.getAttribute(SpiConstants.ICON));
   }

   public static Image getImage(String path)
   {
      Image image = null;

      if ( !StringUtils.isEmpty(path) && path.startsWith("{")) //$NON-NLS-1$
      {
         Iterator qualifiedPath = StringUtils.split(path, "}"); //$NON-NLS-1$
         String bundleId = null;
         String imagePath = null;
         
         if (qualifiedPath.hasNext())
         {
            bundleId = ((String) qualifiedPath.next()).substring(1);
         }
         if (qualifiedPath.hasNext())
         {
            imagePath = (String) qualifiedPath.next();
         }
         if ( !StringUtils.isEmpty(bundleId) && !StringUtils.isEmpty(imagePath))
         {
            image = getDefault().getImageManager(bundleId).getImage(imagePath);
         }
      }
      
      return (null != image) ? image : getDefault().getImageManager().getImage(path);
   }

   public static Image getImage(String path, int style)
   {
      Image image = null;

      if ( !StringUtils.isEmpty(path) && path.startsWith("{")) //$NON-NLS-1$
      {
         Iterator<String> qualifiedPath = StringUtils.split(path, "}"); //$NON-NLS-1$
         String bundleId = null;
         String imagePath = null;
         
         if (qualifiedPath.hasNext())
         {
            bundleId = qualifiedPath.next().substring(1);
         }
         if (qualifiedPath.hasNext())
         {
            imagePath = qualifiedPath.next();
         }
         if (!StringUtils.isEmpty(bundleId) && !StringUtils.isEmpty(imagePath))
         {
            image = getDefault().getImageManager(bundleId).getIcon(imagePath, style);
         }
      }
      
      return image != null ? image : getDefault().getImageManager().getImage(path);
   }

   public static void warn(String message)
   {
      plugin.getLog().log(new Status(IStatus.WARNING, CarnotConstants.DIAGRAM_PLUGIN_ID, 0, message, null));
   }

   public static void log(IStatus status)
   {
      plugin.getLog().log(status);
   }

   public static boolean isVerticalModelling(DiagramType diagram)
   {
      OrientationType direction = diagram.getOrientation();
      return direction == null || OrientationType.VERTICAL_LITERAL.equals(direction);
            
   }
   
   public static boolean isVerticalModelling(IGraphicalObject symbol)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram(symbol);
      return isVerticalModelling(diagram);
   }

   public static String getCurrentPerspectiveId()
   {
      try
      {
         IWorkbench workbench = PlatformUI.getWorkbench();
         if ((null != workbench.getActiveWorkbenchWindow())
               && (null != workbench.getActiveWorkbenchWindow().getActivePage()))
         {
            IPerspectiveDescriptor perspective = workbench.getActiveWorkbenchWindow()
                  .getActivePage()
                  .getPerspective();
            return perspective.getId();
         }
      }
      catch (Exception ex)
      {
         // no active perspective yet
      }
      return null;
   }

   public static boolean isBusinessView(WorkflowModelEditor editor)
   {
      if (editor != null && BpmUiConstants.CWD_PERSPECTIVE_ID.equals(
            getCurrentPerspectiveId()))
      {
         ILaunchManager lm = DebugPlugin.getDefault().getLaunchManager();
         IDebugTarget[] targets = lm.getDebugTargets();
         for (int i = 0; i < targets.length; i++)
         {
            if (targets[i] instanceof ICWMDebugTarget)
            {
               ICWMDebugTarget cwmTarget = (ICWMDebugTarget) targets[i];
               if (!cwmTarget.isTerminated() && cwmTarget.getEditor().equals(editor))
               {
                  PlatformUI.getPreferenceStore().setValue(
                     "org.eclipse.stardust.modeling.core.analystView", cwmTarget.isAnalystMode()); //$NON-NLS-1$
                  break;
               }
            }
         }
      }
      
      // TODO: Consider to refactor the logic of this method to an explicit interface.  
      // Quick fix: This prevents exceptions when diagrams are exported by project tate.
      try
      {
         return PlatformUI.getPreferenceStore().getBoolean(
               "org.eclipse.stardust.modeling.core.analystView"); //$NON-NLS-1$
      }
      catch (RuntimeException e)
      {  
         return false;
      }
   }

   public static String getViewAsPerspectiveId(WorkflowModelEditor editor)
   {
      return DiagramPlugin.isBusinessView(editor) ? BpmUiConstants.CWB_PERSPECTIVE_ID
            : DiagramPlugin.getCurrentPerspectiveId();
   }

   public static boolean isBusinessPerspective()
   {
      return BpmUiConstants.CWB_PERSPECTIVE_ID.equals(getCurrentPerspectiveId());
   }
   
   private class SpiAwareImageManager extends ImageManager
   {
      public SpiAwareImageManager()
      {
         super(DiagramPlugin.this);
      }

      public ImageDescriptor getImageDescriptor(String path)
      {
         ImageDescriptor result = null;

         if ( !StringUtils.isEmpty(path) && path.startsWith("{")) //$NON-NLS-1$
         {
            Iterator qualifiedPath = StringUtils.split(path, "}"); //$NON-NLS-1$
            String bundleId = null;
            String imagePath = null;
            
            if (qualifiedPath.hasNext())
            {
               bundleId = ((String) qualifiedPath.next()).substring(1);
            }
            if (qualifiedPath.hasNext())
            {
               imagePath = (String) qualifiedPath.next();
            }
            if ( !StringUtils.isEmpty(bundleId) && !StringUtils.isEmpty(imagePath))
            {
               result = getDefault().getImageManager(bundleId).getImageDescriptor(imagePath);
            }
         }

         if (null == result)
         {
            result = super.getImageDescriptor(path);
         }
         
         return result;
      }

   }
}
