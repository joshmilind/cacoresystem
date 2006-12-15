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
   * The data element component(s) used for a derived data element.
   */

public  class DataElementDerivation 	implements java.io.Serializable 
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
	
		
	public java.lang.Integer displayOrder;
	public java.lang.Integer getDisplayOrder()
	{
		return displayOrder;
	}
	public void setDisplayOrder(java.lang.Integer displayOrder)
	{
		this.displayOrder = displayOrder;
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
	
		
	public java.lang.String leadingCharacters;
	public java.lang.String getLeadingCharacters()
	{
		return leadingCharacters;
	}
	public void setLeadingCharacters(java.lang.String leadingCharacters)
	{
		this.leadingCharacters = leadingCharacters;
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
	
		
	public java.lang.String trailingCharacters;
	public java.lang.String getTrailingCharacters()
	{
		return trailingCharacters;
	}
	public void setTrailingCharacters(java.lang.String trailingCharacters)
	{
		this.trailingCharacters = trailingCharacters;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.DataElement dataElement;
	public gov.nih.nci.cadsr.domain.DataElement getDataElement()
	{
			
		if(dataElement==null ||  dataElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElement as child where child.id in (select parent.dataElement.id from gov.nih.nci.cadsr.domain.DataElementDerivation as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(DataElementDerivation.class.getName());
				log.error("DataElementDerivation:getDataElement throws exception ... ...",ex);
			}
		}
		return dataElement;	
					
	}

	public void setDataElement(gov.nih.nci.cadsr.domain.DataElement dataElement)
	{
		this.dataElement = dataElement;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.Function leftOperand;
	public gov.nih.nci.cadsr.domain.Function getLeftOperand()
	{
			
		if(leftOperand==null ||  leftOperand.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Function as child where child.id in (select parent.leftOperand.id from gov.nih.nci.cadsr.domain.DataElementDerivation as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Function");				 
				if (resultList!=null && resultList.size()>0) 
					leftOperand = (gov.nih.nci.cadsr.domain.Function)resultList.get(0);
				else
					leftOperand = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElementDerivation.class.getName());
				log.error("DataElementDerivation:getLeftOperand throws exception ... ...",ex);
			}
		}
		return leftOperand;	
					
	}

	public void setLeftOperand(gov.nih.nci.cadsr.domain.Function leftOperand)
	{
		this.leftOperand = leftOperand;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.DerivedDataElement derivedDataElement;
	public gov.nih.nci.cadsr.domain.DerivedDataElement getDerivedDataElement()
	{
			
		if(derivedDataElement==null ||  derivedDataElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DerivedDataElement as child where child.id in (select parent.derivedDataElement.id from gov.nih.nci.cadsr.domain.DataElementDerivation as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DerivedDataElement");				 
				if (resultList!=null && resultList.size()>0) 
					derivedDataElement = (gov.nih.nci.cadsr.domain.DerivedDataElement)resultList.get(0);
				else
					derivedDataElement = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElementDerivation.class.getName());
				log.error("DataElementDerivation:getDerivedDataElement throws exception ... ...",ex);
			}
		}
		return derivedDataElement;	
					
	}

	public void setDerivedDataElement(gov.nih.nci.cadsr.domain.DerivedDataElement derivedDataElement)
	{
		this.derivedDataElement = derivedDataElement;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DataElementDerivation) 
		{
			DataElementDerivation c =(DataElementDerivation)obj; 			 
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