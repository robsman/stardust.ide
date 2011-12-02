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
package org.eclipse.stardust.modeling.core.modelserver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;


public class ModelCopier extends EcoreUtil.Copier
{
   private static final long serialVersionUID = 1L;

   public void copy(ModelType source, ModelType target)
   {
      EClass eClass = source.eClass();
      for (int i = 0, size = eClass.getFeatureCount(); i < size; i++)
      {
         EStructuralFeature feature = eClass.getEStructuralFeature(i);
         if (feature.isChangeable() && !feature.isDerived())
         {
            // TODO: really check what exactly is copied here
            if (feature instanceof EAttribute)
            {
               copyAttribute((EAttribute) feature, source, target);
            }
         }
      }      
      
      ConnectionManager connectionManagerTarget = null;
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(target);
      if (editor != null)
      {
         connectionManagerTarget = editor.getConnectionManager();
      }
      if(connectionManagerTarget == null)
      {
         connectionManagerTarget = new ConnectionManager(target);         
      }      
      
      ConnectionManager connectionManagerSource = new ConnectionManager(source);
      connectionManagerTarget.getAllConnections().clear();
      connectionManagerTarget.save();
      List<Connection> all = new ArrayList<Connection>(connectionManagerSource.getAllConnections());
      for(Connection connection : all)
      {
         Connection copy = (Connection) EcoreUtil.copy(connection);
         connectionManagerTarget.setConnectionManager(copy);
         connectionManagerTarget.getAllConnections().add(copy);
      }      
      connectionManagerTarget.save();      
      
      ExternalPackages packages = source.getExternalPackages();
      if (packages != null)
      {
         if(target.getExternalPackages() == null)
         {
            ExternalPackages createExternalPackages = XpdlFactory.eINSTANCE.createExternalPackages();
            target.setExternalPackages(createExternalPackages);                        
         }
         else
         {
            target.getExternalPackages().getExternalPackage().clear();
         }
         
         for (ExternalPackage pkg : packages.getExternalPackage())
         {
            ExternalPackage copy = (ExternalPackage) EcoreUtil.copy(pkg);
            target.getExternalPackages().getExternalPackage().add(copy);
         }
      }
      else
      {
         target.setExternalPackages(null);
      }
   }
}