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
package org.eclipse.stardust.modeling.project.effort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.IAttributeCategory;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.project.Constants;
import org.eclipse.stardust.modeling.project.Project_Messages;


public class EffortParameters extends EffortNotifier
{
   private ModelType model;

   public final EffortParameterScope MODEL_SCOPE = new EffortParameterScope(this, ModelType.class, null);
   public final EffortParameterScope PROCESS_SCOPE = new EffortParameterScope(this, ProcessDefinitionType.class, null);
   public final EffortParameterScope ACTIVITY_SCOPE = new EffortParameterScope(this, ActivityType.class, null);
   public final EffortParameterScope TRANSITION_SCOPE = new EffortParameterScope(this, TransitionType.class, null);
   public final EffortParameterScope DATA_SCOPE = new EffortParameterScope(this, DataType.class, null);
   public final EffortParameterScope ROLE_SCOPE = new EffortParameterScope(this, RoleType.class, null);
   public final EffortParameterScope ORGANIZATION_SCOPE = new EffortParameterScope(this, OrganizationType.class, null);
   public final EffortParameterScope CONDITIONAL_PERFORMER_SCOPE = new EffortParameterScope(this, ConditionalPerformerType.class, null);
   public final EffortParameterScope INTERACTIVE_APPLICATION_SCOPE = new EffortParameterScope(this, ApplicationType.class, "Interactive"); //$NON-NLS-1$
   public final EffortParameterScope NON_INTERACTIVE_APPLICATION_SCOPE = new EffortParameterScope(this, ApplicationType.class, "Service"); //$NON-NLS-1$
   
   private final EffortParameterScope[] SCOPES = {
      MODEL_SCOPE,
      PROCESS_SCOPE,
      ACTIVITY_SCOPE,
      TRANSITION_SCOPE,
      DATA_SCOPE,
      ROLE_SCOPE,
      ORGANIZATION_SCOPE,
      CONDITIONAL_PERFORMER_SCOPE,
      INTERACTIVE_APPLICATION_SCOPE,
      NON_INTERACTIVE_APPLICATION_SCOPE
   };
   
   public final List<EffortParameterScope> SCOPE_LIST = Collections.unmodifiableList(Arrays.asList(SCOPES));

   // extra flag to prevent reloading if the effort model was not changed.
   private boolean modified = false;

   private boolean loading;

   public static final String SEPARATOR = ":"; //$NON-NLS-1$ 

   public EffortParameterScope getScope(Class<?> scope, String filter)
   {
      for (int i = 0; i < SCOPES.length; i++)
      {
         if (SCOPES[i].isApplicable(scope, filter))
         {
            return SCOPES[i];
         }
      }
      return null;
   }
   
   public static EffortParameters getEffortParameters(ModelType model)
   {
      if (model == null)
      {
         return null;
      }
      for (Adapter adapter : model.eAdapters())
      {
        if (adapter instanceof EffortParameters)
        {
          return (EffortParameters) adapter;
        }
      }
      return new EffortParameters(model);
   }

   private EffortParameters(ModelType model)
   {
      this.model = model;
      loadFromModel();
      model.eAdapters().add(new AdapterImpl()
      {
         public void notifyChanged(Notification notification)
         {
            if (notification.getFeature() == CarnotWorkflowModelPackage.eINSTANCE.getIExtensibleElement_Attribute())
            {
               boolean needsRefresh = AttributeUtil.getAttribute(EffortParameters.this.model, Constants.NOTIFIER) == null;
               if (modified && needsRefresh)
               {
                  loadFromModel();
               }
            }
         }
      });
   }

