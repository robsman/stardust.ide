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
package org.eclipse.stardust.modeling.core.editors;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.stardust.modeling.common.ui.BpmUiActivator;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import ag.carnot.config.Parameters;
import ag.carnot.workflow.model.Modules;

public class UpgradePage extends EditorPart
{
   private final WorkflowModelEditor cwmEditor;

   private Composite partControl;

   private ControlListener controlListener;
   private PaintListener paintListener;

   private String product;

   private String release;

   private String licensee;

   private String expiration;

   private String processors;

   private String moduleError;

   public UpgradePage(WorkflowModelEditor editor)
   {
      this.cwmEditor = editor;
      updateStrings();
   }

   public String getPageName()
   {
      return Diagram_Messages.TXT_OLD_MD_FORMAT;
   }

   public void dispose()
   {
      if ( !partControl.isDisposed())
      {
         partControl.removeControlListener(controlListener);
         this.controlListener = null;
         
         partControl.removePaintListener(paintListener);
         this.paintListener = null;
      }
      
      super.dispose();
   }

   public void doSave(IProgressMonitor monitor)
   {
      cwmEditor.doSave(monitor);
   }

   public void doSaveAs()
   {
      cwmEditor.doSaveAs();
   }

   public boolean isSaveAsAllowed()
   {
      return cwmEditor.isSaveAsAllowed();
   }

   public final boolean isDirty()
   {
      return cwmEditor.isDirty();
   }

   public void init(IEditorSite site, IEditorInput input) throws PartInitException
   {
      setSite(site);
      setInput(input);

      setPartName(getPageName());
   }

   public void setFocus()
   {
/*      if ((null != partControl) && !partControl.isDisposed())
      {
         partControl.setFocus();
      }*/
   }

   public final void createPartControl(Composite parent)
   {
      this.partControl = new Composite(parent, SWT.NONE);
      
      GridLayout pageLayout = new GridLayout(1, false);
      pageLayout.marginHeight = 0;
      pageLayout.marginWidth = 0;
      pageLayout.verticalSpacing = 0;
      pageLayout.horizontalSpacing = 0;
      partControl.setLayout(pageLayout);
      
      Composite topPanel = new Composite(partControl, SWT.NONE);
      topPanel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
      
      GridLayout layout = new GridLayout(3, false);
      layout.marginHeight = 10;
      layout.marginWidth = 10;
      layout.verticalSpacing = 5;
      layout.horizontalSpacing = 5;
      topPanel.setLayout(layout);
      topPanel.setBackground(parent.getDisplay().getSystemColor(
            SWT.COLOR_LIST_BACKGROUND));
      topPanel.setForeground(parent.getDisplay().getSystemColor(
            SWT.COLOR_LIST_FOREGROUND));

      // label on top
      
      Label label = FormBuilder.createLabel(topPanel, getTitle(), 3);
      label.setFont(JFaceResources.getFontRegistry().get(JFaceResources.HEADER_FONT));
      label.setBackground(topPanel.getBackground());
      label.setForeground(topPanel.getForeground());

      Label lblLicensePath = FormBuilder.createLabel(topPanel, Diagram_Messages.LBL_MD_MUST_BE_UPGRADE);
      lblLicensePath.setBackground(topPanel.getBackground());
      lblLicensePath.setForeground(topPanel.getForeground());
      FormBuilder.createButton(topPanel, Diagram_Messages.BUT_UPRGADE_NOW, new SelectionListener() 
      {
         public void widgetSelected(SelectionEvent e)
         {
            cwmEditor.doUpgradeModel();
         }

         public void widgetDefaultSelected(SelectionEvent e)
         {}
      });
      
      FormBuilder.createButton(topPanel, Diagram_Messages.BUT_CLOSE_EDITOR, new SelectionListener() 
      {
         public void widgetSelected(SelectionEvent e)
         {
            cwmEditor.closeEditor(false);
         }

         public void widgetDefaultSelected(SelectionEvent e)
         {}
      });

      // now the main editor page
      Composite logoPanel = new Composite(partControl, SWT.NONE);
      logoPanel.setLayout(new GridLayout());
      logoPanel.setBackground(parent.getDisplay().getSystemColor(
            SWT.COLOR_LIST_BACKGROUND));
      logoPanel.setForeground(parent.getDisplay().getSystemColor(
            SWT.COLOR_LIST_FOREGROUND));
      logoPanel.setLayoutData(new GridData(GridData.FILL_BOTH));
      createPageControl(logoPanel);
   }

