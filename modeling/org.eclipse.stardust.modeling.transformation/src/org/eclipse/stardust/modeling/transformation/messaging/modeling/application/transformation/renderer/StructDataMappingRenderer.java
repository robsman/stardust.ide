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

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.runtime.beans.BigData;
import org.eclipse.stardust.engine.core.struct.TypedXPath;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.MappingConfiguration;


public class StructDataMappingRenderer implements IMappingRenderer {
	private MessageTransformationController controller;
	private StructAccessPointType messageType;

	public StructDataMappingRenderer(MessageTransformationController controller,
			AccessPointType messageType) {
		super();
		this.controller = controller;
		this.messageType = (StructAccessPointType)messageType;
	}

	public AccessPointType getType() {
		return messageType;
	}

	public String renderGetterCode(boolean ignoreArrays, boolean variablesAsIndices, MappingConfiguration config) {
		String javaPath = createJavaPath(ignoreArrays, variablesAsIndices, config);
		return javaPath;
	}

	public String renderSetterCode(String getterCode, boolean ignoreArrays, boolean variablesAsIndices, MappingConfiguration config) {
	    String accessCode = createJavaPath(ignoreArrays, variablesAsIndices, config);
		if (controller.getArraySelectionDepthTarget() > 0 ||
		      MessageTransformationController.ENABLE_SIMPLE_CONTENT && accessCode.endsWith("@")) {
			return accessCode + " = " + getterCode; //$NON-NLS-1$
		}
	    return getterCode;
	}

