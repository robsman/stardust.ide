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
package org.eclipse.stardust.modeling.integration.webservices;

import javax.wsdl.BindingOperation;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.xml.namespace.QName;

import org.eclipse.jface.viewers.LabelProvider;

public class QNameLabelProvider extends LabelProvider
{
   public String getText(Object element)
   {
      if (element instanceof QName)
      {
         QName name = (QName) element;
         if (name != null)
         {
            if (name.getNamespaceURI() == null)
            {
               return name.getLocalPart();
            }
            else
            {
               return name.getLocalPart() + " {" + name.getNamespaceURI() + "}"; //$NON-NLS-1$ //$NON-NLS-2$
            }
         }
      }
      if (element instanceof Service)
      {
         Service service = (Service) element;
         QName name = service.getQName();
         if (name != null)
         {
            return name.getLocalPart();
         }
      }
      if (element instanceof Port)
      {
         Port port = (Port) element;
         String name = port.getName();
         if (name != null)
         {
            return name;
         }
      }
      if (element instanceof BindingOperation)
      {
         BindingOperation operation = (BindingOperation) element;
         String name = operation.getName();
         if (name != null)
         {
            String inputName = operation.getBindingInput() == null
               ? null : operation.getBindingInput().getName();
            String outputName = operation.getBindingOutput() == null
               ? null : operation.getBindingOutput().getName();
            if (inputName == null)
            {
               if (outputName == null)
               {
                  return name;
               }
               else
               {
                  return name + "(:none," + outputName + ")"; //$NON-NLS-1$ //$NON-NLS-2$
               }
            }
            else
            {
               if (outputName == null)
               {
                  return name + "(" + inputName + ",:none)"; //$NON-NLS-1$ //$NON-NLS-2$
               }
               else
               {
                  return name + "(" + inputName + "," + outputName + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
               }
            }
         }
      }
      return super.getText(element);
   }
}
