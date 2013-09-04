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

import java.util.Map;

import org.eclipse.wst.jsdt.core.JavaScriptCore;
import org.eclipse.wst.jsdt.core.infer.InferredAttribute;
import org.eclipse.wst.jsdt.internal.compiler.ast.ArrayReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.Expression;
import org.eclipse.wst.jsdt.internal.compiler.ast.FieldReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.NameReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.SingleNameReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.ThisReference;
import org.eclipse.wst.jsdt.internal.compiler.impl.Constant;
import org.eclipse.wst.jsdt.internal.compiler.lookup.Binding;
import org.eclipse.wst.jsdt.internal.compiler.lookup.BlockScope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.ClassScope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.CompilationUnitScope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.FieldBinding;
import org.eclipse.wst.jsdt.internal.compiler.lookup.MethodBinding;
import org.eclipse.wst.jsdt.internal.compiler.lookup.ProblemFieldBinding;
import org.eclipse.wst.jsdt.internal.compiler.lookup.ProblemReasons;
import org.eclipse.wst.jsdt.internal.compiler.lookup.ReferenceBinding;
import org.eclipse.wst.jsdt.internal.compiler.lookup.Scope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.TypeBinding;
import org.eclipse.wst.jsdt.internal.compiler.lookup.TypeIds;
import org.eclipse.wst.jsdt.internal.compiler.util.HashtableOfObject;
import org.eclipse.wst.jsdt.internal.compiler.util.Util;

public class JSFieldReference extends FieldReference {
    private Expression arrayReceiverReceive;
    private InferredAttribute attribute;
    protected Map arrayMap;

    public JSFieldReference(char[] source, long pos) {
        super(source, pos);
    }

    public TypeBinding resolveType(BlockScope scope, boolean define, TypeBinding useType) {
       if( this.isPrototype() ){
           
           //construc the name of the type based on the receiver
           char [] possibleTypeName = Util.getTypeName( receiver );
           TypeBinding typeBinding = scope.getJavaLangObject();
           if( possibleTypeName != null ){
               Binding possibleTypeBinding = scope.getBinding( possibleTypeName, Binding.TYPE  & RestrictiveFlagMASK, this, true /*resolve*/);
               
               if( possibleTypeBinding.isValidBinding() ){
                   typeBinding = (TypeBinding)possibleTypeBinding;
               }
               char[] fieldname=new char[]{'p','r','o','t','o','t','y','p','e'};
               this.binding=scope.getJavaLangObject().getField(fieldname, true);
           }
           
           constant = Constant.NotAConstant;
           return this.resolvedType = typeBinding;
       }   
       
       
       if (this.receiverType == null) {
          if ((receiver instanceof FieldReference) && !(receiver instanceof JSFieldReference)) {
             receiver = this.buildFieldReference((Expression)receiver);
          }
           if (scope instanceof CompilationUnitScope) {
               ((CompilationUnitScope)scope).typeOrPackageCache = new HashtableOfObject();
           }
           this.receiverType = receiver.resolveType(scope);           
       }
          
       if (this.receiverType == null) {
           this.binding=new ProblemFieldBinding(null,this.token,ProblemReasons.NotFound);
           constant = Constant.NotAConstant;
           this.resolvedType=TypeBinding.ANY;
           return null;
       }

       Binding memberBinding = scope.getFieldOrMethod(this.receiverType, token, this);
       
       constant = Constant.NotAConstant;
       if( memberBinding instanceof FieldBinding ){
           FieldBinding fieldBinding =/* this.codegenBinding =*/ this.binding = (FieldBinding)memberBinding;
           if (!fieldBinding.isValidBinding()) {
               this.binding=fieldBinding;
               this.resolvedType=TypeBinding.ANY;
               if (!define)
               {
                   constant = Constant.NotAConstant;
                   scope.problemReporter().options.enableSemanticValidation = true;
                   scope.problemReporter().invalidField(this, this.receiverType);
                   return null;
               }
               else    // should add binding here
               {
                   
               }

           }

           //this.receiver.ccomputeConversion(scope, this.receiverType, this.receiverType);
           if (isFieldUseDeprecated(fieldBinding, scope, (this.bits & IsStrictlyAssigned) !=0)) {
               scope.problemReporter().deprecatedField(fieldBinding, this);
           }
           boolean isImplicitThisRcv = receiver.isImplicitThis();
           //constant = isImplicitThisRcv ? fieldBinding.constant() : Constant.NotAConstant;
           if (fieldBinding.isStatic()) {
               // static field accessed through receiver? legal but unoptimal (optional warning)
               if (!(isImplicitThisRcv
                       || (receiver instanceof NameReference 
                           && (((NameReference) receiver).bits & Binding.TYPE) != 0))) {
                   scope.problemReporter().nonStaticAccessToStaticField(this, fieldBinding);
               }
               if (!isImplicitThisRcv
                       && fieldBinding.declaringClass != receiverType
                       && fieldBinding.declaringClass.canBeSeenBy(scope)) {
                   scope.problemReporter().indirectAccessToStaticField(this, fieldBinding);
               }
           }
           // perform capture conversion if read access
           return this.resolvedType = 
               (((this.bits & IsStrictlyAssigned) == 0) 
                   ? fieldBinding.type
                   : fieldBinding.type);
       }
       else if( memberBinding instanceof MethodBinding ){
           this.resolvedType= scope.getJavaLangFunction();
           this.binding=new ProblemFieldBinding(null,this.token,ProblemReasons.NotFound);
           if( memberBinding.isValidBinding() )
               return this.resolvedType;
           return null;
       }
       
       return null;
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
          return super.resolveType(scope);
       } else {
          if (value != null && value.toString().equalsIgnoreCase("true")) { //$NON-NLS-1$
             CompilationUnitScope superScope = (CompilationUnitScope) this.getRootScope(scope);
             receiverType = superScope.getJavaLangArray();
             
          }           
       }
       return super.resolveType(scope);
        
    }

    @Override
    public TypeBinding resolveType(ClassScope scope) {
        if (this.receiver instanceof ArrayReference) {
            return this.arrayReceiverReceive.resolveType(scope);
        } else {
            return super.resolveType(scope);
        }

    }

    public void setArrayReceiverReceiver(Expression areceiver) {
        this.arrayReceiverReceive = areceiver;

    }

    @Override
    public void resolve(BlockScope scope) {
        super.resolve(scope);
    }

    public void setAttribute(InferredAttribute att) {
        this.attribute = att;
    }

    public Map getArrayMap() {
        return arrayMap;
    }

    public void setArrayMap(Map arrayMap) {
        this.arrayMap = arrayMap;
    }

    protected Scope getRootScope(Scope scope) {
        if (scope.parent != null) {
            return getRootScope(scope.parent);
        }
        return scope;
    }

    protected Expression buildFieldReference(Expression expression) {
        if (expression instanceof FieldReference) {
            FieldReference fieldReference = (FieldReference) expression;
            FieldReference fr = new JSFieldReference(fieldReference.token,
                    fieldReference.nameSourcePosition);
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
            ((JSFieldReference) fr).setArrayMap(this.arrayMap);
            return fr;
        } else {
            return null;
        }
    }

}
