package org.eclipse.stardust.modeling.repository.common;

public class IconDescriptor
{
   private final String bundleId;

   private final String iconPath;

   public IconDescriptor(String bundleId, String iconPath)
   {
      this.bundleId = bundleId;
      this.iconPath = iconPath;
   }

   public String getBundleId()
   {
      return bundleId;
   }

   public String getIconPath()
   {
      return iconPath;
   }
}
