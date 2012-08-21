/*******************************************************************************
 * Copyright (c) 2011 - 2012 SunGard CSA 
 *******************************************************************************/

package org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice;

import javax.wsdl.Part;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.DefaultTableLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;


/**
 * @author fherinean
 * @version $Revision: 57229 $
 */
public class JaxWSTemplatesPropertyPage extends
   AbstractModelElementPropertyPage
{
   private static final String EMPTY_STRING = ""; //$NON-NLS-1$

   private TableViewer viewer;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      viewer.setInput(getSynchronizer().getXmlParts());
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   private Object getSelectedItem()
   {
      IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
      Object selection = sel.getFirstElement();
      return selection;
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
      Table table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      table.setHeaderVisible(true);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(table);
      table.addMouseListener(new MouseAdapter()
      {
         public void mouseDoubleClick(MouseEvent e)
         {
            Object selection = getSelectedItem();
            if (selection instanceof DataPathType)
            {
               selectPageForObject(selection);
            }
         }
      });

      String[] columnProperties = new String[] {
    		  Diagram_Messages.COL_NAME_Name,
    		  Diagram_Messages.COL_NAME_DefaultValue};

      viewer = new TableViewer(table);
      TableUtil.createColumns(table, columnProperties);
      TableUtil.setInitialColumnSizes(table, new int[] {50, 50});

      TableLabelProvider labelProvider = new DefaultTableLabelProvider(null)
      {
         public String getText(int index, Object element)
         {
            Part part = (Part) element;
            switch (index)
            {
               case 0: return part.getName();
               case 1: return getSynchronizer().getTemplate(part);
            }
            return EMPTY_STRING;
         }
      };
      TableUtil.setLabelProvider(viewer, labelProvider, columnProperties);
      viewer.setContentProvider(new ArrayContentProvider());

      return composite;
   }

   private JaxWSOutlineSynchronizer getSynchronizer()
   {
      return (JaxWSOutlineSynchronizer)
         getElement().getAdapter(JaxWSOutlineSynchronizer.class);
   }
}
