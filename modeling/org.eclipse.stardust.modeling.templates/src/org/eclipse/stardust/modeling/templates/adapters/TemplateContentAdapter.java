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
package org.eclipse.stardust.modeling.templates.adapters;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.dialogs.Dialog;

import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.cap.NameIdDialog;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CommandUtils;
import org.eclipse.stardust.modeling.repository.common.ImportCancelledException;
import org.eclipse.stardust.modeling.templates.Templates_Messages;
import org.eclipse.stardust.modeling.templates.spi.ITemplate;



public class TemplateContentAdapter extends EContentAdapter
{
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;

   private ModelType targetModel;
   private Map nameIdCache = new HashMap();
   private Map changedCache = new HashMap();
   private Map dataRefCache = new HashMap();
   private String templateID;
   private List symbols = new ArrayList();

   public TemplateContentAdapter(ModelType targetModel, ITemplate template)
   {
      super();

      this.targetModel = targetModel;
      templateID = template.getId() + "-" + ModelUtils.getMaxUsedOid(targetModel);       //$NON-NLS-1$
      collectTargetData(this.targetModel);
   }

   public void notifyChanged(Notification notification)
   {
      super.notifyChanged(notification);
      EObject container = (EObject)notification.getNotifier();
      if (notification.getEventType() == 5)
      {
         List list = (List) notification.getNewValue();
         for (Iterator i = list.iterator(); i.hasNext();) {
            performConsistencyCheck((EObject)i.next(), container);
         }
      }
      if (notification.getEventType() == 3)
      {
         if (notification.getNewValue() instanceof EObject) {
            EObject element = (EObject)notification.getNewValue();
            performConsistencyCheck(element, container);
         }
      }
   }

   private void performConsistencyCheck(EObject element, EObject container)
   {
      if (element instanceof INodeSymbol && !(container instanceof RoleType)) {
         if (element.eContainer() != null) {
            symbols.add(element);
         }
      }
      if (element instanceof ActivityType)
      {
         ActivityType activityType = (ActivityType)element;
         IModelParticipant performer = activityType.getPerformer();
         if (performer != null)
         {
            RoleType roleType = (RoleType) ModelUtils.findElementById(targetModel.getRole(), performer.getId());
            if (roleType != null)
            {
               activityType.setPerformer(roleType);
            }
         }
      }
      if (checkElementInModel(container, element))
      {
         if (element instanceof TransitionType)
         {
            EClass eClass = PKG.getTransitionType();
            IdFactory idFactory = new IdFactory(Diagram_Messages.ID_TransitionConnection, Diagram_Messages.BASENAME_Transition);
            List list = (List) container.eGet(getContainingFeature(eClass, container));
            idFactory.computeNames(list);
            ((IIdentifiableElement) element).setId(idFactory.getId());
            ((IIdentifiableElement) element).setName(idFactory.getName());
            if (idFactory.getReferingElement() == null)
            {
               idFactory.setReferingElement((IIdentifiableModelElement) element);
            }
            return;
         }
         if (element instanceof TypeDeclarationType)
         {
            String orgName = ((TypeDeclarationType)element).getName();
            ((TypeDeclarationType)element).setName(MessageFormat.format(Templates_Messages.TXT_COPY_OF, new Object[]{orgName}));
            ((TypeDeclarationType)element).setId(MessageFormat.format(Templates_Messages.TXT_COPY_OF, new Object[]{orgName}));
            dataRefCache.put(orgName, ((TypeDeclarationType)element).getName());
         } else {
            openDialog(container, element);
            if (element instanceof DataType) {
               String type = AttributeUtil.getAttributeValue((DataType) element, "carnot:engine:dataType"); //$NON-NLS-1$
               String newType = (String) dataRefCache.get(type);
               AttributeUtil.setAttribute((IExtensibleElement)element, "carnot:engine:dataType", newType); //$NON-NLS-1$
            }
         }
      }
   }

