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

import org.eclipse.wst.jsdt.internal.compiler.ast.Assignment;
import org.eclipse.wst.jsdt.internal.compiler.ast.Expression;
import org.eclipse.wst.jsdt.internal.compiler.ast.FieldReference;
import org.eclipse.wst.jsdt.internal.compiler.ast.Reference;
import org.eclipse.wst.jsdt.internal.compiler.classfmt.ClassFileConstants;
import org.eclipse.wst.jsdt.internal.compiler.impl.Constant;
import org.eclipse.wst.jsdt.internal.compiler.lookup.BaseTypeBinding;
import org.eclipse.wst.jsdt.internal.compiler.lookup.Binding;
import org.eclipse.wst.jsdt.internal.compiler.lookup.BlockScope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.ClassScope;
import org.eclipse.wst.jsdt.internal.compiler.lookup.TypeBinding;

public class JSAssignment extends Assignment {

	public JSAssignment(Expression lhs, Expression expression, int sourceEnd) {
		super(lhs, expression, sourceEnd);
	}


	public TypeBinding resolveType(BlockScope scope) {
		// due to syntax lhs may be only a NameReference, a FieldReference or an ArrayReference
		this.constant = Constant.NotAConstant;
		if (!(this.lhs instanceof Reference) || this.lhs.isThis()) {
			scope.problemReporter().expressionShouldBeAVariable(this.lhs);
			return null;
		}
		TypeBinding rhsType = this.expression.resolveType(scope);
		TypeBinding lhsType = lhs.resolveType(scope,true,rhsType);
//		this.expression.setExpectedType(lhsType); // needed in case of generic method invocation
		if (lhsType != null)
			this.resolvedType = lhsType;
		if (lhsType == null || rhsType == null) {
			return null;
		}

		//check if the lhs is prototype, in which case we are done
		if( lhs instanceof FieldReference && ((FieldReference)lhs).isPrototype() )
			return this.resolvedType;

		// check for assignment with no effect
		Binding left = getDirectBinding(this.lhs);
		if (left != null && left == getDirectBinding(this.expression)) {
			scope.problemReporter().assignmentHasNoEffect(this, left.shortReadableName());
		}

		// Compile-time conversion of base-types : implicit narrowing integer into byte/short/character
		// may require to widen the rhs expression at runtime
//		if (lhsType != rhsType) // must call before computeConversion() and typeMismatchError()
//			scope.compilationUnitScope().recordTypeConversion(lhsType, rhsType);


		if ((this.expression.isConstantValueOfTypeAssignableToType(rhsType, lhsType)
				|| (lhsType.isBaseType() && BaseTypeBinding.isWidening(lhsType.id, rhsType.id)))
				|| rhsType.isCompatibleWith(lhsType)) {
			return this.resolvedType;
		} else if (scope.isBoxingCompatibleWith(rhsType, lhsType)
							|| (rhsType.isBaseType()  // narrowing then boxing ?
									&& scope.compilerOptions().sourceLevel >= ClassFileConstants.JDK1_5 // autoboxing
									&& !lhsType.isBaseType()
									&& this.expression.isConstantValueOfTypeAssignableToType(rhsType, scope.environment().computeBoxingType(lhsType)))) {
			return this.resolvedType;
		}
		if (rhsType.isFunctionType() && this.lhs.isTypeReference())
			return lhsType;
		scope.problemReporter().typeMismatchError(rhsType, lhsType, this.expression);
		return lhsType;
	}

	@Override
	public void resolve(BlockScope scope) {
		super.resolve(scope);
	}

	@Override
	public TypeBinding resolveType(ClassScope scope) {
		// TODO Auto-generated method stub
		return super.resolveType(scope);
	}

}
