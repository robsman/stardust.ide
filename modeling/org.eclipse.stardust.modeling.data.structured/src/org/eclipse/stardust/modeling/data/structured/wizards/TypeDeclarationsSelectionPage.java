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
package org.eclipse.stardust.modeling.data.structured.wizards;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.data.structured.StructLabelProvider;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaContent;

public class TypeDeclarationsSelectionPage extends WizardPage
{
   private static String[] COLUMNS = {Structured_Messages.TypeDependenciesColumnLabel};
   
   private static final String LINE_SEPARATOR = System.getProperty("line.separator");  //$NON-NLS-1$

   private final TypeDeclarationType typeDeclaration;
   
   private TableViewer viewer;

   private Set<TypeDeclarationType> dependencies;

   private StructLabelProvider labelProvider = new StructLabelProvider();

   protected TypeDeclarationsSelectionPage(TypeDeclarationType typeDeclaration)
   {
      super("TypesPage"); //$NON-NLS-1$
      this.typeDeclaration = typeDeclaration;
      this.dependencies = getDependencies(typeDeclaration);
   }

   public void createControl(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 3);
      FormBuilder.createLabel(composite, Structured_Messages.ExportedTypeLabel);
      FormBuilder.createLabel(composite, "").setImage(labelProvider.getImage(typeDeclaration)); //$NON-NLS-1$
      FormBuilder.createLabel(composite, labelProvider.getText(typeDeclaration));
      
      Table table = FormBuilder.createTable(composite, SWT.BORDER | SWT.CHECK,
            COLUMNS, new int[] {100}, 3);
      viewer = new TableViewer(table);
      viewer.setContentProvider(new ArrayContentProvider());
      viewer.setLabelProvider(labelProvider);
      
      viewer.setInput(dependencies);
      TableItem[] items = viewer.getTable().getItems();
      for (int i = 0; i < items.length; i++)
      {
         items[i].setChecked(true);
      }
      
      setControl(composite);
   }

   private Set<TypeDeclarationType> getDependencies(TypeDeclarationType typeDeclaration)
   {
      Set<TypeDeclarationType> dependencies = CollectionUtils.newSet();
      Set<String> missing = CollectionUtils.newSet();
      addDependencies(dependencies, missing, typeDeclaration);
      if (!missing.isEmpty())
      {
         StringBuffer locations = new StringBuffer();
         locations.append(Structured_Messages.STRINGBUFFER_REFERENCED_SCHEMA_SPECIFIED_AT_THE_FOLLOWING_LOCATION_WERE_NOT_FOUND);
         locations.append(LINE_SEPARATOR);
         for (Iterator<String> i = missing.iterator(); i.hasNext();)
         {
            locations.append(LINE_SEPARATOR);
            locations.append(i.next());
         }
         MessageDialog.openWarning(getShell(), Structured_Messages.DIA_MISSING_REFERENCES, locations.toString());
      }
      return dependencies;
   }

   private void addDependencies(Set<TypeDeclarationType> dependencies, Set<String> missing, TypeDeclarationType typeDeclaration)
   {
      XSDSchema schema = typeDeclaration.getSchemaType().getSchema();
      List<XSDSchemaContent> contents = schema.getContents();
      for (int i = 0; i < contents.size(); i++)
      {
         XSDSchemaContent item = (XSDSchemaContent) contents.get(i);
         if (item instanceof XSDImport)
         {
            String location = ((XSDImport) item).getSchemaLocation();
            TypeDeclarationType declaration = getSchemaContainingTypeDeclaration(location, missing, typeDeclaration);
            if (declaration != null && !dependencies.contains(declaration))
            {
               dependencies.add(declaration);
               addDependencies(dependencies, missing, declaration);
            }
         }
      }
   }

   private TypeDeclarationType getSchemaContainingTypeDeclaration(String location, Set<String> missing, TypeDeclarationType typeDeclaration)
   {
      if (location != null && location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
      {
         String typeId = location.substring(StructuredDataConstants.URN_INTERNAL_PREFIX.length());
         TypeDeclarationsType typeDeclarations = (TypeDeclarationsType) typeDeclaration.eContainer();
         if (typeDeclarations != null)
         {
            TypeDeclarationType declaration = typeDeclarations.getTypeDeclaration(typeId);
            if (declaration == null && !missing.contains(location))
            {
               missing.add(location);
            }
            else
            {
               if (declaration.getSchemaType() != null)
               {
                  return declaration;
               }
               if (declaration.getExternalReference() != null)
               {
                  return getSchemaContainingTypeDeclaration(declaration.getExternalReference().getLocation(), missing, typeDeclaration);
               }
            }
         }
      }
      return null;
   }

   public List<TypeDeclarationType> getTypes2Save()
   {
      List<TypeDeclarationType> selected = CollectionUtils.newList();
      selected.add(typeDeclaration);
      if (viewer == null)
      {
         selected.addAll(dependencies);
      }
      else
      {
         TableItem[] items = viewer.getTable().getItems();
         for (int i = 0; i < items.length; i++)
         {
            if (items[i].getChecked())
            {
               Object data = items[i].getData();
               if (dependencies.contains(data))
               {
                  selected.add((TypeDeclarationType) data);
               }
            }
         }
      }
      return selected;
   }
}