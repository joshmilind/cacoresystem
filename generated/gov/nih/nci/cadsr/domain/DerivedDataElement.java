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
   * Information about a Data Element which is derived, the rule controlling its derivation, and the 
   * Data Element(s) from which it is derived. (ISO 11179) 
   * 
   */

public  class DerivedDataElement 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String concatenationCharacter;
	public java.lang.String getConcatenationCharacter()
	{
		return concatenationCharacter;
	}
	public void setConcatenationCharacter(java.lang.String concatenationCharacter)
	{
		this.concatenationCharacter = concatenationCharacter;
	}
	
		
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
	
		
	public java.lang.String id;
	public java.lang.String getId()
	{
		return id;
	}
	public void setId(java.lang.String id)
	{
		this.id = id;
	}
	
		
	public java.lang.String methods;
	public java.lang.String getMethods()
	{
		return methods;
	}
	public void setMethods(java.lang.String methods)
	{
		this.methods = methods;
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
	
		
	public java.lang.String rule;
	public java.lang.String getRule()
	{
		return rule;
	}
	public void setRule(java.lang.String rule)
	{
		this.rule = rule;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.DataElement dataElement;
	public gov.nih.nci.cadsr.domain.DataElement getDataElement()
	{
			
		if(dataElement==null ||  dataElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElement as child where child.id in (select parent.dataElement.id from gov.nih.nci.cadsr.domain.DerivedDataElement as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElement");				 
				if (resultList!=null && resultList.size()>0) 
					dataElement = (gov.nih.nci.cadsr.domain.DataElement)resultList.get(0);
				else
					dataElement = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DerivedDataElement.class.getName());
				log.error("DerivedDataElement:getDataElement throws exception ... ...",ex);
			}
		}
		return dataElement;	
					
	}

	public void setDataElement(gov.nih.nci.cadsr.domain.DataElement dataElement)
	{
		this.dataElement = dataElement;
	}
		
	
	
	
		
		
	private java.util.Collection dataElementDerivationCollection = new java.util.HashSet();
	public java.util.Collection getDataElementDerivationCollection()
	{
		if (dataElementDerivationCollection==null || dataElementDerivationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementDerivation as child, gov.nih.nci.cadsr.domain.DerivedDataElement as parent  where child in elements(parent.dataElementDerivationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementDerivation");				 
				dataElementDerivationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DerivedDataElement.class.getName());
				log.error("DerivedDataElement:getDataElementDerivationCollection throws exception ... ...",ex);
			}
		}	
		return dataElementDerivationCollection;
	}
	
	public void setDataElementDerivationCollection(java.util.Collection dataElementDerivationCollection)
	{
		this.dataElementDerivationCollection = dataElementDerivationCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.DerivationType derivationType;
	public gov.nih.nci.cadsr.domain.DerivationType getDerivationType()
	{
			
		if(derivationType==null ||  derivationType.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DerivationType as child where child.id in (select parent.derivationType.id from gov.nih.nci.cadsr.domain.DerivedDataElement as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DerivationType");				 
				if (resultList!=null && resultList.size()>0) 
					derivationType = (gov.nih.nci.cadsr.domain.DerivationType)resultList.get(0);
				else
					derivationType = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DerivedDataElement.class.getName());
				log.error("DerivedDataElement:getDerivationType throws exception ... ...",ex);
			}
		}
		return derivationType;	
					
	}

	public void setDerivationType(gov.nih.nci.cadsr.domain.DerivationType derivationType)
	{
		this.derivationType = derivationType;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DerivedDataElement) 
		{
			DerivedDataElement c =(DerivedDataElement)obj; 			 
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