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

   */

public  class Protein 	implements java.io.Serializable 
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
	
		
	public java.lang.String copyrightStatement;
	public java.lang.String getCopyrightStatement()
	{
		return copyrightStatement;
	}
	public void setCopyrightStatement(java.lang.String copyrightStatement)
	{
		this.copyrightStatement = copyrightStatement;
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
	
		
	public java.util.Collection keywords;
	public java.util.Collection getKeywords()
	{
		return keywords;
	}
	public void setKeywords(java.util.Collection keywords)
	{
		this.keywords = keywords;
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
	
		
	public java.lang.String primaryAccession;
	public java.lang.String getPrimaryAccession()
	{
		return primaryAccession;
	}
	public void setPrimaryAccession(java.lang.String primaryAccession)
	{
		this.primaryAccession = primaryAccession;
	}
	
		
	public java.util.Collection secondaryAccession;
	public java.util.Collection getSecondaryAccession()
	{
		return secondaryAccession;
	}
	public void setSecondaryAccession(java.util.Collection secondaryAccession)
	{
		this.secondaryAccession = secondaryAccession;
	}
	
		
	public java.lang.String uniProtCode;
	public java.lang.String getUniProtCode()
	{
		return uniProtCode;
	}
	public void setUniProtCode(java.lang.String uniProtCode)
	{
		this.uniProtCode = uniProtCode;
	}
	
	
		
		
	private java.util.Collection proteinAliasCollection = new java.util.HashSet();
	public java.util.Collection getProteinAliasCollection()
	{
		if (proteinAliasCollection==null || proteinAliasCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.ProteinAlias as child, gov.nih.nci.cabio.domain.Protein as parent  where child in elements(parent.proteinAliasCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.ProteinAlias");				 
				proteinAliasCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Protein.class.getName());
				log.error("Protein:getProteinAliasCollection throws exception ... ...",ex);
			}
		}	
		return proteinAliasCollection;
	}
	
	public void setProteinAliasCollection(java.util.Collection proteinAliasCollection)
	{
		this.proteinAliasCollection = proteinAliasCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child, gov.nih.nci.cabio.domain.Protein as parent  where child in elements(parent.geneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Protein.class.getName());
				log.error("Protein:getGeneCollection throws exception ... ...",ex);
			}
		}	
		return geneCollection;
	}
	
	public void setGeneCollection(java.util.Collection geneCollection)
	{
		this.geneCollection = geneCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection taxonCollection = new java.util.HashSet();
	public java.util.Collection getTaxonCollection()
	{
		if (taxonCollection==null || taxonCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Taxon as child, gov.nih.nci.cabio.domain.Protein as parent  where child in elements(parent.taxonCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Taxon");				 
				taxonCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Protein.class.getName());
				log.error("Protein:getTaxonCollection throws exception ... ...",ex);
			}
		}	
		return taxonCollection;
	}
	
	public void setTaxonCollection(java.util.Collection taxonCollection)
	{
		this.taxonCollection = taxonCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.ProteinSequence proteinSequence;
	public gov.nih.nci.cabio.domain.ProteinSequence getProteinSequence()
	{
			
		if(proteinSequence==null ||  proteinSequence.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.ProteinSequence as child where child.id in (select parent.proteinSequence.id from gov.nih.nci.cabio.domain.Protein as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.ProteinSequence");				 
				if (resultList!=null && resultList.size()>0) 
					proteinSequence = (gov.nih.nci.cabio.domain.ProteinSequence)resultList.get(0);
				else
					proteinSequence = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Protein.class.getName());
				log.error("Protein:getProteinSequence throws exception ... ...",ex);
			}
		}
		return proteinSequence;	
					
	}

	public void setProteinSequence(gov.nih.nci.cabio.domain.ProteinSequence proteinSequence)
	{
		this.proteinSequence = proteinSequence;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Protein) 
		{
			Protein c =(Protein)obj; 			 
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