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
package org.eclipse.stardust.modeling.transformation.messaging.modeling;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * 
 * @author Rainer Pielmann
 */
public class MessageTransformationModelingPlugin extends AbstractUIPlugin
{
	// The shared instance
	
	private static MessageTransformationModelingPlugin plugin;
	private IPreferenceStore combinedPreferenceStore;


	/**
	 * The constructor
	 */
	public MessageTransformationModelingPlugin()
	{
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static MessageTransformationModelingPlugin getDefault()
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
	public ImageDescriptor getImageDescriptor(String path)
	{
		return AbstractUIPlugin.imageDescriptorFromPlugin(
				"org.eclipse.stardust.modeling.transformation", path); //$NON-NLS-1$
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
