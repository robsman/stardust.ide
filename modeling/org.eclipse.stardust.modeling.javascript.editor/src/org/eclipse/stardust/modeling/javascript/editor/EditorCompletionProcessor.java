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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.CodeCompletionHelper;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.wst.jsdt.internal.ui.text.java.AbstractJavaCompletionProposal;
import org.eclipse.wst.jsdt.internal.ui.text.java.JavaCompletionProcessor;
import org.eclipse.wst.jsdt.internal.ui.text.java.JavaMethodCompletionProposal;
import org.eclipse.wst.jsdt.internal.ui.text.java.LazyJavaTypeCompletionProposal;
import org.eclipse.wst.jsdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.wst.jsdt.ui.text.java.JavaContentAssistInvocationContext;

public class EditorCompletionProcessor extends JavaCompletionProcessor {

	private Map<String, Object> typeMap;
	   private static final Image primitiveImage = JavaScriptEditorPlugin.getDefault()
       .getImageDescriptor("icons/primitive_data.JPG").createImage(); //$NON-NLS-1$
	   private static final Image legoImage = JavaScriptEditorPlugin.getDefault()
	   .getImageDescriptor("icons/lego_icon.JPG").createImage(); //$NON-NLS-1$
	   private static final Image primitiveBpImage = JavaScriptEditorPlugin.getDefault()
       .getImageDescriptor("icons/primitive_data_bp.JPG").createImage(); //$NON-NLS-1$
	   private static final Image legoBpImage = JavaScriptEditorPlugin.getDefault()
       .getImageDescriptor("icons/lego_icon_bp.JPG").createImage(); //$NON-NLS-1$
	   private static final Image serializableImage = JavaScriptEditorPlugin.getDefault()
	   .getImageDescriptor("icons/serializable_data.JPG").createImage(); //$NON-NLS-1$
	   private static final Image entityImage = JavaScriptEditorPlugin.getDefault()
	   .getImageDescriptor("icons/entity_data.JPG").createImage(); //$NON-NLS-1$
	   private static final Image libraryImage = JavaScriptEditorPlugin.getDefault()
	   .getImageDescriptor("icons/jarlibrary.JPG").createImage();	    //$NON-NLS-1$

	public EditorCompletionProcessor(IEditorPart editor,	ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	    /*URL url = this.getClass().getResource(imageLocation);
	    ImageDescriptor image = ImageDescriptor.createFromURL(url);*/
	}

	protected List filterAndSortProposals(List proposals,
			IProgressMonitor monitor, ContentAssistInvocationContext context) {
		typeMap = CodeCompletionHelper.getInstance().getTypeMap();
		List proposalList =  super.filterAndSortProposals(proposals, monitor, context);
		boolean root = isRootProposals(proposalList);		
		List<AbstractJavaCompletionProposal> newProposalList = new ArrayList<AbstractJavaCompletionProposal>();
		for (Iterator<AbstractJavaCompletionProposal> i = proposalList.iterator(); i.hasNext();) 
		{
			AbstractJavaCompletionProposal proposal = i.next();
			if (isProposalValid(proposal)) 
			{
				if (proposal instanceof JavaMethodCompletionProposal) {
					Reflect.setFieldValue(proposal, "fProposalInfoComputed", true); //$NON-NLS-1$
				}
				if (!root || isIppProposal(proposal))
				{
					adaptDisplayString(proposal);
					try {
						setLabelProvider(context);
						newProposalList.add(proposal);											
					} catch (Throwable t) {
						//When this fails, the proposal is not shown
					}
				}	
			}
		}
		//typeMap.clear();
		return newProposalList;
	}

	private void setLabelProvider(ContentAssistInvocationContext context) {
		JavaContentAssistInvocationContext invocationContext = (JavaContentAssistInvocationContext)context;
		Object actLabelProvider = invocationContext.getLabelProvider();
		Object fContext = Reflect.getFieldValue(actLabelProvider, "fContext"); //$NON-NLS-1$
		JSCompletionProposalLabelProvider lp = new JSCompletionProposalLabelProvider();
		Reflect.setFieldValue(lp, "fContext", fContext); //$NON-NLS-1$
		Reflect.setFieldValue(invocationContext, "fLabelProvider", lp); //$NON-NLS-1$
	}

	private boolean isIppProposal(AbstractJavaCompletionProposal proposal) {
		Object ippType = typeMap.get(proposal.getReplacementString());
		if (ippType != null) 
		{
			return true;
		}
		return false;
	}

	private boolean isRootProposals(
			List<AbstractJavaCompletionProposal> newProposalList) {
		for (Iterator<AbstractJavaCompletionProposal> i = newProposalList.iterator(); i.hasNext();) 
		{
			AbstractJavaCompletionProposal proposal = i.next();
			Object ippType = typeMap.get(proposal.getReplacementString());
			if (ippType != null) 
			{
				return true;
			}			
		}
		return false;
	}

	private boolean isProposalValid(AbstractJavaCompletionProposal proposal) {		
		try {
			for (int i = 0; i < badGlobalProposals.length; i++) 
			{
				if (proposal.getDisplayString().indexOf(badGlobalProposals[i]) > -1) 
				{
					return false;
				}
			}
			for (int i = 0; i < badProposals.length; i++) 
			{
				if (proposal.getDisplayString().equalsIgnoreCase(badProposals[i])) 
				{
					return false;
				}
			}
			if (proposal instanceof LazyJavaTypeCompletionProposal) 
			{
				return false;
			}
			return true;			
		} catch (Throwable t) {
			return true;
		}
	}
	
