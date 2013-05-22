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
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.eclipse.stardust.model.xpdl.builder.process.BpmProcessDefinitionBuilder.newProcessDefinition;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.junit.Test;


public class TestChangeUndoRedo
{

   @Test
   public void undoableCreateProcess()
   {
      ModelType model = newBpmModel().withIdAndName("test", "test").build();

      EditingSession editSession = new EditingSession();
      editSession.trackModel(model);

      assertEquals(0, model.getProcessDefinition().size());


      editSession.beginEdit();
      assertTrue(editSession.isInEditMode());
      newProcessDefinition(model).withIdAndName("PD_1", "Test Process 1").build();
      editSession.endEdit();
      assertFalse(editSession.isInEditMode());

      assertEquals(1, model.getProcessDefinition().size());
      assertNotNull(model.getProcessDefinition().get(0));

      editSession.undoLast();
      assertEquals(0, model.getProcessDefinition().size());

      editSession.redoNext();
      assertEquals(1, model.getProcessDefinition().size());

      assertNotNull(ModelUtils.findElementById(model.getProcessDefinition(), "PD_1"));
      assertNull(ModelUtils.findElementById(model.getProcessDefinition(), "My_Process"));

      editSession.beginEdit();
      ModelUtils.findElementById(model.getProcessDefinition(), "PD_1").setId("My_Process");
      editSession.endEdit();

      assertNull(ModelUtils.findElementById(model.getProcessDefinition(), "PD_1"));
      assertNotNull(ModelUtils.findElementById(model.getProcessDefinition(), "My_Process"));

      editSession.undoLast();
      assertNotNull(ModelUtils.findElementById(model.getProcessDefinition(), "PD_1"));
      assertNull(ModelUtils.findElementById(model.getProcessDefinition(), "My_Process"));

      editSession.undoLast();
      assertNull(ModelUtils.findElementById(model.getProcessDefinition(), "PD_1"));
      assertNull(ModelUtils.findElementById(model.getProcessDefinition(), "My_Process"));

      editSession.redoNext();
      assertNotNull(ModelUtils.findElementById(model.getProcessDefinition(), "PD_1"));
      assertNull(ModelUtils.findElementById(model.getProcessDefinition(), "My_Process"));

      editSession.redoNext();
      assertNull(ModelUtils.findElementById(model.getProcessDefinition(), "PD_1"));
      assertNotNull(ModelUtils.findElementById(model.getProcessDefinition(), "My_Process"));
   }

}
