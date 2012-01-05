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
package org.eclipse.stardust.modeling.debug.debugger.types;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.modeling.debug.Constants;

import ag.carnot.workflow.model.Application;
import ag.carnot.workflow.model.ApplicationContext;

public class ApplicationDigest extends AbstractJavaTypeValue
{
   private NamedValue[] properties;
   
   /**
    * This constuctor is called from within the moonglow plugin.
    * 
    * @param name
    * @param value
    */
   public ApplicationDigest(IVariable variable)
   {
      super(variable);
      
      try
      {
         IVariable[] subVariables = getAssociatedVariable().getValue().getVariables();

         Object[] result = JavaTypeValueFactory
               .createArrayInstance("properties", subVariables); //$NON-NLS-1$
         if (null != result)
         {
            properties = new NamedValue[result.length];
            System.arraycopy(result, 0, properties, 0, result.length);
         }
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
   
   /**
    * This constructor is called from within the debugging engine. Breakpoints on it have
    * do be set programmatically while launching the debugging engine.
    * 
    * @param application
    */
   public ApplicationDigest(Application application)
   {
      super(null);

      Assert.isNotNull(application);
      
      properties = new NamedValue[application.getAllAttributes().size()];
      int propIdx = 0;
      for (Iterator propIter = application.getAllAttributes().entrySet()
            .iterator(); propIter.hasNext();)
      {
         Map.Entry entry = (Map.Entry) propIter.next();

         properties[propIdx] = new NamedValue((String) entry.getKey(),
               Reflect.convertObjectToString(entry.getValue()));

         ++propIdx;
      }
   }

   /**
    * This constructor is called from within the debugging engine. Breakpoints on it have
    * do be set programmatically while launching the debugging engine.
    * <p>
    * The application is built by the selected application context.
    * 
    * @param applicationContext
    */
   public ApplicationDigest(ApplicationContext applicationContext)
   {
      super(null);

      Assert.isNotNull(applicationContext);
      
      properties = new NamedValue[applicationContext.getAllAttributes().size()];
      int propIdx = 0;
      for (Iterator propIter = applicationContext.getAllAttributes().entrySet()
            .iterator(); propIter.hasNext();)
      {
         Map.Entry entry = (Map.Entry) propIter.next();

         properties[propIdx] = new NamedValue((String) entry.getKey(),
               Reflect.convertObjectToString(entry.getValue()));

         ++propIdx;
      }
   }

   /**
    * @return Returns the properties.
    */
   public NamedValue[] getProperties()
   {
      return properties;
   }
   
}
