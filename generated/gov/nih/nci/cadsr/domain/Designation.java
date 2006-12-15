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
   * A name by which an Administered Component is known in a specific Context. Also a placeholder to track 
   * the usage of Administered Components by different Contexts. 
   * 
   */

public  class Designation 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String createdBy;
	public java.lang.String getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(java.lang.String createdBy)
	{
		this.createdBy = createdBy;
	}
	
		
	public java.util.Date dateCreated;
	public java.util.Date getDateCreated()
	{
		return dateCreated;
	}
	public void setDateCreated(java.util.Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}
	
		
	public java.util.Date dateModified;
	public java.util.Date getDateModified()
	{
		return dateModified;
	}
	public void setDateModified(java.util.Date dateModified)
	{
		this.dateModified = dateModified;
	}
	
		
	public java.lang.String id;
	public java.lang.String getId()
	{
		return id;
	}
	public void setId(java.lang.String id)
	{
		this.id = id;
	}
	
		
	public java.lang.String languageName;
	public java.lang.String getLanguageName()
	{
		return languageName;
	}
	public void setLanguageName(java.lang.String languageName)
	{
		this.languageName = languageName;
	}
	
		
	public java.lang.String modifiedBy;
	public java.lang.String getModifiedBy()
	{
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
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
	
		
	public java.lang.String type;
	public java.lang.String getType()
	{
		return type;
	}
	public void setType(java.lang.String type)
	{
		this.type = type;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.Context context;
	public gov.nih.nci.cadsr.domain.Context getContext()
	{
			
		if(context==null ||  context.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Context as child where child.id in (select parent.context.id from gov.nih.nci.cadsr.domain.Designation as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Context");				 
				if (resultList!=null && resultList.size()>0) 
					context = (gov.nih.nci.cadsr.domain.Context)resultList.get(0);
				else
					context = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Designation.class.getName());
				log.error("Designation:getContext throws exception ... ...",ex);
			}
		}
		return context;	
					
	}

	public void setContext(gov.nih.nci.cadsr.domain.Context context)
	{
		this.context = context;
	}
		
	
	
	
		
		
	private java.util.Collection designationClassSchemeItemCollection = new java.util.HashSet();
	public java.util.Collection getDesignationClassSchemeItemCollection()
	{
		if (designationClassSchemeItemCollection==null || designationClassSchemeItemCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DesignationClassSchemeItem as child, gov.nih.nci.cadsr.domain.Designation as parent  where child in elements(parent.designationClassSchemeItemCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DesignationClassSchemeItem");				 
				designationClassSchemeItemCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Designation.class.getName());
				log.error("Designation:getDesignationClassSchemeItemCollection throws exception ... ...",ex);
			}
		}	
		return designationClassSchemeItemCollection;
	}
	
	public void setDesignationClassSchemeItemCollection(java.util.Collection designationClassSchemeItemCollection)
	{
		this.designationClassSchemeItemCollection = designationClassSchemeItemCollection;
	}	
		
	
	
	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Designation) 
		{
			Designation c =(Designation)obj; 			 
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