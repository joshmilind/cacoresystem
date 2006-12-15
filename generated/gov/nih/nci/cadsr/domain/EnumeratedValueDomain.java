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
   * A value domain expressed as a list of all permissible values.
   */

public  class EnumeratedValueDomain  extends gov.nih.nci.cadsr.domain.ValueDomain 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
	
		
		
	private java.util.Collection valueDomainPermissibleValueCollection = new java.util.HashSet();
	public java.util.Collection getValueDomainPermissibleValueCollection()
	{
		if (valueDomainPermissibleValueCollection==null || valueDomainPermissibleValueCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue as child, gov.nih.nci.cadsr.domain.EnumeratedValueDomain as parent  where child in elements(parent.valueDomainPermissibleValueCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue");				 
				valueDomainPermissibleValueCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(EnumeratedValueDomain.class.getName());
				log.error("EnumeratedValueDomain:getValueDomainPermissibleValueCollection throws exception ... ...",ex);
			}
		}	
		return valueDomainPermissibleValueCollection;
	}
	
	public void setValueDomainPermissibleValueCollection(java.util.Collection valueDomainPermissibleValueCollection)
	{
		this.valueDomainPermissibleValueCollection = valueDomainPermissibleValueCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof EnumeratedValueDomain) 
		{
			EnumeratedValueDomain c =(EnumeratedValueDomain)obj; 			 
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