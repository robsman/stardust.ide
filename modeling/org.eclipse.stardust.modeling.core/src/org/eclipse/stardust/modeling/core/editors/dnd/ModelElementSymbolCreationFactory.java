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
package org.eclipse.stardust.modeling.core.editors.dnd;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.Pair;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ReloadConnectionsAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.UpdateDiagramAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.*;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.SymbolContainerLayoutEditPolicy;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.descriptors.ModelElementDescriptor;

public class ModelElementSymbolCreationFactory
{
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;
   private static XpdlPackage XPKG = XpdlPackage.eINSTANCE;
   
   public static SymbolCreationFactory getFactory(
         Object transferElement)
   {
      return getFactory(transferElement, null);
   }

   public static SymbolCreationFactory getFactory(
         final Object transferElement, final IGatewayLocator locator)
   {
      final EClass eClass = getSymbolEClass((Object) transferElement);
      
      return new SymbolCreationFactory()
      {
         public Object getNewObject()
         {
            // if (eClass == null && !(transferElement instanceof IObjectDescriptor))
            if (eClass == null)
            {
               return UnexecutableCommand.INSTANCE;
            }
            final boolean hasConnectionToReload = (eClass == CarnotWorkflowModelPackage.eINSTANCE
                     .getActivitySymbolType())
                     || (eClass == CarnotWorkflowModelPackage.eINSTANCE
                           .getOrganizationSymbolType())
                     || (eClass == CarnotWorkflowModelPackage.eINSTANCE.getRoleSymbolType())
                     || (eClass == CarnotWorkflowModelPackage.eINSTANCE
                           .getApplicationSymbolType());

            CompoundDiagramCommand compound = new CompoundDiagramCommand(
                  Diagram_Messages.DIAGRAM_CreateSymbol);
            if (transferElement instanceof IObjectDescriptor)
            { 
               // the current model
               ModelType model = editor.getWorkflowModel();
               ConnectionManager cm = editor.getConnectionManager();
               try
               {
                  Command cmd = cm.linkObject(model, new IObjectDescriptor[] {(IObjectDescriptor) transferElement});
                  if (cmd != null)
                  {
                     compound.add(cmd);
                  }
                  else
                  {
                     return UnexecutableCommand.INSTANCE;
                  }
               }
               catch (CoreException e)
               {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }               
            }
            
            final DelegateCommand update = new DelegateCommand();
            final DelegateCommand reload = new DelegateCommand();

            final IdFactory id = new IdFactory(
                  "Symbol", Diagram_Messages.BASENAME_Symbol, (IIdentifiableModelElement) null); //$NON-NLS-1$
            CreateSymbolCommand cmd = new CreateSymbolCommand(
                  IContainedElementCommand.PARENT, id, eClass)
            {
               public void execute()
               {
                  /*
                  iterate over model to find the new element 
                  (the one with the URI, href)
                  */
                  if (transferElement instanceof IObjectDescriptor)
                  {                     
                     ModelType model = editor.getWorkflowModel();
                     Iterator<? extends EObject> content = null;
                     if (CarnotWorkflowModelPackage.eINSTANCE.getDataSymbolType() == eClass)
                     {
                        content = model.getData().iterator();
                     }
                     else
                     {
                        content = model.eAllContents();
                     }
                     modelElement = findMatchingItem((IObjectDescriptor) transferElement, content);
                  }
                  id.setReferingElement((IIdentifiableModelElement) modelElement);
                  ISymbolContainer container = getSymbolContainer();
                  setTargetEditPart(editor.findEditPart(container));
                  setTransferElement(transferElement);
                  super.execute();

                  if (hasConnectionToReload && reloadConnections)
                  {
                     DelegateCommand delegate = new DelegateCommand()
                     {
                        public void execute()
                        {
                           super.execute();
                           setReloadConnectionsCommand(reload,
                                 (INodeSymbol) getModelElement());
                        }
                     };
                     update.setDelegate(delegate);
                     setUpdateDiagramCommand(delegate, (INodeSymbol) getModelElement(),
                           locator);
                  }
               }
            };
            cmd.setParent(symbolContainer);
            cmd.setLocation(new Rectangle(dropLocation.x, dropLocation.y, -1, -1));

            compound.add(cmd);
            if (hasConnectionToReload)
            {
               compound.add(update);
               compound.add(reload);
            }

            return compound;
         }

         private void setUpdateDiagramCommand(final DelegateCommand compound,
               final INodeSymbol symbol, final IGatewayLocator locator)
         {
            final UpdateDiagramAction update = new UpdateDiagramAction(editor)
            {
               protected List<?> getSelectedObjects()
               {
                  return Collections.singletonList(editor.findEditPart(ModelUtils
                        .findContainingDiagram(symbol)));
               }

               protected Dimension getGatewayDelta(final FlowControlType flowType,
                     Dimension activitySize, int gatewaySize)
               {
                  if (null != locator)
                  {
                     return locator.getGatewayLocation(flowType, activitySize,
                           gatewaySize);
                  }
                  else
                  {
                     return IGatewayLocator.getDefaultLocator(symbol)
                        .getGatewayLocation(flowType, activitySize, gatewaySize);
                  }
               }

               public Command createGatewayUpdateCommand(final GatewaySymbol[] gateway)
               {
                  return new DelegatingCommand()
                  {
                     public Command createDelegate()
                     {
                        ISymbolContainer container = getSymbolContainer();
                        WorkflowModelEditor editor = getEditor();
                        AbstractGraphicalEditPart host = (AbstractGraphicalEditPart)
                           editor.findEditPart(container);
                        AbstractNodeSymbolEditPart part = (AbstractNodeSymbolEditPart)
                           editor.findEditPart(gateway[0]);
                        Point location = new Point(gateway[0].getXPos(), gateway[0].getYPos());
                        return SymbolContainerLayoutEditPolicy.getSnapToGridCommand(host,
                              gateway[0], location, part);
                     }
                  };
               }
            };
            update.runEmbedded(compound);
         }

         private void setReloadConnectionsCommand(DelegateCommand compound,
               final INodeSymbol symbol)
         {
            final ReloadConnectionsAction reload = new ReloadConnectionsAction(editor)
            {
               protected List<?> getSelectedObjects()
               {
                  return Collections.singletonList(editor.findEditPart(symbol));
               }
            };

            CompoundCommand command = reload.createReloadCommand();
            ConnectionFilter connectionFilter = new ConnectionFilter(editor, symbol);
            connectionFilter.filter(command);
            if (!command.isEmpty())
            {
               compound.setDelegate(command);
            }
         }
      };
   }

