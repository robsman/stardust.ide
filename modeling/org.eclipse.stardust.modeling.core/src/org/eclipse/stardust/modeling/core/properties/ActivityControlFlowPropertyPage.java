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

import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XpdlUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetActivityControlFlowCmd;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

public class ActivityControlFlowPropertyPage extends AbstractModelElementPropertyPage
{
   private static final int JOIN = 0;

   private static final int SPLIT = 1;

   private static final int LOOP = 2;

   private static final String[] NAMES = {Diagram_Messages.LB_TITLE_Join, Diagram_Messages.LB_TITLE_Split, Diagram_Messages.LB_TITLE_Loop};

   private Button[] joinButtons;

   private Button[] splitButtons;

   private Button[] loopButtons;

   private LabeledText loopConditionText;

   protected void performDefaults()
   {
      super.performDefaults();
      setSelectedButton(joinButtons, getActivity().getJoin().getValue());
      setSelectedButton(splitButtons, getActivity().getSplit().getValue());
      setSelectedButton(loopButtons, getLoopType().getValue());
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      //WidgetBindingManager wBndMgr = getWidgetBindingManager();

      getActivity();
      setSelectedButton(joinButtons, getActivity().getJoin().getValue());
      setSelectedButton(splitButtons, getActivity().getSplit().getValue());
      setSelectedButton(loopButtons, getLoopType().getValue());

      //wBndMgr.bind(loopConditionText, element, PKG_CWM.getActivityType_LoopCondition());

      if (element instanceof GatewaySymbol)
      {
         switch (((GatewaySymbol) element).getFlowKind().getValue())
         {
         case FlowControlType.JOIN:
            disableButtons(splitButtons);
            break;
         case FlowControlType.SPLIT:
            disableButtons(joinButtons);
            break;
         }
         disableButtons(loopButtons);
         loopConditionText.getText().setEnabled(false);
      }
      else
      {
         enableLoopCondition();
      }

      if (isStartActivity())
      {
         disableButtons(joinButtons);
      }

      if (isEndActivity())
      {
         disableButtons(splitButtons);
      }
   }

