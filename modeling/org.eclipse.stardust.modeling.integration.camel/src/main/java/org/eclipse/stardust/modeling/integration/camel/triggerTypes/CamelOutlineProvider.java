package org.eclipse.stardust.modeling.integration.camel.triggerTypes;

import static org.eclipse.stardust.modeling.integration.camel.Constants.*;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IFilter;

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.ModelElementPropertyDialog;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.DefaultOutlineProvider;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;

public class CamelOutlineProvider extends DefaultOutlineProvider
{
   public CamelOutlineProvider(AbstractModelElementPropertyPage page,
         EStructuralFeature elementListFeature, EStructuralFeature idFeature,
         EStructuralFeature nameFeature, String parentNodeId, String pageClassName)
   {
      super(page, elementListFeature, idFeature, nameFeature, parentNodeId,
            pageClassName, null);
   }

   public CamelOutlineProvider(AbstractModelElementPropertyPage page,
         EStructuralFeature elementListFeature, EStructuralFeature idFeature,
         EStructuralFeature nameFeature, String parentNodeId, Class< ? > pageClass)
   {
      super(page, elementListFeature, idFeature, nameFeature, parentNodeId, pageClass
            .getName(), null);
   }

   public CamelOutlineProvider(AbstractModelElementPropertyPage page,
         EStructuralFeature elementListFeature, EStructuralFeature idFeature,
         EStructuralFeature nameFeature, String parentNodeId, String pageClassName,
         IFilter filter)
   {
      super(page, elementListFeature, idFeature, nameFeature, parentNodeId,
            pageClassName, filter);
   }

   @Override
   public void addNodeTo(String parentNodeId, CarnotPreferenceNode node)
   {
      super.addNodeTo(parentNodeId, node);
   }

   public ConfigurationElement createPageConfiguration(IModelElement element)
   {
      if ((element instanceof AccessPointType))
      {
         AccessPointType accessPoint = (AccessPointType) element;
         if (accessPoint.getDirection().getLiteral()
               .equalsIgnoreCase(Direction.OUT.getId()))
         {
            return new OutAccessPointConfigurationElement(element);
         }
      }
      return null;
   }

   private class OutAccessPointConfigurationElement extends ConfigurationElement
   {
      private Map<String, Object> attributes = new HashMap<String, Object>();

      public OutAccessPointConfigurationElement(IModelElement element)
      {
         super(ConfigurationElement.CFG_PAGE);
         attributes.put(SpiConstants.ID, ModelElementPropertyDialog.composePageId(
               OUT_ACCESS_POINT_LIST_PAGE_ID, OUT_ACCESS_POINT_PAGE_ID));
         attributes.put(SpiConstants.NAME, ((AccessPointType) element).getName());
         attributes.put(SpiConstants.ICON,
               getEditor().getIconFactory().getIconFor(element));
         attributes.put(SpiConstants.PROPERTY_PAGE_CLASS,
               OutAccessPointDetailsPage.class.getName());
      }

      public Object createExecutableExtension(String propertyName) throws CoreException
      {
         return new OutAccessPointDetailsPage();
      }

      public String getAttribute(String name) throws InvalidRegistryObjectException
      {
         return (String) attributes.get(name);
      }

      public String[] getAttributeNames()
      {
         return (String[]) attributes.keySet().toArray(new String[attributes.size()]);
      }
   }
}
