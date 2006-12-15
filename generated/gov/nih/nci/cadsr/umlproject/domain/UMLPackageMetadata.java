package gov.nih.nci.cadsr.umlproject.domain;

import gov.nih.nci.cadsr.umlproject.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * CSI type = UMLPACKAGE
   */

public  class UMLPackageMetadata 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String description;
	public java.lang.String getDescription()
	{
		return description;
	}
	public void setDescription(java.lang.String description)
	{
		this.description = description;
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
	
		
	public java.lang.String name;
	public java.lang.String getName()
	{
		return name;
	}
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
	
		
		
	private java.util.Collection UMLClassMetadataCollection = new java.util.HashSet();
	public java.util.Collection getUMLClassMetadataCollection()
	{
		if (UMLClassMetadataCollection==null || UMLClassMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as child, gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata as parent  where child in elements(parent.UMLClassMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata");				 
				UMLClassMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(UMLPackageMetadata.class.getName());
				log.error("UMLPackageMetadata:getUMLClassMetadataCollection throws exception ... ...",ex);
			}
		}	
		return UMLClassMetadataCollection;
	}
	
	public void setUMLClassMetadataCollection(java.util.Collection UMLClassMetadataCollection)
	{
		this.UMLClassMetadataCollection = UMLClassMetadataCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.Project project;
	public gov.nih.nci.cadsr.umlproject.domain.Project getProject()
	{
			
		if(project==null ||  project.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.Project as child where child.id in (select parent.project.id from gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.Project");				 
				if (resultList!=null && resultList.size()>0) 
					project = (gov.nih.nci.cadsr.umlproject.domain.Project)resultList.get(0);
				else
					project = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLPackageMetadata.class.getName());
				log.error("UMLPackageMetadata:getProject throws exception ... ...",ex);
			}
		}
		return project;	
					
	}

	public void setProject(gov.nih.nci.cadsr.umlproject.domain.Project project)
	{
		this.project = project;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem classSchemeClassSchemeItem;
	public gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem getClassSchemeClassSchemeItem()
	{
			
		if(classSchemeClassSchemeItem==null ||  classSchemeClassSchemeItem.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as child where child.id in (select parent.classSchemeClassSchemeItem.id from gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem");				 
				if (resultList!=null && resultList.size()>0) 
					classSchemeClassSchemeItem = (gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem)resultList.get(0);
				else
					classSchemeClassSchemeItem = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLPackageMetadata.class.getName());
				log.error("UMLPackageMetadata:getClassSchemeClassSchemeItem throws exception ... ...",ex);
			}
		}
		return classSchemeClassSchemeItem;	
					
	}

	public void setClassSchemeClassSchemeItem(gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem classSchemeClassSchemeItem)
	{
		this.classSchemeClassSchemeItem = classSchemeClassSchemeItem;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.SubProject subProject;
	public gov.nih.nci.cadsr.umlproject.domain.SubProject getSubProject()
	{
			
		if(subProject==null ||  subProject.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.SubProject as child where child.id in (select parent.subProject.id from gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.SubProject");				 
				if (resultList!=null && resultList.size()>0) 
					subProject = (gov.nih.nci.cadsr.umlproject.domain.SubProject)resultList.get(0);
				else
					subProject = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLPackageMetadata.class.getName());
				log.error("UMLPackageMetadata:getSubProject throws exception ... ...",ex);
			}
		}
		return subProject;	
					
	}

	public void setSubProject(gov.nih.nci.cadsr.umlproject.domain.SubProject subProject)
	{
		this.subProject = subProject;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof UMLPackageMetadata) 
		{
			UMLPackageMetadata c =(UMLPackageMetadata)obj; 			 
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