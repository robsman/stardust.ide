/*******************************************************************************
 * Copyright (c) 2011 - 2012 SunGard CSA
 *******************************************************************************/

package org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Binding;
import javax.wsdl.BindingInput;
import javax.wsdl.BindingOperation;
import javax.wsdl.BindingOutput;
import javax.wsdl.Definition;
import javax.wsdl.Fault;
import javax.wsdl.Input;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.Output;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.xml.namespace.QName;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.xml.sax.InputSource;

import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.runtime.utils.XmlUtils;
import org.eclipse.stardust.engine.extensions.jaxws.addressing.EndpointReferenceType;
import org.eclipse.stardust.engine.extensions.jaxws.addressing.WSAddressing;
import org.eclipse.stardust.engine.extensions.jaxws.app.IBasicAuthenticationParameters;
import org.eclipse.stardust.engine.extensions.jaxws.app.WSConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IAttributeCategory;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionHandler;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ResourceHandler;
import org.eclipse.stardust.modeling.validation.util.ProjectClassLoader;

public class JaxWSPropertyPage extends AbstractModelElementPropertyPage
{
   private static final String EMPTY_STRING = ""; //$NON-NLS-1$

   private static final String ENGINE_SCOPE = "carnot:engine"; //$NON-NLS-1$
   private static final String TEMPLATE_CATEGORY = "template"; //$NON-NLS-1$
   private static final String MAPPING_CATEGORY = "mapping"; //$NON-NLS-1$
   private static final String NAMESPACE_CATEGORY = "namespace"; //$NON-NLS-1$
   private static final String INPUT_CATEGORY = "input"; //$NON-NLS-1$
   private static final String OUTPUT_CATEGORY = "output"; //$NON-NLS-1$
   private static final String FAULT_CATEGORY = "fault"; //$NON-NLS-1$

   private static final String ENDPOINT_REFERENCE_LABEL = Diagram_Messages.WebServicePropertyPage_WsAddressingEndpointReferenceAccessPointName;
   private static final String ENDPOINT_ADDRESS_LABEL = Diagram_Messages.WebServicePropertyPage_EndpointAddressAccessPointName;
   private static final String AUTHENTICATION_LABEL = Diagram_Messages.WebServicePropertyPage_AuthenticationAccessPointName;

   private static final String WSDL_URL_LABEL = Diagram_Messages.WebServicePropertyPage_WsdlUrlLabel;
   private static final String LOAD_BUTTON_LABEL = Diagram_Messages.WebServicePropertyPage_LoadButtonLabel;

   private static final String[][] implementationLabels = {
      {WSConstants.WS_GENERIC_EPR, Diagram_Messages.WebServicePropertyPage_GenericResourceLabel},
      {WSConstants.WS_CARNOT_EPR, Diagram_Messages.WebServicePropertyPage_InfinitySpecificLabel}
   };

   private static final String[][] mechanismLabels = {
      {WSConstants.WS_BASIC_AUTHENTICATION, Diagram_Messages.WebServicePropertyPage_HttpBasicAuthorizationLabel},
      {WSConstants.WS_SECURITY_AUTHENTICATION, Diagram_Messages.WebServicePropertyPage_WsSecurityLabel}
   };

   private static final String[][] variantLabels = {
      {WSConstants.WS_PASSWORD_TEXT, Diagram_Messages.WebServicePropertyPage_UsernamePasswordLabel},
      {WSConstants.WS_PASSWORD_DIGEST, Diagram_Messages.WebServicePropertyPage_UsernamePasswordDigestLabel}//,
      //{WSConstants.WS_XWSS_CONFIGURATION, Webservices_Messages.WebServicePropertyPage_XWSSConfigurationLabel}
   };

   private LabeledText wsdlText;
   private ComboViewer serviceViewer;
   private Label portLabel;
   private ComboViewer portViewer;
   private LabelWithStatus operationLabel;
   private ComboViewer operationViewer;
   private Label styleLabel;
   private Label useLabel;
   private Label protocolLabel;
   private Button addressingButton;
   private Button authenticationButton;
   private Combo implementationCombo;
   private Combo mechanismCombo;
   private Combo variantCombo;

   private Definition wsdlDefinition;
   private Service service;
   private Port port;
   private Binding binding;
   private BindingOperation operation;
   private JaxWSOutlineSynchronizer synchronizer;

   private Label endpointLabel;
   private Text endpoint;

   private DynamicBoundService dynamicBoundService;

   private MessageDialog dialog;
   private Map<Runnable, Boolean> threadMap = new HashMap<Runnable, Boolean>();
   private ModelType model;

   private WSDLLoader currentLoader;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ApplicationType application = (ApplicationType) element;
      model = (ModelType) application.eContainer();
      synchronizer.init(application);
      setWSDLData(application);
      setServiceData(application);
      setAddressingData(application);
      setSecurityData(application);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ApplicationType application = (ApplicationType) element;

