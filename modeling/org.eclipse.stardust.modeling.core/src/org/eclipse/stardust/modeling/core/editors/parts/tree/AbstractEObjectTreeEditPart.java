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
package org.eclipse.stardust.modeling.core.editors.parts.tree;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.merge.ShareUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.Extensible;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.IImageManager;
import org.eclipse.stardust.modeling.common.ui.jface.IconWithOverlays;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdaptee;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdapter;
import org.eclipse.stardust.modeling.core.editors.parts.PropertySourceFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.policies.TreeElementComponentEditPolicy;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionInfo;
import org.eclipse.stardust.modeling.core.modelserver.jobs.CollisionState;
import org.eclipse.stardust.modeling.core.modelserver.jobs.StateCache;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.StringUtils;
import ag.carnot.workflow.model.PredefinedConstants;

public abstract class AbstractEObjectTreeEditPart extends AbstractTreeEditPart
      implements NotificationAdaptee
{
   public static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   public static final int STATE_OK = 0;

   public static final int STATE_WARNINGS = 1;

   public static final int STATE_ERRORS = 2;

   private EStructuralFeature[] childrenFeatures;

   private String iconPath;

   private int state = STATE_OK;

   private EObject eObjectModel;

   private NotificationAdapter notificationAdapter;

   private IPropertySource propertySource;

   private WorkflowModelEditor editor;

   public void removeChild(EditPart child)
   {
      super.removeChild(child);
   }

   public void setState(int state)
   {
      this.state = state;

      if (checkTreeItem())
      {
         getWidget().getDisplay().asyncExec(new Runnable()
         {
            public void run()
            {
               refreshVisuals();
            }
         });
      }
   }

   protected String getIconPath()
   {
      return iconPath;
   }

   public void setIconPath(String iconPath)
   {
      this.iconPath = iconPath;
   }

   protected Image getIcon()
   {
      String iconLocator = getIconPath();
      if (StringUtils.isEmpty(iconLocator))
      {
         return null;
      }
      IImageManager imageManager = DiagramPlugin.getDefault().getImageManager();
      if (state == STATE_ERRORS)
      {
         return imageManager.getIcon(iconLocator, IImageManager.ICON_STYLE_ERRORS);
      }
      if (state == STATE_WARNINGS)
      {
         return imageManager.getIcon(iconLocator, IImageManager.ICON_STYLE_WARNINGS);
      }
      EObject object = getEObjectModel();
      if (object.eIsProxy())
      {
         return imageManager.getIcon(iconLocator, IImageManager.ICON_STYLE_REF);
      }
      String linkUri = getLinkUri(object);
      if (linkUri != null)
      {
         if (isRef(object))
         {
            return imageManager.getIcon(iconLocator, IImageManager.ICON_STYLE_REF);
         }
         else
         {
            return imageManager.getIcon(iconLocator, IImageManager.ICON_STYLE_LINK);
         }
      }
      return imageManager.getIcon(iconLocator, IImageManager.ICON_STYLE_PLAIN);
   }

   private boolean isRef(EObject object)
   {
      if (object instanceof DataType)
      {
         return ((DataType) object).getExternalReference() != null;
      }
      if (object instanceof ActivityType)
      {
         return ((ActivityType) object).getExternalRef() != null;
      }
      return false;
   }

   private boolean isProvidingInterface(EObject object)
   {
      if (object instanceof ProcessDefinitionType)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) object;
         if (process.getFormalParameterMappings() != null
               && process.getExternalRef() == null)
         {
            return true;
         }
      }
      return false;
   }

   private String getLinkUri(EObject object)
   {
      if (object instanceof IExtensibleElement)
      {
         return AttributeUtil.getAttributeValue((IExtensibleElement) object,
               IConnectionManager.URI_ATTRIBUTE_NAME);
      }
      if (object instanceof Extensible)
      {
         return ExtendedAttributeUtil.getAttributeValue((Extensible) object,
               IConnectionManager.URI_ATTRIBUTE_NAME);
      }
      return null;
   }

   protected AbstractEObjectTreeEditPart(WorkflowModelEditor editor, EObject model)
   {
      this(editor, model, model, null, null);
   }

   protected AbstractEObjectTreeEditPart(WorkflowModelEditor editor, EObject model,
         String iconPath)
   {
      this(editor, model, model, iconPath, null);
   }

   protected AbstractEObjectTreeEditPart(WorkflowModelEditor editor, EObject model,
         String iconPath, EStructuralFeature[] childrenFeatures)
   {
      this(editor, model, model, iconPath, childrenFeatures);
   }

   protected AbstractEObjectTreeEditPart(WorkflowModelEditor editor, Object model,
         EObject eObjectModel, String iconPath, EStructuralFeature[] childrenFeatures)
   {
      setModel(model);
      setIconPath(iconPath);
      setChildrenFeatures(childrenFeatures);

      this.eObjectModel = eObjectModel;
      this.editor = editor;
   }

   public EObject getEObjectModel()
   {
      return (null != eObjectModel) ? eObjectModel : (EObject) getModel();
   }

   protected Image getImage()
   {
      Image image = getIcon();

      image = addProcessInterfaceOverlay(image);
      image = addPrivateModifierOverlay(image);

      ModelType model = ModelUtils.findContainingModel(eObjectModel);
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
      if (editor != null && ShareUtils.isLockableElement(eObjectModel)
            && ShareUtils.isModelShared(model))
      {
         return editor.getModelServer().getIcon(image, eObjectModel);
      }
      return image;
   }

   private Image addProcessInterfaceOverlay(Image image)
   {
      if (image != null)
      {
         if (getEObjectModel() != null)
         {
            if (getEObjectModel() instanceof ProcessDefinitionType)
            {
               if (this.isProvidingInterface(getEObjectModel()))
               {
                  IconWithOverlays iwo = new IconWithOverlays(image,
                        IconWithOverlays.OVR_IF);
                  iwo.defaultPosition = IDecoration.TOP_LEFT;
                  image = iwo.createImage();
               }
            }
         }
      }
      return image;
   }

   private Image addPrivateModifierOverlay(Image image)
   {
      String visibilityString = null;
      if (image != null)
      {
         if (getEObjectModel() != null)
         {
            if (getEObjectModel() instanceof TypeDeclarationType)
            {
               TypeDeclarationType declaration = (TypeDeclarationType) getEObjectModel();
               ExtendedAttributeType visibility = ExtendedAttributeUtil.getAttribute(
                     declaration.getExtendedAttributes(),
                     PredefinedConstants.MODELELEMENT_VISIBILITY);
               if (visibility != null)
               {
                  visibilityString = visibility.getValue();
               }
            }
            if (getEObjectModel() instanceof ApplicationType)
            {
               AttributeType visibility = AttributeUtil.getAttribute(
                     (IExtensibleElement) getEObjectModel(),
                     PredefinedConstants.MODELELEMENT_VISIBILITY);
               if (visibility != null)
               {
                  visibilityString = visibility.getValue();
               }
            }
            if (visibilityString == null || visibilityString == "" //$NON-NLS-1$
                  || visibilityString.equalsIgnoreCase("Public")) //$NON-NLS-1$
            {
               return image;
            }
            else
            {
               IconWithOverlays iwo = new IconWithOverlays(image,
                     IconWithOverlays.OVR_PRIVATE);
               iwo.defaultPosition = IDecoration.TOP_LEFT;
               image = iwo.createImage();
            }
         }
      }
      return image;
   }

   @SuppressWarnings("unchecked")
   public List getCurrentModelChildren()
   {
      return getModelChildren();
   }

   @SuppressWarnings("unchecked")
   protected List getModelChildren()
   {
      if (childrenFeatures != null)
      {
         List<Object> children = CollectionUtils.newList();
         for (int i = 0; i < childrenFeatures.length; i++)
         {
            EStructuralFeature feature = childrenFeatures[i];
            Object value = getEObjectModel().eGet(feature);
            if (value instanceof List)
            {
               List<Object> items = CollectionUtils.newList((List< ? >) value);
               Collections.sort(items, ModelUtils.IDENTIFIABLE_COMPARATOR);
               children.addAll(items);
            }
            else
            {
               children.add(value);
            }
         }
         return children;
      }
      return super.getModelChildren();
   }

   public void handleNotification(Notification n)
   {
      // whenever a notification involves an index, then we refresh the children
      // otherwise refresh the visuals
      if (n.getPosition() == Notification.NO_INDEX
            && n.getEventType() != Notification.ADD_MANY
            && n.getEventType() != Notification.REMOVE_MANY)
      {
         refreshVisuals();
         if (PKG_CWM.getIIdentifiableElement_Id().equals(n.getFeature())
               || PKG_CWM.getIIdentifiableElement_Name().equals(n.getFeature()))
         {
            // refresh parent to update sort order of children
            if (null != getParent())
            {
               getParent().refresh();
            }
         }
      }
      else
      {
         refreshChildren();
      }
   }

   public void activate()
   {
      if (!isActive())
      {
         super.activate();

         final EObject model = getEObjectModel();
         if (null != model)
         {
            model.eAdapters().add(getNotificationAdapter());
         }
      }
   }

   public void deactivate()
   {
      if (isActive())
      {
         final EObject model = getEObjectModel();
         if (null != model)
         {
            model.eAdapters().remove(getNotificationAdapter());
         }

         super.deactivate();
      }
   }

   @SuppressWarnings("unchecked")
   public Object getAdapter(Class key)
   {
      Object adapter;

      if (EditPart.class == key)
      {
         adapter = this;
      }
      else if (IPropertySource.class == key)
      {
         adapter = getPropertySource();
      }
      else if (EObject.class.equals(key) && getModel() instanceof EObject)
      {
         return getModel();
      }
      else
      {
         adapter = super.getAdapter(key);
      }

      return adapter;
   }

   protected EditPart getEditPartForChild(Object child)
   {
      return (EditPart) getViewer().getEditPartRegistry().get(child);
   }

   protected NotificationAdapter getNotificationAdapter()
   {
      if (null == notificationAdapter)
      {
         this.notificationAdapter = new NotificationAdapter(this);
      }
      return notificationAdapter;
   }

   protected IPropertySource getPropertySource()
   {
      if (null == propertySource)
      {
         this.propertySource = createPropertySource();
      }
      return propertySource;
   }

   protected IPropertySource createPropertySource()
   {
      return PropertySourceFactory.getPropertySource(this, getEObjectModel());
   }

   public EStructuralFeature[] getChildrenFeatures()
   {
      return childrenFeatures;
   }

   public void setChildrenFeatures(EStructuralFeature[] childrenFeatures)
   {
      this.childrenFeatures = childrenFeatures;
   }

   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new TreeElementComponentEditPolicy());
   }

   public WorkflowModelEditor getEditor()
   {
      return editor;
   }

   public String getLabel()
   {
      // default behavior
      return super.getText();
   }

   /**
    * Overwrite default behavior of getText to append lock information
    */
   public final String getText()
   {
      String label = getLabel();
      ModelType model = ModelUtils.findContainingModel(eObjectModel);
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(model);
      if (editor != null && ShareUtils.isLockableElement(eObjectModel)
            && ShareUtils.isModelShared(model))
      {
         StateCache stateCache = editor.getModelServer().getStateCache();
         CollisionInfo stateInfo = stateCache.getState(eObjectModel);
         if (stateInfo.getState() == CollisionState.LOCKED_BY_OTHER)
         {
            String owner = stateInfo.getOwner();
            if (owner == null)
            {
               return MessageFormat.format(Diagram_Messages.LBL_LOCKED,
                     new Object[] {label});
            }
            else
            {
               return MessageFormat.format(Diagram_Messages.LBL_NULL_LOCKED_BY_ONE,
                     new Object[] {label, owner}); 
            }
         }
      }
      return label;
   }

   public void refreshTextAndIcon()
   {
      super.refreshVisuals();
   }
}