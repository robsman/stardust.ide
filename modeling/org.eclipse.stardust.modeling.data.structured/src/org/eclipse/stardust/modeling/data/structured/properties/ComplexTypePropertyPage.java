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
package org.eclipse.stardust.modeling.data.structured.properties;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.XSDMapping;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XSDElementCheckForType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XsdTextProvider;
import org.eclipse.stardust.modeling.common.ui.jface.utils.ComboBoxCellEditorViewer;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.TableEditorTraverseManager;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.data.structured.StructContentProvider;
import org.eclipse.stardust.modeling.data.structured.StructLabelProvider;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.stardust.modeling.data.structured.annotations.*;
import org.eclipse.stardust.modeling.data.structured.validation.ElementValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.xsd.*;

public class ComplexTypePropertyPage extends AbstractModelElementPropertyPage
   implements IDataPropertyPage, IAnnotationChangedListener
{
   private static final String NEW_ELEMENT_PLACEHOLDER = Structured_Messages.ComplexTypePropertyPage_NewElementPlaceholder;
   private static final String ANONYMOUS_TYPE = Structured_Messages.ComplexTypePropertyPage_AnonymousPlaceholder;
   private static final String ATTRIBUTE_TYPE = Structured_Messages.ComplexTypePropertyPage_AttributePlaceholder;
   private static final String ELEMENT_TYPE = Structured_Messages.ComplexTypePropertyPage_ElementPlaceholder;

   private TreeViewer viewer;
   private ComboBoxCellEditorViewer comboViewer;
   private ComboBoxCellEditorViewer attributeComboViewer;
   private DelegateCellEditor defaultValueCellEditor;

   private StructLabelProvider labelProvider;
   private StructContentProvider contentProvider;
   private Comparator<XSDNamedComponent> typesComparator;

   private Button deleteButton;
   private Button moveUpButton;
   private Button moveDownButton;

   private XSDComplexTypeDefinition type;
   private TypeDeclarationType declaration;

   // TODO: verify usage, maybe can be removed
   private HashMap<String, String> namespace2prefix = new HashMap<String, String>();
   // TODO: verify usage, maybe can be removed
   private HashSet<String> prefixes = new HashSet<String>();

   private ArrayList<XSDNamedComponent> internalTypes = null;
   private ArrayList<XSDSimpleTypeDefinition> predefinedTypes = null;
   private HashMap<XSDNamedComponent, TypeDeclarationType> internalTypes2declarations = null;
   private AnnotationViewer annotationViewer;

   private DateFormat dateFormat = new SimpleDateFormat(Diagram_Messages.SIMPLE_DATE_FORMAT, Locale.GERMANY);
   private SashForm form;
   private Map<XSDElementDeclaration, Map<IAnnotation, Object>> defaultAnnotationMap = new HashMap<XSDElementDeclaration, Map<IAnnotation,Object>>();
   //private Link baseTypeLink;

   public void performDefaults()
   {
   resetAnnotationSettings();
   try
      {
         super.performDefaults();
      }
      catch(Exception e)
      {
      }
      totalRefresh();
   }

   private void totalRefresh()
   {
      if (!viewer.getTree().isDisposed())
      {
         viewer.refresh(getModelElement());
         updateButtons();
         Object[] exp = viewer.getExpandedElements();
         viewer.collapseAll();
         viewer.setExpandedElements(exp);
      }
   }

   protected void performApply()
   {
      defaultAnnotationMap.clear();
      super.performApply();
   }

   public boolean performCancel()
   {
      this.performDefaults();
      return super.performCancel();
   }

   public static final boolean isNewType(Object candidate)
   {
      return (candidate instanceof XSDElementDeclaration)
         && NEW_ELEMENT_PLACEHOLDER == ((XSDElementDeclaration) candidate).getName();
   }

   public static final boolean isAnonymous(XSDElementDeclaration candidate)
   {
      String name = candidate.getName();
      return name == null || name.length() == 0;
   }

   public void elementChanged()
   {
      declaration = (TypeDeclarationType) getElement().getAdapter(EObject.class);
      DefaultValueModifier.setDeclaration(declaration);
      try
      {
         type = TypeDeclarationUtils.getComplexType(declaration);
      }
      catch(IllegalArgumentException e)
      {
         XpdlTypeType dataType = declaration.getDataType();
         if (dataType instanceof ExternalReferenceType)
         {
            String externalUrl = ((ExternalReferenceType) dataType).getLocation();
            if(!StringUtils.isEmpty(externalUrl))
            {
               setMessage(MessageFormat.format(Structured_Messages.ComplexTypePropertyPage_ResourceNotFound,
                     new Object [] {externalUrl}), ERROR);
            }
         }
      }

      if(type != null)
      {
         type.updateElement(true);
         annotationViewer.setDeclaration(declaration);
         addPlaceholders(type, new HashSet<XSDComponent>());
         viewer.setInput(type);
         if (!viewer.getTree().isEnabled()) {
            viewer.expandAll();
         }
      }
      updateButtons();
   }

   private void addPlaceholders(XSDComponent type, HashSet<XSDComponent> visited)
   {
      if (!TypeDeclarationUtils.isInternalSchema(declaration)
            || visited.contains(type))
      {
         return;
      }
      visited.add(type);
      if (type instanceof XSDModelGroup && isLocal((XSDModelGroup) type))
      {
         contentProvider.setNewElement((XSDModelGroup) type, createNewElement());
      }
      Object[] children = contentProvider.getChildren(type);
      for (int i = 0; i < children.length; i++)
      {
         addPlaceholders((XSDComponent) children[i], visited);
      }
   }

   private XSDElementDeclaration createNewElement()
   {
      XSDElementDeclaration newEntry = XSDFactory.eINSTANCE.createXSDElementDeclaration();
      newEntry.setName(NEW_ELEMENT_PLACEHOLDER);
      return newEntry;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public Control createBody(Composite parent)
   {
   DefaultAnnotationModifier.INSTANCE.addAnnotationChangedListener(this);

   Composite composite = FormBuilder.createComposite(parent, 1);
      /*baseTypeLink = FormBuilder.createLink(composite, "Extension of sfi:<a>Pix</a>");
      baseTypeLink.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            baseTypeLink.setText("You have clicked '" + e.text + "'.");
         }
      });*/

      form = new SashForm(composite, SWT.VERTICAL);
      form.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(400));

      Tree tree = FormBuilder.createTree(form, SWT.SINGLE | SWT.FULL_SELECTION
            | SWT.BORDER);
      tree.setHeaderVisible(true);
//      tree.setLinesVisible(true);

      viewer = new TreeViewer(tree);
      for (int i = 0; i < StructLabelProvider.COMPLEX_TYPE_COLUMNS.length; i++)
      {
         TreeColumn column = new TreeColumn(tree, SWT.LEFT);
         column.setText(StructLabelProvider.COMPLEX_TYPE_COLUMNS[i]);
         column.setWidth(200);
      }
      viewer.setUseHashlookup(true);
      labelProvider = new StructLabelProvider(tree);
      contentProvider = new StructContentProvider(false);
      viewer.setContentProvider(contentProvider);
      viewer.setLabelProvider(labelProvider);
      viewer.setColumnProperties(StructLabelProvider.COMPLEX_TYPE_COLUMNS);
      viewer.setCellModifier(new Modifier());
      tree.addTraverseListener(
            new TableEditorTraverseManager(viewer, TableEditorTraverseManager.NONE, 0, 0, 2));
      final TextCellEditor nameEditor = new TextCellEditor(tree);
      nameEditor.getControl().addTraverseListener(
            new TableEditorTraverseManager(viewer, TableEditorTraverseManager.PREVIOUS_ROW, 1, 0, 2)
            {
               public void keyTraversed(TraverseEvent e)
               {
                  if (e.detail == SWT.TRAVERSE_TAB_PREVIOUS
                        || !isNewType(((IStructuredSelection) viewer.getSelection()).getFirstElement())
                        || nameEditor.isDirty())
                  {
                     super.keyTraversed(e);
                  }
               }

               public Object getPreviousElement(Object selection)
               {
                  Object[] elements = contentProvider.getElements(viewer.getInput());
                  for (int i = 1; i < elements.length; i++)
                  {
                     if (elements[i].equals(selection))
                     {
                        return elements[i - 1];
                     }
                  }
                  return null;
               }
            });
      ComboBoxCellEditor typeEditor = new ComboBoxCellEditor(tree, new String[0]);
      ((CCombo) typeEditor.getControl()).setEditable(false);
      typeEditor.getControl().addTraverseListener(
            new TableEditorTraverseManager(viewer, 0, 2, 0, 2));
      ComboBoxCellEditor maxOccursEditor = new ComboBoxCellEditor(tree, new String[0]);
      maxOccursEditor.getControl().addTraverseListener(
            new TableEditorTraverseManager(viewer, 1, TableEditorTraverseManager.NEXT_ROW, 0, 2)
            {
               public Object getNextElement(Object selection)
               {
                  Object[] elements = contentProvider.getElements(viewer.getInput());
                  for (int i = 0; i < elements.length - 1; i++)
                  {
                     if (elements[i].equals(selection))
                     {
                        return elements[i + 1];
                     }
                  }
                  return null;
               }
            });
      defaultValueCellEditor = new DelegateCellEditor(tree);
      viewer.setCellEditors(new CellEditor[] {
            nameEditor, typeEditor, maxOccursEditor /*, defaultValueCellEditor */});
      attributeComboViewer  = new ComboBoxCellEditorViewer(maxOccursEditor);
      attributeComboViewer.setContentProvider(contentProvider);
      attributeComboViewer.setLabelProvider(labelProvider);
      comboViewer = new ComboBoxCellEditorViewer(typeEditor);
      comboViewer.setContentProvider(new ArrayContentProvider());
      comboViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            if (element instanceof XSDCompositor)
            {
               XSDCompositor compositor = (XSDCompositor) element;
               return '<' + compositor.getName() + '>';
            }
            if (element instanceof XSDNamedComponent)
            {
               XSDNamedComponent component = (XSDNamedComponent) element;
               String name = component.getQName(type);
               if (name.indexOf(':') < 0)
               {
                  String prefix = computePrefix(name, component.getSchema());
                  if (prefix != null)
                  {
                     name = prefix + ':' + name;
                  }
               }
               return name;
            }
            return labelProvider.getText(element);
         }
      });

      tree.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
         }
         public void widgetSelected(SelectionEvent e)
         {
            Widget item = e.item;
            if(item instanceof TreeItem)
            {
               Item previousItem = defaultValueCellEditor.setItem((Item) item);
               if(previousItem != null && !previousItem.isDisposed())
               {
                  Object previous = previousItem.getData();
                  viewer.update(previous, StructLabelProvider.COMPLEX_TYPE_COLUMNS);
               }
            }
         }
      });

      typesComparator = new Comparator<XSDNamedComponent>()
      {
         public int compare(XSDNamedComponent arg0, XSDNamedComponent arg1)
         {
            String s0 = labelProvider.getText(arg0);
            String s1 = labelProvider.getText(arg1);
            return s0.compareToIgnoreCase(s1);
         }
      };

      annotationViewer = new AnnotationViewer();
      annotationViewer.createControl(form, viewer);

      viewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            XSDElementDeclaration decl = null;
            if (!selection.isEmpty())
            {
               Object item = selection.getFirstElement();
               if (item instanceof XSDElementDeclaration && !isNewType(item))
               {
                  decl = (XSDElementDeclaration) item;
               }
            }
            annotationViewer.setInput(decl);
            form.layout(true);
            updateButtons();
         }
      });

      form.setWeights(new int[] {50,50});

      return composite;
   }

   private String computePrefix(String id, XSDSchema schema)
   {
      if (schema.getTargetNamespace() == null)
      {
         return null;
      }
      String prefix = namespace2prefix.get(schema.getTargetNamespace());
      if (prefix == null)
      {
         for (Map.Entry<String, String> entry : schema.getQNamePrefixToNamespaceMap().entrySet())
         {
            if (schema.getTargetNamespace().equals(entry.getValue()))
            {
               // TODO: prefix may be null for default namespace
               prefix = entry.getKey();
               break;
            }
         }
         if (prefix == null)
         {
            prefix = TypeDeclarationUtils.computePrefix(id, prefixes);
         }
         prefixes.add(prefix);
         namespace2prefix.put(schema.getTargetNamespace(), prefix);
      }
      return prefix;
   }

   protected void updateButtons()
   {
      boolean canBeDeleted = false;
      boolean isFirst = true;
      boolean isLast = true;
      ISelection selection = viewer.getSelection();
      if (!selection.isEmpty() && selection instanceof IStructuredSelection)
      {
         XSDComponent selectedObject = (XSDComponent) ((IStructuredSelection) selection).getFirstElement();
         EObject item = selectedObject.eContainer();
         if (item != null)
         {
            EObject component = item.eContainer();
            if (isLocal(selectedObject) && (component instanceof XSDModelGroup || selectedObject instanceof XSDAttributeDeclaration))
            {
               canBeDeleted = true;
               List<?> list = getContainer(item);
               if (list != null && list.size() > 0)
               {
                  int ix = list.indexOf(item);
                  if (ix >= 0)
                  {
                     isFirst = ix == 0;
                     isLast = ix == list.size() - 1;
                  }
               }
            }
         }
      }

      XpdlTypeType type = declaration.getDataType();
      if (type instanceof ExternalReferenceType)
      {
         String location = ((ExternalReferenceType) type).getLocation();
         if(location != null
               && !location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
         {
            canBeDeleted = false;
            isFirst = true;
            isLast = true;
         }
      }

      deleteButton.setEnabled(canBeDeleted);
      moveUpButton.setEnabled(!isFirst);
      moveDownButton.setEnabled(!isLast);
   }

   private List<?> getContainer(EObject item)
   {
      EReference reference = item.eContainmentFeature();
      EObject parent = item.eContainer();
      return (List<?>) parent.eGet(reference);
   }

   private boolean validateNumericAnnotation(String annotationValue, String javaTypeName)
   {
      if(javaTypeName.equals(Float.class.getName()))
      {
         try
         {
            Float.parseFloat(annotationValue);
         }
         catch(NumberFormatException e)
         {
            setMessage(MessageFormat.format(Structured_Messages.DefaultValueValidation_NotFloat,
                  new Object [] {annotationValue}), ERROR);
            setValid(false);
            return false;
         }
      }
      else if(javaTypeName.equals(Double.class.getName()))
      {
         try
         {
            Double.parseDouble(annotationValue);
         }
         catch(NumberFormatException e)
         {
            setMessage(MessageFormat.format(Structured_Messages.DefaultValueValidation_NotDouble,
                  new Object [] {annotationValue}), ERROR);
            setValid(false);
            return false;
         }
      }
      else if(javaTypeName.equals(Byte.class.getName()))
      {
         try
         {
            Byte.parseByte(annotationValue);
         }
         catch(NumberFormatException e)
         {
            setMessage(MessageFormat.format(Structured_Messages.DefaultValueValidation_NotByte,
                  new Object [] {annotationValue}), ERROR);
            setValid(false);
            return false;
         }
      }
      else if(javaTypeName.equals(Date.class.getName()))
      {
         try {
            dateFormat.parse(annotationValue);
         } catch (ParseException e) {
            setMessage(MessageFormat.format(Structured_Messages.DefaultValueValidation_NotDate,
                  new Object [] {annotationValue}), ERROR);
            setValid(false);
            return false;
         }
      }
      else
      {
         try
         {
            Integer.parseInt(annotationValue);
         }
         catch(NumberFormatException e)
         {
            setMessage(MessageFormat.format(Structured_Messages.DefaultValueValidation_NotNumber,
                  new Object [] {annotationValue}), ERROR);
            setValid(false);
            return false;
         }
      }

      setMessage(null);
      setValid(true);

      return true;
   }

   private void validateInput()
   {
      List<String> messages = ElementValidator.validateElements(declaration);
      if (messages != null && !messages.isEmpty())
      {
         setMessage(messages.get(0), ERROR);
         setValid(false);
         return;
      }
      setMessage(null);
      setValid(true);
   }

   public void contributeVerticalButtons(Composite parent)
   {
      deleteButton = FormBuilder.createButton(parent, Structured_Messages.DeleteButtonLabel, new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {}

         public void widgetSelected(SelectionEvent e)
         {
            deleteField();
         }
      });
      moveUpButton = FormBuilder.createButton(parent, Structured_Messages.MoveUpButtonLabel, new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {}

         public void widgetSelected(SelectionEvent e)
         {
            moveFieldUp();
         }
      });
      moveDownButton = FormBuilder.createButton(parent, Structured_Messages.MoveDownButtonLabel, new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {}

         public void widgetSelected(SelectionEvent e)
         {
            moveFieldDown();
         }
      });
