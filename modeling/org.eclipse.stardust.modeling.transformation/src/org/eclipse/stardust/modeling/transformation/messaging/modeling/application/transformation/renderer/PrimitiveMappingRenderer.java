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
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.MappingConfiguration;



public class PrimitiveMappingRenderer implements IMappingRenderer {
	
	private MessageTransformationController controller;
	private AccessPointType messageType;

	public PrimitiveMappingRenderer(MessageTransformationController controller,
			AccessPointType messageType) {
		super();
		this.controller = controller;
		this.messageType = messageType;
	}

	public AccessPointType getType() {		
		return messageType;
	}

	public String renderAssignmentCode(IMappingRenderer sourceMapper,
			IMappingRenderer targetMapper, String inset, int depth,
			String result, MappingConfiguration config) {
		return null;
	}

	public String renderGetterCode(boolean ignoreArrays,
			boolean variablesAsIndices, MappingConfiguration config) {
		return messageType.getName();
	}

	public String renderListMappingCode(IMappingRenderer sourceMapper,	IMappingRenderer targetMapper, String inset, int depth, MappingConfiguration config) {
		String result = ""; //$NON-NLS-1$
		String sourceArray = sourceMapper.renderGetterCode(false, true, config);
		String targetArray = targetMapper.renderGetterCode(false, true, config);
		if (targetMapper instanceof StructDataMappingRenderer && controller.isList(targetMapper.getType())) {
			int idx1 = targetArray.lastIndexOf("["); //$NON-NLS-1$
			int idx2 = targetArray.lastIndexOf("]"); //$NON-NLS-1$
			targetArray = targetArray.substring(0, targetArray.length() - (idx2 - idx1) - 1);
			targetArray = targetArray + "[n0]"; //$NON-NLS-1$
		}
		String targetCondition = targetMapper.renderGetterCode(false, true, config);
		if (targetCondition.endsWith("]") && targetMapper instanceof StructDataMappingRenderer) { //$NON-NLS-1$
			int idx1 = targetCondition.lastIndexOf("["); //$NON-NLS-1$
			int idx2 = targetCondition.lastIndexOf("]"); //$NON-NLS-1$
			targetCondition = targetCondition.substring(0, targetCondition.length() - (idx2 - idx1) - 1);
		}
		
		int index = 0 + 0;
		if (depth > 9) {
			index = 1;
		}
		String sourcePath = sourceArray.substring(0, sourceArray.length() - 4 - index);
		String targetPath = targetArray.substring(0, sourceArray.length() - 4 - index);
		// Loop clause head code
		String varDeclaration= "n" + depth;  //$NON-NLS-1$
		Object o = controller.getUsedVar().get("n" + depth); //$NON-NLS-1$
		if (o == null) {
		   varDeclaration= "var n" + depth;  //$NON-NLS-1$
		   controller.getUsedVar().put("n" + depth, "defined"); //$NON-NLS-1$ //$NON-NLS-2$
		} 
		result = result + inset + "for (" + varDeclaration + " = 0; n" + depth + " < " + targetCondition + ".length; ++n" + depth + "){\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

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

	public String renderSetterCode(String getterCode, boolean ignoreArrays,
			boolean variablesAsIndices, MappingConfiguration config) {
		return getterCode;
	}

	public String getTypeString() {
	       AttributeType attType = AttributeUtil.getAttribute(messageType, "carnot:engine:type"); //$NON-NLS-1$
           if (attType != null) {
              String typeString = attType.getValue();
              if (typeString != null) {
                if (typeString.equals("java.lang.String")) { //$NON-NLS-1$
                    return "String"; //$NON-NLS-1$
                }
                if (typeString.equals("java.lang.Byte")) { //$NON-NLS-1$
                   return "Number"; //$NON-NLS-1$
               }                
                if (typeString.equals("java.lang.Integer")) { //$NON-NLS-1$
                    return "Number"; //$NON-NLS-1$
                }
                if (typeString.equals("java.lang.Long")) { //$NON-NLS-1$
                    return "Number"; //$NON-NLS-1$
                }
                if (typeString.equals("java.lang.Float")) { //$NON-NLS-1$
                    return "Number"; //$NON-NLS-1$
                }
                if (typeString.equals("java.lang.Boolean")) { //$NON-NLS-1$
                    return "Boolean"; //$NON-NLS-1$
                }
                if (typeString.equals("java.lang.Double")) { //$NON-NLS-1$
                    return "Number"; //$NON-NLS-1$
                }   
                if (typeString.equalsIgnoreCase("double")) { //$NON-NLS-1$
                   return "Number"; //$NON-NLS-1$
                }
                if (typeString.equalsIgnoreCase("int")) { //$NON-NLS-1$
                   return "Number"; //$NON-NLS-1$
                }
                if (typeString.equalsIgnoreCase("float")) { //$NON-NLS-1$
                   return "Number"; //$NON-NLS-1$
                }                
                if (typeString.equalsIgnoreCase("string")) { //$NON-NLS-1$
                   return "String"; //$NON-NLS-1$
                }
                if (typeString.equalsIgnoreCase("short")) { //$NON-NLS-1$
                   return "Number"; //$NON-NLS-1$
                }       
                if (typeString.equalsIgnoreCase("long")) { //$NON-NLS-1$
                   return "Number"; //$NON-NLS-1$
                }                   
                if (typeString.equalsIgnoreCase("byte")) { //$NON-NLS-1$
                   return "Number"; //$NON-NLS-1$
                }   
                if (typeString.equalsIgnoreCase("boolean")) { //$NON-NLS-1$
                   return "Boolean"; //$NON-NLS-1$
                }      
                if (typeString.equalsIgnoreCase("calendar")) { //$NON-NLS-1$
                   return "Date"; //$NON-NLS-1$
                }  
                if (typeString.equalsIgnoreCase("timestamp")) { //$NON-NLS-1$
                   return "Date"; //$NON-NLS-1$
                } 
                return "String"; //$NON-NLS-1$
              }
           }
		return " "; //$NON-NLS-1$
	}
	
    public String renderAdditionCode(IMappingRenderer sourceMapper, IMappingRenderer targetMapper, MappingConfiguration config) {   
       String sourcePath = sourceMapper.renderGetterCode(false, false, config);
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

}
