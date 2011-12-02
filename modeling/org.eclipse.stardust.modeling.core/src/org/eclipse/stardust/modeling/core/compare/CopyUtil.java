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
package org.eclipse.stardust.modeling.core.compare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class CopyUtil
{
   private static Map visited;
   private static Map features;
   
   public static void reset()
   {
      if (visited == null)
      {
         visited = new HashMap();
      }
      else
      {
         visited.clear();
      }

      if (features == null)
      {
         features = new HashMap();
      }
      else
      {
         features.clear();
      }
   }
   
   /**
    * Creates an instance of the class.
    * 
    * @param eClass
    *           the class to instantiate.
    * @return an instance of the class.
    */
   public static EObject create(EClass eClass)
   {
      return eClass.getEPackage().getEFactoryInstance().create(eClass);
   }

   /**
    * Returns a self-contained copy of the eObject.
    * 
    * @param eObject
    *           the object to copy.
    * @return the copy.
    * @see Copier
    */
   public static EObject copy(EObject eObject, EObject target)
   {
      Copier copier = new Copier(target, visited);
      EObject result = copier.copy(eObject);
      copier.copyReferences();
      return result;
   }

   /**
    * Returns a collection of the self-contained copies of each {@link EObject} in
    * eObjects.
    * 
    * @param eObjects
    *           the collection of objects to copy.
    * @return the collection of copies.
    * @see Copier
    */
   public static Collection copyAll(Collection eObjects, EObject target)
   {
      Copier copier = new Copier(target, visited);
      Collection result = copier.copyAll(eObjects);
      copier.copyReferences();
      return result;
   }

   /**
    * A mapping building traverser of a collection of
    * {@link EObject#eAllContents content trees}; the map is from {@link EObject} to
    * <code>EObject</code>, i.e., from original to copy; use
    * {@link EcoreUtil#copy EcoreUtil.copy} or {@link EcoreUtil#copyAll EcoreUtil.copyAll}
    * to do routine copies. Since this implementation extends a Map implementation, it
    * acts as the result of the over all copy. The client can call {@link #copy copy} and
    * {@link #copyAll copyAll} repeatedly. When all the objects have been copied, the
    * client should call {@link #copyReferences copyReferences} to copy the
    * {@link #copyReference appropriate} {@link EObject#eCrossReferences cross references}.
    * 
    * <pre>
    * Copier copier = new Copier();
    * EObject result = copier.copy(eObject);
    * Collection results = copier.copyAll(eObjects);
    * copier.copyReferences();
    * </pre>
    * 
    * The copier delegates to {@link #copyContainment copyContainment},
    * {@link #copyAttribute copyAttribute} during the copy phase and to
    * {@link #copyReference copyReference}, during the cross reference phase. This allows
    * tailored handling through derivation.
    */
   public static class Copier
   {
      private EObject target;
      private Map visited;
      private ModelType model;

      public Copier(EObject target, Map visited)
      {
         this.target = target;
         this.visited = visited;
         model = ModelUtils.findContainingModel(target);
      }

      /**
       * Returns a collection containing a copy of each EObject in the given collection.
       * 
       * @param eObjects
       *           the collection of objects to copy.
       * @return the collection of copies.
       */
      public Collection copyAll(Collection eObjects)
      {
         Collection result = new ArrayList(eObjects.size());
         for (Iterator i = eObjects.iterator(); i.hasNext();)
         {
            result.add(copy((EObject) i.next()));
         }
         return result;
      }

      /**
       * Returns a copy of the given eObject.
       * 
       * @param eObject
       *           the object to copy.
       * @return the copy.
       */
      public EObject copy(EObject eObject)
      {
         // TODO
         EObject copyEObject = (EObject) visited.get(eObject);
         if (copyEObject == null)
         {
            boolean newObject = false;
            copyEObject = getElementInOtherModel(eObject, model);
            if (copyEObject == null)
            {
               copyEObject = createCopy(eObject);
               newObject = true;
            }
            visited.put(eObject, copyEObject);
            EClass eClass = eObject.eClass();
            if (newObject)
            {
               for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i)
               {
                  EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
                  if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived())
                  {
                     if (eStructuralFeature instanceof EAttribute)
                     {
                        copyAttribute((EAttribute) eStructuralFeature, eObject, copyEObject);
                     }
                     else
                     {
                        EReference eReference = (EReference) eStructuralFeature;
                        if (eReference.isContainment())
                        {
                           copyContainment(eReference, eObject, copyEObject);
                        }
                        else
                        {
                           Object eRefObj = eObject.eGet(eReference);
   
                           EObject refEObject = null;
   
                           if (eRefObj instanceof EObject && !(eReference.isTransient()))
                           {
                              refEObject = (EObject) eRefObj;
                           }
   
                           if (refEObject != null)
                           {
                              ComparableModelElementNode node = new ComparableModelElementNode(
                                    refEObject);
   
                              if (isModelElementInTargetModel(node.getEObject(), this.target))
                              {
                                 copyContainment(eReference, eObject, copyEObject);
                              }
                           }
   
                        }
                     }
                  }
               }
            }
         }
/*         else
         {
            System.err.println("Reusing object: " + copyEObject);
         }*/
         return copyEObject;
      }

      /**
       * Returns a new instance of the object's target class.
       * 
       * @param eObject
       *           the object to copy.
       * @return a new instance of the target class.
       * @see #getTarget(EClass)
       * @see EcoreUtil#create(EClass)
       */
      protected EObject createCopy(EObject eObject)
      {
         return create(getTarget(eObject.eClass()));
      }

      /**
       * Returns the target class used to create a copy instance for objects of the given
       * source class.
       * 
       * @param eClass
       *           the source class.
       * @return the target class used to create a copy instance.
       * @see #getTarget(EStructuralFeature)
       */
      protected EClass getTarget(EClass eClass)
      {
         return eClass;
      }

      /**
       * Returns the target feature used to populate a copy instance from the given source
       * feature.
       * 
       * @param eStructuralFeature
       *           the source feature.
       * @return the target feature used to populate a copy instance.
       * @see #getTarget(EClass)
       */
      protected EStructuralFeature getTarget(EStructuralFeature eStructuralFeature)
      {
         return eStructuralFeature;
      }

      /**
       * Called to handle the copying of a containment feature; this adds a list of copies
       * or sets a single copy as appropriate for the multiplicity.
       * 
       * @param eReference
       *           the feature to copy.
       * @param eObject
       *           the object from which to copy.
       * @param copyEObject
       *           the object to copy to.
       */
      protected void copyContainment(EReference eReference, EObject eObject,
            EObject copyEObject)
      {
         if (eObject.eIsSet(eReference))
         {
            if (eReference.isMany())
            {
               List source = (List) eObject.eGet(eReference);
               List target = (List) copyEObject.eGet(getTarget(eReference));
               if (source.isEmpty())
               {
                  target.clear();
               }
               else
               {
                  target.addAll(copyAll(source));
               }
            }
            else
            {
               EObject childEObject = (EObject) eObject.eGet(eReference);
               copyEObject.eSet(getTarget(eReference), childEObject == null
                     ? null
                     : copy(childEObject));
            }
         }
      }

      /**
       * Called to handle the copying of an attribute; this adds a list of values or sets
       * a single value as appropriate for the multiplicity.
       * 
       * @param eAttribute
       *           the attribute to copy.
       * @param eObject
       *           the object from which to copy.
       * @param copyEObject
       *           the object to copy to.
       */
      protected void copyAttribute(EAttribute eAttribute, EObject eObject,
            EObject copyEObject)
      {
         if (eObject.eIsSet(eAttribute))
         {
            if (FeatureMapUtil.isFeatureMap(eAttribute))
            {
               FeatureMap featureMap = (FeatureMap) eObject.eGet(eAttribute);
               for (int i = 0, size = featureMap.size(); i < size; ++i)
               {
                  EStructuralFeature feature = featureMap.getEStructuralFeature(i);
                  if (feature instanceof EReference
                        && ((EReference) feature).isContainment())
                  {
                     Object value = featureMap.getValue(i);
                     if (value != null)
                     {
                        copy((EObject) value);
                     }
                  }
               }
            }
            else if (eAttribute.isMany())
            {
               List source = (List) eObject.eGet(eAttribute);
               List target = (List) copyEObject.eGet(getTarget(eAttribute));
               if (source.isEmpty())
               {
                  target.clear();
               }
               else
               {
                  target.addAll(source);
               }
            }
            else
            {
               copyEObject.eSet(getTarget(eAttribute), eObject.eGet(eAttribute));
            }
         }
      }

      /**
       * Hooks up cross references; it delegates to {@link #copyReference copyReference}.
       */
      public void copyReferences()
      {
         for (Iterator i = visited.entrySet().iterator(); i.hasNext();)
         {
            Map.Entry entry = (Map.Entry) i.next();
            EObject eObject = (EObject) entry.getKey();
            EObject copyEObject = (EObject) entry.getValue();
            EClass eClass = eObject.eClass();
            for (int j = 0, size = eClass.getFeatureCount(); j < size; ++j)
            {
               EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(j);
               if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived())
               {
                  if (eStructuralFeature instanceof EReference)
                  {
                     EReference eReference = (EReference) eStructuralFeature;
                     if (!eReference.isContainment())
                     {
                        copyReference(eReference, eObject, copyEObject);
                     }
                  }
                  else if (FeatureMapUtil.isFeatureMap(eStructuralFeature))
                  {
                     FeatureMap featureMap = (FeatureMap) eObject
                           .eGet(eStructuralFeature);
                     // <changed>
                     FeatureMap copyFeatureMap = (FeatureMap) features.get(featureMap);
                     if (copyFeatureMap == null)
                     {
                        copyFeatureMap = (FeatureMap) copyEObject
                              .eGet(getTarget(eStructuralFeature));
                        features.put(featureMap, copyFeatureMap);
                        // </changed>
                        for (int k = 0, featureMapSize = featureMap.size(); k < featureMapSize; ++k)
                        {
                           EStructuralFeature feature = featureMap.getEStructuralFeature(k);
                           if (feature instanceof EReference)
                           {
                              Object referencedEObject = featureMap.getValue(k);
                              // TODO
                              Object copyReferencedEObject = visited.get(referencedEObject);
                              copyFeatureMap.add(feature, copyReferencedEObject == null
                                    ? referencedEObject
                                    : copyReferencedEObject);
                           }
                           else
                           {
                              copyFeatureMap.add(featureMap.get(k));
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      /**
       * Called to handle the copying of a cross reference; this adds values or sets a
       * single value as appropriate for the multiplicity while omitting any bidirectional
       * reference that isn't in the copy map.
       * 
       * @param eReference
       *           the reference to copy.
       * @param eObject
       *           the object from which to copy.
       * @param copyEObject
       *           the object to copy to.
       */
      protected void copyReference(EReference eReference, EObject eObject,
            EObject copyEObject)
      {
         if (eObject.eIsSet(eReference))
         {
            if (eReference.isMany())
            {
               List source = (List) eObject.eGet(eReference);
               InternalEList target = (InternalEList) copyEObject
                     .eGet(getTarget(eReference));
               if (source.isEmpty())
               {
                  target.clear();
               }
               else
               {
                  boolean isBidirectional = eReference.getEOpposite() != null;
                  int index = 0;
                  for (Iterator k = source.iterator(); k.hasNext();)
                  {
                     Object referencedEObject = k.next();
                     // TODO
                     Object copyReferencedEObject = visited.get(referencedEObject);
                     if (copyReferencedEObject == null)
                     {
                        if (!isBidirectional)
                        {
                           target.addUnique(index, referencedEObject);
                           ++index;
                        }
                     }
                     else
                     {
                        if (isBidirectional)
                        {
                           int position = target.indexOf(copyReferencedEObject);
                           if (position == -1)
                           {
                              target.addUnique(index, copyReferencedEObject);
                           }
                           else if (index != position)
                           {
                              target.move(index, copyReferencedEObject);
                           }
                        }
                        else
                        {
                           target.addUnique(index, copyReferencedEObject);
                        }
                        ++index;
                     }
                  }
               }
            }
            else
            {
               Object referencedEObject = eObject.eGet(eReference);
               if (referencedEObject == null)
               {
                  copyEObject.eSet(getTarget(eReference), null);
               }
               else
               {
                  // TODO
                  Object copyReferencedEObject = visited.get(referencedEObject);
                  if (copyReferencedEObject == null)
                  {
                     if (eReference.getEOpposite() == null)
                     {
                        copyEObject.eSet(getTarget(eReference), referencedEObject);
                     }
                  }
                  else
                  {
                     copyEObject.eSet(getTarget(eReference), copyReferencedEObject);
                  }
               }
            }
         }
      }
   }
   
   public static boolean isModelElementInTargetModel(EObject element, EObject target)
   {
      ModelType model = ModelUtils.findContainingModel(target);
      
      boolean elementExistsInModel = element instanceof IModelElement
         ? getElementInOtherModel(element, model) != null : false;

      // exists this object in the list of selected nodes to import
      if (!elementExistsInModel)
      {
         elementExistsInModel = ImportSelectionRegistration.lookup(element);
      }

      return elementExistsInModel;
   }

   private static Collection getContainingList(EObject obj, ModelType model)
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
      if (parent == null)
      {
         return null;
      }
      Object o = parent.eGet(feature);
      if (!(o instanceof Collection))
      {
         return null; //o = Collections.singleton(o);
      }
      return (Collection) o;
   }

   public static EObject getElementInOtherModel(EObject element, ModelType model)
   {
      EObject other = (EObject) visited.get(element);
      if (other != null)
      {
         return other;
      }
      Collection listOfEObjects = getContainingList(element, model);
      if (listOfEObjects != null)
      {
         ComparableModelElementNode elementNode = new ComparableModelElementNode(
               element);
         for (Iterator _iterator = listOfEObjects.iterator(); _iterator.hasNext();)
         {
            ComparableModelElementNode targetNode = new ComparableModelElementNode(
                  (EObject) _iterator.next());
            if (elementNode.getElementIdentifier().equals(
                  targetNode.getElementIdentifier()))
            {
               return targetNode.getEObject();
            }
         }
      }
      return null;
   }
}
