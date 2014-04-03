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
package org.eclipse.stardust.modeling.core.properties;

import static org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils.forEachReferencedModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsFactory;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.properties.IProcessInterfaceInvocationGenerator.IProcessDefinitionTypeProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

public class ProcessInterfacePropertyPage extends AbstractModelElementPropertyPage
      implements IButtonManager
{
   public static final String FORMAL_PARAMETERS_ID = "interface"; //$NON-NLS-1$
   
   public static final String SOAP_INVOCATION_TYPE = "SOAP"; //$NON-NLS-1$
   public static final String REST_INVOCATION_TYPE = "REST"; //$NON-NLS-1$
   public static final String BOTH_INVOCATION_TYPES = "BOTH"; //$NON-NLS-1$

   private static final int[] elementFeatureIds = {
         XpdlPackage.FORMAL_PARAMETER_TYPE__ID, XpdlPackage.FORMAL_PARAMETER_TYPE__NAME,
         XpdlPackage.FORMAL_PARAMETER_TYPE__MODE};

   private static final String[] labelProperties = {Diagram_Messages.LBL_ID, Diagram_Messages.LBL_NAME};

   private Button[] buttons;

   private Object selection;

   private EObjectLabelProvider labelProvider;

   private TableViewer implementsViewer;

   private ModelElementsOutlineSynchronizer outlineSynchronizer;

   private boolean isImplementing = false;

   private boolean isProviding = false;

   private ProcessDefinitionType process = null;

   private Table implementsTable;

   private TableViewer parametersViewer;

   private List<ProcessDefinitionType> processes;

   private FormalParameterMappingsType parameterMappings;

   private FormalParametersType formalParameters;

   private Composite mainComposite;

   private Composite comboComposite;

   private Composite stackComposite;

   private Composite exposesComposite;

   private Composite implementsComposite;

   private Composite verticalButtonsComposite;

   private Combo combo;

   private StackLayout stackLayout;

   private Composite wsdlComposite;

   private LabelWithStatus wsdlLabel;

   private Button wsdlButton;

   private Button restButton;
   
   private static final String INVOCATION_GENERATOR_ID = "org.eclipse.stardust.modeling.core.processInterfaceInvocationGenerator"; //$NON-NLS-1$
   
   private IProcessInterfaceInvocationGenerator piInvocationGenerators[];

   public ProcessInterfacePropertyPage()
   {
      IConfigurationElement[] config = Platform.getExtensionRegistry()
         .getConfigurationElementsFor(INVOCATION_GENERATOR_ID);
      List <IProcessInterfaceInvocationGenerator> genList = CollectionUtils.newList(); 
      try 
      {
         for (IConfigurationElement e : config) 
         {
             final Object o = e.createExecutableExtension("class"); //$NON-NLS-1$
             if (o instanceof IProcessInterfaceInvocationGenerator) 
             {
                genList.add((IProcessInterfaceInvocationGenerator)o);
             }
         }
         piInvocationGenerators = genList.toArray(new IProcessInterfaceInvocationGenerator[0]);
      } 
      catch (CoreException ex) 
      {
         System.out.println(ex.getMessage());
         piInvocationGenerators = new IProcessInterfaceInvocationGenerator[0];
      }
   }
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      process = (ProcessDefinitionType) element;
      initializeImplementsPanel();
      initalizeExposePanel();
      if (isProviding)
      {
         combo.select(1);
      }
      else
      {
         if (isImplementing)
         {
            combo.select(0);
         }
         else
         {
            combo.select(2);
         }
      }
      updateStackLayout();
      updateOutline();
   }

   private void updateOutline()
   {
      outlineSynchronizer.setInit(false);
      if (isProviding)
      {
         outlineSynchronizer.init(formalParameters);
         updateButtons(null, buttons);
         expandTree();
      }
      else
      {
         if (isImplementing)
         {
            IdRef externalReference = process.getExternalRef();
            if (externalReference != null)
            {
               ProcessDefinitionType selectedProcess = findProcess(externalReference);
               if (selectedProcess != null)
               {
                  parametersViewer.getTable().select(processes.indexOf(selectedProcess));
                  outlineSynchronizer.init(selectedProcess.getFormalParameters());
                  expandTree();
               }
            }
         }
         else
         {
            outlineSynchronizer.init(null);
         }
      }

   }

   public void apply()
   {     
      if (!isImplementing)
      {
         process.setExternalRef(null);
      }
      /*if (!isProviding)
      {
         process.setFormalParameters(null);
      }*/  
      if (!isProviding && !isImplementing)
      {
         process.setFormalParameters(null);
         process.setFormalParameterMappings(null);
      }
      this.validatePage();
   }

   private void initializeImplementsPanel()
   {
      IdRef externalReference = process.getExternalRef();
      implementsTable.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {

         }

         public void widgetSelected(SelectionEvent e)
         {
            if (e.item != null)
            {
               TableItem tableItem = (TableItem) e.item;
               if (tableItem.getData() != null)
               {
                  ProcessDefinitionType processDefinition = (ProcessDefinitionType) tableItem
                        .getData();
                  ModelType referencedModel = (ModelType) processDefinition.eContainer();
                  ExternalPackage packageRef = getEditor().getWorkflowModel()
                        .getExternalPackages()
                        .getExternalPackage(referencedModel.getId());
                  IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
                  idRef.setRef(processDefinition.getId());
                  idRef.setPackageRef(packageRef);
                  process.setExternalRef(idRef);
                  
                  parameterMappings = ExtensionsFactory.eINSTANCE.createFormalParameterMappingsType();
                  FormalParametersType referencedParametersType = processDefinition.getFormalParameters();                        
                  formalParameters = XpdlFactory.eINSTANCE.createFormalParametersType();
                  for (Iterator<FormalParameterType> i = referencedParametersType.getFormalParameter().iterator(); i.hasNext();) {
                     FormalParameterType referencedParameterType = i.next();
                     FormalParameterType parameterType = ModelUtils.cloneFormalParameterType(referencedParameterType, null);
                     formalParameters.addFormalParameter(parameterType);
                     parameterMappings.setMappedData(parameterType, null);
                  }                        
                  process.setFormalParameters(formalParameters);
                  process.setFormalParameterMappings(parameterMappings);                  
                  updateOutline();
               }
            }
         }
      });
      isImplementing = (externalReference != null);
      if (externalReference != null)
      {
         ProcessDefinitionType selectedProcess = findProcess(externalReference);
         if (selectedProcess != null)
         {
            implementsViewer.getTable().select(processes.indexOf(selectedProcess));
            
         }
      }
   }
   
   private void initalizeExposePanel()
   {
      formalParameters = process.getFormalParameters();
      parameterMappings = process.getFormalParameterMappings();
      isProviding = ((parameterMappings != null) && (process.getExternalRef() == null));
      parametersViewer.setInput(formalParameters);
      if (formalParameters != null)
      {
         AttributeType externalInvocationType = AttributeUtil.getAttribute(process,
               "carnot:engine:externalInvocationType"); //$NON-NLS-1$
         String externalInvocationTypeValue = null;
         if (externalInvocationType == null)
         {
            wsdlButton.setSelection(false);
            restButton.setSelection(false);
         }
         else
         {
            externalInvocationTypeValue = externalInvocationType.getValue();
            if (externalInvocationTypeValue.equalsIgnoreCase(SOAP_INVOCATION_TYPE))
            {
               wsdlButton.setSelection(true);
               restButton.setSelection(false);
            } 
            else if (externalInvocationTypeValue.equalsIgnoreCase(REST_INVOCATION_TYPE))
            {
               restButton.setSelection(true);
               wsdlButton.setSelection(false);
            }
            else
            {
               restButton.setSelection(true);
               wsdlButton.setSelection(true);
            }
         }
         for(IProcessInterfaceInvocationGenerator piInvocationGenerator : piInvocationGenerators)
         {
            piInvocationGenerator.setComponentVisibility(externalInvocationTypeValue);
         }
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public void dispose()
   {
      if (outlineSynchronizer != null)
      {
         outlineSynchronizer.dispose();
      }
      super.dispose();
   }

   private Object getSelectedItem()
   {
      if (parametersViewer != null)
      {
         IStructuredSelection sel = (IStructuredSelection) parametersViewer
               .getSelection();
         Object selection = sel.getFirstElement();
         return selection;
      }
      return null;
   }

   public Control createBody(Composite parent)
   {      
      stackLayout = new StackLayout();
      mainComposite = FormBuilder.createComposite(parent, 1);
      comboComposite = FormBuilder.createComposite(mainComposite, 1);

      GridData gridData = new GridData();
      gridData.grabExcessVerticalSpace = false;
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      comboComposite.setLayoutData(gridData);

      combo = FormBuilder.createCombo(comboComposite, 1);
      combo.add(Diagram_Messages.COMBOBOX_Implement_Process_Interface);
      combo.add(Diagram_Messages.COMBOBOX_Provide_Process_Interface);
      combo.add(Diagram_Messages.COMBOBOX_No_Process_Interface);
      combo.addSelectionListener(new SelectionListener()
      {

         public void widgetDefaultSelected(SelectionEvent e)
         {

         }

         public void widgetSelected(SelectionEvent e)
         {
            updateModel();
            updateStackLayout();
            updateOutline();
         }

      });

      stackComposite = FormBuilder.createComposite(mainComposite, 1);
      stackComposite.setLayout(stackLayout);
      exposesComposite = createExposeComposite(stackComposite);
      implementsComposite = createImplementsPanel(stackComposite);
      stackLayout.topControl = implementsComposite;
      mainComposite.setEnabled(enablePage);
      return mainComposite;
   }

   private Composite createImplementsPanel(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      implementsTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      implementsTable.setHeaderVisible(true);
      implementsTable.setLayoutData(FormBuilder
            .createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(implementsTable);
      implementsViewer = new TableViewer(implementsTable);
      TableUtil.createColumns(implementsTable, new String[] {Diagram_Messages.COL_MD, Diagram_Messages.COL_PROCESS});
      TableUtil.setInitialColumnSizes(implementsTable, new int[] {35, 35});
      processes = collectReferencedProcessDefinitions(this.getEditor().getWorkflowModel());
      implementsViewer.setContentProvider(new ArrayContentProvider());
      implementsViewer
            .setLabelProvider(new ProcessDefinitionImplemetationLabelProvider());
      implementsViewer.setInput(processes);

      implementsTable.addSelectionListener(new SelectionListener()
      {

         public void widgetDefaultSelected(SelectionEvent e)
         {

         }

         public void widgetSelected(SelectionEvent e)
         {
            if (e.item != null)
            {
               TableItem tableItem = (TableItem) e.item;
               if (tableItem.getData() != null)
               {
                  ProcessDefinitionType referencedProcess = (ProcessDefinitionType) tableItem
                        .getData();
                  ModelType referencedModel = (ModelType) referencedProcess.eContainer();
                  ExternalPackage packageRef = getEditor().getWorkflowModel()
                        .getExternalPackages()
                        .getExternalPackage(referencedModel.getId());
                  IdRef idRef = CarnotWorkflowModelFactory.eINSTANCE.createIdRef();
                  idRef.setRef(referencedProcess.getId());
                  idRef.setPackageRef(packageRef);
                  process.setExternalRef(idRef);

                  parameterMappings = ExtensionsFactory.eINSTANCE
                        .createFormalParameterMappingsType();

                  for (Iterator<FormalParameterMappingType> i = referencedProcess
                        .getFormalParameterMappings().getMapping().iterator(); i
                        .hasNext();)
                  {
                     FormalParameterMappingType mappingType = i.next();
                     parameterMappings.setMappedData(mappingType.getParameter(), null);
                  }
                  process.setFormalParameterMappings(parameterMappings);
                  updateOutline();
               }
            }
         }

      });

      return composite;
   }
   
   private void evaluateCheckBoxSelection()
   {
      String externalInvocationTypeValue = null;
      if (wsdlButton.getSelection() && restButton.getSelection())
      {
         externalInvocationTypeValue = BOTH_INVOCATION_TYPES;
      }
      else if (!wsdlButton.getSelection() && restButton.getSelection())
      {
         externalInvocationTypeValue = REST_INVOCATION_TYPE;
      }
      else if (wsdlButton.getSelection() && !restButton.getSelection())
      {
         externalInvocationTypeValue = SOAP_INVOCATION_TYPE;
      }
      AttributeUtil.setAttribute(process, "carnot:engine:externalInvocationType", //$NON-NLS-1$
            externalInvocationTypeValue);
      for(IProcessInterfaceInvocationGenerator piInvocationGenerator : piInvocationGenerators)
      {
         piInvocationGenerator.setComponentVisibility(externalInvocationTypeValue);
      }
      validatePage();
   }

   private Composite createExposeComposite(Composite parent)
   {
   
      TabFolder tabFolder = new TabFolder (parent, SWT.NONE);
      tabFolder.addSelectionListener(new SelectionListener() {

         public void widgetDefaultSelected(SelectionEvent e)
         {
         }

         public void widgetSelected(SelectionEvent e)
         {
            validatePage();
         }
         
      });
      
      TabItem definitionItem = new TabItem (tabFolder, SWT.NULL);
      definitionItem.setText(Diagram_Messages.LB_PROCESSINTERFACE_FORMAL_PARAMETERS);
      
      TabItem wsdlItem = new TabItem(tabFolder, SWT.NULL);
      wsdlItem.setText(Diagram_Messages.LB_PROCESSINTERFACE_REMOTE_INVOCATION);
      

      Composite exposeComposite = FormBuilder.createComposite(tabFolder, 2);
      definitionItem.setControl(exposeComposite);

      wsdlComposite = FormBuilder.createComposite(tabFolder, 3);
      wsdlItem.setControl(wsdlComposite);

      wsdlLabel = FormBuilder.createLabelWithLeftAlignedStatus(wsdlComposite, Diagram_Messages.LBL_INVOCATION_TYPE);
      
      wsdlButton = FormBuilder.createCheckBox(wsdlComposite, Diagram_Messages.BUT_BOX_SOAP);
      wsdlButton.addSelectionListener(new SelectionListener() {

         public void widgetDefaultSelected(SelectionEvent e)
         {            
         }

         public void widgetSelected(SelectionEvent e)
         {
            evaluateCheckBoxSelection();
         }
         
      });
      
      restButton = FormBuilder.createCheckBox(wsdlComposite, Diagram_Messages.BUT_BOX_REST);
      
      restButton.addSelectionListener(new SelectionListener() {

         public void widgetDefaultSelected(SelectionEvent e)
         {
         }

         public void widgetSelected(SelectionEvent e)
         {
            evaluateCheckBoxSelection();
         }
         
      });

      IProcessDefinitionTypeProvider processProvider = new IProcessDefinitionTypeProvider()
      {
         public ProcessDefinitionType getProcessDefinitionType()
         {
            return process;
         }
      };
      
      for(IProcessInterfaceInvocationGenerator piInvocationGenerator : piInvocationGenerators)
      {
         piInvocationGenerator.createExposeComposite(wsdlComposite, processProvider);
      }
      
      Table table = new Table(exposeComposite, SWT.BORDER | SWT.FULL_SELECTION);
      table.setHeaderVisible(true);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(table);
      table.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateButtons(getSelectedItem(), buttons);
         }
      });
      table.addMouseListener(new MouseAdapter()
      {
         public void mouseDoubleClick(MouseEvent e)
         {
            Object selection = getSelectedItem();
            if (selection instanceof FormalParameterType)
            {
               selectPageForObject(selection);
            }
         }
      });

      parametersViewer = new TableViewer(table);
      TableUtil.createColumns(table, new String[] {
            Diagram_Messages.COL_NAME_Id, Diagram_Messages.COL_NAME_Name,
            Diagram_Messages.COL_NAME_Mode});
      TableUtil.setInitialColumnSizes(table, new int[] {35, 35, 30});
      labelProvider = new EObjectLabelProvider(getEditor())
      {
         public String getText(String name, Object element)
         {
            FormalParameterType parameterType = (FormalParameterType) element;
            if (("id").equalsIgnoreCase(name)) //$NON-NLS-1$
            {
               return parameterType.getId();
            }
            if (("name").equalsIgnoreCase(name)) //$NON-NLS-1$
            {
               parameterType.getName();
            }
            if (("mode").equalsIgnoreCase(name)) //$NON-NLS-1$
            {
               if (parameterType.getMode().equals(ModeType.IN))
               {
                  return "IN"; //$NON-NLS-1$
               }
               if (parameterType.getMode().equals(ModeType.OUT))
               {
                  return "OUT"; //$NON-NLS-1$
               }
               if (parameterType.getMode().equals(ModeType.INOUT))
               {
                  return "INOUT"; //$NON-NLS-1$
               }
            }
            return super.getText(name, element);
         }
      };
      TableUtil.setLabelProvider(parametersViewer, labelProvider, new String[] {
            "id", "name", "mode"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      parametersViewer.setContentProvider(new ModelElementsTableContentProvider(
            XpdlPackage.eINSTANCE.getFormalParametersType_FormalParameter(),
            elementFeatureIds, labelProperties));

      outlineSynchronizer = new ModelElementsOutlineSynchronizer(
            new DefaultOutlineProvider(this, XpdlPackage.eINSTANCE
                  .getFormalParametersType_FormalParameter(), XpdlPackage.eINSTANCE
                  .getFormalParameterType_Id(), XpdlPackage.eINSTANCE
                  .getFormalParameterType_Name(), FORMAL_PARAMETERS_ID,
                  FormalParameterPropertyPage.class.getName()));

      addModelElementsOutlineSynchronizer(outlineSynchronizer);

      verticalButtonsComposite = FormBuilder.createComposite(exposeComposite, 1);

      return tabFolder;
   }

   public void contributeVerticalButtons(Composite parent)
   {
      buttons = createButtons(verticalButtonsComposite);
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         updateButtons(getSelectedItem(), buttons);
         validatePage();
      }      
      super.setVisible(visible);
   }

   public void updateButtons(Object selection, Button[] buttons)
   {
      this.selection = selection;

      for (int i = 0; i < buttons.length; i++)
      {
         if (buttons[i].isDisposed())
         {
            return;
         }
      }
      buttons[ADD_BUTTON].setEnabled(true);
      buttons[DELETE_BUTTON].setEnabled(selection instanceof FormalParameterType);
      buttons[UP_BUTTON].setEnabled(selection instanceof FormalParameterType
            && canMoveUp((FormalParameterType) selection));
      buttons[DOWN_BUTTON].setEnabled(selection instanceof FormalParameterType
            && canMoveDown((FormalParameterType) selection));
   }

   private boolean canMoveUp(FormalParameterType selection)
   {
      if (selection instanceof Proxy)
      {
         Proxy proxy = (Proxy) selection;
         InvocationHandler ih = Proxy.getInvocationHandler(proxy);
         
         Object value = Reflect.getFieldValue(ih, "val$element"); //$NON-NLS-1$
         if (value != null)
         {
            selection = (FormalParameterType) value;
         }
      }      
      
      ProcessDefinitionType pd = (ProcessDefinitionType) selection.eContainer()
            .eContainer();
      if (pd == null || pd.getFormalParameters() == null
            || pd.getFormalParameters().getFormalParameter() == null)
      {
         return false;
      }
      return pd.getFormalParameters().getFormalParameter().indexOf(selection) > 0;
   }

   private boolean canMoveDown(FormalParameterType selection)
   {
      if (selection instanceof Proxy)
      {
         Proxy proxy = (Proxy) selection;
         InvocationHandler ih = Proxy.getInvocationHandler(proxy);
         
         Object value = Reflect.getFieldValue(ih, "val$element"); //$NON-NLS-1$
         if (value != null)
         {
            selection = (FormalParameterType) value;
         }
      }      
      
      ProcessDefinitionType pd = (ProcessDefinitionType) selection.eContainer()
            .eContainer();
      if (pd == null || pd.getFormalParameters() == null
            || pd.getFormalParameters().getFormalParameter() == null)
      {
         return false;
      }
      int index = pd.getFormalParameters().getFormalParameter().indexOf(selection);
      return index >= 0
            && index < pd.getFormalParameters().getFormalParameter().size() - 1;
   }

   public Button[] createButtons(Composite parent)
   {
      final Button[] buttons = new Button[BUTTON_COUNT];

      buttons[ADD_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Add,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performAdd(buttons);
               }
            });

      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_Delete, new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performDelete(buttons);
               }
            });

      buttons[UP_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_MoveUp,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performUp(buttons);
               }
            });

      buttons[DOWN_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_MoveDown, new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performDown(buttons);
               }
            });

      return buttons;
   }

   public Object getSelection()
   {
      return selection == null ? getSelectedItem() : selection;
   }

   private void performUp(Button[] buttons)
   {
      FormalParameterType fpt = (FormalParameterType) getSelection();
      if (fpt instanceof Proxy)
      {
         Proxy proxy = (Proxy) fpt;
         InvocationHandler ih = Proxy.getInvocationHandler(proxy);
         
         Object value = Reflect.getFieldValue(ih, "val$element"); //$NON-NLS-1$
         if (value != null)
         {
            fpt = (FormalParameterType) value;
         }
      }      
            
      EList<FormalParameterType> params = process.getFormalParameters()
            .getFormalParameter();
      int index = params.indexOf(fpt);
      if (index > 0)
      {
         params.move(index - 1, index);
         updateButtons(params, buttons);
      }
   }

   private void performDown(Button[] buttons)
   {
      FormalParameterType fpt = (FormalParameterType) getSelection();
      if (fpt instanceof Proxy)
      {
         Proxy proxy = (Proxy) fpt;
         InvocationHandler ih = Proxy.getInvocationHandler(proxy);
         
         Object value = Reflect.getFieldValue(ih, "val$element"); //$NON-NLS-1$
         if (value != null)
         {
            fpt = (FormalParameterType) value;
         }
      }            
      
      EList<FormalParameterType> params = process.getFormalParameters()
            .getFormalParameter();
      int index = params.indexOf(fpt);
      if (index >= 0 && index < params.size() - 1)
      {
         params.move(index + 1, index);
         updateButtons(fpt, buttons);
      }
   }

   private void performDelete(Button[] buttons)
   {
      FormalParameterType fpt = (FormalParameterType) getSelection();
      if (fpt instanceof Proxy)
      {
         for (Iterator<FormalParameterType> i = process.getFormalParameters()
               .getFormalParameter().iterator(); i.hasNext();)
         {
            FormalParameterType t = i.next();
            if (t.getId() == null && fpt.getId() == null)
            {
               fpt = t;
            }
            else
            {
               if (t.getId().equalsIgnoreCase(fpt.getId()))
               {
                  fpt = t;
               }
            }
         }
      }      
      process.getFormalParameters().getFormalParameter().remove(fpt);
      if (fpt.getId() == null) {
         fpt.setId("****"); //$NON-NLS-1$
      }
      process.getFormalParameterMappings().setMappedData(fpt, null);
      updateButtons(null, buttons);

      this.updateOutline();
      selectPage(ProcessInterfacePropertyPage.FORMAL_PARAMETERS_ID);
   }

   private void performAdd(Button[] buttons)
   {
      IdFactory idFactory = new IdFactory(Diagram_Messages.ID_FormalParameter,
            Diagram_Messages.BASENAME_FormalParameter, XpdlPackage.eINSTANCE
                  .getFormalParameterType(), XpdlPackage.eINSTANCE
                  .getFormalParameterType_Id(), XpdlPackage.eINSTANCE
                  .getFormalParameterType_Name());
      idFactory.computeNames(process.getFormalParameters().getFormalParameter());
      XpdlFactory xpdlFactory = XpdlPackage.eINSTANCE.getXpdlFactory();
      FormalParameterType parameterType = xpdlFactory.createFormalParameterType();
      parameterType.setId(idFactory.getId());
      parameterType.setName(idFactory.getName());
      formalParameters.addFormalParameter(parameterType);
      if (preselect)
      {
         selectPageForObject(parameterType);
      }
   }

   private List<ProcessDefinitionType> collectReferencedProcessDefinitions(ModelType model)
   {
      final List<ProcessDefinitionType> processesList = CollectionUtils.newList();
      forEachReferencedModel(model, new Predicate<ModelType>()
      {
         public boolean accept(ModelType externalModel)
         {
            for (ProcessDefinitionType externalProcess : externalModel.getProcessDefinition())
            {
               if (externalProcess.getFormalParameters() != null)
               {
                  processesList.add(externalProcess);                              
               }
            }
            return true;
         }
      });
      return processesList;
   }

   private ProcessDefinitionType findProcess(IdRef externalReference)
   {
      for (Iterator<ProcessDefinitionType> i = processes.iterator(); i.hasNext();)
      {
         ProcessDefinitionType proc = i.next();
         ModelType refModel = (ModelType) proc.eContainer();
         String pid = externalReference.getRef();
         String mid = externalReference.getPackageRef().getId();
         if (refModel.getId().equalsIgnoreCase(mid) && proc.getId().equalsIgnoreCase(pid))
         {
            return proc;
         }
      }
      return null;
   }

   private void updateStackLayout()
   {
      if (combo.getSelectionIndex() == 1)
      {
         isProviding = true;
         isImplementing = false;
         stackLayout.topControl = exposesComposite;
      }
      if (combo.getSelectionIndex() == 0)
      {
         isProviding = false;
         isImplementing = true;
         stackLayout.topControl = implementsComposite;
      }
      if (combo.getSelectionIndex() == 2)
      {
         isProviding = false;
         isImplementing = false;
         stackLayout.topControl = null;
      }
      stackComposite.layout();
   }

   private void updateModel()
   {
      if (combo.getSelectionIndex() == 0)
      {
         isProviding = false;
         isImplementing = true;
         this.outlineSynchronizer.init(null);
      }
      if (combo.getSelectionIndex() == 1)
      {
         formalParameters = process.getFormalParameters();
         if (formalParameters == null)
         {
            formalParameters = XpdlFactory.eINSTANCE.createFormalParametersType();
            process.setFormalParameters(formalParameters);
         }
         parameterMappings = process.getFormalParameterMappings();
         if (parameterMappings == null)
         {
            parameterMappings = ExtensionsFactory.eINSTANCE
                  .createFormalParameterMappingsType();
            process.setFormalParameterMappings(parameterMappings);
         }
      }

   }

   public void setEnablePage(boolean enablePage)
   {
      this.enablePage = enablePage;
   }
   
   private void validatePage()
   {
      String validationProblem = null;
      if (process != null
            && process.getFormalParameters() != null
            && AttributeUtil
                  .getAttribute(process, "carnot:engine:externalInvocationType") != null) //$NON-NLS-1$
      {
         for (Iterator<FormalParameterType> i = process.getFormalParameters()
               .getFormalParameter().iterator(); i.hasNext();)
         {
            FormalParameterType parameterType = i.next();
            String category = parameterType.getDataType().getCarnotType();
            if (category == null)
            {
               if (parameterType.getDataType().getBasicType() != null)
               {
                  category = "primitive"; //$NON-NLS-1$
               }
               if (parameterType.getDataType().getDeclaredType() != null)
               {
                  category = "struct"; //$NON-NLS-1$
               }
            }
            else
            {
               category = parameterType.getDataType().getCarnotType();
            }
            if (!"struct".equals(category) && !"primitive".equals(category)) //$NON-NLS-1$ //$NON-NLS-2$
            {
               validationProblem = Diagram_Messages.MODEL_ProcessInterface_InvalidForExternalInvocation;
            }
            else
            {
               if ("primitive".equals(category)) //$NON-NLS-1$
               {
                  DataType dataType = process.getFormalParameterMappings().getMappedData(
                        parameterType);
                  String typeValue = AttributeUtil.getAttributeValue(dataType,
                        PredefinedConstants.TYPE_ATT);
                  if (typeValue.equals(Type.Calendar.getId())
                        || typeValue.equals(Type.Timestamp.getId()))
                  {
                     validationProblem = Diagram_Messages.MODEL_ProcessInterface_InvalidForExternalInvocation;
                  }
               }
            }
         }
      }
      IQuickValidationStatus status = null;
      if (validationProblem != null && restButton != null && wsdlButton != null)
      {
         status = IQuickValidationStatus.ERRORS;
         wsdlLabel.setValidationStatus(status);
         wsdlLabel.setToolTipText(validationProblem);
      }
      else
      {
         if (restButton != null && wsdlButton != null)
         {
            status = IQuickValidationStatus.OK;
            wsdlLabel.setValidationStatus(status);
            wsdlLabel.getLabel().setToolTipText(null);
         }
      }
      for(IProcessInterfaceInvocationGenerator piInvocationGenerator : piInvocationGenerators)
      {
         piInvocationGenerator.handleValidationStatusFromParent(status);
      }
   }   
}