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
   * The actual text of the data element as specified on a Case Report Form of a Protocol. 
   * 
   */

public  class Question  extends gov.nih.nci.cadsr.domain.FormElement 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String defaultValidValueId;
	public java.lang.String getDefaultValidValueId()
	{
		return defaultValidValueId;
	}
	public void setDefaultValidValueId(java.lang.String defaultValidValueId)
	{
		this.defaultValidValueId = defaultValidValueId;
	}
	
		
	public java.lang.String defaultValue;
	public java.lang.String getDefaultValue()
	{
		return defaultValue;
	}
	public void setDefaultValue(java.lang.String defaultValue)
	{
		this.defaultValue = defaultValue;
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
	
		
	public java.lang.String isEditable;
	public java.lang.String getIsEditable()
	{
		return isEditable;
	}
	public void setIsEditable(java.lang.String isEditable)
	{
		this.isEditable = isEditable;
	}
	
		
	public java.lang.String isMandatory;
	public java.lang.String getIsMandatory()
	{
		return isMandatory;
	}
	public void setIsMandatory(java.lang.String isMandatory)
	{
		this.isMandatory = isMandatory;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.DataElement dataElement;
	public gov.nih.nci.cadsr.domain.DataElement getDataElement()
	{
			
		if(dataElement==null ||  dataElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElement as child where child.id in (select parent.dataElement.id from gov.nih.nci.cadsr.domain.Question as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElement");				 
				if (resultList!=null && resultList.size()>0) 
					dataElement = (gov.nih.nci.cadsr.domain.DataElement)resultList.get(0);
				else
					dataElement = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Question.class.getName());
				log.error("Question:getDataElement throws exception ... ...",ex);
			}
		}
		return dataElement;	
					
	}

	public void setDataElement(gov.nih.nci.cadsr.domain.DataElement dataElement)
	{
		this.dataElement = dataElement;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ValueDomain valueDomain;
	public gov.nih.nci.cadsr.domain.ValueDomain getValueDomain()
	{
			
		if(valueDomain==null ||  valueDomain.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomain as child where child.id in (select parent.valueDomain.id from gov.nih.nci.cadsr.domain.Question as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomain");				 
				if (resultList!=null && resultList.size()>0) 
					valueDomain = (gov.nih.nci.cadsr.domain.ValueDomain)resultList.get(0);
				else
					valueDomain = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Question.class.getName());
				log.error("Question:getValueDomain throws exception ... ...",ex);
			}
		}
		return valueDomain;	
					
	}

	public void setValueDomain(gov.nih.nci.cadsr.domain.ValueDomain valueDomain)
	{
		this.valueDomain = valueDomain;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.QuestionCondition questionCondition;
	public gov.nih.nci.cadsr.domain.QuestionCondition getQuestionCondition()
	{
			
		if(questionCondition==null ||  questionCondition.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionCondition as child where child.id in (select parent.questionCondition.id from gov.nih.nci.cadsr.domain.Question as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(Question.class.getName());
				log.error("Question:getQuestionCondition throws exception ... ...",ex);
			}
		}
		return questionCondition;	
					
	}

	public void setQuestionCondition(gov.nih.nci.cadsr.domain.QuestionCondition questionCondition)
	{
		this.questionCondition = questionCondition;
	}
		
	
	
	
		
		
	private java.util.Collection validValueCollection = new java.util.HashSet();
	public java.util.Collection getValidValueCollection()
	{
		if (validValueCollection==null || validValueCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValidValue as child, gov.nih.nci.cadsr.domain.Question as parent  where child in elements(parent.validValueCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValidValue");				 
				validValueCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Question.class.getName());
				log.error("Question:getValidValueCollection throws exception ... ...",ex);
			}
		}	
		return validValueCollection;
	}
	
	public void setValidValueCollection(java.util.Collection validValueCollection)
	{
		this.validValueCollection = validValueCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection questionComponentCollection = new java.util.HashSet();
	public java.util.Collection getQuestionComponentCollection()
	{
		if (questionComponentCollection==null || questionComponentCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionConditionComponents as child, gov.nih.nci.cadsr.domain.Question as parent  where child in elements(parent.questionComponentCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.QuestionConditionComponents");				 
				questionComponentCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Question.class.getName());
				log.error("Question:getQuestionComponentCollection throws exception ... ...",ex);
			}
		}	
		return questionComponentCollection;
	}
	
	public void setQuestionComponentCollection(java.util.Collection questionComponentCollection)
	{
		this.questionComponentCollection = questionComponentCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.Module module;
	public gov.nih.nci.cadsr.domain.Module getModule()
	{
			
		if(module==null ||  module.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Module as child where child.id in (select parent.module.id from gov.nih.nci.cadsr.domain.Question as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Module");				 
				if (resultList!=null && resultList.size()>0) 
					module = (gov.nih.nci.cadsr.domain.Module)resultList.get(0);
				else
					module = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Question.class.getName());
				log.error("Question:getModule throws exception ... ...",ex);
			}
		}
		return module;	
					
	}

	public void setModule(gov.nih.nci.cadsr.domain.Module module)
	{
		this.module = module;
	}
		
	
	
	
		
		
	private java.util.Collection questionRepetitionCollection = new java.util.HashSet();
	public java.util.Collection getQuestionRepetitionCollection()
	{
		if (questionRepetitionCollection==null || questionRepetitionCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionRepetition as child, gov.nih.nci.cadsr.domain.Question as parent  where child in elements(parent.questionRepetitionCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.QuestionRepetition");				 
				questionRepetitionCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Question.class.getName());
				log.error("Question:getQuestionRepetitionCollection throws exception ... ...",ex);
			}
		}	
		return questionRepetitionCollection;
	}
	
	public void setQuestionRepetitionCollection(java.util.Collection questionRepetitionCollection)
	{
		this.questionRepetitionCollection = questionRepetitionCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Question) 
		{
			Question c =(Question)obj; 			 
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