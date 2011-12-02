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
package org.eclipse.stardust.modeling.core.modelserver;

import java.io.InputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.core.RepositoryProvider;

public interface Vcs
{
   public void setRepositoryProvider(RepositoryProvider repositoryProvider);
   
   public void add(IResource[] resources, IProgressMonitor monitor) throws RMSException;

   public void commit(IResource[] resources, String comment, boolean keepLock, IProgressMonitor monitor) throws RMSException;

   public void delete(IResource[] resources, IProgressMonitor monitor) throws RMSException;
   
   public void forceUnlock(IFile[] resources, IProgressMonitor monitor) throws RMSException;

   public InputStream getHeadRevision(IFile file) throws RMSException;

   public List<ModelHistoryEntry> getHistory(IResource resource, IProgressMonitor monitor) throws RMSException;

   public VcsStatus getStatus(IResource resource);

   public InputStream getWorkingRevision(IFile file) throws RMSException;   
   
   public boolean isShared(IResource resource, boolean forceRemoteCheck) throws RMSException;

   public void lock(IResource[] file, IProgressMonitor monitor) throws RMSException;
   
   public void resolve(IResource[] resources, IProgressMonitor monitor) throws RMSException;

   public void revert(IResource[] resources, IProgressMonitor monitor) throws RMSException;
   
   public void unlock(IResource[] resources, IProgressMonitor monitor) throws RMSException;;
   
   public void update(IResource[] resources, IProgressMonitor monitor) throws RMSException;
}