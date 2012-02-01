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
package org.eclipse.stardust.modeling.templates.defaulttemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.modeling.templates.Templates_Messages;

/**
 * Supports URLs with scheme "classpath:/". Searches for resources in CLASSPATH
 */
public class ClasspathUriConverter implements URIConverter
{
   public static final String CLASSPATH_SCHEME = "classpath"; //$NON-NLS-1$


   public InputStream createInputStream(URI uri) throws IOException
   {
      URL resourceUrl = this.getClass().getResource(uri.path());
      if (resourceUrl == null)
      {
         throw new PublicException(MessageFormat.format(Templates_Messages.Could_not_find_resource, new Object[]{uri.path()}));
      }
      return resourceUrl.openStream();
   }

   public OutputStream createOutputStream(URI uri) throws IOException
   {
      throw new RuntimeException(Templates_Messages.EXC_NOT_SUPPORTED);
   }

   public Map getURIMap()
   {
      return URIConverter.URI_MAP;
   }

   public URI normalize(URI uri)
   {
      // no normalization implemented
      return uri;
   }

public Map contentDescription(URI arg0, Map arg1) throws IOException {
	// TODO Auto-generated method stub
	return null;
}

public InputStream createInputStream(URI uri, Map arg1) throws IOException {
    URL resourceUrl = this.getClass().getResource(uri.path());
    if (resourceUrl == null)
    {
       throw new PublicException(MessageFormat.format(Templates_Messages.Could_not_find_resource, new Object[]{uri.path()}));
    }
    return resourceUrl.openStream();
}

public OutputStream createOutputStream(URI arg0, Map arg1) throws IOException {
	// TODO Auto-generated method stub
	return null;
}

public void delete(URI arg0, Map arg1) throws IOException {
	// TODO Auto-generated method stub
	
}

public boolean exists(URI arg0, Map arg1) {
	// TODO Auto-generated method stub
	return false;
}

public Map getAttributes(URI arg0, Map arg1) {
	// TODO Auto-generated method stub
	return null;
}

public EList getContentHandlers() {
	// TODO Auto-generated method stub
	return null;
}

public URIHandler getURIHandler(URI uri) {
	// TODO Auto-generated method stub
	return null;
}

public EList getURIHandlers() {
	// TODO Auto-generated method stub
	return null;
}

public void setAttributes(URI arg0, Map arg1, Map arg2) throws IOException {
	// TODO Auto-generated method stub
	
}
}
