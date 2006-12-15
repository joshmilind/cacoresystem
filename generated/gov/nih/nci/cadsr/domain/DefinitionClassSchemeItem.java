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
   * A class that serves to allow many to many relationships between Definitions and ClassSchemeClassSchemeItem, 
   * providing uniqueness to the CS/CSI pairing to a definition. 
   * 
   */

public  class DefinitionClassSchemeItem 	implements java.io.Serializable 
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
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem classSchemeClassSchemeItem;
	public gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem getClassSchemeClassSchemeItem()
	{
			
		if(classSchemeClassSchemeItem==null ||  classSchemeClassSchemeItem.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as child where child.id in (select parent.classSchemeClassSchemeItem.id from gov.nih.nci.cadsr.domain.DefinitionClassSchemeItem as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(DefinitionClassSchemeItem.class.getName());
				log.error("DefinitionClassSchemeItem:getClassSchemeClassSchemeItem throws exception ... ...",ex);
			}
		}
		return classSchemeClassSchemeItem;	
					
	}

	public void setClassSchemeClassSchemeItem(gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem classSchemeClassSchemeItem)
	{
		this.classSchemeClassSchemeItem = classSchemeClassSchemeItem;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.Definition definition;
	public gov.nih.nci.cadsr.domain.Definition getDefinition()
	{
			
		if(definition==null ||  definition.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Definition as child where child.id in (select parent.definition.id from gov.nih.nci.cadsr.domain.DefinitionClassSchemeItem as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Definition");				 
				if (resultList!=null && resultList.size()>0) 
					definition = (gov.nih.nci.cadsr.domain.Definition)resultList.get(0);
				else
					definition = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DefinitionClassSchemeItem.class.getName());
				log.error("DefinitionClassSchemeItem:getDefinition throws exception ... ...",ex);
			}
		}
		return definition;	
					
	}

	public void setDefinition(gov.nih.nci.cadsr.domain.Definition definition)
	{
		this.definition = definition;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DefinitionClassSchemeItem) 
		{
			DefinitionClassSchemeItem c =(DefinitionClassSchemeItem)obj; 			 
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