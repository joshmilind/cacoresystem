package gov.nih.nci.common.provenance.domain;

import gov.nih.nci.common.provenance.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**

   */

public  class InternetSource  extends gov.nih.nci.common.provenance.domain.Source 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String ownerInstitution;
	public java.lang.String getOwnerInstitution()
	{
		return ownerInstitution;
	}
	public void setOwnerInstitution(java.lang.String ownerInstitution)
	{
		this.ownerInstitution = ownerInstitution;
	}
	
		
	public java.lang.String ownerPersons;
	public java.lang.String getOwnerPersons()
	{
		return ownerPersons;
	}
	public void setOwnerPersons(java.lang.String ownerPersons)
	{
		this.ownerPersons = ownerPersons;
	}
	
		
	public java.lang.String sourceURI;
	public java.lang.String getSourceURI()
	{
		return sourceURI;
	}
	public void setSourceURI(java.lang.String sourceURI)
	{
		this.sourceURI = sourceURI;
	}

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof InternetSource) 
		{
			InternetSource c =(InternetSource)obj; 			 
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