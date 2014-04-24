package org.eclipse.stardust.modeling.repository.common.ui;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.model.xpdl.carnot.merge.MergeAction;
import org.eclipse.stardust.model.xpdl.carnot.util.IconFactory;
import org.eclipse.stardust.modeling.repository.common.ImportStrategy;
import org.eclipse.stardust.modeling.repository.common.ui.dialogs.ClosureDisplayDialog;
import org.eclipse.stardust.modeling.repository.common.ui.dialogs.ConflictDialog;
import org.eclipse.stardust.modeling.repository.common.ui.dialogs.UsageDisplayDialog;

public class InteractiveImportStrategy implements ImportStrategy
{
   private final boolean asLink;

   private final IconFactory iconFactory;

   public InteractiveImportStrategy(boolean asLink, IconFactory iconFactory)
   {
      this.asLink = asLink;
      this.iconFactory = iconFactory;
   }

   @Override
   public boolean isImportAsLink()
   {
      return asLink;
   }

   @Override
   public boolean acceptClosure(EObject eObject, List<EObject> closure)
   {
      return ClosureDisplayDialog.acceptClosure(null, iconFactory, eObject, closure);
   }

   @Override
   public MergeAction decideMergeOrReplace(EObject element, EObject original)
   {
      if(asLink)
      {
         MergeAction action = ConflictDialog.acceptClosure(null, iconFactory, element, original);
         if (action == null)
         {
            UsageDisplayDialog.setUsage(null);
         }
         return action;
      }
      else
      {
         MergeAction action = UsageDisplayDialog.acceptClosure(null, iconFactory, element, original);
         if (action == null)
         {
            UsageDisplayDialog.setUsage(null);
         }
         return action;
      }
   }

}