   private void collectTargetData(ModelType model)
   {
      String id;
      String name;
      // here we scan all children and children of those children
      for (Iterator i = model.eAllContents(); i.hasNext();)
      {
         EObject child = (EObject) i.next();

         EClass eClass = child.eClass();
         EObject parent = child.eContainer();

         Map eClassNameIdCache = new HashMap();
         Object localNameIdCache = null;

         if(nameIdCache.containsKey(parent))
         {
            eClassNameIdCache = (HashMap) nameIdCache.get(parent);
            if(eClassNameIdCache.containsKey(eClass))
            {
               if (child instanceof DiagramType)
               {
                  localNameIdCache = (List) eClassNameIdCache.get(eClass);
               }
               else
               {
                  localNameIdCache = (HashMap) eClassNameIdCache.get(eClass);
               }
            }
         }
         if(localNameIdCache == null)
         {
            if (child instanceof DiagramType)
            {
               localNameIdCache = new ArrayList();
            }
            else
            {
               localNameIdCache = new HashMap();
            }
         }
         if (child instanceof IIdentifiableElement)
         {
            id = ((IIdentifiableElement) child).getId();
            name = ((IIdentifiableElement) child).getName();
            ((HashMap) localNameIdCache).put(id, name);
            eClassNameIdCache.put(eClass, localNameIdCache);
         }
         else if (child instanceof IModelElement)
         {
            if (child instanceof DiagramType)
            {
               name = ((DiagramType) child).getName();
               ((ArrayList) localNameIdCache).add(name);
               eClassNameIdCache.put(eClass, localNameIdCache);
            }
         }
         else if (child instanceof TypeDeclarationType)
         {
            id = ((TypeDeclarationType) child).getId();
            name = ((TypeDeclarationType) child).getName();
            ((HashMap) localNameIdCache).put(id, name);
            eClassNameIdCache.put(eClass, localNameIdCache);
         }
         nameIdCache.put(parent, eClassNameIdCache);
      }
   }

   private boolean checkElementInModel(EObject targetContainer, EObject element)
   {
      EClass eClass = element.eClass();

      EObject parent = targetContainer;
      EObject checkParent = (EObject) changedCache.get(parent);
      if(checkParent != null)
      {
         parent = checkParent;
      }

      String id;
      String name;

      Map eClassNameIdCache = new HashMap();
      Object localNameIdCache = null;

      if(nameIdCache.containsKey(parent))
      {
         eClassNameIdCache = (HashMap) nameIdCache.get(parent);
         if(eClassNameIdCache.containsKey(eClass))
         {
            if (element instanceof IIdentifiableElement)
            {
               id = ((IIdentifiableElement) element).getId();
               name = ((IIdentifiableElement) element).getName();
               localNameIdCache = (HashMap) eClassNameIdCache.get(eClass);
               if(((HashMap) localNameIdCache).containsKey(id)
                     || ((HashMap) localNameIdCache).containsValue(name))
               {
                  return true;
               }
            } else if (element instanceof TypeDeclarationType) {
               id = ((TypeDeclarationType) element).getId();
               name = ((TypeDeclarationType) element).getName();
               localNameIdCache = (HashMap) eClassNameIdCache.get(eClass);
               if(((HashMap) localNameIdCache).containsKey(id)
                     || ((HashMap) localNameIdCache).containsValue(name))
               {
                  return true;
               }
            }
         }
      }
      return false;

   }

   private boolean openDialog(EObject parent, EObject element)
   {
      EClass eClass = element.eClass();

      String id;
      String name;

      Map eClassNameIdCache = new HashMap();
      Object localNameIdCache = null;

      eClassNameIdCache = (HashMap) nameIdCache.get(parent);

      id = ((IIdentifiableElement) element).getId();
      name = ((IIdentifiableElement) element).getName();
      localNameIdCache = (HashMap) eClassNameIdCache.get(eClass);

      NameIdDialog nameIdDialog = new NameIdDialog(null, id, name, (HashMap) localNameIdCache);
      // add modified EObjects to newElements
      if (Dialog.OK == nameIdDialog.open())
      {
        ((IIdentifiableElement) element).setId(nameIdDialog.getId());
        ((IIdentifiableElement) element).setName(nameIdDialog.getName());
        ((HashMap) localNameIdCache).put(nameIdDialog.getId(), nameIdDialog.getName());
        eClassNameIdCache.put(eClass, localNameIdCache);
        nameIdCache.put(parent, eClassNameIdCache);
        return true;
      } else {
        throw new ImportCancelledException();
      }
   }

   private EStructuralFeature getContainingFeature(EClass eClass, EObject container)
   {
      List containingFeatureList = container.eClass().getEStructuralFeatures();
      return CommandUtils.findContainmentFeature(containingFeatureList, eClass);
   }

   public List getAddedSymbols()
   {
      return symbols;
   }
}