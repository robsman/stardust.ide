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
package org.eclipse.stardust.modeling.validation.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.*;

public class QualityAssuranceCodeValidator implements IModelValidator
{   
   public Issue[] validate(ModelType model) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      
      if(model.getQualityControl() != null)
      {
         EList<Code> codes = model.getQualityControl().getCode();
         for(Code code : codes)
         {
            String id = code.getCode();
            if(StringUtils.isEmpty(id))
            {
               result.add(Issue.error(code, Validation_Messages.ERR_ELEMENT_EmptyId,
                     ValidationService.PKG_CWM.getCode_Code()));
            }            
            else if(!ModelUtils.isValidId(id))
            {
               result.add(Issue.error(code, 
                     MessageFormat.format(Validation_Messages.ERR_ELEMENT_InvalidId,
                           new Object[] {id}),                     
                     ValidationService.PKG_CWM.getCode_Code()));
               
            }
            else if(isDuplicate(code))
            {
               result.add(Issue.error(code, 
                     MessageFormat.format(Validation_Messages.MSG_DuplicateIdUsed, 
                           new Object[] {id}),
                           ValidationService.PKG_CWM.getCode_Code()));               
            }            
         }      
      }
      
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);   
   }

   private boolean isDuplicate(Code code)
   {
      ModelType model = ModelUtils.findContainingModel(code);
      EList<Code> codes = model.getQualityControl().getCode();
      for(Code current : codes)
      {
         if(!current.equals(code))
         {
            if(!StringUtils.isEmpty(code.getCode())
                  && code.getCode().equals(current.getCode()))
            {
               return true;
            }            
         }         
      }      
      
      return false;
   }
}