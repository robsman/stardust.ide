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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ag.carnot.bpm.rt.data.structured.IXPathMap;
import ag.carnot.bpm.rt.data.structured.StructuredDataValueFactory;
import ag.carnot.bpm.rt.data.structured.StructuredDataXPathUtils;
import ag.carnot.bpm.rt.data.structured.TypedXPath;
import ag.carnot.workflow.runtime.beans.BigData;

/**
 * Displays details panel for selected tree element of structured data
 */
public class StructuredDetailsPanel extends Composite
{
   private final StructuredValue parentValue;
   private final IXPathMap xPathMap;

   public StructuredDetailsPanel(Composite parent, StructuredValue parentValue, IXPathMap xPathMap, ModifyListener dataModificationListener, boolean supportsValueModification)
   {
      super(parent, SWT.NONE);
      
      this.xPathMap = xPathMap;
      this.parentValue = parentValue;
      
      if (parentValue == null)
      {
         setLayout(new GridLayout(1, false));
         Label label = new Label(this, SWT.NONE);
         label.setText(Debug_Messages.MSG_SelectType);
         label.setLayoutData(new GridData(SWT.FILL, GridData.CENTER, true, false));
      }
      else
      {
         GridLayout layout = new GridLayout();
         layout.numColumns = 2;
         setLayout(layout);
         if (parentValue.getXPath().getType() == BigData.NULL)
         {
            // display all defined primitive properties of the complex type
            Map complexType = (Map)parentValue.getData();
   
            for (Iterator i = this.xPathMap.getAllXPaths().iterator(); i.hasNext(); )
            {
               TypedXPath typedXPath = (TypedXPath)i.next();
               if (StructuredDataXPathUtils.getParentXPath(typedXPath.getXPath()).equals(parentValue.getXPath().getXPath()) &&
                     typedXPath.isList() == false && typedXPath.getType() != BigData.NULL)
               {
                  // display the primitive
                  createPrimitiveGui(typedXPath, 
                        complexType.get(StructuredDataXPathUtils.getLastXPathPart(typedXPath.getXPath())), dataModificationListener, supportsValueModification);
               }
            }
         } 
         else 
         {
            // display the primitive
            createPrimitiveGui(parentValue.getXPath(), parentValue.getData(), dataModificationListener, supportsValueModification);
         }
      }
   }
   
   private void createPrimitiveGui(final TypedXPath xPath, Object data, ModifyListener dataModificationListener, boolean supportsValueModification)
   {
      Label label = new Label(this, SWT.RIGHT);
      label.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
      final String propertyName = StructuredDataXPathUtils.getLastXPathPart(xPath.getXPath());
      label.setText(propertyName);
      
      final Text text = new Text(this, SWT.BORDER);
      GridData layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false);
      layoutData.widthHint = Dialog.convertWidthInCharsToPixels(getFontMetrics(), 50);
      text.setLayoutData(layoutData);
      String stringRepresentation = StructuredDataValueFactory.convertToString(xPath.getType(), xPath.getXsdTypeName(), data);
      if (stringRepresentation != null)
      {
         text.setText(stringRepresentation);
      }
      if (supportsValueModification)
      {
         text.addModifyListener(new ModifyListener()
         {
            public void modifyText(ModifyEvent e)
            {
               Object objectRepresentation = StructuredDataValueFactory.convertTo(xPath.getType(), text.getText());
               if (xPath.isList() && xPath.getType() != BigData.NULL)
               {
                  // list of primitives, special handling
                  // replace itself in the parent list
                  Map parentMap = (Map)parentValue.getParent().getData();
                  List list = (List) parentMap.get(StructuredDataXPathUtils.getLastXPathPart(parentValue.getXPath().getXPath()));
                  int index = list.indexOf(parentValue.getData());
                  list.set(index, objectRepresentation);
                  
                  parentValue.setData(objectRepresentation);
               }
               else
               {
                  ((Map)parentValue.getData()).put(propertyName, objectRepresentation);
               }
            }
         });
         text.addModifyListener(dataModificationListener);
      } 
      else 
      {
         text.setEditable(false);
      }
   }

   public FontMetrics getFontMetrics()
   {
      GC gc = new GC(this);
      gc.setFont(getFont());
      FontMetrics fm = gc.getFontMetrics();
      gc.dispose();
      return fm;
   }
}
