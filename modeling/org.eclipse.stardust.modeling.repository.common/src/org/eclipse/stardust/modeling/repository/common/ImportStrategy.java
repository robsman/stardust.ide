package org.eclipse.stardust.modeling.repository.common;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.model.xpdl.carnot.merge.MergeAction;

public interface ImportStrategy
{
   boolean isImportAsLink();

   boolean acceptClosure(EObject eObject, List<EObject> closure);

   MergeAction decideMergeOrReplace(EObject element, EObject original);
}
