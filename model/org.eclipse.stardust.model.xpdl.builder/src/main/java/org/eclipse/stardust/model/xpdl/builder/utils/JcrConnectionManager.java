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
package org.eclipse.stardust.model.xpdl.builder.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.connectionhandler.JcrConnectionHandler;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnection;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.repository.common.Attribute;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionHandler;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ExternalPackageResolver;
import org.eclipse.stardust.modeling.repository.common.IFilter;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.stardust.modeling.repository.common.Repository;
import org.eclipse.stardust.modeling.repository.common.RepositoryFactory;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.stardust.modeling.repository.common.descriptors.EObjectDescriptor;

public class JcrConnectionManager implements IConnectionManager
{
   private static final IFilter BY_REF_FILTER = new IFilter()
   {
      public boolean accept(Object object)
      {
         if (object instanceof EObjectDescriptor)
         {
            EObjectDescriptor eObjectdescriptor = (EObjectDescriptor) object;
            if (eObjectdescriptor.getEObject() instanceof TypeDeclarationType)
            {
               TypeDeclarationType typeDeclaration = eObjectdescriptor.getEObject();
               ExtendedAttributeType visibility = ExtendedAttributeUtil.getAttribute(
                     typeDeclaration.getExtendedAttributes(),
                     PredefinedConstants.MODELELEMENT_VISIBILITY);
               if (visibility != null && "Private".equals(visibility.getValue())) //$NON-NLS-1$
               {
                  return false;
               }
            }
            if (eObjectdescriptor.getEObject() instanceof ApplicationType)
            {
               ApplicationType applicationType = eObjectdescriptor.getEObject();
               AttributeType visibility = AttributeUtil.getAttribute(
                     (IExtensibleElement) applicationType,
                     PredefinedConstants.MODELELEMENT_VISIBILITY);
               if (visibility != null && "Private".equals(visibility.getValue())) //$NON-NLS-1$
               {
                  return false;
               }
            }
            if (eObjectdescriptor.getEObject() instanceof RoleType)
            {
               RoleType roleType = eObjectdescriptor.getEObject();
               AttributeType visibility = AttributeUtil.getAttribute(
                     (IExtensibleElement) roleType,
                     PredefinedConstants.MODELELEMENT_VISIBILITY);
               if (visibility != null && "Private".equals(visibility.getValue())) //$NON-NLS-1$
               {
                  return false;
               }
            }
            if (eObjectdescriptor.getEObject() instanceof OrganizationType)
            {
               OrganizationType orgType = eObjectdescriptor.getEObject();
               AttributeType visibility = AttributeUtil.getAttribute(
                     (IExtensibleElement) orgType,
                     PredefinedConstants.MODELELEMENT_VISIBILITY);
               if (visibility != null && "Private".equals(visibility.getValue())) //$NON-NLS-1$
               {
                  return false;
               }
            }
            if (eObjectdescriptor.getEObject() instanceof ConditionalPerformerType)
            {
               ConditionalPerformerType conPerfType = eObjectdescriptor.getEObject();
               AttributeType visibility = AttributeUtil.getAttribute(
                     (IExtensibleElement) conPerfType,
                     PredefinedConstants.MODELELEMENT_VISIBILITY);
               if (visibility != null && "Private".equals(visibility.getValue())) //$NON-NLS-1$
               {
                  return false;
               }
            }
            if (eObjectdescriptor.getEObject() instanceof DataType)
            {
               DataType dataType = eObjectdescriptor.getEObject();
               AttributeType visibility = AttributeUtil.getAttribute(
                     (IExtensibleElement) dataType,
                     PredefinedConstants.MODELELEMENT_VISIBILITY);
               if (visibility != null && "Private".equals(visibility.getValue())) //$NON-NLS-1$
               {
                  return false;
               }
            }
            if (eObjectdescriptor.getEObject() instanceof ProcessDefinitionType)
            {
               ProcessDefinitionType process = (ProcessDefinitionType) eObjectdescriptor
                     .getEObject();
               if (process.getFormalParameters() == null)
               {
                  return false;
               }
            }
         }
         return true;
      }
   };
   
   private Repository repository;

   private Map<Connection, ConnectionHandler> handlers = CollectionUtils.newMap();

   private ModelType model;

   // attribute name

   public void setModel(ModelType model)
   {
      this.model = model;
      model.setConnectionManager(this);      
   }

