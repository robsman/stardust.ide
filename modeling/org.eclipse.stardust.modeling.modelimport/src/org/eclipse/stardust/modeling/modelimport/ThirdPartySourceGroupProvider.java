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
package org.eclipse.stardust.modeling.modelimport;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class ThirdPartySourceGroupProvider
{
   private Composite addtionalOptionsComposite;

   private Button withDiagramButton;

   private Button prototypeModeButton;

   private Button productionModeButton;

   public Control createAdditionalOptionsGroup(Composite optionsGroup, boolean enabled)
   {
      addtionalOptionsComposite = new Composite(optionsGroup, SWT.NONE);
      {
         GridLayout layout = new GridLayout();
         layout.numColumns = 4;
         layout.verticalSpacing = 12;
         addtionalOptionsComposite.setLayout(layout);

         GridData data = new GridData();
         data.verticalAlignment = GridData.FILL;
         data.grabExcessVerticalSpace = true;
         data.horizontalAlignment = GridData.FILL;
         data.horizontalSpan = 2;
         addtionalOptionsComposite.setLayoutData(data);
      }

      withDiagramButton = new Button(addtionalOptionsComposite, SWT.CHECK);
      withDiagramButton.setEnabled(enabled);
      Label withDiagramLabel = new Label(addtionalOptionsComposite, SWT.NONE);
      {
         GridData data = new GridData();
         data.horizontalSpan = 3;
         withDiagramLabel.setLayoutData(data);
      }
      withDiagramLabel.setText(Import_Messages.LB_IncludeDiagram); // TODO

      prototypeModeButton = new Button(addtionalOptionsComposite, SWT.RADIO);
      prototypeModeButton.setEnabled(enabled);
      Label prototypeModeLabel = new Label(addtionalOptionsComposite, SWT.NONE);
      prototypeModeLabel.setText(Import_Messages.LB_PrototypeMode); // TODO
      if (enabled)
      {
         prototypeModeButton.setSelection(true);
      }

      productionModeButton = new Button(addtionalOptionsComposite, SWT.RADIO);
      productionModeButton.setEnabled(enabled);
      Label productionModeLabel = new Label(addtionalOptionsComposite, SWT.NONE);
      productionModeLabel.setText(Import_Messages.LB_ProductionMode); // TODO

      return addtionalOptionsComposite;
   }
   
   protected int getImportMode()
   {
      if (prototypeModeButton.getSelection())
      {
         return 0;
      }
      else
      {
         return 1;
      }
   }
   
   protected boolean includesDiagrams()
   {
      return withDiagramButton.getSelection();
   }
}
