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
package org.eclipse.stardust.modeling.data.structured.validation;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.validation.IModelValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDSchema;

public class ModelValidator implements IModelValidator {
	private static final Issue[] ISSUE_ARRAY = new Issue[0];

	// validate references
	public Issue[] validate(ModelType model) throws ValidationException {
		IProject project = ModelUtils.getProjectFromEObject(model);
		List<Issue> result = CollectionUtils.newList();

		TypeDeclarationsType declarations = model.getTypeDeclarations();
		List<TypeDeclarationType> allDeclarations = declarations
				.getTypeDeclaration();
		for (TypeDeclarationType declaration : allDeclarations) {
			Issue duplicateId = checkTypeDeclaration(declaration,
					allDeclarations);
			if (duplicateId != null) {
				result.add(duplicateId);
			}

			XpdlTypeType dataType = declaration.getDataType();
			if (dataType instanceof SchemaTypeType
					|| dataType instanceof ExternalReferenceType) {
				if (dataType instanceof ExternalReferenceType) {
					String location = ((ExternalReferenceType) dataType)
							.getLocation();
					if (location != null
							&& !location
									.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX)) {
						try {
							new URL(location);
							// if it's a real url do nothing
						} catch (MalformedURLException e) {
							if (GenericUtils.getFile(project, location) == null) {
								result
										.add(Issue
												.error(
														declaration,
														MessageFormat
																.format(
																		"TypeDeclaration ''{0}'': imported file ''{1}'' not found.", //$NON-NLS-1$
																		declaration
																				.getId(),
																		location)));
							}
						}
					}
				}
				if (dataType instanceof SchemaTypeType) {
					XSDSchema schema = declaration.getSchema();
					if (schema != null) {
						schema.validate();						
						List<XSDDiagnostic> diagnostics = schema
								.getDiagnostics();
						schema.clearDiagnostics();
						for (XSDDiagnostic diagnostic : diagnostics) {
							String message = diagnostic.getMessage();
							result.add(Issue.error(declaration, message));
						}

						List<XSDImport> xsdImports = TypeDeclarationUtils
								.getImports(schema);
						if (xsdImports != null) {
							for (XSDImport xsdImport : xsdImports) {
								String location = ((XSDImport) xsdImport)
										.getSchemaLocation();
								if (location
										.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX)) {
									String typeId = location
											.substring(StructuredDataConstants.URN_INTERNAL_PREFIX
													.length());
									if (declarations.getTypeDeclaration(typeId) == null) {
										result
												.add(Issue
														.error(
																declaration,
																MessageFormat
																		.format(
																				"TypeDeclaration ''{0}'': referenced type ''{1}'' not found.", //$NON-NLS-1$
																				declaration
																						.getId(),
																				typeId)));
									}
								}
							}
						}
					}
				}
			}
		}
		return result.toArray(ISSUE_ARRAY);
	}

	private Issue checkTypeDeclaration(TypeDeclarationType checkDeclaration,
			List<TypeDeclarationType> allDeclarations) {
		for (TypeDeclarationType declaration : allDeclarations) {
			if (!declaration.equals(checkDeclaration)) {
				if (declaration.getId().equals(checkDeclaration.getId())) {
					return Issue.error(checkDeclaration, MessageFormat.format(
							"TypeDeclaration ''{0}'' has duplicate Id.", //$NON-NLS-1$
							checkDeclaration.getId()));
				}
			}
		}
		return null;
	}
}