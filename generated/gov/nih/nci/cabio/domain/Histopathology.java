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
   * An object representing anatomical changes in a diseased tissue sample associated with an expression 
   * experiment; captures the relationship between organ and disease. 
   * 
   */

public  class Histopathology 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String ageOfOnset;
	public java.lang.String getAgeOfOnset()
	{
		return ageOfOnset;
	}
	public void setAgeOfOnset(java.lang.String ageOfOnset)
	{
		this.ageOfOnset = ageOfOnset;
	}
	
		
	public java.lang.String comments;
	public java.lang.String getComments()
	{
		return comments;
	}
	public void setComments(java.lang.String comments)
	{
		this.comments = comments;
	}
	
		
	public java.lang.String grossDescription;
	public java.lang.String getGrossDescription()
	{
		return grossDescription;
	}
	public void setGrossDescription(java.lang.String grossDescription)
	{
		this.grossDescription = grossDescription;
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
	
		
	public java.lang.String microscopicDescription;
	public java.lang.String getMicroscopicDescription()
	{
		return microscopicDescription;
	}
	public void setMicroscopicDescription(java.lang.String microscopicDescription)
	{
		this.microscopicDescription = microscopicDescription;
	}
	
		
	public java.lang.String relationalOperation;
	public java.lang.String getRelationalOperation()
	{
		return relationalOperation;
	}
	public void setRelationalOperation(java.lang.String relationalOperation)
	{
		this.relationalOperation = relationalOperation;
	}
	
		
	public java.lang.String survivalInfo;
	public java.lang.String getSurvivalInfo()
	{
		return survivalInfo;
	}
	public void setSurvivalInfo(java.lang.String survivalInfo)
	{
		this.survivalInfo = survivalInfo;
	}
	
		
	public java.lang.Float tumorIncidenceRate;
	public java.lang.Float getTumorIncidenceRate()
	{
		return tumorIncidenceRate;
	}
	public void setTumorIncidenceRate(java.lang.Float tumorIncidenceRate)
	{
		this.tumorIncidenceRate = tumorIncidenceRate;
	}
	
	
		
	
	
	
		
		
	private java.util.Collection anomalyCollection = new java.util.HashSet();
	public java.util.Collection getAnomalyCollection()
	{
		if (anomalyCollection==null || anomalyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Anomaly as child, gov.nih.nci.cabio.domain.Histopathology as parent  where child in elements(parent.anomalyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Anomaly");				 
				anomalyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Histopathology.class.getName());
				log.error("Histopathology:getAnomalyCollection throws exception ... ...",ex);
			}
		}	
		return anomalyCollection;
	}
	
	public void setAnomalyCollection(java.util.Collection anomalyCollection)
	{
		this.anomalyCollection = anomalyCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.DiseaseOntology diseaseOntology;
	public gov.nih.nci.cabio.domain.DiseaseOntology getDiseaseOntology()
	{
			
		if(diseaseOntology==null ||  diseaseOntology.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.DiseaseOntology as child where child.id in (select parent.diseaseOntology.id from gov.nih.nci.cabio.domain.Histopathology as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.DiseaseOntology");				 
				if (resultList!=null && resultList.size()>0) 
					diseaseOntology = (gov.nih.nci.cabio.domain.DiseaseOntology)resultList.get(0);
				else
					diseaseOntology = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Histopathology.class.getName());
				log.error("Histopathology:getDiseaseOntology throws exception ... ...",ex);
			}
		}
		return diseaseOntology;	
					
	}

	public void setDiseaseOntology(gov.nih.nci.cabio.domain.DiseaseOntology diseaseOntology)
	{
		this.diseaseOntology = diseaseOntology;
	}
		
	
	
	
		
		
	private java.util.Collection libraryCollection = new java.util.HashSet();
	public java.util.Collection getLibraryCollection()
	{
		if (libraryCollection==null || libraryCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Library as child, gov.nih.nci.cabio.domain.Histopathology as parent  where child in elements(parent.libraryCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Library");				 
				libraryCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Histopathology.class.getName());
				log.error("Histopathology:getLibraryCollection throws exception ... ...",ex);
			}
		}	
		return libraryCollection;
	}
	
	public void setLibraryCollection(java.util.Collection libraryCollection)
	{
		this.libraryCollection = libraryCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection clinicalTrialProtocolCollection = new java.util.HashSet();
	public java.util.Collection getClinicalTrialProtocolCollection()
	{
		if (clinicalTrialProtocolCollection==null || clinicalTrialProtocolCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.ClinicalTrialProtocol as child, gov.nih.nci.cabio.domain.Histopathology as parent  where child in elements(parent.clinicalTrialProtocolCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.ClinicalTrialProtocol");				 
				clinicalTrialProtocolCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Histopathology.class.getName());
				log.error("Histopathology:getClinicalTrialProtocolCollection throws exception ... ...",ex);
			}
		}	
		return clinicalTrialProtocolCollection;
	}
	
	public void setClinicalTrialProtocolCollection(java.util.Collection clinicalTrialProtocolCollection)
	{
		this.clinicalTrialProtocolCollection = clinicalTrialProtocolCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child, gov.nih.nci.cabio.domain.Histopathology as parent  where child in elements(parent.geneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Histopathology.class.getName());
				log.error("Histopathology:getGeneCollection throws exception ... ...",ex);
			}
		}	
		return geneCollection;
	}
	
	public void setGeneCollection(java.util.Collection geneCollection)
	{
		this.geneCollection = geneCollection;
	}	
		
	
	
	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.OrganOntology organOntology;
	public gov.nih.nci.cabio.domain.OrganOntology getOrganOntology()
	{
			
		if(organOntology==null ||  organOntology.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.OrganOntology as child where child.id in (select parent.organOntology.id from gov.nih.nci.cabio.domain.Histopathology as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.OrganOntology");				 
				if (resultList!=null && resultList.size()>0) 
					organOntology = (gov.nih.nci.cabio.domain.OrganOntology)resultList.get(0);
				else
					organOntology = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Histopathology.class.getName());
				log.error("Histopathology:getOrganOntology throws exception ... ...",ex);
			}
		}
		return organOntology;	
					
	}

	public void setOrganOntology(gov.nih.nci.cabio.domain.OrganOntology organOntology)
	{
		this.organOntology = organOntology;
	}
		
	
	
	
		
		
	private java.util.Collection metastasisCollection = new java.util.HashSet();
	public java.util.Collection getMetastasisCollection()
	{
		if (metastasisCollection==null || metastasisCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Histopathology as child, gov.nih.nci.cabio.domain.Histopathology as parent  where child in elements(parent.metastasisCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Histopathology");				 
				metastasisCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Histopathology.class.getName());
				log.error("Histopathology:getMetastasisCollection throws exception ... ...",ex);
			}
		}	
		return metastasisCollection;
	}
	
	public void setMetastasisCollection(java.util.Collection metastasisCollection)
	{
		this.metastasisCollection = metastasisCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Histopathology) 
		{
			Histopathology c =(Histopathology)obj; 			 
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