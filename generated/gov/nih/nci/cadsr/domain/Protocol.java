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
   * Identification of a Clinical Trial Protocol document and its collection of Case Report Forms (CRFs). 
   * Note: Protocols will be uniquely identified within each of the 3 areas of caCORE - caBIO, SPORES and 
   * caDSR- using the following three attributes: Prot 
   * 
   */

public  class Protocol  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String approvedBy;
	public java.lang.String getApprovedBy()
	{
		return approvedBy;
	}
	public void setApprovedBy(java.lang.String approvedBy)
	{
		this.approvedBy = approvedBy;
	}
	
		
	public java.util.Date approvedDate;
	public java.util.Date getApprovedDate()
	{
		return approvedDate;
	}
	public void setApprovedDate(java.util.Date approvedDate)
	{
		this.approvedDate = approvedDate;
	}
	
		
	public java.lang.String changeNumber;
	public java.lang.String getChangeNumber()
	{
		return changeNumber;
	}
	public void setChangeNumber(java.lang.String changeNumber)
	{
		this.changeNumber = changeNumber;
	}
	
		
	public java.lang.String changeType;
	public java.lang.String getChangeType()
	{
		return changeType;
	}
	public void setChangeType(java.lang.String changeType)
	{
		this.changeType = changeType;
	}
	
		
	public java.lang.String leadOrganizationName;
	public java.lang.String getLeadOrganizationName()
	{
		return leadOrganizationName;
	}
	public void setLeadOrganizationName(java.lang.String leadOrganizationName)
	{
		this.leadOrganizationName = leadOrganizationName;
	}
	
		
	public java.lang.String phase;
	public java.lang.String getPhase()
	{
		return phase;
	}
	public void setPhase(java.lang.String phase)
	{
		this.phase = phase;
	}
	
		
	public java.lang.String protocolID;
	public java.lang.String getProtocolID()
	{
		return protocolID;
	}
	public void setProtocolID(java.lang.String protocolID)
	{
		this.protocolID = protocolID;
	}
	
		
	public java.lang.String reviewedBy;
	public java.lang.String getReviewedBy()
	{
		return reviewedBy;
	}
	public void setReviewedBy(java.lang.String reviewedBy)
	{
		this.reviewedBy = reviewedBy;
	}
	
		
	public java.util.Date reviewedDate;
	public java.util.Date getReviewedDate()
	{
		return reviewedDate;
	}
	public void setReviewedDate(java.util.Date reviewedDate)
	{
		this.reviewedDate = reviewedDate;
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
	
	
		
	
	
	
		
		
	private java.util.Collection formCollection = new java.util.HashSet();
	public java.util.Collection getFormCollection()
	{
		if (formCollection==null || formCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Form as child, gov.nih.nci.cadsr.domain.Protocol as parent  where child in elements(parent.formCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Form");				 
				formCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Protocol.class.getName());
				log.error("Protocol:getFormCollection throws exception ... ...",ex);
			}
		}	
		return formCollection;
	}
	
	public void setFormCollection(java.util.Collection formCollection)
	{
		this.formCollection = formCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Protocol) 
		{
			Protocol c =(Protocol)obj; 			 
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