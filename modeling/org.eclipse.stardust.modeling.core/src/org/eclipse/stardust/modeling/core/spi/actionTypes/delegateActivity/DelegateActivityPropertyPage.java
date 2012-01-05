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
package org.eclipse.stardust.modeling.core.spi.actionTypes.delegateActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IActionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.ActionTypeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;


public class DelegateActivityPropertyPage extends AbstractModelElementPropertyPage
      implements IActionPropertyPage
{
   private static final String RANDOM_USER = Diagram_Messages.RANDOM_USER;

   private static final String PARTICIPANT = Diagram_Messages.PARTICIPANT;

   private static final String CURRENT_USER = Diagram_Messages.CURRENT_USER;

   private static final String DEFAULT_PERFORMER = Diagram_Messages.DEFAULT_PERFORMER;

   private static final String SEND_TO_WORKLIST = Diagram_Messages.SEND_TO_WORKLIST;

   private static final String RANDOM_USER_VAL = "randomUser"; //$NON-NLS-1$

   private static final String PARTICIPANT_VAL = "participant"; //$NON-NLS-1$

   private static final String CURRENT_USER_VAL = "currentUser"; //$NON-NLS-1$

   private static final String DEFAULT_PERFORMER_VAL = "defaultPerformer"; //$NON-NLS-1$

   private static final String DELEGATE_ACTIVITY_TYPE = "delegateActivity"; //$NON-NLS-1$

   private Button defaultPerformerButton;

   private Button currentUserButton;

   private Button participantButton;

   private Button randomUserButton;

   private ListViewer vwParticipants;

   private Composite participantComposite;

   private StackLayout participantCompLayout;

   private Composite emptyComposite;

   private Composite listComposite;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (ActionTypeUtil.getActionType(element).getId().equals(DELEGATE_ACTIVITY_TYPE)
            && (defaultPerformerButton != null))
      {

         IModelParticipant target = (IModelParticipant) AttributeUtil.getIdentifiable(
               (IExtensibleElement) element, CarnotConstants.TARGET_ATT);
         String targetWorklistAttr = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, CarnotConstants.TARGET_WORKLIST_ATT);
         initParticipantList(element, target);
         if (targetWorklistAttr != null)
         {

            defaultPerformerButton.setSelection(targetWorklistAttr
                  .equals(DEFAULT_PERFORMER_VAL));
            currentUserButton.setSelection(targetWorklistAttr.equals(CURRENT_USER_VAL));
            participantButton.setSelection(targetWorklistAttr.equals(PARTICIPANT_VAL));
            randomUserButton.setSelection(targetWorklistAttr.equals(RANDOM_USER_VAL));
            if (participantButton.getSelection())
            {
               setListComposite();
            }
         }
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (ActionTypeUtil.getActionType(element).getId().equals(DELEGATE_ACTIVITY_TYPE))
      {
         if (defaultPerformerButton != null)
         {
            ((IExtensibleElement) element).getAttribute().clear();
            String value = ""; //$NON-NLS-1$
            IModelParticipant participantValue = null; //$NON-NLS-1$
            if (defaultPerformerButton.getSelection())
            {
               value = DEFAULT_PERFORMER_VAL;
            }
            else if (currentUserButton.getSelection())
            {
               value = CURRENT_USER_VAL;
            }
            else if (participantButton.getSelection())
            {
               value = PARTICIPANT_VAL;
               
               ISelection selection = vwParticipants.getSelection();
               if ( !selection.isEmpty() && (selection instanceof IStructuredSelection))
               {
                  Object selectedParticipant = ((IStructuredSelection) selection).getFirstElement();
                  if (selectedParticipant instanceof IModelParticipant)
                  {
                     participantValue = (IModelParticipant) selectedParticipant;
                  }
               }
            }
            else if (randomUserButton.getSelection())
            {
               value = RANDOM_USER_VAL;
            }

            AttributeUtil.setReference((IExtensibleElement) element,
                  CarnotConstants.TARGET_ATT, participantValue);
            AttributeUtil.setAttribute((IExtensibleElement) element,
                  CarnotConstants.TARGET_WORKLIST_ATT,
                  CarnotConstants.TARGET_WORKLIST_TYPE_ATT, value);
         }
      }
   }

   public Control createBody(Composite parent)
   {
      Group sendToWorklistGroup = FormBuilder.createGroup(parent, SEND_TO_WORKLIST, 4);
      ((GridLayout) sendToWorklistGroup.getLayout()).marginHeight = 10;
      sendToWorklistGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1,
            1));

      defaultPerformerButton = FormBuilder.createRadioButton(sendToWorklistGroup,
            DEFAULT_PERFORMER);
      defaultPerformerButton.setSelection(true);
      currentUserButton = FormBuilder
            .createRadioButton(sendToWorklistGroup, CURRENT_USER);
      participantButton = FormBuilder.createRadioButton(sendToWorklistGroup, PARTICIPANT);
      randomUserButton = FormBuilder.createRadioButton(sendToWorklistGroup, RANDOM_USER);

      participantComposite = FormBuilder.createComposite(parent, 1, 2);
      participantCompLayout = new StackLayout();
      participantComposite.setLayout(participantCompLayout);

      emptyComposite = FormBuilder.createComposite(participantComposite, 1);
      listComposite = FormBuilder.createComposite(participantComposite, 1);
      this.vwParticipants = new ListViewer(FormBuilder.createList(listComposite));
      vwParticipants.setContentProvider(new ArrayContentProvider());
      vwParticipants.setLabelProvider(new EObjectLabelProvider(getEditor()));
      vwParticipants.setSorter(new ViewerSorter());

      participantCompLayout.topControl = emptyComposite;

      defaultPerformerButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setEmptyComposite();
         }
      });
      currentUserButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setEmptyComposite();
         }
      });

      participantButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setListComposite();
         }
      });
      randomUserButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setEmptyComposite();
         }
      });

      return parent;
   }

   private void setEmptyComposite()
   {
      participantCompLayout.topControl = emptyComposite;
      participantComposite.layout();
   }

   private void initParticipantList(IModelElement element, IModelParticipant target)
   {
      java.util.List participants = new ArrayList();
      
      ActivityType activityType = ModelUtils.findContainingActivity(element);
      if (null != activityType.getPerformer())
      {
         for (Iterator iter = getActivityPerformer(activityType.getPerformer())
               .iterator(); iter.hasNext();)
         {
            IModelParticipant modelParticipant = (IModelParticipant) iter.next();
            participants.add(modelParticipant);
         }
      }
      
      vwParticipants.setInput(participants);
      vwParticipants.setSelection(target == null ? StructuredSelection.EMPTY
            : new StructuredSelection(target), true);
   }

   private java.util.List/* <IModelParticipant> */getActivityPerformer(
         IModelParticipant performer)
   {
      java.util.List participants = new ArrayList();
      getReferencedPerformer(performer, participants, new HashSet());

      return participants;
   }

   private static void getReferencedPerformer(IModelParticipant performer,
         java.util.List participants, Set visitedParticipants)
   {
      if ((null != performer) && !visitedParticipants.contains(performer))
      {
         participants.add(performer);
         visitedParticipants.add(performer);
         
         if (performer instanceof OrganizationType)
         {
            for (Iterator iter = ((OrganizationType) performer).getParticipant().iterator(); iter.hasNext();)
            {
               ParticipantType ref = (ParticipantType) iter.next();
               getReferencedPerformer(ref.getParticipant(), participants,
                     visitedParticipants);
            }
         }
      }
   }

   private void setListComposite()
   {
      participantCompLayout.topControl = listComposite;
      participantComposite.layout();
   }
}
