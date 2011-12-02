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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;


public interface ConnectionHandler
{
   void open(Connection connection) throws CoreException;

   void close(Connection connection) throws CoreException;
   
   /**
    * Select all objects provided by this connection matching the filters.
    * 
    * @param filters a list of filters that the objects must match. It may be null or
    *           empty, in which case all objects are considered to match the criteria.
    * @return a List of IObjectDescriptors, empty if no objects matches the filters.
    * @throws CoreException
    */
   List<IObjectDescriptor> select(IFilter[] filters) throws CoreException;
   
   /**
    * Request the handler to import or link the objects defined by the descriptors.
    * 
    * @param model the model into which the objects must be imported or linked
    * @param descriptors the array of descriptors defining the objects to be imported.
    * @param asLink if true, the objects must be linked, otherwise imported.  
    */
   void importObject(ModelType model, IObjectDescriptor[] descriptors, boolean asLink);
   
   /**
    * Callback method invoked at model loading time to resolve linked elements.
    * 
    * @param model the model just loaded.
    * @param object the stub of the linked object.
    * @return the full object that will replace the stub.
    */
   EObject resolve(ModelType model, EObject object);

   /**
    * Finds the object descriptor corresponding to the uri argument.
    * 
    * @param uri the uri of the object descriptor to be searched.
    * @return the object descriptor corresponding to the uri or null
    */
   IObjectDescriptor find(URI uri);
}
