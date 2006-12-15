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
   * An object representing the various names (scientific, common, abbreviated, etc.) for a species 
   * associated with a specific instance of a Gene, Chromosome, Pathway, Protein, or Tissue. 
   * 
   */

public  class Taxon 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String abbreviation;
	public java.lang.String getAbbreviation()
	{
		return abbreviation;
	}
	public void setAbbreviation(java.lang.String abbreviation)
	{
		this.abbreviation = abbreviation;
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
	
		
	public java.lang.String commonName;
	public java.lang.String getCommonName()
	{
		return commonName;
	}
	public void setCommonName(java.lang.String commonName)
	{
		this.commonName = commonName;
	}
	
		
	public java.lang.String ethnicityStrain;
	public java.lang.String getEthnicityStrain()
	{
		return ethnicityStrain;
	}
	public void setEthnicityStrain(java.lang.String ethnicityStrain)
	{
		this.ethnicityStrain = ethnicityStrain;
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
	
		
	public java.lang.String scientificName;
	public java.lang.String getScientificName()
	{
		return scientificName;
	}
	public void setScientificName(java.lang.String scientificName)
	{
		this.scientificName = scientificName;
	}
	
	
		
		
	private java.util.Collection cloneCollection = new java.util.HashSet();
	public java.util.Collection getCloneCollection()
	{
		if (cloneCollection==null || cloneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Clone as child, gov.nih.nci.cabio.domain.Taxon as parent  where child in elements(parent.cloneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Clone");				 
				cloneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Taxon.class.getName());
				log.error("Taxon:getCloneCollection throws exception ... ...",ex);
			}
		}	
		return cloneCollection;
	}
	
	public void setCloneCollection(java.util.Collection cloneCollection)
	{
		this.cloneCollection = cloneCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection tissueCollection = new java.util.HashSet();
	public java.util.Collection getTissueCollection()
	{
		if (tissueCollection==null || tissueCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Tissue as child, gov.nih.nci.cabio.domain.Taxon as parent  where child in elements(parent.tissueCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Tissue");				 
				tissueCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Taxon.class.getName());
				log.error("Taxon:getTissueCollection throws exception ... ...",ex);
			}
		}	
		return tissueCollection;
	}
	
	public void setTissueCollection(java.util.Collection tissueCollection)
	{
		this.tissueCollection = tissueCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child, gov.nih.nci.cabio.domain.Taxon as parent  where child in elements(parent.geneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Taxon.class.getName());
				log.error("Taxon:getGeneCollection throws exception ... ...",ex);
			}
		}	
		return geneCollection;
	}
	
	public void setGeneCollection(java.util.Collection geneCollection)
	{
		this.geneCollection = geneCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection pathwayCollection = new java.util.HashSet();
	public java.util.Collection getPathwayCollection()
	{
		if (pathwayCollection==null || pathwayCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Pathway as child, gov.nih.nci.cabio.domain.Taxon as parent  where child in elements(parent.pathwayCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Pathway");				 
				pathwayCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Taxon.class.getName());
				log.error("Taxon:getPathwayCollection throws exception ... ...",ex);
			}
		}	
		return pathwayCollection;
	}
	
	public void setPathwayCollection(java.util.Collection pathwayCollection)
	{
		this.pathwayCollection = pathwayCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection chromosomeCollection = new java.util.HashSet();
	public java.util.Collection getChromosomeCollection()
	{
		if (chromosomeCollection==null || chromosomeCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Chromosome as child, gov.nih.nci.cabio.domain.Taxon as parent  where child in elements(parent.chromosomeCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Chromosome");				 
				chromosomeCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Taxon.class.getName());
				log.error("Taxon:getChromosomeCollection throws exception ... ...",ex);
			}
		}	
		return chromosomeCollection;
	}
	
	public void setChromosomeCollection(java.util.Collection chromosomeCollection)
	{
		this.chromosomeCollection = chromosomeCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection proteinCollection = new java.util.HashSet();
	public java.util.Collection getProteinCollection()
	{
		if (proteinCollection==null || proteinCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Protein as child, gov.nih.nci.cabio.domain.Taxon as parent  where child in elements(parent.proteinCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Protein");				 
				proteinCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Taxon.class.getName());
				log.error("Taxon:getProteinCollection throws exception ... ...",ex);
			}
		}	
		return proteinCollection;
	}
	
	public void setProteinCollection(java.util.Collection proteinCollection)
	{
		this.proteinCollection = proteinCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Taxon) 
		{
			Taxon c =(Taxon)obj; 			 
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