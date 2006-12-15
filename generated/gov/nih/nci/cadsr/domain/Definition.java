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
   * The information pertaining to definitions for an Administered Item in a specific Context. 
   * 
   */

public  class Definition 	implements java.io.Serializable 
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
	
		
	public java.lang.String text;
	public java.lang.String getText()
	{
		return text;
	}
	public void setText(java.lang.String text)
	{
		this.text = text;
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
				String hql = "select child from gov.nih.nci.cadsr.domain.Context as child where child.id in (select parent.context.id from gov.nih.nci.cadsr.domain.Definition as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(Definition.class.getName());
				log.error("Definition:getContext throws exception ... ...",ex);
			}
		}
		return context;	
					
	}

	public void setContext(gov.nih.nci.cadsr.domain.Context context)
	{
		this.context = context;
	}
		
	
	
	
		
		
	private java.util.Collection definitionClassSchemeItemCollection = new java.util.HashSet();
	public java.util.Collection getDefinitionClassSchemeItemCollection()
	{
		if (definitionClassSchemeItemCollection==null || definitionClassSchemeItemCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DefinitionClassSchemeItem as child, gov.nih.nci.cadsr.domain.Definition as parent  where child in elements(parent.definitionClassSchemeItemCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DefinitionClassSchemeItem");				 
				definitionClassSchemeItemCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Definition.class.getName());
				log.error("Definition:getDefinitionClassSchemeItemCollection throws exception ... ...",ex);
			}
		}	
		return definitionClassSchemeItemCollection;
	}
	
	public void setDefinitionClassSchemeItemCollection(java.util.Collection definitionClassSchemeItemCollection)
	{
		this.definitionClassSchemeItemCollection = definitionClassSchemeItemCollection;
	}	
		
	
	
	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Definition) 
		{
			Definition c =(Definition)obj; 			 
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