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
package org.eclipse.stardust.modeling.core.marker;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.ui.IMarkerResolution;


/**
 * @author barry.grotjahn
 * @version $Revision: 35818 $
 */
public class OrganizationResolutionGenerator implements IResolutionGenerator
{
   public boolean hasResolutions(WorkflowModelEditor editor, Issue issue)
   {
      if (issue.getModelElement() instanceof OrganizationType)
      {
         return issue.getFeature().equals
            (ValidationService.PKG_CWM.getTeamLeadConnectionType_TeamLeadSymbol());
      }
      return false;
   }

   public void addResolutions(List<IMarkerResolution> list, final WorkflowModelEditor editor, final Issue issue)
   {
      if(issue.getFeature().equals(ValidationService.PKG_CWM.getTeamLeadConnectionType_TeamLeadSymbol()))
      {
         list.add(new MarkerResolution(new Action(Diagram_Messages.TXT_SET_TEAM_LEADER)
         {
            public void run()
            {
               OrganizationType organization = (OrganizationType) issue.getModelElement();
               for(Object symbol : organization.getSymbols())
               {
                  for(TeamLeadConnectionType connection : ((OrganizationSymbolType) symbol).getTeamLead())
                  {
                     RoleType teamLead = connection.getTeamLeadSymbol().getRole();
                     if(organization.getTeamLead() == null)
                     {
                        SetValueCmd cmd = new SetValueCmd(organization, 
                              ValidationService.PKG_CWM.getOrganizationType_TeamLead(), teamLead);
                        editor.getEditDomain().getCommandStack().execute(cmd);
                     }                     
                  }                  
               }               
            }
         }));            
      }
   }
}