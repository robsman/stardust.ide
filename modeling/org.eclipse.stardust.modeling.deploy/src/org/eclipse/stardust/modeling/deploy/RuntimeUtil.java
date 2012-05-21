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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.common.config.ParametersFacade;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.api.runtime.DeploymentElement;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.KernelTweakingProperties;
import org.eclipse.stardust.engine.core.runtime.utils.XmlUtils;

public class RuntimeUtil
{
   public static List<DeploymentElement> createDeploymentElements(List<File> modelFiles)
         throws IOException
   {
      List<DeploymentElement> units = CollectionUtils.newList(modelFiles.size());
      for (File file : modelFiles)
      {
         units.add(createDeploymentElement(file));
      }
      return units;
   }

   public static DeploymentElement createDeploymentElement(File file)
         throws IOException
   {
      byte[] content = XmlUtils.getContent(file);
      if (!file.getName().endsWith(XpdlUtils.EXT_XPDL))
      {
         if (ParametersFacade.instance().getBoolean(
               KernelTweakingProperties.XPDL_MODEL_DEPLOYMENT, true))
         {
            String encoding = Parameters.instance().getObject(PredefinedConstants.XML_ENCODING, XpdlUtils.ISO8859_1_ENCODING);
            content = XpdlUtils.convertCarnot2Xpdl(content, encoding);
         }
      }
      else
      {
         if (!ParametersFacade.instance().getBoolean(
               KernelTweakingProperties.XPDL_MODEL_DEPLOYMENT, true))
         {
            String encoding = Parameters.instance().getObject(PredefinedConstants.XML_ENCODING, XpdlUtils.ISO8859_1_ENCODING);
            content = XpdlUtils.convertXpdl2Carnot(content, encoding);
         }
      }
      return new DeploymentElement(content);
   }
}
