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
package org.eclipse.stardust.modeling.project.effort;

import java.util.StringTokenizer;

import org.eclipse.stardust.modeling.project.Project_Messages;


public class EffortPerUnit extends EffortNotifier
{
    public static final String[] DEFAULT_LABELS = {
       Project_Messages.getString("EffortPerUnit.DefineColumnLabel"), //$NON-NLS-1$
       Project_Messages.getString("EffortPerUnit.DesignColumnLabel"), //$NON-NLS-1$
       Project_Messages.getString("EffortPerUnit.ImplementationColumnLabel"), //$NON-NLS-1$
       Project_Messages.getString("EffortPerUnit.ValidationColumnLabel"), //$NON-NLS-1$
       Project_Messages.getString("EffortPerUnit.RolloutColumnLabel") //$NON-NLS-1$
    };
    
    private double[] values;
    private String[] labels;

   private ScopedItem parent;
    
    public EffortPerUnit(ScopedItem parent, double value)
    {
       this(parent, DEFAULT_LABELS, value);
    }
    
    public EffortPerUnit(ScopedItem parent, String initializer)
    {
       this(parent, DEFAULT_LABELS, initializer == null || initializer.indexOf(';') >= 0
             ? 0.0 : Double.parseDouble(initializer));
       if (initializer != null && initializer.indexOf(';') >= 0)
       {
          setValuesFromInitializer(initializer);
       }
    }

    private EffortPerUnit(ScopedItem parent, String[] labels, double value)
    {
       this.parent = parent;
       this.labels = labels == null ? new String[0] : labels;
       values = new double[this.labels.length];
       for (int i = 0; i < values.length; i++)
       {
          values[i] = value;
       }
    }
    
    public int elementCount()
    {
       return values.length;
    }
    
    public double getEffort(int index)
    {
       return values[index];
    }
    
    public void setEffort(int index, double value)
    {
       Double oldValue = new Double(values[index]);
       values[index] = value;
       notifyListeners(new EffortEvent(this, labels[index], new Double(value), oldValue));
    }
    
    public String getLabel(int index)
    {
       return labels[index];
    }
    
    /* should be used only from the specific constructor */
    private void setValuesFromInitializer(String initializer)
    {
       StringTokenizer st = new StringTokenizer(initializer, ";"); //$NON-NLS-1$
       int i = 0;
       while (st.hasMoreTokens() && i < values.length)
       {
          values[i++] = Double.parseDouble(st.nextToken());
       }
    }
    
    public String getInitializer()
    {
       StringBuffer sb = new StringBuffer();
       for (int i = 0; i < values.length; i++)
       {
          if (i > 0)
          {
             sb.append(';');
          }
          sb.append(values[i]);
       }
       return sb.toString();
    }

    public double[] getEffort()
    {
       return (double[]) values.clone();
    }

    public void setEffort(String property, double newValue)
    {
       for (int i = 0; i < labels.length; i++)
       {
          if (property.equals(labels[i]))
          {
             double oldValue = values[i];
             values[i] = newValue;
             parent.getScope().getEffortParameters().markModified();
             notifyListeners(new EffortEvent(this, property, new Double(newValue), new Double(oldValue)));
          }
       }
    }
}
