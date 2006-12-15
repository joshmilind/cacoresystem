package gov.nih.nci.cadsr.domain;

import gov.nih.nci.cadsr.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A concept that can be represented in the form of a data element, described independently of any particular 
   * representation. (ISO 11179) 
   * 
   */

public  class DataElementConcept  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.Property property;
	public gov.nih.nci.cadsr.domain.Property getProperty()
	{
			
		if(property==null ||  property.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Property as child where child.id in (select parent.property.id from gov.nih.nci.cadsr.domain.DataElementConcept as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Property");				 
				if (resultList!=null && resultList.size()>0) 
					property = (gov.nih.nci.cadsr.domain.Property)resultList.get(0);
				else
					property = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElementConcept.class.getName());
				log.error("DataElementConcept:getProperty throws exception ... ...",ex);
			}
		}
		return property;	
					
	}

	public void setProperty(gov.nih.nci.cadsr.domain.Property property)
	{
		this.property = property;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptualDomain conceptualDomain;
	public gov.nih.nci.cadsr.domain.ConceptualDomain getConceptualDomain()
	{
			
		if(conceptualDomain==null ||  conceptualDomain.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptualDomain as child where child.id in (select parent.conceptualDomain.id from gov.nih.nci.cadsr.domain.DataElementConcept as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptualDomain");				 
				if (resultList!=null && resultList.size()>0) 
					conceptualDomain = (gov.nih.nci.cadsr.domain.ConceptualDomain)resultList.get(0);
				else
					conceptualDomain = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElementConcept.class.getName());
				log.error("DataElementConcept:getConceptualDomain throws exception ... ...",ex);
			}
		}
		return conceptualDomain;	
					
	}

	public void setConceptualDomain(gov.nih.nci.cadsr.domain.ConceptualDomain conceptualDomain)
	{
		this.conceptualDomain = conceptualDomain;
	}
		
	
	
	
		
		
	private java.util.Collection dataElementCollection = new java.util.HashSet();
	public java.util.Collection getDataElementCollection()
	{
		if (dataElementCollection==null || dataElementCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElement as child, gov.nih.nci.cadsr.domain.DataElementConcept as parent  where child in elements(parent.dataElementCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElement");				 
				dataElementCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DataElementConcept.class.getName());
				log.error("DataElementConcept:getDataElementCollection throws exception ... ...",ex);
			}
		}	
		return dataElementCollection;
	}
	
	public void setDataElementCollection(java.util.Collection dataElementCollection)
	{
		this.dataElementCollection = dataElementCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection childDataElementConceptRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getChildDataElementConceptRelationshipCollection()
	{
		if (childDataElementConceptRelationshipCollection==null || childDataElementConceptRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementConceptRelationship as child, gov.nih.nci.cadsr.domain.DataElementConcept as parent  where child in elements(parent.childDataElementConceptRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementConceptRelationship");				 
				childDataElementConceptRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DataElementConcept.class.getName());
				log.error("DataElementConcept:getChildDataElementConceptRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return childDataElementConceptRelationshipCollection;
	}
	
	public void setChildDataElementConceptRelationshipCollection(java.util.Collection childDataElementConceptRelationshipCollection)
	{
		this.childDataElementConceptRelationshipCollection = childDataElementConceptRelationshipCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ObjectClass objectClass;
	public gov.nih.nci.cadsr.domain.ObjectClass getObjectClass()
	{
			
		if(objectClass==null ||  objectClass.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClass as child where child.id in (select parent.objectClass.id from gov.nih.nci.cadsr.domain.DataElementConcept as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClass");				 
				if (resultList!=null && resultList.size()>0) 
					objectClass = (gov.nih.nci.cadsr.domain.ObjectClass)resultList.get(0);
				else
					objectClass = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElementConcept.class.getName());
				log.error("DataElementConcept:getObjectClass throws exception ... ...",ex);
			}
		}
		return objectClass;	
					
	}

	public void setObjectClass(gov.nih.nci.cadsr.domain.ObjectClass objectClass)
	{
		this.objectClass = objectClass;
	}
		
	
	
	
		
		
	private java.util.Collection parentDataElementConceptRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getParentDataElementConceptRelationshipCollection()
	{
		if (parentDataElementConceptRelationshipCollection==null || parentDataElementConceptRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementConceptRelationship as child, gov.nih.nci.cadsr.domain.DataElementConcept as parent  where child in elements(parent.parentDataElementConceptRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementConceptRelationship");				 
				parentDataElementConceptRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DataElementConcept.class.getName());
				log.error("DataElementConcept:getParentDataElementConceptRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return parentDataElementConceptRelationshipCollection;
	}
	
	public void setParentDataElementConceptRelationshipCollection(java.util.Collection parentDataElementConceptRelationshipCollection)
	{
		this.parentDataElementConceptRelationshipCollection = parentDataElementConceptRelationshipCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DataElementConcept) 
		{
			DataElementConcept c =(DataElementConcept)obj; 			 
			String thisId = getId();		
			
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