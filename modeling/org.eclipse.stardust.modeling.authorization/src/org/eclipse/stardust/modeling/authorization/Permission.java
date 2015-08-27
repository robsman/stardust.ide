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
package org.eclipse.stardust.modeling.authorization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.engine.core.runtime.utils.Authorization2;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class Permission
{
   public static final String SCOPE = Constants.SCOPE + Constants.SCOPE_SEPARATOR;
   
   private String id;
   private String name;

   private boolean isALL = false;
   private boolean isOWNER = false;
   private ArrayList<ParticipantReference> participants = new ArrayList<ParticipantReference>();

   private boolean defaultALL = false;
   private boolean defaultOWNER = false;
   private ArrayList<IModelParticipant> defaultParticipants = new ArrayList<IModelParticipant>();

   private boolean fixedALL = false;
   private boolean fixedOWNER = false;
   private ArrayList<IModelParticipant> fixedParticipants = new ArrayList<IModelParticipant>();

   public Permission(IConfigurationElement config, IExtensibleElement element)
   {
      id = config.getAttribute(Constants.SCOPE_ATTRIBUTE) + "." + config.getAttribute(Constants.ID_ATTRIBUTE); //$NON-NLS-1$
      name = config.getAttribute(Constants.NAME_ATTRIBUTE);
      
      String baseName = SCOPE + getId();
      List<AttributeType> attributes = element.getAttribute();
      boolean isSet = false;
      for (int i = 0; i < attributes.size(); i++)
      {
         AttributeType attribute = attributes.get(i);
         if (attribute.getName().startsWith(baseName))
         {
            isSet = true;
            if (Authorization2.ALL.equals(attribute.getValue()))
            {
               isALL = true;
            }
            else if (Authorization2.OWNER.equals(attribute.getValue()))
            {
               isOWNER = true;
            }
            else
            {
               EObject referenceElement = AttributeUtil.getReferenceElement(attribute);
               if (referenceElement instanceof IModelParticipant)
               {
                  participants.add(new ParticipantReference(this, (IModelParticipant) referenceElement));
               }
            }
         }
      }
      ModelType model = ModelUtils.findContainingModel(element);
      String defaultParticipantName = config.getAttribute(Constants.DEFAULT_PARTICIPANT_ATTRIBUTE);
      if (defaultParticipantName != null)
      {
         if (Constants.ALL_PARTICIPANT.equals(defaultParticipantName))
         {
            defaultALL = true;
         }
         else if (Constants.OWNER_PARTICIPANT.equals(defaultParticipantName))
         {
            defaultOWNER = true;
         }
         else
         {
            IModelParticipant participant = (IModelParticipant) ModelUtils.findIdentifiableElement(
                  model.getRole(), defaultParticipantName);
            if (participant == null)
            {
               participant = (IModelParticipant) ModelUtils.findIdentifiableElement(
                     model.getOrganization(), defaultParticipantName);
            }
            if (participant != null)
            {
               defaultParticipants.add(participant);
            }
         }
      }
      String fixedParticipantName = config.getAttribute(Constants.FIXED_PARTICIPANT_ATTRIBUTE);
      if (fixedParticipantName != null)
      {
         if (Constants.ALL_PARTICIPANT.equals(fixedParticipantName))
         {
            fixedALL = true;
         }
         else if (Constants.OWNER_PARTICIPANT.equals(fixedParticipantName))
         {
            fixedOWNER = true;
         }
         else
         {
            IModelParticipant participant = (IModelParticipant) ModelUtils.findIdentifiableElement(
                  model.getRole(), fixedParticipantName);
            if (participant == null)
            {
               participant = (IModelParticipant) ModelUtils.findIdentifiableElement(
                     model.getOrganization(), fixedParticipantName);
            }
            if (participant != null)
            {
               fixedParticipants.add(participant);
            }
         }
      }
      if (!isSet)
      {
         restoreDefaults();
      }
   }

   public String getName()
   {
      return name;
   }

   public String getId()
   {
      return id;
   }
   
   public String toString()
   {
	  return getName() + " (" + getId() + ')'; //$NON-NLS-1$
   }

   public boolean contains(IModelParticipant participant)
   {
      for (IModelParticipant reference : fixedParticipants)
      {
         if (participant == reference)
         {
            return true;
         }
      }
      return find(participant) != null;
   }

   public void removeParticipant(IModelParticipant participant)
   {
      ParticipantReference reference = find(participant);
      if (reference != null)
      {
         participants.remove(reference);
      }
   }

   private ParticipantReference find(IModelParticipant participant)
   {
      for (ParticipantReference reference : participants)
      {
         if (participant == reference.getParticipant())
         {
            return reference;
         }
      }
      return null;
   }

   public void setALL()
   {
      isALL = true;
      participants.clear();
   }

   public void setOWNER()
   {
      isALL = false;
      isOWNER = true;
   }

   public void unsetALL()
   {
      isALL = false;
   }

   public void unsetOWNER()
   {
      isOWNER = false;
   }

   public boolean isALL()
   {
      return isALL || fixedALL;
   }
   
   public boolean isOWNER()
   {
      return isOWNER || fixedOWNER;
   }
   
   public boolean isFixedOWNER()
   {
      return fixedOWNER;
   }

   
   /**
    * Should be invoked on clean state, since this method adds new attributes without touching existing ones.
    * 
    * @param element
    */
   public void save(IExtensibleElement element)
   {
//      defaultALL = isALL;
//      defaultOWNER = isOWNER;
//      defaultParticipants.clear();
      String baseName = SCOPE + getId();
      if (isALL)
      {
         createAttribute(element, baseName, Authorization2.ALL);
      }
      else
      {
         if (isOWNER)
         {
            createAttribute(element, baseName, Authorization2.OWNER);
         }
         for (int i = 0; i < participants.size(); i++)
         {
            String attrName = baseName + '[' + i + ']';
            ParticipantReference reference = (ParticipantReference) participants.get(i);
            if (reference.getParticipant() != null)
            {
               createAttribute(element, attrName, reference.getParticipant());
//               defaultParticipants.add(reference.getParticipant());
            }
         }
      }
   }

   private void createAttribute(IExtensibleElement element, String attrName, String value)
   {
      AttributeType attribute = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
      attribute.setName(attrName);
      attribute.setValue(value);
      element.getAttribute().add(attribute);
   }

   private void createAttribute(IExtensibleElement element, String attrName, IModelParticipant participant)
   {
      AttributeType attribute = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
      attribute.setName(attrName);
      element.getAttribute().add(attribute);
      AttributeUtil.setReference(attribute, participant);
   }

   public void restoreDefaults()
   {
      isALL = defaultALL;
      isOWNER = defaultOWNER;
      participants.clear();
      for (IModelParticipant participant : defaultParticipants)
      {
         participants.add(new ParticipantReference(this, (IModelParticipant) participant));
      }      
   }

   public boolean isEmpty()
   {
      return participants.isEmpty() && !isOWNER;
   }

   public void addParticipant(IModelParticipant participant)
   {
      isALL = false;
      participants.add(new ParticipantReference(this, participant));
   }

   public boolean isDefaultAll()
   {
      return defaultALL || fixedALL;
   }

   public boolean isDefaultOwner()
   {
      return defaultOWNER || fixedOWNER;
   }

   public boolean isDefault(IModelParticipant participant)
   {
      for (IModelParticipant reference : fixedParticipants)
      {
         if (participant == reference)
         {
            return true;
         }
      }
      return defaultParticipants.contains(participant);
   }

   public void setDefault(IModelParticipant participant)
   {
	  defaultALL = false;
	  defaultOWNER = false;
	  defaultParticipants.clear();
	  defaultParticipants.add(participant);
   }
   
   public List<IModelParticipant> getDefaultParticipants()
   {
      return defaultParticipants;
   }
   
   public List<IModelParticipant> getFixedParticipants()
   {
      return fixedParticipants;
   }
   
   public List<IModelParticipant> getParticipants()
   {
      List<IModelParticipant> result = new ArrayList<IModelParticipant>();
      for (Iterator<ParticipantReference> i = participants.iterator(); i.hasNext();)
      {
         ParticipantReference reference = i.next();
         if (null != reference.getParticipant())
         {
            result.add(reference.getParticipant());
         }
      }
      return result;
   }
      
   }