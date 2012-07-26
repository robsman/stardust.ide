/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.session;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.eclipse.stardust.model.xpdl.builder.process.BpmProcessDefinitionBuilder.newProcessDefinition;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class TestAttributeChangeUndoRedo
{

   @Test
   public void undoableCreateProcessWithAttributes()
   {
      ModelType model = newBpmModel().withIdAndName("test", "test").build();

      EditingSession editSession = new EditingSession();
      editSession.trackModel(model);

      assertEquals(0, model.getProcessDefinition().size());

      editSession.beginEdit();
      assertTrue(editSession.isInEditMode());
      ProcessDefinitionType pd1 = newProcessDefinition(model).withIdAndName("PD_1",
            "Test Process 1").build();
      editSession.endEdit();
      assertFalse(editSession.isInEditMode());

      editSession.beginEdit();
      assertTrue(editSession.isInEditMode());
      AttributeUtil.setAttribute(pd1, "intAttr", Type.Integer.getId(),
            Integer.toString(42));
      editSession.endEdit();
      assertFalse(editSession.isInEditMode());
      assertThat(editSession.getPendingUndo().changedObjects().size(), is(1));
      assertTrue(editSession.getPendingUndo().changedObjects().contains(pd1));

      traceChanges(editSession);

      editSession.beginEdit();
      assertTrue(editSession.isInEditMode());
      AttributeUtil.setAttribute(pd1, "intAttr", Type.Integer.getId(),
            Integer.toString(8));
      editSession.endEdit();
      assertFalse(editSession.isInEditMode());
      assertThat(editSession.getPendingUndo().changedObjects().size(), is(1));
      assertTrue(editSession.getPendingUndo().changedObjects().contains(pd1));

      traceChanges(editSession);

      editSession.beginEdit();
      assertTrue(editSession.isInEditMode());
      AttributeUtil.setAttribute(pd1, "stringAttr", Type.String.getId(), "bla");
      editSession.endEdit();
      assertFalse(editSession.isInEditMode());
      assertThat(editSession.getPendingUndo().changedObjects().size(), is(1));
      assertTrue(editSession.getPendingUndo().changedObjects().contains(pd1));

      traceChanges(editSession);

      editSession.beginEdit();
      assertTrue(editSession.isInEditMode());
      AttributeUtil.clearExcept(pd1, new String[0]);
      editSession.endEdit();
      assertFalse(editSession.isInEditMode());
      assertThat(editSession.getPendingUndo().changedObjects().size(), is(1));
      assertTrue(editSession.getPendingUndo().changedObjects().contains(pd1));

      traceChanges(editSession);

      editSession.beginEdit();
      assertTrue(editSession.isInEditMode());
      pd1.setDescription(CarnotWorkflowModelFactory.eINSTANCE.createDescriptionType());
      ModelUtils.setCDataString(pd1.getDescription().getMixed(), "bla");
      editSession.endEdit();
      assertFalse(editSession.isInEditMode());
      assertThat(editSession.getPendingUndo().changedObjects().size(), is(1));
      assertTrue(editSession.getPendingUndo().changedObjects().contains(pd1));

      traceChanges(editSession);

      editSession.beginEdit();
      assertTrue(editSession.isInEditMode());
      ModelUtils.setCDataString(pd1.getDescription().getMixed(), "blub");
      editSession.endEdit();
      assertFalse(editSession.isInEditMode());
      assertThat(editSession.getPendingUndo().changedObjects().size(), is(1));
      assertTrue(editSession.getPendingUndo().changedObjects().contains(pd1));

      traceChanges(editSession);
   }

   public void traceChanges(EditingSession editSession)
   {
      for (EObject changed : editSession.getPendingUndo().changedObjects())
      {
         System.out.println("Changed: " + changed);
      }
      for (EObject changed : editSession.getPendingUndo().addedObjects())
      {
         System.out.println("Added: " + changed);
      }
      for (EObject changed : editSession.getPendingUndo().removedObjects())
      {
         System.out.println("Removed: " + changed);
      }
   }

}
