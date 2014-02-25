/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.ui.web.modeler.edit.model.element;

import static org.eclipse.stardust.ui.web.modeler.marshaling.GsonUtils.extractString;

import javax.annotation.Resource;

import org.eclipse.stardust.model.xpdl.builder.common.EObjectUUIDMapper;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.ui.web.modeler.edit.spi.CommandHandler;
import org.eclipse.stardust.ui.web.modeler.edit.spi.OnCommand;
import org.eclipse.stardust.ui.web.modeler.edit.utils.CommandHandlerUtils;
import org.eclipse.stardust.ui.web.modeler.service.ModelService;
import org.springframework.context.ApplicationContext;

import com.google.gson.JsonObject;

/**
 * @author Shrikant.Gangal
 *
 */
@CommandHandler
public class ProcessChangeCommandHandler
{
   @Resource
   private ApplicationContext springContext;

   @OnCommand(commandId = "process.create")
   public void createProcess(ModelType model, JsonObject request)
   {
      ProcessDefinitionType processDefinition = getModelBuilderFacade().createProcess(model, null, extractString(request, ModelerConstants.NAME_PROPERTY), 
            extractString(request, "defaultLaneName"), extractString(request, "defaultPoolName"));

      // Add process definition to UUID map.
      
      EObjectUUIDMapper mapper = modelService().uuidMapper();
      mapper.map(processDefinition);
   }

   /**
    * @param model
    * @param request
    */
   @OnCommand(commandId = "process.delete")
   public void deleteProcess(ModelType model, JsonObject request)
   {
      String id = extractString(request, ModelerConstants.ID_PROPERTY);
      ProcessDefinitionType processDefinition = getModelBuilderFacade().findProcessDefinition(model, id);
      synchronized (model)
      {
    	  model.getProcessDefinition().remove(processDefinition);
      }
   }

   private ModelService modelService()
   {
      return springContext.getBean(ModelService.class);
   }

   private ModelBuilderFacade getModelBuilderFacade()
   {
      return CommandHandlerUtils.getModelBuilderFacade(springContext);
   }
}
