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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.compare.BufferedContent;
import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.DocumentRoot;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.swt.graphics.Image;

public class ComparableModelElementNode extends BufferedContent
      implements IStructureComparator, IStreamContentAccessor, IEditableContent,
      ITypedElement
{
   private static final String ZEROS = "0000"; //$NON-NLS-1$

   private EObject eObject;

   private ETypedElement typedElement;

   private ArrayList children;

   public ComparableModelElementNode(EObject eObject)
   {
      this.eObject = eObject;
   }

   public ComparableModelElementNode(ETypedElement typedElement, EObject eObject)
   {
      this(eObject);
      this.typedElement = typedElement;
   }

   public ComparableModelElementNode getParent()
   {
      EObject parent = null;

      if (eObject != null)
      {
         parent = this.eObject.eContainer();
      }

      return parent != null ? new ComparableModelElementNode(parent) : null;
   }

   public boolean hasChildren()
   {
      return getChildren().length > 0 ? true : false;
   }

   public Object[] getChildren()
   {
      children = new ArrayList();

      if (eObject != null && typedElement == null)
      {
         // looking for attributes
         for (Iterator _attributeIterator = getAttributes(eObject).iterator(); _attributeIterator
               .hasNext();)
         {
            EStructuralFeature eStructuralFeature = (EStructuralFeature) _attributeIterator
                  .next();

            if (eStructuralFeature instanceof EAttribute
                  || eStructuralFeature instanceof EReference)
            {
               if (eObject.eGet(eStructuralFeature) != null
                     && !(eObject.eGet(eStructuralFeature) instanceof EcoreEMap))
               {
                  IStructureComparator child = createChild(eStructuralFeature, eObject);

                  if (child != null)
                  {
                     children.add(child);
                  }
               }
            }
         }

         if (eObject instanceof XmlTextNode)
         {
            EAttribute mixedFeature = ExtendedMetaData.INSTANCE.getMixedFeature(eObject
                  .eClass());

            if (mixedFeature != null)
            {
               Object feature = eObject.eGet(mixedFeature);

               if (feature instanceof BasicFeatureMap)
               {
                  IStructureComparator child = createChild(mixedFeature, eObject);

                  if (child != null)
                  {
                     children.add(child);
                  }
               }
            }
         }

         for (Iterator _iterator = eObject.eContents().iterator(); _iterator.hasNext();)
         {
            IStructureComparator child = createChild((EObject) _iterator.next());
            if (child != null)
            {
               children.add(child);
            }
         }
      }

      return children.toArray();
   }

   protected IStructureComparator createChild(EObject child)
   {
      if (child instanceof EStringToStringMapEntryImpl)
      {
         return null;
      }
      return new ComparableModelElementNode(child);
   }

   protected IStructureComparator createChild(ETypedElement child, EObject parent)
   {
      return new ComparableModelElementNode(child, parent);
   }

   public EObject getEObject()
   {
      return eObject;
   }

   /*
    * (non Javadoc) see ITypedElement.getName
    */
   public String getName()
   {
      return getElementName();
   }

   /*
    * (non Javadoc) see ITypedElement.getType
    */
   public String getType()
   {
      return ITypedElement.UNKNOWN_TYPE;
   }

   /*
    * (non Javadoc) see ITypedElement.getImage
    */
   public Image getImage()
   {
      Image image = null;

      if (eObject != null && typedElement == null)
      {
         String iconPath = IconFactory.getDefault().getIconFor(eObject);
         image = DiagramPlugin.getImage(iconPath);
      }
      else if (eObject != null && typedElement != null)
      {
         if (eObject.eGet((EStructuralFeature) typedElement) instanceof EObject)
         {
            String iconPath = IconFactory.getDefault().getIconFor((EObject) eObject
                  .eGet((EStructuralFeature) typedElement));
            image = DiagramPlugin.getImage(iconPath);
         }
         else
         {
            image = DiagramPlugin.getImage("/icons/full/obj16/attribute_obj.gif"); //$NON-NLS-1$
         }
      }

      return image;
   }

   public boolean equals(Object other)
   {
      if (other instanceof ITypedElement && getElementIdentifier() != null)
      {
         return getElementIdentifier().equals(
               ((ComparableModelElementNode) other).getElementIdentifier());
      }
      return super.equals(other);
   }

   public int hashCode()
   {
      if (getElementIdentifier() != null)
      {
         return getElementIdentifier().hashCode();
      }
      return super.hashCode();
   }

   /**
    * Method creates a stringified representation of the value of an attribute and returns
    * it as an InputStream.
    */
   protected InputStream createStream() throws CoreException
   {
      String stringifiedValue = null;

      if (eObject instanceof DescriptionType)
      {
         stringifiedValue = getDescriptionText((DescriptionType) eObject);
      }
      else
      {
         if (typedElement != null && !isIgnorable())
         {
            Object value = eObject.eGet((EStructuralFeature) typedElement);
            if (value != null)
            {
               if (value instanceof BasicFeatureMap)
               {
                  stringifiedValue = getCDataString((BasicFeatureMap) value);
               }
               else
               {
                  if (typedElement instanceof EReference)
                  {
                     value = this.getElementIdentifier();
                  }

                  StringBuffer strBuffer = new StringBuffer();
                  strBuffer.append(value);
                  stringifiedValue = strBuffer.toString();
               }
            }
         }
      }

      if (stringifiedValue == null)
      {
         stringifiedValue = new String();
      }

      return new ByteArrayInputStream(stringifiedValue.getBytes());
   }

   private List getAttributes(EObject eObject)
   {
      return ExtendedMetaData.INSTANCE.getAllAttributes(eObject.eClass());
   }

   /**
    * Method returns a name wether for an eObject or an eAttribute child
    * 
    * @return name of the element
    */
   private String getElementName() // TODO elementOid is a not very good solution
   {
      if (eObject != null && typedElement == null)
      {
         // get the name of the element if any exists
         for (Iterator elements = getAttributes(eObject).iterator(); elements.hasNext();)
         {
            EStructuralFeature eStructFeature = (EStructuralFeature) elements.next();
            if (eStructFeature.getName().equals("name")) //$NON-NLS-1$
            {
               String name = (String) eObject.eGet(eStructFeature);
               if (name != null)
               {
                  return name;
               }
            }
         }

         // otherwise try to get the elementOid value
         for (Iterator elements = getAttributes(eObject).iterator(); elements.hasNext();)
         {
            EStructuralFeature eStructFeature = (EStructuralFeature) elements.next();
            if (eStructFeature.getName().equals("elementOid")) //$NON-NLS-1$
            {
               String name = Reflect.getHumanReadableClassName(eObject.eClass()
                     .getInstanceClass());

               Long value = (Long) eObject.eGet(eStructFeature);
               name = name + " : " + value; //$NON-NLS-1$
               return name;
            }
         }
      }
      else
      {

         String value = null;
         if (typedElement instanceof EReference)
         {
            Object refObject = eObject.eGet((EStructuralFeature) typedElement);

            if (refObject instanceof EObject)
            {
               EObject refEObject = (EObject) refObject;
               for (Iterator elements = getAttributes(refEObject).iterator(); elements
                     .hasNext();)
               {
                  EStructuralFeature eStructFeature = (EStructuralFeature) elements
                        .next();
                  if (eStructFeature.getName().equals("name")) //$NON-NLS-1$
                  {
                     String name = (String) refEObject.eGet(eStructFeature);
                     if (name != null)
                     {
                        value = typedElement.getName() + " : " + name; //$NON-NLS-1$
                     }
                  }
               }

               if (value == null)
               {
                  for (Iterator elements = getAttributes(refEObject).iterator(); elements
                        .hasNext();)
                  {
                     EStructuralFeature eStructFeature = (EStructuralFeature) elements
                           .next();
                     if (eStructFeature.getName().equals("elementOid")) //$NON-NLS-1$
                     {
                        String name = Reflect.getHumanReadableClassName(refEObject
                              .eClass().getInstanceClass());
                        value = name + " : " + refEObject.eGet(eStructFeature);//$NON-NLS-1$
                     }
                  }
               }
            }
         }
         else
         {
            value = typedElement.getName() + " : " //$NON-NLS-1$
                  + eObject.eGet((EStructuralFeature) typedElement);
         }

         Object feature = eObject.eGet((EStructuralFeature) typedElement);

         if (feature instanceof BasicFeatureMap)
         {
            value = convert(getCDataString((FeatureMap) feature));
         }

         return value;
      }

      if (eObject instanceof DescriptionType)
      {
         return convert(getDescriptionText((DescriptionType) eObject));
      }

      if (eObject instanceof XmlTextNode)
      {
         return Reflect.getHumanReadableClassName(eObject.eClass().getInstanceClass());
      }

      if (eObject instanceof ParticipantType)
      {
         return "participant"; // TODO //$NON-NLS-1$
      }

      if (eObject instanceof DocumentRoot)
      {
         return Diagram_Messages.ComparableModelElementNode_root;
      }

      return Diagram_Messages.ComparableModelElementNode_noName;
   }

   private String convert(String text)
   {
      StringBuffer sb = new StringBuffer();
      sb.append("\""); //$NON-NLS-1$
      for (int i = 0; i < text.length(); i++)
      {
         char c = text.charAt(i);
         if (Character.isISOControl(c))
         {
            sb.append(getHumanReadableForm(c));
         }
         else
         {
            sb.append(c);
         }
      }
      sb.append("\""); //$NON-NLS-1$
      return sb.toString();
   }

   private String getHumanReadableForm(char c)
   {
      int b = c;
      String hex = Integer.toHexString(b);
      return "\\0x" + ZEROS.substring(0, 4 - hex.length()) + Integer.toHexString(b); //$NON-NLS-1$
   }

   private String getCDataString(FeatureMap map)
   {
      String text = ModelUtils.getCDataString((FeatureMap) map);
      return text == null ? "" : text; //$NON-NLS-1$
   }

   private String getDescriptionText(DescriptionType type)
   {
      String text = ModelUtils.getDescriptionText((DescriptionType) eObject);

      return text == null ? "" : text.trim(); //$NON-NLS-1$
   }

   /**
    * Method returns the identifier of a model element by getting the elementOID, oid, id
    * or name attribute.
    * 
    * @return
    */
   public Object getElementIdentifier()
   {
      Object identifier = null;

      // ignore model element
      if (eObject instanceof ModelType)
      {
         return ""; //$NON-NLS-1$
      }

      if (eObject != null && typedElement == null)
      {
         // data mappings have to be identified by id, direction and context, because id
         // can be the same for in and out data mapping -> conflicts when importing model
         // elements
         if (eObject instanceof DataMappingType)
         {
            DataMappingType dataMapping = (DataMappingType) eObject;
            identifier = dataMapping.getId() + "_" + dataMapping.getDirection().getName() //$NON-NLS-1$
               + "_" + dataMapping.getContext(); //$NON-NLS-1$ 
         }
         // data mappings have to be identified by id, direction and context, because id
         // can be the same for in and out data mapping -> conflicts when importing model
         // elements
         else if (eObject instanceof DataPathType)
         {
            DataPathType dataPath = (DataPathType) eObject;
            identifier = dataPath.getId() + "_" + dataPath.getDirection().getName(); //$NON-NLS-1$
         }
         else if (eObject instanceof DiagramType)
         {
            identifier = ((DiagramType) eObject).getName();
         }
         // TODO restore if possible identification of pools/lanes by id
         // pools/lanes have to be identified by elementOid, because id/name may be null
         else if (eObject instanceof IIdentifiableElement)
         {
            identifier = ((IIdentifiableElement) eObject).getId();
         }
         else if (eObject instanceof IModelElement)
         {
            identifier = String.valueOf(((IModelElement) eObject).getElementOid());
         }
      }
      else if (typedElement != null) // TODO
      {
         if (typedElement instanceof EReference)
         {
            Object refObject = eObject.eGet((EStructuralFeature) typedElement);

            if (refObject instanceof EObject)
            {
               ComparableModelElementNode node = new ComparableModelElementNode(
                     (EObject) refObject);
               identifier = node.getElementIdentifier();
            }
         }
         else
         {
            identifier = typedElement.getName(); // shlould be unique wihtin its context
         }
      }

      if (identifier == null)
      {
         identifier = eObject != null ? eObject.eClass().getName() : null;
      }
      else
      {
         identifier = eObject != null ? "" + identifier + ":" //$NON-NLS-1$ //$NON-NLS-2$
               + eObject.eClass().getClassifierID() : ""; //$NON-NLS-1$
      }

      return identifier;
   }

   public boolean isEditable()
   {
      return true;
   }

   /**
    * This method is called on a parent to add or remove a child, or to copy the contents
    * a child. src : parent dest : child
    */
   public ITypedElement replace(ITypedElement dest, ITypedElement src)
   {
      if (dest != null && src != null) // TODO description copying
      {
         // copy (this: parent, dest: old value, src: new value)
         copyModelElementValue((ComparableModelElementNode) src,
               (ComparableModelElementNode) dest);
      }
      else if (dest != null && src == null)
      {
         // remove
         removeModelElement((ComparableModelElementNode) dest);
      }
      else if (dest == null && src != null)
      {
         // add the 'source' node to the 'this' parent
         addModelElement((ComparableModelElementNode) src);
      }
      return dest;
   }

   public void setContent(byte[] contents)
   {
      super.setContent(contents);
   }

   public ETypedElement getETypedElement()
   {
      return typedElement;
   }

   /**
    * Method copies attribute values from one to an other node.
    * 
    * @param src
    * @param dest
    */
   private void copyModelElementValue(ComparableModelElementNode src,
         ComparableModelElementNode dest)
   {
      if (dest.getETypedElement() != null && src.getETypedElement() != null)
      {
         dest.getEObject().eSet((EStructuralFeature) dest.getETypedElement(),
               src.getEObject().eGet((EStructuralFeature) src.getETypedElement()));
      }
      else
      {
         // handling for description types
         if (src.getEObject().eContainingFeature() != null
               && dest.getEObject().eContainingFeature() != null)
         {
            eObject.eUnset(dest.getEObject().eContainingFeature());
            Object value = EcoreUtil.copy(src.getEObject());
            eObject.eSet(src.getEObject().eContainingFeature(), value);
         }
      }
   }

   /**
    * Method removes an element from a parent node.
    * 
    * @param dest
    */
   private void removeModelElement(ComparableModelElementNode dest)
   {
      EObject destinationEObject = dest.getEObject();

      Object feature = null;

      if (dest.getETypedElement() != null)
      {
         feature = dest.getETypedElement();
      }
      else
      {
         EStructuralFeature containingFeature = destinationEObject.eContainingFeature();
         feature = eObject.eGet(containingFeature);
         if (!(feature instanceof EStructuralFeature || feature instanceof List))
         {
            feature = null;
         }
      }

      // remove from list
      if (feature != null)
      {
         if (feature instanceof List)
         {
            ((List) feature).remove(destinationEObject);
            if (destinationEObject instanceof LaneSymbol)
            {
               if (eObject instanceof ISwimlaneSymbol)
               {
                  ((LaneSymbol) destinationEObject).setParentLane(null);
                  ((LaneSymbol) destinationEObject).setParentPool(null);
               }
            }
         }
         else
         {
            eObject.eUnset((EStructuralFeature) feature);
         }
      }
      else
      { // handling for description types
         if (destinationEObject.eContainingFeature() != null)
         {
            eObject.eUnset(destinationEObject.eContainingFeature());
         }
      }
   }

   /**
    * Method adds an element by copying it from one node to an other node.
    * 
    * @param src
    */
   private void addModelElement(ComparableModelElementNode src)
   {
      EObject sourceEObject = src.getEObject();

      Object feature = null;
      Object value = null;

      if (src.getETypedElement() != null)
      {
         // node is a leaf node
         feature = src.getETypedElement();
         value = sourceEObject.eGet((EStructuralFeature) feature);
      }
      else
      {
         feature = eObject.eGet(sourceEObject.eContainingFeature());
         if (!(feature instanceof EStructuralFeature || feature instanceof List))
         {
            feature = null;
         }

         // need to copy the node
         value = CopyUtil.copy(sourceEObject, this.getEObject());
      }

      if (feature != null)
      {
         if (feature instanceof List)
         {
            ((List) feature).add(value);
            if (value instanceof LaneSymbol)
            {
               if (eObject instanceof ISwimlaneSymbol)
               {
                  ((LaneSymbol) value).setParentLane((ISwimlaneSymbol) eObject);
                  ((LaneSymbol) value).setParentPool(getPool(eObject));
               }
            }
         }
         else
         {
            eObject.eSet((EStructuralFeature) feature, value);
         }
      }
      else
      { // handling for description types
         if (sourceEObject.eContainingFeature() != null)
         {
            eObject.eSet(sourceEObject.eContainingFeature(), value);
         }
      }
   }

   private PoolSymbol getPool(EObject object)
   {
      if (eObject instanceof PoolSymbol)
      {
         return (PoolSymbol) eObject;
      }
      if (eObject instanceof LaneSymbol)
      {
         return ((LaneSymbol) eObject).getParentPool();
      }
      return null;
   }

   /**
    * Method shows if a leaf element could be ignored during the compare process
    * 
    * @return
    */
   private boolean isIgnorable()
   {
      return (eObject != null && typedElement != null
            && ((eObject instanceof IIdentifiableElement) || (eObject instanceof DataType)) && "elementOid" //$NON-NLS-1$
      .equals(typedElement.getName()));
   }

   public String toString()
   {
      return getName();
   }
}
