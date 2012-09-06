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
package org.eclipse.stardust.model.xpdl.builder.initializer;


import org.eclipse.stardust.engine.api.runtime.Document;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;

/**
 * @author rsauer
 * @version $Revision: 52562 $
 */
public class DmsDocumentInitializer extends AbstractDmsItemInitializer
      implements IDataInitializer
{
   protected Class<?> getInterfaceType()
   {
      return Document.class;
   }
}
