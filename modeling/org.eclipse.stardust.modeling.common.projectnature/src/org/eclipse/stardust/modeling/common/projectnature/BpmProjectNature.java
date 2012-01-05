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
package org.eclipse.stardust.modeling.common.projectnature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.stardust.common.StringUtils;

/**
 * @author rsauer
 * @version $Revision$
 */
public class BpmProjectNature implements IProjectNature
{
   public static final String NATURE_ID = ModelingCoreActivator.PLUGIN_ID
         + ".carnotBusinessProcessManagement"; //$NON-NLS-1$

   public static final String FACETED_PROJECT_NATURE = "org.eclipse.wst.common.project.facet.core.nature"; //$NON-NLS-1$
   
   public static final String PREFERENCE_AUTO_VALIDATION = ModelingCoreActivator.PLUGIN_ID
         + ".autoValidation"; //$NON-NLS-1$

   public static final String PROJECT_PREFERENCES = ModelingCoreActivator.PLUGIN_ID
         + ".projectPreferences"; //$NON-NLS-1$

   public static final QualifiedName PREFERENCE_CARNOT_HOME = new QualifiedName(
         PROJECT_PREFERENCES, "CARNOT_HOME"); //$NON-NLS-1$

   public static final QualifiedName PREFERENCE_CARNOT_WORK = new QualifiedName(
         PROJECT_PREFERENCES, "CARNOT_WORK"); //$NON-NLS-1$

   public static final String PREFERENCE_AUTO_ID_GENERATION = ModelingCoreActivator.PLUGIN_ID
         + ".autoIdGeneration"; //$NON-NLS-1$
   
   public static final String PREFERENCE_AUTO_SUBPROCESS_NAME_GENERATION = ModelingCoreActivator.PLUGIN_ID
   + ".autoSubprocessNameGeneration"; //$NON-NLS-1$

   public static final String PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE = ModelingCoreActivator.PLUGIN_ID
         + ".warnSwitchActivityType"; //$NON-NLS-1$

   public static final String PREFERENCE_ALWAYS_SWITCH_ACTIVITY_TYPE = ModelingCoreActivator.PLUGIN_ID
         + ".alwaysSwitchActivityType"; //$NON-NLS-1$

   public static final String PREFERENCE_NEVER_SWITCH_ACTIVITY_TYPE = ModelingCoreActivator.PLUGIN_ID
         + ".neverSwitchActivityType"; //$NON-NLS-1$

   public static final String PREFERENCE_MODELING_DIRECTION = ModelingCoreActivator.PLUGIN_ID
         + ".modelingDirection"; //$NON-NLS-1$
   
   public static final String PREFERENCE_CLASSIC_MODE = ModelingCoreActivator.PLUGIN_ID
         + ".classicMode"; //$NON-NLS-1$   
   
   public static final String PREFERENCE_VIEW_FORK_ON_TRAVERSAL_MODE = ModelingCoreActivator.PLUGIN_ID
         + ".viewForkOnTraversalMode"; //$NON-NLS-1$   
   public static final boolean DEFAULT_PREFERENCE_VIEW_FORK_ON_TRAVERSAL_MODE = false;
      
   // focus for newly created elements
   public static final String PREFERENCE_FOCUS_MODE = ModelingCoreActivator.PLUGIN_ID
         + ".focusMode"; //$NON-NLS-1$      
   public static final String PREFERENCE_FOCUS_MODE_ELEMENT = "focusModeElement"; //$NON-NLS-1$      
   public static final String PREFERENCE_FOCUS_MODE_DIALOG = "focusModeDialog"; //$NON-NLS-1$      
   public static final String PREFERENCE_FOCUS_MODE_EDITOR = "focusModeEditor"; //$NON-NLS-1$      
   public static final String DEFAULT_PREFERENCE_FOCUS_MODE = PREFERENCE_FOCUS_MODE_ELEMENT;
   
   // SnapGrid
   public static final String PREFERENCE_SNAP_GRID_MODE = ModelingCoreActivator.PLUGIN_ID
         + ".snapGridMode"; //$NON-NLS-1$   
   public static final String PREFERENCE_SNAP_GRID_PIXEL = ModelingCoreActivator.PLUGIN_ID
         + ".snapGridPixel"; //$NON-NLS-1$   
   public static final String PREFERENCE_VISIBLE_GRID_FACTOR = ModelingCoreActivator.PLUGIN_ID
         + ".visibleGridFactor"; //$NON-NLS-1$      
   public static final boolean DEFAULT_PREFERENCE_SNAP_GRID_MODE = true;
   public static final int DEFAULT_PREFERENCE_SNAP_GRID_PIXEL = 5;
   public static final int DEFAULT_PREFERENCE_VISIBLE_GRID_FACTOR = 4;

