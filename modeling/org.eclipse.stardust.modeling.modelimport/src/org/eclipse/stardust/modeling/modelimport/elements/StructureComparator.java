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

import java.util.Collections;
import java.util.List;

import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.Coordinates;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TextType;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.swt.graphics.Image;

public class StructureComparator implements IStructureComparator, ITypedElement, IEditableContent
{
   EObject model;
   private IStructureComparator parent;
   private IStructureComparator[] children;
   private String key;

   public StructureComparator(EObject model, IStructureComparator parent)
   {
      this.model = model;
      this.parent = parent;
      this.key = computeUniqueIdentifier();
   }

   String computeUniqueIdentifier()
   {
      String id = null;

      // special cases of no IModelElement
      if (model instanceof DiagramType)
      {
         id = ((DiagramType) model).getName();
      }
      
      if (model instanceof ViewType)
      {
         id = ((DiagramType) model).getName();
      }

      if (model instanceof AttributeType)
      {
         id = ((AttributeType) model).getName();
      }
      
      if (model instanceof ParticipantType)
      {
         id =  ((ParticipantType) model).getParticipant().getId() ;
      }
      
      if (model instanceof ContextType)
      {
         id = (null != ((ContextType) model).getMetaType()) ? ((ContextType) model).getMetaType().getId() : ((ContextType) model).toString();
      }
      
      if (model instanceof Coordinates)
      {
         id = Integer.toString(getList(model.eContainer(), model.eContainingFeature()).indexOf(model));
      }
      
      if (model instanceof DataMappingType)
      {
         id = ((DataMappingType) model).getContext() + ":" //$NON-NLS-1$
            + ((DataMappingType) model).getDirection() + ":" //$NON-NLS-1$
            + ((DataMappingType) model).getId();
      }
      
      // Carnot EObjects
      if (model instanceof IIdentifiableElement)
      {
         id = ((IIdentifiableElement) model).getId();
      }
      
      if (model instanceof DataPathType)
      {
         id = ((DataPathType) model).getDirection() + ":" + id; //$NON-NLS-1$
      }
      
      if (model instanceof TypeDeclarationType)
      {
         id = ((TypeDeclarationType) model).getId();
      }
      
      if (model instanceof Connection)
      {
         id = ((Connection) model).getId();
      }
      
      if (id == null && model instanceof IModelElement)
      {
         id = Long.toString(((IModelElement) model).getElementOid());
      }
      
      if (id == null && model.eContainingFeature() != null && !model.eContainingFeature().isMany())
      {
         id = model.eContainingFeature().getName();
      }
      
      if (id == null)
      {
         id = getEObjectLabel(model);
      }
      
      return model.eClass().getName() + ':' + id;
   }

   public Object[] getChildren()
   {
      if (children == null)
      {
         List<IStructureComparator> all = CollectionUtils.newList();
   
         addAttributes(all);
   
         addReferences(all);
   
         addContents(all);
         
         children = all.toArray(new IStructureComparator[all.size()]);
      }
      return children;
   }

   void addContents(List<IStructureComparator> all)
   {
      List<EObject> contents = model.eContents();
      for (EObject eObject : contents)
      {
         if (CarnotWorkflowModelPackage.eNS_URI.equals(eObject.eClass().getEPackage().getNsURI())
               || XpdlPackage.eNS_URI.equals(eObject.eClass().getEPackage().getNsURI()))
         {
            addContentElement(all, eObject);
         }            
      }
   }

   void addContentElement(List<IStructureComparator> all, EObject eObject)
   {
      // TODO: dynamic attributes !!!
      all.add(new StructureComparator(eObject, this));
   }

   void addReferences(List<IStructureComparator> all)
   {
      List<EReference> references = model instanceof IModelElement || model instanceof ModelType || model instanceof TypeDeclarationType
         ? model.eClass().getEAllReferences() : Collections.<EReference>emptyList();
      for (EReference reference : references)
      {
         addReference(all, reference);
      }
   }

   void addReference(List<IStructureComparator> all, EReference reference)
   {
      if (!reference.isContainment() && !reference.isMany())
      {
         all.add(new AttributeComparator(model, reference.getName(), this));
      }
   }

   void addAttributes(List<IStructureComparator> all)
   {
      List<EAttribute> attributes = model instanceof IModelElement || model instanceof ModelType || model instanceof TypeDeclarationType
         ? model.eClass().getEAllAttributes() : Collections.<EAttribute>emptyList();
      for (EAttribute attribute : attributes)
      {
         addAttribute(all, attribute);
      }
   }

   private void addAttribute(List<IStructureComparator> all, EAttribute attribute)
   {
      if (!attribute.isMany())
      {
         all.add(new AttributeComparator(model, attribute.getName(), this));
      }
   }

   public IStructureComparator getParent()
   {
      return parent;
   }

   public Image getImage()
   {
      // TODO: add images for description and text types and other things 
      if (model instanceof AttributeType || model instanceof DescriptionType || 
          model instanceof TextType || model instanceof XmlTextNode)
      {
         return DiagramPlugin.getImage("/icons/full/obj16/attribute_obj.gif"); //$NON-NLS-1$
      }
      String icon = IconFactory.getDefault().getIconFor(model);
      return icon == null ? null : DiagramPlugin.getImage(icon);
   }

   public String getName()
   {
      return getName(this.model);
   }

