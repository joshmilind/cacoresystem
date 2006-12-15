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
   * The allowable values for a given question on a Case Report Form.
   */

public  class ValidValue  extends gov.nih.nci.cadsr.domain.FormElement 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String description;
	public java.lang.String getDescription()
	{
		return description;
	}
	public void setDescription(java.lang.String description)
	{
		this.description = description;
	}
	
		
	public java.lang.Integer displayOrder;
	public java.lang.Integer getDisplayOrder()
	{
		return displayOrder;
	}
	public void setDisplayOrder(java.lang.Integer displayOrder)
	{
		this.displayOrder = displayOrder;
	}
	
		
	private java.lang.String meaningText;
	public java.lang.String getMeaningText()
	{
		return meaningText;
	}
	public void setMeaningText(java.lang.String meaningText)
	{
		this.meaningText = meaningText;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.Question question;
	public gov.nih.nci.cadsr.domain.Question getQuestion()
	{
			
		if(question==null ||  question.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Question as child where child.id in (select parent.question.id from gov.nih.nci.cadsr.domain.ValidValue as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Question");				 
				if (resultList!=null && resultList.size()>0) 
					question = (gov.nih.nci.cadsr.domain.Question)resultList.get(0);
				else
					question = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ValidValue.class.getName());
				log.error("ValidValue:getQuestion throws exception ... ...",ex);
			}
		}
		return question;	
					
	}

	public void setQuestion(gov.nih.nci.cadsr.domain.Question question)
	{
		this.question = question;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue valueDomainPermissibleValue;
	public gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue getValueDomainPermissibleValue()
	{
			
		if(valueDomainPermissibleValue==null ||  valueDomainPermissibleValue.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue as child where child.id in (select parent.valueDomainPermissibleValue.id from gov.nih.nci.cadsr.domain.ValidValue as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue");				 
				if (resultList!=null && resultList.size()>0) 
					valueDomainPermissibleValue = (gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue)resultList.get(0);
				else
					valueDomainPermissibleValue = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ValidValue.class.getName());
				log.error("ValidValue:getValueDomainPermissibleValue throws exception ... ...",ex);
			}
		}
		return valueDomainPermissibleValue;	
					
	}

	public void setValueDomainPermissibleValue(gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue valueDomainPermissibleValue)
	{
		this.valueDomainPermissibleValue = valueDomainPermissibleValue;
	}
		
	
	
	
		
	
	
	
		
		
	private java.util.Collection conditionComponent = new java.util.HashSet();
	public java.util.Collection getConditionComponent()
	{
		if (conditionComponent==null || conditionComponent.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionConditionComponents as child, gov.nih.nci.cadsr.domain.ValidValue as parent  where child in elements(parent.conditionComponent) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.QuestionConditionComponents");				 
				conditionComponent = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ValidValue.class.getName());
				log.error("ValidValue:getConditionComponent throws exception ... ...",ex);
			}
		}	
		return conditionComponent;
	}
	
	public void setConditionComponent(java.util.Collection conditionComponent)
	{
		this.conditionComponent = conditionComponent;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ValidValue) 
		{
			ValidValue c =(ValidValue)obj; 			 
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