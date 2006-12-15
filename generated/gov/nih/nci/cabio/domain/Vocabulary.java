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
   * The object describes the vocabulary.
   */

public  class Vocabulary 	implements java.io.Serializable 
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
	
		
	public java.lang.String coreTerm;
	public java.lang.String getCoreTerm()
	{
		return coreTerm;
	}
	public void setCoreTerm(java.lang.String coreTerm)
	{
		this.coreTerm = coreTerm;
	}
	
		
	public java.lang.String generalTerm;
	public java.lang.String getGeneralTerm()
	{
		return generalTerm;
	}
	public void setGeneralTerm(java.lang.String generalTerm)
	{
		this.generalTerm = generalTerm;
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
	
	
		
		
	private java.util.Collection anomalyCollection = new java.util.HashSet();
	public java.util.Collection getAnomalyCollection()
	{
		if (anomalyCollection==null || anomalyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Anomaly as child, gov.nih.nci.cabio.domain.Vocabulary as parent  where child in elements(parent.anomalyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Anomaly");				 
				anomalyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Vocabulary.class.getName());
				log.error("Vocabulary:getAnomalyCollection throws exception ... ...",ex);
			}
		}	
		return anomalyCollection;
	}
	
	public void setAnomalyCollection(java.util.Collection anomalyCollection)
	{
		this.anomalyCollection = anomalyCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Vocabulary) 
		{
			Vocabulary c =(Vocabulary)obj; 			 
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