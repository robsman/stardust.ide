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
package org.eclipse.stardust.modeling.javascript;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.JavaRuntime;

public class JSClassLoader extends ClassLoader {
	private IJavaProject javaProject;
	private static final String PROTOCAL_PREFIX = "file:///";

	public JSClassLoader(IJavaProject project) {
		super();
		if (project == null || !project.exists() || !project.isOpen())
			throw new IllegalArgumentException("Invalid javaProject");
		this.javaProject = project;
	}

	public Class findClass(String className) {
		try {
			String[] classPaths = JavaRuntime
					.computeDefaultRuntimeClassPath(javaProject);
			URL[] urls = new URL[classPaths.length];
			for (int i = 0; i < classPaths.length; i++)
				urls[i] = new URL(PROTOCAL_PREFIX
						+ computeForURLClassLoader(classPaths[i]));
			ClassLoader loader = new URLClassLoader(urls);
			Class classObject = loader.loadClass(className);
			return classObject;

		} catch (Exception e) {
			return null;
		}
	}

	private static String computeForURLClassLoader(String classpath) {
		if (!classpath.endsWith("/")) {
			File file = new File(classpath);
			if (file.exists() && file.isDirectory())
				classpath = classpath.concat("/");
		}
		return classpath;
	}
}
