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
package org.eclipse.stardust.modeling.repository.file;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionHandler;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ExtendedModelManager;
import org.eclipse.stardust.modeling.repository.common.IFilter;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ImportableDescriptor;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.stardust.modeling.repository.common.descriptors.CategoryDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.EObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.ModelElementDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;

public class FileConnectionHandler implements ConnectionHandler
{
   private boolean open;
   private URI uri;
   private IObjectDescriptor[] children = null;
   private Connection connection;
   
   private static final List<String> PARTICIPANTS = Arrays.asList(new String[] {
         "role", "organization", "conditionalPerformer"
   });
   
   private EObjectDescriptor modelDescriptor;
   
   // close file
   public void close(Connection connection) throws CoreException
   {
      if (!open)
      {
         throw new CoreException(new Status(IStatus.ERROR, ObjectRepositoryActivator.PLUGIN_ID,
                 0, MessageFormat.format(File_Messages.EXC_ALREADY_CLOSED, new Object[]{connection.getId()}),null));
      }
      open = false;
      this.connection = null;
   }

   public void open(Connection connection) throws CoreException
   {            
      if (open)
      {
          throw new CoreException(new Status(IStatus.ERROR, ObjectRepositoryActivator.PLUGIN_ID,
                  0, MessageFormat.format(File_Messages.EXC_ALREADY_OPEN, new Object[]{connection.getId()}),null));
      }
      
      uri = ConnectionManager.makeURI(connection);
      String filename = connection.getAttribute("filename"); //$NON-NLS-1$
      if (filename == null)
      {
         throw new CoreException(new Status(IStatus.ERROR,
               ObjectRepositoryActivator.PLUGIN_ID, 0, File_Messages.EXC_MISSING_FILENAME, null));
      }
      
      this.connection = connection;
      try
      {
         updateCache(filename);
      }
      catch (IOException ex)
      {
         throw new CoreException(new Status(IStatus.WARNING,
               "org.eclipse.stardust.modeling.repository.file", File_Messages.EXC_UNABLE_TO_LOAD_MD, ex)); //$NON-NLS-1$
      }
      open = true;            
   }

   // open the selected file from the file connection
   // check, if file is valid file
   private void updateCache(String fileName) throws IOException
   {
      File file = resolve(connection, fileName);
      ExtendedModelManager manager = new ExtendedModelManager();            
      manager.load(file);
      ModelType model = manager.getModel();
      IconFactory iconFactory = IconFactory.getDefault();
      modelDescriptor = new EObjectDescriptor(uri, model, model.getId(), model.getName(),
            ModelUtils.getDescriptionText(model.getDescription()),
            CarnotConstants.DIAGRAM_PLUGIN_ID, iconFactory.getIconFor(model));
      List<IObjectDescriptor> descriptors = ImportUtils.createObjectDescriptors(iconFactory, model, uri);
      if (descriptors.size() > 0)
      {
         children = descriptors.toArray(new IObjectDescriptor[0]);
      }
   }

   public List<IObjectDescriptor> select(IFilter[] filters) throws CoreException
   {
      List<IObjectDescriptor> result = CollectionUtils.newList();
      if (filters == null || filters.length == 0)
      {
         for (int i = 0; i < children.length; i++)
         {
            result.add(children[i]);
         }
      }
      else
      {
         for (int i = 0; i < children.length; i++)
         {
            IObjectDescriptor descriptor = children[i];
            for (int j = 0; j < filters.length; j++)
            {
               if (filters[j] != null && filters[j].accept(descriptor))
               {
                  result.add(descriptor);
                  break;
               }
            }
         }
      }
      IObjectDescriptor searchDescriptor = (IObjectDescriptor) connection.getProperty("search.result"); //$NON-NLS-1$
      if (searchDescriptor != null)
      {
         result.add(searchDescriptor);
      }
      return Collections.unmodifiableList(result);      
   }
   
   public void importObject(ModelType model, IObjectDescriptor[] descriptors, boolean asLink)
   {      
      for (int i = 0; i < descriptors.length; i++) 
      {
         IObjectDescriptor descriptor = descriptors[i];
         if (descriptor instanceof ImportableDescriptor)
         {
            ((ImportableDescriptor) descriptor).importElements(IconFactory.getDefault(), model, asLink);
         }
      }   
   }
   
   public EObject resolve(ModelType model, EObject object)
   {
      URI uri = ConnectionManager.getURI(object);
      if (uri != null)
      {
         IObjectDescriptor node = find(uri);
         if (node != null)
         {
            return ((ModelElementDescriptor) node).resolveElement(object);
         }
      }
      return object;
   }

   public IObjectDescriptor find(URI uri)
   {
      if (uri.equals(this.uri))
      {
         return modelDescriptor;
      }
      for (IObjectDescriptor child : children)
      {
         if (uri.equals(child.getURI()))
         {
            return child;
         }
         else if (child instanceof CategoryDescriptor)
         {
            URI categoryUri = child.getURI();
            if (isChildOf(categoryUri, uri))
            {
               return ((CategoryDescriptor) child).find(uri);
            }
         }
      }
      return null;
   }

   public boolean isChildOf(URI categoryUri, URI uri)
   {
      if (uri.toString().startsWith(categoryUri.toString()))
      {
         return true;
      }
      if ("participants".equals(categoryUri.lastSegment()) && uri.segmentCount() > categoryUri.segmentCount())
      {
         return PARTICIPANTS.contains(uri.segment(categoryUri.segmentCount() - 1));
      }
      return false;
   }

   static File resolve(Connection connection, String fileName)
   {
      File file = null;
      try
      {
         java.net.URI uri = java.net.URI.create(fileName);
         if ("project".equals(uri.getScheme())) //$NON-NLS-1$
         {
            ConnectionManager manager = (ConnectionManager) connection.getProperty(IConnectionManager.CONNECTION_MANAGER);
            ModelType model = manager.getModel();
            org.eclipse.emf.common.util.URI modelURI = model.eResource().getURI();
            if (modelURI.isPlatformResource())
            {
               file = new File(modelURI.segment(1), uri.getPath());
            }
            else if (modelURI.isFile())
            {
               String fileString = modelURI.toFileString();
               java.net.URI netModelUri = new File(fileString).toURI();
               java.net.URI platformRelative = Platform.getLocation().toFile().toURI().relativize(
                     netModelUri);
               modelURI = org.eclipse.emf.common.util.URI.createPlatformResourceURI(platformRelative.toString(), false);
               file = new File(modelURI.segment(1), uri.getPath());
            }
         }
         else if ("platform".equals(uri.getScheme())) //$NON-NLS-1$
         {
            file = new File(uri.getPath());
         }
         else
         {
            file = new File(fileName);
         }
         if (!file.isAbsolute())
         {
            file = new File(Platform.getLocation().toFile(), file.toString());
         }
      }
      catch (Exception e)
      {
         file = new File(fileName);
      }
      return file;
   }
}