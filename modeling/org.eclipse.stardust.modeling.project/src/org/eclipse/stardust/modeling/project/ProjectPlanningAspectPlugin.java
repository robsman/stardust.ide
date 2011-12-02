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
package org.eclipse.stardust.modeling.project;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ProjectPlanningAspectPlugin extends AbstractUIPlugin
{
	private static ProjectPlanningAspectPlugin plugin;

	/**
	 * The constructor.
	 */
	public ProjectPlanningAspectPlugin()
	{
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception
	{
		super.stop(context);

		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static ProjectPlanningAspectPlugin getDefault()
	{
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return AbstractUIPlugin.imageDescriptorFromPlugin(
				"ag.carnot.bpm.modeling.project", path); //$NON-NLS-1$
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public Image getImage(String path)
	{
		Image image = plugin.getImageRegistry().get(path);

		if (image == null)
		{
			ImageDescriptor descriptor = getImageDescriptor(path);

			if (descriptor != null)
				image = descriptor.createImage();

			if (image != null)
				plugin.getImageRegistry().put(path, image);
		}

		return image;
	}
}
