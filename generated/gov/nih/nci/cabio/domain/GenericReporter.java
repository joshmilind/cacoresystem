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

public  class GenericReporter 	implements java.io.Serializable 
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
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child where child.id in (select parent.gene.id from gov.nih.nci.cabio.domain.GenericReporter as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(GenericReporter.class.getName());
				log.error("GenericReporter:getGene throws exception ... ...",ex);
			}
		}
		return gene;	
					
	}

	public void setGene(gov.nih.nci.cabio.domain.Gene gene)
	{
		this.gene = gene;
	}
		
	
	
	
		
		
	private java.util.Collection genericArrayCollection = new java.util.HashSet();
	public java.util.Collection getGenericArrayCollection()
	{
		if (genericArrayCollection==null || genericArrayCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.GenericArray as child, gov.nih.nci.cabio.domain.GenericReporter as parent  where child in elements(parent.genericArrayCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.GenericArray");				 
				genericArrayCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(GenericReporter.class.getName());
				log.error("GenericReporter:getGenericArrayCollection throws exception ... ...",ex);
			}
		}	
		return genericArrayCollection;
	}
	
	public void setGenericArrayCollection(java.util.Collection genericArrayCollection)
	{
		this.genericArrayCollection = genericArrayCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof GenericReporter) 
		{
			GenericReporter c =(GenericReporter)obj; 			 
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