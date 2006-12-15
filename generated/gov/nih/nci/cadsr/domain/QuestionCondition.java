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
   * A condition created by using a question or other conditions
   */

public  class QuestionCondition 	implements java.io.Serializable 
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
	
	
		
		
	private java.util.Collection question = new java.util.HashSet();
	public java.util.Collection getQuestion()
	{
		if (question==null || question.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Question as child, gov.nih.nci.cadsr.domain.QuestionCondition as parent  where child in elements(parent.question) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Question");				 
				question = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(QuestionCondition.class.getName());
				log.error("QuestionCondition:getQuestion throws exception ... ...",ex);
			}
		}	
		return question;
	}
	
	public void setQuestion(java.util.Collection question)
	{
		this.question = question;
	}	
		
	
	
	
		
		
	private java.util.Collection triggeredActionCollection = new java.util.HashSet();
	public java.util.Collection getTriggeredActionCollection()
	{
		if (triggeredActionCollection==null || triggeredActionCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.TriggerAction as child, gov.nih.nci.cadsr.domain.QuestionCondition as parent  where child in elements(parent.triggeredActionCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.TriggerAction");				 
				triggeredActionCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(QuestionCondition.class.getName());
				log.error("QuestionCondition:getTriggeredActionCollection throws exception ... ...",ex);
			}
		}	
		return triggeredActionCollection;
	}
	
	public void setTriggeredActionCollection(java.util.Collection triggeredActionCollection)
	{
		this.triggeredActionCollection = triggeredActionCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection questionCondition = new java.util.HashSet();
	public java.util.Collection getQuestionCondition()
	{
		if (questionCondition==null || questionCondition.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionConditionComponents as child, gov.nih.nci.cadsr.domain.QuestionCondition as parent  where child in elements(parent.questionCondition) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.QuestionConditionComponents");				 
				questionCondition = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(QuestionCondition.class.getName());
				log.error("QuestionCondition:getQuestionCondition throws exception ... ...",ex);
			}
		}	
		return questionCondition;
	}
	
	public void setQuestionCondition(java.util.Collection questionCondition)
	{
		this.questionCondition = questionCondition;
	}	
		
	
	
	
		
		
	private java.util.Collection conditionComponentCollection = new java.util.HashSet();
	public java.util.Collection getConditionComponentCollection()
	{
		if (conditionComponentCollection==null || conditionComponentCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionConditionComponents as child, gov.nih.nci.cadsr.domain.QuestionCondition as parent  where child in elements(parent.conditionComponentCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.QuestionConditionComponents");				 
				conditionComponentCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(QuestionCondition.class.getName());
				log.error("QuestionCondition:getConditionComponentCollection throws exception ... ...",ex);
			}
		}	
		return conditionComponentCollection;
	}
	
	public void setConditionComponentCollection(java.util.Collection conditionComponentCollection)
	{
		this.conditionComponentCollection = conditionComponentCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection condtionMessage = new java.util.HashSet();
	public java.util.Collection getCondtionMessage()
	{
		if (condtionMessage==null || condtionMessage.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConditionMessage as child, gov.nih.nci.cadsr.domain.QuestionCondition as parent  where child in elements(parent.condtionMessage) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConditionMessage");				 
				condtionMessage = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(QuestionCondition.class.getName());
				log.error("QuestionCondition:getCondtionMessage throws exception ... ...",ex);
			}
		}	
		return condtionMessage;
	}
	
	public void setCondtionMessage(java.util.Collection condtionMessage)
	{
		this.condtionMessage = condtionMessage;
	}	
		
	
	
	
		
		
	private java.util.Collection forcedConditionTriggeredActionCollection = new java.util.HashSet();
	public java.util.Collection getForcedConditionTriggeredActionCollection()
	{
		if (forcedConditionTriggeredActionCollection==null || forcedConditionTriggeredActionCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.TriggerAction as child, gov.nih.nci.cadsr.domain.QuestionCondition as parent  where child in elements(parent.forcedConditionTriggeredActionCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.TriggerAction");				 
				forcedConditionTriggeredActionCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(QuestionCondition.class.getName());
				log.error("QuestionCondition:getForcedConditionTriggeredActionCollection throws exception ... ...",ex);
			}
		}	
		return forcedConditionTriggeredActionCollection;
	}
	
	public void setForcedConditionTriggeredActionCollection(java.util.Collection forcedConditionTriggeredActionCollection)
	{
		this.forcedConditionTriggeredActionCollection = forcedConditionTriggeredActionCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof QuestionCondition) 
		{
			QuestionCondition c =(QuestionCondition)obj; 			 
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