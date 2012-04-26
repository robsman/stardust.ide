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
               /*
               result.add(Issue.error(code, "error",
                     ValidationService.PKG_CWM.getCode_Code()));
               */
            }            
            else if(!ModelUtils.isValidId(id))
            {
               
            }
 
            if(isDuplicate(id))
            {
               
            }
            
         }      
      }
      
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);   
   }

   private boolean isDuplicate(String id)
   {
      return false;
   }
}