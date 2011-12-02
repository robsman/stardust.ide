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
package org.eclipse.stardust.modeling.diagramexport.servlet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.DocumentRoot;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotWorkflowModelResourceImpl;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.core.highlighting.HighlightState;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.CompareHelper;
import ag.carnot.base.StringUtils;
import ag.carnot.config.ExtensionProviderUtils;
import ag.carnot.config.Parameters;
import ag.carnot.config.ParametersFacade;
import ag.carnot.error.InternalException;
import ag.carnot.error.ObjectNotFoundException;
import ag.carnot.error.PublicException;
import ag.carnot.web.ServiceFactoryLocator;
import ag.carnot.web.ServiceFactoryProvider;
import ag.carnot.web.ServiceFactoryProvider.Factory;
import ag.carnot.workflow.model.xpdl.XpdlUtils;
import ag.carnot.workflow.query.ActivityInstanceQuery;
import ag.carnot.workflow.query.ActivityInstances;
import ag.carnot.workflow.query.ProcessInstanceQuery;
import ag.carnot.workflow.runtime.ActivityInstance;
import ag.carnot.workflow.runtime.ActivityInstanceState;
import ag.carnot.workflow.runtime.DeployedModelDescription;
import ag.carnot.workflow.runtime.ProcessInstance;
import ag.carnot.workflow.runtime.QueryService;
import ag.carnot.workflow.runtime.ServiceFactory;
import ag.carnot.workflow.runtime.beans.removethis.KernelTweakingProperties;
import ag.carnot.workflow.runtime.beans.removethis.SecurityProperties;

public class DiagramServlet extends HttpServlet
{
   private static final long serialVersionUID = 2L;

   private static final Map<Long, HighlightState> NO_HIGHLIGHTING = Collections.emptyMap();
   private static final String MISSING_MANDATORY_PARAMETER_ERROR = "Exact one of these parameters is mandatory for diagram retrieval: {0}, {1} or {2}.";

   public static final String INIT_PRM_CARNOT_USER = "carnotUser";
   public static final String INIT_PRM_CARNOT_PASSWORD = "carnotPassword";
   public static final String INIT_PRM_FONT_SIZE = "fontSize";

   public static final String PRM_ENCODING = "encoding";
   public static final String PRM_MODEL_VERSION = "modelVersion";
   public static final String PRM_MODEL_OID = "modelOid";
   public static final String PRM_ACTIVITY_OID = "activityInstanceOid";
   public static final String PRM_ACTIVITY_ID = "activityId";
   public static final String PRM_PROCESS_OID = "processInstanceOid";
   public static final String PRM_PROCESS_ID = "processId";
   public static final String PRM_DIAGRAM_ID = "diagramId";
   public static final String PRM_FONT_SIZE = INIT_PRM_FONT_SIZE;
   public static final String PRM_PARTITION_ID = "partitionId";
   public static final String PRM_REALM_ID = "realmId";
   public static final String PRM_MODEL_SOURCE = "modelSource";

   public static final Integer DEFAULT_DIAGRAM_FONT_SIZE = new Integer(7);
   
   private static DiagramRenderService renderService;
   private Map<CacheKey, ModelType> modelsCache = CollectionUtils.newHashMap();
   private Integer fontSize = null;

   public void destroy()
   {
      getRenderService().shutDown();

      super.destroy();
   }
   
   private long getIdFromParam(String paramName, Map<?,?> params)
   {
      long id = -1;
         
      String param = getStringParam(paramName, params);
      try
      {
         id = Long.parseLong(param);
      }
      catch (Exception ignored)
      {
      }
      
      return id;
   }
        
   private long getModelOid(String modelOidParam) 
   {
      return Long.parseLong(modelOidParam);
   }
   
   private void validateRequired(String paramName, Map<?,?> params) 
   {
      String value = getStringParam(paramName, params);
      if(StringUtils.isEmpty(value))
      {
         StringBuffer errorMessage = new StringBuffer();
         errorMessage.append("The paramater ");
         errorMessage.append(paramName);
         errorMessage.append(" must not be empty"); 
         throw new IllegalArgumentException(errorMessage.toString());
      }
   }
   
