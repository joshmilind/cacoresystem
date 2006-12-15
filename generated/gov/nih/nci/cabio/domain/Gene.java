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
   * Gene objects are the effective portal to most of the genomic information provided by the caBIO data 
   * services; organs, diseases, chromosomes, pathways, sequence data, and expression experiments 
   * are among the many objects accessible via a gene. 
   * 
   */

public  class Gene 	implements java.io.Serializable 
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
	
		
	public java.lang.Long clusterId;
	public java.lang.Long getClusterId()
	{
		return clusterId;
	}
	public void setClusterId(java.lang.Long clusterId)
	{
		this.clusterId = clusterId;
	}
	
		
	public java.lang.String fullName;
	public java.lang.String getFullName()
	{
		return fullName;
	}
	public void setFullName(java.lang.String fullName)
	{
		this.fullName = fullName;
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
	
		
	public java.lang.String symbol;
	public java.lang.String getSymbol()
	{
		return symbol;
	}
	public void setSymbol(java.lang.String symbol)
	{
		this.symbol = symbol;
	}
	
	
		
		
	private gov.nih.nci.cabio.domain.Taxon taxon;
	public gov.nih.nci.cabio.domain.Taxon getTaxon()
	{
			
		if(taxon==null ||  taxon.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Taxon as child where child.id in (select parent.taxon.id from gov.nih.nci.cabio.domain.Gene as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getTaxon throws exception ... ...",ex);
			}
		}
		return taxon;	
					
	}

	public void setTaxon(gov.nih.nci.cabio.domain.Taxon taxon)
	{
		this.taxon = taxon;
	}
		
	
	
	
		
		
	private java.util.Collection pathwayCollection = new java.util.HashSet();
	public java.util.Collection getPathwayCollection()
	{
		if (pathwayCollection==null || pathwayCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Pathway as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.pathwayCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Pathway");				 
				pathwayCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getPathwayCollection throws exception ... ...",ex);
			}
		}	
		return pathwayCollection;
	}
	
	public void setPathwayCollection(java.util.Collection pathwayCollection)
	{
		this.pathwayCollection = pathwayCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection nucleicAcidSequenceCollection = new java.util.HashSet();
	public java.util.Collection getNucleicAcidSequenceCollection()
	{
		if (nucleicAcidSequenceCollection==null || nucleicAcidSequenceCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.NucleicAcidSequence as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.nucleicAcidSequenceCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.NucleicAcidSequence");				 
				nucleicAcidSequenceCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getNucleicAcidSequenceCollection throws exception ... ...",ex);
			}
		}	
		return nucleicAcidSequenceCollection;
	}
	
	public void setNucleicAcidSequenceCollection(java.util.Collection nucleicAcidSequenceCollection)
	{
		this.nucleicAcidSequenceCollection = nucleicAcidSequenceCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection locationCollection = new java.util.HashSet();
	public java.util.Collection getLocationCollection()
	{
		if (locationCollection==null || locationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Location as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.locationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Location");				 
				locationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getLocationCollection throws exception ... ...",ex);
			}
		}	
		return locationCollection;
	}
	
	public void setLocationCollection(java.util.Collection locationCollection)
	{
		this.locationCollection = locationCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection geneRelativeLocationCollection = new java.util.HashSet();
	public java.util.Collection getGeneRelativeLocationCollection()
	{
		if (geneRelativeLocationCollection==null || geneRelativeLocationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.GeneRelativeLocation as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.geneRelativeLocationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.GeneRelativeLocation");				 
				geneRelativeLocationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getGeneRelativeLocationCollection throws exception ... ...",ex);
			}
		}	
		return geneRelativeLocationCollection;
	}
	
	public void setGeneRelativeLocationCollection(java.util.Collection geneRelativeLocationCollection)
	{
		this.geneRelativeLocationCollection = geneRelativeLocationCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection homologousAssociationCollection = new java.util.HashSet();
	public java.util.Collection getHomologousAssociationCollection()
	{
		if (homologousAssociationCollection==null || homologousAssociationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.HomologousAssociation as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.homologousAssociationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.HomologousAssociation");				 
				homologousAssociationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getHomologousAssociationCollection throws exception ... ...",ex);
			}
		}	
		return homologousAssociationCollection;
	}
	
	public void setHomologousAssociationCollection(java.util.Collection homologousAssociationCollection)
	{
		this.homologousAssociationCollection = homologousAssociationCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection genericReporterCollection = new java.util.HashSet();
	public java.util.Collection getGenericReporterCollection()
	{
		if (genericReporterCollection==null || genericReporterCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.GenericReporter as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.genericReporterCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.GenericReporter");				 
				genericReporterCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getGenericReporterCollection throws exception ... ...",ex);
			}
		}	
		return genericReporterCollection;
	}
	
	public void setGenericReporterCollection(java.util.Collection genericReporterCollection)
	{
		this.genericReporterCollection = genericReporterCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection databaseCrossReferenceCollection = new java.util.HashSet();
	public java.util.Collection getDatabaseCrossReferenceCollection()
	{
		if (databaseCrossReferenceCollection==null || databaseCrossReferenceCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.common.domain.DatabaseCrossReference as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.databaseCrossReferenceCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.common.domain.DatabaseCrossReference");				 
				databaseCrossReferenceCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getDatabaseCrossReferenceCollection throws exception ... ...",ex);
			}
		}	
		return databaseCrossReferenceCollection;
	}
	
	public void setDatabaseCrossReferenceCollection(java.util.Collection databaseCrossReferenceCollection)
	{
		this.databaseCrossReferenceCollection = databaseCrossReferenceCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection libraryCollection = new java.util.HashSet();
	public java.util.Collection getLibraryCollection()
	{
		if (libraryCollection==null || libraryCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Library as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.libraryCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Library");				 
				libraryCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getLibraryCollection throws exception ... ...",ex);
			}
		}	
		return libraryCollection;
	}
	
	public void setLibraryCollection(java.util.Collection libraryCollection)
	{
		this.libraryCollection = libraryCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.Chromosome chromosome;
	public gov.nih.nci.cabio.domain.Chromosome getChromosome()
	{
			
		if(chromosome==null ||  chromosome.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Chromosome as child where child.id in (select parent.chromosome.id from gov.nih.nci.cabio.domain.Gene as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Chromosome");				 
				if (resultList!=null && resultList.size()>0) 
					chromosome = (gov.nih.nci.cabio.domain.Chromosome)resultList.get(0);
				else
					chromosome = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getChromosome throws exception ... ...",ex);
			}
		}
		return chromosome;	
					
	}

	public void setChromosome(gov.nih.nci.cabio.domain.Chromosome chromosome)
	{
		this.chromosome = chromosome;
	}
		
	
	
	
		
		
	private java.util.Collection histopathologyCollection = new java.util.HashSet();
	public java.util.Collection getHistopathologyCollection()
	{
		if (histopathologyCollection==null || histopathologyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Histopathology as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.histopathologyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Histopathology");				 
				histopathologyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getHistopathologyCollection throws exception ... ...",ex);
			}
		}	
		return histopathologyCollection;
	}
	
	public void setHistopathologyCollection(java.util.Collection histopathologyCollection)
	{
		this.histopathologyCollection = histopathologyCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection geneAliasCollection = new java.util.HashSet();
	public java.util.Collection getGeneAliasCollection()
	{
		if (geneAliasCollection==null || geneAliasCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.GeneAlias as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.geneAliasCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.GeneAlias");				 
				geneAliasCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getGeneAliasCollection throws exception ... ...",ex);
			}
		}	
		return geneAliasCollection;
	}
	
	public void setGeneAliasCollection(java.util.Collection geneAliasCollection)
	{
		this.geneAliasCollection = geneAliasCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection organOntologyCollection = new java.util.HashSet();
	public java.util.Collection getOrganOntologyCollection()
	{
		if (organOntologyCollection==null || organOntologyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.OrganOntology as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.organOntologyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.OrganOntology");				 
				organOntologyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getOrganOntologyCollection throws exception ... ...",ex);
			}
		}	
		return organOntologyCollection;
	}
	
	public void setOrganOntologyCollection(java.util.Collection organOntologyCollection)
	{
		this.organOntologyCollection = organOntologyCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection proteinCollection = new java.util.HashSet();
	public java.util.Collection getProteinCollection()
	{
		if (proteinCollection==null || proteinCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Protein as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.proteinCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Protein");				 
				proteinCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getProteinCollection throws exception ... ...",ex);
			}
		}	
		return proteinCollection;
	}
	
	public void setProteinCollection(java.util.Collection proteinCollection)
	{
		this.proteinCollection = proteinCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection geneOntologyCollection = new java.util.HashSet();
	public java.util.Collection getGeneOntologyCollection()
	{
		if (geneOntologyCollection==null || geneOntologyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.GeneOntology as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.geneOntologyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.GeneOntology");				 
				geneOntologyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getGeneOntologyCollection throws exception ... ...",ex);
			}
		}	
		return geneOntologyCollection;
	}
	
	public void setGeneOntologyCollection(java.util.Collection geneOntologyCollection)
	{
		this.geneOntologyCollection = geneOntologyCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection targetCollection = new java.util.HashSet();
	public java.util.Collection getTargetCollection()
	{
		if (targetCollection==null || targetCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Target as child, gov.nih.nci.cabio.domain.Gene as parent  where child in elements(parent.targetCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Target");				 
				targetCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getTargetCollection throws exception ... ...",ex);
			}
		}	
		return targetCollection;
	}
	
	public void setTargetCollection(java.util.Collection targetCollection)
	{
		this.targetCollection = targetCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Gene) 
		{
			Gene c =(Gene)obj; 			 
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