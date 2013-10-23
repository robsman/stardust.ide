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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.runtime.beans.BigData;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.transformation.model.MappingModelUtil;
import org.eclipse.stardust.engine.extensions.transformation.model.mapping.ExternalClass;
import org.eclipse.stardust.engine.extensions.transformation.model.mapping.FieldMapping;
import org.eclipse.stardust.engine.extensions.transformation.model.mapping.MappingFactory;
import org.eclipse.stardust.engine.extensions.transformation.model.mapping.TransformationProperty;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.CodeCompletionHelper;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.core.ui.AccessPathBrowserContentProvider;
import org.eclipse.stardust.modeling.javascript.editor.JSCompilationUnitEditor.RegionWithLineOffset;
import org.eclipse.stardust.modeling.javascript.editor.JavaScriptValidator;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.MessageTransformationUtils;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.Constants;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.FieldMappingsComparator;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.renderer.IMappingRenderer;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.renderer.PrimitiveMappingRenderer;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.renderer.SerializableMappingRenderer;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.renderer.StructDataMappingRenderer;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.MappingConfiguration;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.MultipleAccessPathBrowserContentProvider;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wst.jsdt.core.IJavaScriptProject;
import org.eclipse.wst.jsdt.core.JavaScriptCore;
import org.eclipse.wst.jsdt.core.compiler.IProblem;

public class MessageTransformationController {


    private Map<String, FieldMapping> fieldMappings = new HashMap<String, FieldMapping>();
    private Map<String, AccessPointType> fieldPathsToAccessPoints = new HashMap<String, AccessPointType>();
	private List<AccessPointType> availableMessageTypes = new ArrayList<AccessPointType>();
	private String masterDocument;
	private String fieldsDocument = "//Fields\n"; //$NON-NLS-1$
	private String expressionDocument = "//Expressions\n"; //$NON-NLS-1$
	private String statementsDocument = "//Statements\n"; //$NON-NLS-1$
	private RegionWithLineOffset fieldsRegion;
	private RegionWithLineOffset statementsRegion;
	private RegionWithLineOffset expressionRegion;
	private AccessPointType selectedSourceField;
	private AccessPointType selectedTargetField;
	private List<AccessPointType> selectedSourceFields = new ArrayList<AccessPointType>();
	private FieldMapping selectedTargetFieldMapping;
	private String draggedText;
	private List<AccessPointType> sourceMessageTypes;
	private List<AccessPointType> targetMessageTypes;
	private List<AccessPointType> externalClassTypes;
	private List<AccessPointType> externalClassTypesMissing;
	private ModelType modelType = null;
	private MultipleAccessPathBrowserContentProvider sourceAPB;
	private MultipleAccessPathBrowserContentProvider targetAPB;
	private Map<StructAccessPointType, String> xPathMap = new HashMap<StructAccessPointType, String>();
	private AccessPathBrowserContentProvider apc = new AccessPathBrowserContentProvider(
		DirectionType.INOUT_LITERAL);
	private TransformationProperty trafoProp;
	private boolean ignoreAnnotationChanges = false;
	private IModelElement application;
	private String jScriptProlog;
	private Map<String, IProblem[]> errorCache = new HashMap<String, IProblem[]>();
	private Map<String,String> usedVar = new HashMap<String,String>();
	private JavaScriptValidator javaScriptValidator;
    private IProject project;
	private boolean simpleMode;
	private boolean withSerialiable;
    private List<AccessPointType> invalidAccessPoints = new ArrayList<AccessPointType>();
    private static final Type[] TYPES = {
        Type.Calendar, Type.String, Type.Timestamp, Type.Boolean, Type.Byte, Type.Char,
        Type.Double, Type.Float, Type.Integer, Type.Long, Type.Short};
    private IJavaScriptProject javaProject;
    private IModelElement modelElement;
    private MessageTransformationUtils mtaUtils;
    private boolean externalReference = false;

    public MessageTransformationUtils getMtaUtils() {
		return mtaUtils;
	}

	public int getArraySelectionDepthSource() {
		return arraySelectionDepthSource;
	}

	public int getArraySelectionDepthTarget() {
		return arraySelectionDepthTarget;
	}

	private int arraySelectionDepthSource;
	private int arraySelectionDepthTarget;


	public Map<String, String> getUsedVar() {
		return usedVar;
	}

	public IProject getProject() {
		return project;
	}

    public IModelElement getModelElement() {
		return modelElement;
	}

	public String getNameString() {
		if (this.isSimpleMode()) {
			return Modeling_Messages.NAME_PARAMETER;
		}
		return Modeling_Messages.NAME_MESSAGE;
	}

    public void reset() {
		availableMessageTypes.clear();
	}

	public void intializeModel(ModelType model, IModelElement element) {
	   modelElement = element;
	   intializeModel(model, null, element);
	}

	public void intializeModel(ModelType model, IModelElementNodeSymbol symbol,
			IModelElement element) {

	    mtaUtils = new MessageTransformationUtils(ModelUtils.getProjectFromEObject(element), element, model);
		String xmlString = AttributeUtil.getAttributeValue((IExtensibleElement) element, Constants.TRANSFORMATION_PROPERTY);
		if (xmlString != null) {
			trafoProp = (TransformationProperty) MappingModelUtil.transformXML2Ecore(xmlString);
		}
		CodeCompletionHelper.getInstance().getTypeMap().clear();

		this.application = element;
		this.modelType = model;

        //Retrieve the for transformation available types from the model (Data, StructTypes).
		//All found types are implicitly added to the available message types.
		availableMessageTypes.clear();
		extractMessageTypesFromModelData(model, element);
        extractMessagesFromStructuredTypes(model);
        ExternalPackages packages = model.getExternalPackages();
        if (packages != null)
        {
           for (ExternalPackage pkg : packages.getExternalPackage())
           {
              ModelType otherModel = ModelUtils.getExternalModel(pkg);
              if (otherModel != null)
              {
                 extractMessagesFromStructuredTypes(otherModel);
              }
           }
        }

        //Extract the already defined Input - and Outputmessages from the model element
    	sourceMessageTypes = new ArrayList<AccessPointType>();
		targetMessageTypes = new ArrayList<AccessPointType>();
		externalClassTypes = new ArrayList<AccessPointType>();
		externalClassTypesMissing = new ArrayList<AccessPointType>();
      extractAccessPoints(element);
      if (trafoProp != null)
      {
         extractExternalClassesfromTrafoprop(model, element);
      }
	}

   private void extractAccessPoints(IModelElement element)
   {
      DataTypeType structuredDataType = ModelUtils.getDataType(element, StructuredDataConstants.STRUCTURED_DATA);
      // trafoProp = (TransformationProperty)
      // MappingModelUtil.transformXML2Ecore(xmlString.getBytes());
      // take access points from the model element
      // IN - for source messages
      // OUT - for target messages
      List<AccessPointType> toBeRemoved = new ArrayList<AccessPointType>();
      RuntimeException rte = null;
      boolean failed = false;
      invalidAccessPoints.clear();
      AccessPointType extractedAP = null;

      for (int i = 0; i < getAccessPoint(application).size(); i++)
      {
         AccessPointType accessPoint = getAccessPoint(application).get(i);
         if (isPrimitive(accessPoint))
         {
            extractedAP = mtaUtils.extractPrimitiveAccessPoints(structuredDataType, accessPoint);
         }
         else
         {
            if (isSerializable(accessPoint))
            {
               extractedAP = mtaUtils.extractSerializableAccessPoints(structuredDataType, accessPoint);
            }
            else
            {
               if (AttributeUtil.getAttribute(accessPoint, "carnot:engine:dataType") == null) //$NON-NLS-1$
               {
                  toBeRemoved.add(accessPoint);
               }
               try
               {
                  extractedAP = mtaUtils.extractStructAccessPoints(structuredDataType, accessPoint);
               }
               catch (RuntimeException ex)
               {
                  invalidAccessPoints.add(accessPoint);
                  failed = true;
                  rte = ex;
               }
            }

         }
         if (extractedAP != null && extractedAP.getDirection().equals(DirectionType.IN_LITERAL))
         {
            sourceMessageTypes.add(extractedAP);
         }
         else
         {
            if (extractedAP != null)
            {
               targetMessageTypes.add(extractedAP);
            }
         }
      }
      if (failed)
      {
         throw rte;
      }
      removeAccessPoints(toBeRemoved);
   }

