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
package org.eclipse.stardust.modeling.refactoring.query.participants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.jdt.ui.search.IMatchPresentation;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.search.ui.text.Match;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.refactoring.query.matches.EObjectMatch;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;


/**
 * @author fherinean
 * @version $Revision$
 */
public class MatchPresentation implements IMatchPresentation
{
   public ILabelProvider createLabelProvider()
   {
      return new EObjectLabelProvider(null)
      {
         public String getText(Object element)
         {
            IIdentifiableElement object = fixElement(element);
            if (object instanceof ModelType)
            {
               return getFixedText((EObject) element);
            }
            else
            {
               return getFixedText((EObject) element, object) + " (" + getFixedText(object.eContainer()) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            }
         }

         public Image getImage(Object element)
         {
            if(editor == null)
            {
               ModelType modelType = ModelUtils.findContainingModel((EObject) element);
               if(modelType != null)
               {
                  editor = GenericUtils.getWorkflowModelEditor(modelType);
               }
            }   
            if(editor == null)
            {
               return null;
            }            
            return super.getImage(fixElement(element));
         }

         private String getFixedText(EObject object)
         {
            return getFixedText(object, null);
         }

         private String getFixedText(EObject object, EObject upTo)
         {
            if (object instanceof ModelType || object == upTo)
            {
               return getLabel(object);
            }
            return getFixedText(object.eContainer(), upTo) + ", " + getLabel(object); //$NON-NLS-1$
         }

         private String getLabel(EObject object)
         {
            String type = object.eClass().getName();
            if (type.endsWith("Type")) //$NON-NLS-1$
            {
               type = type.substring(0, type.length() - 4);
            }
            return Character.toUpperCase(type.charAt(0)) +
               type.substring(1) + ": " + super.getText(object); //$NON-NLS-1$
         }
      };
   }

   private IIdentifiableElement fixElement(Object element)
   {
      if (element instanceof EObject)
      {
         EObject eObject = (EObject) element;
         return findIdentifiable(eObject);
      }
      return null;
   }

   public void showMatch(Match match, int currentOffset, int currentLength,
                         boolean activate) throws PartInitException
   {
      Object element = match.getElement();
      if (element instanceof EObject)
      {
         EObject eObject = (EObject) element;
         final IEditorPart part = getEditorPart(((EObjectMatch) match).getFile(), activate);
         if (part != null)
         {
            if (part instanceof WorkflowModelEditor)
            {
               WorkflowModelEditor editor = (WorkflowModelEditor) part;
               IIdentifiableElement identifiable = findIdentifiable(eObject);
               IIdentifiableElement other = findCorrespondingObject(
                  editor.getWorkflowModel(), identifiable);
               IModelElement selection = findEditPart(editor, other);
               editor.selectElement(selection);
/*               ShowPropertiesAction action = new ShowPropertiesAction(part.getSite(),
                  part.getSite().getSelectionProvider());
               action.run();*/
            }
         }
      }
   }

   private IModelElement findEditPart(WorkflowModelEditor editor, EObject other)
   {
      EditPart part = null;
      while (part == null || !(other instanceof IModelElement))
      {
         part = editor.findEditPart(other);
         if (part == null || !(other instanceof IModelElement))
         {
            other = other.eContainer();
         }
      }
      return (IModelElement) other;
   }

   private IIdentifiableElement findIdentifiable(EObject eObject)
   {
      while (!(eObject instanceof IIdentifiableElement))
      {
         eObject = eObject.eContainer();
      }
      return (IIdentifiableElement) eObject;
   }

   private IIdentifiableElement findCorrespondingObject(ModelType model, IIdentifiableElement identifiable)
   {
      if (identifiable instanceof ModelType)
      {
         return model;
      }
      EObject otherParent = findCorrespondingObject(model, (IIdentifiableElement) identifiable.eContainer());
      EClass otherParentClass = otherParent.eClass();
      String featureName = identifiable.eContainingFeature().getName();
      EStructuralFeature feature = otherParentClass.getEStructuralFeature(featureName);
      return ModelUtils.findIdentifiableElement(otherParent, feature, identifiable.getId());
   }

   private IEditorPart getEditorPart(IFile file, boolean activate) throws PartInitException
   {
      List<IEditorReference> refs = new ArrayList<IEditorReference>();

      final IWorkbench workbench = PlatformUI.getWorkbench();
      IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
      if (windows != null)
      {
         for (int i = 0; i < windows.length; i++)
         {
            IWorkbenchWindow window = windows[i];
            IWorkbenchPage[] pages = window.getPages();
            if (pages != null)
            {
               for (int j = 0; j < pages.length; j++)
               {
                  IWorkbenchPage page = pages[j];
                  IEditorReference[] references = page.getEditorReferences();
                  if (references != null)
                  {
                     for (int k = 0; k < references.length; k++)
                     {
                        IEditorReference reference = references[k];
                        IEditorInput input = reference.getEditorInput();
                        if (input instanceof FileEditorInput)
                        {
                           IFile current = ((FileEditorInput) input).getFile();
                           if (file.equals(current))
                           {
                              refs.add(reference);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      if (refs.isEmpty())
      {
         IWorkbenchWindow wbw = workbench.getActiveWorkbenchWindow();
         IWorkbenchPage wbp = wbw.getActivePage();
         return IDE.openEditor(wbp, file, activate);
      }
      for (int i = 0; i < refs.size(); i++)
      {
         IEditorReference reference = (IEditorReference) refs.get(i);
         IEditorPart part = reference.getEditor(false);
         if (part instanceof WorkflowModelEditor)
         {
            return activateEditor(reference, activate);
         }
      }
      return activateEditor((IEditorReference) refs.get(0), activate);
   }

   private IEditorPart activateEditor(IEditorReference reference, boolean activate)
   {
      IEditorPart part = reference.getEditor(activate);
      reference.getPage().bringToTop(part);
      return part;
   }
}