/*      copyButton = FormBuilder.createButton(parent, "Copy", new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {}

         public void widgetSelected(SelectionEvent e)
         {
            copyType();
         }
      });*/
   }

   protected void moveFieldDown()
   {
      Object selection = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
      moveItem(((EObject) selection).eContainer(), 1);
   }

   protected void moveFieldUp()
   {
      Object selection = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
      moveItem(((EObject) selection).eContainer(), -1);
   }

   protected void deleteField()
   {
      XSDSchema schema = ComplexTypePropertyPage.this.type.getSchema();

      Object selection = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
      XSDTypeDefinition oldDef = null;
      if (selection instanceof XSDElementDeclaration)
      {
         oldDef = ((XSDElementDeclaration) selection).getTypeDefinition();
      }
      moveItem(((EObject) selection).eContainer(), 0);
      if (oldDef != null)
      {
         if(!XSDElementCheckForType.needsType(ComplexTypePropertyPage.this.declaration, oldDef))
         {
            TypeDeclarationUtils.removeImport(schema, oldDef.getSchema());
            TypeDeclarationUtils.removeNameSpace(schema, oldDef.getName(), ((ModelType) declaration.eContainer().eContainer()).getId());
         }
      }
   }

   private void moveItem(EObject item, int amount)
   {
      EList<?> list = (EList<?>) getContainer(item);
      if (amount == 0)
      {
         list.remove(item);
      }
      else
      {
         int ix = list.indexOf(item);
         list.move(ix + amount, ix);
      }
      viewer.refresh(item.eContainer());
      updateButtons();
   }

   private boolean isLocal(XSDComponent component)
   {
      return type.getSchema().equals(component.getSchema());
   }

   private class Modifier implements ICellModifier
   {
      public boolean canModify(Object element, String property)
      {
         defaultValueCellEditor.enableEditor(false);
         if (!TypeDeclarationUtils.isInternalSchema(declaration))
         {
            return false;
         }
         if (element instanceof XSDAttributeDeclaration)
         {
/*
            if(StructLabelProvider.COMPLEX_TYPE_COLUMNS[3].equals(property))
            {
               XSDTypeDefinition typeDefinition = ((XSDAttributeDeclaration) element).getTypeDefinition();
               if(typeDefinition.getComplexType() == null)
               {
                  String javaTypeName = XSDMapping.getJavaTypeForXSDType(typeDefinition.getName());
                  if(!StringUtils.isEmpty(javaTypeName))
                  {
                     defaultValueCellEditor.enableEditor(true);
                     return true;
                  }
                  else
                  {
                     XSDSimpleTypeDefinition type = typeDefinition.getSimpleType();
                     List elements = type.getEnumerationFacets();
                     if (elements.isEmpty())
                     {
                        elements = type.getPatternFacets();
                     }
                     if (!elements.isEmpty())
                     {
                        defaultValueCellEditor.enableEditor(true);
                        return true;
                     }
                  }
               }
               return false;
            }
*/
            return isLocal((XSDAttributeDeclaration) element);
         }
         else if (element instanceof XSDModelGroup)
         {
            return isLocal((XSDModelGroup) element)
               && StructLabelProvider.COMPLEX_TYPE_COLUMNS[1].equals(property);
         }
         if (!(element instanceof XSDElementDeclaration))
         {
            return false;
         }
         XSDElementDeclaration entry = (XSDElementDeclaration) element;
         if (isNewType(entry))
         {
            return StructLabelProvider.COMPLEX_TYPE_COLUMNS[0].equals(property);
         }
/*
         if(StructLabelProvider.COMPLEX_TYPE_COLUMNS[3].equals(property))
         {
            XSDTypeDefinition typeDefinition = entry.getTypeDefinition();
            if(typeDefinition.getComplexType() == null)
            {
               String javaTypeName = XSDMapping.getJavaTypeForXSDType(typeDefinition.getName());
               if(!StringUtils.isEmpty(javaTypeName))
               {
                  defaultValueCellEditor.enableEditor(true);
                  return true;
               }
               else
               {
                  XSDSimpleTypeDefinition type = typeDefinition.getSimpleType();
                  List elements = type.getEnumerationFacets();
                  if (elements.isEmpty())
                  {
                     elements = type.getPatternFacets();
                  }
                  if (!elements.isEmpty())
                  {
                     defaultValueCellEditor.enableEditor(true);
                     return true;
                  }
               }
            }
            return false;
         }
*/
         return isLocal(entry);
      }

      public Object getValue(Object element, String property)
      {
         if (element instanceof XSDTerm || element instanceof XSDAttributeDeclaration)
         {
            XSDComponent entry = (XSDComponent) element;
            if(StructLabelProvider.COMPLEX_TYPE_COLUMNS[0].equals(property))
            {
               return (Object) labelProvider.getColumnText(entry, 0);
            }
            else if(StructLabelProvider.COMPLEX_TYPE_COLUMNS[1].equals(property))
            {
               return getTypeIndex(entry);
            }
            else if(StructLabelProvider.COMPLEX_TYPE_COLUMNS[2].equals(property))
            {
               return getCardinalityIndex(entry);
            }
            else if(StructLabelProvider.COMPLEX_TYPE_COLUMNS[3].equals(property))
            {
               return DefaultValueModifier.getValueForElement(entry);
            }
         }
         return null;
      }

      private Integer getCardinalityIndex(XSDComponent term)
      {
         updateAvailableCardinality(term);
         return (term instanceof XSDTerm || term instanceof XSDAttributeDeclaration) ?
               new Integer(XsdTextProvider.getCardinalityIndex((XSDConcreteComponent) term)) : null;
      }

      private void updateAvailableCardinality(EObject term)
      {
         if (term instanceof XSDElementDeclaration)
         {
            attributeComboViewer.setInput(StructLabelProvider.DEFAULT_CARDINALITY_LABELS);
         }
         else if (term instanceof XSDAttributeDeclaration)
         {
            attributeComboViewer.setInput(XSDAttributeUseCategory.VALUES.toArray());
         }
         else
         {
            attributeComboViewer.setInput(Collections.EMPTY_LIST);
         }
      }

      private Integer getTypeIndex(EObject term)
      {
         updateAvailableTypes(term);
         Object type = null;
         if (term instanceof XSDAttributeDeclaration)
         {
            type = ((XSDAttributeDeclaration) term).getTypeDefinition();
         }
         else if (term instanceof XSDElementDeclaration)
         {
            XSDElementDeclaration element = (XSDElementDeclaration) term;
            if (element.isElementDeclarationReference())
            {
               type = element.getResolvedElementDeclaration();
            }
            else if (element.getAnonymousTypeDefinition() != null)
            {
               type = ANONYMOUS_TYPE;
            }
            else
            {
               type = ((XSDElementDeclaration) term).getTypeDefinition();
            }
         }
         else if (term instanceof XSDModelGroup)
         {
            type = ((XSDModelGroup) term).getCompositor();
         }
         return comboViewer.findIndex(type);
      }

      private void updateAvailableTypes(EObject term)
      {
         List<Object> types = new ArrayList<Object>();
         if (term instanceof XSDAttributeDeclaration)
         {
            // add all predefined types
            types.addAll(getPredefinedTypes());

            // add all simple internal types: SchemaTypes and ExternalReferences that are to internal SchemaTypes
            types.addAll(getInternalTypes(true));

            // add conversions
            types.add(ELEMENT_TYPE);
         }
         else if (term instanceof XSDModelGroup)
         {
            // special case of selection between sequence & choice
            types.add(XSDCompositor.SEQUENCE_LITERAL);
            types.add(XSDCompositor.CHOICE_LITERAL);
            if (term.eContainer() instanceof XSDParticle
                  && term.eContainer().eContainer() instanceof XSDModelGroup)
            {
               types.add(ANONYMOUS_TYPE);
            }
         }
         else if (term instanceof XSDElementDeclaration)
         {
            // add all predefined types
            types.addAll(getPredefinedTypes());

            // add all internal types: SchemaTypes and ExternalReferences that are to internal SchemaTypes
            types.addAll(getInternalTypes(false));

            // add conversions
            types.add(XSDCompositor.SEQUENCE_LITERAL);
            types.add(XSDCompositor.CHOICE_LITERAL);
            types.add(ANONYMOUS_TYPE);
            if (((XSDElementDeclaration) term).getTypeDefinition() instanceof XSDSimpleTypeDefinition)
            {
               if (term.eContainer().eContainer().eContainer().eContainer() instanceof XSDComplexTypeDefinition)
               {
                  types.add(ATTRIBUTE_TYPE);
               }
            }
         }
         comboViewer.setInput(types);
      }

      private List<XSDSimpleTypeDefinition> getPredefinedTypes()
      {
         if (predefinedTypes == null)
         {
            predefinedTypes = new ArrayList<XSDSimpleTypeDefinition>();
            XSDSchema schema = type.getSchema();
            XSDSchema xsdSchema = schema.getSchemaForSchema();
            predefinedTypes.addAll(xsdSchema.getSimpleTypeIdMap().values());
            Collections.sort(predefinedTypes, typesComparator);
         }
         return predefinedTypes;
      }

      private List<XSDNamedComponent> getInternalTypes(boolean onlySimpleTypes)
      {
         if (internalTypes == null)
         {
            List<TypeDeclarationType> declarations = ((TypeDeclarationsType) declaration.eContainer()).getTypeDeclaration();
            internalTypes = new ArrayList<XSDNamedComponent>();
            internalTypes2declarations = new HashMap<XSDNamedComponent, TypeDeclarationType>();
            for (int i = 0; i < declarations.size(); i++)
            {
               TypeDeclarationType decl = (TypeDeclarationType) declarations.get(i);
               if (decl != declaration && isInternal(decl))
               {
                  XSDNamedComponent component = TypeDeclarationUtils.findElementOrTypeDeclaration(decl);
                  if (component instanceof XSDTypeDefinition)
                  {
                     if (!onlySimpleTypes || component instanceof XSDSimpleTypeDefinition)
                     {
                        internalTypes.add(component);
                        internalTypes2declarations.put(component, decl);
                     }
                  }
                  else if (component instanceof XSDElementDeclaration)
                  {
                     XSDElementDeclaration element = (XSDElementDeclaration) component;
                     if (element.isElementDeclarationReference())
                     {
                        // TODO: (fh) is that correct ? Should we follow references ?
                        if (!onlySimpleTypes || element.getTypeDefinition() instanceof XSDSimpleTypeDefinition)
                        {
                           internalTypes.add(element);
                           internalTypes2declarations.put(element, decl);
                        }
                     }
                     else if (element.getAnonymousTypeDefinition() != null)
                     {
                        if (!onlySimpleTypes || element.getAnonymousTypeDefinition() instanceof XSDSimpleTypeDefinition)
                        {
                           internalTypes.add(element);
                           internalTypes2declarations.put(element, decl);
                        }
                     }
                     else
                     {
                        XSDTypeDefinition definition = element.getTypeDefinition();
                        if (definition != null)
                        {
                           if (!onlySimpleTypes || definition instanceof XSDSimpleTypeDefinition)
                           {
                              internalTypes.add(definition);
                              internalTypes2declarations.put(definition, decl);
                           }
                        }
                     }
                  }
               }
            }
            Collections.sort(internalTypes, typesComparator);
         }
         return internalTypes;
      }

      public void modify(Object element, String property, Object value)
      {
         if (element instanceof Item)
         {
            element = ((Item) element).getData();
         }

         if (StructLabelProvider.COMPLEX_TYPE_COLUMNS[0].equals(property))
         {
            setName((XSDNamedComponent) element, (String) value);
         }
         else if (StructLabelProvider.COMPLEX_TYPE_COLUMNS[1].equals(property))
         {
            setType(element, comboViewer.findObject((Integer) value));
         }
         else if (StructLabelProvider.COMPLEX_TYPE_COLUMNS[2].equals(property))
         {
            if (element instanceof XSDElementDeclaration)
            {
               setCardinality((XSDElementDeclaration) element, ((Integer) value).intValue());
            }
            else if(element instanceof XSDAttributeDeclaration)
            {
               setCardinality((XSDAttributeDeclaration) element, ((Integer) value).intValue());
            }
         }
         else if (StructLabelProvider.COMPLEX_TYPE_COLUMNS[3].equals(property))
         {
            String javaTypeName = null;
            if (element instanceof XSDElementDeclaration)
            {
               XSDTypeDefinition typeDefinition = ((XSDElementDeclaration) element).getTypeDefinition();
               if(typeDefinition.getComplexType() == null)
               {
                  javaTypeName = XSDMapping.getJavaTypeForXSDType(typeDefinition.getName());
               }
            }
            else if (element instanceof XSDAttributeDeclaration)
            {
               XSDTypeDefinition typeDefinition = ((XSDAttributeDeclaration) element).getTypeDefinition();
               if (typeDefinition.getComplexType() == null)
               {
                  javaTypeName = XSDMapping.getJavaTypeForXSDType(typeDefinition.getName());
               }
            }
            String annotationValue = (String) value;
            if(!StringUtils.isEmpty(annotationValue)
                  && !StringUtils.isEmpty(javaTypeName))
            {
               try
               {
                  Class<?> clazz = Class.forName(javaTypeName);
                  String superClass = ((Class<?>) clazz.getSuperclass()).getName();
                  if(superClass.equals(Number.class.getName())
                        || javaTypeName.equals(Date.class.getName()))
                  {
                     if (!validateNumericAnnotation(annotationValue, javaTypeName))
                     {
                        return;
                     }
                  }
               }
               catch (ClassNotFoundException e)
               {
               }
            }
            DefaultValueModifier.setOrRemoveAnnotation(element, value);
            viewer.update(element, StructLabelProvider.COMPLEX_TYPE_COLUMNS);
         }
      }

      private void setType(Object element, Object value)
      {
         if (element instanceof XSDModelGroup)
         {
            changeModelGroup((XSDModelGroup) element, value);
         }
         else if (element instanceof XSDAttributeDeclaration)
         {
            changeAttribute((XSDAttributeDeclaration) element, value);
         }
         else if (element instanceof XSDElementDeclaration)
         {
            changeElement((XSDElementDeclaration) element, value);
         }
         annotationViewer.setInput(element instanceof XSDElementDeclaration
               ? (XSDElementDeclaration) element : null);
         form.layout(true);
         validateInput();
      }

      private void changeElement(XSDElementDeclaration decl, Object newType)
      {
         XSDTypeDefinition oldDef = decl.getTypeDefinition();
         DefaultValueModifier.setOrRemoveAnnotation(decl, null);
         if (oldDef != null && oldDef.equals(newType))
         {
            return;
         }

         if (newType == ANONYMOUS_TYPE)
         {
            changeElementToAnonymous(decl);
         }
         else if (newType == ATTRIBUTE_TYPE)
         {
            changeElementToAttribute(decl);
         }
         else if (newType instanceof XSDCompositor)
         {
            changeElementToModelGroup(decl, (XSDCompositor) newType);
         }
         else if (newType instanceof XSDElementDeclaration)
         {
            setElementReference(decl, (XSDElementDeclaration) newType);
         }
         else if (newType instanceof XSDTypeDefinition)
         {
            setElementType(decl, (XSDTypeDefinition) newType);
         }
         if (!XSDElementCheckForType.needsType(declaration, oldDef))
         {
            XSDSchema schema = type.getSchema();
            TypeDeclarationUtils.removeImport(schema, oldDef.getSchema());
            TypeDeclarationUtils.removeNameSpace(schema, oldDef.getName(), ((ModelType) declaration.eContainer().eContainer()).getId());
            schema.updateElement(true);
         }
      }

      private void changeElementToModelGroup(XSDElementDeclaration decl,
            XSDCompositor compositor)
      {
         decl.updateElement(true);
         if (decl.eContainer() instanceof XSDParticle)
         {
            XSDModelGroup newModelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
            newModelGroup.setCompositor(compositor);

            // transfer content
            if (decl.getAnonymousTypeDefinition() != null)
            {
               if (decl.getAnonymousTypeDefinition() instanceof XSDComplexTypeDefinition)
               {
                  XSDComplexTypeContent complexContent = ((XSDComplexTypeDefinition) decl.getAnonymousTypeDefinition()).getContent();
                  if (complexContent instanceof XSDParticle)
                  {
                     XSDTerm term = ((XSDParticle) complexContent).getTerm();
                     if (term instanceof XSDModelGroup)
                     {
                        XSDModelGroup modelGroup = (XSDModelGroup) term;
                        List<XSDParticle> content = new ArrayList<XSDParticle>();
                        content.addAll(modelGroup.getContents());
                        newModelGroup.getContents().addAll(content);
                        contentProvider.removeNewElement(modelGroup);
                     }
                  }
               }
            }

            XSDParticle newParticle = XSDFactory.eINSTANCE.createXSDParticle();
            newParticle.setContent(newModelGroup);

            XSDParticle oldParticle = (XSDParticle) decl.eContainer();
            XSDModelGroup parent = (XSDModelGroup) oldParticle.eContainer();
            int index = parent.getContents().indexOf(oldParticle);
            parent.getContents().remove(oldParticle);
            parent.getContents().add(index, newParticle);
            parent.updateElement(true);

            contentProvider.setNewElement(newModelGroup, createNewElement());
            viewer.refresh(parent);
         }
      }

      private void setElementReference(XSDElementDeclaration decl, XSDElementDeclaration ref)
      {
         decl.updateElement(true);
         updateImports(type.getSchema(), ref);
         if (decl.getTypeDefinition() != null)
         {
            decl.setTypeDefinition(null);
         }
         if (decl.getAnonymousTypeDefinition() != null)
         {
            decl.setAnonymousTypeDefinition(null);
         }
         decl.setResolvedElementDeclaration(ref);
         decl.updateElement(true);
         viewer.refresh(decl);
      }

      private void setElementType(XSDElementDeclaration decl, XSDTypeDefinition def)
      {
         decl.updateElement(true);
         updateImports(type.getSchema(), def);
         if (decl.getResolvedElementDeclaration() != decl)
         {
            decl.setResolvedElementDeclaration(decl);
         }
         if (decl.getAnonymousTypeDefinition() != null)
         {
            decl.setAnonymousTypeDefinition(null);
         }
         decl.setTypeDefinition(def);
         decl.updateElement(true);
         viewer.refresh(decl);
      }

      private void changeElementToAttribute(XSDElementDeclaration decl)
      {
         decl.updateElement(true);

         XSDSimpleTypeDefinition simpleType = (XSDSimpleTypeDefinition) decl.getTypeDefinition();

         XSDParticle particle = (XSDParticle) decl.eContainer();
         XSDModelGroup modelGroup = (XSDModelGroup) particle.eContainer();
         modelGroup.getContents().remove(particle);

         XSDParticle modelGroupParticle = (XSDParticle) modelGroup.eContainer();
         XSDComplexTypeDefinition complexType = (XSDComplexTypeDefinition) modelGroupParticle.eContainer();

         XSDAttributeDeclaration attribute = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
         attribute.setName(decl.getName());
         decl.setTypeDefinition(null);
         attribute.setTypeDefinition(simpleType);

         XSDAttributeUse use = XSDFactory.eINSTANCE.createXSDAttributeUse();
         use.setContent(attribute);
         complexType.getAttributeContents().add(use);

         complexType.updateElement(true);

         if (!(complexType == ComplexTypePropertyPage.this.type))
         {
            viewer.refresh(complexType);
         }
         viewer.refresh();
      }

      private void updateImports(XSDSchema schema, XSDNamedComponent component)
      {
         TypeDeclarationType typeDecl = (TypeDeclarationType) internalTypes2declarations.get(component);
         if (typeDecl != null)
         {
            XSDImport xsdImport = findImport(schema, component.getSchema());
            if (xsdImport == null)
            {
               xsdImport = XSDFactory.eINSTANCE.createXSDImport();
            }
            String tns = component.getTargetNamespace();
            xsdImport.setNamespace(tns);
            xsdImport.setSchemaLocation(StructuredDataConstants.URN_INTERNAL_PREFIX + typeDecl.getId());
            if (xsdImport.eContainer() != schema)
            {
               schema.getContents().add(0, xsdImport);
            }
            if (!schema.getQNamePrefixToNamespaceMap().values().contains(tns))
            {
               String prefix = computePrefix(typeDecl.getId(), component.getSchema());
               schema.getQNamePrefixToNamespaceMap().put(prefix, tns);
            }
            schema.updateElement(true);
         }
      }

      private XSDImport findImport(XSDSchema schema, XSDSchema otherSchema)
      {
         if (otherSchema != null)
         {
            for (XSDSchemaContent item : schema.getContents())
            {
               if (item instanceof XSDImport)
               {
                  if (otherSchema.equals(((XSDImport) item).getResolvedSchema()))
                  {
                     return (XSDImport) item;
                  }
               }
            }
         }
         return null;
      }

      private void changeElementToAnonymous(XSDElementDeclaration decl)
      {
         decl.updateElement(true);
         // nothing to do if it is already an anonymous type
         if (decl.getAnonymousTypeDefinition() == null)
         {
            XSDModelGroup modelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
            modelGroup.setCompositor(XSDCompositor.SEQUENCE_LITERAL);

            XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
            particle.setContent(modelGroup);

            XSDComplexTypeDefinition complexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
            complexTypeDefinition.setContent(particle);

            decl.setAnonymousTypeDefinition(complexTypeDefinition);
            decl.updateElement(true);

            contentProvider.setNewElement(modelGroup, createNewElement());
            viewer.refresh(decl);
         }
      }

      private void changeAttribute(XSDAttributeDeclaration decl, Object newType)
      {
         XSDTypeDefinition oldDef = decl.getTypeDefinition();
         DefaultValueModifier.setOrRemoveAnnotation(decl, null);
         if(oldDef != null && oldDef.equals(newType))
         {
            return;
         }

         decl.updateElement(true);
         if (newType == ELEMENT_TYPE)
         {
            XSDAttributeUse use = (XSDAttributeUse) decl.eContainer();
            XSDComplexTypeDefinition complexType = (XSDComplexTypeDefinition) use.eContainer();
            XSDComplexTypeContent content = complexType.getContent();
            if (content instanceof XSDParticle)
            {
               XSDModelGroup modelGroup = (XSDModelGroup) ((XSDParticle) content).getContent();
               XSDElementDeclaration newDecl = XSDFactory.eINSTANCE.createXSDElementDeclaration();
               newDecl.setName(decl.getName());
               newDecl.setTypeDefinition(decl.getTypeDefinition());
               XSDParticle newParticle = XSDFactory.eINSTANCE.createXSDParticle();
               newParticle.setContent(newDecl);
               complexType.getAttributeContents().remove(use);
               modelGroup.getContents().add(newParticle);
               complexType.updateElement(true);
               if (!(complexType == ComplexTypePropertyPage.this.type))
               {
                  viewer.refresh(complexType);
               }
               viewer.refresh();
            }
         }
         else if (newType instanceof XSDSimpleTypeDefinition)
         {
            decl.setTypeDefinition((XSDSimpleTypeDefinition) newType);
            decl.updateElement(true);
            viewer.update(decl, StructLabelProvider.COMPLEX_TYPE_COLUMNS);
         }
         if (!XSDElementCheckForType.needsType(declaration, oldDef))
         {
            XSDSchema schema = type.getSchema();
            TypeDeclarationUtils.removeImport(schema, oldDef.getSchema());
            TypeDeclarationUtils.removeNameSpace(schema, oldDef.getName(), ((ModelType) declaration.eContainer().eContainer()).getId());
            schema.updateElement(true);
         }
      }

      private void changeModelGroup(XSDModelGroup modelGroup, Object newType)
      {
         if (modelGroup.getCompositor() != newType)
         {
            if (newType instanceof XSDCompositor)
            {
               modelGroup.updateElement(true);
               modelGroup.setCompositor((XSDCompositor) newType);
               modelGroup.updateElement(true);
               viewer.update(modelGroup, StructLabelProvider.COMPLEX_TYPE_COLUMNS);
            }
            else if (newType == ANONYMOUS_TYPE)
            {
               changeModelGroupToAnonymousType(modelGroup);
            }
         }
      }

      private void changeModelGroupToAnonymousType(XSDModelGroup modelGroup)
      {
         modelGroup.updateElement(true);

         XSDModelGroup newModelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
         newModelGroup.setCompositor(modelGroup.getCompositor());
         List<XSDParticle> contents = new ArrayList<XSDParticle>();
         contents.addAll(modelGroup.getContents());
         newModelGroup.getContents().addAll(contents);

         XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
         particle.setContent(newModelGroup);

         XSDComplexTypeDefinition complexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
         complexTypeDefinition.setContent(particle);

         XSDElementDeclaration decl = XSDFactory.eINSTANCE.createXSDElementDeclaration();
         decl.setAnonymousTypeDefinition(complexTypeDefinition);

         XSDParticle oldParticle = (XSDParticle) modelGroup.eContainer();
         XSDModelGroup parent = (XSDModelGroup) oldParticle.eContainer();
         List<XSDParticle> list = parent.getContents();
         decl.setName(computeUniqueName(modelGroup.getCompositor().getName(), list));

         XSDParticle newParticle = XSDFactory.eINSTANCE.createXSDParticle();
         newParticle.setContent(decl);

         int ix = list.indexOf(oldParticle);
         list.remove(oldParticle);
         list.add(ix, newParticle);
         parent.updateElement(true);

         contentProvider.removeNewElement(modelGroup);
         contentProvider.setNewElement(newModelGroup, createNewElement());
         viewer.refresh(parent);
      }

      private void setCardinality(XSDAttributeDeclaration decl, int cardinality)
      {
         decl.updateElement(true);
         XSDAttributeUse xsdAttributeUse = (XSDAttributeUse) decl.eContainer();
         XSDAttributeUseCategory category = XSDAttributeUseCategory.get(cardinality);
         xsdAttributeUse.setUse(category);
         decl.updateElement();
         viewer.update(decl, StructLabelProvider.COMPLEX_TYPE_COLUMNS);
      }

      private void setCardinality(XSDElementDeclaration decl, int cardinality)
      {
         decl.updateElement(true);
         if (decl.eContainer() instanceof XSDParticle)
         {
            XSDParticle particle = (XSDParticle) decl.eContainer();
            switch (cardinality)
            {
            case 0:
               particle.unsetMinOccurs();
               particle.unsetMaxOccurs();
               break;
            case 1:
               particle.setMinOccurs(0);
               particle.unsetMaxOccurs();
               break;
            case 2:
               particle.setMinOccurs(0);
               particle.setMaxOccurs(XSDParticle.UNBOUNDED);
               break;
            case 3:
               particle.unsetMinOccurs();
               particle.setMaxOccurs(XSDParticle.UNBOUNDED);
               break;
            }
            decl.updateElement();
            viewer.update(decl, StructLabelProvider.COMPLEX_TYPE_COLUMNS);
         }
      }

      private void setName(XSDNamedComponent component, String newName)
      {
         component.updateElement(true);
         if (isNewType(component))
         {
            if (NEW_ELEMENT_PLACEHOLDER.equals(newName)
                  || StringUtils.isEmpty(newName.trim()))
            {
               // user has made no changes on the <new>.
               return;
            }
            XSDElementDeclaration xsdElement = (XSDElementDeclaration) component;
            XSDModelGroup parent = contentProvider.getEditingParent(xsdElement);
            XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
            particle.setContent(xsdElement);
            parent.getContents().add(particle);
            xsdElement.setName(newName);
            XSDSimpleTypeDefinition stringType = type.resolveSimpleTypeDefinition(XMLResource.XML_SCHEMA_URI, "string"); //$NON-NLS-1$
            xsdElement.setTypeDefinition(stringType);
            component.updateElement();
            contentProvider.setNewElement(parent, createNewElement());
            viewer.refresh(parent);
            updateButtons();
         }
         else
         {
            component.setName(newName);
            component.updateElement();
            viewer.update(component, StructLabelProvider.COMPLEX_TYPE_COLUMNS);
         }
         validateInput();
         return;
      }
   }

   public static String computeUniqueName(String baseName, List<XSDParticle> contents)
   {
      int counter = 0;
      while (nameExists(counter == 0 ? baseName : baseName + counter, contents))
      {
         counter++;
      }
      return counter == 0 ? baseName : baseName + counter;
   }

   private static boolean nameExists(String name, List<XSDParticle> contents)
   {
      for (int i = 0; i < contents.size(); i++)
      {
         XSDParticle particle = contents.get(i);
         XSDTerm term = particle.getTerm();
         if (term instanceof XSDElementDeclaration)
         {
            XSDElementDeclaration decl = (XSDElementDeclaration) term;
            if (name.equals(decl.getName()))
            {
               return true;
            }
         }
      }
      return false;
   }

   public boolean isInternal(TypeDeclarationType decl)
   {
      XpdlTypeType dataType = decl.getDataType();
      if (dataType instanceof SchemaTypeType)
      {
         return true;
      }
      if (dataType instanceof ExternalReferenceType)
      {
         String location = ((ExternalReferenceType) dataType).getLocation();
         return location != null && location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX);
      }
      return false;
   }

   public void annotationChanged(IAnnotation annotation, Object oldValue, Object newValue) {
      DefaultAnnotationModifier.stopNotifying();
      if (annotationViewer.isChangeAllMode()) {
         if (newValue == null) {
            try {
               deleteAllAnnotations(declaration, annotation);
            } catch (Throwable t) {
            }
            totalRefresh();
         } else {
            handleAllAnnotationChange(this.type, annotation, oldValue);
         }
      } else {
         CategoryAnnotation root = (CategoryAnnotation) DefaultAnnotationModifier.getRootAnnotation(annotation);
         handleDefaultValueChange(annotation, oldValue, root.getElement());
      }
      DefaultAnnotationModifier.startNotifying();
   }

   private void deleteAllAnnotations(Object element, IAnnotation annotation) {
      Object[] children = contentProvider.getChildren(element);
      for (int i = 0; i < children.length; i++) {
         Object child = children[i];
         if (child instanceof XSDElementDeclaration) {
            XSDElementDeclaration decl = (XSDElementDeclaration) child;
            annotationViewer.setInput(decl);
            if (decl.getTypeDefinition() instanceof XSDSimpleTypeDefinition && !(ComplexTypePropertyPage.isNewType(child))) {
               handleDefaultValueChange(annotation, DefaultAnnotationModifier.getAnnotationValue(annotation), child);
               DefaultAnnotationModifier.deleteAnnotation(annotation);
            }
         }
         deleteAllAnnotations(child, annotation);
      }
   }

   private void addDefaultValue(IAnnotation annotation, Map<IAnnotation, Object> defaultList, Object value) {
      Object defaultValue = defaultList.get(annotation);
      if (defaultValue == null) {
         defaultList.put(annotation, value);
      }
   }

   private void handleAllAnnotationChange(Object element, IAnnotation annotation, Object oldValue) {
      Object[] children = contentProvider.getChildren(element);
      for (int i = 0; i < children.length; i++) {
         Object child = children[i];
         if (child instanceof XSDElementDeclaration) {
            XSDElementDeclaration decl = (XSDElementDeclaration) child;
            if (decl.getTypeDefinition() instanceof XSDSimpleTypeDefinition && !(ComplexTypePropertyPage.isNewType(child))) {
               handleDefaultValueChange(annotation, oldValue, child);
            }
         }
         handleAllAnnotationChange(child, annotation, oldValue);
      }
   }

   private void handleDefaultValueChange(IAnnotation annotation, Object oldValue,
         Object child) {
      Map<IAnnotation, Object> defaultList = defaultAnnotationMap.get((XSDElementDeclaration) child);
      if (defaultList == null) {
         defaultList = new HashMap<IAnnotation,Object>();
         defaultAnnotationMap.put((XSDElementDeclaration) child, defaultList);
      }
      addDefaultValue(annotation, defaultList, oldValue);
   }

   private void resetAnnotationSettings()
   {
      DefaultAnnotationModifier.stopNotifying();
      for (Iterator<XSDElementDeclaration> i = defaultAnnotationMap.keySet().iterator(); i.hasNext();) {
         XSDElementDeclaration decl = i.next();
         annotationViewer.setInput(decl);
         Map<IAnnotation,Object> map = defaultAnnotationMap.get(decl);
         for (Iterator<IAnnotation> j = map.keySet().iterator(); j.hasNext();) {
            IAnnotation annotation = j.next();
            Object value =  map.get(annotation);
            if (value == null) {
               DefaultAnnotationModifier.deleteAnnotation(annotation);
            } else {
               DefaultAnnotationModifier.INSTANCE.setValue(annotation, value);
            }
         }
      }
      DefaultAnnotationModifier.startNotifying();
   }
}