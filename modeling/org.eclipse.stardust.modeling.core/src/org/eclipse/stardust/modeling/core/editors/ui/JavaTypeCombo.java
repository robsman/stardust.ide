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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.modeling.common.platform.utils.ItemConsumer;
import org.eclipse.stardust.modeling.common.platform.utils.ItemProducer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeFinderListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.progress.UIJob;


/**
 * @author fherinean
 * @version $Revision$
 */
public class JavaTypeCombo extends TextTableCombo
{
   private LabelProvider postLabelProvider = new JavaElementLabelProvider(
                        JavaElementLabelProvider.SHOW_POST_QUALIFIED);
   private LabelProvider preLabelProvider = new JavaElementLabelProvider(
                        JavaElementLabelProvider.SHOW_QUALIFIED);
   private TypeFinder finder = null;

   private String interfaceFilter;
   private String classFilter;

   public JavaTypeCombo(Composite parent, TypeFinder finder, int style)
   {
      super(parent, style);
      getTableManager().addLabelProvider(
         new DefaultTableLabelProvider(postLabelProvider));
      setTypeFinder(finder);
   }

   private boolean matchFilters(final IType type)
   {
      return
         (interfaceFilter == null || finder.implementsInterface(type, interfaceFilter))
          && (classFilter == null || finder.extendsClass(type, classFilter));
   }

   public String getTextAtSelection()
   {
      TableManager manager = getTableManager();
      Object selection = manager.getSelectedItem();
      return selection == null ? "" : preLabelProvider.getText(selection); //$NON-NLS-1$
   }

   public void setInterfaceFilter(String filter)
   {
      interfaceFilter = filter;
   }

   public void setClassFilter(String filter)
   {
      classFilter = filter;
   }

   public void setTypeFinder(TypeFinder finder)
   {
      this.finder = finder;
      setDataProducer(finder == null ? null : new ItemProducer()
      {
         public void produce(String hint, final ItemConsumer consumer)
         {
            JavaTypeCombo.this.finder.findTypes(hint, new TypeFinderListener()
            {
               public void typeFound(final IType type)
               {
                  if (matchFilters(type))
                  {
                     new UIJob(Diagram_Messages.JOB_TypeSearch)
                     {
                        public IStatus runInUIThread(IProgressMonitor monitor)
                        {
                           consumer.addData(type);
                           return Status.OK_STATUS;
                        }
                     }.schedule();
                  }
               }

               public void startSearch()
               {
//                  consumer.clear();
               }

               public void endSearch()
               {
//                  consumer.produceComplete();
               }
            });
         }
      });
   }
}
