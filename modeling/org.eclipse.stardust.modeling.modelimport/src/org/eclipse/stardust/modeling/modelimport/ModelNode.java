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
package org.eclipse.stardust.modeling.modelimport;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ModelNode
{
   private String id;
   private String name;
   private String versionCount;
   private String description;
   private String validFrom;
   private String validTo;
   private String deploymentComments;
   private String deploymentTime;
   private String oid;
   private boolean released;
   private String releaseTime;
   private String version;
   private String owner;
   private ModelNode parent;
   private ArrayList versions;
   private ArrayList privateVersions;
   private FileSystemStore store;

   public ModelNode(FileSystemStore store, String id, String name, String version, String versionCount)
   {
      this(store, id, name, version);
      this.versionCount = versionCount;
   }

   public ModelNode(FileSystemStore store, String id, String name, String version)
   {
      this.store = store;
      this.id = id;
      this.name = name;
      this.version = version;
      versions = new ArrayList();
      privateVersions = new ArrayList();
   }

   public String getId()
   {
      return id;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public void setValidFrom(String validFrom)
   {
      this.validFrom = validFrom;
   }

   public void setValidTo(String validTo)
   {
      this.validTo = validTo;
   }

   public void setDeploymentComment(String deploymentComments)
   {
      this.deploymentComments = deploymentComments;
   }

   public void setDeploymentTime(String deploymentTime)
   {
      this.deploymentTime = deploymentTime;
   }

   public void setModelOID(String oid)
   {
      this.oid = oid;
   }

   public void release(String releaseTime)
   {
      released = true;
      this.releaseTime = releaseTime;
   }

   public ModelNode attachPublicVersion(String id, String name, String version, String versionCount)
   {
       ModelNode modelnodebean = new ModelNode(store, id, name, version, versionCount);
       addToPublicVersions(modelnodebean);
       return modelnodebean;
   }

   public ModelNode attachPrivateVersion(String owner, String version)
   {
       ModelNode modelnodebean = new ModelNode(store, id, name, version);
       modelnodebean.owner = owner;
       addToPrivateVersions(modelnodebean);
       return modelnodebean;
   }

   private void addToPublicVersions(ModelNode modelnodebean)
   {
       versions.add(modelnodebean);
       modelnodebean.parent = this;
   }

   private void addToPrivateVersions(ModelNode modelnodebean)
   {
       privateVersions.add(modelnodebean);
       modelnodebean.parent = this;
   }

   public Iterator publicVersions()
   {
      return versions.iterator();
   }

   public String getFullVersion()
   {
       return isPrivateVersion() ? parent.version + "_" + version : version; //$NON-NLS-1$
   }

   public boolean isPrivateVersion()
   {
       return owner != null;
   }

   public FileSystemStore getStore()
   {
      return store;
   }
   
   public String getFullName()
   {
	   StringBuffer sb = new StringBuffer();
	   if (name != null)
	   {
		   sb.append(name).append(' '); 
	   }
	   sb.append('(').append(id).append(" - "); //$NON-NLS-1$
	   if (isPrivateVersion())
	   {
		   sb.append(parent.version).append('_');
	   }
	   sb.append(version);
	   sb.append(')');
	   return sb.toString();
   }

   public boolean hasChildren()
   {
	   return !versions.isEmpty();
   }

   public ModelNode getParent()
   {
	   return parent;
   }

   public Object[] getVersions()
   {
	   return versions.toArray();
   }
}
