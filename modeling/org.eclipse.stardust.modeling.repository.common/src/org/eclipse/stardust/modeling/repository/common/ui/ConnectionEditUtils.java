package org.eclipse.stardust.modeling.repository.common.ui;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.commands.Command;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionHandler;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ImportCancelledException;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.stardust.modeling.repository.common.ui.dialogs.UsageDisplayDialog;

public class ConnectionEditUtils
{
   public static boolean mustLink(IObjectDescriptor descriptor, ConnectionManager manager)
   {
      URI uri = descriptor.getURI();
      String id = uri.authority();
      Connection connection = manager.getConnection(id);
      if (connection == null)
      {
         return false;
      }
      return "true".equals(connection.getAttribute(IConnectionManager.BY_REFERENCE)); //$NON-NLS-1$
   }

   public static Command linkObject(ModelType model, IObjectDescriptor[] descriptors, ConnectionManager manager)
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

         connection = manager.getConnection(id);
         if (connection == null)
         {
            String message = MessageFormat.format(Repository_Messages.MSG_FORMAT_CONNECTION_NULL_DOES_NOT_EXIST,
                  new Object[] {id});
            throw new CoreException(new Status(IStatus.ERROR,
                  ObjectRepositoryActivator.PLUGIN_ID, 0, message, null));
         }
         connections.add(connection);
         ConnectionHandler handler = manager.getConnectionHandler(connection);
         if (handler != null)
         {
            try
            {
               handler.importObject(model, entryValues, "true".equals(connection //$NON-NLS-1$
                     .getAttribute(IConnectionManager.BY_REFERENCE)));
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
   }

   private static void reloadConnections(ArrayList<Connection> connections)
   {
      for (Iterator<Connection> i = connections.iterator(); i.hasNext();)
      {
         Connection connection = i.next();
         ConnectionManager manager = ConnectionManager.getConnectionManager(connection);
         if (manager != null)
         {
            try
            {
               manager.close(connection);
            }
            catch (CoreException e)
            {

            }
            connection.eNotify(new NotificationImpl(Notification.REMOVE, null, null, 0));
         }
      }
   }

   private static void sortDescriptors(IObjectDescriptor[] descriptors,
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

   private ConnectionEditUtils()
   {
      // utility class
   }
}
