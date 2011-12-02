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
package org.eclipse.stardust.modeling.templates.basic.factories;

import java.util.Map;

import org.eclipse.stardust.modeling.templates.spi.ITemplate;
import org.eclipse.stardust.modeling.templates.spi.ITemplateFactory;


public class BasicControlFlowPatterns implements ITemplateFactory
{
   ITemplateFactory[] childFactories;

   public ITemplateFactory[] getChildFactories()
   {                 
      return childFactories;
   }

   public String getDescription()
   {
      return "Basic Control Flow Patterns";
   }

   public String getId()
   {
      return "Basic Control Flow Patterns";
   }

   public String getName() 
   {
      return "Basic Control Flow Patterns";
   }

   public ITemplateFactory getParentFactory()
   {
      return null;
   }

   public ITemplate[] getTemplates()
   {
      ITemplate[] result = new ITemplate[6];
      result[0] = new SequenceTemplateAdapter(this);
      result[1] = new LoopTemplateAdapter(this);
      result[2] = new ParallelSplitTemplateAdapter(this);
      result[3] = new ParallelSplitAndSynchronizationTemplateAdapter(this);
      result[4] = new ExclusiveChoiceTemplateAdapter(this);
      result[5] = new ExclusiveChoiceAndMergeTemplateAdapter(this);      
      return result;     
   }

   public void initialize(Map parameters)
   {

   }

}
