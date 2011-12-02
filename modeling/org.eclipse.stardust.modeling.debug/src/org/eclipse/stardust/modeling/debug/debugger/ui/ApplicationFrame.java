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
package org.eclipse.stardust.modeling.debug.debugger.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;


/**
 * This class is adapted from
 * {@link ag.carnot.workflow.tools.defdesk.ApplicationFrame}. 
 * 
 * @author sborn
 * @version $Revision$
 */
public class ApplicationFrame extends JDialog implements ActionListener
{
   private static final long serialVersionUID = 1L;
    
   private JButton completeButton = null;
   private JButton suspendButton = null;
   private boolean doComplete = false;
    
   /** */
   public ApplicationFrame(Frame parent, String name, JPanel panel)
   {
      super(parent, name, true);

      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

      Container contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());

      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      completeButton = new JButton(Internal_Debugger_Messages.getString("B_Complete")); //$NON-NLS-1$
      completeButton.addActionListener(this);
      buttonPanel.add(completeButton);
      suspendButton = new JButton(Internal_Debugger_Messages.getString("B_Suspend")); //$NON-NLS-1$
      suspendButton.addActionListener(this);
      buttonPanel.add(suspendButton);

      // Put everything together, using the content pane's BorderLayout.
      contentPane.add(panel, BorderLayout.PAGE_START);
      contentPane.add(Box.createHorizontalStrut(1), BorderLayout.CENTER);
      contentPane.add(buttonPanel, BorderLayout.PAGE_END);

      pack();
   }

   /** */
   public void replaceContent(JPanel panel)
   {
      getContentPane().removeAll();
      getContentPane().add(panel);
      getContentPane().validate();
   }

   /**
    * denied the closing of the dialog and shows a message
    */
   protected void processWindowEvent(WindowEvent e)
   {
      if (e.getID() == WindowEvent.WINDOW_CLOSING)
      {
         JOptionPane.showMessageDialog(this, Internal_Debugger_Messages.getString("MSG_DialogWillBeClosedByCarnot")); //$NON-NLS-1$
      }
      super.processWindowEvent(e);
   }

   public boolean getDoComplete()
   {
      return doComplete;
   }
   
   public void actionPerformed(ActionEvent e)
   {
      if (completeButton == e.getSource())
      {
         doComplete = true;
         setVisible(false);
      }
      else if (suspendButton == e.getSource())
      {
         doComplete = false;
         setVisible(false);
      }
   }
}