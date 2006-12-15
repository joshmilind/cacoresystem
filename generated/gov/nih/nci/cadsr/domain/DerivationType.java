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
   * The type of Derived Data Element that is being created. For example a Data Element that is derived/created 
   * by subtracting two dates represented by other data elements would be a Calculated Representation 
   * Type. Types include: Calculated, Complex Recode, 
   * 
   */

public  class DerivationType 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String createdBy;
	public java.lang.String getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(java.lang.String createdBy)
	{
		this.createdBy = createdBy;
	}
	
		
	public java.util.Date dateCreated;
	public java.util.Date getDateCreated()
	{
		return dateCreated;
	}
	public void setDateCreated(java.util.Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}
	
		
	public java.util.Date dateModified;
	public java.util.Date getDateModified()
	{
		return dateModified;
	}
	public void setDateModified(java.util.Date dateModified)
	{
		this.dateModified = dateModified;
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
	
		
	public java.lang.String id;
	public java.lang.String getId()
	{
		return id;
	}
	public void setId(java.lang.String id)
	{
		this.id = id;
	}
	
		
	public java.lang.String modifiedBy;
	public java.lang.String getModifiedBy()
	{
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
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
	
	
		
		
	private java.util.Collection derivedDataElementCollection = new java.util.HashSet();
	public java.util.Collection getDerivedDataElementCollection()
	{
		if (derivedDataElementCollection==null || derivedDataElementCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DerivedDataElement as child, gov.nih.nci.cadsr.domain.DerivationType as parent  where child in elements(parent.derivedDataElementCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DerivedDataElement");				 
				derivedDataElementCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DerivationType.class.getName());
				log.error("DerivationType:getDerivedDataElementCollection throws exception ... ...",ex);
			}
		}	
		return derivedDataElementCollection;
	}
	
	public void setDerivedDataElementCollection(java.util.Collection derivedDataElementCollection)
	{
		this.derivedDataElementCollection = derivedDataElementCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection conceptDerivationRuleCollection = new java.util.HashSet();
	public java.util.Collection getConceptDerivationRuleCollection()
	{
		if (conceptDerivationRuleCollection==null || conceptDerivationRuleCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child, gov.nih.nci.cadsr.domain.DerivationType as parent  where child in elements(parent.conceptDerivationRuleCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptDerivationRule");				 
				conceptDerivationRuleCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DerivationType.class.getName());
				log.error("DerivationType:getConceptDerivationRuleCollection throws exception ... ...",ex);
			}
		}	
		return conceptDerivationRuleCollection;
	}
	
	public void setConceptDerivationRuleCollection(java.util.Collection conceptDerivationRuleCollection)
	{
		this.conceptDerivationRuleCollection = conceptDerivationRuleCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DerivationType) 
		{
			DerivationType c =(DerivationType)obj; 			 
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