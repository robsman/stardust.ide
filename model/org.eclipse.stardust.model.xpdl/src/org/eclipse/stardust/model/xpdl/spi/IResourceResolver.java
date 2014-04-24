package org.eclipse.stardust.model.xpdl.spi;

import org.eclipse.emf.ecore.EObject;

public interface IResourceResolver
{
   String resolveToLocalUri(String uri, EObject context);
}