   private void validateNumeric(String paramName, Map< ? , ? > params)
   {
      validateRequired(paramName, params);
      StringBuffer errorMessageTemplate = new StringBuffer();
      errorMessageTemplate.append("The paramater ");
      errorMessageTemplate.append(paramName);
      errorMessageTemplate.append(" must be a numeric value");

      long id = -1;
      String param = getStringParam(paramName, params);
      try
      {
         id = Long.parseLong(param);
      }
      catch (Exception e)
      {
         throw new IllegalArgumentException(errorMessageTemplate.toString());
      }

      if (id <= 0)
      {
         errorMessageTemplate.append(" bigger than 0");
         throw new IllegalArgumentException(errorMessageTemplate.toString());
      }
   }
   
   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException
   {
      // super.doGet(req, resp);
   
      Map<?,?> params = req.getParameterMap();
      initFontSize(params);
   
      String imgEncoding = getStringParam(PRM_ENCODING, params);
      if (null == imgEncoding)
      {
         imgEncoding = "image/png";
      }
      
      try
      {
         if (params.containsKey(PRM_ACTIVITY_OID))
         {
            validateNumeric(PRM_ACTIVITY_OID, params);
            long aiOid = getIdFromParam(PRM_ACTIVITY_OID, params);

            generateActivityDiagram(aiOid, getStringParam(PRM_DIAGRAM_ID, params),
                  imgEncoding, getModelSource(req), req, resp);

         }
         else if (params.containsKey(PRM_PROCESS_OID))
         {
            validateNumeric(PRM_PROCESS_OID, params);
            long piOid = getIdFromParam(PRM_PROCESS_OID, params); 

            generateProcessDiagram(piOid, getStringParam(PRM_ACTIVITY_ID, params),
                  getStringParam(PRM_DIAGRAM_ID, params), imgEncoding,
                  getModelSource(req), req, resp);
           
         }
         else if (params.containsKey(PRM_PROCESS_ID)) 
         {
            validateRequired(PRM_PROCESS_ID, params);
            String processId = getStringParam(PRM_PROCESS_ID, params);
            
            validateRequired(PRM_MODEL_OID, params);
            String modelOidParam = getStringParam(PRM_MODEL_OID, params);
            long modelOid = getModelOid(modelOidParam);
             
            generateProcessDiagram( -1L, processId, modelOid,
                  getStringParam(PRM_DIAGRAM_ID, params), NO_HIGHLIGHTING, imgEncoding,
                  getModelSource(req), req, resp);
         }
         else
         {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, // 
                  MessageFormat.format(MISSING_MANDATORY_PARAMETER_ERROR, new Object[] {
                        PRM_ACTIVITY_OID, PRM_PROCESS_OID, PRM_PROCESS_ID}));
         }
      }
      catch (Exception x)
      {
         resp.sendError(HttpServletResponse.SC_BAD_REQUEST, x.getMessage());
      }
   }

   protected ServiceFactory getServiceFactory(HttpServletRequest request)
   {
      ServiceFactory srvcFact = null;

      // try to get some customized service factory providers
      List<Factory> providerFactories = ExtensionProviderUtils
            .getExtensionProviders(ServiceFactoryProvider.Factory.class);
      for (Factory factory : providerFactories)
      {
         if (null != factory)
         {
            ServiceFactoryProvider provider = factory.getServiceFactoryProvider();
            if (null != provider)
            {
               // ask each provider for a service factory. If any is returned then this will be taken.
               srvcFact = provider.getServiceFactory(request);
               if (null != srvcFact)
               {
                  break;
               }
            }
         }
      }

      // if no service factory could be achieved by extension provider then fall back to default implementation
      if (null == srvcFact)
      {
         Map<String, Object> properties = CollectionUtils.newHashMap();

         String partitionId = getStringParam(PRM_PARTITION_ID, request.getParameterMap());
         if ( !StringUtils.isEmpty(partitionId))
         {
            properties.put(SecurityProperties.PARTITION, partitionId);
         }

         String realmId = getStringParam(PRM_REALM_ID, request.getParameterMap());
         if ( !StringUtils.isEmpty(realmId))
         {
            properties.put(SecurityProperties.REALM, realmId);
         }

         srvcFact = ServiceFactoryLocator.get(getInitParameter(INIT_PRM_CARNOT_USER),
               getInitParameter(INIT_PRM_CARNOT_PASSWORD), properties);
      }

      return srvcFact;
   }

   private static String getXmlEncoding(String text)
   {
      String pattern = "encoding=\"";
      int offset = text.indexOf(pattern) + pattern.length();
      int pos = text.indexOf("\"", offset);
      return text.substring(offset, pos);
   }
   
   private void initFontSize(Map<?, ?> params)
   {
      fontSize = DiagramServlet.DEFAULT_DIAGRAM_FONT_SIZE;
      
      try
      {
         if (params.containsKey(PRM_FONT_SIZE))
         {
            fontSize = new Integer(getStringParam(PRM_FONT_SIZE, params));
         }
         else if ( !StringUtils.isEmpty(getInitParameter(INIT_PRM_FONT_SIZE)))
         {
            fontSize = new Integer(getInitParameter(INIT_PRM_FONT_SIZE));
         }
      }
      catch (Throwable t)
      {
         // left empty by intent
      }
   }
   
   private void generateActivityDiagram(long activityOid, String diagramId,
         String encoding, String modelSource, HttpServletRequest req,
         HttpServletResponse resp)
   {
      // TODO
      
      // bootstrapping carnot.properties
      Parameters.instance();
      
      ServiceFactory srvcFact = getServiceFactory(req);

      try
      {
         QueryService srvcQuery = srvcFact.getQueryService();
         try
         {
            ActivityInstanceQuery saiq = ActivityInstanceQuery.findAll();
            saiq.where(ActivityInstanceQuery.OID.isEqual(activityOid));
            ActivityInstance sai = srvcQuery.findFirstActivityInstance(saiq);
            if (null != sai)
            {
               String processId = sai.getProcessDefinitionId();

               Map<Long, HighlightState> highlighting = CollectionUtils.newHashMap();

               ActivityInstanceQuery aiq = ActivityInstanceQuery
                     .findForProcessInstance(sai.getProcessInstanceOID());
               ActivityInstances ais = srvcQuery.getAllActivityInstances(aiq);
               for (Iterator<?> i = ais.iterator(); i.hasNext();)
               {
                  ActivityInstance ai = (ActivityInstance) i.next();

                  Long activityEOid = new Long(ai.getActivity().getElementOID());

                  if ( !StringUtils.isEmpty(sai.getActivity().getId())
                        && CompareHelper.areEqual(sai.getActivity().getId(),
                              ai.getActivity().getId()))
                  {
                     highlighting.put(activityEOid, HighlightState.SELECTED_LITERAL);
                  }

                  highlighting.put(activityEOid, mergeHighlighting(
                        (HighlightState) highlighting.get(activityEOid),
                        getHighlightingFromState(ai.getState())));
               }

               generateProcessDiagram(sai.getProcessInstanceOID(), processId, -1, diagramId,
                     highlighting, encoding, modelSource, req, resp);
            }
         }
         catch (ObjectNotFoundException e)
         {
            // TODO
         }
         finally
         {
            srvcFact.release(srvcQuery);
         }
      }
      finally
      {
         srvcFact.close();
      }
   }

   private void generateProcessDiagram(long processOid, String activityId,
         String diagramId, String encoding, String modelSource, HttpServletRequest req,
         HttpServletResponse resp)
   {
      // TODO
      
      // bootstrapping carnot.properties
      Parameters.instance();
      
      ServiceFactory srvcFact = getServiceFactory(req);

      try
      {
         QueryService srvcQuery = srvcFact.getQueryService();
         try
         {
            ProcessInstanceQuery piq = ProcessInstanceQuery.findAll();
            piq.where(ProcessInstanceQuery.OID.isEqual(processOid));
            ProcessInstance pi = srvcQuery.findFirstProcessInstance(piq);
            if (null != pi)
            {
               String processId = pi.getProcessID();

               Map<Long, HighlightState> highlighting = CollectionUtils.newHashMap();

               ActivityInstanceQuery aiq = ActivityInstanceQuery
                     .findForProcessInstance(processOid);
               ActivityInstances ais = srvcQuery.getAllActivityInstances(aiq);
               for (Iterator<?> i = ais.iterator(); i.hasNext();)
               {
                  ActivityInstance ai = (ActivityInstance) i.next();

                  Long activityEOid = new Long(ai.getActivity().getElementOID());

                  if ( !StringUtils.isEmpty(activityId)
                        && CompareHelper.areEqual(activityId, ai.getActivity().getId()))
                  {
                     highlighting.put(activityEOid, HighlightState.SELECTED_LITERAL);
                  }

                  highlighting.put(activityEOid, mergeHighlighting(
                        (HighlightState) highlighting.get(activityEOid),
                        getHighlightingFromState(ai.getState())));
               }

               generateProcessDiagram(processOid, processId, -1, diagramId, highlighting,
                     encoding, modelSource, req, resp);
            }
         }
         catch (ObjectNotFoundException e)
         {
            // TODO
         }
         finally
         {
            srvcFact.release(srvcQuery);
         }
      }
      finally
      {
         srvcFact.close();
      }
   }

   private void generateProcessDiagram(long processOid, String processId,
         long modelOid, String diagramId, Map<Long, HighlightState> highlighting, String encoding,
         String modelSource, HttpServletRequest req, HttpServletResponse resp)
   {
      
      // load model
      try
      {
         ModelType model = null;
         if (StringUtils.isEmpty(modelSource))
         {
            // empty model source means that the model shall be loaded from audit trail.
            ServiceFactory srvcFact = getServiceFactory(req);

            QueryService qSrvc = srvcFact.getQueryService();

            DeployedModelDescription modelDescr;

            if ( -1 != processOid)
            {
               ProcessInstanceQuery qryPi = ProcessInstanceQuery.findAll();
               qryPi.where(ProcessInstanceQuery.OID.isEqual(processOid));
               try 
               {
                  ProcessInstance pi = qSrvc.findFirstProcessInstance(qryPi);               
                  modelDescr = qSrvc.getModelDescription(pi.getModelOID());
               }
               catch(ObjectNotFoundException e)
               {
                  StringBuffer errorMessage = new StringBuffer();
                  errorMessage.append("Could not find process instance for oid ");
                  errorMessage.append(processOid);
                  throw new IllegalArgumentException(errorMessage.toString());
               }
            }
            else
            {
               modelDescr = qSrvc.getModelDescription(modelOid);
               if(modelDescr == null) 
               {
                  StringBuffer errorMessage = new StringBuffer();
                  errorMessage.append("Could not find model for oid ");
                  errorMessage.append(modelOid);
                  throw new IllegalArgumentException(errorMessage.toString());
               }
            }

            model = (ModelType) modelsCache.get(new CacheKey(modelDescr));

            // TODO check if model cache is current

            if (null == model)
            {
               String modelXml = qSrvc.getModelAsXML(modelDescr.getModelOID());

               if (ParametersFacade.instance().getBoolean(
                     KernelTweakingProperties.XPDL_MODEL_DEPLOYMENT, true))
               {
                  modelXml = XpdlUtils.convertXpdl2Carnot(modelXml,
                        XpdlUtils.UTF8_ENCODING);
               }
               
               String modelXmlEncoding = getXmlEncoding(modelXml);

               CarnotWorkflowModelResourceImpl resource = new CarnotWorkflowModelResourceImpl(
                     URI.createURI("http://only/a/dummy/URI"));

               Map<String, Boolean> options = CollectionUtils.newHashMap();
               options.put("RECORD_UNKNOWN_FEATURE", Boolean.TRUE);
               resource.load(new ByteArrayInputStream(modelXml
                     .getBytes(modelXmlEncoding)), options);

               EList<EObject> l = resource.getContents();
               for (EObject o : l)
               {
                  if (o instanceof DocumentRoot)
                  {
                     model = ((DocumentRoot) o).getModel();
                     break;
                  }
               }
               
               modelsCache.put(new CacheKey(modelDescr), model);
            }

            if (null == model)
            {
               // throw new InternalException(MessageFormat.format(
               // "Model {0} cannot be loaded.", new Object[] {modelFile}));
            }
         }
         else
         {
            // model shall be loaded from given model source.
            
            WorkflowModelManager manager = new WorkflowModelManager();
            File modelFile = new File(modelSource);

            model = (ModelType) modelsCache.get(new CacheKey(modelFile));

            if (null == model)
            {
               manager.load(modelFile);
               model = manager.getModel();

               modelsCache.put(new CacheKey(modelFile), model);
            }

            if (null == model)
            {
               throw new InternalException(MessageFormat.format(
                     "Model {0} cannot be loaded.", new Object[] { modelFile }));
            }
         }

         DiagramType diagram = DiagramLocator.findDiagram(model, processId, diagramId);

         byte[] imgData;
         if (null != diagram)
         {
            DiagramRenderJob job = getRenderService().scheduleJob(diagram, highlighting,
                  encoding, fontSize);

            if (null != job)
            {
               synchronized (job)
               {
                  while ( !job.done)
                  {
                     try
                     {
                        job.wait();
                     }
                     catch (InterruptedException e)
                     {
                        // ignore
                     }
                  }
               }

               if (null != job.error)
               {
                  throw new PublicException("Failed rendering diagram.", job.error);
               }

               imgData = job.imgData;
            }
            else
            {
               imgData = null;
            }
         }
         else
         {
            imgData = null;
         }

         if (null != imgData)
         {
            resp.setContentType(encoding);
            resp.setContentLength(imgData.length);
            resp.getOutputStream().write(imgData);
         }
         else
         {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
   
   private static class CacheKey
   {
      private int modelOid;
      private long deploymentTime;
      private String path;

      public CacheKey(int modelOid, long deploymentTime)
      {
         this.modelOid = modelOid;
         this.deploymentTime = deploymentTime;
      }
      
      public CacheKey(DeployedModelDescription modelDescr)
      {
         modelOid = modelDescr.getModelOID();
         deploymentTime = modelDescr.getDeploymentTime().getTime();
      }

      public CacheKey(File modelFile)
      {
         path = modelFile.getAbsolutePath();
      }
      
      public boolean equals(Object other)
      {
         boolean isEqual;

         if (this == other)
         {
            isEqual = true;
         }
         else if (!(other instanceof CacheKey))
         {
            isEqual = false;
         }
         else
         {
            final CacheKey otherKey = (CacheKey) other;

            isEqual = (modelOid == otherKey.modelOid) 
                  && (deploymentTime == otherKey.deploymentTime)
                  && (null != path ? path.equals(otherKey.path) : null == otherKey.path);
         }

         return isEqual;
      }
      
      public int hashCode()
      {
         int result;

         result = modelOid;
         result = 29 * result + (new Long(deploymentTime).hashCode());
         result = 29 * result + (path != null ? path.hashCode() : 0);

         return result;
      }
   }

   private static HighlightState getHighlightingFromState(ActivityInstanceState state)
   {
      HighlightState result = HighlightState.DEFAULT_LITERAL;

      if (null != state)
      {
         switch (state.getValue())
         {
         case ActivityInstanceState.INTERRUPTED:
         case ActivityInstanceState.ABORTED:
            result = HighlightState.BROKEN_LITERAL;
            break;

         case ActivityInstanceState.APPLICATION:
         case ActivityInstanceState.HIBERNATED:
         case ActivityInstanceState.SUSPENDED:
            result = HighlightState.ACTIVE_LITERAL;
            break;

         case ActivityInstanceState.COMPLETED:
            result = HighlightState.DONE_LITERAL;
            break;

         case ActivityInstanceState.CREATED:
            result = HighlightState.DEFAULT_LITERAL;
            break;

         default:
            break;
         }
      }
      return result;
   }

   private static HighlightState mergeHighlighting(HighlightState lhs, HighlightState rhs)
   {
      HighlightState[] precedence = new HighlightState[] {
            HighlightState.SELECTED_LITERAL,//
            HighlightState.BROKEN_LITERAL,//
            HighlightState.ACTIVE_LITERAL,//
            HighlightState.DONE_LITERAL,//
      };

      HighlightState result = HighlightState.DEFAULT_LITERAL;

      for (int i = 0; i < precedence.length; i++ )
      {
         HighlightState state = precedence[i];
         if (state.equals(lhs) || state.equals(rhs))
         {
            result = state;
            break;
         }
      }

      return result;
   }

   private static String getStringParam(String name, Map<?, ?> params)
   {
      String result = null;

      if (params.containsKey(name))
      {
         if ((params.get(name) instanceof Object[])
               && (0 < ((Object[]) params.get(name)).length)
               && (((Object[]) params.get(name))[0] instanceof String))
         {
            result = (String) ((Object[]) params.get(name))[0];
         }
         else if (params.get(name) instanceof String)
         {
            result = (String) params.get(name);
         }
      }

      return result;
   }

   private static synchronized DiagramRenderService getRenderService()
   {
      if (null == renderService)
      {
         renderService = new DiagramRenderService();
         Thread renderThread = new Thread(renderService);
         renderThread.setDaemon(true);
         renderThread.setName(DiagramRenderService.class.getName() + "  "
               + renderThread.getName());
         renderThread.start();
      }

      return renderService;
   }

   /**
    * Gets the servlet parameter 'modelSource' and returns it as absolute path.
    * If {@link File#isAbsolute()} evaluates to <code>false</code> for this file path,
    * then it will be interpreted as relative to the servlets context root. 
    * 
    * @param req The servlet reqest.
    * @return The absolute path.
    * 
    * @throws ParameterException when given modelSource does not exists.
    */
   private static String getModelSource(HttpServletRequest req) 
   {
      Map<?, ?> params = req.getParameterMap();
      String modelSource = getStringParam(PRM_MODEL_SOURCE, params);

      if (StringUtils.isEmpty(modelSource))
      {
         return "";
      }

      File modelSourceFile = new File(modelSource);
      if (!modelSourceFile.isAbsolute())
      {
         modelSourceFile = new File(req.getSession().getServletContext().getRealPath(
               modelSource));
      }

      String absolutePath = modelSourceFile.getAbsolutePath();
      if (!modelSourceFile.exists())
      {
         throw new ParameterException(MessageFormat.format(
               "Path {0} for modelSource does not exists.", new Object[] {absolutePath}));
      }

      return absolutePath;
   }
   
   private static class ParameterException extends RuntimeException
   {
      private static final long serialVersionUID = 1L;

      public ParameterException(String message)
      {
         super(message);
      }
   }
   
   public static void main(String[] args)
   {
      Map<CacheKey, String> testCache = CollectionUtils.newHashMap();
      long ts1 = System.currentTimeMillis() - 10000;
      long ts2 = System.currentTimeMillis() - 20000;

      CacheKey key1 = new CacheKey(1, ts1);
      CacheKey key2 = new CacheKey(2, ts2);

      testCache.put(key1, "Key1");
      testCache.put(key2, "Key2");

      System.out.println(testCache.get(new CacheKey(3, ts1)));
      System.out.println(testCache.get(new CacheKey(3, ts2)));
      System.out.println(testCache.get(new CacheKey(1, ts2)));
      System.out.println(testCache.get(new CacheKey(2, ts1)));
      System.out.println(testCache.get(new CacheKey(1, ts1)));
      System.out.println(testCache.get(new CacheKey(2, ts2)));
   }
}
