package org.eclipse.stardust.ui.web.modeler.xpdl.edit.postprocessing;

import org.eclipse.emf.ecore.EObject;
import org.springframework.stereotype.Service;

import org.eclipse.stardust.common.Pair;
import org.eclipse.stardust.model.xpdl.builder.session.Modification;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.ui.web.modeler.edit.spi.ChangePostprocessor;

@Service
public class XpdlChangesetNormalizer implements ChangePostprocessor
{
   @Override
   public int getInspectionPhase()
   {
      return 1;
   }

   @Override
   public void inspectChange(Modification change)
   {
      // modified
      for (EObject changedObject : change.getModifiedElements())
      {
         if (null == change.findContainer(changedObject, ModelType.class))
         {
            // no XPDL element
            continue;
         }
         Pair<InspectionQualifier, EObject> inspectionResult = inspectModification(changedObject);
         if ((null == inspectionResult) || (InspectionQualifier.Accept == inspectionResult.getFirst()))
         {
            continue;
         }
         else if (InspectionQualifier.Ignore == inspectionResult.getFirst())
         {
            change.markUnmodified(changedObject);
         }
         else if (InspectionQualifier.ModifyInstead == inspectionResult.getFirst())
         {
            change.markAlsoModified(inspectionResult.getSecond());
            change.markUnmodified(changedObject);
         }
      }

      for (EObject candidate : change.getAddedElements())
      {
         if ((null != change.findContainer(candidate, ModelType.class))
               && !isModelOrModelElement(candidate))
         {
            if ( !isIgnoredElement(candidate))
            {
               // report any change to a non-element sub-object as modification of the
               // containing parent element
               EObject changedElement = determineChangedElement(candidate);
               if ((candidate != changedElement)
                     && !(change.getAddedElements().contains(changedElement) || change.getRemovedElements()
                           .contains(changedElement)))
               {
                  change.markAlsoModified(changedElement);
               }
            }
            change.markUnmodified(candidate);
         }

      }
      // removed objects will automatically be reported as modifications of their
      // container (TODO check multi level containment)

      // removed
      for (EObject candidate : change.getRemovedElements())
      {
         if ((null != change.findContainer(candidate, ModelType.class))
               && !isModelOrModelElement(candidate))
         {
            change.markUnmodified(candidate);
         }
      }
   }

   public Pair<InspectionQualifier, EObject> inspectModification(EObject element)
   {
      if (isModelOrModelElement(element))
      {
         return ACCEPT;
      }
      else
      {
         EObject replacement = determineChangedElement(element);
         if (null != replacement)
         {
            return modifyInstead(replacement);
         }
         else
         {
            return ACCEPT;
         }
      }
   }

   public EObject determineChangedElement(EObject changedObject)
   {
      EObject element = changedObject;
      while ((null != element) && !isModelOrModelElement(element))
      {
         element = element.eContainer();
      }
      return (null != element) ? element : changedObject;
   }

   private boolean isModelOrModelElement(EObject changedObject)
   {
      return (changedObject instanceof ModelType)
            || (changedObject instanceof IModelElement)
            || (changedObject instanceof org.eclipse.stardust.model.xpdl.xpdl2.Extensible);
   }

   private boolean isIgnoredElement(EObject changedObject)
   {
      // ignore nothing for the time being
      return false;
   }

   enum InspectionQualifier
   {
      Accept,
      Ignore,
      ModifyInstead,
   }

   private static final Pair<InspectionQualifier, EObject> ACCEPT = new Pair<InspectionQualifier, EObject>(
         InspectionQualifier.Accept, null);

   private static final Pair<InspectionQualifier, EObject> IGNORE = new Pair<InspectionQualifier, EObject>(
         InspectionQualifier.Ignore, null);

   private static final Pair<InspectionQualifier, EObject> modifyInstead(EObject element)
   {
      return new Pair<InspectionQualifier, EObject>(InspectionQualifier.ModifyInstead, element);
   }
}
