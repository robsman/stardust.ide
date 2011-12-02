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

import org.eclipse.wst.jsdt.internal.compiler.ast.ArrayReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.Expression;
import org.eclipse.wst.jsdt.internal.compiler.impl.Constant;
import org.eclipse.wst.jsdt.internal.compiler.lookup.ArrayBinding;
import org.eclipse.wst.jsdt.internal.compiler.lookup.BlockScope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.TypeBinding;

public class JSArrayReference extends ArrayReference 
{
   private Map arrayMap;

   public JSArrayReference(Expression rec, Expression pos)
   {
      super(rec, pos);
   }
   
   
	public TypeBinding resolveType(BlockScope scope) {

		constant = Constant.NotAConstant;
		TypeBinding arrayType = receiver.resolveType(scope);
		if (arrayType != null) {
			if (arrayType.isArrayType()) {
				TypeBinding elementType = ((ArrayBinding) arrayType).elementsType();
				this.resolvedType = elementType;
	          } else {
	              if (arrayType != null) {
	                  this.resolvedType = arrayType;                 
	              } else {
	                 this.resolvedType=TypeBinding.UNKNOWN;
	              }
	           }
		}
		else 
			this.resolvedType=TypeBinding.UNKNOWN;
		  position.resolveTypeExpecting(scope, new TypeBinding[] {scope.getJavaLangNumber(),scope.getJavaLangString(),TypeBinding.ANY});
		return this.resolvedType;
	}

   public Map getArrayMap()
   {
      return arrayMap;
   }

   public void setArrayMap(Map arrayMap)
   {
      this.arrayMap = arrayMap;
   }

}
