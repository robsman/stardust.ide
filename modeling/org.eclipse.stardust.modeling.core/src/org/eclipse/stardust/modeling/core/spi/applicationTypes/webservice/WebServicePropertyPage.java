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
package org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.EditPart;
import org.eclipse.stardust.engine.extensions.jaxws.app.WSConstants;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

public class WebServicePropertyPage extends AbstractModelElementPropertyPage
{
   private static final String BASE_ID = "_cwm_spi_application_"; //$NON-NLS-1$
   
   private Map delegates = new HashMap();
   private Map bodies = new HashMap();
   private Button radioJaxws;
   private Button radioCxf;
   private StackLayout stackLayout;
   private Composite bodyStack;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      AbstractModelElementPropertyPage delegate = getCreateDelegate();
      delegate.loadFieldsFromElement(symbol, element);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      AbstractModelElementPropertyPage delegate = getCreateDelegate();
      delegate.loadElementFromFields(symbol, element);
   }

   public Object getAdapter(Class adapter)
   {
      AbstractModelElementPropertyPage delegate = getCreateDelegate();
      return delegate.getAdapter(adapter);
   }

   public Control createBody(Composite parent)
   {
      Composite bodyComposite = FormBuilder.createComposite(parent, 1);
      if (isRuntimeSelectionActive())
      {
         Group versionGroup = FormBuilder.createGroup(bodyComposite, "Runtime: ", 2); //$NON-NLS-1$
         versionGroup.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData());
         radioJaxws = FormBuilder.createRadioButton(versionGroup, Diagram_Messages.BUT_JAX);
         radioJaxws.addSelectionListener(new SelectionListener() {
               public void widgetDefaultSelected(SelectionEvent e)
               {
               }
   
               public void widgetSelected(SelectionEvent e)
               {
                  if (radioJaxws.getSelection())
                  {
                     setRuntime(WSConstants.JAXWS_RUNTIME);
                  }
               }
         });
         radioCxf = FormBuilder.createRadioButton(versionGroup, Diagram_Messages.BUT_JAXWS_CXF);
         radioCxf.setVisible(false); // TODO enable CXF specific handling
         radioCxf.addSelectionListener(new SelectionListener() {
               public void widgetDefaultSelected(SelectionEvent e)
               {
               }
      
               public void widgetSelected(SelectionEvent e)
               {
                  if (radioCxf.getSelection())
                  {
                     // TODO enable CXF specific handling
                     // setRuntime(WSConstants.JAXWS_CXF_RUNTIME);
                  }
               }
         });
      }
      String runtime = getSetRuntimeAttribute();
      if (isRuntimeSelectionActive())
      {
         radioJaxws.setSelection(WSConstants.JAXWS_RUNTIME.equals(runtime));
         // radioCxf.setSelection(WSConstants.JAXWS_CXF_RUNTIME.equals(runtime));
      }
      bodyStack = FormBuilder.createComposite(bodyComposite, 1);
      stackLayout = new StackLayout();
      bodyStack.setLayout(stackLayout);
      setCreateBody();
      return bodyComposite;
   }
   
   private boolean isRuntimeSelectionActive()
   {
      return "true".equalsIgnoreCase(System.getProperty("moonglow.includeAxisSupport", "true")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   private void setCreateBody()
   {
      String style = getSetRuntimeAttribute();
      Control body = (Control) bodies.get(style);
      if (body == null)
      {
         AbstractModelElementPropertyPage delegate = getCreateDelegate();
         body = delegate.createBody(bodyStack);
         bodies.put(style, body);
      }
      stackLayout.topControl = body;
      bodyStack.layout();
   }

   protected void setRuntime(String version)
   {
      ApplicationType app = (ApplicationType) getModelElement();
      AttributeUtil.setAttribute(app, WSConstants.RUNTIME_ATT, version);
      // TODO: move / clear attributes
      boolean mustInitialize = true; //!delegates.containsKey(version);
      removePreferenceNodes(BASE_ID);
      setCreateBody();
      if (mustInitialize)
      {
         AbstractModelElementPropertyPage delegate = getCreateDelegate();
         IModelElementNodeSymbol symbol = getModelElementNodeSymbol();
         IModelElement element = getModelElementFromSymbol(symbol);
         delegate.loadFieldsFromElement(symbol, element);
      }
   }

   private AbstractModelElementPropertyPage getCreateDelegate()
   {
      String style = getSetRuntimeAttribute();
      AbstractModelElementPropertyPage delegate = 
         (AbstractModelElementPropertyPage) delegates.get(style);
      if (delegate == null)
      {
         try
         {
            // TODO: declare pages!
            delegate = (AbstractModelElementPropertyPage) SpiExtensionRegistry.createPropertyPage(
               "_cwm_spi_application_", style); //$NON-NLS-1$
            delegate.setContainer(getContainer());
            delegate.setElement(getElement());
         }
         catch (Exception ex)
         {
            // default to JAXWS ?
            // radioJaxws.setEnabled(false);
            // TODO: return a dummy 3.0 page
         }
         /*if (delegate == null)
         {
            delegate = new SessionBean20PropertyPage();
         }*/
         delegates.put(style, delegate);
      }
      return delegate;
   }

   protected ApplicationType getApplication(IAdaptable adaptable)
   {
      Object element = adaptable;
      if (element instanceof EditPart)
      {
         element = ((EditPart) element).getModel();
      }
      if (element instanceof IModelElementNodeSymbol)
      {
         element = ((IModelElementNodeSymbol) element).getModelElement();
      }
      return element instanceof ApplicationType ? (ApplicationType) element : null;
   }

   private String getSetRuntimeAttribute()
   {
      String style = WSConstants.JAXWS_RUNTIME;
      ApplicationType app = (ApplicationType) getModelElement();
      if (app.getAttribute().isEmpty())
      {
         // new created apps are by default jaxws
         AttributeUtil.setAttribute(app, WSConstants.RUNTIME_ATT, style);
      }
      else
      {
         style = AttributeUtil.getAttributeValue(app, WSConstants.RUNTIME_ATT);
         if (style == null)
         {
            // old style app (not yet deleted for reference)
            // String wsStyle = AttributeUtil.getAttributeValue(app, WSConstants.WS_BINDING_STYLE_ATT);
            // style = wsStyle == null ? WSConstants.JAXWS_RUNTIME : WSConstants.AXIS_RUNTIME;
            AttributeUtil.setAttribute(app, WSConstants.RUNTIME_ATT, style);
         }
      }
      return style;
   }
/*   
   public void apply()
   {
      delegate.apply();
   }

   public void applyData(Object data)
   {
      delegate.applyData(data);
   }

   public Point computeSize()
   {
      return delegate.computeSize();
   }

   public void contributeButtons(Composite parent)
   {
      delegate.contributeButtons(parent);
   }

   public void contributeVerticalButtons(Composite parent)
   {
      delegate.contributeVerticalButtons(parent);
   }

   public void dispose()
   {
      delegate.dispose();
      super.dispose();
   }

   public void elementChanged()
   {
      delegate.elementChanged();
   }

   public IPreferencePageContainer getContainer()
   {
      return delegate.getContainer();
   }

   public String getDescription()
   {
      return delegate.getDescription();
   }

   public IAdaptable getElement()
   {
      return delegate.getElement();
   }

   public String getErrorMessage()
   {
      return delegate.getErrorMessage();
   }

   public Image getImage()
   {
      return delegate.getImage();
   }

   public String getMessage()
   {
      return delegate.getMessage();
   }

   public int getMessageType()
   {
      return delegate.getMessageType();
   }

   public PreferenceManager getPreferenceManager()
   {
      return delegate.getPreferenceManager();
   }

   public IPreferenceStore getPreferenceStore()
   {
      return delegate.getPreferenceStore();
   }

   public String getTitle()
   {
      return delegate.getTitle();
   }

   public boolean isValid()
   {
      return delegate.isValid();
   }

   public boolean okToLeave()
   {
      return delegate.okToLeave();
   }

   public boolean performCancel()
   {
      return delegate.performCancel();
   }

   public void performHelp()
   {
      delegate.performHelp();
   }

   public boolean performOk()
   {
      return delegate.performOk();
   }

   public void setContainer(IPreferencePageContainer container)
   {
      delegate.setContainer(container);
   }

   public void setDescription(String description)
   {
      delegate.setDescription(description);
   }

   public void setElement(IAdaptable element)
   {
      createDelegate(element);
      delegate.setElement(element);
   }

   private void createDelegate(IAdaptable element)
   {
      ApplicationType app = getApplication(element);
      if (app != null)
      {
         String style = AttributeUtil.getAttributeValue(app, STYLE_ATT);
         if (style == null)
         {
            delegate = (AbstractModelElementPropertyPage) SpiExtensionRegistry.createPropertyPage(
                  "_cwm_spi_application_", "jaxwswebservice"); //$NON-NLS-1$ //$NON-NLS-2$
         }
      }
      if (delegate == null)
      {
         delegate = new AxisWebServicePropertyPage();
      }
   }

   public void setErrorMessage(String newMessage)
   {
      delegate.setErrorMessage(newMessage);
   }

   public void setImageDescriptor(ImageDescriptor desc)
   {
      delegate.setImageDescriptor(desc);
   }

   public void setMessage(String newMessage, int newType)
   {
      delegate.setMessage(newMessage, newType);
   }

   public void setMessage(String newMessage)
   {
      delegate.setMessage(newMessage);
   }

   public void setPreferenceStore(IPreferenceStore store)
   {
      delegate.setPreferenceStore(store);
   }

   public void setSize(Point uiSize)
   {
      super.setSize(uiSize);
      delegate.setSize(uiSize);
   }

   public void setTitle(String title)
   {
      super.setTitle(title);
      delegate.setTitle(title);
   }

   public void setValid(boolean b)
   {
      super.setValid(b);
      delegate.setValid(b);
   }*/
}
