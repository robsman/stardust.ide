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
package org.eclipse.stardust.modeling.integration.ejb30.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledCombo;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.integration.ejb30.EJB30_Messages;
import org.eclipse.stardust.modeling.integration.ejb30.TypeSelector;
import org.eclipse.stardust.modeling.integration.ejb30.TypeSelector.TypeListener;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;



/**
 * @author fherinean
 * @version $Revision$
 */
public class EntityBean30PropertyPage extends AbstractModelElementPropertyPage
      implements IDataPropertyPage
{
   private static final String CLASS_SUFFIX = ".class"; //$NON-NLS-1$

   private TypeSelector.TextSelector beanClassBrowser;

   private LabeledText beanClassText;

   private LabeledCombo emSourceCombo;

   private LabeledText jndiText;

   private Label pkType;

   private Label pkClass;

   private Label pkElements;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager wBndMgr = getWidgetBindingManager();
      wBndMgr.bind(beanClassText, (IExtensibleElement) element,
            CarnotConstants.CLASS_NAME_ATT);
      wBndMgr.bind(emSourceCombo, (IExtensibleElement) element,
            CarnotConstants.ENTITY_MANAGER_SOURCE_ATT);
      wBndMgr.bind(jndiText, (IExtensibleElement) element,
            CarnotConstants.JNDI_PATH_ATT);

      if (isPredefined(element))
      {
         disableControls();
      }
   }

   private void disableControls()
   {
      beanClassBrowser.getTextControl().setEnabled(false);
      beanClassText.getText().setEditable(false);
   }

   private boolean isPredefined(IModelElement element)
   {
      return ((DataType) element).isPredefined();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      AttributeUtil.setAttribute((IExtensibleElement) element,
            CarnotConstants.PRIMARY_KEY_ATT, pkClass.getText());
      AttributeUtil.setAttribute((IExtensibleElement) element,
            CarnotConstants.PRIMARY_KEY_TYPE_ATT, pkType.getText());
      AttributeUtil.setAttribute((IExtensibleElement) element,
            CarnotConstants.PRIMARY_KEY_ELEMENTS_ATT, pkElements.getText());
   }

   public Control createBody(final Composite parent)
   {
      TypeFinder finder = new TypeFinder(getModelElement());

      Composite composite = FormBuilder.createComposite(parent, 2);

      // bean class
      LabelWithStatus beanLabel = FormBuilder.createLabelWithRightAlignedStatus(
            composite, EJB30_Messages.LBL_BEAN_CLASS_NAME);
      beanClassBrowser = new TypeSelector.TextSelector(finder, composite,
            EJB30_Messages.TXT_ENTITY_BEAN_CL, IJavaElementSearchConstants.CONSIDER_CLASSES);
      beanClassText = new LabeledText(beanClassBrowser.getTextControl(), beanLabel);
      beanClassBrowser.addListener(new TypeListener()
      {
         public void typeChanged(TypeInfo type)
         {
            updatePkInfo(type);
         }
      });

      emSourceCombo = FormBuilder.createLabeledCombo(composite, EJB30_Messages.LBL_ENTITY_MANAGER);
      emSourceCombo.getCombo().setItems(new String[] {
            // TODO: i18n !!!
    		// Done! ;-)  
            EJB30_Messages.LBL_JNDI,
            EJB30_Messages.LBL_FACTORY_JNDI,
            EJB30_Messages.LBL_UNIT_NAME
      });
      emSourceCombo.getCombo().addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {}

         public void widgetSelected(SelectionEvent e)
         {
            updateJndiLabel();
         }
      });

      // jndi path
      jndiText = FormBuilder.createLabeledText(composite, EJB30_Messages.LB_JNDIPath);
      
      FormBuilder.createLabel(composite, EJB30_Messages.LBL_PRIMARY_KEY_TYPE);
      pkType = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      FormBuilder.createLabel(composite, EJB30_Messages.LBL_PRIMARY_KEY_CL);
      pkClass = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      FormBuilder.createLabel(composite, EJB30_Messages.LBL_PRIMARY_KEY_ELEMENT);
      pkElements = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$

      return composite;
   }

   protected void updatePkInfo(TypeInfo type)
   {
      pkType.setText(""); //$NON-NLS-1$
      pkClass.setText(""); //$NON-NLS-1$
      pkElements.setText(""); //$NON-NLS-1$
      if (type != null)
      {
         String typeName = type.getFullName();
         try
         {
            Class<?> typeClass = Class.forName(typeName);
            updatePkInfoFromClass(typeClass);
         }
         catch (ClassNotFoundException e)
         {
            try
            {
               updatePkInfoFromSource(type);
            }
            catch (JavaModelException e1)
            {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      }
   }

   private void updatePkInfoFromClass(Class<?> typeClass)
   {
      Entity entity = typeClass.getAnnotation(Entity.class);
      if (entity == null)
      {
        setMessage(MessageFormat.format(
              EJB30_Messages.MSG_TYPE_STELLE_NULL_IS_NOT_ANNTATED,
              typeClass.getName()), WARNING);
        return;
      }
      IdClass idClass = typeClass.getAnnotation(IdClass.class);
      if (idClass != null)
      {
         pkType.setText(EJB30_Messages.TXT_ID_CLASS);
         Class<?> pkClass = idClass.value();
         if (pkClass != null)
         {
            this.pkClass.setText(pkClass.getName());
            StringBuffer sb = new StringBuffer();
            Field[] fields = typeClass.getFields();
            for (int i = 0; i < fields.length; i++)
            {
               Id id = fields[i].getAnnotation(Id.class);
               if (id != null)
               {
                  if (sb.length() > 0)
                  {
                     sb.append(',');
                  }
                  sb.append(EJB30_Messages.TXT_FIELD);
                  sb.append(fields[i].getName());
               }
            }
            Method[] methods = typeClass.getMethods();
            for (int i = 0; i < methods.length; i++)
            {
               Id id = methods[i].getAnnotation(Id.class);
               if (id != null)
               {
                  if (sb.length() > 0)
                  {
                     sb.append(',');
                  }
                  sb.append(EJB30_Messages.TXT_PROPERTY);
                  sb.append(methods[i].getName());
               }
            }
            pkElements.setText(sb.toString());
         }
         else
         {
            pkType.setText(""); //$NON-NLS-1$
            pkElements.setText(""); //$NON-NLS-1$
         }
      }
      else
      {
         boolean pkFound = false;
         if (!pkFound)
         {
            Field[] fields = typeClass.getFields();
            for (int i = 0; i < fields.length && !pkFound; i++)
            {
               EmbeddedId id = fields[i].getAnnotation(EmbeddedId.class);
               if (id != null)
               {
                  pkType.setText(EJB30_Messages.TXT_EMBEDDED_ID);
                  pkClass.setText(fields[i].getType().getName());
                  pkElements.setText(MessageFormat.format(EJB30_Messages.TXT_FIELD,new Object[]{fields[i].getName()}));
                  pkFound = true;
               }
            }
         }
         if (!pkFound)
         {
            Method[] methods = typeClass.getMethods();
            for (int i = 0; i < methods.length && !pkFound; i++)
            {
               EmbeddedId id = methods[i].getAnnotation(EmbeddedId.class);
               if (id != null)
               {
                  pkType.setText(EJB30_Messages.TXT_EMBEDDED_ID);
                  pkClass.setText(methods[i].getReturnType().getName());
                  pkElements.setText(MessageFormat.format(EJB30_Messages.TXT_PROPERTY,new Object[]{methods[i].getName()}));
                  pkFound = true;
               }
            }
         }
         if (!pkFound)
         {
            Field[] fields = typeClass.getFields();
            for (int i = 0; i < fields.length && !pkFound; i++)
            {
               Id id = fields[i].getAnnotation(Id.class);
               if (id != null)
               {
                  pkType.setText(EJB30_Messages.TXT_ID);
                  pkClass.setText(fields[i].getType().getName());
                  pkElements.setText(MessageFormat.format(EJB30_Messages.TXT_FIELD, new Object[]{fields[i].getName()}));
                  pkFound = true;
               }
            }
         }
         if (!pkFound)
         {
            Method[] methods = typeClass.getMethods();
            for (int i = 0; i < methods.length && !pkFound; i++)
            {
               Id id = methods[i].getAnnotation(Id.class);
               if (id != null)
               {
                  pkType.setText(EJB30_Messages.TXT_ID);
                  this.pkClass.setText(methods[i].getReturnType().getName());
                  pkElements.setText(MessageFormat.format(EJB30_Messages.TXT_PROPERTY, new Object[]{methods[i].getName()}));
                  pkFound = true;
               }
            }
         }
      }
   }

   private void updatePkInfoFromSource(TypeInfo type) throws JavaModelException
   {
      TypeDeclaration td = MyASTVisitor.findTypeDeclaration(type);
      Annotation entity = MyASTVisitor.getAnnotation(type, td, Entity.class);
      if (entity == null)
      {
        setMessage(MessageFormat.format(
              EJB30_Messages.MSG_TYPE_IS_NOT_ANNOTATED_AS_ENTITY,
              type.getFullName()), WARNING);
        return;
      }
      Annotation idClass = MyASTVisitor.getAnnotation(type, td, IdClass.class);
      if (idClass != null)
      {
         pkType.setText(EJB30_Messages.TXT_ID_CL);
         if (idClass instanceof SingleMemberAnnotation)
         {
            SingleMemberAnnotation sma = (SingleMemberAnnotation) idClass;
            String pkClassName = sma.getValue().toString();
            if (pkClassName.endsWith(CLASS_SUFFIX))
            {
               pkClassName = pkClassName.substring(0, pkClassName.length() - CLASS_SUFFIX.length());
            }
            String pkClass = type.resolve(pkClassName);
            if (pkClass != null)
            {
               this.pkClass.setText(pkClass);
               StringBuffer sb = new StringBuffer();
               FieldDeclaration[] fields = td.getFields();
               for (int i = 0; i < fields.length; i++)
               {
                  Annotation id = MyASTVisitor.getAnnotation(type, fields[i], Id.class);
                  if (id != null)
                  {
                     if (sb.length() > 0)
                     {
                        sb.append(',');
                     }
                     sb.append(EJB30_Messages.TXT_FIELD);
                     VariableDeclarationFragment first = (VariableDeclarationFragment)
                        fields[i].fragments().get(0);
                     sb.append(first.getName().toString());
                  }
               }
               MethodDeclaration[] methods = td.getMethods();
               for (int i = 0; i < methods.length; i++)
               {
                  Annotation id = MyASTVisitor.getAnnotation(type, methods[i], Id.class);
                  if (id != null)
                  {
                     if (sb.length() > 0)
                     {
                        sb.append(',');
                     }
                     sb.append(EJB30_Messages.TXT_PROPERTY);
                     sb.append(methods[i].getName().toString());
                  }
               }
               pkElements.setText(sb.toString());
            }
            else
            {
               pkType.setText(""); //$NON-NLS-1$
               pkElements.setText(""); //$NON-NLS-1$
            }
         }
      }
      else
      {
         boolean pkFound = false;
         if (!pkFound)
         {
            FieldDeclaration[] fields = td.getFields();
            for (int i = 0; i < fields.length && !pkFound; i++)
            {
               Annotation id = MyASTVisitor.getAnnotation(type, fields[i], EmbeddedId.class);
               if (id != null)
               {
                  pkType.setText(EJB30_Messages.TXT_EMBEDDED_ID);
                  VariableDeclarationFragment first = (VariableDeclarationFragment)
                     fields[i].fragments().get(0);
                  String pkClass = type.resolve(fields[i].getType().toString());
                  this.pkClass.setText(pkClass == null ? "" : pkClass); //$NON-NLS-1$
                  pkElements.setText(MessageFormat.format(EJB30_Messages.TXT_FIELD, new Object[]{first.getName().toString()}));
                  pkFound = true;
               }
            }
         }
         if (!pkFound)
         {
            MethodDeclaration[] methods = td.getMethods();
            for (int i = 0; i < methods.length && !pkFound; i++)
            {
               Annotation id = MyASTVisitor.getAnnotation(type, methods[i], EmbeddedId.class);
               if (id != null)
               {
                  pkType.setText(EJB30_Messages.TXT_EMBEDDED_ID);
                  String pkClass = type.resolve(methods[i].getReturnType2().toString());
                  this.pkClass.setText(pkClass == null ? "" : pkClass); //$NON-NLS-1$
                  pkElements.setText(MessageFormat.format(EJB30_Messages.TXT_PROPERTY,new Object[]{methods[i].getName().toString()}));
                  pkFound = true;
               }
            }
         }
         if (!pkFound)
         {
            FieldDeclaration[] fields = td.getFields();
            for (int i = 0; i < fields.length && !pkFound; i++)
            {
               Annotation id = MyASTVisitor.getAnnotation(type, fields[i], Id.class);
               if (id != null)
               {
                  pkType.setText(EJB30_Messages.TXT_ID);
                  VariableDeclarationFragment first = (VariableDeclarationFragment)
                     fields[i].fragments().get(0);
                  String pkClass = type.resolve(fields[i].getType().toString());
                  this.pkClass.setText(pkClass == null ? "" : pkClass); //$NON-NLS-1$
                  pkElements.setText(MessageFormat.format(EJB30_Messages.TXT_FIELD, new Object[]{first.getName().toString()}));
                  pkFound = true;
               }
            }
         }
         if (!pkFound)
         {
            MethodDeclaration[] methods = td.getMethods();
            for (int i = 0; i < methods.length && !pkFound; i++)
            {
               Annotation id = MyASTVisitor.getAnnotation(type, methods[i], Id.class);
               if (id != null)
               {
                  pkType.setText(EJB30_Messages.TXT_ID);
                  String pkClass = type.resolve(methods[i].getReturnType2().toString());
                  this.pkClass.setText(pkClass == null ? "" : pkClass); //$NON-NLS-1$
                  pkElements.setText(MessageFormat.format(EJB30_Messages.TXT_PROPERTY, new Object[]{methods[i].getName().toString()}));
                  pkFound = true;
               }
            }
         }
      }
   }

   protected void updateJndiLabel()
   {
      int index = emSourceCombo.getCombo().getSelectionIndex();
      jndiText.getLabel().setText(index < 2 ? EJB30_Messages.LB_JNDIPath : EJB30_Messages.LBL_UNIT_NAMEE);
   }
}
