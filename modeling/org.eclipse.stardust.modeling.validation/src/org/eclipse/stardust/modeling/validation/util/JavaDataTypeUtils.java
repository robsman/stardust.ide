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
package org.eclipse.stardust.modeling.validation.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.extensions.ejb.data.EntityBeanConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class JavaDataTypeUtils
{
   private static final String PROTOCOL_SEPARATOR = "://"; //$NON-NLS-1$

   private static final String PATH_SEPARATOR = "."; //$NON-NLS-1$

   private static IJavaProject javaProject;

   public static Map initPrimitiveAttributes(Type type, String defaultValue)
   {
      Map attributes = new HashMap();

      attributes.put(PredefinedConstants.TYPE_ATT, type);
      if (null != defaultValue)
      {
         attributes.put(PredefinedConstants.DEFAULT_VALUE_ATT, defaultValue);
      }

      return attributes;
   }

   public static Map initSerializableBeanAttributes(String beanClassName)
   {
      Map attributes = new HashMap();

      attributes.put(PredefinedConstants.CLASS_NAME_ATT, beanClassName);

      return attributes;
   }

   public static String getReferenceClassName(ITypedElement data, boolean convert)
   {
      String className = null;
      DataTypeType dataType = null;
      if (data instanceof AccessPointType)
      {
         dataType = ((AccessPointType) data).getType();
      }
      else if (data instanceof DataType)
      {
         dataType = ((DataType) data).getType();
      }

      if (dataType.getId().equals(PredefinedConstants.PRIMITIVE_DATA))
      {
         className = AttributeUtil.getAttributeValue((IExtensibleElement) data,
               PredefinedConstants.TYPE_ATT);
      }
      else if (dataType.getId().equals(PredefinedConstants.SERIALIZABLE_DATA))
      {
         className = AttributeUtil.getAttributeValue((IExtensibleElement) data,
               PredefinedConstants.CLASS_NAME_ATT);
      }
      else if (dataType.getId().equals(PredefinedConstants.ENTITY_BEAN_DATA))
      {
         String version = AttributeUtil.getAttributeValue((IExtensibleElement) data, EntityBeanConstants.VERSION_ATT);
         if(version == null || version.equals(EntityBeanConstants.VERSION_2_X))
         {
            className = AttributeUtil.getAttributeValue((IExtensibleElement) data,
                  PredefinedConstants.REMOTE_INTERFACE_ATT);
         }
         else
         {
            className = AttributeUtil.getAttributeValue((IExtensibleElement) data,
                  PredefinedConstants.CLASS_NAME_ATT);            
         }
      }
      else
      {
         throw new PublicException(Validation_Messages.ERR_NotAJavaDataType);
      }
      String convertedClassName = TypeFinder.getClassFromAbbreviatedName(className);
      return convert && (null != convertedClassName) ? convertedClassName : className;
   }

   public static IType getReferenceClass(ITypedElement data)
   {
      return getTypeFromCurrentProject(getReferenceClassName(data, true));
   }

   // TODO: (rsauer) need to explicitly pass in the resource in question to prevent
   // problems if i.e. the editor was closed already
   // TODO: remove (fh) dangerous, because can be used in a different project context
   // than the current determined one.
   public static IType getTypeFromCurrentProject(final String referenceClassName)
   {
      if (referenceClassName == null)
      {
         return null;
      }
      // strip class parameters if any
      String className = referenceClassName; 
      int ix = referenceClassName.indexOf('<');
      if (ix > 0)
      {
         className = referenceClassName.substring(0, ix);
      }
      // now search for the raw class
      final IWorkbench workbench = PlatformUI.getWorkbench();
      EditorPartRetriever editorPartRetriever = new EditorPartRetriever(workbench);
      workbench.getDisplay().syncExec(editorPartRetriever);
      IEditorPart part = editorPartRetriever.getEditorPart();

      IType type = null;
      if (null != part)
      {
         IResource editorResource = (IResource) part.getEditorInput().getAdapter(
               IResource.class);
         javaProject = JavaCore.create(editorResource.getProject());
         
         try
         {
            type = javaProject.findType(className);
         }
         catch (JavaModelException e)
         {
//            e.printStackTrace();
         }
      }
      return type;
   }

   public static List parse(String pathExpression)
   {
      List parsedPath = Collections.EMPTY_LIST;

      if (!StringUtils.isEmpty(pathExpression))
      {
         int protocolIndex = pathExpression.indexOf(PROTOCOL_SEPARATOR);
         if (protocolIndex != -1)
         {
            String protocol = pathExpression.substring(0, protocolIndex);
            if (!"java".equals(protocol)) //$NON-NLS-1$
            {
               throw new PublicException(Validation_Messages.ERR_InvalidJavaBeanAccessPathType
                     + pathExpression);
            }
            pathExpression = pathExpression.substring(protocolIndex
                  + PROTOCOL_SEPARATOR.length());
         }

         Iterator pathTokens = StringUtils.split(pathExpression, PATH_SEPARATOR);

         parsedPath = new ArrayList();

         StringBuffer buffer = new StringBuffer();

         while (pathTokens.hasNext())
         {
            String token = (String) pathTokens.next();
            buffer.append(token);
            if (token.endsWith(")")) //$NON-NLS-1$
            {
               parsedPath.add(buffer.toString());
               buffer = new StringBuffer();
            }
            else
            {
               buffer.append("."); //$NON-NLS-1$
            }
         }
         // @todo (ub): if the buffer is != "" here, it is an error
      }
      return parsedPath;
   }

   public static BridgeObject getBridgeObject(String apTypeName, String path,
         DirectionType direction) throws ValidationException
   {
      IType accessPointType = getTypeFromCurrentProject(apTypeName);
      return new BridgeObject(accessPointType, direction);
   }

   public static BridgeObject getBridgeObject(ITypedElement ap, String path,
         DirectionType direction) throws ValidationException
   {
      DataTypeType dataType = (DataTypeType) ap.getMetaType();
      IAccessPathEditor editor = AccessPointUtil.getSPIAccessPathEditor(dataType);
      ITypedElement element = traversePath(editor, ap, path, direction);
      String referenceClassName = getReferenceClassName(element, true);
      IType accessPointType = getTypeFromCurrentProject(referenceClassName);
      return new BridgeObject(accessPointType, direction, referenceClassName);
   }

   public static ITypedElement traversePath(IAccessPathEditor editor,
         ITypedElement typed, String accessPath, DirectionType direction)
         throws ValidationException
   {
      String id = null;
      String path = accessPath;
      if (path != null && path.length() == 0)
      {
         path = null;
      }
      ITypedElement element = typed;
      while (element != null && editor != null && path != null)
      {
         String[] splitted = editor.splitAccessPath(path);
         id = splitted[0];
         path = splitted[1];
         
         DirectionType segmentDirection = (DirectionType.IN_LITERAL.equals(direction) && !StringUtils.isEmpty(path))
               ? DirectionType.OUT_LITERAL
               : direction; 
         
         List accessPoints = editor.getAccessPoints(id,
            (IExtensibleElement) element, segmentDirection);
         element = null;
         for (int i = 0; i < accessPoints.size(); i++)
         {
            AccessPointType ap = (AccessPointType) accessPoints.get(i);
            if (ap.getId().equals(id))
            {
               element = ap;
               editor = AccessPointUtil.getSPIAccessPathEditor(ap.getType());
               break;
            }
         }
      }
      if (element == null)
      {
         throw new ValidationException(MessageFormat.format(
            Validation_Messages.JavaDataTypeUtils_segmentProcessingError,
            new Object[] {accessPath, id}), accessPath);
      }
      return element;
   }

   private JavaDataTypeUtils()
   {
   // no instances allowed
   }

   private static final class EditorPartRetriever implements Runnable
   {
      private final IWorkbench workbench;

      private IEditorPart editorPart;

      private EditorPartRetriever(IWorkbench workbench)
      {
         super();
         this.workbench = workbench;
         this.editorPart = null;
      }

      public void run()
      {
         IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
         if (window != null)
         {
            editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage().getActiveEditor();
         }
      }

      public IEditorPart getEditorPart()
      {
         return editorPart;
      }
   }
}
