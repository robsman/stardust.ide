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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.SelectionPopup;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateConnectionSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.MoveNodeSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.tools.SnapCenterToGrid;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.stardust.modeling.core.utils.SnapGridUtils;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

public class CreateOrganizationHierarchyAction extends SelectionAction
{
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;
   private WorkflowModelEditor editor;
   private EditPart targetEP;
   private List participantList;
   private int downValue = 100;
   private int rightValue = 100;

   public CreateOrganizationHierarchyAction(IWorkbenchPart part)
   {
      super(part);
      this.editor = (WorkflowModelEditor) part;
      setText(Diagram_Messages.LB_CreateOrganizationHierarchy);
      setToolTipText(Diagram_Messages.LB_CreateOrganizationHierarchy);
      setId(DiagramActionConstants.CREATE_ORGANIZATION_HIERARCHY);
      setImageDescriptor(DiagramPlugin
            .getImageDescriptor("icons/full/obj16/organization.gif")); //$NON-NLS-1$
   }

   protected boolean calculateEnabled()
   {
      List selection = getSelectedObjects();
      if (selection.size() != 1)
      {
         return false;
      }
      // we must have organizations in the model (and a hierarchy)
      Object selected = getSelectedObjects().get(0);
      if (selected != null
            && (selected instanceof DiagramEditPart
            || selected instanceof DiagramRootEditPart))
      {
         // when the target is a lane, we may need to reorder all symbols and resize lane
         if (GenericUtils.getTargetEditPart(editor) == null)
         {
            return false;
         }
      }
      else
      {
         return false;
      }

      ModelType model = (ModelType) editor.getModel();
      return model.getOrganization().size() > 0;
   }

   public void run()
   {
      ModelType model = (ModelType) editor.getModel();
      OrganizationType organization = null;
      List<OrganizationType> organizations = model.getOrganization();
      if (organizations.size() > 1)
      {
         SelectionPopup popup = new SelectionPopup(editor.getSite().getShell());
         popup.setContentProvider(new ArrayContentProvider());
         popup.setLabelProvider(new LabelProvider()
         {
            public Image getImage(Object element)
            {
               if (element instanceof OrganizationType)
               {
                  return DiagramPlugin.getImage(editor.getIconFactory().getIconFor((OrganizationType) element));
               }
               return super.getImage(element);
            }

            public String getText(Object element)
            {
               if (element instanceof OrganizationType)
               {
                  return ((OrganizationType) element).getName();
               }
               return super.getText(element);
            }
         });
         popup.setInput(organizations);
         organization = (OrganizationType) popup.open();
         if(organization == null)
         {
            return;
         }
      }
      else
      {
         organization = (OrganizationType) organizations.get(0);
      }
      participantList = new ArrayList();

      DiagramEditorPage diagramEditorPage = (DiagramEditorPage) editor.getCurrentPage();
      Point location = diagramEditorPage.getMouseLocation().getCopy();
      CompoundCommand compoundCommand = new CompoundCommand();

      // target symbol container can be pool, lane or diagram (if model diagram)
      targetEP = GenericUtils.getTargetEditPart(editor);

      drawOrganizationHierarchy(organization, location, compoundCommand);
      Command resizeCmd = resizeSymbols(compoundCommand);
      Command connectionCmd = drawOrganizationHierarchyConnections(compoundCommand, organization);
      compoundCommand = (CompoundCommand) compoundCommand.chain(resizeCmd);
      compoundCommand = (CompoundCommand) compoundCommand.chain(connectionCmd);
      if(targetEP instanceof AbstractSwimlaneEditPart)
      {
         compoundCommand.add(new DelegatingCommand()
         {
            public Command createDelegate()
            {
               return PoolLaneUtils.resizeLane((AbstractSwimlaneEditPart) targetEP);
            }
         });
         // here children of siblings must be ordered (if there are any)
         compoundCommand.add(new DelegatingCommand()
         {
            public Command createDelegate()
            {
               return PoolLaneUtils.reorderSiblings(targetEP, null);
            }
         });
      }

      execute(compoundCommand);
      targetEP.refresh();
   }