   private void extractMessagesFromStructuredTypes(ModelType model)
   {
      for (Iterator<TypeDeclarationType> j = model.getTypeDeclarations().getTypeDeclaration()
              .iterator(); j.hasNext();) {
          TypeDeclarationType typeAdapter = j.next();
          if(TypeDeclarationUtils.getType(typeAdapter) == TypeDeclarationUtils.COMPLEX_TYPE)
          {
              AccessPointType messageType = mtaUtils.createStructAccessPoint((EObject) typeAdapter, typeAdapter.getId(),
                    DirectionType.INOUT_LITERAL, false);
              availableMessageTypes.add(messageType);
          }
      }
   }

   private void extractMessageTypesFromModelData(ModelType model, IModelElement element)
   {
	  DataTypeType primitiveType = ModelUtils.getDataType(element, "primitive"); //$NON-NLS-1$
	  List<Type> primitiveTypes = Arrays.asList(TYPES);
	  for (Iterator<Type> i = primitiveTypes.iterator(); i.hasNext();)
	  {
		  Type type = i.next();
		  AccessPointType accessPoint = mtaUtils.createPrimitiveAccessPoint(primitiveType, type.getName(), DirectionType.IN_LITERAL);
          if (accessPoint != null)
          {
             availableMessageTypes.add(accessPoint);
          }
	  }
   }

   private void extractExternalClassesfromTrafoprop(ModelType model, IModelElement element) {
	   if (trafoProp.getExternalClasses() != null) {
		   for(Iterator<ExternalClass> s = trafoProp.getExternalClasses().iterator(); s.hasNext();) {
			    ExternalClass externalClass = s.next();
				String className = externalClass.getClassName();
				if (className != null) {
					String typeName = externalClass.getInstanceName();
					AccessPointType accessPoint = mtaUtils.createSerializableAccessPoints(element, className, typeName);
					if (mtaUtils.classExists(className)) {
						accessPoint.setElementOid(1);
					} else {
						accessPoint.setElementOid(-99);
					}
					externalClassTypes.add(accessPoint);
				}
			}
	   }
	}


   public void removeInvalidAccessPoints() {
      removeAccessPoints(invalidAccessPoints);
   }

   public void initializeMappings(IModelElement element) {
		if (trafoProp != null) {
			for (Iterator<FieldMapping> k = trafoProp.getFieldMappings().iterator(); k.hasNext();) {
				FieldMapping fm = k.next();
				if (fm != null && fieldPathExists(fm.getFieldPath())) {
					fieldMappings.put(fm.getFieldPath(), fm);
				} else {

				}
			}
			refreshJavaScriptContext();
		}
	}

    private boolean fieldPathExists(String fieldPath) {
       for (Iterator<AccessPointType> i = targetMessageTypes.iterator(); i.hasNext();) {
           String realfieldPath = fieldPath;
	       boolean isContained = false;
	       AccessPointType messageType = i.next();
	       if (messageType instanceof StructAccessPointType) {
	          realfieldPath = realfieldPath.substring(realfieldPath.indexOf("/") + 1, realfieldPath.length());  //$NON-NLS-1$
	          isContained = ((StructAccessPointType)messageType).getXPathMap().containsXPath(realfieldPath);
	          String firstSegment = fieldPath.substring(0, fieldPath.indexOf("/")); //$NON-NLS-1$
	          isContained = isContained &&  (firstSegment.equalsIgnoreCase(messageType.getId()));
	           //It may be possible that this is an Attribute type - check this
	           if (!isContained) {
	              String lastSegment = realfieldPath.substring(realfieldPath.lastIndexOf("/") + 1); //$NON-NLS-1$
	              String attributeFieldPath = realfieldPath;
	              if (realfieldPath.lastIndexOf("/") > 0) { //$NON-NLS-1$
	                  attributeFieldPath = attributeFieldPath.substring(0, attributeFieldPath.lastIndexOf("/"));                      //$NON-NLS-1$
	                  attributeFieldPath = attributeFieldPath + "/@" + lastSegment; 	                                      //$NON-NLS-1$
	              } else {
	                 attributeFieldPath = "@" + attributeFieldPath; //$NON-NLS-1$
	              }
	              isContained = ((StructAccessPointType)messageType).getXPathMap().containsXPath(attributeFieldPath);
	           }
	       } else {
	    	  if (isSerializable(messageType)) {
	    		  isContained = true;
	    	  } else {
		          realfieldPath = realfieldPath.replaceAll("/", ""); //$NON-NLS-1$ //$NON-NLS-2$
		          isContained = messageType.getName().equalsIgnoreCase(realfieldPath);
	    	  }
	       }
	       if (isContained) {
	           return true;
	       }
	   }
	   return false;
	}

    public void refreshJavaScriptContext()
    {
       CodeCompletionHelper.getInstance().clear();
       jScriptProlog = "function ippInitialize(e) {return null;}\n"; //$NON-NLS-1$
       jScriptProlog = jScriptProlog + "function ippImport(e) {return null;}\n"; //$NON-NLS-1$

       for (Iterator<AccessPointType> i = sourceMessageTypes.iterator(); i.hasNext();)
       {
          AccessPointType apt = i.next();
          if (apt instanceof StructAccessPointType)
          {
             StructAccessPointType messageType = (StructAccessPointType) apt;
             String typeString = getStructuredTypeName(messageType);
             AttributeType attributeType = AttributeUtil.getAttribute(messageType,
                   "carnot:engine:dataType"); //$NON-NLS-1$
             String id = ""; //$NON-NLS-1$
             if (attributeType != null)
             {
                if (attributeType.getReference() != null)
                {
                   IdentifiableReference ref = attributeType.getReference();
                   if (ref != null)
                   {
                      ModelType rModel = ModelUtils.findContainingModel(ref.getIdentifiable());
                      if (rModel != null && !rModel.getId().equals(modelType.getId()))
                      {
                         id = rModel.getId() + "/"; //$NON-NLS-1$
                      }
                   }
                }
             }
             jScriptProlog = jScriptProlog + "var " + messageType.getId() //$NON-NLS-1$
                   + " = ippInitialize(\"" + id + typeString + "\");\n"; //$NON-NLS-1$ //$NON-NLS-2$
          }
          else
          {
             AccessPointType messageType = apt;
             if (isSerializable(apt))
             {
                String className = AttributeUtil.getAttributeValue(apt,
                      "carnot:engine:className"); //$NON-NLS-1$
                jScriptProlog = jScriptProlog + "var " + messageType.getId() //$NON-NLS-1$
                      + " = ippInitialize(\"" + className + "\");\n"; //$NON-NLS-1$ //$NON-NLS-2$
             }
             else
             {
                jScriptProlog = jScriptProlog + "var " + messageType.getId() //$NON-NLS-1$
                      + " = new " + getTypeString(messageType) + "();\n"; //$NON-NLS-1$ //$NON-NLS-2$
                CodeCompletionHelper.getInstance()
                      .getTypeMap()
                      .put(messageType.getId(), messageType);
             }
          }
       }
       for (Iterator<AccessPointType> i = targetMessageTypes.iterator(); i.hasNext();)
       {
          AccessPointType apt = i.next();
          if (apt instanceof StructAccessPointType)
          {
             StructAccessPointType messageType = (StructAccessPointType) apt;
             String typeString = getStructuredTypeName(messageType);
             AttributeType attributeType = AttributeUtil.getAttribute(messageType,
                   "carnot:engine:dataType"); //$NON-NLS-1$
             String id = ""; //$NON-NLS-1$
             if (attributeType != null)
             {
                if (attributeType.getReference() != null)
                {
                   IdentifiableReference ref = attributeType.getReference();
                   if (ref != null)
                   {
                      ModelType rModel = ModelUtils.findContainingModel(ref.getIdentifiable());
                      if (rModel != null && !rModel.getId().equals(modelType.getId()))
                      {
                         id = rModel.getId() + "/"; //$NON-NLS-1$
                      }
                   }
                }
             }
             jScriptProlog = jScriptProlog + "var " + messageType.getId() //$NON-NLS-1$
                   + " = ippInitialize(\"" + id + typeString + "\");\n"; //$NON-NLS-1$ //$NON-NLS-2$
          }
          else
          {
             AccessPointType messageType = apt;
             if (isSerializable(apt))
             {
                String className = AttributeUtil.getAttributeValue(apt,
                      "carnot:engine:className"); //$NON-NLS-1$
                jScriptProlog = jScriptProlog + "var " + messageType.getId() //$NON-NLS-1$
                      + " = ippInitialize(\"" + className + "\");\n"; //$NON-NLS-1$ //$NON-NLS-2$
             }
             else
             {
                jScriptProlog = jScriptProlog + "var " + messageType.getId() //$NON-NLS-1$
                      + " = new " + getTypeString(messageType) + "();\n"; //$NON-NLS-1$ //$NON-NLS-2$
                CodeCompletionHelper.getInstance()
                      .getTypeMap()
                      .put(messageType.getId(), messageType);
             }
          }
       }
       for (Iterator<AccessPointType> i = externalClassTypes.iterator(); i.hasNext();)
       {
          AccessPointType apt = i.next();
          if (apt instanceof StructAccessPointType)
          {
             StructAccessPointType messageType = (StructAccessPointType) apt;
             String typeString = getStructuredTypeName(messageType);
             jScriptProlog = jScriptProlog + "var " + messageType.getId() //$NON-NLS-1$
                   + " = ippInitialize(\"" + typeString + "\");\n"; //$NON-NLS-1$ //$NON-NLS-2$
          }
          else
          {
             AccessPointType messageType = apt;
             if (isSerializable(apt) && apt.getElementOid() == 1)
             {
                String className = AttributeUtil.getAttributeValue(apt,
                      "carnot:engine:className"); //$NON-NLS-1$
                jScriptProlog = jScriptProlog + "var " + messageType.getId() //$NON-NLS-1$
                      + " = ippInitialize(\"" + className + "\");\n"; //$NON-NLS-1$ //$NON-NLS-2$
                CodeCompletionHelper.getInstance()
                      .getExternalTypeMap()
                      .put(messageType.getId(), messageType);
             }
          }
       }
       fieldsDocument = "//Fields\n" + jScriptProlog; //$NON-NLS-1$

    }

