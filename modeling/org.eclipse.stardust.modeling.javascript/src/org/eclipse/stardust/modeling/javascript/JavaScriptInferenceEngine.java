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
package org.eclipse.stardust.modeling.javascript;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.api.runtime.ActivityInstance;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.runtime.beans.BigData;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.CodeCompletionHelper;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.spi.dataTypes.plainXML.PlainXMLAccessPointType;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.validation.util.FieldInfo;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.jsdt.core.ast.IArrayReference;
import org.eclipse.wst.jsdt.core.ast.IAssignment;
import org.eclipse.wst.jsdt.core.ast.IBinaryExpression;
import org.eclipse.wst.jsdt.core.ast.IBlock;
import org.eclipse.wst.jsdt.core.ast.IEqualExpression;
import org.eclipse.wst.jsdt.core.ast.IExpression;
import org.eclipse.wst.jsdt.core.ast.IFieldReference;
import org.eclipse.wst.jsdt.core.ast.IFunctionCall;
import org.eclipse.wst.jsdt.core.ast.ILocalDeclaration;
import org.eclipse.wst.jsdt.core.infer.InferEngine;
import org.eclipse.wst.jsdt.core.infer.InferredAttribute;
import org.eclipse.wst.jsdt.core.infer.InferredMethod;
import org.eclipse.wst.jsdt.core.infer.InferredType;
import org.eclipse.wst.jsdt.internal.codeassist.complete.CompletionOnMemberAccess;
import org.eclipse.wst.jsdt.internal.compiler.ast.Argument;
import org.eclipse.wst.jsdt.internal.compiler.ast.ArrayReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.Assignment;
import org.eclipse.wst.jsdt.internal.compiler.ast.BinaryExpression;
import org.eclipse.wst.jsdt.internal.compiler.ast.Block;
import org.eclipse.wst.jsdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.wst.jsdt.internal.compiler.ast.EqualExpression;
import org.eclipse.wst.jsdt.internal.compiler.ast.Expression;
import org.eclipse.wst.jsdt.internal.compiler.ast.FieldReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.LocalDeclaration;
import org.eclipse.wst.jsdt.internal.compiler.ast.MessageSend;
import org.eclipse.wst.jsdt.internal.compiler.ast.MethodDeclaration;
import org.eclipse.wst.jsdt.internal.compiler.ast.ProgramElement;
import org.eclipse.wst.jsdt.internal.compiler.ast.Reference;
import org.eclipse.wst.jsdt.internal.compiler.ast.SingleNameReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.SingleTypeReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.Statement;
import org.eclipse.wst.jsdt.internal.compiler.ast.StringLiteral;
import org.eclipse.wst.jsdt.internal.compiler.lookup.BlockScope;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDSimpleTypeDefinition;

/**
 * @author sauer
 * @version $Revision: 29651 $
 */
public class JavaScriptInferenceEngine extends InferEngine
{
	


	
    synchronized public boolean visit(IFunctionCall functionCall) {       
       MessageSend messageSend = null;
       if (functionCall instanceof MessageSend) {
          messageSend = (MessageSend)functionCall;
       }
       if (messageSend != null) {
          if (messageSend.arguments != null) {
             for (int i = 0; i < messageSend.arguments.length; i++) {
                if (messageSend.arguments[i] instanceof CompletionOnMemberAccess && !(messageSend.arguments[i] instanceof JSCompletionOnMemberAccess)) {
                   messageSend.arguments[i] =  buildCompletionOnMemberAccess((Expression)messageSend.arguments[i]);    
                }
             }          
         }
         if ((messageSend.receiver instanceof ArrayReference) && !(messageSend.receiver instanceof JSArrayReference)) {
             messageSend.receiver = this.buildArrayReference((Expression)messageSend.receiver);
          } else {
             if ((messageSend.receiver instanceof FieldReference) && !(messageSend.receiver instanceof JSFieldReference)) {
                messageSend.receiver = this.buildFieldReference((Expression)messageSend.receiver);
             }           
          }          
       }
       return super.visit(functionCall);
    }


   private static final char[] NAME_IPP_INITIALIZE = "ippInitialize".toCharArray(); //$NON-NLS-1$
   private static final char[] NAME_IPP_IMPORT = "ippImport".toCharArray(); //$NON-NLS-1$

   private CompilationUnitDeclaration cu;

   private ModelType model;
   private TypeFinder typeFinder;

   private Map<String, ITypedElement> inferredIppVariables;
   private Map<String, ITypedElement> inferredIppAccessPointsByName;
   private Map<String, ITypedElement> inferredIppAccessPointsByType;

   /**
    * One record per data, provides inferred types per XPath against data.
    */
   private Map<String, Map<String, InferredType>> inferredVariables;

   /**
    * Maps inferred attributes to the data they are declared against.
    */
   private Map<InferredAttribute, ITypedElement> bpmAttributesToAp;

   /**
    * Maps XPaths to Class name.
    */
   private Map<String, String> xPathToJavaType;
   private Map<String, String> xPathToRealJavaType;
   private Map<String, String> xPathToJavaField;   
   
   /**
    * Maps inferred attributes to data relative XPaths.
    */
   private Map<InferredAttribute, String> bpmAttributesToXPath;
   private Map arrayMap = new HashMap();

