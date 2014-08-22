package org.eclipse.stardust.modeling.integration.camel.triggerTypes;

import static org.eclipse.stardust.engine.api.model.PredefinedConstants.*;
import java.util.*;
import java.util.List;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.SelectionDialog;

import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.DereferencePathBrowserComposite;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.ui.StringUtils;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.integration.camel.Camel_Messages;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;

public class OutAccessPointDetailsPage extends AbstractModelElementPropertyPage
{
   private final static String EMPTY_String = "";

   private final static String PRIMITIVE = "primitive";

   private final static String STRUCT = "struct";

   private final static String SERIALIZABLE = "serializable";

   private final static String DMS_DOCUMENT = "dmsDocument";

   private final static String STRUCTURED_EA_KEY = "carnot:engine:dataType";

   /**
    * struct dmsFolderList dmsFolder dmsDocumentList dmsDocument plainXML entity
    * serializable primitive
    */
   private static Map<String, String> dataTypes = new LinkedHashMap<String, String>();
   {
      dataTypes.put(PRIMITIVE, "Primitive");
      dataTypes.put(STRUCT, "Structured Data");
      dataTypes.put(DMS_DOCUMENT, "Document");
      dataTypes.put(SERIALIZABLE, "Serializable Data");
   }

   private static Map<String, Object> primitives = new LinkedHashMap<String, Object>();
   {
      primitives.put("Calendar", "Calendar");
      primitives.put("String", "String");
      primitives.put("Timestamp", "Timestamp");
      primitives.put("boolean", "Boolean");
      primitives.put("byte", "Byte");
      primitives.put("char", "Char");
      primitives.put("double", "Double");
      primitives.put("float", "Float");
      primitives.put("int", "Int");
      primitives.put("long", "Long");
      primitives.put("short", "Short");
   }

   private AccessPointType accessPointType;

   private LabeledText nameText;

   private LabeledText idText;

   private ComboViewer dataTypeViewer;

   private Label dataStructureLabel;

   private ComboViewer dataStructureViewer;

   private Label dataStructureSeparator;

   private LabeledText classNameText;

   private Button browseButton;

   private Label dataLabel;

   private ComboViewer dataViewer;

   private TypeInfo type;

   private int style = IJavaElementSearchConstants.CONSIDER_ALL_TYPES;

   private DereferencePathBrowserComposite[] browser;

   private TypeInfo filter;

   private TypeFinder finder;

   private ModelType model;

