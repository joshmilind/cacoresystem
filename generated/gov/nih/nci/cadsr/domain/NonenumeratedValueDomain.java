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
   * A value domain that is specified by a description rather than a list of all Permissible Values. (ISO 
   * 11179) 
   * 
   */

public  class NonenumeratedValueDomain  extends gov.nih.nci.cadsr.domain.ValueDomain 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof NonenumeratedValueDomain) 
		{
			NonenumeratedValueDomain c =(NonenumeratedValueDomain)obj; 			 
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