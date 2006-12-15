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
   * The information pertaining to a concept. A unit of knowledge created by a unique combination of characteristics. 
   * (ISO 1087) 
   * 
   */

public  class Concept  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
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
	
		
	public java.lang.String evsSource;
	public java.lang.String getEvsSource()
	{
		return evsSource;
	}
	public void setEvsSource(java.lang.String evsSource)
	{
		this.evsSource = evsSource;
	}
	
	
		
		
	private java.util.Collection valueDomainPermissibleValueCollection = new java.util.HashSet();
	public java.util.Collection getValueDomainPermissibleValueCollection()
	{
		if (valueDomainPermissibleValueCollection==null || valueDomainPermissibleValueCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue as child, gov.nih.nci.cadsr.domain.Concept as parent  where child in elements(parent.valueDomainPermissibleValueCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue");				 
				valueDomainPermissibleValueCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Concept.class.getName());
				log.error("Concept:getValueDomainPermissibleValueCollection throws exception ... ...",ex);
			}
		}	
		return valueDomainPermissibleValueCollection;
	}
	
	public void setValueDomainPermissibleValueCollection(java.util.Collection valueDomainPermissibleValueCollection)
	{
		this.valueDomainPermissibleValueCollection = valueDomainPermissibleValueCollection;
	}	
		
	
	
	
		
	
	
	
		
		
	private java.util.Collection componentConceptCollection = new java.util.HashSet();
	public java.util.Collection getComponentConceptCollection()
	{
		if (componentConceptCollection==null || componentConceptCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ComponentConcept as child, gov.nih.nci.cadsr.domain.Concept as parent  where child in elements(parent.componentConceptCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ComponentConcept");				 
				componentConceptCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Concept.class.getName());
				log.error("Concept:getComponentConceptCollection throws exception ... ...",ex);
			}
		}	
		return componentConceptCollection;
	}
	
	public void setComponentConceptCollection(java.util.Collection componentConceptCollection)
	{
		this.componentConceptCollection = componentConceptCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Concept) 
		{
			Concept c =(Concept)obj; 			 
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