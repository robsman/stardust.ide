package org.eclipse.bpmn2.modeler.runtime.stardust.utils.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerResourceImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDSchema;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.helpers.DefaultHandler;

public class SdbpmnModelerResourceImpl extends Bpmn2ModelerResourceImpl
{
	public SdbpmnModelerResourceImpl(URI uri)
	{
		super(uri);
	}

	@Override
	protected XMLLoad createXMLLoad() {
		return new XMLLoadImpl(createXMLHelper()) {
			Bpmn2ModelerXmlHandler handler;

			@Override
			protected DefaultHandler makeDefaultHandler() {
				handler = new SdbpmnModelerXmlHandler(resource, helper, options);
				return handler;
			}
		};
	}
	protected static class SdbpmnModelerXmlHandler  extends Bpmn2ModelerXmlHandler {
		EmbeddedXSDResourceImpl embeddedSchemas = null;
		int schemaIndex = 0;

		/**
		 * @param xmiResource
		 * @param helper
		 * @param options
		 */
		public SdbpmnModelerXmlHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options) {
			super(xmiResource, helper, options);
		}


		@Override
        public void endElement(String uri, String localName, String name) {
			int i = name.indexOf(":"); //$NON-NLS-1$
			if (i > 0) {
				// parse out prefix and localName from name
				// and check if this is an XSD Schema object
				String prefix = name.substring(0, i);
				localName = name.substring(i + 1);
				String namespace = helper.getNamespaceURI(prefix);
				if ("schema".equals(localName) && "http://www.w3.org/2001/XMLSchema".equals(namespace)) {
					// this is an embedded "xsd:schema" element: use an XSDResource to load
					// all embedded schema definitions the first time only:
					if (embeddedSchemas==null) {
						embeddedSchemas = new EmbeddedXSDResourceImpl(xmlResource.getURI());
						InputStream inputStream = null;
						try {
							URIConverter uriConverter = getURIConverter();
							Map<Object, Object> options = xmlResource.getDefaultLoadOptions();
							URI xsduri = xmlResource.getURI();
							inputStream = uriConverter.createInputStream(xsduri, options);
							embeddedSchemas.load(inputStream, options);
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}
					try {
			               Object peek = objects.peek();
						// If there are more than one embedded schemas, they will be
						// defined in the same order as the XSDResource contents, so
						// just use an index to fetch the next definition.
						EList<EObject> contents = embeddedSchemas.getContents();
						XSDSchema schema = (XSDSchema) contents.get(schemaIndex++);
						// pop the current object from the parser's EObject stack
						// (this should be an "AnyType" object) and replace it with
						// the XSDSchema object.
						((InternalEObject) schema).eSetResource((Resource.Internal) xmlResource, null);

						types.pop();
//						types.push(XSDPackage.eINSTANCE.getXSDSchema());

						EObject popEObject = objects.popEObject();
						EReference eContainmentFeature = popEObject.eContainmentFeature();
						EObject eContainer = popEObject.eContainer();
						try {
							((Collection<EObject>)eContainer.eGet(eContainmentFeature)).remove(popEObject);
							((Collection<EObject>)eContainer.eGet(eContainmentFeature)).add(schema);
						} catch (Exception e) {}

						if (schemaIndex >= contents.size()) schemaIndex = 0;
						return;
					}
					catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}
            super.endElement(uri, localName, name);
        }
	}

//		@Override
//        public void endElement(String uri, String localName, String name) {
//			int i = name.indexOf(":"); //$NON-NLS-1$
//			if (i > 0) {
//				// parse out prefix and localName from name
//				// and check if this is an XSD Schema object
//				String prefix = name.substring(0, i);
//				localName = name.substring(i + 1);
//				String namespace = helper.getNamespaceURI(prefix);
//				if ("schema".equals(localName) && "http://www.w3.org/2001/XMLSchema".equals(namespace)) {
//					// this is an embedded "xsd:schema" element: use an XSDResource to load
//					// all embedded schema definitions the first time only:
//					if (embeddedSchemas==null) {
//						embeddedSchemas = new EmbeddedXSDResourceImpl(xmlResource.getURI());
//						InputStream inputStream = null;
//						try {
//							URIConverter uriConverter = getURIConverter();
//							Map<Object, Object> options = xmlResource.getDefaultLoadOptions();
//							URI xsduri = xmlResource.getURI();
//							inputStream = uriConverter.createInputStream(xsduri, options);
//							embeddedSchemas.load(inputStream, options);
//						} catch (IOException exception) {
//							exception.printStackTrace();
//						}
//					}
//					try {
//			               Object peek = objects.peek();
//							System.out
//							.println("SdbpmnModelerResourceImpl.SdbpmnModelerXmlHandler.endElement() peek " + peek);
//						   if (!(peek instanceof ExtensionAttributeValue)) {
//							   peek = ((EObject)peek).eContainer();
//						   }
//			               if (peek instanceof ExtensionAttributeValue)
//			               {
//			            	   System.out
//									.println("SdbpmnModelerResourceImpl.SdbpmnModelerXmlHandler.endElement() ExtensionAttributeValue");
//								elements.pop();
////								super.endElement(uri, localName, namespace);
//
//
//			            	   EList<EObject> contents = embeddedSchemas.getContents();
//			            	   XSDSchema schema = (XSDSchema) contents.get(schemaIndex++);
//			            	   System.out
//									.println("SdbpmnModelerResourceImpl.SdbpmnModelerXmlHandler.endElement() schema: " + schema);
//			            	   setFeatureValue(objects.peekEObject(), Bpmn2Package.Literals.BASE_ELEMENT__EXTENSION_VALUES, schema);
////			                  ((InternalEObject) schema).eSetResource((Resource.Internal) xmlResource, null);
//			                  // TODO ((BaseElement) peek).setSchema(schema);
////			                  ExtensionHelper.setExtensionValue(
////			                        (BaseElement) ((ExtensionAttributeValue) peek).eContainer(), "schema",
////			                        javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI, schema); // W3C_XML_SCHEMA_NS_URI, schema);
//			                  if (schemaIndex >= contents.size()) schemaIndex = 0;
//								objects.pop();
//								types.pop();
//								mixedTargets.push( null);
//
//			               } else {
//			            	   System.out
//									.println("SdbpmnModelerResourceImpl.SdbpmnModelerXmlHandler.endElement() peek is no ext-attribute-value");
//			               }
//
//						/*
//						// If there are more than one embedded schemas, they will be
//						// defined in the same order as the XSDResource contents, so
//						// just use an index to fetch the next definition.
//						EList<EObject> contents = embeddedSchemas.getContents();
//						XSDSchema schema = (XSDSchema) contents.get(schemaIndex++);
//						// pop the current object from the parser's EObject stack
//						// (this should be an "AnyType" object) and replace it with
//						// the XSDSchema object.
//						((InternalEObject) schema).eSetResource((Resource.Internal) xmlResource, null);
//						objects.pop();
//						//objects.push(schema);
//
//						ExtendedMetaData metadata = XmlExtendedMetadata.INSTANCE;
//					    EStructuralFeature extensionElementType = metadata.demandFeature("http://www.w3.org/2001/XMLSchema",
//					               "schema", true, true);
//						types.pop();
//						//types.push(extensionElementType);
//						EObject popEObject = objects.popEObject();
//						setFeatureValue(popEObject, extensionElementType, schema);
////						types.pop();
////						types.push(schema.eContainmentFeature());
//
////						types.push("object"); //XSDPackage.eINSTANCE.getXSDSchema());
//
//						// (not sure if this is needed - double check!)
////						FeatureMap pop = mixedTargets.peek();
////						pop.add(extensionElementType, schema);
//						mixedTargets.push( null);*/
//					}
//					catch (Exception exception) {
//						exception.printStackTrace();
//					}
//				}
//			}
//            super.endElement(uri, localName, name);
//        }
//	}

	@Override
	protected XMLSave createXMLSave()
	{
		return new SdbpmnModelerXmlSave(createXMLHelper());
	}

	public class SdbpmnModelerXmlSave extends Bpmn2ModelerXMLSave {

		public SdbpmnModelerXmlSave(XMLHelper helper) {
			super(helper);
		}

		protected void saveElement(EObject o, EStructuralFeature f)
		{
//			final EObject ob = o;
			if (o instanceof XSDComponent)
			{
//				Display.getDefault().asyncExec( new Runnable() {
//					@Override
//					public void run() {
//						TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(ob.eResource());
//						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
//							@Override
//							protected void doExecute() {
								XSDComponent component = (XSDComponent) o;
								component.updateElement();
								if (toDOM)
								{
									currentNode.appendChild(document.importNode(component.getElement(), true));
								}
								else
								{
									Element element = component.getElement();
									saveDomElement(element);
								}
//							}
//						});
//					}
//				});
				return;
			}

			super.saveElement(o, f);
		}

		private void saveDomElement(Element element)
		{
			boolean empty = true;
			doc.startElement(element.getTagName());
			NamedNodeMap attributes = element.getAttributes();
			for (int i = 0; i < attributes.getLength(); i++ )
			{
				Attr attribute = (Attr) attributes.item(i);
				doc.addAttribute(attribute.getName(), attribute.getValue());
			}
			NodeList list = element.getChildNodes();
			for (int i = 0; i < list.getLength(); i++ )
			{
				Node node = list.item(i);
				if (node instanceof Element)
				{
					empty = false;
					saveDomElement((Element) node);
				}
				else if (node instanceof Text)
				{
					empty = false;
					doc.addText(stripWhitespace(((Text) node).getData()));
				}
				else if (node instanceof CDATASection)
				{
					empty = false;
					doc.addCDATA(((CDATASection) node).getData());
				}
				else if (node instanceof Comment)
				{
					empty = false;
					doc.addComment(((Comment) node).getData());
				}
				else if (node instanceof Entity)
				{
					// ignore
				}
				else if (node instanceof ProcessingInstruction)
				{
					// ignore
				}
			}
			if (empty)
			{
				doc.endEmptyElement();
			}
			else
			{
				doc.endElement();
			}
		}

		private String stripWhitespace(String value)
		{
			int begin = 0;
			int end = value.length();
			while (end > begin && Character.isWhitespace(value.charAt(end - 1)))
			{
				end-- ;
			}
			if (end < 0)
			{
				return ""; //$NON-NLS-1$
			}
			while (begin < end && Character.isWhitespace(value.charAt(begin)))
			{
				begin++ ;
			}
			return value.substring(begin, end);
		}
	}

}
