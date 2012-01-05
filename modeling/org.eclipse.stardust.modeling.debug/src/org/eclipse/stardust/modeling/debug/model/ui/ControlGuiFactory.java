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
package org.eclipse.stardust.modeling.debug.model.ui;

import java.util.List;
import java.util.Map;

import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConverter;
import org.eclipse.stardust.engine.core.struct.StructuredDataXPathUtils;
import org.eclipse.stardust.modeling.debug.debugger.types.DataField;
import org.eclipse.stardust.modeling.debug.debugger.types.DataMappingDigest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Creates GUI components depending on the data
 */
public class ControlGuiFactory
{
   public static Control create(final IVariable variable, DataMappingDigest dataMappingDigest,
         Composite panel, final Map outValues) throws Exception
   {
      Class mappingClass = Class.forName(dataMappingDigest.getMappedTypeName());
      if (Map.class.isAssignableFrom(mappingClass) || List.class.isAssignableFrom(mappingClass))
      {
         // structured
         final Composite composite = new Composite(panel, SWT.NONE);
         GridData gdField = new GridData(SWT.FILL, GridData.CENTER, true, false);
         gdField.heightHint = 300;
         gdField.widthHint = 300;
         composite.setLayoutData(gdField);
         composite.setLayout(new FormLayout());

         // Create the sash first, so the other controls
         // can be attached to it.
         final Sash sash = new Sash(composite, SWT.VERTICAL);
         FormData data = new FormData();
         data.top = new FormAttachment(0, 0); // Attach to top
         data.bottom = new FormAttachment(100, 0); // Attach to bottom
         data.left = new FormAttachment(30, 0); // Attach halfway across
         sash.setLayoutData(data);
         sash.addSelectionListener(new SelectionAdapter()
         {
            public void widgetSelected(SelectionEvent event)
            {
               // We reattach to the left edge, and we use the x value of the event to
               // determine the offset from the left
               ((FormData) sash.getLayoutData()).left = new FormAttachment(0, event.x);

               // Until the parent window does a layout, the sash will not be redrawn in
               // its new location.
               sash.getParent().layout();
            }
         });

         // Create the first text box and attach its right edge
         // to the sash
         DataField dataField = dataMappingDigest.getDataField();

         final IXPathMap xPathMap = DebuggerUtils.getXPathMapOfCurrentModel(dataField.getDeclaredTypeAdapterId());
         String path = dataField.getDataPath();
         String mappingXPath = StructuredDataXPathUtils.getXPathWithoutIndexes(path);

         Object rootData;
         String value = dataField.getValue();
         if (StringUtils.isEmpty(value))
         {
            rootData = StructuredDataXPathUtils.createInitialValue(xPathMap, mappingXPath);
         }
         else
         {
            StructuredDataConverter structuredDataConverter = new StructuredDataConverter(xPathMap);
            rootData = structuredDataConverter.toCollection(value, path, true);
         }

         final StructuredValue rootValue = new StructuredValue(rootData,
               xPathMap.getXPath(mappingXPath), null, xPathMap);

         StructuredTree left = new StructuredTree(composite, rootValue, xPathMap, variable.supportsValueModification());
         data = new FormData();
         data.top = new FormAttachment(0, 0);
         data.bottom = new FormAttachment(100, 0);
         data.left = new FormAttachment(0, 0);
         data.right = new FormAttachment(sash, 0);
         left.getTree().setLayoutData(data);
         left.getTree().addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {
               // ignore
            }

            public void widgetSelected(SelectionEvent e)
            {
               Tree tree = (Tree) e.widget;
               TreeItem[] selection = tree.getSelection();

               if (selection.length == 1)
               {

                  Control [] children = composite.getChildren();
                  for (int i=0; i<children.length; i++)
                  {
                     if (children[i] instanceof ScrolledComposite)
                     {
                        children[i].dispose();
                     }
                  }
                  StructuredValue parentValue = (StructuredValue) selection[0].getData();
                  StructuredDataModificationListener modificationListener = new StructuredDataModificationListener(
                        variable, outValues, rootValue);
                  boolean editable = variable.supportsValueModification();
                  createStructuredDataPanel(composite, sash, xPathMap, parentValue,
                        modificationListener, editable);

                  composite.layout();
               }
            }
         });

         // Create the second text box and attach its left edge
         // to the sash
         createStructuredDataPanel(composite, sash, xPathMap, null, null, false);

         return composite;
      }
      else
      {
         // old way (text field for everything else)
         Text control = new Text(panel, SWT.BORDER);
         if ("java.lang.Character".equals(dataMappingDigest.getMappedTypeName()))
         {
            control.setTextLimit(1);
         }
         control.setText(variable.getValue().getValueString());

         if (variable.supportsValueModification())
         {
            control.setEditable(true);
            control.addModifyListener(new PrimitiveDataModificationListener(variable, outValues));
         }
         else
         {
            control.setEditable(false);
         }
         GridData gdField = new GridData(SWT.FILL, GridData.CENTER, true, false);
         gdField.widthHint = 300;
         control.setLayoutData(gdField);

         return control;
      }
   }

   private static void createStructuredDataPanel(final Composite parent, final Sash sash,
         final IXPathMap xPathMap, StructuredValue parentValue,
         StructuredDataModificationListener modificationListener, boolean editable)
   {
      ScrolledComposite scroller = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
      scroller.setExpandHorizontal(true);
      scroller.setExpandVertical(true);
      FormData data = new FormData();
      data.top = new FormAttachment(0, 0);
      data.bottom = new FormAttachment(100, 0);
      data.left = new FormAttachment(sash, 0);
      data.right = new FormAttachment(100, 0);
      scroller.setLayoutData(data);
      StructuredDetailsPanel newDetails = new StructuredDetailsPanel(
            scroller, parentValue, xPathMap, modificationListener, editable);
      scroller.setContent(newDetails);
      scroller.setMinSize(newDetails.computeSize(SWT.DEFAULT, SWT.DEFAULT));
   }
}
