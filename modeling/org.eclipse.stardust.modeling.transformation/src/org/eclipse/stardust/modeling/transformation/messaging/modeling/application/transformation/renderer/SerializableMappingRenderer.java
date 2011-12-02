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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.renderer;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.core.ui.StringUtils;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.MappingConfiguration;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.MultipleAccessPathBrowserContentProvider;



public class SerializableMappingRenderer implements IMappingRenderer {
	private MessageTransformationController controller;
	private AccessPointType messageType;

	public SerializableMappingRenderer(MessageTransformationController controller,
			AccessPointType messageType) {
		super();
		this.controller = controller;
		this.messageType = messageType;
	}

	public AccessPointType getType() {		
		return messageType;
	}
	
	public String renderGetterCode(boolean ignoreArrays, boolean variablesAsIndices, MappingConfiguration config) {
		String result = createJavaPath(ignoreArrays, variablesAsIndices, config);
		result = createJavaAccessCode(result, true, config);
		return result;
	}

	public String renderSetterCode(String getterCode, boolean ignoreArrays, boolean variablesAsIndices, MappingConfiguration config) {
		String accessCode = createJavaPath(ignoreArrays, variablesAsIndices, config);
		accessCode = createJavaAccessCode(accessCode, false, config);
		return accessCode + ".set" + messageType.getName().substring(0,1).toUpperCase() + messageType.getName().substring(1) + "(" + getterCode + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	public String renderListMappingCode(IMappingRenderer sourceMapper,	IMappingRenderer targetMapper, String inset, int depth, MappingConfiguration config) {
		String result = null;
		if (controller.isJavaList(targetMapper.getType())) {
			result = generateJavaListMapping(sourceMapper, targetMapper, inset, depth, config);    			  
		}
		if (controller.isJavaArray(targetMapper.getType())) {
  		    result = generateJavaArrayMapping(sourceMapper, targetMapper, inset, depth, config);    			  
		} 
		return result;
	}
	
	public String renderAssignmentCode(IMappingRenderer sourceMapper,	IMappingRenderer targetMapper, String inset, int depth, String result, MappingConfiguration config) {
		// Assign source child fields to target fields - multiple fields cause
		// recursion
		Object[] sourceChildren = controller.getSourceAPB().getChildren(sourceMapper.getType());
		Object[] targetChildren = controller.getTargetAPB().getChildren(targetMapper.getType());
		for (int i = 0; i < sourceChildren.length; i++) {
			IMappingRenderer sourceChildTypeMapper = new SerializableMappingRenderer(controller, (AccessPointType) sourceChildren[i]);
			IMappingRenderer targetChildTypeMapper = new SerializableMappingRenderer(controller, (AccessPointType) targetChildren[i]);
			if (controller.isJavaList((AccessPointType) sourceChildren[i])) {
				result = result + renderListMappingCode(sourceChildTypeMapper, targetChildTypeMapper, inset + "   ", depth + 1, config); //$NON-NLS-1$
			} else {
				if (controller.isJavaArray((AccessPointType) sourceChildren[i])) {
					result = result + generateJavaArrayMapping(sourceChildTypeMapper, targetChildTypeMapper, inset + "   ", depth + 1, config); //$NON-NLS-1$
				} else {
					if (controller.isComplexType((AccessPointType) sourceChildren[i])) {
						result = renderAssignmentCode(sourceChildTypeMapper, targetChildTypeMapper, inset, depth, result, config);
					} else {
						String sourceChildArray = sourceChildTypeMapper.renderGetterCode(false, true, config);
						String setterCode = targetChildTypeMapper.renderSetterCode(sourceChildArray, false, true, config); //????
						result = result + inset + "   " + setterCode + ";\n"; //$NON-NLS-1$ //$NON-NLS-2$
					}					
				}
			}
		}
		return result;
	}	
	
	public String renderAdditionCode(IMappingRenderer sourceMapper,	IMappingRenderer targetMapper, MappingConfiguration config) {	
		String result = ""; //$NON-NLS-1$
		String sourcePath = this.renderGetterCode(false, false, config);
		if (sourcePath.endsWith(".")) { //$NON-NLS-1$
			sourcePath = sourcePath.replace(".", ""); //$NON-NLS-1$ //$NON-NLS-2$
		}
		String targetIndex = targetMapper.renderGetterCode(false, false, config);		
		if (targetIndex.endsWith("]")) { //$NON-NLS-1$
			result = targetIndex;
			if (config.isAppend()) {
				int idx1 = targetIndex.lastIndexOf("["); //$NON-NLS-1$
				int idx2 = targetIndex.lastIndexOf("]"); //$NON-NLS-1$
				int idx = idx2 - idx1 + 1;
				targetIndex = targetIndex.substring(0, targetIndex.length() - idx);					
				targetIndex = targetIndex + ".length + 1"; //$NON-NLS-1$
				String xPath = controller.getXPathFor(targetMapper.getType());;
				config.getIndexMap().put(xPath, targetIndex);				
				result =  targetMapper.renderGetterCode(false, false, config);				
			}
			if (result.endsWith(".")) { //$NON-NLS-1$
				result = targetIndex.replace(".", ""); //$NON-NLS-1$ //$NON-NLS-2$
			}		
			result = result + " = " + sourcePath; //$NON-NLS-1$
		} else {
			int idx1 = targetIndex.lastIndexOf("get("); //$NON-NLS-1$
			int idx2 = targetIndex.lastIndexOf(")"); //$NON-NLS-1$
			int idx = idx2 - idx1 + 1;
			targetIndex = targetIndex.substring(0, targetIndex.length() - idx);
			if (targetIndex.endsWith(".")) { //$NON-NLS-1$
				targetIndex = targetIndex.substring(0, targetIndex.length() - 1);
			}
			if (config.isAppend()) {
				result = targetIndex + ".add(" + renderGetterCode(false, false, config) + ");";				 //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				String xPath = controller.getXPathFor(targetMapper.getType());
				String indexString = config.getIndexMap().get(xPath);
				result = targetIndex + ".set(" + indexString + ", " + renderGetterCode(false, false, config) + ");";   //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}			
		}
		return result;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private String createJavaPath(boolean ignoreArrays, boolean useVariableAsIndices, MappingConfiguration config) {
		String javaPath = ""; //$NON-NLS-1$
		if (controller.isRoot(messageType)) {
			javaPath = messageType.getId();
		} else {
			javaPath = controller.getXPathFor(messageType);
		}
		javaPath = javaPath.replace('/', '.');
		if (!ignoreArrays) {			
			javaPath = addJavaListInfo(useVariableAsIndices, config); 			 	
		}
		return javaPath;
	}
	
	private String createJavaAccessCode(String mappingCode, boolean fullPath, MappingConfiguration config) {
		String resultCode = ""; //$NON-NLS-1$
		String xPath = ""; //$NON-NLS-1$
		String[] segments = mappingCode.split("\\."); //$NON-NLS-1$
		int pathLength = segments.length;
		if (!fullPath) {
			pathLength = pathLength - 1;
		}		
		for (int i = 0 ; i < pathLength; i++) {
			String segment = segments[i];
			if (!segment.startsWith("(")) { //$NON-NLS-1$
				if (StringUtils.isEmpty(xPath)) {
					xPath = xPath + segment;	
				} else {
					xPath = xPath + "/" + segment; //$NON-NLS-1$
				}
				xPath = xPath.replaceAll("\\[0\\]", ""); //$NON-NLS-1$ //$NON-NLS-2$
				xPath = xPath.replaceAll("\\(\\)", ""); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (i > 0) {
				if (segment.startsWith("(") && config != null && config.getIndexMap().get(xPath) != null) { //$NON-NLS-1$
					segment = "get" + segment.substring(0,1).toUpperCase() + config.getIndexMap().get(xPath) + ")";; //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					if (segment.endsWith("]") && config != null && config.getIndexMap().get(xPath) != null) { //$NON-NLS-1$
						String indexString = config.getIndexMap().get(xPath);
						segment = segment.replace("[0]", "[" + indexString + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						segment = "get" + segment.substring(0,1).toUpperCase() + segment.substring(1); //$NON-NLS-1$
					} else {
						segment = "get" + segment.substring(0,1).toUpperCase() + segment.substring(1);					 //$NON-NLS-1$
					}
					if (!segment.startsWith("get(0)") && !segment.startsWith("get(n"))  //$NON-NLS-1$ //$NON-NLS-2$
					{
						if (!segment.endsWith("]")) { //$NON-NLS-1$
							segment = segment + "()";	 //$NON-NLS-1$
						}					
					} 
				}
			}
			resultCode = resultCode + "." + segment + "";;			 //$NON-NLS-1$ //$NON-NLS-2$
		}
		resultCode = resultCode.replaceFirst("\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
		return resultCode;
	}	
	
	private String addJavaListInfo(boolean useVariablesAsIndices, MappingConfiguration config) {
		MultipleAccessPathBrowserContentProvider apcp;
		if (controller.isSourceField(messageType)) {
	    	apcp = controller.getSourceAPB();
	    } else {
	    	apcp = controller.getTargetAPB();
	    }			
		String listIdx = ".(n)"; //$NON-NLS-1$
	    if (!useVariablesAsIndices) {
	       listIdx = ".(0)"; //$NON-NLS-1$
	    }
		String arrIdx = "()[n]"; //$NON-NLS-1$
	    if (!useVariablesAsIndices) {
	       arrIdx = "()[0]"; //$NON-NLS-1$
	    }
	    
	    String accessPath = createJavaPath(true, true, config);
	    accessPath = createJavaAccessCode(accessPath, true, config);

		String xPath = controller.getXPathFor(messageType);		
		String segments[] = xPath.split("/"); //$NON-NLS-1$
		AccessPointType parent = null;
		for (int i = 0; i < segments.length; i++) {
			if (i == 0) {
				parent = controller.getMessageTypeByName(segments[0]);				
			} else {
				parent = apcp.getChild(parent, segments[i]);
				if (controller.isJavaList(parent)) {
					segments[i] = segments[i] + listIdx;											
				}
				if (controller.isJavaArray(parent)) {
					segments[i] = segments[i] + arrIdx;											
				} 				
			}			
		}
		
		int n = 0;
		int adepth = 0;
		int arraySelectionDepth = 0;
		if (controller.isSourceField(messageType)) {
			arraySelectionDepth = controller.getArraySelectionDepthSource();
		} else {
			arraySelectionDepth = controller.getArraySelectionDepthTarget();
		}
		if (useVariablesAsIndices) {
	        for (int i = 0; i < segments.length; i++) {
	            if ((segments[i].indexOf("(n)") != -1) || (segments[i].indexOf("[n]") != -1)) { //$NON-NLS-1$ //$NON-NLS-2$
	            	adepth ++;
	            	if (adepth >= arraySelectionDepth) {
		                segments[i] = segments[i].replace("(n)", "(n" + n + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		                segments[i] = segments[i].replace("[n]", "[n" + n + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		                n++;	            		
	            	} else {
	            		segments[i] = segments[i].replace("(n)", "(0)"); //$NON-NLS-1$ //$NON-NLS-2$
	            		segments[i] = segments[i].replace("[n]", "[0]"); //$NON-NLS-1$ //$NON-NLS-2$
	            	}
	            }
	        }		   
		}
		String javaPath = ""; //$NON-NLS-1$
		for (int i = 0; i < segments.length; i++) {
			javaPath = javaPath + segments[i] + "."; //$NON-NLS-1$
		}
		javaPath = javaPath.substring(0, javaPath.length() - 1);
		return javaPath;
	}
	
	private String generateJavaListMapping(IMappingRenderer sourceMapper, IMappingRenderer targetMapper, String inset, int depth, MappingConfiguration config) {
		String result = ""; //$NON-NLS-1$
		String sourceArray = sourceMapper.renderGetterCode(false, true, config);
		String targetArray = targetMapper.renderGetterCode(false, true, config);
		int index = 0 + 0;
		if (depth > 9) {
			index = 1;
		}		
		String sourcePath = sourceArray.substring(0, sourceArray.length() - 8 - index);
		String targetPath = targetArray.substring(0, targetArray.length() - 8 - index);
		// Loop clause head code
		String varDeclaration= "n" + depth;  //$NON-NLS-1$
		Object o = controller.getUsedVar().get("n" + depth); //$NON-NLS-1$
		if (o == null) {
		   varDeclaration= "var n" + depth;  //$NON-NLS-1$
		   controller.getUsedVar().put("n" + depth, "defined"); //$NON-NLS-1$ //$NON-NLS-2$
		} 
		result = result + inset + targetPath + ".clear();\n"; //$NON-NLS-1$
		result = result + inset + "for (" + varDeclaration + " = 0; n" + depth	+ " < " + sourcePath + ".size(); ++n" + depth + "){\n";		 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		//PersonOut.getAdresses().add(PersonIn.getAdresses().get(0).getClass().newInstance());
		// Instantiate object contained in the array
		if (controller.isComplexType(sourceMapper.getType())) {
			result = result + inset + "   " + targetPath + ".add(" + sourcePath + ".get(0).getClass().newInstance());\n";  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			result = renderAssignmentCode(sourceMapper, targetMapper, inset, depth, result, config);
		} else {
			result = result + inset + "   " + targetPath + ".add(" + sourceArray + ");\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

		// Closing the loop clause
		result = result + inset + "}\n"; //$NON-NLS-1$
		result = result.replace("= null", "= "	+ AttributeUtil.getAttributeValue(sourceMapper.getType(), "RootElement")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		result = result.replace("null", AttributeUtil.getAttributeValue(targetMapper.getType(), "RootElement")); //$NON-NLS-1$ //$NON-NLS-2$
		return result;
	}	
	
	private String generateJavaArrayMapping(IMappingRenderer sourceMapper, IMappingRenderer targetMapper, String inset, int depth, MappingConfiguration config) {
		String result = ""; //$NON-NLS-1$
		String sourceArray = sourceMapper.renderGetterCode(false, true, config);
		String targetArray = targetMapper.renderGetterCode(false, true, config);
		int index = 0 + 0;
		if (depth > 9) {
			index = 1;
		}
		String sourcePath = sourceArray.substring(0, sourceArray.length() - 4 - index);
		// Loop clause head code
		String varDeclaration= "n" + depth;  //$NON-NLS-1$
		Object o = controller.getUsedVar().get("n" + depth); //$NON-NLS-1$
		if (o == null) {
		   varDeclaration= "var n" + depth;  //$NON-NLS-1$
		   controller.getUsedVar().put("n" + depth, "defined"); //$NON-NLS-1$ //$NON-NLS-2$
		} 
		result = result + inset + "for (" + varDeclaration + " = 0; n" + depth + " < " + sourcePath + ".length; ++n" + depth + "){\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		// Instantiate object contained in the array
		if (controller.isComplexType(sourceMapper.getType())) {
			result = renderAssignmentCode(sourceMapper, targetMapper, inset, depth,	result, config);
		} else {
			result = result + inset + "   " + targetArray + " = " + sourceArray //$NON-NLS-1$ //$NON-NLS-2$
					+ ";\n"; //$NON-NLS-1$
		}

		// Closing the loop clause
		result = result + inset + "}\n"; //$NON-NLS-1$
		result = result.replace("= null", "= " //$NON-NLS-1$ //$NON-NLS-2$
				+ AttributeUtil.getAttributeValue(sourceMapper.getType(), "RootElement")); //$NON-NLS-1$
		result = result.replace("null", AttributeUtil.getAttributeValue(targetMapper.getType(), "RootElement")); //$NON-NLS-1$ //$NON-NLS-2$
				
		return result;
	}

	public String getTypeString() {
 	   String className = AttributeUtil.getAttributeValue(messageType, "carnot:engine:className"); //$NON-NLS-1$
 	   if (className.indexOf("<") > -1) { //$NON-NLS-1$
 		  int idx1 = className.indexOf("<"); //$NON-NLS-1$
 		  int idx2 = className.indexOf(">"); //$NON-NLS-1$
 		  className = className.substring(idx1 + 1 , idx1 + idx2 - idx1);
 	   }
 	   if (className.endsWith("String")) { //$NON-NLS-1$
 		   return "String"; //$NON-NLS-1$
 	   }
 	   if (className.endsWith("java.lang.Integer")) { //$NON-NLS-1$
 	       return "Number"; //$NON-NLS-1$
 	   }
 	   if (className.endsWith("Double")) { //$NON-NLS-1$
 	       return "Number"; //$NON-NLS-1$
 	   }
 	   if (className.endsWith("Float")) { //$NON-NLS-1$
 		   return "Number"; //$NON-NLS-1$
 	   }
 	   if (className.endsWith("Boolean")) { //$NON-NLS-1$
 		   return "Number"; //$NON-NLS-1$
 	   }
 	   if (className.endsWith("Date")) { //$NON-NLS-1$
 	       return "Date"; //$NON-NLS-1$
 	   }   
 	   if (className.endsWith("Calendar")) { //$NON-NLS-1$
 	       return "Date"; //$NON-NLS-1$
 	   }
	   return className;
	}

}