      updateAttributes(application);
      updateMappings(application);
      updateTemplates(application);
      updateAccessPoints(application);
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   private void updateAttributes(ApplicationType application)
   {
      AttributeUtil.setAttribute(application, WSConstants.WS_WSDL_URL_ATT,
            wsdlText.getText().getText().trim());
      AttributeUtil.setAttribute(application, WSConstants.WS_SERVICE_NAME_ATT,
            service == null ? null : service.getQName().toString());
      AttributeUtil.setAttribute(application, WSConstants.WS_PORT_NAME_ATT,
            port == null ? null : port instanceof BindingWrapper
                  ? ((BindingWrapper) port).getQName().toString() : port.getName());
      if (service instanceof DynamicBoundService)
      {
         String access = endpoint.getText().trim();
         AttributeUtil.setAttribute(application, WSConstants.WS_UDDI_ACCESS_POINT_ATT,
               access.length() > 0 ? access : null);
      }
      else
      {
         AttributeUtil.setAttribute(application, WSConstants.WS_UDDI_ACCESS_POINT_ATT, null);
      }

      if (operation != null)
      {
         AttributeUtil.setAttribute(application, WSConstants.WS_OPERATION_NAME_ATT,
               operation.getName());
         BindingInput input = operation.getBindingInput();
         AttributeUtil.setAttribute(application, WSConstants.WS_OPERATION_INPUT_NAME_ATT,
               input == null ? null : input.getName());
         BindingOutput output = operation.getBindingOutput();
         AttributeUtil.setAttribute(application, WSConstants.WS_OPERATION_OUTPUT_NAME_ATT,
               output == null ? null : output.getName());
         AttributeUtil.setAttribute(application, WSConstants.WS_SOAP_ACTION_URI_ATT,
               JaxWSResource.getSoapActionUri(operation));
         AttributeUtil.setAttribute(application, WSConstants.WS_SOAP_PROTOCOL_ATT,
               JaxWSResource.getOperationProtocol(operation));

         List<Part> inputOrdering = operation.getOperation().getInput() == null
            || operation.getOperation().getInput().getMessage() == null
               ? Collections.emptyList()
               : operation.getOperation().getInput().getMessage().getOrderedParts(null);
         AttributeUtil.setAttribute(application, WSConstants.WS_INPUT_ORDER_ATT,
               getPartsOrder(inputOrdering));
         List<Part> outputOrdering = operation.getOperation().getOutput() == null
            || operation.getOperation().getOutput().getMessage() == null
               ? Collections.emptyList()
               : operation.getOperation().getOutput().getMessage().getOrderedParts(null);
         AttributeUtil.setAttribute(application, WSConstants.WS_OUTPUT_ORDER_ATT,
               getPartsOrder(outputOrdering));
      }
      else
      {
         AttributeUtil.setAttribute(application, WSConstants.WS_OPERATION_NAME_ATT, null);
         AttributeUtil.setAttribute(application, WSConstants.WS_OPERATION_INPUT_NAME_ATT, null);
         AttributeUtil.setAttribute(application, WSConstants.WS_OPERATION_OUTPUT_NAME_ATT, null);
         AttributeUtil.setAttribute(application, WSConstants.WS_SOAP_ACTION_URI_ATT, null);
         AttributeUtil.setAttribute(application, WSConstants.WS_INPUT_ORDER_ATT, null);
         AttributeUtil.setAttribute(application, WSConstants.WS_OUTPUT_ORDER_ATT, null);
      }

      AttributeUtil.setAttribute(application, WSConstants.WS_IMPLEMENTATION_ATT,
         addressingButton.getSelection() ?
            implementationLabels[implementationCombo.getSelectionIndex()][0] : null);

      AttributeUtil.setAttribute(application, WSConstants.WS_AUTHENTICATION_ATT,
         authenticationButton.getSelection() ?
            mechanismLabels[mechanismCombo.getSelectionIndex()][0] : null);
      AttributeUtil.setAttribute(application, WSConstants.WS_VARIANT_ATT,
         authenticationButton.getSelection() &&
         mechanismLabels[mechanismCombo.getSelectionIndex()][0].equals(WSConstants.WS_SECURITY_AUTHENTICATION) ?
            variantLabels[variantCombo.getSelectionIndex()][0] : null);
   }

