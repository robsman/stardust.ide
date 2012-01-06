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
package org.eclipse.stardust.modeling.javascript.editor.controller;

import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.CodeCompletionHelper;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor.RegionWithLineOffset;

public class JavaScriptEditorController
{
   public JavaScriptEditorController() {
		super();
		CodeCompletionHelper.getInstance().getTypeMap().clear();
	}

protected String masterDocument;

   protected String fieldsDocument = "//Fields\n";
   protected String expressionDocument = "//Expressions\n";
   protected String statementsDocument = "//Statements\n";

   protected Region fieldsRegion;
   protected Region statementsRegion;
   protected RegionWithLineOffset expressionRegion;
   
   private String jScript;
   
   public void intializeModel(ModelType model)
   {
      jScript = "function ippInitialize(e) {return null}\n";
      jScript = jScript + "function ippImport(e) {return null}\n";
      
      List modelDatas = model.getData();      
      for(int i = 0; i < modelDatas.size(); i++)
      {
         DataType data = (DataType) modelDatas.get(i);         
         jScript = jScript + "var " + data.getId() + " = ippInitialize(\"" + data.getId() + "\");\n";
      }
      jScript = jScript + "var " + PredefinedConstants.ACTIVITY_INSTANCE_ACCESSPOINT +
            " = ippInitialize(\"" + PredefinedConstants.ACTIVITY_INSTANCE_ACCESSPOINT + "\");\n";
      fieldsDocument = "//Fields\n" + jScript;
   }
      
   public String getJScript()
   {
      return jScript;
   }

   public void recalculateRegions(IDocument document)
   {
      // TODO: Reuse code from MessageTransformationController.recalculateRegions(IDocument)
      
      int lines = document.getNumberOfLines();
      int fo, fl, so, sl, eo, el;
      fo = fl = so = sl = eo = el = 0;
      for (int i = 0; i < lines - 1; i++)
      {
         try
         {
            // constructor, etc.
            IRegion region = document.getLineInformation(i);
            String content = document.get(region.getOffset(), document.getLineLength(i));
            if (content.startsWith("//Fields"))
            {
               fo = document.getLineOffset(i + 1);
            }
            // functions
            if (content.startsWith("//Statements"))
            {
               fl = (document.getLineOffset(i - 1) + document.getLineLength(i - 1)) - fo;
               so = document.getLineOffset(i + 1);
               fieldsRegion = new Region(fo, fl);
               document.get(eo, el);
            }
            // expression for boolean
            if (content.startsWith("//Expression"))
            {
               sl = document.getLineOffset(i - 1) + document.getLineLength(i - 1) - so;
               eo = document.getLineOffset(i + 1);
               el = document.getLineOffset(lines - 1) + document.getLineLength(lines - 1)
                     - eo;
               statementsRegion = new Region(so, sl);
               expressionRegion = new RegionWithLineOffset(eo, el);
               expressionRegion.setLineOffset(i + 1);
            }
         }
         catch (Throwable t)
         {
            t.printStackTrace();
         }
      }
   }
   
   // getter/setter
   public String getMasterDocument()
   {
      masterDocument = fieldsDocument + statementsDocument + expressionDocument;
      return masterDocument;
   }

   public void setMasterDocument(String masterDocument)
   {
      this.masterDocument = masterDocument;
   }

   public Region getFieldsRegion()
   {
      return fieldsRegion;
   }

   public void setFieldsRegion(Region fieldsRegion)
   {
      this.fieldsRegion = fieldsRegion;
   }

   public Region getStatementsRegion()
   {
      return statementsRegion;
   }

   public void setStatementsRegion(Region statementsRegion)
   {
      this.statementsRegion = statementsRegion;
   }

   public RegionWithLineOffset getExpressionRegion()
   {
      return expressionRegion;
   }

   public void setExpressionRegion(RegionWithLineOffset expressionRegion)
   {
      this.expressionRegion = expressionRegion;
   }
}