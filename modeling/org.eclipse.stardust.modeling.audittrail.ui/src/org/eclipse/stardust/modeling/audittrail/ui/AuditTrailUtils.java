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
package org.eclipse.stardust.modeling.audittrail.ui;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.audittrail.AuditTrailDbManager;
import org.eclipse.swt.widgets.Shell;

/**
 * @author rsauer
 * @version $Revision$
 */
public class AuditTrailUtils
{
   public static final Object NO_AUDIT_TRAIL_DB = new Object();

   public static IFolder createNewDb(Shell shell)
   {
      IFolder result = null;

      InputDialog dlg = new InputDialog(shell, Audittrail_UI_Messages.DIA_NEW_AUDITTRAIL_DB,
            Audittrail_UI_Messages.IP_DIA_ENTER_NAME_OF_NEW_AUDITTRAIL_DB, null, new IInputValidator()
            {
               public String isValid(String newText)
               {
                  String result = null;

                  if (StringUtils.isEmpty(newText))
                  {
                     result = Audittrail_UI_Messages.TXT_NAME_MUST_NOT_BE_EMPTY;
                  }
                  else if (null != AuditTrailDbManager.findDb(newText))
                  {
                     result = Audittrail_UI_Messages.TXT_DB_ALREADY_EXISTS;
                  }
                  return result;
               }
            });
      if (Window.OK == dlg.open())
      {
         result = AuditTrailDbManager.createDb(dlg.getValue());
      }

      return result;
   }
}
