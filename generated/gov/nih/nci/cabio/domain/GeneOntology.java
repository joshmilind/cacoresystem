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
   * An object providing entry to a Gene object's position in the Gene Ontology Consortium's controlled 
   * vocabularies, as recorded by LocusLink; provides access to gene objects corresponding to the ontological 
   * term, as well as to ancestor and descendant terms i 
   * 
   */

public  class GeneOntology 	implements java.io.Serializable 
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
	
	
		
		
	private java.util.Collection parentGeneOntologyRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getParentGeneOntologyRelationshipCollection()
	{
		if (parentGeneOntologyRelationshipCollection==null || parentGeneOntologyRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.GeneOntologyRelationship as child, gov.nih.nci.cabio.domain.GeneOntology as parent  where child in elements(parent.parentGeneOntologyRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.GeneOntologyRelationship");				 
				parentGeneOntologyRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(GeneOntology.class.getName());
				log.error("GeneOntology:getParentGeneOntologyRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return parentGeneOntologyRelationshipCollection;
	}
	
	public void setParentGeneOntologyRelationshipCollection(java.util.Collection parentGeneOntologyRelationshipCollection)
	{
		this.parentGeneOntologyRelationshipCollection = parentGeneOntologyRelationshipCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Gene as child, gov.nih.nci.cabio.domain.GeneOntology as parent  where child in elements(parent.geneCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(GeneOntology.class.getName());
				log.error("GeneOntology:getGeneCollection throws exception ... ...",ex);
			}
		}	
		return geneCollection;
	}
	
	public void setGeneCollection(java.util.Collection geneCollection)
	{
		this.geneCollection = geneCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection childGeneOntologyRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getChildGeneOntologyRelationshipCollection()
	{
		if (childGeneOntologyRelationshipCollection==null || childGeneOntologyRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.GeneOntologyRelationship as child, gov.nih.nci.cabio.domain.GeneOntology as parent  where child in elements(parent.childGeneOntologyRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.GeneOntologyRelationship");				 
				childGeneOntologyRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(GeneOntology.class.getName());
				log.error("GeneOntology:getChildGeneOntologyRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return childGeneOntologyRelationshipCollection;
	}
	
	public void setChildGeneOntologyRelationshipCollection(java.util.Collection childGeneOntologyRelationshipCollection)
	{
		this.childGeneOntologyRelationshipCollection = childGeneOntologyRelationshipCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof GeneOntology) 
		{
			GeneOntology c =(GeneOntology)obj; 			 
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