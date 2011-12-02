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

import java.util.Map;

import org.eclipse.stardust.model.xpdl.carnot.DiagramType;


/**
 * @author rsauer
 * @version $Revision$
 */
public class DiagramRenderJob
{
   final DiagramType diagram;
   final Map highlighting;
   final String encoding;
   final Integer fontSize;

   boolean done;
   byte[] imgData;
   Throwable error;
   
   DiagramRenderJob(DiagramType diagram, Map highlighting, String encoding,
         Integer fontSize)
   {
      this.diagram = diagram;
      this.highlighting = highlighting;
      this.encoding = encoding;
      this.fontSize = fontSize;
   }
}
