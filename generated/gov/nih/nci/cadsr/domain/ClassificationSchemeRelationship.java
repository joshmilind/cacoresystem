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
   * The affiliation between two occurrences of Classification Schemes.
   */

public  class ClassificationSchemeRelationship 	implements java.io.Serializable 
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
	
		
	public java.lang.Integer displayOrder;
	public java.lang.Integer getDisplayOrder()
	{
		return displayOrder;
	}
	public void setDisplayOrder(java.lang.Integer displayOrder)
	{
		this.displayOrder = displayOrder;
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
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassificationScheme childClassificationScheme;
	public gov.nih.nci.cadsr.domain.ClassificationScheme getChildClassificationScheme()
	{
			
		if(childClassificationScheme==null ||  childClassificationScheme.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationScheme as child where child.id in (select parent.childClassificationScheme.id from gov.nih.nci.cadsr.domain.ClassificationSchemeRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationScheme");				 
				if (resultList!=null && resultList.size()>0) 
					childClassificationScheme = (gov.nih.nci.cadsr.domain.ClassificationScheme)resultList.get(0);
				else
					childClassificationScheme = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ClassificationSchemeRelationship.class.getName());
				log.error("ClassificationSchemeRelationship:getChildClassificationScheme throws exception ... ...",ex);
			}
		}
		return childClassificationScheme;	
					
	}

	public void setChildClassificationScheme(gov.nih.nci.cadsr.domain.ClassificationScheme childClassificationScheme)
	{
		this.childClassificationScheme = childClassificationScheme;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassificationScheme parentClassificationScheme;
	public gov.nih.nci.cadsr.domain.ClassificationScheme getParentClassificationScheme()
	{
			
		if(parentClassificationScheme==null ||  parentClassificationScheme.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationScheme as child where child.id in (select parent.parentClassificationScheme.id from gov.nih.nci.cadsr.domain.ClassificationSchemeRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationScheme");				 
				if (resultList!=null && resultList.size()>0) 
					parentClassificationScheme = (gov.nih.nci.cadsr.domain.ClassificationScheme)resultList.get(0);
				else
					parentClassificationScheme = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ClassificationSchemeRelationship.class.getName());
				log.error("ClassificationSchemeRelationship:getParentClassificationScheme throws exception ... ...",ex);
			}
		}
		return parentClassificationScheme;	
					
	}

	public void setParentClassificationScheme(gov.nih.nci.cadsr.domain.ClassificationScheme parentClassificationScheme)
	{
		this.parentClassificationScheme = parentClassificationScheme;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ClassificationSchemeRelationship) 
		{
			ClassificationSchemeRelationship c =(ClassificationSchemeRelationship)obj; 			 
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