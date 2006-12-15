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
   * A questionnaire that documents all the patient data stipulated in the protocol and used by clinicians 
   * to record information about patient's visits while on the clinical trial. 
   * 
   */

public  class Form  extends gov.nih.nci.cadsr.domain.FormElement 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String displayName;
	public java.lang.String getDisplayName()
	{
		return displayName;
	}
	public void setDisplayName(java.lang.String displayName)
	{
		this.displayName = displayName;
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
	
	
		
		
	private java.util.Collection protocolCollection = new java.util.HashSet();
	public java.util.Collection getProtocolCollection()
	{
		if (protocolCollection==null || protocolCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Protocol as child, gov.nih.nci.cadsr.domain.Form as parent  where child in elements(parent.protocolCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Protocol");				 
				protocolCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Form.class.getName());
				log.error("Form:getProtocolCollection throws exception ... ...",ex);
			}
		}	
		return protocolCollection;
	}
	
	public void setProtocolCollection(java.util.Collection protocolCollection)
	{
		this.protocolCollection = protocolCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection moduleCollection = new java.util.HashSet();
	public java.util.Collection getModuleCollection()
	{
		if (moduleCollection==null || moduleCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Module as child, gov.nih.nci.cadsr.domain.Form as parent  where child in elements(parent.moduleCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Module");				 
				moduleCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Form.class.getName());
				log.error("Form:getModuleCollection throws exception ... ...",ex);
			}
		}	
		return moduleCollection;
	}
	
	public void setModuleCollection(java.util.Collection moduleCollection)
	{
		this.moduleCollection = moduleCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Form) 
		{
			Form c =(Form)obj; 			 
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