   synchronized public void initialize()
   {
      super.initialize();           
      this.model = null;
      if ((null != PlatformUI.getWorkbench())
            && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow())
            && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage()))
      {
         IEditorPart currentEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
               .getActivePage().getActiveEditor();
         if (currentEditor instanceof WorkflowModelEditor)
         {
            this.model = ((WorkflowModelEditor) currentEditor).getWorkflowModel();
         }
      }
      else
      {
         for (int i = 0; i < PlatformUI.getWorkbench().getWorkbenchWindows().length; i++)
         {
            IWorkbenchWindow window = PlatformUI.getWorkbench().getWorkbenchWindows()[i];

            if (null != window.getActivePage())
            {
               IEditorPart activeEditor = window.getActivePage().getActiveEditor();
               if (activeEditor instanceof WorkflowModelEditor)
               {
                  this.model = ((WorkflowModelEditor) activeEditor).getWorkflowModel();
                  break;
               }
            }
         }
      }
      if (typeFinder == null)
      {
         typeFinder = new TypeFinder((EObject) model);
      }

      inferredIppVariables = CollectionUtils.newMap();
      inferredIppAccessPointsByName = CollectionUtils.newMap();
      inferredIppAccessPointsByType = CollectionUtils.newMap();
      
      inferredVariables = CollectionUtils.newMap();
      bpmAttributesToAp = CollectionUtils.newMap();
      bpmAttributesToXPath = CollectionUtils.newMap();      
      
      xPathToJavaType = CollectionUtils.newMap();    
      xPathToRealJavaType = CollectionUtils.newMap();      
      xPathToJavaField = CollectionUtils.newMap();      
   }

   @Override
   synchronized public void setCompilationUnit(CompilationUnitDeclaration compilationUnit)
   {
      super.setCompilationUnit(compilationUnit);
      this.cu = compilationUnit;
   }

   
   /*public void endVisit(IFieldReference fieldDeclaration) {
      // TODO Auto-generated method stub
      super.endVisit(fieldDeclaration);
  }*/
   
   /**
    * is called second, after inferredIppVariables was set
    * 
    * something like root1.Repetitive_Sequence_B___Partial_Fill_Details or
    * <CompleteOnMemberAccess:CURRENT_DATE.>
    * 
    */
   @SuppressWarnings("restriction")
   synchronized public boolean visit(IFieldReference iFieldReference)
   {
      FieldReference fieldReference = null;
      if (iFieldReference instanceof FieldReference) {
         fieldReference = (FieldReference)iFieldReference;
      }
      try
      {                  
         if ((fieldReference.receiver instanceof ArrayReference) && !(fieldReference.receiver instanceof JSArrayReference)) {
            fieldReference.receiver = this.buildArrayReference((Expression)fieldReference.receiver);
         } else {
            if ((fieldReference.receiver instanceof FieldReference) && !(fieldReference.receiver instanceof JSFieldReference)) {
               fieldReference.receiver = this.buildFieldReference((Expression)fieldReference.receiver);
            }                     
         }
         
         ProgramElement[] pe = cu.statements;   
         for (int i = 0; i < pe.length; i++) {
           if (!(pe[i] instanceof JSAssignment)) {
               if (pe[i] instanceof Assignment) {
                   pe[i] = (Assignment) buildAssignment((Assignment)pe[i]);
               }  else {
                  if (pe[i] instanceof CompletionOnMemberAccess && !(pe[i] instanceof JSCompletionOnMemberAccess)) {
                     pe[i] = buildCompletionOnMemberAccess((Expression)pe[i]);       
                  } else {
                     if (pe[i] instanceof FieldReference && !((pe[i] instanceof JSFieldReference) || (pe[i] instanceof JSCompletionOnMemberAccess))) {
                        pe[i] = buildFieldReference((Expression)pe[i]); 
                     }
                  }              
               }         
           }
         }
         
         ITypedElement data = findAccessPointFromDereferentiation(fieldReference);
         if (null != data)
         {
            
        	String xPath = findXPathFromDereferentiation(fieldReference);            
            Map<String, InferredType> inferredDerefPaths = inferredVariables
                  .get(((IIdentifiableElement) data).getId());
            
            if ((null != inferredDerefPaths) && !inferredDerefPaths.containsKey(xPath))
            {
               // node is referenced the first time, infer next level to have completion
               Stack<String> xPaths = new Stack<String>();
               xPaths.push(xPath);
               do
               {
                  // make sure parent nodes are inferred first
                  xPath = findParentXPath(xPath);                  
                  if (!xPaths.contains(xPath))
                  {
                     xPaths.push(xPath);
                  }
                  else
                  {
                     break;
                  }
               }
               while (!inferredDerefPaths.containsKey(xPath));

               do
               {
                  xPath = xPaths.pop();
                  InferredType attrType = inferredDerefPaths.get(xPath);
                  if (null == attrType)
                  {
                     boolean isJavaType = false;
                     String className = null;

                     boolean isXMLType = false;

                     if (data instanceof DataType)
                     {
                        // is primitive data
                        if (((DataType) data).getType().getId().equals(
                              PredefinedConstants.PRIMITIVE_DATA))
                        {
                           String type = AttributeUtil.getAttributeValue((DataType) data,
                                 PredefinedConstants.TYPE_ATT);
                           attrType = getInferredTypeFromJavaType(type, xPath, null);
                        }
                        else if (GenericUtils.isStructuredDataType((DataType) data)
                              || GenericUtils.isDMSDataType((DataType) data)
                              || GenericUtils.isXMLDataType((DataType) data))
                        {
                           // for all data we get an IAccessPathEditor from the
                           // AccessPointUtil
                           isXMLType = true;
                        }
                        else
                        {
                           String type = GenericUtils.getReferenceClassName((DataType) data);
                           attrType = getInferredTypeFromJavaType(type, xPath, null);
                           if(attrType == null)
                           {
                              isJavaType = true;
                              className = GenericUtils.getReferenceClassName((DataType) data);
                              attrType = addType(className.toCharArray());
                              attrType.isDefinition = true;
                              attrType.isAnonymous = false;                              
                           }                              
                        }
                     }

                     // XML, structured types (all children too)
                     if (attrType == null)
                     {
                        // ROOT                     
                        if ("/".equals(xPath)) //$NON-NLS-1$
                        {
                           attrType = addType((((IIdentifiableElement) data).getId()).toCharArray());
                        }
                        else
                        {
                           attrType = addType((((IIdentifiableElement) data).getId() + "_" + xPath.replace('/', '_')).toCharArray()); //$NON-NLS-1$
                        }
                        
                        attrType.isDefinition = true;
                        // TODO investigate if this should be true or false
                        attrType.isAnonymous = true;
                        isXMLType = true;
                     }
                     inferredDerefPaths.put(xPath, attrType);

                     // root access path editor for data
                     IAccessPathEditor apEditor = AccessPointUtil
                           .getSPIAccessPathEditor((DataTypeType) data.getMetaType());
                     
                     if (isXMLType)
                     {                     
                        List<AccessPointType> apAttributes;
                        // ROOT                     
                        if ("/".equals(xPath)) //$NON-NLS-1$
                        {
                           apAttributes = apEditor.getAccessPoints(null, (IExtensibleElement) data, DirectionType.INOUT_LITERAL);                        
                        }                        
                        else
                        {
                           int idx = xPath.lastIndexOf("/") + 1; //$NON-NLS-1$
                           String elementName = xPath.substring(idx, xPath.length());
                           ITypedElement accessPointType = inferredIppAccessPointsByName
                                 .get(elementName);
                           apAttributes = apEditor.getAccessPoints(null, (IExtensibleElement) accessPointType, DirectionType.INOUT_LITERAL);
                        } 
                        
                        List<XSDEnumerationFacet> enums = checkEnumeration(data);
                        if (enums != null) {
                        	addEnumsTo(attrType, xPath, enums);	
                        }                        
                        for (int i = 0; i < apAttributes.size(); ++i)
                        {
                           AccessPointType apAttribute = apAttributes.get(i);
                           String attrName = apAttribute.getId();
                           boolean isAtAttribute = false;
                           if (attrName.startsWith("@")) { //$NON-NLS-1$
                              attrName = attrName.replace("@", ""); //$NON-NLS-1$ //$NON-NLS-2$
                              isAtAttribute = true;
                           }
                           InferredAttribute ia = attrType.addAttribute(attrName.toCharArray(), cu, 0);
                           if (isAtAttribute) {
                              addContentMethodsTo(attrType);                              
                           }
                           boolean isArray = false;                           
                           if (apAttribute.getType().getId().startsWith("prim")) //$NON-NLS-1$
                           {
                              if (apAttribute instanceof StructAccessPointType)
                              {
                                 isArray = AttributeUtil.getBooleanValue(apAttribute, "carnot:engine:data:indexed"); //$NON-NLS-1$
                                 if(isArray)                                    
                                 {                                    
                                    if (apAttribute instanceof StructAccessPointType) {
                                       arrayMap.put(((StructAccessPointType)apAttribute).getXPath().toString(), "true");                                   //$NON-NLS-1$
                                       ia.type = getInferredTypeFromXPath(((StructAccessPointType) apAttribute).getXPath());
                                    } else {
                                       arrayMap.put(apAttribute.getId(), "true");                                     //$NON-NLS-1$
                                    }
                                 }
                                 else
                                 {
                                    ia.type = getInferredTypeFromXPath(((StructAccessPointType) apAttribute).getXPath());
                                 }
                              }
                              else if (apAttribute instanceof PlainXMLAccessPointType)
                              {
                                 isArray = AttributeUtil.getBooleanValue(apAttribute, "carnot:engine:data:indexed"); //$NON-NLS-1$
                                 if(isArray)
                                 {                                    
                                    if (apAttribute instanceof StructAccessPointType) {
                                       arrayMap.put(((StructAccessPointType)apAttribute).getXPath().toString(), "true"); //$NON-NLS-1$
                                    } else {
                                       arrayMap.put(apAttribute.getId(), "true");                                     //$NON-NLS-1$
                                    }
                                 }
                                 else
                                 {
                                    ia.type = getInferredTypeFromXPath(((PlainXMLAccessPointType) apAttribute).getXPath());
                                 }
                              }                              
                           }
                           else
                           {
                              isArray = AttributeUtil.getBooleanValue(apAttribute, "carnot:engine:data:indexed"); //$NON-NLS-1$
                              if(isArray)
                              {
                                 if (apAttribute instanceof StructAccessPointType) {
                                    arrayMap.put(((StructAccessPointType)apAttribute).getXPath().toString(), "true"); //$NON-NLS-1$
                                 } else {
                                    arrayMap.put(apAttribute.getId(), "true");                                     //$NON-NLS-1$
                                 }
                              }
                           }
                           
                           bpmAttributesToAp.put(ia, data);
                           if ("/".equals(xPath)) //$NON-NLS-1$
                           {                           
                              bpmAttributesToXPath.put(ia, "/" + apAttribute.getId()); //$NON-NLS-1$
                           }
                           else
                           {
                              bpmAttributesToXPath.put(ia, xPath + "/" + apAttribute.getId());                                  //$NON-NLS-1$
                           }                                 
                           inferredIppAccessPointsByName.put(apAttribute.getId(), (AccessPointType) apAttribute);
                        }
                     }
                     if (isJavaType)
                     {
                        InferredType myType = null;
                        if (!("/".equals(xPath))) //$NON-NLS-1$
                        {                           
                           String useClassName = xPathToJavaType.get(xPath);;
                           if(!StringUtils.isEmpty(useClassName))
                           {
                             className = useClassName;
      
                              myType = getInferredTypeFromJavaType(className, xPath, null);
                              if(myType == null)
                              {
                                 myType = addType(className.toCharArray());
                                 myType.isDefinition = true;
                                 myType.isAnonymous = false;
                              }                                 
                              inferredDerefPaths.put(xPath, myType);                              
                           }
                        }                        
                        if (!("/".equals(xPath)) && myType == null) //$NON-NLS-1$
                        {                           
                           String useClassName = xPathToJavaField.get(xPath);
                           if(!StringUtils.isEmpty(useClassName))
                           {
                              className = useClassName;
                              myType = getInferredTypeFromJavaType(className, xPath, null);
                              if(myType == null)
                              {
                                 myType = addType(className.toCharArray());
                                 myType.isDefinition = true;
                                 myType.isAnonymous = false;
                              }                                 
                              inferredDerefPaths.put(xPath, myType);                              
                           }
                        }                        
                        if(myType == null)
                        {
                           myType = attrType;
                        }                        
                        
                        // must we implement visit for methods (for children)?
                        if (className != null)
                        {
                           inferreJavaFields(xPath, myType, className);                           
                           inferreJavaMethods(xPath, myType, className);
                        }
                     }
                  }
               }
               while (!xPaths.isEmpty());;
            }
         }
      }
      catch (Throwable t)
      {
         t.printStackTrace();
      }
      return super.visit(fieldReference);
   }

   private void inferreJavaMethods(String xPath, InferredType attrType,
         String className) throws JavaModelException
   {
      TypeInfo typeInfo = null;
      if (className.startsWith("java.util.List_")) { //$NON-NLS-1$
    	  typeInfo = typeFinder.findType("java.util.List");    	   //$NON-NLS-1$
      } else {
    	  typeInfo = typeFinder.findType(className);  
      }	  
      if (typeInfo != null) {
          String[] parameterSignatures = {}; 
          String[] parameterTypes = {}; 
          List methods = typeInfo.getMethods();         
          if (className.endsWith("ActivityInstance")) { //$NON-NLS-1$
             MethodInfo mi = new MethodInfo(false, "getAge", parameterSignatures, parameterTypes, null, "J", "long", true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi);             
          }
          if (className.endsWith("Activity")) {             //$NON-NLS-1$
             MethodInfo mi = new MethodInfo(false, "getMeasure", parameterSignatures, parameterTypes, null, "Ljava.lang.String;", "java.lang.String", true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi);
             mi = new MethodInfo(false, "getTargetMeasureQuantity", parameterSignatures, parameterTypes, null, "J", "long", true);        //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi);
             mi = new MethodInfo(false, "getDifficulty", parameterSignatures, parameterTypes, null, "I", "int", true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi);
             mi = new MethodInfo(false, "getTargetProcessingTime", parameterSignatures, parameterTypes, null, "J", "long", true);  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi);
             mi = new MethodInfo(false, "getTargetIdleTime", parameterSignatures, parameterTypes, null, "J", "long", true);  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi);
             mi = new MethodInfo(false, "getTargetWaitingTime", parameterSignatures, parameterTypes, null, "J", "long", true);  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi);
             mi = new MethodInfo(false, "getTargetQueueDepth", parameterSignatures, parameterTypes, null, "J", "long", true);  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi);
             mi = new MethodInfo(false, "getTargetCostPerExecution", parameterSignatures, parameterTypes, null, "D", "double", true);  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi);             
             mi = new MethodInfo(false, "getTargetCostPerSecond", parameterSignatures, parameterTypes, null, "D", "double", true);  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi); 
             mi = new MethodInfo(false, "getResourcePerformanceCalculation", parameterSignatures, parameterTypes, null, "B", "boolean", true);  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
             methods.add(mi);    
          }
          for (int m = 0; m < methods.size(); m++)
          {
             MethodInfo methodInfo = (MethodInfo) methods.get(m);
             String methodName = getMethodName(methodInfo);
             if (!methodName.startsWith("<") && methodInfo.isAccessible()) //$NON-NLS-1$
             {
            	MethodDeclaration md = new MethodDeclaration(cu.compilationResult);;
                InferredMethod inferredMethod = attrType.addMethod(methodName.toCharArray(), md, 0);
                String returnType = methodInfo.getReturnType();                
                String xPathForJava;         
                if ("/".equals(xPath)) //$NON-NLS-1$
                {
                   xPathForJava = xPath + methodName;
                }
                else
                {
                   xPathForJava = xPath + "/" + methodName;                                     //$NON-NLS-1$
                }
                if (returnType.startsWith("java.util.List<")) { //$NON-NLS-1$
                	String realType = returnType.replaceAll("java.util.List", ""); //$NON-NLS-1$ //$NON-NLS-2$
                	realType = realType.replaceAll("<", ""); //$NON-NLS-1$ //$NON-NLS-2$
                	realType = realType.replaceAll(">", ""); //$NON-NLS-1$ //$NON-NLS-2$
                	returnType = "java.util.List_" + realType; //$NON-NLS-1$
                	xPathToRealJavaType.put(xPathForJava, realType);                	
                }
                if (xPathToRealJavaType.get(xPath) != null) {
                	returnType = xPathToRealJavaType.get(xPath);
                }
                xPathToJavaType.put(xPathForJava, returnType);                                           
                int paramCnt = methodInfo.getParameterCount();
                InferredType iType = getInferredTypeFromJavaType(returnType, xPathForJava, className);
                if(iType == null)
                {
                   iType = addType(returnType.toCharArray());
                   iType.isDefinition = true;
                   iType.isAnonymous = true;                                    
                }                                 
                SingleTypeReference sRef = new SingleTypeReference(iType.getName(), 0); 
                sRef.resolvedType = null;
                md.returnType = sRef;
                md.inferredType = iType;

                if(paramCnt > 0)
                {
                   md.arguments = new Argument[paramCnt];
                }                                    
                                                    
                for (int p = 0; p < paramCnt; p++)
                {
                   md.arguments[p] = new Argument(new String("param" + p).toCharArray(), 0, null, 0);; //$NON-NLS-1$
                }                                    
             }
          }
      }
   }

   private void inferreJavaFields(String xPath, InferredType attrType,
         String className) throws JavaModelException
   {
      TypeInfo typeInfo = typeFinder.findType(className);
      if (typeInfo != null) {
          List fields = typeInfo.getFields();      
          for (int f = 0; f < fields.size(); f++)
          {
             FieldInfo fieldInfo = (FieldInfo) fields.get(f);             
             if (fieldInfo.isPublic() || fieldInfo.isEnum()) {
                 String fieldName = fieldInfo.getFieldName();
                 String fieldType = fieldInfo.getFieldType();             
                 InferredAttribute iAttribute = attrType.addAttribute(fieldName.toCharArray(), cu, 0);
                 String xPathForJavaField;
                 // ROOT                     
                 if ("/".equals(xPath)) //$NON-NLS-1$
                 {
                    xPathForJavaField = xPath + fieldName;
                 }
                 else
                 {
                    xPathForJavaField = xPath + "/" + fieldName; //$NON-NLS-1$
                 }
                 xPathToJavaField.put(xPathForJavaField, fieldName);   
                 
                 InferredType iType = getInferredTypeFromJavaType(fieldType, xPath, null);
                 if(iType == null)
                 {
                    iType = addType(fieldType.toCharArray());
                    iType.isDefinition = true;
                    iType.isAnonymous = true;                                    
                 }            
                 iAttribute.type = iType;            	 
             }
          }
      }
   }   
   
   private void addGetTargetExecutionTimeTo(InferredType attrType)
   {
      //String getContent()
      MethodDeclaration md = new MethodDeclaration(
            cu.compilationResult);
      InferredMethod inferredMethod = attrType.addMethod("getTargetExecutionTime".toCharArray(), md, 0); //$NON-NLS-1$
      String returnType = "String";       //$NON-NLS-1$
      InferredType iType = StringType;                                
      md.returnType = new SingleTypeReference(iType.getName(), 0);
      md.inferredType = iType;      
   }
   
   private void addContentMethodsTo(InferredType attrType)
   {
      //String getContent()
      MethodDeclaration md = new MethodDeclaration(
            cu.compilationResult);
      InferredMethod inferredMethod = attrType.addMethod("getContent".toCharArray(), md, 0); //$NON-NLS-1$
      String returnType = "String";       //$NON-NLS-1$
      InferredType iType = StringType;                                
      md.returnType = new SingleTypeReference(iType.getName(), 0);
      md.inferredType = iType;
      
      //void setContent(String content)
      md = new MethodDeclaration(cu.compilationResult);
      inferredMethod = attrType.addMethod("setContent".toCharArray(), md, 0);            //$NON-NLS-1$
      returnType = "void"; //$NON-NLS-1$
      iType = getInferredTypeFromJavaType(returnType, null, null);
      if(iType == null)
      {
         iType = addType(returnType.toCharArray());
         iType.isDefinition = true;
         iType.isAnonymous = true;                                    
      } 
      
      md.returnType = new SingleTypeReference(iType.getName(), 0);
      md.arguments = new Argument[1];                                                                                 
      md.arguments[0] = new Argument(new String("param" + 0).toCharArray(), 0, null, 0);                                                 //$NON-NLS-1$
   }
   
   private void addEnumsTo(InferredType attrType, String xPath, List<XSDEnumerationFacet> enums)
   {
       for (Iterator<XSDEnumerationFacet> i = enums.iterator(); i.hasNext();) {
    	   XSDEnumerationFacet facet = i.next();
    	   String fieldName = facet.getLexicalValue();   
    	   InferredAttribute iAttribute = attrType.addAttribute(fieldName.toCharArray(), cu, 0);
           String xPathForJavaField;
           // ROOT                     
           if ("/".equals(xPath)) //$NON-NLS-1$
           {
              xPathForJavaField = xPath + fieldName;
           }
           else
           {
              xPathForJavaField = xPath + "/" + fieldName; //$NON-NLS-1$
           }
           xPathToJavaField.put(xPathForJavaField, fieldName);   
           
           InferredType iType = getInferredTypeFromJavaType(Type.String.getId(), xPath, null);
           if(iType == null)
           {
              iType = addType(Type.String.getId().toCharArray());
              iType.isDefinition = true;
              iType.isAnonymous = true;                                    
           }            
           iAttribute.type = iType; 
       }	   	                                                       
   }   

   synchronized public boolean visit(InferredAttribute inferredAttr)
   {
      boolean result = super.visit(inferredAttr);
      if (null == inferredAttr.type)
      {
         final String xPath = bpmAttributesToXPath.get(inferredAttr);
         if (null != xPath)
         {
            // find the data this attribute was inferred against
            final ITypedElement data = bpmAttributesToAp.get(inferredAttr);
            if (null != data)
            {
               Map<String, InferredType> inferredDerefPaths = inferredVariables
                     .get(((IIdentifiableElement) data).getId());
               // if the node targeted by the xPath is currently referenced from
               // JavaScript, there will be a type entry in the map
               // ??
               InferredType attrType = inferredDerefPaths.get(xPath);
               if (null != attrType)
               {
                  if (false /* TODO if is list */)
                  {
                     inferredAttr.type = new InferredType(InferredType.ARRAY_NAME);
                     inferredAttr.type.referenceClass = StringType;
                  }
                  else
                  {
                     inferredAttr.type = attrType;
                  }
               }
            }
         }
      } 
      return result;
   }

   @Override
   @SuppressWarnings("restriction")
   synchronized public void endVisit(ILocalDeclaration localDeclaration)
   {
      super.endVisit(localDeclaration);
      try
      {
         endVisitImpl(localDeclaration);
      }
      catch (Throwable t)
      {
         t.printStackTrace();
      }
   }

   public boolean visit(ILocalDeclaration localDeclaration) {
	   boolean result = super.visit(localDeclaration); 
	   localDeclaration.setInferredType(null);
	   IExpression expr = localDeclaration.getInitialization();
	   if (expr instanceof CompletionOnMemberAccess) {
		   expr = this.buildCompletionOnMemberAccess((Expression) expr);
	   }
	   ((LocalDeclaration)localDeclaration).initialization = (Expression) expr;
	   return result;
   }

