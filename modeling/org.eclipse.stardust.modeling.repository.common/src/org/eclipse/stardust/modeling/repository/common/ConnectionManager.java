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

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnection;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;

public class ConnectionManager implements IConnectionManager
{
   private Repository repository;

   private Map<Connection, ConnectionHandler> handlers = CollectionUtils.newMap();

   private ModelType model;

   // attribute name

   public ConnectionManager(ModelType model)
   {
      this.model = model;
      this.model.setConnectionManager(this);

      RepositoryFactory factory = RepositoryFactory.eINSTANCE;
      repository = factory.createRepository();

      Map<String, Object> infoMap = CollectionUtils.newMap(); // contains the actual
      // attributes
      List<String> infoList = CollectionUtils.newList(); // used to preserve the order

      List<AttributeType> list = model.getAttribute();
      for (int i = 0; i < list.size(); i++)
      {
         AttributeType att = (AttributeType) list.get(i);
         String name = att.getName();
         if (name.startsWith(CONNECTION_SCOPE))
         {
            name = name.substring(CONNECTION_SCOPE.length());
            handleAttribute(infoMap, infoList, att, name);
         }
      }

      ExternalPackageResolver externalPackageResolver = new ExternalPackageResolver(model);
      model.eAdapters().add(externalPackageResolver);

      for (int i = 0; i < infoList.size(); i++)
      {
         String identifier = (String) infoList.get(i);
         @SuppressWarnings("unchecked")
         Map<String, Object> attributes = (Map<String, Object>) infoMap.get(identifier);

         Connection connection = factory.createConnection();
         connection.setProperty(CONNECTION_MANAGER, this);
         connection.setId((String) attributes.get("id")); //$NON-NLS-1$
         connection.setName((String) attributes.get("name")); //$NON-NLS-1$
         connection.setType((String) attributes.get("type")); //$NON-NLS-1$
         @SuppressWarnings("unchecked")
         Map<String, String> connectionAttributes = (Map<String, String>) attributes
               .get("attribute"); //$NON-NLS-1$
         if (connectionAttributes != null)
         {
            for (Iterator<Map.Entry<String, String>> itr = connectionAttributes
                  .entrySet().iterator(); itr.hasNext();)
            {
               Map.Entry<String, String> entry = itr.next();
               Attribute attr = factory.createAttribute();
               attr.setName(entry.getKey());
               attr.setValue(entry.getValue());
               connection.getAttributes().add(attr);
            }
         }
         repository.getConnection().add(connection);
         connection.eAdapters().add(externalPackageResolver);
      }

   }

   private void handleAttribute(Map<String, Object> infoMap, List<String> infoList,
         AttributeType att, String name)
   {
      int ix = name.indexOf(':');
      if (ix > 0)
      {
         String identifier = name.substring(0, ix);
         if (infoList != null && !infoList.contains(identifier))
         {
            infoList.add(identifier);
         }
         name = name.substring(ix + 1);
         @SuppressWarnings("unchecked")
         Map<String, Object> map = (Map<String, Object>) infoMap.get(identifier);
         if (map == null)
         {
            map = CollectionUtils.newMap();
            infoMap.put(identifier, map);
         }
         handleAttribute(map, null, att, name);
      }
      else
      {
         infoMap.put(name, att.getValue());
      }
   }

   public void close()
   {
      for (Map.Entry<Connection, ConnectionHandler> entry : handlers.entrySet())
      {
         try
         {
            entry.getValue().close(entry.getKey());
         }
         catch (CoreException e)
         {
            e.printStackTrace();
         }
      }
      save();
   }

   public void save()
   {
      List<AttributeType> list = model.getAttribute();
      for (Iterator<AttributeType> i = list.iterator(); i.hasNext();)
      {
         AttributeType att = i.next();
         String name = att.getName();
         if (name.startsWith(CONNECTION_SCOPE))
         {
            i.remove();
         }
      }

      List<Connection> connections = repository.getConnection();
      for (int i = 0; i < connections.size(); i++)
      {
         Connection connection = (Connection) connections.get(i);
         String identifier = CONNECTION_SCOPE + String.valueOf(i + 1) + ':';
         AttributeUtil.setAttribute(model, identifier + "id", connection.getId()); //$NON-NLS-1$
         AttributeUtil.setAttribute(model, identifier + "name", connection.getName()); //$NON-NLS-1$
         AttributeUtil.setAttribute(model, identifier + "type", connection.getType()); //$NON-NLS-1$
         List<Attribute> attributes = connection.getAttributes();
         for (int j = 0; j < attributes.size(); j++)
         {
            Attribute attribute = (Attribute) attributes.get(j);
            AttributeUtil.setAttribute(model, identifier + "attribute:" //$NON-NLS-1$
                  + attribute.getName(), attribute.getValue());
         }
      }
   }

