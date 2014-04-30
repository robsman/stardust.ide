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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.LoopType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XpdlUtil;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EFeatureAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EObjectAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetActivityControlFlowCmd;
import org.eclipse.stardust.modeling.core.editors.ui.IdentifiableLabelProvider;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.core.utils.XpdlFeatureAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class ActivityControlFlowPropertyPage extends AbstractModelElementPropertyPage
{
   private static final int JOIN = 0;

   private static final int SPLIT = 1;

   private static final int LOOP = 2;

   private static final String[] NAMES = {Diagram_Messages.LB_TITLE_Join, Diagram_Messages.LB_TITLE_Split, Diagram_Messages.LB_TITLE_Loop};

   private Button[] joinButtons;

   private Button[] splitButtons;

   private Button[] loopButtons;

   private Button[] standardLoopButtons;

   private Button[] multiLoopButtons;

   private LabeledText loopConditionText;

   private Composite standardLoopGroup;

   private Composite multiLoopGroup;

   private org.eclipse.stardust.model.xpdl.xpdl2.LoopType loop;

   private LoopMultiInstanceType loopMulti;

   private LoopStandardType loopStandard;

   private Group loopGroup;

   private LabeledViewer inputViewer;

   private LabeledViewer counterViewer;

   private LabeledViewer outputViewer;

   private String inContext;

   private String outContext;

   protected void performDefaults()
   {
      super.performDefaults();
      setSelectedButton(joinButtons, getActivity().getJoin().getValue());
      setSelectedButton(splitButtons, getActivity().getSplit().getValue());
      setSelectedButton(loopButtons, getLoopButtonIndex());
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      getActivity();
      setSelectedButton(joinButtons, getActivity().getJoin().getValue());
      setSelectedButton(splitButtons, getActivity().getSplit().getValue());
      setSelectedButton(loopButtons, getLoopButtonIndex());

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
         setLoopType(null);
         disableButtons(loopButtons);
      }
      else
      {
         enableLoopGroup();

         TestTimeType test = loopStandard.getTestTime();
         if (test != null)
         {
            standardLoopButtons[test.getValue()].setSelection(true);
         }
         wBndMgr.bind(loopConditionText, loopStandard,
               XpdlPackage.eINSTANCE.getLoopStandardType_LoopCondition(),
               XpdlFeatureAdapter.INSTANCE);

         MIOrderingType ordering = loopMulti.getMIOrdering();
         if (ordering != null)
         {
            multiLoopButtons[ordering.getValue()].setSelection(true);
         }

         wBndMgr.bind(inputViewer, loopMulti.getLoopDataRef(),
               ExtensionPackage.eINSTANCE.getLoopDataRefType_InputItemRef(),
               new AccessPointFeatureAdapter(inputViewer, inContext));
         wBndMgr.bind(counterViewer, loopMulti.getLoopDataRef(),
               ExtensionPackage.eINSTANCE.getLoopDataRefType_LoopCounterRef(),
               new AccessPointFeatureAdapter(counterViewer, inContext));
         wBndMgr.bind(outputViewer, loopMulti.getLoopDataRef(),
               ExtensionPackage.eINSTANCE.getLoopDataRefType_OutputItemRef(),
               new AccessPointFeatureAdapter(outputViewer, outContext));
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

   private int getLoopButtonIndex()
   {
      if (getActivity().getLoop() != null)
      {
         LoopTypeType type = loop.getLoopType();
         if (type != null)
         {
            return type.getValue() + 1;
         }
      }
      return 0;
   }

   private void setLoopType(LoopTypeType type)
   {
      ActivityType activity = getActivity();
      if (type == null)
      {
         activity.setLoop(null);
      }
      else
      {
         if (activity.getLoop() != loop)
         {
            activity.setLoop(loop);
         }
         if (type != loop.getLoopType())
         {
            loop.setLoopType(type);
         }
         setLoopStandard(type == LoopTypeType.STANDARD ? loopStandard : null);
         setLoopMulti(type == LoopTypeType.MULTI_INSTANCE ? loopMulti : null);
      }
   }

   private void setLoopStandard(LoopStandardType loopStandard)
   {
      if (loop.getLoopStandard() != loopStandard)
      {
         loop.setLoopStandard(loopStandard);
      }
   }

   private void setLoopMulti(LoopMultiInstanceType loopMulti)
   {
      if (loop.getLoopMultiInstance() != loopMulti)
      {
         loop.setLoopMultiInstance(loopMulti);
      }
   }

   public Control createBody(Composite parent)
   {
      update2LoopStandard();
      initLoopData();

      Composite composite = FormBuilder.createComposite(parent, 1);
      createGroup(composite, JOIN, JoinSplitType.VALUES);
      createGroup(composite, SPLIT, JoinSplitType.VALUES);
      createLoopGroup(composite);
      return composite;
   }

   private void initLoopData()
   {
      loop = getActivity().getLoop();
      if (loop == null)
      {
         loop = XpdlFactory.eINSTANCE.createLoopType();
      }
      loopStandard = loop.getLoopStandard();
      if (loopStandard == null)
      {
         loopStandard = XpdlFactory.eINSTANCE.createLoopStandardType();
         loopStandard.setTestTime(TestTimeType.BEFORE);
      }
      loopMulti = loop.getLoopMultiInstance();
      if (loopMulti == null)
      {
         loopMulti = XpdlFactory.eINSTANCE.createLoopMultiInstanceType();
         loopMulti.setMIOrdering(MIOrderingType.SEQUENTIAL);

         LoopDataRefType dataRef = loopMulti.getLoopDataRef();
         if (dataRef == null)
         {
            dataRef = ExtensionFactory.eINSTANCE.createLoopDataRefType();
            loopMulti.setLoopDataRef(dataRef);
         }
      }
   }

   private void createLoopGroup(Composite composite)
   {
      List<Enumerator> loopTypes = new ArrayList<Enumerator>();
      loopTypes.add(LoopType.NONE_LITERAL);
      loopTypes.addAll(LoopTypeType.VALUES);

      loopGroup = createGroup(composite, LOOP, loopTypes);
      standardLoopGroup = createStandardLoopGroup(loopGroup);
      multiLoopGroup = createMultiLoopGroup(loopGroup);

      SelectionAdapter listener = new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            enableLoopGroup();
         }
      };
      for (Button loopButton : loopButtons)
      {
         loopButton.addSelectionListener(listener);
      }
   }

   private Composite createStandardLoopGroup(Group loopGroup)
   {
      int cols = TestTimeType.values().length + 1;

      Composite standardGroup = createLoopTypePanel(loopGroup, cols);

      FormBuilder.createLabel(standardGroup, " ");
      standardLoopButtons = new Button[TestTimeType.values().length];
      SelectionAdapter listener = new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            for (TestTimeType test : TestTimeType.values())
            {
               if (standardLoopButtons[test.getValue()].getSelection())
               {
                  if (loopStandard.getTestTime() != test)
                  {
                     loopStandard.setTestTime(test);
                  }
               }
            }
         }
      };
      for (TestTimeType test : TestTimeType.values())
      {
         Button button = FormBuilder.createRadioButton(
               standardGroup, ModelUtils.getFlowTypeText(test.getLiteral()));
         button.addSelectionListener(listener);
         standardLoopButtons[test.getValue()] = button;
      }

      Composite conditionComposite = FormBuilder.createComposite(standardGroup, 2, cols);
      loopConditionText = FormBuilder.createLabeledText(conditionComposite,
         Diagram_Messages.LB_FORMBUILDER_LoopCondition);

      return standardGroup;
   }

   private Composite createLoopTypePanel(Group loopGroup, int cols)
   {
      Composite panel = FormBuilder.createComposite(loopGroup, cols, 3);
      ((GridLayout) panel.getLayout()).makeColumnsEqualWidth = true;
      ((GridLayout) panel.getLayout()).marginWidth = 0;

      Composite separator = FormBuilder.createComposite(panel, 1, cols);
      ((GridLayout) separator.getLayout()).marginWidth = 10;
      ((GridData) separator.getLayoutData()).grabExcessVerticalSpace = false;
      FormBuilder.createHorizontalSeparator(separator, 1);
      return panel;
   }

   private Composite createMultiLoopGroup(Group loopGroup)
   {
      int cols = MIOrderingType.values().length + 1;

      Composite multiGroup = createLoopTypePanel(loopGroup, cols);

      FormBuilder.createLabel(multiGroup, " ");
      multiLoopButtons = new Button[MIOrderingType.values().length];
      SelectionAdapter listener = new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            for (MIOrderingType ordering : MIOrderingType.values())
            {
               if (multiLoopButtons[ordering.getValue()].getSelection())
               {
                  if (loopMulti.getMIOrdering() != ordering)
                  {
                     loopMulti.setMIOrdering(ordering);
                     if (ordering == MIOrderingType.PARALLEL
                           && getActivity().getImplementation() == ActivityImplementationType.SUBPROCESS_LITERAL
                           && getActivity().getSubProcessMode() == SubProcessModeType.SYNC_SHARED_LITERAL)
                     {
                        MessageBox mb = new MessageBox(getShell(), SWT.OK | SWT.ICON_WARNING);
                        mb.setText("Incompatible subprocess execution mode");
                        mb.setMessage("Sub process execution mode will be changed to "
                              + ModelUtils.getSubprocessModeTypeText(SubProcessModeType.SYNC_SEPARATE_LITERAL));
                        mb.open();
                        getActivity().setSubProcessMode(SubProcessModeType.SYNC_SEPARATE_LITERAL);
                     }
                  }
               }
            }
         }
      };
      for (MIOrderingType ordering : MIOrderingType.values())
      {
         Button button = FormBuilder.createRadioButton(
               multiGroup, ModelUtils.getFlowTypeText(ordering.getLiteral()));
         button.addSelectionListener(listener);
         multiLoopButtons[ordering.getValue()] = button;
      }

      IdentifiableLabelProvider labelProvider = new  IdentifiableLabelProvider(getEditor());

      LabelWithStatus inputLabel = FormBuilder.createLabelWithRightAlignedStatus(multiGroup, "Input:");
      LabelWithStatus counterLabel = FormBuilder.createLabelWithRightAlignedStatus(multiGroup, "Counter:");
      LabelWithStatus outputLabel = FormBuilder.createLabelWithRightAlignedStatus(multiGroup, "Output:");

      List<ApplicationContextTypeType> inContexts = ActivityUtil.getContextTypes(getActivity(), DirectionType.IN_LITERAL);
      inContext = filterContext(inContexts);
      List<AccessPointType> inAPs = inContext == null
            ? Collections.<AccessPointType>emptyList()
            : ActivityUtil.getAccessPoints(getActivity(), true, inContext);

      List<ApplicationContextTypeType> outContexts = ActivityUtil.getContextTypes(getActivity(), DirectionType.OUT_LITERAL);
      outContext = filterContext(outContexts);
      List<AccessPointType> outAPs = outContext == null
            ? Collections.<AccessPointType>emptyList()
            : ActivityUtil.getAccessPoints(getActivity(), false, outContext);

      inputViewer = createCombo(multiGroup, labelProvider, inputLabel, inAPs);
      counterViewer = createCombo(multiGroup, labelProvider, counterLabel, inAPs);
      outputViewer = createCombo(multiGroup, labelProvider, outputLabel, outAPs);

      return multiGroup;
   }

   private String filterContext(List<ApplicationContextTypeType> contexts)
   {
      for (int i = contexts.size() - 1; i >= 0; i--)
      {
         String context = contexts.get(i).getId();
         if (!PredefinedConstants.DEFAULT_CONTEXT.equals(context) && !PredefinedConstants.ENGINE_CONTEXT.equals(context))
         {
            return context;
         }
      }
      return null;
   }

   private LabeledViewer createCombo(Composite parent, IdentifiableLabelProvider labelProvider,
         LabelWithStatus label, List<?> values)
   {
      Composite panel = FormBuilder.createComposite(parent, 1, 1);
      ((GridLayout) panel.getLayout()).marginWidth = 0;
      ((GridLayout) panel.getLayout()).marginRight = 10;

      ComboViewer inputCombo = FormBuilder.createComboViewer(panel, values);
      inputCombo.setLabelProvider(labelProvider);
      return new LabeledViewer(inputCombo, label);
   }

   @SuppressWarnings("incomplete-switch")
   private void update2LoopStandard()
   {
      ActivityType activity = getActivity();
      if (activity.getLoop() == null)
      {
         LoopType loopType = activity.getLoopType();
         // (fh) conversion to xpdl format
         if (loopType == LoopType.WHILE_LITERAL || loopType == LoopType.REPEAT_LITERAL)
         {
            setLoopType(LoopTypeType.STANDARD);
            switch (loopType)
            {
            case WHILE_LITERAL:
               loopStandard.setTestTime(TestTimeType.BEFORE);
               break;
            case REPEAT_LITERAL:
               loopStandard.setTestTime(TestTimeType.AFTER);
               break;
            }
            activity.setLoopType(null);
            String condition = activity.getLoopCondition();
            if (condition != null)
            {
               XpdlUtil.setLoopStandardCondition(loopStandard, condition);
               activity.setLoopCondition(null);
            }
         }
      }
   }

   private void enableLoopGroup()
   {
      boolean s = false;
      boolean m = false;

      if (loopButtons[0].getSelection())
      {
         setLoopType((LoopTypeType) null);
      }
      else if (loopButtons[1].getSelection())
      {
         setLoopType(LoopTypeType.STANDARD);
         s = true;
      }
      else if (loopButtons[2].getSelection())
      {
         setLoopType(LoopTypeType.MULTI_INSTANCE);
         m = true;
      }

      setVisible(standardLoopGroup, s);
      setVisible(multiLoopGroup, m);
      loopGroup.layout();
   }

   private static void setVisible(Control control, boolean visible)
   {
      control.setVisible(visible);
      ((GridData) control.getLayoutData()).exclude = !visible;
   }

   private Group createGroup(Composite composite, int type, List<? extends Enumerator> enums)
   {
      Button[] buttons = new Button[enums.size()];
      Group group = FormBuilder.createGroup(composite, NAMES[type], enums.size());
      ((GridLayout) group.getLayout()).makeColumnsEqualWidth = true;
      for (int i = 0; i < enums.size(); i++)
      {
         Enumerator rawEnum = enums.get(i);
         buttons[i] = createRadioButton(group,
               ModelUtils.getFlowTypeText(rawEnum.getLiteral()) + "  ", rawEnum.getValue(), type);//$NON-NLS-1$
         ((GridData) buttons[i].getLayoutData()).horizontalAlignment = SWT.LEFT;
      }
      switch (type)
      {
      case JOIN:
         joinButtons = buttons;
         break;
      case SPLIT:
         splitButtons = buttons;
         break;
      case LOOP:
         loopButtons = buttons;
         break;
      }
      return group;
   }

   private Button createRadioButton(Group group, final String name, final int value, final int type)
   {
      Button button = FormBuilder.createRadioButton(group, name);
      if (type != LOOP)
      {
         SelectionAdapter listener = new SelectionAdapter()
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
                  }
               }
            }
         };
         button.addSelectionListener(listener);
      }
      return button;
   }

   private static class AccessPointFeatureAdapter extends EFeatureAdapter
   {
      private LabeledViewer viewer;
      private String prefix;

      private AccessPointFeatureAdapter(LabeledViewer viewer, String context)
      {
         this.viewer = viewer;
         this.prefix = context == null ? "" : context + ':';
      }

      @Override
      public Object fromModel(EObjectAdapter binding, Object value)
      {
         if (value instanceof String && ((String) value).startsWith(prefix))
         {
            @SuppressWarnings("unchecked")
            List<AccessPointType> accessPoints = (List<AccessPointType>) viewer.getViewer().getInput();
            return ModelUtils.findIdentifiableElement(accessPoints , ((String) value).substring(prefix.length()));
         }
         return super.fromModel(binding, value);
      }

      @Override
      public Object toModel(EObjectAdapter binding, Object value)
      {
         if (value instanceof AccessPointType)
         {
            return prefix + ((AccessPointType) value).getId();
         }
         return super.toModel(binding, value);
      }
   }
}