   public static EClass getSymbolEClass(Object transferElement)
   {
      EClass eClass = null;
      
      if (transferElement instanceof IObjectDescriptor)
      {
         transferElement = ((IObjectDescriptor) transferElement).getType();
      }
      if (transferElement instanceof ActivityType
            || PKG.getActivityType().equals(transferElement))
      {
         eClass = PKG.getActivitySymbolType();
      }
      else if (transferElement instanceof ApplicationType
            || PKG.getApplicationType().equals(transferElement))
      {
         eClass = PKG.getApplicationSymbolType();
      }
      else if (transferElement instanceof ProcessDefinitionType
            || PKG.getProcessDefinitionType().equals(transferElement))
      {
         eClass = PKG.getProcessSymbolType();
      }
      else if (transferElement instanceof DataType
            || PKG.getDataType().equals(transferElement))
      {
         eClass = PKG.getDataSymbolType();
      }
      else if (transferElement instanceof TypeDeclarationType
            || XPKG.getTypeDeclarationType().equals(transferElement))
      {
         eClass = PKG.getDataSymbolType();
      }
      else if (transferElement instanceof RoleType
            || PKG.getRoleType().equals(transferElement))
      {
         eClass = PKG.getRoleSymbolType();
      }
      else if (transferElement instanceof OrganizationType
            || PKG.getOrganizationType().equals(transferElement))
      {
         eClass = PKG.getOrganizationSymbolType();
      }
      else if (transferElement instanceof ConditionalPerformerType
            || PKG.getConditionalPerformerType().equals(transferElement))
      {
         eClass = PKG.getConditionalPerformerSymbolType();
      }
      else if (transferElement instanceof ModelerType
            || PKG.getModelerType().equals(transferElement))
      {
         eClass = PKG.getModelerSymbolType();
      }
      else if (transferElement instanceof TriggerType
            || PKG.getTriggerType().equals(transferElement))
      {
         eClass = PKG.getStartEventSymbol();
      }
      
      return eClass;
   }

