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
package org.eclipse.stardust.modeling.core.wizards;

import java.io.File;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.export.DiagramExporter;
import org.eclipse.stardust.modeling.core.utils.ImageFormat;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;


public class ExportCarnotModelDiagramWizard extends Wizard implements IExportWizard
{
   private ExportCarnotModelDiagramWizardPage page;
   private IWorkbench workbench;
   private IStructuredSelection selection;

   public ExportCarnotModelDiagramWizard()
   {
      super();
      setWindowTitle(Diagram_Messages.DESC_ExportDiagram);
   }
   
   public boolean canFinish()
   {
      boolean canFinish = super.canFinish();
      if (page.getDiagram() != null && page.getDiagram().size() > 1)
      {
         File diagramImgPath = new File(page.getFileName());
         canFinish = canFinish ? diagramImgPath.isDirectory() : canFinish;
      }
      return canFinish;
   }

   @SuppressWarnings("unchecked")
   public boolean performFinish()
   {
      List<DiagramType> diagrams = page.getDiagram();
      if (null != diagrams)
      {
         String imageFormat = page.getImageFormat();
         for(DiagramType diagram : diagrams)
         {
            DiagramExporter exporter = new DiagramExporter(diagram);
            File file = new File(diagrams.size() == 1
                  ? page.getFileName()
                  : createFileName(diagram, page.getFileName()).getAbsolutePath());
             if (null != file)
            {
               String fileName = file.getName();
               int pointIdx = fileName.lastIndexOf("."); //$NON-NLS-1$
               if (-1 == pointIdx)
               {
                  file = new File(file.getAbsolutePath() + "." + imageFormat); //$NON-NLS-1$
               }

               if (ImageFormat.PNG.equals(imageFormat))
               {
                  exporter.dumpDiagramToPNGFile(file);
               }
               else if (ImageFormat.JPEG.equals(imageFormat))
               {
                  exporter.dumpDiagramToJPEGFile(file);
               }
               else if (ImageFormat.GIF.equals(imageFormat))
               {
                  exporter.dumpDiagramToGIFFile(file);
               }
            }
         }
         return true;
      }

      return false;
   }

   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      this.workbench = workbench;
      this.selection = selection;
   }

   public void addPages()
   {
      page = new ExportCarnotModelDiagramWizardPage(Diagram_Messages.WIZARD_ExportModel,
            selection, workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor());
      addPage(page);
   }

   private File createFileName(DiagramType diagram, String baseDir)
   {
      StringBuffer buffer = new StringBuffer();
      if (null != baseDir)
      {
         buffer.append(baseDir).append(File.separatorChar);
      }

      ModelType model;
      EObject rawContainer = diagram.eContainer();
      if (rawContainer instanceof ModelType)
      {
         model = (ModelType) rawContainer;
         buffer.append(model.getId()).append("_"); //$NON-NLS-1$
      }
      else if (rawContainer instanceof ProcessDefinitionType)
      {
         ProcessDefinitionType processDefinition = (ProcessDefinitionType) rawContainer;
         model = (ModelType) (processDefinition).eContainer();
         buffer.append(model.getId()).append("_"); //$NON-NLS-1$
         buffer.append(processDefinition.getId()).append("_"); //$NON-NLS-1$
      }
      else
      {
         return null;
      }

      buffer.append(diagram.getName());

      return new File(buffer.toString());
   }
}