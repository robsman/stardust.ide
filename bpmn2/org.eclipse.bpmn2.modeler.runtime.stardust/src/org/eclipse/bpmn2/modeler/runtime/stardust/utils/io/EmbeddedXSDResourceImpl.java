/**
 * <copyright>
 *
 * Copyright (c) 2002 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 * IBM - Initial API and implementation
 *
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.utils.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.impl.XSDSchemaImpl;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDParser;
import org.eclipse.xsd.util.XSDResourceImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * The <b>Resource</b> implementation for a WSDL resource.
 */
public class EmbeddedXSDResourceImpl extends XSDResourceImpl {
	protected Document document;

	public EmbeddedXSDResourceImpl() {
		super();
	}

	public EmbeddedXSDResourceImpl(URI uri) {
		super(uri);
	}

	protected void doSave(OutputStream os, Map options) throws IOException {
		if (document != null) {
			doSerialize(os, document, options == null ? null : (String) options.get(XSD_ENCODING));
		}
	}

	/**
	 * Loads a new {@link XSDResourceImpl} into the resource set.
	 * 
	 * @param resourceSet the resource set to hold the new resource.
	 * @param uri the URI of the new resource.
	 * @param inputStream the contents of the new resource.
	 * @param options any options to influence loading behavior.
	 * @return a new XSDResourceImpl.
	 */
	protected void doLoad(InputStream inputStream, Map options) throws IOException {
		// This pattern avoids loading the IProgressMonitor class when there is
		// no progress monitor.
		// This is important for stand-alone execution to work correctly.
		//
		IProgressMonitor progressMonitor = null;
		Object monitor = options == null ? null : options.get("XSD_PROGRESS_MONITOR");
		if (monitor != null) {
			progressMonitor = (IProgressMonitor) monitor;
			progressMonitor.setTaskName(XSDPlugin.INSTANCE.getString("_UI_ResourceLoad_progress "));
			progressMonitor.subTask(getURI().toString());
		}

		XSDParser xsdParser = new XSDParser();
		try {
			if (options != null && Boolean.TRUE.equals(options.get("XSD_TRACK_LOCATION"))) {
				xsdParser.parse(inputStream);
				document = xsdParser.getDocument();
			} else {
				document = getDocument(inputStream, xsdParser);
			}

			if (xsdParser.getEncoding() != null) {
				getDefaultSaveOptions().put(XSD_ENCODING, xsdParser.getEncoding());
			}

			if (document != null && document.getDocumentElement() != null) {
				ResourceSet globalResourceSet = XSDSchemaImpl.getGlobalResourceSet();
				Object oldMonitor = globalResourceSet.getLoadOptions().get("XSD_PROGRESS_MONITOR ");
				try {
					XSDSchemaImpl.getGlobalResourceSet().getLoadOptions().put("XSD_PROGRESS_MONITOR ", progressMonitor);
					findSchemas(document.getDocumentElement());
				} finally {
					XSDSchemaImpl.getGlobalResourceSet().getLoadOptions().put("XSD_PROGRESS_MONITOR ", oldMonitor);
				}
			}
		} catch (Exception exception) {
			XSDPlugin.INSTANCE.log(exception);
		}

		for (Iterator i = getContents().iterator(); i.hasNext();) {
			XSDSchema xsdSchema = (XSDSchema) i.next();
			assignDiagnostics(xsdSchema, xsdParser.getDiagnostics());

			for (Iterator diagnostics = xsdParser.getDiagnostics().iterator(); diagnostics.hasNext();) {
				XSDDiagnostic xsdDiagnostic = (XSDDiagnostic) diagnostics.next();
				switch (xsdDiagnostic.getSeverity().getValue()) {
				case XSDDiagnosticSeverity.FATAL:
				case XSDDiagnosticSeverity.ERROR: {
					getErrors().add(xsdDiagnostic);
					break;
				}
				case XSDDiagnosticSeverity.WARNING:
				case XSDDiagnosticSeverity.INFORMATION: {
					getWarnings().add(xsdDiagnostic);
					break;
				}
				}
			}
		}

		if (progressMonitor != null) {
			progressMonitor.worked(1);
		}
	}

	protected boolean findSchemas(Element element) {
		if (XSDConstants.nodeType(element) == XSDConstants.SCHEMA_ELEMENT) {
			XSDSchema xsdSchema = XSDSchemaImpl.createSchema(element);
			getContents().add(xsdSchema);
		} else {
			for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
				if (child instanceof Element) {
					findSchemas((Element) child);
				}
			}
		}
		return true;
	}

	public void attached(EObject eObject) {
		super.attached(eObject);

		if (eObject instanceof XSDSchema) {
			((XSDSchema) eObject).setSchemaLocation(getURI().toString());
		}
	}
}