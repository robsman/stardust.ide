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
public class JaxWSTypesPropertyPage extends
   AbstractModelElementPropertyPage
{
   private TableViewer viewer;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      viewer.setInput(getSynchronizer().getMappedParts());
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
      Table tree = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      tree.setHeaderVisible(true);
      tree.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(tree);
      tree.addMouseListener(new MouseAdapter()
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
            Diagram_Messages.LB_PartName,
            Diagram_Messages.COL_NAME_JavaType};
      
      viewer = new TableViewer(tree);
      TableUtil.createColumns(tree, columnProperties);
      TableUtil.setInitialColumnSizes(tree, new int[] {50, 50});

      TableLabelProvider labelProvider = new DefaultTableLabelProvider(null)
      {
         public String getText(int index, Object element)
         {
            if (element instanceof Part)
            {
               Part name = (Part) element;
               switch (index)
               {
                  case 0: return name.getName();
                  case 1: return getSynchronizer().getMapping(name);
               }
            }
            return super.getText(index, element);
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
