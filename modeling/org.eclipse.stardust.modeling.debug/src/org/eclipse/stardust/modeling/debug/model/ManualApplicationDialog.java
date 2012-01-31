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
package org.eclipse.stardust.modeling.debug.model;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaStackFrame;
import org.eclipse.jdt.debug.core.IJavaType;
import org.eclipse.jdt.debug.core.IJavaValue;
import org.eclipse.jdt.debug.core.IJavaVariable;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.stardust.common.Base64;
import org.eclipse.stardust.common.DateUtils;
import org.eclipse.stardust.common.Money;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.engine.core.struct.StructuredDataConverter;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.DebugPlugin;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;
import org.eclipse.stardust.modeling.debug.debugger.types.ActivityInstanceDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.DataMappingDigest;
import org.eclipse.stardust.modeling.debug.debugger.types.DebugVariableUtils;
import org.eclipse.stardust.modeling.debug.model.ui.ControlGuiFactory;
import org.eclipse.stardust.modeling.debug.model.ui.DebuggerUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;

public class ManualApplicationDialog extends Dialog
{
   private final ActivityInstanceDigest ai;

   private final DataMappingDigest[] dataMappings;

   private final Map outValues = new HashMap();

   /**
    * Factory method for instances implementing {@link IWMVariableValueEditor}.
    *
    * @return
    */
   private static IWMVariableValueEditor createWmVariableValueEditor()
   {
      // TODO (sb): Create and use extension point "IWMVariableValueEditor-factory"
      // instead of reflection.
      String className = "org.eclipse.stardust.modeling.debug.model.CWMVariableValueEditor"; //$NON-NLS-1$
      Bundle debuggerBundle = DebugPlugin.getContext().getBundle();

      try
      {
         Class valueEditorClass = debuggerBundle.loadClass(className);
         return (IWMVariableValueEditor) valueEditorClass.newInstance();
      }
      catch (Exception e)
      {
         throw new InternalException(MessageFormat.format(
               Internal_Debugger_Messages.getString("MSG_CannotInstantiateClass"), new Object[] { className }), e); //$NON-NLS-1$
      }
   }