/**
    * called first ('var root1 = ippInitialize("root");' as example) here we must create
    * access points and fill
    * 
    * inferredIppAccessPointsByType inferredVariables inferredIppVariables
    */
   synchronized protected void endVisitImpl(ILocalDeclaration localDeclaration)
   {
      try {
    	  String name = new String(localDeclaration.getName());
          if (null == localDeclaration.getInferredType())
          {
             if (localDeclaration.getInitialization() instanceof MessageSend)
             {
                MessageSend initializer = ((MessageSend) localDeclaration.getInitialization());
                if (Arrays.equals(NAME_IPP_INITIALIZE, initializer.selector)
                      && (1 == initializer.arguments.length))
                {
                   Expression typeArgument = initializer.arguments[0];
                   if (typeArgument instanceof StringLiteral)
                   {
                      // the data name, can contain also "(" or other chars
                      String targetTypeName = String.valueOf(((StringLiteral) typeArgument)
                            .source());
                      ITypedElement accessPoint = inferredIppAccessPointsByType
                            .get(targetTypeName);                 
                      ITypedElement dataType = null;
                      if ((null == accessPoint) && (null != model))
                      {
                         if (targetTypeName.indexOf(".") > -1) { //$NON-NLS-1$
                             DataType serializableType = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
                             serializableType.setId(name);
                             serializableType.setName(name);
                             serializableType.setPredefined(true);
                             DataTypeType serializableDataType = (DataTypeType) ModelUtils.findElementById(model.getDataType(), PredefinedConstants.SERIALIZABLE_DATA);
                             //(rp) Workaround for CRNT-12167
                             try {
                                 Reflect.setFieldValue(serializableType, "type", serializableDataType);                        	 //$NON-NLS-1$
                             } catch (Throwable t) {                        	
                            	 serializableType.setType(serializableDataType);
                             }
                             //(rp) End of Workaround
                             AttributeUtil.setAttribute(serializableType, PredefinedConstants.BROWSABLE_ATT, "true"); //$NON-NLS-1$
                             AttributeUtil.setAttribute(serializableType, PredefinedConstants.CLASS_NAME_ATT, targetTypeName);
                             dataType = serializableType;
                         } else {
                        	 if (PredefinedConstants.ACTIVITY_INSTANCE_ACCESSPOINT.equals(name))
                             {
                                DataType activityInstance = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
                                activityInstance.setId(PredefinedConstants.ACTIVITY_INSTANCE_ACCESSPOINT);
                                activityInstance.setName("Activity Instance"); //$NON-NLS-1$
                                activityInstance.setPredefined(true);
                                DataTypeType serializableDataType = (DataTypeType) ModelUtils.findElementById(model.getDataType(), PredefinedConstants.SERIALIZABLE_DATA);
                                //(rp) Workaround for CRNT-12167
                                try {
                                    Reflect.setFieldValue(activityInstance, "type", serializableDataType);                        	 //$NON-NLS-1$
                                } catch (Throwable t) {                        	
                                    activityInstance.setType(serializableDataType);
                                }
                                //(rp) End of Workaround
                                AttributeUtil.setAttribute(activityInstance, PredefinedConstants.BROWSABLE_ATT, "true"); //$NON-NLS-1$
                                AttributeUtil.setAttribute(activityInstance, PredefinedConstants.CLASS_NAME_ATT, ActivityInstance.class.getName());
                                dataType = activityInstance;
                             }
                             else
                             {
                              dataType = (DataType) ModelUtils.findIdentifiableElement(
                                    model.getData(), name);
                              CodeCompletionHelper.getInstance()
                                    .getTypeMap()
                                    .put(name, dataType);

                             }
                         }

                         if (dataType != null)
                         {
                            accessPoint = dataType;
                         }
                         else
                         {
                            ModelType referModel = model;
                            String[] namePath = targetTypeName.split("/"); //$NON-NLS-1$
                            if (!model.getId().equals(namePath[0]) && namePath.length > 1)
                            {
                               referModel = ModelUtils.getExternalModel(model, namePath[0]);
                               targetTypeName = namePath[1];
                            }
                            IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(referModel,
                                  targetTypeName);
                            accessPoint = new StructAccessPointType(xPathMap.getRootXPath(),
                                  xPathMap);
                            ((StructAccessPointType) accessPoint).setId(targetTypeName);
                            ((StructAccessPointType) accessPoint)
                                  .setType((DataTypeType) ModelUtils.findIdentifiableElement(
                                        referModel.getDataType(), "struct"));                         //$NON-NLS-1$
                         }
                         inferredIppAccessPointsByType.put(targetTypeName, accessPoint);
                      }
                      if (null != accessPoint)
                      {
                    	 String variableName = String.valueOf(localDeclaration.getName());
                    	 CodeCompletionHelper.getInstance().getTypeMap().put(variableName, accessPoint);
                    	 Map<String, InferredType> derefPaths = inferredVariables
                               .get(((IIdentifiableElement) accessPoint).getId());

                         if (null == derefPaths)
                         {
                            InferredType apType = addType(targetTypeName.toCharArray());
                            apType.isDefinition = true;
                            // TODO throws NPE since Thursday apType.superClass = ObjectType;

                            derefPaths = CollectionUtils.newMap();
                            // derefPaths.put("/", apType);
                            inferredVariables.put(((IIdentifiableElement) accessPoint)
                                  .getId(), derefPaths);
                            if (dataType != null && dataType instanceof DataType
                                  && !(GenericUtils.isStructuredDataType((DataType) dataType)
                                   || GenericUtils.isDMSDataType((DataType) dataType)
                                   || GenericUtils.isXMLDataType((DataType) dataType)))
                            {
                               String javaType = GenericUtils.getReferenceClassName((DataType) dataType);
                               if(javaType != null)
                               {
                                  InferredType iType = getInferredTypeFromJavaType(javaType, null, null);
                                  if(iType != null)
                                  {
                                     localDeclaration.setInferredType(iType);
                                  }
                               }
                            }
                         }
                         else
                         {
                            localDeclaration.setInferredType(derefPaths.get("/")); //$NON-NLS-1$
                         }
                         inferredIppVariables.put(String.valueOf(localDeclaration.getName()),
                               accessPoint);
                      }
                   }
                }
             }
          }

      } catch (Throwable t) {
    	  
      }
   }

   /**
    * is called by visit to get the access point from earlier filled
    * 
    * inferredIppVariables
    */
   synchronized private ITypedElement findAccessPointFromDereferentiation(Reference reference)
   {
      ITypedElement result = null;
      // find upmost object reference

      Expression receiver = reference;
      do
      {
         if (receiver instanceof FieldReference)
         {
            receiver = ((FieldReference) receiver).receiver;
         }
         else if (receiver instanceof ArrayReference)
         {
            receiver = ((ArrayReference) receiver).receiver;
         }
         else if (receiver instanceof MessageSend)
         {
            receiver = ((MessageSend) receiver).receiver;
         }
      }
      while ((receiver instanceof FieldReference) || (receiver instanceof ArrayReference) || (receiver instanceof MessageSend));

      if (null != model)
      {
         // TODO support other APs
         if (receiver instanceof SingleNameReference)
         {
            final String accessPointId = String
                  .valueOf(((SingleNameReference) receiver).token);
            result = inferredIppVariables.get(accessPointId);
         }
      }
      return result;
   }

   synchronized public void doInfer()
   {
      try
      {
         super.doInfer();
      }
      catch (Throwable ex)
      {
    	  ex.printStackTrace();
      }
   }

   synchronized private String findXPathFromDereferentiation(FieldReference fieldReference)
   {
      FieldReference saveFieldReference = fieldReference;
      Stack stack = new Stack();            
      // build XPath in reverse
      StringBuffer xPath = new StringBuffer();
      Expression receiver = fieldReference;
    
      // java types
      do
      {
         if (receiver instanceof FieldReference)
         {
            receiver = ((FieldReference) receiver).receiver;
         }
         else if (receiver instanceof ArrayReference)
         {
            receiver = ((ArrayReference) receiver).receiver;
         }
         else if (receiver instanceof MessageSend)
         {
            String selector = String.valueOf(((MessageSend) receiver).selector);
            stack.push(selector);
            receiver = ((MessageSend) receiver).receiver;
         }
         else if (receiver instanceof SingleNameReference)
         {
            break;
         }
      }
      while ((receiver instanceof FieldReference) 
            || (receiver instanceof ArrayReference)
            || (receiver instanceof MessageSend)
            || (receiver instanceof SingleNameReference));
      
      while(!stack.isEmpty())
      {
         String entry = (String) stack.pop();
         if(xPath.length() == 0)
         {
            xPath.insert(0, "/");                //$NON-NLS-1$
            xPath.append(entry);
         }
         else
         {
            xPath.append("/"); //$NON-NLS-1$
            xPath.append(entry);            
         }         
      }
      if(xPath.length() != 0)
      {
         return xPath.toString();
      }      
      xPath = new StringBuffer();
      receiver = saveFieldReference;
      do
      {
         if (receiver instanceof FieldReference)
         {
            xPath.insert(0, String.valueOf(((FieldReference) receiver).token));
            xPath.insert(0, "/"); //$NON-NLS-1$

            receiver = ((FieldReference) receiver).receiver;
         }
         else if (receiver instanceof ArrayReference)
         {
            receiver = ((ArrayReference) receiver).receiver;
         }
      }
      while ((receiver instanceof FieldReference) || (receiver instanceof ArrayReference));

      return (0 < xPath.length()) ? xPath.toString() : "/"; //$NON-NLS-1$
   }  



