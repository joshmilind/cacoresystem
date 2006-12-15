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
   * The information about the affiliation between two occurrences of Data Element Concepts. 
   * 
   */

public  class DataElementConceptRelationship 	implements java.io.Serializable 
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
	
		
	public java.lang.String description;
	public java.lang.String getDescription()
	{
		return description;
	}
	public void setDescription(java.lang.String description)
	{
		this.description = description;
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
	
	
		
		
	private gov.nih.nci.cadsr.domain.DataElementConcept parentDataElementConcept;
	public gov.nih.nci.cadsr.domain.DataElementConcept getParentDataElementConcept()
	{
			
		if(parentDataElementConcept==null ||  parentDataElementConcept.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementConcept as child where child.id in (select parent.parentDataElementConcept.id from gov.nih.nci.cadsr.domain.DataElementConceptRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementConcept");				 
				if (resultList!=null && resultList.size()>0) 
					parentDataElementConcept = (gov.nih.nci.cadsr.domain.DataElementConcept)resultList.get(0);
				else
					parentDataElementConcept = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElementConceptRelationship.class.getName());
				log.error("DataElementConceptRelationship:getParentDataElementConcept throws exception ... ...",ex);
			}
		}
		return parentDataElementConcept;	
					
	}

	public void setParentDataElementConcept(gov.nih.nci.cadsr.domain.DataElementConcept parentDataElementConcept)
	{
		this.parentDataElementConcept = parentDataElementConcept;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.DataElementConcept childDataElementConcept;
	public gov.nih.nci.cadsr.domain.DataElementConcept getChildDataElementConcept()
	{
			
		if(childDataElementConcept==null ||  childDataElementConcept.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementConcept as child where child.id in (select parent.childDataElementConcept.id from gov.nih.nci.cadsr.domain.DataElementConceptRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementConcept");				 
				if (resultList!=null && resultList.size()>0) 
					childDataElementConcept = (gov.nih.nci.cadsr.domain.DataElementConcept)resultList.get(0);
				else
					childDataElementConcept = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElementConceptRelationship.class.getName());
				log.error("DataElementConceptRelationship:getChildDataElementConcept throws exception ... ...",ex);
			}
		}
		return childDataElementConcept;	
					
	}

	public void setChildDataElementConcept(gov.nih.nci.cadsr.domain.DataElementConcept childDataElementConcept)
	{
		this.childDataElementConcept = childDataElementConcept;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DataElementConceptRelationship) 
		{
			DataElementConceptRelationship c =(DataElementConceptRelationship)obj; 			 
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