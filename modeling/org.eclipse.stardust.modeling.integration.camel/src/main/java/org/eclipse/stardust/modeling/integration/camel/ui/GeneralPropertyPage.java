package org.eclipse.stardust.modeling.integration.camel.ui;

import static org.eclipse.stardust.engine.extensions.camel.CamelConstants.*;
import static org.eclipse.stardust.engine.extensions.camel.CamelConstants.InvocationTypes.*;
import static org.eclipse.stardust.engine.extensions.camel.CamelConstants.InvocationPatterns.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.extensions.camel.CamelConstants;
import org.eclipse.stardust.engine.extensions.camel.GenericProducer;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.IButtonManager;
import org.eclipse.stardust.modeling.core.properties.ModelElementAdaptable;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.integration.camel.Camel_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Camel Application type property page; 3 fields are provided camelContextId,
 * routeDefinition and additional spring beans definition
 * 
 * @author Fradj.ZAYEN
 * 
 */
public class GeneralPropertyPage extends AbstractModelElementPropertyPage
{
   private static final String SPI_NODE = "cwm_spi_camel_general_tab_"; //$NON-NLS-1$
   private static final String PRODUCER_NODE = "cwm_spi_camel_producer_tab_"; //$NON-NLS-1$
   private static final String CONSUMER_NODE = "cwm_spi_camel_consumer_tab_"; //$NON-NLS-1$

   private Text camelContextNameText;
   private Text additionalSpringBeanDefinitions;
   private ComboViewer invocationPatternViewer;
   private ComboViewer invocationTypeViewer;
   private Label invocationTypeLabel;
   private IExtensibleElement extensibleElement;
   
   private Label bodyInAccessPointLabel;
   private ComboViewer bodyInAccessPointViewer;
   
   private Label bodyOutAccessPointLabel;
   private ComboViewer bodyOutAccessPointViewer;
   
   public void dispose()
   {
      super.dispose();
   }
   
   public void populateBodyInAccessPointViewer()
   {
	   String intBodyAP = 
			   AttributeUtil.getAttributeValue(getApplication(), CamelConstants.CAT_BODY_IN_ACCESS_POINT);
	      
	   AccessPointType bodyInAccessPoint = null;
	   
	   List<AccessPointType> inAccessPoints = new ArrayList<AccessPointType>();
	   
	   List<AccessPointType> accessPoints = getApplication().getAccessPoint();
      
	   for (Iterator<AccessPointType> _iterator = accessPoints.iterator(); _iterator.hasNext();)
	   {
		   AccessPointType apt = _iterator.next();
    	  
		   if (apt.getDirection().equals(DirectionType.IN_LITERAL) 
				   || apt.getDirection().equals(DirectionType.INOUT_LITERAL))
		   {
			   inAccessPoints.add(apt);
			   
			   if (apt.getId().equals(intBodyAP))
			   {
				   bodyInAccessPoint = apt;
			   }
		   }
	   }
	   
	   bodyInAccessPointViewer.setInput(inAccessPoints);
	   bodyInAccessPointViewer.refresh();
	   
	   if (bodyInAccessPoint != null)
	   {
		   bodyInAccessPointViewer.setSelection(new StructuredSelection(bodyInAccessPoint));
	   }
	   else
	   {
		   AttributeUtil.setAttribute(getApplication(), CamelConstants.CAT_BODY_IN_ACCESS_POINT, null);
	   }
	   
	   bodyInAccessPointViewer.getCombo().setEnabled(bodyInAccessPointViewer.getCombo().getItemCount() != 0);

   }
   