   public JcrConnectionManager(ModelType model)
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

   public Connection getConnectionForAttribute(String filename)
   {
      for (Connection connection : repository.getConnection())
      {
         List<Attribute> attributes = connection.getAttributes();
         for (int j = 0; j < attributes.size(); j++)
         {
            Attribute attribute = (Attribute) attributes.get(j);
            if(attribute.getName().equals("filename"))
            {
               if (attribute.getValue().equals(filename))
               {
                  return connection;
               }               
            }
         }
      }
      return null;
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
      //IConfigurationElement config = getConfigurationElement(type);
      String baseId = type;
      String baseName = type; // config.getAttribute(SpiConstants.NAME);

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
      return new JcrConnectionHandler();
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

   public List<IObjectDescriptor> select(Connection connection, IFilter[] filters)
         throws CoreException
   {
      /*if (isLinked)
      {
         filters = new IFilter[] {new LinkedFilter(filters)};
      }*/
      filters = getFilters(filters, "true".equals(connection.getAttribute(BY_REFERENCE))); //$NON-NLS-1$
      ConnectionHandler handler = (ConnectionHandler) handlers.get(connection);
      if (handler != null)
      {
         List<IObjectDescriptor> result = handler.select(filters);
         return filters == null ? result : decorateWithFilters(result, filters);
      }
      String message = MessageFormat.format(Repository_Messages.MSG_FORMAT_CONNECTION_NULL_IS_CLOSED,
            new Object[] {connection.getId()});
      throw new CoreException(new Status(IStatus.ERROR,
            ObjectRepositoryActivator.PLUGIN_ID, 0, message, null));
   }

   private List<IObjectDescriptor> decorateWithFilters(List<IObjectDescriptor> result, IFilter[] filters)
   {
      List<IObjectDescriptor> descriptors = CollectionUtils.newList(result.size());
      for (IObjectDescriptor desc : result)
      {
         descriptors.add(getFilteredObjectDescriptor(desc, filters));
      }
      return descriptors;
   }

   private IObjectDescriptor getFilteredObjectDescriptor(final IObjectDescriptor desc,
         final IFilter[] filters)
   {
      InvocationHandler handler = new InvocationHandler()
      {
         public Object invoke(Object proxy, Method method, Object[] args)
               throws Throwable
         {
            if (IObjectDescriptor.class.equals(method.getDeclaringClass()) && "getChildren".equals(method.getName())) //$NON-NLS-1$
            {
               IObjectDescriptor[] children = desc.getChildren();
               List<IObjectDescriptor> filtered = CollectionUtils.newList();
               for (int i = 0; i < children.length; i++)
               {
                  if (BY_REF_FILTER.accept(children[i]))
                  {
                     filtered.add(getFilteredObjectDescriptor(children[i], filters));
                  }
               }
               return filtered.toArray(new IObjectDescriptor[filtered.size()]);
            }
            return method.invoke(desc, args);
         }
      };
      ClassLoader classLoader = desc.getClass().getClassLoader();
      Class<?>[] interfaces = getInterfaces(desc);                
      return (IObjectDescriptor) Proxy.newProxyInstance(classLoader, interfaces, handler);
   }
   

   private Class< ? >[] getInterfaces(IObjectDescriptor desc)
   {
      List<Class< ? >> result = new ArrayList<Class< ? >>();
      Class< ? >[] interfaces = desc.getClass().getInterfaces();
      for (int i = 0; i < interfaces.length; i++)
      {
         result.add(interfaces[i]);
      }
      if (desc.getClass().getSuperclass() != null)
      {
         interfaces = desc.getClass().getSuperclass().getInterfaces();
         for (int i = 0; i < interfaces.length; i++)
         {
            result.add(interfaces[i]);
         }
      }
      return (Class< ? >[]) result.toArray((new Class< ? >[result.size()]));
   }

   /**
    * TODO describe
    * @param filters
    * @param byReference 
    * @return
    */
   private IFilter[] getFilters(IFilter[] filters, boolean byReference)
   {
      if (filters != null || byReference)
      {
         List<IFilter> list = CollectionUtils.newList();
         if (filters != null)
         {
            for (int i = 0; i < filters.length; i++)
            {
               if (filters[i] != null)
               {
                  list.add(filters[i]);
               }
            }
         }
         if (byReference)
         {
            list.add(BY_REF_FILTER);
         }
         if (list.size() > 0)
         {
            return list.toArray(new IFilter[list.size()]);
         }
      }
      return null;
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

   public boolean mustLink(IObjectDescriptor descriptor)
   {
      URI uri = descriptor.getURI();
      String id = uri.authority();
      Connection connection = getConnection(id);
      if (connection == null)
      {
         return false;
      }
      return "true".equals(connection.getAttribute(BY_REFERENCE)); //$NON-NLS-1$
   }

   /*public Command linkObject(ModelType model, IObjectDescriptor[] descriptors)
         throws CoreException
   {
      ArrayList<Connection> connections = new ArrayList<Connection>();
      UsageDisplayDialog.setUsage(null);
      
      ChangeRecorder recorder = new ChangeRecorder(model);

      String id;
      IObjectDescriptor[] entryValues;
      Map<String, ArrayList<IObjectDescriptor>> container = CollectionUtils.newMap();
      Connection connection;

      // collect descriptors for each connection
      sortDescriptors(descriptors, container);

      // here loop through all connections found
      Iterator<Map.Entry<String, ArrayList<IObjectDescriptor>>> it = container.entrySet()
            .iterator();
      while (it.hasNext())
      {
         Map.Entry<String, ArrayList<IObjectDescriptor>> entry = it.next();
         id = entry.getKey();
         entryValues = (IObjectDescriptor[]) entry.getValue().toArray(
               new IObjectDescriptor[0]);

         connection = getConnection(id);
         if (connection == null)
         {
            String message = MessageFormat.format(Repository_Messages.MSG_FORMAT_CONNECTION_NULL_DOES_NOT_EXIST,
                  new Object[] {id});
            throw new CoreException(new Status(IStatus.ERROR,
                  ObjectRepositoryActivator.PLUGIN_ID, 0, message, null));
         }         
         connections.add(connection);
         ConnectionHandler handler = (ConnectionHandler) handlers.get(connection);
         if (handler != null)
         {
            try
            {
               handler.importObject(model, entryValues, "true".equals(connection //$NON-NLS-1$
                     .getAttribute(BY_REFERENCE)));
            }
            catch (ImportCancelledException ice)
            {
               recorder.dispose();
               return null;
            }
         }
      }
      final ChangeDescription changes = recorder.endRecording();
      //rp: Workaround for CRNT-23880
      reloadConnections(connections);
      return new Command()
      {
         public void execute()
         {
         // nothing to be done as the changes are already applied
         }

         public void undo()
         {
            changes.applyAndReverse();
         }

         public void redo()
         {
            changes.applyAndReverse();
         }
      };
   }*/

   private void sortDescriptors(IObjectDescriptor[] descriptors,
         Map<String, ArrayList<IObjectDescriptor>> container)
   {
      URI uri;
      String id;
      IObjectDescriptor descriptor;
      ArrayList<IObjectDescriptor> descriptorValues;
      for (int i = 0; i < descriptors.length; i++)
      {
         descriptor = descriptors[i];
         uri = descriptor.getURI();
         id = uri.authority();
         // is the key inside?
         if (container.containsKey(id))
         {
            descriptorValues = container.get(id);
            descriptorValues.add(descriptors[i]);
            container.put(id, descriptorValues);
         }
         else
         {
            descriptorValues = new ArrayList<IObjectDescriptor>();
            descriptorValues.add(descriptors[i]);
            container.put(id, descriptorValues);
         }
      }
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
   
   public static String createFileConnection(ModelType model, ModelType referencedModel)
   {
      String id = null;
      
      JcrConnectionManager jcrConnectionManager = XpdlModelIoUtils.getJcrConnectionManager(model);
      IConnection findConnection = jcrConnectionManager.getConnectionForAttribute("project:/" + referencedModel.getName() + ".xpdl");
      
      if(findConnection == null)
      {
         try
         {
            Connection connection = jcrConnectionManager.create("file");
            id = connection.getId();
            RepositoryFactory factory = RepositoryFactory.eINSTANCE;
            
            Attribute attribute = factory.createAttribute();
            attribute.setName("filename");
            attribute.setValue("project:/" + referencedModel.getName() + ".xpdl"); //$NON-NLS-1$               
            connection.getAttributes().add(attribute);               
         }
         catch (CoreException e)
         {
         }
      }
      else
      {
         id = findConnection.getId();
      }
      
      return id;
   }
}