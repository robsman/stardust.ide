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
package org.eclipse.stardust.modeling.data.structured.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class StructPropertyPage extends AbstractModelElementPropertyPage
{
   private AbstractModelElementPropertyPage delegate = null;
   
   public void performDefaults()
   {
      delegate = getCreateDelegate();
      if(delegate instanceof ComplexTypePropertyPage)
      {
         ((ComplexTypePropertyPage) delegate).performDefaults();         
      }
      else if(delegate instanceof SimpleTypePropertyPage)
      {
         ((SimpleTypePropertyPage) delegate).performDefaults();         
      }      
   }
      
   protected void performApply() {
	  delegate = getCreateDelegate();
	  if(delegate instanceof ComplexTypePropertyPage)
	  {
	    ((ComplexTypePropertyPage) delegate).performApply();         
	  }
	  super.performApply();
   }

   public boolean performCancel() {
	  delegate = getCreateDelegate();
	  if(delegate instanceof ComplexTypePropertyPage)
	  {
	    ((ComplexTypePropertyPage) delegate).performCancel();         
	  }
	  return super.performCancel();
   }
   
   public Control createBody(Composite parent)
   {
      delegate = getCreateDelegate();
      return delegate.createBody(parent);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // never used
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // never used
   }

   private AbstractModelElementPropertyPage getCreateDelegate()
   {
      if(delegate == null)
      {
         TypeDeclarationType declaration = (TypeDeclarationType) getElement().getAdapter(EObject.class);
         int type = TypeDeclarationUtils.COMPLEX_TYPE;
         try
         {
            type = TypeDeclarationUtils.getType(declaration);            
         }
         catch(IllegalArgumentException e)
         {
         }
         if(type == TypeDeclarationUtils.SIMPLE_TYPE)
         {
            delegate = new SimpleTypePropertyPage()
            {
               public void setErrorMessage(String newMessage)
               {
                  StructPropertyPage.this.setErrorMessage(newMessage);
               }

               public void setMessage(String newMessage, int newType)
               {
                  StructPropertyPage.this.setMessage(newMessage, newType);
               }

               public void setValid(boolean b)
               {
                  StructPropertyPage.this.setValid(b);
               }   
               
               public boolean isValid()
               {
                  return StructPropertyPage.this.isValid();
               }                  
            };
         }
         else
         {
            delegate = new ComplexTypePropertyPage()
            {
               public void setErrorMessage(String newMessage)
               {
                  StructPropertyPage.this.setErrorMessage(newMessage);
               }

               public void setMessage(String newMessage, int newType)
               {
                  StructPropertyPage.this.setMessage(newMessage, newType);
               }

               public void setValid(boolean b)
               {
                  StructPropertyPage.this.setValid(b);
               }
               
               public boolean isValid()
               {
                  return StructPropertyPage.this.isValid();
               }                  
            };
         }
         delegate.setContainer(getContainer());
         delegate.setElement(getElement());         
      }
      return delegate;
   }
   
   public void apply()
   {
      delegate = getCreateDelegate();
      delegate.apply();
   }

   public void elementChanged()
   {
      delegate = getCreateDelegate();
      delegate.elementChanged();
   }

   public void contributeVerticalButtons(Composite parent)
   {
      delegate = getCreateDelegate();
      delegate.contributeVerticalButtons(parent);
   }

   @SuppressWarnings("unchecked")
   public Object getAdapter(Class adapter)
   {
      AbstractModelElementPropertyPage delegate = getCreateDelegate();
      return delegate.getAdapter(adapter);
   }
}