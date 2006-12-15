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
   * An object representation of a gene sequence; provides access to the clones from which it was derived, 
   * the ASCII representation of the residues it contains, and the sequence ID. 
   * 
   */

public  class NucleicAcidSequence 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String accessionNumber;
	public java.lang.String getAccessionNumber()
	{
		return accessionNumber;
	}
	public void setAccessionNumber(java.lang.String accessionNumber)
	{
		this.accessionNumber = accessionNumber;
	}
	
		
	public java.lang.String accessionNumberVersion;
	public java.lang.String getAccessionNumberVersion()
	{
		return accessionNumberVersion;
	}
	public void setAccessionNumberVersion(java.lang.String accessionNumberVersion)
	{
		this.accessionNumberVersion = accessionNumberVersion;
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
	
		
	public java.lang.Long id;
	public java.lang.Long getId()
	{
		return id;
	}
	public void setId(java.lang.Long id)
	{
		this.id = id;
	}
	
		
	public java.lang.Long length;
	public java.lang.Long getLength()
	{
		return length;
	}
	public void setLength(java.lang.Long length)
	{
		this.length = length;
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
	
		
	public java.lang.String value;
	public java.lang.String getValue()
	{
		return value;
	}
	public void setValue(java.lang.String value)
	{
		this.value = value;
	}
	
	
		
		
	private java.util.Collection databaseCrossReferenceCollection = new java.util.HashSet();
	public java.util.Collection getDatabaseCrossReferenceCollection()
	{
		if (databaseCrossReferenceCollection==null || databaseCrossReferenceCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.common.domain.DatabaseCrossReference as child, gov.nih.nci.cabio.domain.NucleicAcidSequence as parent  where child in elements(parent.databaseCrossReferenceCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.common.domain.DatabaseCrossReference");				 
				databaseCrossReferenceCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(NucleicAcidSequence.class.getName());
				log.error("NucleicAcidSequence:getDatabaseCrossReferenceCollection throws exception ... ...",ex);
			}
		}	
		return databaseCrossReferenceCollection;
	}
	
	public void setDatabaseCrossReferenceCollection(java.util.Collection databaseCrossReferenceCollection)
	{
		this.databaseCrossReferenceCollection = databaseCrossReferenceCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.CloneRelativeLocation cloneRelativeLocation;
	public gov.nih.nci.cabio.domain.CloneRelativeLocation getCloneRelativeLocation()
	{
			
		if(cloneRelativeLocation==null ||  cloneRelativeLocation.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.CloneRelativeLocation as child where child.id in (select parent.cloneRelativeLocation.id from gov.nih.nci.cabio.domain.NucleicAcidSequence as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.CloneRelativeLocation");				 
				if (resultList!=null && resultList.size()>0) 
					cloneRelativeLocation = (gov.nih.nci.cabio.domain.CloneRelativeLocation)resultList.get(0);
				else
					cloneRelativeLocation = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(NucleicAcidSequence.class.getName());
				log.error("NucleicAcidSequence:getCloneRelativeLocation throws exception ... ...",ex);
			}
		}
		return cloneRelativeLocation;	
					
	}

	public void setCloneRelativeLocation(gov.nih.nci.cabio.domain.CloneRelativeLocation cloneRelativeLocation)
	{
		this.cloneRelativeLocation = cloneRelativeLocation;
	}
		
	
	
	
		
	
	
	
		
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child, gov.nih.nci.cabio.domain.NucleicAcidSequence as parent  where child in elements(parent.geneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(NucleicAcidSequence.class.getName());
				log.error("NucleicAcidSequence:getGeneCollection throws exception ... ...",ex);
			}
		}	
		return geneCollection;
	}
	
	public void setGeneCollection(java.util.Collection geneCollection)
	{
		this.geneCollection = geneCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection locationCollection = new java.util.HashSet();
	public java.util.Collection getLocationCollection()
	{
		if (locationCollection==null || locationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Location as child, gov.nih.nci.cabio.domain.NucleicAcidSequence as parent  where child in elements(parent.locationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Location");				 
				locationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(NucleicAcidSequence.class.getName());
				log.error("NucleicAcidSequence:getLocationCollection throws exception ... ...",ex);
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
		if(obj instanceof NucleicAcidSequence) 
		{
			NucleicAcidSequence c =(NucleicAcidSequence)obj; 			 
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