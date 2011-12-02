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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsFactory;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType;
import org.eclipse.stardust.model.xpdl.xpdl2.ModeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.StringUtils;

public class MergeUtils
{
   public static Map<EObject, EObject> createClosureMap(List<EObject> closure, ModelType targetModel)
   {
      Map<EObject, EObject> map = new HashMap<EObject, EObject>();
      for (EObject element : closure)
      {
         if (element.eContainingFeature() != null)
         {
            EObject original = ModelUtils.findElementById(element instanceof TypeDeclarationType ?
                  targetModel.getTypeDeclarations() : targetModel,
                  element.eContainingFeature(), MergeUtils.getId(element));
            map.put(element, original);            
         }
      }
      return map;
   }   
   
   public static URI createQualifiedUri(URI uri, EObject eObject, boolean qualifyUri)
   {
      String id = getId(eObject);
      return qualifyUri && id != null
         ? uri.appendSegment(eObject.eContainingFeature().getName()).appendSegment(id)
         : uri;
   }
   
   public static String getId(EObject eObject)
   {
      if (eObject instanceof IIdentifiableElement)
      {
         return ((IIdentifiableElement) eObject).getId();
      }
      else if (eObject instanceof TypeDeclarationType)
      {
         return ((TypeDeclarationType) eObject).getId();
      }
      return null;
   }

   public static String getName(EObject eObject)
   {
      if (eObject instanceof IIdentifiableElement)
      {
         return ((IIdentifiableElement) eObject).getName();
      }
      else if (eObject instanceof TypeDeclarationType)
      {
         return ((TypeDeclarationType) eObject).getName();
      }
      return null;
   }

   public static void deleteElement(EObject element, EObject parent)
   {
      EReference eFtrContainment = element.eContainmentFeature();
      if(eFtrContainment != null)
      {
         // parent
         if(parent == null)
         {
            parent = element.eContainer();
         }
         Object containment = parent.eGet(eFtrContainment);         
         if (containment instanceof List)
         {
            ((List<?>) containment).remove(element);
         }  
         else
         {
            parent.eUnset(eFtrContainment);
         }
      }
   }      
   
