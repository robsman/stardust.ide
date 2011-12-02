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
package org.eclipse.stardust.modeling.core.properties;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ModelElementsOutlineSynchronizer
      extends ModelElementNotificationAdapter
{
   private OutlineProvider provider;

   public ModelElementsOutlineSynchronizer(OutlineProvider provider)
   {
      super(provider.getElementListFeature(), new int[0], provider.getFilter(), false);
      this.provider = provider;
   }

   public void init(EObject target)
   {
      dispose();
      super.init(target);
      provider.removeChildrenNodes(provider.getParentNodeId());
      if (target != null) {
         List elements = getChildren(target);
         for (int i = 0; i < elements.size(); i++)
         {
             addNode((EObject) elements.get(i), i); 
         }         
      }
      provider.updateVisuals();
   }

   public void elementChanged(EObject element)
   {
      // for the moment nothing to do since label/icon changes are performed somwhere else
      // todo: implement it here
   }

   public void elementAdded(EObject element)
   {
      addNode(element, getNewIndex());
      provider.updateVisuals();
   }

   private void addNode(final EObject element, int index)
   {
	  IModelElement modelElement = null;
	  if (element instanceof IModelElement)
	  {
		 modelElement = (IModelElement) element;
	  }
	  else
	  {
	     ClassLoader classLoader = IModelElement.class.getClassLoader();
		 Class<?>[] interfaces = element.getClass().getInterfaces();
		 Class<?>[] classes = new Class[interfaces.length + 1];
		 System.arraycopy(interfaces, 0, classes, 0, interfaces.length);
		 classes[interfaces.length] = IModelElement.class;
		 InvocationHandler invocationHandler = new InvocationHandler()
		 {
			public Object invoke(Object proxy, Method mtd, Object[] args)
					throws Throwable
		    {
				if (IModelElement.class.equals(mtd.getDeclaringClass()))
				{
					return null;
				}
				return mtd.invoke(element, args);
			}
	     };
		 modelElement = (IModelElement) Proxy.newProxyInstance(classLoader, classes, invocationHandler);
	  }
      provider.addNodesFor(provider.getParentNodeId(), modelElement,
            new ModelElementAdaptable(
               new Class[] {IModelElement.class},
               new Object[] {modelElement},
               provider.getAdaptable()
            ), index);
   }

   public List<IPreferenceNode> getNodes()
   {
      List<IPreferenceNode> nodesList = new ArrayList<IPreferenceNode>();
      
      PreferenceManager pm = provider.getPreferenceManager();
      CarnotPreferenceNode parent = (CarnotPreferenceNode) pm.find(provider.getParentNodeId());
      if(parent == null)
      {
         return nodesList;         
      }
      
      IPreferenceNode[] nodes = parent.getSubNodes();
      for (int i = 0; i < nodes.length; i++)
      {
         nodesList.add(nodes[i]);
      }
      return nodesList;
   }   
   
   private int getNewIndex()
   {
      int index = -1;
      PreferenceManager pm = provider.getPreferenceManager();
      CarnotPreferenceNode parent = (CarnotPreferenceNode) pm.find(provider.getParentNodeId());
      IPreferenceNode[] nodes = parent.getSubNodes();
      for (int i = 0; i < nodes.length; i++)
      {
         index = Math.max(index, ((CarnotPreferenceNode) nodes[i]).getSortOrder());
      }
      return index + 1;
   }

   public void elementMoved(final EObject element)
   {
	  IModelElement modelElement = null;
	  if (element instanceof IModelElement)
	  {
		 modelElement = (IModelElement) element;
	  } else
	  {
		ClassLoader classLoader = IModelElement.class.getClassLoader();
		Class<?>[] interfaces = element.getClass().getInterfaces();
		Class<?>[] classes = new Class[interfaces.length + 1];
		System.arraycopy(interfaces, 0, classes, 0, interfaces.length);
		classes[interfaces.length] = IModelElement.class;
		InvocationHandler invocationHandler = new InvocationHandler()
		{
			public Object invoke(Object proxy, Method mtd, Object[] args)
					throws Throwable
			{
			 if (IModelElement.class.equals(mtd.getDeclaringClass()))
			 {
				return null;
			 }
			 return mtd.invoke(element, args);
		}
	   };
	 modelElement = (IModelElement) Proxy.newProxyInstance(classLoader, classes, invocationHandler);
	}
 
	  PreferenceManager pm = provider.getPreferenceManager();
      String prefix = provider.getParentNodeId();
      List elements = getChildren(element.eContainer());
      CarnotPreferenceNode parentNode = (CarnotPreferenceNode) pm.find(prefix);
      for (int i = 0; i < elements.size(); i++)
      {
         EObject me =  (EObject)elements.get(i);
         IPreferenceNode[] subnodes = parentNode.getSubNodes();
         for (int j = 0; j < subnodes.length; j++)
         {
            if (subnodes[j] instanceof CarnotPreferenceNode)
            {
               CarnotPreferenceNode node = (CarnotPreferenceNode) subnodes[j];
               if (me.equals(node.getAdaptable().getAdapter(IModelElement.class)))
               {
                  node.setSortOrder(i);
               }
            }
         }
      }
      provider.updateVisuals();
   }

   public void elementRemoved(final EObject element)
   {
	   
		  IModelElement modelElement = null;
		  if (element instanceof IModelElement)
		  {
			 modelElement = (IModelElement) element;
		  } else
		  {
			ClassLoader classLoader = IModelElement.class.getClassLoader();
			Class<?>[] interfaces = element.getClass().getInterfaces();
			Class<?>[] classes = new Class[interfaces.length + 1];
			System.arraycopy(interfaces, 0, classes, 0, interfaces.length);
			classes[interfaces.length] = IModelElement.class;
			InvocationHandler invocationHandler = new InvocationHandler()
			{
				public Object invoke(Object proxy, Method mtd, Object[] args)
						throws Throwable
				{
				 if (IModelElement.class.equals(mtd.getDeclaringClass()))
				 {
					return null;
				 }
				 return mtd.invoke(element, args);
			}
		   };
		 modelElement = (IModelElement) Proxy.newProxyInstance(classLoader, classes, invocationHandler);
		} 
	   
	   
      //String id = provider.getId((IModelElement) element);
	  String id = provider.getId(modelElement);	  
      provider.removeNode(provider.getParentNodeId() + "." + id); //$NON-NLS-1$
      provider.updateVisuals();
   }
}