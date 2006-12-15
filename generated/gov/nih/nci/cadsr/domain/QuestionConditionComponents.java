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
   * the components of a condition. the components could be a question or other condition 
   * 
   */

public  class QuestionConditionComponents 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String constantValue;
	public java.lang.String getConstantValue()
	{
		return constantValue;
	}
	public void setConstantValue(java.lang.String constantValue)
	{
		this.constantValue = constantValue;
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
	
		
	public java.lang.String id;
	public java.lang.String getId()
	{
		return id;
	}
	public void setId(java.lang.String id)
	{
		this.id = id;
	}
	
		
	public java.lang.String logicalOperand;
	public java.lang.String getLogicalOperand()
	{
		return logicalOperand;
	}
	public void setLogicalOperand(java.lang.String logicalOperand)
	{
		this.logicalOperand = logicalOperand;
	}
	
		
	public java.lang.String operand;
	public java.lang.String getOperand()
	{
		return operand;
	}
	public void setOperand(java.lang.String operand)
	{
		this.operand = operand;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.Question question;
	public gov.nih.nci.cadsr.domain.Question getQuestion()
	{
			
		if(question==null ||  question.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Question as child where child.id in (select parent.question.id from gov.nih.nci.cadsr.domain.QuestionConditionComponents as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(QuestionConditionComponents.class.getName());
				log.error("QuestionConditionComponents:getQuestion throws exception ... ...",ex);
			}
		}
		return question;	
					
	}

	public void setQuestion(gov.nih.nci.cadsr.domain.Question question)
	{
		this.question = question;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.QuestionCondition questionCondition;
	public gov.nih.nci.cadsr.domain.QuestionCondition getQuestionCondition()
	{
			
		if(questionCondition==null ||  questionCondition.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionCondition as child where child.id in (select parent.questionCondition.id from gov.nih.nci.cadsr.domain.QuestionConditionComponents as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(QuestionConditionComponents.class.getName());
				log.error("QuestionConditionComponents:getQuestionCondition throws exception ... ...",ex);
			}
		}
		return questionCondition;	
					
	}

	public void setQuestionCondition(gov.nih.nci.cadsr.domain.QuestionCondition questionCondition)
	{
		this.questionCondition = questionCondition;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.QuestionCondition parentQuestionCondition;
	public gov.nih.nci.cadsr.domain.QuestionCondition getParentQuestionCondition()
	{
			
		if(parentQuestionCondition==null ||  parentQuestionCondition.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionCondition as child where child.id in (select parent.parentQuestionCondition.id from gov.nih.nci.cadsr.domain.QuestionConditionComponents as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.QuestionCondition");				 
				if (resultList!=null && resultList.size()>0) 
					parentQuestionCondition = (gov.nih.nci.cadsr.domain.QuestionCondition)resultList.get(0);
				else
					parentQuestionCondition = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(QuestionConditionComponents.class.getName());
				log.error("QuestionConditionComponents:getParentQuestionCondition throws exception ... ...",ex);
			}
		}
		return parentQuestionCondition;	
					
	}

	public void setParentQuestionCondition(gov.nih.nci.cadsr.domain.QuestionCondition parentQuestionCondition)
	{
		this.parentQuestionCondition = parentQuestionCondition;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.Function function;
	public gov.nih.nci.cadsr.domain.Function getFunction()
	{
			
		if(function==null ||  function.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Function as child where child.id in (select parent.function.id from gov.nih.nci.cadsr.domain.QuestionConditionComponents as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Function");				 
				if (resultList!=null && resultList.size()>0) 
					function = (gov.nih.nci.cadsr.domain.Function)resultList.get(0);
				else
					function = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(QuestionConditionComponents.class.getName());
				log.error("QuestionConditionComponents:getFunction throws exception ... ...",ex);
			}
		}
		return function;	
					
	}

	public void setFunction(gov.nih.nci.cadsr.domain.Function function)
	{
		this.function = function;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ValidValue validValue;
	public gov.nih.nci.cadsr.domain.ValidValue getValidValue()
	{
			
		if(validValue==null ||  validValue.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValidValue as child where child.id in (select parent.validValue.id from gov.nih.nci.cadsr.domain.QuestionConditionComponents as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValidValue");				 
				if (resultList!=null && resultList.size()>0) 
					validValue = (gov.nih.nci.cadsr.domain.ValidValue)resultList.get(0);
				else
					validValue = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(QuestionConditionComponents.class.getName());
				log.error("QuestionConditionComponents:getValidValue throws exception ... ...",ex);
			}
		}
		return validValue;	
					
	}

	public void setValidValue(gov.nih.nci.cadsr.domain.ValidValue validValue)
	{
		this.validValue = validValue;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof QuestionConditionComponents) 
		{
			QuestionConditionComponents c =(QuestionConditionComponents)obj; 			 
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