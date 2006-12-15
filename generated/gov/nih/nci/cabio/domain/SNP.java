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
   * An object representing a Single Nucleotide Polymorphism; provides access to the clones and the 
   * trace files from which it was identified, the two most common substitutions at that position, the 
   * offset of the SNP in the parent sequence, and a confidence sco 
   * 
   */

public  class SNP 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String alleleA;
	public java.lang.String getAlleleA()
	{
		return alleleA;
	}
	public void setAlleleA(java.lang.String alleleA)
	{
		this.alleleA = alleleA;
	}
	
		
	public java.lang.String alleleB;
	public java.lang.String getAlleleB()
	{
		return alleleB;
	}
	public void setAlleleB(java.lang.String alleleB)
	{
		this.alleleB = alleleB;
	}
	
		
	public java.lang.String bigid;
	public java.lang.String getBigid()
	{
		return bigid;
	}
	public void setBigid(java.lang.String bigid)
	{
		this.bigid = bigid;
	}
	
		
	public java.lang.String DBSNPID;
	public java.lang.String getDBSNPID()
	{
		return DBSNPID;
	}
	public void setDBSNPID(java.lang.String DBSNPID)
	{
		this.DBSNPID = DBSNPID;
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
	
		
	public java.lang.String validationStatus;
	public java.lang.String getValidationStatus()
	{
		return validationStatus;
	}
	public void setValidationStatus(java.lang.String validationStatus)
	{
		this.validationStatus = validationStatus;
	}
	
	
		
		
	private java.util.Collection databaseCrossReferenceCollection = new java.util.HashSet();
	public java.util.Collection getDatabaseCrossReferenceCollection()
	{
		if (databaseCrossReferenceCollection==null || databaseCrossReferenceCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.common.domain.DatabaseCrossReference as child, gov.nih.nci.cabio.domain.SNP as parent  where child in elements(parent.databaseCrossReferenceCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.common.domain.DatabaseCrossReference");				 
				databaseCrossReferenceCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(SNP.class.getName());
				log.error("SNP:getDatabaseCrossReferenceCollection throws exception ... ...",ex);
			}
		}	
		return databaseCrossReferenceCollection;
	}
	
	public void setDatabaseCrossReferenceCollection(java.util.Collection databaseCrossReferenceCollection)
	{
		this.databaseCrossReferenceCollection = databaseCrossReferenceCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection locationCollection = new java.util.HashSet();
	public java.util.Collection getLocationCollection()
	{
		if (locationCollection==null || locationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Location as child, gov.nih.nci.cabio.domain.SNP as parent  where child in elements(parent.locationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Location");				 
				locationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(SNP.class.getName());
				log.error("SNP:getLocationCollection throws exception ... ...",ex);
			}
		}	
		return locationCollection;
	}
	
	public void setLocationCollection(java.util.Collection locationCollection)
	{
		this.locationCollection = locationCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection populationFrequencyCollection = new java.util.HashSet();
	public java.util.Collection getPopulationFrequencyCollection()
	{
		if (populationFrequencyCollection==null || populationFrequencyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.PopulationFrequency as child, gov.nih.nci.cabio.domain.SNP as parent  where child in elements(parent.populationFrequencyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.PopulationFrequency");				 
				populationFrequencyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(SNP.class.getName());
				log.error("SNP:getPopulationFrequencyCollection throws exception ... ...",ex);
			}
		}	
		return populationFrequencyCollection;
	}
	
	public void setPopulationFrequencyCollection(java.util.Collection populationFrequencyCollection)
	{
		this.populationFrequencyCollection = populationFrequencyCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection geneRelativeLocationCollection = new java.util.HashSet();
	public java.util.Collection getGeneRelativeLocationCollection()
	{
		if (geneRelativeLocationCollection==null || geneRelativeLocationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.GeneRelativeLocation as child, gov.nih.nci.cabio.domain.SNP as parent  where child in elements(parent.geneRelativeLocationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.GeneRelativeLocation");				 
				geneRelativeLocationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(SNP.class.getName());
				log.error("SNP:getGeneRelativeLocationCollection throws exception ... ...",ex);
			}
		}	
		return geneRelativeLocationCollection;
	}
	
	public void setGeneRelativeLocationCollection(java.util.Collection geneRelativeLocationCollection)
	{
		this.geneRelativeLocationCollection = geneRelativeLocationCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof SNP) 
		{
			SNP c =(SNP)obj; 			 
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