/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Sungard         - improved to be able to perform code completion on array elements   
 *******************************************************************************/
package org.eclipse.stardust.modeling.javascript;

import java.util.Map;

import org.eclipse.wst.jsdt.internal.codeassist.complete.CompletionNodeFound;
import org.eclipse.wst.jsdt.internal.codeassist.complete.CompletionOnMemberAccess;
import org.eclipse.wst.jsdt.internal.compiler.ast.ArrayReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.Expression;
import org.eclipse.wst.jsdt.internal.compiler.ast.FieldReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.MessageSend;
import org.eclipse.wst.jsdt.internal.compiler.ast.ThisReference;
import org.eclipse.wst.jsdt.internal.compiler.lookup.BlockScope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.ClassScope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.CompilationUnitScope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.ProblemMethodBinding;
import org.eclipse.wst.jsdt.internal.compiler.lookup.ProblemReasons;
import org.eclipse.wst.jsdt.internal.compiler.lookup.Scope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.TypeBinding;


public class JSCompletionOnMemberAccess extends CompletionOnMemberAccess{
    private Expression arrayReceiverReceive;
    private boolean isArray;
    private Map arrayMap;

    public JSCompletionOnMemberAccess(char[] source, long pos,
            boolean isInsideAnnotation) {
        super(source, pos, isInsideAnnotation);
    }



    @Override
   public TypeBinding resolveType(BlockScope scope, boolean define, TypeBinding useType)
   {
      //return super.resolveType(scope, define, useType);
       return this.resolveType(scope);
   }



   public TypeBinding resolveType(BlockScope scope) {
         String key = this.receiver.toString();
         key = key.replaceAll("\\[[^\\]]*\\]|\\..*/", ""); //$NON-NLS-1$ //$NON-NLS-2$
         key = key.substring(key.indexOf('.') + 1, key.length());         
         key = key.replace(".", "/"); //$NON-NLS-1$ //$NON-NLS-2$
         key = key.replace("(", ""); //$NON-NLS-1$ //$NON-NLS-2$
         key = key.replace(")", ""); //$NON-NLS-1$ //$NON-NLS-2$
         Object value = arrayMap.get(key);
         if (this.receiver instanceof ArrayReference) {             
            ArrayReference arref = (ArrayReference)this.receiver;               
            this.receiver = arref.receiver;      
         } else {
            if (value != null && value.toString().equalsIgnoreCase("true")) { //$NON-NLS-1$
               CompilationUnitScope superScope = (CompilationUnitScope) this.getRootScope(scope);
               receiverType = superScope.getJavaLangArray();                           
            }             
         }


        if (receiverType == null) {
           if ((receiver instanceof FieldReference) && !(receiver instanceof JSFieldReference)) {
              receiver = this.buildFieldReference((Expression)receiver);
           }
            this.receiverType = receiver.resolveType(scope);           
        }            
        if (this.receiverType == null && receiver instanceof MessageSend) {
            MessageSend messageSend = (MessageSend) receiver;
            if(messageSend.receiver instanceof ThisReference) {
                Expression[] arguments = messageSend.arguments;
                int length = arguments == null ? 0 : arguments.length;
                TypeBinding[] argBindings = new TypeBinding[length];
                for (int i = 0; i < length; i++) {
                    argBindings[i] = arguments[i].resolvedType;
                    if(argBindings[i] == null || !argBindings[i].isValidBinding()) {
                        throw new CompletionNodeFound();
                    }
                }
                    
                ProblemMethodBinding problemMethodBinding = new ProblemMethodBinding(messageSend.selector, argBindings, ProblemReasons.NotFound);
                throw new CompletionNodeFound(this, problemMethodBinding, scope);
            }
        }
        
        if (this.receiverType == null || this.receiverType.isBaseType())
            throw new CompletionNodeFound();
           //throw new CompletionNodeFound(this, this.receiverType, scope);
        else
            throw new CompletionNodeFound(this, this.receiverType, scope);
        // array types are passed along to find the length field
    
    }



    @Override
    public void resolve(BlockScope scope) {

            super.resolve(scope);   
        
        
    }

    @Override
    public TypeBinding resolveType(ClassScope scope) {
        if (this.receiver instanceof ArrayReference) {
            return this.arrayReceiverReceive.resolveType(scope);
        } else 
        {
            return super.resolveType(scope);
        }
        
    }

    public void setArrayReceiverReceiver(Expression areceiver) {
        this.arrayReceiverReceive = areceiver;
        
    }

   public boolean isArray()
   {
      return isArray;
   }

   public void setArray(boolean isArray)
   {
      this.isArray = isArray;
   }

   public Map getArrayMap()
   {
      return arrayMap;
   }

   public void setArrayMap(Map arrayMap)
   {
      this.arrayMap = arrayMap;
   }
   
   private Scope getRootScope(Scope scope) {
      if (scope.parent != null) {
         return getRootScope(scope.parent);
      }
      return scope;
   }
   
   private Expression buildFieldReference(Expression expression) {
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
   

   
}