   protected void createPageControl(Composite parent)
   {
      // billboard image
      final Image image = DiagramPlugin.getImage("icons/default_panel.gif"); //$NON-NLS-1$
      this.controlListener = new ControlListener()
      {
         public void controlMoved(ControlEvent e)
         {}

         public void controlResized(ControlEvent e)
         {
            Control control = (Control) e.widget;
            control.redraw();
         }
      };
      parent.addControlListener(controlListener);

      this.paintListener = new PaintListener()
      {
         public void paintControl(PaintEvent e)
         {
            Rectangle size = image.getBounds();
            Control control = (Control) e.widget;
            Point pageSize = control.getSize();
            int left = (pageSize.x - size.width) / 2;

            Color foreground = e.gc.getForeground();
            int width = size.width; 
            int start = 5;
            
            if (product != null)
            {
               start = drawString(e.gc, MessageFormat.format(
                     release == null ? product : Diagram_Messages.LicensePage_ProductWithRelease,
                     new Object[] {product, release}), left, start,
                     ColorConstants.darkBlue, ColorConstants.white, 1, width);
            }
            if (moduleError != null)
            {
               int leftError = left + 70;
               start = drawString(e.gc, moduleError,
                  leftError, start + 8, new Color(null, 166, 0, 0), ColorConstants.darkGray, 1, width);
            }
            else
            {
               start = drawString(e.gc, MessageFormat.format(Diagram_Messages.LicensePage_Product,
                     new Object[] {licensee}), left, start + 8,
                     ColorConstants.darkBlue, ColorConstants.white, 1, width);
               start = drawString(e.gc, MessageFormat.format(Diagram_Messages.LicensePage_Expiration,
                     new Object[] {expiration}), left, start,
                     ColorConstants.darkBlue, ColorConstants.white, 1, width);
               start = drawString(e.gc, MessageFormat.format(Diagram_Messages.LicensePage_ValidCPUs,
                     new Object[] {processors}), left, start,
                     ColorConstants.darkBlue, ColorConstants.white, 1, width);
            }
            e.gc.setForeground(foreground);
            
            int top = Math.max((pageSize.y - size.height) / 2, start);
            e.gc.drawImage(image, left, top);
         }
      };
      parent.addPaintListener(paintListener);
   }


   private int drawString(GC gc, String fullText, int left, int start, Color base,
         Color shadow, int delta, int maxWidth)
   {
      ArrayList paragraphs = splitParagraphs(fullText);
      for (int p = 0; p < paragraphs.size(); p++)
      {
         ArrayList splits = splitString(gc, (String) paragraphs.get(p), maxWidth);
         for (int i = 0; i < splits.size(); i++)
         {
            String text = (String) splits.get(i);
//          disable shadow as per change from CARNOT to Infinity/SunGard brand.            
//          gc.setForeground(shadow);
//          gc.drawString(text, left + delta, start + delta, true);
            gc.setForeground(base);
            gc.drawString(text, left, start, true);
            start += gc.getFontMetrics().getHeight();
         }
         if (p < paragraphs.size() - 1)
         {
            start += 8; // explicit new paragraph
         }
      }
      return start;
   }

   private ArrayList splitParagraphs(String text)
   {
      ArrayList list = new ArrayList();
      int pos = 0;
      while (pos >= 0)
      {
         int newPos = text.indexOf("\n\n", pos); //$NON-NLS-1$
         if (newPos < 0)
         {
            list.add(text.substring(pos));
            pos = newPos;
         }
         else
         {
            list.add(text.substring(pos, newPos));
            pos = newPos + 2;
         }
      }
      return list;
   }

   private ArrayList splitString(GC gc, String text, int maxWidth)
   {
      text = text.replace('\n', ' ');
      ArrayList list = new ArrayList();
      int pos = 0;
      while (pos >= 0 && gc.stringExtent(text.substring(pos)).x > maxWidth)
      {
         int breakPos = text.lastIndexOf(' ');
         while (breakPos > pos && gc.stringExtent(text.substring(pos, breakPos)).x > maxWidth)
         {
            breakPos = text.lastIndexOf(' ', breakPos - 1);
         }
         if (breakPos > pos)
         {
            list.add(text.substring(pos, breakPos));
            pos = breakPos + 1;
         }
         else
         {
            pos = -1;
         }
      }
      list.add(text.substring(pos));
      return list;
   }

   public Control getPartControl()
   {
      return partControl;
   }

   public void redraw()
   {
      updateStrings();
      partControl.redraw();
   }

   private void updateStrings()
   {
      String moduleName = Modules.PROCESS_WORKBENCH_4_DEVELOPERS; 
      if (DiagramPlugin.isBusinessPerspective())
      {
         moduleName = Modules.PROCESS_WORKBENCH_4_ANALYSTS;
      }
      else
      {
         if (Parameters.instance().getString("License." + moduleName + ".product") == null) //$NON-NLS-1$ //$NON-NLS-2$
         {
            // try old modeling license
            if (Parameters.instance().getString("License." + Modules.MODELLING + ".product") != null) //$NON-NLS-1$ //$NON-NLS-2$
            {
               moduleName = Modules.MODELLING;
            }
         }
      }
      moduleError = MessageFormat.format(Diagram_Messages.ERROR_UNSUPPORTED_MD_VERSION, new Object[]{cwmEditor.getWorkflowModel().getCarnotVersion()});
      
      product = BpmUiActivator.getDefault().getString("License." + moduleName + ".product"); //$NON-NLS-1$ //$NON-NLS-2$
      release = BpmUiActivator.getDefault().getString("License." + moduleName + ".release"); //$NON-NLS-1$ //$NON-NLS-2$
      licensee = BpmUiActivator.getDefault().getString("License." + moduleName + ".licensee", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      expiration = BpmUiActivator.getDefault().getString("License." + moduleName + ".expiration", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      processors = BpmUiActivator.getDefault().getString("License." + moduleName + ".processors", "0"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }
}
