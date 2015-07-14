/*******************************************************************************
* Copyright (c) 2015 SunGard CSA LLC and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Barry.Grotjahn (SunGard CSA LLC) - initial API and implementation and/or initial documentation
*******************************************************************************/

package org.eclipse.stardust.model.xpdl.carnot.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.connectionhandler.EObjectProxyHandler;
import org.eclipse.stardust.model.xpdl.carnot.util.connectionhandler.IdRefHandler;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;

public class WorkspaceManager
{
   private static final Logger trace = LogManager.getLogger(WorkspaceManager.class);

   private static WorkspaceManager INSTANCE;
   private static Map<String, ModelType> models;

   private WorkspaceManager()
   {
      // TODO Auto-generated constructor stub
   }


   public static WorkspaceManager getInstance()
   {
      if(INSTANCE == null)
      {
         models = new HashMap<String, ModelType>();
         INSTANCE = new WorkspaceManager();
      }

      return INSTANCE;
   }

   public ModelType getModel(URI uri)
   {
      uri = createPlatformURI(uri);
      ModelType modelType = models.get(uri.toString());
      trace.info("get " + uri.toString() + " / " + modelType);

      return modelType;
   }

   public EObject findElement(EObject element)
   {
      ModelType model = ModelUtils.findContainingModel(element);
      if (model != null && model.getExternalPackages() != null && element.eIsProxy())
      {
         URI eProxyURI = ((InternalEObject) element).eProxyURI();
         if (eProxyURI != null)
         {
            String id = eProxyURI.toString();
            for (ExternalPackage pkg : model.getExternalPackages().getExternalPackage())
            {
               String pkgConnectionUri = ExtendedAttributeUtil.getAttributeValue(pkg, IConnectionManager.URI_ATTRIBUTE_NAME);
               if (id.startsWith(pkgConnectionUri))
               {
                  ModelType externalModel = ModelUtils.getExternalModel(pkg);
                  if(externalModel != null)
                  {
                     String path = id.substring(pkgConnectionUri.length());
                     int ix = path.indexOf('/');
                     if (ix > 0)
                     {
                        String type = path.substring(0, ix);
                        String elementId = path.substring(ix + 1);
                        String elementUuuid = null;
                        if(elementId.contains("?uuid="))
                        {
                           int idx = elementId.indexOf("?uuid=");
                           elementUuuid = elementId.substring(idx + 6);
                           elementId = elementId.substring(0, idx);
                        }

                        if(type.equals("data"))
                        {
                           for(DataType data : externalModel.getData())
                           {
                              String uuid = ModelUtils.getUUID(data);
                              if(uuid != null && elementUuuid != null)
                              {
                                 if(uuid.equals(elementUuuid))
                                 {
                                    return data;
                                 }
                              }
                              else
                              {
                                 if(elementId.equals(data.getId()))
                                 {
                                    return data;
                                 }
                              }
                           }
                        }
                        else if(type.equals("role")
                              || type.equals("organization")
                              || type.equals("conditionalPerformer"))
                        {
                           List<IModelParticipant> participants = CollectionUtils.newArrayList();

                           if(type.equals("role"))
                           {
                              List<RoleType> roles = externalModel.getRole();
                              participants.addAll(roles);
                           }
                           else if(type.equals("organization"))
                           {
                              List<OrganizationType> organizations = externalModel.getOrganization();
                              participants.addAll(organizations);
                           }
                           else if(type.equals("conditionalPerformer"))
                           {
                              List<ConditionalPerformerType> conditionalPerformers = externalModel.getConditionalPerformer();
                              participants.addAll(conditionalPerformers);
                           }

                           for(IModelParticipant participant : participants)
                           {
                              String uuid = ModelUtils.getUUID(participant);
                              if(uuid != null && elementUuuid != null)
                              {
                                 if(uuid.equals(elementUuuid))
                                 {
                                    return participant;
                                 }
                              }
                              else
                              {
                                 if(elementId.equals(participant.getId()))
                                 {
                                    return participant;
                                 }
                              }
                           }
                        }
                        else
                        {
                           System.err.println("type " + type);
                        }
                     }
                  }
               }
            }
         }
      }

      return null;
   }


   public void resolve(ModelType model)
   {
      for (Iterator<EObject> i = model.eAllContents(); i.hasNext();)
      {
         EObject object = i.next();
         if (object.eIsProxy())
         {
            if (object instanceof EObjectImpl)
            {

               /*
               URI uri = ((EObjectImpl) object).eProxyURI();
               // decode uri from the format produced by xpdl transformation
               if (uri.opaquePart() != null)
               {
                  try
                  {
                     QName qname = QName.valueOf(uri.opaquePart());
                     if (model.getExternalPackages() != null)
                     {
                        ExternalPackage pkg = model.getExternalPackages().getExternalPackage(qname.getNamespaceURI());
                        if (pkg != null)
                        {
                           String pkgConnectionUri = ExtendedAttributeUtil.getAttributeValue(pkg, IConnectionManager.URI_ATTRIBUTE_NAME);
                           uri = URI.createURI(pkgConnectionUri + uri.scheme() + '/' + qname.getLocalPart());
                           ((InternalEObject) object).eSetProxyURI(uri);
                        }
                     }
                  }
                  catch (Exception ex)
                  {
                     // not a special reference
                  }
               }
               */

               try
               {
                  resolve(object);
               }
               catch (Throwable t)
               {
               }

            }
         }
         else
         {
            if (object instanceof IdRefOwner || object instanceof DataType)
            {
               IdRefHandler.adapt((IIdentifiableModelElement) object);
            }
         }
      }
   }

   private void resolve(EObject object)
   {
      EObject target = findElement(object);
      if(target != null)
      {
         EObjectProxyHandler.createProxy(object, target);
      }
   }


   public void addModel(URI uri, ModelType model)
   {
      uri = createPlatformURI(uri);
      models.put(uri.toString(), model);

      trace.info("add " + uri.toString() + " / " + model);
   }

   private URI createPlatformURI(URI uri)
   {
      /*
      if(!uri.isPlatformResource())
      {
         String location = uri.toString();
         java.net.URI netModelUri = new File(uri.toFileString()).toURI();

         IContainer[] containers = ResourcesPlugin.getWorkspace().getRoot().findContainersForLocationURI(netModelUri);
         if (containers != null && containers.length > 0)
         {
            IContainer container = containers[0];
            String projectString = container.getProject().toString();

            int pos = location.indexOf(projectString.substring(1));

            String projectLocation = location.substring(pos);
            uri = URI.createPlatformResourceURI(projectLocation, false);
         }
      }
      */

      return uri;
   }

   public boolean usesModel(ModelType toCheck)
   {
      for(ModelType model : models.values())
      {
         if(!model.equals(toCheck))
         {
            if (model != null && model.getExternalPackages() != null)
            {
               for (ExternalPackage pkg : model.getExternalPackages().getExternalPackage())
               {
                  ModelType externalModel = ModelUtils.getExternalModel(pkg);
                  if(externalModel.equals(toCheck))
                  {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public void cleanCache(ModelType workflowModel)
   {
      if(!usesModel(workflowModel))
      {
         // check how many editors are open ...
      }
   }
}