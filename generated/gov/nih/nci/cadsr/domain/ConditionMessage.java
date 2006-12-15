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
   * The messge associated to a condition
   */

public  class ConditionMessage 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String id;
	public java.lang.String getId()
	{
		return id;
	}
	public void setId(java.lang.String id)
	{
		this.id = id;
	}
	
		
	public java.lang.String message;
	public java.lang.String getMessage()
	{
		return message;
	}
	public void setMessage(java.lang.String message)
	{
		this.message = message;
	}
	
		
	public java.lang.String messageType;
	public java.lang.String getMessageType()
	{
		return messageType;
	}
	public void setMessageType(java.lang.String messageType)
	{
		this.messageType = messageType;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.QuestionCondition questionCondition;
	public gov.nih.nci.cadsr.domain.QuestionCondition getQuestionCondition()
	{
			
		if(questionCondition==null ||  questionCondition.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionCondition as child where child.id in (select parent.questionCondition.id from gov.nih.nci.cadsr.domain.ConditionMessage as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(ConditionMessage.class.getName());
				log.error("ConditionMessage:getQuestionCondition throws exception ... ...",ex);
			}
		}
		return questionCondition;	
					
	}

	public void setQuestionCondition(gov.nih.nci.cadsr.domain.QuestionCondition questionCondition)
	{
		this.questionCondition = questionCondition;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ConditionMessage) 
		{
			ConditionMessage c =(ConditionMessage)obj; 			 
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