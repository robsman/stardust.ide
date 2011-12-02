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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledCombo;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.javascript.editor.EditorUtils;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor.RegionWithLineOffset;
import org.eclipse.stardust.modeling.javascript.editor.controller.JavaScriptEditorController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import ag.carnot.base.StringUtils;


/**
 * @author fherinean
 * @version $Revision$
 */
public class TransitionPropertyPage extends IdentifiablePropertyPage
{
   private ComboViewer targetViewer;   
   private Composite sourceViewerComposite;
   private Label conditionExpression;
   
   private Button forkButton;
   private JSCompilationUnitEditor transitionConditionEditor;
   private JavaScriptEditorController controller = new JavaScriptEditorController();
   
   private IProject project;
   private ModelType model;
   
   public static final String[] EXPRESSION_TYPES = {
      "CONDITION", "OTHERWISE"  //$NON-NLS-1$//$NON-NLS-2$
   };
   
   public void dispose()
   {
      super.dispose();
      EditorUtils.deleteFileStructure(project, model);      
   }

   protected void performDefaults()
   {
      super.performDefaults();
      TransitionType transition = (TransitionType) getModelElement();
      String expression = getExpression(transition);
      String condition = transition.getCondition();
      if(StringUtils.isEmpty(condition))
      {
         condition = EXPRESSION_TYPES[0];
      }      
      targetViewer.setSelection(new StructuredSelection(condition));            
      setSelection(condition, expression);      
   }

   private String getExpression(TransitionType transition)
   {
      XmlTextNode type = transition.getExpression();
      String expression = type == null ? null : ModelUtils.getCDataString(transition.getExpression().getMixed());
      return expression;
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ModelType model = ModelUtils.findContainingModel(element);
      controller.intializeModel(model);       
      
      super.loadFieldsFromElement(symbol, element);
      TransitionType transition = (TransitionType) element;
      forkButton.setSelection(transition.isForkOnTraversal());
      String expression = getExpression(transition);
      // <Condition Type="OTHERWISE"/>
      String condition = transition.getCondition();
      // for older models, but maybe this should be done by an upgrade job
      if(StringUtils.isEmpty(condition))
      {
         condition = EXPRESSION_TYPES[0];
      }
      setSelection(condition, expression);
      targetViewer.setSelection(new StructuredSelection(condition));            
      refreshDocument();   
      transitionConditionEditor.getAdaptedSourceViewer().getTextWidget().setText(expression == null ? "" : expression.trim()); //$NON-NLS-1$      
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadElementFromFields(symbol, element);
      TransitionType transition = (TransitionType) element;
      transition.setForkOnTraversal(forkButton.getSelection());
      String condition = transitionConditionEditor.getAdaptedSourceViewer().getTextWidget().getText().trim();      
      String selection = (String) ((IStructuredSelection) targetViewer.getSelection()).getFirstElement();
      
      transition.setCondition(selection);
      if(selection.equals(EXPRESSION_TYPES[1]))
      {
         transition.setExpression(null);            
      }
      else
      {
         XmlTextNode expression = transition.getExpression();
         if (expression == null)
         {
            expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
            transition.setExpression(expression);
         }
         if(!StringUtils.isEmpty(condition))
         {
            ModelUtils.setCDataString(expression.getMixed(), condition, true);                     
         }
         else
         {
            ModelUtils.setCDataString(expression.getMixed(), "true", true); //$NON-NLS-1$
         }
      }
   }

   protected void contributeExtraControls(Composite composite)
   {
      model = ModelUtils.findContainingModel(getModelElement());
      
      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      IEditorSite editorSite = editor.getEditorSite();      
      
      project = ModelUtils.getProjectFromEObject(getModelElement());

      forkButton = FormBuilder.createCheckBox(composite,
            Diagram_Messages.CHECKBOX_ForkOnTraversal, 2);

      FormBuilder.createHorizontalSeparator(composite, 2);
      
      createTypeViewer(composite, Diagram_Messages.LB_ConditionType);      
      conditionExpression = FormBuilder.createLabel(composite, Diagram_Messages.LB_ConditionExpression, 2);

      IFile tempFileResource = GenericUtils.cleanFileStructure(getModelElement(), "conditions.js"); //$NON-NLS-1$

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
      
      conditionExpression.setVisible(false);
      sourceViewerComposite.setVisible(false);               
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
   
   private LabeledViewer createTypeViewer(Composite parent, String label)
   {
      LabeledCombo targetCombo = FormBuilder.createLabeledCombo(parent, label);
      targetViewer = new ComboViewer(targetCombo.getCombo());
      targetViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            for (int i = 0; i < EXPRESSION_TYPES.length; i++)
            {
               if (EXPRESSION_TYPES[i].equals(element))
               {
                  return EXPRESSION_TYPES[i];
               }
            }
            return null;
         }
      });
      for (int i = 0; i < EXPRESSION_TYPES.length; i++)
      {
         targetViewer.add(EXPRESSION_TYPES[i]);
      }
      targetViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            TransitionType transition = (TransitionType) getModelElement();            
            String selection = (String) ((IStructuredSelection) event.getSelection()).getFirstElement();
            String expression = null;
            if(selection.equals(EXPRESSION_TYPES[0]))
            {
               expression = getExpression(transition);
            }
            if(StringUtils.isEmpty(expression))
            {
               expression = "true"; //$NON-NLS-1$
            }
            setSelection(selection, expression); 
         }         
      });      
      LabeledViewer labeledViewer = new LabeledViewer(targetViewer, targetCombo.getLabel());
      return labeledViewer;
   } 
   
   private void setSelection(String selection, String expression)
   {
      if(expression == null)
      {
         expression = ""; //$NON-NLS-1$
      }
      
      if(selection.equals(EXPRESSION_TYPES[1]))
      {
         transitionConditionEditor.getAdaptedSourceViewer().getTextWidget().setText(""); //$NON-NLS-1$
         transitionConditionEditor.getAdaptedSourceViewer().setEditable(false);   
         ((JSCompilationUnitEditor.AdaptedSourceViewer) transitionConditionEditor.getAdaptedSourceViewer()).setAutoCompletion(false);
         sourceViewerComposite.setEnabled(false);    
         conditionExpression.setVisible(false);
         sourceViewerComposite.setVisible(false);               
      }
      else
      {         
         transitionConditionEditor.getAdaptedSourceViewer().setEditable(true);               
         transitionConditionEditor.getAdaptedSourceViewer().getTextWidget().setText(expression.trim());       
         ((JSCompilationUnitEditor.AdaptedSourceViewer) transitionConditionEditor.getAdaptedSourceViewer()).setAutoCompletion(true); 
         sourceViewerComposite.setEnabled(true);
         conditionExpression.setVisible(true);
         sourceViewerComposite.setVisible(true);  
      }    
   }
}