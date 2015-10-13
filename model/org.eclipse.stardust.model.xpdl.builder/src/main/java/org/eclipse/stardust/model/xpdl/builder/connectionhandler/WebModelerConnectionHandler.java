/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.model.xpdl.builder.connectionhandler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.text.MessageFormat;
import java.util.*;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.runtime.DocumentManagementService;
import org.eclipse.stardust.model.xpdl.builder.strategy.ModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.builder.utils.PepperIconFactory;
import org.eclipse.stardust.model.xpdl.builder.utils.WebModelerConnectionManager;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.IconFactory;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.repository.common.*;
import org.eclipse.stardust.modeling.repository.common.descriptors.CategoryDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.EObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.ModelElementDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;

public class WebModelerConnectionHandler implements ConnectionHandler
{
   DocumentManagementService documentManagementService;

   private boolean open;
   private URI uri;
   private IObjectDescriptor[] children = null;
   private Connection connection;
   private ModelManagementStrategy strategy;

   private static final List<String> PARTICIPANTS = Arrays.asList(new String[] {
         "role", "organization", "conditionalPerformer"
   });

   private EObjectDescriptor modelDescriptor;
   private ModelType model;

   private boolean missingModel;

   public WebModelerConnectionHandler(ModelManagementStrategy strategy)
   {
      this.strategy = strategy;
   }

   synchronized ModelType loadModel(String id)
   {
      String modelId = id.split("\\.")[0];
      ModelType model = strategy.getModels(false).get(modelId);
      if (model == null)
      {
         model = strategy.loadModel(modelId);
      }
      if (model == null)
      {
         String uuid = connection.getAttribute("connectionUUID");
         if (uuid != null)
         {
            for (ModelType uuidModel : strategy.getModels().values())
            {
               AttributeType attribute = AttributeUtil.getAttribute(uuidModel, "carnot:model:uuid");
               if (attribute != null)
               {
                  if (attribute.getValue().equals(uuid))
                  {
                     model = uuidModel;
                  }
               }
            }
         }
      }
      return model;
   }

   public void importObject(ModelType model, IObjectDescriptor[] descriptors, boolean asLink)
   {
      for (int i = 0; i < descriptors.length; i++)
      {
         IObjectDescriptor descriptor = descriptors[i];
         if (descriptor instanceof ImportableDescriptor)
         {
            try
            {
               ((ImportableDescriptor) descriptor).importElements(model, new SimpleImportStrategy(asLink));
            }
            catch (Exception f)
            {
               if (f instanceof UndeclaredThrowableException)
               {
                  Throwable undeclaredThrowable = ((UndeclaredThrowableException) f).getUndeclaredThrowable();
                  if (undeclaredThrowable instanceof InvocationTargetException)
                  {
                     Throwable targetException = ((InvocationTargetException) undeclaredThrowable).getTargetException();
                     if (targetException instanceof ImportCancelledException)
                     {
                        throw new ImportCancelledException();
                     }
                  }
               }
               else if (f instanceof ImportCancelledException)
               {
                  throw new ImportCancelledException();
               }
            }
         }
      }
   }

   public EObject resolve(ModelType model, final EObject object)
   {
      URI uri = WebModelerConnectionManager.getURI(object);
      {
         IObjectDescriptor node = find(uri);
         if (node instanceof ModelElementDescriptor)
         {
            return EObjectProxyHandler.createProxy(object, ((ModelElementDescriptor) node).<EObject>getEObject());
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
      String uuid = parseQuery(uri.query()).get("uuid");
      uri = uri.trimQuery();
      if (children != null)
      {
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
                  IObjectDescriptor item = ((CategoryDescriptor) child).find(uri);
                  if (!StringUtils.isEmpty(uuid))
                  {
                     if (!matches(uuid, item))
                     {
                        item = findByUUID(uuid, (CategoryDescriptor) child);
                     }
                  }
                  return item;
               }
            }
         }
      }
      return null;
   }

   private boolean matches(String uuid, IObjectDescriptor item)
   {
      return item instanceof EObjectDescriptor && uuid.equals(ModelUtils.getUUID(((EObjectDescriptor) item).<EObject>getEObject()));
   }

   private IObjectDescriptor findByUUID(String uuid, CategoryDescriptor category)
   {
      for (IObjectDescriptor item : category.getChildren())
      {
         if (matches(uuid, item))
         {
            return item;
         }
      }
      return null;
   }

   private Map<String, String> parseQuery(String query)
   {
      if (StringUtils.isEmpty(query))
      {
         return Collections.emptyMap();
      }
      Map<String, String> map = CollectionUtils.newMap();
      for (String token : query.split("&"))
      {
         int ix = token.indexOf('=');
         map.put(ix < 0 ? token : token.substring(0, ix), ix < 0 ? token : token.substring(ix + 1));
      }
      return map;
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

   public void open(Connection connection) throws CoreException
   {
      if (open)
      {
          throw new CoreException(new Status(IStatus.ERROR, ObjectRepositoryActivator.PLUGIN_ID,
                  0, MessageFormat.format("EXC_ALREADY_OPEN", new Object[]{connection.getId()}),null));
      }

      uri = WebModelerConnectionManager.makeURI(connection);
      String filename = connection.getAttribute("filename"); //$NON-NLS-1$

      String xpdlId = null;

      if (filename != null)
      {
         xpdlId = resolve(filename);
      }

      this.connection = connection;
      try
      {
         if (xpdlId != null)
         {
            updateCache(xpdlId);
         }
      }
      catch (IOException ex)
      {
         throw new CoreException(new Status(IStatus.WARNING,
               "org.eclipse.stardust.modeling.repository.file", "EXC_UNABLE_TO_LOAD_MD", ex)); //$NON-NLS-1$
      }
      open = true;
   }

   // close file
   public void close(Connection connection) throws CoreException
   {
      if (!open)
      {
         throw new CoreException(new Status(IStatus.ERROR, ObjectRepositoryActivator.PLUGIN_ID,
                 0, MessageFormat.format("EXC_ALREADY_CLOSED", new Object[]{connection.getId()}),null));
      }
      open = false;
      this.connection = null;
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

   // open the selected file from the file connection
   // check, if file is valid file
   private void updateCache(String id) throws IOException
   {
      if (!missingModel)
      {
         model = loadModel(id);
         missingModel = model == null;
      }

      if (model == null)
      {
         throw new IOException("Model not found: " + id);
      }

      IconFactory iconFactory = new PepperIconFactory();

      modelDescriptor = new EObjectDescriptor(uri, model, model.getId(), model.getName(),
            ModelUtils.getDescriptionText(model.getDescription()),
            CarnotConstants.DIAGRAM_PLUGIN_ID, null);
      List<IObjectDescriptor> descriptors = ImportUtils.createObjectDescriptors(iconFactory, model, uri);
      if (descriptors.size() > 0)
      {
         children = descriptors.toArray(new IObjectDescriptor[0]);
      }
   }

   private String resolve(String fileName)
   {
      String xpdlName = null;
      fileName = fileName.replace(" ", "%20");
      java.net.URI uri = java.net.URI.create(fileName);
      if ("project".equals(uri.getScheme())) //$NON-NLS-1$
      {
         String path = uri.getPath();
         xpdlName = path.substring(1);
      }
      else if ("platform".equals(uri.getScheme())) //$NON-NLS-1$
      {
         // uri.getPath();
      }
      else if (null == uri.getScheme())
      {
         xpdlName = fileName;
      }
      return xpdlName;
   }
}