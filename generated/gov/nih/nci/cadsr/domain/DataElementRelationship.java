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
   * The affiliation between two occurrences of Data Elements.
   */

public  class DataElementRelationship 	implements java.io.Serializable 
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
	
	
		
		
	private gov.nih.nci.cadsr.domain.DataElement childDataElement;
	public gov.nih.nci.cadsr.domain.DataElement getChildDataElement()
	{
			
		if(childDataElement==null ||  childDataElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElement as child where child.id in (select parent.childDataElement.id from gov.nih.nci.cadsr.domain.DataElementRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElement");				 
				if (resultList!=null && resultList.size()>0) 
					childDataElement = (gov.nih.nci.cadsr.domain.DataElement)resultList.get(0);
				else
					childDataElement = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElementRelationship.class.getName());
				log.error("DataElementRelationship:getChildDataElement throws exception ... ...",ex);
			}
		}
		return childDataElement;	
					
	}

	public void setChildDataElement(gov.nih.nci.cadsr.domain.DataElement childDataElement)
	{
		this.childDataElement = childDataElement;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.DataElement parentDataElement;
	public gov.nih.nci.cadsr.domain.DataElement getParentDataElement()
	{
			
		if(parentDataElement==null ||  parentDataElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElement as child where child.id in (select parent.parentDataElement.id from gov.nih.nci.cadsr.domain.DataElementRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElement");				 
				if (resultList!=null && resultList.size()>0) 
					parentDataElement = (gov.nih.nci.cadsr.domain.DataElement)resultList.get(0);
				else
					parentDataElement = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElementRelationship.class.getName());
				log.error("DataElementRelationship:getParentDataElement throws exception ... ...",ex);
			}
		}
		return parentDataElement;	
					
	}

	public void setParentDataElement(gov.nih.nci.cadsr.domain.DataElement parentDataElement)
	{
		this.parentDataElement = parentDataElement;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DataElementRelationship) 
		{
			DataElementRelationship c =(DataElementRelationship)obj; 			 
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