   public ManualApplicationDialog(Shell parentShell, ActivityInstanceDigest ai,
         DataMappingDigest[] dataMappings)
   {
      super(parentShell);

      this.ai = ai;
      this.dataMappings = dataMappings;

      setShellStyle(getShellStyle() | SWT.RESIZE);
   }

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);

      shell.setText(MessageFormat.format("{0} [{1}]", //$NON-NLS-1$
            new Object[] {ai.getActivityName(), ai.getActivityId()}));
   }

   protected void createButtonsForButtonBar(Composite parent)
   {
      // override with "Complete" and "Suspend" buttons

      createButton(parent, IDialogConstants.OK_ID, Internal_Debugger_Messages
            .getString("B_Complete"), true); //$NON-NLS-1$
      createButton(parent, IDialogConstants.CANCEL_ID, Internal_Debugger_Messages
            .getString("B_Suspend"), false); //$NON-NLS-1$
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite composite = (Composite) super.createDialogArea(parent);

      ScrolledComposite scrolled = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL);
      GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
      gridData.widthHint = 800;
      gridData.heightHint = 600;
      scrolled.setLayoutData(gridData);
      scrolled.setExpandHorizontal(true);
      scrolled.setExpandVertical(true);

      Composite panel = new Composite(scrolled, SWT.NONE);
      scrolled.setContent(panel);

      // add controls to composite as necessary
      panel.setLayout(new GridLayout(2, false));

      for (int idx = 0; idx < dataMappings.length; ++idx)
      {
         Label label = new Label(panel, SWT.NONE);
         label.setText(dataMappings[idx].getMappingName());
         GridData gdLabel = new GridData(GridData.BEGINNING, GridData.CENTER, false,
               false);
         gdLabel.widthHint = 150;
         label.setLayoutData(gdLabel);

         IVariable variable = new JavaVariableDecorator((IJavaVariable) dataMappings[idx]
               .getDataField().getWritebackVariable(), dataMappings[idx]
               .supportsValueModification());

         try
         {
            ControlGuiFactory.create(variable, dataMappings[idx], panel, this.outValues);
         }
         catch (Exception e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      scrolled.setMinSize(panel.computeSize(SWT.DEFAULT, SWT.DEFAULT));

      return composite;
   }

   protected void cancelPressed()
   {
      suspendActivityInstance(ai);

      super.cancelPressed();
   }

   protected void okPressed()
   {
      try
      {
         completeActivityInstance(ai);
      }
      catch (Exception e)
      {
         // TODO: handle exception
         try
         {
            suspendActivityInstance(ai);
         }
         catch (Exception e1)
         {
            // TODO: handle exception
         }
      }

      super.okPressed();
   }

   private void completeActivityInstance(ActivityInstanceDigest ai)
   {
      for (Iterator iter = outValues.entrySet().iterator(); iter.hasNext();)
      {
         Map.Entry entry = (Map.Entry) iter.next();
         IVariable variable = (IVariable) entry.getKey();
         try
         {
            // TODO support additional primitive types
            Object value = entry.getValue();
            if (value == null)
            {
               variable.setValue((String) null);
            }
            else
            {
               variable.setValue(value.toString());
            }
         }
         catch (DebugException ex)
         {
            // TODO Auto-generated catch block
            ex.printStackTrace();
         }
      }
   }

   private void suspendActivityInstance(ActivityInstanceDigest ai)
   {}

   private final class JavaVariableDecorator implements IJavaVariable
   {
      private IJavaVariable variable;

      private boolean supportsModification;

      public JavaVariableDecorator(IJavaVariable variable, boolean supportsModification)
      {
         this.variable = variable;
         this.supportsModification = supportsModification;
      }

      public Object getAdapter(Class adapter)
      {
         return variable.getAdapter(adapter);
      }

      public IDebugTarget getDebugTarget()
      {
         return variable.getDebugTarget();
      }

      public String getGenericSignature() throws DebugException
      {
         return variable.getGenericSignature();
      }

      public IJavaType getJavaType() throws DebugException
      {
         return variable.getJavaType();
      }

      public ILaunch getLaunch()
      {
         return variable.getLaunch();
      }

      public String getModelIdentifier()
      {
         return variable.getModelIdentifier();
      }

      public String getName() throws DebugException
      {
         return variable.getName();
      }

      public String getReferenceTypeName() throws DebugException
      {
         return variable.getReferenceTypeName();
      }

      public String getSignature() throws DebugException
      {
         return variable.getSignature();
      }

      public IValue getValue() throws DebugException
      {
         return new JavaValueDecorator((IJavaValue) variable.getValue(),
               supportsModification);
      }

      public boolean hasValueChanged() throws DebugException
      {
         return variable.hasValueChanged();
      }

      public boolean isFinal() throws DebugException
      {
         return variable.isFinal();
      }

      public boolean isLocal() throws DebugException
      {
         return variable.isLocal();
      }

      public boolean isPackagePrivate() throws DebugException
      {
         return variable.isPackagePrivate();
      }

      public boolean isPrivate() throws DebugException
      {
         return variable.isPrivate();
      }

      public boolean isProtected() throws DebugException
      {
         return variable.isProtected();
      }

      public boolean isPublic() throws DebugException
      {
         return variable.isPublic();
      }

      public boolean isStatic() throws DebugException
      {
         return variable.isStatic();
      }

      public boolean isSynthetic() throws DebugException
      {
         return variable.isSynthetic();
      }

      public void setValue(IValue value) throws DebugException
      {
         variable.setValue(value);
      }

      public void setValue(String expression) throws DebugException
      {
         IWMVariableValueEditor editor = createWmVariableValueEditor();

         IThread thread = ai.getThread();
         if (null != thread)
         {
            IStackFrame[] stackFrames = thread.getStackFrames();
            CWMStackFrame stackFrame = null;
            for (int idx = stackFrames.length - 1; idx >= 0; --idx)
            {
               if (stackFrames[idx] instanceof CWMStackFrame)
               {
                  stackFrame = (CWMStackFrame) stackFrames[idx];
               }
            }

            if (null != stackFrame)
            {
               IValue value = editor.evaluate2((IJavaStackFrame) stackFrame
                     .getJavaStackFrame(), getEvaluationString(expression));
               variable.setValue(value);
            }
         }
      }

      public boolean supportsValueModification()
      {
         return supportsModification && variable.supportsValueModification();
      }

      public boolean verifyValue(IValue value) throws DebugException
      {
         return variable.verifyValue(value);
      }

      public boolean verifyValue(String expression) throws DebugException
      {
         return variable.verifyValue(expression);
      }

      private String getEvaluationString(String value) throws DebugException
      {
         StringBuffer result = new StringBuffer();

         IJavaType javaType = variable.getJavaType();
         Class type = null;
         if (null != javaType)
         {
            type = Reflect.getClassFromClassName(javaType.getName());

            if (null != type)
            {
               if (String.class.isAssignableFrom(type))
               {
                  result.append("new String(\""); //$NON-NLS-1$
                  result.append(value);
                  result.append("\")"); //$NON-NLS-1$
               }
               else if (Character.class.isAssignableFrom(type))
               {
                  result.append("new Character('"); //$NON-NLS-1$
                  if (StringUtils.isEmpty(value))
                  {
                     result.append(' ');
                  }
                  else
                  {
                     result.append(value.charAt(0));
                  }
                  result.append("')"); //$NON-NLS-1$
               }
               else if (Calendar.class.isAssignableFrom(type))
               {
                  try
                  {
                     if (value.indexOf(' ') < 0)
                     {
                        value += " 00:00:00"; //$NON-NLS-1$
                     }
                     Date date = DateUtils.getInteractiveDateFormat().parse(value);
                     result
                           .append("java.util.Calendar result = java.util.Calendar.getInstance();"); //$NON-NLS-1$
                     result.append("result.setTime(new java.util.Date("); //$NON-NLS-1$
                     result.append(date.getTime());
                     result.append("L));"); //$NON-NLS-1$
                     result.append("return result;"); //$NON-NLS-1$
                  }
                  catch (Exception e)
                  {
                     e.printStackTrace();
                     return Constants.EMPTY;
                  }
               }
               else if (Date.class.equals(type))
               {
                  try
                  {
                     if (value.indexOf(' ') < 0)
                     {
                        value += " 00:00:00"; //$NON-NLS-1$
                     }
                     Date date = DateUtils.getInteractiveDateFormat().parse(value);
                     result.append("new java.util.Date("); //$NON-NLS-1$
                     result.append(date.getTime());
                     result.append("L)"); //$NON-NLS-1$
                  }
                  catch (Exception e)
                  {
                     e.printStackTrace();
                     return Constants.EMPTY;
                  }
               }
               else if (Map.class.isAssignableFrom(type) || List.class.isAssignableFrom(type))
               {
                  DataMappingDigest dataMappingDigest = findDataMappingDigest(variable.getName());
                  String dataPath = dataMappingDigest.getDataField().getDataPath();
                  if (dataPath == null)
                  {
                     dataPath = ""; //$NON-NLS-1$
                  }
                  try
                  {
                     IXPathMap xPathMap = DebuggerUtils.getXPathMapOfCurrentModel(dataMappingDigest.getDataField().getDeclaredTypeAdapterId());
                     StructuredDataConverter structuredDataConverter = new StructuredDataConverter(xPathMap);

                     // convert structured data value to a Map / List object
                     Object o = structuredDataConverter.toCollection(value, dataPath, true);

                     // serialize the object into string (Base64)
                     ByteArrayOutputStream out = new ByteArrayOutputStream();
                     ObjectOutputStream objectOut = new ObjectOutputStream(out);
                     objectOut.writeObject(o);
                     objectOut.close();
                     String encodedResult = new String(Base64.encode(out.toByteArray()));

                     // Eclipse debugger will deserialize it and show the value as Map / List
                     result.append("java.lang.String encodedResult = \"" + encodedResult + "\";");
                     result.append("byte[] encodedBytes = encodedResult.getBytes();");
                     result.append("byte[] bytes = org.eclipse.stardust.common.Base64.decode(encodedBytes);");
                     result.append("java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(bytes);");
                     result.append("java.io.ObjectInputStream objectIn = new java.io.ObjectInputStream(in);");
                     result.append("return objectIn.readObject();");
                  }
                  catch (Exception e)
                  {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                  }
               }
               else if (Money.class.equals(type))
               {
               }
               else
               {
                  try
                  {
                     // will throw an exception if constructor is not available.
                     type.getConstructor(new Class[] {String.class});

                     result.append("new "); //$NON-NLS-1$
                     result.append(javaType.getName());
                     result.append("(\""); //$NON-NLS-1$
                     result.append(value);
                     result.append("\")"); //$NON-NLS-1$
                  }
                  catch (Exception e)
                  {
                     e.printStackTrace();
                     return Constants.EMPTY;
                  }
               }
            }
         }

         return result.toString();
      }

      private DataMappingDigest findDataMappingDigest(String mappingId)
      {
         for (int i = 0; i < dataMappings.length; i++)
         {
            if (dataMappings[i].getMappingId().equals(mappingId))
            {
               return dataMappings[i];
            }
         }
         throw new RuntimeException(MessageFormat.format(
               Internal_Debugger_Messages.getString("MSG_DataMappingNotFound"), new Object[] { mappingId })); //$NON-NLS-1$
      }
   }

   private final class JavaValueDecorator implements IJavaValue
   {
      private IJavaValue value;

      private boolean supportsModification;

      public JavaValueDecorator(IJavaValue value, boolean supportsModification)
      {
         this.value = value;
         this.supportsModification = supportsModification;
      }

      public Object getAdapter(Class adapter)
      {
         return value.getAdapter(adapter);
      }

      public IDebugTarget getDebugTarget()
      {
         return value.getDebugTarget();
      }

      public String getGenericSignature() throws DebugException
      {
         return value.getGenericSignature();
      }

      public IJavaType getJavaType() throws DebugException
      {
         return value.getJavaType();
      }

      public ILaunch getLaunch()
      {
         return value.getLaunch();
      }

      public String getModelIdentifier()
      {
         return value.getModelIdentifier();
      }

      public String getReferenceTypeName() throws DebugException
      {
         return value.getReferenceTypeName();
      }

      public String getSignature() throws DebugException
      {
         return value.getSignature();
      }

      public String getValueString() throws DebugException
      {
         return getReadableValueString(value);
      }

      public IVariable[] getVariables() throws DebugException
      {
         IVariable[] variables = value.getVariables();
         JavaVariableDecorator[] decorators = new JavaVariableDecorator[variables.length];
         for (int idx = 0; idx < decorators.length; ++idx)
         {
            decorators[idx] = new JavaVariableDecorator((IJavaVariable) variables[idx],
                  supportsModification);
         }

         return value.getVariables();
      }

      public boolean hasVariables() throws DebugException
      {
         return value.hasVariables();
      }

      public boolean isAllocated() throws DebugException
      {
         return value.isAllocated();
      }

      private String getReadableValueString(IJavaValue value) throws DebugException
      {
         StringBuffer result = new StringBuffer();

         IJavaType javaType = value.getJavaType();
         Class type = null;
         if (null != javaType)
         {
            type = Reflect.getClassFromClassName(javaType.getName());

            if (null != type)
            {
               if (String.class.equals(type))
               {
                  result.append(value.getValueString());
               }
               else if (Boolean.class.equals(type))
               {
                  result.append(DebugVariableUtils.extractAsBoolean(
                        "value", value.getVariables())); //$NON-NLS-1$
               }
               else if (Byte.class.equals(type))
               {
                  result.append(DebugVariableUtils.extractAsLong(
                        "value", value.getVariables())); //$NON-NLS-1$
               }
               else if (Short.class.isAssignableFrom(type))
               {
                  result.append(DebugVariableUtils.extractAsLong(
                        "value", value.getVariables())); //$NON-NLS-1$
               }
               else if (Integer.class.equals(type))
               {
                  result.append(DebugVariableUtils.extractAsLong(
                        "value", value.getVariables())); //$NON-NLS-1$
               }
               else if (Long.class.equals(type))
               {
                  result.append(DebugVariableUtils.extractAsLong(
                        "value", value.getVariables())); //$NON-NLS-1$
               }
               else if (Float.class.equals(type))
               {
                  result.append(DebugVariableUtils.extractAsDouble(
                        "value", value.getVariables())); //$NON-NLS-1$
               }
               else if (Double.class.equals(type))
               {
                  result.append(DebugVariableUtils.extractAsDouble(
                        "value", value.getVariables())); //$NON-NLS-1$
               }
               else if (Character.class.equals(type))
               {
                  result.append(DebugVariableUtils.extractAsString(
                        "value", value.getVariables())); //$NON-NLS-1$
               }
               else if (Calendar.class.isAssignableFrom(type))
               {
                  result.append(formatTime(DebugVariableUtils.extractAsLong(
                        "time", value.getVariables()))); //$NON-NLS-1$
               }
               else if (Date.class.equals(type))
               {
                  result.append(formatTime(DebugVariableUtils.extractAsLong(
                        "fastTime", value.getVariables()))); //$NON-NLS-1$
               }
               else if (Money.class.equals(type))
               {
               }
            }
         }

         return result.toString();
      }

      private String formatTime(long l)
      {
         return DateUtils.getInteractiveDateFormat().format(new Date(l));
      }

      public boolean isNull()
      {
         return value == null;
      }
   }

   public static void main(String[] args)
   {
      Display display = new Display();
      Color red = display.getSystemColor(SWT.COLOR_RED);
      Color blue = display.getSystemColor(SWT.COLOR_BLUE);
      Shell shell = new Shell(display);
      shell.setLayout(new FillLayout());

      // set the size of the scrolled content - method 1
      final ScrolledComposite sc1 = new ScrolledComposite(shell, SWT.V_SCROLL);
      final Composite c1 = new Composite(sc1, SWT.NONE);
      sc1.setContent(c1);
      c1.setBackground(red);
      GridLayout layout = new GridLayout();
      layout.numColumns = 4;
      c1.setLayout(layout);
      Button b1 = new Button(c1, SWT.PUSH);
      b1.setText("first button"); //$NON-NLS-1$
      c1.setSize(c1.computeSize(SWT.DEFAULT, SWT.DEFAULT));

      // set the minimum width and height of the scrolled content - method 2
      final ScrolledComposite sc2 = new ScrolledComposite(shell, SWT.H_SCROLL
            | SWT.V_SCROLL | SWT.BORDER);
      sc2.setExpandHorizontal(true);
      sc2.setExpandVertical(true);
      final Composite c2 = new Composite(sc2, SWT.NONE);
      sc2.setContent(c2);
      c2.setBackground(blue);
      layout = new GridLayout();
      layout.numColumns = 4;
      c2.setLayout(layout);
      Button b2 = new Button(c2, SWT.PUSH);
      b2.setText("first button"); //$NON-NLS-1$
      sc2.setMinSize(c2.computeSize(SWT.DEFAULT, SWT.DEFAULT));

      Button add = new Button(shell, SWT.PUSH);
      add.setText("add children"); //$NON-NLS-1$
      final int[] index = new int[] {0};
      add.addListener(SWT.Selection, new Listener()
      {
         public void handleEvent(Event e)
         {
            index[0]++;
            Button button = new Button(c1, SWT.PUSH);
            button.setText("button " + index[0]); //$NON-NLS-1$
            // reset size of content so children can be seen - method 1
            c1.setSize(c1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
            c1.layout();

            button = new Button(c2, SWT.PUSH);
            button.setText("button " + index[0]); //$NON-NLS-1$
            // reset the minimum width and height so children can be seen - method 2
            sc2.setMinSize(c2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
            c2.layout();
         }
      });

      shell.open();
      while (!shell.isDisposed())
      {
         if (!display.readAndDispatch())
            display.sleep();
      }
      display.dispose();
   }
}
