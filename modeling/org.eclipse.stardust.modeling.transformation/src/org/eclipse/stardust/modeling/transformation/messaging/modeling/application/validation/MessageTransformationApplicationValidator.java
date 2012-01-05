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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.validation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContext;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.javascript.editor.EditorUtils;
import org.eclipse.stardust.modeling.javascript.editor.JavaScriptValidator;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.wst.jsdt.core.IJavaScriptProject;
import org.eclipse.wst.jsdt.core.JavaScriptCore;
import org.eclipse.wst.jsdt.core.compiler.IProblem;

import com.infinity.bpm.messaging.model.mapping.FieldMapping;


public class MessageTransformationApplicationValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      
      
      List issues = new ArrayList();
      ModelType model = ModelUtils.findContainingModel(element);
      VariableContext context = VariableContextHelper.getInstance().getContext(model);
      MessageTransformationController controller = new MessageTransformationController();
      try {
         controller.intializeModel(model, element);         
      } catch (RuntimeException ex) {
         if (ex.getMessage().indexOf("external reference") > 0) { //$NON-NLS-1$
            issues.add(Issue.warning(element, Modeling_Messages.MSG_MTA_INVALID_REFERENCES)); 
            return (Issue[]) issues.toArray(new Issue[issues.size()]); 
         }         
      }

      controller.initializeMappings(element);        
            
      IProject project = ModelUtils.getProjectFromEObject(element);      
      IJavaScriptProject javaProject = JavaScriptCore.create(project);
      try
      {
         EditorUtils.addJSSupport(project, model);
      }
      catch (CoreException e)
      {
      }               
            
      String toCheck = controller.getJScriptProlog();      
      Map fieldMappings = controller.getFieldMappings();
      List<AccessPointType> aps = controller.getExternalClassTypes();
      for (Iterator<AccessPointType> i  = aps.iterator(); i.hasNext();) {
    	  AccessPointType apt = i.next();
    	  if (apt.getElementOid() == -99) {
    		  issues.add(Issue.warning(element, Modeling_Messages.MSG_MTA_REFERRED_CLASS_MISSING));  
    	  }
      }
      for (Iterator i = fieldMappings.keySet().iterator(); i.hasNext();) {
         String path = i.next().toString();
         FieldMapping fm = (FieldMapping) fieldMappings.get(path);
         if (fm != null) {                       
        	JavaScriptValidator javaScriptValidator = new JavaScriptValidator((IJavaScriptProject)javaProject);
        	String mappingExpression = fm.getMappingExpression();
        	mappingExpression = context.replaceAllVariablesByDefaultValue(mappingExpression);       	
        	IProblem[] test = javaScriptValidator.validate(toCheck + "\n" + mappingExpression);  //$NON-NLS-1$
            if(test != null && test.length > 0)
            {   
               issues.add(Issue.warning(element, Modeling_Messages.MSG_MTA_INVALID_JAVASCRIPT)); 
               return (Issue[]) issues.toArray(new Issue[issues.size()]);                              
            } 
         }
      } 
      return (Issue[]) issues.toArray(new Issue[issues.size()]);
   }
   
}