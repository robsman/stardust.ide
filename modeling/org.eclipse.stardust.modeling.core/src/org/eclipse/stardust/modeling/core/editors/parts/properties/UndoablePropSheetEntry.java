/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Sungard - Hooked in a special connection routing property command factory
 *             - Adapted functionality to be able to deal with locked model elements
 *******************************************************************************/
package org.eclipse.stardust.modeling.core.editors.parts.properties;

import java.util.EventObject;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.ForwardUndoCompoundCommand;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.BpmUiActivator;
import org.eclipse.stardust.modeling.common.ui.IWorkflowModelEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertySheetEntry;


/**
 * <p>
 * UndoablePropertySheetEntry provides undo support for changes made to IPropertySources
 * by the {@link org.eclipse.ui.views.properties.PropertySheetViewer}. Clients can
 * construct a {@link org.eclipse.ui.views.properties.PropertySheetPage} and use this
 * class as the root entry. All changes made to property sources displayed on that page
 * will be done using the provided command stack.
 * <p>
 * <b>NOTE:</b> If you intend to use an IPropertySourceProvider for a PropertySheetPage
 * whose root entry is an instance of of UndoablePropertySheetEntry, you should set the
 * IPropertySourceProvider on that root entry, rather than the PropertySheetPage.
 */
public final class UndoablePropSheetEntry extends PropertySheetEntry
{
   private static final IPropSheetCmdFactory DEFAULT_CMD_FACTORY = new DefaultPropSheetCmdFactory();

   private CommandStack stack;
   private CommandStackListener commandStackListener;

   private UndoablePropSheetEntry()
   {
   }

   /**
    * Constructs the root entry using the given command stack.
    * 
    * @param stack
    *           the command stack
    * @since 3.1
    */
   public UndoablePropSheetEntry(CommandStack stack)
   {
      setCommandStack(stack);
   }

   /**
    * @see org.eclipse.ui.views.properties.PropertySheetEntry#createChildEntry()
    */
   protected PropertySheetEntry createChildEntry()
   {
      return new UndoablePropSheetEntry();
   }

   /**
    * @see org.eclipse.ui.views.properties.IPropertySheetEntry#dispose()
    */
   public void dispose()
   {
      if (stack != null)
      {
         stack.removeCommandStackListener(commandStackListener);
      }
      super.dispose();
   }

   CommandStack getCommandStack()
   {
      // only the root has, and is listening too, the command stack
      if (getParent() != null)
      {
         return ((UndoablePropSheetEntry) getParent()).getCommandStack();
      }
      return stack;
   }

   void setCommandStack(CommandStack stack)
   {
      this.stack = stack;
      commandStackListener = new CommandStackListener()
      {
         public void commandStackChanged(EventObject e)
         {
            refreshFromRoot();
         }
      };
      stack.addCommandStackListener(commandStackListener);
   }

   /**
    * @see org.eclipse.ui.views.properties.IPropertySheetEntry#resetPropertyValue()
    */
   public void resetPropertyValue()
   {
      CompoundCommand cc = new CompoundCommand();

      if (getParent() == null)
      {
         // root does not have a default value
         return;
      }
      else
      {
         // Use our parent's values to reset our values.
         boolean change = false;
         Object[] objects = getParent().getValues();
         for (int i = 0; i < objects.length; i++ )
         {
            // TODO
            IPropSheetCmdFactory cmdFactory = null;
            if (getDescriptor() instanceof IAdaptable)
            {
               cmdFactory = (IPropSheetCmdFactory) ((IAdaptable) getDescriptor()).getAdapter(IPropSheetCmdFactory.class);
            }
            if (null == cmdFactory)
            {
               cmdFactory = DEFAULT_CMD_FACTORY;
            }

            IPropertySource source = getPropertySource(objects[i]);
            if (source.isPropertySet(getDescriptor().getId()))
            {
               // source.resetPropertyValue(getDescriptor()getId());
               /*
                * restoreCmd = new ResetValueCommand(); restoreCmd.setTarget(source);
                * restoreCmd.setPropertyId(getDescriptor().getId()); cc.add(restoreCmd);
                */
               cc.add(cmdFactory.createResetValueCommand(getDescriptor(), source));

               change = true;
            }
         }
         if (change)
         {
            getCommandStack().execute(cc);
            refreshFromRoot();
         }
      }
   }

   /**
    * @see PropertySheetEntry#valueChanged(PropertySheetEntry)
    */
   protected void valueChanged(PropertySheetEntry child)
   {
      valueChanged((UndoablePropSheetEntry) child, new ForwardUndoCompoundCommand());
   }

   void valueChanged(UndoablePropSheetEntry child, CompoundCommand command)
   {
      CompoundCommand cc = new CompoundCommand();
      command.add(cc);

      for (int i = 0; i < getValues().length; i++ )
      {
         IPropSheetCmdFactory cmdFactory = null;
         if (child.getDescriptor() instanceof IAdaptable)
         {
            cmdFactory = (IPropSheetCmdFactory) ((IAdaptable) child.getDescriptor()).getAdapter(IPropSheetCmdFactory.class);
         }
         if (null == cmdFactory)
         {
            cmdFactory = DEFAULT_CMD_FACTORY;
         }

         /*
          * setCommand = new SetValueCommand(child.getDisplayName());
          * setCommand.setTarget(getPropertySource(getValues()[i]));
          * setCommand.setPropertyId(child.getDescriptor().getId());
          * setCommand.setPropertyValue(child.getValues()[i]); cc.add(setCommand);
          */
         cc.add(cmdFactory.createSetValueCommand(child, child.getDescriptor(),
               getPropertySource(getValues()[i]), child.getValues()[i]));
      }

      // inform our parent
      if (getParent() != null)
      {
         ((UndoablePropSheetEntry) getParent()).valueChanged(this, command);
      }
      else
      {
         // I am the root entry
         stack.execute(command);
      }
   }

   @Override
   public CellEditor getEditor(Composite parent)
   {
      UndoablePropSheetEntry entry = this;
      while (entry.getParent() != null)
      {
         entry = (UndoablePropSheetEntry) entry.getParent();
      }
      Object object = entry.getEditValue(0);
      if (object instanceof EObject)
      {
         EObject eObject = (EObject) object;
         ModelType model = ModelUtils.findContainingModel(eObject);
         if (model != null)
         {
            IWorkflowModelEditor editor = BpmUiActivator.findWorkflowModelEditor(model);
            if (editor != null && editor.requireLock(eObject))
            {
               return null;
            }
         }
      }
      return super.getEditor(parent);
   }
}
