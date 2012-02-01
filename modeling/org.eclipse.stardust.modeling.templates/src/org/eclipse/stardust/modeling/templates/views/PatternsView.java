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
package org.eclipse.stardust.modeling.templates.views;


import java.net.URL;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.templates.spi.ITemplate;
import org.eclipse.stardust.modeling.templates.spi.ITemplateFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;



public class PatternsView extends ViewPart
{
   private WorkbenchWindowListener listener;
   private Browser browser;
   private JTextPane textPane;


   public void dispose()
   {
      listener.unregister();
      listener = null;
      super.dispose();
   }

   public void createPartControl(Composite parent)
   {
      listener = new WorkbenchWindowListener(new WorkbenchPageListener(new PartListener(new DropTemplateWorkflowModelEditorAction())));
      listener.register();
      
      FillLayout parentLayout = new FillLayout();      
      parent.setLayout(parentLayout);      
      SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);

      Tree tree = new Tree(sashForm, SWT.BORDER);
      tree.setLinesVisible(false);
      tree.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      Composite composite = FormBuilder.createComposite(sashForm, 1);
      Composite swingComposite = new Composite(composite, SWT.EMBEDDED);
      swingComposite.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      java.awt.Frame locationFrame = SWT_AWT.new_Frame(swingComposite);                 
      textPane = new JTextPane();
      JScrollPane scrollPane = new JScrollPane(textPane);      
      locationFrame.add(scrollPane);
      textPane.setContentType("text/html"); //$NON-NLS-1$
      StyleSheet css = ((HTMLEditorKit)textPane.getEditorKit()).getStyleSheet();
      URL url = this.getClass().getResource("/html/carnot.css"); //$NON-NLS-1$
      css.importStyleSheet(url);
      textPane.setEditable(false);
      sashForm.setWeights(new int[] { 1, 2});      
      final TreeViewer viewer = new TreeViewer(tree);      
      viewer.setContentProvider(new PatternsContentProvider());
      viewer.setLabelProvider(new PatternsLabelProvider());
      viewer.addSelectionChangedListener(new ISelectionChangedListener(){

         public void selectionChanged(SelectionChangedEvent event)
         {
            TreeSelection selection = (TreeSelection) event.getSelection();
            Object object = selection.getFirstElement();
            if (object instanceof ITemplate) {
               ITemplate template = (ITemplate)object;
               textPane.setEditorKit(new ExtendedHTMLEditorKit(template));
               textPane.setText(template.getDescription());
            } else {
               if (object instanceof ITemplateFactory) {
                  ITemplateFactory templateFactory = (ITemplateFactory)object;
                  textPane.setText(templateFactory.getDescription());
               }
            }
         }
         
      });
      

      viewer.addDragSupport(DND.DROP_COPY, new Transfer[] {LocalSelectionTransfer.getTransfer()}, new DragSourceAdapter()
      {
         public void dragStart(DragSourceEvent event)
         {
            ISelection selection = viewer.getSelection();
            LocalSelectionTransfer.getTransfer().setSelection(selection);
            LocalSelectionTransfer.getTransfer().setSelectionSetTime(event.time & 0xFFFFFFFFL);
            event.doit = DropTemplateWorkflowModelEditorAction.isValidDndSelection(selection);
         }

         public void dragSetData(DragSourceEvent event)
         {
            event.data = LocalSelectionTransfer.getTransfer().getSelection();
         }

         public void dragFinished(DragSourceEvent event)
         {
            LocalSelectionTransfer.getTransfer().setSelection(null);
            LocalSelectionTransfer.getTransfer().setSelectionSetTime(0);
        }   
      });

      // Add actions to the local tool bar
      IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
      Action refreshAction = new Action(Diagram_Messages.LB_VersionRepository_Refresh)
      {
         public void run()
         {
            viewer.setInput("org.eclipse.stardust.modeling.templates.templateProvider"); //$NON-NLS-1$
         }
      };
      tbm.add(refreshAction);
      
      refreshAction.run();
   }

   public void setFocus()
   {
      // nothing to do here
   }
}