   private String getPartsOrder(List<Part> ordering)
   {
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < ordering.size(); i++)
      {
         Part part = ordering.get(i);
         if (buffer.length() > 0)
         {
            buffer.append(','); //$NON-NLS-1$
         }
         buffer.append(part.getName());
      }
      return buffer.toString();
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   private void updateMappings(ApplicationType application)
   {
      IAttributeCategory engineScope = AttributeUtil.createAttributeCategory(
            application, ENGINE_SCOPE);
      IAttributeCategory mappingScope = engineScope.createAttributeCategory(MAPPING_CATEGORY);
      IAttributeCategory namespaceScope = engineScope.createAttributeCategory(NAMESPACE_CATEGORY);

      // clear all mapping/namespace attributes
      engineScope.removeAttributeCategory(mappingScope.getId());
      engineScope.removeAttributeCategory(namespaceScope.getId());

      if (operation != null)
      {
         IAttributeCategory inputMappingScope = mappingScope.createAttributeCategory(INPUT_CATEGORY);
         IAttributeCategory inputNamespaceScope = namespaceScope.createAttributeCategory(INPUT_CATEGORY);
         List<Part> inputParts = operation.getOperation().getInput() == null
                 || operation.getOperation().getInput().getMessage() == null
               ? Collections.emptyList()
               : operation.getOperation().getInput().getMessage().getOrderedParts(null);
         addMappings(inputMappingScope, inputNamespaceScope, inputParts);

         IAttributeCategory outputMappingScope = mappingScope.createAttributeCategory(OUTPUT_CATEGORY);
         IAttributeCategory outputNamespaceScope = namespaceScope.createAttributeCategory(OUTPUT_CATEGORY);
         List<Part> outputParts = operation.getOperation().getOutput() == null
                  || operation.getOperation().getOutput().getMessage() == null
               ? Collections.emptyList()
               : operation.getOperation().getOutput().getMessage().getOrderedParts(null);
         addMappings(outputMappingScope, outputNamespaceScope, outputParts);

         IAttributeCategory allFaultsScope = mappingScope.createAttributeCategory(FAULT_CATEGORY);
         Collection<Fault> faults = operation.getOperation().getFaults() == null
               ? Collections.emptyList()
               : operation.getOperation().getFaults().values();
         for (Iterator<Fault> i = faults.iterator(); i.hasNext();)
         {
            Fault fault = i.next();
            IAttributeCategory faultScope = allFaultsScope.createAttributeCategory(fault.getName());
            List<Part> faultParts = fault.getMessage() == null
                  ? Collections.emptyList()
                  : fault.getMessage().getOrderedParts(null);
            addMappings(faultScope, null, faultParts);
         }
      }
   }

