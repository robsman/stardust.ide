package org.eclipse.stardust.ui.web.modeler.utils.test;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import javax.annotation.PostConstruct;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import org.eclipse.stardust.engine.api.runtime.AdministrationService;
import org.eclipse.stardust.engine.api.runtime.DocumentManagementService;
import org.eclipse.stardust.engine.api.runtime.QueryService;
import org.eclipse.stardust.engine.api.runtime.ServiceFactory;
import org.eclipse.stardust.engine.api.runtime.User;
import org.eclipse.stardust.engine.api.runtime.UserService;
import org.eclipse.stardust.engine.api.runtime.WorkflowService;
import org.eclipse.stardust.ui.web.modeler.common.ServiceFactoryLocator;

@Qualifier("default")
@Scope("session")
public class MockServiceFactoryLocator implements ServiceFactoryLocator
{
   private String userId = null;

   private ServiceFactory serviceFactoryMock = null;

   private UserService userService = null;

   private WorkflowService workflowService = null;

   private AdministrationService adminService = null;

   private QueryService queryService = null;

   private DocumentManagementService dmsService = null;

   @PostConstruct
   public void init()
   {
      this.serviceFactoryMock = Mockito.mock(ServiceFactory.class);
      this.userService = Mockito.mock(UserService.class);
      this.workflowService = Mockito.mock(WorkflowService.class);
      this.adminService = Mockito.mock(AdministrationService.class);
      this.queryService = Mockito.mock(QueryService.class);
      this.dmsService = Mockito.mock(DocumentManagementService.class);

      Mockito.when(serviceFactoryMock.getAdministrationService()).thenReturn(adminService);
      Mockito.when(serviceFactoryMock.getDocumentManagementService()).thenReturn(dmsService);
      Mockito.when(serviceFactoryMock.getQueryService()).thenReturn(queryService);
      Mockito.when(serviceFactoryMock.getUserService()).thenReturn(userService);
      Mockito.when(serviceFactoryMock.getWorkflowService()).thenReturn(workflowService);

      // define some basic user info
      User mockUser = Mockito.mock(User.class);
      Mockito.when(mockUser.getAccount()).thenAnswer(new Answer<String>()
      {
         @Override
         public String answer(InvocationOnMock invocation) throws Throwable
         {
            return isEmpty(userId) ? "motu" : userId;
         }
      });

      Mockito.when(userService.getUser()).thenReturn(mockUser);
   }

   public void runAsUser(String userId)
   {
      this.userId = userId;
   }

   @Override
   public ServiceFactory get()
   {
      return serviceFactoryMock;
   }
}
