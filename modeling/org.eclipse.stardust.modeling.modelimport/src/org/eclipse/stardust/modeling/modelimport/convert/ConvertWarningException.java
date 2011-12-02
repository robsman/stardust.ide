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
package org.eclipse.stardust.modeling.modelimport.convert;

import ag.carnot.workflow.model.IProcessDefinition;

/** 
 *
 *
 * @author kberberich
 * @version $Revision$
 */
public class ConvertWarningException extends Exception
{
   /**
    * to make eclipse happy
    */
   private static final long serialVersionUID = 1L;
   
   private IProcessDefinition processDefinition;

   public ConvertWarningException()
   {
      super(); 
   }

   public ConvertWarningException(String message)
   {
      super(message);
   }

   public IProcessDefinition getProcessDefinition()
   {
      return processDefinition;
   }

   public void setProcessDefinition(IProcessDefinition processDefinition)
   {
      this.processDefinition = processDefinition;
   }
}