   private boolean isEndActivity()
   {
      ActivityType activity = getActivity();
      ProcessDefinitionType process = (ProcessDefinitionType) activity.eContainer();
      List<DiagramType> diagramList = process.getDiagram();
      for (int i = 0; i < diagramList.size(); i++)
      {
         DiagramType diagram = diagramList.get(i);
         List<ActivitySymbolType> activitySymbolList = DiagramUtil.getSymbols(diagram,
            CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ActivitySymbol(),
            activity);
         for (ActivitySymbolType activitySymbol : activitySymbolList)
         {
            List<TransitionConnectionType> outTransitions = activitySymbol.getOutTransitions();
            for (TransitionConnectionType connection : outTransitions)
            {
               if (connection.getTargetActivitySymbol() instanceof EndEventSymbol)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }

   private boolean isStartActivity()
   {
      ActivityType activity = getActivity();
      ProcessDefinitionType process = (ProcessDefinitionType) activity.eContainer();
      List<DiagramType> diagramList = process.getDiagram();
      for (int i = 0; i < diagramList.size(); i++)
      {
         DiagramType diagram = diagramList.get(i);
         List<ActivitySymbolType> activitySymbolList = DiagramUtil.getSymbols(diagram,
            CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ActivitySymbol(),
            activity);
         for (ActivitySymbolType activitySymbol : activitySymbolList)
         {
            List<TransitionConnectionType> inTransitions = activitySymbol.getInTransitions();
            for (TransitionConnectionType connection : inTransitions)
            {
               if (connection.getSourceActivitySymbol() instanceof StartEventSymbol)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }

   private void setSelectedButton(Button[] buttons, int value)
   {
      for (int i = 0; i < buttons.length; i++)
      {
         if (buttons[i] != null)
         {
            buttons[i].setSelection(i == value);
         }
      }
   }

   private void disableButtons(Button[] buttons)
   {
      for (int i = 0; i < buttons.length; i++)
      {
         if (buttons[i] != null)
         {
            buttons[i].setEnabled(false);
         }
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   private void setJoinSplitType(int flowType, int gatewayType)
   {
      SetActivityControlFlowCmd cmd = new SetActivityControlFlowCmd(getEditor(), getActivity(),
            FlowControlType.get(flowType), JoinSplitType.get(gatewayType));
      if (cmd.canExecute())
      {
         cmd.execute();
      }
   }

   private ActivityType getActivity()
   {
      return (ActivityType) getModelElement();
   }

   private LoopType getLoopType()
   {
      ActivityType activity = getActivity();
      org.eclipse.stardust.model.xpdl.xpdl2.LoopType loop = activity.getLoop();
      if (loop != null && loop.getLoopType() == LoopTypeType.STANDARD)
      {
         LoopStandardType loopStandard = loop.getLoopStandard();
         if (loopStandard == null)
         {
            return LoopType.REPEAT_LITERAL;
         }
         switch (loopStandard.getTestTime())
         {
         case BEFORE:
            return LoopType.WHILE_LITERAL;
         case AFTER:
            return LoopType.REPEAT_LITERAL;
         }
      }
      return LoopType.NONE_LITERAL;
   }

   @SuppressWarnings("incomplete-switch")
   private void setLoopType(LoopType type)
   {
      ActivityType activity = getActivity();
      if (type == null || type == LoopType.NONE_LITERAL)
      {
         activity.setLoop(null);
      }
      else
      {
         LoopStandardType loopStandard = getOrCreateLoopStandard(activity);
         switch (type)
         {
         case WHILE_LITERAL:
            loopStandard.setTestTime(TestTimeType.BEFORE);
            break;
         case REPEAT_LITERAL:
            loopStandard.setTestTime(TestTimeType.AFTER);
            break;
         }
      }
   }

   private void setLoopCondition(String condition)
   {
      LoopStandardType loopStandard = getOrCreateLoopStandard(getActivity());
      XpdlUtil.setLoopStandardCondition(loopStandard, condition);
   }

   private static LoopStandardType getOrCreateLoopStandard(ActivityType activity)
   {
      org.eclipse.stardust.model.xpdl.xpdl2.LoopType loop = activity.getLoop();
      LoopStandardType loopStandard = XpdlUtil.getOrCreateLoopStandard(loop);
      if (loop == null)
      {
         activity.setLoop((org.eclipse.stardust.model.xpdl.xpdl2.LoopType) loopStandard.eContainer());
      }
      return loopStandard;
   }

   public Control createBody(Composite parent)
   {
      /*ActivityType activity = getActivity();
      LoopType loopType = activity.getLoopType();
      if (loopType != null)
      {
         // (fh) conversion to xpdl format
         setLoopType(loopType);
         activity.setLoopType(null);
      }
      String condition = activity.getLoopCondition();
      if (condition != null)
      {
         // (fh) conversion to xpdl format
         setLoopCondition(condition);
         activity.setLoopCondition(null);
      }*/

      Composite composite = FormBuilder.createComposite(parent, 1);

      createGroup(composite, joinButtons = new Button[JoinSplitType.VALUES.size()], JOIN,
            JoinSplitType.VALUES, 0);
      createGroup(composite, splitButtons = new Button[JoinSplitType.VALUES.size()],
            SPLIT, JoinSplitType.VALUES, 0);
      Group loopGroup = createGroup(composite, loopButtons = new Button[LoopType.VALUES
            .size()], LOOP, LoopType.VALUES, 1);
      loopButtons[1].addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            enableLoopCondition();
         }
      });

      Composite conditionComposite = FormBuilder.createComposite(loopGroup, 2,
            LoopType.VALUES.size());
      loopConditionText = FormBuilder.createLabeledText(conditionComposite,
         Diagram_Messages.LB_FORMBUILDER_LoopCondition);
      loopConditionText.getText().addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            String condition = loopConditionText.getText().getText().trim();
            String previous = XpdlUtil.getLoopStandardCondition(getActivity().getLoop());
            previous = previous == null ? "" : previous.trim();
            if (!previous.equals(condition))
            {
               setLoopCondition(condition);
            }
         }
      });

      return composite;
   }

   private void enableLoopCondition()
   {
      boolean enabled = !loopButtons[1].getSelection();
      loopConditionText.getText().setEnabled(enabled);
      String condition = "";
      if (enabled)
      {
         org.eclipse.stardust.model.xpdl.xpdl2.LoopType loop = getActivity().getLoop();
         if (loop != null && loop.getLoopType() == LoopTypeType.STANDARD)
         {
            LoopStandardType loopStandard = getOrCreateLoopStandard(getActivity());
            if (loopStandard != null)
            {
               condition = XpdlUtil.getLoopStandardCondition(loopStandard);
            }
         }
      }
      loopConditionText.getText().setText(condition);
   }

   private Group createGroup(Composite composite, Button[] buttons, int type, List<? extends Enumerator> enums,
         int start)
   {
      Group group = FormBuilder.createGroup(composite, NAMES[type], enums.size());
      for (int i = start; i < enums.size(); i++)
      {
         Enumerator rawEnum = enums.get(i);
         buttons[i] = createRadioButton(group,
               ModelUtils.getFlowTypeText(rawEnum.getLiteral()) + "  ", rawEnum.getValue(), type);//$NON-NLS-1$
      }
      return group;
   }

   private Button createRadioButton(Group group, final String name, final int value, final int type)
   {
      Button button = FormBuilder.createRadioButton(group, name);
      button.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            if (((Button) e.widget).getSelection())
            {
               switch (type)
               {
                  case JOIN:
                     setJoinSplitType(FlowControlType.JOIN, value);
                     break;
                  case SPLIT:
                     setJoinSplitType(FlowControlType.SPLIT, value);
                     break;
                  case LOOP:
                     setLoopType(LoopType.getByName(name.trim()));
                     break;
               }
            }
         }

      });
      return button;
   }
}