   public static EObject findMatchingItem(IObjectDescriptor descriptor, Iterator<? extends EObject> elements)
   {
      IIdentifiableModelElement identifiable = null;
      EObject similar = null;
      while (elements.hasNext())
      {
         EObject object = elements.next();
         if (object instanceof IIdentifiableModelElement)
         {
            if (matchesURI((IIdentifiableModelElement) object, descriptor))
            {
               identifiable = (IIdentifiableModelElement) object;
               // break; (fh) we don't stop here because we want to take the *last* matching element
            }
            else if (isSimilar((IIdentifiableModelElement) object, descriptor))
            {
               similar = (IIdentifiableModelElement) object;
            }
         }
         else if (object instanceof TypeDeclarationType)
         {
            if(CompareHelper.areEqual(((IObjectReference) descriptor).getId(), ((TypeDeclarationType) object).getId()))
            {
               similar = (TypeDeclarationType) object;
            }
         }         
      }
      return identifiable == null ? similar : identifiable;
   }

   private static boolean isSimilar(IIdentifiableModelElement element, IObjectDescriptor descriptor)
   {
      if (descriptor instanceof ModelElementDescriptor)
      {
         return isSimilar(((ModelElementDescriptor) descriptor).getIdentifiable(), element);
      }
      return false;
   }

   private static boolean isSimilar(IIdentifiableModelElement identifiable, IIdentifiableModelElement other)
   {
      return CompareHelper.areEqual(other.eClass(), identifiable.eClass())
//         && CompareHelper.areEqual(element.eContainingFeature(), identifiable.eContainingFeature())
         && CompareHelper.areEqual(other.getId(), identifiable.getId());
   }

   private static boolean matchesURI(IIdentifiableModelElement element, IObjectDescriptor descriptor)
   {
      // check if element is proxy
      if (element.eIsProxy() && element instanceof InternalEObject)
      {
         return CompareHelper.areEqual(descriptor.getURI(), ((InternalEObject) element).eProxyURI());
      }
      // check if element has link attribute
      String uri = AttributeUtil.getAttributeValue(element, IConnectionManager.URI_ATTRIBUTE_NAME);
      if (uri != null)
      {
         Object classifier = descriptor.getType();
         // TypeDeclarations are converted to Data
         if (XpdlPackage.eINSTANCE.getTypeDeclarationType() == classifier)
         {
            classifier = CarnotWorkflowModelPackage.eINSTANCE.getDataType();
         }
         return element.eClass().equals(classifier)
            && descriptor.getURI() != null
            && uri.equals(descriptor.getURI().toString());
      }
      return false;
   }
   
   private static class ConnectionFilter
   {
      private WorkflowModelEditor editor;
      private INodeSymbol symbol;
      
      private boolean userWasAsked = false;
      private boolean onlyClosest = false;
      private Map<EClass, Pair<List<Command>, CreateConnectionSymbolCommand>> connectionMap = CollectionUtils.newMap();
      private int returnCode = -1;
      
      public ConnectionFilter(WorkflowModelEditor editor, INodeSymbol symbol)
      {
         this.editor = editor;
         this.symbol = symbol;
      }

      public void filter(CompoundCommand compoundCommand)
      {
         @SuppressWarnings("unchecked")
         List<Command> commands = compoundCommand.getCommands();
         for (int commandCounter = commands.size() - 1; commandCounter >= 0; commandCounter--)
         {
            Command command = commands.get(commandCounter);
            if (command instanceof CreateConnectionSymbolCommand)
            {
               if (!isValidCommand(commands, (CreateConnectionSymbolCommand) command))
               {
                  commands.remove(commandCounter);
               }
            }
            else if (command instanceof CompoundCommand)
            {
               filter((CompoundCommand) command);
               if (((CompoundCommand) command).isEmpty())
               {
                  commands.remove(commandCounter);
               }
            }
         }
      }
      
      private boolean isValidClass(EClass eClass)
      {
         return eClass == CarnotWorkflowModelPackage.eINSTANCE.getTransitionConnectionType()
             || eClass == CarnotWorkflowModelPackage.eINSTANCE.getPartOfConnectionType()
             || eClass == CarnotWorkflowModelPackage.eINSTANCE.getWorksForConnectionType()
             || eClass == CarnotWorkflowModelPackage.eINSTANCE.getPerformsConnectionType()
             || eClass == CarnotWorkflowModelPackage.eINSTANCE.getTeamLeadConnectionType()                              
             || eClass == CarnotWorkflowModelPackage.eINSTANCE.getExecutedByConnectionType();
      }

