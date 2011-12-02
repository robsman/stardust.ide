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

import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.PublicInterfaceSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AnnotationSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.ExecutedByConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.GenericLinkConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PartOfConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PerformsConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PoolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.RefersToConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.SubProcessOfConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.SymbolGroupEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.TeamLeadConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.TextSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.TriggersConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.WorksForConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPropertyPageContributor;
import org.eclipse.stardust.modeling.core.editors.ui.ModelElementPropertyDialog;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.model.IWorkbenchAdapter;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ShowPropertiesAction extends PropertyDialogAction
{
   private WorkflowModelEditor editor;

   public ShowPropertiesAction(WorkflowModelEditor editor,
         ISelectionProvider selectionProvider)
   {
      super(editor.getSite(), selectionProvider);
      this.editor = editor;
   }

   public boolean isEnabled()
   {
      return !(getStructuredSelection().getFirstElement() instanceof ChildCategoryNode)
            && (isApplicableForSelection(getStructuredSelection()));
   }

   public boolean isApplicableForSelection(IStructuredSelection selection)
   {
      if (selection.size() != 1)
      {
         return false;
      }
      Object object = getStructuredSelection().getFirstElement();
      // should not be possible to change name and id of the default pool
      if(object instanceof PoolEditPart)
      {
         PoolSymbol symbol = ((PoolEditPart) object).getPoolModel();
         if(DiagramUtil.isDefaultPool(symbol))
         {
            return false;
         }
      }      
      
      if (!(object instanceof IAdaptable))
      {
         return false;
      }
      EObject element = (EObject)
         ((IAdaptable) object).getAdapter(IModelElement.class);
      if (null == element)
      {
         element = (EObject)
            ((IAdaptable) object).getAdapter(EObject.class);
      }
      if (null == element)
      {
         return false;
      }
      if (CarnotPropertyPageContributor.instance().isApplicableTo((IAdaptable) object))
      {
         PreferenceManager pageManager = new PreferenceManager(
               ModelElementPropertyDialog.NODE_PATH_SEPARATOR);
         IAdaptable adaptable = (IAdaptable) getStructuredSelection().getFirstElement();
         
         Iterator pages = getPages(pageManager, adaptable);
         if (!pages.hasNext())
         {
            return false;
         }
         return true;
      }
      return false;
   }

   private Iterator getPages(PreferenceManager pageManager, IAdaptable adaptable)
   {
      if(adaptable instanceof GenericLinkConnectionEditPart)
      {
         GenericLinkConnectionType model = (GenericLinkConnectionType) ((GenericLinkConnectionEditPart) adaptable).getModel();
         LinkTypeType link = ((GenericLinkConnectionType) model).getLinkType();
         EditPart linkEP = editor.findEditPart(link);
         if(linkEP != null)
         {
            adaptable = linkEP;
         }
      }
            
      CarnotPropertyPageContributor.instance().contributePropertyPages(pageManager,
            adaptable);
      
      Iterator pages = pageManager.getElements(PreferenceManager.PRE_ORDER).iterator();
      return pages;
   }

   public PreferenceDialog createDialog()
   {
      return createDialog(null);
   }
   
   public PreferenceDialog createDialog(ChangeRecorder recorder)
   {
      if (!isApplicableForSelection(getStructuredSelection()))
      {
         return null;
      }
      IAdaptable element = (IAdaptable) getStructuredSelection().getFirstElement();
      
      PreferenceManager pageManager = new PreferenceManager(
            ModelElementPropertyDialog.NODE_PATH_SEPARATOR);
      Shell shell = editor.getSite().getShell();
      String name = getName(element);
      
      Iterator pages = getPages(pageManager, element);
            
      if (!pages.hasNext())
      {
         // different messages for symbols having no property pages
         String informationMessage = NLS.bind(Diagram_Messages.BIND_NoPropertyPages, name);
         if(element instanceof PartOfConnectionEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesConnectionPartOf;         
         } 
         else if(element instanceof PerformsConnectionEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesConnectionPerformedBy;
         }
         else if(element instanceof SubProcessOfConnectionEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesSubprocessOf;            
         }
         else if(element instanceof GenericLinkConnectionEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesGenericLink;
         }
         else if(element instanceof ExecutedByConnectionEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesExecutedBy;
         }
         else if(element instanceof WorksForConnectionEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesWorksFor;
         }         
         else if(element instanceof TeamLeadConnectionEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesTeamLead;
         }
         else if(element instanceof TriggersConnectionEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesTriggersConnection;
         }         
         else if(element instanceof RefersToConnectionEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesRefersToConnection;
         }         
         else if(element instanceof AnnotationSymbolEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesAnnotationSymbol;
         }
         else if(element instanceof TextSymbolEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesTextSymbol;
         }
         else if(element instanceof SymbolGroupEditPart)
         {
            informationMessage = Diagram_Messages.BIND_NoPropertyPagesSymbolGroup;
         }
         
         MessageDialog.openInformation(shell, Diagram_Messages.MSG_PropertyPages, informationMessage);
         return null;
      }

      ModelElementPropertyDialog preferenceDialog = new ModelElementPropertyDialog(
            editor, shell, pageManager, element, recorder);
      editor.getModelServer().setCachedModelElement(ModelUtils.getEObject(element));
      
      preferenceDialog.create();
      preferenceDialog.getShell()
            .setText(NLS.bind(Diagram_Messages.BIND_Properties, name));

      PlatformUI.getWorkbench().getHelpSystem().setHelp(preferenceDialog.getShell(),
            PlatformUI.PLUGIN_ID + ".property_dialog_context"); //$NON-NLS-1$

      return preferenceDialog;
   }

   private static String getName(IAdaptable element)
   {
      if (element instanceof EditPart)
      {
         if(element instanceof DiagramRootEditPart)
         {
            element = (DiagramEditPart) ((DiagramRootEditPart) element).getChildren().get(0);
         }
         Object model = ((EditPart) element).getModel();
         if (model instanceof PublicInterfaceSymbol) {
        	 return Diagram_Messages.PageTitle_ProcessInterface;
         }
         if (model instanceof IModelElementNodeSymbol)
         {
            model = ((IModelElementNodeSymbol) model).getModelElement();
         }
         if (model instanceof IIdentifiableElement)
         {
            return ((IIdentifiableElement) model).getName();
         }
         if (model instanceof TransitionConnectionType)
         {
            return ((TransitionConnectionType) model).getTransition().getName();
         }         
         // special case for Gateways
         if (model instanceof GatewaySymbol)
         {
            GatewaySymbol gateway = (GatewaySymbol) model;
            ActivitySymbolType symbol = gateway.getActivitySymbol();
            if(symbol == null)
               return ""; //$NON-NLS-1$
            ActivityType activity = (ActivityType) symbol.getModelElement();
            return (gateway.getFlowKind().getValue() == FlowControlType.JOIN
                  ? " Join" : "Split") + " Gateway (" + activity.getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
         }
         if (model instanceof DiagramType)
         {
            return ((DiagramType) model).getName()
                  + " (" //$NON-NLS-1$
                  + ((IIdentifiableElement) ((DiagramType) (model)).eContainer())
                        .getName() + ")"; //$NON-NLS-1$
         }
         if (model instanceof TypeDeclarationType)
         {
            return ((TypeDeclarationType) model).getName();
         }         
         if (model instanceof DataMappingConnectionType)
         {
            return Diagram_Messages.BIND_PropertyPagesDataMappingConnection;
         }    
         if (model instanceof IObjectDescriptor)
         {            
            return ((IObjectDescriptor)model).getLabel();
         }   
         if (model instanceof Connection)
         {            
            return ((Connection)model).getName();
         }   
         if (model instanceof ExternalPackage)
         {
            return ((ExternalPackage) model).getName();
         }
      }
      IWorkbenchAdapter adapter = (IWorkbenchAdapter) element
            .getAdapter(IWorkbenchAdapter.class);
      if (adapter != null)
      {
         return adapter.getLabel(element);
      }
      return "";//$NON-NLS-1$
   }
}