   // here one command should iterate all createSymbolCommands and select the symbols
   private void drawOrganizationHierarchy(OrganizationType organization, Point location, CompoundCommand compoundCommand)
   {
      Point useLocation = location.getCopy();
      if(!participantList.contains(organization))
      {
         participantList.add(organization);
         /*
         if(SymbolCollisionUtils.isCollision((EditPart) targetEP, useLocation))
         {

         }
         */

         Command cmd = createSymbol(organization, useLocation);
         compoundCommand.add(cmd);
         useLocation.y += downValue;
      }

      RoleType teamLead = organization.getTeamLead();
      if(teamLead != null)
      {
         if(!participantList.contains(teamLead))
         {
            participantList.add(teamLead);
            Command cmd = createSymbol(teamLead, useLocation);
            compoundCommand.add(cmd);
            useLocation.x += rightValue;
         }
      }

      List participants = organization.getParticipant();
      for(Iterator it = participants.iterator(); it.hasNext();)
      {
         ParticipantType participant = (ParticipantType) it.next();
         IModelParticipant member = participant.getParticipant();
         if(member instanceof OrganizationType)
         {
            drawOrganizationHierarchy((OrganizationType) member, useLocation, compoundCommand);
            useLocation.x += rightValue;
         }
         else
         {
            if(!participantList.contains(member))
            {
               participantList.add(member);
               Command cmd = createSymbol(member, useLocation);
               compoundCommand.add(cmd);
               useLocation.x += rightValue;
            }
         }
      }
   }

   private Command createSymbol(IModelParticipant element, Point location)
   {
      EClass eClass = null;
      if(element instanceof OrganizationType)
      {
         eClass = PKG.getOrganizationSymbolType();
      }
      else if(element instanceof ConditionalPerformerType)
      {
         eClass = PKG.getConditionalPerformerSymbolType();
      }
      // role
      else
      {
         eClass = PKG.getRoleSymbolType();
      }

      IdFactory id = new IdFactory("Symbol", Diagram_Messages.BASENAME_Symbol, element); //$NON-NLS-1$
      Command cmd = new CreateSymbolCommand(IContainedElementCommand.PARENT,
            id, eClass);
      ((CreateSymbolCommand) cmd).setParent((EObject) targetEP.getModel());
      ((CreateSymbolCommand) cmd).setLocation(new Rectangle(location.x, location.y, -1, -1));
      return cmd;
   }

   private Command resizeSymbols(final CompoundCommand cmd)
   {
      return new DelegatingCommand()
      {
         private Map elementToSymbol = new HashMap();

         private void collectSymbols()
         {
            for(Iterator i = ((CompoundCommand) cmd).getCommands().iterator(); i.hasNext();)
            {
               Command child = (Command) i.next();
               if(child instanceof CreateSymbolCommand)
               {
                  IModelParticipantSymbol symbol = (IModelParticipantSymbol) ((CreateSymbolCommand) child).getModelElement();
                  IIdentifiableModelElement model = symbol.getModelElement();
                  elementToSymbol.put(model, symbol);
               }
            }
         }

         public Command createDelegate()
         {
            collectSymbols();
            CompoundCommand command = new CompoundCommand();
            resizeSymbols(command);
            return command;
         }

         /* see SymbolContainerLayoutEditPolicy.getMoveSymbolToCenterCommand
            the EditPolicies should do this in all cases
         */
         private void resizeSymbols(CompoundCommand command)
         {
            Iterator it = elementToSymbol.entrySet().iterator();
            while (it.hasNext())
            {
               Map.Entry entry = (Map.Entry) it.next();
               IModelParticipantSymbol symbol = (IModelParticipantSymbol) entry.getValue();

               // size depends on snap2grid values
               AbstractGraphicalEditPart editPart = (AbstractGraphicalEditPart) editor.findEditPart(symbol);
               Dimension prefSize = editPart.getFigure().getPreferredSize();
               Dimension size = SnapGridUtils.getSnapDimension(prefSize, (AbstractGraphicalEditPart) targetEP, 2, true);

               boolean change = false;
               if(size.height < prefSize.height)
               {
                  prefSize.height += SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;
                  change = true;
               }
               if(size.width < prefSize.width)
               {
                  prefSize.width += SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;
                  change = true;
               }
               if(change)
               {
                  size = SnapGridUtils.getSnapDimension(prefSize,
                        (AbstractGraphicalEditPart) targetEP, 2, true);
               }

               Point newLocation = new Point(new Long(symbol.getXPos()).intValue()
                     - size.width / 2, new Long(symbol.getYPos()).intValue()
                     - size.height / 2);
               // new location if snaptogrid is enabled
               Point setLocation = SnapGridUtils.getSnapLocation((AbstractGraphicalEditPart) targetEP,
                     (AbstractNodeSymbolEditPart) editPart, null, size, newLocation);

               // only if snap2grid is enabled
               if(SnapGridUtils.getSnapToHelper((AbstractGraphicalEditPart) targetEP) != null)
               {
                  Rectangle rect = new Rectangle();
                  rect.setLocation(setLocation);
                  rect.setSize(size);

                  MoveNodeSymbolCommand moveCommand = new MoveNodeSymbolCommand();
                  moveCommand.setPart(symbol);
                  moveCommand.setBounds(rect);
                  command.add(moveCommand);
               }
            }
         }
      };
   }