   public static void importElements(EObject eObject, ModelType targetModel, 
         List<EObject> closure, Map<EObject, EObject> map, Map<EObject, MergeAction> reuseReplace,
         LinkAttribute linkAttribute)
   {      
      // make a list with all models affected by the change
      // so it can only be the source model
      Set<ModelType> roots = new HashSet<ModelType>();
      roots.add(targetModel);
      for (int i = 0; i < closure.size(); i++)
      {
         ModelType containingModel = ModelUtils.findContainingModel((EObject) closure.get(i));
         if (containingModel != null)
         {
            roots.add(containingModel);
         }
      }
      
      // compute boundary oids required for oid realocation
      long maxOid = ModelUtils.getMaxUsedOid(targetModel);
      // next max oid of source model
      long nextOid = maxOid;
      for (ModelType model : roots)
      {
         if (model != null && model != targetModel)
         {
            nextOid = Math.max(nextOid, ModelUtils.getMaxUsedOid(model));
         }
      }
      nextOid++;
      
      // collect all references by id and replace them with nulls.
      // this is mandatory since references by id are lost during transfer
      // as a consequence of the target objects being removed from their container.
      Map<IdentifiableReference, EObject> refs = CollectionUtils.newMap();
      for (ModelType model : roots)
      {
         // get all content from source model
         for (Iterator<EObject> c = model.eAllContents(); c.hasNext();)
         {
            EObject eo = c.next();
            if (eo instanceof AttributeType)
            {
               AttributeType att = (AttributeType) eo;
               IdentifiableReference ir = att.getReference();
               if (ir != null)
               {
                  EObject id = ir.getIdentifiable();
                  if (closure.contains(id))
                  {
                     refs.put(ir, id);
                     // set ref in source model to null?
                     ir.setIdentifiable(null);
                  }
               }
            }
         }
      }
      
      // sort elements per actions
      Map<EObject, EObject> reuse = CollectionUtils.newMap();
      Map<EObject, EObject> replace = CollectionUtils.newMap();
      List<EObject> add = CollectionUtils.newList();
      
      for (int i = 0; i < closure.size(); i++)
      {
         EObject element = (EObject) closure.get(i);
         EObject original = null;
         // if this is a new element we have no original
         if (map != null)
         {
            original = (EObject) map.get(element);
         }
         
         if (original != null)
         {
            MergeAction action = MergeAction.REPLACE;
            if (reuseReplace != null)
            {
               MergeAction actionId = reuseReplace.get(original);
               if (actionId != null)
               {
                  action = actionId;
               }
            }            
                       
            if (action == MergeAction.REUSE)
            {
               reuse.put(element, original);
            }
            else if (action == MergeAction.REPLACE)
            {
               replace.put(element, original);
            }
            else
            {
               return;
            }
         }
         else
         {
            add.add(element);
         }
      }
      
      for (Map.Entry<EObject, EObject> entry : reuse.entrySet())
      {
         EObject element = entry.getKey();
         EObject original = entry.getValue();
         // reusing will move original to the new model, so we need to move it back afterwards
         EStructuralFeature containingFeature = original.eContainingFeature();
         EObject eContainer = original.eContainer();
         List originalContainer = (List) eContainer.eGet(containingFeature); 
         // replace element with original?
         replace(element, original);
         if (!originalContainer.contains(original))
         {
            originalContainer.add(original);
         }
      }
      
      for (Map.Entry<EObject, EObject> entry : replace.entrySet())
      {
         EObject element = entry.getKey();
         EObject original = entry.getValue();
         nextOid = updateOids(element, maxOid, nextOid);
         if(linkAttribute != null)
         {
            linkAttribute.setLinkInfo(element, element != eObject);
         }
         
         // collision, set uuid
         if (ShareUtils.isLockableElement(element))
         {
            if (!ShareUtils.isModelShared(targetModel))
            {
               UUIDUtils.unsetUUID(element);
            }               
         }                  
         replace(original, element);
      }
      
      for (int i = 0; i < add.size(); i++)
      {
         EObject element = (EObject) add.get(i);
         nextOid = updateOids(element, maxOid, nextOid);
         if(linkAttribute != null)
         {
            linkAttribute.setLinkInfo(element, element != eObject);
         }
         
         EObject parent = null;
         if(element instanceof TypeDeclarationType)
         {
            parent = targetModel.getTypeDeclarations();
         }
         else
         {
            parent = targetModel;
         }
         // collision, set uuid
         if (ShareUtils.isLockableElement(element))
         {
            if (!ShareUtils.isModelShared(targetModel))
            {
               UUIDUtils.unsetUUID(element);
            }               
         }         
         Object ref = parent.eGet(element.eContainingFeature());
         if (ref instanceof List)
         {
            ((List) ref).add(element);
         }
         else
         {
            parent.eSet(element.eContainingFeature(), element);
         }
      }
      
      // now restore all references by id
      for (Map.Entry<IdentifiableReference, EObject> entry : refs.entrySet())
      {
         IdentifiableReference ir = entry.getKey();
         EObject element = entry.getValue();
         EObject original = reuse.get(element);
         
         try
         {
            ir.setIdentifiable(original == null ? element : original);
         }
         catch (NullPointerException e)
         {
         }
      }
   }

   private static long updateOids(EObject element, long maxOid, long nextOid)
   {
      if (element instanceof IModelElement)
      {
         IModelElement ime = (IModelElement) element;
         if (!ime.isSetElementOid() || ime.getElementOid() <= maxOid)
         {
            ime.setElementOid(nextOid++);
         }
         for (Iterator<EObject> i = element.eAllContents(); i.hasNext();)
         {
            EObject child = i.next();
            nextOid = updateOids(child, maxOid, nextOid);
         }
      }
      return nextOid;
   }

   public static void replace(EObject object, EObject replacementObject, boolean preserveContainer)
   {
      if (preserveContainer)
      {
         EStructuralFeature containingFeature = replacementObject.eContainingFeature();
         EObject eContainer = replacementObject.eContainer();
         List originalContainer = (List) eContainer.eGet(containingFeature); 
         replace(object, replacementObject);
         if (!originalContainer.contains(replacementObject))
         {
            originalContainer.add(replacementObject);
         }
      }
      else
      {
         replace(object, replacementObject);
      }
   }
   
