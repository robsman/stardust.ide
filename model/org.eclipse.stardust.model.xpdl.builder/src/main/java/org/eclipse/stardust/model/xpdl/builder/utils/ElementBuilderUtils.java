package org.eclipse.stardust.model.xpdl.builder.utils;

import java.util.List;


public class ElementBuilderUtils
{

   public static <T, E extends T> String deriveDefaultId(E element, List<T> domain,
         String baseId)
   {
      String id = baseId + (1 + domain.size());
      if (null != XpdlModelUtils.findElementById(domain, id))
      {
         int counter = 1;
         while (true)
         {
            String paddedId = id + "_" + counter;
            if (null == XpdlModelUtils.findElementById(domain, paddedId))
            {
               id = paddedId;
               break;
            }
         }
      }

      return id;
   }

   private ElementBuilderUtils()
   {
      // utility class
   }
}
