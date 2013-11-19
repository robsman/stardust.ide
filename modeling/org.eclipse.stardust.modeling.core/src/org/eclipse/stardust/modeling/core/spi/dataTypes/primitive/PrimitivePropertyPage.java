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
package org.eclipse.stardust.modeling.core.spi.dataTypes.primitive;

import static org.eclipse.stardust.common.CollectionUtils.newHashMap;
import static org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils.forEachReferencedModel;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.BindingManager;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IBindingMediator;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtButtonAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtComboAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.Verifier;
import org.eclipse.stardust.modeling.core.VerifierFactory;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.SwtDatePickerAdapter;
import org.eclipse.stardust.modeling.core.editors.ui.SwtVerifierTextAdapter;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.ui.PrimitiveDataModelAdapter;
import org.eclipse.stardust.modeling.core.ui.PrimitiveDataWidgetAdapter;
import org.eclipse.stardust.modeling.core.utils.ExtensibleElementValueAdapter;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;

import com.gface.date.DatePickerCombo;

/**
 * @author fherinean
 * @version $Revision$
 */

public class PrimitivePropertyPage extends AbstractModelElementPropertyPage
      implements IDataPropertyPage
{
   private static final Type[] TYPES = fetchTypes();

   // TODO: unify with other empty arrays
   private static final Object[] emptyArray = new Object[0];

   private ComboViewer typeViewer;

   private Composite valueComposite;

   private Map<Type, Object> valueControlsMap = newHashMap();

   private Label enumLabel;

   private Tree enumTree;

   private TreeViewer enumViewer;

   private ComboViewer enumComboViewer;

   private Button enumSorter;

   private Label enumSorterLabel;

   protected boolean grouped;

   private static Type[] fetchTypes()
   {
      List<Type> types = Type.getTypes();
      Type[] array = types.toArray(new Type[types.size()]);
      Arrays.sort(array);
      return array;
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager binding = getWidgetBindingManager();

      // bind types, typeViewer and valueComposites
      BindingManager mgr = binding.getModelBindingManager();
      mgr.bind(
            new PrimitiveDataModelAdapter(ModelUtils.findContainingModel(element), Arrays
                  .asList(TYPES), (IExtensibleElement) element),
            new PrimitiveDataWidgetAdapter(typeViewer, valueComposite, valueControlsMap));

      // bind typeViewer and type attribute of DataType
      mgr.bind(
            WidgetBindingManager.createModelAdapter((IExtensibleElement) element,
                  CarnotConstants.TYPE_ATT, false), getSwtComboAdapter());
      
      mgr.bind(
            WidgetBindingManager.createModelAdapter((IExtensibleElement) element, "carnot:engine:dataType",  //$NON-NLS-1$
                  ExtensibleElementValueAdapter.INSTANCE),
            BindingManager.createWidgetAdapter(enumViewer));

      // bind valueComposites and value attribute of DataType
      for (int i = 0; i < TYPES.length; i++)
      {
         Object control = valueControlsMap.get(TYPES[i]);
         if ((TYPES[i].equals(Type.Timestamp)) || (TYPES[i].equals(Type.Calendar)))
         {
            Button resetBtn = null;
            DatePickerCombo datePickerCombo;
            datePickerCombo = ((DatePickerComposite) control).getCalendarCombo();
            resetBtn = ((DatePickerComposite) control).getResetBtn();
            mgr.bind(
                  WidgetBindingManager.createModelAdapter((IExtensibleElement) element,
                        CarnotConstants.DEFAULT_VALUE_ATT, false),
                  getSwtDatePickerAdapter(datePickerCombo, resetBtn));
         }
         else if (TYPES[i].equals(Type.Boolean))
         {
            Button button = (Button) control;
            mgr.bind(
               WidgetBindingManager.createModelAdapter((IExtensibleElement) element,
                  // The default value of a primitive is always stored in the model
                  // as of type String and never boolean
                  CarnotConstants.DEFAULT_VALUE_ATT, false),
                  getSwtButtonAdapter(button));
         }
         else if (TYPES[i].equals(Type.Enumeration))
         {
            mgr.bind(
                  WidgetBindingManager.createModelAdapter((IExtensibleElement) element,
                        CarnotConstants.DEFAULT_VALUE_ATT, false), new SwtComboAdapter((Combo) control));
         }
         else
         {
            final Type type = TYPES[i];
            Text text = (Text) control;
            mgr.bind(
                  WidgetBindingManager.createModelAdapter((IExtensibleElement) element,
                        CarnotConstants.DEFAULT_VALUE_ATT, false),
                  getSwtVerifierTextAdapter(type, text));
         }
      }

      if (isPredefined(element))
      {
         disableControls();
      }
   }

   private SwtVerifierTextAdapter getSwtVerifierTextAdapter(final Type type, Text text)
   {
      return new SwtVerifierTextAdapter(text)
      {
         public void updateControl(Object value)
         {
            Type selectedType = (Type) ((IStructuredSelection) typeViewer
                  .getSelection()).getFirstElement();
            if (type.equals(selectedType))
            {
               super.updateControl(value);
            }
         }
      };
   }

   private SwtButtonAdapter getSwtButtonAdapter(Button b)
   {
      return new SwtButtonAdapter(b)
      {
         public void bind(IBindingMediator manager)
         {
            super.bind(manager);
            final Button button = (Button) getWidget();
            button.addSelectionListener(new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  updateModel(button.getSelection()
                        ? Boolean.TRUE.toString()
                        : Boolean.FALSE.toString());
               }
            });
         }

         public void updateControl(Object value)
         {
        	if (value != null && !(value instanceof Boolean))
        	{
        	   value = "true".equalsIgnoreCase(value.toString()) ? Boolean.TRUE : Boolean.FALSE; //$NON-NLS-1$
        	}
            Type selectedType = (Type) ((IStructuredSelection) typeViewer
                  .getSelection()).getFirstElement();
            if (Type.Boolean.equals(selectedType))
            {
               super.updateControl(value);
            }
         }
      };
   }

   private SwtDatePickerAdapter getSwtDatePickerAdapter(DatePickerCombo datePickerCombo, Button resetBtn)
   {
      return new SwtDatePickerAdapter(datePickerCombo, resetBtn)
      {
         public void updateControl(Object value)
         {
            Type selectedType = (Type) ((IStructuredSelection) typeViewer.getSelection())
                  .getFirstElement();
            if (Type.Calendar.equals(selectedType) || Type.Timestamp.equals(selectedType))
            {
               super.updateControl(value);
            }
         }
      };
   }

   private SwtComboAdapter getSwtComboAdapter()
   {
      return new SwtComboAdapter(typeViewer.getCombo())
      {
         public void updateControl(Object value)
         {
            super.updateControl(value);

            Type selectedType = (Type) ((IStructuredSelection) typeViewer.getSelection())
                  .getFirstElement();
            selectedType = (selectedType == null) ? Type.String : selectedType;
            Object object = valueControlsMap.get(selectedType);
            ((StackLayout) valueComposite.getLayout()).topControl = object instanceof DatePickerComposite
                  ? ((DatePickerComposite) object).getCalendarComposite()
                  : (Control) object;

            valueComposite.layout();
            exclude(selectedType != Type.Enumeration, enumLabel, enumTree, enumSorterLabel, enumSorter);
            enumLabel.getParent().layout();
         }
      };
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

   private void disableControls()
   {
      typeViewer.getCombo().setEnabled(false);
      for (Object obj : valueControlsMap.values())
      {
         Control control = obj instanceof DatePickerComposite
               ? ((DatePickerComposite) obj).getCalendarComposite()
               : (Control) obj;

         if (control instanceof Text)
         {
            ((Text) control).setEditable(false);
         }
         else if (control instanceof Button)
         {
            ((Button) control).setEnabled(false);
         }
         else if (control instanceof DatePickerCombo)
         {
            ((DatePickerCombo) control).setEnabled(false);
         }
      }
      enumTree.setEnabled(false);
   }

   private boolean isPredefined(IModelElement element)
   {
      return ((DataType) element).isPredefined();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      FormBuilder.createLabel(composite, Diagram_Messages.LB_SPI_Type);

      typeViewer = new ComboViewer(FormBuilder.createCombo(composite));
      typeViewer.setContentProvider(new ArrayContentProvider());
      typeViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object type)
         {
            return ((Type) type).getId();
         }
      });
      
      enumLabel = FormBuilder.createLabel(composite, "Structure: "); //$NON-NLS-1$
      enumTree = createEnumTree(composite);
      enumViewer = createEnumViewer(enumTree);
      enumSorterLabel = FormBuilder.createLabel(composite, "");
      enumSorter = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_GroupModelElements);
      enumSorter.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {}

         public void widgetSelected(SelectionEvent e)
         {
            grouped = enumSorter.getSelection();
            enumViewer.refresh(true);
         }
      });

      FormBuilder.createLabel(composite, Diagram_Messages.LB_DefaultValue);

      valueComposite = new Composite(composite, SWT.NONE);
      GridData gd = new GridData();
      gd.grabExcessHorizontalSpace = true;
      if (0 < typeViewer.getCombo().getBounds().height)
      {
         gd.heightHint = typeViewer.getCombo().getBounds().height;
      }
      gd.horizontalAlignment = SWT.FILL;
      valueComposite.setLayoutData(gd);

      StackLayout layout = new StackLayout();
      valueComposite.setLayout(layout);

      valueControlsMap.put(TYPES[0], createDatePickerComposite());

      Combo enumCombo = FormBuilder.createCombo(valueComposite);
      enumComboViewer = new ComboViewer(enumCombo);
      enumComboViewer.setContentProvider(new ArrayContentProvider());
      valueControlsMap.put(TYPES[1], enumCombo);

      valueControlsMap.put(TYPES[2], FormBuilder.createText(valueComposite));

      valueControlsMap.put(TYPES[3], createDatePickerComposite());

      valueControlsMap.put(TYPES[4], new Button(valueComposite, SWT.CHECK));

      valueControlsMap.put(TYPES[5], createVerifiedText(VerifierFactory.byteVerifier));

      valueControlsMap.put(TYPES[6], createLimitedText(1));

      valueControlsMap.put(TYPES[7], createVerifiedText(VerifierFactory.doubleVerifier));

      valueControlsMap.put(TYPES[8], createVerifiedText(VerifierFactory.floatVerifier));

      valueControlsMap.put(TYPES[9], createVerifiedText(VerifierFactory.intVerifier));

      valueControlsMap.put(TYPES[10], createVerifiedText(VerifierFactory.longVerifier));

      valueControlsMap.put(TYPES[11], createVerifiedText(VerifierFactory.shortVerifier));

      return composite;
   }

   private Tree createEnumTree(Composite parent)
   {
      Tree tree = FormBuilder.createTree(parent, SWT.SINGLE | SWT.FULL_SELECTION
            | SWT.BORDER);
      tree.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      return tree;
   }

   private TreeViewer createEnumViewer(Tree tree)
   {
      final TreeViewer viewer = new TreeViewer(tree);
      viewer.setUseHashlookup(true);
      viewer.setContentProvider(getEnumContentProvider());
      viewer.setLabelProvider(new EObjectLabelProvider(getEditor()));
      viewer.setSorter(new ViewerSorter());
      viewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
            Object[] facets = emptyArray;
            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
            if (!selection.isEmpty())
            {
               Object value = selection.getFirstElement();
               if (value instanceof TypeDeclarationType)
               {
                  facets = getFacets((TypeDeclarationType) value);
               }
            }
            enumComboViewer.setInput(facets);
            if (facets.length > 0)
            {
               enumComboViewer.setSelection(new StructuredSelection(facets[0]), true);
            }
         }
      });
      viewer.setInput(Collections.singleton(getEditor().getWorkflowModel()));
      return viewer;
   }

   private ITreeContentProvider getEnumContentProvider()
   {
      return new ITreeContentProvider()
      {
         @Override
         public void dispose()
         {
            // nothing to do here
         }

         @Override
         public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
         {
            // nothing to do here
         }

         @Override
         public Object[] getElements(Object inputElement)
         {
            final List<EObject> result = CollectionUtils.newList();
            if (inputElement instanceof Collection)
            {
               for (Object object : (Collection<?>) inputElement)
               {
                  addContent(result, object);
               }
            }
            else
            {
               addContent(result, inputElement);
            }
            return result.isEmpty() ? emptyArray : result.toArray();
         }

         private void addContent(final List<EObject> result, Object inputElement)
         {
            if (inputElement instanceof ModelType)
            {
               ModelType model = (ModelType) inputElement;
               if (grouped)
               {
                  addModel(result, model);
                  forEachReferencedModel(model, new Predicate<ModelType>()
                  {
                     public boolean accept(ModelType externalModel)
                     {
                        addModel(result, externalModel);
                        return true;
                     }
                  });
               }
               else
               {
                  addTypeDeclarations(result, model);
                  forEachReferencedModel(model, new Predicate<ModelType>()
                  {
                     public boolean accept(ModelType externalModel)
                     {
                        addTypeDeclarations(result, externalModel);
                        return true;
                     }
                  });
               }
            }
         }

         private void addTypeDeclarations(final List<EObject> result, ModelType model)
         {
            TypeDeclarationsType declarations = model.getTypeDeclarations();
            if (declarations != null)
            {
               for (TypeDeclarationType decl : declarations.getTypeDeclaration())
               {
                  if (TypeDeclarationUtils.isEnumeration(decl, true))
                  {
                     result.add(decl);
                  }
               }
            }
         }

         @Override
         public Object[] getChildren(Object parentElement)
         {
            if (parentElement instanceof ModelType)
            {
               List<EObject> result = CollectionUtils.newList();
               addTypeDeclarations(result, (ModelType) parentElement);
               return result.toArray();
            }
            return emptyArray;
         }

         @Override
         public Object getParent(Object element)
         {
            if (element instanceof TypeDeclarationType)
            {
               return ModelUtils.findContainingModel((EObject) element);
            }
            return null;
         }

         @Override
         public boolean hasChildren(Object element)
         {
            return element instanceof ModelType;
         }

         private void addModel(final List<EObject> result, ModelType model)
         {
            TypeDeclarationsType declarations = model.getTypeDeclarations();
            if (declarations != null)
            {
               for (TypeDeclarationType decl : declarations.getTypeDeclaration())
               {
                  if (TypeDeclarationUtils.isEnumeration(decl, true))
                  {
                     result.add(model);
                     break;
                  }
               }
            }
         }
      };
   }

   protected boolean isEnumeration(TypeDeclarationType decl)
   {
      XSDNamedComponent component = TypeDeclarationUtils.findElementOrTypeDeclaration(decl);
      if (component instanceof XSDElementDeclaration)
      {
         component = ((XSDElementDeclaration) component).getTypeDefinition();
      }
      if (component instanceof XSDSimpleTypeDefinition)
      {
         XSDEnumerationFacet effectiveFacet = ((XSDSimpleTypeDefinition) component).getEffectiveEnumerationFacet();
         if(effectiveFacet != null)
         {
            List<?> values = effectiveFacet.getValue();
            return !values.isEmpty();
         }
      }
      return false;
   }

   private Object[] getFacets(TypeDeclarationType decl)
   {
      XSDNamedComponent component = TypeDeclarationUtils.findElementOrTypeDeclaration(decl);
      if (component instanceof XSDElementDeclaration)
      {
         component = ((XSDElementDeclaration) component).getTypeDefinition();
      }
      if (component instanceof XSDSimpleTypeDefinition)
      {
         XSDEnumerationFacet effectiveFacet = ((XSDSimpleTypeDefinition) component).getEffectiveEnumerationFacet();
         return effectiveFacet.getValue().toArray();
      }
      return emptyArray;
   }

   private Text createVerifiedText(Verifier verifier)
   {
      Text text = FormBuilder.createText(valueComposite);
      text.addVerifyListener(verifier);
      return text;
   }

   private Text createLimitedText(int limit)
   {
      Text text = FormBuilder.createText(valueComposite);
      text.setTextLimit(limit);
      return text;
   }

   private DatePickerComposite createDatePickerComposite()
   {
      Composite calendarComposite = new Composite(valueComposite, SWT.NONE);
      GridData gdCal = new GridData();
      gdCal.grabExcessHorizontalSpace = true;
      gdCal.horizontalAlignment = SWT.FILL;
      calendarComposite.setLayoutData(gdCal);
      GridLayout gl = new GridLayout();
      gl.numColumns = 2;
      gl.marginHeight = 0;
      gl.marginWidth = 0;
      calendarComposite.setLayout(gl);
      GridData gdDP = new GridData();
      gdDP.grabExcessHorizontalSpace = true;
      gdDP.horizontalAlignment = SWT.FILL;
      
      final DatePickerCombo calendarCombo = new DatePickerCombo(calendarComposite, SWT.BORDER);
      calendarCombo.setLayoutData(gdDP);
      calendarCombo.setEditable(false);
      calendarCombo.setDateFormat(new SimpleDateFormat(Diagram_Messages.SIMPLE_DATE_FORMAT,
            Locale.GERMANY));
      
      Button resetButton = new Button(calendarComposite, SWT.NONE);
      resetButton.setText(Diagram_Messages.BUT_RESET);
      GridData gdBtn = new GridData();
      gdBtn.grabExcessVerticalSpace = true;
      resetButton.setLayoutData(gdBtn);
      DatePickerComposite datePickerComposite = new DatePickerComposite(calendarComposite, calendarCombo, resetButton);
      return datePickerComposite;
   }
   
   public class DatePickerComposite
   {
      private final Composite calendarComposite;

      private final DatePickerCombo calendarCombo;

      private final Button resetBtn;

      public DatePickerComposite(Composite calendarComposite,
            DatePickerCombo calendarCombo, Button resetBtn)
      {
         this.calendarComposite = calendarComposite;
         this.calendarCombo = calendarCombo;
         this.resetBtn = resetBtn;
      }

      public Button getResetBtn()
      {
         return resetBtn;
      }

      public Composite getCalendarComposite()
      {
         return calendarComposite;
      }

      public DatePickerCombo getCalendarCombo()
      {
         return calendarCombo;
      }
   }
}