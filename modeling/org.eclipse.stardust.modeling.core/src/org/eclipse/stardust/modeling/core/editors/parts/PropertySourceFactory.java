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
package org.eclipse.stardust.modeling.core.editors.parts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LoopType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TextSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.TextType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ActivityCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.properties.AnyTextCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ApplicationCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.properties.BoundEObjectPropertyId;
import org.eclipse.stardust.modeling.core.editors.parts.properties.CmdFactoryPropertyDescriptorDecorator;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ConnectionRoutingCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.properties.DataCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.properties.DefaultPropSheetCmdFactory;
import org.eclipse.stardust.modeling.core.editors.parts.properties.EObjectPropertySource;
import org.eclipse.stardust.modeling.core.editors.parts.properties.IPropSheetCmdFactory;
import org.eclipse.stardust.modeling.core.editors.parts.properties.LaneParticipantCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.properties.UndoablePropSheetEntry;
import org.eclipse.stardust.modeling.core.ui.EEnumPropertyDescriptor;
import org.eclipse.stardust.modeling.core.ui.ModelElementListPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;


public class PropertySourceFactory
{
   public static final String CATEGORY_BASE = Diagram_Messages.CATEGORY_BASE;

   public static final String CATEGORY_DIAGRAM = Diagram_Messages.CATEGORY_DIAGRAM;

   public static final String CATEGORY_ALL = Diagram_Messages.CATEGORY_ALL;

   private static final IPropertyDescriptor[] EMPTY_PROP_DESC_ARRAY = new IPropertyDescriptor[0];
   private static IPropSheetCmdFactory nameCommandFactoryInstance = new DefaultPropSheetCmdFactory()
   {
      public Command createSetValueCommand(UndoablePropSheetEntry entry,
            IPropertyDescriptor descriptor, IPropertySource target, Object value)
      {
         if (null == value)
         {
            return createResetValueCommand(descriptor, target);
         }
         else
         {
            CompoundCommand result = new CompoundCommand(descriptor.getDisplayName());
            result.add(super.createSetValueCommand(entry, descriptor, target, value));
            addUpdateCommand(result, (BoundEObjectPropertyId) descriptor.getId(), target, value);
            return result.unwrap();
         }
      }

      public Command createResetValueCommand(IPropertyDescriptor descriptor,
            IPropertySource target)
      {
         CompoundCommand result = new CompoundCommand(descriptor.getDisplayName());
         result.add(super.createResetValueCommand(descriptor, target));
         addUpdateCommand(result, (BoundEObjectPropertyId) descriptor.getId(), target, null);
         return result.unwrap();
      }

      private void addUpdateCommand(CompoundCommand result, BoundEObjectPropertyId id,
                                    IPropertySource target, Object value)
      {
         result.add(new SetValueCmd((IIdentifiableElement) target.getEditableValue(),
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(),
            ModelUtils.computeId((String) value)));
      }
   };

   public static IPropertySource getPropertySource(EditPart part, EObject object)
   {
      IPropertySource propSource = null;

      if (object instanceof IModelElementNodeSymbol)
      {
         final IIdentifiableModelElement model = ((IModelElementNodeSymbol) object)
               .getModelElement();
         if (null != model)
         {
            List descriptors = new ArrayList();
            addBasicProperties(part, model, descriptors);
            addSymbolProperties(part, object, descriptors);
            addOtherEmfDescriptors(part, model, descriptors);

            propSource = new EObjectPropertySource(model,
                  (IPropertyDescriptor[]) descriptors.toArray(EMPTY_PROP_DESC_ARRAY));
         }
      }

      if (null == propSource)
      {
         List descriptors = new ArrayList();
         addBasicProperties(part, object, descriptors);
         addSymbolProperties(part, object, descriptors);
         addOtherEmfDescriptors(part, object, descriptors);

         propSource = new EObjectPropertySource(object,
               (IPropertyDescriptor[]) descriptors.toArray(EMPTY_PROP_DESC_ARRAY));
      }

      return propSource;
   }

