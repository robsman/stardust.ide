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

/**
 * This class provides only one method which is called by communication methods, e.g.
 * {@link ManagedRunner#communicateWithSuspendedActivityInstance}.
 * Calling {@link #suspendThread} will suspend the current thread due to a communication
 * breakpoint set by {@link LaunchDelegate}.
 * <p>
 * The class has to be kept as simple as possible (i.e. no other imports than from jrt),
 * otherwise changing object values in a workflow model debug session will fail due
 * to unresolveable classes.
 *  
 * @author sborn
 * @version $Revision$
 */
public class ManagedRunnerHelper
{
   public static String suspendThreadMethodName = "suspendThread"; //$NON-NLS-1$
   
   /**
    * The current thread suspends due to a communication breakpoint 
    * on entry of this method.
    */
   static void suspendThread()
   {
   }
}
