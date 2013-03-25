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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.io.StringReader;
import java.net.URL;
import java.util.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.SAXXMLHandler;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.core.model.beans.ModelBean;
import org.eclipse.stardust.engine.core.model.beans.XMLConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionFactory;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDParser;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CwmXmlHandler extends SAXXMLHandler
{
   private static final String STOPPER = "__stopper__"; //$NON-NLS-1$

   private static final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;
   private static final XpdlPackage XPDL_PKG = XpdlPackage.eINSTANCE;

   // MUST be kept in ascending order
   private static final String[] SCHEMA_KEYWORDS_45 = {
      "complexType", "element", "enumeration", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      "restriction", "schema", "sequence", "simpleType"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

   private boolean isSchemaKeyword45(String name)
   {
      return Arrays.binarySearch(SCHEMA_KEYWORDS_45, name) >= 0;
   }

   public CwmXmlHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options)
   {
      super(xmiResource, helper, options);
      hrefAttribute = WorkflowModelManager.PROXY_ATT;
      xmiResource.eAdapters().add(new SchemaLocatorAdapter());
   }

   protected EPackage getPackageForURI(String uriString)
   {
      EPackage ePackage = super.getPackageForURI(uriString);
      return ePackage == null ? CarnotWorkflowModelPackage.eINSTANCE : ePackage;
   }

   protected void setValueFromId(EObject object, EReference eReference, String ids)
   {
      // overriding default behaviour to allow for IDs with spaces
      if ((eReference.getEType() instanceof EClass)
            && (CWM_PKG.getIIdentifiableElement().isSuperTypeOf((EClass) eReference.getEType()))
            && (null != eReference.getEAnnotation(ElementIdRefs.ANNOTATION_ID)))
      {
         boolean mustAdd = deferIDREFResolution;

         int size = 0;
         int position = 0;

         String id = ids;
         if ( !deferIDREFResolution)
         {
            EReference eOpposite = eReference.getEOpposite();
            mustAdd = eOpposite == null || eOpposite.isTransient()
                  || eReference.isMany();
         }

         if (mustAdd)
         {
            if (size == capacity)
               growArrays();

            identifiers[size] = id;
            positions[size] = position;
            ++size;
         }
         ++position;

         if (size <= REFERENCE_THRESHOLD)
         {
            for (int i = 0; i < size; i++ )
            {
               SingleReference ref = new SingleReference(object, eReference,
                     identifiers[i], positions[i], getLineNumber(), getColumnNumber());
               forwardSingleReferences.add(ref);
            }
         }
         else
         {
            // TODO
         }
      }
      else
      {
         super.setValueFromId(object, eReference, ids);
      }
   }

   protected void handleForwardReferences(boolean isEndDocument)
   {
      if (isEndDocument)
      {
         // resolve pending elements by ID
         for (Iterator<SingleReference> i = forwardSingleReferences.iterator(); i.hasNext();)
         {
            SingleReference ref = i.next();
            if (doResolveReference(ref))
            {
               i.remove();
            }
         }
      }

      forwardSingleReferences.clear();

      super.handleForwardReferences(isEndDocument);
   }

   protected boolean doResolveReference(SingleReference ref)
   {
      boolean resolved = false;
      EStructuralFeature feature = ref.getFeature();
      EClassifier eType = feature.getEType();
      if ((eType instanceof EClass)
            && (CWM_PKG.getIIdentifiableElement().isSuperTypeOf((EClass) eType)
             || XPDL_PKG.getExternalPackage().isSuperTypeOf((EClass) eType)))
      {
         EAnnotation aEIdRef = feature.getEAnnotation(ElementIdRefs.ANNOTATION_ID);
         if (null != aEIdRef)
         {
            String scope = null;
            String refType = null;
            EMap<String, String> details = aEIdRef.getDetails();
            for (Map.Entry<String, String> detail : details)
            {
               if (scope == null && ElementIdRefs.ATTR_SCOPE.equals(detail.getKey()))
               {
                  scope = detail.getValue();
               }
               if (refType == null && ElementIdRefs.ATTR_REF_TYPE.equals(detail.getKey()))
               {
                  refType = detail.getValue();
               }
            }

            EObject resolvedTarget = null;

            if (ElementIdRefs.SCOPE_PROCESS.equals(scope))
            {
               ProcessDefinitionType proc = ModelUtils.findContainingProcess(ref.getObject());
               if (null != proc)
               {
                  resolvedTarget = resolveReferenceByElementId(ref, null,
                        proc.eContents(), refType);
               }
            }
            else if (ElementIdRefs.SCOPE_MODEL.equals(scope))
            {
               ModelType model = ModelUtils.findContainingModel(ref.getObject());
               String refId = ref.getValue().toString();
               if (null != model)
               {
                  /*int ix = refId.indexOf('#');
                  if (ix > 0)
                  {
                     URI target = URI.createURI(refId.substring(0, ix));
                     Resource res = model.eResource();
                     ResourceSet set = res.getResourceSet();
                     target = target.resolve(res.getURI());
                     Resource targetRes = set.getResource(target, true);
                     if (targetRes != null)
                     {
                        ModelType targetModel = getModel(targetRes);
                        if (targetModel != null)
                        {
                           model = targetModel;
                           refId = refId.substring(ix + 1);
                        }
                     }
                  }*/
                  List<? extends EObject> candidates = null;
                  if (CWM_PKG.getIdRef_PackageRef() == feature)
                  {
                     ExternalPackages packages = model.getExternalPackages();
                     if (packages != null)
                     {
                        candidates = packages.getExternalPackage();
                     }
                  }
                  else
                  {
                     candidates = model.eContents();
                  }
                  if (candidates != null)
                  {
                     resolvedTarget = resolveReferenceByElementId(ref, refId, candidates, refType);
                  }
               }
            }
            else if (ElementIdRefs.SCOPE_POOL.equals(scope))
            {
               PoolSymbol pool = ModelUtils.findContainingPool(ref.getObject());
               if (null != pool)
               {
                  // (fh) the pool itself may be referenced, or the content
                  if (ref.getValue().equals(getId(pool, refType)))
                  {
                     resolvedTarget = pool;
                  }
                  else
                  {
                     resolvedTarget = resolveReferenceByElementId(ref, null,
                        pool.eContents(), refType);
                  }
               }
            }
            else
            {
               // TODO unsupported scope
            }

            if (null != resolvedTarget)
            {
               setFeatureValue(ref.getObject(), feature, resolvedTarget,
                     ref.getPosition());
               resolved = true;
            }
            else
            {
               // TODO
            }
         }
      }
      else if ((eType instanceof EClass)
            && (CWM_PKG.getIModelElement().isSuperTypeOf((EClass) eType)))
      {
         EObject obj = xmlResource.getEObject((String) ref.getValue());

         if (null != obj)
         {
            setFeatureValue(ref.getObject(), feature, obj,
                  ref.getPosition());
            resolved = true;
         }
      }
      return resolved;
   }

   protected static EObject resolveReferenceByElementId(SingleReference ref, String refId,
         List<? extends EObject> candidates, String refType)
   {
      EObject result = null;
      if (refId == null)
      {
         refId = ref.getValue().toString();
      }
      for (EObject content : candidates)
      {
         if (ref.getFeature().getEType().isInstance(content))
         {
            if (refId.equals(getId(content, refType)))
            {
               result = content;
               break;
            }
         }
      }

      return result;
   }

   private static String getId(EObject content, String refType)
   {
      String id = null;
      if (content instanceof ExternalPackage)
      {
         id = ((ExternalPackage) content).getId();
      }
      else if (refType == null || ElementIdRefs.REF_TYPE_ID.equals(refType))
      {
         id = ((IIdentifiableElement) content).getId();
      }
      else if (content instanceof IModelElement && ElementIdRefs.REF_TYPE_OID.equals(refType))
      {
         id = Long.toString(((IModelElement) content).getElementOid());
      }
      else if (content.eIsProxy())
      {
         id = ((EObjectImpl) content).eProxyURI().toString();
      }
      return id;
   }

   protected void handleObjectAttribs(EObject obj)
   {
      super.handleObjectAttribs(obj);

      if (obj instanceof IModelElement)
      {
         IModelElement element = (IModelElement) obj;

         if (element.isSetElementOid())
         {
            xmlResource.setID(obj, Long.toString(element.getElementOid()));
         }
      }
   }

   private boolean inSchema = false;
   private MyXSDParser xsdParser = new MyXSDParser();
   private Stack<Map<String, String>> namespaces = new Stack<Map<String, String>>();
   private Map<String, String> current = null;
   private int schemaElementCount = 0;

   public void startPrefixMapping(String prefix, String uri)
   {
      if (current == null)
      {
         current = new HashMap<String, String>();
      }
      current.put(prefix,  uri);
      if (!(objects.peek() instanceof SchemaTypeType))
      {
         super.startPrefixMapping(prefix, uri);
      }
   }

   // TODO: optimize namespace handling
   public void startElement(String uri, String localName, String name)
   {
      namespaces.push(current == null ? CollectionUtils.<String, String>newMap() : current);
      String prefix = getPrefix(name);
      boolean hasNamespace = uri != null && uri.length() > 0;
      if (!hasNamespace)
      {
         if (prefix.length() > 0)
         {
            localName = name.substring(prefix.length() + 1);
         }
         uri = getURI(prefix, true);
      }
      if (isSchemaKeyword45(name) && isXpdlNamespace(uri))
      {
         uri = XMLResource.XML_SCHEMA_URI;
      }
      if (!inSchema && isSchemaElement(uri, prefix))
      {
         startXsdDocument();
      }
      if (inSchema)
      {
         schemaElementCount++;
         elements.push(name);
         try
         {
            xsdParser.startElement(uri, localName, name, attribs);
            if (current != null)
            {
               for (Map.Entry<String, String> entry : current.entrySet())
               {
                  String key = entry.getKey();
                  if (!key.equals(STOPPER))
                  {
                     xsdParser.declareNamespace(entry.getValue(), key);
                  }
               }
            }
            if (hasNamespace)
            {
               String searchedUri = getURI(prefix, false);
               if (uri != null && (searchedUri == null || !searchedUri.equals(uri)))
               {
                  xsdParser.declareNamespace(uri, prefix);
               }
               namespaces.peek().put(prefix, uri);
            }
         }
         catch (Throwable e)
         {
            // TODO: propagate error
            e.printStackTrace();
         }
         return;
      }
      super.startElement(uri, localName, name);
      current = null;
   }

   private void startXsdDocument()
   {
      inSchema = true;
      namespaces.peek().put(STOPPER, STOPPER);
      xsdParser.startDocument();
   }

   private String getPrefix(String name)
   {
      int ix = name.indexOf(':');
      return ix < 0 ? "" : name.substring(0, ix); //$NON-NLS-1$
   }

   private boolean isSchemaElement(String uri, String prefix)
   {
      return XMLResource.XML_SCHEMA_URI.equals(uri.length() == 0 ? getURI(prefix, true) : uri);
   }

   private boolean isXpdlNamespace(String uri)
   {
      return XpdlPackage.eNS_URI.equals(uri);
   }

   private String getURI(String prefix, boolean askHelper)
   {
      for (int i = namespaces.size() - 1; i >= 0; i--)
      {
         Map<String, String> current = namespaces.get(i);
         String uri = current.get(prefix);
         if (uri != null)
         {
            return uri;
         }
         if (current.get(STOPPER) == STOPPER)
         {
            break;
         }
      }
      if (!askHelper)
      {
         return null;
      }
      String uri = helper.getURI(prefix);
      return uri == null ? "" : uri; //$NON-NLS-1$
   }

   @Override
   public void endPrefixMapping(String prefix)
   {
      if (!(objects.peek() instanceof SchemaTypeType))
      {
         super.endPrefixMapping(prefix);
      }
   }

   public void endElement(String uri, String localName, String name)
   {
      String prefix = getPrefix(name);
      boolean hasNamespace = uri != null && uri.length() > 0;
      if (!hasNamespace)
      {
         if (prefix.length() > 0)
         {
            localName = name.substring(prefix.length() + 1);
         }
         uri = getURI(prefix, true);
      }
      namespaces.pop();
      if (isSchemaKeyword45(name) && isXpdlNamespace(uri))
      {
         uri = XMLResource.XML_SCHEMA_URI;
      }
      if (inSchema)
      {
         schemaElementCount--;
         elements.pop();
         try
         {
            xsdParser.endElement(uri, localName, name);
            if (schemaElementCount == 0)
            {
               inSchema = false;
               xsdParser.endDocument();
               Object peek = objects.peek();
               if (peek instanceof SchemaTypeType)
               {
                  XSDSchema schema = xsdParser.getSchema();
                  ((InternalEObject) schema).eSetResource((Resource.Internal) xmlResource, null);
                  ((SchemaTypeType) peek).setSchema(schema);
               }
               else if (peek instanceof ExtendedAttributeType
                     && ExtendedAttributeType.EXTERNAL_ANNOTATIONS_NAME.equals(((ExtendedAttributeType) peek).getName()))
               {
                  ExtendedAnnotationType annotation = ExtensionFactory.eINSTANCE.createExtendedAnnotationType();
                  annotation.getSchema().updateElement();
                  Element source = xsdParser.getDocument().getDocumentElement();
                  Element imported = (Element) annotation.getSchema().getDocument().importNode(source, true);
                  annotation.getSchema().getElement().appendChild(imported);
                  annotation.setElement(imported);
                  ((ExtendedAttributeType) peek).setExtendedAnnotation(annotation);
                  ((ExtendedAttributeType) peek).getMixed().clear();
                  ((ExtendedAttributeType) peek).getGroup().clear();
                  ((ExtendedAttributeType) peek).getAny().clear();
               }
            }
         }
         catch (Throwable e)
         {
            // TODO: propagate error
            e.printStackTrace();
         }
         return;
      }
      super.endElement(uri, localName, name);
   }

   public void startEntity(String name)
   {
      if (inSchema)
      {
         xsdParser.startEntity(name);
         return;
      }
      super.startEntity(name);
   }

   public void endEntity(String name)
   {
      if (inSchema)
      {
         xsdParser.endEntity(name);
         return;
      }
      super.endEntity(name);
   }

   public void comment(char[] ch, int start, int length)
   {
      if (inSchema)
      {
         try
         {
            xsdParser.comment(ch, start, length);
         }
         catch (SAXException e)
         {
            // TODO: propagate error
            e.printStackTrace();
         }
         return;
      }
      super.comment(ch, start, length);
   }

   public void startCDATA()
   {
      if (inSchema)
      {
         xsdParser.startCDATA();
         return;
      }
      super.startCDATA();
   }

   public void endCDATA()
   {
      if (inSchema)
      {
         xsdParser.endCDATA();
         return;
      }
      super.endCDATA();
   }

   public InputSource resolveEntity(String publicId, String systemId) throws SAXException
   {
      if (null != systemId)
      {
         if (XMLConstants.WORKFLOWMODEL_30_DTD_URL.equals(systemId)
               || XMLConstants.WORKFLOWMODEL_31_DTD_URL.equals(systemId)
               || systemId.endsWith(XMLConstants.DTD_NAME))
         {
            // strip old DTD (not doing so would include the DTD inline on save)
            return new InputSource(new StringReader("")); //$NON-NLS-1$
         }
         else if (XMLConstants.WORKFLOWMODEL_31_XSD_URL.equals(systemId)
               || systemId.endsWith(XMLConstants.WORKFLOWMODEL_XSD))
         {
            try
            {
               URL xsdUrl = ModelBean.class.getResource(XMLConstants.WORKFLOWMODEL_XSD);
               if (null != xsdUrl)
               {
                  return new InputSource(xsdUrl.openStream());
               }
            }
            catch (Exception e)
            {
               // e.printStackTrace();
            }
         }
      }
      if (inSchema)
      {
         return xsdParser.resolveEntity(publicId, systemId);
      }
      return super.resolveEntity(publicId, systemId);
   }

   public void processingInstruction(String target, String data)
   {
      if (inSchema)
      {
         xsdParser.processingInstruction(target, data);
         return;
      }
      super.processingInstruction(target, data);
   }

   public void characters(char[] ch, int start, int length)
   {
      if (inSchema)
      {
         // sanity size control
         if (ch != null && start >= 0 && length > 0 && ch.length >= start + length)
         {
            try
            {
               while (start < length && Character.isWhitespace(ch[start]))
               {
                  start++;
                  length--;
               }
               while (length > 0 && Character.isWhitespace(ch[start + length -1]))
               {
                  length--;
               }
               if (length > 0)
               {
                  xsdParser.characters(ch, start, length);
               }
            }
            catch (Throwable e)
            {
               // TODO: propagate error
               e.printStackTrace();
            }
         }
         return;
      }
      super.characters(ch, start, length);
   }

   class MyXSDParser extends XSDParser
   {
      public MyXSDParser()
      {
         super(null);
      }

      public void declareNamespace(String uri, String prefix)
      {
         String attributeURI = XSDConstants.XMLNS_URI_2000;
         String attributeQName = XMLResource.XML_NS;
         if (prefix.length() > 0)
         {
            attributeQName = attributeQName + ':' + prefix;
         }
         String attributeValue = uri;
         element.setAttributeNS(attributeURI, attributeQName, attributeValue);
      }
   }

   public ModelType getModel(Resource resource)
   {
      ModelType model = null;
      EList<EObject> l = resource.getContents();
      Iterator<EObject> i = l.iterator();
      while (i.hasNext())
      {
         EObject o = i.next();
         if (o instanceof DocumentRoot)
         {
            model = ((DocumentRoot) o).getModel();
         }
      }
      if (model != null)
      {
         // resolve string-id references in attributes
         ModelUtils.resolve(model, model);
      }
      return model;
   }
}
