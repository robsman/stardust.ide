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
package org.eclipse.stardust.modeling.core.editors.parts.dialog;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.swt.widgets.Composite;

public interface IPropertyView
{
   public void createPartControl(Composite parent);

   public void selectionChanged(AbstractEditPart editPart);

   public void dispose();

   public Composite getComposite();

   public void writeContent(EObject eObject);

   public void readContent(EObject eObject);

   public CommandStackEventListener createCommandStackListener();
}
