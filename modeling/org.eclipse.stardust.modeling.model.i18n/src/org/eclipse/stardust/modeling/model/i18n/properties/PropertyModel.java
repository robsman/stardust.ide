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
package org.eclipse.stardust.modeling.model.i18n.properties;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.Code;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.QualityControlType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class PropertyModel
{
   public static final String DESCRIPTION = "Description"; //$NON-NLS-1$
   public static final String NAME = "Name"; //$NON-NLS-1$
   
   private static final String PROPERTIES_SUFFIX = ".properties"; //$NON-NLS-1$
   private static final String CARNOT_MODEL_RESOURCE_BUNDLE = "carnot:model:resourceBundle"; //$NON-NLS-1$

   /**
    * Map locales vs. resource files.
    */
   private Map<Object, IResource> files = CollectionUtils.newMap();
   
   /**
    * Map locales vs. properties.
    */
   private Map<Object, SortedProperties> bundles = CollectionUtils.newMap();

   /**
    * Cached list with project's source folders.
    */
   private List<IContainer> sourceFolders;

   private String baseName;
   private String baseNamePrefix;
   
   private ModelType model = null;
   private List<IPropertyModelListener> listeners = CollectionUtils.newList();
   private List<String> prefixes = CollectionUtils.newList();
   
   private PropertyBundleCommand command = null;
   private int useCounter = 0;
   
   private static Map<URI, PropertyModel> models = CollectionUtils.newMap();

   // ProperyModels cannot be directly created but obtained via get(...) method.
   private PropertyModel(ModelType model)
   {
      this.model = model;
      command = new PropertyBundleCommand(model);
      collectBundles();
   }

   public static synchronized PropertyModel get(ModelType type)
   {
      PropertyModel model = (PropertyModel) models.get(getIdentifier(type));
      if (model == null)
      {
         model = new PropertyModel(type);
         models.put(getIdentifier(type), model);
      }
      model.useCounter++;
      return model;
   }

   private static URI getIdentifier(ModelType type)
   {
      return type.eResource().getURI();
   }

   public static synchronized void dispose(PropertyModel model)
   {
      model.useCounter--;
      if (model.useCounter <= 0)
      {
         model.files.clear();
         model.bundles.clear();
         model.sourceFolders.clear();
         models.remove(getIdentifier(model.model));
      }
   }
   
   public void dispose()
   {
      PropertyModel.dispose(this);
   }

   private void collectPrefixes()
   {
      prefixes.clear();
      prefixes.add(computePrefix(null, model, null, false));
      for (Iterator<EObject> i = model.eAllContents(); i.hasNext();)
      {
         EObject object = i.next();
         if (isRegistered(object))
         {
            prefixes.add(computePrefix(null, object, null, false));
         }
      }
   }

   /**
    * Checks if there are any locales defined.
    * 
    * @return true if there is at least one locale defined.
    */
   public boolean hasLocales()
   {
      return !bundles.isEmpty();
   }

   /**
    * Gets the defined locales.
    * 
    * @return a set of objects that can be either a platform supported Locale or a string
    *         representing a non-supported locale in the form <country code>[_<language code>].
    */
   public Set<Object> getLocales()
   {
      return bundles.keySet();
   }

   /**
    * Creates a new property bundle for the specified locale.
    * 
    * @param locale the Locale for which the bundle is created.
    * @param sourceFolder the folder in which the actual resource file will be created.
    */
   public void addLocale(Object locale, Properties properties)
   {
      IContainer sourceFolder = (IContainer) sourceFolders.get(0);
      String localeId = locale.toString();
      SortedProperties props = new SortedProperties(prefixes);
      if (properties != null)
      {
         props.putAll(properties);
      }
      try
      {
         IContainer folder = getCreateFolder(sourceFolder);
         IFile file = folder.getFile(Path.fromOSString(
               baseNamePrefix + localeId + PROPERTIES_SUFFIX));
         if (!file.exists())
         {
            if (properties == null)
            {
               addDefaultKeys(props);
            }
            file.create(new ByteArrayInputStream(new byte[] {}), true, null);
            AttributeUtil.setAttribute((IExtensibleElement) model,
                  CARNOT_MODEL_RESOURCE_BUNDLE, baseName);
            bundles.put(locale, props);
            files.put(locale, file);
         }
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      command.addLocale(locale);
      fireLocalesChanged();
   }

   private void addDefaultKeys(Properties props)
   {
      addDefaultKeys(props, model);
      for (Iterator<EObject> i = model.eAllContents(); i.hasNext();)
      {
         EObject object = i.next();
         if (isRegistered(object))
         {
            addDefaultKeys(props, object);
         }
      }
   }

   public boolean isRegistered(EObject object)
   {
      return object instanceof DataType
          || object instanceof RoleType
          || object instanceof OrganizationType
          || object instanceof ConditionalPerformerType
          || object instanceof ApplicationType
          || object instanceof ProcessDefinitionType
          || object instanceof DiagramType
          || object instanceof ActivityType
          || object instanceof TriggerType
          || object instanceof DataPathType
          || object instanceof DataMappingType
          || object instanceof Code          
          || object instanceof EventHandlerType;
   }

   private void addDefaultKeys(Properties props, EObject model)
   {
      String prefix = computePrefix(null, model, null, true);
      if(model instanceof Code)
      {
         String descriptionKey = prefix + DESCRIPTION;
         if (shouldAddKey(descriptionKey))
         {
            props.put(descriptionKey, getDescriptionFrom(model));
         }                  
      }
      else
      {
         String nameKey = prefix + NAME;
         if (shouldAddKey(nameKey))
         {
            props.put(nameKey, getNameFrom(model));
         }
         String descriptionKey = prefix + DESCRIPTION;
         if (shouldAddKey(descriptionKey))
         {
            props.put(descriptionKey, getDescriptionFrom(model));
         }         
      }
   }

   private boolean shouldAddKey(String key)
   {
      if (hasLocales())
      {
         for (SortedProperties props : bundles.values())
         {
            if (props.containsKey(key))
            {
               return true;
            }
         }
         return false;
      }
      return true;
   }

   private Object getDescriptionFrom(EObject model)
   {
      String description = null;
      if (model instanceof ModelType)
      {
         description = ModelUtils.getDescriptionText(
               ((ModelType) model).getDescription());
      }
      else if (model instanceof IIdentifiableModelElement)
      {
         description = ModelUtils.getDescriptionText(
               ((IIdentifiableModelElement) model).getDescription());
      }
      else if (model instanceof Code)
      {
         description = ((Code) model).getValue();
      }      
      return description == null ? "" : description; //$NON-NLS-1$
   }

   private Object getNameFrom(EObject model)
   {
      String name = null;
      if (model instanceof IIdentifiableElement)
      {
         name = ((IIdentifiableElement) model).getName();
         if (name == null)
         {
            name = ((IIdentifiableElement) model).getId();
         }
      }
      if (model instanceof DiagramType)
      {
    	  name = ((DiagramType) model).getName();
      }
      if (name == null && model instanceof IModelElement)
      {
         name = String.valueOf(((IModelElement) model).getElementOid());
      }
      return name == null ? "" : name; //$NON-NLS-1$
   }

   /**
    * Saves the content of this PropertyModel.
    */
   public void save()
   {
      collectPrefixes();
      for (Object locale : bundles.keySet())
      {
         SortedProperties properties = bundles.get(locale);
         IFile file = (IFile) files.get(locale);
         
         save(locale, properties, file);
      }
   }

   /**
    * Gets the basename for the bundles as defined in the workflow model containing
    * the model element.
    *  
    * @return a string containing the basename.
    */
   public String getBasename()
   {
      return baseName;
   }

   /**
    * Sets the basename and recomputes the properties.
    * 
    * @param value the new basename.
    */
   public void setBasename(String value)
   {
      AttributeUtil.setAttribute((IExtensibleElement) model,
            CARNOT_MODEL_RESOURCE_BUNDLE, value);
      collectBundles();
   }

   /**
    * Gets the value of a property.
    * 
    * @param locale the locale for which the value is retrieved.
    * @param propertyName the name of the property.
    * @return the property value.
    */
   public String getProperty(Object locale, String propertyName)
   {
      if (locale == null || propertyName == null)
      {
         return null;
      }
      Properties properties = (Properties) bundles.get(locale);
      return (String) properties.get(propertyName);
   }

   /**
    * Sets the value of a property.
    * 
    * @param locale the locale for which the value is set.
    * @param propertyName the name of the property.
    * @param value the new value of the property.
    */
   public void setProperty(Object locale, String propertyName, String value)
   {
      Properties properties = (Properties) bundles.get(locale);
      command.setProperty(locale, propertyName, value,
            (String) properties.setProperty(propertyName, value));
   }

   /**
    * Deletes a property value.
    * 
    * @param locale the locale for which the value is deleted.
    * @param propertyName the name of the property.
    */
   public void deleteProperty(Object locale, String propertyName)
   {
      Properties properties = (Properties) bundles.get(locale);
      command.setProperty(locale, propertyName, null,
            (String) properties.remove(propertyName));
   }

   /**
    * Checks if the project has source folders.
    * 
    * @return true if the project contains at least one source folder.
    */
   public boolean hasSourceFolders()
   {
      return !sourceFolders.isEmpty();
   }

   private IContainer getCreateFolder(IContainer folder) throws CoreException
   {
      int dot = baseName.lastIndexOf('.');
      if (dot > 0)
      {
         StringTokenizer st = new StringTokenizer(baseName.substring(0, dot), "."); //$NON-NLS-1$
         while (st.hasMoreTokens())
         {
            String token = st.nextToken();
            folder = folder.getFolder(Path.fromOSString(token));
            if (!folder.exists())
            {
               ((IFolder) folder).create(true, true, null);
            }
         }
      }
      return folder;
   }
   
   public String computePrefix(EObject changedElement, EObject element, String useThisId, boolean appendDot)
   {
      StringBuffer buffer = new StringBuffer();
      buffer.append(getQualifierPrefix(element));
      appendToPrefix(buffer, changedElement, element, useThisId);
      if (appendDot)
      {
         buffer.append('.');
      }
      return buffer.toString();
   }

   private String getQualifierPrefix(EObject object)
   {
      return object instanceof ModelType ? "Model" //$NON-NLS-1$
           : object instanceof DataType ? "Data" //$NON-NLS-1$
           : object instanceof Code ? "QualityAssuranceCode" //$NON-NLS-1$                 
           : object instanceof RoleType ? "Role" //$NON-NLS-1$
           : object instanceof OrganizationType ? "Organization" //$NON-NLS-1$
           : object instanceof ConditionalPerformerType ? "CondPerformer" //$NON-NLS-1$
           : object instanceof ApplicationType ? "Application" //$NON-NLS-1$
           : object instanceof ProcessDefinitionType ? "Process" //$NON-NLS-1$
           : object instanceof DiagramType ? "Diagram" //$NON-NLS-1$
           : object instanceof ActivityType ? "Activity" //$NON-NLS-1$
           : object instanceof TriggerType ? "Trigger" //$NON-NLS-1$
           : object instanceof DataPathType ?
                 ((DataPathType) object).getDirection().getName() + "DataPath" //$NON-NLS-1$
           : object instanceof DataMappingType ? 
                 ((DataMappingType) object).getDirection().getName() + "DataMapping" //$NON-NLS-1$
           : object instanceof EventHandlerType ? 
                object.eContainer() instanceof ProcessDefinitionType ? "ProcessEH" //$NON-NLS-1$
              : object.eContainer() instanceof ActivityType ? "ActivityEH" //$NON-NLS-1$
              : null
           : null;
   }

   private void appendToPrefix(StringBuffer buffer, EObject changedElement,
         EObject element, String useThisId)
   {
      if (element instanceof ModelType)
      {
         return;
      }
      if (element instanceof QualityControlType)
      {
         return;
      }
      
      appendToPrefix(buffer, changedElement, element.eContainer(), useThisId);
      buffer.append('.');
      if (element == changedElement && useThisId != null)
      {
         buffer.append(useThisId);
      }
      else if (element instanceof DataMappingType)
      {
         buffer.append(((DataMappingType) element).getContext());
         buffer.append('.');
         buffer.append(((DataMappingType) element).getId());
      }      
      else if (element instanceof IIdentifiableElement
          && !StringUtils.isEmpty(((IIdentifiableElement) element).getId()))
      {
         buffer.append(((IIdentifiableElement) element).getId());
      }
      else if (element instanceof IModelElement)
      {
         buffer.append(((IModelElement) element).getElementOid());
      }
      else if (element instanceof Code)
      {
         buffer.append(((Code) element).getCode());
      }      
      else
      {         
         buffer.append(element.toString());
      }
   }

   private void collectBundles()
   {
      baseName = AttributeUtil.getAttributeValue(model, CARNOT_MODEL_RESOURCE_BUNDLE);
      if (baseName == null)
      {
         baseName = "ag.carnot." + model.getId(); //$NON-NLS-1$
      }
      
      IProject project = ModelUtils.getProjectFromEObject(model);
      sourceFolders = collectSourceFolders(JavaCore.create(project));
      
      List<IContainer> folders = CollectionUtils.newList(sourceFolders.size());
      folders.addAll(sourceFolders);
      
      StringTokenizer st = new StringTokenizer(baseName, "."); //$NON-NLS-1$
      String token = st.nextToken();
      while (!folders.isEmpty() && st.hasMoreTokens())
      {
         folders = filterFolders(folders, token);
         token = st.nextToken();
      }

      // File name is the last token + "_" + locale + .properties
      while (st.hasMoreTokens())
      {
         token = st.nextToken();
      }
      baseNamePrefix = token + "_"; //$NON-NLS-1$
      
      files.clear();
      bundles.clear();
      
      if (!folders.isEmpty())
      {
         collectBundles(folders, baseNamePrefix);
      }
      
      fireLocalesChanged();
   }

   private void fireLocalesChanged()
   {
      for (int i = 0; i < listeners.size(); i++)
      {
         IPropertyModelListener listener = (IPropertyModelListener) listeners.get(i);
         listener.localesChanged();
      }
   }

   private void collectBundles(List<IContainer> folders, String token)
   {
      try
      {
         for (int i = 0; i < folders.size(); i++)
         {
            IContainer resource = folders.get(i);
            IResource[] members = resource.members();
            for (int j = 0; j < members.length; j++)
            {
               String name = members[j].getName();
               if (members[j] != null && members[j] instanceof IFile
                     && name.startsWith(token)
                     && name.endsWith(PROPERTIES_SUFFIX))
               {
                  
                  String locale = name.substring(token.length(), name.length()
                        - PROPERTIES_SUFFIX.length());
                  SortedProperties props = new SortedProperties(prefixes);
                  try
                  {
                     props.load(((IFile) members[j]).getContents());
                  }
                  catch (IOException e)
                  {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
                  Object loc = getLocale(locale);
                  bundles.put(loc, props);
                  files.put(loc, members[j]);
               }
            }
         }
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private Object getLocale(String locale)
   {
      Locale[] all = Locale.getAvailableLocales();
      for (int i = 0; i < all.length; i++)
      {
         if (all[i].toString().equals(locale))
         {
            return all[i];
         }
      }
      return locale;
   }

   private List<IContainer> filterFolders(List<IContainer> folders, String token)
   {
      List<IContainer> result = CollectionUtils.newList();
      for (int i = 0; i < folders.size(); i++)
      {
         IContainer resource = folders.get(i);
         IResource member = resource.findMember(token);
         if (member != null && member instanceof IContainer)
         {
            result.add((IContainer) member);
         }
      }
      return result;
   }

   private List<IContainer> collectSourceFolders(IJavaProject javaProject)
   {
      List<IContainer> sourceFolders = null;
      if (javaProject != null)
      {
         try
         {
            IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();
            sourceFolders = CollectionUtils.newList(roots.length);
            for (int i = 0; i < roots.length; i++)
            {
               if (roots[i].getKind() == IPackageFragmentRoot.K_SOURCE)
               {
                  sourceFolders.add((IContainer) roots[i].getResource());
               }
            }
         }
         catch (JavaModelException e)
         {
            // do nothing
//            e.printStackTrace();
         }
      }
      return sourceFolders == null ? Collections.<IContainer>emptyList() : sourceFolders;
   }

   public void save(Object locale, Properties properties, IFile file)
   {
      String header = locale instanceof Locale
         ? ((Locale) locale).getDisplayName() : locale.toString();
      try
      {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         properties.store(baos, header);
         ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
         file.setContents(bais, true, true, null);
      }
      catch (IOException e1)
      {
         e1.printStackTrace();
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }
   }

   public void addPropertyModelListener(IPropertyModelListener listener)
   {
      listeners.add(listener);
   }

   public void deleteNls(Object nls)
   {
      bundles.remove(nls);
      if (bundles.isEmpty())
      {
         AttributeUtil.setAttribute((IExtensibleElement) model,
               CARNOT_MODEL_RESOURCE_BUNDLE, null);
      }
      IFile file = (IFile) files.remove(nls);
      if (file != null)
      {
         try
         {
            file.delete(true, null);
         }
         catch (CoreException e)
         {
            e.printStackTrace();
         }
      }
      fireLocalesChanged();
   }

   public Command getCommand()
   {
      return command;
   }

   public void updatePropertiesFor(IIdentifiableElement element, String oldId, String newId)
   {
      updatePropertiesFor(element, element, oldId, newId);
      for (Iterator<EObject> i = element.eAllContents(); i.hasNext();)
      {
         EObject eObject = i.next();
         if (isRegistered(eObject))
         {
            updatePropertiesFor(element, eObject, oldId, newId);
         }
      }
      command.addUpdatePropertiesFor(element, oldId, newId);
   }

   private void updatePropertiesFor(EObject changedElement, EObject eObject, String oldId, String newId)
   {
      String oldPrefix = computePrefix(changedElement, eObject, oldId, false);
      String newPrefix = computePrefix(changedElement, eObject, newId, false);
      
      Collection<SortedProperties> values = bundles.values();
      for (SortedProperties props : values)
      {
         List<String> keys2change = CollectionUtils.newList();
         for (Object object : props.keySet())
         {
            String key = (String) object;
            int dot = key.lastIndexOf('.');
            if (dot >= 0 && oldPrefix.equals(key.substring(0, dot)))
            {
               keys2change.add(key);
            }
         }
         for (String oldKey : keys2change)
         {
            String newKey = newPrefix + oldKey.substring(oldPrefix.length());
            String value = (String) props.remove(oldKey);
            props.setProperty(newKey, value);
         }
      }
   }

   public Properties getPropertiesFor(Object locale)
   {
      return (Properties) bundles.get(locale);
   }
}