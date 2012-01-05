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
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.Code;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.QualityControlType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor.RegionWithLineOffset;
import org.eclipse.stardust.modeling.javascript.editor.controller.JavaScriptEditorController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import ag.carnot.workflow.model.PredefinedConstants;

/**
 * @author fherinean
 * @version $Revision: 49787 $
 */
public class ActivityQualityControlPropertyPage extends AbstractModelElementPropertyPage
{
   public static final String QUALITY_CONTROL_ID = "_cwm_quality_control_"; //$NON-NLS-1$
   public static final String QUALITY_CONTROL_LABEL = Diagram_Messages.QUALITY_CONTROL_LABEL;

   private Button qualityControl;
   private JSCompilationUnitEditor transitionConditionEditor;
   private JavaScriptEditorController controller = new JavaScriptEditorController();
   private ComboViewer performerViewer;
   private LabeledText createLabeledText;
   
   protected boolean currentSelection;
   private Combo classCombo;
   private Composite sourceViewerComposite;
   private RoleType createRoleType;
   private String probability;   
   private IModelParticipant currentPerformer;
   private IModelParticipant manualPerformer;

   private void validate()
   {
      if(validateProbability())
      {
         setErrorMessage(Diagram_Messages.QUALITY_CONTROL_PROBABILITY_VALIDATION);
         setValid(false);         
      }
      else if(validatePerformer())
      {
         setErrorMessage(Diagram_Messages.QUALITY_ASSURANCE_PERFORMER_VALIDATION);
         setValid(false);         
      }      
      else if(validatePerformers())
      {
         setErrorMessage(Diagram_Messages.QUALITY_ASSURANCE_PERFORMERS_VALIDATION);
         setValid(false);         
      }            
      else
      {
         setErrorMessage(null);
         setValid(true);         
      }      
   }
   
   private boolean validateProbability()
   {
      boolean error = false;      
      
      if(currentSelection)
      {      
         if(StringUtils.isEmpty(probability))
         {
            error = true;
         }
         else
         {
            try
            {
               int parseInt = Integer.parseInt(probability);
               if((parseInt < 0) || (parseInt > 100))
               {
                  error = true;               
               }            
            }
            catch (NumberFormatException e)
            {
               error = true;            
            }
         }
      }      
      return error;
   }

   private boolean validatePerformer()
   {
      boolean error = false;      
      
      if(currentSelection)
      {      
         if(currentPerformer == null)
         {
            error = true;            
         }
         else if(currentPerformer instanceof ConditionalPerformerType)
         {
            error = true;                        
         }
      }
      
      return error;
   }

   private boolean validatePerformers()
   {
      boolean error = false;      
      
      if(currentSelection)
      {      
         if(currentPerformer != null && manualPerformer != null
               && currentPerformer.equals(manualPerformer))
         {
            error = true;            
         }
      }
      
      return error;
   }
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ActivityType activity = (ActivityType) element;
      
      ModelType model = ModelUtils.findContainingModel(element);
      controller.intializeModel(model);       
      
      refreshDocument();   

      currentSelection = AttributeUtil.getBooleanValue((IExtensibleElement) activity, PredefinedConstants.ACTIVITY_IS_QUALITY_ASSURANCE_ATT);
      qualityControl.setSelection(currentSelection);
      manualPerformer = activity.getPerformer();
      
      setFields(false);

      if(currentSelection)
      {
         IModelParticipant qualityControlPerformer = activity.getQualityControlPerformer();         
         if(qualityControlPerformer != null)
         {
            performerViewer.setSelection(new StructuredSelection(qualityControlPerformer));
         }
         createLabeledText.getText().setText(AttributeUtil.getCDataAttribute((IExtensibleElement) activity, PredefinedConstants.QUALITY_ASSURANCE_PROBABILITY_ATT));
         transitionConditionEditor.getAdaptedSourceViewer().getTextWidget().setText(AttributeUtil.getCDataAttribute((IExtensibleElement) activity, PredefinedConstants.QUALITY_ASSURANCE_FORMULA_ATT));
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ActivityType activity = (ActivityType) element;
      
      if(!currentSelection)
      {
         AttributeUtil.setAttribute((IExtensibleElement) activity, PredefinedConstants.ACTIVITY_IS_QUALITY_ASSURANCE_ATT, null);
         activity.setQualityControlPerformer(null);
         AttributeUtil.setAttribute((IExtensibleElement) activity, PredefinedConstants.QUALITY_ASSURANCE_PROBABILITY_ATT, null);
         AttributeUtil.setAttribute((IExtensibleElement) activity, PredefinedConstants.QUALITY_ASSURANCE_FORMULA_ATT, null);                        
      }
      else
      {
         AttributeUtil.setBooleanAttribute((IExtensibleElement) activity, PredefinedConstants.ACTIVITY_IS_QUALITY_ASSURANCE_ATT, true);         
         activity.setQualityControlPerformer(currentPerformer);
         AttributeUtil.setCDataAttribute((IExtensibleElement) activity, PredefinedConstants.QUALITY_ASSURANCE_FORMULA_ATT, transitionConditionEditor.getAdaptedSourceViewer().getTextWidget().getText());                        
         AttributeUtil.setCDataAttribute((IExtensibleElement) activity, PredefinedConstants.QUALITY_ASSURANCE_PROBABILITY_ATT, createLabeledText.getText().getText());
      }
   }