	public String getStructuredTypeName(StructAccessPointType messageType) {
		TypeDeclarationType declarationType = (TypeDeclarationType) AttributeUtil
				.getIdentifiable(messageType, StructuredDataConstants.TYPE_DECLARATION_ATT);
		return declarationType.getId();
	}

	public void recalculateRegions(IDocument document) {
		int lines = document.getNumberOfLines();
		int fo, fl, so, sl, eo, el;
		fo = fl = so = sl = eo = el = 0;
		for (int i = 0; i < lines - 1; i++) {
			try {
				IRegion region = document.getLineInformation(i);
				String content = document.get(region.getOffset(), document
						.getLineLength(i));
				if (content.startsWith("//Fields")) { //$NON-NLS-1$
					fo = document.getLineOffset(i + 1);
				}
				if (content.startsWith("//Statements")) { //$NON-NLS-1$
					fl = (document.getLineOffset(i - 1) + document
							.getLineLength(i - 1))
							- fo;
					so = document.getLineOffset(i + 1);
					fieldsRegion = new RegionWithLineOffset(fo, fl);
					fieldsRegion.setLineOffset(i);
					document.get(eo, el);
				}
				if (content.startsWith("//Expression")) { //$NON-NLS-1$
					sl = document.getLineOffset(i - 1)
							+ document.getLineLength(i - 1) - so;
					eo = document.getLineOffset(i + 1);
					el = document.getLineOffset(lines - 1)
							+ document.getLineLength(lines - 1) - eo;
					statementsRegion = new RegionWithLineOffset(so, sl);
					statementsRegion.setLineOffset(i);
					expressionRegion = new RegionWithLineOffset(eo, el);
					expressionRegion.setLineOffset(i + 1);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	public Map<String, FieldMapping> getFieldMappings() {
		return fieldMappings;
	}

	public List<AccessPointType> getAvailableMessageTypes() {
		return availableMessageTypes;
	}

	public String getMasterDocument() {
		masterDocument = fieldsDocument + statementsDocument
				+ expressionDocument;
		return masterDocument;
	}

	public void setMasterDocument(String masterDocument) {
		this.masterDocument = masterDocument;
	}

	public RegionWithLineOffset getFieldsRegion() {
		return fieldsRegion;
	}

	public RegionWithLineOffset getStatementsRegion() {
		return statementsRegion;
	}

	public RegionWithLineOffset getExpressionRegion() {
		return expressionRegion;
	}

    public boolean targetMessageFieldSelected(TreeSelection targetTreeSelection) {
       selectedTargetField = (AccessPointType) targetTreeSelection
               .getFirstElement();
       if (selectedTargetField == null) {
           return false;
       }

           String xPath = getXPathFor(selectedTargetField);
           if (selectedTargetField != null && xPath != null) {
               this.selectedTargetFieldMapping = fieldMappings
                       .get(xPath);
               if (null != selectedTargetFieldMapping) {
                   if (selectedTargetFieldMapping.getMappingExpression() != null) {
                       expressionDocument = "//Expression\n" //$NON-NLS-1$
                               + selectedTargetFieldMapping.getMappingExpression();
                   } else {
                       expressionDocument = "//Expression\n" + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
                   }
                   statementsDocument = "//Statements\n" + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
                   return true;
               }

       }
       return false;
   }

   public void sourceMessageFieldSelected(TreeSelection sourceTreeSelection) {
       selectedSourceFields = extractSelectedElements(sourceTreeSelection);
       selectedSourceField = (AccessPointType) sourceTreeSelection.getFirstElement();
       if (selectedSourceField == null) {
           return;
       }
       if (!isSimpleMode()) {
           arraySelectionDepthSource = getArraySelectionDepth(selectedSourceField);
           String xPath = this.getXPathFor(selectedSourceField);
           if (selectedSourceField != null && xPath != null) {
               FieldMapping fm = fieldMappings.get(xPath);
               if (fm != null) {
                   String javaPath = getMapperByType(selectedSourceField).renderGetterCode(false, false, null);
                   draggedText = javaPath;
                   draggedText = draggedText.replaceAll("@",""); //$NON-NLS-1$ //$NON-NLS-2$
               }
           } else {
               if (selectedSourceField != null && isRoot(selectedSourceField)) {
                   draggedText = selectedSourceField.getName();
                   draggedText = draggedText.replaceAll("@",""); //$NON-NLS-1$ //$NON-NLS-2$
               }
           }
       }
   }

	private List<AccessPointType> extractSelectedElements(TreeSelection sourceTreeSelection) {
		List<AccessPointType> result = new ArrayList<AccessPointType>();
		for (Iterator<?> i = sourceTreeSelection.iterator(); i.hasNext();) {
			AccessPointType messageType = (AccessPointType) i
					.next();
			result.add(messageType);
		}
		return result;
	}

	public String renderXPathString(TreePath treePath) {
		String result = ((StructAccessPointType) treePath.getFirstSegment())
				.getId();
		for (int i = 1; i < treePath.getSegmentCount(); i++) {
			result = result + "/" //$NON-NLS-1$
					+ ((StructAccessPointType) treePath.getSegment(i)).getId();
		}
		return result;
	}

	public AccessPointType getSelectedSourceField() {
		return selectedSourceField;
	}

	public AccessPointType getSelectedTargetField() {
		return selectedTargetField;
	}

	public FieldMapping getSelectedTargetFieldMapping() {
		return selectedTargetFieldMapping;
	}

	public boolean isBasicTargetFieldMapping() {
		return (null == selectedTargetFieldMapping)
				|| !selectedTargetFieldMapping.isAdvancedMapping();
	}

	public void setBasicTargetFieldMapping(boolean basic) {
		if (null != selectedTargetFieldMapping) {
			selectedTargetFieldMapping.setAdvancedMapping(!basic);
		}
	}

	public String getDraggedText() {
		return draggedText;
	}

	public void updateExpressions(String text) {
		expressionDocument = "//Expression\n" + text; //$NON-NLS-1$
		FieldMapping fm = fieldMappings
				.get(getXPathFor(selectedTargetField));
		if (fm != null) {
			String expression = text;
			fm.setMappingExpression(expression);
			validateMapping(fm, true);
		}
	}

	public void updateStatements(String text) {
		statementsDocument = "//Statements\n" + text; //$NON-NLS-1$
	}

	public void saveFields(IModelElementNodeSymbol symbol, IModelElement element) {
		// Store
		TransformationProperty property = MappingFactory.eINSTANCE.createTransformationProperty();
		for (Iterator<FieldMapping> j = fieldMappings.values().iterator(); j.hasNext();) {
			FieldMapping fm = j.next();
			if (fm != null && !fm.getMappingExpression().equalsIgnoreCase("")) { //$NON-NLS-1$
				property.getFieldMappings().add(fm);
				AccessPointType accessPoint = fieldPathsToAccessPoints.get(fm.getFieldPath());
				if (accessPoint != null && this.isContentMapping(accessPoint)) {
					fm.setContentMapping(true);
				}
			}
		}

		ECollections.sort(property.getFieldMappings(), new FieldMappingsComparator());

		if (externalClassTypes != null) {
	      for (Iterator<AccessPointType> j = externalClassTypes.iterator(); j.hasNext();) {
	         AccessPointType apt = j.next();
	         ExternalClass externalClass = MappingFactory.eINSTANCE.createExternalClass();
	         externalClass.setClassName(AttributeUtil.getAttributeValue(apt, PredefinedConstants.CLASS_NAME_ATT));
	         externalClass.setInstanceName(apt.getName());
	         property.getExternalClasses().add(externalClass);
	      }
		}

		String xmlString = MappingModelUtil.transformEcore2XML(property);

		AttributeUtil.setAttribute((IExtensibleElement) element,
				Constants.TRANSFORMATION_PROPERTY, xmlString);

	}

	public void performDropMapping(AccessPointType mapTarget, MappingConfiguration config) {
		IMappingRenderer sourceMapper = getMapperByType(selectedSourceField);
		IMappingRenderer targetMapper = getMapperByType(mapTarget);
		this.arraySelectionDepthSource = this.getArraySelectionDepth(selectedSourceField);
		this.arraySelectionDepthTarget = this.getArraySelectionDepth(mapTarget);
		if (checkStandardMappingAllowed(mapTarget, selectedSourceField)) {
			if (selectedSourceFields.size() > 1) {
				performMultipleDropMapping(mapTarget, config);
			} else {
				performSingleDropMapping(sourceMapper, targetMapper, config);
			}
		} else {
			//This is only allowed for root types of the same data type (Struct / Serializable)
			//TO BE REFACTORED - move this to the interface implementors as well!!!
			String sourceType = AttributeUtil.getAttributeValue(sourceMapper.getType(), "carnot:engine:dataType"); //$NON-NLS-1$
			String targetType = AttributeUtil.getAttributeValue(targetMapper.getType(), "carnot:engine:dataType"); //$NON-NLS-1$
			if (sourceType == null || targetType == null) {
				sourceType = AttributeUtil.getAttributeValue(sourceMapper.getType(), "carnot:engine:className"); //$NON-NLS-1$
				targetType = AttributeUtil.getAttributeValue(targetMapper.getType(), "carnot:engine:className"); //$NON-NLS-1$
			}
 			if (sourceType != null && targetType != null && sourceType.equalsIgnoreCase(targetType)) {
				if (sourceAPB.hasChildren(sourceMapper.getType()) && targetAPB.hasChildren(targetMapper.getType())) {
					Object[] sourceChildren = sourceAPB.getChildren(sourceMapper.getType());
					Object[] targetChildren = targetAPB.getChildren(targetMapper.getType());
					for (int i = 0; i < targetChildren.length; i++) {
						AccessPointType targetChild = (AccessPointType) targetChildren[i];
						AccessPointType sourceChild = (AccessPointType) sourceChildren[i];
						selectedSourceField = sourceChild;
						sourceMapper = getMapperByType(sourceChild);
						targetMapper = getMapperByType(targetChild);
						performSingleDropMapping(sourceMapper, targetMapper, config);
					}
				}
			}
		}
		draggedText = null;
	}


    private boolean checkStandardMappingAllowed(AccessPointType target,	AccessPointType source) {
    	if (isPrimitive(source)) {
			if (isPrimitive(target)) {
	            if (isEqualOrSimilar(source, target, true)) {
	                return true;
	            }
			}
			if (isRoot(target)) {
				return false;
			}
			if (isList(target)) {
				return false;
			}
			return true;
		}
		if (isPrimitive(target)) {
			if (isPrimitive(source)) {
				return true;
			}
			if (isRoot(source)) {
				return false;
			}
			if (isList(source)) {
				return false;
			}
			return true;
		}
		if (!isRoot(source) && !isRoot(target)) {
			if (isEqualOrSimilar(source, target, true)) {
				return true;
			}
			return false;
		}
		if (isEqualOrSimilar(source, target, true) && isList(target)) {
			return true;
		}

		return false;
	}

	private IMappingRenderer getMapperByType(AccessPointType messageType) {
		if (this.isSerializable(messageType)) {
			return new SerializableMappingRenderer(this, messageType);
		}
		if (this.isStructData(messageType)) {
			return new StructDataMappingRenderer(this, messageType);
		}
		if (this.isPrimitive(messageType)) {
			return new PrimitiveMappingRenderer(this, messageType);
		}
		return null;
	}

	public boolean isPrimitive(AccessPointType apt) {
       if (apt != null && (!(apt instanceof StructAccessPointType)) && apt.getType() != null && apt.getType().getId() != null && apt.getType().getId().startsWith("prim")) { //$NON-NLS-1$
          return true;
       }
       if (apt != null && ((apt instanceof StructAccessPointType)) && apt.getType() != null && apt.getType().getId() != null && apt.getType().getId().startsWith("prim")) { //$NON-NLS-1$
          return true;
       }
       return false;
    }

    public boolean isSerializable(AccessPointType apt) {
       if (apt.getType() != null) {
    	   return (apt.getType().getId().equalsIgnoreCase("serializable")); //$NON-NLS-1$
       }
       return false;
    }

    public boolean isStructData(AccessPointType apt) {
        if (apt != null && apt instanceof StructAccessPointType) {
        	return true;
        }
        return false;
     }


   private void performSingleDropMapping(IMappingRenderer sm, IMappingRenderer tm, MappingConfiguration config) {
		usedVar.clear();
	    if (sourceAPB.hasChildren(sm.getType()) && targetAPB.hasChildren(tm.getType())) {
	       if (isList(tm.getType()) && isList(sm.getType())) {
	    	    String mappingCode = sm.renderListMappingCode(sm, tm, "", 0, config); //$NON-NLS-1$
	    	    FieldMapping fm = fieldMappings.get(getXPathFor(tm.getType()));
				fm.setMappingExpression(mappingCode);
				fm.setAdvancedMapping(true);
				validateMapping(fm, true);
			} else {
				mapComplexToComplexMessage(sm, tm, config);
			}
		} else {
			if (sourceAPB.hasChildren(selectedSourceField)) {
				mapComplextToPrimitiveMessage(selectedSourceField, tm.getType());
			} else {
				mapPrimitiveToPrimitiveMessage(sm, tm, config);
			}
		}
	}

	public boolean isList(AccessPointType messageType)
   {
      if (messageType instanceof StructAccessPointType) {
         return ((StructAccessPointType)messageType).getXPath().isList();
      } else {
    	 return isJavaArray(messageType) || isJavaList(messageType);
      }
   }

	public boolean isJavaList(AccessPointType messageType)
	{
	  if (isSerializable(messageType)) {
	  	  String className = AttributeUtil.getAttributeValue(messageType, PredefinedConstants.CLASS_NAME_ATT);
	  	  if (className.startsWith("java.util.List")) { //$NON-NLS-1$
	 		  return true;
	 	  }
	  }
	  return false;
	}

	public boolean isJavaArray(AccessPointType messageType)
	{
	  if (isSerializable(messageType)) {
	  	  String className = AttributeUtil.getAttributeValue(messageType, PredefinedConstants.CLASS_NAME_ATT);
	  	  if (className.endsWith("[]")) { //$NON-NLS-1$
	 		  return true;
	 	  }
	  }
	  return false;
	}

   private void performMultipleDropMapping(AccessPointType mapTarget, MappingConfiguration config) {
	    IMappingRenderer targetRenderer = getMapperByType(mapTarget);
		String mappingCode = createFieldAddition(targetRenderer, selectedSourceFields);
		FieldMapping fm = fieldMappings.get(getXPathFor(mapTarget));
		mappingCode = mappingCode.replaceAll("@", "");	 //$NON-NLS-1$ //$NON-NLS-2$
	    fm.setAdvancedMapping(targetRenderer instanceof SerializableMappingRenderer);
 		fm.setMappingExpression(mappingCode);
		validateMapping(fm, true);
	}

    private void mapPrimitiveToPrimitiveMessage(IMappingRenderer sm, IMappingRenderer tm, MappingConfiguration config) {
       usedVar.clear();
       String mappingCode = ""; //$NON-NLS-1$
       FieldMapping fm = fieldMappings.get(getXPathFor(tm.getType()));

       if (!isList(sm.getType()) && isList(tm.getType())) {
          mappingCode = sm.renderAdditionCode(sm, tm, config);
          fm = fieldMappings.get(getXPathFor(tm.getType()));
          fm.setMappingExpression(fm.getMappingExpression() + "\n" + mappingCode); //$NON-NLS-1$
          fm.setAdvancedMapping(config != null && config.isOverwrite() || config.isAppend());
          validateMapping(fm, true);
      } else {
         if (isList(tm.getType())) {
            mappingCode =  sm.renderListMappingCode(sm, tm, "", 0, config);                         //$NON-NLS-1$
            fm.setAdvancedMapping(true);
         } else {
           String getterCode = sm.renderGetterCode(false, false, config);
           mappingCode = tm.renderSetterCode(getterCode, false, false, config);
           fm.setAdvancedMapping(tm instanceof SerializableMappingRenderer);
           if (tm instanceof StructDataMappingRenderer) {
               fm.setAdvancedMapping(arraySelectionDepthTarget > 0);
           }
         }
         mappingCode = mappingCode.replaceAll("@", ""); //$NON-NLS-1$ //$NON-NLS-2$
         fm.setMappingExpression(mappingCode);
         validateMapping(fm, true);
      }



  }

	private void mapComplextToPrimitiveMessage(
			AccessPointType sourceMessage,
			AccessPointType targetMessage) {
		String mappingCode = createFieldAddition(sourceMessage);
		FieldMapping fm = fieldMappings.get(getXPathFor(targetMessage));
		mappingCode = mappingCode.replaceAll("@", ""); //$NON-NLS-1$ //$NON-NLS-2$
		fm.setMappingExpression(mappingCode);
		validateMapping(fm, true);
	}

	private void mapComplexToComplexMessage(IMappingRenderer sourceMapper, IMappingRenderer targetMapper, MappingConfiguration config) {
		if (isEqualOrSimilar(sourceMapper.getType(), targetMapper.getType(), true)) {
			if (!isList(sourceMapper.getType()) && isList(targetMapper.getType())) {
				String mappingCode = sourceMapper.renderAdditionCode(sourceMapper, targetMapper, config);
				FieldMapping fm = fieldMappings.get(getXPathFor(targetMapper.getType()));
				fm.setMappingExpression(fm.getMappingExpression() + "\n" + mappingCode); //$NON-NLS-1$
				fm.setAdvancedMapping(config != null && config.isOverwrite() || config.isAppend());
				validateMapping(fm, true);
			}
		}
		if (isRoot(targetMapper.getType())) {
			targetMapper = getMapperByType((AccessPointType) targetAPB.getChildren(targetMapper.getType())[0]);
		}
		if (isRoot(sourceMapper.getType())) {
			sourceMapper = getMapperByType((AccessPointType) sourceAPB.getChildren(sourceMapper.getType())[0]);
		}
		Object[] sourceChildren = sourceAPB.getChildren(sourceMapper.getType());
		Object[] targetChildren = targetAPB.getChildren(targetMapper.getType());
		for (int i = 0; i < targetChildren.length; i++) {
			AccessPointType targetChild = (AccessPointType) targetChildren[i];
			for (int j = 0; j < sourceChildren.length; j++) {
				AccessPointType sourceChild = (AccessPointType) sourceChildren[j];
				if (isList(sourceChild) && isList(targetChild)) {
					String mappingCode = sourceMapper.renderListMappingCode(getMapperByType(sourceChild), getMapperByType(targetChild), "", 0, config); //$NON-NLS-1$
					FieldMapping fm = fieldMappings.get(getXPathFor(targetChild));
					fm.setMappingExpression(mappingCode);
					validateMapping(fm, true);
					mappingCode = mappingCode.replaceAll("@", ""); //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					if (sourceAPB.hasChildren(sourceChild)) {
						mapComplexToComplexMessage(getMapperByType(sourceChild), getMapperByType(targetChild), config);
					} else {
						if (isEqualOrSimilar(targetChild, sourceChild, false)) {
							mapPrimitiveToPrimitiveMessage(getMapperByType(sourceChild), getMapperByType(targetChild), config);
						}
					}
				}
			}
		}
	}


	public boolean isComplexType(AccessPointType messageType)
   {
	  if (messageType instanceof StructAccessPointType) {
	     return (((StructAccessPointType)messageType).getXPath().getType() == BigData.NULL);
	  }	else {
		  if (isSerializable(messageType)) {
				MultipleAccessPathBrowserContentProvider apcp = null;
			    if (isSourceField(messageType)) {
			    	apcp = getSourceAPB();
			    } else {
			    	apcp = getTargetAPB();
			    }
			    if (apcp.getChildren(messageType).length > 0) {
			    	return true;
			    }
		  }
	  }
      return false;
   }

 	public boolean isEqualOrSimilar(AccessPointType sourceMessage, AccessPointType targetMessage, boolean advanced) {
 		IMappingRenderer sr = getMapperByType(sourceMessage);
 		IMappingRenderer tr = getMapperByType(targetMessage);
 		if (advanced) {
			//Refactor this. Find a suitable way to compare primtives with Serializables and Struct types
 			if (sr instanceof PrimitiveMappingRenderer || tr instanceof PrimitiveMappingRenderer) {
 			    if (sr.getTypeString().equals(tr.getTypeString())) {
 			       return true;
 			    }
 			}
 			if (sr.getTypeString().equalsIgnoreCase(tr.getTypeString()) || isSpecialCase(sr, tr)) {
 				Object[] sourceChildren = sourceAPB.getChildren(sourceMessage, true);
 				Object[] targetChildren = targetAPB.getChildren(targetMessage, true);
 				if (sourceChildren.length != targetChildren.length) {
 					return false;
 				}
 				for (int i = 0; i < sourceChildren.length; i++) {
 					AccessPointType sourceChild = (AccessPointType) sourceChildren[i];
 					AccessPointType targetChild = (AccessPointType) targetChildren[i];
 					if (!isEqualOrSimilar(sourceChild, targetChild, true)) {
 						return false;
 					}
 				}
 				return true;
 			}
 		} else {
 			String targetName = targetMessage.getId();
 			String sourceName = sourceMessage.getId();
 			if (targetName.equalsIgnoreCase(sourceName)) {
 				return true;
 			}
 			return false;
 		}
 		return false;
	}

 	private boolean isSpecialCase(IMappingRenderer sr, IMappingRenderer tr) {
		//This is a workaround for the case of dealing with Serializable Arrays which are not exposed with fq-Names
 		if ((sr instanceof SerializableMappingRenderer) && (tr instanceof SerializableMappingRenderer)) {
 			if (tr.getTypeString().endsWith("[]")) { //$NON-NLS-1$
 				String shortTr = tr.getTypeString().replace("[]", ""); //$NON-NLS-1$ //$NON-NLS-2$
 				return (sr.getTypeString().endsWith(shortTr));
 			}
 		}
 	 	return false;
	}

	public boolean isIndexingRequired(AccessPointType st, AccessPointType tt) {
 		if (this.isList(st) && (this.isList(tt))) {
 			return false;
 		}
 		int stSelectionDepth = getArraySelectionDepth(st);
 		int ttSelectionDepth = getArraySelectionDepth(tt);;
 		if (stSelectionDepth > 0 || ttSelectionDepth > 0) {
 			return true;
 		}
 		return false;
 	}

	private String createFieldAddition(AccessPointType messageType) {
		String result = ""; //$NON-NLS-1$
		if (sourceAPB.hasChildren(messageType)) {
			Object[] children = sourceAPB.getChildren(messageType);
			for (int j = 0; j < children.length; j++) {
				StructAccessPointType childType = (StructAccessPointType) children[j];
				result = result + createFieldAddition(childType) + " + "; //$NON-NLS-1$
			}
		} else {
			result = getXPathFor(messageType);
		}
		result = result.replaceAll("/", "."); //$NON-NLS-1$ //$NON-NLS-2$
		if (result.endsWith("+ ")) { //$NON-NLS-1$
			result = result.substring(0, result.length() - 2);
		}
		return result.trim();
	}

	private String createFieldAddition(IMappingRenderer targetRenderer, List<AccessPointType> messageTypes) {
		String result = ""; //$NON-NLS-1$
		for (Iterator<AccessPointType> i = messageTypes.iterator(); i.hasNext();) {
			AccessPointType messageType = (AccessPointType) i.next();
			IMappingRenderer renderer = getMapperByType(messageType);
			result = result + renderer.renderGetterCode(false, false, null) + " + ";			 //$NON-NLS-1$
		}
		result = targetRenderer.renderSetterCode(result, false, false, null);
		if (result.endsWith(" + ")) { //$NON-NLS-1$
			result = result.substring(0, result.length() - 3);
		}
		if (result.endsWith(" + )")) { //$NON-NLS-1$
			result = result.substring(0, result.length() - 4) + ")"; //$NON-NLS-1$
		}
		return result.trim();
	}


	public AccessPointType getMessageTypeByName(String name) {
		for (Iterator<AccessPointType> i = sourceMessageTypes.iterator(); i.hasNext();) {
			AccessPointType apt = i.next();
			if (apt.getName().equalsIgnoreCase(name)) {
				return apt;
			}
		}
		for (Iterator<AccessPointType> i = targetMessageTypes.iterator(); i.hasNext();) {
			AccessPointType apt = i.next();
			if (apt.getName().equalsIgnoreCase(name)) {
				return apt;
			}
		}
		return null;
	}


	public boolean isRoot(AccessPointType messageType) {
	    if (messageType instanceof StructAccessPointType) {
	        StructAccessPointType sapt = (StructAccessPointType)messageType;
	        return sapt != null
            && "".equals(sapt.getXPath().getXPath());	        //$NON-NLS-1$
	    }
	    if (isPrimitive(messageType)) {
	       return true;
	    }
	    if (isSerializable(messageType)) {
	    	String rootElement = AttributeUtil.getAttributeValue(messageType, "RootElement"); //$NON-NLS-1$
	    	return rootElement.equalsIgnoreCase(messageType.getName());
	    }
	    return false;
	}

	public boolean containsSelectedSourceField(Object targetElement) {
		if (selectedSourceField != null) {
			IMappingRenderer sourceRenderer = getMapperByType(selectedSourceField);
			FieldMapping sm = fieldMappings
					.get(getXPathFor(selectedSourceField));

			if (sm != null) {
				String javaPath = getXPathFor(selectedSourceField);
				if (sourceRenderer instanceof SerializableMappingRenderer) {
					javaPath = sourceRenderer.renderGetterCode(true, true, null);
				}
				if (isPrimitive(selectedSourceField)) {
	               javaPath = javaPath.replace("/", "");		    //$NON-NLS-1$ //$NON-NLS-2$
				} else {
				   javaPath = javaPath.replace('/', '.');
				}
				AccessPointType keyElement = (AccessPointType) targetElement;
				Object key = getXPathFor(keyElement);
				FieldMapping tm = fieldMappings.get(key);
				if (tm != null && javaPath != null) {
					String targetMessage = tm.getMappingExpression();
					if (StringUtils.isEmpty(targetMessage)) {
						return false;
					}
					javaPath = javaPath.replaceAll("\n", " "); //$NON-NLS-1$ //$NON-NLS-2$
					targetMessage = targetMessage.replaceAll("\n", " "); //$NON-NLS-1$ //$NON-NLS-2$
					if (sourceRenderer instanceof SerializableMappingRenderer) {
						if (targetMessage != null && targetMessage.indexOf(javaPath) > -1) {
							return true;
						}
					}
					String regex = ".*\\b" + javaPath + "\\b.*"; //$NON-NLS-1$ //$NON-NLS-2$
					if (targetMessage != null && targetMessage.matches(regex)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isContainedInSelectedTargetField(Object sourceElement) {
		AccessPointType sourceMessageType = (AccessPointType) sourceElement;
		IMappingRenderer sourceRenderer = getMapperByType(sourceMessageType);
		String javaPath = getXPathFor(sourceMessageType);
		if (javaPath != null) {
		   if (sourceRenderer instanceof SerializableMappingRenderer) {
			 javaPath = sourceRenderer.renderGetterCode(true, true, null);
		   }
           if (isPrimitive(sourceMessageType)) {
              javaPath = javaPath.replace("/", ""); //$NON-NLS-1$ //$NON-NLS-2$
           } else {
              javaPath = javaPath.replace('/', '.');
           }
			if (selectedTargetField != null) {
				FieldMapping tm = fieldMappings.get(getXPathFor(selectedTargetField));
				if (tm != null) {
					String targetMessage = tm.getMappingExpression();
					if (StringUtils.isEmpty(targetMessage)) {
						return false;
					}
					javaPath = javaPath.replaceAll("\n", " "); //$NON-NLS-1$ //$NON-NLS-2$
					targetMessage = targetMessage.replaceAll("\n", " ");					 //$NON-NLS-1$ //$NON-NLS-2$
					if (sourceRenderer instanceof SerializableMappingRenderer) {
						if (targetMessage != null && targetMessage.indexOf(javaPath) > -1) {
							return true;
						}
					}
					String regex = ".*\\b" + javaPath + "\\b.*"; //$NON-NLS-1$ //$NON-NLS-2$
					if (targetMessage != null && targetMessage.matches(regex)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void addSourceMessageType(AccessPointType declaringType,
			String messageName) {

	    AccessPointType accessPoint = null;
	    if (declaringType instanceof StructAccessPointType) {
	        accessPoint = mtaUtils.createStructAccessPoint((EObject) declaringType, messageName,
	              DirectionType.IN_LITERAL, true);
	    } else {
	       if (declaringType.getType() == null || declaringType.getType().getId().equalsIgnoreCase("serializable")) { //$NON-NLS-1$
	    	  //String className = AttributeUtil.getAttributeValue(declaringType, PredefinedConstants.CLASS_NAME_ATT);
	    	  //accessPoint = mtaUtils.createSerializableAccessPoint(declaringType, messageName, className, DirectionType.IN_LITERAL);
	    	  accessPoint = declaringType;
	    	  accessPoint.setName(messageName);
	    	  accessPoint.setId(messageName);
	    	  DataTypeType serializableType = ModelUtils.getDataType(application, "serializable"); //$NON-NLS-1$
	    	  accessPoint.setType(serializableType);
	    	  accessPoint.setDirection(DirectionType.IN_LITERAL);
	       } else {
	          accessPoint = mtaUtils.createPrimitiveAccessPoint(declaringType, messageName, DirectionType.IN_LITERAL);;
	       }
	    }
	    //application.eAdapters().clear();
	    getAccessPoint(application).add(accessPoint);
		sourceMessageTypes.add(accessPoint);
		refreshJavaScriptContext();
	}

	public AccessPointType createExternalClass(AccessPointType declaringType,
			String messageName, boolean addToContext) {
	    AccessPointType accessPoint = null;
       if (declaringType.getType() == null || declaringType.getType().getId().equalsIgnoreCase("serializable")) { //$NON-NLS-1$
    	   accessPoint = declaringType;
	       accessPoint.setName(messageName);
	       accessPoint.setId(messageName);
	       DataTypeType serializableType = ModelUtils.getDataType(application, "serializable"); //$NON-NLS-1$
	       accessPoint.setType(serializableType);
	       accessPoint.setDirection(DirectionType.IN_LITERAL);
	    }
        if (addToContext) {
    		externalClassTypes.add(accessPoint);
    		refreshJavaScriptContext();
        }
		return accessPoint;
	}

	public void addTargetMessageType(AccessPointType declaringType,
			String messageName) {

	    AccessPointType accessPoint = null;
	    if (declaringType instanceof StructAccessPointType) {
	        accessPoint = mtaUtils.createStructAccessPoint((EObject) declaringType, messageName,
	              DirectionType.OUT_LITERAL, true);
	    } else {
	       if (declaringType.getType() == null || declaringType.getType().getId().equalsIgnoreCase("serializable")) { //$NON-NLS-1$
	          //accessPoint = mtaUtils.createSerializableAccessPoint(declaringType, messageName, DirectionType.IN_LITERAL);
	    	  accessPoint = declaringType;
	    	  accessPoint.setName(messageName);
	    	  accessPoint.setId(messageName);
	    	  DataTypeType serializableType = ModelUtils.getDataType(application, "serializable"); //$NON-NLS-1$
	    	  accessPoint.setType(serializableType);
	    	  accessPoint.setDirection(DirectionType.OUT_LITERAL);
	       } else {
	          accessPoint = mtaUtils.createPrimitiveAccessPoint(declaringType, messageName, DirectionType.OUT_LITERAL);
	       }
	    }
		getAccessPoint(application).add(accessPoint);
		targetMessageTypes.add(accessPoint);
		refreshJavaScriptContext();
	}



	public List<AccessPointType> getAccessPoint(IModelElement apOwner) {
	   if (apOwner instanceof ApplicationType) {
	      return ((ApplicationType)apOwner).getAccessPoint();
	   }
       if (apOwner instanceof ContextType) {
          return ((ContextType)apOwner).getAccessPoint();
       }
       return new ArrayList<AccessPointType>();
	}

	public List<AccessPointType> getSourceMessageTypes() {
		return sourceMessageTypes;
	}

	public List<AccessPointType> getExternalClassTypes() {
		return externalClassTypes;
	}

	public List<AccessPointType> getExternalClassTypesMissing() {
		return externalClassTypesMissing;
	}

	public List<AccessPointType> getTargetMessageTypes() {
		return targetMessageTypes;
	}

	public boolean isDeleteSourceMessageAvailable() {
		return !sourceMessageTypes.isEmpty() && isRoot(selectedSourceField);
	}

	public boolean isDeleteTargetMessageAvailable() {
		return !targetMessageTypes.isEmpty() && isRoot(selectedTargetField);
	}

	public void performSourceMessageRemovement() {
		List<AccessPointType> newList = new ArrayList<AccessPointType>();
		for (Iterator<AccessPointType> i = selectedSourceFields.iterator(); i.hasNext();) {
		   AccessPointType selectedType = i.next();
		   removeMappingsFor(selectedType);
		}
        for (Iterator<AccessPointType> j = sourceMessageTypes.iterator(); j.hasNext();) {
           AccessPointType messageType = j.next();
           boolean found = false;
           for (Iterator<AccessPointType> i = selectedSourceFields.iterator(); i.hasNext();) {
              AccessPointType selectedType = i.next();
              if (messageType.getId() == selectedType.getId()) {
                 found = true;
              }
           }
           if (!found) {
              newList.add(messageType);
           }
       }
       sourceMessageTypes = newList;
	   for (Iterator<AccessPointType> i = selectedSourceFields.iterator(); i.hasNext();) {
	       remove(i.next());
	   }
	   if (project != null) {
		   initilizeValidator(project);
	   }
	   errorCache.clear();
	   refreshJavaScriptContext();
	}

	public void performTargetMessageRemovement() {
		removeMappingsFor(selectedTargetField);
		List<AccessPointType> newList = new ArrayList<AccessPointType>();
		for (Iterator<AccessPointType> i = targetMessageTypes.iterator(); i.hasNext();) {
			AccessPointType messageType = i.next();
			if (messageType.getId() != selectedTargetField.getId()) {
				newList.add(messageType);
			}
		}
		targetMessageTypes = newList;
		getAccessPoint(application).remove(selectedTargetField);
		remove(selectedTargetField);
		if (project != null) {
			initilizeValidator(project);
		}
		errorCache.clear();
		refreshJavaScriptContext();
	}

	private void remove(AccessPointType messageType) {
		int removeIndex = -1;
		for (Iterator<AccessPointType> i = getAccessPoint(application).iterator(); i
				.hasNext();) {
			AccessPointType type = i.next();
			if (type.getId().equalsIgnoreCase(messageType.getId())) {
				removeIndex = getAccessPoint(application).indexOf(type);
			}
		}
		if (removeIndex > -1) {
		    try {
	            getAccessPoint(application).remove(removeIndex);
		    } catch (Throwable t) {
		       //Ignore this
		    }
		}
	}

	public void removeAccessPoints(List<AccessPointType> messageTypes) {
	   for (Iterator<AccessPointType> i = messageTypes.iterator(); i.hasNext();) {
	      AccessPointType type = i.next();
	      remove(type);
	   }
	}

	public void performMappingExpressionRemovement() {
		FieldMapping fm = fieldMappings
				.get(getXPathFor(selectedTargetField));
		if (fm != null) {
			fm.setMappingExpression(""); //$NON-NLS-1$
			expressionDocument = "//Expressions\n"; //$NON-NLS-1$
		}
		removeMappingsFor(selectedTargetField);
	}

	public void performMappingStatementsRemovement() {
		FieldMapping fm = fieldMappings
				.get(getXPathFor(selectedTargetField));
		if (fm != null) {
			//fm.setMappingStatements("");
			statementsDocument = "//Statements\n"; //$NON-NLS-1$
		}
	}

	private void removeMappingsFor(AccessPointType messageType) {
		List<String> removeKeys = new ArrayList<String>();
		for (Iterator<String> i = fieldMappings.keySet().iterator(); i.hasNext();) {
			String key = i.next();
			if (key.startsWith(messageType.getId() + "/")) { //$NON-NLS-1$
				FieldMapping fm = fieldMappings.get(key);
				if (fm != null) {
					String expression = fm.getMappingExpression();
					fm.setMappingExpression(""); //$NON-NLS-1$
					if (expression != null && !StringUtils.isEmpty("")) { //$NON-NLS-1$
						removeKeys.add(key);
					}
				}
			}
		}
		for (Iterator<String> i = removeKeys.iterator(); i.hasNext();) {
			String key = i.next().toString();
			fieldMappings.remove(key);
		}

	}

	public boolean isClearMappingExpressionAvailable() {
		if (selectedTargetField == null) {
			return false;
		}
		FieldMapping fm = fieldMappings
				.get(getXPathFor(selectedTargetField));
		if (fm == null) {
			return false;
		}
		return (fm.getMappingExpression() != null && !fm.getMappingExpression()
				.equals("")); //$NON-NLS-1$
	}

	public boolean isClearMappingStatementAvailable() {
		if (selectedTargetField == null) {
			return false;
		}
		FieldMapping fm = fieldMappings
				.get(getXPathFor(selectedTargetField));
		if (fm == null) {
			return false;
		}
		return false;
	}

	public boolean isToggleBreakpointAvailable() {
		return (!simpleMode && selectedTargetField != null && !isRoot(selectedTargetField));
	}

	public boolean validateMapping(FieldMapping fieldMapping, boolean updateCache) {
		if (!externalReference) {
	        javaScriptValidator = new JavaScriptValidator((IJavaScriptProject) javaProject);
	        if (javaScriptValidator != null && fieldMapping != null && !StringUtils.isEmpty(fieldMapping.getMappingExpression())) {
	            IProblem[] problems = null;
	            String fieldPath = fieldMapping.getFieldPath();
	            if (errorCache.get(fieldPath) == null || updateCache) {
	                String sourceCode = jScriptProlog + "\n" +  fieldMapping.getMappingExpression() + ";"; //$NON-NLS-1$ //$NON-NLS-2$
	                problems = javaScriptValidator.validate(sourceCode);
	                errorCache.put(fieldPath, problems);
	            }
	            problems = errorCache.get(fieldPath);
	            if (problems != null) {
	                for (int i = 0; i < problems.length; i++) {
	                    IProblem problem = problems[i];
	                    if (problem.isError()) {
	                        return false;
	                    }
	                }
	            }
	        }
		}
		return true;
	}

	public String getToolTip(TreeItem item, int columnIndex) {
		if (columnIndex == 3 && (item.getData() instanceof StructAccessPointType)) {
			StructAccessPointType messageType = (StructAccessPointType) item
					.getData();
			String xPath = getXPathFor(messageType);
			if (messageType.getXPath() != null) {
				FieldMapping fm = fieldMappings.get(xPath);
				if (fm != null) {
					String message = ""; //$NON-NLS-1$
					IProblem[] problems = errorCache.get(fm.getFieldPath());
					if (problems != null) {
						for (int i = 0; i < problems.length; i++) {
							IProblem problem = problems[i];
							message = message + Modeling_Messages.MSG_ERROR
									+ problem.getMessage() + "\n"; //$NON-NLS-1$
						}
						return message;
					}
				}
			}
		}
		return null;
	}

	public boolean hasMappingExpression(Object element) {
		AccessPointType messageType = (AccessPointType) element;
		FieldMapping fieldMapping = fieldMappings
				.get(getXPathFor(messageType));
		return (fieldMapping != null
				&& fieldMapping.getMappingExpression() != null && !fieldMapping
				.getMappingExpression().equalsIgnoreCase("")); //$NON-NLS-1$
	}

	public boolean hasMappingStatement(Object element) {
		AccessPointType messageType = (AccessPointType) element;
		FieldMapping fieldMapping = fieldMappings
				.get(getXPathFor(messageType));
		return (fieldMapping != null
				&&  !fieldMapping
				.getMappingExpression().equalsIgnoreCase("")); //$NON-NLS-1$
	}

	public String getXPathFor(AccessPointType messageType) {
		if (messageType == null) {
			return null;
		}
		return AttributeUtil.getAttributeValue(messageType, "FullXPath"); //$NON-NLS-1$
	}

	public String getRootFor(AccessPointType messageType) {
		if (messageType == null) {
			return null;
		}
		return AttributeUtil.getAttributeValue(messageType, "RootElement"); //$NON-NLS-1$
	}

	public AccessPathBrowserContentProvider getApc() {
		return apc;
	}

	public boolean isSourceField(AccessPointType messageType) {
		String xPath = getXPathFor(messageType);
		if (xPath == null) {
			return false;
		}
		for (Iterator<AccessPointType> i = sourceMessageTypes.iterator(); i.hasNext();) {
			AccessPointType sourceType = i.next();
			if (xPath.startsWith(sourceType.getId() + "/")) { //$NON-NLS-1$
				return true;
			}
		}
		return false;
	}

	public void createXPathsForTree(TreeSelection treeSelection) {

		for (int i = 0; i < treeSelection.getPaths().length; i++) {
			TreePath treePath = treeSelection.getPaths()[i];
			StructAccessPointType messageType = (StructAccessPointType) treePath
					.getSegment(treePath.getSegmentCount() - 1);
			String xPath = renderXPathString(treePath).toLowerCase();
			xPathMap.put(messageType, xPath);
		}

	}

	public Map<StructAccessPointType, String> getXPathMap() {
		return xPathMap;
	}

	public ModelType getModelType() {
		return modelType;
	}

	public MultipleAccessPathBrowserContentProvider getSourceAPB() {
		return sourceAPB;
	}

	public void setSourceAPB(MultipleAccessPathBrowserContentProvider sourceAPP) {
		this.sourceAPB = sourceAPP;
	}

	public MultipleAccessPathBrowserContentProvider getTargetAPB() {
		return targetAPB;
	}

	public void setTargetAPB(MultipleAccessPathBrowserContentProvider targetAPB) {
		this.targetAPB = targetAPB;
	}

	public String getTypeString(AccessPointType messageType) {
		IMappingRenderer renderer = getMapperByType(messageType);
	    return renderer.getTypeString();
	}

	public void ignoreUpcomingAnnotationChanges() {
		ignoreAnnotationChanges = true;
	}

	public String getJScriptProlog() {
		return jScriptProlog;
	}

	public void initilizeValidator(IProject project) {
		try {
			this.project = project;
			if (project.hasNature("org.eclipse.wst.jsdt.core.jsNature")) { //$NON-NLS-1$
				javaProject = JavaScriptCore.create(project);
				javaScriptValidator = new JavaScriptValidator((IJavaScriptProject) javaProject);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

   public boolean isEditable()
   {
      return !isRoot(selectedTargetField) || isPrimitive(selectedTargetField);

   }

   public boolean isSimpleMode()
   {
      return simpleMode;
   }

   public void setSimpleMode(boolean simpleMode)
   {
      this.simpleMode = simpleMode;
   }

   public boolean isWithSerializable()
   {
	   return withSerialiable;
   }

   public void setWithSerializable(boolean withSerializable)
   {
	   this.withSerialiable = withSerializable;
   }

   public void setExternalReference(boolean externalReference)
   {
      this.externalReference = externalReference;
   }

   public boolean isExternalReference()
   {
      return externalReference;
   }

   public Map getFieldPathsToAccessPoints() {
	   return fieldPathsToAccessPoints;
   }

   public boolean isContentMapping(AccessPointType accessPoint) {
	   if (accessPoint instanceof StructAccessPointType) {
	       //rainer:Fix for CRNT-17926
		   Object[] children = targetAPB.getChildrenWithoutSetup(accessPoint, false);
		   for (int i = 0; i < children.length; i++) {
			   AccessPointType child = (AccessPointType)children[i];
			   if (child.getId().indexOf("@") > -1) { //$NON-NLS-1$
				   return true;
			   }
		   }
	   }
	   return false;
   }

   public int getArraySelectionDepth(AccessPointType messageType) {
	   IMappingRenderer renderer = getMapperByType(messageType);
	   String javaPath = renderer.renderGetterCode(false, false, null);
	   String[] segments = javaPath.split("\\."); //$NON-NLS-1$
	   int depth = 0;
	   if (renderer instanceof StructDataMappingRenderer) {
		   for (int i = 0; i < segments.length; i++) {
			   String segment = segments[i];
			   if (segment.indexOf("[0]") > -1) { //$NON-NLS-1$
				   depth = depth + 1;
			   }
		   }
	   }
	   if (renderer instanceof SerializableMappingRenderer) {
		   for (int i = 0; i < segments.length; i++) {
			   String segment = segments[i];
			   if ((segment.indexOf("(0)") > -1) || (segment.indexOf("[0]") > -1)) { //$NON-NLS-1$ //$NON-NLS-2$
				   depth = depth + 1;
			   }
		   }
	   }
	   return depth;
   }



}