	private void adaptDisplayString(AbstractJavaCompletionProposal proposal) {
		Object ippType = typeMap.get(proposal.getReplacementString());
		if (ippType != null) 
		{
			if (ippType instanceof AccessPointType) {
				String displayString = proposal.getDisplayString();
				String ippTypeId = ((AccessPointType)ippType).getId();
				String ippTypeName = ((AccessPointType)ippType).getMetaType().getName();	
				displayString = displayString.replace("any", ippTypeName); //$NON-NLS-1$
				Reflect.setFieldValue(proposal, "fDisplayString", displayString); //$NON-NLS-1$
				proposal.setImage(getImageByType(ippType, proposal));
			}
			if (ippType instanceof DataType) {
				String displayString = proposal.getDisplayString();
				String ippTypeId = ((DataType)ippType).getType().getId();
				String ippTypeName = ((DataType)ippType).getType().getName();
				if (CodeCompletionHelper.getInstance().getExternalTypeMap().containsKey(((DataType)ippType).getId())) {
					displayString = displayString.replace("any", "External Class"); //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					displayString = displayString.replace("any", ippTypeName);					 //$NON-NLS-1$
				}
				Reflect.setFieldValue(proposal, "fDisplayString", displayString); //$NON-NLS-1$
				proposal.setImage(getImageByType(ippType, proposal));				
			}
		}
	}
					
	private Image getImageByType(Object ippType, AbstractJavaCompletionProposal proposal) {
		Image image = proposal.getImage();
		if (ippType instanceof AccessPointType)
		{
			AccessPointType apt = (AccessPointType)ippType;	
			if (apt.getType().getId().equalsIgnoreCase("struct")) { //$NON-NLS-1$
				image = legoImage;	
			} else {
				image = primitiveImage;
			}			
		} else {
			String ippTypeId = ((DataType)ippType).getType().getId();
			if (ippTypeId.equalsIgnoreCase("serializable"))  //$NON-NLS-1$
			{
				if (CodeCompletionHelper.getInstance().getExternalTypeMap().containsKey(((DataType)ippType).getId())) {
					image = libraryImage;
				} else {
					image = serializableImage;					
				}
			}
			if (ippTypeId.equalsIgnoreCase("primitive")) //$NON-NLS-1$
			{
				image = primitiveImage;
			}
			if (ippTypeId.equalsIgnoreCase("entity")) //$NON-NLS-1$
			{
				image = entityImage;
			}			
			if (ippTypeId.startsWith("struct"))  //$NON-NLS-1$
			{
				image = legoImage;
			}			
		}
		return image;
	}


	private String[] badGlobalProposals = new String[]{
			"Window", //$NON-NLS-1$
			"ippInitialize", //$NON-NLS-1$
			"ippImport", //$NON-NLS-1$
			"Global", //$NON-NLS-1$
			"[ECMA", //$NON-NLS-1$
			"[JavaScript", //$NON-NLS-1$
			"[Common", //$NON-NLS-1$
			"debugger", //$NON-NLS-1$
			"prototype" //$NON-NLS-1$
	};
	
	private String[] badProposals = new String[]{
			"Class    String - Object", //$NON-NLS-1$
			"constructor    Function - Object", //$NON-NLS-1$
			"prototype - String", //$NON-NLS-1$
			"Value    Number - Object", //$NON-NLS-1$
			"CanPut(String property)  Boolean - Object", //$NON-NLS-1$
			"charAt(Number pos)  String - String", //$NON-NLS-1$
			"charCodeAt(Number pos)  Number - String",			 //$NON-NLS-1$
			"DefaultValue()  void - Object", //$NON-NLS-1$
			"Delete(String property)  void - Object", //$NON-NLS-1$
			"Get(String property)  Object - Object", //$NON-NLS-1$
			"hasOwnProperty(Object V)  Boolean - Object", //$NON-NLS-1$
			"HasProperty(String property)  Boolean - Object", //$NON-NLS-1$
			"indexOf(String searchString, Number position)  Number - String", //$NON-NLS-1$
			"isPrototypeOf(Object V)  Boolean - Object", //$NON-NLS-1$
			"lastIndexOf(String searchString, Number position)  Number - String", //$NON-NLS-1$
			"localeCompare(String otherString)  Number - String", //$NON-NLS-1$
			"match(String regexp)  any[] - String", //$NON-NLS-1$
			"Match(String value, String index)  Object - Object", //$NON-NLS-1$
			"propertyIsEnumerable(Object V)  Boolean - Object", //$NON-NLS-1$
			"Put(String property, String value)  void - Object", //$NON-NLS-1$
			"replace(String searchValue, String replaceValue)  String - String", //$NON-NLS-1$
			"search(String regexp)  Number - String", //$NON-NLS-1$
			"slice(Number start, Number end)  String - String", //$NON-NLS-1$
			"split(String separator, Number limit)  any[] - String", //$NON-NLS-1$
			"valueOf()  Object - Object", //$NON-NLS-1$
			"fromCharCode(Number charCode) - String", //$NON-NLS-1$
			"concat(Array args)  any[] - Array", //$NON-NLS-1$
			"join(String seperator)  any[] - Array", //$NON-NLS-1$
			"pop()  Object - Array", //$NON-NLS-1$
			"push(Array args)  void - Array", //$NON-NLS-1$
			"reverse()  any[] - Array", //$NON-NLS-1$
			"shift()  Object - Array", //$NON-NLS-1$
			"slice(Number start, Number end)  any[] - Array", //$NON-NLS-1$
			"sort(Function funct)  any[] - Array", //$NON-NLS-1$
			"splice(Number start, Number deletecount, Array items)  any[] - Array", //$NON-NLS-1$
			"unshift(Array start)  any[] - Array" //$NON-NLS-1$
	};	
}
