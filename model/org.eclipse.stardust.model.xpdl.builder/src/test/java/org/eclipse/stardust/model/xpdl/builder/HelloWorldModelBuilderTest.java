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

import static junit.framework.Assert.assertTrue;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ADMINISTRATOR_ROLE;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.DEFAULT_CONTEXT;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.eclipse.stardust.model.xpdl.builder.BpmActivityDef;
import org.eclipse.stardust.model.xpdl.builder.BpmActivitySequenceDef;
import org.eclipse.stardust.model.xpdl.builder.BpmModelDef;
import org.eclipse.stardust.model.xpdl.builder.BpmProcessDef;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.junit.Before;
import org.junit.Test;


public class HelloWorldModelBuilderTest
{

   private ModelType model;

   @Before
   public void initHelloWorldModel()
   {
      // specify transitions explicitly
      this.model = newBpmModel().withName("Hello-World Model").definedAs(new BpmModelDef()
      {{
         primitiveVariable().withId("name").ofType(String.class);

         processDefinition().withIdAndName("HELLO_WORLD", "Hello World").definedAs(new BpmProcessDef()
         {{
            manualTrigger().accessibleTo(ADMINISTRATOR_ROLE);

            manualActivity().withIdAndName("Activity1", "Who are you?").havingDefaultPerformer(ADMINISTRATOR_ROLE)
                  .definedAs(new BpmActivityDef()
            {{
               outMapping().inContext(DEFAULT_CONTEXT).toVariable("name");
            }});

            manualActivity().withIdAndName("Activity2", "Greeting").havingDefaultPerformer(ADMINISTRATOR_ROLE)
                  .definedAs(new BpmActivityDef()
            {{
               inMapping().inContext(DEFAULT_CONTEXT).fromVariable("name");
            }});

            transition().from("Activity1").to("Activity2").unconditionally();
         }});
      }}).build();

      // don't specify transitions explicitly
      this.model = newBpmModel().withName("Hello-World Model").definedAs(new BpmModelDef()
      {{
         primitiveVariable().withId("Name").ofType(String.class);

         processDefinition().withIdAndName("HELLO_WORLD", "Hello World")
               .definedAs(new BpmProcessDef()
         {{
            manualTrigger().accessibleTo(ADMINISTRATOR_ROLE);

            activitySequence().definedAs(new BpmActivitySequenceDef()
            {{
               manualActivity().withName("Who are you?")
                     .havingDefaultPerformer(ADMINISTRATOR_ROLE)
                     .definedAs(new BpmActivityDef()
               {{
                  outMapping().inContext(DEFAULT_CONTEXT).toVariable("Name");
               }});

               manualActivity().withName("Greeting")
                     .havingDefaultPerformer(ADMINISTRATOR_ROLE)
                     .definedAs(new BpmActivityDef()
               {{
                  inMapping().inContext(DEFAULT_CONTEXT).fromVariable("Name");
               }});
            }});
         }});
      }}).build();

      byte[] modelContent = XpdlModelIoUtils.saveModel(model);
      System.out.println(new String(modelContent));
   }

   @Test
   public void verifyStringVariable()
   {
      DataType aString = XpdlModelUtils.findElementById(model.getData(), "Name");

      assertThat(aString, not(is(nullValue())));
      assertTrue(aString.isSetElementOid());
   }

}