      private boolean isValidCommand(List<Command> commands, CreateConnectionSymbolCommand command)
      {
         EClass eClass = command.getEClass();
         if (!isValidClass(eClass))
         {
            return false;
         }
         else
         {
            INodeSymbol other = command.getSourceSymbol();
            if (other == symbol)
            {
               other = command.getTargetSymbol();
            }
            if (other == symbol)
            {
               return false; // we don't like connections to the same symbol
            }
            if (other instanceof IModelElementNodeSymbol && (!userWasAsked || onlyClosest))
            {
               Pair<List<Command>, CreateConnectionSymbolCommand> pair = (Pair<List<Command>, CreateConnectionSymbolCommand>) connectionMap.get(eClass);
               if (pair == null)
               {
                  pair = new Pair<List<Command>, CreateConnectionSymbolCommand>(commands, command);
                  connectionMap.put(eClass, pair);
               }
               
               if (!userWasAsked)
               {
                  MessageDialog dialog = new MessageDialog(editor.getSite().getShell(), Diagram_Messages.DIA_MULTIPLE_CONNECTION_DETECTED,
                        null, Diagram_Messages.DIA_DO_YOU_WANT_TO_CREATE_CONNECTION_TO_ALL_SYMBOLS_NOR_TO_THE_CLOSEST_CLOSSEST_SYMBOL_ONLY,
                        MessageDialog.QUESTION, new String[] {Diagram_Messages.MSG_DIA_NONE, Diagram_Messages.MSG_DIA_ALL_SYMBOLS, Diagram_Messages.MSG_DIA_CLOSEST_ONLY}, 0);
                  returnCode = dialog.open();
                  userWasAsked = true;
                  if(returnCode <= 0)
                  {
                     return false;
                  }
                  
                  onlyClosest = returnCode == 2;
                  if (!onlyClosest)
                  {
                     return true;
                  }
               }
               
               // only closest
               CreateConnectionSymbolCommand otherCommand = (CreateConnectionSymbolCommand) pair.getSecond();
               double distance2 = getDistance2(command.getSourceSymbol(), command.getTargetSymbol());
               double otherDistance2 = getDistance2(otherCommand.getSourceSymbol(), otherCommand.getTargetSymbol());
               if (distance2 < otherDistance2)
               {
                  List<Command> otherCommands = pair.getFirst();
                  otherCommands.remove(otherCommand);
                  pair = new Pair<List<Command>, CreateConnectionSymbolCommand>(commands, command);
                  connectionMap.put(eClass, pair);
               }
               else
               {
                  return false;
               }
            }
         }
         if(userWasAsked && returnCode <= 0)
         {
            return false;
         }               
         
         return true;
      }

      private double getDistance2(INodeSymbol first, INodeSymbol second)
      {
         if (first.eContainer() != second.eContainer())
         {
            return Double.MAX_VALUE;
         }
         EditPart firstEP = editor.findEditPart(first);
         EditPart secondEP = editor.findEditPart(second);
         if (firstEP instanceof GraphicalEditPart && secondEP instanceof GraphicalEditPart)
         {
            Rectangle firstBounds = ((GraphicalEditPart) firstEP).getFigure().getBounds().getCopy();
            Rectangle secondBounds = ((GraphicalEditPart) secondEP).getFigure().getBounds().getCopy();
            Rectangle fixed = first == symbol ? firstBounds
                  : second == symbol ? secondBounds : null;
            if (fixed != null)
            {
               fixed.x -= fixed.width / 2;
               fixed.y -= fixed.height / 2;
            }
            double dx1 = firstBounds.x - secondBounds.x;
            dx1 = dx1 * dx1;
            double dx2 = firstBounds.x - secondBounds.right();
            dx2 = dx2 * dx2;
            double dx3 = firstBounds.right() - secondBounds.x;
            dx3 = dx3 * dx3;
            double dx4 = firstBounds.right() - secondBounds.right();
            dx4 = dx4 * dx4;
            double minX = Math.min(Math.min(dx1, dx2), Math.min(dx3, dx4));
            double dy1 = firstBounds.y - secondBounds.y;
            dy1 = dy1 * dy1;
            double dy2 = firstBounds.y - secondBounds.bottom();
            dy2 = dy2 * dy2;
            double dy3 = firstBounds.bottom() - secondBounds.y;
            dy3 = dy3 * dy3;
            double dy4 = firstBounds.bottom() - secondBounds.bottom();
            dy4 = dy4 * dy4;
            double minY = Math.min(Math.min(dy1, dy2), Math.min(dy3, dy4));
            double dist = minX + minY;
            return dist;
         }
         double dx = first.getXPos() - second.getXPos();
         double dy = first.getYPos() - second.getYPos();
         double dist = dx * dx + dy * dy;
         return dist;
      }
   }
}