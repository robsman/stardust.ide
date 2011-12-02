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
package org.eclipse.stardust.modeling.javascript.editor;

import org.eclipse.wst.jsdt.core.CompletionProposal;
import org.eclipse.wst.jsdt.ui.text.java.CompletionProposalLabelProvider;

public class JSCompletionProposalLabelProvider extends CompletionProposalLabelProvider {

	public JSCompletionProposalLabelProvider() {
		super();
		// TODO Auto-generated constructor stub
	}
	//Here we go....

	@Override
	public String createLabel(CompletionProposal proposal) {
		// TODO Auto-generated method stub
		try {
			return super.createLabel(proposal);	
		} catch (Throwable t) {
			return String.valueOf(proposal.getCompletion());
		}
		
	}

}