   public static final boolean DEFAULT_PREFERENCE_AUTO_VALIDATION = true;

   public static final boolean DEFAULT_PREFERENCE_AUTO_ID_GENERATION = true;

   public static final String DEFAULT_PREFERENCE_MODELING_DIRECTION = "Vertical"; //$NON-NLS-1$
   
   // better use a boolean value than a string
   public static final boolean DEFAULT_PREFERENCE_CLASSIC_MODE = true;
   
   public static final boolean DEFAULT_PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE = true;

   public static final boolean DEFAULT_PREFERENCE_ALWAYS_SWITCH_ACTIVITY_TYPE = false;

   public static final boolean DEFAULT_PREFERENCE_NEVER_SWITCH_ACTIVITY_TYPE = false;

   public static final String PREFERENCE_DISTRIBUTE_ONE_SYMBOL_GRID = ModelingCoreActivator.PLUGIN_ID
         + "oneSymbol"; //$NON-NLS-1$

   public static final String PREFERENCE_DISTRIBUTE_ALL_SYMBOLS_GRID = ModelingCoreActivator.PLUGIN_ID
         + "allSymbols"; //$NON-NLS-1$

   public static final String PREFERENCE_DISTRIBUTE_PROMPT_GRID = ModelingCoreActivator.PLUGIN_ID
         + "prompt"; //$NON-NLS-1$

   public static final boolean DEFAULT_PREFERENCE_DISTRIBUTE_ONE_SYMBOL_GRID = false;

   public static final boolean DEFAULT_PREFERENCE_DISTRIBUTE_ALL_SYMBOLS_GRID = false;

   public static final boolean DEFAULT_PREFERENCE_DISTRIBUTE_PROMPT_GRID = true;

   public static final boolean DEFAULT_PREFERENCE_AUTO_SUBPROCESS_NAME_GENERATION = true;

   public static final String PREFERENCE_REPORT_FORMAT_HTML = ModelingCoreActivator.PLUGIN_ID
         + "htmlReportFormat"; //$NON-NLS-1$

   public static final String PREFERENCE_REPORT_FORMAT_PDF = ModelingCoreActivator.PLUGIN_ID
         + "pdfReportFormat";//$NON-NLS-1$

   public static final String PREFERENCE_REPORT_FORMAT_PROMPT = ModelingCoreActivator.PLUGIN_ID
         + "promptReportFormat";//$NON-NLS-1$

   public static final boolean DEFAULT_PREFERENCE_REPORT_FORMAT_HTML = false;

   public static final boolean DEFAULT_PREFERENCE_REPORT_FORMAT_PDF = false;

   public static final boolean DEFAULT_PREFERENCE_REPORT_FORMAT_PROMPT = true;
   
   // COLLISION
   public static final String PREFERENCE_COLLISION_UPDATE = "collisionUpdate"; //$NON-NLS-1$
   public static final boolean DEFAULT_PREFERENCE_COLLISION_UPDATE = true;
   
   public static final String PREFERENCE_COLLISION_REFRESH = "collisionRefresh"; //$NON-NLS-1$
   public static final boolean DEFAULT_PREFERENCE_COLLISION_REFRESH = true;
   
   public static final String PREFERENCE_COLLISION_REFRESH_RATE = "collisionRefreshRate"; //$NON-NLS-1$
   public static final int DEFAULT_PREFERENCE_COLLISION_REFRESH_RATE = 300;

   public static final String PREFERENCE_COLLISION_CONNECTION_RETRY = "collisionRetry"; //$NON-NLS-1$
   public static final boolean DEFAULT_PREFERENCE_COLLISION_CONNECTION_RETRY = true;   
   
   public static final String PREFERENCE_COLLISION_CONNECTION_RETRY_RATE = "collisionConnectionRetryRate"; //$NON-NLS-1$
   public static final int DEFAULT_PREFERENCE_COLLISION_CONNECTION_RETRY_RATE = 300;
   
   //Multi Package Modeling (Surge)
   public static final String PREFERENCE_MULTIPACKAGEMODELING_VISIBILITY = "multiPackageModelingVisibility"; //$NON-NLS-1$
   public static final String DEFAULT_PREFERENCE_MULTIPACKAGEMODELING_VISIBILITY = "Private"; //$NON-NLS-1$

