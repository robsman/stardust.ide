/*******************************************************************************
 * Copyright (c) 2011 - 2012 SunGard CSA 
 *******************************************************************************/

package org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.wsdl.BindingOperation;
import javax.wsdl.Fault;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.xml.namespace.QName;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.ModelElementAdaptable;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.ui.IWorkbenchPropertyPage;


public class JaxWSOutlineSynchronizer
{
   private static final String BASE_ID = "_cwm_spi_application_"; //$NON-NLS-1$
   private static final String PARTS_NODE = "_cwm_parts_"; //$NON-NLS-1$
   private static final String TYPES_NODE = "_cwm_types_"; //$NON-NLS-1$
   private static final String TEMPLATES_NODE = "_cwm_templates_"; //$NON-NLS-1$
   
   private static final ConfigHolder<?>[] NAMES = new ConfigHolder[]
   {
      new ConfigHolder<JaxWSPartsPropertyPage>(PARTS_NODE, Diagram_Messages.CONFIG_Parts, JaxWSPartsPropertyPage.class),
      new ConfigHolder<JaxWSTypesPropertyPage>(TYPES_NODE, Diagram_Messages.ELEMENT_TypeMappings, JaxWSTypesPropertyPage.class),
      new ConfigHolder<JaxWSTemplatesPropertyPage>(TEMPLATES_NODE, Diagram_Messages.ELEMENT_XMLTemplates, JaxWSTemplatesPropertyPage.class)
   };
   
   private JaxWSPropertyPage page;
   private BindingOperation operation;
   
   /**
    * contains the parts that are java mapped.
    * valid for both in and out parts
    */
   private Set<Part> mappedParts = new HashSet<Part>();
   
   /**
    * contains the parts that are xml mapped
    * valid only for in parts
    */
   private Set<Part> xmlParts = new HashSet<Part>();

   /**
    * contains the set of defined user mappings.
    * the values represent java package names
    */
   private Map<Object, String> javaValues = new HashMap<Object, String>();
   
   /**
    * contains the templates for the xml parts
    */
   private Map<Object, String> xmlValues = new HashMap<Object, String>();

   public JaxWSOutlineSynchronizer(JaxWSPropertyPage page)
   {
      this.page = page;
   }
   
   public void init(ApplicationType target)
   {
      for (Iterator<AttributeType> i = target.getAttribute().iterator(); i.hasNext();)
      {
         AttributeType attribute = i.next();
         checkAddValue(attribute, CarnotConstants.MAPPING_PREFIX, javaValues);
         checkAddValue(attribute, CarnotConstants.TEMPLATE_PREFIX, xmlValues);
      }
   }
   
   private void checkAddValue(AttributeType attribute, String prefix, Map<Object, String> map)
   {
      if (attribute.getName().startsWith(prefix))
      {
         String key = attribute.getName().substring(prefix.length());
         String value = attribute.getValue();
         if (value != null && value.trim().length() > 0)
         {
            map.put(key, value.trim());
         }
      }
   }

   public void setOperation(BindingOperation operation)
   {
      this.operation = operation;
      
      mappedParts.clear();
      xmlParts.clear();

      checkMappings();

      updatePage(PARTS_NODE);
      updateChildPages(TYPES_NODE, JaxWSMappingPropertyPage.class, mappedParts);
      updateChildPages(TEMPLATES_NODE, JaxWSXmlTemplatePropertyPage.class, xmlParts);
      
      page.refreshTree();
   }