   private Command drawOrganizationHierarchyConnections(final CompoundCommand cmd, final OrganizationType organization)
   {
      return new DelegatingCommand()
      {
         private Map elementToSymbol = new HashMap();

         private void collectSymbols()
         {
            for(Iterator i = ((CompoundCommand) cmd).getCommands().iterator(); i.hasNext();)
            {
               Command child = (Command) i.next();
               if(child instanceof CreateSymbolCommand)
               {
                  IModelParticipantSymbol symbol = (IModelParticipantSymbol) ((CreateSymbolCommand) child).getModelElement();
                  IIdentifiableModelElement model = symbol.getModelElement();
                  elementToSymbol.put(model, symbol);
               }
            }
         }

         public Command createDelegate()
         {
            collectSymbols();
            CompoundCommand command = new CompoundCommand();
            drawOrganizationHierarchyConnection(organization, command);
            return command;
         }

         private void drawOrganizationHierarchyConnection(OrganizationType organization, CompoundCommand command)
         {
            OrganizationSymbolType organizationSymbol = (OrganizationSymbolType) elementToSymbol.get(organization);
            RoleType teamLead = organization.getTeamLead();
            if(teamLead != null)
            {
               RoleSymbolType roleSymbol = (RoleSymbolType) elementToSymbol.get(teamLead);
               CreateConnectionSymbolCommand connectionCmd = new CreateConnectionSymbolCommand(
                     null, PKG.getTeamLeadConnectionType());
               connectionCmd.setParent((EObject) targetEP.getModel());
               connectionCmd.setTargetSymbol((INodeSymbol) organizationSymbol);
               connectionCmd.setSourceSymbol(roleSymbol);
               command.add(connectionCmd);
            }

            List participants = organization.getParticipant();
            for(Iterator it = participants.iterator(); it.hasNext();)
            {
               ParticipantType participant = (ParticipantType) it.next();
               IModelParticipant member = participant.getParticipant();
               IModelParticipantSymbol participantSymbol = (IModelParticipantSymbol) elementToSymbol.get(member);
               if(member instanceof OrganizationType)
               {
                  CreateConnectionSymbolCommand connectionCmd = new CreateConnectionSymbolCommand(
                        null, PKG.getPartOfConnectionType());
                  connectionCmd.setParent((EObject) targetEP.getModel());
                  connectionCmd.setTargetSymbol((INodeSymbol) organizationSymbol);
                  connectionCmd.setSourceSymbol(participantSymbol);
                  command.add(connectionCmd);
                  drawOrganizationHierarchyConnection((OrganizationType) member, command);
               }
               else
               {
                  CreateConnectionSymbolCommand connectionCmd = new CreateConnectionSymbolCommand(
                        null, PKG.getWorksForConnectionType());
                  connectionCmd.setParent((EObject) targetEP.getModel());
                  connectionCmd.setTargetSymbol((INodeSymbol) organizationSymbol);
                  connectionCmd.setSourceSymbol(participantSymbol);
                  command.add(connectionCmd);
               }
            }
         }
      };
   }
}