   public static void replace(EObject object, EObject replacementObject)
   {      
      EObject topContainer = object;
      while (topContainer.eContainer() != null)
      {
         topContainer = topContainer.eContainer();
      }
      
      if(!(replacementObject instanceof TypeDeclarationType))
      {
         replaceReferences(topContainer, object, replacementObject);
         Iterator contents = topContainer.eAllContents();
         while (contents.hasNext())
         {
            replaceReferences((EObject) contents.next(), object, replacementObject);
         }
      }

      try
      {
         EcoreUtil.replace(object, replacementObject);
      }
      catch (NullPointerException e)
      {
      }      
      
      if(replacementObject instanceof TypeDeclarationType)
      {
         replaceReferences(topContainer, object, replacementObject);
         Iterator contents = topContainer.eAllContents();
         while (contents.hasNext())
         {
            replaceReferences((EObject) contents.next(), object, replacementObject);
         }
      }      
      if(object instanceof ProcessDefinitionType)
      {
         mergeFormalParameter((ProcessDefinitionType) object, (ProcessDefinitionType) replacementObject);
      }
   }

   private static void replaceReferences(EObject source, EObject object,
         EObject replacementObject)
   {
      EList<EObject> crossReferences = source.eCrossReferences();
      EContentsEList.FeatureIterator<EObject> featureIterator = (EContentsEList.FeatureIterator<EObject>) crossReferences.iterator();
      // make a copy so that processing will not destoy the live iterator
      List<EObject> objects = CollectionUtils.newList();
      List<EReference> references = CollectionUtils.newList();
      while (featureIterator.hasNext())
      {
         objects.add(featureIterator.next());
         references.add((EReference) featureIterator.feature());
      }
      if (!objects.contains(replacementObject))
      {
         for (int i = 0; i < objects.size(); i++)
	     {
	        Object target = objects.get(i);
	        EReference feature = references.get(i);
	        if (target == object)
	        {
	           EcoreUtil.replace(source, feature, object, replacementObject);
	        }
	        else if (target instanceof List)
	        {
	           List<?> list = (List<?>) target;
	           int index = list.indexOf(object);
	           if (index >= 0)
	           {
	              EcoreUtil.replace(source, feature, object, replacementObject);
	           }
	        }
         }
      }
      if (source instanceof AttributeType)
      {
	     AttributeType attribute = (AttributeType) source;
	     IdentifiableReference reference = attribute.getReference();
	     if (reference != null && object == reference.getIdentifiable())
	     {
	    	reference.setIdentifiable(replacementObject);
	     }
      }
   }
   
   public static void mergeFormalParameter(ProcessDefinitionType source, ProcessDefinitionType target)
   {      
      FormalParameterMappingsType parameterMappings = ExtensionsFactory.eINSTANCE.createFormalParameterMappingsType();
      FormalParametersType formalParameters = XpdlFactory.eINSTANCE.createFormalParametersType();
      FormalParameterMappingsType formalParameterMappings = source.getFormalParameterMappings();
      FormalParametersType referencedParametersType = source.getFormalParameters();
      if(referencedParametersType == null)
      {
         return;
      }
      
      for (Iterator<FormalParameterType> i = referencedParametersType.getFormalParameter().iterator(); i.hasNext();) {
         FormalParameterType referencedParameterType = i.next();
         ModeType mode = referencedParameterType.getMode();
         
         DataType mappedData = formalParameterMappings.getMappedData(referencedParameterType);         
         IIdentifiableModelElement modelElement = (IIdentifiableModelElement) getSameModelElement(mappedData, (ModelType) target.eContainer(), null);         
         
         FormalParameterType parameterType = ModelUtils.cloneFormalParameterType(referencedParameterType, mappedData);
         
         parameterType.setMode(mode);
         formalParameters.addFormalParameter(parameterType);
         parameterMappings.setMappedData(parameterType, (DataType) modelElement);
      }                        
      target.setFormalParameters(formalParameters);
      target.setFormalParameterMappings(parameterMappings);                  
   }  
   
   
   // not working for diagram children
   // target must be of type Model!!!
   public static EObject getSameModelElement(EObject source, ModelType target,
         Map changedCache)
   {
      Stack stack = new Stack();
      // return the model
      if (source instanceof ModelType)
      {
         return target;
      }
      if (source instanceof TypeDeclarationsType)
      {
         return target.getTypeDeclarations();
      }
      if (source instanceof TypeDeclarationType)
      {
         TypeDeclarationsType declarations = target.getTypeDeclarations();
         TypeDeclarationType typeDeclaration = declarations
               .getTypeDeclaration(((TypeDeclarationType) source).getId());
         return typeDeclaration;
      }

      EObject parent = source.eContainer();
      boolean getParent = false;
      if (parent == null)
      {
         return null;
      }

      while (!(parent instanceof ModelType) && parent.eContainer() != null)
      {
         if (getParent)
         {
            parent = parent.eContainer();
            if (changedCache != null)
            {
               EObject checkParent = (EObject) changedCache.get(parent);
               if (checkParent != null)
               {
                  parent = checkParent;
               }
            }
         }
         if (!(parent instanceof ModelType))
         {
            stack.push(parent);
         }
         getParent = true;
      }
      if (stack.isEmpty())
      {
         return getSameElement(source, target);
      }
      EObject targetObject = target;
      while (!stack.isEmpty())
      {
         EObject child = (EObject) stack.pop();
         // find element in model
         parent = getSameElement(child, targetObject);
         if (parent == null)
         {
            return null;
         }
         targetObject = parent;
      }

      return getSameElement(source, targetObject);
   }

