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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.core.compatibility.el.BooleanExpression;
import org.eclipse.stardust.engine.core.compatibility.el.CombineOperation;
import org.eclipse.stardust.engine.core.compatibility.el.ComparisonOperation;
import org.eclipse.stardust.engine.core.compatibility.el.ConstantExpression;
import org.eclipse.stardust.engine.core.compatibility.el.DereferencePath;
import org.eclipse.stardust.engine.core.compatibility.el.Interpreter;
import org.eclipse.stardust.engine.core.compatibility.el.SyntaxError;
import org.eclipse.stardust.engine.core.compatibility.el.ValueExpression;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.Model_Messages;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ScriptOrFnNode;
import org.mozilla.javascript.Token;

/**
 * @author rsauer
 * @version $Revision$
 */
public class ElUtils
{
   private static final ContextFactory jsContextFactory = ContextFactory.getGlobal();
   
   public static Map/*<String, List<DereferencePath>>*/ xrefData(String expression)
         throws SyntaxError
   {
      Map/*<String, List<DereferencePath>>*/ result = new HashMap/*<String, List<DereferencePath>>*/();

      xrefData(Interpreter.parse(expression), result);

      return result;
   }

   public static int getRefCount(String dataId, Map/*<String, List<DereferencePath>>*/ xrefs)
   {
      return xrefs.containsKey(dataId) ? ((List) xrefs.get(dataId)).size() : 0;
   }

   public static BooleanExpression getLhsExpression(CombineOperation term)
   {
      return (BooleanExpression) Reflect.getFieldValue(term, "lhsExpression"); //$NON-NLS-1$
   }

   public static BooleanExpression getRhsExpression(CombineOperation term)
   {
      return (BooleanExpression) Reflect.getFieldValue(term, "rhsExpression"); //$NON-NLS-1$
   }

   public static ValueExpression getLhsOperand(ComparisonOperation term)
   {
      return (ValueExpression) Reflect.getFieldValue(term, "lhsValue"); //$NON-NLS-1$
   }

   public static ValueExpression getRhsOperand(ComparisonOperation term)
   {
      return (ValueExpression) Reflect.getFieldValue(term, "rhsValue"); //$NON-NLS-1$
   }

   public static String getDataId(DereferencePath derefExpression)
   {
      return (String) Reflect.getFieldValue(derefExpression, "baseReference"); //$NON-NLS-1$
   }

   public static String getDerefExpression(DereferencePath derefExpression)
   {
      return (String) Reflect.getFieldValue(derefExpression, "accessPath"); //$NON-NLS-1$
   }

   private static void xrefData(BooleanExpression expression,
         Map/*<String, List<DereferencePath>>*/ xrefs)
   {
      if (expression instanceof CombineOperation)
      {
         CombineOperation parsedExpressions = (CombineOperation) expression;

         xrefData(ElUtils.getLhsExpression(parsedExpressions), xrefs);
         xrefData(ElUtils.getRhsExpression(parsedExpressions), xrefs);
      }
      else if (expression instanceof ComparisonOperation)
      {
         ComparisonOperation parsedExpressions = (ComparisonOperation) expression;

         xrefData(ElUtils.getLhsOperand(parsedExpressions), xrefs);
         xrefData(ElUtils.getRhsOperand(parsedExpressions), xrefs);
      }
      else
      {
         throw new InternalException(MessageFormat.format(Model_Messages.EXC_UNSUPPORTED_BOOLEAN_EXPRESSION_NULL, new Object[]{expression}));
      }
   }

   private static void xrefData(ValueExpression expression,
         Map/*<String, List<DereferencePath>>*/ xrefs)
   {
      if (expression instanceof ConstantExpression)
      {
         // nothing
      }
      else if (expression instanceof DereferencePath)
      {
         String dataId = ElUtils.getDataId((DereferencePath) expression);

         List/*<DereferencePath>*/ xref = (List) xrefs.get(dataId);
         if (null == xref)
         {
            xref = new ArrayList/*<DereferencePath>*/();
            xrefs.put(dataId, xref);
         }
         xref.add((DereferencePath) expression);
      }
      else
      {
    	  throw new InternalException(MessageFormat.format(Model_Messages.EXC_UNSUPPORTED_BOOLEAN_EXPRESSION_NULL, new Object[]{expression}));
      }
   }
   
   public static ScriptOrFnNode parseJsExpressions(String expression)
   {
      Context cx = jsContextFactory.enter();
      try
      {
         CompilerEnvirons compilerEnv = new CompilerEnvirons();
         compilerEnv.setGeneratingSource(true);
         compilerEnv.initFromContext(cx);
         ErrorReporter compilationErrorReporter = null;
         if (null == compilationErrorReporter)
         {
            compilationErrorReporter = compilerEnv.getErrorReporter();
         }

         Parser p = new Parser(compilerEnv, compilationErrorReporter);

         return p.parse(expression, "expression", 1); //$NON-NLS-1$
      }
      finally
      {
         Context.exit();
      }
   }
   
