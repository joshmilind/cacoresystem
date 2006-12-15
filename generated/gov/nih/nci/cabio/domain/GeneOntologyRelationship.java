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
   * This object specifies GoOntologyrRelationship.
   */

public  class GeneOntologyRelationship 	implements java.io.Serializable 
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
	
		
	public java.lang.String relationshipType;
	public java.lang.String getRelationshipType()
	{
		return relationshipType;
	}
	public void setRelationshipType(java.lang.String relationshipType)
	{
		this.relationshipType = relationshipType;
	}
	
	
		
		
	private gov.nih.nci.cabio.domain.GeneOntology childGeneOntology;
	public gov.nih.nci.cabio.domain.GeneOntology getChildGeneOntology()
	{
			
		if(childGeneOntology==null ||  childGeneOntology.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.GeneOntology as child where child.id in (select parent.childGeneOntology.id from gov.nih.nci.cabio.domain.GeneOntologyRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.GeneOntology");				 
				if (resultList!=null && resultList.size()>0) 
					childGeneOntology = (gov.nih.nci.cabio.domain.GeneOntology)resultList.get(0);
				else
					childGeneOntology = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(GeneOntologyRelationship.class.getName());
				log.error("GeneOntologyRelationship:getChildGeneOntology throws exception ... ...",ex);
			}
		}
		return childGeneOntology;	
					
	}

	public void setChildGeneOntology(gov.nih.nci.cabio.domain.GeneOntology childGeneOntology)
	{
		this.childGeneOntology = childGeneOntology;
	}
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.GeneOntology parentGeneOntology;
	public gov.nih.nci.cabio.domain.GeneOntology getParentGeneOntology()
	{
			
		if(parentGeneOntology==null ||  parentGeneOntology.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.GeneOntology as child where child.id in (select parent.parentGeneOntology.id from gov.nih.nci.cabio.domain.GeneOntologyRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.GeneOntology");				 
				if (resultList!=null && resultList.size()>0) 
					parentGeneOntology = (gov.nih.nci.cabio.domain.GeneOntology)resultList.get(0);
				else
					parentGeneOntology = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(GeneOntologyRelationship.class.getName());
				log.error("GeneOntologyRelationship:getParentGeneOntology throws exception ... ...",ex);
			}
		}
		return parentGeneOntology;	
					
	}

	public void setParentGeneOntology(gov.nih.nci.cabio.domain.GeneOntology parentGeneOntology)
	{
		this.parentGeneOntology = parentGeneOntology;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof GeneOntologyRelationship) 
		{
			GeneOntologyRelationship c =(GeneOntologyRelationship)obj; 			 
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