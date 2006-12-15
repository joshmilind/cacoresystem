package gov.nih.nci.cadsr.domain;

import gov.nih.nci.cadsr.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**

   */

public  class TriggerAction 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String action;
	public java.lang.String getAction()
	{
		return action;
	}
	public void setAction(java.lang.String action)
	{
		this.action = action;
	}
	
		
	public java.lang.String createdBy;
	public java.lang.String getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(java.lang.String createdBy)
	{
		this.createdBy = createdBy;
	}
	
		
	public java.lang.String criterionValue;
	public java.lang.String getCriterionValue()
	{
		return criterionValue;
	}
	public void setCriterionValue(java.lang.String criterionValue)
	{
		this.criterionValue = criterionValue;
	}
	
		
	public java.util.Date dateCreated;
	public java.util.Date getDateCreated()
	{
		return dateCreated;
	}
	public void setDateCreated(java.util.Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}
	
		
	public java.util.Date dateModified;
	public java.util.Date getDateModified()
	{
		return dateModified;
	}
	public void setDateModified(java.util.Date dateModified)
	{
		this.dateModified = dateModified;
	}
	
		
	public java.lang.String forcedValue;
	public java.lang.String getForcedValue()
	{
		return forcedValue;
	}
	public void setForcedValue(java.lang.String forcedValue)
	{
		this.forcedValue = forcedValue;
	}
	
		
	public java.lang.String id;
	public java.lang.String getId()
	{
		return id;
	}
	public void setId(java.lang.String id)
	{
		this.id = id;
	}
	
		
	public java.lang.String instruction;
	public java.lang.String getInstruction()
	{
		return instruction;
	}
	public void setInstruction(java.lang.String instruction)
	{
		this.instruction = instruction;
	}
	
		
	public java.lang.String modifiedBy;
	public java.lang.String getModifiedBy()
	{
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}
	
		
	public java.lang.String triggerRelationship;
	public java.lang.String getTriggerRelationship()
	{
		return triggerRelationship;
	}
	public void setTriggerRelationship(java.lang.String triggerRelationship)
	{
		this.triggerRelationship = triggerRelationship;
	}
	
	
		
		
	private java.util.Collection protocolCollection = new java.util.HashSet();
	public java.util.Collection getProtocolCollection()
	{
		if (protocolCollection==null || protocolCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Protocol as child, gov.nih.nci.cadsr.domain.TriggerAction as parent  where child in elements(parent.protocolCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Protocol");				 
				protocolCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(TriggerAction.class.getName());
				log.error("TriggerAction:getProtocolCollection throws exception ... ...",ex);
			}
		}	
		return protocolCollection;
	}
	
	public void setProtocolCollection(java.util.Collection protocolCollection)
	{
		this.protocolCollection = protocolCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection administeredComponentClassSchemeItemCollection = new java.util.HashSet();
	public java.util.Collection getAdministeredComponentClassSchemeItemCollection()
	{
		if (administeredComponentClassSchemeItemCollection==null || administeredComponentClassSchemeItemCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem as child, gov.nih.nci.cadsr.domain.TriggerAction as parent  where child in elements(parent.administeredComponentClassSchemeItemCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem");				 
				administeredComponentClassSchemeItemCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(TriggerAction.class.getName());
				log.error("TriggerAction:getAdministeredComponentClassSchemeItemCollection throws exception ... ...",ex);
			}
		}	
		return administeredComponentClassSchemeItemCollection;
	}
	
	public void setAdministeredComponentClassSchemeItemCollection(java.util.Collection administeredComponentClassSchemeItemCollection)
	{
		this.administeredComponentClassSchemeItemCollection = administeredComponentClassSchemeItemCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.QuestionCondition questionCondition;
	public gov.nih.nci.cadsr.domain.QuestionCondition getQuestionCondition()
	{
			
		if(questionCondition==null ||  questionCondition.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionCondition as child where child.id in (select parent.questionCondition.id from gov.nih.nci.cadsr.domain.TriggerAction as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.QuestionCondition");				 
				if (resultList!=null && resultList.size()>0) 
					questionCondition = (gov.nih.nci.cadsr.domain.QuestionCondition)resultList.get(0);
				else
					questionCondition = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(TriggerAction.class.getName());
				log.error("TriggerAction:getQuestionCondition throws exception ... ...",ex);
			}
		}
		return questionCondition;	
					
	}

	public void setQuestionCondition(gov.nih.nci.cadsr.domain.QuestionCondition questionCondition)
	{
		this.questionCondition = questionCondition;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.FormElement targetFormElement;
	public gov.nih.nci.cadsr.domain.FormElement getTargetFormElement()
	{
			
		if(targetFormElement==null ||  targetFormElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.FormElement as child where child.id in (select parent.targetFormElement.id from gov.nih.nci.cadsr.domain.TriggerAction as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.FormElement");				 
				if (resultList!=null && resultList.size()>0) 
					targetFormElement = (gov.nih.nci.cadsr.domain.FormElement)resultList.get(0);
				else
					targetFormElement = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(TriggerAction.class.getName());
				log.error("TriggerAction:getTargetFormElement throws exception ... ...",ex);
			}
		}
		return targetFormElement;	
					
	}

	public void setTargetFormElement(gov.nih.nci.cadsr.domain.FormElement targetFormElement)
	{
		this.targetFormElement = targetFormElement;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.QuestionCondition enforcedCondition;
	public gov.nih.nci.cadsr.domain.QuestionCondition getEnforcedCondition()
	{
			
		if(enforcedCondition==null ||  enforcedCondition.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionCondition as child where child.id in (select parent.enforcedCondition.id from gov.nih.nci.cadsr.domain.TriggerAction as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.QuestionCondition");				 
				if (resultList!=null && resultList.size()>0) 
					enforcedCondition = (gov.nih.nci.cadsr.domain.QuestionCondition)resultList.get(0);
				else
					enforcedCondition = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(TriggerAction.class.getName());
				log.error("TriggerAction:getEnforcedCondition throws exception ... ...",ex);
			}
		}
		return enforcedCondition;	
					
	}

	public void setEnforcedCondition(gov.nih.nci.cadsr.domain.QuestionCondition enforcedCondition)
	{
		this.enforcedCondition = enforcedCondition;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.FormElement sourceFormElement;
	public gov.nih.nci.cadsr.domain.FormElement getSourceFormElement()
	{
			
		if(sourceFormElement==null ||  sourceFormElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.FormElement as child where child.id in (select parent.sourceFormElement.id from gov.nih.nci.cadsr.domain.TriggerAction as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.FormElement");				 
				if (resultList!=null && resultList.size()>0) 
					sourceFormElement = (gov.nih.nci.cadsr.domain.FormElement)resultList.get(0);
				else
					sourceFormElement = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(TriggerAction.class.getName());
				log.error("TriggerAction:getSourceFormElement throws exception ... ...",ex);
			}
		}
		return sourceFormElement;	
					
	}

	public void setSourceFormElement(gov.nih.nci.cadsr.domain.FormElement sourceFormElement)
	{
		this.sourceFormElement = sourceFormElement;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof TriggerAction) 
		{
			TriggerAction c =(TriggerAction)obj; 			 
			String thisId = getId();		
			
			if(thisId != null && thisId.equals(c.getId()))
				eq = true;
			
			}
			return eq;
		}
		
	public int hashCode()
	{
		int h = 0;
		
		if(getId() != null)
			h += getId().hashCode();
		
		return h;
	}
}