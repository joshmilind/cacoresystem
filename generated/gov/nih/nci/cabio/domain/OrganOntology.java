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
   * A representation of an organ whose name occurs in a controlled vocabulary; provides access to any 
   * Histopathology objects for the organ, and, because it is "ontolog-able," provides access to its 
   * ancestral and descendant terms in the vocabulary. 
   * 
   */

public  class OrganOntology 	implements java.io.Serializable 
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
	
	
		
		
	private java.util.Collection anomalyCollection = new java.util.HashSet();
	public java.util.Collection getAnomalyCollection()
	{
		if (anomalyCollection==null || anomalyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Anomaly as child, gov.nih.nci.cabio.domain.OrganOntology as parent  where child in elements(parent.anomalyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Anomaly");				 
				anomalyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(OrganOntology.class.getName());
				log.error("OrganOntology:getAnomalyCollection throws exception ... ...",ex);
			}
		}	
		return anomalyCollection;
	}
	
	public void setAnomalyCollection(java.util.Collection anomalyCollection)
	{
		this.anomalyCollection = anomalyCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection childOrganOntologyRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getChildOrganOntologyRelationshipCollection()
	{
		if (childOrganOntologyRelationshipCollection==null || childOrganOntologyRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.OrganOntologyRelationship as child, gov.nih.nci.cabio.domain.OrganOntology as parent  where child in elements(parent.childOrganOntologyRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.OrganOntologyRelationship");				 
				childOrganOntologyRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(OrganOntology.class.getName());
				log.error("OrganOntology:getChildOrganOntologyRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return childOrganOntologyRelationshipCollection;
	}
	
	public void setChildOrganOntologyRelationshipCollection(java.util.Collection childOrganOntologyRelationshipCollection)
	{
		this.childOrganOntologyRelationshipCollection = childOrganOntologyRelationshipCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection parentOrganOntologyRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getParentOrganOntologyRelationshipCollection()
	{
		if (parentOrganOntologyRelationshipCollection==null || parentOrganOntologyRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.OrganOntologyRelationship as child, gov.nih.nci.cabio.domain.OrganOntology as parent  where child in elements(parent.parentOrganOntologyRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.OrganOntologyRelationship");				 
				parentOrganOntologyRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(OrganOntology.class.getName());
				log.error("OrganOntology:getParentOrganOntologyRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return parentOrganOntologyRelationshipCollection;
	}
	
	public void setParentOrganOntologyRelationshipCollection(java.util.Collection parentOrganOntologyRelationshipCollection)
	{
		this.parentOrganOntologyRelationshipCollection = parentOrganOntologyRelationshipCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child, gov.nih.nci.cabio.domain.OrganOntology as parent  where child in elements(parent.geneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(OrganOntology.class.getName());
				log.error("OrganOntology:getGeneCollection throws exception ... ...",ex);
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
				String hql = "select child from gov.nih.nci.cabio.domain.Histopathology as child, gov.nih.nci.cabio.domain.OrganOntology as parent  where child in elements(parent.histopathologyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Histopathology");				 
				histopathologyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(OrganOntology.class.getName());
				log.error("OrganOntology:getHistopathologyCollection throws exception ... ...",ex);
			}
		}	
		return histopathologyCollection;
	}
	
	public void setHistopathologyCollection(java.util.Collection histopathologyCollection)
	{
		this.histopathologyCollection = histopathologyCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof OrganOntology) 
		{
			OrganOntology c =(OrganOntology)obj; 			 
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