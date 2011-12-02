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
package org.eclipse.stardust.modeling.templates.emf.template.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotWorkflowModelResourceImpl;
import org.eclipse.stardust.model.xpdl.carnot.util.CwmXmlHandler;
import org.eclipse.stardust.model.xpdl.carnot.util.CwmXmlLoad;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.templates.emf.template.ReferenceType;
import org.xml.sax.helpers.DefaultHandler;



/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.modeling.templates.emf.template.util.TemplateResourceFactoryImpl
 * @generated NOT
 */
public class TemplateResourceImpl extends CarnotWorkflowModelResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public TemplateResourceImpl(URI uri) {
		super(uri);
	}

	/**
	 * @generated NOT
	 */
    protected XMLLoad createXMLLoad() {
        return new CwmXmlLoad(createXMLHelper()) {
            protected DefaultHandler makeDefaultHandler() {
                return new CwmXmlHandler(resource, helper, options) {
                    protected boolean doResolveReference(SingleReference ref) {
                       boolean resolved = super.doResolveReference(ref);
                       if (!resolved && ref.getObject() instanceof ReferenceType)
                       {
                          EClassifier eType = ref.getFeature().getEType();
                          // TODO: (fh) move resolving of TypeDeclarationType forward references to CwmXmlHandler
                          if (eType instanceof EClass)
                          {
                             EObject resolvedTarget = null;

                             ModelType model = ModelUtils.findContainingModel(ref.getObject());
                             String refId = ref.getValue().toString();
                             if (null != model)
                             {
                                int ix = refId.indexOf('#');
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
                                }
                                if (XpdlPackage.eINSTANCE.getTypeDeclarationType().isSuperTypeOf((EClass) eType))
                                {
                                   resolvedTarget = ModelUtils.findElementById(model.getTypeDeclarations().getTypeDeclaration(), refId);
                                }
                                else if (CarnotWorkflowModelPackage.eINSTANCE.getActivityType().isSuperTypeOf((EClass) eType))
                                {
                                   ReferenceType refObject = (ReferenceType) ref.getObject();
                                   ReferenceType parent = (ReferenceType) refObject.eContainer();
                                   ProcessDefinitionType processDefinition = parent.getProcessDefinition();
                                   resolvedTarget = ModelUtils.findElementById(processDefinition.getActivity(), refId);
                                }
                                if (null != resolvedTarget)
                                {
                                   setFeatureValue(ref.getObject(), ref.getFeature(), resolvedTarget,
                                         ref.getPosition());
                                   resolved = true;
                                }
                             }
                          }
                       }
                       return resolved;
                    }
                };
            }
        };
    }

} //TemplateResourceImpl
