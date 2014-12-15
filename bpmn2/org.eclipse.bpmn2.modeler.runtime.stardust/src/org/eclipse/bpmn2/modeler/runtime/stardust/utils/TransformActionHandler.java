/*******************************************************************************
 * Copyright (c) 2014 ITpearls, AG
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ITpearls AG - Stardust Runtime Extension
 *
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.stardust.model.bpmn2.input.BPMNModelImporter;
import org.eclipse.stardust.model.bpmn2.transform.TransformationControl;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.DialectStardustXPDL;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Simon Nikles
 *
 */
public class TransformActionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent evt) throws ExecutionException {
		List<IFile> selectedFiles = new ArrayList<IFile>();
		try {
			IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveSite(evt).getSelectionProvider().getSelection();
			@SuppressWarnings("rawtypes")
			Iterator iterator = selection.iterator();
			while (iterator.hasNext()) {
				IFile item = (IFile)iterator.next();
				selectedFiles.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if (selectedFiles.isEmpty()) return null;

		Shell shell = HandlerUtil.getActiveShell(evt);
		List<ViewerFilter> filters = new ArrayList<ViewerFilter>();
		filters.add(new ViewerFilter()
	    {
		      @Override
		      public boolean select(Viewer viewer, Object parentElement, Object element)
		      {
		        return !(element instanceof IFile) || "xpdl".equals(((IFile)element).getFileExtension());
		      }
		    });
		IFile firstFile = selectedFiles.get(0);
		IPath sourcePath = firstFile.getProjectRelativePath();
		sourcePath = sourcePath.removeFileExtension();
		sourcePath = sourcePath.addFileExtension("xpdl");
		IFile targetFile = WorkspaceResourceDialog.openNewFile(shell, "Transformation target ","Select transformation target file:", sourcePath, filters);

		System.out.println(targetFile);
		Definitions definitions = loadBpmnModel(firstFile);
		if (null != targetFile) {
	    	File outputFile = targetFile.getRawLocation().toFile(); // getFullPath().toFile();
	    	//String parentFolder = outputFile.getParent();
	        TransformationControl transf = TransformationControl.getInstance(new DialectStardustXPDL());
	        try {
	            FileOutputStream targetStream = new FileOutputStream(outputFile);
	            try {
	                transf.transformToTarget(definitions, targetStream);
	            } finally {
	                targetStream.close();
	            }
	        } catch (IOException ioe) {
	            throw new RuntimeException("Failed transforming model.", ioe);
	        }
	        return (ModelType)transf.getTargetModel();
		}

		return null;
	}

    public static Definitions loadBpmnModel(IFile file) {
        Definitions definitions = null;
        try {
            Bpmn2Resource bpmnModel = BPMNModelImporter.importModel(file.getRawLocationURI());
            definitions = BPMNModelImporter.getDefinitions(bpmnModel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return definitions;
    }
}