   // split/join
   public static final String PREFERENCE_SPLIT_AND = ModelingCoreActivator.PLUGIN_ID
   		+ "splitAND"; //$NON-NLS-1$
   public static final String PREFERENCE_SPLIT_XOR = ModelingCoreActivator.PLUGIN_ID
   		+ "splitXOR"; //$NON-NLS-1$
   public static final String PREFERENCE_SPLIT_PROMPT = ModelingCoreActivator.PLUGIN_ID
   		+ "splitPrompt"; //$NON-NLS-1$

   public static final boolean DEFAULT_PREFERENCE_SPLIT_AND = false;
   public static final boolean DEFAULT_PREFERENCE_SPLIT_XOR = false;
   public static final boolean DEFAULT_PREFERENCE_SPLIT_PROMPT = true;
   
   public static final String PREFERENCE_JOIN_AND = ModelingCoreActivator.PLUGIN_ID
		+ "joinAND"; //$NON-NLS-1$
   public static final String PREFERENCE_JOIN_XOR = ModelingCoreActivator.PLUGIN_ID
		+ "joinXOR"; //$NON-NLS-1$
   public static final String PREFERENCE_JOIN_PROMPT = ModelingCoreActivator.PLUGIN_ID
		+ "joinPrompt"; //$NON-NLS-1$

   public static final boolean DEFAULT_PREFERENCE_JOIN_AND = false;
   public static final boolean DEFAULT_PREFERENCE_JOIN_XOR = false;
   public static final boolean DEFAULT_PREFERENCE_JOIN_PROMPT = true;   

   // deploy
   public static final String PREFERENCE_DEPLOY_realm = ModelingCoreActivator.PLUGIN_ID + "deployRealm"; //$NON-NLS-1$
   public static final String PREFERENCE_DEPLOY_partition = ModelingCoreActivator.PLUGIN_ID + "deployPartition"; //$NON-NLS-1$
   public static final String PREFERENCE_DEPLOY_id = ModelingCoreActivator.PLUGIN_ID + "deployId"; //$NON-NLS-1$
   public static final String PREFERENCE_DEPLOY_password = ModelingCoreActivator.PLUGIN_ID + "deployPassword"; //$NON-NLS-1$
   public static final String PREFERENCE_DEPLOY_domain = ModelingCoreActivator.PLUGIN_ID + "deployDomain"; //$NON-NLS-1$
   public static final String PREFERENCE_DEPLOY_version = ModelingCoreActivator.PLUGIN_ID + "deployVersion"; //$NON-NLS-1$
   
   public static final String DEFAULT_PREFERENCE_DEPLOY_id = "motu"; //$NON-NLS-1$
   public static final String DEFAULT_PREFERENCE_DEPLOY_password = "motu"; //$NON-NLS-1$   
   
   public static void enableBpmNature(IProject project)
   {
      try
      {
         IProjectDescription prjDescr = project.getDescription();

         List<String> ids = new ArrayList<String>(Arrays.asList(prjDescr.getNatureIds()));

         if (!ids.contains(NATURE_ID))
         {
            // insert before Java Nature or nature overlay will not been shown
            int idxJavaNature = ids.indexOf(JavaCore.NATURE_ID);
            ids.add(Math.max(idxJavaNature, 0), NATURE_ID);
         }

         prjDescr.setNatureIds(ids.toArray(StringUtils.EMPTY_STRING_ARRAY));
         project.setDescription(prjDescr, null);
      }
      catch (CoreException e)
      {
         // TODO
         e.printStackTrace();
      }
   }

   public static void disableBpmNature(IProject project)
   {
      try
      {
         IProjectDescription prjDescr = project.getDescription();

         List<String> ids = new ArrayList<String>(Arrays.asList(prjDescr.getNatureIds()));

         if (ids.contains(NATURE_ID))
         {
            ids.remove(NATURE_ID);
         }

         prjDescr.setNatureIds(ids.toArray(StringUtils.EMPTY_STRING_ARRAY));
         project.setDescription(prjDescr, null);
      }
      catch (CoreException e)
      {
         // TODO
         e.printStackTrace();
      }
   }

   public void configure() throws CoreException
   {
   }

   public void deconfigure() throws CoreException
   {
   }

   public IProject getProject()
   {
      return null;
   }

   public void setProject(IProject project)
   {
   }
}