   private void updatePage(String id)
   {
      String label = null;
      final Class<?>[] propertyPageClass = new Class[1];
      for (int i = 0; i < NAMES.length; i++)
      {
         if (NAMES[i].id.equals(id))
         {
            label = NAMES[i].name;
            propertyPageClass[0] = NAMES[i].propertyPageClass;
            break;
         }
      }
      String composedId = page.composePageId(BASE_ID, id);
      IPreferenceNode node = page.getNode(composedId);
      if (node == null)
      {
         ConfigurationElement config = ConfigurationElement.createPageConfiguration(
            id, label, null, propertyPageClass[0].getName());
         node = new CarnotPreferenceNode(config, new ModelElementAdaptable(
            new Class[] {IModelElement.class, JaxWSOutlineSynchronizer.class},
            new Object[] {page.getApplication(), this},
            page.getElement()))
         {
            public void createPage()
            {
               try
               {
                  IWorkbenchPropertyPage page = (IWorkbenchPropertyPage) propertyPageClass[0].newInstance();
                  internalSetPage(page);
               }
               catch (Exception e)
               {
                  ErrorDialog.openError(page.getShell(), "Exception", e.getMessage(), //$NON-NLS-1$
                        Status.CANCEL_STATUS);
               }
            }
         };
         page.addNodeTo(BASE_ID, (CarnotPreferenceNode) node, null);
      }
      else
      {
         AbstractModelElementPropertyPage page =
            (AbstractModelElementPropertyPage) node.getPage();
         page.elementChanged();
      }
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   private void checkMappings()
   {
      if (operation == null)
      {
         return;
      }
      Operation op = operation.getOperation();
      
      checkMappings(op.getInput() == null ? null : op.getInput().getMessage(), "input", DirectionType.IN_LITERAL); //$NON-NLS-1$
      checkMappings(op.getOutput() == null ? null : op.getOutput().getMessage(), "output", DirectionType.OUT_LITERAL); //$NON-NLS-1$
      for (Iterator<Fault> i = op.getFaults().values().iterator(); i.hasNext();)
      {
         Fault fault = i.next();
         checkMappings(fault.getMessage(), "fault:" + fault.getName(), DirectionType.OUT_LITERAL); //$NON-NLS-1$
      }
      
      for (Iterator<Part> i = xmlParts.iterator(); i.hasNext();)
      {
         Part part = i.next();
         checkReplace("input", part, xmlValues); //$NON-NLS-1$
      }
   }

   @SuppressWarnings("unchecked") //$NON-NLS-1$
   private void checkMappings(Message message, String category, DirectionType direction)
   {
      if (message != null)
      {
         for (Iterator<Part> i = message.getParts().values().iterator(); i.hasNext();)
         {
            Part part = i.next();
            if (!isDefault(part))
            {
               checkReplace(category, part, javaValues); //$NON-NLS-1$
               setMappedLocal(part, javaValues.containsKey(part), direction);
            }
         }
      }
   }

   private void checkReplace(String prefix, Part part, Map<Object, String> where)
   {
      String name = prefix + ":" + part.getName(); //$NON-NLS-1$
      if (!where.containsKey(part) && where.containsKey(name))
      {
         String value = where.remove(name);
         where.put(part, value);
      }
   }

   public BindingOperation getOperation()
   {
      return operation;
   }

   public void setMappedLocal(Part part, boolean mapped, DirectionType direction)
   {
      if (mapped)
      {
         mappedParts.add(part);
      }
      else
      {
         mappedParts.remove(part);
      }
      
      // only IN parameters can have a template
      if (direction.getValue() == DirectionType.IN
            || direction.getValue() == DirectionType.INOUT)
      {
         if (mapped)
         {
            xmlParts.remove(part);
         }
         else
         {
            xmlParts.add(part);
         }
      }
   }

   public void setMapped(Part part, boolean mapped, DirectionType direction)
   {
      setMappedLocal(part, mapped, direction);
      updateChildPages(TYPES_NODE, JaxWSMappingPropertyPage.class, mappedParts);
      updateChildPages(TEMPLATES_NODE, JaxWSXmlTemplatePropertyPage.class, xmlParts);
      page.refreshTree();
   }

   private <T> void updateChildPages(String rootPageId, final Class<T> pageClass, Set<Part> parts)
   {
      String composedId = page.composePageId(BASE_ID, rootPageId);
      if (!parts.isEmpty())
      {
         updatePage(rootPageId);
         IPreferenceNode node = page.getNode(composedId);
         IPreferenceNode[] children = node.getSubNodes();
         // remove obsolete template nodes
         Set<Part> partSet = new HashSet<Part>();
         for (int i = 0; i < children.length; i++)
         {
            Part part = (Part) ((CarnotPreferenceNode)children[i])
               .getAdaptable().getAdapter(Part.class);
            if (parts.contains(part))
            {
               partSet.add(part);
            }
            else
            {
               page.removePreferenceNodes(
                     page.composePageId(composedId, part.getName()), true);
            }
         }
         // add new template nodes
         for (Iterator<Part> i = parts.iterator(); i.hasNext();)
         {
            Part part = i.next();
            if (!partSet.contains(part))
            {
               ConfigurationElement config = ConfigurationElement.createPageConfiguration(
                  part.getName(), part.getName(), null, pageClass.getName());
               CarnotPreferenceNode child = new CarnotPreferenceNode(config, new ModelElementAdaptable(
                  new Class[] {IModelElement.class, JaxWSOutlineSynchronizer.class, Part.class},
                  new Object[] {page.getApplication(), this, part},
                  page.getElement()))
               {
                  public void createPage()
                  {
                     try
                     {
                        IWorkbenchPropertyPage page = (IWorkbenchPropertyPage) pageClass.newInstance();
                        internalSetPage(page);
                     }
                     catch (Exception e)
                     {
                        ErrorDialog.openError(page.getShell(), "Exception", e.getMessage(), //$NON-NLS-1$
                              Status.CANCEL_STATUS);
                     }
                  }
               };
               page.addNodeTo(composedId, (CarnotPreferenceNode) child, null);
            }
         }
      }
      else
      {
         page.removePreferenceNodes(composedId, true);
      }
   }

   public boolean isDefault(Part part)
   {
      QName name = JaxWSResource.getType(part);
      return JaxWSResource.getDefaultMappedClass(name) != null;
   }

   public boolean isMapped(Part part)
   {
      return mappedParts.contains(part);
   }

   public String getTemplate(Part part)
   {
      String value = xmlValues.get(part);
      // never return null.
      return value == null ? "" : value; //$NON-NLS-1$
   }

   public void setTemplate(Part part, String value)
   {
      if (value.length() == 0)
      {
         xmlValues.remove(part);
      }
      else
      {
         xmlValues.put(part, value);
      }
      updatePage(TEMPLATES_NODE);
   }

   public Set<Part> getXmlParts()
   {
      return xmlParts;
   }

   public Set<Part> getMappedParts()
   {
      return mappedParts;
   }

   public String getMapping(Part part)
   {
      String value = javaValues.get(part);
      // never return null. An empty string represents an invalid mapping
      return value == null ? "" : value; //$NON-NLS-1$
   }

   public void setMapping(Part part, String value)
   {
      if (value.length() == 0)
      {
         javaValues.remove(part);
      }
      else
      {
         javaValues.put(part, value);
      }
      updatePage(TYPES_NODE);
   }
   
   private static class ConfigHolder<T>
   {
      private String id;
      private String name;
      private Class<T> propertyPageClass;
      
      public ConfigHolder(String id, String name, Class<T> propertyPageClass)
      {
         this.id = id;
         this.name = name;
         this.propertyPageClass = propertyPageClass;
      }
   }

   public String getWsdlLocation()
   {
      return page.getWsdlLocation();
   }
}
