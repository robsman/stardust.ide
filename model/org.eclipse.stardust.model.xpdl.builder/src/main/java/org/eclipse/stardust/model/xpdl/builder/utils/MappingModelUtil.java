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
package org.eclipse.stardust.model.xpdl.builder.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.stardust.engine.core.compatibility.ipp.PreStardustTypeNameResolver;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class MappingModelUtil
{
   public static String transformEcore2XML(ModelType model)
   {
      try
      {
         ByteArrayOutputStream stream = new ByteArrayOutputStream();
         WebModelerModelManager mgr = new WebModelerModelManager();
         mgr.setModel(model);
         mgr.save(URI.createFileURI("temp.xml"), stream);
         return stream.toString("UTF8");
      }
      catch (IOException e)
      {
         throw new RuntimeException("Could not complete transformation", e);
      }
   }

   public static EObject transformXML2Ecore(String xmlMessage)
   {
      PreStardustTypeNameResolver typeNameResolver = new PreStardustTypeNameResolver();
      String r1 = typeNameResolver.resolveTypeName("com.infinity.bpm.messaging.model");
      String r2 = typeNameResolver.resolveTypeName("com.infinity.bpm.messaging.model.mapping.MappingPackage");
      xmlMessage = xmlMessage.replaceAll("com.infinity.bpm.messaging.model.mapping.MappingPackage", r2);
      xmlMessage = xmlMessage.replaceAll("com.infinity.bpm.messaging.model", r1);
      ByteArrayInputStream stream = new ByteArrayInputStream(xmlMessage.getBytes());
      XMLResourceImpl resource = new XMLResourceImpl();
      resource.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, ExtendedMetaData.INSTANCE);
      resource.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, ExtendedMetaData.INSTANCE);
      resource.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
      resource.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
      resource.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
      resource.getDefaultLoadOptions().put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
      resource.getDefaultLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
      try
      {
         resource.load(stream, null);
         return (EObject) resource.getContents().get(0);
      }
      catch (Exception e)
      {
         throw new RuntimeException("Could not read message transformation configuration", e);
      }
   }
}
