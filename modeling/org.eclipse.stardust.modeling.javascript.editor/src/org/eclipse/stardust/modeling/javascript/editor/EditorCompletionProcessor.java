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
       .getImageDescriptor("icons/primitive_data.JPG").createImage();
	   private static final Image legoImage = JavaScriptEditorPlugin.getDefault()
	   .getImageDescriptor("icons/lego_icon.JPG").createImage();
	   private static final Image primitiveBpImage = JavaScriptEditorPlugin.getDefault()
       .getImageDescriptor("icons/primitive_data_bp.JPG").createImage();
	   private static final Image legoBpImage = JavaScriptEditorPlugin.getDefault()
       .getImageDescriptor("icons/lego_icon_bp.JPG").createImage();
	   private static final Image serializableImage = JavaScriptEditorPlugin.getDefault()
	   .getImageDescriptor("icons/serializable_data.JPG").createImage();
	   private static final Image entityImage = JavaScriptEditorPlugin.getDefault()
	   .getImageDescriptor("icons/entity_data.JPG").createImage();
	   private static final Image libraryImage = JavaScriptEditorPlugin.getDefault()
	   .getImageDescriptor("icons/jarlibrary.JPG").createImage();	   

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
					Reflect.setFieldValue(proposal, "fProposalInfoComputed", true);
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
		Object fContext = Reflect.getFieldValue(actLabelProvider, "fContext");
		JSCompletionProposalLabelProvider lp = new JSCompletionProposalLabelProvider();
		Reflect.setFieldValue(lp, "fContext", fContext);
		Reflect.setFieldValue(invocationContext, "fLabelProvider", lp);
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
				displayString = displayString.replace("any", ippTypeName);
				Reflect.setFieldValue(proposal, "fDisplayString", displayString);
				proposal.setImage(getImageByType(ippType, proposal));
			}
			if (ippType instanceof DataType) {
				String displayString = proposal.getDisplayString();
				String ippTypeId = ((DataType)ippType).getType().getId();
				String ippTypeName = ((DataType)ippType).getType().getName();
				if (CodeCompletionHelper.getInstance().getExternalTypeMap().containsKey(((DataType)ippType).getId())) {
					displayString = displayString.replace("any", "External Class");
				} else {
					displayString = displayString.replace("any", ippTypeName);					
				}
				Reflect.setFieldValue(proposal, "fDisplayString", displayString);
				proposal.setImage(getImageByType(ippType, proposal));				
			}
		}
	}
					
	private Image getImageByType(Object ippType, AbstractJavaCompletionProposal proposal) {
		Image image = proposal.getImage();
		if (ippType instanceof AccessPointType)
		{
			AccessPointType apt = (AccessPointType)ippType;	
			if (apt.getType().getId().equalsIgnoreCase("struct")) {
				image = legoImage;	
			} else {
				image = primitiveImage;
			}			
		} else {
			String ippTypeId = ((DataType)ippType).getType().getId();
			if (ippTypeId.equalsIgnoreCase("serializable")) 
			{
				if (CodeCompletionHelper.getInstance().getExternalTypeMap().containsKey(((DataType)ippType).getId())) {
					image = libraryImage;
				} else {
					image = serializableImage;					
				}
			}
			if (ippTypeId.equalsIgnoreCase("primitive"))
			{
				image = primitiveImage;
			}
			if (ippTypeId.equalsIgnoreCase("entity"))
			{
				image = entityImage;
			}			
			if (ippTypeId.startsWith("struct")) 
			{
				image = legoImage;
			}			
		}
		return image;
	}


	private String[] badGlobalProposals = new String[]{
			"Window",
			"ippInitialize",
			"ippImport",
			"Global",
			"[ECMA",
			"[JavaScript",
			"[Common",
			"debugger",
			"prototype"
	};
	
	private String[] badProposals = new String[]{
			"Class    String - Object",
			"constructor    Function - Object",
			"prototype - String",
			"Value    Number - Object",
			"CanPut(String property)  Boolean - Object",
			"charAt(Number pos)  String - String",
			"charCodeAt(Number pos)  Number - String",			
			"DefaultValue()  void - Object",
			"Delete(String property)  void - Object",
			"Get(String property)  Object - Object",
			"hasOwnProperty(Object V)  Boolean - Object",
			"HasProperty(String property)  Boolean - Object",
			"indexOf(String searchString, Number position)  Number - String",
			"isPrototypeOf(Object V)  Boolean - Object",
			"lastIndexOf(String searchString, Number position)  Number - String",
			"localeCompare(String otherString)  Number - String",
			"match(String regexp)  any[] - String",
			"Match(String value, String index)  Object - Object",
			"propertyIsEnumerable(Object V)  Boolean - Object",
			"Put(String property, String value)  void - Object",
			"replace(String searchValue, String replaceValue)  String - String",
			"search(String regexp)  Number - String",
			"slice(Number start, Number end)  String - String",
			"split(String separator, Number limit)  any[] - String",
			"valueOf()  Object - Object",
			"fromCharCode(Number charCode) - String",
			"concat(Array args)  any[] - Array",
			"join(String seperator)  any[] - Array",
			"pop()  Object - Array",
			"push(Array args)  void - Array",
			"reverse()  any[] - Array",
			"shift()  Object - Array",
			"slice(Number start, Number end)  any[] - Array",
			"sort(Function funct)  any[] - Array",
			"splice(Number start, Number deletecount, Array items)  any[] - Array",
			"unshift(Array start)  any[] - Array"
	};	
}
