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
package org.eclipse.stardust.modeling.debug.engine;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;

import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.common.FilteringIterator;
import org.eclipse.stardust.common.Functor;
import org.eclipse.stardust.common.OneElementIterator;
import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.common.TransformingIterator;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.engine.api.dto.ActivityInstanceDetails;
import org.eclipse.stardust.engine.core.persistence.AndTerm;
import org.eclipse.stardust.engine.core.persistence.ClosableIterator;
import org.eclipse.stardust.engine.core.persistence.ClosableIteratorUtils;
import org.eclipse.stardust.engine.core.persistence.ComparisonTerm;
import org.eclipse.stardust.engine.core.persistence.DefaultPersistentVector;
import org.eclipse.stardust.engine.core.persistence.FetchPredicate;
import org.eclipse.stardust.engine.core.persistence.IdentifiablePersistent;
import org.eclipse.stardust.engine.core.persistence.Join;
import org.eclipse.stardust.engine.core.persistence.Joins;
import org.eclipse.stardust.engine.core.persistence.Operator;
import org.eclipse.stardust.engine.core.persistence.Persistent;
import org.eclipse.stardust.engine.core.persistence.PersistentVector;
import org.eclipse.stardust.engine.core.persistence.PredicateTerm;
import org.eclipse.stardust.engine.core.persistence.Predicates;
import org.eclipse.stardust.engine.core.persistence.QueryExtension;
import org.eclipse.stardust.engine.core.persistence.ResultIterator;
import org.eclipse.stardust.engine.core.persistence.Session;
import org.eclipse.stardust.engine.core.persistence.Session.FilterOperation;
import org.eclipse.stardust.engine.core.persistence.Session.FilterOperation.FilterResult;
import org.eclipse.stardust.engine.core.runtime.beans.AuditTrailPartitionBean;
import org.eclipse.stardust.engine.core.runtime.beans.DetailsFactory;
import org.eclipse.stardust.engine.core.runtime.beans.IActivityInstance;
import org.eclipse.stardust.engine.core.runtime.beans.IAuditTrailPartition;
import org.eclipse.stardust.engine.core.runtime.beans.IDataValue;
import org.eclipse.stardust.engine.core.runtime.beans.IProcessInstance;
import org.eclipse.stardust.engine.core.runtime.beans.ProcessInstanceBean;
import org.eclipse.stardust.engine.core.runtime.setup.DataClusterInstance;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;

/**
 * This class is adapted from 
 * {@link ag.carnot.workflow.tools.defdesk.debugger.DebugSession}.
 * 
 * @author sborn
 * @version $Revision$
 */
public class DebugSession implements Session, Session.NotJoinEnabled
{
   private static final Logger trace = LogManager.getLogger(DebugSession.class);
   
   private static final String debugModeLimitation = Internal_Debugger_Messages.getString("MSG_DebugModelLimitation"); //$NON-NLS-1$


   private Map objects = new HashMap();
   private long oidCounter = 0;
   private short partitionOidCounter = 0;

   public DebugSession()
   {
   }

   public void connect()
   {
   }

   public void disconnect()
   {
   }

   public void cluster(Persistent object)
   {
      registerObject(object);
   }

   public boolean exists(Class type, QueryExtension queryExtension)
   {
      return findFirst(type, queryExtension) != null;
   }

   public long getCount(Class type)
   {
      return getCount(type, NO_QUERY_EXTENSION, null, 0);
   }
   
   public long getCount(Class type, QueryExtension queryExtension)
   {
      return getCount(type, queryExtension, null, 0);
   }
   
   public long getCount(Class type, QueryExtension queryExtension, int timeout)
   {
      return getCount(type, queryExtension, null, timeout);
   }
   
   public long getCount(Class type, QueryExtension queryExtension, boolean mayFail)
   {
      return getCount(type, queryExtension, null, 0);
   }
   
   public long getCount(Class type, QueryExtension queryExtension,
         FetchPredicate fetchPredicate, int timeout)
   {
      return getCount(type, queryExtension, fetchPredicate, timeout, Long.MAX_VALUE);
   }
   
