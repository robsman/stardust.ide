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
package org.eclipse.stardust.modeling.common.ui;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.common.BpmCommonActivator;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import ag.carnot.workflow.model.Modules;

/**
 * The activator class controls the plug-in life cycle
 */
public class BpmUiActivator extends Plugin
{
   private static final Logger trace = LogManager.getLogger(BpmUiActivator.class);
   
   // The plug-in ID
   public static final String PLUGIN_ID = "org.eclipse.stardust.modeling.common.ui"; //$NON-NLS-1$

   // The shared instance
   private static BpmUiActivator plugin;

   private String moduleError;

   private final IPerspectiveListener perspectiveListener = new PerspectiveAdapter()
   {
      public void perspectiveChanged(IWorkbenchPage page,
            IPerspectiveDescriptor perspective, String changeId)
      {
         updateVisuals(page, perspective);
      }

      public void perspectiveActivated(IWorkbenchPage page,
            IPerspectiveDescriptor perspective)
      {
         updateVisuals(page, perspective);
      }

      private void updateVisuals(IWorkbenchPage page, IPerspectiveDescriptor perspective)
      {
         IPreferenceStore store = PlatformUI.getPreferenceStore();
         if (!"ag.carnot.workflow.modeler.debuggingPerspective".equals( //$NON-NLS-1$
               perspective.getId()))
         {
            store.setValue("org.eclipse.stardust.modeling.core.analystView", //$NON-NLS-1$
                  "ag.carnot.workflow.modeler.businessModelingPerspective".equals( //$NON-NLS-1$
                        perspective.getId()));
         }
         updateEditors(page);
      }
   };

