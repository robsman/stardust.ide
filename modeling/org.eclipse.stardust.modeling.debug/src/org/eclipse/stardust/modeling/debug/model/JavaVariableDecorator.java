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
package org.eclipse.stardust.modeling.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.jdt.debug.core.IJavaType;
import org.eclipse.jdt.debug.core.IJavaVariable;
import org.eclipse.stardust.modeling.debug.Constants;


public class JavaVariableDecorator implements IJavaVariable
{
   private IJavaVariable variable;
   private IJavaVariable hasChanged;
   
   private String name;
   private boolean supportsModification;
   
   public JavaVariableDecorator(IJavaVariable variable, IJavaVariable hasChanged, String name)
   {
      this.variable = variable;
      this.hasChanged = hasChanged;
      this.name = name;
      this.supportsModification = true;
   }
   
   public JavaVariableDecorator(IJavaVariable variable, IJavaVariable hasChanged)
   {
      this(variable, hasChanged, null);
   }
   
   public boolean isSupportsModification()
   {
      return supportsModification;
   }

   public void setSupportsModification(boolean supportsModification)
   {
      this.supportsModification = supportsModification;
   }
   
   public Object getAdapter(Class adapter)
   {
      return variable.getAdapter(adapter);
   }

   public IDebugTarget getDebugTarget()
   {
      return variable.getDebugTarget();
   }

   public String getGenericSignature() throws DebugException
   {
      return variable.getGenericSignature();
   }

   public IJavaType getJavaType() throws DebugException
   {
      return variable.getJavaType();
   }

   public ILaunch getLaunch()
   {
      return variable.getLaunch();
   }

   public String getModelIdentifier()
   {
      return Constants.ID_CWM_DEBUG_MODEL;
   }

   public String getName() throws DebugException
   {
      return null != name ? name : variable.getName();
   }

   public String getReferenceTypeName() throws DebugException
   {
      return variable.getReferenceTypeName();
   }

   public String getSignature() throws DebugException
   {
      return variable.getSignature();
   }

   public IValue getValue() throws DebugException
   {
      return variable.getValue();
   }

   public boolean hasValueChanged() throws DebugException
   {
      return variable.hasValueChanged();
   }

   public boolean isFinal() throws DebugException
   {
      return variable.isFinal();
   }

   public boolean isLocal() throws DebugException
   {
      return variable.isLocal();
   }

   public boolean isPackagePrivate() throws DebugException
   {
      return variable.isPackagePrivate();
   }

   public boolean isPrivate() throws DebugException
   {
      return variable.isPrivate();
   }

   public boolean isProtected() throws DebugException
   {
      return variable.isProtected();
   }

   public boolean isPublic() throws DebugException
   {
      return variable.isPublic();
   }

   public boolean isStatic() throws DebugException
   {
      return variable.isStatic();
   }

   public boolean isSynthetic() throws DebugException
   {
      return variable.isSynthetic();
   }

   public void setValue(IValue value) throws DebugException
   {
      variable.setValue(value);
      hasChanged.setValue(Boolean.TRUE.toString());
   }

   public void setValue(String expression) throws DebugException
   {
      variable.setValue(expression);
      hasChanged.setValue(Boolean.TRUE.toString());
   }

   public boolean supportsValueModification()
   {
      return supportsModification && variable.supportsValueModification();
   }

   public boolean verifyValue(IValue value) throws DebugException
   {
      return variable.verifyValue(value);
   }

   public boolean verifyValue(String expression) throws DebugException
   {
      return variable.verifyValue(expression);
   }
}
