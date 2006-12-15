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
   * The affiliation between two occurrences of Value Domains.
   */

public  class ValueDomainRelationship 	implements java.io.Serializable 
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
	
	
		
		
	private gov.nih.nci.cadsr.domain.ValueDomain parentValueDomain;
	public gov.nih.nci.cadsr.domain.ValueDomain getParentValueDomain()
	{
			
		if(parentValueDomain==null ||  parentValueDomain.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomain as child where child.id in (select parent.parentValueDomain.id from gov.nih.nci.cadsr.domain.ValueDomainRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomain");				 
				if (resultList!=null && resultList.size()>0) 
					parentValueDomain = (gov.nih.nci.cadsr.domain.ValueDomain)resultList.get(0);
				else
					parentValueDomain = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ValueDomainRelationship.class.getName());
				log.error("ValueDomainRelationship:getParentValueDomain throws exception ... ...",ex);
			}
		}
		return parentValueDomain;	
					
	}

	public void setParentValueDomain(gov.nih.nci.cadsr.domain.ValueDomain parentValueDomain)
	{
		this.parentValueDomain = parentValueDomain;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ValueDomain childValueDomain;
	public gov.nih.nci.cadsr.domain.ValueDomain getChildValueDomain()
	{
			
		if(childValueDomain==null ||  childValueDomain.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomain as child where child.id in (select parent.childValueDomain.id from gov.nih.nci.cadsr.domain.ValueDomainRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomain");				 
				if (resultList!=null && resultList.size()>0) 
					childValueDomain = (gov.nih.nci.cadsr.domain.ValueDomain)resultList.get(0);
				else
					childValueDomain = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ValueDomainRelationship.class.getName());
				log.error("ValueDomainRelationship:getChildValueDomain throws exception ... ...",ex);
			}
		}
		return childValueDomain;	
					
	}

	public void setChildValueDomain(gov.nih.nci.cadsr.domain.ValueDomain childValueDomain)
	{
		this.childValueDomain = childValueDomain;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ValueDomainRelationship) 
		{
			ValueDomainRelationship c =(ValueDomainRelationship)obj; 			 
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