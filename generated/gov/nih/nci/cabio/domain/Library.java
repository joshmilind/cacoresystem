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
   * An object representing a CGAP library; provides access to information about: the tissue sample 
   * and its method of preparation, the library protocol that was used, the clones contained in the library, 
   * and the sequence information derived from the library. 
   * 
   */

public  class Library 	implements java.io.Serializable 
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
	
		
	public java.lang.String cloneProducer;
	public java.lang.String getCloneProducer()
	{
		return cloneProducer;
	}
	public void setCloneProducer(java.lang.String cloneProducer)
	{
		this.cloneProducer = cloneProducer;
	}
	
		
	public java.lang.Long clonesToDate;
	public java.lang.Long getClonesToDate()
	{
		return clonesToDate;
	}
	public void setClonesToDate(java.lang.Long clonesToDate)
	{
		this.clonesToDate = clonesToDate;
	}
	
		
	public java.lang.String cloneVector;
	public java.lang.String getCloneVector()
	{
		return cloneVector;
	}
	public void setCloneVector(java.lang.String cloneVector)
	{
		this.cloneVector = cloneVector;
	}
	
		
	public java.lang.String cloneVectorType;
	public java.lang.String getCloneVectorType()
	{
		return cloneVectorType;
	}
	public void setCloneVectorType(java.lang.String cloneVectorType)
	{
		this.cloneVectorType = cloneVectorType;
	}
	
		
	public java.util.Date creationDate;
	public java.util.Date getCreationDate()
	{
		return creationDate;
	}
	public void setCreationDate(java.util.Date creationDate)
	{
		this.creationDate = creationDate;
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
	
		
	public java.lang.Long id;
	public java.lang.Long getId()
	{
		return id;
	}
	public void setId(java.lang.Long id)
	{
		this.id = id;
	}
	
		
	public java.lang.String keyword;
	public java.lang.String getKeyword()
	{
		return keyword;
	}
	public void setKeyword(java.lang.String keyword)
	{
		this.keyword = keyword;
	}
	
		
	public java.lang.String labHost;
	public java.lang.String getLabHost()
	{
		return labHost;
	}
	public void setLabHost(java.lang.String labHost)
	{
		this.labHost = labHost;
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
	
		
	public java.lang.String rsite1;
	public java.lang.String getRsite1()
	{
		return rsite1;
	}
	public void setRsite1(java.lang.String rsite1)
	{
		this.rsite1 = rsite1;
	}
	
		
	public java.lang.String rsite2;
	public java.lang.String getRsite2()
	{
		return rsite2;
	}
	public void setRsite2(java.lang.String rsite2)
	{
		this.rsite2 = rsite2;
	}
	
		
	public java.lang.Long sequencesToDate;
	public java.lang.Long getSequencesToDate()
	{
		return sequencesToDate;
	}
	public void setSequencesToDate(java.lang.Long sequencesToDate)
	{
		this.sequencesToDate = sequencesToDate;
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
	
		
	public java.lang.Long uniGeneId;
	public java.lang.Long getUniGeneId()
	{
		return uniGeneId;
	}
	public void setUniGeneId(java.lang.Long uniGeneId)
	{
		this.uniGeneId = uniGeneId;
	}
	
	
		
		
	private java.util.Collection cloneCollection = new java.util.HashSet();
	public java.util.Collection getCloneCollection()
	{
		if (cloneCollection==null || cloneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Clone as child, gov.nih.nci.cabio.domain.Library as parent  where child in elements(parent.cloneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Clone");				 
				cloneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Library.class.getName());
				log.error("Library:getCloneCollection throws exception ... ...",ex);
			}
		}	
		return cloneCollection;
	}
	
	public void setCloneCollection(java.util.Collection cloneCollection)
	{
		this.cloneCollection = cloneCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.Protocol protocol;
	public gov.nih.nci.cabio.domain.Protocol getProtocol()
	{
			
		if(protocol==null ||  protocol.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Protocol as child where child.id in (select parent.protocol.id from gov.nih.nci.cabio.domain.Library as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(Library.class.getName());
				log.error("Library:getProtocol throws exception ... ...",ex);
			}
		}
		return protocol;	
					
	}

	public void setProtocol(gov.nih.nci.cabio.domain.Protocol protocol)
	{
		this.protocol = protocol;
	}
		
	
	
	
		
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child, gov.nih.nci.cabio.domain.Library as parent  where child in elements(parent.geneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Library.class.getName());
				log.error("Library:getGeneCollection throws exception ... ...",ex);
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
				String hql = "select child from gov.nih.nci.cabio.domain.Histopathology as child, gov.nih.nci.cabio.domain.Library as parent  where child in elements(parent.histopathologyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Histopathology");				 
				histopathologyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Library.class.getName());
				log.error("Library:getHistopathologyCollection throws exception ... ...",ex);
			}
		}	
		return histopathologyCollection;
	}
	
	public void setHistopathologyCollection(java.util.Collection histopathologyCollection)
	{
		this.histopathologyCollection = histopathologyCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.Tissue tissue;
	public gov.nih.nci.cabio.domain.Tissue getTissue()
	{
			
		if(tissue==null ||  tissue.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Tissue as child where child.id in (select parent.tissue.id from gov.nih.nci.cabio.domain.Library as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Tissue");				 
				if (resultList!=null && resultList.size()>0) 
					tissue = (gov.nih.nci.cabio.domain.Tissue)resultList.get(0);
				else
					tissue = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Library.class.getName());
				log.error("Library:getTissue throws exception ... ...",ex);
			}
		}
		return tissue;	
					
	}

	public void setTissue(gov.nih.nci.cabio.domain.Tissue tissue)
	{
		this.tissue = tissue;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Library) 
		{
			Library c =(Library)obj; 			 
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