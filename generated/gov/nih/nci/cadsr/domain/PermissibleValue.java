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
   * The exact names, codes and text that can be stored in a data field in an information management system. 
   * ISO DEF: An expression of a value meaning in a specific value domain. 
   * 
   */

public  class PermissibleValue 	implements java.io.Serializable 
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
	
		
	public java.lang.Long highValueNumber;
	public java.lang.Long getHighValueNumber()
	{
		return highValueNumber;
	}
	public void setHighValueNumber(java.lang.Long highValueNumber)
	{
		this.highValueNumber = highValueNumber;
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
	
		
	public java.lang.Long lowValueNumber;
	public java.lang.Long getLowValueNumber()
	{
		return lowValueNumber;
	}
	public void setLowValueNumber(java.lang.Long lowValueNumber)
	{
		this.lowValueNumber = lowValueNumber;
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
	
		
	public java.lang.String value;
	public java.lang.String getValue()
	{
		return value;
	}
	public void setValue(java.lang.String value)
	{
		this.value = value;
	}
	
	
		
		
	private java.util.Collection valueDomainPermissibleValueCollection = new java.util.HashSet();
	public java.util.Collection getValueDomainPermissibleValueCollection()
	{
		if (valueDomainPermissibleValueCollection==null || valueDomainPermissibleValueCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue as child, gov.nih.nci.cadsr.domain.PermissibleValue as parent  where child in elements(parent.valueDomainPermissibleValueCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue");				 
				valueDomainPermissibleValueCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(PermissibleValue.class.getName());
				log.error("PermissibleValue:getValueDomainPermissibleValueCollection throws exception ... ...",ex);
			}
		}	
		return valueDomainPermissibleValueCollection;
	}
	
	public void setValueDomainPermissibleValueCollection(java.util.Collection valueDomainPermissibleValueCollection)
	{
		this.valueDomainPermissibleValueCollection = valueDomainPermissibleValueCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ValueMeaning valueMeaning;
	public gov.nih.nci.cadsr.domain.ValueMeaning getValueMeaning()
	{
			
		if(valueMeaning==null ||  valueMeaning.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueMeaning as child where child.id in (select parent.valueMeaning.id from gov.nih.nci.cadsr.domain.PermissibleValue as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueMeaning");				 
				if (resultList!=null && resultList.size()>0) 
					valueMeaning = (gov.nih.nci.cadsr.domain.ValueMeaning)resultList.get(0);
				else
					valueMeaning = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(PermissibleValue.class.getName());
				log.error("PermissibleValue:getValueMeaning throws exception ... ...",ex);
			}
		}
		return valueMeaning;	
					
	}

	public void setValueMeaning(gov.nih.nci.cadsr.domain.ValueMeaning valueMeaning)
	{
		this.valueMeaning = valueMeaning;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof PermissibleValue) 
		{
			PermissibleValue c =(PermissibleValue)obj; 			 
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