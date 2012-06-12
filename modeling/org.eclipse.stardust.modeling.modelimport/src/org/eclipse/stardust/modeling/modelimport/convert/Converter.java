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
package org.eclipse.stardust.modeling.modelimport.convert;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.common.error.ApplicationException;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.engine.api.model.IActivity;
import org.eclipse.stardust.engine.api.model.IApplication;
import org.eclipse.stardust.engine.api.model.IConditionalPerformer;
import org.eclipse.stardust.engine.api.model.IDataMapping;
import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.engine.api.model.IModelParticipant;
import org.eclipse.stardust.engine.api.model.IOrganization;
import org.eclipse.stardust.engine.api.model.IProcessDefinition;
import org.eclipse.stardust.engine.api.model.IRole;
import org.eclipse.stardust.engine.api.model.ITransition;
import org.eclipse.stardust.engine.api.model.ITrigger;
import org.eclipse.stardust.engine.api.model.ImplementationType;
import org.eclipse.stardust.engine.api.model.Inconsistency;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.compatibility.diagram.Diagram;
import org.eclipse.stardust.engine.core.compatibility.diagram.NodeSymbol;
import org.eclipse.stardust.engine.core.model.builder.DefaultModelBuilder;
import org.eclipse.stardust.engine.core.model.gui.ActivitySymbol;
import org.eclipse.stardust.engine.core.model.gui.ApplicationSymbol;
import org.eclipse.stardust.engine.core.model.gui.ConditionalPerformerSymbol;
import org.eclipse.stardust.engine.core.model.gui.DataMappingConnection;
import org.eclipse.stardust.engine.core.model.gui.DataSymbol;
import org.eclipse.stardust.engine.core.model.gui.ExecutedByConnection;
import org.eclipse.stardust.engine.core.model.gui.OrganizationSymbol;
import org.eclipse.stardust.engine.core.model.gui.PerformsConnection;
import org.eclipse.stardust.engine.core.model.gui.RoleSymbol;
import org.eclipse.stardust.engine.core.model.gui.TransitionConnection;
import org.eclipse.stardust.engine.core.model.utils.ModelElementList;
import org.eclipse.stardust.engine.core.runtime.utils.XmlUtils;
import org.eclipse.stardust.modeling.modelimport.Import_Messages;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * @author Marc Gille
 */
public abstract class Converter
{
   private static final Logger trace = LogManager.getLogger(Converter.class);

   public static final int PROTOTYPE_MODE = 0;

   public static final int PRODUCTION_MODE = 1;

   protected LinkedList converterWarnings;

   protected IModel model;

   private Indent indent;

   private HashMap processData;

   private static final String DATA_X_POS = "DATA_X_POS"; //$NON-NLS-1$

   protected int triggerId;

   public Converter()
   {
      indent = new Indent();
      converterWarnings = new LinkedList();
   }

   /**
    * Creates an activity-transition-graph for all activities and transitions of all
    * process definitions in the default diagram of the process definition, whereby it is
    * assumed, that at least one diagram exists per process. If not, no
    * activity-transition-graph is created.
    */
   protected void populateDefaultDiagrams()
   {
      ModelElementList<IProcessDefinition> processes = model.getProcessDefinitions();

      for (IProcessDefinition processDefinition : processes)
      {
         processData = new HashMap();
         processData.put(DATA_X_POS, new Integer(40));

         try
         {
            ModelElementList<IActivity> activities = processDefinition.getActivities();
            if (activities.isEmpty())
            {
               continue;
            }

            @SuppressWarnings("unchecked")
            Iterator<Diagram> diagrams = processDefinition.getAllDiagrams();

            if (!diagrams.hasNext())
            {
               continue;
            }

            traverseActivityGraph(diagrams.next(), processDefinition.getRootActivity(),
                  0, new ArrayList(), new HashMap());
         }
         catch (Exception e)
         {
            trace.warn(
                  Import_Messages.MSG_CouldNotCreateActivityGraphForProcess
                        + processDefinition.getName()
                        + "(" + processDefinition.getId() + ")", e); //$NON-NLS-1$ //$NON-NLS-2$
            ConvertWarningException warning = new ConvertWarningException(
                  Import_Messages.MSG_CouldNotCreateActivityGraph + e.getMessage());
            warning.setProcessDefinition(processDefinition);
            converterWarnings.addLast(warning);
         }
      }
   }

