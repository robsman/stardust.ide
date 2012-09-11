/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.model.xpdl.builder.utils;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.xsd.XSDSchema;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ElementCopier
{
   public static final CarnotWorkflowModelFactory F_CWM = CarnotWorkflowModelFactory.eINSTANCE;   
   
   private ModelType target;
   private ModelType source;

   public ElementCopier(ModelType target, ModelType source)
   {
      this.target = target;
      this.source = source;
   }   

   public EObject copy(EObject object)
   {      
      Copier copier = new Copier();
      
      if(object instanceof DataType)
      {
         DataType copy = (DataType) copier.copy(object);      
         DataTypeType type = ((DataType) object).getType();
         DataTypeType targetType = (DataTypeType) findInTarget(type);
         if(targetType != null)
         {
            copy.setType(targetType);
         }
         
         copier.copyReferences();
         
         target.getData().add((DataType) copy);
         
         return copy;
      }
      else if(object instanceof IModelParticipant)
      {
         IModelParticipant copy = (IModelParticipant) copier.copy(object);         
         copier.copyReferences();
         
         if(object instanceof RoleType)
         {
            target.getRole().add((RoleType) copy);            
         }
         else if(object instanceof OrganizationType)
         {
            target.getOrganization().add((OrganizationType) copy);            
         }
         else if(object instanceof ConditionalPerformerType)
         {
            target.getConditionalPerformer().add((ConditionalPerformerType) copy);            
         }
         
         return copy;
         
      }
      
      
      return null;
   }
   
   EObject findInTarget(EObject object)
   {
      if(object instanceof DataTypeType)
      {
         EList<DataTypeType> dataType = target.getDataType();
         for (DataTypeType dataTypeType : dataType)
         {
            if(dataTypeType.getId().equals(((DataTypeType) object).getId()))
            {
               return dataTypeType;
            }            
         }
      }
      
      return null;      
   }
    
   class Copier extends EcoreUtil.Copier
   {
      private static final long serialVersionUID = 1L;

      public EObject copy(EObject object)
      {
         if(object instanceof XSDSchema)
         {
            XSDSchema original = (XSDSchema) object;
            XSDSchema clone = (XSDSchema) original.cloneConcreteComponent(true, false);
            Document doc = clone.updateDocument();
            if(original.getElement() != null)
            {            
               Element clonedElement = (Element) doc.importNode(original.getElement(), true);
               doc.appendChild(clonedElement);
               clone.setElement(clonedElement);
            }
            return clone;                     
         }
         return super.copy(object);
      }               
   };                       
}