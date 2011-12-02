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
package org.eclipse.stardust.modeling.authorization.propertypages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ScopeUtils;
import org.eclipse.stardust.modeling.authorization.AuthorizationUtils;
import org.eclipse.stardust.modeling.authorization.Authorization_Messages;
import org.eclipse.stardust.modeling.authorization.Constants;
import org.eclipse.stardust.modeling.authorization.Permission;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;


public class AuthorizationPropertyPage extends PropertyPage implements IWorkbenchPropertyPage
{
   private static final String[] PERMISSION_COLUMN_NAME = {
      Authorization_Messages.PERMISSION_COLUMN_LABEL
   };
   
   private static final String[] PARTICIPANT_COLUMN_NAME = {
      Authorization_Messages.PARTICIPANT_COLUMN_LABEL 
   };
   
   private TableViewer permissionViewer;
   private TableViewer participantViewer;
   private ParticipantLabelProvider participantLabelProvider;
   
   private List<Object> participants;
   private ArrayList<Permission> permissions;
   
   private Permission permission;
   
   protected Control createContents(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
      
      Table permissionTable = FormBuilder.createTable(composite, SWT.SINGLE | SWT.FULL_SELECTION
            | SWT.BORDER, PERMISSION_COLUMN_NAME, new int[] {100}, 1);
      permissionViewer = new TableViewer(permissionTable);
      permissionViewer.setContentProvider(new ArrayContentProvider());
      permissionViewer.setLabelProvider(new PermissionLabelProvider());
      permissionViewer.setInput(getPermissions());
      permissionViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            //if(isValid())
            //{
               updateParticipantsTable();
               validate();            
            //}
         }
      });
      
      Table participantTable = FormBuilder.createTable(composite, SWT.SINGLE | SWT.FULL_SELECTION
            | SWT.BORDER | SWT.CHECK, PARTICIPANT_COLUMN_NAME, new int[] {100}, 1);
      participantViewer = new TableViewer(participantTable);
      participantViewer.setContentProvider(new ArrayContentProvider());
      participantLabelProvider = new ParticipantLabelProvider(participantTable.getFont());
      participantViewer.setLabelProvider(participantLabelProvider);
      participantViewer.setInput(Collections.EMPTY_LIST);
      participantTable.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {/*ignore*/}

         public void widgetSelected(SelectionEvent e)
         {
            if (e.detail == SWT.CHECK)
            {
               TableItem item = (TableItem) e.item;
               Object participant = item.getData();
               if (Constants.ALL_PARTICIPANT == participant)
               {
                  if (item.getChecked())
                  {
                     permission.setALL();
                  }
                  else
                  {
                     permission.unsetALL();
                  }
               }
               else if (Constants.OWNER_PARTICIPANT == participant)
               {
                  if (item.getChecked())
                  {
                     permission.setOWNER();
                  }
                  else
                  {
                     permission.unsetOWNER();
                  }
               }
               else if (participant instanceof IModelParticipant)
               {
                  if (item.getChecked())
                  {
                     permission.addParticipant((IModelParticipant) participant);
                  }
                  else
                  {
                     permission.removeParticipant((IModelParticipant) participant);
                  }
               }
               updateCheckStatus();
               validate();               
            }
         }
      });
      validate();
      
      return composite;
   }

   private void validate()
   {
      boolean isValid = true;
      
      IModelElement element = getModel();
      if(element instanceof ModelType)
      {
         HashSet<IModelParticipant> scoped = ScopeUtils.findScopedParticipants((ModelType) element);
         for(IModelParticipant participant : scoped)
         {
            if(permission != null && permission.contains(participant))
            {
               isValid = false;
               break;
            }            
         }         
      }
      
      if(isValid)
      {
         setErrorMessage(null);
         setValid(true);         
      }
      else
      {
         setValid(false);                  
         setErrorMessage(Authorization_Messages.ERR_MSG_SCOPED_PARTICIPANTS_ARE_NOT_ALLOWED_FOR_MODEL_LEVEL_GRANTS);      
      }      
   }

   private List<Object> getParticipants()
   {
      if (participants == null)
      {
         Comparator<IModelParticipant> comparator = new Comparator<IModelParticipant>()
         {
            public int compare(IModelParticipant first, IModelParticipant second)
            {
               return toString(first).compareTo(toString(second));
            }
   
            private String toString(IModelParticipant o)
            {
               String label = o.getName();
               if (label == null)
               {
                  label = o.getId();
               }
               return label == null ? "" : label; //$NON-NLS-1$
            }
         };
   
         participants = new ArrayList<Object>();
         participants.add(Constants.ALL_PARTICIPANT);
   
         if (AuthorizationUtils.isInteractiveActivity((IExtensibleElement) getModel()))
         {
            participants.add(Constants.OWNER_PARTICIPANT);
         }
         
         IModelElement element = getModel();
         ModelType model = ModelUtils.findContainingModel(element);
         
         ArrayList<IModelParticipant> roles = new ArrayList<IModelParticipant>();
         roles.addAll(model.getRole());
         Collections.sort(roles, comparator);
         participants.addAll(roles);
         
         ArrayList<IModelParticipant> organizations = new ArrayList<IModelParticipant>();
         organizations.addAll(model.getOrganization());
         Collections.sort(organizations, comparator);
         participants.addAll(organizations);
      }
      return participants;
   }

   private List<Permission> getPermissions()
   {
      if (permissions == null)
      {
         permissions = (ArrayList<Permission>) AuthorizationUtils.getPermissions((IExtensibleElement) getModel());
      }
      return permissions;
   }

   private IModelElement getModel()
   {
      IModelElement element = null;
      IAdaptable adaptable = getElement();
      if (adaptable != null)
      {
         element = (IModelElement) adaptable.getAdapter(IModelElement.class);
         if (element instanceof IModelElementNodeSymbol)
         {
            element = ((IModelElementNodeSymbol) element).getModelElement();
         }
      }
      return element;
   }

   public boolean performOk()
   {
      IExtensibleElement element = (IExtensibleElement) getModel();
      if (element == null) {
         return true;
      }
      List<AttributeType> attributes = element.getAttribute();
      for (int i = attributes.size() - 1; i >= 0; i--)
      {
         AttributeType attribute = attributes.get(i);
         if (attribute.getName() != null && attribute.getName().startsWith(Permission.SCOPE))
         {
            attributes.remove(i);
         }
      }
      for (Permission permission : getPermissions())
      {
         permission.save(element);
      }
      return true;
   }

   protected void performDefaults()
   {
      for (Permission permission : permissions)
      {
         permission.restoreDefaults();
      }
      updateParticipantsTable();
      super.performDefaults();
   }

   private void updateParticipantsTable()
   {
      IStructuredSelection selection = (IStructuredSelection) permissionViewer.getSelection();
      permission = (Permission) selection.getFirstElement();
      if (permission == null)
      {
         participantViewer.setInput(Collections.EMPTY_LIST);
      }
      else
      {
         participantLabelProvider.setPermission(permission);
         participantViewer.setInput(getParticipants());
         updateCheckStatus();
      }
   }

   private void updateCheckStatus()
   {
      List<Object> participants = getParticipants();

      TableItem[] items = participantViewer.getTable().getItems();
      items[0].setChecked(permission.isALL());

      boolean isInteractiveActivity = AuthorizationUtils.isInteractiveActivity((IExtensibleElement) getModel());
      if (isInteractiveActivity)
      {
         items[1].setChecked(permission.isOWNER());
      }
      for (int i = isInteractiveActivity ? 2 : 1; i < participants.size(); i++)
      {
         IModelParticipant participant = (IModelParticipant) participants.get(i);
         items[i].setChecked(permission.contains(participant));
      }
   }
}