   private ModifyListener idSyncListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         if (GenericUtils.getAutoIdValue())
         {
            String computedId = NameIdUtils.createIdFromName(null, getModelElement());
            idText.getText().setText(computedId);
         }
      }
   };

   private ParameterMappingType getParameterMapping(AccessPointType accessPoint,
         TriggerType trigger)
   {
      for (ParameterMappingType parameterMapping : trigger.getParameterMapping())
      {
         if (parameterMapping.getParameter().equalsIgnoreCase(accessPoint.getId()))
            return parameterMapping;
      }
      return null;
   }

   /**
    * (fh) This method is invoked only once when the dialog is created. You can use
    * getAdapter() to get the adapter object or getModelElement() to get the model element
    * if it is required for body construction.
    */
   @Override
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      accessPointType = (AccessPointType) element;
      nameText.getText().removeModifyListener(idSyncListener);
      WidgetBindingManager binding = getWidgetBindingManager();
      binding.bind(idText, accessPointType,
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id());
      binding.bind(nameText, accessPointType,
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
      nameText.getText().addModifyListener(idSyncListener);
      bindParameterMapping();
      TriggerType trigger = (TriggerType) accessPointType.eContainer();
      if (accessPointType.getType() != null)
      {
         initializeViewForAccessPointType();
         dataTypeViewer.setSelection(new StructuredSelection(accessPointType.getType()
               .getId()));
         if (accessPointType.getType().getId().equalsIgnoreCase(SERIALIZABLE))
         {
            AttributeType sdtType = getTypeAttribute(accessPointType.getAttribute(),
                  CLASS_NAME_ATT);
            if (sdtType != null)
            {
               classNameText.getText().setText(sdtType.getValue());
            }
         }
         if (accessPointType.getType().getId().equalsIgnoreCase(STRUCT))
         {
            AttributeType sdtType = getTypeAttribute(accessPointType.getAttribute(),
                  STRUCTURED_EA_KEY);
            if (sdtType != null)
            {
               TypeDeclarationType filteredData = findTypeDeclarationType(
                     accessPointType, sdtType.getValue());
               dataStructureViewer.setSelection(new StructuredSelection(filteredData));
            }
         }
         else if (accessPointType.getType().getId().equalsIgnoreCase(DMS_DOCUMENT))
         {
            AttributeType sdtType = getTypeAttribute(accessPointType.getAttribute(),
                  STRUCTURED_EA_KEY);
            if (sdtType != null)
            {
               TypeDeclarationType filteredData = findTypeDeclarationType(
                     accessPointType, sdtType.getValue());
               dataStructureViewer.setSelection(new StructuredSelection(filteredData));
            }
         }
         else
         {// primitive
            AttributeType attribute = getTypeAttributeForPrimitive(accessPointType);
            if (attribute != null)
               dataStructureViewer.setSelection(new StructuredSelection(attribute
                     .getValue()));
         }
         ParameterMappingType parameterMapping = getParameterMapping(accessPointType,
               trigger);
         if (parameterMapping != null)
         {
            if (parameterMapping.getData() != null)
            {
               dataViewer
                     .setSelection(new StructuredSelection(parameterMapping.getData()));
            }
         }
         else
         {
            parameterMapping = CarnotWorkflowModelFactory.eINSTANCE
                  .createParameterMappingType();
            parameterMapping.setParameter(accessPointType.getId());
            ((TriggerType) accessPointType.eContainer()).getParameterMapping().add(
                  parameterMapping);
         }
      }
      else
      {// default view
         dataTypeViewer.setSelection(new StructuredSelection("primitive"));
      }
   }

   /**
    * (fh) This method is invoked to store values from the page controls into the model
    * element. It has the behavior of "commit" and is invoked either when the user presses
    * the "Apply" button or closes the dialog with "Ok".
    */
   @Override
   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   @Override
   public Control createBody(Composite parent)
   {
      final Composite composite = FormBuilder.createComposite(parent, 3);
      this.nameText = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_Name);
      FormBuilder.createLabel(composite, EMPTY_String);
      this.idText = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_ID);
      FormBuilder.createLabel(composite, EMPTY_String);
      boolean autoIdButtonValue = GenericUtils.getAutoIdValue();
      if (autoIdButtonValue)
      {
         idText.getText().setEditable(false);
      }
      FormBuilder.createLabel(composite, Camel_Messages.label_Data_Type);
      dataTypeViewer = new ComboViewer(FormBuilder.createCombo(composite));
      FormBuilder.createLabel(composite, EMPTY_String);
      dataTypeViewer.setContentProvider(ArrayContentProvider.getInstance());
      dataTypeViewer.setLabelProvider(new LabelProvider()
      {
         @Override
         public String getText(Object element)
         {
            return OutAccessPointDetailsPage.dataTypes.get(element);
         }
      });
      dataTypeViewer.setInput(dataTypes.keySet());
      dataTypeViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         /**
          * will change the type of the accesspoint
          */
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            if (selection.size() > 0)
            {
               TriggerType trigger = (TriggerType) getModelElement().eContainer();
               for (int i = 0; i < trigger.getAccessPoint().size(); i++)
               {
                  AccessPointType accessPoint = trigger.getAccessPoint().get(i);
                  if (accessPoint.getId().equalsIgnoreCase(accessPointType.getId()))
                  {
                     accessPoint.setType(ModelUtils.getDataType(
                           (TriggerType) getModelElement().eContainer(),
                           (String) selection.getFirstElement()));
                     accessPointType = accessPoint;
                  }
               }
               initializeViewForAccessPointType();
            }
         }
      });
      dataStructureLabel = FormBuilder.createLabel(composite,
            Camel_Messages.label_Data_Strucutre);
      dataStructureViewer = new ComboViewer(FormBuilder.createCombo(composite));
      dataStructureSeparator = FormBuilder.createLabel(composite, EMPTY_String);
      dataStructureViewer.setContentProvider(ArrayContentProvider.getInstance());
      dataStructureViewer.setLabelProvider(new LabelProvider()
      {
         @Override
         public String getText(Object element)
         {
            if (element instanceof TypeDeclarationType)
               return ((TypeDeclarationType) element).getName();
            else if (element instanceof String)
               return (String) element;
            return (String) OutAccessPointDetailsPage.primitives.get(element);
         }
      });
      dataStructureViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            if (selection.size() > 0)
            {
               AttributeType attribute = null;
               if (selection.getFirstElement() instanceof String)
               {// primitive
                  attribute = getTypeAttributeForPrimitive(accessPointType);
                  if (attribute == null)
                  {
                     attribute = CarnotWorkflowModelFactory.eINSTANCE
                           .createAttributeType();
                     attribute.setName(TYPE_ATT);
                     attribute.setValue((String) selection.getFirstElement());
                     attribute.setType("org.eclipse.stardust.engine.core.pojo.data.Type");
                  }
                  else
                  {
                     setTypeAttributeValueForPrimitive(accessPointType,
                           (String) selection.getFirstElement());
                  }
               }
               else if (selection.getFirstElement() instanceof TypeDeclarationType)// SDT
               {
                  attribute = getTypeAttributeForSDT(accessPointType);
                  if (attribute == null)
                  {
                     attribute = CarnotWorkflowModelFactory.eINSTANCE
                           .createAttributeType();
                     attribute.setName(STRUCTURED_EA_KEY);
                     attribute.setValue(((TypeDeclarationType) selection
                           .getFirstElement()).getId());
                  }
                  else
                  {
                     setTypeAttributeValueForSDT(accessPointType,
                           ((TypeDeclarationType) selection.getFirstElement()).getId());
                  }
               }
               accessPointType.getAttribute().add(attribute);
            }
         }
      });
      classNameText = FormBuilder.createLabeledText(composite,
            Camel_Messages.label_Class_Name);
      classNameText.getText().addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            String newType = classNameText.getText().getText();
            if (model != null)
            {
               newType = VariableContextHelper.getInstance().getContext(model)
                     .replaceAllVariablesByDefaultValue(newType);
            }
            if ((null == type) || !CompareHelper.areEqual(type.getFullName(), newType))
            {
               if (finder != null)
               {
                  type = finder.findType(newType);
               }
               else if ((browser != null) && (browser.length > 0))
               {
                  type = browser[0].getTypeFinder().findType(newType);
               }
               updateType();
            }
         }
      });
      browseButton = FormBuilder.createButton(composite, Diagram_Messages.Btn_Browse,
            new SelectionListener()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  IRunnableContext context = new ApplicationWindow(composite.getShell());
                  IJavaSearchScope scope = null;
                  if (filter != null)
                  {
                     try
                     {
                        scope = SearchEngine.createHierarchyScope(filter.getType());
                     }
                     catch (JavaModelException ex)
                     {
                        ex.printStackTrace();
                     }
                  }
                  if (scope == null)
                  {
                     IJavaProject project = finder == null ? null : finder
                           .getJavaProject();
                     if (project != null)
                     {
                        scope = SearchEngine
                              .createJavaSearchScope(new IJavaElement[] {project});
                     }
                     else
                     {
                        scope = SearchEngine.createWorkspaceScope();
                     }
                  }
                  try
                  {
                     SelectionDialog dialog = JavaUI.createTypeDialog(
                           composite.getShell(), context, scope, style, false);
                     dialog.setTitle("");
                     if (dialog.open() == Window.OK)
                     {
                        type = new TypeInfo(finder, (IType) dialog.getResult()[0], null);
                        classNameText.getText().setText(type.getFullName());
                        AttributeType attribute = getTypeAttributeForSerializable(accessPointType);
                        if (attribute == null)
                        {
                           attribute = CarnotWorkflowModelFactory.eINSTANCE
                                 .createAttributeType();
                           attribute.setName(CLASS_NAME_ATT);
                           attribute.setValue(type.getFullName());
                        }
                        else
                        {
                           setTypeAttributeValueForSerializable(accessPointType,
                                 type.getFullName());
                        }
                        accessPointType.getAttribute().add(attribute);
                        updateType();
                     }
                  }
                  catch (JavaModelException e1)
                  {
                     e1.printStackTrace();
                  }
               }

               public void widgetDefaultSelected(SelectionEvent e)
               {}
            });
      browseButton.getRegion();
      classNameText.getText().setEnabled(false);
      dataLabel = FormBuilder.createLabel(composite, Camel_Messages.label_Data);
      dataViewer = new ComboViewer(FormBuilder.createCombo(composite));
      FormBuilder.createLabel(composite, EMPTY_String);
      dataViewer.setContentProvider(ArrayContentProvider.getInstance());
      dataViewer.setLabelProvider(new LabelProvider()
      {
         @Override
         public String getText(Object element)
         {
            if (element instanceof DataType)
               return ((DataType) element).getName();
            else if (element instanceof String)
               return (String) element;
            return null;
         }
      });
      dataViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            if (selection.size() > 0)
            {
               TriggerType trigger = (TriggerType) getModelElement().eContainer();
               ParameterMappingType parameterMapping = getParameterMapping(
                     accessPointType, trigger);
               if (parameterMapping != null)
               {
                  parameterMapping.setData((DataType) selection.getFirstElement());
               }
               else
               {
                  parameterMapping = CarnotWorkflowModelFactory.eINSTANCE
                        .createParameterMappingType();
                  parameterMapping.setParameter(accessPointType.getId());
                  parameterMapping.setData((DataType) selection.getFirstElement());
                  ((TriggerType) accessPointType.eContainer()).getParameterMapping().add(
                        parameterMapping);
               }
            }
         }
      });
      return composite;
   }

   private static TypeDeclarationType findTypeDeclarationType(
         AccessPointType accessPoint, String value)
   {
      ModelType model = ModelUtils.findContainingModel(accessPoint);
      TypeDeclarationsType typeDeclarations = model.getTypeDeclarations();
      for (TypeDeclarationType typeDeclaration : typeDeclarations.getTypeDeclaration())
      {
         if (typeDeclaration.getId().equalsIgnoreCase(value))
         {
            return typeDeclaration;
         }
      }
      return null;
   }

   private static void setAttributeValue(List<AttributeType> accessPoints,
         String extendedAttributeName, String value)
   {
      for (int i = 0; i < accessPoints.size(); i++)
      {
         if (((AttributeType) accessPoints.get(i)).getName().equalsIgnoreCase(
               extendedAttributeName))
            ((AttributeType) accessPoints.get(i)).setValue(value);
      }
   }

   private static AttributeType getTypeAttribute(List<AttributeType> values,
         String extendedAttributeName)
   {
      for (AttributeType attribute : values)
      {
         if (attribute.getName().equalsIgnoreCase(extendedAttributeName))
            return attribute;
      }
      return null;
   }

   private static AttributeType getTypeAttributeForPrimitive(AccessPointType accessPoint)
   {
      List<AttributeType> attributes = accessPoint.getAttribute();
      return getTypeAttribute(attributes, TYPE_ATT);
   }

   private static AttributeType getTypeAttributeForSDT(AccessPointType accessPoint)
   {
      List<AttributeType> attributes = accessPoint.getAttribute();
      return getTypeAttribute(attributes, STRUCTURED_EA_KEY);
   }

   private static AttributeType getTypeAttributeForSerializable(
         AccessPointType accessPoint)
   {
      List<AttributeType> attributes = accessPoint.getAttribute();
      return getTypeAttribute(attributes, CLASS_NAME_ATT);
   }

   private static void setTypeAttributeValueForSDT(AccessPointType accessPoint,
         String value)
   {
      List<AttributeType> attributes = accessPoint.getAttribute();
      setAttributeValue(attributes, STRUCTURED_EA_KEY, value);
   }

   private static void setTypeAttributeValueForPrimitive(AccessPointType accessPoint,
         String value)
   {
      List<AttributeType> attributes = accessPoint.getAttribute();
      setAttributeValue(attributes, TYPE_ATT, value);
   }

   private static void setTypeAttributeValueForSerializable(AccessPointType accessPoint,
         String value)
   {
      List<AttributeType> attributes = accessPoint.getAttribute();
      setAttributeValue(attributes, CLASS_NAME_ATT, value);
   }

   /**
    * used to populate data Type /primitive type combo
    * 
    * @param accessPoint
    * @param type
    * @return
    */
   private Map<String, ? > populateTypeCombo(AccessPointType accessPoint, String type)
   {
      if (type.equals(PRIMITIVE))
         return primitives;
      else if (type.equals(STRUCT) || type.equals(DMS_DOCUMENT))
      {
         return findTypeDeclarations(accessPoint);
      }
      return null;
   }

   private Map<String, TypeDeclarationType> findTypeDeclarations(
         AccessPointType accessPoint)
   {
      ModelType model = ModelUtils.findContainingModel(accessPoint);
      Map<String, TypeDeclarationType> filteredData = new LinkedHashMap<String, TypeDeclarationType>();
      TypeDeclarationsType typeDeclarations = model.getTypeDeclarations();
      for (TypeDeclarationType typeDeclaration : typeDeclarations.getTypeDeclaration())
      {
         filteredData.put(typeDeclaration.getId(), typeDeclaration);
      }
      return filteredData;
   }

   /**
    * initializes the view for primitive, SDT or document type.
    */
   private void initializeViewForAccessPointType()
   {
      if (accessPointType.getType().getId().equals(SERIALIZABLE))
      {
         toggleDataStructureConfiguration(false);
         toggleSerializableConfiguration(true);
         dataViewer.setInput(findData(accessPointType, SERIALIZABLE).values());
         classNameText.getText().setEnabled(false);
      }
      else
      {
         toggleSerializableConfiguration(false);
         toggleDataStructureConfiguration(true);
         if (accessPointType.getType().getId().equals(PRIMITIVE))
         {
            dataStructureLabel.setText(Camel_Messages.label_Primittive_Type);
            dataStructureViewer.setInput(populateTypeCombo(accessPointType, PRIMITIVE)
                  .keySet());
            dataViewer.setInput(findData(accessPointType, PRIMITIVE).values());
         }
         else if (accessPointType.getType().getId().equals(STRUCT))
         {
            dataStructureLabel.setText(Camel_Messages.label_Data_Structure);
            dataStructureViewer.setInput(populateTypeCombo(accessPointType, STRUCT)
                  .values());
            dataViewer.setInput(findData(accessPointType, STRUCT).values());
         }
         else if (accessPointType.getType().getId().equals(DMS_DOCUMENT))
         {
            dataStructureLabel.setText(Camel_Messages.label_Document_Type);
            dataStructureViewer.setInput(populateTypeCombo(accessPointType, DMS_DOCUMENT)
                  .values());
            dataViewer.setInput(findData(accessPointType, DMS_DOCUMENT).values());
         }
      }
      dataStructureLabel.getParent().pack();
   }

   private static Map<String, DataType> findData(AccessPointType accessPoint, String type)
   {
      ModelType model = ModelUtils.findContainingModel(accessPoint);
      Map<String, DataType> filteredData = new LinkedHashMap<String, DataType>();
      for (DataType data : model.getData())
      {
         if (data.getType().getId().equalsIgnoreCase(type))
            filteredData.put(data.getId(), data);
      }
      return filteredData;
   }

   private void bindParameterMapping()
   {
      if (!StringUtils.isEmpty(idText.getText().getText()))
      {
         for (Iterator<ParameterMappingType> iter = getParameter(
               idText.getText().getText(), (TriggerType) getModelElement().eContainer())
               .iterator(); iter.hasNext();)
         {
            getWidgetBindingManager().bind(idText, (ParameterMappingType) iter.next(),
                  PKG_CWM.getParameterMappingType_Parameter());
         }
      }
   }

   private List<ParameterMappingType> getParameter(String id, TriggerType trigger)
   {
      List<ParameterMappingType> parameters = new ArrayList<ParameterMappingType>();
      for (Iterator<ParameterMappingType> iter = trigger.getParameterMapping().iterator(); iter
            .hasNext();)
      {
         ParameterMappingType mapping = (ParameterMappingType) iter.next();
         if ((!StringUtils.isEmpty(id)) && (!StringUtils.isEmpty(mapping.getParameter())))
         {
            if (id.equals(mapping.getParameter()))
            {
               parameters.add(mapping);
            }
         }
      }
      return parameters;
   }

   protected void updateType()
   {
      if (browser != null)
      {
         for (int i = 0; i < browser.length; i++)
         {
            browser[i].setType(type);
         }
      }
   }

   /**
    * Display or hide the fields related to serializable data configuration
    * 
    * @param hide
    */
   private void toggleSerializableConfiguration(boolean hide)
   {
      if (!hide)
      {
         this.browseButton.moveBelow(null);
         this.classNameText.getLabel().moveBelow(null);
         this.classNameText.getText().moveBelow(null);
      }
      else
      {
         this.classNameText.getLabel().moveAbove(dataLabel);
         this.classNameText.getText().moveAbove(dataLabel);
         this.browseButton.moveAbove(dataLabel);
      }
      this.browseButton.setVisible(hide);
      this.classNameText.getLabel().setVisible(hide);
      this.classNameText.getText().setVisible(hide);
   }

   /**
    * Display or hide the fields related to data structure
    * 
    * @param hide
    */
   private void toggleDataStructureConfiguration(boolean hide)
   {
      if (!hide)
      {
         dataStructureLabel.moveBelow(null);
         dataStructureViewer.getCombo().moveBelow(null);
         dataStructureSeparator.moveBelow(null);
      }
      else
      {
         dataStructureLabel.moveAbove(dataLabel);
         dataStructureViewer.getCombo().moveAbove(dataLabel);
         dataStructureSeparator.moveBelow(dataStructureViewer.getCombo());
      }
      dataStructureLabel.setVisible(hide);
      dataStructureViewer.getCombo().setVisible(hide);
      dataStructureSeparator.setVisible(hide);
   }
}
