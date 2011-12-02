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

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Toggle;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.SimpleRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.stardust.modeling.core.editors.tools.CarnotPaletteEditPartFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import ag.carnot.reflect.Reflect;

public abstract class AbstractGraphicalEditorPage extends EditorPart
{
   private final AbstractMultiPageGraphicalEditor editor;

   private final EditDomain editDomain;

   private PaletteViewer paletteViewer;

   AbstractGraphicalEditorPage(AbstractMultiPageGraphicalEditor editor)
   {
      this.editor = editor;
      this.editDomain = new DefaultEditDomain(this);
      editDomain.setCommandStack(editor.getSharedCommandStack());
      editor.addPropertyListener(new IPropertyListener()
      {
         public void propertyChanged(Object source, int propId)
         {
            if (propId == PROP_INPUT)
            {
               updateTitle(AbstractGraphicalEditorPage.this.editor.getEditorInput());
            }
         }
      });
   }

   public abstract String getPageName();

   public abstract GraphicalViewer getGraphicalViewer();

   protected abstract void createPageControl(Composite parent);

   public void doSave(IProgressMonitor monitor)
   {
      editor.doSave(monitor);
   }

   public void doSaveAs()
   {
      editor.doSaveAs();
   }

   public boolean isSaveAsAllowed()
   {
      return editor.isSaveAsAllowed();
   }

   public final boolean isDirty()
   {
      return editor.isDirty();
   }

   public EditDomain getEditDomain()
   {
      return editDomain;
   }

   public CommandStack getCommandStack()
   {
      return getEditDomain().getCommandStack();
   }

   protected SelectionSynchronizer getSelectionSynchronizer()
   {
      return editor.getSelectionSynchronizer();
   }

   /**
    * Stub implementation. Delegates the creation of the palette to the editor.
    * May be overriden to provide specific palettes depending on the edited part. 
    * 
    * @return the PaletteRoot specific to the editor page
    */
   protected abstract PaletteRoot getPaletteRoot();

   public PaletteViewer getPaletteViewer()
   {
      return paletteViewer;
   }

   public void init(IEditorSite site, IEditorInput input) throws PartInitException
   {
      setSite(site);
      setInput(input);

      updateTitle(input);
   }

   public void updateTitle(IEditorInput input)
   {
      setPartName(input.getName() + ": " + getPageName()); //$NON-NLS-1$
   }

   public void setFocus()
   {
      getGraphicalViewer().getControl().setFocus();
   }

   public final void createPartControl(Composite parent)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout();
      layout.marginHeight = 10;
      layout.marginWidth = 10;
      layout.verticalSpacing = 5;
      layout.horizontalSpacing = 5;
      layout.numColumns = 1;
      composite.setLayout(layout);
      composite.setBackground(parent.getDisplay().getSystemColor(
            SWT.COLOR_LIST_BACKGROUND));
      composite.setForeground(parent.getDisplay().getSystemColor(
            SWT.COLOR_LIST_FOREGROUND));

      // label on top
      final Label label = new Label(composite, SWT.HORIZONTAL | SWT.SHADOW_OUT | SWT.LEFT);
      label.setText(getTitle());
      label.setFont(JFaceResources.getFontRegistry().get(JFaceResources.HEADER_FONT));
      label.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
      label.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_FOREGROUND));
      label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      addPropertyListener(new IPropertyListener()
      {
         public void propertyChanged(Object source, int propId)
         {
            if (propId == IWorkbenchPart.PROP_TITLE)
            {
               label.setText(getTitle());
            }
         }
      });

      // now the main editor page
      composite = new Composite(composite, SWT.NONE);
      composite.setLayout(new FillLayout());
      composite.setBackground(parent.getDisplay().getSystemColor(
            SWT.COLOR_LIST_BACKGROUND));
      composite.setForeground(parent.getDisplay().getSystemColor(
            SWT.COLOR_LIST_FOREGROUND));
      composite.setLayoutData(new GridData(GridData.FILL_BOTH));
      createPageControl(composite);
   }

   protected void registerEditPartViewer(EditPartViewer viewer)
   {
      // register viewer to edit domain
      getEditDomain().addViewer(viewer);

      // the multi page network editor keeps track of synchronizing
      editor.getSelectionSynchronizer().addViewer(viewer);

      // add viewer as selection provider
      getSite().setSelectionProvider(viewer);
   }

   protected void configureEditPartViewer(EditPartViewer viewer)
   {
      // configure the shared key handler
      if (viewer.getKeyHandler() != null)
      {
         viewer.getKeyHandler().setParent(editor.getSharedKeyHandler());
      }
   }

   protected void createPaletteViewer(Composite parent)
   {
      // create graphical viewer
      this.paletteViewer = new PaletteViewer();
      
      // (fh) disable the pin of the palette drawers (4.5)
      /*paletteViewer.getPaletteViewerPreferences().setAutoCollapseSetting(
            PaletteViewerPreferences.COLLAPSE_NEVER);*/
      
      paletteViewer.createControl(parent);            

      // configure the viewer
      paletteViewer.getControl().setBackground(parent.getBackground());
      parent.getParent().getParent().getParent().getBackground();
      paletteViewer.setEditPartFactory(new CarnotPaletteEditPartFactory());
      
      // hook the viewer into the EditDomain (only one palette per EditDomain)
      getEditDomain().setPaletteViewer(paletteViewer);      
      getEditDomain().setPaletteRoot(getPaletteRoot());
      //(rp) This is a Workaround for changes came in with GEF 3.4 (see CRNT-12582)
      fixPaletteLayout();
   }

   private void fixPaletteLayout() {
     if (paletteViewer.getRootEditPart() instanceof SimpleRootEditPart) {
         IFigure figure = ((SimpleRootEditPart)paletteViewer.getRootEditPart()).getFigure();
         try {
             fixFigureLayout(figure.getChildren());                            
         } catch (Throwable t) {
       	  //If something goes wrong stay with the default layout as provided by default by GEF 3.4        	  
         }               	  
     }
   }

   private void fixFigureLayout(List<?> list) {	
	for (Iterator<?> i = list.iterator(); i.hasNext();) {
		Object o = i.next();
		if (o instanceof Toggle) {
			Insets insets = new Insets(0,0,0,0);
			Toggle toggle = (Toggle)o;			
			toggle.setStyle(Clickable.STYLE_BUTTON | Clickable.STYLE_TOGGLE );			
			Object border = Reflect.getFieldValue(toggle, "border"); //$NON-NLS-1$
			Object scheme = Reflect.getFieldValue(border, "scheme");					  //$NON-NLS-1$
			Reflect.setFieldValue(scheme, "insets", insets); //$NON-NLS-1$
			if (toggle.toString().indexOf("DrawerFigure$CollapseToggle") > 0) { //$NON-NLS-1$
				toggle.setSelected(true);
			}
		}
		if (o instanceof IFigure) {
			IFigure figure = (IFigure)o;			
			if (figure.getChildren().size() > 0) {
				fixFigureLayout(figure.getChildren());
			}
			figure.setBackgroundColor(ColorConstants.button);
		}
	}	
   }
}
