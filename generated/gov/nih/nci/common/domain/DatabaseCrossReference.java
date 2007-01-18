package gov.nih.nci.common.domain;

import gov.nih.nci.common.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Provides link to data hosted by other sources.
   */

public  class DatabaseCrossReference 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String crossReferenceId;
	public java.lang.String getCrossReferenceId()
	{
		return crossReferenceId;
	}
	public void setCrossReferenceId(java.lang.String crossReferenceId)
	{
		this.crossReferenceId = crossReferenceId;
	}
	
		
	public java.lang.String dataSourceName;
	public java.lang.String getDataSourceName()
	{
		return dataSourceName;
	}
	public void setDataSourceName(java.lang.String dataSourceName)
	{
		this.dataSourceName = dataSourceName;
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
	
		
	public java.lang.String sourceType;
	public java.lang.String getSourceType()
	{
		return sourceType;
	}
	public void setSourceType(java.lang.String sourceType)
	{
		this.sourceType = sourceType;
	}
	
		
	public java.lang.String summary;
	public java.lang.String getSummary()
	{
		return summary;
	}
	public void setSummary(java.lang.String summary)
	{
		this.summary = summary;
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
	
	
		
		
	private gov.nih.nci.cabio.domain.Gene gene;
	public gov.nih.nci.cabio.domain.Gene getGene()
	{
			
		if(gene==null ||  gene.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child where child.id in (select parent.gene.id from gov.nih.nci.common.domain.DatabaseCrossReference as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				if (resultList!=null && resultList.size()>0) 
					gene = (gov.nih.nci.cabio.domain.Gene)resultList.get(0);
				else
					gene = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DatabaseCrossReference.class.getName());
				log.error("DatabaseCrossReference:getGene throws exception ... ...",ex);
			}
		}
		return gene;	
					
	}

	public void setGene(gov.nih.nci.cabio.domain.Gene gene)
	{
		this.gene = gene;
	}
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.SNP SNP;
	public gov.nih.nci.cabio.domain.SNP getSNP()
	{
			
		if(SNP==null ||  SNP.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.SNP as child where child.id in (select parent.SNP.id from gov.nih.nci.common.domain.DatabaseCrossReference as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.SNP");				 
				if (resultList!=null && resultList.size()>0) 
					SNP = (gov.nih.nci.cabio.domain.SNP)resultList.get(0);
				else
					SNP = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DatabaseCrossReference.class.getName());
				log.error("DatabaseCrossReference:getSNP throws exception ... ...",ex);
			}
		}
		return SNP;	
					
	}

	public void setSNP(gov.nih.nci.cabio.domain.SNP SNP)
	{
		this.SNP = SNP;
	}
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.NucleicAcidSequence nucleicAcidSequence;
	public gov.nih.nci.cabio.domain.NucleicAcidSequence getNucleicAcidSequence()
	{
			
		if(nucleicAcidSequence==null ||  nucleicAcidSequence.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.NucleicAcidSequence as child where child.id in (select parent.nucleicAcidSequence.id from gov.nih.nci.common.domain.DatabaseCrossReference as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.NucleicAcidSequence");				 
				if (resultList!=null && resultList.size()>0) 
					nucleicAcidSequence = (gov.nih.nci.cabio.domain.NucleicAcidSequence)resultList.get(0);
				else
					nucleicAcidSequence = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DatabaseCrossReference.class.getName());
				log.error("DatabaseCrossReference:getNucleicAcidSequence throws exception ... ...",ex);
			}
		}
		return nucleicAcidSequence;	
					
	}

	public void setNucleicAcidSequence(gov.nih.nci.cabio.domain.NucleicAcidSequence nucleicAcidSequence)
	{
		this.nucleicAcidSequence = nucleicAcidSequence;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DatabaseCrossReference) 
		{
			DatabaseCrossReference c =(DatabaseCrossReference)obj; 			 
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