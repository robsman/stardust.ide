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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;

public class FixInvalidIdsAction
{
   private WorkflowModelEditor editor;
   private IModelElement element;
   
   private boolean autoCreate;
   private boolean autoReplace;
   private boolean autoPrefix;
   
   private InvalidIdentifierDialog replaceDialog;
   private InvalidIdentifierDialog prefixDialog;
   private AutomaticIdentifierDialog noidDialog;
   
   private boolean fixCreate = true;
   private boolean fixReplace = true;
   private boolean fixPrefix = true;

   public FixInvalidIdsAction(WorkflowModelEditor editor, IModelElement element)
   {
      this.editor = editor;
      this.element = element;

      autoCreate = false;
      autoReplace = false;
      autoPrefix = false;
      
      replaceDialog = new InvalidIdentifierDialog(editor.getSite().getShell(), this.element == null, true);
      prefixDialog = new InvalidIdentifierDialog(editor.getSite().getShell(), this.element == null, false);
      noidDialog = new AutomaticIdentifierDialog(editor.getSite().getShell(), this.element == null);
   }

   private void fixAll()
   {
      ModelType model = editor.getWorkflowModel();
      if (model == null)
      {
    	 return;
      }

      CompoundCommand command = new CompoundCommand();
      Set<EObject> visited = new HashSet<EObject>();
      for (Iterator<EObject> itr = model.eAllContents(); itr.hasNext();)
      {
         EObject item = itr.next();
         if (!visited.contains(item))
         {
            visited.add(item);
            if (item instanceof DataType || item instanceof AccessPointType)
            {
               fixIdentifiable(command, (IModelElement) item, false);
            }
         }
      }

      if (command.canExecute())
      {
         editor.getEditDomain().getCommandStack().execute(command);
      }
   }

   private void fixOne()
   {
      CompoundCommand command = new CompoundCommand();
      
      fixIdentifiable(command, element, true);

      if (command.canExecute())
      {
         editor.getEditDomain().getCommandStack().execute(command);
      }
   }

   private void fixIdentifiable(CompoundCommand command, IModelElement identifiable, boolean showLockMessage)
   {
      String id = getId(identifiable);
      // (fh) explicit access points in the engine scope are excluded from change.
      if (identifiable instanceof AccessPointType && id != null && id.startsWith(PredefinedConstants.ENGINE_SCOPE))
      {
         return;
      }
      
      String type = getType(identifiable.eClass());
      String icon = editor.getIconFactory().getIconFor(identifiable);
      if (fixCreate)
      {
         boolean notFixed = false;
         EStructuralFeature feature = identifiable.eContainingFeature();
         EObject econtainer = identifiable.eContainer();
         noidDialog.initialize(econtainer.eGet(feature), icon, type, autoCreate);
         while (id == null || id.length() == 0)
         {
            if (!autoCreate || notFixed)
            {
               switch (noidDialog.open())
               {
               case Window.OK:
                  id = noidDialog.getId();
                  autoCreate = noidDialog.getAutoPerform();
                  break;
               case Window.CANCEL:
                  // no id changes...
                  throw new OperationCanceledException();
               }
            }
            else if (autoCreate)
            {
               noidDialog.updateId();
               id = noidDialog.getId();
            }
            notFixed = true;
         }
      }
      
      if (fixReplace)
      {
         replaceDialog.initialize(id, icon, type, autoReplace);
         boolean notFixed = false;
         while (hasBadCharacters(id))
         {
            if (!autoReplace || notFixed)
            {
               switch (replaceDialog.open())
               {
               case Window.OK:
                  id = replaceDialog.getId();
                  autoReplace = replaceDialog.getAutoPerform();
                  break;
               case Window.CANCEL:
                  // no id changes...
                  throw new OperationCanceledException();
               }
            }
            else if (autoReplace)
            {
               replaceDialog.updateId();
               id = replaceDialog.getId();
            }
            notFixed = true;
         }
      }
      
      if (fixPrefix)
      {
         prefixDialog.initialize(id, icon, type, autoPrefix);
         boolean notFixed = false;
         while (!Character.isJavaIdentifierStart(id.charAt(0)))
         {
            if (!autoPrefix || notFixed)
            {
               switch (prefixDialog.open())
               {
               case Window.OK:
                  id = prefixDialog.getId();
                  autoPrefix = prefixDialog.getAutoPerform();
                  break;
               case Window.CANCEL:
                  // no id changes...
                  throw new OperationCanceledException();
               }
            }
            else if (autoPrefix)
            {
               prefixDialog.updateId();
               id = prefixDialog.getId();
            }
            notFixed = true;
         }
      }
      
      if (!id.equals(getId(identifiable)))
      {
         if (identifiable instanceof DataType || identifiable instanceof AccessPointType)
         {
            command.add(new SetValueCmd(identifiable, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(), id));
         }
      }
   }

   private String getId(EObject element)
   {
      return element instanceof IIdentifiableElement ? ((IIdentifiableElement) element).getId()
           : element instanceof DataMappingType ? ((DataMappingType) element).getId()
           : null;
   }

   private String getType(EClass eClass)
   {
      String name = eClass.getName();
      if (name.length() > 4 && name.endsWith("Type")) //$NON-NLS-1$
      {
         name = name.substring(0, name.length() - 4);
      }
      return name;
   }

   private boolean hasBadCharacters(String id)
   {
      return !ModelUtils.isValidId(id);
   }

   public static void run(WorkflowModelEditor editor)
   {
      try
      {
         new FixInvalidIdsAction(editor, null).fixAll();
      }
      catch (OperationCanceledException oce)
      {
         // graceful exit
      }
   }

   public static void run(WorkflowModelEditor editor, IModelElement element)
   {
      try
      {
         new FixInvalidIdsAction(editor, element).fixOne();
      }
      catch (OperationCanceledException oce)
      {
         // graceful exit
      }
   }
}