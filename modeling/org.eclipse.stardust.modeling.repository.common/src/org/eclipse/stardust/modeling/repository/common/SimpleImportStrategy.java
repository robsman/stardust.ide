package org.eclipse.stardust.modeling.repository.common;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.model.xpdl.carnot.merge.MergeAction;

public class SimpleImportStrategy implements ImportStrategy
{
   private final boolean asLink;

   public SimpleImportStrategy(boolean asLink)
   {
      this.asLink = asLink;
   }

   @Override
   public boolean isImportAsLink()
   {
      return asLink;
   }

   @Override
   public MergeAction decideMergeOrReplace(EObject element, EObject original)
   {
      return MergeAction.REPLACE;
   }

   @Override
   public boolean acceptClosure(EObject eObject, List<EObject> closure)
   {
      return true;
   }
}