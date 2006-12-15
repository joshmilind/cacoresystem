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
   * An object representation of a molecular/cellular pathway compiled by BioCarta. Pathways are associated 
   * with specific Taxon objects, and contain multiple Gene objects, which may be Targets for treatment. 
   * 
   */

public  class Pathway 	implements java.io.Serializable 
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
	
		
	public java.lang.String diagram;
	public java.lang.String getDiagram()
	{
		return diagram;
	}
	public void setDiagram(java.lang.String diagram)
	{
		this.diagram = diagram;
	}
	
		
	public java.lang.String displayValue;
	public java.lang.String getDisplayValue()
	{
		return displayValue;
	}
	public void setDisplayValue(java.lang.String displayValue)
	{
		this.displayValue = displayValue;
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
	
	
		
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child, gov.nih.nci.cabio.domain.Pathway as parent  where child in elements(parent.geneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Pathway.class.getName());
				log.error("Pathway:getGeneCollection throws exception ... ...",ex);
			}
		}	
		return geneCollection;
	}
	
	public void setGeneCollection(java.util.Collection geneCollection)
	{
		this.geneCollection = geneCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection histopathologyCollection = new java.util.HashSet();
	public java.util.Collection getHistopathologyCollection()
	{
		if (histopathologyCollection==null || histopathologyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Histopathology as child, gov.nih.nci.cabio.domain.Pathway as parent  where child in elements(parent.histopathologyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Histopathology");				 
				histopathologyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Pathway.class.getName());
				log.error("Pathway:getHistopathologyCollection throws exception ... ...",ex);
			}
		}	
		return histopathologyCollection;
	}
	
	public void setHistopathologyCollection(java.util.Collection histopathologyCollection)
	{
		this.histopathologyCollection = histopathologyCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.Taxon taxon;
	public gov.nih.nci.cabio.domain.Taxon getTaxon()
	{
			
		if(taxon==null ||  taxon.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Taxon as child where child.id in (select parent.taxon.id from gov.nih.nci.cabio.domain.Pathway as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(Pathway.class.getName());
				log.error("Pathway:getTaxon throws exception ... ...",ex);
			}
		}
		return taxon;	
					
	}

	public void setTaxon(gov.nih.nci.cabio.domain.Taxon taxon)
	{
		this.taxon = taxon;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Pathway) 
		{
			Pathway c =(Pathway)obj; 			 
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