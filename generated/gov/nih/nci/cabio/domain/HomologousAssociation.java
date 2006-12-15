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

public  class HomologousAssociation 	implements java.io.Serializable 
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
	
		
	public java.lang.Long homologousId;
	public java.lang.Long getHomologousId()
	{
		return homologousId;
	}
	public void setHomologousId(java.lang.Long homologousId)
	{
		this.homologousId = homologousId;
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
	
		
	public java.lang.Float similarityPercentage;
	public java.lang.Float getSimilarityPercentage()
	{
		return similarityPercentage;
	}
	public void setSimilarityPercentage(java.lang.Float similarityPercentage)
	{
		this.similarityPercentage = similarityPercentage;
	}
	
	
		
		
	private gov.nih.nci.cabio.domain.Gene homologousGene;
	public gov.nih.nci.cabio.domain.Gene getHomologousGene()
	{
			
		if(homologousGene==null ||  homologousGene.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child where child.id in (select parent.homologousGene.id from gov.nih.nci.cabio.domain.HomologousAssociation as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				if (resultList!=null && resultList.size()>0) 
					homologousGene = (gov.nih.nci.cabio.domain.Gene)resultList.get(0);
				else
					homologousGene = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(HomologousAssociation.class.getName());
				log.error("HomologousAssociation:getHomologousGene throws exception ... ...",ex);
			}
		}
		return homologousGene;	
					
	}

	public void setHomologousGene(gov.nih.nci.cabio.domain.Gene homologousGene)
	{
		this.homologousGene = homologousGene;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof HomologousAssociation) 
		{
			HomologousAssociation c =(HomologousAssociation)obj; 			 
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