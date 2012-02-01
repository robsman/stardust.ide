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
package org.eclipse.stardust.modeling.templates.basic.ui;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.anchors.TransitionConnectionAnchor;
import org.eclipse.stardust.modeling.templates.basic.Templates_Basic_Messages;
import org.eclipse.stardust.modeling.templates.defaulttemplate.TemplateHelper;



public class ParallelSplitTemplateWizard extends Wizard  {
	private ParallelSplitTemplatePage newTemplatePage; 
	private WorkflowModelEditor editor;
	private ModelType model;
	private DiagramType diagram;
	private ProcessDefinitionType process;
	private int xHint;
	private int yHint;
	private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;   
	private static CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;	
	private EditPart editPart;
	private TemplateHelper templateHelper;
	private XmlTextNode expression;

   private boolean killed;

	public ParallelSplitTemplateWizard()
   {
      super();
      // TODO Auto-generated constructor stub
   }
	
   public ParallelSplitTemplateWizard(WorkflowModelEditor targetEditor, ModelType targetModel,
         DiagramType targetDiagram, EditPart editPart, int xHint, int yHint)
   {
	 super();
	 this.setWindowTitle(Templates_Basic_Messages.TXT_APPLY_PARALLEL_SPLIT_TEMPLATE);
	 this.editPart = editPart;
	 model = targetModel;
	 editor = targetEditor;
	 diagram = targetDiagram; 
	 process = (ProcessDefinitionType)targetDiagram.eContainer();
	 this.xHint = xHint;
	 this.yHint = yHint;
	 if (editPart.getModel() != null && editPart.getModel() instanceof ISymbolContainer) {
	     this.xHint = 5;
	     this.yHint = 5;
	    
	 }
	 templateHelper = new TemplateHelper(model, diagram,  process, editPart);
   }

   public void addPages() {
		super.addPages();
		newTemplatePage = new ParallelSplitTemplatePage(Templates_Basic_Messages.TXT_AND_SPLIT_TEMPLATE, Templates_Basic_Messages.TXT_AND_SPLIT_TEMPLATE, null); 
		this.addPage(newTemplatePage);
	}

	public boolean performFinish() {

		String kind = newTemplatePage.getKind();
	    int number = Integer.parseInt(newTemplatePage.getNumber());
		int x = xHint;
		int y = yHint;
		
		//Create activity which contains the AND Split
		ActivityType rootActivity = templateHelper.createActivity(kind + " 0", kind);		 //$NON-NLS-1$
		rootActivity.setSplit(JoinSplitType.AND_LITERAL);				
        ActivitySymbolType rootActivitySymbol = templateHelper.createActivitySymbol(rootActivity, x, y);              
		GatewaySymbol gatewaySymbol = templateHelper.createGatewaySymbol(rootActivitySymbol, FlowControlType.SPLIT_LITERAL);
		y = y + 100;
		gatewaySymbol.setXPos(x);
		gatewaySymbol.setYPos(y);
        TransitionConnectionType rootTransitionSymbol = templateHelper.createTransitionSymbol(null);
        rootTransitionSymbol.setSourceActivitySymbol(rootActivitySymbol);
        rootTransitionSymbol.setTargetActivitySymbol(gatewaySymbol);
		//Create the split activities for the split
        y = y + 100;
		for (int i = 0; i < number; i++) {		   
		   ActivityType splitActivity = templateHelper.createActivity(kind + " " + (i + 1), kind); //$NON-NLS-1$
		   ActivitySymbolType splitActivitySymbol = templateHelper.createActivitySymbol(splitActivity, x, y);
		   TransitionType splitTransition = templateHelper.createTransition("Transition " + i); //$NON-NLS-1$
	       splitTransition.setCondition("CONDITION"); //$NON-NLS-1$
	       expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
	       splitTransition.setExpression(expression);
	       ModelUtils.setCDataString(expression.getMixed(), "true", true);  //$NON-NLS-1$
	       TransitionConnectionType splitTransitionSymbol = templateHelper.createTransitionSymbol(splitTransition);
	       splitTransition.setFrom(rootActivity);
	       splitTransition.setTo(splitActivity);
	       splitTransitionSymbol.setSourceActivitySymbol(gatewaySymbol);
	       splitTransitionSymbol.setTargetActivitySymbol(splitActivitySymbol);
	       splitTransitionSymbol.setTargetAnchor(TransitionConnectionAnchor.TOP);
		   x = x + 200;
		}		
		return true;
	}
	
    public boolean isKilled()
    {
       return killed;
    }  
	    
}
