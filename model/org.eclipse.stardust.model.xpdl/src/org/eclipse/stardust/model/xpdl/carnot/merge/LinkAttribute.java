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
package org.eclipse.stardust.model.xpdl.carnot.merge;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.Extensible;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;


public class LinkAttribute
{
   private URI rootURI; 
   private URI uri;
   private boolean asLink; 
   private boolean qualifyURI; 
   private String attrName;
   
   public LinkAttribute(URI rootURI, boolean asLink, boolean qualifyURI, String attrName)
   {
      this.rootURI = rootURI;
      this.asLink = asLink;
      this.qualifyURI = qualifyURI;
      this.attrName = attrName;
   }

   // we have an extra icon when this attribute is set 
   public void setLinkInfo(EObject element, boolean qualify)
   {
      setLinkInfo(element, element, qualify);
   }
   
   // we have an extra icon when this attribute is set 
   public void setLinkInfo(EObject element, EObject source, boolean qualify)
   {
      uri = MergeUtils.createQualifiedUri(rootURI, source, qualifyURI || qualify);      
      setLinkInfoAttr(element, uri, asLink, attrName);
   }
   
   public static void setLinkInfoAttr(EObject element, URI uri, boolean asLink, String attrName)
   {
      if (element instanceof Extensible)
      {
         ExtendedAttributeUtil.setAttribute((Extensible) element, attrName, uri.toString());
      }
      else
      {
         if (asLink)
         {
            ((InternalEObject) element).eSetProxyURI(uri);
         }
         else
         {
            if (element instanceof IExtensibleElement)
            {
               AttributeUtil.setAttribute((IExtensibleElement) element, attrName, uri.toString());
            }
         }
      }
   }   
}