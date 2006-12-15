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

public  class CloneRelativeLocation 	implements java.io.Serializable 
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
	
		
	public java.lang.String type;
	public java.lang.String getType()
	{
		return type;
	}
	public void setType(java.lang.String type)
	{
		this.type = type;
	}
	
	
		
		
	private gov.nih.nci.cabio.domain.Clone clone;
	public gov.nih.nci.cabio.domain.Clone getClone()
	{
			
		if(clone==null ||  clone.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Clone as child where child.id in (select parent.clone.id from gov.nih.nci.cabio.domain.CloneRelativeLocation as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Clone");				 
				if (resultList!=null && resultList.size()>0) 
					clone = (gov.nih.nci.cabio.domain.Clone)resultList.get(0);
				else
					clone = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(CloneRelativeLocation.class.getName());
				log.error("CloneRelativeLocation:getClone throws exception ... ...",ex);
			}
		}
		return clone;	
					
	}

	public void setClone(gov.nih.nci.cabio.domain.Clone clone)
	{
		this.clone = clone;
	}
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.NucleicAcidSequence nucleicAcidSequence;
	public gov.nih.nci.cabio.domain.NucleicAcidSequence getNucleicAcidSequence()
	{
			
		if(nucleicAcidSequence==null ||  nucleicAcidSequence.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.NucleicAcidSequence as child where child.id in (select parent.nucleicAcidSequence.id from gov.nih.nci.cabio.domain.CloneRelativeLocation as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.NucleicAcidSequence");				 
				if (resultList!=null && resultList.size()>0) 
					nucleicAcidSequence = (gov.nih.nci.cabio.domain.NucleicAcidSequence)resultList.get(0);
				else
					nucleicAcidSequence = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(CloneRelativeLocation.class.getName());
				log.error("CloneRelativeLocation:getNucleicAcidSequence throws exception ... ...",ex);
			}
		}
		return nucleicAcidSequence;	
					
	}

	public void setNucleicAcidSequence(gov.nih.nci.cabio.domain.NucleicAcidSequence nucleicAcidSequence)
	{
		this.nucleicAcidSequence = nucleicAcidSequence;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof CloneRelativeLocation) 
		{
			CloneRelativeLocation c =(CloneRelativeLocation)obj; 			 
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