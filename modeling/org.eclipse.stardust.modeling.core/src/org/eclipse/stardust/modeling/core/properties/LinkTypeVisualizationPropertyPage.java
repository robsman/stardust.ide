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

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.jface.viewers.LabelProvider;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LinkColor;
import org.eclipse.stardust.model.xpdl.carnot.LinkEndStyle;
import org.eclipse.stardust.model.xpdl.carnot.LinkLineStyle;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EFeatureAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EObjectAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class LinkTypeVisualizationPropertyPage extends AbstractModelElementPropertyPage
{
   private Button nameButton;

   private Button roleButton;

   private LabeledViewer colorViewer;

   private LabeledViewer lineViewer;

   private LabeledViewer startSymbolViewer;

   private LabeledViewer endSymbolViewer;

   // the default value is an empty string,
   // however, we don't want to display that as a selection option
   private EFeatureAdapter adapter = new EFeatureAdapter()
   {
      public Object fromModel(EObjectAdapter binding, Object value)
      {
         return value != null && ((Enumerator) value).getValue() < 0 ? null : value;
      }

      public Object toModel(EObjectAdapter binding, Object value)
      {
         return value == null ? binding.getEFeature().getDefaultValue() : value;
      }
   };

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement node)
   {
      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      wBndMgr.getModelBindingManager().bind(node,
            PKG_CWM.getLinkTypeType_ShowLinkTypeName(), nameButton);
      wBndMgr.getModelBindingManager().bind(node,
            PKG_CWM.getLinkTypeType_ShowRoleNames(), roleButton);
      wBndMgr.bind(colorViewer, node, PKG_CWM.getLinkTypeType_LineColor(), adapter);
      wBndMgr.bind(lineViewer, node, PKG_CWM.getLinkTypeType_LineStyle(), adapter);
      wBndMgr.bind(startSymbolViewer, node, PKG_CWM.getLinkTypeType_SourceSymbol(),
            adapter);
      wBndMgr
            .bind(endSymbolViewer, node, PKG_CWM.getLinkTypeType_TargetSymbol(), adapter);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createLabeledControlsComposite(parent);

      nameButton = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_ShowName,
            2);
      roleButton = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_ShowRole,
            2);
      FormBuilder.createLabel(composite, " ", 2); //$NON-NLS-1$
      colorViewer = FormBuilder.createComboViewer(composite,
            Diagram_Messages.LB_LineColor, LinkColor.VALUES.subList(1,
                  LinkColor.VALUES.size()));
      lineViewer = FormBuilder.createComboViewer(composite,
            Diagram_Messages.LB_LineType, LinkLineStyle.VALUES.subList(1,
                  LinkLineStyle.VALUES.size()));
      lineViewer.getViewer().setLabelProvider(new LabelProvider()
      {

         @Override
         public String getText(Object element)
         {
            String value = super.getText(element);
            if (value.equals(LinkLineStyle.LONG_STROKES_LITERAL.getLiteral()))
            {
               return Diagram_Messages.LINESTYLE_LONG_STROKES;
            }
            if (value.equals(LinkLineStyle.SHORT_STROKES_LITERAL.getLiteral()))
            {
               return Diagram_Messages.LINESTYLE_SHORT_STROKES;
            }
            if (value.equals(LinkLineStyle.NORMAL_LITERAL.getLiteral()))
            {
               return Diagram_Messages.LINESTYLE_NORMAL_LINE;
            }
            return value;
         }

      });
      
      
      FormBuilder.createLabel(composite, " ", 2); //$NON-NLS-1$
      startSymbolViewer = FormBuilder.createComboViewer(composite,
            Diagram_Messages.LB_StartSymbol, LinkEndStyle.VALUES.subList(1,
                  LinkEndStyle.VALUES.size()));
      
      startSymbolViewer.getViewer().setLabelProvider(new LabelProvider()
      {

         @Override
         public String getText(Object element)
         {
            String value = super.getText(element);
            if (value.equals(LinkEndStyle.EMPTY_RHOMBUS_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_EMPTY_RHOMBUS;
            }
            if (value.equals(LinkEndStyle.EMPTY_TRIANGLE_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_EMPTY_TRIANGLE;
            }
            if (value.equals(LinkEndStyle.FILLED_RHOMBUS_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_FILLED_RHOMBUS;
            }
            if (value.equals(LinkEndStyle.FILLED_TRIANGLE_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_FILLED_TRIANGLE;
            }
            if (value.equals(LinkEndStyle.OPEN_TRIANGLE_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_OPEN_TRIANGLE;
            }
            if (value.equals(LinkEndStyle.NO_ARROW_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_WITHOUT_SYMBOL;
            }
            return value;
         }

      });
      
      
      endSymbolViewer = FormBuilder.createComboViewer(composite,
            Diagram_Messages.LB_EndSymbol, LinkEndStyle.VALUES.subList(1,
                  LinkEndStyle.VALUES.size())); //$NON-NLS-1$
      
      endSymbolViewer.getViewer().setLabelProvider(new LabelProvider()
      {

         @Override
         public String getText(Object element)
         {
            String value = super.getText(element);
            if (value.equals(LinkEndStyle.EMPTY_RHOMBUS_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_EMPTY_RHOMBUS;
            }
            if (value.equals(LinkEndStyle.EMPTY_TRIANGLE_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_EMPTY_TRIANGLE;
            }
            if (value.equals(LinkEndStyle.FILLED_RHOMBUS_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_FILLED_RHOMBUS;
            }
            if (value.equals(LinkEndStyle.FILLED_TRIANGLE_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_FILLED_TRIANGLE;
            }
            if (value.equals(LinkEndStyle.OPEN_TRIANGLE_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_OPEN_TRIANGLE;
            }
            if (value.equals(LinkEndStyle.NO_ARROW_LITERAL.getLiteral()))
            {
               return Diagram_Messages.ENDSTYLE_WITHOUT_SYMBOL;
            }
            return value;
         }

      });
      
      colorViewer.getViewer().setLabelProvider(new LabelProvider()
      {

         @Override
         public String getText(Object element)
         {
            String value = super.getText(element);
            if (value.equals(LinkColor.BLACK_LITERAL.getLiteral()))
            {
               return Diagram_Messages.COLOR_BLACK;
            }
            if (value.equals(LinkColor.DARK_BLUE_LITERAL.getLiteral()))
            {
               return Diagram_Messages.COLOR_DARK_BLUE;
            }
            if (value.equals(LinkColor.DARK_GRAY_LITERAL.getLiteral()))
            {
               return Diagram_Messages.COLOR_DARK_GRAY;
            }
            if (value.equals(LinkColor.BLUE_LITERAL.getLiteral()))
            {
               return Diagram_Messages.COLOR_BLUE;
            }
            if (value.equals(LinkColor.LIGHT_GRAY_LITERAL.getLiteral()))
            {
               return Diagram_Messages.COLOR_LIGHT_GRAY;
            }
            if (value.equals(LinkColor.RED_LITERAL.getLiteral()))
            {
               return Diagram_Messages.COLOR_RED;
            }
            if (value.equals(LinkColor.YELLOW_LITERAL.getLiteral()))
            {
               return Diagram_Messages.COLOR_YELLOW;
            }
            return value;
         }

      });

      return composite;
   }
}
