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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.stardust.model.xpdl.carnot.DiagramType;

import ag.carnot.base.log.LogManager;
import ag.carnot.base.log.Logger;

/**
 * @author rsauer
 * @version $Revision$
 */
public class DiagramRenderService implements Runnable
{
   private static final Logger trace = LogManager.getLogger(DiagramRenderService.class);

   private static final DiagramRenderJob SHUTDOWN = new DiagramRenderJob(null, null, null, null);

   private List jobs = new LinkedList();

   public synchronized DiagramRenderJob scheduleJob(DiagramType diagram,
         Map highlighting, String encoding, Integer fontSize)
   {
      DiagramRenderJob job = null;

      if ( !jobs.contains(SHUTDOWN))
      {
         job = new DiagramRenderJob(diagram, highlighting, encoding, fontSize);

         jobs.add(job);
         this.notify();
      }
      return job;
   }

   public synchronized void shutDown()
   {
      if ( !jobs.contains(SHUTDOWN))
      {
         jobs.add(SHUTDOWN);
         this.notify();
      }
   }

   public void run()
   {
      DiagramRenderJob currentJob = null;

      do
      {
         synchronized (this)
         {
            while (jobs.isEmpty())
            {
               try
               {
                  this.wait();
               }
               catch (InterruptedException e)
               {
                  // ignore
               }
            }

            currentJob = (DiagramRenderJob) jobs.remove(0);
         }

         if (SHUTDOWN != currentJob)
         {
            trace.info("Executing render job for diagram " + currentJob.diagram);

            try
            {
               ServletDiagramExporter diagramExporter = new ServletDiagramExporter(
                     currentJob.diagram, currentJob.fontSize);
               diagramExporter.setHighligteStates(currentJob.highlighting);

               if ("image/png".equals(currentJob.encoding))
               {
                  currentJob.imgData = diagramExporter.dumpDiagramToPNG();
               }
               else if ("image/gif".equals(currentJob.encoding))
               {
                  currentJob.imgData = diagramExporter.dumpDiagramToGIF();
               }
               else if ("image/jpeg".equals(currentJob.encoding))
               {
                  currentJob.imgData = diagramExporter.dumpDiagramToJPEG();
               }
               else
               {
                  currentJob.imgData = null;
               }
            }
            catch (Throwable t)
            {
               currentJob.error = t;
               currentJob.imgData = null;
            }

            synchronized (currentJob)
            {
               currentJob.done = true;
               currentJob.notify();
            }
            currentJob = null;
         }
      }
      while (currentJob != SHUTDOWN);

      trace.info("Shutting down ...");
   }
}
