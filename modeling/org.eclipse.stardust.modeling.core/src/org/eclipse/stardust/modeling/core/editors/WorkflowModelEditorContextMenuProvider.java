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
package org.eclipse.stardust.modeling.core.editors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoutingType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.actions.EditDomainAwareAction;
import org.eclipse.stardust.modeling.core.actions.IGraphicalViewerAwareAction;
import org.eclipse.stardust.modeling.core.actions.ISpiAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.SymbolGroupEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.CreateSubprocessAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.DiagramModeAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.IActiveAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.OrientationAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ResetSubprocessAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.SetActivityControlFlowAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.SetActivityImplementationAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.SetActivitySubprocessAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.SetApplicationTypeAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.SetConnectionRoutingAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.SetDataTypeAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ModelTreeEditPart;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;

public class WorkflowModelEditorContextMenuProvider extends ContextMenuProvider {

   private ActionRegistry registry;

	private ActionRegistry localRegistry;

	private WorkflowModelEditor editor;

	WorkflowModelEditorContextMenuProvider(DiagramEditorPage pageEditor,
			ActionRegistry registry) {
		this(pageEditor.getGraphicalViewer(), registry, pageEditor
				.getWorkflowModelEditor());

		localRegistry.registerAction(new ToggleGridAction(pageEditor
				.getGraphicalViewer()));
		localRegistry.registerAction(new ToggleSnapToGeometryAction(pageEditor
				.getGraphicalViewer()));
	}

	WorkflowModelEditorContextMenuProvider(EditPartViewer viewer,
			ActionRegistry registry, WorkflowModelEditor editor) {
		super(viewer);

		if (editor == null) {
			try {
				this.editor = (WorkflowModelEditor) PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage()
						.getActiveEditor();
			} catch (NullPointerException npe) {
				npe.printStackTrace();
			}
		} else {
			this.editor = editor;
		}

		if (null == registry) {
			throw new IllegalArgumentException();
		}

		this.registry = registry;

		this.localRegistry = new ActionRegistry();
	}

	public IAction getAction(String id) {
		IAction action = localRegistry.getAction(id);
		if (null == action) {
			action = registry.getAction(id);
		}
		return action;
	}

