/*******************************************************************************
 * Copyright (c) 2014 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.connectionhandler;

import java.beans.Introspector;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.stardust.model.xpdl.builder.utils.WebModelerConnectionManager;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.merge.LinkAttribute;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceModelElementDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;

public class EObjectProxyHandler implements EObjectReference, InvocationHandler, Adapter
{
   private EObject object;
   private EObject target;
   private EObject proxy;

   public EObjectProxyHandler(EObject object, EObject target)
   {
      this.object = object;
      if (target instanceof IIdentifiableElement)
      {
         updateProxyURI(((IIdentifiableElement) target).getId());
      }
      target.eAdapters().add(this);
   }

   @Override
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
   {
      String name = method.getName();
      if (name.equals("equals") && args.length == 1)
      {
         return proxy == args[0];
      }
      EObject instance = object;
      if (name.startsWith("get"))
      {
         EClass eClass = target.eClass();
         EStructuralFeature feature = eClass.getEStructuralFeature(
               Introspector.decapitalize(name.substring(3)));
         if (feature != null)
         {
            if (!feature.isMany() && !object.eIsSet(feature) || eClass.getEAllContainments().contains(feature))
            {
               instance = target;
            }
         }
         else // @Florin - This makes fail the testcase "TestCrossModelSupport".
         {
            EList<EOperation> operations = eClass.getEAllOperations();
            for (EOperation eOperation : operations)
            {
               if (name.equals(eOperation.getName()))
               {
                  EClassifier eType = eOperation.getEType();
                  boolean isNodeSymbol = INodeSymbol.class.isAssignableFrom(eType.getInstanceClass());
                  boolean isListOfNodeSymbol = false;
                  if (!isNodeSymbol)
                  {
                     boolean isList = Collection.class.isAssignableFrom(eType.getInstanceClass());
                     if (isList)
                     {
                        for (EGenericType eGenericType : eOperation.getEGenericType().getETypeArguments())
                        {
                           isListOfNodeSymbol = INodeSymbol.class.isAssignableFrom(eGenericType.getEClassifier().getInstanceClass());
                           if (isListOfNodeSymbol)
                           {
                              break;
                           }
                        }
                     }
                  }
                  if (!isNodeSymbol && !isListOfNodeSymbol)
                  {
                     instance = target;
                  }
                  break;
               }
            }
         }
      }
      return method.invoke(instance, args);
   }

   public EObject getSelf()
   {
      return proxy;
   }

   @Override
   public Notifier getTarget()
   {
      return target;
   }

   @Override
   public boolean isAdapterForType(Object obj)
   {
      return false;
   }

   @Override
   public void setTarget(Notifier target)
   {
      this.target = (EObject) target;
   }

   @Override
   public void notifyChanged(Notification event)
   {
      Object newValue = event.getNewValue();
      if (newValue != null)
      {
         if (CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id().equals(event.getFeature()))
         {
            updateProxyURI(event.getNewValue().toString());
         }
      }
   }

   private void updateProxyURI(String newId)
   {
      URI uri = ((InternalEObject) object).eProxyURI();
      uri = uri.trimQuery();
      uri = uri.trimSegments(1);
      uri = uri.appendSegment(newId);
      ((InternalEObject) object).eSetProxyURI(uri);
      ((IIdentifiableElement) object).setId(newId);
   }

   @SuppressWarnings("unchecked")
   public static <T extends IIdentifiableModelElement> T importElement(ModelType model, T identifiable)
   {
      ModelType otherModel = ModelUtils.findContainingModel(identifiable);
      String fileConnectionId = WebModelerConnectionManager.createFileConnection(model, otherModel);

      String baseUri = "cnx://" + fileConnectionId + "/";
      URI uri = MergeUtils.createQualifiedUri(URI.createURI(baseUri), identifiable, true);
      Collection<Object> destination = (Collection<Object>) model.eGet(identifiable.eContainingFeature());
      for (Object object : destination)
      {
         if ((object instanceof InternalEObject) && ((EObject) object).eIsProxy()
               && uri.equals(((InternalEObject) object).eProxyURI()))
         {
            return (T) object;
         }
      }

      T local = (T) CarnotWorkflowModelFactory.eINSTANCE.create(identifiable.eClass());
      local.setId(identifiable.getId());
      destination.add(local);

      uri = URI.createURI(baseUri);
      ReplaceModelElementDescriptor descriptor = new ReplaceModelElementDescriptor(uri, local,
            CarnotConstants.DIAGRAM_PLUGIN_ID, null, true);
      LinkAttribute linkAttribute = new LinkAttribute(descriptor.getRootURI(), true, true, IConnectionManager.URI_ATTRIBUTE_NAME);
      ImportUtils.getPackageRef(descriptor, model, otherModel);
      linkAttribute.setLinkInfo(local, true);

      return (T) createProxy(local, identifiable);
   }

   @SuppressWarnings("unchecked")
   public static <T extends EObject> T createProxy(T object, T target)
   {
      Class<?>[] interfaces = new Class[] {InternalEObject.class, object.eClass().getInstanceClass()};
      EObjectProxyHandler handler = new EObjectProxyHandler(object, target);
      handler.proxy = (EObject) Proxy.newProxyInstance(object.getClass().getClassLoader(), interfaces , handler);
      MergeUtils.replace(object, handler.proxy);
      return (T) handler.proxy;
   }
}
