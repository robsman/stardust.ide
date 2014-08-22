package org.eclipse.stardust.modeling.integration.camel.triggerTypes;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.properties.ModelElementAdaptable;
import org.eclipse.stardust.modeling.core.properties.ModelElementsOutlineSynchronizer;
import org.eclipse.stardust.modeling.core.properties.OutlineProvider;

public class CamelModelElementsOutlineSynchronizer
      extends ModelElementsOutlineSynchronizer
{
   public CamelModelElementsOutlineSynchronizer(OutlineProvider provider)
   {
      super(provider);
      this.provider = provider;
   }

   private OutlineProvider provider;

   @Override
   public void elementAdded(EObject element)
   {
      addNode(element, getNewIndex());
      provider.updateVisuals();
   }

   private int getNewIndex()
   {
      int index = -1;
      PreferenceManager pm = provider.getPreferenceManager();
      CarnotPreferenceNode parent = (CarnotPreferenceNode) pm.find(provider
            .getParentNodeId());
      if (parent != null)
      {
         IPreferenceNode[] nodes = parent.getSubNodes();
         for (int i = 0; i < nodes.length; i++)
         {
            index = Math.max(index, ((CarnotPreferenceNode) nodes[i]).getSortOrder());
         }
         return index + 1;
      }
      return 1;
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
         Class< ? >[] interfaces = element.getClass().getInterfaces();
         Class< ? >[] classes = new Class[interfaces.length + 1];
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
         modelElement = (IModelElement) Proxy.newProxyInstance(classLoader, classes,
               invocationHandler);
      }
      provider.addNodesFor(provider.getParentNodeId(), modelElement,
            new ModelElementAdaptable(new Class[] {IModelElement.class},
                  new Object[] {modelElement}, provider.getAdaptable()), index);
   }
}