	public void buildContextMenu(IMenuManager manager) {
		manager.add(new Separator(DiagramActionConstants.GROUP_COLLISION));
		GEFActionConstants.addStandardActionGroups(manager);

		addActionToMenu(manager, DiagramActionConstants.SHARE_MODEL,
				DiagramActionConstants.GROUP_COLLISION);
		addActionToMenu(manager, DiagramActionConstants.UNSHARE_MODEL,
				DiagramActionConstants.GROUP_COLLISION);
		addActionToMenu(manager, DiagramActionConstants.UPDATE_MODEL,
				DiagramActionConstants.GROUP_COLLISION);
		addActionToMenu(manager, DiagramActionConstants.COMMIT_MODEL_ELEMENT,
				DiagramActionConstants.GROUP_COLLISION);
		addActionToMenu(manager, DiagramActionConstants.LOCK_ALL,
				DiagramActionConstants.GROUP_COLLISION);
		addActionToMenu(manager, DiagramActionConstants.LOCK,
				DiagramActionConstants.GROUP_COLLISION);
		addActionToMenu(manager, DiagramActionConstants.UN_LOCK_ALL,
				DiagramActionConstants.GROUP_COLLISION);
		addActionToMenu(manager, DiagramActionConstants.REVERT_CHANGES,
				DiagramActionConstants.GROUP_COLLISION);

		// String[] undoActions = {ActionFactory.UNDO.getId(),
		// ActionFactory.REDO.getId()};
		// addActionsToMenu(manager, undoActions,
		// GEFActionConstants.GROUP_UNDO);

		IStructuredSelection selection = getSelection();
		IAdaptable adaptable = (IAdaptable) selection.getFirstElement();

		IModelElement modelElement = (IModelElement) adaptable
				.getAdapter(IModelElement.class);
		IConnectionSymbol connectionSymbol = (IConnectionSymbol) adaptable
				.getAdapter(IConnectionSymbol.class);

		if (!(selection.getFirstElement() instanceof TreeEditPart)) {
			modelElement = modelElement instanceof IModelElementNodeSymbol ? ((IModelElementNodeSymbol) modelElement)
					.getModelElement()
					: modelElement;
		}

		addActionToMenu(manager, DiagramActionConstants.DEPLOY_MODEL,
				GEFActionConstants.GROUP_FIND);
		addActionToMenu(manager, DiagramActionConstants.CLEANUP_MODEL,
				GEFActionConstants.GROUP_FIND);
		addActionToMenu(manager, DiagramActionConstants.SHOW_IN_DIAGRAM,
				GEFActionConstants.GROUP_FIND);
		addActionToMenu(manager, DiagramActionConstants.SHOW_IN_OUTLINE,
				GEFActionConstants.GROUP_FIND);

		addActionToMenu(manager, DiagramActionConstants.SHRINK_TO_FIT,
				GEFActionConstants.GROUP_EDIT);
		addActionToMenu(manager, DiagramActionConstants.COPYSYMBOL,
				GEFActionConstants.GROUP_COPY);
		addActionToMenu(manager, DiagramActionConstants.PASTESYMBOL,
				GEFActionConstants.GROUP_COPY);
		addActionToMenu(manager,
				DiagramActionConstants.CREATE_SUBPROCESS_FROM_SELECTION,
				GEFActionConstants.GROUP_COPY);

		addActionToMenu(manager, ActionFactory.COPY.getId(),
				GEFActionConstants.GROUP_COPY);
		addActionToMenu(manager, ActionFactory.PASTE.getId(),
				GEFActionConstants.GROUP_COPY);
		addActionToMenu(manager, ActionFactory.CUT.getId(),
				GEFActionConstants.GROUP_COPY);
		addActionToMenu(manager, DiagramActionConstants.CONNECT,
				GEFActionConstants.GROUP_PRINT);

		if (selection.size() == 1 && !isObjectDescriptor(adaptable)) {
			addDynamicMenuEntries(modelElement, connectionSymbol, manager);
		}

		if (checkSelections(selection.toList())) {
			addActionToMenu(manager, ActionFactory.DELETE.getId(),
					Diagram_Messages.TXT_DeleteAll,
					GEFActionConstants.GROUP_EDIT);
		}

		// addCreateConnectionMenuEntries(manager, selection.getFirstElement());

		addActionToMenu(manager, DiagramActionConstants.DELETE_SYMBOL,
				GEFActionConstants.GROUP_EDIT);

		addActionToMenu(manager, DiagramActionConstants.MODEL_DIAGRAM_UPGRADE,
				GEFActionConstants.GROUP_VIEW);

		addActionToMenu(manager, DiagramActionConstants.REFERENCES_SEARCH,
				GEFActionConstants.GROUP_VIEW);

		addActionToMenu(manager, DiagramActionConstants.DATA_UPGRADE,
				GEFActionConstants.GROUP_VIEW);

		addActionToMenu(manager, DiagramActionConstants.CREATE_ACTIVITY_GRAPH,
				GEFActionConstants.GROUP_VIEW);

		addActionToMenu(manager,
				DiagramActionConstants.CREATE_ORGANIZATION_HIERARCHY,
				GEFActionConstants.GROUP_VIEW);

		addActionToMenu(manager, GEFActionConstants.DIRECT_EDIT,
				GEFActionConstants.GROUP_EDIT);

		addActionToMenu(manager,
				DiagramActionConstants.CREATE_PROCESS_DEFINITION,
				GEFActionConstants.GROUP_EDIT);

		addActionToMenu(manager,
				DiagramActionConstants.CREATE_GENERIC_ACTIVITY,
				GEFActionConstants.GROUP_EDIT);

		addSubMenuToMenu(manager, createSubmenu(
				Diagram_Messages.LB_SUBMENU_NewActivity,
				DiagramActionConstants.createActivityActions),
				GEFActionConstants.GROUP_EDIT);

		boolean isChildCategory = selection.getFirstElement() instanceof ChildCategoryNode
				|| adaptable instanceof EditPart
				&& ((EditPart) adaptable).getModel() instanceof TypeDeclarationsType;

		// RepositoryConnection
		if (isChildCategory) {
			addExtensionActionsToMenu(
					manager,
					Diagram_Messages.LB_EXTMENU_New,
					ObjectRepositoryActivator.PLUGIN_ID,
					ObjectRepositoryActivator.CONNECTION_EXTENSION_POINT_ID,
					ObjectRepositoryActivator.CREATE_REPOSITORY_CONNECTION_ACTION,
					GEFActionConstants.GROUP_EDIT);
		}

		// create actions for link/import
		// addActionToMenu(manager,
		// ObjectRepositoryActivator.LINK_CONNECTION_OBJECT_ACTION,
		// GEFActionConstants.GROUP_PRINT);
      addActionToMenu(manager, ObjectRepositoryActivator.IMPORT_CONNECTION_OBJECT_ACTION,
            GEFActionConstants.GROUP_PRINT);
      addActionToMenu(manager,
            ObjectRepositoryActivator.REFRESH_CONNECTION_OBJECT_ACTION,
           GEFActionConstants.GROUP_PRINT);
      addActionToMenu(manager,
            ObjectRepositoryActivator.DELETE_EXTERNAL_REFERENCES_ACTION,
            GEFActionConstants.GROUP_PRINT);
      addActionToMenu(manager,
            ObjectRepositoryActivator.ADD_EXTERNAL_REFERENCES_ACTION,
            GEFActionConstants.GROUP_PRINT);
		// Search
		// addActionToMenu(manager, DiagramActionConstants.SEARCH_CONNECTION,
		// GEFActionConstants.GROUP_PRINT);
		addExtensionActionsToMenu(manager, Diagram_Messages.LB_EXTMENU_New,
				ObjectRepositoryActivator.PLUGIN_ID,
				ObjectRepositoryActivator.CONNECTION_SEARCH_EXTENSION_POINT_ID,
				ObjectRepositoryActivator.SEARCH_ACTION,
				GEFActionConstants.GROUP_EDIT);

		// participants
		if (isChildCategory) {
			addActionsToMenu(manager,
					DiagramActionConstants.participantActions,
					GEFActionConstants.GROUP_EDIT);
		} else {
			addSubMenuToMenu(manager, createSubmenu(
					Diagram_Messages.LB_SUBMENU_NewParticipant,
					DiagramActionConstants.participantActions),
					GEFActionConstants.GROUP_EDIT);
		}

		// data
		addActionToMenu(manager, DiagramActionConstants.CREATE_GENERIC_DATA,
				GEFActionConstants.GROUP_EDIT);
		MenuManager menu = createExtensionMenu(
				Diagram_Messages.LB_EXTMENU_NewData,
				CarnotConstants.DATA_TYPES_EXTENSION_POINT_ID,
				DiagramActionConstants.CREATE_DATA);
		addSubMenuToMenu(manager, menu, GEFActionConstants.GROUP_EDIT);

		// applications
		addActionToMenu(manager,
				DiagramActionConstants.CREATE_GENERIC_APPLICATION,
				GEFActionConstants.GROUP_EDIT);
		addSubMenuToMenu(manager, createExtensionMenu(
				Diagram_Messages.LB_EXTMENU_NewApplication,
				CarnotConstants.APPLICATION_TYPES_EXTENSION_POINT_ID,
				DiagramActionConstants.CREATE_APPLICATION),
				GEFActionConstants.GROUP_EDIT);
		addSubMenuToMenu(manager, createExtensionMenu(
				Diagram_Messages.LB_EXTMENU_NewInteractiveApplication,
				CarnotConstants.CONTEXT_TYPES_EXTENSION_POINT_ID,
				DiagramActionConstants.CREATE_INTERACTIVE_APPLICATION),
				GEFActionConstants.GROUP_EDIT);

		addSubMenuToMenu(manager, createExtensionMenu(
				Diagram_Messages.LB_EXTMENU_NewTrigger,
				CarnotConstants.TRIGGER_TYPES_EXTENSION_POINT_ID,
				DiagramActionConstants.CREATE_TRIGGER),
				GEFActionConstants.GROUP_EDIT);

		addActionToMenu(manager, DiagramActionConstants.CREATE_LINK_TYPE,
				GEFActionConstants.GROUP_EDIT);

		addActionToMenu(manager, DiagramActionConstants.CREATE_DIAGRAM,
				GEFActionConstants.GROUP_EDIT);

      addActionToMenu(manager, DiagramActionConstants.CONVERT_GATEWAYS,
            GEFActionConstants.GROUP_EDIT);

		if (!editor.requireLock(modelElement == null ? connectionSymbol
				: modelElement)) {
			addSubMenuToMenu(manager, createAlignSubmenu(),
					GEFActionConstants.GROUP_REST);
		}
		addSubMenuToMenu(manager, createSubmenu(
				Diagram_Messages.LB_SUBMENU_Distribute,
				DiagramActionConstants.distributeActions),
				GEFActionConstants.GROUP_REST);
		addActionToMenu(manager, DiagramActionConstants.SNAP_TO_GRID,
				GEFActionConstants.GROUP_REST);
		addActionToMenu(manager, DiagramActionConstants.SET_DEFAULT_SIZE,
				GEFActionConstants.GROUP_REST);
		addActionToMenu(manager, DiagramActionConstants.GROUP_SYMBOLS,
				GEFActionConstants.GROUP_REST);
		addActionToMenu(manager, DiagramActionConstants.UNGROUP_SYMBOLS,
				GEFActionConstants.GROUP_REST);

		// todo: maybe create another category?
		addActionToMenu(manager, DiagramActionConstants.SET_PARTICIPANT,
				GEFActionConstants.GROUP_VIEW);

		addActionsToMenu(manager, DiagramActionConstants.viewActions,
				GEFActionConstants.GROUP_VIEW);

		addActionToMenu(manager, ActionFactory.PROPERTIES.getId(),
				GEFActionConstants.GROUP_VIEW);

		addActionToMenu(manager, DiagramActionConstants.EXPORT_DIAGRAM,
				GEFActionConstants.GROUP_VIEW);
		// addActionsToMenu(manager, DiagramActionConstants.saveActions,
		// GEFActionConstants.GROUP_SAVE);

		addContributedContextMenuActions(manager, selection);
	}

