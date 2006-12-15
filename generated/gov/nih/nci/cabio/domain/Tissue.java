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
   * A group of similar cells united to perform a specific function.
   */

public  class Tissue 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String cellLine;
	public java.lang.String getCellLine()
	{
		return cellLine;
	}
	public void setCellLine(java.lang.String cellLine)
	{
		this.cellLine = cellLine;
	}
	
		
	public java.lang.String cellType;
	public java.lang.String getCellType()
	{
		return cellType;
	}
	public void setCellType(java.lang.String cellType)
	{
		this.cellType = cellType;
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
	
		
	public java.lang.String developmentalStage;
	public java.lang.String getDevelopmentalStage()
	{
		return developmentalStage;
	}
	public void setDevelopmentalStage(java.lang.String developmentalStage)
	{
		this.developmentalStage = developmentalStage;
	}
	
		
	public java.lang.String histology;
	public java.lang.String getHistology()
	{
		return histology;
	}
	public void setHistology(java.lang.String histology)
	{
		this.histology = histology;
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
	
		
	public java.lang.String organ;
	public java.lang.String getOrgan()
	{
		return organ;
	}
	public void setOrgan(java.lang.String organ)
	{
		this.organ = organ;
	}
	
		
	public java.lang.String sex;
	public java.lang.String getSex()
	{
		return sex;
	}
	public void setSex(java.lang.String sex)
	{
		this.sex = sex;
	}
	
		
	public java.lang.String supplier;
	public java.lang.String getSupplier()
	{
		return supplier;
	}
	public void setSupplier(java.lang.String supplier)
	{
		this.supplier = supplier;
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
	
	
		
		
	private gov.nih.nci.cabio.domain.Protocol protocol;
	public gov.nih.nci.cabio.domain.Protocol getProtocol()
	{
			
		if(protocol==null ||  protocol.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Protocol as child where child.id in (select parent.protocol.id from gov.nih.nci.cabio.domain.Tissue as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Protocol");				 
				if (resultList!=null && resultList.size()>0) 
					protocol = (gov.nih.nci.cabio.domain.Protocol)resultList.get(0);
				else
					protocol = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Tissue.class.getName());
				log.error("Tissue:getProtocol throws exception ... ...",ex);
			}
		}
		return protocol;	
					
	}

	public void setProtocol(gov.nih.nci.cabio.domain.Protocol protocol)
	{
		this.protocol = protocol;
	}
		
	
	
	
		
		
	private java.util.Collection libraryCollection = new java.util.HashSet();
	public java.util.Collection getLibraryCollection()
	{
		if (libraryCollection==null || libraryCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Library as child, gov.nih.nci.cabio.domain.Tissue as parent  where child in elements(parent.libraryCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Library");				 
				libraryCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Tissue.class.getName());
				log.error("Tissue:getLibraryCollection throws exception ... ...",ex);
			}
		}	
		return libraryCollection;
	}
	
	public void setLibraryCollection(java.util.Collection libraryCollection)
	{
		this.libraryCollection = libraryCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.Taxon taxon;
	public gov.nih.nci.cabio.domain.Taxon getTaxon()
	{
			
		if(taxon==null ||  taxon.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Taxon as child where child.id in (select parent.taxon.id from gov.nih.nci.cabio.domain.Tissue as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(Tissue.class.getName());
				log.error("Tissue:getTaxon throws exception ... ...",ex);
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
		if(obj instanceof Tissue) 
		{
			Tissue c =(Tissue)obj; 			 
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