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
package org.eclipse.stardust.modeling.repository.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeAction;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.IconFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.modeling.common.ui.jface.IImageManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.CategoryDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.EObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.ModelElementDescriptor;
import org.eclipse.stardust.modeling.repository.common.ui.ImageUtil;
import org.eclipse.stardust.modeling.repository.common.ui.dialogs.UsageDisplayDialog;
import org.eclipse.swt.graphics.Image;

import ag.carnot.workflow.model.PredefinedConstants;

public final class ImportUtils
{
   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   private ImportUtils() {}

   public static Map<EObject, MergeAction> reuseReplaceMap(Map<EObject, EObject> map, IconFactory iconFactory)
   {
      Map<EObject, MergeAction> reuseReplace = new HashMap<EObject, MergeAction>();
      for (Map.Entry<EObject, EObject> entry : map.entrySet())
      {
         EObject element = entry.getKey();
         EObject original = entry.getValue();
         if (original != null)
         {
            MergeAction action = UsageDisplayDialog.acceptClosure(null, iconFactory, element, original);
            if (action == null)
            {
               UsageDisplayDialog.setUsage(null);
               return null;
            }
            reuseReplace.put(original, action);
         }
      }      
      UsageDisplayDialog.setUsage(null);
      return reuseReplace;
   }   
      
   // image for tree edit part
   public static Image getImage(IconFactory iconFactory, EObject eObject)
   {
      String icon = iconFactory.getIconFor(eObject);
      String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
      if (icon != null && icon.length() > 0 && icon.charAt(0) == '{')
      {
         int ix = icon.indexOf('}', 1);
         bundleId = icon.substring(1, ix);
         icon = icon.substring(ix + 1);
      }
      IImageManager manager = ImageUtil.getImageManager(bundleId);
      return manager.getPlainIcon(icon);
   }

   public static String getLabel(EObject eObject)
   {
      String name = MergeUtils.getName(eObject);
      return name == null ? MergeUtils.getId(eObject) : name;
   }
      
   public static List<IObjectDescriptor> createObjectDescriptors(IconFactory iconFactory, ModelType model, URI uri)
   {
      List<IObjectDescriptor> types = new ArrayList<IObjectDescriptor>();
      TypeDeclarationsType typeDeclarations = model.getTypeDeclarations();
      List<TypeDeclarationType> elements = Collections.emptyList();
      if (typeDeclarations != null)
      {
         elements = typeDeclarations.getTypeDeclaration();
      }
      addElements(iconFactory, types, elements, uri);

      List<IObjectDescriptor> applications = new ArrayList<IObjectDescriptor>();
      addElements(iconFactory, applications, model.getApplication(), uri);
      
      List<IObjectDescriptor> data = new ArrayList<IObjectDescriptor>();
      addElements(iconFactory, data, model.getData(), uri);
      
      List<IObjectDescriptor> participants = new ArrayList<IObjectDescriptor>();
      addElements(iconFactory, participants, model.getRole(), uri);
      addElements(iconFactory, participants, model.getOrganization(), uri);
      addElements(iconFactory, participants, model.getConditionalPerformer(), uri);
      
      List<IObjectDescriptor> result = new ArrayList<IObjectDescriptor>();
      String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
      if (!types.isEmpty())
      {
         result.add(new CategoryDescriptor(
            uri.appendSegment("typeDeclaration"), "typeDeclarations", "Structured Types", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            ((IObjectDescriptor[]) types.toArray(new IObjectDescriptor[types.size()])),
            bundleId, iconFactory.getIconFor(typeDeclarations)));
      }
      if (!applications.isEmpty())
      {
         result.add(new CategoryDescriptor(
            uri.appendSegment("applications"), "applications", "Applications", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            ((IObjectDescriptor[]) applications.toArray(new IObjectDescriptor[applications.size()])),
            bundleId, iconFactory.getIconFor(PKG_CWM.getApplicationType())));
      }
      if (!data.isEmpty())
      {
         result.add(new CategoryDescriptor(
            uri.appendSegment("data"), "data", "Data", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            ((IObjectDescriptor[]) data.toArray(new IObjectDescriptor[data.size()])),
            bundleId, iconFactory.getIconFor(PKG_CWM.getDataType())));
      }
      if (!participants.isEmpty())
      {
         result.add(new CategoryDescriptor(
            uri.appendSegment("participants"), "participants", "Participants", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            ((IObjectDescriptor[]) participants.toArray(new IObjectDescriptor[participants.size()])),
            bundleId, iconFactory.getIconFor(PKG_CWM.getIModelParticipant())));
      }
      addElements(iconFactory, result, model.getProcessDefinition(), uri);
      return result;
   }
   
   private static void addElements(IconFactory iconFactory, List<IObjectDescriptor> descriptors,
         List<? extends EObject> elements, URI uri)
   {
      for (EObject eObject : elements)
      {
         String icon = iconFactory.getIconFor(eObject);
         String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;
         if (icon != null && icon.length() > 0 && icon.charAt(0) == '{')
         {
            int ix = icon.indexOf('}', 1);
            bundleId = icon.substring(1, ix);
            icon = icon.substring(ix + 1);
         }
         EObjectDescriptor descriptor = null;
         if (eObject instanceof IIdentifiableModelElement)
         {
            IIdentifiableModelElement identifiable = (IIdentifiableModelElement) eObject;
            
            // predefined elements are excluded
            if (identifiable instanceof DataType && ((DataType) identifiable).isPredefined()
                  || identifiable instanceof RoleType && PredefinedConstants.ADMINISTRATOR_ROLE.equals(((RoleType) identifiable).getId()))
            {
               continue;
            }
            
            descriptor = new ModelElementDescriptor(uri, 
                  identifiable, bundleId, icon, true);
         }
         else if (eObject instanceof TypeDeclarationType) 
         {
            TypeDeclarationType decl = (TypeDeclarationType) eObject;
            descriptor = new EObjectDescriptor(MergeUtils.createQualifiedUri(uri, decl, true), decl, decl.getId(), decl.getName(),
                  decl.getDescription(), bundleId, icon);
         }
         if (descriptor != null)
         {
            descriptors.add(descriptor);
         }
      }
   }   
}