	private IStructuredSelection getSelection() {
		ISelectionProvider provider = getViewer();
		IStructuredSelection selection = provider.getSelection() instanceof IStructuredSelection ? (IStructuredSelection) provider
				.getSelection()
				: new StructuredSelection();
		return selection;
	}

	/*
	 * private void addCreateConnectionMenuEntries(IMenuManager manager, Object
	 * selectedObject) { IMenuManager createConnectionManager = new MenuManager(
	 * Editor_Messages.LB_CreateConnection); CreateConnectionAction[] actions =
	 * new CreateConnectionAction[] { new CreateConnectionAction(editor,
	 * CreateConnectionAction.TRANSITION, Editor_Messages.LB_CreateTransition,
	 * selectedObject), new CreateConnectionAction(editor,
	 * CreateConnectionAction.DATA_MAPPING,
	 * Editor_Messages.LB_CreateDataMapping, selectedObject), new
	 * CreateConnectionAction(editor, CreateConnectionAction.EXECUTED_BY,
	 * Editor_Messages.LB_CreateExecutedBy, selectedObject), new
	 * CreateConnectionAction(editor, CreateConnectionAction.PERFORMED_BY,
	 * Editor_Messages.LB_CreatePerforms, selectedObject), new
	 * CreateConnectionAction(editor, CreateConnectionAction.WORKS_FOR, true,
	 * Editor_Messages.LB_CreateWorksFor, selectedObject), new
	 * CreateConnectionAction(editor, CreateConnectionAction.PART_OF, true,
	 * Editor_Messages.LB_CreatePartOf, selectedObject)}; for (int i = 0; i <
	 * actions.length; i++) { if (actions[i].isEnabled()) {
	 * createConnectionManager.add(actions[i]); } }
	 * manager.appendToGroup(GEFActionConstants.GROUP_PRINT,
	 * createConnectionManager); }
	 */