   @Override
   public long getCount(Class type, QueryExtension queryExtension,
         FetchPredicate fetchPredicate, int timeout, long totalCountThreshold)
   {
      if (!queryExtension.getJoins().isEmpty())
      {
         throw new UnsupportedOperationException(debugModeLimitation);
      }

      ClosableIterator items = null;
      try
      {
         items = new TypeFilterIterator(getValuesIterator(), type, new Filter(queryExtension));
         long count = 0;
         while (items.hasNext())
         {
            Object item = items.next();

            if ((null == fetchPredicate) || fetchPredicate.accept(item))
            {
               ++count;
            }
         }
         return count;
      }
      finally
      {
         if (null != items)
         {
            items.close();
         }
      }
   }
   
   private Iterator getValuesIterator()
   {
      return new HashMap(objects).values().iterator();
   }

   public ResultIterator getIterator(Class type)
   {
      return new TypeFilterIterator(getValuesIterator(), type);
   }

   public ResultIterator getIterator(Class type, QueryExtension queryExtension)
   {
      return new TypeFilterIterator(getValuesIterator(), type,
            new Filter(queryExtension));
   }

   public ResultIterator getIterator(Class type, QueryExtension queryExtension, int startIndex, int extent)
   {
      return new SubsetIterator(getIterator(type, queryExtension), startIndex, extent);
   }

   public ResultIterator getIterator(Class type, QueryExtension queryExtension,
         int startIndex, int extent, int timeout)
   {
      if (!queryExtension.getJoins().isEmpty())
      {
         throw new UnsupportedOperationException(debugModeLimitation);
      }
      return new SubsetIterator(getIterator(type, queryExtension), startIndex, extent);
   }

   public ResultIterator getIterator(Class type, QueryExtension queryExtension,
         int startIndex, int extent, FetchPredicate fetchPredicate, boolean countAll,
         int timeout)
   {
      if (!queryExtension.getJoins().isEmpty())
      {
         throw new UnsupportedOperationException(debugModeLimitation);
      }
      return new SubsetIterator(new TypeFilterIterator(
            getIterator(type, queryExtension), type, fetchPredicate),
            startIndex, extent);
   }
   
   public Vector getVector(Class type)
   {
      return (Vector) ClosableIteratorUtils.copyResult(new Vector(), getIterator(type));
   }

   public Vector getVector(Class type, QueryExtension queryExtension)
   {
      return (Vector) ClosableIteratorUtils.copyResult(new Vector(),
            getIterator(type, queryExtension));
   }

   public Persistent findByOID(Class type, long oid)
   {
      Persistent persistent = null;

      if (IdentifiablePersistent.class.isAssignableFrom(type))
      {
         persistent = lookupObjectByOID(oid);
      }
      else if (IAuditTrailPartition.class.isAssignableFrom(type))
      {
         persistent = findFirst(type, QueryExtension.where(Predicates.isEqual(
               AuditTrailPartitionBean.FR__OID, oid)));
      }

      return persistent;
   }

   public Persistent findFirst(Class type, QueryExtension queryExtension)
   {
      return findFirst(type, queryExtension, 0);
   }

   public Persistent findFirst(Class type, QueryExtension queryExtension, int timeout)
   {
      Persistent persistent = null;

      ClosableIterator i = getIterator(type, queryExtension);
      try
      {
         Predicate filter = new Filter(queryExtension);
         while (i.hasNext())
         {
            persistent = (Persistent) i.next();
            if (filter.accept(persistent))
            {
               break;
            }
            persistent = null;
         }
      }
      finally
      {
         i.close();
      }
      return persistent;
   }
   /**
    *
    */
   public void save()
   {
      // Intentionally left empty
   }

   /**
    *
    */
   public void rollback()
   {
      // Intentionally left empty
   }

