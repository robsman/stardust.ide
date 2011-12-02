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
package org.eclipse.stardust.modeling.core.editors.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.IconWithOverlays;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPropertyPage;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CarnotPreferenceNode extends PreferenceNode
{
   private IWorkbenchPropertyPage page;
   
   public final static int LABEL_ORDER = -1;
   public static final int INSERTION_ORDER = -2;
   public static final int SPI_ELEMENT = 1000;

   private ConfigurationElement template;
   private IAdaptable element;
   
   private String executableAttributeName = SpiConstants.PROPERTY_PAGE_CLASS; 

   private Image image;

   private String text;
   
   private IQuickValidationStatus pageStatus;

   private int overlayStyle = IconWithOverlays.OVR_NONE;
   private Image overlaidImage;

   private int sortOrder;
   // for the order in the tree 
   public String category;
   
   private String id;

   public CarnotPreferenceNode(ConfigurationElement template, IAdaptable element)
   {
      this(template, element, LABEL_ORDER);
   }

   public CarnotPreferenceNode(ConfigurationElement template, IAdaptable element, int sortOrder)
   {
      super(getId(template), getName(template), null, null);
      setLabelImage(DiagramPlugin.getImage(template.getAttribute(SpiConstants.ICON)));
      this.category = template.getAttribute(SpiConstants.CATEGORY);
      this.element = element;
      this.template = template;
      this.sortOrder = sortOrder;
   }

   public CarnotPreferenceNode(ConfigurationElement template, IAdaptable element,
		   String executableAttributeName, int sortOrder)
   {
      this(template, element, sortOrder);
      this.executableAttributeName = executableAttributeName;
   }

   private static String getName(ConfigurationElement template)
   {
      String name = template.getAttribute(SpiConstants.NAME);
      return name == null ? "" : name; //$NON-NLS-1$
   }

   private static String getId(ConfigurationElement template)
   {
      String id = template.getAttribute(SpiConstants.ID);
      return id == null ? "<undefinedId>" + Integer.toString(template.hashCode()) : //$NON-NLS-1$
    	  ModelElementPropertyDialog.convertId(id); //$NON-NLS-1$
   }

   public String getId()
   {
      return this.id == null ? super.getId() : this.id;
   }
   
   public String toString()
   {
      return getId();
   }
   
   public void setSortOrder(int sortOrder)
   {
      this.sortOrder = sortOrder;
   }
   
   /*
    * (non-Javadoc) Method declared on IPreferenceNode.
    */
   public void createPage()
   {
      try
      {
         page = (IWorkbenchPropertyPage) template.createExecutableExtension(executableAttributeName);
         internalSetPage(page);
      }
      catch (CoreException e)
      {
         e.printStackTrace();
      }
   }

   public IAdaptable getAdaptable()
   {
      return element;
   }

   public void internalSetPage(IWorkbenchPropertyPage page)
   {
      page.setElement(getAdaptable());
      page.setTitle(getLabelText());
      setPage(page);
   }

   public String getLabelText()
   {
      return text == null ? super.getLabelText() : text;
   }

   public void setLabelText(String text)
   {
      this.text = text;
   }

   public Image getLabelImage()
   {
      Image img = (null != overlaidImage) ? overlaidImage : image;
      
      return (null != img) ? img : super.getLabelImage();
   }

   public void setLabelImage(Image image)
   {
      this.image = image;

      updatePageStatus(pageStatus);
   }

   public void updatePageStatus(IQuickValidationStatus status)
   {
      this.pageStatus = status;
      
      if (status != null && status.hasErrors())
      {
         setOverlayIcon(IconWithOverlays.OVR_ERRORS);
      }
      else if (status != null && status.hasWarnings())
      {
         setOverlayIcon(IconWithOverlays.OVR_WARNINGS);
      }
      else
      {
         setOverlayIcon(IconWithOverlays.OVR_NONE);
      }
   }

   public int getSortOrder()
   {
      return sortOrder;
   }
   
   public void disposeResources()
   {
      setOverlayIcon(IconWithOverlays.OVR_NONE);
      
      super.disposeResources();
   }

   private void setOverlayIcon(int style)
   {
      if (style != overlayStyle)
      {
         if (null != overlaidImage)
         {
            overlaidImage.dispose();
            this.overlaidImage = null;
         }
         this.overlayStyle = IconWithOverlays.OVR_NONE;

         if (IconWithOverlays.OVR_NONE != style)
         {
            Image img = (null != image) ? image : super.getLabelImage();
            if (null != img)
            {
               this.overlaidImage = new IconWithOverlays(img, style).createImage();
               this.overlayStyle = style;
            }
            else
            {
               this.overlayStyle = IconWithOverlays.OVR_NONE;
            }
         }
      }
   }

   public void setId(String id)
   {
      this.id = id;
   }
}