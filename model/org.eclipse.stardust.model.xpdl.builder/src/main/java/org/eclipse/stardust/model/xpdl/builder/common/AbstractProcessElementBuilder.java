package org.eclipse.stardust.model.xpdl.builder.common;

import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;


public abstract class AbstractProcessElementBuilder<T extends IModelElement & IIdentifiableElement, B extends AbstractProcessElementBuilder<T, B>>
      extends AbstractModelElementBuilder<T, B>
{
   protected ProcessDefinitionType process;

   public AbstractProcessElementBuilder(T element)
   {
      super(element);
   }

   public AbstractProcessElementBuilder(ProcessDefinitionType process, T element)
   {
      this(element);

      inProcess(process);
   }

   @Override
   protected T finalizeElement()
   {
      T element = super.finalizeElement();

      if (null == process)
      {
         throw new NullPointerException("Process Definition must be set.");
      }

      return element;
   }

   public B inProcess(ProcessDefinitionType process)
   {
      setProcess(process);

      return self();
   }

   public B forProcess(ProcessDefinitionType process)
   {
      setProcess(process);

      return self();
   }

   public ProcessDefinitionType process()
   {
      return process;
   }

   protected void setProcess(ProcessDefinitionType process)
   {
      if (null == this.process)
      {
         if (null != process)
         {
            this.process = process;

            ModelType containingModel = XpdlModelUtils.findContainingModel(process);
            if (null != containingModel)
            {
               setModel(containingModel);
            }
         }
      }
      else
      {
         if (this.process != process)
         {
            throw new IllegalArgumentException("Process Definition must only be set once.");
         }
      }
   }
}