   // a symbol must be checked by oid
   public static EObject getSameElement(EObject source, EObject model)
   {
      // we have reached to top level already
      if (source instanceof ModelType && model instanceof ModelType)
      {
         return model;
      }

      String sourceUuid = UUIDUtils.getUUID(source);
      EStructuralFeature feature = source.eContainingFeature();
      if (feature == null)
      {
         return null;
      }

      // get a list with this feature from the model
      Object element;
      if (source instanceof TypeDeclarationType)
      {
         ModelType theModel = ModelUtils.findContainingModel(model);
         element = theModel.getTypeDeclarations().eGet(feature);
      }
      else
      {
         element = model.eGet(feature);
      }

      List<EObject> list = new ArrayList<EObject>();
      if (element == null)
      {
         return null;
      }
      if (element instanceof List)
      {
         list = (List<EObject>) element;
      }
      else
      {
         return null;
      }

      // compare if we find the same element in the (other) model
      // first check all uuids
      for (int i = 0; i < list.size(); i++)
      {
         EObject entry = (EObject) list.get(i);
         String uuid = UUIDUtils.getUUID(entry);
         if (!StringUtils.isEmpty(uuid) && !StringUtils.isEmpty(sourceUuid))
         {
            if (uuid.equals(sourceUuid))
            {
               return entry;
            }
         }
      }
      // now check name/id
      for (int i = 0; i < list.size(); i++)
      {
         EObject entry = (EObject) list.get(i);
         if (entry instanceof IIdentifiableElement
               && source instanceof IIdentifiableElement
               && ((IIdentifiableElement) entry).getId() != null
               && ((IIdentifiableElement) source).getId() != null)
         {
            if (((IIdentifiableElement) entry).getId().equals(
                  ((IIdentifiableElement) source).getId()))
            {
               return entry;
            }
         }
         else if (entry instanceof DiagramType && source instanceof DiagramType)
         {
            String entryName = ((DiagramType) entry).getName();
            String sourceName = ((DiagramType) source).getName();

            // equal if we have only one (?)
            if (entryName == null && sourceName == null)
            {
               return entry;
            }
            if (entryName != null
                  && sourceName != null
                  && ((DiagramType) entry).getName().equals(
                        ((DiagramType) source).getName()))
            {
               return entry;
            }
         }
         else if (entry instanceof IModelElement && source instanceof IModelElement)
         {
            if (((IModelElement) entry).getElementOid() == ((IModelElement) source)
                  .getElementOid())
            {
               return entry;
            }
         }
         else if (entry instanceof TypeDeclarationType
               && source instanceof TypeDeclarationType)
         {
            if (((TypeDeclarationType) entry).getId().equals(
                  ((TypeDeclarationType) source).getId()))
            {
               return entry;
            }
         }
      }
      return null;
   }
   
}