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
package org.eclipse.stardust.modeling.integration.camel.ui;

import java.util.Iterator;

import org.eclipse.stardust.engine.extensions.transformation.model.mapping.FieldMapping;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IApplicationPropertyPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.ModelElementPropertyDialog;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationApplicationControlsManager;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Rainer Pielmann
 */
public class InputOutputAccessPointPropertyPage extends
      AbstractModelElementPropertyPage implements IApplicationPropertyPage
{
   IModelElementNodeSymbol defaultSymbol;
   IModelElement defaultModelElement;
   
   

   public void dispose()
   {
      controlsManager.dispose();
      super.dispose();
   }

   final private MessageTransformationApplicationControlsManager controlsManager;

   public InputOutputAccessPointPropertyPage()
   {
      super();
      controlsManager = new MessageTransformationApplicationControlsManager()
      {
         public void refreshModel()
         {
            super.refreshModel();
            IPreferencePageContainer container = getContainer();
            if (container instanceof ModelElementPropertyDialog)
            {
               IPreferenceNode node = ((ModelElementPropertyDialog) container).getNode("cwm_spi_camel_general_tab_");
               if(node instanceof CarnotPreferenceNode)
               {
                  IPreferencePage page = ((CarnotPreferenceNode) node).getPage();
                  ((GeneralPropertyPage) page).populateBodyInAccessPointViewer();
                  ((GeneralPropertyPage) page).populateBodyOutAccessPointViewer();
               }
            }
         }         
      };
   }

   /**
    * 
    */
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      defaultSymbol = symbol;
      defaultModelElement = element;
      ModelType model = null;
      if ((null != PlatformUI.getWorkbench())
            && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow())
            && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage()))
      {
         IEditorPart currentEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
               .getActivePage().getActiveEditor();
         if (currentEditor instanceof WorkflowModelEditor)
         {
            model = ((WorkflowModelEditor) currentEditor).getWorkflowModel();
         }
      }
      
      MessageTransformationController controller = controlsManager.getController();

      controller.intializeModel(model, symbol, element);         

      controlsManager.refreshModel();
      controller.initializeMappings(element);
      controlsManager.getSourceMessageTreeViewer().refresh(true);
      controlsManager.getTargetMessageTreeViewer().refresh(true);
      controlsManager.refreshDocument();
      
      if (controller.getAvailableMessageTypes().isEmpty()) {
         this.setErrorMessage(Modeling_Messages.ERR_MODEL_DOES_NOT_CONTAIN_ANY_STRUCT_DATA); 
      }
   }

   /**
    * 
    */
   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {      
      controlsManager.getController().saveFields(symbol, element);
   }

   /**
    * 
    */
   public Control createBody(final Composite parent)
   {
	   Control control = controlsManager.create(parent, (IModelElement) getModelElement(), true, true, true);
      
	   return control;
   }

   protected void performDefaults()
   {
      super.performDefaults();
      clearMappingStatements();
      controlsManager.getController().reset();
      loadFieldsFromElement(defaultSymbol, defaultModelElement);      
   }

   private void clearMappingStatements()
   {
      for (Iterator i = controlsManager.getController().getFieldMappings().values()
            .iterator(); i.hasNext();)
      {
         FieldMapping fm = (FieldMapping) i.next();
         fm.setMappingExpression(""); //$NON-NLS-1$
         //fm.setMappingStatements("");
      }
      controlsManager.getTargetMessageTreeViewer().refresh(true);
   }
}