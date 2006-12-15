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
   * An object used to hold information pertaining to I.M.A.G.E. clones; provides access to sequence 
   * information, associated trace files, and the clone's library. 
   * 
   */

public  class Clone 	implements java.io.Serializable 
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
	
		
	public java.lang.Long insertSize;
	public java.lang.Long getInsertSize()
	{
		return insertSize;
	}
	public void setInsertSize(java.lang.Long insertSize)
	{
		this.insertSize = insertSize;
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
	
	
		
		
	private java.util.Collection cloneRelativeLocationCollection = new java.util.HashSet();
	public java.util.Collection getCloneRelativeLocationCollection()
	{
		if (cloneRelativeLocationCollection==null || cloneRelativeLocationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.CloneRelativeLocation as child, gov.nih.nci.cabio.domain.Clone as parent  where child in elements(parent.cloneRelativeLocationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.CloneRelativeLocation");				 
				cloneRelativeLocationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Clone.class.getName());
				log.error("Clone:getCloneRelativeLocationCollection throws exception ... ...",ex);
			}
		}	
		return cloneRelativeLocationCollection;
	}
	
	public void setCloneRelativeLocationCollection(java.util.Collection cloneRelativeLocationCollection)
	{
		this.cloneRelativeLocationCollection = cloneRelativeLocationCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.Library library;
	public gov.nih.nci.cabio.domain.Library getLibrary()
	{
			
		if(library==null ||  library.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Library as child where child.id in (select parent.library.id from gov.nih.nci.cabio.domain.Clone as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Library");				 
				if (resultList!=null && resultList.size()>0) 
					library = (gov.nih.nci.cabio.domain.Library)resultList.get(0);
				else
					library = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Clone.class.getName());
				log.error("Clone:getLibrary throws exception ... ...",ex);
			}
		}
		return library;	
					
	}

	public void setLibrary(gov.nih.nci.cabio.domain.Library library)
	{
		this.library = library;
	}
		
	
	
	
		
		
	private java.util.Collection nucleicAcidSequenceCollection = new java.util.HashSet();
	public java.util.Collection getNucleicAcidSequenceCollection()
	{
		if (nucleicAcidSequenceCollection==null || nucleicAcidSequenceCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.NucleicAcidSequence as child, gov.nih.nci.cabio.domain.Clone as parent  where child in elements(parent.nucleicAcidSequenceCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.NucleicAcidSequence");				 
				nucleicAcidSequenceCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Clone.class.getName());
				log.error("Clone:getNucleicAcidSequenceCollection throws exception ... ...",ex);
			}
		}	
		return nucleicAcidSequenceCollection;
	}
	
	public void setNucleicAcidSequenceCollection(java.util.Collection nucleicAcidSequenceCollection)
	{
		this.nucleicAcidSequenceCollection = nucleicAcidSequenceCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection taxonCollection = new java.util.HashSet();
	public java.util.Collection getTaxonCollection()
	{
		if (taxonCollection==null || taxonCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Taxon as child, gov.nih.nci.cabio.domain.Clone as parent  where child in elements(parent.taxonCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Taxon");				 
				taxonCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Clone.class.getName());
				log.error("Clone:getTaxonCollection throws exception ... ...",ex);
			}
		}	
		return taxonCollection;
	}
	
	public void setTaxonCollection(java.util.Collection taxonCollection)
	{
		this.taxonCollection = taxonCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Clone) 
		{
			Clone c =(Clone)obj; 			 
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