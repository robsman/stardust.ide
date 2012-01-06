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
package org.eclipse.stardust.modeling.deploy;

import java.io.IOException;
import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.config.ParametersFacade;
import org.eclipse.stardust.common.utils.xml.XmlUtils;
import org.eclipse.stardust.engine.api.runtime.DeploymentElement;

import ag.carnot.workflow.model.xpdl.XpdlUtils;
import ag.carnot.workflow.runtime.beans.removethis.KernelTweakingProperties;

public class RuntimeUtil
{
   public static List<DeploymentElement> createDeploymentElements(List<String> modelFiles)
         throws IOException
   {
      List<DeploymentElement> units = CollectionUtils.newList(modelFiles.size());
      for (String modelFile : modelFiles)
      {
         units.add(createDeploymentElement(modelFile));
      }
      return units;
   }

   public static DeploymentElement createDeploymentElement(String modelFile)
         throws IOException
   {
      byte[] content = XmlUtils.getContent(modelFile);
      if (!modelFile.endsWith(XpdlUtils.EXT_XPDL))
      {
         if (ParametersFacade.instance().getBoolean(
               KernelTweakingProperties.XPDL_MODEL_DEPLOYMENT, true))
         {
            content = XpdlUtils.convertCarnot2Xpdl(content, XpdlUtils.ISO8859_1_ENCODING);
         }
      }
      else
      {
         if (!ParametersFacade.instance().getBoolean(
               KernelTweakingProperties.XPDL_MODEL_DEPLOYMENT, true))
         {
            content = XpdlUtils.convertXpdl2Carnot(content, XpdlUtils.ISO8859_1_ENCODING);
         }
      }
      return new DeploymentElement(content);
   }
}