   private void addMappings(IAttributeCategory mappingScope, IAttributeCategory namespaceScope, List<Part> parts)
   {
      for (int i = 0; i < parts.size(); i++)
      {
         Part part = parts.get(i);
         String mapping = synchronizer.getMapping(part);
         if (!StringUtils.isEmpty(mapping))
         {
            String partName = part.getName();
            AttributeType mappingAttribute = mappingScope.createAttribute(partName);
            mappingAttribute.setValue(mapping);
            if (namespaceScope != null)
            {
               AttributeType namespaceAttribute = namespaceScope.createAttribute(partName);
               QName elementName = part.getElementName();
               namespaceAttribute.setValue(elementName == null ? partName : elementName.toString());
            }
         }
      }
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   private void updateTemplates(ApplicationType application)
   {
      IAttributeCategory engineScope = AttributeUtil.createAttributeCategory(
            application, ENGINE_SCOPE); //$NON-NLS-1$
      IAttributeCategory templateScope = engineScope.createAttributeCategory(TEMPLATE_CATEGORY);

      // clear all mapping attributes
      engineScope.removeAttributeCategory(templateScope.getId());

      if (operation != null)
      {
         // only input parts may have templates
         IAttributeCategory inputScope = templateScope.createAttributeCategory(INPUT_CATEGORY);
         List<Part> inputParts = operation.getOperation().getInput() == null
                 || operation.getOperation().getInput().getMessage() == null
               ? Collections.emptyList()
               : operation.getOperation().getInput().getMessage().getOrderedParts(null);
         addTemplates(inputScope, inputParts);
      }
   }

   private void addTemplates(IAttributeCategory scope, List<Part> parts)
   {
      for (int i = 0; i < parts.size(); i++)
      {
         Part part = parts.get(i);
         String mapping = synchronizer.getTemplate(part);
         if (!StringUtils.isEmpty(mapping))
         {
            AttributeType attribute = scope.createAttribute(part.getName());
            attribute.setValue(mapping);
         }
      }
   }

   private void updateAccessPoints(ApplicationType application)
   {
      Map<String, AccessPointType> inAPs = new HashMap<String, AccessPointType>();
      Map<String, AccessPointType> outAPs = new HashMap<String, AccessPointType>();

      List<AccessPointType> accessPoints = application.getAccessPoint();
      for (Iterator<AccessPointType> i = accessPoints.iterator(); i.hasNext();)
      {
         AccessPointType ap = i.next();
         if (AccessPointUtil.isIn(ap.getDirection()))
         {
            inAPs.put(ap.getId(), ap);
         }
         if (AccessPointUtil.isOut(ap.getDirection()))
         {
            outAPs.put(ap.getId(), ap);
         }
      }

      DataTypeType serializable = ModelUtils.getDataType(application,
            CarnotConstants.SERIALIZABLE_DATA_ID);
      DataTypeType plainXML = ModelUtils.getDataType(application,
            CarnotConstants.PLAIN_XML_DATA_ID);
      if (addressingButton.getSelection())
      {
         String className = WSConstants.WS_CARNOT_EPR.equals(
               implementationLabels[implementationCombo.getSelectionIndex()][0]) ?
               WSAddressing.IPPEndpointReference.class.getName() :
               EndpointReferenceType.class.getName();
         JaxWSUtil.createAccessPoint(inAPs, application,
               WSConstants.WS_ENDPOINT_REFERENCE_ID, ENDPOINT_REFERENCE_LABEL,
               DirectionType.IN_LITERAL, serializable, className);
      }
      else
      {
         JaxWSUtil.createAccessPoint(inAPs, application,
               WSConstants.WS_ENDPOINT_ADDRESS_ID, ENDPOINT_ADDRESS_LABEL,
               DirectionType.IN_LITERAL, serializable, String.class.getName());
      }

      if (authenticationButton.getSelection())
      {
         String className = IBasicAuthenticationParameters.class.getName();
         JaxWSUtil.createAccessPoint(inAPs, application,
               WSConstants.WS_AUTHENTICATION_ID, AUTHENTICATION_LABEL,
               DirectionType.IN_LITERAL, serializable, className);
      }

      if (operation != null)
      {
         String bindingStyle = styleLabel.getText();
         if (operation.getOperation().getInput() != null
               && operation.getOperation().getInput().getMessage() != null)
         {
            JaxWSUtil.createAccessPoints(application, operation.getOperation().getInput().getMessage(),
                  bindingStyle, DirectionType.IN_LITERAL, serializable, plainXML, inAPs, synchronizer);
         }
         if (operation.getOperation().getOutput() != null
               && operation.getOperation().getOutput().getMessage() != null)
         {
            JaxWSUtil.createAccessPoints(application, operation.getOperation().getOutput().getMessage(),
                  bindingStyle, DirectionType.OUT_LITERAL, serializable, plainXML, outAPs, synchronizer);
         }
      }

      accessPoints.removeAll(inAPs.values());
      accessPoints.removeAll(outAPs.values());
   }

   private void setSecurityData(ApplicationType application)
   {
      String authentication = AttributeUtil.getAttributeValue(application,
            WSConstants.WS_AUTHENTICATION_ATT);
      int index = findIndex(mechanismLabels, authentication);
      authenticationButton.setSelection(index >= 0);
      authenticationChanged(index);

      String variant = AttributeUtil.getAttributeValue(application,
            WSConstants.WS_VARIANT_ATT);
      int vIndex = findIndex(variantLabels, variant);
      mechanismChanged(vIndex < 0 && index >= 0 ? 0 : vIndex);
   }

   private int findIndex(String[][] labels, String value)
   {
      int index = -1;
      for (int i = 0; i < labels.length; i++)
      {
         if (labels[i][0].equals(value))
         {
            index = i;
         }
      }
      return index;
   }

   private void setAddressingData(ApplicationType application)
   {
      String implementation = AttributeUtil.getAttributeValue(application,
            WSConstants.WS_IMPLEMENTATION_ATT);
      int index = findIndex(implementationLabels, implementation);
      addressingButton.setSelection(index >= 0);
      addressingChanged(index);
   }

   private void setWSDLData(ApplicationType application)
   {
      String wsdlUrl = AttributeUtil.getAttributeValue(application, WSConstants.WS_WSDL_URL_ATT);
      wsdlText.getText().setText(wsdlUrl == null ? EMPTY_STRING : wsdlUrl);
      loadWsdl();
   }

   @SuppressWarnings("unchecked")
   private void setServiceData(ApplicationType application)
   {
      if (null != wsdlDefinition)
      {
         String serviceName = AttributeUtil.getAttributeValue(application, WSConstants.WS_SERVICE_NAME_ATT);
         if (null != serviceName)
         {
            QName serviceQName = QName.valueOf(serviceName);
            setViewerSelection(serviceViewer, findService(serviceQName));
            serviceChanged();
            if (null != service)
            {
               if (service instanceof DynamicBoundService)
               {
                  String access = AttributeUtil.getAttributeValue(application, WSConstants.WS_UDDI_ACCESS_POINT_ATT);
                  endpoint.setText(access == null ? EMPTY_STRING : access);
               }
               else
               {
                  endpoint.setText(EMPTY_STRING);
               }
            }

            String portName = AttributeUtil.getAttributeValue(application, WSConstants.WS_PORT_NAME_ATT);
            QName qName = portName == null ? null : QName.valueOf(portName);
            setViewerSelection(portViewer, qName == null ? null :
               service instanceof DynamicBoundService
                  ? ((DynamicBoundService) service).getPort(qName) : service.getPort(portName));
            portChanged();
            if (null != binding)
            {
               String operationName = AttributeUtil.getAttributeValue(application,
                     WSConstants.WS_OPERATION_NAME_ATT);
               if (!StringUtils.isEmpty(operationName))
               {
                  String inputName = AttributeUtil.getAttributeValue(application,
                        WSConstants.WS_OPERATION_INPUT_NAME_ATT);
                  String outputName = AttributeUtil.getAttributeValue(application,
                        WSConstants.WS_OPERATION_OUTPUT_NAME_ATT);
                  List<BindingOperation> operations = binding.getBindingOperations();
                  for (BindingOperation operation : operations)
                  {
                     if (operationName.equals(operation.getName()))
                     {
                        String opInputName = operation.getBindingInput() == null
                           ? null : operation.getBindingInput().getName();
                        String opOutputName = operation.getBindingOutput() == null
                           ? null : operation.getBindingOutput().getName();
                        if (CompareHelper.areEqual(inputName, opInputName)
                              && CompareHelper.areEqual(outputName, opOutputName))
                        {
                           setViewerSelection(operationViewer, operation);
                           operationChanged();
                           break;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private Service findService(QName serviceName)
   {
      Service service = wsdlDefinition.getService(serviceName);
      if (service == null && dynamicBoundService != null && dynamicBoundService.getQName().equals(serviceName))
      {
         service = dynamicBoundService;
      }
      return service;
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 4);

      LabelWithStatus wsdlLabel = FormBuilder.createLabelWithLeftAlignedStatus(composite, WSDL_URL_LABEL);
      Composite wsdlComposite = FormBuilder.createComposite(composite, 2, 3);
      GridLayout layout = (GridLayout) wsdlComposite.getLayout();
      layout.marginWidth = 0;
      layout.marginHeight = 0;
      ((GridData) wsdlComposite.getLayoutData()).grabExcessVerticalSpace = false;
      wsdlText = new LabeledText(FormBuilder.createText(wsdlComposite), wsdlLabel);

      FormBuilder.createButton(wsdlComposite, LOAD_BUTTON_LABEL, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            try
            {
               String wsdlUrl = getWsdlResource();
               if (wsdlUrl.length() != 0)
               {
                  wsdlChanged();
               }
            }
            catch (Exception ex)
            {
               ex.printStackTrace();
               showError(Diagram_Messages.WebServicePropertyPage_LoadErrorMessage, WSConstants.WSDL_LOAD_ERROR_CODE, ex);
            }
         }
      });

      FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_ServiceLabel);
      Combo serviceCombo = FormBuilder.createCombo(composite, 3);
      serviceViewer = new ComboViewer(serviceCombo);
      serviceViewer.setContentProvider(new ArrayContentProvider());
      serviceViewer.setLabelProvider(new QNameLabelProvider());
      serviceViewer.setSorter(new ViewerSorter());
      serviceCombo.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            serviceChanged();
         }
      });

      this.portLabel = FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_PortLabel);
      Combo portCombo = FormBuilder.createCombo(composite);
      this.portViewer = new ComboViewer(portCombo);
      portViewer.setContentProvider(new ArrayContentProvider());
      portViewer.setLabelProvider(new QNameLabelProvider());
      portViewer.setSorter(new ViewerSorter());
      portCombo.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            portChanged();
         }
      });

      operationLabel = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.WebServicePropertyPage_OperationLabel);
      Combo operationCombo = FormBuilder.createCombo(composite);
      operationViewer = new ComboViewer(operationCombo);
      operationViewer.setContentProvider(new ArrayContentProvider());
      operationViewer.setLabelProvider(new QNameLabelProvider());
      operationViewer.setSorter(new ViewerSorter());
      operationCombo.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            operationChanged();
         }
      });

      operationCombo.addVerifyListener(new VerifyListener ()
      {
         public void verifyText(VerifyEvent verifyEvent)
         {
            if (operation != null)
            {
               String validationWarning = validateOperation(operation);
               if (validationWarning != null)
               {
                  operationLabel.setValidationStatus(IQuickValidationStatus.WARNINGS);
                  operationLabel.setToolTipText(validationWarning);
               }
            }
         }
      });

      FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_StyleLabel);
      styleLabel = FormBuilder.createLabel(composite, EMPTY_STRING);
      FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_UseLabel);
      useLabel = FormBuilder.createLabel(composite, EMPTY_STRING);
      FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_ProtocolLabel);
      protocolLabel = FormBuilder.createLabel(composite, EMPTY_STRING, 3);

      FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_AddressingLabel);
      addressingButton = FormBuilder.createCheckBox(composite, Diagram_Messages.WebServicePropertyPage_IncludeAddressingCheckBoxLabel);
      addressingButton.addSelectionListener(new SelectionAdapter()
      {
         private int cache = 0;

         public void widgetSelected(SelectionEvent e)
         {
            cache = addressingChanged(cache);
         }
      });
      FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_ImplementationLabel);
      implementationCombo = FormBuilder.createCombo(composite);
      addLabels(implementationCombo, implementationLabels);

      final SelectionListener mechSel = new SelectionAdapter()
      {
         private int cache = variantLabels.length - 1;

         public void widgetSelected(SelectionEvent e)
         {
            cache = mechanismChanged(cache);
         }
      };

      FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_AuthenticationLabel);
      authenticationButton = FormBuilder.createCheckBox(composite, Diagram_Messages.WebServicePropertyPage_RequiredCheckBoxLabel);
      authenticationButton.addSelectionListener(new SelectionAdapter()
      {
         private int cache = 0;

         public void widgetSelected(SelectionEvent e)
         {
            cache = authenticationChanged(cache);
            mechSel.widgetSelected(null);
         }
      });
      FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_MechanismLabel);
      mechanismCombo = FormBuilder.createCombo(composite);
      mechanismCombo.addSelectionListener(mechSel);
      addLabels(mechanismCombo, mechanismLabels);

      FormBuilder.createLabel(composite, EMPTY_STRING, 2);
      FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_VariantLabel);
      variantCombo = FormBuilder.createCombo(composite);
      addLabels(variantCombo, variantLabels);

      endpointLabel = FormBuilder.createLabel(composite, Diagram_Messages.WebServicePropertyPage_EndpointLabel);
      endpoint = FormBuilder.createText(composite, 3);
      endpointLabel.setVisible(false);
      endpoint.setVisible(false);

      synchronizer = new JaxWSOutlineSynchronizer(this);

      return composite;
   }

   protected String validateOperation(BindingOperation operationToVerify)
   {
      try
      {
         Operation soapOperation = operationToVerify.getOperation();
         if (soapOperation != null)
         {
            Input input = soapOperation.getInput();
            if (input != null)
            {
               Message message = input.getMessage();
               if (message != null)
               {
                  @SuppressWarnings("unchecked")
                  Map<String, Part> inputParts = message.getParts();
                  for (Part part : inputParts.values())
                  {
                     if (JaxWSUtil.findMatchingTypeDeclaration(this.getApplication(), part) == null)
                     {
                        return MessageFormat.format(
                              Diagram_Messages.WebServicePropertyPage_TypeNotFoundForInputPart,
                              new Object[] {part.getName()});
                     }
                  }
               }
            }

            Output output = soapOperation.getOutput();
            if (output != null)
            {
               Message message = output.getMessage();
               if (message != null)
               {
                  @SuppressWarnings("unchecked")
                  Map<String, Part> outputParts = message.getParts();
                  for (Part part : outputParts.values())
                  {
                     if (JaxWSUtil.findMatchingTypeDeclaration(this.getApplication(), part) == null)
                     {
                        return MessageFormat.format(
                              Diagram_Messages.WebServicePropertyPage_TypeNotFoundForOutputPart,
                              new Object[] {part.getName()});
                     }
                  }
               }
            }
         }
      }
      catch (Exception e)
      {
         String messageString = Diagram_Messages.WebServicePropertyPage_UnknownPartValidationError;
         showError(messageString, 0, e);
         return messageString;
      }
      // structured types exist for all parameter parts
      return null;
   }

   private void showError(String message, int code, Exception exception)
   {
      ErrorDialog.openError(null, Diagram_Messages.WebServicePropertyPage_ErrorDialogTitle, message,
         new Status(Status.WARNING, CarnotConstants.DIAGRAM_PLUGIN_ID, code,
            exception.getMessage() == null ? exception.getClass().getName() : exception.getMessage(),
            exception));
   }

   private int addressingChanged(int cache)
   {
      return buttonChanged(cache, addressingButton.getSelection(), implementationCombo);
   }

   private int authenticationChanged(int cache)
   {
      return buttonChanged(cache, authenticationButton.getSelection(), mechanismCombo);
   }

   private int buttonChanged(int cache, boolean selected, Combo control)
   {
      if (selected)
      {
         control.select(cache);
      }
      else
      {
         cache = control.getSelectionIndex();
         control.deselectAll();
      }
      control.setEnabled(selected);
      return cache;
   }

   private int mechanismChanged(int cache)
   {
      int index = mechanismCombo.getSelectionIndex();
      boolean wsSecuritySelected = index >= 0 && index < mechanismLabels.length
         && mechanismLabels[index][0].equals(WSConstants.WS_SECURITY_AUTHENTICATION);
      if (wsSecuritySelected)
      {
         variantCombo.select(cache);
      }
      else
      {
         cache = variantCombo.getSelectionIndex();
         if (index >= 0 && index < mechanismLabels.length)
         {
            variantCombo.select(0);
         }
         else
         {
            variantCombo.deselectAll();
         }
      }
      variantCombo.setEnabled(wsSecuritySelected);
      return cache;
   }

   private void addLabels(Combo combo, String[][] strings)
   {
      for (int i = 0; i < strings.length; i++)
      {
         combo.add(strings[i][1]);
      }
   }

   private String getWsdlResource()
   {
      return wsdlText.getText().getText().trim();
   }

   private void loadWsdl()
   {
      if (getWsdlResource().length() > 0)
      {
         try
         {
            wsdlChanged();
         }
         catch (Exception ex)
         {
            showError(Diagram_Messages.WebServicePropertyPage_LoadErrorMessage, WSConstants.WSDL_LOAD_ERROR_CODE, ex);
         }
      }
   }

   private void wsdlChanged() throws Exception
   {
      // need to override context class loader so we can find the resource from the
      // project classpath
      ClassLoader cclBackup = Thread.currentThread().getContextClassLoader();
      try
      {
         IProject project = ModelUtils.getProjectFromEObject(getApplication());
         String resource = getWsdlResource();
         // strip leading slash if any
         if (resource.startsWith("/")) //$NON-NLS-1$
         {
            resource = resource.substring(1);
         }
         Thread.currentThread().setContextClassLoader(new ProjectClassLoader(
               XmlUtils.class.getClassLoader(), project, resource));
         String[] buttons = new String[] {IDialogConstants.CANCEL_LABEL };
         dialog = new MessageDialog(Display.getDefault().getActiveShell(),
               Diagram_Messages.WebServicePropertyPage_WSDL, null,
               Diagram_Messages.WebServicePropertyPage_Retrieving_WSDL,
               MessageDialog.INFORMATION, buttons, 0)
         {
            protected void buttonPressed(int buttonId)
            {
               threadMap.put(currentLoader, true);
               super.buttonPressed(buttonId);
            }
         };
         currentLoader = new WSDLLoader(resource);
         threadMap.put(currentLoader, false);
         Thread thread = new Thread(currentLoader);
         thread.start();
         dialog.open();
      }
      finally
      {
         // restoring previous context class loader
         Thread.currentThread().setContextClassLoader(cclBackup);
      }
   }

   private Definition getWSDL(String resource) throws CoreException, Exception
   {
      if (resource != null)
      {
         resource = VariableContextHelper.getInstance().getContext(model)
               .replaceAllVariablesByDefaultValue(resource);
      }
      Definition definition = null;
      try
      {
         definition = JaxWSResource.getDefinition(resource);
      }
      catch (Exception ex)
      {
         // Infinity registry specific authentication requirements
         String connectionUri = AttributeUtil.getAttributeValue(getApplication(),
               IConnectionManager.URI_ATTRIBUTE_NAME);
         if (connectionUri != null)
         {
            // (fh) compatibility mode
            ConnectionManager cm = getEditor().getConnectionManager();
            if (cm != null)
            {
               URI uri = URI.createURI(connectionUri);
               String connectionId = uri.authority();
               if (connectionId != null)
               {
                  Connection connection = cm.getConnection(connectionId);
                  if (connection != null)
                  {
                     cm.open(connection);
                     ConnectionHandler handler = cm.getConnectionHandler(connection);
                     if (handler instanceof ResourceHandler)
                     {
                        InputSource source = ((ResourceHandler) handler)
                              .getInputSource(resource);
                        if (source != null)
                        {
                           definition = JaxWSResource.getDefinition(resource, source);
                        }
                     }
                  }
               }
            }
         }
         if (definition == null)
         {
            throw ex;
         }
      }
      return definition;
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   private List<Service> getServices()
   {
      List<Service> services = new ArrayList<Service>();
      for (Iterator<Service> i = wsdlDefinition.getServices().values().iterator(); i.hasNext();)
      {
         services.add(i.next());
      }
      services.add(dynamicBoundService);
      return services;
   }

   private void serviceChanged()
   {
      this.service = (Service) getSelectedItem(serviceViewer);

      boolean isDynamic = service instanceof DynamicBoundService;

      portLabel.setText(isDynamic ? Diagram_Messages.WebServicePropertyPage_BindingLabel : Diagram_Messages.WebServicePropertyPage_PortLabel);
      endpointLabel.setVisible(isDynamic);
      endpoint.setVisible(isDynamic);

      setViewerData(portViewer, getPorts()/*, false*/);
      portChanged();
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   private List<Port> getPorts()
   {
      List<Port> ports = new ArrayList<Port>();
      if (service != null)
      {
         for (Iterator<Port> i = service.getPorts().values().iterator(); i.hasNext();)
         {
            ports.add(i.next());
         }
      }
      return ports;
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   private List<BindingOperation> getOperations()
   {
      return binding == null ? null : binding.getBindingOperations();
   }

   private void portChanged()
   {
      port = (Port) getSelectedItem(portViewer);
      binding = port == null ? null : port.getBinding();
      String style = JaxWSResource.getBindingStyle(binding);
      styleLabel.setText(style == null ? "" : style); //$NON-NLS-1$
      setViewerData(operationViewer, getOperations()/*, false*/);
      operationChanged();
   }

   private Object getSelectedItem(StructuredViewer viewer)
   {
      IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
      return selection.isEmpty() ? null : selection.getFirstElement();
   }

   private void setViewerData(StructuredViewer viewer, List<?> data/*, boolean force*/)
   {
      if (data == null)
      {
         data = Collections.EMPTY_LIST;
      }
      viewer.setInput(data);
      viewer.getControl().setEnabled(!data.isEmpty());
/*      if (force || data.size() == 1)
      {
         viewer.setSelection(new StructuredSelection(data.get(0)));
      }*/
      if (data.size() > 0)
      {
         viewer.setSelection(new StructuredSelection(data.get(0)));
      }
   }

   private void operationChanged()
   {
      operation = (BindingOperation) getSelectedItem(operationViewer);
      String style = JaxWSResource.getOperationStyle(operation);
      if (style == null)
      {
         style = JaxWSResource.getBindingStyle(binding);
      }
      styleLabel.setText(style == null ? "" : style); //$NON-NLS-1$
      String use = JaxWSResource.getOperationUse(operation);
      useLabel.setText(use == null ? "" : use); //$NON-NLS-1$
      String protocol = JaxWSResource.getOperationProtocol(operation);
      protocolLabel.setText(protocol == null ? "" : protocol); //$NON-NLS-1$
      synchronizer.setOperation(operation);

   }

   private void setViewerSelection(StructuredViewer viewer, Object selection)
   {
      viewer.setSelection((null != selection)
            ? new StructuredSelection(selection)
            : StructuredSelection.EMPTY,
            true);
   }

   protected ApplicationType getApplication()
   {
      Object element = getElement();
      if (element instanceof EditPart)
      {
         element = ((EditPart) element).getModel();
      }
      if (element instanceof IModelElementNodeSymbol)
      {
         element = ((IModelElementNodeSymbol) element).getModelElement();
      }
      return element instanceof ApplicationType ? (ApplicationType) element : null;
   }

   public String getWsdlLocation()
   {
      IProject project = ModelUtils.getProjectFromEObject(getApplication());
      String resource = wsdlText.getText().getText().trim();
      ProjectClassLoader cl = new ProjectClassLoader(
            XmlUtils.class.getClassLoader(), project, resource.startsWith("/") //$NON-NLS-1$
            ? resource.substring(1) : resource);
      URL url = cl.getResource(resource);
      return url == null ? resource : url.toString();
   }

   class WSDLLoader implements Runnable {
      private String resource;
      private Definition definition = null;
      private Exception e;

      public Definition getDefinition()
      {
         return definition;
      }
      public WSDLLoader(String resource)
      {
         this.resource = resource;
      }
      public void run()
      {
         try
         {
            definition = getWSDL(resource);
            dynamicBoundService = new DynamicBoundService(definition);
            wsdlDefinition = definition;
            if (definition != null) {
               Display.getDefault().syncExec(new Runnable() {
                  public void run()
                  {
                     setViewerData(serviceViewer, getServices()/*, wsdlDefinition.getServices().size() == 1*/);
                     serviceChanged();
                  }
               });
            }
         }
         catch (Exception ex)
         {
            e = ex;
            boolean isCanceled = ((Boolean) threadMap.get(this)).booleanValue();
            if (!isCanceled && currentLoader == this) {
               Display.getDefault().syncExec(new Runnable() {
                  public void run()
                  {
                     String message = e.getMessage() == null ? e.toString() : e.getMessage();
                     ErrorDialog.openError(null, "", "", //$NON-NLS-1$ //$NON-NLS-2$
                        new Status(Status.WARNING, CarnotConstants.DIAGRAM_PLUGIN_ID, 1, message, e));
                  }
                 });
            }
         }
         finally {
            boolean isCanceled = ((Boolean) threadMap.get(this)).booleanValue();
            if (!isCanceled && currentLoader == this) {
               Display.getDefault().syncExec(new Runnable() {
                  public void run()
                  {
                     dialog.close();
                  }
               });
            }
            threadMap.remove(this);
         }
      }
   }
}
