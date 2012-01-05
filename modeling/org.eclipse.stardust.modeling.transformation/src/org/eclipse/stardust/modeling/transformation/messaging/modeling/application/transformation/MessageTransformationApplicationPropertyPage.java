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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation;

import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.stardust.engine.extensions.transformation.model.mapping.FieldMapping;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IApplicationPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractModelElementNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.IdentifiableModelElementTreeEditPart;
import org.eclipse.stardust.modeling.core.editors.ui.ModelElementPropertyDialog;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

/**
 * @author Rainer Pielmann
 */
public class MessageTransformationApplicationPropertyPage extends
      AbstractModelElementPropertyPage implements IApplicationPropertyPage
{
   IModelElementNodeSymbol defaultSymbol;
   IModelElement defaultModelElement;
   boolean closeHard;
   boolean removedAll;
   final private MessageTransformationApplicationControlsManager controlsManager;
   private MessageTransformationController controller;
   private boolean isExternalReference;
   
   
   @Override
   public void dispose()
   {
      controlsManager.dispose();
      super.dispose();
   }

   public MessageTransformationApplicationPropertyPage()
   {
      super();
      controlsManager = new MessageTransformationApplicationControlsManager();
   }

   /**
    * 
    */
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      controller = controlsManager.getController();
      //controller.setExternalReference(true);
      try {
         defaultSymbol = symbol;
         defaultModelElement = element;
         ModelType model = ModelUtils.findContainingModel(element);         
         controller.intializeModel(model, symbol, element);         
         controlsManager.refreshModel();
         controller.initializeMappings(element);
         controlsManager.getSourceMessageTreeViewer().refresh(true);
         controlsManager.getTargetMessageTreeViewer().refresh(true);
         controlsManager.refreshDocument();        
         if (controller.getAvailableMessageTypes().isEmpty()) {
            setErrorMessage(Modeling_Messages.MSG_MD_NOT_CONTAIN_STR_DATA_TYPE_DEFINE_MSG);
         }         
      } catch (RuntimeException t) {         
         MessageBox messageBox = createMessageBoxByException(t);                                         
         if (messageBox == null) {
            throw t;
         }
         if (messageBox.open() == SWT.CANCEL) {
            ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) this.getContainer();
            closeHard = true;
            dialog.close();
         } else {
            removedAll = true;
            controller.removeInvalidAccessPoints();            
            loadFieldsFromElement(symbol, element);          
         }
      }
   }
   
   private MessageBox createMessageBoxByException(RuntimeException t)
   {
      MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
            SWT.ICON_ERROR | SWT.OK | SWT.CANCEL);      
      if (t.getMessage() == null) {
         return null;
      }
      if (t.getMessage().indexOf("external reference") > 0) { //$NON-NLS-1$
         messageBox.setText(Modeling_Messages.TXT_MISSING_REF);         
         String message = Modeling_Messages.MSG_EXT_REF_REMOVED_CLASSPATH;  
         message = message + "\n\n" + Modeling_Messages.MSG_ORI_MSG ; //$NON-NLS-1$
         message = message + "\n\n" + Modeling_Messages.MSG_REMOVE_INVALID_MSG; //$NON-NLS-1$
         messageBox.setMessage(MessageFormat.format(message, new Object[]{t.getMessage()}));
      } else {
         messageBox.setText(Modeling_Messages.TXT_INVALID_MSG);
         String message = Modeling_Messages.MSG_MSG_TRANS_CONTAINS_INVALID_MSG;  
         message = message + "\n\n"+Modeling_Messages.MSG_ORI_MSG; //$NON-NLS-1$
         message = message + "\n\n" + Modeling_Messages.MSG_WANT_REMOVE_INVALID;          //$NON-NLS-1$
         messageBox.setMessage(MessageFormat.format(message, new Object[]{t.getMessage()}));
      }
      return messageBox;
   }

   public void elementChanged()
   {
      IModelElementNodeSymbol symbol = (IModelElementNodeSymbol)
            getElement().getAdapter(IModelElementNodeSymbol.class);
      IModelElement modelElement = (IModelElement) (symbol == null ?
            getElement().getAdapter(IModelElement.class) : symbol.getModelElement());

      if (modelElement == null)
      {
         modelElement = (IModelElement) this.getModelElement();
         isExternalReference = true;
      }
      if (null != modelElement)
      {
         loadFieldsFromElement(symbol, modelElement);
      }
      /*if (!closeHard && !removedAll) {
         super.performDefaults();          
      }*/
      removedAll = false;
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
      boolean insta = getElement() instanceof IdentifiableModelElementTreeEditPart;
      boolean instb = getElement() instanceof AbstractModelElementNodeSymbolEditPart;
      
      if (!insta && !instb){
         controlsManager.setExternalReference(true);
      }
      return controlsManager.create(parent, (IModelElement) getModelElement());
   }

   @Override
   protected void performDefaults()
   {
      super.performDefaults();
      clearMappingStatements();
      controlsManager.getController().reset();
      loadFieldsFromElement(defaultSymbol, defaultModelElement);               
   }

   private void clearMappingStatements()
   {
      for (Iterator<FieldMapping> i = controlsManager.getController().getFieldMappings().values()
            .iterator(); i.hasNext();)
      {
         FieldMapping fm = i.next();
         fm.setMappingExpression(""); //$NON-NLS-1$
      }
      controlsManager.getTargetMessageTreeViewer().refresh(true);
   }
}