   public void setAllConnections(List<Connection> connections)
   {
      repository.getConnection().addAll(connections);
   }      
   
   public List<Connection> getAllConnections()
   {
      return repository.getConnection();
   }   
   
   public Iterator<Connection> getConnections()
   {
      return repository.getConnection().iterator();
   }

   public Connection getConnection(String id)
   {
      for (Connection connection : repository.getConnection())
      {
         if (id.equals(connection.getId()))
         {
            return connection;
         }
      }
      return null;
   }

   public Connection create(String type) throws CoreException
   {
      Connection connection = createConnection(type);
      connection.setProperty(CONNECTION_MANAGER_CREATED, "true"); //$NON-NLS-1$
      repository.getConnection().add(connection);
      return connection;
   }

   private Connection createConnection(String type) throws CoreException
   {
      IConfigurationElement config = getConfigurationElement(type);
      String baseId = type;
      String baseName = config.getAttribute(SpiConstants.NAME);

      int counter = 0;
      for (Connection connection : repository.getConnection())
      {
         counter = getPostfixNumber(connection.getId(), baseId, '_', counter);
         counter = getPostfixNumber(connection.getName(), baseName, ' ', counter);
      }

      RepositoryFactory factory = RepositoryFactory.eINSTANCE;
      Connection connection = factory.createConnection();
      connection.setProperty(CONNECTION_MANAGER, this);
      connection.setId(counter == 0 ? baseId : baseId + '_' + counter);
      connection.setName(counter == 0 ? baseName : baseName + ' ' + counter);
      connection.setType(type);
      Attribute attribute = factory.createAttribute();
      attribute.setName(BY_REFERENCE);
      attribute.setValue("true"); //$NON-NLS-1$
      connection.getAttributes().add(attribute);
      return connection;
   }

   private int getPostfixNumber(String actual, String base, char separator, int c)
   {
      if (base.equals(actual) && c == 0)
      {
         c = 1;
      }
      else if (actual != null && actual.startsWith(base + separator))
      {
         try
         {
            int number = Integer.parseInt(actual.substring(base.length() + 1));
            c = Math.max(c, number + 1);
         }
         catch (Exception ex)
         {
         }
      }
      return c;
   }

   public ConnectionHandler getConnectionHandler(Connection connection)
   {
      return (ConnectionHandler) handlers.get(connection);
   }

   public boolean isOpen(Connection connection)
   {
      return handlers.containsKey(connection);
   }

   public void open(Connection connection) throws CoreException
   {
      ConnectionHandler handler = (ConnectionHandler) handlers.get(connection);
      if (handler == null)
      {
         handler = createHandler(connection.getType());
         handler.open(connection);
         handlers.put(connection, handler);
      }
   }

   private ConnectionHandler createHandler(String type) throws CoreException
   {
      IConfigurationElement config = getConfigurationElement(type);
      return (ConnectionHandler) config.createExecutableExtension("handler"); //$NON-NLS-1$
   }

   public IConfigurationElement getConfigurationElement(String type) throws CoreException
   {
      List<IConfigurationElement> extensions = SpiExtensionRegistry.instance()
            .getExtensionList(ObjectRepositoryActivator.PLUGIN_ID, "connections"); //$NON-NLS-1$
      for (int i = 0; i < extensions.size(); i++)
      {
         IConfigurationElement config = extensions.get(i);
         if (config.getAttribute(SpiConstants.ID).equals(type))
         {
            return config;
         }
      }
      String message = MessageFormat.format(
            Repository_Messages.MSG_FORMAT_NO_HANDLER_FOUND_FOR_CONNECTION_TYPE_NULL, new Object[] {type});
      throw new CoreException(new Status(IStatus.ERROR,
            ObjectRepositoryActivator.PLUGIN_ID, 0, message, null));
   }

   public void close(Connection connection) throws CoreException
   {
      ConnectionHandler handler = (ConnectionHandler) handlers.get(connection);
      if (handler != null)
      {
         handler.close(connection);
         handlers.remove(connection);
      }
   }

   public Repository getRepository()
   {
      return repository;
   }

   public static URI makeURI(Connection connection)
   {
      String id = connection.getId();
      return URI.createURI(SCHEME + "://" + id + "/"); //$NON-NLS-1$ //$NON-NLS-2$
      // return URI.createHierarchicalURI(SCHEME, id == null ? "_" : id, null, null,
      // null);
   }