   /**
    * Retrieves an object by its OID store string or strings containing a long.
    * For compatibility reasons, both formats must be supported.
    */
   private void registerObject(Persistent object)
   {
      Assert.isNotNull(object);

      if (object instanceof IdentifiablePersistent)
      {
         IdentifiablePersistent ip = (IdentifiablePersistent) object;
         
         if (ip.getOID() == 0)
         {
            ip.setOID(generateOID());
         }
         
         trace.debug(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_AddObjectWithOid"), new Object[] { //$NON-NLS-1$
            object, new Long(ip.getOID()) }));
         
         objects.put(Long.toString(ip.getOID()), object);
      }
      else
      {
         if (object instanceof IAuditTrailPartition)
         {
            IAuditTrailPartition partition = (IAuditTrailPartition) object;
            
            if (partition.getOID() == 0)
            {
               partition.setOID(generatePartitionOID());
            }
         }
         
         // store object with itself as key
         objects.put(object, object);
      }
      object.setPersistenceController(new TransientPersistenceController(object));
   }

   /**
    * Retrieves an object by its OID store string or strings containing a long.
    * For compatibility reasons, both formats must be supported.
    */
   private Persistent lookupObjectByOID(long oid)
   {
      return (Persistent) objects.get(Long.toString(oid));
   }

   /**
    * Creates a persistent vector.
    */
   public PersistentVector createPersistentVector()
   {
      return new DefaultPersistentVector();
   }

   /**
    * Initializes an object reference from the database.
    */
   public Persistent fetchLink(Persistent persistent, String linkName)
   {
      return null;
   }

   /**
    * Clears all instances of a persistent capable
    * class.
    */
   public void deleteAllInstances(Class type, boolean delay)
   {
      throw new UnsupportedOperationException(debugModeLimitation);
   }

   /**
    * Remove the object with the passed OID from the internal persistence
    * simulation (the hashtable).
    * 
    * @param oid the OID of the object to delete
    */
   public void deleteObjectByOID(long oid)
   {
      trace.debug(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_DeleteObjectWithOid"), //$NON-NLS-1$
            new Object[] { new Long(oid) }));

      objects.remove(Long.toString(oid));
   }

   private long generateOID()
   {
      return ++oidCounter;
   }

   private short generatePartitionOID()
   {
      return ++partitionOidCounter;
   }

   public Iterator getWorklist()
   {
      return new TransformingIterator(getIterator(IActivityInstance.class),
            new Functor()
            {
               public Object execute(Object source)
               {
                  return DetailsFactory.create(source,
                        IActivityInstance.class, ActivityInstanceDetails.class);
               }
            },
            new Predicate()
            {
               public boolean accept(Object o)
               {
                  return ((IActivityInstance) o).getCurrentPerformer() != null;
               }
            }
      );
   }

   public Iterator getProcessInstanceData(final long processInstanceOID)
   {
      final IProcessInstance pi = (IProcessInstance) findByOID(ProcessInstanceBean.class,
            processInstanceOID);

      final IProcessInstance rootPI = pi.getRootProcessInstance();

      return new FilteringIterator(getIterator(IDataValue.class),
            new Predicate()
            {
               public boolean accept(Object o)
               {
                  return ((IDataValue) o).getProcessInstance() == rootPI;
               }
            }
      );
   }

   public void delete(Class type, PredicateTerm predicate, boolean delay)
   {
      delete(type, predicate, (Join) null, delay);
   }

   public void delete(Class type, PredicateTerm predicate, Join join, boolean delay)
   {
   }

   public void delete(Class type, PredicateTerm predicate, Joins join, boolean delay)
   {
   }

   public void lock(Class type, long oid)
   {
   }

   public void lock(Class type, long oid, long timeOut)
   {
   }

   public void lock(Class type, long oid, int timeOut)
   {
   }
   
   public boolean isSynchronized(Persistent persistent)
   {
      return true;
   }

   public void setSynchronized(Persistent persistent)
   {
   }

   public Persistent findObjectByPK(Class type, Object pk)
   {
      return null;
   }

   /**
    * Decorator allowing for on-the-fly iteration over a subset of another iterator.
    * 
    * @author rsauer
    * @version $Id$
    */
   private class SubsetIterator implements ResultIterator
   {
      private final ResultIterator source;

      private final int startIndex;
      private final int extent;

      private int currentIndex;

      private SubsetIterator(ResultIterator source, int startIndex, int extent)
      {
         this.source = source;

         this.startIndex = startIndex;
         this.extent = extent;

         this.currentIndex = 0;

         while (currentIndex <= startIndex)
         {
            if (source.hasNext())
            {
               source.next();
            }
            ++currentIndex;
         }
      }

      public boolean hasNext()
      {
         return (currentIndex < (startIndex + extent)) && source.hasNext();
      }

      public Object next()
      {
         ++currentIndex;
         return source.next();
      }

      public void remove()
      {
         throw new UnsupportedOperationException(Internal_Debugger_Messages.getString("EXP_ImmutableIterator")); //$NON-NLS-1$
      }

      public void close()
      {
         source.close();
      }

      public int getStartIndex()
      {
         return startIndex;
      }

      public int getMaxSize()
      {
         return extent;
      }

      public boolean hasMore()
      {
         return source.getMaxSize() >= (startIndex + extent);
      }

      public boolean hasTotalCount()
      {
         return source.hasTotalCount();
      }

      public long getTotalCount() throws UnsupportedOperationException
      {
         return source.getTotalCount();
      }
      
      @Override
      public long getTotalCountThreshold()
      {
         return source.getTotalCountThreshold();
      }
      
   }

   private class Filter implements Predicate
   {
      private final QueryExtension queryExtension;
      private final List predicates;

      private Filter(QueryExtension queryExtension)
      {
         this.queryExtension = queryExtension;

         if (null != queryExtension)
         {
            Iterator parts;
            if (queryExtension.getPredicateTerm() instanceof AndTerm)
            {
               AndTerm andTerm = (AndTerm) queryExtension.getPredicateTerm();
               parts = andTerm.getParts().iterator();
            }
            else
            {
               parts = new OneElementIterator(queryExtension.getPredicateTerm());
            }

            this.predicates = new ArrayList();

            for (Iterator i = parts; i.hasNext();)
            {
               PredicateTerm predicateTerm = (PredicateTerm) i.next();
               
               if (predicateTerm instanceof ComparisonTerm)
               {
                  ComparisonTerm currentTerm = (ComparisonTerm) predicateTerm;
                  
                  String attrName = currentTerm.getLhsField().fieldName;
                  
                  Assert.condition(
                        currentTerm.getValueExpr() instanceof Long
                        || isSimpleArray(currentTerm.getValueExpr(), true)
                        || currentTerm.getValueExpr() instanceof String
                        || isSimpleArray(currentTerm.getValueExpr(), false),
                        MessageFormat.format(Internal_Debugger_Messages.getString("EXP_UnsupportedPerdicateTerm_0"), //$NON-NLS-1$
                              new Object[] {queryExtension.getPredicateTerm() }));
                  
                  Object value = currentTerm.getValueExpr();

                  try
                  {
                     Assert.condition(Operator.IS_EQUAL == currentTerm.getOperator()
                           || Operator.IN == currentTerm.getOperator()
                           || Operator.NOT_IN == currentTerm.getOperator()
                           || Operator.NOT_EQUAL == currentTerm.getOperator(),
                           MessageFormat.format(Internal_Debugger_Messages.getString("EXP_UnsupportedPerdicateTerm_0_1_2"),  //$NON-NLS-1$
                                 new Object[] {attrName, currentTerm.getOperator(), value }));
                  }
                  catch (Exception e)
                  {
                  }

                  predicates.add(new Match(attrName, value));
               }
               else
               {
                  Assert.lineNeverReached(MessageFormat.format(
                        Internal_Debugger_Messages.getString("EXP_UnsupportedPerdicateTerm_0"), new Object[] { queryExtension //$NON-NLS-1$
                              .getPredicateTerm() }));
               }
            }
         }
         else
         {
            this.predicates = Collections.EMPTY_LIST;
         }
      }

      private boolean isSimpleArray(Object value, boolean isLong)
      {
         if (!(value instanceof ArrayList))
         {
            return false;
         }
         ArrayList list = (ArrayList) value;
         for (int i = 0; i < list.size(); i++)
         {
            if (isLong)
            {
               if (!(list.get(i) instanceof Long))
               {
                  return false;
               }
               else if (!(list.get(i) instanceof String))
               {
                  return false;
               }
            }
         }
         return true;
      }

      public String toString()
      {
         return queryExtension.toString();
      }

      public boolean accept(Object o)
      {
         boolean accepted = true;

         try
         {
            for (Iterator i = predicates.iterator(); i.hasNext();)
            {
               Match match = (Match) i.next();
               Field attr = null;
               Class clazz = o.getClass();
               while ((null != clazz) && (null == attr))
               {
                  try
                  {
                     trace.debug(MessageFormat.format(Internal_Debugger_Messages.getString("MSG_LookingUpAttributeInClass"), //$NON-NLS-1$
                           match.getAttrName(), clazz.getName()));
                     attr = clazz.getDeclaredField(match.getAttrName());
                  }
                  catch (NoSuchFieldException e)
                  {
                     // bubble through ancestry

                     clazz = clazz.getSuperclass();
                     if (null == clazz)
                     {
                        throw e;
                     }
                  }
               }

               if (null != attr)
               {
                  attr.setAccessible(true);

                  String key;
                  if (IdentifiablePersistent.class.isAssignableFrom(attr.getType()))
                  {
                     IdentifiablePersistent persistent = (IdentifiablePersistent) attr.get(o);
                     key = Long.toString(persistent.getOID());
                  }
                  else if (IAuditTrailPartition.class.isAssignableFrom(attr.getType()))
                  {
                     // special handling for IAuditTrailPartition since its getOid()
                     // does return short instead of long. 
                     IAuditTrailPartition partition = (IAuditTrailPartition) attr.get(o);
                     key = Long.toString(partition.getOID());
                  }
                  else
                  {
                     key = attr.get(o).toString();
                  }

                  if (match.getValue() instanceof List)
                  {
                     // check for every element in IN-clause
                     boolean matchedInClause = false;
                     for (Iterator in = ((List)match.getValue()).iterator(); in.hasNext(); )
                     {
                        if (key.equals(in.next().toString()))
                        {
                           matchedInClause = true;
                           break;
                        }
                     }
                     accepted &= matchedInClause;
                  }
                  else
                  {
                     accepted &= key.equals(match.getStringValue());
                  }
               }
               else
               {
                  accepted &= (null == match.getValue())
                        || "null".equals(match.getValue()) || "NULL".equals(match.getValue());  //$NON-NLS-1$ //$NON-NLS-2$
               }
            }
            return accepted;
         }
         catch (Exception e)
         {
            throw new InternalException(e);
         }
      }

      private class Match
      {
         private final String attrName;
         private final Object value;

         private Match(String attrName, Object value)
         {
            this.attrName = attrName;
            this.value = value;
         }

         public Object getStringValue()
         {
            if (value == null)
            {
               return null;
            }
            else
            {
               return value.toString();
            }
         }

         public String getAttrName()
         {
            return attrName;
         }

         public Object getValue()
         {
            return value;
         }
      }
   }

   public boolean existsInCache(Class type, Object identityKey)
   {
      // TODO use per type cache
      return objects.containsKey(identityKey);
   }

   public boolean isReadOnly()
   {
      return false;
   }
   
   public void cluster(DataClusterInstance cluster)
   {
      // TODO Auto-generated method stub
   }
   
   public <T> Iterator<T> getSessionCacheIterator(final Class<T> type, final FilterOperation<T> op)
   {
      final Set<T> persistents = new HashSet<T>();
      for (Object o : objects.values())
      {
         if (type.isAssignableFrom(o.getClass()))
         {
            final T t = (T) o;
            final FilterResult result = op.filter(t);
            if (result == FilterResult.ADD)
            {
               persistents.add(t);
            }
         }
      }
      return persistents.iterator();
   }
}
