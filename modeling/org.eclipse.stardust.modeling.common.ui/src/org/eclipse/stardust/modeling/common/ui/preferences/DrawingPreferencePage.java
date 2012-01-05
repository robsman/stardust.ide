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
package org.eclipse.stardust.modeling.common.ui.preferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.UI_Messages;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

public class DrawingPreferencePage extends PreferencePage
      implements IWorkbenchPreferencePage
{
   private Button chkHorizontalModeling;

   private Button chkVerticalModeling;

   private Button chkClassicModeOn;
   
   private Button chkClassicModeOff;
   
   private Button radioOneSymbolToGrid;

   private Button radioAllSymbolsToGrid;

   private Button radioPromptGrid;

   private Button chkViewForkOnTraversal;   
   
   // SnapGrid
   private Button chkEnableSnapGrid;
   private LabeledText snapGridPixel;
   private LabeledText visibleGridFactor;
   
   private Button splitAND;
   private Button splitXOR;
   private Button splitPrompt;

   private Button joinAND;
   private Button joinXOR;
   private Button joinPrompt;   

   public DrawingPreferencePage()
   {}

   public DrawingPreferencePage(String title)
   {
      super(title);
   }

   public DrawingPreferencePage(String title, ImageDescriptor image)
   {
      super(title, image);
   }

   // to validate the values for snap grid
   private ModifyListener snapGridListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         if(!validateSnapGridValues(snapGridPixel.getText().getText())
               && !validateSnapGridValues(visibleGridFactor.getText().getText()))
         {
            setErrorMessage(UI_Messages.WorkbenchPreferencePage_SnapGridValidationErrorMessage);            
            setValid(false);
         }
         else if(!validateSnapGridValues(snapGridPixel.getText().getText()))
         {
            setErrorMessage(UI_Messages.WorkbenchPreferencePage_SnapGridValidationErrorMessage);
            setValid(false);
         }
         else if(!validateSnapGridValues(visibleGridFactor.getText().getText()))
         {
            setErrorMessage(UI_Messages.WorkbenchPreferencePage_SnapGridValidationErrorMessage);
            setValid(false);
         }
         else
         {
            setValid(true);
            setErrorMessage(null);
         }
      }
   };
   
   // validate if values for snap grid are of type integer and not empty
   private boolean validateSnapGridValues(String input)
   {
      boolean valid = true;
      Pattern pattern = Pattern.compile("^\\d+$"); //$NON-NLS-1$
      if(StringUtils.isEmpty(input))
      {
         valid = false;
      }
      else if(input.length() > 2)
      {
         valid = false;
      }
      else
      {
         Matcher pixelMatcher = pattern.matcher(input);
         if(!pixelMatcher.matches())
         {
            valid = false;            
         }
         else
         {
            int value = Integer.parseInt(input);
            if(value <= 0)
            {
               valid = false;               
            }
         }
      }
      return valid;
   }
   
   protected Control createContents(Composite parent)
   {
      Composite panel = FormBuilder.createComposite(parent, 3);

      this.chkViewForkOnTraversal = FormBuilder.createCheckBox(panel,
            UI_Messages.WorkbenchPreferencePage_ViewForkOnTraversalLabel, 3);
      
      this.chkEnableSnapGrid = FormBuilder.createCheckBox(panel,
            UI_Messages.WorkbenchPreferencePage_EnableSnapToGridLabel, 3);

      Group groupSnapGrid = FormBuilder.createGroup(panel,
            UI_Messages.WorkbenchPreferencePage_SnapGridLabel, 2, 3);
      groupSnapGrid.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(3));
      this.snapGridPixel = FormBuilder.createLabeledText(groupSnapGrid, UI_Messages.WorkbenchPreferencePage_SnapGridPixelLabel);
      this.visibleGridFactor = FormBuilder.createLabeledText(groupSnapGrid, UI_Messages.WorkbenchPreferencePage_VisibleGridFactorLabel);
      
      Group group = FormBuilder.createGroup(panel,
            UI_Messages.WorkbenchPreferencePage_ModelingDirectionLabel, 3, 3);
      group.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(3));
      this.chkVerticalModeling = FormBuilder.createRadioButton(group,
            UI_Messages.WorkbenchPreferencePage_VerticalDirection);
      this.chkHorizontalModeling = FormBuilder.createRadioButton(group,
            UI_Messages.WorkbenchPreferencePage_HorizontalDirection);
      Button dummyRadioButtonForAligning = FormBuilder.createRadioButton(group,
            "ignore me"); //$NON-NLS-1$
      dummyRadioButtonForAligning.setEnabled(false);
      dummyRadioButtonForAligning.setVisible(false);

      // Classic Mode
      Group groupClassicMode = FormBuilder.createGroup(panel,
            UI_Messages.WorkbenchPreferencePage_DefaultDiagramMode, 3, 3);
      groupClassicMode.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(3));
      this.chkClassicModeOn = FormBuilder.createRadioButton(groupClassicMode,
            UI_Messages.WorkbenchPreferencePage_DiagramMode_On);
      this.chkClassicModeOff = FormBuilder.createRadioButton(groupClassicMode,
            UI_Messages.WorkbenchPreferencePage_DiagramMode_Off);
      Button dummyRadioButtonForAligningVintage = FormBuilder.createRadioButton(groupClassicMode,
            "ignore me"); //$NON-NLS-1$
      dummyRadioButtonForAligningVintage.setEnabled(false);
      dummyRadioButtonForAligningVintage.setVisible(false);      
      
      Group groupDistributeOption = FormBuilder.createGroup(panel,
            UI_Messages.LB_AutoDistribute, 3, 3);
      groupDistributeOption.setLayoutData(FormBuilder
            .createDefaultSingleLineWidgetGridData(3));
      radioOneSymbolToGrid = FormBuilder.createRadioButton(groupDistributeOption,
            UI_Messages.LB_SnapLastSymbol);
      radioAllSymbolsToGrid = FormBuilder.createRadioButton(groupDistributeOption,
            UI_Messages.LB_SnapAllSymbols);
      radioPromptGrid = FormBuilder.createRadioButton(groupDistributeOption,
            UI_Messages.LB_Prompt);
      
      Group splitType = FormBuilder.createGroup(panel,
              UI_Messages.LB_DefaultSplitType, 3, 3);
      splitType.setLayoutData(FormBuilder
    		  .createDefaultSingleLineWidgetGridData(3));                
      splitAND = FormBuilder.createRadioButton(splitType,
              UI_Messages.LB_AND);
      splitXOR = FormBuilder.createRadioButton(splitType,
              UI_Messages.LB_XOR);
      splitPrompt = FormBuilder.createRadioButton(splitType,
              UI_Messages.LB_Prompt);
      
      Group joinType = FormBuilder.createGroup(panel,
                UI_Messages.LB_DefaultJoinType, 3, 3);
      joinType.setLayoutData(FormBuilder
    		  .createDefaultSingleLineWidgetGridData(3));
      joinAND = FormBuilder.createRadioButton(joinType,
              UI_Messages.LB_AND);
      joinXOR = FormBuilder.createRadioButton(joinType,
              UI_Messages.LB_XOR);
      joinPrompt = FormBuilder.createRadioButton(joinType,
              UI_Messages.LB_Prompt);
      
      updateCheckbox();
      updateSpitJoin();
      updateAutoDistributeGroup();

      return panel;
   }

   private void updateSpitJoin()
   {
	   splitAND.setSelection(PlatformUI.getPreferenceStore().getBoolean(
	              BpmProjectNature.PREFERENCE_SPLIT_AND));
	   splitXOR.setSelection(PlatformUI.getPreferenceStore().getBoolean(
	              BpmProjectNature.PREFERENCE_SPLIT_XOR));
	   splitPrompt.setSelection(PlatformUI.getPreferenceStore().getBoolean(
	              BpmProjectNature.PREFERENCE_SPLIT_PROMPT));
	   
	   joinAND.setSelection(PlatformUI.getPreferenceStore().getBoolean(
	              BpmProjectNature.PREFERENCE_JOIN_AND));
	   joinXOR.setSelection(PlatformUI.getPreferenceStore().getBoolean(
	              BpmProjectNature.PREFERENCE_JOIN_XOR));
	   joinPrompt.setSelection(PlatformUI.getPreferenceStore().getBoolean(
	              BpmProjectNature.PREFERENCE_JOIN_PROMPT));	   
   }   
   
   private void updateAutoDistributeGroup()
   {
      radioOneSymbolToGrid.setSelection(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_ONE_SYMBOL_GRID));
      radioAllSymbolsToGrid.setSelection(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_ALL_SYMBOLS_GRID));
      radioPromptGrid.setSelection(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_PROMPT_GRID));
   }

   private void updateCheckbox()
   {
      chkViewForkOnTraversal.setSelection(PlatformUI.getPreferenceStore()
            .getBoolean(BpmProjectNature.PREFERENCE_VIEW_FORK_ON_TRAVERSAL_MODE));      
      
      // Snap To Grid
      chkEnableSnapGrid.setSelection(PlatformUI.getPreferenceStore()
            .getBoolean(BpmProjectNature.PREFERENCE_SNAP_GRID_MODE));
      snapGridPixel.getText().setText(PlatformUI.getPreferenceStore()
            .getString(BpmProjectNature.PREFERENCE_SNAP_GRID_PIXEL));
      visibleGridFactor.getText().setText(PlatformUI.getPreferenceStore()
            .getString(BpmProjectNature.PREFERENCE_VISIBLE_GRID_FACTOR));
      snapGridPixel.getText().addModifyListener(snapGridListener);
      visibleGridFactor.getText().addModifyListener(snapGridListener);
      
      String direction = PlatformUI.getPreferenceStore().getString(
            BpmProjectNature.PREFERENCE_MODELING_DIRECTION);
      chkVerticalModeling.setSelection(OrientationType.VERTICAL_LITERAL.toString()
            .equals(direction));
      chkHorizontalModeling.setSelection(OrientationType.HORIZONTAL_LITERAL.toString()
            .equals(direction));
      
      boolean classicMode = PlatformUI.getPreferenceStore().getBoolean(
               BpmProjectNature.PREFERENCE_CLASSIC_MODE);
      chkClassicModeOn.setSelection(classicMode);
      chkClassicModeOff.setSelection(!classicMode);      
   }

   public void init(IWorkbench workbench)
   {}

   public boolean performOk()
   {
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_VIEW_FORK_ON_TRAVERSAL_MODE,
            chkViewForkOnTraversal.getSelection());      
      
      // Snap To Grid
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_SNAP_GRID_MODE,
            chkEnableSnapGrid.getSelection());      
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_SNAP_GRID_PIXEL,
            snapGridPixel.getText().getText());      
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_VISIBLE_GRID_FACTOR,
            visibleGridFactor.getText().getText());      

      String direction = BpmProjectNature.DEFAULT_PREFERENCE_MODELING_DIRECTION;
      if (chkVerticalModeling.getSelection())
      {
         direction = OrientationType.VERTICAL_LITERAL.toString();
      }
      else if (chkHorizontalModeling.getSelection())
      {
         direction = OrientationType.HORIZONTAL_LITERAL.toString();
      }
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_MODELING_DIRECTION, direction);

      // use a boolean value for classic mode
      boolean classicMode = BpmProjectNature.DEFAULT_PREFERENCE_CLASSIC_MODE;
      if (chkClassicModeOn.getSelection())
      {
         classicMode = true;
      }
      else if (chkClassicModeOff.getSelection())
      {
         classicMode = false;
      }
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_CLASSIC_MODE, classicMode);
      
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_ONE_SYMBOL_GRID,
            radioOneSymbolToGrid.getSelection());
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_ALL_SYMBOLS_GRID,
            radioAllSymbolsToGrid.getSelection());
      PlatformUI.getPreferenceStore().setValue(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_PROMPT_GRID,
            radioPromptGrid.getSelection());

      PlatformUI.getPreferenceStore().setValue(
              BpmProjectNature.PREFERENCE_SPLIT_AND,
              splitAND.getSelection());
      PlatformUI.getPreferenceStore().setValue(
              BpmProjectNature.PREFERENCE_SPLIT_XOR,
              splitXOR.getSelection());
      PlatformUI.getPreferenceStore().setValue(
              BpmProjectNature.PREFERENCE_SPLIT_PROMPT,
              splitPrompt.getSelection());
      
      PlatformUI.getPreferenceStore().setValue(
              BpmProjectNature.PREFERENCE_JOIN_AND,
              joinAND.getSelection());
      PlatformUI.getPreferenceStore().setValue(
              BpmProjectNature.PREFERENCE_JOIN_XOR,
              joinXOR.getSelection());
      PlatformUI.getPreferenceStore().setValue(
              BpmProjectNature.PREFERENCE_JOIN_PROMPT,
              joinPrompt.getSelection());
      
      return true;
   }

   protected void performDefaults()
   {
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_MODELING_DIRECTION);
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_CLASSIC_MODE);      
      
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_ONE_SYMBOL_GRID);
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_ALL_SYMBOLS_GRID);
      PlatformUI.getPreferenceStore().setToDefault(
            BpmProjectNature.PREFERENCE_DISTRIBUTE_PROMPT_GRID);

      updateCheckbox();
      updateSpitJoin();
      updateAutoDistributeGroup();
   }
}