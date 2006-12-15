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
   * The relationship among items in a Classification Scheme. (ISO 11179)
   */

public  class ClassificationSchemeItemRelationship 	implements java.io.Serializable 
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
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassificationSchemeItem parentClassificationSchemeItem;
	public gov.nih.nci.cadsr.domain.ClassificationSchemeItem getParentClassificationSchemeItem()
	{
			
		if(parentClassificationSchemeItem==null ||  parentClassificationSchemeItem.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationSchemeItem as child where child.id in (select parent.parentClassificationSchemeItem.id from gov.nih.nci.cadsr.domain.ClassificationSchemeItemRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationSchemeItem");				 
				if (resultList!=null && resultList.size()>0) 
					parentClassificationSchemeItem = (gov.nih.nci.cadsr.domain.ClassificationSchemeItem)resultList.get(0);
				else
					parentClassificationSchemeItem = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ClassificationSchemeItemRelationship.class.getName());
				log.error("ClassificationSchemeItemRelationship:getParentClassificationSchemeItem throws exception ... ...",ex);
			}
		}
		return parentClassificationSchemeItem;	
					
	}

	public void setParentClassificationSchemeItem(gov.nih.nci.cadsr.domain.ClassificationSchemeItem parentClassificationSchemeItem)
	{
		this.parentClassificationSchemeItem = parentClassificationSchemeItem;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassificationSchemeItem childClassificationSchemeItem;
	public gov.nih.nci.cadsr.domain.ClassificationSchemeItem getChildClassificationSchemeItem()
	{
			
		if(childClassificationSchemeItem==null ||  childClassificationSchemeItem.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationSchemeItem as child where child.id in (select parent.childClassificationSchemeItem.id from gov.nih.nci.cadsr.domain.ClassificationSchemeItemRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationSchemeItem");				 
				if (resultList!=null && resultList.size()>0) 
					childClassificationSchemeItem = (gov.nih.nci.cadsr.domain.ClassificationSchemeItem)resultList.get(0);
				else
					childClassificationSchemeItem = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ClassificationSchemeItemRelationship.class.getName());
				log.error("ClassificationSchemeItemRelationship:getChildClassificationSchemeItem throws exception ... ...",ex);
			}
		}
		return childClassificationSchemeItem;	
					
	}

	public void setChildClassificationSchemeItem(gov.nih.nci.cadsr.domain.ClassificationSchemeItem childClassificationSchemeItem)
	{
		this.childClassificationSchemeItem = childClassificationSchemeItem;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ClassificationSchemeItemRelationship) 
		{
			ClassificationSchemeItemRelationship c =(ClassificationSchemeItemRelationship)obj; 			 
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