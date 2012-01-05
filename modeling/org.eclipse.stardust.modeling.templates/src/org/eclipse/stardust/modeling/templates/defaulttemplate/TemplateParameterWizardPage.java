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
package org.eclipse.stardust.modeling.templates.defaulttemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType;
import org.eclipse.stardust.modeling.templates.emf.template.FeatureType;
import org.eclipse.stardust.modeling.templates.emf.template.ParameterType;
import org.eclipse.stardust.modeling.templates.emf.template.ReferenceType;
import org.eclipse.stardust.modeling.templates.emf.template.ScopeType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;



public class TemplateParameterWizardPage extends WizardPage
{
   private TemplateType template;
   
   private HashMap controlMapper;

   private ModelType model;

   private Map mapping;

   protected TemplateParameterWizardPage(ModelType targetModel, TemplateType template, Map mapping, ImageDescriptor titleImage)
   {
      super(template.getId(), template.getName(), titleImage);
      this.template = template;
      this.model = targetModel;
      this.mapping = mapping;
      controlMapper = new HashMap();
   }

   public void createControl(Composite parent)
   {
      parent.getShell().setText(template.getName());
      Composite composite = new Composite(parent, SWT.NO);
      composite.setLayout(new GridLayout());
      GridData gd = new GridData();

      gd.grabExcessHorizontalSpace = true;
      gd.grabExcessVerticalSpace = true;
      gd.horizontalAlignment = SWT.FILL;
      gd.verticalAlignment = SWT.FILL;
      composite.setLayoutData(gd);

      List roots = template.getRoots().getRoot();
      for (int i = 0; i < roots.size(); i++)
      {
         ReferenceType ref = (ReferenceType) roots.get(i);
         List parameters = ref.getParameters();
         for (int j = 0; j < parameters.size(); j++)
         {
            ParameterType parameter = (ParameterType) parameters.get(j);
            EObject sourceObject = parameter.getReference();
            EObject object = (EObject) mapping.get(sourceObject);
            String label = object.eClass().getName();
            if (object instanceof IIdentifiableElement)
            {
               label = ((IIdentifiableElement) object).getName();
            }
            Group group = createGroup(composite, label);
   
            List features = parameter.getFeatures();
            for (int k = 0; k < features.size(); k++)
            {
               FeatureType feature = (FeatureType) features.get(k);
               
               Label kind = new Label(group, SWT.NONE);
               label = feature.getLabel();
               kind.setText(label == null ? feature.getName()+ ":" : label + ":");
               if (FeatureStyleType.SELECTION == feature.getType())
               {
                  Combo combo = new Combo(group, SWT.READ_ONLY);
                  ComboViewer viewer = new ComboViewer(combo);
                  viewer.setContentProvider(new ArrayContentProvider());
                  viewer.setLabelProvider(new LabelProvider()
                  {
                     public String getText(Object element)
                     {
                        if (element instanceof IIdentifiableElement)
                        {
                           return ((IIdentifiableElement) element).getName();
                        }
                        return super.getText(element);
                     }
                  });
                  
                  GridData data = new GridData(GridData.FILL_HORIZONTAL);
                  data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
                  combo.setLayoutData(data);
                  
                  if (ScopeType.MODEL == feature.getScope())
                  {
                     EStructuralFeature structuralFeature = object.eClass().getEStructuralFeature(feature.getName());
                     if (structuralFeature != null)
                     {
                        EClassifier eType = structuralFeature.getEType();
                        if (eType instanceof EClass)
                        {
                           List containments = model.eClass().getEAllContainments();
                           for (int l = 0; l < containments.size(); l++)
                           {
                              EStructuralFeature targetFeature = (EStructuralFeature) containments.get(l);
                              if (targetFeature.getEType() == eType)
                              {
                                 Object source = model.eGet(targetFeature);
                                 if (source instanceof List) {
                                    List copy = new ArrayList();
                                    copy.addAll((List) source);
                                    removeContainedParents(copy, object);
                                    source = copy;
                                 }
                                 viewer.setInput(source);
                                 controlMapper.put(feature, viewer);
                                 combo.select(0);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      setControl(composite);
      validateFields();
   }

   private void removeContainedParents(List copy, EObject object)
   {
      if (object.eContainer() == null) {
         return;
      } else {
         if (copy.contains(object)) {
            copy.remove(object);            
         } 
         removeContainedParents(copy, object.eContainer());
      }
   }

   private void validateFields()
   {
      if (false)
      {
         setErrorMessage("Please provide a number of activities.");
         setPageComplete(false);
         return;
      }
      setErrorMessage(null);
      setPageComplete(true);
   }

   protected Group createGroup(Composite parent, String text)
   {
      Group group = new Group(parent, SWT.NULL);
      group.setText(text);
      GridData data = new GridData(GridData.FILL_HORIZONTAL);
      data.horizontalSpan = 2;

      group.setLayoutData(data);
      GridLayout layout = new GridLayout();
      layout.numColumns = 2;
      group.setLayout(layout);
      return group;
   }

   public boolean finish(IProgressMonitor monitor)
   {
      for (Iterator i = controlMapper.entrySet().iterator(); i.hasNext();)
      {
         Map.Entry entry = (Entry) i.next();
         FeatureType feature = (FeatureType) entry.getKey();
         Object control = entry.getValue();
         if (control instanceof StructuredViewer)
         {
            StructuredViewer viewer = (StructuredViewer) control;
            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
            if (!selection.isEmpty())
            {
               Object value = selection.getFirstElement();
               ParameterType parameter = (ParameterType) feature.eContainer();
               EObject object = (EObject) mapping.get(parameter.getReference());
               EStructuralFeature structuralFeature = object.eClass().getEStructuralFeature(feature.getName());
               object.eSet(structuralFeature, value);
            }
         }
         else
         {
            // TODO:
         }
      }
      return true;
   }
}
