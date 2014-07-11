package org.eclipse.stardust.modeling.repository.common.ui;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionHandler;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IFilter;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.stardust.modeling.repository.common.descriptors.EObjectDescriptor;

public class ConnectionQueryUtils
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

   public static List<IObjectDescriptor> select(Connection connection, ConnectionManager connectionManager, IFilter[] filters)
         throws CoreException
   {
      /*if (isLinked)
      {
         filters = new IFilter[] {new LinkedFilter(filters)};
      }*/
      filters = getFilters(filters, "true".equals(connection.getAttribute(IConnectionManager.BY_REFERENCE))); //$NON-NLS-1$
      ConnectionHandler handler = connectionManager.getConnectionHandler(connection);
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

   private static List<IObjectDescriptor> decorateWithFilters(List<IObjectDescriptor> result, IFilter[] filters)
   {
      List<IObjectDescriptor> descriptors = CollectionUtils.newList(result.size());
      for (IObjectDescriptor desc : result)
      {
         descriptors.add(getFilteredObjectDescriptor(desc, filters));
      }
      return descriptors;
   }

   private static IObjectDescriptor getFilteredObjectDescriptor(final IObjectDescriptor desc,
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


   private static Class< ? >[] getInterfaces(IObjectDescriptor desc)
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
   private static IFilter[] getFilters(IFilter[] filters, boolean byReference)
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

   private ConnectionQueryUtils()
   {
      // utility class
   }
}
