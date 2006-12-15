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
   * This captures the many-to-many relationship between value domain and permissible values and allows 
   * to associate a value domain to a permissible value. 
   * 
   */

public  class ValueDomainPermissibleValue 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.util.Date beginDate;
	public java.util.Date getBeginDate()
	{
		return beginDate;
	}
	public void setBeginDate(java.util.Date beginDate)
	{
		this.beginDate = beginDate;
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
	
		
	public java.util.Date endDate;
	public java.util.Date getEndDate()
	{
		return endDate;
	}
	public void setEndDate(java.util.Date endDate)
	{
		this.endDate = endDate;
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
	
		
	public java.lang.String origin;
	public java.lang.String getOrigin()
	{
		return origin;
	}
	public void setOrigin(java.lang.String origin)
	{
		this.origin = origin;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.Concept concept;
	public gov.nih.nci.cadsr.domain.Concept getConcept()
	{
			
		if(concept==null ||  concept.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Concept as child where child.id in (select parent.concept.id from gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(ValueDomainPermissibleValue.class.getName());
				log.error("ValueDomainPermissibleValue:getConcept throws exception ... ...",ex);
			}
		}
		return concept;	
					
	}

	public void setConcept(gov.nih.nci.cadsr.domain.Concept concept)
	{
		this.concept = concept;
	}
		
	
	
	
		
		
	private java.util.Collection validValueCollection = new java.util.HashSet();
	public java.util.Collection getValidValueCollection()
	{
		if (validValueCollection==null || validValueCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValidValue as child, gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue as parent  where child in elements(parent.validValueCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValidValue");				 
				validValueCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ValueDomainPermissibleValue.class.getName());
				log.error("ValueDomainPermissibleValue:getValidValueCollection throws exception ... ...",ex);
			}
		}	
		return validValueCollection;
	}
	
	public void setValidValueCollection(java.util.Collection validValueCollection)
	{
		this.validValueCollection = validValueCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.EnumeratedValueDomain enumeratedValueDomain;
	public gov.nih.nci.cadsr.domain.EnumeratedValueDomain getEnumeratedValueDomain()
	{
			
		if(enumeratedValueDomain==null ||  enumeratedValueDomain.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.EnumeratedValueDomain as child where child.id in (select parent.enumeratedValueDomain.id from gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.EnumeratedValueDomain");				 
				if (resultList!=null && resultList.size()>0) 
					enumeratedValueDomain = (gov.nih.nci.cadsr.domain.EnumeratedValueDomain)resultList.get(0);
				else
					enumeratedValueDomain = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ValueDomainPermissibleValue.class.getName());
				log.error("ValueDomainPermissibleValue:getEnumeratedValueDomain throws exception ... ...",ex);
			}
		}
		return enumeratedValueDomain;	
					
	}

	public void setEnumeratedValueDomain(gov.nih.nci.cadsr.domain.EnumeratedValueDomain enumeratedValueDomain)
	{
		this.enumeratedValueDomain = enumeratedValueDomain;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.PermissibleValue permissibleValue;
	public gov.nih.nci.cadsr.domain.PermissibleValue getPermissibleValue()
	{
			
		if(permissibleValue==null ||  permissibleValue.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.PermissibleValue as child where child.id in (select parent.permissibleValue.id from gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.PermissibleValue");				 
				if (resultList!=null && resultList.size()>0) 
					permissibleValue = (gov.nih.nci.cadsr.domain.PermissibleValue)resultList.get(0);
				else
					permissibleValue = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ValueDomainPermissibleValue.class.getName());
				log.error("ValueDomainPermissibleValue:getPermissibleValue throws exception ... ...",ex);
			}
		}
		return permissibleValue;	
					
	}

	public void setPermissibleValue(gov.nih.nci.cadsr.domain.PermissibleValue permissibleValue)
	{
		this.permissibleValue = permissibleValue;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ValueDomainPermissibleValue) 
		{
			ValueDomainPermissibleValue c =(ValueDomainPermissibleValue)obj; 			 
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