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

   */

public  class AdministeredComponentContact 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String contactRole;
	public java.lang.String getContactRole()
	{
		return contactRole;
	}
	public void setContactRole(java.lang.String contactRole)
	{
		this.contactRole = contactRole;
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
	
		
	public java.lang.Integer rank;
	public java.lang.Integer getRank()
	{
		return rank;
	}
	public void setRank(java.lang.Integer rank)
	{
		this.rank = rank;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem classSchemeClassSchemeItem;
	public gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem getClassSchemeClassSchemeItem()
	{
			
		if(classSchemeClassSchemeItem==null ||  classSchemeClassSchemeItem.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as child where child.id in (select parent.classSchemeClassSchemeItem.id from gov.nih.nci.cadsr.domain.AdministeredComponentContact as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(AdministeredComponentContact.class.getName());
				log.error("AdministeredComponentContact:getClassSchemeClassSchemeItem throws exception ... ...",ex);
			}
		}
		return classSchemeClassSchemeItem;	
					
	}

	public void setClassSchemeClassSchemeItem(gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem classSchemeClassSchemeItem)
	{
		this.classSchemeClassSchemeItem = classSchemeClassSchemeItem;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassificationSchemeItem classificationSchemeItem;
	public gov.nih.nci.cadsr.domain.ClassificationSchemeItem getClassificationSchemeItem()
	{
			
		if(classificationSchemeItem==null ||  classificationSchemeItem.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationSchemeItem as child where child.id in (select parent.classificationSchemeItem.id from gov.nih.nci.cadsr.domain.AdministeredComponentContact as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationSchemeItem");				 
				if (resultList!=null && resultList.size()>0) 
					classificationSchemeItem = (gov.nih.nci.cadsr.domain.ClassificationSchemeItem)resultList.get(0);
				else
					classificationSchemeItem = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(AdministeredComponentContact.class.getName());
				log.error("AdministeredComponentContact:getClassificationSchemeItem throws exception ... ...",ex);
			}
		}
		return classificationSchemeItem;	
					
	}

	public void setClassificationSchemeItem(gov.nih.nci.cadsr.domain.ClassificationSchemeItem classificationSchemeItem)
	{
		this.classificationSchemeItem = classificationSchemeItem;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.Person person;
	public gov.nih.nci.cadsr.domain.Person getPerson()
	{
			
		if(person==null ||  person.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Person as child where child.id in (select parent.person.id from gov.nih.nci.cadsr.domain.AdministeredComponentContact as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Person");				 
				if (resultList!=null && resultList.size()>0) 
					person = (gov.nih.nci.cadsr.domain.Person)resultList.get(0);
				else
					person = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(AdministeredComponentContact.class.getName());
				log.error("AdministeredComponentContact:getPerson throws exception ... ...",ex);
			}
		}
		return person;	
					
	}

	public void setPerson(gov.nih.nci.cadsr.domain.Person person)
	{
		this.person = person;
	}
		
	
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.Organization organization;
	public gov.nih.nci.cadsr.domain.Organization getOrganization()
	{
			
		if(organization==null ||  organization.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Organization as child where child.id in (select parent.organization.id from gov.nih.nci.cadsr.domain.AdministeredComponentContact as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Organization");				 
				if (resultList!=null && resultList.size()>0) 
					organization = (gov.nih.nci.cadsr.domain.Organization)resultList.get(0);
				else
					organization = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(AdministeredComponentContact.class.getName());
				log.error("AdministeredComponentContact:getOrganization throws exception ... ...",ex);
			}
		}
		return organization;	
					
	}

	public void setOrganization(gov.nih.nci.cadsr.domain.Organization organization)
	{
		this.organization = organization;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof AdministeredComponentContact) 
		{
			AdministeredComponentContact c =(AdministeredComponentContact)obj; 			 
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