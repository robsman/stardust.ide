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
package org.eclipse.stardust.modeling.modelimport.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.modelimport.ImportMessages;
import org.eclipse.xsd.XSDSchema;


public class MergeUtil
{
   /**
    * @author fuhrmann
    * @version $Revision$
    */
   private static final class FixedCopier extends EcoreUtil.Copier
   {
      /**
       * to make eclipse happy
       */
      private static final long serialVersionUID = 1L;

      // we resolve IModelElements that were not created by lookup into the matching
      // table prepared during initialize
      public EObject get(Object key)
      {
         EObject result = super.get(key);
         if (result == null && key instanceof IModelElement)
         {
            result = existingElements.get(
                  new Long(((IModelElement) key).getElementOid()));
         }
         return result;
      }

      public EObject copy(EObject object)
      {         
         EObject result;
         if(object instanceof XSDSchema)
         {
            result = ((XSDSchema) object).cloneConcreteComponent(true, false);                     
         }
         else
         {
            result = super.copy(object);
         }

         if (result instanceof IModelElement)
         {
            Long key = new Long(((IModelElement) result).getElementOid());
            if (!existingElements.containsKey(key))
            {
               existingElements.put(key, result);
            }
         }

         return result;
      }

      // just to expose the method
      protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject)
      {
         super.copyReference(eReference, eObject, copyEObject);
      }
   }

   private static final int DUPLICATE_OID = 1;
   private static final int MISSING_OID = 2;
   
   private static HashMap<Long, EObject> existingElements = new HashMap<Long, EObject>();
   private static ArrayList addedElements = new ArrayList();
    
   private static FixedCopier copier = new FixedCopier();

   public static EObject copyAdd(EObject model)
   {
      EObject result = copy(model);
      
      // TODO: (fh) check if it would be okay to copy
      // immediate references to all elements, not only participant types
      if (model instanceof ParticipantType)
      {
         copier.copyReference(
            CarnotWorkflowModelPackage.eINSTANCE.getParticipantType_Participant(),
            model, result);
      }
      
      addedElements.add(result);
      return result;
   }

   public static EObject copy(EObject eObject)
   {
      // if the object was copied once, reuse it !
      EObject result = (EObject) copier.get(eObject);
      return result == null ? copier.copy(eObject) : result;
   }

   public static void copyReferences()
   {
      copier.copyReferences();
      copier.clear();
      for (int i = 0; i < addedElements.size(); i++)
      {
         EObject eObject = (EObject) addedElements.get(i);
         if (eObject instanceof OrganizationType)
         {
            List participantsRef = ((OrganizationType) eObject).getParticipant();
            for (int j = participantsRef.size() - 1; j >= 0; j--)
            {
               ParticipantType ref = (ParticipantType) participantsRef.get(j);
               // participant without a ModelParticipant has to be removed
               if (ref.getParticipant() == null)
               {
                  participantsRef.remove(j);
               }
            }
         }
         replaceReferencesInAddedElements((EObject) addedElements.get(i));
         
      }
      addedElements.clear();
   }

   private static void replaceReferencesInAddedElements(EObject eObject)
   {
      if (eObject instanceof IModelElement)
      {
         HashMap toChange = new HashMap();
         for (Iterator i = eObject.eContents().iterator(); i.hasNext();)
         {
            Object child = i.next();
            if (child instanceof IModelElement)
            {
               Object other = existingElements.get(
                     new Long(((IModelElement) child).getElementOid()));
               if (other != null)
               {
                  toChange.put(child, other);
               }
            }
         }
         for (Iterator i = toChange.entrySet().iterator(); i.hasNext();)
         {
            Map.Entry entry = (Entry) i.next();
            EcoreUtil.replace((EObject) entry.getKey(), (EObject) entry.getValue());
         }
      }
   }
   
   // called by MergeEditorInput 
   public static boolean initialize(ModelType newModel, ModelType targetModel, MergeEditorInput input)
   {
      copier.clear();
      existingElements.clear();
      
      HashMap matching = new HashMap();      
      XMLResource resource = targetModel.eResource() instanceof XMLResource
         ? (XMLResource) targetModel.eResource() : null;
      
      // collect matching elements.
      // IMPORTANT: this must be done before any attempt to fix the oids
      // newModel: only matching elements are collected in matching
      for (Iterator i = newModel.eAllContents(); i.hasNext();)
      {
         EObject child = (EObject) i.next();
         if (child instanceof IModelElement)
         {
            EObject other = ModelServerUtils.getSameModelElement(child, targetModel, newModel);
            if (other != null)
            {
               // TODO replace with Assert
               if (child.eClass().equals(other.eClass()))
               {
                  matching.put(child, other);
               }
            }
         }
      }

      long[] nextOid = {Long.MIN_VALUE};
      
      // all oid's
      HashSet globalOids = new HashSet();

      // elements without oid
      ArrayList problems = new ArrayList();

      List errors = new ArrayList();

      // collect existing oids and fix duplicates
      HashSet localOids = new HashSet();
      int result = 0;
      for (Iterator i = targetModel.eAllContents(); i.hasNext();)
      {
         EObject child = (EObject) i.next();
         result |= checkDuplicateOrUnsetOid(nextOid, globalOids, localOids, problems, child);
      }
      if ((result & DUPLICATE_OID) != 0)
      {
         errors.add(ImportMessages.MergeUtil_DUPLICATE_OIDS_TARGET);            
      }
      if ((result & MISSING_OID) != 0)
      {
         errors.add(ImportMessages.MergeUtil_MISSING_OIDS_TARGET);
      }
      
      // collect new oids and fix duplicates
      localOids.clear();
      result = 0;
      for (Iterator i = newModel.eAllContents(); i.hasNext();)
      {
         EObject child = (EObject) i.next();
         // only if object is not in matching (we then use the old object)
         if (!matching.containsKey(child))
         {
            result |= checkDuplicateOrUnsetOid(nextOid, globalOids, localOids, problems, child);
         }
      }
      if ((result & DUPLICATE_OID) != 0)
      {
         errors.add(ImportMessages.MergeUtil_DUPLICATE_OIDS_SOURCE);            
      }
      if ((result & MISSING_OID) != 0)
      {
         errors.add(ImportMessages.MergeUtil_MISSING_OIDS_SOURCE);
      }
      
      // an Error occured
      if (!errors.isEmpty())
      {
         // should return whether user pressed cancel
         if(input.showErrors((String[]) errors.toArray(new String[errors.size()])) == false)
         {
            return true;
         }
      }      

      // fix missing oids
      nextOid[0]++;
      for (int i = 0; i < problems.size(); i++)
      {
         IModelElement me = (IModelElement) problems.get(i);
         me.setElementOid(nextOid[0]++);
         updateIdCaches(resource, me);
      }
      
      // match the oids
      for (Iterator i = matching.entrySet().iterator(); i.hasNext();)
      {
         Map.Entry entry = (Entry) i.next();
         IModelElement me = (IModelElement) entry.getKey();
         IModelElement other = (IModelElement) entry.getValue();
         me.setElementOid(other.getElementOid());
         updateIdCaches(resource, me);
         existingElements.put(new Long(me.getElementOid()), other);
      }
      
      return false;
   }

   private static int checkDuplicateOrUnsetOid(long[] nextOid,
         HashSet globalOids, HashSet localOids,
         ArrayList problems, EObject child)
   {
      int result = 0;
      if (child instanceof IModelElement)
      {
         IModelElement me = (IModelElement) child;
         if (me.isSetElementOid())
         {
            // element oid
            long oid = me.getElementOid();
            Long key = new Long(oid);
            if (globalOids.contains(key))
            {
               // element has dupplicate oid
               problems.add(me);
            }
            else
            {
               globalOids.add(key);
               // find the highest oid
               nextOid[0] = Math.max(nextOid[0], oid);
            }
            if (localOids.contains(key))
            {
               result |= DUPLICATE_OID;
            }
            else
            {
               localOids.add(key);
            }
         }
         else
         {
            // oid of element not set
            result |= MISSING_OID;
            problems.add(me);
         }
      }
      return result;
   }

   private static void updateIdCaches(XMLResource resource, IModelElement me)
   {
      if (resource != null)
      {
         resource.setID(me, String.valueOf(me.getElementOid()));
      }
   }

   public static EObject getElementInOtherModel(EObject element, ModelType model)
   {
      List list = getContainingList(element, model);
      if (list != null)
      {
         StructureComparator elementNode = new StructureComparator(element, null);
         for (Iterator i = list.iterator(); i.hasNext();)
         {
            EObject target = (EObject) i.next();
            StructureComparator targetNode = new StructureComparator(target, null);
            if (elementNode.equals(targetNode))
            {
               return target;
            }
         }
      }
      return null;
   }

   private static List getContainingList(EObject obj, ModelType model)
   {
      EObject parent = obj.eContainer();
      if (parent == null)
      {
         return null;
      }
      EStructuralFeature feature = obj.eContainingFeature();
      if (parent instanceof ModelType)
      {
         parent = model;
      }
      else
      {
         parent = getElementInOtherModel(parent, model);
      }
      return parent != null && feature.isMany() ? (List) parent.eGet(feature) : null;
   }
}