   /**
    * The constructor
    */
   public BpmUiActivator()
   {
      plugin = this;

      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_AUTO_VALIDATION,
            BpmProjectNature.DEFAULT_PREFERENCE_AUTO_VALIDATION);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION,
            BpmProjectNature.DEFAULT_PREFERENCE_AUTO_ID_GENERATION);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_AUTO_SUBPROCESS_NAME_GENERATION,
            BpmProjectNature.DEFAULT_PREFERENCE_AUTO_SUBPROCESS_NAME_GENERATION);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_ALWAYS_SWITCH_ACTIVITY_TYPE,
            BpmProjectNature.DEFAULT_PREFERENCE_ALWAYS_SWITCH_ACTIVITY_TYPE);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_NEVER_SWITCH_ACTIVITY_TYPE,
            BpmProjectNature.DEFAULT_PREFERENCE_NEVER_SWITCH_ACTIVITY_TYPE);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE,
            BpmProjectNature.DEFAULT_PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_MODELING_DIRECTION,
            BpmProjectNature.DEFAULT_PREFERENCE_MODELING_DIRECTION);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_CLASSIC_MODE,
            BpmProjectNature.DEFAULT_PREFERENCE_CLASSIC_MODE);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_VIEW_FORK_ON_TRAVERSAL_MODE,
            BpmProjectNature.DEFAULT_PREFERENCE_VIEW_FORK_ON_TRAVERSAL_MODE);      
      // snapGrid
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_SNAP_GRID_MODE,
            BpmProjectNature.DEFAULT_PREFERENCE_SNAP_GRID_MODE);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_SNAP_GRID_PIXEL,
            BpmProjectNature.DEFAULT_PREFERENCE_SNAP_GRID_PIXEL);            
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_VISIBLE_GRID_FACTOR,
            BpmProjectNature.DEFAULT_PREFERENCE_VISIBLE_GRID_FACTOR);     
      
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_ONE_SYMBOL_GRID,
            BpmProjectNature.DEFAULT_PREFERENCE_DISTRIBUTE_ONE_SYMBOL_GRID);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_ALL_SYMBOLS_GRID,
            BpmProjectNature.DEFAULT_PREFERENCE_DISTRIBUTE_ALL_SYMBOLS_GRID);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_PROMPT_GRID,
            BpmProjectNature.DEFAULT_PREFERENCE_DISTRIBUTE_PROMPT_GRID);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_REPORT_FORMAT_HTML,
            BpmProjectNature.DEFAULT_PREFERENCE_REPORT_FORMAT_HTML);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_REPORT_FORMAT_PDF,
            BpmProjectNature.DEFAULT_PREFERENCE_REPORT_FORMAT_PDF);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_REPORT_FORMAT_PROMPT,
            BpmProjectNature.DEFAULT_PREFERENCE_REPORT_FORMAT_PROMPT);
      
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_COLLISION_UPDATE,
            BpmProjectNature.DEFAULT_PREFERENCE_COLLISION_UPDATE);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_COLLISION_REFRESH,
            BpmProjectNature.DEFAULT_PREFERENCE_COLLISION_REFRESH);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_COLLISION_REFRESH_RATE,
            BpmProjectNature.DEFAULT_PREFERENCE_COLLISION_REFRESH_RATE);      
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY,
            BpmProjectNature.DEFAULT_PREFERENCE_COLLISION_CONNECTION_RETRY);      
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_COLLISION_CONNECTION_RETRY_RATE,
            BpmProjectNature.DEFAULT_PREFERENCE_COLLISION_CONNECTION_RETRY_RATE);      
      
      
      PlatformUI.getPreferenceStore().setDefault(
              BpmProjectNature.PREFERENCE_SPLIT_AND,
              BpmProjectNature.DEFAULT_PREFERENCE_SPLIT_AND);
      PlatformUI.getPreferenceStore().setDefault(
              BpmProjectNature.PREFERENCE_SPLIT_XOR,
              BpmProjectNature.DEFAULT_PREFERENCE_SPLIT_XOR);
      PlatformUI.getPreferenceStore().setDefault(
              BpmProjectNature.PREFERENCE_SPLIT_PROMPT,
              BpmProjectNature.DEFAULT_PREFERENCE_SPLIT_PROMPT);
      
      PlatformUI.getPreferenceStore().setDefault(
              BpmProjectNature.PREFERENCE_JOIN_AND,
              BpmProjectNature.DEFAULT_PREFERENCE_JOIN_AND);
      PlatformUI.getPreferenceStore().setDefault(
              BpmProjectNature.PREFERENCE_JOIN_XOR,
              BpmProjectNature.DEFAULT_PREFERENCE_JOIN_XOR);
      PlatformUI.getPreferenceStore().setDefault(
              BpmProjectNature.PREFERENCE_JOIN_PROMPT,
              BpmProjectNature.DEFAULT_PREFERENCE_JOIN_PROMPT);      
      
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_DEPLOY_id,
            BpmProjectNature.DEFAULT_PREFERENCE_DEPLOY_id);
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_DEPLOY_password,
            BpmProjectNature.DEFAULT_PREFERENCE_DEPLOY_password);      
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
    */
   public void start(BundleContext context) throws Exception
   {
      super.start(context);
      synchronizeTraceFile();

      try
      {
         IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
         for (int i = 0; i < windows.length; i++)
         {
            windows[i].addPerspectiveListener(perspectiveListener);
         }
      }
      catch (IllegalStateException e)
      {
         // Ignoring exception to prevent error in headless mode.
         // TODO: How to find out explicitly that headless mode is used?
         trace.warn(e);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
    */
   public void stop(BundleContext context) throws Exception
   {
      IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
      for (int i = 0; i < windows.length; i++)
      {
         windows[i].removePerspectiveListener(perspectiveListener);
      }

      plugin = null;
      super.stop(context);
   }

   /**
    * Returns the shared instance
    * 
    * @return the shared instance
    */
   public static BpmUiActivator getDefault()
   {
      return plugin;
   }

   public void setAnalystMode(IWorkbenchPage page, boolean analystMode)
   {
      IPreferenceStore store = PlatformUI.getPreferenceStore();
      store.setValue("org.eclipse.stardust.model.xpdl.diagram.analystView", //$NON-NLS-1$
            analystMode);
      updateEditors(page);
   }

   private void updateEditors(IWorkbenchPage page)
   {
      IEditorReference[] editors = page.getEditorReferences();
      for (int i = 0; i < editors.length; i++)
      {
         IEditorPart part = editors[i].getEditor(false);
         if (part instanceof IWorkflowModelEditor)
         {
            try
            {
               ((IWorkflowModelEditor) part).updateEditor(page);
            }
            catch (IllegalArgumentException e)
            {
               // do nothing
            }
         }
      }
   }

   public String getTraceFilePath()
   {
      IPreferenceStore store = PlatformUI.getPreferenceStore();
      String path = store.getString("org.eclipse.stardust.model.xpdl.license.path"); //$NON-NLS-1$

      if (StringUtils.isEmpty(path))
      {
         Bundle modeler = Platform.getBundle("ag.carnot.workflow.modeler"); //$NON-NLS-1$

         if (null != modeler)
         {
            URL licenseUrl = modeler.getResource("carnot.license"); //$NON-NLS-1$
            if (null != licenseUrl)
            {
               try
               {
                  path = /*Platform.asLocalURL*/FileLocator.toFileURL(licenseUrl).getPath();
                  setTraceFilePath(path);
               }
               catch (IOException e)
               {
                  // ignore
               }
            }
         }
      }

      return path;
   }

   public void setTraceFilePath(String path)
   {
      IPreferenceStore store = PlatformUI.getPreferenceStore();
      store.setValue("org.eclipse.stardust.model.xpdl.license.path", path); //$NON-NLS-1$

      BpmCommonActivator.getDefault().setTraceFilePath(path);

      IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
      for (int i = 0; i < windows.length; i++)
      {
         IWorkbenchPage[] pages = windows[i].getPages();
         for (int j = 0; j < pages.length; j++)
         {
            IEditorReference[] editors = pages[j].getEditorReferences();
            for (int k = 0; k < editors.length; k++)
            {
               IEditorPart part = editors[k].getEditor(false);
               if (part instanceof IWorkflowModelEditor)
               {
                  IWorkflowModelEditor cwmEditor = (IWorkflowModelEditor) part;
                  if (cwmEditor.getWorkflowModel() == null)
                  {
                     try
                     {
                        cwmEditor.updateModel(cwmEditor.getEditorInput());
                     }
                     catch (Exception e)
                     {
                        // ignore e.printStackTrace();
                     }
                  }
                  try
                  {
                     cwmEditor.updateEditor(pages[j]);
                  }
                  catch (Exception e)
                  {
                     // ignore e.printStackTrace();
                  }
               }
            }
         }
      }
   }

   public String hasModule(String module)
   {
      moduleError = null;

      synchronizeTraceFile();

      Exception e = BpmCommonActivator.getDefault().traceModule(module);
      if (null != e)
      {
         if (Parameters.instance().getString("License." + module + ".product") == null) //$NON-NLS-1$ //$NON-NLS-2$
         {
            moduleError = MessageFormat.format(
                  UI_Messages.BpmUiActivator_noLicenseIsPresent, new Object[] {module});
         }
         else
         {
            moduleError = e.getMessage();
         }
      }

      return moduleError;
   }

   private void synchronizeTraceFile()
   {
      String path = getTraceFilePath();

      if (path != null
            && path.length() > 0
            && !path.equals(Parameters.instance().getString(
                  String.valueOf(new char[] {
                        'L', 'i', 'c', 'e', 'n', 's', 'e', '.', 'L', 'i', 'c', 'e', 'n',
                        's', 'e', 'F', 'i', 'l', 'e', 'P', 'a', 't', 'h'}))))
      {
         BpmCommonActivator.getDefault().setTraceFilePath(path);
      }
   }

   public String getString(String name)
   {
      return Parameters.instance().getString(name);
   }

   public String getString(String name, String defaultValue)
   {
      return Parameters.instance().getString(name, defaultValue);
   }

   public boolean hasLicense()
   {
      return hasDeveloperLicense() || hasOldModellingLicense();
   }

   public boolean hasOldModellingLicense()
   {
      return hasModule(Modules.MODELLING) == null;
   }

   public boolean hasDeveloperLicense()
   {
      return hasModule(Modules.PROCESS_WORKBENCH_4_DEVELOPERS) == null;
   }

   public boolean hasBusinessLicense()
   {
      return hasModule(Modules.PROCESS_WORKBENCH_4_ANALYSTS) == null;
   }

   public String getModuleError()
   {
      return moduleError;
   }
   
   public static IWorkflowModelEditor findWorkflowModelEditor(ModelType model)
   {
      if (model == null)
      {
         return null;
      }
      // first try to match the current editor
      IWorkbenchWindow activeWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
      if (activeWindow != null)
      {
         IWorkbenchPage activePage = activeWindow.getActivePage();
         if (activePage != null)
         {
            IEditorPart currentEditor = activePage.getActiveEditor();
            if (currentEditor != null && currentEditor instanceof IWorkflowModelEditor)
            {
               if (model == null || ((IWorkflowModelEditor) currentEditor).getWorkflowModel() == model)
               {
                  return (IWorkflowModelEditor) currentEditor;
               }
            }
         }
      }
      // iterate through all editors
      IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
      for (int i = 0; i < windows.length; i++)
      {
         IWorkbenchPage[] pages = windows[i].getPages();
         for (int j = 0; j < pages.length; j++)
         {
            IEditorReference[] references = pages[j].getEditorReferences();
            for (int k = 0; k < references.length; k++)
            {
               IEditorPart editor = references[k].getEditor(false);
               if (editor instanceof IWorkflowModelEditor)
               {
                  if (((IWorkflowModelEditor) editor).getWorkflowModel() == model)
                  {
                     return (IWorkflowModelEditor) editor;
                  }
               }
            }
         }
      }
      // no matching editor
      return null;
   }   
}