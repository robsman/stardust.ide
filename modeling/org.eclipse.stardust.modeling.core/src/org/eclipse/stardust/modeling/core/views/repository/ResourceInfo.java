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

import java.util.*;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.ui.IMemento;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ResourceInfo
{
   private String file;

   private String id;

   private String name;

   private String version;

   private String label;

   private boolean pseudoNode;

   private ArrayList children = new ArrayList();

   private ResourceInfo parent;

   private int[] versionNumbers;

   private static final String MEMENTO_FILE_TYPE = CarnotConstants.DIAGRAM_PLUGIN_ID
         + ".versionInfo"; //$NON-NLS-1$

   private static final String FILE_KEY = "file"; //$NON-NLS-1$

   private static final String ID_KEY = "id"; //$NON-NLS-1$

   private static final String NAME_KEY = "name"; //$NON-NLS-1$

   private static final String VERSION_KEY = "version"; //$NON-NLS-1$

   private static final String LABEL_KEY = "label"; //$NON-NLS-1$

   private static final String PSEUDO_KEY = "pseudoNode"; //$NON-NLS-1$

   private String basicFileName;

   private ResourceInfo(String id, String name)
   {
      this.id = id;
      // this.label = id;
      this.label = name + " ( " + id + " )"; //$NON-NLS-1$ //$NON-NLS-2$
      pseudoNode = true;
   }

   private ResourceInfo(String id, int[] version)
   {
      this.id = id;
      versionNumbers = version;
      StringBuffer sb = new StringBuffer();
      // sb.append(id).append(' ');
      for (int i = 0; i < version.length; i++)
      {
         sb.append(version[i]).append('.');
      }
      sb.append('x');
      label = sb.toString();
      pseudoNode = true;
   }

   public ResourceInfo(IFile file, String id, String name, String version,
         int[] versionNumbers)
   {
      if (file != null)
      {
         this.file = file.getFullPath().toString();
      }
      this.id = id;
      this.name = name;
      this.version = version;
      setLabel(versionNumbers);
   }

   public ResourceInfo(IMemento memento)
   {
      loadState(memento);
   }

   public ResourceInfo(String id, String name, String fileName)
   {
      this(id, name);
      this.basicFileName = fileName;
   }

   private void setLabel(int[] vs)
   {
      if (vs == null || vs.length == 0)
      {
         versionNumbers = new int[] {1};
         version = "1"; //$NON-NLS-1$
      }
      else
      {
         versionNumbers = new int[vs.length];
         System.arraycopy(vs, 0, versionNumbers, 0, vs.length);
      }
      StringBuffer sb = new StringBuffer();
      if (file == null)
      {
         sb.append('<');
      }
      /*
       * if (name != null) { sb.append(name); } if (id != null || version != null) {
       * sb.append(" ("); } if (id != null) { sb.append(id); if (version != null) {
       * sb.append(' '); } }
       */
      if (version != null)
      {
         sb.append(version);
         /*
          * if (version.indexOf('.') < 0) { sb.append(".0"); }
          */
      }
      /*
       * if (id != null || version != null) { sb.append(")"); }
       */
      if (file == null)
      {
         sb.append('>');
      }
      label = sb.toString();
   }

   public String toString()
   {
      return label;
   }

   public void add(ResourceInfo resourceInfo)
   {
      ResourceInfo parent = resourceInfo.getParent();
      if (parent != null)
      {
         parent.remove(resourceInfo);
      }
      children.add(resourceInfo);
      resourceInfo.setParent(this);
   }

   private void setParent(ResourceInfo parent)
   {
      this.parent = parent;
   }

   public void remove(ResourceInfo resourceInfo)
   {
      children.remove(resourceInfo);
      resourceInfo.setParent(null);
   }

   public ResourceInfo[] getChildren()
   {
      return (ResourceInfo[]) children.toArray(new ResourceInfo[children.size()]);
   }

   public ResourceInfo getParent()
   {
      return parent;
   }

   public boolean hasChildren()
   {
      return !children.isEmpty();
   }

   public String getId()
   {
      return id;
   }

   public String getVersion()
   {
      return version;
   }

   public int[] getVersionNumbers()
   {
      return versionNumbers;
   }

   public void set(ResourceInfo ref)
   {
      this.file = ref.file;
      this.id = ref.id;
      this.name = ref.name;
      this.version = ref.version;
      this.label = ref.label;
      this.versionNumbers = ref.versionNumbers;
   }

   public String getName()
   {
      return name;
   }

   public ResourceInfo findChild(int[] version)
   {
      for (int i = 0; i < children.size(); i++)
      {
         ResourceInfo info = (ResourceInfo) children.get(i);
         if (Arrays.equals(info.getVersionNumbers(), version))
         {
            return info;
         }
      }
      return null;
   }

   public ResourceInfo findChild(String id)
   {
      for (int i = 0; i < children.size(); i++)
      {
         ResourceInfo info = (ResourceInfo) children.get(i);
         if (info.getId().equals(id))
         {
            return info;
         }
      }
      return null;
   }

   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (!(o instanceof ResourceInfo))
         return false;

      final ResourceInfo resourceInfo = (ResourceInfo) o;

      if (isVirtual() != resourceInfo.isVirtual())
         return false;

      if (isVirtual())
      {
         if (id == null && resourceInfo.id != null || id != null
               && !id.equals(resourceInfo.id))
            return false;

         return Arrays.equals(versionNumbers, resourceInfo.versionNumbers);
      }

      if (file != null ? !file.equals(resourceInfo.file) : resourceInfo.file != null)
         return false;

      return true;
   }

   public int hashCode()
   {
      return (file != null ? file.hashCode() : 0);
   }

   public boolean isVirtual()
   {
      return file == null;
   }

   public boolean isPseudoNode()
   {
      return pseudoNode;
   }

   public void setVirtual()
   {
      file = null;
      name = null;
      version = toString(versionNumbers);
      setLabel(versionNumbers);
   }

   public void saveState(IMemento memento)
   {
      save(memento, FILE_KEY, file);
      save(memento, ID_KEY, id);
      save(memento, NAME_KEY, name);
      save(memento, VERSION_KEY, version);
      save(memento, LABEL_KEY, label);
      save(memento, PSEUDO_KEY, pseudoNode ? "TRUE" : "FALSE"); //$NON-NLS-1$ //$NON-NLS-2$
      for (int i = 0; i < children.size(); i++)
      {
         save(memento, (ResourceInfo) children.get(i));
      }
   }

   private void save(IMemento memento, ResourceInfo info)
   {
      // todo: skip virtual children?
      IMemento child = memento.createChild(MEMENTO_FILE_TYPE);
      info.saveState(child);
   }

   private void save(IMemento memento, String name, String value)
   {
      if (value != null && value.length() > 0)
      {
         memento.putString(name, value);
      }
   }

   public void loadState(IMemento memento)
   {
      if (memento != null)
      {
         file = memento.getString(FILE_KEY);
         id = memento.getString(ID_KEY);
         name = memento.getString(NAME_KEY);
         version = memento.getString(VERSION_KEY);
         label = memento.getString(LABEL_KEY);
         pseudoNode = "TRUE".equals(memento.getString(PSEUDO_KEY)); //$NON-NLS-1$
         versionNumbers = getVersionNumbers(version);
         IMemento[] childrens = memento.getChildren(MEMENTO_FILE_TYPE);
         if (childrens != null)
         {
            for (int i = 0; i < childrens.length; i++)
            {
               add(new ResourceInfo(childrens[i]));
            }
         }
      }
   }

   public static int[] getVersionNumbers(String version)
   {
      if (version == null)
      {
         return new int[0];
      }
      StringTokenizer st = new StringTokenizer(version.trim(), "."); //$NON-NLS-1$
      int[] numbers = new int[st.countTokens()];
      int c = 0;
      while (st.hasMoreTokens())
      {
         String token = st.nextToken().trim();
         if (token.length() == 0)
         {
            numbers[c++] = 0;
         }
         else
         {
            try
            {
               numbers[c++] = Integer.parseInt(token);
            }
            catch (Exception ex)
            {
               numbers[c++] = 0;
            }
         }
      }
      return numbers;
   }

   public String getFile()
   {
      return file;
   }

   public String getBasicFileName()
   {
      if (basicFileName == null)
      {
         if (file != null)
         {
            basicFileName = file.lastIndexOf("_" + version + ".") > 0 ? file.substring(0, file //$NON-NLS-1$ //$NON-NLS-2$
                                    .lastIndexOf("_" + version + ".")) : file.substring(0, file.lastIndexOf(".")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         }
      }
      return basicFileName;
   }

   public ResourceInfo findResource(IResource resource)
   {
      for (int i = 0; i < children.size(); i++)
      {
         String name = resource.getFullPath().toString();
         ResourceInfo resourceInfo = (ResourceInfo) children.get(i);
         if (name.equals(resourceInfo.file))
         {
            return resourceInfo;
         }
         resourceInfo = resourceInfo.findResource(resource);
         if (resourceInfo != null)
         {
            return resourceInfo;
         }
      }
      return null;
   }

   public int getChildrenCount()
   {
      return children.size();
   }

   public static String toString(int[] version)
   {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < version.length; i++)
      {
         if (i > 0)
         {
            sb.append('.');
         }
         sb.append(Integer.toString(version[i]));
      }
      return sb.toString();
   }

   public ResourceInfo getPseudoChild(String id, String name, String file, String version)
   {
      String fileStart = file.lastIndexOf("_" + version + ".") > 0 ? file.substring(0, file //$NON-NLS-1$ //$NON-NLS-2$
                              .lastIndexOf("_" + version + ".")) : file.substring(0, file.lastIndexOf(".")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

      for (int i = 0; i < children.size(); i++)
      {
         ResourceInfo info = (ResourceInfo) children.get(i);
         if (info.getId().equals(id) && info.isPseudoNode()
               && fileStart.equalsIgnoreCase(info.getBasicFileName()))
         {
            return info;
         }
      }
      ResourceInfo info = new ResourceInfo(id, name, fileStart);
      add(info);
      return info;
   }

   public ResourceInfo getPseudoChild(int[] version)
   {
      for (int i = 0; i < children.size(); i++)
      {
         ResourceInfo info = (ResourceInfo) children.get(i);
         if (info.isPseudoNode() && Arrays.equals(version, info.versionNumbers))
         {
            return info;
         }
      }
      ResourceInfo info = new ResourceInfo(id, version);
      add(info);
      return info;
   }

   public int countPseudoNodes(String id)
   {
      int count = 0;
      for (int i = 0; i < children.size(); i++)
      {
         ResourceInfo info = (ResourceInfo) children.get(i);
         if (info.id.equals(id) && info.pseudoNode)
         {
            count++;
         }
      }
      return count;
   }

   public boolean hasSuccessors(ResourceInfo child)
   {
      int[] vs = child.getVersionNumbers();
      if (child.isPseudoNode())
      {
         if (vs == null)
         {
            return false;
         }
         for (int i = 0; i < children.size(); i++)
         {
            ResourceInfo resourceInfo = (ResourceInfo) children.get(i);
            if (resourceInfo.getVersionNumbers().length > vs.length)
            {
               return true;
            }
         }
      }
      else
      {
         for (int i = 0; i < children.size(); i++)
         {
            ResourceInfo resourceInfo = (ResourceInfo) children.get(i);
            if (resourceInfo.getVersionNumbers()[vs.length - 1] > vs[vs.length - 1])
            {
               return true;
            }
         }
      }
      return false;
   }

   public void cleanUp(Comparator comparator)
   {
      if (children.size() > 0)
      {
         ResourceInfo[] infos = (ResourceInfo[]) children
               .toArray(new ResourceInfo[children.size()]);
         Arrays.sort(infos, comparator);
         if (infos[0].isPseudoNode())
         {
            for (int i = infos.length - 1; i >= 0; i--)
            {
               if (infos[i].hasChildren())
               {
                  break;
               }
               remove(infos[i]);
            }
         }
         else
         {
            for (int i = 0; i < infos.length; i++)
            {
               if (infos[i].hasChildren() || !infos[i].isVirtual())
               {
                  break;
               }
               remove(infos[i]);
            }
         }
      }
   }
}
