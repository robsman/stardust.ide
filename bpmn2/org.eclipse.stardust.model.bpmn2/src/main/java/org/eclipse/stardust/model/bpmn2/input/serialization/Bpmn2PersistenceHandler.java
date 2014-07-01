package org.eclipse.stardust.model.bpmn2.input.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.bpmn2.util.OnlyContainmentTypeInfo;
import org.eclipse.bpmn2.util.XmlExtendedMetadata;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;

public class Bpmn2PersistenceHandler
{
	private static Logger log = Logger.getLogger(Bpmn2PersistenceHandler.class);

	public Bpmn2Resource loadModel(String contentName, InputStream modelContent)
	{
		try
		{
			//            DirectStreamsURIHandler streamsUriHandler = new DirectStreamsURIHandler();
			//            URI resourceStreamUri = streamsUriHandler.registerInputStream(modelContent, contentName);
			URI resourceStreamUri = getStreamUri(contentName);

			ResourceSet context = new ResourceSetImpl();
			context.getResourceFactoryRegistry()
			.getProtocolToFactoryMap()
			.put(resourceStreamUri.scheme(), new Bpmn2ResourceFactoryImpl()
			{
				@Override
				public Resource createResource(URI uri)
				{
					StardustBpmn2XmlResource result = new StardustBpmn2XmlResource(uri);

					ExtendedMetaData extendedMetadata = new XmlExtendedMetadata();
					result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetadata);
					result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetadata);

					result.getDefaultSaveOptions().put(XMLResource.OPTION_SAVE_TYPE_INFORMATION,
							new OnlyContainmentTypeInfo());

					result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE,
							Boolean.TRUE);
					result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE,
							Boolean.TRUE);

					result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);

					result.getDefaultSaveOptions().put(XMLResource.OPTION_ELEMENT_HANDLER,
							new ElementHandlerImpl(true));

					result.getDefaultSaveOptions().put(XMLResource.OPTION_ENCODING, "UTF-8");

					return result;
				}
			});

			Bpmn2Resource bpmnModel = (Bpmn2Resource) context.createResource(resourceStreamUri);

			// must pass stream directly as load(options) will close the stream internally
			bpmnModel.load(modelContent, getDefaultXmlLoadOptions());
			return (Bpmn2Resource)bpmnModel;
			//            for (EObject eObj : bpmnModel.getContents())
				//            {
			//               if ((eObj instanceof DocumentRoot)
			//                     && (null != ((DocumentRoot) eObj).getDefinitions()))
			//               {
			//                  DocumentRoot rootElement = (DocumentRoot) eObj;
			//                  Definitions definitions = rootElement.getDefinitions();
			//
			//                  if ("ADONIS".equals(definitions.getExporter()))
			//                  {
			//                     new AdonisImporter().fixModel(definitions);
			//                  }
			//
			//                  definitions.setExporter("Eclipse Stardust");
			//                  definitions.setExporterVersion(CurrentVersion.getVersionName());
			//
			//                  String modelUuid;
			//                  try
			//                  {
			//                     // test if current ID already is a UUID ...
			//                     UUID.fromString( !isEmpty(definitions.getId()) ? definitions.getId() : "");
			//                     modelUuid = definitions.getId();
			//                  }
			//                  catch (IllegalArgumentException iae1)
			//                  {
			//                     try
			//                     {
			//                        // ---nope, test if there is a suitable extension attribute already ...
			//                        String stardustUuid = getExtensionAttribute(definitions, ModelerConstants.UUID_PROPERTY);
			//                        UUID.fromString( !isEmpty(stardustUuid) ? stardustUuid : "");
			//                        modelUuid = stardustUuid;
			//                     }
			//                     catch (IllegalArgumentException iae2)
			//                     {
			//                        // ... nope
			//                        modelUuid = Bpmn2Utils.createInternalId();
			//                        setExtensionAttribute(definitions, ModelerConstants.UUID_PROPERTY, modelUuid);
			//                     }
			//                  }
			//
			//                  String modelName = definitions.getName();
			//                  if (isEmpty(modelName))
			//                  {
			//                     modelName = contentName;
			//                     if (modelName.endsWith(".bpmn"))
			//                     {
			//                        modelName = modelName.substring(0, modelName.length() - ".bpmn".length());
			//                     }
			//                  }
			//
			//                  return new ModelDescriptor<Definitions>(modelUuid, modelName,
			//                        definitions);
			//               }
			//            }
		}
		catch (IOException ioe)
		{
			log.warn("Failed loading BPMN2 model.", ioe);
		}
		return null;
	}

	public static URI getStreamUri(String contentName) {
		String schema = "istream";
		String auth = "";
		String fragment = null;
		String device = null;
		String query = null;
		contentName = contentName.replaceAll(" ", "_");
		return URI.createHierarchicalURI(schema, auth, device, new String[] {contentName}, query, fragment);
	}

	//   @Override
	//   public String generateDefaultFileName(Definitions model)
	//   {
	//      if ( !isEmpty(model.getName()))
	//      {
	//         return model.getName() + ".bpmn";
	//      }
	//      else if ( !isEmpty(model.getId()))
	//      {
	//         return model.getId() + ".bpmn";
	//      }
	//      else
	//      {
	//         return Bpmn2Utils.createInternalId() + ".bpmn";
	//      }
	//   }
	//
	//   public void saveModel(Definitions model, OutputStream modelContent)
	//   {
	//      try
	//      {
	//         DirectStreamsURIHandler streamsUriHandler = new DirectStreamsURIHandler();
	//         URI resourceStreamUri = streamsUriHandler.registerOutputStream(modelContent);
	//
	//         ResourceSet context = new ResourceSetImpl();
	//         context.getResourceFactoryRegistry()
	//               .getProtocolToFactoryMap()
	//               .put(resourceStreamUri.scheme(), new Bpmn2ResourceFactoryImpl());
	//
	//         Bpmn2Resource bpmnModel;
	//         if (null != model.eResource())
	//         {
	//            bpmnModel = (Bpmn2Resource) model.eResource();
	//         }
	//         else
	//         {
	//            bpmnModel = (Bpmn2Resource) context.createResource(resourceStreamUri);
	//            bpmnModel.getContents().add(
	//                  (null == model.eContainer()) ? model : model.eContainer());
	//         }
	//
	//         // must pass stream directly as save(options) will close the stream internally
	//         bpmnModel.save(modelContent, getDefaultXmlSaveOptions());
	//      }
	//      catch (IOException ioe)
	//      {
	//         trace.warn("Failed saving BPMN2 model.", ioe);
	//      }
	//   }
	//
	//   @Override
	//   public void saveDeployableModel(Definitions model, OutputStream modelContent)
	//         throws IOException
	//   {
	//      try
	//      {
	//         // TODO transform BPMN2 into XPDL
	//         ModelExporter xpdlExporter = (ModelExporter) Reflect.createInstance("org.eclipse.stardust.ui.web.modeler.bpmn2.export.XpdlModelExporter");
	//         if (null != xpdlExporter)
	//         {
	//            xpdlExporter.exportModel(model, modelContent);
	//         }
	//      }
	//      catch (Exception e)
	//      {
	//         throw new RuntimeException("Failed transforming BPMN2 model.", e);
	//      }
	//   }

	private static Map<Object, Object> getDefaultXmlLoadOptions()
	{
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS, Boolean.TRUE);
		options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		return options;
	}

	private static Map<Object, Object> getDefaultXmlSaveOptions()
	{
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS, Boolean.TRUE);
		options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
		options.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, Boolean.TRUE);
		return options;
	}
}
