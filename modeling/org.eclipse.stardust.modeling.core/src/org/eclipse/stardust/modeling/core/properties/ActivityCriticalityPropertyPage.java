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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContext;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.common.platform.utils.WorkspaceUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.javascript.editor.EditorUtils;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor.RegionWithLineOffset;
import org.eclipse.stardust.modeling.javascript.editor.controller.JavaScriptEditorController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;



/**
 * @author rpielmann
 * @version $Revision: 49787 $
 */
public class ActivityCriticalityPropertyPage extends AbstractModelElementPropertyPage
{
   private Composite sourceViewerComposite;

   private Label criticalityFormula;

   private JSCompilationUnitEditor criticalityFormulaEditor;

   private JavaScriptEditorController controller = new JavaScriptEditorController();

   private IProject project;

   private ModelType model;

   public void dispose()
   {
      super.dispose();
      EditorUtils.deleteFileStructure(project, model);
   }

   protected void performDefaults()
   {
      super.performDefaults();
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ModelType model = ModelUtils.findContainingModel(element);
      controller.intializeModel(model);
      loadFormula(element);
   }

   private void loadFormula(IModelElement element)
   {
      String formula = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            "ipp:criticalityFormula"); //$NON-NLS-1$
      this.refreshDocument();
      if (formula != null)
      {
         criticalityFormulaEditor.getAdaptedSourceViewer().getTextWidget()
               .setText(formula.trim());
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ModelType model = ModelUtils.findContainingModel(element);
      String formula = criticalityFormulaEditor.getAdaptedSourceViewer().getTextWidget()
            .getText().trim();
      VariableContext variableContext = VariableContextHelper.getInstance().getContext(
            model);
      if (variableContext != null && !variableContext.isCriticalityFormulaChanged())
      {
         AttributeUtil.setAttribute((IExtensibleElement) model, "ipp:criticalityFormula", //$NON-NLS-1$
               "String", formula); //$NON-NLS-1$
         variableContext.setCriticalityFormulaChanged(false);
      } else {
         loadFormula(element);
      }
   }

   public void refreshDocument()
   {
      criticalityFormulaEditor.getAdaptedSourceViewer().getDocument()
            .set(controller.getMasterDocument());
      controller.recalculateRegions(criticalityFormulaEditor.getAdaptedSourceViewer()
            .getDocument());

      final RegionWithLineOffset expressionRegion = controller.getExpressionRegion();
      criticalityFormulaEditor.getAdaptedSourceViewer().setVisibleRegion(
            expressionRegion.getOffset(), expressionRegion.getLength());
      criticalityFormulaEditor.setLineOffset(expressionRegion.getLineOffset());
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
      model = ModelUtils.findContainingModel(getModelElement());

      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      IEditorSite editorSite = editor.getEditorSite();

      project = WorkspaceUtils.getProjectFromEObject(getModelElement());

      criticalityFormula = FormBuilder.createLabel(composite,
            Diagram_Messages.LBL_Formula, 2);

      EditorUtils.deleteFileStructure(project, model);
      try
      {
         EditorUtils.addJSSupport(project, model);
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }

      IFile tempFileResource = EditorUtils.createFileStructure(project, model,
            "conditions.js"); //$NON-NLS-1$

      criticalityFormulaEditor = new JSCompilationUnitEditor();

      criticalityFormulaEditor.setTheSite(editorSite);
      criticalityFormulaEditor.setInput(new FileEditorInput(tempFileResource));

      sourceViewerComposite = new Composite(composite, SWT.NONE);
      GridData svcData = new GridData();
      svcData.grabExcessHorizontalSpace = true;
      svcData.grabExcessVerticalSpace = true;
      svcData.horizontalSpan = 2;
      svcData.horizontalAlignment = SWT.FILL;
      svcData.verticalAlignment = SWT.FILL;
      sourceViewerComposite.setLayout(new FillLayout());
      sourceViewerComposite.setLayoutData(svcData);
      criticalityFormulaEditor.createPartControl(sourceViewerComposite);

      criticalityFormula.setVisible(false);
      sourceViewerComposite.setVisible(false);

      criticalityFormulaEditor.getAdaptedSourceViewer().setEditable(true);
      ((JSCompilationUnitEditor.AdaptedSourceViewer) criticalityFormulaEditor
            .getAdaptedSourceViewer()).setAutoCompletion(true);
      sourceViewerComposite.setEnabled(true);
      criticalityFormula.setVisible(true);
      sourceViewerComposite.setVisible(true);

      return composite;
   }
}