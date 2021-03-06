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
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
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



public class LoopTemplateWizard extends Wizard  {
	private LoopTemplatePage newTemplatePage; 
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

	public LoopTemplateWizard()
   {
      super();
      // TODO Auto-generated constructor stub
   }
	
   public LoopTemplateWizard(WorkflowModelEditor targetEditor, ModelType targetModel,
         DiagramType targetDiagram, EditPart editPart, int xHint, int yHint)
   {
	 super();
	 this.setWindowTitle(Templates_Basic_Messages.TXT_APPLY_LOOP_TEMPLATE);
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
		newTemplatePage = new LoopTemplatePage(Templates_Basic_Messages.TXT_LOOP_TEMPLATE, Templates_Basic_Messages.TXT_LOOP_TEMPLATE, null);
		this.addPage(newTemplatePage);
	}

	public boolean performFinish() {

		String kind = newTemplatePage.getKind();
		String condition = newTemplatePage.getLoopCondition();
		String orientation = newTemplatePage.getOrientationText();
	    int number = Integer.parseInt(newTemplatePage.getNumber());
		int x = xHint;
		int y = yHint;
		ActivityType firstActivity = null;
		ActivityType lastActivity = null;
		ActivitySymbolType lastActivitySymbol = null;
		ActivitySymbolType firstActivitySymbol = null;
		
		for (int i = 0; i < number; i++) {		   
		   ActivityType sequenceActivity = templateHelper.createActivity(kind + " " + (i + 1), kind); //$NON-NLS-1$
		   ActivitySymbolType sequenceActivitySymbol = templateHelper.createActivitySymbol(sequenceActivity, x, y);
		   if (lastActivity != null) {
	           TransitionType sequenceTransition = templateHelper.createTransition("Transition " + i); //$NON-NLS-1$
	           sequenceTransition.setCondition("CONDITION"); //$NON-NLS-1$
	           expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
	           sequenceTransition.setExpression(expression);
	           ModelUtils.setCDataString(expression.getMixed(), "true", true);  //$NON-NLS-1$
	           TransitionConnectionType sequenceTransitionSymbol = templateHelper.createTransitionSymbol(sequenceTransition);          
	           sequenceTransition.setFrom(lastActivity);
	           sequenceTransition.setTo(sequenceActivity);
	           sequenceTransitionSymbol.setSourceActivitySymbol(lastActivitySymbol);
	           sequenceTransitionSymbol.setTargetActivitySymbol(sequenceActivitySymbol);
	           if (orientation.equalsIgnoreCase("Vertical")) { //$NON-NLS-1$
	               sequenceTransitionSymbol.setSourceAnchor(TransitionConnectionAnchor.BOTTOM);
	               sequenceTransitionSymbol.setTargetAnchor(TransitionConnectionAnchor.TOP);            
	           } else {
	               sequenceTransitionSymbol.setSourceAnchor(TransitionConnectionAnchor.RIGHT);
	               sequenceTransitionSymbol.setTargetAnchor(TransitionConnectionAnchor.LEFT);    
	           }
		   }
		   if (orientation.equalsIgnoreCase("Vertical")) { //$NON-NLS-1$
	           y = y + 100;		      
		   } else {
		       x = x + 250;
		   }
		   lastActivity = sequenceActivity;
		   lastActivitySymbol = sequenceActivitySymbol;
		   if (firstActivity == null) {
		      firstActivity = sequenceActivity;
		      firstActivitySymbol = sequenceActivitySymbol;
		   }
		}
		//Create Loop transition
        TransitionType loopTransition = templateHelper.createTransition("Transition"); //$NON-NLS-1$
        loopTransition.setCondition("CONDITION"); //$NON-NLS-1$
        expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
        loopTransition.setExpression(expression);
        ModelUtils.setCDataString(expression.getMixed(), condition, true); 
        TransitionConnectionType loopTransitionSymbol = templateHelper.createTransitionSymbol(loopTransition);          
        loopTransition.setFrom(lastActivity);
        loopTransition.setTo(firstActivity);
        loopTransitionSymbol.setSourceActivitySymbol(lastActivitySymbol);
        loopTransitionSymbol.setTargetActivitySymbol(firstActivitySymbol);
        if (orientation.equalsIgnoreCase("Vertical")) { //$NON-NLS-1$
           loopTransitionSymbol.setSourceAnchor(TransitionConnectionAnchor.RIGHT);
           loopTransitionSymbol.setTargetAnchor(TransitionConnectionAnchor.RIGHT);           
        } else {
           loopTransitionSymbol.setSourceAnchor(TransitionConnectionAnchor.TOP);
           loopTransitionSymbol.setTargetAnchor(TransitionConnectionAnchor.TOP);    
        }
        
		return true;
	}
	
    public boolean isKilled()
    {
       return killed;
    }  
	    
}
