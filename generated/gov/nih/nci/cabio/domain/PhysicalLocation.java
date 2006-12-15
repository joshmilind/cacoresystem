package gov.nih.nci.cabio.domain;

import gov.nih.nci.cabio.domain.*;
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

public  class PhysicalLocation  extends gov.nih.nci.cabio.domain.Location 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.Long chromosomalEndPosition;
	public java.lang.Long getChromosomalEndPosition()
	{
		return chromosomalEndPosition;
	}
	public void setChromosomalEndPosition(java.lang.Long chromosomalEndPosition)
	{
		this.chromosomalEndPosition = chromosomalEndPosition;
	}
	
		
	public java.lang.Long chromosomalStartPosition;
	public java.lang.Long getChromosomalStartPosition()
	{
		return chromosomalStartPosition;
	}
	public void setChromosomalStartPosition(java.lang.Long chromosomalStartPosition)
	{
		this.chromosomalStartPosition = chromosomalStartPosition;
	}
	
	
		
		
	private java.util.Collection cytobandCollection = new java.util.HashSet();
	public java.util.Collection getCytobandCollection()
	{
		if (cytobandCollection==null || cytobandCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Cytoband as child, gov.nih.nci.cabio.domain.PhysicalLocation as parent  where child in elements(parent.cytobandCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Cytoband");				 
				cytobandCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(PhysicalLocation.class.getName());
				log.error("PhysicalLocation:getCytobandCollection throws exception ... ...",ex);
			}
		}	
		return cytobandCollection;
	}
	
	public void setCytobandCollection(java.util.Collection cytobandCollection)
	{
		this.cytobandCollection = cytobandCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof PhysicalLocation) 
		{
			PhysicalLocation c =(PhysicalLocation)obj; 			 
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