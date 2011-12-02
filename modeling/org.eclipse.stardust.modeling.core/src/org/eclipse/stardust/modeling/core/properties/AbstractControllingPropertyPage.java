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
package org.eclipse.stardust.modeling.core.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.core.OverdueThresholdVerifier;
import org.eclipse.stardust.modeling.core.PeriodVerifier;
import org.eclipse.stardust.modeling.core.Verifier;
import org.eclipse.stardust.modeling.core.VerifierFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import ag.carnot.workflow.spi.providers.data.java.Type;

public abstract class AbstractControllingPropertyPage
      extends AbstractModelElementPropertyPage
{
   protected static final String PWH = "carnot:pwh:"; //$NON-NLS-1$

   static final String PERIOD = "period"; //$NON-NLS-1$

   protected static final String OVERDUE_THRESHOLD = "overdueThreshold";//$NON-NLS-1$

   private Text[] fields;

   private HashMap verifiers = new HashMap();

   private LabeledViewer combo;

   public abstract ControllingAttribute[] getControllingAttributes();

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ControllingAttribute[] attributes = getControllingAttributes();
      for (int i = 0; i < attributes.length; i++)
      {
         ControllingAttribute attribute = attributes[i];
         if (Type.Boolean.getId().equals(attributes[i].type))
         {
            boolean value = AttributeUtil.getBooleanValue((IExtensibleElement) element, PWH
                  + attribute.id);
            combo.getViewer().setSelection(
                  new StructuredSelection(attribute.keys.get(Boolean.valueOf(value))));
         }
         else
         {
            String value = AttributeUtil.getAttributeValue((IExtensibleElement) element, PWH
                  + attribute.id);
            Verifier verifier = getVerifier(fields[i], attributes[i].type);
            if (verifier != null)
            {
               value = verifier.getExternalValue(value);
            }
            fields[i].setText(value == null ? "" : value); //$NON-NLS-1$
         }
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ControllingAttribute[] attributes = getControllingAttributes();
      for (int i = 0; i < attributes.length; i++)
      {
         ControllingAttribute attribute = attributes[i];
         if (Type.Boolean.getId().equals(attributes[i].type))
         {
            String label = (String) ((StructuredSelection) combo.getViewer()
                  .getSelection()).getFirstElement();
            Boolean value = (Boolean) attribute.values.get(label);
            AttributeUtil.setBooleanAttribute((IExtensibleElement) element, PWH
                  + attribute.id, value.booleanValue());
         }
         else
         {
            String value = fields[i].getText().trim();
            Verifier verifier = getVerifier(fields[i], attributes[i].type);
            if (verifier != null)
            {
               value = verifier.getInternalValue(value);
            }
            AttributeUtil.setAttribute((IExtensibleElement) element, PWH + attribute.id,
                  value);
         }
      }
   }

   private Verifier getVerifier(Text field, String type)
   {
      if (type == null)
      {
         return null;
      }
      Verifier verifier = (Verifier) verifiers.get(field);
      if (verifier == null)
      {
         verifier = PERIOD.equals(type) ? new PeriodVerifier(field) : OVERDUE_THRESHOLD
               .equals(type) ? new OverdueThresholdVerifier(field) : VerifierFactory
               .getVerifier((Type) Type.getKey(Type.class, type));
         verifiers.put(field, verifier);
      }
      return verifier;
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      ControllingAttribute[] attributes = getControllingAttributes();
      fields = new Text[attributes.length];
      for (int i = 0; i < attributes.length; i++)
      {
         if (Type.Boolean.getId().equals(attributes[i].type))
         {
            combo = FormBuilder.createComboViewer(composite, attributes[i].label + ":", //$NON-NLS-1$
                  new ArrayList(attributes[i].keys.values()));
         }
         else
         {
            FormBuilder.createLabel(composite, attributes[i].label + ":"); //$NON-NLS-1$
            fields[i] = FormBuilder.createText(composite);
            Verifier verifier = getVerifier(fields[i], attributes[i].type);
            if (verifier != null)
            {
               fields[i].addVerifyListener(verifier);
            }
         }
         // FormBuilder.createLabel(composite, attributes[i].measureUnit);
      }

      return composite;
   }

   static class ControllingAttribute
   {
      String label;

      String id;

      String type;

      String measureUnit;
      
      Map values;

      Map keys;

      ControllingAttribute(String label, String id, String type, String measureUnit)
      {
         this.label = label;
         this.id = id;
         this.type = type;
         this.measureUnit = measureUnit;
      }
      
      ControllingAttribute(String label, String id, String type, String measureUnit, Map keys, Map values)
      {
         this(label, id, type, measureUnit);
         this.keys = keys;
         this.values = values;
      }
   }
}
