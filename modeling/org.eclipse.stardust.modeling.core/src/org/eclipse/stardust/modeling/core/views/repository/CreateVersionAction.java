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
package org.eclipse.stardust.modeling.core.views.repository;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.common.utils.xml.XmlUtils;
import org.eclipse.stardust.engine.core.model.beans.XMLConstants;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DocumentRoot;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotWorkflowModelResourceImpl;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.ui.IWorkbenchPart;
import org.w3c.dom.Document;

/**
 * @author fherinean
 * @version $Revision$
 */
public class CreateVersionAction extends RepositoryAction
{
   public static final int CREATE_INITIAL_VERSION = 0;

   public static final int CREATE_SUCCESOR_VERSION = 1;

   private int type;

   protected String basicFileName;

   public CreateVersionAction(IWorkbenchPart part, int type)
   {
      super(part);
      this.type = type;
      switch (type)
      {
      case CREATE_INITIAL_VERSION:
         setText(Diagram_Messages.LB_Versionize);
         /*
          * setToolTipText(TaskListMessages.NewTask_tooltip);
          * setImageDescriptor(MarkerUtil.getImageDescriptor("addtsk"));
          * setDisabledImageDescriptor(MarkerUtil.getImageDescriptor("addtsk_disabled"));
          */
         break;
      case CREATE_SUCCESOR_VERSION:
         setText(Diagram_Messages.LB_CreateSuccesorVersion);
         /*
          * setToolTipText(TaskListMessages.NewTask_tooltip);
          * setImageDescriptor(MarkerUtil.getImageDescriptor("addtsk"));
          * setDisabledImageDescriptor(MarkerUtil.getImageDescriptor("addtsk_disabled"));
          */
         break;
      }
   }

   protected boolean calculateEnabled(ResourceInfo info, IFile file)
   {
      int[] copy = copyVersion(info);
      copy[copy.length - 1]++;
      ResourceInfo version = info.getParent().findChild(copy);
      switch (type)
      {
      case CREATE_INITIAL_VERSION:
         return version == null;
      case CREATE_SUCCESOR_VERSION:
         return version != null;
      }
      return false;
   }

   private int[] copyVersion(ResourceInfo info)
   {
      int[] vs = info.getVersionNumbers();
      int[] newVs = new int[vs.length];
      System.arraycopy(vs, 0, newVs, 0, vs.length);
      return newVs;
   }

   protected boolean supportsMultiSelection()
   {
      return false;
   }

   protected boolean run(IFile file)
   {
      ModelType model = null;
      try
      {
         model = create(file);
      }
      catch (CoreException e)
      {
         MessageDialog.openError(getWorkbenchPart().getSite().getShell(),
               Diagram_Messages.MSG_Error, e.getStatus().toString());
         return false;
      }
      if (model == null)
      {
         MessageDialog.openError(getShell(), Diagram_Messages.MSG_Error, MessageFormat
               .format(Diagram_Messages.MSG_RESOURCE_CONTAINS_NO_MODEL,
                     new Object[] {file}));

         return false;
      }

      if (!updateModel(file, model))
      {
         return false;
      }
      basicFileName = basicFileName == null
            ? findResource(file).getBasicFileName()
            : basicFileName;
      file = getNewFile(/* model.getId() */basicFileName.substring(basicFileName
            .lastIndexOf("/") + 1), file //$NON-NLS-1$
            .getParent(), AttributeUtil.getAttributeValue(model, "carnot:engine:version"), //$NON-NLS-1$
            file.getFileExtension()); //$NON-NLS-1$
      basicFileName = null;
      Resource resource = createResource(file);
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      DocumentRoot root = factory.createDocumentRoot();
      root.setModel(model);
      resource.getContents().add(root);
      try
      {
         saveResource(resource);
      }
      catch (IOException e)
      {
         MessageDialog.openError(getWorkbenchPart().getSite().getShell(),
               Diagram_Messages.MSG_Error, //$NON-NLS-1$
               e.getMessage());
         return false;
      }
      if (!supportsMultiSelection())
      {
         openEditor(file);
      }
      return true;
   }