	private MenuManager createAlignSubmenu() {
		MenuManager distmenu = new MenuManager(Diagram_Messages.LB_SUBMENU_Align);

		addActionToMenu(distmenu, GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY);
		addActionToMenu(distmenu, GEFActionConstants.TOGGLE_GRID_VISIBILITY);

		distmenu.add(new Separator());
		addActionsToMenu(distmenu,
				DiagramActionConstants.horizontalAlignActions);
		distmenu.add(new Separator());
		addActionsToMenu(distmenu, DiagramActionConstants.verticalAlignAction);

		return distmenu;
	}

	private boolean checkSelections(List list) {
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object next = iter.next();
			if ((next instanceof ChildCategoryNode)
					|| (next instanceof ModelTreeEditPart)
					// || (next instanceof PoolEditPart) || (next instanceof
					// LaneEditPart)
					|| (next instanceof SymbolGroupEditPart)
					|| (isPredefinedData(next))) {
				return false;
			}
		}
		return true;
	}

	private boolean isPredefinedData(Object obj) {
		if (obj instanceof EditPart) {
			if (((EditPart) obj).getModel() instanceof DataType) {
				return ((DataType) ((EditPart) obj).getModel()).isPredefined();
			} else if (((EditPart) obj).getModel() instanceof DataSymbolType
					&& ((DataSymbolType) ((EditPart) obj).getModel()).getData() != null) {
				return ((DataSymbolType) ((EditPart) obj).getModel()).getData()
						.isPredefined();
			}
		}
		return false;
	}

	private void addActionToMenu(IMenuManager manager, String actionId,
			String label, String group) {
		IAction action = getAction(actionId);
		if ((null != action) && action.isEnabled()) {
			action.setText(label);
			manager.appendToGroup(group, action);
		}
	}

	private MenuManager createSubmenu(String label, String[] actionIds) {
		MenuManager distmenu = new MenuManager(label);
		for (int i = 0; i < actionIds.length; i++) {
			String actionId = actionIds[i];
			addActionToMenu(distmenu, actionId);
		}
		return distmenu;
	}

	private void addExtensionActionsToMenu(IMenuManager menu,
			String labelPrefix, String packageName, String extensionId,
			String actionPrefix, String group) {
		Map extensions = SpiExtensionRegistry.instance().getExtensions(
				packageName, extensionId);
		for (Iterator i = extensions.values().iterator(); i.hasNext();) {
			IConfigurationElement config = (IConfigurationElement) i.next();
			IAction action = getAction(actionPrefix + config.getAttribute("id")); //$NON-NLS-1$
			if ((null != action) && action.isEnabled()) {
				menu.appendToGroup(group, action);
			}
		}
	}

	private MenuManager createExtensionMenu(String label, String extensionId,
			String prefix) {
		MenuManager menu = new MenuManager(label);
		Map extensions = SpiExtensionRegistry.instance().getExtensions(
				extensionId);
		for (Iterator i = extensions.values().iterator(); i.hasNext();) {
			IConfigurationElement config = (IConfigurationElement) i.next();
			IAction action = getAction(prefix + config.getAttribute("id")); //$NON-NLS-1$
			if ((null != action) && action.isEnabled()) {
				menu.add(action);
			}
		}
		return menu;
	}

	private void addSubMenuToMenu(IMenuManager manager, MenuManager menu,
			String group) {
		if (!menu.isEmpty()) {
			manager.appendToGroup(group, menu);
		}
	}

	private void addActionsToMenu(IMenuManager manager, String[] actionIds) {
		for (int i = 0; i < actionIds.length; i++) {
			String actionId = actionIds[i];
			addActionToMenu(manager, actionId);
		}
	}

	private void addActionsToMenu(IMenuManager manager, String[] actionIds,
			String group) {
		for (int i = 0; i < actionIds.length; i++) {
			String actionId = actionIds[i];
			addActionToMenu(manager, actionId, group);
		}
	}

	private void addActionToMenu(IMenuManager manager, String actionId) {
		IAction action = getAction(actionId);
		if ((null != action) && action.isEnabled()) {
			manager.add(action);
		}
	}

	private void addActionToMenu(IMenuManager manager, String actionId,
			String group) {
		IAction action = getAction(actionId);
		if (action != null) {
			if (action instanceof IActiveAction) {
				IActiveAction activeAction = (IActiveAction) action;
				if (activeAction.isActive()) {
					manager.appendToGroup(group, action);
				}
			} else if (action.isEnabled()) {
				manager.appendToGroup(group, action);
			}
		}
	}

	private void addDynamicMenuEntries(IModelElement actualElement,
			IGraphicalObject elementSymbol, IMenuManager manager) {
		if (actualElement instanceof ActivityType) {
			addActivityMenuEntries((ActivityType) actualElement, manager);
		}
		if (elementSymbol instanceof IConnectionSymbol) {
			addConnectionMenuEntries((IConnectionSymbol) elementSymbol, manager);
		} else if (actualElement instanceof IConnectionSymbol) {
			addConnectionMenuEntries((IConnectionSymbol) actualElement, manager);
		}
		if (actualElement instanceof DataType) {
			addDataMenuEntries((DataType) actualElement, manager);
		}
		if (actualElement instanceof ApplicationType) {
			addApplicationMenuEntries((ApplicationType) actualElement, manager);
		}
		if (actualElement instanceof DiagramType) {
			addDiagramMenuEntries((DiagramType) actualElement, manager);
		}
	}

	private void addDiagramMenuEntries(DiagramType diagram, IMenuManager manager) {
		EditDomain domain = getViewer().getEditDomain();
		if (ModelUtils.findContainingProcess(diagram) != null) {
			if (DiagramUtil.getDefaultPool(diagram) != null
					&& !PoolLaneUtils.containsLanes(editor
							.findEditPart(diagram))) {
				if (!editor.getModelServer().requireLock(diagram)) {
					MenuManager classic = new MenuManager(
							Diagram_Messages.WorkflowModelEditorContextMenuProvider_DIAGRAM_MODE);
					DiagramModeAction[] actions = new DiagramModeAction[] {
							new DiagramModeAction(
									Diagram_Messages.DIAGRAM_MODE_ON, diagram,
									domain, true),
							new DiagramModeAction(
									Diagram_Messages.DIAGRAM_MODE_OFF, diagram,
									domain, false) };

					for (int i = 0; i < actions.length; i++) {
						DiagramModeAction action = actions[i];
						if (action.getDiagramMode().equals(diagram.getMode())) {
							action.setChecked(true);
						}
						classic.add(action);
					}
					manager.appendToGroup(GEFActionConstants.GROUP_EDIT,
							classic);
				}
			}
		}

		// ask the diagram, if it is empty - or if it has the default pool (and
		// this is empty)
		int size = diagram.eContents().size();
		if (size > 1) {
			return;
		} else if (size == 1) {
			EObject object = (EObject) diagram.eContents().get(0);
			if (object instanceof PoolSymbol) {
				if (!DiagramUtil.isDefaultPool((ISwimlaneSymbol) object)) {
					return;
				}
				if (object.eContents().size() != 0) {
					return;
				}
			}
		}

		// only for process diagrams
		if (DiagramUtil.getDefaultPool(diagram) != null) {
			if (!editor.getModelServer().requireLock(diagram)) {
				MenuManager orientation = new MenuManager(
						Diagram_Messages.WorkflowModelEditorContextMenuProvider_ORIENTATION);
				OrientationAction[] orientationActions = new OrientationAction[] {
						new OrientationAction(OrientationType.VERTICAL_LITERAL,
								diagram, domain),
						new OrientationAction(
								OrientationType.HORIZONTAL_LITERAL, diagram,
								domain) };

				for (int i = 0; i < orientationActions.length; i++) {
					OrientationAction action = orientationActions[i];
					if (action.getOrientationType().equals(
							diagram.getOrientation())) {
						action.setChecked(true);
					}
					orientation.add(action);
				}
				manager.appendToGroup(GEFActionConstants.GROUP_EDIT,
						orientation);
			}
		}
	}

	private void addApplicationMenuEntries(ApplicationType application,
			IMenuManager manager) {
		if (!application.isInteractive()) {
			if (!editor.getModelServer().requireLock(application)) {
				ModelType model = (ModelType) application.eContainer();
				List modelApplicationTypes = model.getApplicationType();
				String[] missingApplicationTypes = getMissingApplicationTypes(modelApplicationTypes);
				if (missingApplicationTypes.length > 0) {
					addMetaTypes(
							model,
							missingApplicationTypes,
							CarnotConstants.APPLICATION_TYPES_EXTENSION_POINT_ID,
							CarnotWorkflowModelPackage.eINSTANCE
									.getApplicationTypeType(),
							new EStructuralFeature[] { CarnotWorkflowModelPackage.eINSTANCE
									.getApplicationTypeType_Synchronous() });
				}
				List applicationTypes = model.getApplicationType();

				EditDomain domain = getViewer().getEditDomain();
				MenuManager implementation = new MenuManager(
						Diagram_Messages.WorkflowModelEditorContextMenuProvider_TXT_MENU_MANAGER_SetType);

				SetApplicationTypeAction[] actions = new SetApplicationTypeAction[applicationTypes
						.size()];
				for (int i = 0; i < actions.length; i++) {
					actions[i] = new SetApplicationTypeAction(
							(ApplicationTypeType) applicationTypes.get(i),
							application, domain);
				}
				for (int i = 0; i < actions.length; i++) {
					SetApplicationTypeAction action = actions[i];
					if (action.getType().equals(application.getType())) {
						action.setChecked(true);
					}
					implementation.add(action);
				}
				manager.appendToGroup(GEFActionConstants.GROUP_EDIT,
						implementation);
			}
		}
	}

	private boolean isObjectDescriptor(IAdaptable adaptable) {
		return adaptable.getAdapter(IObjectDescriptor.class) != null;
	}

	private void addDataMenuEntries(DataType data, IMenuManager manager) {
		if (!data.isPredefined()) {
			if (!editor.getModelServer().requireLock(data)) {
				ModelType model = (ModelType) data.eContainer();
				EList<DataTypeType> dataTypes = model.getDataType();
				EditDomain domain = getViewer().getEditDomain();
				MenuManager implementation = new MenuManager(
						Diagram_Messages.WorkflowModelEditorContextMenuProvider_TXT_MENU_MANAGER_SetType);
				SetDataTypeAction[] actions = new SetDataTypeAction[dataTypes
						.size()];
				for (int i = 0; i < actions.length; i++) {
					actions[i] = new SetDataTypeAction((DataTypeType) dataTypes
							.get(i), data, domain);
				}

				for (int i = 0; i < actions.length; i++) {
					SetDataTypeAction action = actions[i];
					if (action.getType().equals(data.getType())) {
						action.setChecked(true);
					}
					implementation.add(action);
				}
				manager.appendToGroup(GEFActionConstants.GROUP_EDIT,
						implementation);
			}
		}
	}

	private void addActivityMenuEntries(final ActivityType activity,
			IMenuManager manager) {
		if (!editor.getModelServer().requireLock(activity)) {
			EditDomain domain = getViewer().getEditDomain();
			MenuManager join = new MenuManager(
					Diagram_Messages.TXT_MENU_MANAGER_JoinBehavior);
			SetActivityControlFlowAction[] joinActions = new SetActivityControlFlowAction[] {
					new SetActivityControlFlowAction(editor, domain, activity,
							FlowControlType.JOIN_LITERAL,
							JoinSplitType.NONE_LITERAL),
					new SetActivityControlFlowAction(editor, domain, activity,
							FlowControlType.JOIN_LITERAL,
							JoinSplitType.XOR_LITERAL),
					new SetActivityControlFlowAction(editor, domain, activity,
							FlowControlType.JOIN_LITERAL,
							JoinSplitType.AND_LITERAL) };
			for (int i = 0; i < joinActions.length; i++) {
				SetActivityControlFlowAction action = joinActions[i];
				if (action.getControlFlowType().equals(activity.getJoin())) {
					action.setChecked(true);
				}
				if (!ActivityUtil.hasStartEvent(activity)) {
					join.add(action);
				}
			}
			manager.appendToGroup(GEFActionConstants.GROUP_EDIT, join);

			MenuManager split = new MenuManager(
					Diagram_Messages.TXT_MENU_MANAGER_SplitBehavior);
			SetActivityControlFlowAction[] splitActions = new SetActivityControlFlowAction[] {
					new SetActivityControlFlowAction(editor, domain, activity,
							FlowControlType.SPLIT_LITERAL,
							JoinSplitType.NONE_LITERAL),
					new SetActivityControlFlowAction(editor, domain, activity,
							FlowControlType.SPLIT_LITERAL,
							JoinSplitType.XOR_LITERAL),
					new SetActivityControlFlowAction(editor, domain, activity,
							FlowControlType.SPLIT_LITERAL,
							JoinSplitType.AND_LITERAL) };
			for (int i = 0; i < splitActions.length; i++) {
				SetActivityControlFlowAction action = splitActions[i];
				if (action.getControlFlowType().equals(activity.getSplit())) {
					action.setChecked(true);
				}
				if (!ActivityUtil.hasEndEvent(activity)) {
					split.add(action);
				}
			}
			manager.appendToGroup(GEFActionConstants.GROUP_EDIT, split);


         MenuManager implementation = new MenuManager(
               Diagram_Messages.TXT_MENU_MANAGER_Implementation);
         SetActivityImplementationAction[] actions = new SetActivityImplementationAction[] {
               new SetActivityImplementationAction(
                     ActivityImplementationType.ROUTE_LITERAL, activity, domain),
               new SetActivityImplementationAction(
                     ActivityImplementationType.MANUAL_LITERAL, activity, domain),
               new SetActivityImplementationAction(
                     ActivityImplementationType.APPLICATION_LITERAL, activity, domain),
               new SetActivityImplementationAction(
                     ActivityImplementationType.SUBPROCESS_LITERAL, activity, domain)};
         for (int i = 0; i < actions.length; i++ )
         {
            SetActivityImplementationAction action = actions[i];
            if (action.getImplType().equals(activity.getImplementation()))
            {
               action.setChecked(true);
            }
            implementation.add(action);
         }
         manager.appendToGroup(GEFActionConstants.GROUP_EDIT, implementation);


			if (ActivityImplementationType.SUBPROCESS_LITERAL
							.equals(activity.getImplementation())) {
				MenuManager subprocess = new MenuManager(
						Diagram_Messages.WorkflowModelEditorContextMenuProvider_TXT_MENU_MANAGER_Subprocess);

				ResetSubprocessAction resetSubprocessAction = (ResetSubprocessAction) getAction(DiagramActionConstants.RESET_SUBPROCESS);
				resetSubprocessAction.setActivity(activity);
				if (resetSubprocessAction.isEnabled()) {
					subprocess.add(resetSubprocessAction);
					subprocess.add(new Separator());
				}

				for (Iterator iter = ModelUtils.findContainingModel(activity)
						.getProcessDefinition().iterator(); iter.hasNext();) {
					ProcessDefinitionType process = (ProcessDefinitionType) iter
							.next();
					if (!process.equals(activity.eContainer())) {
						SetActivitySubprocessAction action = new SetActivitySubprocessAction(
								activity, process, editor);
						if (process.equals(activity.getImplementationProcess())) {
							action.setChecked(true);
						}
						subprocess.add(action);
					}
				}
				CreateSubprocessAction subprocessAction = (CreateSubprocessAction) getAction(DiagramActionConstants.CREATE_SUBPROCESS);
				subprocessAction.setActivity(activity);
				subprocess.add(subprocessAction);
				manager
						.appendToGroup(GEFActionConstants.GROUP_EDIT,
								subprocess);
			}
		}
	}

	private void addConnectionMenuEntries(IConnectionSymbol connection,
			IMenuManager manager) {
		if (!editor.requireLock(connection)) {
			EditDomain domain = getViewer().getEditDomain();
			MenuManager implementation = new MenuManager(
					Diagram_Messages.WorkflowModelEditorContextMenuProvider_Routing);
			SetConnectionRoutingAction[] actions = new SetConnectionRoutingAction[] {
					new SetConnectionRoutingAction(RoutingType.DEFAULT_LITERAL,
							connection, domain),
					new SetConnectionRoutingAction(
							RoutingType.SHORTEST_PATH_LITERAL, connection,
							domain),
					new SetConnectionRoutingAction(
							RoutingType.MANHATTAN_LITERAL, connection, domain),
					new SetConnectionRoutingAction(
							RoutingType.EXPLICIT_LITERAL, connection, domain) };
			for (int i = 0; i < actions.length; i++) {
				SetConnectionRoutingAction action = actions[i];
				if (action.getImplType().equals(connection.getRouting())) {
					action.setChecked(true);
				}
				implementation.add(action);
			}
			manager
					.appendToGroup(GEFActionConstants.GROUP_EDIT,
							implementation);
		}
	}

	private void addContributedContextMenuActions(IMenuManager manager,
			final IStructuredSelection selection)
	{
		ModelType model = null;
		List<IModelElement> selectedElements = Collections.emptyList();
		List<IGraphicalObject> selectedSymbols = Collections.emptyList();
		List<EObject> selectedObjects = Collections.emptyList();

		if (!selection.isEmpty())
		{
			selectedElements = CollectionUtils.newList();
			selectedSymbols = CollectionUtils.newList();
			selectedObjects = CollectionUtils.newList();

			for (Iterator<?> i = selection.iterator(); i.hasNext();)
			{
				IModelElement selectedElement = null;
				IGraphicalObject selectedSymbol = null;

				Object item = i.next();
				if (item instanceof IAdaptable)
				{
					IModelElement me = (IModelElement) ((IAdaptable) item)
							.getAdapter(IModelElement.class);
					if (me instanceof IGraphicalObject)
					{
						selectedSymbol = (IGraphicalObject) me;
					}
					else
					{
						selectedElement = me;
					}

					if (null != me)
					{
						model = ModelUtils.findContainingModel(me);
					}
					else
					{
						EObject eobject = (EObject) ((IAdaptable) item).getAdapter(EObject.class);
						if (eobject != null)
						{
							selectedObjects.add(eobject);
						}
					}
				}

				if (null != selectedSymbol)
				{
					selectedSymbols.add(selectedSymbol);
					if (selectedSymbol instanceof IModelElementNodeSymbol)
					{
						selectedElement = ((IModelElementNodeSymbol) selectedSymbol)
								.getModelElement();
					}
					else if (selectedSymbol instanceof IConnectionSymbol)
					{
						if (selectedSymbol instanceof TransitionConnectionType)
						{
							selectedElement = ((TransitionConnectionType) selectedSymbol)
									.getTransition();
						}
					}
				}

				if (null != selectedElement)
				{
					selectedElements.add(selectedElement);
				}
			}

			// seal lists
			selectedElements = Collections.unmodifiableList(selectedElements);
			selectedSymbols = Collections.unmodifiableList(selectedSymbols);
			selectedObjects = Collections.unmodifiableList(selectedObjects);
		}

		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						DiagramPlugin.CONTEXT_MENU_ACTION_EXTENSION_POINT);
		for (int i = 0; i < elements.length; i++)
		{
			IConfigurationElement extension = elements[i];

			try
			{
				boolean matchesType = isTypeMatching(selectedElements,
						extension, DiagramPlugin.EP_ATTR_TARGET_ELEMENT_TYPE)
						&& isTypeMatching(selectedSymbols, extension,
								DiagramPlugin.EP_ATTR_TARGET_SYMBOL_TYPE)
						&& isTypeMatching(selectedObjects, extension,
								DiagramPlugin.EP_ATTR_TARGET_EOBJECT_TYPE);

				if (matchesType)
				{
					Object actionImpl = extension
							.createExecutableExtension(DiagramPlugin.EP_ATTR_ACTION_CLASS);
					if (actionImpl instanceof IAction)
					{
						IAction action = (IAction) actionImpl;

						if (actionImpl instanceof EditDomainAwareAction)
						{
							((EditDomainAwareAction) action).setContext(this
									.getViewer().getEditDomain(), model,
									selectedElements, selectedSymbols);
							((EditDomainAwareAction) action).setEditor(editor);
						}

						if ((actionImpl instanceof IGraphicalViewerAwareAction)
								&& (getViewer() instanceof GraphicalViewer))
						{
							((IGraphicalViewerAwareAction) actionImpl)
									.setGraphicalViewer(editor,
											(GraphicalViewer) getViewer());
						}

						if (actionImpl instanceof ISpiAction)
						{
							((ISpiAction) actionImpl).setConfiguration(
									extension, editor, selection);
						}

						if (action.isEnabled())
						{
							String groupName = GEFActionConstants.GROUP_REST;

							String groupId = extension
									.getAttribute(DiagramPlugin.EP_ATTR_GROUP);
							if (!StringUtils.isEmpty(groupId))
							{
								IContributionItem[] menuItems = manager
										.getItems();
								for (int j = 0; j < menuItems.length; j++)
								{
									IContributionItem menuItem = menuItems[j];
									if (menuItem.isGroupMarker()
											&& menuItem.getId().endsWith(
													groupId))
									{
										groupName = menuItem.getId();
										break;
									}
								}
							}

							manager.appendToGroup(groupName, action);
						}
					}
				}
			}
			catch (ClassNotFoundException e)
			{
				// e.printStackTrace();
			}
			catch (CoreException e)
			{
				// e.printStackTrace();
			}
		}
	}

	private boolean isTypeMatching(List searchList,
			IConfigurationElement extension, String attributeName)
			throws ClassNotFoundException {
		boolean matches;
		String typeName = extension.getAttribute(attributeName);
		if (!StringUtils.isEmpty(typeName)) {
			matches = !searchList.isEmpty();
			for (Iterator i = searchList.iterator(); i.hasNext();) {
				matches &= SpiExtensionRegistry.isMatchingClass(i.next(),
						attributeName, extension);
			}
		} else {
			matches = true;
		}
		return matches;
	}

	private String[] getMissingApplicationTypes(List modelApplicationTypes) {
		Map applicationTypesExtensions = SpiExtensionRegistry.instance()
				.getExtensions(
						CarnotConstants.APPLICATION_TYPES_EXTENSION_POINT_ID);
		List<String> result = new ArrayList();
		List<ApplicationTypeType> applicationTypes = new ArrayList<ApplicationTypeType>();
		int k = 0;
		for (Iterator<IConfigurationElement> i = applicationTypesExtensions
				.values().iterator(); i.hasNext();) {
			IConfigurationElement config = i.next();
			boolean found = false;
			for (Iterator<ApplicationTypeType> j = modelApplicationTypes
					.iterator(); j.hasNext();) {
				ApplicationTypeType modelApplicationType = j.next();
				if (modelApplicationType.getId().equals(
						config.getAttribute(SpiConstants.ID))) {
					found = true;
				}
			}
			if (!found) {
				result.add(config.getAttribute(SpiConstants.ID));
			}
		}
		String str[] = (String[]) result.toArray(new String[result.size()]);
		return str;
	}

	private void addMetaTypes(ModelType model, String[] ids,
			String extensionPointId, EClass type, EStructuralFeature[] features) {
		Map extensions = SpiExtensionRegistry.instance().getExtensions(
				extensionPointId);
		for (int i = 0; i < ids.length; i++) {
			IConfigurationElement config = (IConfigurationElement) extensions
					.get(ids[i]);
			CreateMetaTypeCommand command = new CreateMetaTypeCommand(config,
					type, features);
			command.setParent(model);
			command.execute();
		}
	}

   public void dispose()
   {
      if (registry != null)
      {
         registry.dispose();
         registry = null;
      }
      if (localRegistry != null)
      {
         localRegistry.dispose();
         localRegistry = null;
      }
      super.dispose();
   }
}