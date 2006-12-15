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
   * An irregularity in either the expression of a gene or its structure (i.e., a mutation). 
   * 
   */

public  class Anomaly 	implements java.io.Serializable 
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
	
		
	public java.lang.String description;
	public java.lang.String getDescription()
	{
		return description;
	}
	public void setDescription(java.lang.String description)
	{
		this.description = description;
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
	
	
		
		
	private gov.nih.nci.cabio.domain.Histopathology histopathology;
	public gov.nih.nci.cabio.domain.Histopathology getHistopathology()
	{
			
		if(histopathology==null ||  histopathology.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Histopathology as child where child.id in (select parent.histopathology.id from gov.nih.nci.cabio.domain.Anomaly as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Histopathology");				 
				if (resultList!=null && resultList.size()>0) 
					histopathology = (gov.nih.nci.cabio.domain.Histopathology)resultList.get(0);
				else
					histopathology = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Anomaly.class.getName());
				log.error("Anomaly:getHistopathology throws exception ... ...",ex);
			}
		}
		return histopathology;	
					
	}

	public void setHistopathology(gov.nih.nci.cabio.domain.Histopathology histopathology)
	{
		this.histopathology = histopathology;
	}
		
	
	
	
		
	
	
	
		
		
	private java.util.Collection organOntologyCollection = new java.util.HashSet();
	public java.util.Collection getOrganOntologyCollection()
	{
		if (organOntologyCollection==null || organOntologyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.OrganOntology as child, gov.nih.nci.cabio.domain.Anomaly as parent  where child in elements(parent.organOntologyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.OrganOntology");				 
				organOntologyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Anomaly.class.getName());
				log.error("Anomaly:getOrganOntologyCollection throws exception ... ...",ex);
			}
		}	
		return organOntologyCollection;
	}
	
	public void setOrganOntologyCollection(java.util.Collection organOntologyCollection)
	{
		this.organOntologyCollection = organOntologyCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection vocabularyCollection = new java.util.HashSet();
	public java.util.Collection getVocabularyCollection()
	{
		if (vocabularyCollection==null || vocabularyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Vocabulary as child, gov.nih.nci.cabio.domain.Anomaly as parent  where child in elements(parent.vocabularyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Vocabulary");				 
				vocabularyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Anomaly.class.getName());
				log.error("Anomaly:getVocabularyCollection throws exception ... ...",ex);
			}
		}	
		return vocabularyCollection;
	}
	
	public void setVocabularyCollection(java.util.Collection vocabularyCollection)
	{
		this.vocabularyCollection = vocabularyCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Anomaly) 
		{
			Anomaly c =(Anomaly)obj; 			 
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