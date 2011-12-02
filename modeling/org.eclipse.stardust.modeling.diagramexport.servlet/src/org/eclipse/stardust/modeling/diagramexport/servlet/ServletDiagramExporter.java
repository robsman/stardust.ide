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
package org.eclipse.stardust.modeling.diagramexport.servlet;

import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.modeling.core.export.DiagramExporter;


public class ServletDiagramExporter extends DiagramExporter
{
   public ServletDiagramExporter(DiagramType diagram, Integer fontSize)
   {
      super(diagram, fontSize);
   }
}
