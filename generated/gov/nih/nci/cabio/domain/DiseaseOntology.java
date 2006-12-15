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
   * Disease objects specify a disease name and ID; disease objects also provide access to: ontological 
   * relations to other diseases; clinical trial protocols treating the disease; and specific histologies 
   * associated with instances of the disease. 
   * 
   */

public  class DiseaseOntology 	implements java.io.Serializable 
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
	
		
	public java.lang.String name;
	public java.lang.String getName()
	{
		return name;
	}
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
	
		
		
	private java.util.Collection clinicalTrialProtocolCollection = new java.util.HashSet();
	public java.util.Collection getClinicalTrialProtocolCollection()
	{
		if (clinicalTrialProtocolCollection==null || clinicalTrialProtocolCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.ClinicalTrialProtocol as child, gov.nih.nci.cabio.domain.DiseaseOntology as parent  where child in elements(parent.clinicalTrialProtocolCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.ClinicalTrialProtocol");				 
				clinicalTrialProtocolCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DiseaseOntology.class.getName());
				log.error("DiseaseOntology:getClinicalTrialProtocolCollection throws exception ... ...",ex);
			}
		}	
		return clinicalTrialProtocolCollection;
	}
	
	public void setClinicalTrialProtocolCollection(java.util.Collection clinicalTrialProtocolCollection)
	{
		this.clinicalTrialProtocolCollection = clinicalTrialProtocolCollection;
	}	
		
	
	
	
		
	
	
	
		
		
	private java.util.Collection histopathologyCollection = new java.util.HashSet();
	public java.util.Collection getHistopathologyCollection()
	{
		if (histopathologyCollection==null || histopathologyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Histopathology as child, gov.nih.nci.cabio.domain.DiseaseOntology as parent  where child in elements(parent.histopathologyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Histopathology");				 
				histopathologyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DiseaseOntology.class.getName());
				log.error("DiseaseOntology:getHistopathologyCollection throws exception ... ...",ex);
			}
		}	
		return histopathologyCollection;
	}
	
	public void setHistopathologyCollection(java.util.Collection histopathologyCollection)
	{
		this.histopathologyCollection = histopathologyCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection childDiseaseOntologyRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getChildDiseaseOntologyRelationshipCollection()
	{
		if (childDiseaseOntologyRelationshipCollection==null || childDiseaseOntologyRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.DiseaseOntologyRelationship as child, gov.nih.nci.cabio.domain.DiseaseOntology as parent  where child in elements(parent.childDiseaseOntologyRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.DiseaseOntologyRelationship");				 
				childDiseaseOntologyRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DiseaseOntology.class.getName());
				log.error("DiseaseOntology:getChildDiseaseOntologyRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return childDiseaseOntologyRelationshipCollection;
	}
	
	public void setChildDiseaseOntologyRelationshipCollection(java.util.Collection childDiseaseOntologyRelationshipCollection)
	{
		this.childDiseaseOntologyRelationshipCollection = childDiseaseOntologyRelationshipCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection parentDiseaseOntologyRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getParentDiseaseOntologyRelationshipCollection()
	{
		if (parentDiseaseOntologyRelationshipCollection==null || parentDiseaseOntologyRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.DiseaseOntologyRelationship as child, gov.nih.nci.cabio.domain.DiseaseOntology as parent  where child in elements(parent.parentDiseaseOntologyRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.DiseaseOntologyRelationship");				 
				parentDiseaseOntologyRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DiseaseOntology.class.getName());
				log.error("DiseaseOntology:getParentDiseaseOntologyRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return parentDiseaseOntologyRelationshipCollection;
	}
	
	public void setParentDiseaseOntologyRelationshipCollection(java.util.Collection parentDiseaseOntologyRelationshipCollection)
	{
		this.parentDiseaseOntologyRelationshipCollection = parentDiseaseOntologyRelationshipCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DiseaseOntology) 
		{
			DiseaseOntology c =(DiseaseOntology)obj; 			 
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