   public EObject find(String uri)
   {
      try
      {
         return find(URI.createURI(uri));
      }
      catch (Exception ex)
      {
         // ignore
      }
      return null;
   }

   public IConnection findConnection(String uri)
   {
      try
      {
         return findConnection(URI.createURI(uri));
      }
      catch (Exception ex)
      {
         // ignore
      }
      return null;
   }

   public EObject find(URI uri)
   {
      Connection connection = (Connection) findConnection(uri);
      if (connection != null)
      {
         try
         {
            open(connection);
         }
         catch (CoreException e)
         {
         }
         ConnectionHandler handler = (ConnectionHandler) handlers.get(connection);
         if (handler != null)
         {
            return handler.find(uri);
         }
      }
      return null;
   }

   public IConnection findConnection(URI uri)
   {
      if (SCHEME.equals(uri.scheme()))
      {
         String id = uri.authority();
         return getConnection(id);
      }
      return null;
   }

   public void resolve()
   {
      for (Iterator<EObject> i = model.eContents().iterator(); i.hasNext();)
      {
         EObject object = i.next();
         if (object.eIsProxy())
         {
            if (object instanceof EObjectImpl)
            {
               URI uri = ((EObjectImpl) object).eProxyURI();
               
               // decode uri from the format produced by xpdl transformation
               if (uri.opaquePart() != null)
               {
                  try
                  {
                     QName qname = QName.valueOf(uri.opaquePart());
                     if (model.getExternalPackages() != null)
                     {
                        ExternalPackage pkg = model.getExternalPackages().getExternalPackage(qname.getNamespaceURI());
                        if (pkg != null)
                        {
                           String pkgConnectionUri = ExtendedAttributeUtil.getAttributeValue(pkg, IConnectionManager.URI_ATTRIBUTE_NAME);
                           uri = URI.createURI(pkgConnectionUri + uri.scheme() + '/' + qname.getLocalPart());
                           ((InternalEObject) object).eSetProxyURI(uri);
                        }
                     }
                  }
                  catch (Exception ex)
                  {
                     // not a special reference
                  }
               }
               
               resolve(object, uri);
            }
         }
         /*
          * else if (object instanceof IExtensibleElement) { String uriValue =
          * AttributeUtil.getAttributeValue((IExtensibleElement) object,
          * URI_ATTRIBUTE_NAME); if (uriValue != null) { URI uri =
          * URI.createURI(uriValue); resolve(object, uri); } }
          */
      }
   }

   private void resolve(EObject object, URI uri)
   {
      if (SCHEME.equals(uri.scheme()))
      {
         String id = uri.authority();
         Connection connection = getConnection(id);
         if (connection != null)
         {
            try
            {
               open(connection);
            }
            catch (CoreException e)
            {
            }
            ConnectionHandler handler = (ConnectionHandler) handlers.get(connection);
            if (handler != null)
            {
               handler.resolve(model, object);
            }
         }
      }
   }

   public static URI getURI(EObject object)
   {
      if (object.eIsProxy() && object instanceof InternalEObject)
      {
         return ((InternalEObject) object).eProxyURI();
      }
      if (object instanceof IExtensibleElement)
      {
         String uri = AttributeUtil.getAttributeValue((IExtensibleElement) object,
               URI_ATTRIBUTE_NAME);
         try
         {
            return URI.createURI(uri);
         }
         catch (Exception ex)
         {
            // ignore
         }
      }
      return null;
   }

   public void setConnectionManager(Connection connection)
   {
      connection.setProperty(CONNECTION_MANAGER, this);
   }   
   
   public static ConnectionManager getConnectionManager(Connection connection)
   {
      return (ConnectionManager) connection.getProperty(CONNECTION_MANAGER);
   }

   public ModelType find(ExternalPackage pkg)
   {
      if (pkg != null)
      {
         ModelType model = ModelUtils.findContainingModel(pkg);
         if (model != null)
         {
            String uri = ExtendedAttributeUtil.getAttributeValue(pkg,
                  IConnectionManager.URI_ATTRIBUTE_NAME);
            EObject eObject = find(uri);
            if (eObject instanceof IObjectReference)
            {
               eObject = ((IObjectReference) eObject).getEObject();
            }
            if (eObject instanceof ModelType)
            {
               return (ModelType) eObject;
            }
         }
      }
      return null;
   }

   public ModelType getModel()
   {
      return model;
   }
}