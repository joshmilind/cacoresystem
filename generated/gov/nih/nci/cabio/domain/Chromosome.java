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
   * An object representing a specific chromosome for a specific taxon; provides access to all known 
   * genes contained in the chromosome and to the taxon. 
   * 
   */

public  class Chromosome 	implements java.io.Serializable 
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
	
		
	public java.lang.String number;
	public java.lang.String getNumber()
	{
		return number;
	}
	public void setNumber(java.lang.String number)
	{
		this.number = number;
	}
	
	
		
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child, gov.nih.nci.cabio.domain.Chromosome as parent  where child in elements(parent.geneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Chromosome.class.getName());
				log.error("Chromosome:getGeneCollection throws exception ... ...",ex);
			}
		}	
		return geneCollection;
	}
	
	public void setGeneCollection(java.util.Collection geneCollection)
	{
		this.geneCollection = geneCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.Taxon taxon;
	public gov.nih.nci.cabio.domain.Taxon getTaxon()
	{
			
		if(taxon==null ||  taxon.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Taxon as child where child.id in (select parent.taxon.id from gov.nih.nci.cabio.domain.Chromosome as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Taxon");				 
				if (resultList!=null && resultList.size()>0) 
					taxon = (gov.nih.nci.cabio.domain.Taxon)resultList.get(0);
				else
					taxon = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Chromosome.class.getName());
				log.error("Chromosome:getTaxon throws exception ... ...",ex);
			}
		}
		return taxon;	
					
	}

	public void setTaxon(gov.nih.nci.cabio.domain.Taxon taxon)
	{
		this.taxon = taxon;
	}
		
	
	
	
		
		
	private java.util.Collection locationCollection = new java.util.HashSet();
	public java.util.Collection getLocationCollection()
	{
		if (locationCollection==null || locationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Location as child, gov.nih.nci.cabio.domain.Chromosome as parent  where child in elements(parent.locationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Location");				 
				locationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Chromosome.class.getName());
				log.error("Chromosome:getLocationCollection throws exception ... ...",ex);
			}
		}	
		return locationCollection;
	}
	
	public void setLocationCollection(java.util.Collection locationCollection)
	{
		this.locationCollection = locationCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Chromosome) 
		{
			Chromosome c =(Chromosome)obj; 			 
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