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
package org.eclipse.stardust.modeling.repository.common;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ObjectRepositoryActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.stardust.modeling.repository.common"; //$NON-NLS-1$
    public static final String CONNECTION_EXTENSION_POINT_ID = "connections"; //$NON-NLS-1$
    public static final String CONNECTION_SEARCH_EXTENSION_POINT_ID = "connectionsearch"; //$NON-NLS-1$
    
    public static final String CONNECTION_RESPOSITORY_SELECTION = PLUGIN_ID + '.' + "select"; //$NON-NLS-1$
    public static final String CREATE_REPOSITORY_CONNECTION_ACTION = PLUGIN_ID + '.' + "create."; //$NON-NLS-1$
    public static final String LINK_CONNECTION_OBJECT_ACTION = PLUGIN_ID + '.' + "link"; //$NON-NLS-1$
    public static final String IMPORT_CONNECTION_OBJECT_ACTION = PLUGIN_ID + '.' + "import"; //$NON-NLS-1$
    public static final String REFRESH_CONNECTION_OBJECT_ACTION = PLUGIN_ID + '.' + "refresh"; //$NON-NLS-1$
    public static final String ADD_EXTERNAL_REFERENCES_ACTION = PLUGIN_ID + '.' + "addReferences"; //$NON-NLS-1$
    public static final String DELETE_EXTERNAL_REFERENCES_ACTION = PLUGIN_ID + '.' + "deleteReferences"; //$NON-NLS-1$
    public static final String SEARCH_ACTION = PLUGIN_ID + '.' + "search"; //$NON-NLS-1$
    
    public static String getIcon()
    {
       return "{" + PLUGIN_ID + "}icons/external_model.gif"; //$NON-NLS-1$ //$NON-NLS-2$
    }        

	// The shared instance
	private static ObjectRepositoryActivator plugin;
	
	/**
	 * The constructor
	 */
	public ObjectRepositoryActivator() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ObjectRepositoryActivator getDefault() {
		return plugin;
	}
}
