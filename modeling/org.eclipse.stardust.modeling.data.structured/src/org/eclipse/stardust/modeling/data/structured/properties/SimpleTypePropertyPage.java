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

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IdFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.TableEditorTraverseManager;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.data.structured.StructContentProvider;
import org.eclipse.stardust.modeling.data.structured.StructLabelProvider;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.stardust.modeling.validation.util.FieldInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.xsd.*;

public class SimpleTypePropertyPage extends AbstractModelElementPropertyPage
   implements IDataPropertyPage
{
   private static final String NEW_ELEMENT_PLACEHOLDER = Structured_Messages.SimpleTypePropertyPage_NewFacetPlaceholder;

   private TableViewer viewer;

   private StructLabelProvider labelProvider;

   private StructContentProvider contentProvider;

   private Button deleteButton;

   private Button moveUpButton;

   private Button moveDownButton;

   private TypeDeclarationType declaration;

   private XSDSimpleTypeDefinition type;

   private Text maxLengthText;

   private Text minLengthText;

   private Button enumerationRadioButton;

   private Button patternedRadioButton;

   private Button javaEnumRadioButton;

   private Label baseTypeLabel;

   private Label maxLengthLabel;

   private Label minLengthLabel;

   private LabeledText classText;

   private TypeSelectionComposite classBrowser;

   private ModifyListener listener = new ModifyListener()
   {
      @Override
      public void modifyText(ModifyEvent ev)
      {
         removeEnumerations();
         TypeInfo itype = classBrowser.getType();
         if (itype != null)
         {
            setJavaType(itype);
         }
         viewer.refresh();
         validateClass();
      }
   };

   private TypeFinder finder;

   private String enumName;

   public void performDefaults()
   {
      type = (XSDSimpleTypeDefinition) TypeDeclarationUtils.getSimpleType(declaration);
      setRadioButtons(type);
      try
      {
         super.performDefaults();
      }
      catch(Exception e)
      {

      }

      viewer.refresh(getModelElement());
      validateAll();
   }

   public static final boolean isNewType(XSDConstrainingFacet candidate)
   {
      return NEW_ELEMENT_PLACEHOLDER == candidate.getLexicalValue();
   }

   public void apply()
   {
      // TODO: (fh) if ExternalReference save content to the original file.
   }

   public void elementChanged()
   {
      declaration = (TypeDeclarationType) getElement().getAdapter(EObject.class);
      type = (XSDSimpleTypeDefinition) TypeDeclarationUtils.getSimpleType(declaration);

      finder = new TypeFinder(declaration);
      classBrowser.setTypeFinder(finder);
      classBrowser.setModel((ModelType) declaration.eContainer().eContainer());
      classBrowser.setFilter(Enum.class);

      String namespace = type.getTargetNamespace();
      XSDTypeDefinition baseType = type.getBaseType();
      String label = baseType.getName();
      namespace = baseType.getTargetNamespace();
      if (namespace != null && namespace.trim().length() > 0)
      {
         label = label + " (" + namespace + ")"; //$NON-NLS-1$ //$NON-NLS-2$
      }
      baseTypeLabel.setText(label);

      XSDMaxLengthFacet maxLength = type.getMaxLengthFacet();
      if (maxLength != null)
      {
         maxLengthText.setText(new Integer(maxLength.getValue()).toString());
      }

      XSDMinLengthFacet minLength = type.getMinLengthFacet();
      if (minLength != null)
      {
         minLengthText.setText(new Integer(minLength.getValue()).toString());
      }

      setRadioButtons(type);

      boolean isInternal = TypeDeclarationUtils.isInternalSchema(declaration);
      boolean javaBound = isJavaBound();
      if (isInternal && !javaBound)
      {
         contentProvider.setNewElement(type, createNewElement());
      }

      enumerationRadioButton.setEnabled(enumerationRadioButton.getSelection() || isInternal);
      patternedRadioButton.setEnabled(patternedRadioButton.getSelection() || isInternal);
      javaEnumRadioButton.setEnabled(javaEnumRadioButton.getSelection() || isInternal);

      minLengthText.setEditable(isInternal);
      maxLengthText.setEditable(isInternal);
      viewer.setInput(declaration);
      updateButtons();

      setJavaBound(javaBound);
      if (javaBound)
      {
         enumName = ExtendedAttributeUtil.getAttributeValue(declaration, CarnotConstants.CLASS_NAME_ATT);
         initJavaType(enumName);
      }
      validateClass();      
   }

   private void initJavaType(String enumName)
   {
      TypeInfo enumType = finder.findType(enumName);
      if (enumType == null)
      {
         classBrowser.setTypeText(enumName);
      }
      else
      {
         classBrowser.setType(enumType);
      }
   }

   private void setRadioButtons(XSDSimpleTypeDefinition type)
   {
      if (isJavaBound())
      {
         javaEnumRadioButton.setSelection(true);
         enumerationRadioButton.setSelection(false);
         patternedRadioButton.setSelection(false);
      }
      else if (type.getPatternFacets().isEmpty()
            || !type.getEnumerationFacets().isEmpty())
      {
         enumerationRadioButton.setSelection(true);
         patternedRadioButton.setSelection(false);
         javaEnumRadioButton.setSelection(false);
      }
      else
      {
         patternedRadioButton.setSelection(true);
         enumerationRadioButton.setSelection(false);
         javaEnumRadioButton.setSelection(false);
      }
   }

   private XSDConstrainingFacet createNewElement()
   {
      XSDConstrainingFacet facet = patternedRadioButton.getSelection()
         ? XSDFactory.eINSTANCE.createXSDPatternFacet()
         : (XSDConstrainingFacet) XSDFactory.eINSTANCE.createXSDEnumerationFacet();
      facet.setLexicalValue(NEW_ELEMENT_PLACEHOLDER);
      return facet;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // nothing to do here
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // nothing to do here
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 4);

      FormBuilder.createLabel(composite, Structured_Messages.SimpleTypePropertyPage_BaseTypeLabel);
      baseTypeLabel = FormBuilder.createLabel(composite, "", 3); //$NON-NLS-1$

      FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      enumerationRadioButton = FormBuilder.createRadioButton(composite, Structured_Messages.SimpleTypePropertyPage_EnumerationButtonLabel);
      patternedRadioButton = FormBuilder.createRadioButton(composite, Structured_Messages.SimpleTypePropertyPage_PatternedButtonLabel);
      javaEnumRadioButton = FormBuilder.createRadioButton(composite, Structured_Messages.SimpleTypePropertyPage_JavaBound);

      maxLengthLabel = FormBuilder.createLabel(composite, Structured_Messages.SimpleTypePropertyPage_MaxLengthLabel);
      maxLengthText = FormBuilder.createText(composite, 3);

      minLengthLabel = FormBuilder.createLabel(composite, Structured_Messages.SimpleTypePropertyPage_MinLengthLabel);
      minLengthText = FormBuilder.createText(composite, 3);

      LabelWithStatus classLabel = FormBuilder.createLabelWithRightAlignedStatus(
            composite, Structured_Messages.SimpleTypePropertyPage_EnumClass);
      classBrowser = new TypeSelectionComposite(composite, Structured_Messages.SimpleTypePropertyPage_JavaEnumClass, 3);
      classText = new LabeledText(classBrowser.getText(), classLabel);
      exclude(true, classBrowser.getText().getParent(), classText.getLabel());

      Table table = FormBuilder.createTable(composite, SWT.SINGLE | SWT.FULL_SELECTION
            | SWT.BORDER, StructLabelProvider.SIMPLE_TYPE_COLUMNS, new int[] {99}, 4);
      table.setHeaderVisible(true);
      viewer = new TableViewer(table);
      viewer.setUseHashlookup(true);
      labelProvider = new StructLabelProvider();
      contentProvider = new StructContentProvider(false);
      viewer.setContentProvider(contentProvider);
      viewer.setLabelProvider(labelProvider);
      viewer.setColumnProperties(StructLabelProvider.SIMPLE_TYPE_COLUMNS);

      enumerationRadioButton.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
         }

         public void widgetSelected(SelectionEvent e)
         {
            if (enumerationRadioButton.getSelection())
            {
               removePatterns();
               if (TypeDeclarationUtils.isInternalSchema(declaration))
               {
                  contentProvider.setNewElement(type, createNewElement());
               }
               setJavaBound(false);
               viewer.refresh();
            }
         }
      });

      patternedRadioButton.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
         }

         public void widgetSelected(SelectionEvent e)
         {
            if (patternedRadioButton.getSelection())
            {
               removeEnumerations();
               if (TypeDeclarationUtils.isInternalSchema(declaration))
               {
                  contentProvider.setNewElement(type, createNewElement());
               }
               setJavaBound(false);
               viewer.refresh();
            }
         }
      });

      javaEnumRadioButton.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
         }

         public void widgetSelected(SelectionEvent e)
         {
            if (javaEnumRadioButton.getSelection())
            {
               removePatterns();
               removeEnumerations();
               contentProvider.removeNewElement(type);
               setJavaBound(true);
               viewer.refresh();
               validateClass();
            }
         }
      });

      maxLengthText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            updateMaxLength();
         }
      });

      minLengthText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            updateMinLength();
         }
      });

      viewer.setCellModifier(new Modifier());
      table.addFocusListener(new FocusListener()
      {
         public void focusGained(FocusEvent e)
         {
            ISelection selection = viewer.getSelection();
            if (selection.isEmpty())
            {
               Object[] elements = contentProvider.getElements(viewer.getInput());
               if (elements.length > 0)
               {
                  viewer.setSelection(new StructuredSelection(elements[0]));
               }
            }
         }

         public void focusLost(FocusEvent e)
         {
            // not interested in
         }
      });
      table.addTraverseListener(
            new TableEditorTraverseManager(viewer, TableEditorTraverseManager.NONE, 0, 0, 0));
      final TextCellEditor nameEditor = new TextCellEditor(table);
      nameEditor.getControl().addTraverseListener(
            new TableEditorTraverseManager(viewer, TableEditorTraverseManager.PREVIOUS_ROW,
                  TableEditorTraverseManager.NEXT_ROW, 0, 0)
            {
               public void keyTraversed(TraverseEvent e)
               {
                  if (e.detail == SWT.TRAVERSE_TAB_PREVIOUS
                        || !isNewType((XSDConstrainingFacet) ((IStructuredSelection) viewer.getSelection()).getFirstElement())
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

               public Object getNextElement(Object selection)
               {
                  // <workaround>
                  // <description>
                  // THIS METHOD MAY MODIFY THE CONTENT !
                  // when "new" element is the current selection, there is no "next",
                  // so we do insert a new item here.THIS METHOD MODIFIES CONTENT !
                  // </description>
                  XSDConstrainingFacet adapter = (XSDConstrainingFacet) selection;
                  if (isNewType(adapter))
                  {
                     addEntry(adapter, (String) nameEditor.getValue());
                  }
                  // </workaround>
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
      viewer.setCellEditors(new CellEditor[] {
            nameEditor
      });
      viewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            updateButtons();
         }
      });
      return composite;
   }

   private void exclude(boolean exclude, Control... controls)
   {
      if (controls != null)
      {
         for (Control control : controls)
         {
            GridData gd = (GridData) control.getLayoutData();
            gd.exclude = exclude;
            control.setVisible(!exclude);
         }
      }
   }

   protected void removeEnumerations()
   {
      List<XSDEnumerationFacet> enumerations = type.getEnumerationFacets();
      for (int i = enumerations.size() - 1; i >= 0; i--)
      {
         type.getFacetContents().remove(enumerations.get(i));
      }
   }

   protected void removePatterns()
   {
      List<XSDPatternFacet> patterns = type.getPatternFacets();
      for (int i = patterns.size() - 1; i >= 0; i--)
      {
         type.getFacetContents().remove(patterns.get(i));
      }
   }

   protected void updateButtons()
   {
      boolean isInternal = TypeDeclarationUtils.isInternalSchema(declaration)
            && !isJavaBound();
      if (!isInternal)
      {
         deleteButton.setEnabled(false);
         moveUpButton.setEnabled(false);
         moveDownButton.setEnabled(false);
         return;
      }

      boolean isFacet = false;
      boolean isFirst = true;
      boolean isLast = true;
      ISelection selection = viewer.getSelection();
      if (!selection.isEmpty() && selection instanceof IStructuredSelection)
      {
         IStructuredSelection iss = (IStructuredSelection) selection;
         if (iss.getFirstElement() instanceof XSDConstrainingFacet &&
               !isNewType((XSDConstrainingFacet) iss.getFirstElement()))
         {
            isFacet = true;
            XSDConstrainingFacet element = (XSDConstrainingFacet) iss.getFirstElement();
            Object[] children = contentProvider.getChildren(element.getSimpleTypeDefinition());
            if (children.length > 0)
            {
               isFirst = element == children[0];
               // -2 because the last child is the <new> one
               isLast = element == children[children.length - 2];
            }
         }
      }
      deleteButton.setEnabled(isFacet);
      moveUpButton.setEnabled(!isFirst);
      moveDownButton.setEnabled(!isLast);
   }

   private boolean isJavaBound()
   {
      return ExtendedAttributeUtil.getAttribute(declaration, CarnotConstants.CLASS_NAME_ATT) != null;
   }

   protected void updateMaxLength()
   {
      String stringValue = maxLengthText.getText().trim();
      long longValue = -1;
      try
      {
         longValue = Long.parseLong(stringValue);
      }
      catch (NumberFormatException nfe)
      {
         // ignore;
      }
      XSDMaxLengthFacet maxLength = type.getMaxLengthFacet();
      if (longValue < 0)
      {
         if (maxLength != null)
         {
            type.getFacetContents().remove(maxLength);
         }
      }
      else
      {
         if (maxLength == null)
         {
            maxLength = XSDFactory.eINSTANCE.createXSDMaxLengthFacet();
            type.getFacetContents().add(maxLength);
         }
         maxLength.setLexicalValue(Long.toString(longValue));
      }
      validateAll();
   }

   protected void updateMinLength()
   {
      String stringValue = minLengthText.getText().trim();
      long longValue = -1;
      try
      {
         longValue = Long.parseLong(stringValue);
      }
      catch (NumberFormatException nfe)
      {
         // ignore;
      }
      XSDMinLengthFacet minLength = type.getMinLengthFacet();
      if (longValue < 0)
      {
         if (minLength != null)
         {
            type.getFacetContents().remove(minLength);
         }
      }
      else
      {
         if (minLength == null)
         {
            minLength = XSDFactory.eINSTANCE.createXSDMinLengthFacet();
            type.getFacetContents().add(minLength);
         }
         minLength.setLexicalValue(Long.toString(longValue));
      }
      validateAll();
   }

   private void validateAll()
   {
	   validateLength();
	   if(isValid())
	   {
		   validateInput();
	   }
      if(isValid())
      {
         validateClass();
      }	   	   
   }

   private void validateClass()
   {
      if(ExtendedAttributeUtil.getAttribute(declaration, CarnotConstants.CLASS_NAME_ATT) != null)
      {
         ModelType model = ModelUtils.findContainingModel(declaration);         
         String className = ExtendedAttributeUtil.getAttributeValue(declaration, CarnotConstants.CLASS_NAME_ATT);

         if(StringUtils.isEmpty(className))
         {
            setMessage(Structured_Messages.ERROR_MSG_NO_CLASSNAME, ERROR);
            setValid(false);
            return;            
         }
         else
         {
            if(model != null)
            {
               TypeFinder finder = new TypeFinder(model);
               TypeInfo type = finder.findType(className);
               
               if (type == null)
               {
                  setMessage(MessageFormat.format(Structured_Messages.ERROR_MSG_INVALID_CLASSNAME, 
                        className), ERROR);
                  setValid(false);
                  return;
               }
            }               
         }
      }      
      
      setMessage(null);
      setValid(true);      
   }
      
   private void validateLength()
   {
      String minLengthString = minLengthText.getText().trim();
      String maxLengthString = maxLengthText.getText().trim();

      if(StringUtils.isEmpty(maxLengthString)
            && StringUtils.isEmpty(minLengthString))
      {
         setMessage(null);
         setValid(true);
         return;
      }
      long maxLengthValue = 0;
      if(!StringUtils.isEmpty(maxLengthString))
      {
         try
         {
            maxLengthValue = Long.parseLong(maxLengthString);
         }
         catch (NumberFormatException nfe)
         {
            setMessage(Structured_Messages.SimpleTypePropertyPage_MaxLengthLongValue, ERROR);
            setValid(false);
            return;
         }
         if(maxLengthValue < 0)
         {
            setMessage(Structured_Messages.SimpleTypePropertyPage_MaxLengthLongValue, ERROR);
            setValid(false);
            return;
         }
      }
      long minLengthValue = 0;
      if(!StringUtils.isEmpty(minLengthString))
      {
         try
         {
            minLengthValue = Long.parseLong(minLengthString);
         }
         catch (NumberFormatException nfe)
         {
            setMessage(Structured_Messages.SimpleTypePropertyPage_MinLengthLongValue, ERROR);
            setValid(false);
            return;
         }
         if(minLengthValue < 0)
         {
            setMessage(Structured_Messages.SimpleTypePropertyPage_MinLengthLongValue, ERROR);
            setValid(false);
            return;
         }
      }
      if (maxLengthValue < minLengthValue)
      {
         setMessage(Structured_Messages.SimpleTypePropertyPage_InvalidLengthMessage, ERROR);
         setValid(false);
         return;
      }
      setMessage(null);
      setValid(true);
   }

   private void validateInput()
   {
      Object[] children = contentProvider.getChildren(type);
      Set<String> usedValues = new HashSet<String>();
      for (int i = 0; i < children.length; i++)
      {
         XSDConstrainingFacet element = (XSDConstrainingFacet) children[i];
         String value = element.getLexicalValue();
         if (usedValues.contains(value))
         {
            setMessage(MessageFormat.format(
                  Structured_Messages.SimpleTypePropertyPage_DuplicateValueMessage,
                  new Object [] {value}), ERROR);
            setValid(false);
            return;
         }
         usedValues.add(value);
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
   }

   protected void moveFieldDown()
   {
      IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
      XSDConstrainingFacet facet = (XSDConstrainingFacet) selection.getFirstElement();

      List<? extends XSDRepeatableFacet> list = enumerationRadioButton.getSelection()
                  ? type.getEnumerationFacets()
                  : type.getPatternFacets();
      int index = list.indexOf(facet);

      XSDConstrainingFacet[] elements = list.toArray(new XSDConstrainingFacet[list.size()]);
      // remove all elements in reverse order up to and including the selected element
      for (int i = elements.length - 1; i >= index; i--)
      {
         remove(elements[i]);
      }
      // add back the first two elements swapped
      add(elements[index + 1]);
      add(elements[index]);
      // ad back the rest of the list
      for (int i = index + 2; i < elements.length; i++)
      {
         add(elements[i]);
      }
      viewer.refresh();
      viewer.setSelection(selection);
   }

   private void remove(XSDConstrainingFacet facet)
   {
      if (facet instanceof XSDEnumerationFacet
         || facet instanceof XSDPatternFacet)
      {
         type.getFacetContents().remove(facet);
      }
   }

   private void add(XSDConstrainingFacet facet)
   {
      if (facet instanceof XSDEnumerationFacet
            || facet instanceof XSDPatternFacet)
      {
         type.getFacetContents().add(facet);
      }
   }

   protected void moveFieldUp()
   {
      IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
      XSDConstrainingFacet facet = (XSDConstrainingFacet) selection.getFirstElement();

      List<? extends XSDRepeatableFacet> list = enumerationRadioButton.getSelection()
                  ? type.getEnumerationFacets()
                  : type.getPatternFacets();
      int index = list.indexOf(facet);

      XSDConstrainingFacet[] elements = list.toArray(new XSDConstrainingFacet[list.size()]);
      // remove all elements in reverse order up to and including the one before the selected element
      for (int i = elements.length - 1; i >= index - 1; i--)
      {
         remove(elements[i]);
      }
      // add back the first two elements swapped
      add(elements[index]);
      add(elements[index - 1]);
      // ad back the rest of the list
      for (int i = index + 1; i < elements.length; i++)
      {
         add(elements[i]);
      }
      viewer.refresh();
      viewer.setSelection(selection);
   }

   protected void deleteField()
   {
      IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
      XSDConstrainingFacet facet = (XSDConstrainingFacet) selection.getFirstElement();
      remove(facet);
      viewer.refresh();
      validateAll();
   }

   private class Modifier implements ICellModifier
   {
      public boolean canModify(Object element, String property)
      {
         return !isJavaBound() && TypeDeclarationUtils.isInternalSchema(declaration);
      }

      public Object getValue(Object element, String property)
      {
         XSDConstrainingFacet entry = (XSDConstrainingFacet) element;
         return entry.getLexicalValue();
      }

      public void modify(Object element, String property, Object newValue)
      {
         if (element instanceof Item)
         {
            element = ((Item) element).getData();
         }
         XSDConstrainingFacet entry = (XSDConstrainingFacet) element;
         String oldValue = (String) entry.getLexicalValue();
         newValue = ((String) newValue).trim(); // remove leading and trailing white spaces
         if (!oldValue.equals(newValue)/* && !StringUtils.isEmpty((String) newValue)*/)
         {
            if (isNewType(entry))
            {
               addEntry(entry, (String) newValue);
            }
            else
            {
               entry.setLexicalValue((String) newValue);
               viewer.update(entry, StructLabelProvider.SIMPLE_TYPE_COLUMNS);
            }
            validateAll();
         }
      }
   }

   private void addEntry(XSDConstrainingFacet current, String value)
   {
      // (fh) must set new element before changing the structure
      contentProvider.setNewElement(type, createNewElement());
      current.setLexicalValue(value);
      add(current);
      viewer.refresh();
      updateButtons();
   }

   private void setJavaBound(boolean bound)
   {
      if (bound)
      {
         maxLengthText.setText(""); //$NON-NLS-1$
         minLengthText.setText(""); //$NON-NLS-1$
         if (!isJavaBound())
         {
            ExtendedAttributeUtil.createAttribute(declaration, CarnotConstants.CLASS_NAME_ATT);
         }
         classBrowser.getText().addModifyListener(listener);
         if (enumName != null)
         {
            initJavaType(enumName);
         }
      }
      else
      {
         // this does in fact remove the attribute
         ExtendedAttributeUtil.setAttribute(declaration, CarnotConstants.CLASS_NAME_ATT, null);
         classBrowser.getText().removeModifyListener(listener);
         classBrowser.setType(null);
      }
      exclude(bound, maxLengthLabel, minLengthLabel, maxLengthText, minLengthText);
      exclude(!bound, classBrowser.getText().getParent(), classText.getLabel());
      minLengthText.getParent().layout();
   }

   private void setJavaType(TypeInfo itype)
   {
      enumName = itype.getFullName();
      int ix = enumName.lastIndexOf('.');
      String name = ix >= 0 ? enumName.substring(ix + 1) : enumName;
      IdFactory factory = new IdFactory(null, name,
            XpdlPackage.eINSTANCE.getTypeDeclarationType(),
            XpdlPackage.eINSTANCE.getTypeDeclarationType_Id(),
            XpdlPackage.eINSTANCE.getTypeDeclarationType_Name());
      TypeDeclarationsType container = (TypeDeclarationsType) declaration.eContainer();
      List<TypeDeclarationType> declarations = CollectionUtils.copyList(container.getTypeDeclaration());
      declarations.remove(declaration);
      factory.computeNames(declarations, false);
      
      String oldName = ExtendedAttributeUtil.getAttributeValue(declaration, CarnotConstants.CLASS_NAME_ATT);
      
      String newId = factory.getId();
      if (!newId.equals(declaration.getId()))
      {
         if(StringUtils.isEmpty(oldName)
               || !oldName.endsWith(enumName))
         {
            declaration.setId(newId);
         }
      }
      String newName = factory.getName();
      if (!newName.equals(declaration.getName()))
      {
         if(StringUtils.isEmpty(oldName)
               || !oldName.endsWith(enumName))
         {         
            declaration.setName(newName);
         }
      }
      ExtendedAttributeUtil.setAttribute(declaration, CarnotConstants.CLASS_NAME_ATT, enumName);
      try
      {
         for (FieldInfo field : itype.getFields())
         {
            if (field.isEnum())
            {
               XSDConstrainingFacet item = createNewElement();
               item.setLexicalValue(field.getFieldName());
               add(item);
            }
         }
      }
      catch (JavaModelException ex)
      {
         ex.printStackTrace();
      }
   }
}