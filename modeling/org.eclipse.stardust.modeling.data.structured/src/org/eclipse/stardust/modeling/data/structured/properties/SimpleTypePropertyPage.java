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
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.TableEditorTraverseManager;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.data.structured.StructContentProvider;
import org.eclipse.stardust.modeling.data.structured.StructLabelProvider;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xsd.XSDConstrainingFacet;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDMaxLengthFacet;
import org.eclipse.xsd.XSDMinLengthFacet;
import org.eclipse.xsd.XSDPatternFacet;
import org.eclipse.xsd.XSDRepeatableFacet;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;

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

   private Label baseTypeLabel;

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
      if (isInternal)
      {
         contentProvider.setNewElement(type, createNewElement());
      }
      enumerationRadioButton.setEnabled(enumerationRadioButton.getSelection() || isInternal);
      patternedRadioButton.setEnabled(patternedRadioButton.getSelection() || isInternal);
      minLengthText.setEditable(isInternal);
      maxLengthText.setEditable(isInternal);
      viewer.setInput(declaration);
      updateButtons();
   }
   
   private void setRadioButtons(XSDSimpleTypeDefinition type)
   {
      if (type.getPatternFacets().isEmpty()
            || !type.getEnumerationFacets().isEmpty())
      {
         enumerationRadioButton.setSelection(true);
         patternedRadioButton.setSelection(false);         
      }
      else
      {
         patternedRadioButton.setSelection(true);
         enumerationRadioButton.setSelection(false);
      }
   }      
   
   private XSDConstrainingFacet createNewElement()
   {
      XSDConstrainingFacet facet = enumerationRadioButton.getSelection()
         ? (XSDConstrainingFacet) XSDFactory.eINSTANCE.createXSDEnumerationFacet()
         : XSDFactory.eINSTANCE.createXSDPatternFacet();         
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
      Composite composite = FormBuilder.createComposite(parent, 3);
      
      FormBuilder.createLabel(composite, Structured_Messages.SimpleTypePropertyPage_BaseTypeLabel);
      baseTypeLabel = FormBuilder.createLabel(composite, "", 2); //$NON-NLS-1$

      FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      enumerationRadioButton = FormBuilder.createRadioButton(composite, Structured_Messages.SimpleTypePropertyPage_EnumerationButtonLabel);
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
               viewer.refresh();
            }
         }
      });
      patternedRadioButton = FormBuilder.createRadioButton(composite, Structured_Messages.SimpleTypePropertyPage_PatternedButtonLabel);
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
               viewer.refresh();
            }
         }
      });
      
      FormBuilder.createLabel(composite, Structured_Messages.SimpleTypePropertyPage_MaxLengthLabel);
      maxLengthText = FormBuilder.createText(composite, 2);
      maxLengthText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            updateMaxLength();
         }
      });

      FormBuilder.createLabel(composite, Structured_Messages.SimpleTypePropertyPage_MinLengthLabel);
      minLengthText = FormBuilder.createText(composite, 2);
      minLengthText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            updateMinLength();
         }
      });

      Table table = FormBuilder.createTable(composite, SWT.SINGLE | SWT.FULL_SELECTION
            | SWT.BORDER, StructLabelProvider.SIMPLE_TYPE_COLUMNS, new int[] {99}, 3);
      table.setHeaderVisible(true);
      viewer = new TableViewer(table);
      viewer.setUseHashlookup(true);
      labelProvider = new StructLabelProvider();
      contentProvider = new StructContentProvider(false);
      viewer.setContentProvider(contentProvider);
      viewer.setLabelProvider(labelProvider);
      viewer.setColumnProperties(StructLabelProvider.SIMPLE_TYPE_COLUMNS);
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
      boolean isInternal = TypeDeclarationUtils.isInternalSchema(declaration);
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
         return TypeDeclarationUtils.isInternalSchema(declaration);
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
}