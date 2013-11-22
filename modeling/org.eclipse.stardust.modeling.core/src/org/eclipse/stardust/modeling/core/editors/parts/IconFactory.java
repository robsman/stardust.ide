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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.model.beans.QNameUtil;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ModelerType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;

/**
 * @author fherinean
 * @version $Revision$
 */
public class IconFactory implements org.eclipse.stardust.model.xpdl.carnot.util.IconFactory
{
   private static final String ORG_ECLIPSE_XSD_EDIT = "org.eclipse.xsd.edit"; //$NON-NLS-1$

   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   private static IconFactory defaultFactory = new IconFactory(null);

   private boolean simpleIcon = false;
   private boolean keepSimpleIconState = false;

   public void keepSimpleIconState()
   {
      keepSimpleIconState = true;
   }

   private WorkflowModelEditor editor;

   public IconFactory(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public String getIconFor(EObject model)
   {
      if (model instanceof EClass)
      {
         return getIconFor((EClass) model);
      }

      if(!keepSimpleIconState)
      {
         simpleIcon = editor == null ? true : false;
      }

      if (model instanceof IModelElementNodeSymbol)
      {
         EObject newModel = ((IModelElementNodeSymbol) model).getModelElement();
/*         if (newModel == null && model instanceof AbstractEventSymbol)
         {
            return "icons/full/obj16/trigger.gif"; //$NON-NLS-1$
         }*/
         model = newModel;
      }
      /*
       * if (model instanceof ParameterMappingType) { // icons for parameter mappings
       * resolve to icons for the data. model = ((ParameterMappingType) model).getData(); }
       */
      if (model instanceof DataTypeType)
      {
         return getIcon((DataTypeType) model, "icons/full/obj16/data.gif"); //$NON-NLS-1$
      }
      if (model instanceof DataType)
      {
         if (simpleIcon)
         {
            return "icons/full/obj16/data.gif"; //$NON-NLS-1$
         }

         DataTypeType metaType = ((DataType) model).getType();
         TypeDeclarationType decl = StructuredTypeUtils.getTypeDeclaration((DataType) model);
         if(decl != null)
         {
            if(TypeDeclarationUtils.isEnumeration(decl, false))
            {
               ModelType containingModel = ModelUtils.findContainingModel(model);
               metaType = GenericUtils.getDataTypeType(containingModel, PredefinedConstants.PRIMITIVE_DATA);
            }
         }

         return getIcon(metaType, "icons/full/obj16/data.gif"); //$NON-NLS-1$
      }
      if (model instanceof TypeDeclarationsType)
      {
         return "icons/full/obj16/data_structured_definition.gif"; //$NON-NLS-1$
      }
      if (model instanceof TypeDeclarationType)
      {
         TypeDeclarationType declaration = (TypeDeclarationType) model;
         XpdlTypeType xpdlType = declaration.getDataType();
         if (xpdlType instanceof SchemaTypeType)
         {
            String icon = getXSDIcon(declaration);
            if (icon != null)
            {
               return icon;
            }
         }
         else if (xpdlType instanceof ExternalReferenceType)
         {
            // TODO: lazy loading of schemas + set correct icon type + overlay for reference.
            ExternalReferenceType externalReference = (ExternalReferenceType) xpdlType;
            String location = externalReference.getLocation();
            if (location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
            {
               String icon = getXSDIcon(declaration);
               if (icon != null)
               {
                  return icon;
               }
            }
            return QNameUtil.toString(ORG_ECLIPSE_XSD_EDIT, "icons/full/obj16/XSDSchema.gif"); //$NON-NLS-1$
         }
         return getIconFor(declaration.eClass());
      }
      if (model instanceof AccessPointType)
      {
         if (simpleIcon)
         {
            return "icons/full/obj16/data.gif"; //$NON-NLS-1$
         }
         return getIcon(((AccessPointType) model).getType(), "icons/full/obj16/data.gif"); //$NON-NLS-1$
      }
      if (model instanceof TriggerTypeType)
      {
         return getIcon((TriggerTypeType) model, "icons/full/obj16/trigger.gif"); //$NON-NLS-1$
      }
      if (model instanceof TriggerType)
      {
         if (simpleIcon)
         {
            return "icons/full/obj16/start_event.gif"; //$NON-NLS-1$
         }
         return getIcon(((TriggerType) model).getType(), "icons/full/obj16/trigger.gif"); //$NON-NLS-1$
      }
      if (model instanceof ApplicationTypeType)
      {
         return getIcon((ApplicationTypeType) model, "icons/full/obj16/application.gif"); //$NON-NLS-1$
      }
      if (model instanceof ApplicationType)
      {
         if (((ApplicationType) model).isInteractive()
               && (((ApplicationType) model).getContext().size() > 0))
         {
            model = (EObject) ((ApplicationType) model).getContext().get(0);
         }
         else
         {
            if (simpleIcon)
            {
               return "icons/full/obj16/application.gif"; //$NON-NLS-1$
            }
            return getIcon(((ApplicationType) model).getType(),
                  "icons/full/obj16/application.gif"); //$NON-NLS-1$
         }

      }
      if (model instanceof ApplicationContextTypeType)
      {
         return getIcon((ApplicationContextTypeType) model,
               "icons/full/obj16/context.gif"); //$NON-NLS-1$
      }
      if (model instanceof ContextType)
      {
         if (simpleIcon)
         {
            return "icons/full/obj16/context.gif"; //$NON-NLS-1$
         }
         return getIcon(((ContextType) model).getType(), "icons/full/obj16/context.gif"); //$NON-NLS-1$
      }
      if (model instanceof AbstractEventAction)
      {
         if (simpleIcon)
         {
            return "icons/full/obj16/event_action.gif"; //$NON-NLS-1$
         }
         return getIcon(((AbstractEventAction) model).getType(),
               "icons/full/obj16/event_action.gif"); //$NON-NLS-1$
      }
      if (model instanceof ActivityType)
      {
         if (simpleIcon)
         {
            return "icons/full/obj16/activity.gif"; //$NON-NLS-1$
         }

         ActivityType activity = (ActivityType) model;
         ActivityImplementationType type = activity.getImplementation();
         if (ActivityImplementationType.ROUTE_LITERAL.equals(type))
         {
            return "icons/full/obj16/activity_route.gif"; //$NON-NLS-1$
         }
         if (ActivityImplementationType.MANUAL_LITERAL.equals(type))
         {
            return "icons/full/obj16/activity_manual.gif"; //$NON-NLS-1$
         }
         if (ActivityImplementationType.SUBPROCESS_LITERAL.equals(type))
         {
            return "icons/full/obj16/activity_subprocess.gif"; //$NON-NLS-1$
         }
         if (ActivityImplementationType.APPLICATION_LITERAL.equals(type))
         {
            return "icons/full/obj16/activity_application.gif"; //$NON-NLS-1$
         }
         return "icons/full/obj16/activity.gif"; //$NON-NLS-1$
      }
      if (model instanceof ConditionalPerformerType)
      {
         return "icons/full/obj16/conditional.gif"; //$NON-NLS-1$
      }
      if (model instanceof DataMappingType)
      {
         return DirectionType.IN_LITERAL.equals(((DataMappingType) model).getDirection())
               ? "icons/full/obj16/in_datamapping.gif" : "icons/full/obj16/out_datamapping.gif"; //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (model instanceof DataPathType)
      {
         DataPathType dataPath = (DataPathType) model;
         if (dataPath.isDescriptor())
         {
            return "icons/full/obj16/descriptor.gif"; //$NON-NLS-1$
         }
         DirectionType direction = dataPath.getDirection();
         if (DirectionType.IN_LITERAL.equals(direction))
         {
            return "icons/full/obj16/in_datapath.gif"; //$NON-NLS-1$
         }
         if (DirectionType.OUT_LITERAL.equals(direction))
         {
            return "icons/full/obj16/out_datapath.gif"; //$NON-NLS-1$
         }
         return "icons/full/obj16/datapath.gif"; //$NON-NLS-1$
      }
      if (model instanceof DiagramType)
      {
         return "icons/full/obj16/diagram.gif"; //$NON-NLS-1$
      }
      if (model instanceof EventHandlerType)
      {
         if (simpleIcon)
         {
            return "icons/full/obj16/event_handler.gif"; //$NON-NLS-1$
         }
         return getIcon(((EventHandlerType) model).getType(),
               "icons/full/obj16/event_handler.gif"); //$NON-NLS-1$
      }
      if (model instanceof ModelerType)
      {
         return "icons/full/obj16/modeler.gif"; //$NON-NLS-1$
      }
      if (model instanceof ModelType)
      {
         return "icons/full/obj16/model.gif"; //$NON-NLS-1$
      }
      if (model instanceof OrganizationType)
      {
         return "icons/full/obj16/organization.gif"; //$NON-NLS-1$
      }
      if (model instanceof ProcessDefinitionType)
      {
         return "icons/full/obj16/process.gif"; //$NON-NLS-1$
      }
      if (model instanceof RoleType)
      {
         return "icons/full/obj16/role.gif"; //$NON-NLS-1$
      }
      if (model instanceof TransitionType)
      {
         return "icons/full/obj16/transition.gif"; //$NON-NLS-1$
      }
      if (model instanceof LinkTypeType)
      {
         return "icons/full/obj16/link_type.gif"; //$NON-NLS-1$
      }
      if (model instanceof ViewType)
      {
         return "icons/full/obj16/view.gif"; //$NON-NLS-1$
      }
      if (model instanceof ExternalPackages)
      {
         return "icons/full/obj16/view.gif"; //$NON-NLS-1$
      }
      if (model instanceof ExternalPackage)
      {
         return "icons/full/obj16/view.gif"; //$NON-NLS-1$
      }
      return null;
   }

   private static String getXSDIcon(TypeDeclarationType declaration)
   {
      XSDNamedComponent component = TypeDeclarationUtils.findElementOrTypeDeclaration(declaration);
      if (component instanceof XSDElementDeclaration)
      {
         XSDElementDeclaration element = (XSDElementDeclaration) component;
         component = element.getTypeDefinition();
      }
      if (component instanceof XSDSimpleTypeDefinition)
      {
         return QNameUtil.toString(ORG_ECLIPSE_XSD_EDIT, "icons/full/obj16/XSDSimpleTypeDefinition.gif"); //$NON-NLS-1$
      }
      if (component instanceof XSDComplexTypeDefinition)
      {
         return QNameUtil.toString(ORG_ECLIPSE_XSD_EDIT, "icons/full/obj16/XSDComplexTypeDefinition.gif"); //$NON-NLS-1$
      }
      return null;
   }

   private static String getIconFor(EClass model)
   {
      if (PKG_CWM.getDataTypeType().equals(model) || PKG_CWM.getDataType().equals(model))
      {
         return "icons/full/obj16/data.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getAccessPointType().equals(model))
      {
         return "icons/full/obj16/data.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getTriggerTypeType().equals(model)
            || PKG_CWM.getTriggerType().equals(model))
      {
         return "icons/full/obj16/trigger.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getApplicationTypeType().equals(model)
            || PKG_CWM.getApplicationType().equals(model))
      {
         return "icons/full/obj16/application.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getApplicationContextTypeType().equals(model)
            || PKG_CWM.getContextType().equals(model))
      {
         return "icons/full/obj16/context.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getActivityType().equals(model))
      {
         return "icons/full/obj16/activity.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getConditionalPerformerType().equals(model))
      {
         return "icons/full/obj16/conditional.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getDataMappingConnectionType().equals(model))
      {
         return "icons/full/obj16/draw_datamapping.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getDataMappingType().equals(model))
      {
         return "icons/full/obj16/datamapping.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getDataPathType().equals(model))
      {
         return "icons/full/obj16/datapath.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getDiagramType().equals(model))
      {
         return "icons/full/obj16/diagram.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getEventHandlerType().equals(model))
      {
         return "icons/full/obj16/event_handler.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getExecutedByConnectionType().equals(model))
      {
         return "icons/full/obj16/executedBy.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getModelerType().equals(model))
      {
         return "icons/full/obj16/modeler.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getModelType().equals(model))
      {
         return "icons/full/obj16/model.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getOrganizationType().equals(model))
      {
         return "icons/full/obj16/organization.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getPartOfConnectionType().equals(model))
      {
         return "icons/full/obj16/part_of.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getPerformsConnectionType().equals(model))
      {
         return "icons/full/obj16/performedBy.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getProcessDefinitionType().equals(model))
      {
         return "icons/full/obj16/process.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getRoleType().equals(model))
      {
         return "icons/full/obj16/role.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getTeamLeadConnectionType().equals(model))
      {
         return "icons/full/obj16/team_lead.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getTransitionConnectionType().equals(model))
      {
         return "icons/full/obj16/draw_transition.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getTransitionType().equals(model))
      {
         return "icons/full/obj16/transition.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getLinkTypeType().equals(model)
            || PKG_CWM.getGenericLinkConnectionType().equals(model))
      {
         return "icons/full/obj16/link_type.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getViewType().equals(model))
      {
         return "icons/full/obj16/view.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getWorksForConnectionType().equals(model))
      {
         return "icons/full/obj16/works_for.gif"; //$NON-NLS-1$
      }
      else if (PKG_CWM.getIModelParticipant().equals(model))
      {
         return "icons/full/obj16/participants.gif"; //$NON-NLS-1$
      }
      else if (XpdlPackage.eINSTANCE.getTypeDeclarationsType().equals(model))
      {
         return "icons/full/obj16/data_structured_definition.gif"; //$NON-NLS-1$
      }
      return null;
   }

   private static String getIcon(IMetaType type, String defaultIcon)
   {
      if (type == null)
      {
         return defaultIcon;
      }
      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
      String icon = registry.getTypeIcon(type.getExtensionPointId(), type.getId());
      return icon == null ? defaultIcon : icon;
   }

   public static IconFactory getDefault()
   {
      return defaultFactory;
   }
}