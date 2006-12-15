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
   * A subclass of SourceReference that contains a URL to the original information.
   */

public  class URLSourceReference  extends gov.nih.nci.common.provenance.domain.SourceReference 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String sourceURL;
	public java.lang.String getSourceURL()
	{
		return sourceURL;
	}
	public void setSourceURL(java.lang.String sourceURL)
	{
		this.sourceURL = sourceURL;
	}

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof URLSourceReference) 
		{
			URLSourceReference c =(URLSourceReference)obj; 			 
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