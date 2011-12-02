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
package org.eclipse.stardust.modeling.integration.webservices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

import javax.xml.registry.BulkResponse;
import javax.xml.registry.BusinessLifeCycleManager;
import javax.xml.registry.BusinessQueryManager;
import javax.xml.registry.Connection;
import javax.xml.registry.ConnectionFactory;
import javax.xml.registry.FindQualifier;
import javax.xml.registry.JAXRException;
import javax.xml.registry.RegistryService;
import javax.xml.registry.infomodel.InternationalString;
import javax.xml.registry.infomodel.Organization;
import javax.xml.registry.infomodel.RegistryObject;
import javax.xml.registry.infomodel.Service;
import javax.xml.registry.infomodel.ServiceBinding;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;


public class UddiRegistryBrowserWizard extends Wizard
{
   private String uddiRegistryUrl;
   private Connection connection;
   
   private String bindingKey;
   private String bindingAccessPoint;
   
   private ServiceSelectionPage servicePage;
   private BindingSelectionPage bindingPage;

   public UddiRegistryBrowserWizard(String uddiUrl)
   {
      uddiRegistryUrl = uddiUrl;
      setWindowTitle(Webservices_Messages.UddiRegistryBrowserWizard_WindowTitle); 
      addPage(new BusinessSearchPage());
      addPage(servicePage = new ServiceSelectionPage());
      addPage(bindingPage = new BindingSelectionPage());
   }

   public void updateBinding(ServiceBinding binding)
   {
      try
      {
         bindingKey = binding.getKey().getId();
         bindingAccessPoint = binding.getAccessURI();
      }
      catch (JAXRException e)
      {
         e.printStackTrace();
      }
   }

   public void dispose()
   {
      if (connection != null)
      {
         try
         {
            connection.close();
         }
         catch (JAXRException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      connection = null;
      super.dispose();
   }

   private class BindingSelectionPage extends WizardPage
   {
      private Table bindingTable;
      private TableViewer bindingViewer;

      protected BindingSelectionPage()
      {
         super(Webservices_Messages.UddiRegistryBrowserWizard_BindingPageName);
         setDescription(Webservices_Messages.UddiRegistryBrowserWizard_BindingPageDescription); 
         setPageComplete(false);
      }

      @SuppressWarnings("unchecked") //$NON-NLS-1$
      private void updateBindings(Service service)
      {
         setPageComplete(false);
         Collection<ServiceBinding> bindings = new ArrayList<ServiceBinding>();
         try
         {
            bindings = service.getServiceBindings();
         }
         catch (JAXRException e1)
         {
            e1.printStackTrace();
         }
         bindingTable.setRedraw(false);
         bindingTable.removeAll();
         int index = 1;
         for (Iterator<ServiceBinding> i = bindings.iterator(); i.hasNext(); index++)
         {
            ServiceBinding element = i.next();
            bindingViewer.add(new InfomodelWrapper(element, index));
         }
         bindingTable.setRedraw(true);
      }

      public void createControl(Composite parent)
      {
         Composite composite = FormBuilder.createComposite(parent, 1);

         bindingTable = FormBuilder.createTable(composite, 
               SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER,
               new String[] {Webservices_Messages.UddiRegistryBrowserWizard_BindingPageTypeColumnLabel, Webservices_Messages.UddiRegistryBrowserWizard_BindingPageAccessPointColumnLabel}, new int[] {30, 70}, 3);
         bindingTable.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {
               setPageComplete(bindingTable.getSelectionCount() == 1);
               if (isPageComplete())
               {
                  ServiceBinding binding = (ServiceBinding) ((InfomodelWrapper) bindingViewer.getElementAt(
                        bindingTable.getSelectionIndex())).getRegistryObject();
                  updateBinding(binding);
               }
            }
         });
         bindingViewer = new TableViewer(bindingTable);
         bindingViewer.setLabelProvider(new InfomodelLabelProvider());
         
         setControl(composite);
      }

      public void dispose()
      {
         bindingViewer = null;
         bindingTable = null;
         super.dispose();
      }
   }

   private class ServiceSelectionPage extends WizardPage
   {
      private Table serviceTable;
      private TableViewer serviceViewer;

      protected ServiceSelectionPage()
      {
         super(Webservices_Messages.UddiRegistryBrowserWizard_ServicePageName); 
         setDescription(Webservices_Messages.UddiRegistryBrowserWizard_ServicePageDescription); 
         setPageComplete(false);
      }