   /**
    * Traverses the activity graph recursively. If the activity traversed is already
    * visited, the corresponding symbol is returned.
    * 
    * @param diagram
    * @param startActivity
    * @param x
    * @param yLevels
    * @param visitedActivities
    * @return
    */
   private ActivitySymbol traverseActivityGraph(Diagram diagram, IActivity startActivity,
         int x, ArrayList<Integer> yLevels, HashMap<String, ActivitySymbol> visitedActivities)
   {
      int X_OFFSET = 40;
      int Y_OFFSET = 160;
      int WIDTH = 300;
      int HEIGHT = 200;

      if (visitedActivities.get(startActivity.getId()) != null)
      {
         return (ActivitySymbol) visitedActivities.get(startActivity.getId());
      }

      if (x >= yLevels.size())
      {
         yLevels.add(new Integer(0));
      }

      ActivitySymbol activitySymbol = new ActivitySymbol(startActivity);
      visitedActivities.put(startActivity.getId(), activitySymbol);
      diagram.addToNodes(activitySymbol, 0);

      // Position symbol

      int xPos = X_OFFSET + x * WIDTH;
      int yPos = Y_OFFSET + ((Integer) yLevels.get(x)).intValue() * HEIGHT;
      activitySymbol.move(xPos, yPos);
      createPerformerSymbol(startActivity.getPerformer(), activitySymbol, diagram);
      createApplicationSymbol(startActivity.getApplication(), activitySymbol, diagram);
      createDataSymbols(startActivity.getAllDataMappings(), activitySymbol, diagram);

      // Increment x-Level for subsequent activities

      ++x;

      Iterator outTransitions = startActivity.getAllOutTransitions();

      while (outTransitions.hasNext())
      {
         ActivitySymbol nextActivitySymbol;
         ITransition transition = (ITransition) outTransitions
               .next();
         IActivity nextActivity = transition.getToActivity();

         nextActivitySymbol = (ActivitySymbol) visitedActivities
               .get(nextActivity.getId());

         if (nextActivitySymbol == null)
         {
            nextActivitySymbol = traverseActivityGraph(diagram, nextActivity, x, yLevels,
                  visitedActivities);
            // Increment y-Level
            yLevels.set(x, new Integer(((Integer) yLevels.get(x)).intValue() + 1));
         }

         try
         {
            TransitionConnection transitionConnection = new TransitionConnection(
                  transition);
            transitionConnection.setFirstSymbol(activitySymbol);
            transitionConnection.setSecondSymbol(nextActivitySymbol, false);

            diagram.addToConnections(transitionConnection, 0);
         }
         catch (Throwable e)
         {
            System.out.println(Import_Messages.MSG_CreateTransitions
                  + startActivity.getName() + "-> " + nextActivity.getName() + ": " + e); //$NON-NLS-1$ //$NON-NLS-2$
         }
      }

      // Decrement x-Level
      --x;
      return activitySymbol;
   }

   private void createDataSymbols(Iterator dataMappings, ActivitySymbol activitySymbol,
         Diagram diagram)
   {
      while (dataMappings.hasNext())
      {
         IDataMapping dataMapping;
         DataMappingConnection mappingConn;
         DataSymbol dataSymbol;

         dataMapping = (IDataMapping) dataMappings.next();
         dataSymbol = (DataSymbol) processData.get(dataMapping.getData().getId());

         if (dataSymbol == null)
         {
            int dataXPos = ((Integer) processData.get(DATA_X_POS)).intValue();
            dataSymbol = new DataSymbol(dataMapping.getData());
            dataSymbol.move(dataXPos, 20);
            diagram.addToNodes(dataSymbol, 0);
            processData.put(dataMapping.getData().getId(), dataSymbol);
            dataXPos = dataXPos + dataSymbol.getWidth() + 20;
            processData.put(DATA_X_POS, new Integer(dataXPos));
         }

         mappingConn = new DataMappingConnection(dataMapping.getData(), dataMapping
               .getActivity(), dataMapping.getDirection());

         if (dataMapping.getDirection() == Direction.IN)
         {
            mappingConn.setFirstSymbol(dataSymbol);
            mappingConn.setSecondSymbol(activitySymbol);
         }
         else
         {
            mappingConn.setFirstSymbol(activitySymbol);
            mappingConn.setSecondSymbol(dataSymbol);
         }

         diagram.addToConnections(mappingConn, 0);
      }
   }

   private void createApplicationSymbol(IApplication application,
         ActivitySymbol activitySymbol, Diagram diagram)
   {
      if (application != null)
      {
         ApplicationSymbol applicationSymbol = new ApplicationSymbol(application);
         int appX = activitySymbol.getX()
               + ((activitySymbol.getWidth() - applicationSymbol.getWidth()) / 2);
         int appY = activitySymbol.getY() + 70;
         applicationSymbol.move(appX, appY);
         diagram.addToNodes(applicationSymbol, 0);
         ExecutedByConnection exCon = new ExecutedByConnection(applicationSymbol);
         exCon.setSecondSymbol(activitySymbol, false);
         diagram.addToConnections(exCon, 0);
      }
   }

