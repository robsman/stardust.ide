/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.core;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

public class BlacklistVerifier implements VerifyListener
{
   private String[] blacklist;

   public BlacklistVerifier(String... blacklist)
   {
      this.blacklist = blacklist;
   }

   public void verifyText(VerifyEvent e)
   {
      StringBuffer sb = new StringBuffer(((Text) e.widget).getText());
      sb.replace(e.start, e.end, e.text);
      String result = sb.toString();
      if (blacklist != null)
      {
         for (int i = 0; i < blacklist.length; i++)
         {
            if (result.equals(blacklist[i]))
            {
               e.doit = false;
               break;
            }
         }
      }            
   }
}