synchronized private String findParentXPath(String xPath)
   {
      int posSeparator = xPath.lastIndexOf("/"); //$NON-NLS-1$

      if (0 < posSeparator)
      {
         return xPath.substring(0, posSeparator);
      }
      else
      {
         return "/"; //$NON-NLS-1$
      }
   }

   synchronized private InferredType getInferredTypeFromJavaType(String type, String xPath, String className)
   {
	  InferredType inferredType = null;
      if (type.equals(Type.Calendar.getId()) 
            || type.equals(Type.Timestamp.getId()))
      {
         // JavaScript has already a Date type
         inferredType = addType("Date".toCharArray()); //$NON-NLS-1$
      }      
      if (type.equals(Type.String.getId()) 
            || type.equals(Type.Char.getId()) 
            || type.equals(String.class.getName())
            || type.equals(Character.class.getName()))
      {
         inferredType = StringType;
      }
      else if (type.equals(Type.Double.getId()) 
            || type.equals(Type.Float.getId()) 
            || type.equals(Type.Integer.getId())
            || type.equals(Type.Long.getId()) 
            || type.equals(Integer.class.getName())
            || type.equals(BigInteger.class.getName())
            || type.equals(Long.class.getName())
            || type.equals(Short.class.getName())
            || type.equals(BigDecimal.class.getName())
            || type.equals(Float.class.getName())
            || type.equals(Number.class.getName())
            || type.equals(Double.class.getName())
            || type.equals(Type.Byte.getId())
            || type.equals(Byte.class.getName()))         
      {
         inferredType = NumberType;
      }
      else if (type.equals(Type.Boolean.getId())
            || type.equals(Boolean.class.getName()))
      {
         inferredType = BooleanType;
      }
      else if (type.equals("void") //$NON-NLS-1$
            || type.equals(Void.class.getName()))
      {
         inferredType = VoidType;
      }
      else if (type.equals(Object.class.getName())
            || type.equals("java.lang.Class<java.lang.Object>")) //$NON-NLS-1$
      {
         inferredType = ObjectType;         
      }  
      else if (type.endsWith("[]") && (className != null)) //$NON-NLS-1$
      {
          InferredType myType = null;            
          String fullQualifiedName = getFullQualifiedName(type, className);
          if (fullQualifiedName != null) {
              fullQualifiedName = fullQualifiedName.replace("[]", ""); //$NON-NLS-1$ //$NON-NLS-2$
              if (xPath.startsWith("/")) { //$NON-NLS-1$
            	  xPath = xPath.substring(1, xPath.length());  
              }
              arrayMap.put(xPath, "true"); //$NON-NLS-1$
              myType = addType(fullQualifiedName.toCharArray());
              myType.isDefinition = true;
              myType.isAnonymous = false;
              try
              {
                 inferreJavaFields(xPath.toString(), myType, fullQualifiedName);
                 inferreJavaMethods(xPath.toString(), myType, fullQualifiedName);
              }
              catch (JavaModelException e)
              {
              }                           	  
          } else {
        	  myType = ArrayType;
          }
          return myType;              
      }      
      return inferredType;
   }

   private String getFullQualifiedName(String type, String className) {
      IJavaProject jp = JavaCore.create(ModelUtils.getProjectFromEObject(this.model));
      JSClassLoader cl = new JSClassLoader(jp);
      Class clazz;
	try {
		clazz = cl.loadClass(className);
		if (clazz == null) {
			return null;
		}
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (type.equalsIgnoreCase(field.getType().getSimpleName())) {
				return field.getType().getCanonicalName();
			}
		}
	} catch (Throwable t) {
		return null;
		//t.printStackTrace();
	} 
	return null;
}

