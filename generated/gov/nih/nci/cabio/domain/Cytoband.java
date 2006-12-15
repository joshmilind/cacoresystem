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

public  class Cytoband 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String bigid;
	public java.lang.String getBigid()
	{
		return bigid;
	}
	public void setBigid(java.lang.String bigid)
	{
		this.bigid = bigid;
	}
	
		
	public java.lang.Long id;
	public java.lang.Long getId()
	{
		return id;
	}
	public void setId(java.lang.Long id)
	{
		this.id = id;
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
	
	
		
		
	private gov.nih.nci.cabio.domain.PhysicalLocation physicalLocation;
	public gov.nih.nci.cabio.domain.PhysicalLocation getPhysicalLocation()
	{
			
		if(physicalLocation==null ||  physicalLocation.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.PhysicalLocation as child where child.id in (select parent.physicalLocation.id from gov.nih.nci.cabio.domain.Cytoband as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.PhysicalLocation");				 
				if (resultList!=null && resultList.size()>0) 
					physicalLocation = (gov.nih.nci.cabio.domain.PhysicalLocation)resultList.get(0);
				else
					physicalLocation = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Cytoband.class.getName());
				log.error("Cytoband:getPhysicalLocation throws exception ... ...",ex);
			}
		}
		return physicalLocation;	
					
	}

	public void setPhysicalLocation(gov.nih.nci.cabio.domain.PhysicalLocation physicalLocation)
	{
		this.physicalLocation = physicalLocation;
	}
		
	
	
	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Cytoband) 
		{
			Cytoband c =(Cytoband)obj; 			 
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