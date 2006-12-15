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

public  class CytogeneticLocation  extends gov.nih.nci.cabio.domain.Location 	implements java.io.Serializable 
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
	
		
	private java.lang.Long endCytobandLocId;
	public java.lang.Long getEndCytobandLocId()
	{
		return endCytobandLocId;
	}
	public void setEndCytobandLocId(java.lang.Long endCytobandLocId)
	{
		this.endCytobandLocId = endCytobandLocId;
	}
	
		
	private java.lang.Long startCytobandLocId;
	public java.lang.Long getStartCytobandLocId()
	{
		return startCytobandLocId;
	}
	public void setStartCytobandLocId(java.lang.Long startCytobandLocId)
	{
		this.startCytobandLocId = startCytobandLocId;
	}
	
	
		
		
	private gov.nih.nci.cabio.domain.Cytoband endCytoband;
	public gov.nih.nci.cabio.domain.Cytoband getEndCytoband()
	{
			
		if(endCytoband==null ||  endCytoband.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Cytoband as child where child.id in (select parent.endCytoband.id from gov.nih.nci.cabio.domain.CytogeneticLocation as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Cytoband");				 
				if (resultList!=null && resultList.size()>0) 
					endCytoband = (gov.nih.nci.cabio.domain.Cytoband)resultList.get(0);
				else
					endCytoband = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(CytogeneticLocation.class.getName());
				log.error("CytogeneticLocation:getEndCytoband throws exception ... ...",ex);
			}
		}
		return endCytoband;	
					
	}

	public void setEndCytoband(gov.nih.nci.cabio.domain.Cytoband endCytoband)
	{
		this.endCytoband = endCytoband;
	}
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.Cytoband startCytoband;
	public gov.nih.nci.cabio.domain.Cytoband getStartCytoband()
	{
			
		if(startCytoband==null ||  startCytoband.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Cytoband as child where child.id in (select parent.startCytoband.id from gov.nih.nci.cabio.domain.CytogeneticLocation as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Cytoband");				 
				if (resultList!=null && resultList.size()>0) 
					startCytoband = (gov.nih.nci.cabio.domain.Cytoband)resultList.get(0);
				else
					startCytoband = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(CytogeneticLocation.class.getName());
				log.error("CytogeneticLocation:getStartCytoband throws exception ... ...",ex);
			}
		}
		return startCytoband;	
					
	}

	public void setStartCytoband(gov.nih.nci.cabio.domain.Cytoband startCytoband)
	{
		this.startCytoband = startCytoband;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof CytogeneticLocation) 
		{
			CytogeneticLocation c =(CytogeneticLocation)obj; 			 
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