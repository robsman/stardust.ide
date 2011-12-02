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
package org.eclipse.stardust.modeling.core;

/**
 * @author fherinean
 * @version $Revision$
 */
import java.text.DecimalFormatSymbols;

import org.eclipse.swt.events.VerifyListener;

public interface Verifier extends VerifyListener
{
   public DecimalFormatSymbols symbols = new DecimalFormatSymbols();

   int BYTE = 0;
   int SHORT = 1;
   int INT = 2;
   int LONG = 3;
   int FLOAT = 4;
   int DOUBLE = 5;
   int MONEY = 6;
   int PERIOD = 7;
   int OVERDUE_THRESHOLD = 8;

   char INTERNAL_GROUPING_SEPARATOR = ',';
   char INTERNAL_DECIMAL_SEPARATOR = '.';
   char INTERNAL_EXPONENTIAL_SIGN = 'E';
   char ALTERNATE_EXPONENTIAL_SIGN = 'e';
   char INTERNAL_MINUS_SIGN = '-';

   String getExternalValue(String value);

   String getInternalValue(String value);

   int getType();
}