      public void createControl(final Composite parent)
      {
         Composite composite = FormBuilder.createComposite(parent, 2);

         serviceTable = FormBuilder.createTable(composite, 
               SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER,
               new String[] {Webservices_Messages.UddiRegistryBrowserWizard_ServicePageNameColumnLabel, Webservices_Messages.UddiRegistryBrowserWizard_ServicePageDescriptionColumnLabel}, new int[] {30, 70}, 2); 
         serviceTable.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {
               setPageComplete(serviceTable.getSelectionCount() == 1);
               if (isPageComplete())
               {
                  Service service = (Service) ((InfomodelWrapper) serviceViewer.getElementAt(
                        serviceTable.getSelectionIndex())).getRegistryObject();
                  bindingPage.updateBindings(service);
               }
            }
         });
         serviceViewer = new TableViewer(serviceTable);
         serviceViewer.setLabelProvider(new InfomodelLabelProvider());
         
         setControl(composite);
      }

      @SuppressWarnings("unchecked") //$NON-NLS-1$
      private void updateServices(Organization organization)
      {
         setPageComplete(false);
         Collection<Service> services = new ArrayList<Service>();
         try
         {
            services = organization.getServices();
         }
         catch (JAXRException e1)
         {
            e1.printStackTrace();
         }
         serviceTable.setRedraw(false);
         serviceTable.removeAll();
         int index = 1;
         for (Iterator<Service> i = services.iterator(); i.hasNext(); index++)
         {
            Service element = i.next();
            serviceViewer.add(new InfomodelWrapper(element, index));
         }
         serviceTable.setRedraw(true);
      }

      public void dispose()
      {
         serviceViewer = null;
         serviceTable = null;
         super.dispose();
      }
   }

   private Connection openConnection() throws JAXRException
   {
      ConnectionFactory factory = ConnectionFactory.newInstance();
      Properties connectionProperties = new Properties();
      connectionProperties.put("javax.xml.registry.queryManagerURL", uddiRegistryUrl); //$NON-NLS-1$
      factory.setProperties(connectionProperties);
      return factory.createConnection();
   }

   private Collection<String> createFindQualifiers()
   {
      Collection<String> findQualifiers = new ArrayList<String>();
      findQualifiers.add(FindQualifier.SORT_BY_NAME_DESC);
      return findQualifiers;
   }

   private Collection<InternationalString> createNamePatterns(
         RegistryService service, String patternText) throws JAXRException
   {
      BusinessLifeCycleManager lifeManager = service.getBusinessLifeCycleManager();
      InternationalString pattern = lifeManager.createInternationalString();
      pattern.addLocalizedString(
            lifeManager.createLocalizedString(new Locale(""), patternText)); //$NON-NLS-1$
      pattern.addLocalizedString(
            lifeManager.createLocalizedString(Locale.getDefault(), patternText));
      Collection<InternationalString> namePatterns = new ArrayList<InternationalString>();
      namePatterns.add(pattern);
      return namePatterns;
   }

   private class BusinessSearchPage extends WizardPage
   {
      private Text filterText;
      private TableViewer organizationViewer;
      private Table organizationTable;

      protected BusinessSearchPage()
      {
         super(Webservices_Messages.UddiRegistryBrowserWizard_BusinessPageName);
         setDescription(Webservices_Messages.UddiRegistryBrowserWizard_BusinessPageDescription); 
         setPageComplete(false);
      }

      public void createControl(final Composite parent)
      {
         Composite composite = FormBuilder.createComposite(parent, 2);
         
         FormBuilder.createLabel(composite, Webservices_Messages.UddiRegistryBrowserWizard_BusinessPageHint, 2); 
         filterText = FormBuilder.createText(composite);
         filterText.setText(Webservices_Messages.UddiRegistryBrowserWizard_BusinessPageDefaultFilter);
         FormBuilder.createButton(composite, Webservices_Messages.UddiRegistryBrowserWizard_SearchButtonLabel, new SelectionListener() 
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {
               BusyIndicator.showWhile(parent.getDisplay(), new Runnable()
               {
                  @SuppressWarnings("unchecked") //$NON-NLS-1$
                  public void run()
                  {
                     try
                     {
                        connection = openConnection();
                        RegistryService registryService = connection.getRegistryService();
                        BusinessQueryManager manager = registryService.getBusinessQueryManager();
                        Collection<String> findQualifiers = createFindQualifiers();
                        Collection<InternationalString> namePatterns = createNamePatterns(
                              registryService, filterText.getText());
                        BulkResponse response = manager.findOrganizations(findQualifiers,
                              namePatterns, null, null, null, null);
                        Collection<Organization> result = response.getCollection();
                        
                        organizationTable.setRedraw(false);
                        organizationTable.removeAll();
                        int index = 1;
                        for (Iterator<Organization> iter = result.iterator(); iter.hasNext(); index++)
                        {
                           Organization element = iter.next();
                           organizationViewer.add(new InfomodelWrapper(element, index));
                        }
                        organizationTable.setRedraw(true);
                     }
                     catch (JAXRException ex)
                     {
                        // TODO: display error message
                        ex.printStackTrace();
                     }
                  }
               });
            }
         });
         
         organizationTable = FormBuilder.createTable(composite, 
               SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER,
               new String[] {Webservices_Messages.UddiRegistryBrowserWizard_BusinessPageNameColumnLabel, Webservices_Messages.UddiRegistryBrowserWizard_BusinessPageDescriptionColumnLabel}, new int[] {30, 70}, 2);
         organizationTable.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {
               setPageComplete(organizationTable.getSelectionCount() == 1);
               if (isPageComplete())
               {
                  Organization organization = (Organization) ((InfomodelWrapper) organizationViewer.getElementAt(
                        organizationTable.getSelectionIndex())).getRegistryObject();
                  servicePage.updateServices(organization);
               }
            }
         });
         organizationViewer = new TableViewer(organizationTable);
         organizationViewer.setLabelProvider(new InfomodelLabelProvider());
         
         setControl(composite);
      }

      public void dispose()
      {
         organizationViewer = null;
         organizationTable = null;
         filterText = null;
         super.dispose();
      }
   }
   
   private static class InfomodelLabelProvider implements ITableLabelProvider
   {
      public Image getColumnImage(Object element, int columnIndex)
      {
         return null; // no images
      }

      public String getColumnText(Object element, int columnIndex)
      {
         InfomodelWrapper wrapper = (InfomodelWrapper) element;
         switch (columnIndex)
         {
            case 0: return wrapper.getName();
            case 1: return wrapper.getDescription();
         }
         return ""; //$NON-NLS-1$
      }

      public void addListener(ILabelProviderListener listener)
      {
         // ignore
      }

      public void dispose()
      {
         // ignore (nothing to dispose);
      }

      public boolean isLabelProperty(Object element, String property)
      {
         return false;
      }

      public void removeListener(ILabelProviderListener listener)
      {
         // ignore
      }
   }   
   private static class InfomodelWrapper
   {
      private String name;
      private String description;
      private RegistryObject registryObject;
      
      public InfomodelWrapper(RegistryObject object, int index)
      {
         this.registryObject = object;
         String type = "RegistryObject"; //$NON-NLS-1$
         if (object instanceof Organization)
         {
            type = "Organization"; //$NON-NLS-1$
         }
         else if (object instanceof Service)
         {
            type = "Service"; //$NON-NLS-1$
         }
         if (object instanceof ServiceBinding)
         {
            type = "Binding"; //$NON-NLS-1$
         }
         try
         {
            if (object instanceof ServiceBinding)
            {
               name = type + index;
            }
            else
            {
               name = object.getName().getValue();
            }
         }
         catch (JAXRException e)
         {
            name = type + index;
         }
         try
         {
            if (object instanceof ServiceBinding)
            {
               description = ((ServiceBinding) object).getAccessURI();
            }
            else
            {
               description = object.getDescription().getValue();
            }
         }
         catch (JAXRException e)
         {
            description = ""; //$NON-NLS-1$
         }
      }

      public String getDescription()
      {
         return description;
      }

      public String getName()
      {
         return name;
      }

      public RegistryObject getRegistryObject()
      {
         return registryObject;
      }
   }

   public String getBindingKey()
   {
      return bindingKey;
   }

   public String getAccessPoint()
   {
      return bindingAccessPoint;
   }

   @Override
   public boolean performFinish()
   {
      // nothing to do here
      return true;
   }
}