   private void createPerformerSymbol(IModelParticipant performer,
         ActivitySymbol activitySymbol, Diagram diagram)
   {
      NodeSymbol performerSymbol = null;
      PerformsConnection performsConn = null;

      if (performer instanceof IRole)
      {
         performerSymbol = new RoleSymbol((IRole) performer);
         performsConn = new PerformsConnection((RoleSymbol) performerSymbol);
      }
      else if (performer instanceof IOrganization)
      {
         performerSymbol = new OrganizationSymbol((IOrganization) performer);
         performsConn = new PerformsConnection((OrganizationSymbol) performerSymbol);
      }
      else if (performer instanceof IConditionalPerformer)
      {
         performerSymbol = new ConditionalPerformerSymbol(
               (IConditionalPerformer) performer);
         performsConn = new PerformsConnection(
               (ConditionalPerformerSymbol) performerSymbol);
      }

      if (performerSymbol != null)
      {
         int perfX = activitySymbol.getX()
               + ((activitySymbol.getWidth() - performerSymbol.getWidth()) / 2);
         int perfY = activitySymbol.getY() - 70;
         performerSymbol.move(perfX, perfY);
         diagram.addToNodes(performerSymbol, 0);
         performsConn.setSecondSymbol(activitySymbol);
         diagram.addToConnections(performsConn, 0);
      }
   }

   protected String getIndent()
   {
      return indent.toString();
   }

   protected void incIndent()
   {
      indent.inc();
   }

   protected void decIndent()
   {
      indent.dec();
   }

   /**
    * Create missing links etc. for inkonsistent or incomplete models in order to achieve
    * executability.
    */
   protected void adjustForExecutability()
   {
      IModelParticipant administrator = model.findParticipant("Administrator"); //$NON-NLS-1$

      Iterator processDefinitions = model.getAllProcessDefinitions();

      while (processDefinitions.hasNext())
      {
         IProcessDefinition processDefinition = (IProcessDefinition) processDefinitions
               .next();

         if (!processDefinition.getAllTriggers().hasNext())
         {
            // Create at least one trigger

            String id = "trigger" + ++triggerId; //$NON-NLS-1$
            String name = id.toUpperCase();

            ITrigger trigger = processDefinition.createTrigger(id, name, model
                  .findTriggerType(PredefinedConstants.MANUAL_TRIGGER), 0);
            trigger.setAttribute(PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT,
                  administrator.getId());
         }

         Iterator activities = processDefinition.getAllActivities();

         while (activities.hasNext())
         {
            IActivity activity = (IActivity) activities.next();

            if (activity.getImplementationType().equals(ImplementationType.Manual)
                  && activity.getPerformer() == null)
            {
               activity.setPerformer(administrator);
            }
            else if (activity.getImplementationType().equals(
                  ImplementationType.Application))
            {
               if (activity.getApplication().isInteractive()
                     && activity.getPerformer() == null)
               {
                  // All interactive application activities become manuals

                  activity.setImplementationType(ImplementationType.Manual);
                  activity.setApplication(null);
                  activity.setPerformer(administrator);
               }
               else
               {
                  // All non-interactive application activities become routes

                  activity.setImplementationType(ImplementationType.Route);
                  activity.setApplication(null);
               }
            }
         }
      }
   }

   protected Document getDocumentFromInputStream(InputStream inputStream)
   {
      DocumentBuilder domBuilder;
      Document document;

      org.eclipse.stardust.common.Assert.isNotNull(inputStream, MessageFormat.format(
            Import_Messages.MSG_CannotCreateDocFromNull, null));

      try
      {
         domBuilder = XmlUtils.newDomBuilder();
         document = domBuilder.parse(new InputSource(inputStream));
      }
      catch (Exception e)
      {
         throw new InternalException(Import_Messages.MSG_CouldNotCreateFromInputStream,
               e);
      }

      return document;
   }

   protected IModel createModel()
   {
      return DefaultModelBuilder.create().createModel("name", "name", "description"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   public Vector getInconsistencies()
   {
      Inconsistency inconsistency;
      Vector inconsistencies = new Vector();
      Iterator warnings = converterWarnings.iterator();
      String message;

      while (warnings.hasNext())
      {
         ConvertWarningException warning = (ConvertWarningException) warnings.next();
         message = "Process " + warning.getProcessDefinition().getName() //$NON-NLS-1$
               + ": " + warning.getMessage(); //$NON-NLS-1$
         inconsistency = new Inconsistency(message, warning.getProcessDefinition()
               .getElementOID(), Inconsistency.WARNING);
         inconsistencies.addElement(inconsistency);
      }

      return inconsistencies;
   }
}

class Indent
{
   private static String TAB = "   "; //$NON-NLS-1$

   private int level = 0;

   public String toString()
   {
      StringBuffer buffer = new StringBuffer();

      buffer.append('[');
      buffer.append(level);
      buffer.append(']');

      for (int n = 0; n < level; ++n)
      {
         buffer.append(TAB);
      }

      return buffer.toString();
   }

   public void inc()
   {
      ++level;
   }

   public void dec()
   {
      --level;
   }
}