   protected boolean updateModel(IFile file, ModelType model)
   {
      ResourceInfo info = findResource(file);
      int[] newVersion = bumpVersion(info);
      String version = toString(newVersion);
      AttributeUtil.setAttribute(model, "carnot:engine:version", version); //$NON-NLS-1$
      return true;
   }

   private void saveResource(Resource emfResource) throws IOException
   {
      Map options = new HashMap();
      URI uri = emfResource.getURI();
      options.put(XMLResource.OPTION_ENCODING, XMLConstants.ENCODING_ISO_8859_1);
      if ((null != emfResource) //
            && (null != uri.fileExtension())
            && uri.fileExtension().endsWith(XpdlUtils.EXT_XPDL)
            && (emfResource instanceof CarnotWorkflowModelResourceImpl))
      {
         Document domCwm = ((XMLResource) emfResource).save(null, options, null);
         if (null != domCwm)
         {
            // TODO transform to XPDL
            Source xsltSource;
            try
            {
               final URL xsltURL = XpdlUtils.getCarnot2XpdlStylesheet();
               if (xsltURL == null)
               {
                  throw new InternalException(
                        Diagram_Messages.MSG_UNABLE_TO_FIND_XPDL_EXPORT_STYLESHEET);
               }
               xsltSource = new StreamSource(xsltURL.openStream());
            }
            catch (IOException e)
            {
               throw new PublicException(Diagram_Messages.MSG_INVALID_JAXP_SETUP, e);
            }

            // need to override context class loader so XpdlUtils extension class is
            // accessible from Xalan
            ClassLoader cclBackup = Thread.currentThread().getContextClassLoader();
            try
            {
               Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

               StreamResult target = new StreamResult(
                     ((CarnotWorkflowModelResourceImpl) emfResource).getNewOutputStream());

               TransformerFactory transformerFactory = XmlUtils.newTransformerFactory();
               Transformer xpdlTrans = transformerFactory.newTransformer(xsltSource);

               XmlUtils.transform(new DOMSource(domCwm), xpdlTrans, target, null, 3,
                     XpdlUtils.UTF8_ENCODING);
            }
            catch (TransformerConfigurationException e)
            {
               throw new PublicException(Diagram_Messages.MSG_INVALID_JAXP_SETUP, e);
            }
            finally
            {
               // restoring previous context class loader
               Thread.currentThread().setContextClassLoader(cclBackup);
            }
         }
      }
      else
      {
         emfResource.save(options);
      }
   }

   private IFile getNewFile(String id, IContainer parent, String version, String extension)
   {
      int ix = 0;
      while (true)
      {
         StringBuffer sb = new StringBuffer();
         sb.append(id);
         if (ix > 0)
         {
            sb.append(ix);
         }
         sb.append('_').append(version).append("." + extension); //$NON-NLS-1$
         IFile newFile = parent.getFile(new Path(sb.toString()));
         if (newFile.exists())
         {
            ix++;
         }
         else
         {
            return newFile;
         }
      }
   }

   private Resource createResource(IFile file)
   {
      if (file.exists())
      {
         if (!MessageDialog.openQuestion(getShell(), file.getName(),
               Diagram_Messages.MSG_AnotherFileAlreadyExists))
         {
            return null;
         }
      }

      ResourceSet resourceSet = new ResourceSetImpl();
      URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toOSString());
      return resourceSet.createResource(fileURI);
   }

   private String toString(int[] newVersion)
   {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < newVersion.length; i++)
      {
         if (i > 0)
         {
            sb.append('.');
         }
         sb.append(newVersion[i]);
      }
      String version = sb.toString();
      return version;
   }

   private int[] bumpVersion(ResourceInfo info)
   {
      switch (type)
      {
      case CREATE_INITIAL_VERSION:
         int[] copy = copyVersion(info);
         copy[copy.length - 1]++;
         return copy;
      case CREATE_SUCCESOR_VERSION:
         int count = info.countPseudoNodes(info.getId());
         int[] vs = info.getVersionNumbers();
         int[] newVersion = new int[vs.length + count + 1];
         System.arraycopy(vs, 0, newVersion, 0, vs.length);
         Arrays.fill(newVersion, vs.length, newVersion.length, 0);
         newVersion[newVersion.length - 1]++;
         return newVersion;
      }
      return null;
   }
}
