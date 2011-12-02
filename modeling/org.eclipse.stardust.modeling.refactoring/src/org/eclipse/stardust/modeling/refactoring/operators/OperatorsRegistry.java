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
package org.eclipse.stardust.modeling.refactoring.operators;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.ltk.core.refactoring.participants.MoveArguments;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.ui.StringUtils;


/**
 * @author fherinean
 * @version $Revision$
 */
public class OperatorsRegistry
{
   private static OperatorsRegistry instance = new OperatorsRegistry();

   private List types;

   public OperatorsRegistry()
   {
      types = new ArrayList();

      types.add(new DataOperator(CarnotConstants.HIBERNATE_DATA_ID,
            new String[] {CarnotConstants.CLASS_NAME_ATT}));      
      
      types.add(new DataOperator(CarnotConstants.SERIALIZABLE_DATA_ID,
            new String[] {CarnotConstants.CLASS_NAME_ATT}));

      types.add(new DataOperator(CarnotConstants.ENTITY_BEAN_DATA_ID, new String[] {
            CarnotConstants.REMOTE_INTERFACE_ATT, CarnotConstants.HOME_INTERFACE_ATT,
            CarnotConstants.PRIMARY_KEY_ATT, CarnotConstants.CLASS_NAME_ATT}));

      types.add(new ApplicationOperator(CarnotConstants.PLAIN_JAVA_APPLICATION_ID,
            new String[] {CarnotConstants.CLASS_NAME_ATT},
            new String[] {CarnotConstants.CONSTRUCTOR_NAME_ATT},
            new String[] {CarnotConstants.METHOD_NAME_ATT}));

      types
            .add(new ApplicationOperator(CarnotConstants.SESSION_BEAN_APPLICATION_ID,
                  new String[] {
                        CarnotConstants.REMOTE_INTERFACE_ATT,
                        CarnotConstants.HOME_INTERFACE_ATT, CarnotConstants.CLASS_NAME_ATT}, 
                  new String[0], new String[] {
                        CarnotConstants.METHOD_NAME_ATT,
                        CarnotConstants.CREATE_METHOD_NAME_ATT}));

      // Spring Bean Application
      types.add(new ApplicationOperator(CarnotConstants.SPRING_BEAN_APPLICATION_ID,
            new String[] {CarnotConstants.CLASS_NAME_ATT},
            new String[] {CarnotConstants.CONSTRUCTOR_NAME_ATT},
            new String[] {CarnotConstants.METHOD_NAME_ATT}));
      
      // for jms applications we check access points only
      types.add(new ApplicationOperator(CarnotConstants.JMS_APPLICATION_ID,
            new String[0], new String[0], new String[0]));

      // for web service applications we check access points and type mappings
      types.add(new ApplicationOperator(CarnotConstants.WEB_SERVICE_APPLICATION_ID, true,
            new String[] {CarnotConstants.MAPPING_PREFIX}, new String[0], new String[0]));

      types.add(new InteractiveApplicationOperator(new String[] {
            CarnotConstants.CASABAC_CONTEXT_ID, CarnotConstants.JFC_CONTEXT_ID, CarnotConstants.JSF_CONTEXT_ID}));

      types.add(new TriggerOperator(CarnotConstants.JMS_TRIGGER_ID));
      types.add(new TriggerOperator(CarnotConstants.MAIL_TRIGGER_ID));

      types.add(new DataPathOperator());

      types.add(new DataMappingOperator());

      types.add(new ConditionOperator(CarnotConstants.EXCEPTION_CONDITION_ID,
            new String[] {CarnotConstants.EXCEPTION_NAME_ATT}));

      types.add(new SetDataActionOperator());
      types.add(new DataActionOperator(CarnotConstants.EXCLUDE_USER_ACTION_ID,
            new String[] {"carnot:engine:excludedPerformerData", //$NON-NLS-1$
                  "carnot:engine:excludedPerformerDataPath"})); //$NON-NLS-1$
      types.add(new DataActionOperator(CarnotConstants.SEND_MAIL_ACTION_ID, new String[] {
            "carnot:engine:mailBodyData", //$NON-NLS-1$
            "carnot:engine:mailBodyDataPath"})); //$NON-NLS-1$
   }

   public List populate(ModelType model, Object element, RefactoringArguments arguments)
   {
      List result = new ArrayList();
      for (int i = 0; i < types.size(); i++)
      {
         IJdtOperator operator = (IJdtOperator) types.get(i);
         result.addAll(operator.getRefactoringChanges(model, element, arguments));
      }
      return result;
   }

   public List search(IFile file, ModelType model, Object element)
   {
      List result = new ArrayList();
      for (int i = 0; i < types.size(); i++)
      {
         IJdtOperator operator = (IJdtOperator) types.get(i);
         result.addAll(operator.getQueryMatches(file, model, element));
      }
      return result;
   }

   public static OperatorsRegistry instance()
   {
      return instance;
   }

   public static String getNewPackageName(IPackageFragment iPackage, RefactoringArguments arguments)
   {
      if (arguments instanceof RenameArguments)
      {
         return ((RenameArguments) arguments).getNewName();
      }
      /*
      if (arguments instanceof MoveArguments
            && ((MoveArguments) arguments).getDestination() instanceof IPackageFragment)
      {
         return ((IPackageFragment) ((MoveArguments) arguments).getDestination())
               .getElementName()
               + "." + iPackage.getElementName(); //$NON-NLS-1$
      }
      */
      return iPackage.getElementName();
   }
   
   public static String getNewClassName(IType iType, RefactoringArguments arguments)
   {
      if (arguments instanceof RenameArguments)
      {
         if (StringUtils.isEmpty(iType.getPackageFragment().getElementName()))
         {
            return ((RenameArguments) arguments).getNewName();
         }
         return iType.getPackageFragment().getElementName() + "." //$NON-NLS-1$
               + ((RenameArguments) arguments).getNewName();
      }
      if (arguments instanceof MoveArguments
            && ((MoveArguments) arguments).getDestination() instanceof IPackageFragment)
      {
         return ((IPackageFragment) ((MoveArguments) arguments).getDestination())
               .getElementName()
               + "." + iType.getElementName(); //$NON-NLS-1$
      }
      return iType.getFullyQualifiedName();
   }

   public static String getNewMethodName(IMethod iMethod, RefactoringArguments arguments)
   {
      if (arguments instanceof RenameArguments)
      {
         return ((RenameArguments) arguments).getNewName();
      }
      return iMethod.getElementName();
   }
}