   public void initializeDefaults()
   {
      // Parameters for models
      MODEL_SCOPE.addParameter(new EffortByKeyParameter(MODEL_SCOPE,
            Project_Messages.getString("EffortParameters.ApplicationServerParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.WeblogicKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.WebSphereKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.JBossKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.NetWeaverKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0, 1.0, 1.0}));
      MODEL_SCOPE.addParameter(new EffortByKeyParameter(MODEL_SCOPE,
            Project_Messages.getString("EffortParameters.DBMSParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.OracleKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.SqlServerKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.MySqlKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0, 1.0}));
      MODEL_SCOPE.addParameter(new EffortByKeyParameter(MODEL_SCOPE,
            Project_Messages.getString("EffortParameters.DirectoryServiceParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.LDAPKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.ActiveDirectoryKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0}));
      MODEL_SCOPE.addParameter(new EffortByKeyParameter(MODEL_SCOPE,
            Project_Messages.getString("EffortParameters.PortalTechnologyParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.SWTKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.SwingKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.JSFKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.JSPKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.DotNetKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.OtherPortalKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0}));
      MODEL_SCOPE.addParameter(new EffortByQuantityParameter(MODEL_SCOPE,
            Project_Messages.getString("EffortParameters.HighComplexityReportsParameter"), 1.0)); //$NON-NLS-1$
      MODEL_SCOPE.addParameter(new EffortByQuantityParameter(MODEL_SCOPE,
            Project_Messages.getString("EffortParameters.MediumComplexityReportsParameter"), 1.0)); //$NON-NLS-1$
      MODEL_SCOPE.addParameter(new EffortByQuantityParameter(MODEL_SCOPE,
            Project_Messages.getString("EffortParameters.LowComplexityReportsParameter"), 1.0)); //$NON-NLS-1$

      // Parameters for processes
      PROCESS_SCOPE.addParameter(new EffortByKeyParameter(PROCESS_SCOPE,
            Project_Messages.getString("EffortParameters.RemainingModelingComplexityParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.WellDefinedKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.ComplexKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.MediumKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.SimpleKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0, 1.0, 1.0}));

      // Parameters for activities
      ACTIVITY_SCOPE.addParameter(new EffortByKeyParameter(ACTIVITY_SCOPE,
            Project_Messages.getString("EffortParameters.SubprocessRefinementParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.NoRefinementKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.HighComplexityRefinementKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.MediumComplexityRefinementKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.LowComplexityRefinementKey")}, //$NON-NLS-1$
            new double[] {0.0, 3.0, 2.0, 1.0}));

      // Parameters for transition
      TRANSITION_SCOPE.addParameter(new EffortByKeyParameter(TRANSITION_SCOPE,
            Project_Messages.getString("EffortParameters.TransitionTypeParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.ExpressionOnProcessDataKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.ComplexOrAuxillaryLogicKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0}));
      TRANSITION_SCOPE.addParameter(new EffortByKeyParameter(TRANSITION_SCOPE,
            Project_Messages.getString("EffortParameters.TransitionComplexityParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.HighKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.MediumKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.LowKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0, 1.0}));

      // Parameters for data
      List<String> dataTechnologies = collectDataTechnologies();
      dataTechnologies.add(Project_Messages.getString("EffortParameters.NewDevelopmentKey")); //$NON-NLS-1$
      DATA_SCOPE.addParameter(new EffortByKeyParameter(DATA_SCOPE,
            Project_Messages.getString("EffortParameters.DataTechnologyParameter"), //$NON-NLS-1$
            (String[]) dataTechnologies.toArray(new String[dataTechnologies.size()]),
            createDoubleArray(dataTechnologies.size(), 1.0)));
      DATA_SCOPE.addParameter(new EffortByKeyParameter(DATA_SCOPE,
            Project_Messages.getString("EffortParameters.DataDevelopmentParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.LinkToDBMSKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.LinkToOtherKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.NewDataModelKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0, 1.0}));

      // Parameters for Interactive Application
      INTERACTIVE_APPLICATION_SCOPE.addParameter(new EffortByKeyParameter(INTERACTIVE_APPLICATION_SCOPE,
            Project_Messages.getString("EffortParameters.ImplementationParameter"), //$NON-NLS-1$
            new String[] {Project_Messages.getString("EffortParameters.ToDoKey")}, //$NON-NLS-1$
            new double[] {1.0}));
      INTERACTIVE_APPLICATION_SCOPE.addParameter(new EffortByKeyParameter(INTERACTIVE_APPLICATION_SCOPE,
            Project_Messages.getString("EffortParameters.ComplexityParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.HighKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.MediumKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.LowKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0, 1.0}));

      // Parameters for Non-Interactive Application
      NON_INTERACTIVE_APPLICATION_SCOPE.addParameter(new EffortByKeyParameter(NON_INTERACTIVE_APPLICATION_SCOPE,
            Project_Messages.getString("EffortParameters.TechnologyParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.ToDoKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.NewDevelopmentKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0}));
      NON_INTERACTIVE_APPLICATION_SCOPE.addParameter(new EffortByKeyParameter(NON_INTERACTIVE_APPLICATION_SCOPE,
            Project_Messages.getString("EffortParameters.QualityAvailableDocumentationParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.HighKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.MediumKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.LowKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0, 1.0}));
      NON_INTERACTIVE_APPLICATION_SCOPE.addParameter(new EffortByKeyParameter(NON_INTERACTIVE_APPLICATION_SCOPE,
            Project_Messages.getString("EffortParameters.TechnologyParameter"), //$NON-NLS-1$
            new String[] {Project_Messages.getString("EffortParameters.ToDoKey")}, //$NON-NLS-1$
            new double[] {1.0}));
      NON_INTERACTIVE_APPLICATION_SCOPE.addParameter(new EffortByKeyParameter(NON_INTERACTIVE_APPLICATION_SCOPE,
            Project_Messages.getString("EffortParameters.ComplexityParameter"), //$NON-NLS-1$
            new String[] {
                  Project_Messages.getString("EffortParameters.HighKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.MediumKey"), //$NON-NLS-1$
                  Project_Messages.getString("EffortParameters.LowKey")}, //$NON-NLS-1$
            new double[] {1.0, 1.0, 1.0}));

      // Parameters for Participants
      CONDITIONAL_PERFORMER_SCOPE.addParameter(new EffortByKeyParameter(CONDITIONAL_PERFORMER_SCOPE,
            Project_Messages.getString("EffortParameters.PerformerComplexityParameter"), //$NON-NLS-1$
            new String[] {Project_Messages.getString("EffortParameters.ToDoKey")}, //$NON-NLS-1$
            new double[] {1.0}));
      ROLE_SCOPE.addParameter(new EffortByKeyParameter(ROLE_SCOPE,
            Project_Messages.getString("EffortParameters.RoleComplexityParameter"), //$NON-NLS-1$
            new String[] {Project_Messages.getString("EffortParameters.ToDoKey")}, //$NON-NLS-1$
            new double[] {1.0}));
      ORGANIZATION_SCOPE.addParameter(new EffortByKeyParameter(ORGANIZATION_SCOPE,
            Project_Messages.getString("EffortParameters.OrganizationComplexityParameter"), //$NON-NLS-1$
            new String[] {Project_Messages.getString("EffortParameters.ToDoKey")}, //$NON-NLS-1$
            new double[] {1.0}));
   }

   private double[] createDoubleArray(int size, double initialValue)
   {
      double[] result = new double[size];
      Arrays.fill(result, initialValue);
      return result;
   }

   private List<String> collectDataTechnologies()
   {
      List<String> dataTechnologies = CollectionUtils.newList();
      for (DataTypeType dataType : model.getDataType())
      {
         dataTechnologies.add(dataType.getName());
      }
      return dataTechnologies;
   }

   private void loadFromModel()
   {
      loading = true;
      for (int i = 0; i < SCOPES.length; i++)
      {
         SCOPES[i].clear();
      }
      IAttributeCategory planningCat = AttributeUtil.createAttributeCategory(model,
            Constants.SCOPE);
      for (IAttributeCategory category : planningCat.getAttributeCategories())
      {
         if (category.getId().startsWith(Constants.EFFORT_PARAMETER))
         {
            String scopeClassName = getAttribute(category, "scope"); //$NON-NLS-1$
            String filter = getAttribute(category, "filter"); //$NON-NLS-1$
            String typeName = getAttribute(category, "type"); //$NON-NLS-1$
            String name = getAttribute(category, "name"); //$NON-NLS-1$
            String initializers = getAttribute(category, "initializers"); //$NON-NLS-1$
            try
            {
               Class<?> scopeClass = Class.forName(scopeClassName);
               EffortParameterScope scope = getScope(scopeClass, filter);
               if (scope != null)
               {
                  EffortParameter parameter = null;
                  if ("org.eclipse.stardust.modeling.project.effort.EffortByKeyParameter" //$NON-NLS-1$
                        .equals(typeName))
                  {
                     parameter = new EffortByKeyParameter(scope, name, initializers);
                  }
                  if ("org.eclipse.stardust.modeling.project.effort.EffortByQuantityParameter" //$NON-NLS-1$
                        .equals(typeName))
                  {
                     parameter = new EffortByQuantityParameter(scope, name, initializers);
                  }
                  scope.addParameter(parameter);
               }
            }
            catch (Exception e)
            {
               throw new RuntimeException(e);
            }
         }
      }
      if (planningCat.getAttributeCategories().isEmpty())
      {
         initializeDefaults();
      }
      loading = false;
      modified = false;
      notifyListeners(new EffortEvent(this, "scopes", null, null)); //$NON-NLS-1$
   }

   public void saveToModel()
   {
      // make sure notifications are disabled.
      modified = false;
      // delete all planning attributes, otherwise deleting of parameters will
      // produce unexpected results.
      IAttributeCategory planningCat = AttributeUtil.createAttributeCategory(model,
            Constants.SCOPE);
      for (IAttributeCategory category : planningCat.getAttributeCategories())
      {
         planningCat.removeAttributeCategory(category.getId());
      }

      // set new attributes
      int n = 0;
      for (int i = 0; i < SCOPES.length; i++)
      {
         EffortParameterScope scope = SCOPES[i];
         for (Iterator<String> iterator = scope.getNames(); iterator.hasNext();)
         {
            String name = (String) iterator.next();
            EffortParameter parameter = scope.getParameter(name);
            IAttributeCategory category = planningCat
                  .createAttributeCategory(Constants.EFFORT_PARAMETER + "[" + n++ + "]"); //$NON-NLS-1$ //$NON-NLS-2$
            setAttribute(category, "scope", scope.getScopeClass().getName()); //$NON-NLS-1$
            setAttribute(category, "filter", scope.getFilter()); //$NON-NLS-1$
            setAttribute(category, "type", parameter.getClass().getName()); //$NON-NLS-1$
            setAttribute(category, "name", name); //$NON-NLS-1$
            setAttribute(category, "initializers", parameter.getInitializers()); //$NON-NLS-1$
         }
      }
      // remove change notifier if set
      AttributeUtil.setAttribute(model, Constants.NOTIFIER, null);
   }

   private String getAttribute(IAttributeCategory category, String name)
   {
      AttributeType attribute = category.getAttribute(name);
      return attribute == null ? null : attribute.getValue();
   }

   private void setAttribute(IAttributeCategory category, String name, String value)
   {
      AttributeType attribute = category.getAttribute(name);
      if (attribute == null)
      {
         attribute = category.createAttribute(name);
      }
      attribute.setValue(value);
   }

   public void parameterNameChanged(EffortParameter parameter, String newName, String oldName)
   {
      EffortParameterScope scope = parameter.getScope();
      for (IExtensibleElement extensible : getExtensibles(scope))
      {
         if (scope.isApplicable(extensible))
         {
            AttributeType attribute = AttributeUtil.getAttribute(extensible, Constants.SCOPE + SEPARATOR + oldName);
            if (attribute != null)
            {
               attribute.setName(Constants.SCOPE + SEPARATOR + newName);
            }
         }
      }
   }

   public void keyNameChanged(EffortKey key, String newName, String oldName)
   {
      EffortParameter parameter = key.getParameter();
      EffortParameterScope scope = parameter.getScope();
      for (IExtensibleElement extensible : getExtensibles(scope))
      {
         if (scope.isApplicable(extensible))
         {
            AttributeType attribute = AttributeUtil.getAttribute(extensible,
                  Constants.SCOPE + SEPARATOR + parameter.getName());
            if (attribute != null && CompareHelper.areEqual(attribute.getValue(), oldName))
            {
               attribute.setValue(newName);
            }
         }
      }
   }

   List<IExtensibleElement> getExtensibles(EffortParameterScope scope)
   {
      List<IExtensibleElement> collectibles = CollectionUtils.newList();
      
      if (MODEL_SCOPE == scope)
      {
         collectibles.add(model);
      }
      if (PROCESS_SCOPE == scope)
      {
         collectibles.addAll(model.getProcessDefinition());
      }
      if (ACTIVITY_SCOPE == scope)
      {
         for (ProcessDefinitionType process : model.getProcessDefinition())
         {
            collectibles.addAll(process.getActivity());
         }
      }
      if (TRANSITION_SCOPE == scope)
      {
         for (ProcessDefinitionType process : model.getProcessDefinition())
         {
            collectibles.addAll(process.getTransition());
         }
      }
      if (DATA_SCOPE == scope)
      {
         collectibles.addAll(model.getData());
      }
      if (ROLE_SCOPE == scope)
      {
         collectibles.addAll(model.getRole());
      }
      if (ORGANIZATION_SCOPE == scope)
      {
         collectibles.addAll(model.getOrganization());
      }
      if (CONDITIONAL_PERFORMER_SCOPE == scope)
      {
         collectibles.addAll(model.getConditionalPerformer());
      }
      if (INTERACTIVE_APPLICATION_SCOPE == scope)
      {
         collectibles.addAll(model.getApplication());
      }
      if (NON_INTERACTIVE_APPLICATION_SCOPE == scope)
      {
         collectibles.addAll(model.getApplication());
      }
      return collectibles;
   }

   public void markModified()
   {
      if (!loading)
      {
         modified = true;
         AttributeUtil.setAttribute(model, Constants.NOTIFIER, "dummy"); //$NON-NLS-1$
      }
   }

   public ModelType getModel()
   {
      return model;
   }
}
