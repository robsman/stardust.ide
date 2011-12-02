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
package org.eclipse.stardust.modeling.project.effort;

import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.modeling.project.Constants;

import ag.carnot.base.CollectionUtils;

public class EffortCalculator
{
	public List<EffortEntry> calculateEfforts(EffortParameters parameters)
	{
		List<EffortEntry> entryList = CollectionUtils.newList();
		for (int i = 0; i < parameters.SCOPE_LIST.size(); i++)
		{
		   EffortParameterScope scope = (EffortParameterScope) parameters.SCOPE_LIST.get(i);
           List<IExtensibleElement> extensibles = parameters.getExtensibles(scope);
           for (IExtensibleElement extensible : extensibles)
           {
              calculateEffortsForModelElement(extensible, scope, entryList);
		   }
		}
		return entryList;
	}

	public void calculateEffortsForModelElement(IExtensibleElement element,
			EffortParameterScope elementType, List<EffortEntry> entryList)
	{
		for (int n = 0; n < element.getAttribute().size(); ++n)
		{
			AttributeType attribute = (AttributeType) element.getAttribute().get(n);

			// Filter cost driver parameters
			String name = attribute.getName();
			if (!name.equals(Constants.NOTIFIER) && name.startsWith(Constants.SCOPE) &&
			      !name.startsWith(Constants.SCOPE + ":" + Constants.EFFORT_PARAMETER)) //$NON-NLS-1$
			{
				// Need to add the ":"
				EffortEntry entry = new EffortEntry(elementType, ((IIdentifiableElement) element).getName(),
                    name.substring(Constants.SCOPE.length() + 1),
					attribute.getValue());
                entryList.add(entry);
			}
		}
	}
}