   public static Map<String, List<Node>> xrefData4Js(String expression)
   {
      ScriptOrFnNode ast = parseJsExpressions(expression);
      
      Map<String, List<Node>> dataIds = new HashMap<String, List<Node>>();
      
      if (ast.hasChildren())
      {
         Stack<Node> backtrack = new Stack<Node>();
         
         Node astNode = ast.getFirstChild();
         do
         {
            if (Token.ENTERWITH == astNode.getType())
            {
               throw new UnsupportedElSyntaxException(Model_Messages.EXC_WITH_IS_NOT_SUPPORTED);
            }
            else if (Token.NAME == astNode.getType())
            {
               List<Node> refs = dataIds.get(astNode.getString());
               if (null == refs)
               {
                  refs = new ArrayList<Node>();
                  dataIds.put(astNode.getString(), refs);
               }
               
               refs.add(astNode);
            }
            
            if (astNode.hasChildren())
            {
               backtrack.push(astNode);
               astNode = astNode.getFirstChild();
            }
            else
            {
               astNode = astNode.getNext();
            }
            
            while ((null == astNode) && !backtrack.isEmpty())
            {
               astNode = backtrack.pop().getNext();
            }
         }
         while (null != astNode);
      }
      
      return dataIds;
   }
   
   public static void updateTransitionConditions(DataType dataType, String oldValue, String newValue)
   {
      ModelType modelType = (ModelType) dataType.eContainer();
      for (Iterator<ProcessDefinitionType> iter = modelType.getProcessDefinition().iterator(); iter.hasNext();)
      {
         for (Iterator<TransitionType> iterator = iter.next().getTransition()
               .iterator(); iterator.hasNext();)
         {
            final TransitionType transition = iterator.next();
            final String expression = transition.getExpression() == null
                  ? null
                  : ModelUtils.getCDataString(transition.getExpression().getMixed());
            patchJsExpressions(expression, oldValue, newValue);
            String newExpression = patchJsExpressions(expression, oldValue, newValue);
            ModelUtils.setCDataString(transition.getExpression()
                  .getMixed(), newExpression);
         }
      }               
   }
   
   public static String patchExpressions(String expression, String oldId, String newId)
   throws SyntaxError
{
   String result = expression;
   
   int currentPos = 0;
   int startOfDataId = result.indexOf(oldId, currentPos);
   while ( -1 != startOfDataId)
   {
      Map/*<String, List<DereferencePath>>*/ dataRefCount = ElUtils.xrefData(result);
   
      int nIdRefs = ElUtils.getRefCount(oldId, dataRefCount);
      int nMangledIdRefs = ElUtils.getRefCount(newId, dataRefCount);
   
      StringBuffer buffer = new StringBuffer(result);
      buffer.replace(startOfDataId, startOfDataId + oldId.length(), newId);
   
      try
      {
         Map/*<String, List<DereferencePath>>*/ newRefCount = ElUtils.xrefData(buffer.toString());
   
         // ref count of mangled ID must have gone one up, of unmangled ID one down
         int nIdRefsNow = ElUtils.getRefCount(oldId, newRefCount);
         int nMangledIdRefsNow = ElUtils.getRefCount(newId, newRefCount);
   
         if ((nIdRefs == (1 + nIdRefsNow))
               && ((1 + nMangledIdRefs) == nMangledIdRefsNow))
         {
            currentPos = startOfDataId + newId.length();
            result = buffer.toString();
         }
         else
         {
            currentPos = 1 + startOfDataId;
         }
      }
      catch (SyntaxError se)
      {
         // change makes expression invalid, so skip this occurance
         currentPos = 1 + startOfDataId;
      }
   
      if (currentPos < result.length())
      {
         startOfDataId = result.indexOf(oldId, currentPos);
      }
      else
      {
         startOfDataId = -1;
      }
   }
   
   // TODO verify ref count for old ID is zero      
   return result;
}

   
   public static String patchJsExpressions(String expression, String oldId, String newId)
   {
      String result = expression;

      int currentPos = 0;
      int startOfDataId = result.indexOf(oldId, currentPos);
      while ( -1 != startOfDataId)
      {
         Map<String, List<Node>> dataRefCount = ElUtils.xrefData4Js(result);

         int nIdRefs = ElUtils.getRefCount(oldId, dataRefCount);
         int nMangledIdRefs = ElUtils.getRefCount(newId, dataRefCount);

         StringBuffer buffer = new StringBuffer(result);
         buffer.replace(startOfDataId, startOfDataId + oldId.length(), newId);

         try
         {
            Map<String, List<Node>> newRefCount = ElUtils.xrefData4Js(buffer.toString());

            // ref count of mangled ID must have gone one up, of unmangled ID one down
            int nIdRefsNow = ElUtils.getRefCount(oldId, newRefCount);
            int nMangledIdRefsNow = ElUtils.getRefCount(newId, newRefCount);

            if ((nIdRefs == (1 + nIdRefsNow))
                  && ((1 + nMangledIdRefs) == nMangledIdRefsNow))
            {
               currentPos = startOfDataId + newId.length();
               result = buffer.toString();
            }
            else
            {
               currentPos = 1 + startOfDataId;
            }
         }
         catch (Exception e)
         {
            // change makes expression invalid, so skip this occurence
            currentPos = 1 + startOfDataId;
         }

         if (currentPos < result.length())
         {
            startOfDataId = result.indexOf(oldId, currentPos);
         }
         else
         {
            startOfDataId = -1;
         }
      }

      // TODO verify ref count for old ID is zero

      return result;
   }   

   private ElUtils()
   {
      // utility class
   }
}