   public void populateBodyOutAccessPointViewer()
   {
	   bodyOutAccessPointViewer.refresh();
	   
	   String outBodyAP = 
			   AttributeUtil.getAttributeValue(getApplication(), CamelConstants.CAT_BODY_OUT_ACCESS_POINT);
	   
	   AccessPointType bodyOutAccessPoint = null;
	   
	   List<AccessPointType> outAccessPoints = new ArrayList<AccessPointType>();
	   
	   List<AccessPointType> accessPoints = getApplication().getAccessPoint();
      
	   for (Iterator<AccessPointType> _iterator = accessPoints.iterator(); _iterator.hasNext();)
	   {
		   AccessPointType apt = _iterator.next();
		   
		   if (apt.getDirection().equals(DirectionType.OUT_LITERAL) 
				   || apt.getDirection().equals(DirectionType.INOUT_LITERAL))
		   {			   
			   outAccessPoints.add(apt);
			   
			   if (apt.getId().equals(outBodyAP))
			   {
				   bodyOutAccessPoint = apt;
			   }
		   }
	   }
	   
	   bodyOutAccessPointViewer.setInput(outAccessPoints);
	   bodyOutAccessPointViewer.refresh();
	   
	   if (bodyOutAccessPoint != null)
	   {
		   bodyOutAccessPointViewer.setSelection(new StructuredSelection(bodyOutAccessPoint));
	   }
	   else
	   {
		   AttributeUtil.setAttribute(getApplication(), CamelConstants.CAT_BODY_OUT_ACCESS_POINT, null);
	   }
	   
	   bodyOutAccessPointViewer.getCombo().setEnabled(bodyOutAccessPointViewer.getCombo().getItemCount() != 0);

   }	

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      extensibleElement = (IExtensibleElement) element;
      getAttributeValue(CamelConstants.CAMEL_CONTEXT_ID_ATT, camelContextNameText);
      getAttributeValue(CamelConstants.ADDITIONAL_SPRING_BEANS_DEF_ATT, additionalSpringBeanDefinitions);
      getInvocationPatternValue(invocationPatternViewer, invocationTypeViewer, element);
   }


   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      extensibleElement = (IExtensibleElement) element;
      setAttributeValue(CamelConstants.CAMEL_CONTEXT_ID_ATT, null, camelContextNameText);
      setAttributeValue(CamelConstants.ADDITIONAL_SPRING_BEANS_DEF_ATT, null, additionalSpringBeanDefinitions);
      
      AttributeUtil.setAttribute(extensibleElement, CamelConstants.SUPPORT_MULTIPLE_ACCESS_POINTS, "boolean", Boolean.toString(true));
      
   }

   private ApplicationType getApplication()
   {
      return (ApplicationType) getModelElement();
   }
   
   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      
      FormBuilder.createLabel(composite, Camel_Messages.label_CamelContextId);
      camelContextNameText = FormBuilder.createText(composite);
      if (StringUtils.isEmpty(camelContextNameText.getText()))
      {
         camelContextNameText.setText(DEFAULT_CAMEL_CONTEXT_ID);
      }
    
      FormBuilder.createLabel(composite, Camel_Messages.label_Invocation_Pattern);
      invocationPatternViewer = new ComboViewer(FormBuilder.createCombo(composite));
      invocationPatternViewer.setContentProvider(ArrayContentProvider.getInstance());
      invocationPatternViewer.add(Camel_Messages.label_Invocation_Pattern_Send);
      invocationPatternViewer.add(Camel_Messages.label_Invocation_Pattern_SendReceive);
      invocationPatternViewer.add(Camel_Messages.label_Invocation_Pattern_Receive);
      invocationPatternViewer.setSelection(new StructuredSelection(Camel_Messages.label_Invocation_Pattern_Send));
      invocationPatternViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            // String producerMethod = CorrelationValue.PROCESS;
            ApplicationType application = getApplication();
            if (((String) selection.getFirstElement()).equalsIgnoreCase(Camel_Messages.label_Invocation_Pattern_Send))
            {
               initializePropertyPage();
               AttributeUtil.setAttribute(application, CamelConstants.INVOCATION_TYPE_EXT_ATT, SYNCHRONOUS);
               AttributeUtil.setAttribute(application, CamelConstants.INVOCATION_PATTERN_EXT_ATT, SEND);

               addPropertyPage(getApplication(), createProducerConfigurationElement());
               changeApplicationTypeToProducer();
            }

            if (((String) selection.getFirstElement())
                  .equalsIgnoreCase(Camel_Messages.label_Invocation_Pattern_SendReceive))
            {
               AttributeUtil.setAttribute(application, CamelConstants.INVOCATION_PATTERN_EXT_ATT, SENDRECEIVE);
               if (getExtendedAttributeValueForInvocationType() != null
                     && getExtendedAttributeValueForInvocationType().equalsIgnoreCase(
                           Camel_Messages.label_Invocation_Type_Sync))
               {
                  initializePropertyPage();
                  invocationTypeLabel.setVisible(true);
                  invocationTypeViewer.getCombo().setVisible(true);
                  AttributeUtil.setAttribute(application, CamelConstants.INVOCATION_TYPE_EXT_ATT, SYNCHRONOUS);
                  addPropertyPage(getApplication(), createProducerConfigurationElement());
                  changeApplicationTypeToProducer();
               }
               if (getExtendedAttributeValueForInvocationType() != null
                     && getExtendedAttributeValueForInvocationType().equalsIgnoreCase(
                           Camel_Messages.label_Invocation_Type_Async))
               {
                  initializePropertyPage();
                  invocationTypeLabel.setVisible(true);
                  invocationTypeViewer.getCombo().setVisible(true);
                  invocationTypeViewer.setSelection(
                        new StructuredSelection(Camel_Messages.label_Invocation_Type_Async), true);
                  addPropertyPage(getApplication(), createProducerConfigurationElement());
                  addPropertyPage(getApplication(), createConsumerConfigurationElement());
                  changeApplicationTypeToConsumer();
               }
            }

            if (((String) selection.getFirstElement())
                  .equalsIgnoreCase(Camel_Messages.label_Invocation_Pattern_Receive))
            {
               initializePropertyPage();
               AttributeUtil.setAttribute(application, CamelConstants.INVOCATION_TYPE_EXT_ATT, ASYNCHRONOUS);
               AttributeUtil.setAttribute(application, CamelConstants.INVOCATION_PATTERN_EXT_ATT, RECEIVE);
               addPropertyPage(getApplication(), createConsumerConfigurationElement());
               changeApplicationTypeToConsumer();
            }
         }
      });

      invocationTypeLabel = FormBuilder.createLabel(composite, Camel_Messages.label_Invocation_Type);
      invocationTypeViewer = new ComboViewer(FormBuilder.createCombo(composite));
      invocationTypeViewer.setContentProvider(ArrayContentProvider.getInstance());
      invocationTypeViewer.add(Camel_Messages.label_Invocation_Type_Sync);
      invocationTypeViewer.add(Camel_Messages.label_Invocation_Type_Async);
      invocationTypeViewer.setSelection(new StructuredSelection(Camel_Messages.label_Invocation_Type_Sync));
      invocationTypeViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            ApplicationType application = getApplication();
            if ((selection == null || selection.getFirstElement() == null)
                  || (((String) selection.getFirstElement())
                        .equalsIgnoreCase(Camel_Messages.label_Invocation_Type_Sync)))
            {
               initializePropertyPage();
               AttributeUtil.setAttribute(application, CamelConstants.INVOCATION_TYPE_EXT_ATT, SYNCHRONOUS);
               invocationTypeLabel.setVisible(true);
               invocationTypeViewer.getCombo().setVisible(true);
               addPropertyPage(getApplication(), createProducerConfigurationElement());
            }

            if (((String) selection.getFirstElement()).equalsIgnoreCase(Camel_Messages.label_Invocation_Type_Async))
            {
               initializePropertyPage();
               AttributeUtil.setAttribute(application, CamelConstants.INVOCATION_TYPE_EXT_ATT, ASYNCHRONOUS);
               invocationTypeLabel.setVisible(true);
               invocationTypeViewer.getCombo().setVisible(true);
               addPropertyPage(getApplication(), createProducerConfigurationElement());
               addPropertyPage(getApplication(), createConsumerConfigurationElement());
            }
         }
      });
      
      FormBuilder.createHorizontalSeparator(composite, 2).setVisible(false);
      FormBuilder.createHorizontalSeparator(composite, 2);
      FormBuilder.createHorizontalSeparator(composite, 2).setVisible(false);
      
      bodyInAccessPointLabel = FormBuilder.createLabel(composite, Camel_Messages.label_Body_Input_Access_Point);
      bodyInAccessPointViewer = new ComboViewer(FormBuilder.createCombo(composite));
      bodyInAccessPointViewer.setContentProvider(ArrayContentProvider.getInstance());
      bodyInAccessPointViewer.setLabelProvider(new AccessPointLabelProvider());
      
      bodyInAccessPointViewer.setComparer(new IElementComparer() {
  		
  		@Override
  		public int hashCode(Object hashCode) {
  			return hashCode.hashCode();
  		}
  		
  		@Override
		public boolean equals(Object one, Object two) {
			
			if (one != null && two != null)
			{
				if (one instanceof AccessPointType && two instanceof AccessPointType)
				{
					String aptOneId = ((AccessPointType) one).getId();
					String aptTwoId = ((AccessPointType) two).getId();
					
					return aptOneId.equals(aptTwoId);
				}
				
				return one.equals(two);
				
			}
				
			return false;
		}
      });
      
      this.populateBodyInAccessPointViewer();

      bodyInAccessPointViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
        	 IStructuredSelection selection = (IStructuredSelection) event.getSelection();
             ApplicationType application = getApplication();
             if (selection != null && selection.getFirstElement() != null)
             {
            	AccessPointType apt = (AccessPointType) selection.getFirstElement();
                AttributeUtil.setAttribute(application, CamelConstants.CAT_BODY_IN_ACCESS_POINT, "String", apt.getId());
             }
             else
             {
            	 AttributeUtil.setAttribute(application, CamelConstants.CAT_BODY_IN_ACCESS_POINT, null);
             }
         }
         
      });
      
      bodyOutAccessPointLabel = FormBuilder.createLabel(composite, Camel_Messages.label_Body_Output_Access_Point);
      bodyOutAccessPointViewer = new ComboViewer(FormBuilder.createCombo(composite));
      bodyOutAccessPointViewer.setContentProvider(ArrayContentProvider.getInstance());
      bodyOutAccessPointViewer.setLabelProvider(new AccessPointLabelProvider());
      bodyOutAccessPointViewer.setComparer(new IElementComparer() {
		
		@Override
		public int hashCode(Object hashCode) {
			return hashCode.hashCode();
		}
		
		@Override
		public boolean equals(Object one, Object two) {
			
			if (one != null && two != null)
			{
				if (one instanceof AccessPointType && two instanceof AccessPointType)
				{
					String aptOneId = ((AccessPointType) one).getId();
					String aptTwoId = ((AccessPointType) two).getId();
					
					return aptOneId.equals(aptTwoId);
				}
				
				return one.equals(two);
				
			}
				
			return false;
		}
	});
      
      this.populateBodyOutAccessPointViewer();
      
      bodyOutAccessPointViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
        	 IStructuredSelection selection = (IStructuredSelection) event.getSelection();
             ApplicationType application = getApplication();
             if (selection != null && selection.getFirstElement() != null)
             {
            	 AccessPointType apt = (AccessPointType) selection.getFirstElement();
                AttributeUtil.setAttribute(application, CamelConstants.CAT_BODY_OUT_ACCESS_POINT, "String", apt.getId());
             }
             else
             {
            	 AttributeUtil.setAttribute(application, CamelConstants.CAT_BODY_OUT_ACCESS_POINT, null);
             }
         }
         
      });
      
      FormBuilder.createHorizontalSeparator(composite, 2).setVisible(false);
      FormBuilder.createHorizontalSeparator(composite, 2).setVisible(false);
      
      FormBuilder.createLabel(composite, Camel_Messages.label_additionalSpringBeanDef);
      additionalSpringBeanDefinitions = FormBuilder.createTextArea(composite, 2);

     
      // this is required for camel consumer application; it allows the application type
      // to behave different that the producer type
      if (getApplication().getType() != null
            && getApplication().getType().getId().equalsIgnoreCase("camelConsumerApplication")
            && AttributeUtil.getAttributeValue(getApplication(), CamelConstants.INVOCATION_PATTERN_EXT_ATT) == null)
      {
         invocationPatternViewer.setSelection(new StructuredSelection(Camel_Messages.label_Invocation_Pattern_Receive),
               true);
      }

      addPropertyPage(getApplication(), createAccessPointConfigurationElement());
      
      return composite;
   }

   private void setAttributeValue(String attrName, String attrType, Text control)
   {
      if (control instanceof Text)
      {
         AttributeUtil.setAttribute(extensibleElement, attrName, control.getText());
      }
   }

   private void getAttributeValue(String attrName, Text control)
   {
      String value;
      if ((value = AttributeUtil.getAttributeValue(extensibleElement, attrName)) != null)
      {
         control.setText(value);
      }
   }

   private String getExtendedAttributeValueForInvocationPattern()
   {
      return AttributeUtil.getAttributeValue(extensibleElement, CamelConstants.INVOCATION_PATTERN_EXT_ATT);
   }

   private String getProducerMethodName()
   {
      return AttributeUtil.getAttributeValue(extensibleElement, CamelConstants.PRODUCER_METHOD_NAME_ATT);
   }

   private void initializePropertyPage()
   {
      removePreferenceNodes(composePageId(SPI_NODE, CONSUMER_NODE), true);
      removePreferenceNodes(composePageId(SPI_NODE, PRODUCER_NODE), true);
      invocationTypeLabel.setVisible(false);
      invocationTypeViewer.getCombo().setVisible(false);
   }

   private void getInvocationPatternValue(ComboViewer invocationPatternViewer, ComboViewer invocationTypeViewer,
         IModelElement element)
   {
      if (getExtendedAttributeValueForInvocationPattern() == null
            && getExtendedAttributeValueForInvocationType() == null)
      {
         // backward compatibility
         if (getProducerMethodName() != null)
         {
            String producerMethodNameValue = getProducerMethodName();

            if (producerMethodNameValue.equalsIgnoreCase(CamelConstants.SEND_METHOD))
            {
               initSendAsynchronousApplication();
            }
            if (producerMethodNameValue.equalsIgnoreCase(CamelConstants.SEND_METHOD_WITH_HEADER))
               initSendAsynchronousApplication();
            if (producerMethodNameValue.equalsIgnoreCase(CamelConstants.SEND_RECEIVE_METHOD_WITH_HEADER))
               initSendReceiveSynchronousApplication();

         }
         else
         {
            // default is sendAsync
            initSendAsynchronousApplication();
            // if(element.)supportsMultipleAccessPoints=true if out accessPoint is
            // provided then set to sendReceive sync
            if (AttributeUtil.getAttributeValue(extensibleElement, CamelConstants.SUPPORT_MULTIPLE_ACCESS_POINTS) != null
                  && AttributeUtil.getAttributeValue(extensibleElement, CamelConstants.SUPPORT_MULTIPLE_ACCESS_POINTS)
                        .equalsIgnoreCase("true"))
            {
               boolean hasOutAccessPoints = false;
               if (element instanceof ApplicationTypeImpl)
               {
                  List<?> accessPoints = ((ApplicationTypeImpl) element).getAccessPoint();
                  if (accessPoints != null && !accessPoints.isEmpty())
                  {
                     for (int i = 0; i < accessPoints.size(); i++)
                     {
                        AccessPointType accessPoint = (AccessPointType) accessPoints.get(i);
                        if (accessPoint.getDirection().getLiteral().equalsIgnoreCase(Direction.OUT.getId())
                              || accessPoint.getDirection().getLiteral().equalsIgnoreCase(Direction.IN_OUT.getId()))
                        {
                        	hasOutAccessPoints = true;
                        	break;
                        }
                     }
                     if (hasOutAccessPoints)
                     {// if at least one Access Point is provided then the application
                      // should be send/Receive sync
                        initSendReceiveSynchronousApplication();
                     }
                  }
               }
            }
         }
      }
      else
      {

         if (getExtendedAttributeValueForInvocationPattern() == null)
         {
            if (getExtendedAttributeValueForInvocationType() != null
                  && getExtendedAttributeValueForInvocationType().equalsIgnoreCase(SYNCHRONOUS))
            {
               initSendReceiveSynchronousApplication();
            }
            else
            {
               initSendAsynchronousApplication();
            }
         }
         else
         {
            if (getExtendedAttributeValueForInvocationPattern().equalsIgnoreCase(SEND))
            {
               initSendAsynchronousApplication();
            }

            if (getExtendedAttributeValueForInvocationPattern().equalsIgnoreCase(SENDRECEIVE))
            {
               if (getExtendedAttributeValueForInvocationType().equalsIgnoreCase(ASYNCHRONOUS))
               {
            	   initSendReceiveAsynchronousApplication();
               }
               if (getExtendedAttributeValueForInvocationType().equalsIgnoreCase(SYNCHRONOUS))
               {
                  initSendReceiveSynchronousApplication();
               }
            }
            if (getExtendedAttributeValueForInvocationPattern().equalsIgnoreCase(RECEIVE))
            {
               initializePropertyPage();
               addPropertyPage(getApplication(), createConsumerConfigurationElement());
               invocationPatternViewer.setSelection(new StructuredSelection(
                     Camel_Messages.label_Invocation_Pattern_Receive), true);
               changeApplicationTypeToConsumer();
            }
         }
      }
   }

   private void initSendReceiveAsynchronousApplication()
   {
      initializePropertyPage();
      invocationTypeLabel.setVisible(true);
      invocationTypeViewer.getCombo().setVisible(true);
      invocationPatternViewer.setSelection(
            new StructuredSelection(Camel_Messages.label_Invocation_Pattern_SendReceive), true);
      invocationTypeViewer.setSelection(new StructuredSelection(Camel_Messages.label_Invocation_Type_Async), true);
      addPropertyPage(getApplication(), createConsumerConfigurationElement());
      addPropertyPage(getApplication(), createProducerConfigurationElement());
   }

   private void initSendReceiveSynchronousApplication()
   {
      initializePropertyPage();
      invocationTypeLabel.setVisible(true);
      invocationTypeViewer.getCombo().setVisible(true);
      invocationPatternViewer.setSelection(
            new StructuredSelection(Camel_Messages.label_Invocation_Pattern_SendReceive), true);
      invocationTypeViewer.setSelection(new StructuredSelection(Camel_Messages.label_Invocation_Type_Sync));
      addPropertyPage(getApplication(), createProducerConfigurationElement());
   }

   private void initSendAsynchronousApplication()
   {
      initializePropertyPage();
      invocationTypeLabel.setVisible(false);
      invocationTypeViewer.getCombo().setVisible(false);
      invocationPatternViewer.setSelection(new StructuredSelection(Camel_Messages.label_Invocation_Pattern_Send), true);
      addPropertyPage(getApplication(), createProducerConfigurationElement());
   }

   private void changeApplicationType(String appType)
   {
      ApplicationTypeType type = ModelUtils.getApplicationType(getApplication(), appType);
      ModelType model = ModelUtils.findContainingModel(getApplication());

      if (type == null)
      {
         SpiExtensionRegistry registry = SpiExtensionRegistry.instance();

         Map< ? , ? > extensions = registry.getExtensions(CarnotConstants.APPLICATION_TYPES_EXTENSION_POINT_ID);

         IConfigurationElement config = (IConfigurationElement) extensions.get(appType);

         if (config != null)

         {

            CreateMetaTypeCommand cmd = new CreateMetaTypeCommand(config,

            CarnotWorkflowModelPackage.eINSTANCE.getApplicationTypeType(),

            new EStructuralFeature[] {

            CarnotWorkflowModelPackage.eINSTANCE.getApplicationTypeType_Synchronous()});

            cmd.setParent(model);

            cmd.execute();

         }

         type = ModelUtils.getApplicationType(getApplication(), appType);
      }
      getApplication().setType(type);

   }

   private void changeApplicationTypeToProducer()
   {
      changeApplicationType("camelSpringProducerApplication");
   }

   private void changeApplicationTypeToConsumer()
   {
      changeApplicationType("camelConsumerApplication");
   }

   private String getExtendedAttributeValueForInvocationType()
   {
      return AttributeUtil.getAttributeValue(extensibleElement, CamelConstants.INVOCATION_TYPE_EXT_ATT);
   }

   private ConfigurationElement createAccessPointConfigurationElement()
   {   
	   String iconName = "{org.eclipse.stardust.modeling.transformation.modeling.externalwebapp}icons/message_transformation_application_icon.gif"; //$NON-NLS-1$
	   return  ConfigurationElement.createPageConfiguration(
			   "org.eclipse.stardust.modeling.integration.camel.ui.InputOutputAccessPointPropertyPage", //$NON-NLS-1$
			   Modeling_Messages.LBL_TYPED_ACCESS_POINTS, 
			   iconName, 
			   org.eclipse.stardust.modeling.integration.camel.ui.InputOutputAccessPointPropertyPage.class);
   }

   private ConfigurationElement createConsumerConfigurationElement()
   {
      return new ConsumerConfigurationElement();
   }

   private ConfigurationElement createProducerConfigurationElement()
   {
      return new ProducerConfigurationElement();
   }

   private void addPropertyPage(ApplicationType application, ConfigurationElement config)
   {
      if (getNode(composePageId(SPI_NODE, config.getAttribute(SpiConstants.ID))) == null)
      {
         CarnotPreferenceNode node = new CarnotPreferenceNode(config, new ModelElementAdaptable(new Class[] {
               IButtonManager.class, IModelElement.class, IModelElementNodeSymbol.class}, new Object[] {
               this, application}, getElement()), CarnotPreferenceNode.SPI_ELEMENT + 1);

         this.addNodeTo(SPI_NODE, node, null);
         refreshTree();
      }
   }

   private class ConsumerConfigurationElement extends ConfigurationElement
   {
      private Map attributes = new HashMap();

      public ConsumerConfigurationElement()
      {
         super(ConfigurationElement.CFG_PAGE);
         attributes.put(SpiConstants.ID, CONSUMER_NODE); //$NON-NLS-1$
         attributes.put(SpiConstants.NAME, "Consumer Route");//
         attributes.put(SpiConstants.ICON, null);
         attributes.put(SpiConstants.PROPERTY_PAGE_CLASS, CamelConsumerPropertyPage.class.getName());//
      }

      public Object createExecutableExtension(String propertyName) throws CoreException
      {
         return new CamelConsumerPropertyPage();
      }

      public String getAttribute(String name) throws InvalidRegistryObjectException
      {
         return (String) attributes.get(name);
      }

      public String[] getAttributeNames()
      {
         return (String[]) attributes.keySet().toArray(new String[attributes.size()]);
      }
   }

   private class ProducerConfigurationElement extends ConfigurationElement
   {
      private HashMap attributes = new HashMap();

      public ProducerConfigurationElement()
      {
         super(ConfigurationElement.CFG_PAGE);
         attributes.put(SpiConstants.ID, PRODUCER_NODE); //$NON-NLS-1$
         attributes.put(SpiConstants.NAME, "Producer Route");//
         attributes.put(SpiConstants.ICON, null);
         attributes.put(SpiConstants.PROPERTY_PAGE_CLASS, CamelProducerSpringBeanPropertyPage.class.getName());//
      }

      public Object createExecutableExtension(String propertyName) throws CoreException
      {
         return new CamelProducerSpringBeanPropertyPage();
      }

      public String getAttribute(String name) throws InvalidRegistryObjectException
      {
         return (String) attributes.get(name);
      }

      public String[] getAttributeNames()
      {
         return (String[]) attributes.keySet().toArray(new String[attributes.size()]);
      }
   }
   
   private class AccessPointLabelProvider extends LabelProvider
   {

		@Override
		public String getText(Object element) {
			
			if (element instanceof AccessPointType)
			{
				return ((AccessPointType) element).getName();
			}
			
			return super.getText(element);
		}
   }
   
}