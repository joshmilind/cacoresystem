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
   * An association class that serves to allow many to many relationships between Administered Component 
   * and ClassSchemeClassSchemeItem, providing uniqueness to the CS/CSI pairing to an AC. 
   * 
   */

public  class AdministeredComponentClassSchemeItem 	implements java.io.Serializable 
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
	
	
		
		
	private java.util.Collection sourceObjectClass = new java.util.HashSet();
	public java.util.Collection getSourceObjectClass()
	{
		if (sourceObjectClass==null || sourceObjectClass.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClassRelationship as child, gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem as parent  where child in elements(parent.sourceObjectClass) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClassRelationship");				 
				sourceObjectClass = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(AdministeredComponentClassSchemeItem.class.getName());
				log.error("AdministeredComponentClassSchemeItem:getSourceObjectClass throws exception ... ...",ex);
			}
		}	
		return sourceObjectClass;
	}
	
	public void setSourceObjectClass(java.util.Collection sourceObjectClass)
	{
		this.sourceObjectClass = sourceObjectClass;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem classSchemeClassSchemeItem;
	public gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem getClassSchemeClassSchemeItem()
	{
			
		if(classSchemeClassSchemeItem==null ||  classSchemeClassSchemeItem.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as child where child.id in (select parent.classSchemeClassSchemeItem.id from gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem");				 
				if (resultList!=null && resultList.size()>0) 
					classSchemeClassSchemeItem = (gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem)resultList.get(0);
				else
					classSchemeClassSchemeItem = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(AdministeredComponentClassSchemeItem.class.getName());
				log.error("AdministeredComponentClassSchemeItem:getClassSchemeClassSchemeItem throws exception ... ...",ex);
			}
		}
		return classSchemeClassSchemeItem;	
					
	}

	public void setClassSchemeClassSchemeItem(gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem classSchemeClassSchemeItem)
	{
		this.classSchemeClassSchemeItem = classSchemeClassSchemeItem;
	}
		
	
	
	
		
	
	
	
		
		
	private java.util.Collection targetObjectClass = new java.util.HashSet();
	public java.util.Collection getTargetObjectClass()
	{
		if (targetObjectClass==null || targetObjectClass.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClassRelationship as child, gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem as parent  where child in elements(parent.targetObjectClass) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClassRelationship");				 
				targetObjectClass = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(AdministeredComponentClassSchemeItem.class.getName());
				log.error("AdministeredComponentClassSchemeItem:getTargetObjectClass throws exception ... ...",ex);
			}
		}	
		return targetObjectClass;
	}
	
	public void setTargetObjectClass(java.util.Collection targetObjectClass)
	{
		this.targetObjectClass = targetObjectClass;
	}	
		
	
	
	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof AdministeredComponentClassSchemeItem) 
		{
			AdministeredComponentClassSchemeItem c =(AdministeredComponentClassSchemeItem)obj; 			 
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