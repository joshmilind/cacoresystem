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

public  class WebServicesSourceReference  extends gov.nih.nci.common.provenance.domain.SourceReference 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String request;
	public java.lang.String getRequest()
	{
		return request;
	}
	public void setRequest(java.lang.String request)
	{
		this.request = request;
	}

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof WebServicesSourceReference) 
		{
			WebServicesSourceReference c =(WebServicesSourceReference)obj; 			 
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