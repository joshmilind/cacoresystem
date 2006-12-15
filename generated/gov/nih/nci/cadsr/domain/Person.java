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
   * Information about a contact person
   */

public  class Person 	implements java.io.Serializable 
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
	
		
	public java.lang.String firstName;
	public java.lang.String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(java.lang.String firstName)
	{
		this.firstName = firstName;
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
	
		
	public java.lang.String lastName;
	public java.lang.String getLastName()
	{
		return lastName;
	}
	public void setLastName(java.lang.String lastName)
	{
		this.lastName = lastName;
	}
	
		
	public java.lang.String middleInitial;
	public java.lang.String getMiddleInitial()
	{
		return middleInitial;
	}
	public void setMiddleInitial(java.lang.String middleInitial)
	{
		this.middleInitial = middleInitial;
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
	
		
	public java.lang.String position;
	public java.lang.String getPosition()
	{
		return position;
	}
	public void setPosition(java.lang.String position)
	{
		this.position = position;
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
	
	
		
		
	private java.util.Collection addressCollection = new java.util.HashSet();
	public java.util.Collection getAddressCollection()
	{
		if (addressCollection==null || addressCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Address as child, gov.nih.nci.cadsr.domain.Person as parent  where child in elements(parent.addressCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Address");				 
				addressCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Person.class.getName());
				log.error("Person:getAddressCollection throws exception ... ...",ex);
			}
		}	
		return addressCollection;
	}
	
	public void setAddressCollection(java.util.Collection addressCollection)
	{
		this.addressCollection = addressCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection administeredComponentContact = new java.util.HashSet();
	public java.util.Collection getAdministeredComponentContact()
	{
		if (administeredComponentContact==null || administeredComponentContact.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.AdministeredComponentContact as child, gov.nih.nci.cadsr.domain.Person as parent  where child in elements(parent.administeredComponentContact) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.AdministeredComponentContact");				 
				administeredComponentContact = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Person.class.getName());
				log.error("Person:getAdministeredComponentContact throws exception ... ...",ex);
			}
		}	
		return administeredComponentContact;
	}
	
	public void setAdministeredComponentContact(java.util.Collection administeredComponentContact)
	{
		this.administeredComponentContact = administeredComponentContact;
	}	
		
	
	
	
		
		
	private java.util.Collection contactCommunication = new java.util.HashSet();
	public java.util.Collection getContactCommunication()
	{
		if (contactCommunication==null || contactCommunication.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ContactCommunication as child, gov.nih.nci.cadsr.domain.Person as parent  where child in elements(parent.contactCommunication) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ContactCommunication");				 
				contactCommunication = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Person.class.getName());
				log.error("Person:getContactCommunication throws exception ... ...",ex);
			}
		}	
		return contactCommunication;
	}
	
	public void setContactCommunication(java.util.Collection contactCommunication)
	{
		this.contactCommunication = contactCommunication;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.Organization organization;
	public gov.nih.nci.cadsr.domain.Organization getOrganization()
	{
			
		if(organization==null ||  organization.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Organization as child where child.id in (select parent.organization.id from gov.nih.nci.cadsr.domain.Person as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(Person.class.getName());
				log.error("Person:getOrganization throws exception ... ...",ex);
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
		if(obj instanceof Person) 
		{
			Person c =(Person)obj; 			 
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