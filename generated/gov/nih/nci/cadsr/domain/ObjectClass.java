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
   * A set of ideas, abstractions, or things in the real world that can be identified with explicit boundaries 
   * and meaning and whose properties and behavior follow the same rules. (ISO 11179) 
   * 
   */

public  class ObjectClass  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String definitionSource;
	public java.lang.String getDefinitionSource()
	{
		return definitionSource;
	}
	public void setDefinitionSource(java.lang.String definitionSource)
	{
		this.definitionSource = definitionSource;
	}
	
	
		
	
	
	
		
		
	private java.util.Collection targetObjectClassRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getTargetObjectClassRelationshipCollection()
	{
		if (targetObjectClassRelationshipCollection==null || targetObjectClassRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClassRelationship as child, gov.nih.nci.cadsr.domain.ObjectClass as parent  where child in elements(parent.targetObjectClassRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClassRelationship");				 
				targetObjectClassRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ObjectClass.class.getName());
				log.error("ObjectClass:getTargetObjectClassRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return targetObjectClassRelationshipCollection;
	}
	
	public void setTargetObjectClassRelationshipCollection(java.util.Collection targetObjectClassRelationshipCollection)
	{
		this.targetObjectClassRelationshipCollection = targetObjectClassRelationshipCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection sourcObjectClassRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getSourcObjectClassRelationshipCollection()
	{
		if (sourcObjectClassRelationshipCollection==null || sourcObjectClassRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClassRelationship as child, gov.nih.nci.cadsr.domain.ObjectClass as parent  where child in elements(parent.sourcObjectClassRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClassRelationship");				 
				sourcObjectClassRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ObjectClass.class.getName());
				log.error("ObjectClass:getSourcObjectClassRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return sourcObjectClassRelationshipCollection;
	}
	
	public void setSourcObjectClassRelationshipCollection(java.util.Collection sourcObjectClassRelationshipCollection)
	{
		this.sourcObjectClassRelationshipCollection = sourcObjectClassRelationshipCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection dataElementConceptCollection = new java.util.HashSet();
	public java.util.Collection getDataElementConceptCollection()
	{
		if (dataElementConceptCollection==null || dataElementConceptCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementConcept as child, gov.nih.nci.cadsr.domain.ObjectClass as parent  where child in elements(parent.dataElementConceptCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementConcept");				 
				dataElementConceptCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ObjectClass.class.getName());
				log.error("ObjectClass:getDataElementConceptCollection throws exception ... ...",ex);
			}
		}	
		return dataElementConceptCollection;
	}
	
	public void setDataElementConceptCollection(java.util.Collection dataElementConceptCollection)
	{
		this.dataElementConceptCollection = dataElementConceptCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getConceptDerivationRule()
	{
			
		if(conceptDerivationRule==null ||  conceptDerivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.conceptDerivationRule.id from gov.nih.nci.cadsr.domain.ObjectClass as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptDerivationRule");				 
				if (resultList!=null && resultList.size()>0) 
					conceptDerivationRule = (gov.nih.nci.cadsr.domain.ConceptDerivationRule)resultList.get(0);
				else
					conceptDerivationRule = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ObjectClass.class.getName());
				log.error("ObjectClass:getConceptDerivationRule throws exception ... ...",ex);
			}
		}
		return conceptDerivationRule;	
					
	}

	public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule)
	{
		this.conceptDerivationRule = conceptDerivationRule;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ObjectClass) 
		{
			ObjectClass c =(ObjectClass)obj; 			 
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