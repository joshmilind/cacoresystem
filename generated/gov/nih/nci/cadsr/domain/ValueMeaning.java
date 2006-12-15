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
   * The information pertaining to the meaning or semantic content of a Value. (ISO 11179) 
   * 
   */

public  class ValueMeaning  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String comments;
	public java.lang.String getComments()
	{
		return comments;
	}
	public void setComments(java.lang.String comments)
	{
		this.comments = comments;
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
	
		
	public java.lang.String shortMeaning;
	public java.lang.String getShortMeaning()
	{
		return shortMeaning;
	}
	public void setShortMeaning(java.lang.String shortMeaning)
	{
		this.shortMeaning = shortMeaning;
	}
	
	
		
		
	private java.util.Collection permissibleValueCollection = new java.util.HashSet();
	public java.util.Collection getPermissibleValueCollection()
	{
		if (permissibleValueCollection==null || permissibleValueCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.PermissibleValue as child, gov.nih.nci.cadsr.domain.ValueMeaning as parent  where child in elements(parent.permissibleValueCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.PermissibleValue");				 
				permissibleValueCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ValueMeaning.class.getName());
				log.error("ValueMeaning:getPermissibleValueCollection throws exception ... ...",ex);
			}
		}	
		return permissibleValueCollection;
	}
	
	public void setPermissibleValueCollection(java.util.Collection permissibleValueCollection)
	{
		this.permissibleValueCollection = permissibleValueCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getConceptDerivationRule()
	{
			
		if(conceptDerivationRule==null ||  conceptDerivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.conceptDerivationRule.id from gov.nih.nci.cadsr.domain.ValueMeaning as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(ValueMeaning.class.getName());
				log.error("ValueMeaning:getConceptDerivationRule throws exception ... ...",ex);
			}
		}
		return conceptDerivationRule;	
					
	}

	public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule)
	{
		this.conceptDerivationRule = conceptDerivationRule;
	}
		
	
	
	
		
		
	private java.util.Collection conceptualDomainCollection = new java.util.HashSet();
	public java.util.Collection getConceptualDomainCollection()
	{
		if (conceptualDomainCollection==null || conceptualDomainCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptualDomain as child, gov.nih.nci.cadsr.domain.ValueMeaning as parent  where child in elements(parent.conceptualDomainCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptualDomain");				 
				conceptualDomainCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ValueMeaning.class.getName());
				log.error("ValueMeaning:getConceptualDomainCollection throws exception ... ...",ex);
			}
		}	
		return conceptualDomainCollection;
	}
	
	public void setConceptualDomainCollection(java.util.Collection conceptualDomainCollection)
	{
		this.conceptualDomainCollection = conceptualDomainCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ValueMeaning) 
		{
			ValueMeaning c =(ValueMeaning)obj; 			 
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