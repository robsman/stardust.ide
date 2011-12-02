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
package org.eclipse.stardust.modeling.core.search.tree;

import java.util.Map;

import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;


public class ResultViewTreeViewer extends TreeViewer
{
   private ResultViewFilter resultViewFilter;
   private Map matchedElements;
   
   public void setContents(ModelType contents, String rootLabel, boolean addDocumentRoot)
   {
      // the factory is always a new one
      resultViewFilter = new ResultViewFilter(contents);
      ((ResultViewTreeEditPartFactory) getEditPartFactory()).setFilter(resultViewFilter);         
      ((ResultViewTreeEditPartFactory) getEditPartFactory()).setRootLabel(rootLabel);                  
      
      if(matchedElements != null)
      {
         resultViewFilter.setMatchedElements(matchedElements);
      }
      
      if(addDocumentRoot)
      {
         super.setContents(contents.eContainer());
      }
      else
      {
         super.setContents(contents);
      }
      
      if(resultViewFilter.getNumberMatchedElements() == 0)
      {
         getControl().setVisible(false);         
      }
      else
      {
         getControl().setVisible(true);         
      }
   }

   public ResultViewFilter getResultViewFilter()
   {
      return resultViewFilter;
   }

   public void setMatchedElements(Map matchedElements)
   {
      this.matchedElements = matchedElements;      
   }
}