   private void setFields(boolean preselect)
   {
      if(!currentSelection)
      {
         // disable 
         classCombo.setEnabled(false);
         performerViewer.setSelection(new StructuredSelection(createRoleType));
         createLabeledText.getText().setText("");
         createLabeledText.getText().setEnabled(false);
         transitionConditionEditor.getAdaptedSourceViewer().getTextWidget().setText("");
         sourceViewerComposite.setVisible(true);
         sourceViewerComposite.setEnabled(false);
      }
      else
      {               
         classCombo.setEnabled(true);
         createLabeledText.getText().setEnabled(true);
         transitionConditionEditor.getAdaptedSourceViewer().getTextWidget().setText("true");               
         sourceViewerComposite.setVisible(true);               
         sourceViewerComposite.setEnabled(true);
         
         if(preselect)
         {
            createLabeledText.getText().setText("100");
            
            ActivityType activity = (ActivityType) getModelElement();
            ModelType model = ModelUtils.findContainingModel(activity);
            QualityControlType qualityControlType = model.getQualityControl();
            if(qualityControlType != null)
            {
               EList<Code> validQualityCodes = activity.getValidQualityCodes();
               validQualityCodes.addAll(qualityControlType.getCode());
            }
         }         
      }      
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      qualityControl = FormBuilder.createCheckBox(composite, Diagram_Messages.QUALITY_CONTROL_ACTIVITY, 2);
      qualityControl.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            currentSelection = qualityControl.getSelection();
            setFields(true);            
            validate();
         }
      });            
      
      FormBuilder.createLabelWithRightAlignedStatus(composite, Diagram_Messages.QUALITY_CONTROL_PARTICIPANT);
      classCombo = FormBuilder.createCombo(composite);
      
      performerViewer = new ComboViewer(classCombo);
      performerViewer.setContentProvider(new ArrayContentProvider());
      performerViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            IModelParticipant performer = (IModelParticipant) element;
            return performer.getName();
         }
      });
      
      performerViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IModelParticipant selection = (IModelParticipant) ((IStructuredSelection) performerViewer.getSelection()).getFirstElement();
            if(selection.equals(createRoleType))
            {
               currentPerformer = null;
            }
            else
            {
               currentPerformer = selection;
            }
            validate();
         }         
      });      
      
      ModelType model = ModelUtils.findContainingModel(getModelElement());
      
      List<IModelParticipant> participants = new ArrayList<IModelParticipant>();
      createRoleType = CarnotWorkflowModelFactory.eINSTANCE.createRoleType();
      participants.add(createRoleType);      
      participants.addAll(model.getRole());
      participants.addAll(model.getOrganization());
      participants.addAll(model.getConditionalPerformer());

      performerViewer.setInput(participants.toArray());      

      createLabeledText = FormBuilder.createLabeledText(composite, Diagram_Messages.QUALITY_CONTROL_PROBABILITY);
      createLabeledText.getText().addModifyListener(new ModifyListener()
      {         
         public void modifyText(ModifyEvent e)
         {
            probability = ((Text) e.widget).getText();
            validate();            
         }
      });
      
      FormBuilder.createLabel(composite, Diagram_Messages.QUALITY_CONTROL_FORMULA, 2);      
      
      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      IEditorSite editorSite = editor.getEditorSite();      
      
      IFile tempFileResource = GenericUtils.cleanFileStructure(getModelElement(), "qualitycontrol.js"); //$NON-NLS-1$

      transitionConditionEditor = new JSCompilationUnitEditor();
      
      transitionConditionEditor.setTheSite(editorSite);
      transitionConditionEditor.setInput(new FileEditorInput(tempFileResource));

      sourceViewerComposite = new Composite(composite, SWT.NONE);
      GridData svcData = new GridData();
      svcData.grabExcessHorizontalSpace = true;
      svcData.grabExcessVerticalSpace = true;
      svcData.horizontalSpan = 2;
      svcData.horizontalAlignment = SWT.FILL;
      svcData.verticalAlignment = SWT.FILL;
      sourceViewerComposite.setLayout(new FillLayout());
      sourceViewerComposite.setLayoutData(svcData);
      transitionConditionEditor.createPartControl(sourceViewerComposite);
      
      transitionConditionEditor.getAdaptedSourceViewer().setEditable(true);               
      ((JSCompilationUnitEditor.AdaptedSourceViewer) transitionConditionEditor.getAdaptedSourceViewer()).setAutoCompletion(true); 
      sourceViewerComposite.setEnabled(true);
      sourceViewerComposite.setVisible(true);  

      validate();
      
      return composite;
   }
   
   public void refreshDocument()
   {
      transitionConditionEditor.getAdaptedSourceViewer().getDocument().set(
            controller.getMasterDocument());
      controller.recalculateRegions(transitionConditionEditor.getAdaptedSourceViewer()
            .getDocument());
      
      final RegionWithLineOffset expressionRegion = controller.getExpressionRegion();
      transitionConditionEditor.getAdaptedSourceViewer().setVisibleRegion(
            expressionRegion.getOffset(), expressionRegion.getLength());
      transitionConditionEditor.setLineOffset(expressionRegion.getLineOffset());
   }      
}