   static String getName(EObject model)
   {
      String name = null;
      if (model instanceof TypeDeclarationType)
      {
         name = ((TypeDeclarationType) model).getName();
      }
      
      if (model instanceof Connection)
      {
         name = ((Connection) model).getName();
      }
      
      if (model instanceof DiagramType)
      {
         name = ((DiagramType) model).getName();
      }
      
      if (model instanceof AttributeType)
      {
         AttributeType attribute = (AttributeType) model;
         StringBuffer buffer = new StringBuffer();
         buffer.append(attribute.getName());
         if (attribute.getType() != null)
         {
            buffer.append('[').append(attribute.getType()).append(']');
         }
         buffer.append('=').append(attribute.getAttributeValue());
         name = buffer.toString();
      }
      
      if (model instanceof ParticipantType)
      {
         name = "participant=" + ((ParticipantType) model).getParticipant().getId(); //$NON-NLS-1$
      }

      if (model instanceof IIdentifiableElement)
      {
         name = ((IIdentifiableElement) model).getId();
      }
      
      if (name == null && model instanceof IModelElement)
      {
         name = model.eClass().getName() + ": " + ((IModelElement) model).getElementOid(); //$NON-NLS-1$
      }
      
      if (name == null)
      {
         // is it text type?
         EStructuralFeature mixed = model.eClass().getEStructuralFeature("mixed"); //$NON-NLS-1$
         if (mixed != null)
         {
            String prefix = model.eContainingFeature() == null
               ? model.eClass().getName() : model.eContainingFeature().getName();
            name = prefix + "=" + ModelUtils.getCDataString((FeatureMap) model.eGet(mixed)); //$NON-NLS-1$
         }
      }
      
      if (name == null)
      {
         EPackage pkg = model.eClass().getEPackage();
         name = pkg.getNsURI().equals(CarnotWorkflowModelPackage.eNS_URI)
            ? getEObjectLabel(model) : pkg.getNsURI() + ":" + model.eClass().getName(); //$NON-NLS-1$
      }
      
      return name;
   }

   private static String getEObjectLabel(EObject model)
   {
      String label = model.toString();
      int idx = label.indexOf('(');
      return idx < 0 ? label : label.substring(idx);
   }

   public String getType()
   {
      return model.eClass().getName();
   }

   public String toString()
   {
      return getName();
   }

   public int hashCode()
   {
      return key.hashCode();
   }

   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      return obj instanceof StructureComparator
         && key.equals(((StructureComparator)obj).key);
   }

   public int getCategory()
   {
      if (!(model instanceof IModelElement))
      {
         return model.eClass().getClassifierID() + 1000;
      }
      return model.eContainingFeature().getFeatureID() + 10000;
   }

   public boolean isEditable()
   {
      return false;
   }

   public ITypedElement replace(ITypedElement dest, ITypedElement src)
   {
      children = null;
      if (dest == null)
      {
         return add(src);
      }
      else if (src == null)
      {
         return remove(dest);
      }
      else
      {
         return copy(dest, src);
      }
   }

   ITypedElement copy(ITypedElement dest, ITypedElement src)
   {
      EObject destModel = getModel(dest);
      EObject srcModel = getModel(src);
      if (dest instanceof AttributeComparator)
      {
         EStructuralFeature feature = destModel.eClass().getEStructuralFeature(((AttributeComparator) dest).attributeName);
         Object value = srcModel.eGet(feature);
         if (value instanceof EObject)
         {
            value = MergeUtil.copy((EObject) value);
         }
         destModel.eSet(feature, value);
      }
      else
      {
         EcoreUtil.replace(destModel, MergeUtil.copy(srcModel));
      }
      return dest;
   }

   EObject getModel(ITypedElement dest)
   {
      return dest instanceof AttributeComparator
         ? ((AttributeComparator) dest).model
         : ((StructureComparator) dest).model;
   }

   ITypedElement remove(ITypedElement dest)
   {
      EObject destModel = getModel(dest);
      if (destModel instanceof LaneSymbol)
      {
         ((LaneSymbol) destModel).setParentLane(null);
         ((LaneSymbol) destModel).setParentPool(null);
      }
      EStructuralFeature feature = destModel.eContainingFeature();
      if (feature.isMany())
      {
         getList(model, feature).remove(destModel);
      }
      else
      {
         if (feature.isUnsettable())
         {
            model.eUnset(feature);
         }
         else
         {
            model.eSet(feature, null);
         }
      }
      return null;
   }

   ITypedElement add(ITypedElement src)
   {
      EObject srcModel = getModel(src);
      EStructuralFeature feature = srcModel.eContainingFeature();
      EObject value = clone(srcModel);
      if (feature.isMany())
      {
         getList(model, feature).add(value);
      }
      else
      {
         model.eSet(feature, value);
      }
      if (value instanceof LaneSymbol)
      {
         ((LaneSymbol) value).setParentLane((ISwimlaneSymbol) model);
         ((LaneSymbol) value).setParentPool(getPool());
      }
      return new StructureComparator(value, this);
   }

   EObject clone(EObject model)
   {
      return MergeUtil.copyAdd(model);
   }

   @SuppressWarnings("unchecked")
   <T extends EObject> List<T> getList(EObject model, EStructuralFeature feature)
   {
      return (List<T>) model.eGet(feature);
   }

   private PoolSymbol getPool()
   {
      if (model instanceof PoolSymbol)
      {
         return (PoolSymbol) model;
      }
      if (model instanceof LaneSymbol)
      {
         return ((LaneSymbol) model).getParentPool();
      }
      return null;
   }

   public void setContent(byte[] newContent)
   {
      // intentionally left empty
   }
}