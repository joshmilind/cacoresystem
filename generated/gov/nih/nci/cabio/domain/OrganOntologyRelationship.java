package gov.nih.nci.cabio.domain;

import gov.nih.nci.cabio.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Organ relationship object describes relationships among organs.
   */

public  class OrganOntologyRelationship 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String bigid;
	public java.lang.String getBigid()
	{
		return bigid;
	}
	public void setBigid(java.lang.String bigid)
	{
		this.bigid = bigid;
	}
	
		
	public java.lang.Long id;
	public java.lang.Long getId()
	{
		return id;
	}
	public void setId(java.lang.Long id)
	{
		this.id = id;
	}
	
		
	public java.lang.String type;
	public java.lang.String getType()
	{
		return type;
	}
	public void setType(java.lang.String type)
	{
		this.type = type;
	}
	
	
		
		
	private gov.nih.nci.cabio.domain.OrganOntology childOrganOntology;
	public gov.nih.nci.cabio.domain.OrganOntology getChildOrganOntology()
	{
			
		if(childOrganOntology==null ||  childOrganOntology.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.OrganOntology as child where child.id in (select parent.childOrganOntology.id from gov.nih.nci.cabio.domain.OrganOntologyRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.OrganOntology");				 
				if (resultList!=null && resultList.size()>0) 
					childOrganOntology = (gov.nih.nci.cabio.domain.OrganOntology)resultList.get(0);
				else
					childOrganOntology = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(OrganOntologyRelationship.class.getName());
				log.error("OrganOntologyRelationship:getChildOrganOntology throws exception ... ...",ex);
			}
		}
		return childOrganOntology;	
					
	}

	public void setChildOrganOntology(gov.nih.nci.cabio.domain.OrganOntology childOrganOntology)
	{
		this.childOrganOntology = childOrganOntology;
	}
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.OrganOntology parentOrganOntology;
	public gov.nih.nci.cabio.domain.OrganOntology getParentOrganOntology()
	{
			
		if(parentOrganOntology==null ||  parentOrganOntology.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.OrganOntology as child where child.id in (select parent.parentOrganOntology.id from gov.nih.nci.cabio.domain.OrganOntologyRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.OrganOntology");				 
				if (resultList!=null && resultList.size()>0) 
					parentOrganOntology = (gov.nih.nci.cabio.domain.OrganOntology)resultList.get(0);
				else
					parentOrganOntology = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(OrganOntologyRelationship.class.getName());
				log.error("OrganOntologyRelationship:getParentOrganOntology throws exception ... ...",ex);
			}
		}
		return parentOrganOntology;	
					
	}

	public void setParentOrganOntology(gov.nih.nci.cabio.domain.OrganOntology parentOrganOntology)
	{
		this.parentOrganOntology = parentOrganOntology;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof OrganOntologyRelationship) 
		{
			OrganOntologyRelationship c =(OrganOntologyRelationship)obj; 			 
			Long thisId = getId();		
			
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