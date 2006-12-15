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
   * e.g caCore
   */

public  class Project 	implements java.io.Serializable 
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
	
		
	public java.lang.String longName;
	public java.lang.String getLongName()
	{
		return longName;
	}
	public void setLongName(java.lang.String longName)
	{
		this.longName = longName;
	}
	
		
	public java.lang.String shortName;
	public java.lang.String getShortName()
	{
		return shortName;
	}
	public void setShortName(java.lang.String shortName)
	{
		this.shortName = shortName;
	}
	
		
	public java.lang.String version;
	public java.lang.String getVersion()
	{
		return version;
	}
	public void setVersion(java.lang.String version)
	{
		this.version = version;
	}
	
	
		
		
	private java.util.Collection subProjectCollection = new java.util.HashSet();
	public java.util.Collection getSubProjectCollection()
	{
		if (subProjectCollection==null || subProjectCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.SubProject as child, gov.nih.nci.cadsr.umlproject.domain.Project as parent  where child in elements(parent.subProjectCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.SubProject");				 
				subProjectCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Project.class.getName());
				log.error("Project:getSubProjectCollection throws exception ... ...",ex);
			}
		}	
		return subProjectCollection;
	}
	
	public void setSubProjectCollection(java.util.Collection subProjectCollection)
	{
		this.subProjectCollection = subProjectCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection UMLPackageMetadataCollection = new java.util.HashSet();
	public java.util.Collection getUMLPackageMetadataCollection()
	{
		if (UMLPackageMetadataCollection==null || UMLPackageMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata as child, gov.nih.nci.cadsr.umlproject.domain.Project as parent  where child in elements(parent.UMLPackageMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata");				 
				UMLPackageMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Project.class.getName());
				log.error("Project:getUMLPackageMetadataCollection throws exception ... ...",ex);
			}
		}	
		return UMLPackageMetadataCollection;
	}
	
	public void setUMLPackageMetadataCollection(java.util.Collection UMLPackageMetadataCollection)
	{
		this.UMLPackageMetadataCollection = UMLPackageMetadataCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassificationScheme classificationScheme;
	public gov.nih.nci.cadsr.domain.ClassificationScheme getClassificationScheme()
	{
			
		if(classificationScheme==null ||  classificationScheme.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationScheme as child where child.id in (select parent.classificationScheme.id from gov.nih.nci.cadsr.umlproject.domain.Project as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationScheme");				 
				if (resultList!=null && resultList.size()>0) 
					classificationScheme = (gov.nih.nci.cadsr.domain.ClassificationScheme)resultList.get(0);
				else
					classificationScheme = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Project.class.getName());
				log.error("Project:getClassificationScheme throws exception ... ...",ex);
			}
		}
		return classificationScheme;	
					
	}

	public void setClassificationScheme(gov.nih.nci.cadsr.domain.ClassificationScheme classificationScheme)
	{
		this.classificationScheme = classificationScheme;
	}
		
	
	
	
		
		
	private java.util.Collection UMLClassMetadataCollection = new java.util.HashSet();
	public java.util.Collection getUMLClassMetadataCollection()
	{
		if (UMLClassMetadataCollection==null || UMLClassMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as child, gov.nih.nci.cadsr.umlproject.domain.Project as parent  where child in elements(parent.UMLClassMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata");				 
				UMLClassMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Project.class.getName());
				log.error("Project:getUMLClassMetadataCollection throws exception ... ...",ex);
			}
		}	
		return UMLClassMetadataCollection;
	}
	
	public void setUMLClassMetadataCollection(java.util.Collection UMLClassMetadataCollection)
	{
		this.UMLClassMetadataCollection = UMLClassMetadataCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection UMLAttributeMetadataCollection = new java.util.HashSet();
	public java.util.Collection getUMLAttributeMetadataCollection()
	{
		if (UMLAttributeMetadataCollection==null || UMLAttributeMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata as child, gov.nih.nci.cadsr.umlproject.domain.Project as parent  where child in elements(parent.UMLAttributeMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata");				 
				UMLAttributeMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Project.class.getName());
				log.error("Project:getUMLAttributeMetadataCollection throws exception ... ...",ex);
			}
		}	
		return UMLAttributeMetadataCollection;
	}
	
	public void setUMLAttributeMetadataCollection(java.util.Collection UMLAttributeMetadataCollection)
	{
		this.UMLAttributeMetadataCollection = UMLAttributeMetadataCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection UMLAssociationMetadataCollection = new java.util.HashSet();
	public java.util.Collection getUMLAssociationMetadataCollection()
	{
		if (UMLAssociationMetadataCollection==null || UMLAssociationMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata as child, gov.nih.nci.cadsr.umlproject.domain.Project as parent  where child in elements(parent.UMLAssociationMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata");				 
				UMLAssociationMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Project.class.getName());
				log.error("Project:getUMLAssociationMetadataCollection throws exception ... ...",ex);
			}
		}	
		return UMLAssociationMetadataCollection;
	}
	
	public void setUMLAssociationMetadataCollection(java.util.Collection UMLAssociationMetadataCollection)
	{
		this.UMLAssociationMetadataCollection = UMLAssociationMetadataCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Project) 
		{
			Project c =(Project)obj; 			 
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