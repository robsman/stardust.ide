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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.Code;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.QualityControlType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.ui.ActivityQualityControlCodesViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


/**
 * @author fherinean
 * @version $Revision: 49787 $
 */
public class ActivityQualityControlCodesPropertyPage extends AbstractModelElementPropertyPage
{
   public static final String QUALITY_CONTROL_CODES_ID = "_cwm_quality_control_codes_"; //$NON-NLS-1$
   public static final String QUALITY_CONTROL_CODES_LABEL = Diagram_Messages.QUALITY_CONTROL_CODES_LABEL;

   private ComboViewer performerViewer;
   private LabeledText createLabeledText;
   
   protected boolean currentSelection;
   private Composite sourceViewerComposite;
   private QualityControlType qualityControl;
   private TableViewer viewer;
   private EList<Code> code;
   private ActivityQualityControlCodesViewer activityQualityControlCodesViewer;
      
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ActivityType activity = (ActivityType) element;      
      ModelType model = ModelUtils.findContainingModel(element);
      qualityControl = model.getQualityControl();
      if(qualityControl == null)
      {
         code = new BasicEList<Code>();
      }
      else
      {
         code = qualityControl.getCode();         
      }
      
      activityQualityControlCodesViewer.setInput(code, activity);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
           
      activityQualityControlCodesViewer = new ActivityQualityControlCodesViewer();
      activityQualityControlCodesViewer.createControl(composite);
      
      return composite;
   }   
}