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
   * Function to be applied to the relationship
   */

public  class Function 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String createdBy;
	public java.lang.String getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(java.lang.String createdBy)
	{
		this.createdBy = createdBy;
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
	
		
	public java.lang.String id;
	public java.lang.String getId()
	{
		return id;
	}
	public void setId(java.lang.String id)
	{
		this.id = id;
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
	
		
	public java.lang.String name;
	public java.lang.String getName()
	{
		return name;
	}
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
		
	public java.lang.String symbol;
	public java.lang.String getSymbol()
	{
		return symbol;
	}
	public void setSymbol(java.lang.String symbol)
	{
		this.symbol = symbol;
	}
	
	
		
	
	
	
		
		
	private java.util.Collection conditionComponent = new java.util.HashSet();
	public java.util.Collection getConditionComponent()
	{
		if (conditionComponent==null || conditionComponent.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.QuestionConditionComponents as child, gov.nih.nci.cadsr.domain.Function as parent  where child in elements(parent.conditionComponent) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.QuestionConditionComponents");				 
				conditionComponent = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Function.class.getName());
				log.error("Function:getConditionComponent throws exception ... ...",ex);
			}
		}	
		return conditionComponent;
	}
	
	public void setConditionComponent(java.util.Collection conditionComponent)
	{
		this.conditionComponent = conditionComponent;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getConceptDerivationRule()
	{
			
		if(conceptDerivationRule==null ||  conceptDerivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.conceptDerivationRule.id from gov.nih.nci.cadsr.domain.Function as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptDerivationRule");				 
				if (resultList!=null && resultList.size()>0) 
					conceptDerivationRule = (gov.nih.nci.cadsr.domain.ConceptDerivationRule)resultList.get(0);
				else
					conceptDerivationRule = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Function.class.getName());
				log.error("Function:getConceptDerivationRule throws exception ... ...",ex);
			}
		}
		return conceptDerivationRule;	
					
	}

	public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule)
	{
		this.conceptDerivationRule = conceptDerivationRule;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Function) 
		{
			Function c =(Function)obj; 			 
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