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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.launch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.transform.TransformerException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.engine.extensions.transformation.format.IMessageFormat;
import org.eclipse.stardust.engine.extensions.transformation.format.ParsingException;
import org.eclipse.stardust.engine.extensions.transformation.format.SerializationException;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.transformation.messaging.format.FormatManager;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.w3c.dom.Document;

public class ProcessingLaunchDelegate extends LaunchConfigurationDelegate
{
	private String sourceFormat;
	private FileInputStream fis;
	private Document schemaDocument;
    private String targetFormat;

	public ProcessingLaunchDelegate()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public synchronized void launch(ILaunchConfiguration configuration, String mode,	ILaunch launch, IProgressMonitor monitor) throws CoreException
	{
	    final ILaunchManager launchManager = org.eclipse.debug.core.DebugPlugin.getDefault()
         .getLaunchManager();
	    //Loading Model
		String modelUri = configuration.getAttribute(ProcessingLauncherConstants.MODEL_URI, "");		 //$NON-NLS-1$
	    ModelType model = null;
		try {
			model = loadModel(modelUri);
		} catch (Exception e) {
		   if (e instanceof PublicException) {
		      if (!(e.getCause() instanceof TransformerException)) {
		         throw new RuntimeException(Modeling_Messages.EXC_MD_COULD_NOT_LOD, e);
		      } 
		   } 			
		}
		if (model != null) {
	       MessageConsole myConsole = new MessageConsole(Modeling_Messages.MSG_OP_CSL, null);
	        ConsolePlugin.getDefault().getConsoleManager().addConsoles(new
	        IConsole[] { myConsole });
	        MessageConsoleStream msgStream = myConsole.newMessageStream();
	        
	        Document sourceDoc = null;      
	        String inputFileName = configuration.getAttribute(ProcessingLauncherConstants.SOURCE_FILE_PATH, (String) null);
	        try
	        {
	         fis = new FileInputStream(inputFileName);
	            
	            sourceFormat = configuration.getAttribute(ProcessingLauncherConstants.SOURCE_FORMAT, (String) null);
	            targetFormat = configuration.getAttribute(ProcessingLauncherConstants.TARGET_FORMAT, (String) null);
	            String declaredTypeId = configuration.getAttribute(ProcessingLauncherConstants.LEGO_DATA, (String) null);
	            TypeDeclarationType typeDeclaration = model.getTypeDeclarations().getTypeDeclaration(declaredTypeId);
	            schemaDocument = typeDeclaration.getSchema().getDocument();
	         if (schemaDocument != null)
	         {
	           sourceDoc = performParsing(fis, sourceFormat, schemaDocument, configuration);
	         }  
	        }       
	        catch (FileNotFoundException e)
	        {
	            throw new RuntimeException(Modeling_Messages.EXC_IP_FILE_NOT_FOUND+inputFileName+"'.", e); //$NON-NLS-2$
	        }
	        catch (ParsingException e)
	        {
	            throw new RuntimeException(Modeling_Messages.EXC_PB_PR_FILE_CONTENT_OF+inputFileName+"'.", e); //$NON-NLS-2$
	        }
	        catch (Throwable e)
	        {
	            throw new RuntimeException(Modeling_Messages.EXC_PB_PR_FILE_CONTENT_OF+inputFileName+"'.", e); //$NON-NLS-2$
	        }
	        
	        if (configuration.getAttribute(ProcessingLauncherConstants.TEST_SERIALIZATION,  true))
	        {
	            if (configuration.getAttribute(ProcessingLauncherConstants.SCREEN_ONLY, true))
	            {
	                try
	                {
	                    performSerializing(sourceDoc, msgStream, schemaDocument, configuration);                   
	                }
	                catch (SerializationException e)
	                {
	                    throw new RuntimeException(Modeling_Messages.EXC_PB_SER_SR_DOC, e);
	                }               
	            }
	            else
	            {
	                try
	                {
	                    msgStream.println(Modeling_Messages.TXT_WRT_OP_TO + configuration.getAttribute(ProcessingLauncherConstants.TARGET_FILE_PATH,   "")); //$NON-NLS-2$
	                    performSerializing(sourceDoc, new FileOutputStream(configuration.getAttribute(ProcessingLauncherConstants.TARGET_FILE_PATH, "")), schemaDocument, configuration); //$NON-NLS-1$
	                    msgStream.println(Modeling_Messages.TXT_DONE);
	                }
	                catch (FileNotFoundException e)
	                {
	                    throw new RuntimeException(Modeling_Messages.EXC_EXPO_FILE_FOUND, e);
	                }
	                catch (SerializationException e)
	                {
	                    throw new RuntimeException(Modeling_Messages.PB_SER_SR_DOC, e);                 
	                }   
	                catch (RuntimeException e)
	                {
	                   e.printStackTrace(); 
	                   throw e;
	                }
	                catch (Throwable e)
	                {
	                    throw new RuntimeException(Modeling_Messages.PB_SER_SR_DOC, e);
	                }
	            }
	        }
	        launchManager.removeLaunch(launch); 
		}

	}
	
	public Document performParsing(FileInputStream fis, String sourceFormat, Object schemaDocument, ILaunchConfiguration launchConfiguration) throws ParsingException {
		IMessageFormat messageFormat = FormatManager.getMessageFormat(sourceFormat, launchConfiguration);	
		Document sourceDoc = messageFormat.parse(fis, schemaDocument);
		return sourceDoc;
	}
	
	public void performSerializing(Document document, OutputStream os, Object schemaDocument, ILaunchConfiguration launchConfiguration) throws SerializationException {
        IMessageFormat messageFormat = FormatManager.getMessageFormat(targetFormat, launchConfiguration);   
        messageFormat.serialize(document, os, schemaDocument);		    
	}
	
	public ModelType loadModel(String modelUri) throws Exception
	{
	      WorkflowModelManager modelManager = new WorkflowModelManager();
	      modelManager.load(URI.createURI(modelUri));
	      ModelType model = modelManager.getModel();
	      return model;
	}
}
