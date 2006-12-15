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
   * The diseaser relationship specifies the relationship among diseases.
   */

public  class DiseaseOntologyRelationship 	implements java.io.Serializable 
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
	
	
		
		
	private gov.nih.nci.cabio.domain.DiseaseOntology childDiseaseOntology;
	public gov.nih.nci.cabio.domain.DiseaseOntology getChildDiseaseOntology()
	{
			
		if(childDiseaseOntology==null ||  childDiseaseOntology.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.DiseaseOntology as child where child.id in (select parent.childDiseaseOntology.id from gov.nih.nci.cabio.domain.DiseaseOntologyRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.DiseaseOntology");				 
				if (resultList!=null && resultList.size()>0) 
					childDiseaseOntology = (gov.nih.nci.cabio.domain.DiseaseOntology)resultList.get(0);
				else
					childDiseaseOntology = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DiseaseOntologyRelationship.class.getName());
				log.error("DiseaseOntologyRelationship:getChildDiseaseOntology throws exception ... ...",ex);
			}
		}
		return childDiseaseOntology;	
					
	}

	public void setChildDiseaseOntology(gov.nih.nci.cabio.domain.DiseaseOntology childDiseaseOntology)
	{
		this.childDiseaseOntology = childDiseaseOntology;
	}
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.DiseaseOntology parentDiseaseOntology;
	public gov.nih.nci.cabio.domain.DiseaseOntology getParentDiseaseOntology()
	{
			
		if(parentDiseaseOntology==null ||  parentDiseaseOntology.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.DiseaseOntology as child where child.id in (select parent.parentDiseaseOntology.id from gov.nih.nci.cabio.domain.DiseaseOntologyRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.DiseaseOntology");				 
				if (resultList!=null && resultList.size()>0) 
					parentDiseaseOntology = (gov.nih.nci.cabio.domain.DiseaseOntology)resultList.get(0);
				else
					parentDiseaseOntology = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DiseaseOntologyRelationship.class.getName());
				log.error("DiseaseOntologyRelationship:getParentDiseaseOntology throws exception ... ...",ex);
			}
		}
		return parentDiseaseOntology;	
					
	}

	public void setParentDiseaseOntology(gov.nih.nci.cabio.domain.DiseaseOntology parentDiseaseOntology)
	{
		this.parentDiseaseOntology = parentDiseaseOntology;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DiseaseOntologyRelationship) 
		{
			DiseaseOntologyRelationship c =(DiseaseOntologyRelationship)obj; 			 
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