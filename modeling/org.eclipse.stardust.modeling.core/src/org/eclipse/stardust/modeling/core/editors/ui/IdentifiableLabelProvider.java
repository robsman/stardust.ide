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
package org.eclipse.stardust.modeling.core.editors.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.IconWithOverlays;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.swt.graphics.Image;


public class IdentifiableLabelProvider extends EObjectLabelProvider {

	private ModelType model;
	private boolean showGroupInfo = false;

	public IdentifiableLabelProvider(WorkflowModelEditor editor) {
		super(editor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getText(Object element) {        
		if ((element instanceof IIdentifiableModelElement) &&  showGroupInfo)
        {
		   IIdentifiableModelElement pdt = (IIdentifiableModelElement)element;
		   ModelType model = ModelUtils.findContainingModel(pdt);
           if (model != IdentifiableLabelProvider.this.model)
           {
              String modelName = model == null ? "null" : model.getName(); //$NON-NLS-1$
              if (modelName == null)
              {
                 modelName = model.getId();
                 if (modelName == null)
                 {
                    modelName = "<Model>"; //$NON-NLS-1$
                 }
              }
              return modelName + " / " + pdt.getName(); //$NON-NLS-1$
           }
        }
		return super.getText(element);
	}
	
	public void setModel(ModelType model)
	{
	   this.model = model;
	}
	
	public boolean isShowGroupInfo() 
	{
	   return showGroupInfo;
	}

	public void setShowGroupInfo(boolean showGroupInfo) {
	   this.showGroupInfo = showGroupInfo;
	}

   public Image getImage(Object element)
   {
      Image image = super.getImage(element);
      IIdentifiableModelElement pdt = (IIdentifiableModelElement) element;
      ModelType model = ModelUtils.findContainingModel(pdt);
      if (model != IdentifiableLabelProvider.this.model)
      {
         ImageDescriptor descriptor = ImageDescriptor.createFromImage(image);
         IconWithOverlays icon = new IconWithOverlays(descriptor,
               IconWithOverlays.OVR_REF);
         return icon.createImage();
      }
      return image;
   }	
	

}
