/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data;

import java.util.List;

import org.eclipse.bpmn2.DataStore;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectOrStoreExt;
import org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder;
import org.eclipse.stardust.model.xpdl.builder.variable.BpmDocumentVariableBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

/**
 * @author Simon Nikles
 *
 */
public class DataStore2Stardust extends Data2Stardust {

    public DataStore2Stardust(ModelType carnotModel, List<String> failures) {
        super(carnotModel, failures);
    }

	public void addDataStore(DataStore data) {
        if (data == null) return;
        String name = getName(data);
        addVariable(data, name);
	}

	private void addVariable(DataStore store, String name) {

		StardustDataObjectOrStoreExt ext = ExtensionHelper2.getInstance().getDataStoreExtension(store);
		if (null == ext) return;

		if (PredefinedConstants.DOCUMENT_DATA.equals(ext.dataType)) {
			addDocumentVariable(ext, store);
		} else if (PredefinedConstants.DOCUMENT_LIST_DATA.equals(ext.dataType)) {
			addDocumentListVariable(ext, store);
		} else {
			super.addVariable(store, getName(store));
		}
	}

	private void addDocumentListVariable(StardustDataObjectOrStoreExt ext, DataStore store) {
		// TODO
//		BpmDocumentVariableBuilder document =
//				BpmModelBuilder.newDocumentVariable(carnotModel)
//				.withIdAndName(store.getId(), getName(store));
//				document.
//				document.setTypeDeclaration(ext.structuredDataTypeFullId);
//		document.build();
	}

	private void addDocumentVariable(StardustDataObjectOrStoreExt ext, DataStore store) {
		BpmDocumentVariableBuilder document =
				BpmModelBuilder.newDocumentVariable(carnotModel)
				.withIdAndName(store.getId(), getName(store));
				document.setTypeDeclaration(ext.structuredDataTypeFullId);
		document.build();
	}

	private String getName(DataStore data) {
		return getNonEmptyName(data.getName(), data.getId(), data);
	}

}
