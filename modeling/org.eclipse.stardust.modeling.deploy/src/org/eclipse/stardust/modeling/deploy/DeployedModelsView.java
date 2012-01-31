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
package org.eclipse.stardust.modeling.deploy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.DateUtils;
import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.api.runtime.DeployedModelDescription;
import org.eclipse.stardust.engine.core.compatibility.gui.utils.DateEntry;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class DeployedModelsView extends JComponent
{
   private static final long serialVersionUID = 1L;

   public static final int DEPLOYMENT_MODE = 0;
   public static final int ADMINISTRATION_MODE = 1;
   public static final int SELECTION_MODE = 2;

   private boolean overwrite;
   private List<ModelTemplate> deployments;

   private JRadioButton oneYear;
   private JRadioButton twoYears;
   private JRadioButton fullScale;
   private JList list;
   private DateEntry validFromEntry;
   private JTextArea commentEntry;

   private long now;
   private long start;
   private long end;
   private List<ModelTemplate> templates = Collections.emptyList();
   private Map<Segment, ModelTemplate> segments = CollectionUtils.newMap();

   private Action oneYearAction = new AbstractAction(
         Deploy_Messages.getString("MSG_ONE_YEAR")) //$NON-NLS-1$
   {
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e)
      {
         set1YearScale();
         list.repaint();
      }
   };

   private Action twoYearsAction = new AbstractAction(
         Deploy_Messages.getString("MSG_TWO_YEARS")) //$NON-NLS-1$
   {
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e)
      {
         Calendar now = Calendar.getInstance();
         Calendar start = (Calendar) now.clone();
         start.add(Calendar.MONTH, -4);
         Calendar end = (Calendar) now.clone();
         end.add(Calendar.MONTH, 20);
         DeployedModelsView.this.now = now.getTime().getTime();
         DeployedModelsView.this.start = start.getTime().getTime();
         DeployedModelsView.this.end = end.getTime().getTime();
         list.repaint();
      }
   };

   private Action fullScaleAction = new AbstractAction(
         Deploy_Messages.getString("MSG_ALL")) //$NON-NLS-1$
   {
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent event)
      {
         Calendar now = Calendar.getInstance();
         long n = now.getTime().getTime();
         long s = Long.MAX_VALUE;
         long e = Long.MIN_VALUE;
         boolean set = false;
         for (ModelTemplate template : templates)
         {
            Date d = template.validFrom;
            if (d != null && s >= d.getTime())
            {
               s = d.getTime();
               set = true;
            }
         }
         if (set)
         {
            long ds = n - s;
            long es = e - n;
            if (ds < es)
            {
               ds = es;
            }
            ds += 1000l * 3600 * 24 * 7;
            DeployedModelsView.this.now = n;
            DeployedModelsView.this.start = n - ds;
            DeployedModelsView.this.end = n + ds;
            list.repaint();
         }
      }
   };

   private ListSelectionListener listener = new ListSelectionListener()
   {
      public void valueChanged(ListSelectionEvent e)
      {
         if (!overwrite)
         {
            list.setSelectedValue(deployments.get(0), false);
         }
         else
         {
            if (e != null)
            {
               if (list.getSelectedIndex() == -1)
               {
                  list.setSelectedIndex(e.getFirstIndex());
               }
            }
         }
      }
   };

   private InputVerifier detailsVerifier = new InputVerifier()
   {
      boolean inMessage = false;

      public boolean verify(JComponent input)
      {
         if (inMessage)
         {
            return false;
         }

         Date from = validFromEntry.getDate();
         for (ModelTemplate m : deployments)
         {
            m.validFrom = from;
         }
         computeSegments();
         list.repaint();

         return true;
      }
   };

   public DeployedModelsView()
   {
      setLayout(new BorderLayout());

      JToolBar buttons = new JToolBar();
      buttons.setFloatable(false);
      buttons.setBorderPainted(false);

      oneYear = new JRadioButton(oneYearAction);
      twoYears = new JRadioButton(twoYearsAction);
      fullScale = new JRadioButton(fullScaleAction);
      ButtonGroup group = new ButtonGroup();
      group.add(oneYear);
      group.add(twoYears);
      group.add(fullScale);
      oneYear.setSelected(true);

      buttons.add(new JLabel(Deploy_Messages.getString("LB_SCALE"))); //$NON-NLS-1$
      buttons.add(oneYear);
      buttons.add(twoYears);
      buttons.add(fullScale);
      add(buttons, BorderLayout.NORTH);
      set1YearScale();

      list = new JList()
      {
         private static final long serialVersionUID = 1L;

         public void paint(Graphics g)
         {
            super.paint(g);
            double w = getSize().getWidth();
            double scale = w / (end - start);
            g.setColor(Color.black);
            int n = (int) (scale * (now - start));
            g.drawLine(n, 0, n, getSize().height);
         }
      };
      list.setCellRenderer(new TimeFrameRenderer());
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JScrollPane scroller = new JScrollPane(list);
      scroller.setPreferredSize(new Dimension(400, 200));
      add(scroller);

      validFromEntry = new DateEntry();
      commentEntry = new JTextArea(5, 15);
      validFromEntry.setInputVerifier(detailsVerifier);
      commentEntry.setInputVerifier(detailsVerifier);

      CellConstraints cc = new CellConstraints();
      FormLayout layout = new FormLayout("4dlu, default, 4dlu, default", //$NON-NLS-1$
            "default, 4dlu, default, 4dlu, default, 4dlu, default, default, default"); //$NON-NLS-1$
      JPanel panel = new JPanel(layout);
      panel.add(new JLabel(Deploy_Messages.getString("LBL_VALID_FROM")), cc.xy(2, 1)); //$NON-NLS-1$
      panel.add(validFromEntry, cc.xy(4, 1));
      panel.add(new JLabel(Deploy_Messages.getString("LBL_DEPLOYMENT_COMMENT")), cc.xy(2, 3)); //$NON-NLS-1$
      panel.add(new JScrollPane(commentEntry), cc.xywh(2, 5, 3, 1));
      add(panel, BorderLayout.EAST);
      list.addListSelectionListener(listener);
   }

   private void set1YearScale()
   {
      Calendar now = Calendar.getInstance();
      Calendar start = (Calendar) now.clone();
      start.add(Calendar.MONTH, -2);
      Calendar end = (Calendar) now.clone();
      end.add(Calendar.MONTH, 10);
      this.now = now.getTime().getTime();
      this.start = start.getTime().getTime();
      this.end = end.getTime().getTime();
   }

   public void setData(List<DeployedModelDescription> descriptions, List<IModel> models, boolean overwrite)
   {
      this.overwrite = overwrite;
      
      ModelTemplate selection = null;
      templates = CollectionUtils.newList(descriptions.size() + models.size());
      deployments = CollectionUtils.newList(models.size());
      for (IModel model : models)
      {
         ModelTemplate deployment = new ModelTemplate(model);
         deployments.add(deployment);
         if (!overwrite)
         {
            templates.add(deployment);
            if (deployments.isEmpty())
            {
               selection = deployment;
            }
         }
      }

      IModel firstModel = models.get(0);
      for (int i = 0; i < descriptions.size(); i++)
      {
         DeployedModelDescription description = descriptions.get(i);
         ModelTemplate template = new ModelTemplate(description);
         if (!overwrite || template.id.equals(firstModel.getId()))
         {
            templates.add(template);
         }
         if (overwrite && template.modelOID == firstModel.getModelOID())
         {
            selection = template;
         }
      }

      if (selection == null)
      {
         selection = deployments.get(0);
      }

      validFromEntry.setDate(selection.validFrom);
      commentEntry.setText(selection.comment);

      initialize(selection);
   }

   private void initialize(ModelTemplate selection)
   {
      computeSegments();
      DefaultListModel listModel = new DefaultListModel();
      for (ModelTemplate template : templates)
      {
         listModel.addElement(template);
      }
      this.list.setModel(listModel);
      this.list.setSelectedValue(selection, true);
      listener.valueChanged(null);
      set1YearScale();
   }

   private void computeSegments()
   {
      ArrayList<Long> list = CollectionUtils.newArrayList();
      for (ModelTemplate m : templates)
      {
         Long ts = new Long(DateUtils.getTimestamp(m.validFrom, Long.MIN_VALUE));
         if (!list.contains(ts))
         {
            list.add(ts);
         }
         ts = Long.MAX_VALUE;
         if (!list.contains(ts))
         {
            list.add(ts);
         }
      }
      Collections.sort(list);
      segments.clear();
      for (int i = 0; i < list.size() - 1; i++)
      {
         Segment s = new Segment(list.get(i), list.get(i + 1));
         for (ModelTemplate m : templates)
         {
            long ts = DateUtils.getTimestamp(m.validFrom, Long.MIN_VALUE);
            long es = Long.MAX_VALUE;
            if (ts <= s.start && es >= s.end)
            {
               segments.put(s, m);
               break;
            }
         }
      }
   }

   private static class Segment
   {
      long start;
      long end;

      public Segment(long start, long end)
      {
         this.start = start;
         this.end = end;
      }
   }

   private class TimeFrameRenderer extends DefaultListCellRenderer
   {
      private static final long serialVersionUID = 1L;
      
      private Color Active = new Color(0, 0.7f, 0);

      private ModelTemplate current;
      private Border border;
      private Font bold;

      private TimeFrameRenderer()
      {
         border = BorderFactory.createEmptyBorder(1, 1, 8, 1);
         setHorizontalAlignment(SwingConstants.LEFT);
      }

      public void paint(Graphics g)
      {
         super.paint(g);
         double w = getSize().getWidth();
         int h = getSize().height - 8;
         double scale = w / (end - start);
         int s = (int) (current.validFrom == null ? -1 :
               scale * (current.validFrom.getTime() - start));
         int e = (int) (w + 1);
         g.setColor(Active);
         g.drawLine(s, h, e, h);
         for (Map.Entry<Segment, ModelTemplate> entry : segments.entrySet())
         {
            if (entry.getValue() == current)
            {
               Segment seg = entry.getKey();
               int s1 = seg.start <= start ? s : (int) (scale * (seg.start - start));
               int e1 = seg.end >= end ? e : (int) (scale * (seg.end - start));
               g.drawLine(s1, h - 1, e1, h - 1);
               g.drawLine(s1, h, e1, h);
               g.drawLine(s1, h + 1, e1, h + 1);
            }
         }
         g.setColor(Color.black);
         g.drawLine(s, h - 3, s, h + 3);
         g.drawLine(e, h - 3, e, h + 3);
      }

      public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus)
      {

         if (isSelected && overwrite)
         {
            current = deployments.get(0);
         }
         else
         {
            current = (ModelTemplate) value;
         }

         super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         setOpaque(isSelected);
         StringBuffer text = new StringBuffer();
         text.append(' ');
         text.append(current.name);
         text.append(": "); //$NON-NLS-1$
         text.append(DateUtils.formatDate(current.validFrom));
         setText(text.toString());
         if (deployments.contains(current))
         {
            setEnabled(true);
            setForeground(Color.red);
            setFont(bold == null ? bold = getFont().deriveFont(Font.BOLD) : bold);
         }
         setBorder(border);
         return this;
      }
   }

   private class ModelTemplate
   {
      private int modelOID;
      private String name;
      private Date validFrom;
      private String comment;
      private String id;

      private ModelTemplate(Object source)
      {
         if (source instanceof DeployedModelDescription)
         {
            set((DeployedModelDescription) source);
         }
         else if (source instanceof IModel)
         {
            set((IModel) source);
         }
      }

      private void set(DeployedModelDescription md)
      {
         modelOID = md.getModelOID();
         id = md.getId();
         name = MessageFormat.format(
               Deploy_Messages.getString("LBL_NAME_VERSION_OID"), new Object[] { //$NON-NLS-1$
                     md.getName(), md.getVersion(), modelOID});
         validFrom = md.getValidFrom();
         comment = md.getDeploymentComment();
      }

      private void set(IModel md)
      {
         modelOID = md.getModelOID();
         id = md.getId();
         String version = (String) md.getAttribute(PredefinedConstants.VERSION_ATT);
        
         if (modelOID == 0)
         {
            name = MessageFormat.format(
                  Deploy_Messages.getString("LBL_NAME_VERSION"), //$NON-NLS-1$
                  new Object[] {md.getName(), version});
         }
         else
         {
            name = MessageFormat.format(
                  Deploy_Messages.getString("LBL_NAME_VERSION_OID"), //$NON-NLS-1$
                  new Object[] {md.getName(), version, modelOID});
         }

         validFrom = (Date) md.getAttribute(PredefinedConstants.VALID_FROM_ATT);
         comment = (String) md.getAttribute(PredefinedConstants.DEPLOYMENT_COMMENT_ATT);
      }
   }

   public Date getValidFrom()
   {
      return validFromEntry.getDate();
   }

   public String getComment()
   {
      return commentEntry.getText();
   }

   public int getSelectedModelOID()
   {
      ModelTemplate template = (ModelTemplate) list.getSelectedValue();
      return template == null ? -1 : template.modelOID;
   }
}
