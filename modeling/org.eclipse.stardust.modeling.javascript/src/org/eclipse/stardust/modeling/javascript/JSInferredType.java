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
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.wst.jsdt.core.infer.InferredAttribute;
import org.eclipse.wst.jsdt.core.infer.InferredType;
import org.eclipse.wst.jsdt.internal.compiler.ast.ASTNode;
import org.eclipse.wst.jsdt.internal.compiler.util.HashtableOfObject;





public class JSInferredType extends InferredType
{

   public JSInferredType(char[] className)
   {
      super(className);
      // TODO Auto-generated constructor stub
   }

   public InferredAttribute addAttribute(char [] name, ASTNode definer)
   {
       InferredAttribute attribute = findAttribute(name);
       if (attribute==null) 
       {
           attribute=new JSInferredAttribute(name, this ,definer.sourceStart,definer.sourceEnd);

           if (this.numberAttributes == this.attributes.length)
               
               System.arraycopy(
                       this.attributes,
                       0,
                       this.attributes = new JSInferredAttribute[this.numberAttributes  * 2],
                       0,
                       this.numberAttributes );
                       this.attributes [this.numberAttributes  ++] = attribute;
   
           HashtableOfObject htoo = (HashtableOfObject) Reflect.getFieldValue(this,"attributesHash"); //$NON-NLS-1$
           htoo.put(name, attribute);           
           if( !isAnonymous )
               this.updatePositions(definer.sourceStart, definer.sourceEnd);
       }
       return attribute;
   }

}