synchronized private InferredType getInferredTypeFromXPath(TypedXPath xPath)
   {
      int type = xPath.getType();
      if (type == BigData.BOOLEAN)
      {
         return BooleanType;
      }
      if (type == BigData.DATE)
      {
         InferredType myType = null;            
         String className = "java.util.Date"; //$NON-NLS-1$
         myType = addType(className.toCharArray());
         myType.isDefinition = true;
         myType.isAnonymous = false;
         try
         {
            inferreJavaFields(xPath.toString(), myType, className);
            inferreJavaMethods(xPath.toString(), myType, className);
         }
         catch (JavaModelException e)
         {
         }                           
         return myType;         
         // JavaScript has already a Date type
         // return addType("Date".toCharArray());
      }           
      if (type == BigData.BYTE)
      {
         return NumberType;         
      }
      if (type == BigData.DOUBLE)
      {
         return NumberType;
      }
      if (type == BigData.FLOAT)
      {
         return NumberType;
      }
      if (type == BigData.INTEGER)
      {
         return NumberType;
      }
      if (type == BigData.LONG)
      {
         return NumberType;
      }
      if (type == BigData.SHORT)
      {
         return NumberType;         
      }
      return StringType;
   }
   
   synchronized public String getMethodName(MethodInfo methodInfo)
   {
      return methodInfo.getLabel().substring(0, methodInfo.getLabel().indexOf("(")); //$NON-NLS-1$
   }   
   
   synchronized private JSAssignment buildAssignment(Assignment assignment) {
      JSAssignment ass = new JSAssignment(assignment.lhs, assignment.expression, assignment.sourceEnd);
      ass.bits = assignment.bits;
      ass.constant = assignment.constant;
      // ass.implicitConversion = assignment.implicitConversion;
      ass.resolvedType = assignment.resolvedType;
      ass.sourceEnd = assignment.sourceEnd;
      ass.sourceStart = assignment.sourceStart;
      ass.statementEnd = assignment.statementEnd;
      return ass;
  }

   
   synchronized  private Expression buildCompletionOnMemberAccess(
         Expression expression) {
     if (expression instanceof CompletionOnMemberAccess) {
         CompletionOnMemberAccess fieldReference = (CompletionOnMemberAccess)expression;
         CompletionOnMemberAccess fr = new JSCompletionOnMemberAccess(fieldReference.token, fieldReference.nameSourcePosition, fieldReference.isInsideAnnotation);
         fr.binding = fieldReference.binding;
         fr.bits = fieldReference.bits;
         fr.constant = fieldReference.constant;
         // fr.implicitConversion = fieldReference.implicitConversion;
         fr.nameSourcePosition = fieldReference.nameSourcePosition;
         fr.receiver = fieldReference.receiver;
         fr.receiverType = fieldReference.receiverType;
         fr.resolvedType = fieldReference.resolvedType;
         //fr.resolvedType = null;
         fr.sourceEnd = fieldReference.sourceEnd;
         fr.sourceStart = fieldReference.sourceStart;
         fr.statementEnd = fieldReference.statementEnd;
         fr.token = fieldReference.token;
         fr.isInsideAnnotation = fieldReference.isInsideAnnotation;
         ((JSCompletionOnMemberAccess)fr).setArrayMap(this.arrayMap);         
         return fr;
     } else {
         return null;
     }
 }

 synchronized  private Expression buildFieldReference(Expression expression) {
      if (expression instanceof FieldReference) {
          FieldReference fieldReference = (FieldReference)expression;
          FieldReference fr = new JSFieldReference(fieldReference.token, fieldReference.nameSourcePosition);
          fr.binding = fieldReference.binding;
          fr.bits = fieldReference.bits;
          fr.constant = fieldReference.constant;
          // fr.implicitConversion = fieldReference.implicitConversion;
          fr.nameSourcePosition = fieldReference.nameSourcePosition;
          fr.receiver = fieldReference.receiver;
          fr.receiverType = fieldReference.receiverType;
          fr.resolvedType = fieldReference.resolvedType;
          fr.sourceEnd = fieldReference.sourceEnd;
          fr.sourceStart = fieldReference.sourceStart;
          fr.statementEnd = fieldReference.statementEnd;
          fr.token = fieldReference.token;
          ((JSFieldReference)fr).setArrayMap(this.arrayMap);
          return fr;
      } else {
          return null;
      }
  }
 
 synchronized private Expression buildArrayReference(Expression expression) {
    if (expression instanceof ArrayReference) {
       ArrayReference fieldReference = (ArrayReference)expression;
       ArrayReference fr = new JSArrayReference(fieldReference.receiver, fieldReference.position);        
       fr.bits = fieldReference.bits;
       fr.constant = fieldReference.constant;
       // fr.implicitConversion = fieldReference.implicitConversion;
       fr.receiver = fieldReference.receiver;        
       fr.resolvedType = fieldReference.resolvedType;
       fr.sourceEnd = fieldReference.sourceEnd;
       fr.sourceStart = fieldReference.sourceStart;
       fr.statementEnd = fieldReference.statementEnd;       
       ((JSArrayReference)fr).setArrayMap(this.arrayMap);
       return fr;
    } else {
        return null;
    }        
 }
 
 synchronized public boolean visit(IBinaryExpression iBinaryExpression)
 {
    BinaryExpression binaryExpression = null;    
    if (iBinaryExpression instanceof BinaryExpression) {
       binaryExpression = (BinaryExpression)iBinaryExpression;
    }
    if (binaryExpression != null) {
       //rhs
       if (binaryExpression.right instanceof CompletionOnMemberAccess && !(binaryExpression.right instanceof JSCompletionOnMemberAccess)) {
          binaryExpression.right = buildCompletionOnMemberAccess(binaryExpression.right); 
       } else {
          if (binaryExpression.right instanceof FieldReference && !((binaryExpression.right instanceof JSFieldReference) || (binaryExpression.right instanceof JSCompletionOnMemberAccess))) {
             binaryExpression.right = buildFieldReference(binaryExpression.right); 
          }      
       }
       //lhs
       if (binaryExpression.left instanceof CompletionOnMemberAccess && !(binaryExpression.left instanceof JSCompletionOnMemberAccess)) {
          binaryExpression.left = buildCompletionOnMemberAccess(binaryExpression.left); 
       } else {
          if (binaryExpression.left instanceof FieldReference && !((binaryExpression.left instanceof JSFieldReference) || (binaryExpression.left instanceof JSCompletionOnMemberAccess))) {
             binaryExpression.left = buildFieldReference(binaryExpression.left); 
          }      
       }       
    }
    return super.visit(binaryExpression);
 }
 
 synchronized public boolean visit(IAssignment iAssignment) {
    Assignment assignment = null;    
    if (iAssignment instanceof Assignment) {
       assignment = (Assignment)iAssignment;
    }
    if (assignment != null) {
       //expression   
       if (assignment.expression instanceof CompletionOnMemberAccess && !(assignment.expression instanceof JSCompletionOnMemberAccess)) {
          assignment.expression = buildCompletionOnMemberAccess(assignment.expression);       
       } else {
          if (assignment.expression instanceof FieldReference && !((assignment.expression instanceof JSFieldReference) || (assignment.expression instanceof JSCompletionOnMemberAccess))) {
             assignment.expression = buildFieldReference(assignment.expression); 
          }
       }
       //lhs   
       if (assignment.lhs instanceof CompletionOnMemberAccess && !(assignment.lhs instanceof JSCompletionOnMemberAccess)) {
          assignment.lhs = buildCompletionOnMemberAccess(assignment.lhs);       
       } else {
          if (assignment.lhs instanceof FieldReference && !((assignment.lhs instanceof JSFieldReference) || (assignment.lhs instanceof JSCompletionOnMemberAccess))) {
             assignment.lhs = buildFieldReference(assignment.lhs); 
          }
       }       
    }
    return super.visit(assignment);
 }
 
 synchronized public boolean visit(IBlock iBlock)
 {
    Block block = null;
    if (iBlock instanceof Block) {
       block = (Block)iBlock;
    }
    if (block != null) {
       Statement[] statements = block.statements;
       if(statements == null)
       {
          return super.visit(block);      
       }
       for (int i = 0; i < statements.length; i++) {
          if (statements[i] instanceof Assignment && !(statements[i] instanceof JSAssignment)) {
             statements[i] = (Assignment) buildAssignment((Assignment)statements[i]);
          } else {
              if (statements[i] instanceof CompletionOnMemberAccess && !(statements[i] instanceof JSCompletionOnMemberAccess)) {
                  statements[i] = buildCompletionOnMemberAccess((Expression)statements[i]);       
              } else {
                 if (statements[i] instanceof ArrayReference && !(statements[i] instanceof JSArrayReference)) {
                    statements[i] = buildArrayReference((Expression)statements[i]);       
                 } else {                  
                    if (statements[i] instanceof FieldReference && !((statements[i] instanceof JSFieldReference) || (statements[i] instanceof JSCompletionOnMemberAccess))) {
                       statements[i] = buildFieldReference((Expression)statements[i]); 
                    }
                }            
             }
          }         
       }              
    }
   return super.visit(iBlock);
 }
 
 synchronized public boolean visit(IArrayReference iArrayReference)
 {
    ArrayReference arrayReference = null;
    if (iArrayReference instanceof ArrayReference) {
       arrayReference = (ArrayReference)iArrayReference;
    }
    if (arrayReference != null) {
       if (arrayReference.receiver instanceof CompletionOnMemberAccess && !(arrayReference.receiver instanceof JSCompletionOnMemberAccess)) {
          arrayReference.receiver = buildCompletionOnMemberAccess(arrayReference.receiver);       
       } else {
          if (arrayReference.receiver instanceof FieldReference && !((arrayReference.receiver instanceof JSFieldReference) || (arrayReference.receiver instanceof JSCompletionOnMemberAccess))) {
             arrayReference.receiver = buildFieldReference(arrayReference.receiver); 
          }
       } 
       if (arrayReference.position != null) {
           if (arrayReference.position instanceof CompletionOnMemberAccess && !(arrayReference.position instanceof JSCompletionOnMemberAccess)) {
        	   arrayReference.position = buildCompletionOnMemberAccess(arrayReference.position);       
            } else {
               if (arrayReference.position instanceof FieldReference && !((arrayReference.position instanceof JSFieldReference) || (arrayReference.position instanceof JSCompletionOnMemberAccess))) {
            	   arrayReference.position = buildFieldReference(arrayReference.position); 
               }
            }
       }
    }
    return super.visit(iArrayReference);
 }
 
 
 //This method is relevant for Assignments which are part of a for-statement (see CRNT-13336)
 synchronized public boolean visit(Assignment assignment, BlockScope scope) {
	   if (assignment.expression instanceof CompletionOnMemberAccess && !(assignment.expression instanceof JSCompletionOnMemberAccess)) {
	      assignment.expression = buildCompletionOnMemberAccess(assignment.expression);       
	   } else {
	      if (assignment.expression instanceof FieldReference && !((assignment.expression instanceof JSFieldReference) || (assignment.expression instanceof JSCompletionOnMemberAccess))) {
	         assignment.expression = buildFieldReference(assignment.expression); 
	      }
	   }
	   //lhs   
	   if (assignment.lhs instanceof CompletionOnMemberAccess && !(assignment.lhs instanceof JSCompletionOnMemberAccess)) {
	      assignment.lhs = buildCompletionOnMemberAccess(assignment.lhs);       
	   } else {
	      if (assignment.lhs instanceof FieldReference && !((assignment.lhs instanceof JSFieldReference) || (assignment.lhs instanceof JSCompletionOnMemberAccess))) {
	         assignment.lhs = buildFieldReference(assignment.lhs); 
	      }
	   }	   
	   return true;
	}
 
 
 synchronized public boolean visit(IEqualExpression iEqualExpression) {	
	 EqualExpression equalExpression  = null;
	 if (iEqualExpression instanceof EqualExpression) {
		 equalExpression = (EqualExpression)iEqualExpression;
	 }
	 if (equalExpression != null) {
	     if (equalExpression.left instanceof CompletionOnMemberAccess && !(equalExpression.left instanceof JSCompletionOnMemberAccess)) {
	    	 equalExpression.left = buildCompletionOnMemberAccess( equalExpression.left);       
	     } else {
	         if (equalExpression.left instanceof FieldReference && !((equalExpression.left instanceof JSFieldReference) || (equalExpression.left instanceof JSCompletionOnMemberAccess))) {
	      	   equalExpression.left = buildFieldReference(equalExpression.left); 	     
	        }
	     }
	     if (equalExpression.right instanceof CompletionOnMemberAccess && !(equalExpression.right instanceof JSCompletionOnMemberAccess)) {
	    	 equalExpression.right = buildCompletionOnMemberAccess( equalExpression.right);       
	     } else {
	         if (equalExpression.right instanceof FieldReference && !((equalExpression.right instanceof JSFieldReference) || (equalExpression.right instanceof JSCompletionOnMemberAccess))) {
	      	   equalExpression.right = buildFieldReference(equalExpression.right); 	     
	        }
	     }
	 }
	 return super.visit(equalExpression);
 }
 
 	private List<XSDEnumerationFacet> checkEnumeration(ITypedElement data) {
	   TypeDeclarationType type = (TypeDeclarationType) AttributeUtil.getIdentifiable((IExtensibleElement) data, StructuredDataConstants.TYPE_DECLARATION_ATT);
	   if (type != null) {
	     	List schemaContent = type.getSchema().getContents();
			if (!schemaContent.isEmpty()) {
				if (schemaContent.get(0) instanceof XSDSimpleTypeDefinition) {
					XSDSimpleTypeDefinition st = (XSDSimpleTypeDefinition) schemaContent.get(0);
					if (!st.getEnumerationFacets().isEmpty()) {
						return st.getEnumerationFacets();						
					}
				}
			}
		}
		return null;
	}
 
}
