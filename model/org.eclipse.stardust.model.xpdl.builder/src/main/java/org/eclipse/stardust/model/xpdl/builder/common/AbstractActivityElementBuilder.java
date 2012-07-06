package org.eclipse.stardust.model.xpdl.builder.common;

import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;


public abstract class AbstractActivityElementBuilder<T extends IModelElement & IIdentifiableElement, B extends AbstractActivityElementBuilder<T, B>>
      extends AbstractProcessElementBuilder<T, B>
{
   protected ActivityType activity;

   public AbstractActivityElementBuilder(T element)
   {
      super(null, element);
   }

   public B forActivity(ActivityType activity)
   {
      setActivity(activity);
      
      return self();
   }

   @Override
   protected T finalizeElement()
   {
      super.finalizeElement();
      
      if (null == activity)
      {
         throw new NullPointerException("Activity must be set.");
      }

      return element;
   }

   public ActivityType activity()
   {
      return activity;
   }

   protected void setActivity(ActivityType activity)
   {
      if (null == this.activity)
      {
         if (null != activity)
         {
            this.activity = activity;

            ProcessDefinitionType process = XpdlModelUtils.findContainingProcess(activity);
            if (null != process)
            {
               setProcess(process);
            }
         }
      }
      else
      {
         if (this.activity != activity)
         {
            throw new IllegalArgumentException("Activity must only be set once.");
         }
      }
   }
}
