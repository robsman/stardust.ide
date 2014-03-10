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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.search.ElementReferenceSearcher;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class DeleteExternalReferenceAction extends SelectionAction
{
   private List<ExternalPackage> selectedPackages;

   private WorkflowModelEditor editor;

   private ModelType modelType;

   public DeleteExternalReferenceAction(WorkflowModelEditor part)
   {
      super(part);
      setId(ObjectRepositoryActivator.DELETE_EXTERNAL_REFERENCES_ACTION);
      setText(Diagram_Messages.TXT_DELETE);
      ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
      setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
      editor = part;
      modelType = (ModelType) editor.getModel();
   }

   protected boolean calculateEnabled()
   {
      selectedPackages = getSelectedPackages();
      if (selectedPackages != null && selectedPackages.size() == 1)
      {
         return true;
      }
      return false;
   }

   @SuppressWarnings("unchecked")
   private List<ExternalPackage> getSelectedPackages()
   {
      if (getSelectedObjects().size() == 0)
         return null;
      List<Object> selection = getSelectedObjects();
      Object entry;
      List<ExternalPackage> result = new ArrayList<ExternalPackage>();
      for (int i = 0; i < selection.size(); i++)
      {
         entry = getSelectedObjects().get(i);
         if (entry instanceof EditPart)
         {
            Object model = ((EditPart) entry).getModel();
            if (model instanceof ExternalPackage)
            {
               result.add((ExternalPackage) model);
            }
            else
            {
               return null;
            }
         }
      }
      return result.size() == 0 ? null : result;
   }

   public void run()
   {
      MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
            SWT.ICON_WARNING | SWT.OK | SWT.CANCEL);
      messageBox.setText(Diagram_Messages.TXT_WR);
      String message = Diagram_Messages.MSG_THIS_OPERATION_WILL_REMOVE_ALL_REFERENCES_TO_NULL_FROM_MODEL;
      messageBox.setMessage(MessageFormat.format(message, new Object[]{((ExternalPackage) selectedPackages.get(0)).getHref()}));
      if (messageBox.open() == SWT.OK)
      {
         EditDomain domain = editor.getEditDomain();
         CompoundCommand command = new CompoundCommand();

         ChangeRecorder targetRecorder = new ChangeRecorder();
         targetRecorder.beginRecording(Collections.singleton(modelType.eContainer()));
         removeReferences();
         final ChangeDescription change = targetRecorder.endRecording();
         targetRecorder.dispose();

         Command cmd = new Command()
         {
            public void execute()
            {}

            public void undo()
            {
               change.applyAndReverse();
            }

            public void redo()
            {
               change.applyAndReverse();
            }
         };
         command.add(cmd);
         domain.getCommandStack().execute(command);

      }

   }

   private void removeReferences()
   {
      ElementReferenceSearcher searcher = new ElementReferenceSearcher();
      for (Iterator<ExternalPackage> i = selectedPackages.iterator(); i.hasNext();)
      {
         ArrayList<Object> list = new ArrayList<Object>();
         ExternalPackage externalPackage = i.next();
         Map resultMap = searcher.search(externalPackage);
         for (Iterator j = resultMap.values().iterator(); j.hasNext();)
         {
            Object o = j.next();
            if (o instanceof ArrayList)
            {
               List elements = (ArrayList) o;
               for (Iterator k = elements.iterator(); k.hasNext();)
               {
                  list.add(k.next());
               }
            }
            else
            {
               list.add(o);
            }
         }
         for (Iterator j = list.iterator(); j.hasNext();)
         {
            Object o = j.next();
            if (o instanceof DataType)
            {
               DataType dataType = (DataType) o;
               AttributeUtil.setAttribute(dataType.getAttribute(),
                     IConnectionManager.URI_ATTRIBUTE_NAME, null);
               dataType.setExternalReference(null);
            }
            if (o instanceof ProcessDefinitionType)
            {
               ProcessDefinitionType processDefinitionType = (ProcessDefinitionType) o;
               processDefinitionType.setExternalRef(null);
            }
            if (o instanceof ActivityType)
            {
               ActivityType activityType = (ActivityType) o;
               activityType.setExternalRef(null);
            }
         }
         modelType.getExternalPackages().getExternalPackage().remove(externalPackage);
         if (modelType.getExternalPackages().getExternalPackage().isEmpty())
         {
            modelType.setExternalPackages(null);
         }
      }
   }
}