	public String renderAdditionCode(IMappingRenderer sourceMapper,	IMappingRenderer targetMapper, MappingConfiguration config) {
		String sourcePath = createJavaPath(false, false, config);
		if (sourcePath.endsWith(".")) { //$NON-NLS-1$
			sourcePath = sourcePath.replace(".", ""); //$NON-NLS-1$ //$NON-NLS-2$
		}
		String targetIndex = targetMapper.renderGetterCode(false, false, config);
		if (targetIndex.endsWith(".")) { //$NON-NLS-1$
			targetIndex = targetIndex.replace(".", ""); //$NON-NLS-1$ //$NON-NLS-2$
		}
		String result = targetIndex;
		if (config.isAppend()) {
			int idx1 = targetIndex.lastIndexOf("["); //$NON-NLS-1$
			int idx2 = targetIndex.lastIndexOf("]"); //$NON-NLS-1$
			int idx = idx2 - idx1 + 1;
			targetIndex = targetIndex.substring(0, targetIndex.length() - idx);
			targetIndex = targetIndex + ".length + 1"; //$NON-NLS-1$
			String xPath = controller.getXPathFor(targetMapper.getType());
			config.getIndexMap().put(xPath, targetIndex);
			result = targetMapper.renderGetterCode(false, false, config);
		}
		if (result.endsWith(".")) { //$NON-NLS-1$
			result = targetIndex.replace(".", ""); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return result + " = " + sourcePath + ";"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public String renderListMappingCode(IMappingRenderer sourceMapper,	IMappingRenderer targetMapper, String inset, int depth, MappingConfiguration config) {
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

	public String renderAssignmentCode(IMappingRenderer sourceMapper, IMappingRenderer targetMapper, String inset, int depth, String result, MappingConfiguration config) {
		// Assign source child fields to target fields - multiple fields cause
		// recursion
		Object[] sourceChildren = controller.getSourceAPB().getChildren(sourceMapper.getType());
		Object[] targetChildren = controller.getTargetAPB().getChildren(targetMapper.getType());
		for (int i = 0; i < sourceChildren.length; i++) {
			IMappingRenderer sourceChildTypeMapper = new StructDataMappingRenderer(controller, (AccessPointType) sourceChildren[i]);
			IMappingRenderer targetChildTypeMapper = new StructDataMappingRenderer(controller, (AccessPointType) targetChildren[i]);
			if (controller.isList((AccessPointType) sourceChildren[i])) {
				result = result	+ this.renderListMappingCode(sourceChildTypeMapper, targetChildTypeMapper, inset + "   ", depth + 1, config); //$NON-NLS-1$
			} else {
				if (controller.isComplexType((AccessPointType) sourceChildren[i])) {
					result = renderAssignmentCode(sourceChildTypeMapper, targetChildTypeMapper, inset, depth, result, config);
				} else {
					String sourceChildArray = sourceChildTypeMapper.renderGetterCode(false, true, config);
					String targetChildArray = targetChildTypeMapper.renderGetterCode(false, true, config);
					String assignment = MessageTransformationController.setMappingExpression(targetChildArray + " = " + sourceChildArray,
					      null, sourceChildTypeMapper, targetChildTypeMapper);
                    result = result + inset + "   " + assignment + ";\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			}
		}
		return result;
	}



	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private String createJavaPath(boolean ignoreArrays, boolean useVariableAsIndices, MappingConfiguration config) {
		String javaPath = ""; //$NON-NLS-1$
		if (controller.isRoot(messageType)) {
			javaPath = messageType.getId();
		} else {
			javaPath = controller.getXPathFor(messageType);
		}
		javaPath = javaPath.replace('/', '.');
		if (!ignoreArrays) {
			 javaPath = addArrayInfo(useVariableAsIndices, config);
		}
		return javaPath;
	}

	private String addArrayInfo(boolean useVariablesAsIndices, MappingConfiguration config) {
	    String idx = "[n]"; //$NON-NLS-1$
	    if (!useVariablesAsIndices) {
	       idx = "[0]"; //$NON-NLS-1$
	    }
		TypedXPath parentXPath = messageType.getXPath();
		String segments[] = parentXPath.toString().split("/"); //$NON-NLS-1$
		int j = segments.length;
		while (!StringUtils.isEmpty(parentXPath.toString())) {
			j--;
			if (parentXPath.isList()) {
				String xPath = controller.getRootFor(messageType) + "/" + parentXPath.getXPath();  //$NON-NLS-1$
				if (config != null && config.getIndexMap().get(xPath) != null) {
					String idx2 = config.getIndexMap().get(xPath);
					segments[j] = segments[j] + "[" + idx2 + "]"; //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					segments[j] = segments[j] + idx;
				}
			}
			parentXPath = parentXPath.getParentXPath();
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
	            if (segments[i].indexOf("[n]") != -1) { //$NON-NLS-1$
	            	adepth ++;
	            	if (adepth >= arraySelectionDepth) {
		                segments[i] = segments[i].replace("[n]", "[n" + n + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		                n++;
	            	} else {
	            		segments[i] = segments[i].replace("[n]", "[0]"); //$NON-NLS-1$ //$NON-NLS-2$
	            	}
	            }
	        }
		}
		String javaPath = ""; //$NON-NLS-1$
		for (int i = 0; i < segments.length; i++) {
			javaPath = javaPath + segments[i] + "."; //$NON-NLS-1$
		}
		javaPath = controller.getRootFor(messageType) + "."	+ javaPath.substring(0, javaPath.length() - 1); //$NON-NLS-1$
		return javaPath;
	}

	public String getTypeString() {
	   TypedXPath xPath = ((StructAccessPointType)messageType).getXPath();
	   int type = xPath.getType();
	   if (type == BigData.STRING) {
	       return "String"; //$NON-NLS-1$
	   }
	   if (type == BigData.INTEGER) {
	       return "Number"; //$NON-NLS-1$
	   }
	   if (type == BigData.BYTE) {
	        return "Number"; //$NON-NLS-1$
	   }
	   if (type == BigData.LONG) {
	        return "Number"; //$NON-NLS-1$
	   }
	   if (type == BigData.SHORT) {
	        return "Number"; //$NON-NLS-1$
	   }
	   if (type == BigData.FLOAT) {
	        return "Number"; //$NON-NLS-1$
	   }
	   if (type == BigData.BOOLEAN) {
	        return "Boolean"; //$NON-NLS-1$
	   }
	   if (type == BigData.DOUBLE) {
	        return "Number"; //$NON-NLS-1$
	   }
	   if (type == BigData.NULL) {
	      if (!StringUtils.isEmpty(xPath.getXsdTypeName())) {
	        return xPath.getXsdTypeName();
	      } else {
	        return xPath.getXsdElementName();
	      }
	   }
	   if (type == BigData.DATE) {
	      return "Date"; //$NON-NLS-1$
	   }
	   if (type == BigData.PERIOD) {
	      return "String"; //$NON-NLS-1$
	   }
      if (type == BigData.DECIMAL) {
         return "Decimal"; //$NON-NLS-1$
      }

	   return " ";	    //$NON-NLS-1$
	}
}