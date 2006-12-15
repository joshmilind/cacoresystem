package gov.nih.nci.cadsr.umlproject.domain;

import gov.nih.nci.cadsr.umlproject.domain.*;
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

public  class SemanticMetadata 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String conceptCode;
	public java.lang.String getConceptCode()
	{
		return conceptCode;
	}
	public void setConceptCode(java.lang.String conceptCode)
	{
		this.conceptCode = conceptCode;
	}
	
		
	public java.lang.String conceptDefinition;
	public java.lang.String getConceptDefinition()
	{
		return conceptDefinition;
	}
	public void setConceptDefinition(java.lang.String conceptDefinition)
	{
		this.conceptDefinition = conceptDefinition;
	}
	
		
	public java.lang.String conceptName;
	public java.lang.String getConceptName()
	{
		return conceptName;
	}
	public void setConceptName(java.lang.String conceptName)
	{
		this.conceptName = conceptName;
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
	
		
	public java.lang.Boolean isPrimaryConcept;
	public java.lang.Boolean getIsPrimaryConcept()
	{
		return isPrimaryConcept;
	}
	public void setIsPrimaryConcept(java.lang.Boolean isPrimaryConcept)
	{
		this.isPrimaryConcept = isPrimaryConcept;
	}
	
		
	public java.lang.Integer order;
	public java.lang.Integer getOrder()
	{
		return order;
	}
	public void setOrder(java.lang.Integer order)
	{
		this.order = order;
	}
	
		
	public java.lang.Integer orderLevel;
	public java.lang.Integer getOrderLevel()
	{
		return orderLevel;
	}
	public void setOrderLevel(java.lang.Integer orderLevel)
	{
		this.orderLevel = orderLevel;
	}
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.Concept concept;
	public gov.nih.nci.cadsr.domain.Concept getConcept()
	{
			
		if(concept==null ||  concept.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Concept as child where child.id in (select parent.concept.id from gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Concept");				 
				if (resultList!=null && resultList.size()>0) 
					concept = (gov.nih.nci.cadsr.domain.Concept)resultList.get(0);
				else
					concept = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(SemanticMetadata.class.getName());
				log.error("SemanticMetadata:getConcept throws exception ... ...",ex);
			}
		}
		return concept;	
					
	}

	public void setConcept(gov.nih.nci.cadsr.domain.Concept concept)
	{
		this.concept = concept;
	}
		
	
	
	
		
	
	
	
		
	
	
	
		
	
	
	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof SemanticMetadata) 
		{
			SemanticMetadata c =(SemanticMetadata)obj; 			 
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