   public static IPropertySource getSimplePropertySource(EditPart part, EObject model,
         EStructuralFeature feature)
   {
      List descriptors = new ArrayList();
      descriptors.add(new PropertyDescriptor(new BoundEObjectPropertyId(model, feature,
            part), feature.getName()));
      return new EObjectPropertySource(model, (IPropertyDescriptor[]) descriptors
            .toArray(EMPTY_PROP_DESC_ARRAY));
   }

   private static void addBasicProperties(EditPart part, EObject object, List descriptors)
   {
      final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;

      if (object instanceof IModelElement)
      {
         PropertyDescriptor elementOidDescr = new PropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getIModelElement_ElementOid(),
                     part), Diagram_Messages.DISPL_NAME_ElementOID);
         elementOidDescr.setCategory(CATEGORY_BASE);
         descriptors.add(elementOidDescr);
      }

      if (object instanceof IIdentifiableElement)
      {
         TextPropertyDescriptor idDescr = new TextPropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getIIdentifiableElement_Id(),
                     part), Diagram_Messages.DISPL_NAME_ID);
         idDescr.setCategory(CATEGORY_BASE);
         descriptors.add(idDescr);

         TextPropertyDescriptor nameDescr = new TextPropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getIIdentifiableElement_Name(),
                     part), Diagram_Messages.DISPL_NAME_Name);
         PropertyDescriptor composite = CmdFactoryPropertyDescriptorDecorator.create(
               nameDescr, nameCommandFactoryInstance);
         composite.setCategory(CATEGORY_BASE);
         descriptors.add(composite);
      }

      if (object instanceof ActivityType)
      {
         PropertyDescriptor joinDescr = new ModelElementListPropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getActivityType_Join(), part),
               Diagram_Messages.DISPL_NAME_JoinMode, Arrays.asList(new JoinSplitType[] {
                     JoinSplitType.NONE_LITERAL, JoinSplitType.XOR_LITERAL,
                     JoinSplitType.AND_LITERAL}));
         PropertyDescriptor composite = CmdFactoryPropertyDescriptorDecorator.create(
               joinDescr, ActivityCommandFactory.INSTANCE);
         composite.setCategory(Diagram_Messages.CATEGEGORY_FlowControl);
         descriptors.add(composite);

         PropertyDescriptor splitDescr = new ModelElementListPropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getActivityType_Split(), part),
               Diagram_Messages.DISPL_NAME_SplitMode, Arrays.asList(new JoinSplitType[] {
                     JoinSplitType.NONE_LITERAL, JoinSplitType.XOR_LITERAL,
                     JoinSplitType.AND_LITERAL}));
         composite = CmdFactoryPropertyDescriptorDecorator.create(splitDescr,
               ActivityCommandFactory.INSTANCE);
         composite.setCategory(Diagram_Messages.CATEGEGORY_FlowControl);
         descriptors.add(composite);

         PropertyDescriptor loopDescr = new ModelElementListPropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getActivityType_LoopType(),
                     part), Diagram_Messages.DISPL_NAME_LoopMode, Arrays
                     .asList(new LoopType[] {
                           LoopType.NONE_LITERAL, LoopType.WHILE_LITERAL,
                           LoopType.REPEAT_LITERAL}));
         loopDescr.setCategory(Diagram_Messages.CATEGORY_FlowControl);
         descriptors.add(loopDescr);

         PropertyDescriptor loopCondDescr = new PropertyDescriptor(
               new BoundEObjectPropertyId(object,
                     CWM_PKG.getActivityType_LoopCondition(), part),
               Diagram_Messages.DISPL_NAME_LoopCondition);
         loopCondDescr.setCategory(Diagram_Messages.CATEGORY_FlowControl);
         descriptors.add(loopCondDescr);

         PropertyDescriptor implementationDescr = new ModelElementListPropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG
                     .getActivityType_Implementation(), part),
               Diagram_Messages.DISPL_NAME_Implementation,
               ActivityImplementationType.VALUES);
         composite = CmdFactoryPropertyDescriptorDecorator.create(implementationDescr,
               ActivityCommandFactory.INSTANCE);
         composite.setCategory(CATEGORY_BASE);
         descriptors.add(composite);
      }

      if (object instanceof LaneSymbol)
      {
         LaneSymbol lane = (LaneSymbol) object;
         ModelType model = ModelUtils.findContainingModel(lane);
         ArrayList participants = new ArrayList();
         participants.addAll(model.getRole());
         participants.addAll(model.getOrganization());
         participants.addAll(model.getConditionalPerformer());
         PropertyDescriptor participantDescr = new ModelElementListPropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG
                     .getISwimlaneSymbol_Participant(), part),
               Diagram_Messages.DISPL_NAME_Participant, participants);
         PropertyDescriptor composite = CmdFactoryPropertyDescriptorDecorator.create(
               participantDescr, LaneParticipantCommandFactory.INSTANCE);
         composite.setCategory(CATEGORY_BASE);
         descriptors.add(composite);
      }

      if (object instanceof TextSymbolType)
      {
         TextPropertyDescriptor idDescr = new TextPropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getTextSymbolType_Text(), part),
               Diagram_Messages.DISPL_NAME_Text);
         idDescr.setCategory(CATEGORY_BASE);
         descriptors.add(idDescr);
      }

      if (object instanceof DataType)
      {
         ModelType model = (ModelType) object.eContainer();
         PropertyDescriptor typeDescr = new ModelElementListPropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getDataType_Type(), part),
               Diagram_Messages.LB_PropertyType, model.getDataType());  
         PropertyDescriptor composite = CmdFactoryPropertyDescriptorDecorator.create(
               typeDescr, DataCommandFactory.INSTANCE);
         composite.setCategory(Diagram_Messages.CATEGORY_BASE);
         descriptors.add(composite);
   }

      if (object instanceof ApplicationType && !((ApplicationType) object).isInteractive())
      {
         ModelType model = (ModelType) object.eContainer();
         PropertyDescriptor typeDescr = new ModelElementListPropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getApplicationType_Type(), part),
               Diagram_Messages.LB_PropertyType, model.getApplicationType());  
         PropertyDescriptor composite = CmdFactoryPropertyDescriptorDecorator.create(
               typeDescr, ApplicationCommandFactory.INSTANCE);
         composite.setCategory(Diagram_Messages.CATEGORY_BASE);
         descriptors.add(composite);
      }
   }

   private static void addSymbolProperties(EditPart part, EObject object, List descriptors)
   {
      final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;

      if (object instanceof IModelElement)
      {
         PropertyDescriptor elementOidDescr = new PropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getIModelElement_ElementOid(),
                     part), Diagram_Messages.DISPL_NAME_SymbolElementOID);
         elementOidDescr.setCategory(CATEGORY_DIAGRAM);
         descriptors.add(elementOidDescr);
      }

      if (object instanceof INodeSymbol)
      {
         PropertyDescriptor xPosDescr = new PropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getINodeSymbol_XPos(), part),
               "X Pos"); //$NON-NLS-1$
         xPosDescr.setCategory(CATEGORY_DIAGRAM);
         descriptors.add(xPosDescr);

         PropertyDescriptor yPosDescr = new PropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getINodeSymbol_YPos(), part),
               "Y Pos"); //$NON-NLS-1$
         yPosDescr.setCategory(CATEGORY_DIAGRAM);
         descriptors.add(yPosDescr);

         PropertyDescriptor widthDescr = new PropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getINodeSymbol_Width(), part),
               Diagram_Messages.DISPL_NAME_Width);
         widthDescr.setCategory(CATEGORY_DIAGRAM);
         descriptors.add(widthDescr);

         PropertyDescriptor heightDescr = new PropertyDescriptor(
               new BoundEObjectPropertyId(object, CWM_PKG.getINodeSymbol_Height(), part),
               Diagram_Messages.DISPL_NAME_Height);
         heightDescr.setCategory(CATEGORY_DIAGRAM);
         descriptors.add(heightDescr);
      }

      if (object instanceof IConnectionSymbol)
      {
         PropertyDescriptor routingDescr = CmdFactoryPropertyDescriptorDecorator.create(
               new EEnumPropertyDescriptor(new BoundEObjectPropertyId(object, CWM_PKG
                     .getIConnectionSymbol_Routing(), part),
                     Diagram_Messages.DISPL_NAME_Routing,
                     CarnotWorkflowModelPackage.eINSTANCE.getRoutingType()),
               ConnectionRoutingCommandFactory.INSTANCE);
         routingDescr.setCategory(CATEGORY_DIAGRAM);
         descriptors.add(routingDescr);
      }
   }

   private static void addOtherEmfDescriptors(EditPart part, EObject object,
         List descriptors)
   {
      final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;

      Set handledDescriptors = new HashSet(descriptors.size());
      for (Iterator i = descriptors.iterator(); i.hasNext();)
      {
         PropertyDescriptor descr = (PropertyDescriptor) i.next();
         Object id = descr.getId();
         if ((id instanceof BoundEObjectPropertyId)
               && ((BoundEObjectPropertyId) id).getObject() == object)
         {
            handledDescriptors.add(((BoundEObjectPropertyId) id).getId());
         }
         else if (id instanceof EStructuralFeature)
         {
            handledDescriptors.add(id);
         }
      }

      EClass cls = object.eClass();
      /*
       * Iterator it = cls.getEAllAttributes().iterator(); while (it.hasNext()) {
       * EAttribute attr = (EAttribute) it.next();
       * 
       * if ( !handledDescriptors.contains(attr)) { EDataType type =
       * attr.getEAttributeType(); if (attr.isID()) { // shouldn't be editable
       * descriptors.add(new PropertyDescriptor(attr, attr.getName())); } else if
       * (type.getInstanceClass() == String.class) { if (attr.isMany()) {
       * descriptors.add(new StringListPropertyDescriptor(attr, attr.getName())); } else {
       * descriptors.add(new TextPropertyDescriptor(attr, attr.getName())); } } else if
       * (type.getInstanceClass() == boolean.class) { descriptors.add(new
       * BooleanPropertyDescriptor(attr, attr.getName())); } else if (attr.isChangeable() &&
       * (type instanceof EEnum)) { descriptors.add(new EEnumPropertyDescriptor(attr,
       * attr.getName(), (EEnum) type)); } } }
       */

      if (cls != null)
      {
         for (Iterator i = cls.getEAllReferences().iterator(); i.hasNext();)
         {
            EReference reference = (EReference) i.next();
            if (!handledDescriptors.contains(reference))
            {
               if (reference.getEReferenceType().equals(CWM_PKG.getTextType())
                     || reference.getEReferenceType()
                           .equals(CWM_PKG.getDescriptionType()))
               {
                  PropertyDescriptor textDescr = new PropertyDescriptor(reference,
                        reference.getName());
                  textDescr.setLabelProvider(new LabelProvider()
                  {
                     public String getText(Object element)
                     {
                        if (element instanceof TextType)
                        {
                           return ModelUtils.getCDataString(((TextType) element)
                                 .getMixed());
                        }
                        else if (element instanceof DescriptionType)
                        {
                           return ModelUtils.getCDataString(((DescriptionType) element)
                                 .getMixed());
                        }
                        else
                        {
                           return super.getText(element);
                        }
                     }
                  });
                  PropertyDescriptor composite = CmdFactoryPropertyDescriptorDecorator
                        .create(textDescr, AnyTextCommandFactory.INSTANCE);
                  composite.setCategory(CATEGORY_BASE);
                  descriptors.add(composite);
               }
               /*
                * if ((object instanceof IndependentSubProcess) &&
                * reference.isChangeable() &&
                * reference.getEType().equals(bpmnPackage.getDiagram())) {
                * IndependentSubProcess independentSubProcess = (IndependentSubProcess)
                * object; descriptors.add(new DiagramRefPropertyDescriptor( reference,
                * reference.getName(), independentSubProcess.getContainingProcess()
                * .getContainingPool() .getContainingDiagram()
                * .getContainingRepository())); } else if ((object instanceof
                * IndependentSubProcess) && reference.isChangeable() &&
                * reference.getEType().equals(bpmnPackage.getProcess())) {
                * IndependentSubProcess independentSubProcess = (IndependentSubProcess)
                * object; if (null != independentSubProcess.getDiagramRef()) {
                * descriptors.add(new ProcessRefPropertyDescriptor( reference,
                * reference.getName(), independentSubProcess.getDiagramRef())); } }
                */
            }
         }
      }
   }
}
