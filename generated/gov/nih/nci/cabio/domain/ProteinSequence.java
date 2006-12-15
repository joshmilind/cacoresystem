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

public  class ProteinSequence 	implements java.io.Serializable 
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
	
		
	public java.lang.String checkSum;
	public java.lang.String getCheckSum()
	{
		return checkSum;
	}
	public void setCheckSum(java.lang.String checkSum)
	{
		this.checkSum = checkSum;
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
	
		
	public java.lang.Long length;
	public java.lang.Long getLength()
	{
		return length;
	}
	public void setLength(java.lang.Long length)
	{
		this.length = length;
	}
	
		
	public java.lang.Double molecularWeightInDaltons;
	public java.lang.Double getMolecularWeightInDaltons()
	{
		return molecularWeightInDaltons;
	}
	public void setMolecularWeightInDaltons(java.lang.Double molecularWeightInDaltons)
	{
		this.molecularWeightInDaltons = molecularWeightInDaltons;
	}
	
		
	public java.lang.String value;
	public java.lang.String getValue()
	{
		return value;
	}
	public void setValue(java.lang.String value)
	{
		this.value = value;
	}
	
	
		
		
	private gov.nih.nci.cabio.domain.Protein protein;
	public gov.nih.nci.cabio.domain.Protein getProtein()
	{
			
		if(protein==null ||  protein.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Protein as child where child.id in (select parent.protein.id from gov.nih.nci.cabio.domain.ProteinSequence as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Protein");				 
				if (resultList!=null && resultList.size()>0) 
					protein = (gov.nih.nci.cabio.domain.Protein)resultList.get(0);
				else
					protein = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ProteinSequence.class.getName());
				log.error("ProteinSequence:getProtein throws exception ... ...",ex);
			}
		}
		return protein;	
					
	}

	public void setProtein(gov.nih.nci.cabio.domain.Protein protein)
	{
		this.protein = protein;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ProteinSequence) 
		{
			ProteinSequence c =(ProteinSequence)obj; 			 
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