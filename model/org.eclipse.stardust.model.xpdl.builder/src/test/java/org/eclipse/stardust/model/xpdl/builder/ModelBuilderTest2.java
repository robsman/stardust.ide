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
package org.eclipse.stardust.model.xpdl.builder;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ADMINISTRATOR_ROLE;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.DEFAULT_CONTEXT;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;

import org.eclipse.stardust.model.xpdl.builder.BpmActivityDef;
import org.eclipse.stardust.model.xpdl.builder.BpmActivitySequenceDef;
import org.eclipse.stardust.model.xpdl.builder.BpmModelDef;
import org.eclipse.stardust.model.xpdl.builder.BpmProcessDef;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.junit.Before;
import org.junit.Test;


public class ModelBuilderTest2
{

   private ModelType model;

   @Before
   public void initSimpleModel()
   {
      // specify transitions explicitly
      this.model = newBpmModel().withName("Test Model").definedAs(new BpmModelDef()
      {{
         primitiveVariable().withId("aString").ofType(String.class);

         processDefinition().withIdAndName("TEST_PROCESS", "Test Process").definedAs(new BpmProcessDef()
         {{
            manualTrigger().accessibleTo(ADMINISTRATOR_ROLE);

            manualActivity().withId("Activity1").havingDefaultPerformer(ADMINISTRATOR_ROLE).definedAs(new BpmActivityDef()
            {{
               outMapping().inContext(DEFAULT_CONTEXT).toVariable("aString");
            }});

            manualActivity().withId("Activity2").havingDefaultPerformer(ADMINISTRATOR_ROLE).definedAs(new BpmActivityDef()
            {{
               inMapping().inContext(DEFAULT_CONTEXT).fromVariable("aString");
            }});

            transition().from("Activity1").to("Activity2");
         }});
      }}).build();

      // don't specify transitions explicitly
      this.model = newBpmModel().withName("Test Model").definedAs(new BpmModelDef()
      {{
         primitiveVariable().withId("aString").ofType(String.class);

         processDefinition().withIdAndName("TEST_PROCESS", "Test Process").definedAs(new BpmProcessDef()
         {{
            manualTrigger().accessibleTo(ADMINISTRATOR_ROLE);

            activitySequence().definedAs(new BpmActivitySequenceDef()
            {{
               manualActivity().withId("Activity1").havingDefaultPerformer(ADMINISTRATOR_ROLE).definedAs(new BpmActivityDef()
               {{
                  outMapping().inContext(DEFAULT_CONTEXT).toVariable("aString");
               }});

               manualActivity().withId("Activity2").havingDefaultPerformer(ADMINISTRATOR_ROLE).definedAs(new BpmActivityDef()
               {{
                  inMapping().inContext(DEFAULT_CONTEXT).fromVariable("aString");
               }});
            }});
         }});
      }}).build();
   }

   @Test
   public void verifyStringVariable()
   {
      DataType aString = XpdlModelUtils.findElementById(model.getData(), "aString");

      assertNotNull(aString);
      assertTrue(aString.isSetElementOid());
   }

}
