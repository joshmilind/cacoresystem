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

   */

public  class UMLClassMetadata 	implements java.io.Serializable 
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
	
		
	public java.lang.String fullyQualifiedName;
	public java.lang.String getFullyQualifiedName()
	{
		return fullyQualifiedName;
	}
	public void setFullyQualifiedName(java.lang.String fullyQualifiedName)
	{
		this.fullyQualifiedName = fullyQualifiedName;
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
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.Project project;
	public gov.nih.nci.cadsr.umlproject.domain.Project getProject()
	{
			
		if(project==null ||  project.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.Project as child where child.id in (select parent.project.id from gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(UMLClassMetadata.class.getName());
				log.error("UMLClassMetadata:getProject throws exception ... ...",ex);
			}
		}
		return project;	
					
	}

	public void setProject(gov.nih.nci.cadsr.umlproject.domain.Project project)
	{
		this.project = project;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata UMLPackageMetadata;
	public gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata getUMLPackageMetadata()
	{
			
		if(UMLPackageMetadata==null ||  UMLPackageMetadata.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata as child where child.id in (select parent.UMLPackageMetadata.id from gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata");				 
				if (resultList!=null && resultList.size()>0) 
					UMLPackageMetadata = (gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata)resultList.get(0);
				else
					UMLPackageMetadata = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLClassMetadata.class.getName());
				log.error("UMLClassMetadata:getUMLPackageMetadata throws exception ... ...",ex);
			}
		}
		return UMLPackageMetadata;	
					
	}

	public void setUMLPackageMetadata(gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata UMLPackageMetadata)
	{
		this.UMLPackageMetadata = UMLPackageMetadata;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.UMLGeneralizationMetadata UMLGeneralizationMetadata;
	public gov.nih.nci.cadsr.umlproject.domain.UMLGeneralizationMetadata getUMLGeneralizationMetadata()
	{
			
		if(UMLGeneralizationMetadata==null ||  UMLGeneralizationMetadata.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLGeneralizationMetadata as child where child.id in (select parent.UMLGeneralizationMetadata.id from gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLGeneralizationMetadata");				 
				if (resultList!=null && resultList.size()>0) 
					UMLGeneralizationMetadata = (gov.nih.nci.cadsr.umlproject.domain.UMLGeneralizationMetadata)resultList.get(0);
				else
					UMLGeneralizationMetadata = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLClassMetadata.class.getName());
				log.error("UMLClassMetadata:getUMLGeneralizationMetadata throws exception ... ...",ex);
			}
		}
		return UMLGeneralizationMetadata;	
					
	}

	public void setUMLGeneralizationMetadata(gov.nih.nci.cadsr.umlproject.domain.UMLGeneralizationMetadata UMLGeneralizationMetadata)
	{
		this.UMLGeneralizationMetadata = UMLGeneralizationMetadata;
	}
		
	
	
	
		
		
	private java.util.Collection UMLAttributeMetadataCollection = new java.util.HashSet();
	public java.util.Collection getUMLAttributeMetadataCollection()
	{
		if (UMLAttributeMetadataCollection==null || UMLAttributeMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata as child, gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as parent  where child in elements(parent.UMLAttributeMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata");				 
				UMLAttributeMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(UMLClassMetadata.class.getName());
				log.error("UMLClassMetadata:getUMLAttributeMetadataCollection throws exception ... ...",ex);
			}
		}	
		return UMLAttributeMetadataCollection;
	}
	
	public void setUMLAttributeMetadataCollection(java.util.Collection UMLAttributeMetadataCollection)
	{
		this.UMLAttributeMetadataCollection = UMLAttributeMetadataCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ObjectClass objectClass;
	public gov.nih.nci.cadsr.domain.ObjectClass getObjectClass()
	{
			
		if(objectClass==null ||  objectClass.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClass as child where child.id in (select parent.objectClass.id from gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClass");				 
				if (resultList!=null && resultList.size()>0) 
					objectClass = (gov.nih.nci.cadsr.domain.ObjectClass)resultList.get(0);
				else
					objectClass = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLClassMetadata.class.getName());
				log.error("UMLClassMetadata:getObjectClass throws exception ... ...",ex);
			}
		}
		return objectClass;	
					
	}

	public void setObjectClass(gov.nih.nci.cadsr.domain.ObjectClass objectClass)
	{
		this.objectClass = objectClass;
	}
		
	
	
	
		
		
	private java.util.Collection UMLAssociationMetadataCollection = new java.util.HashSet();
	public java.util.Collection getUMLAssociationMetadataCollection()
	{
		if (UMLAssociationMetadataCollection==null || UMLAssociationMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata as child, gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as parent  where child in elements(parent.UMLAssociationMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata");				 
				UMLAssociationMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(UMLClassMetadata.class.getName());
				log.error("UMLClassMetadata:getUMLAssociationMetadataCollection throws exception ... ...",ex);
			}
		}	
		return UMLAssociationMetadataCollection;
	}
	
	public void setUMLAssociationMetadataCollection(java.util.Collection UMLAssociationMetadataCollection)
	{
		this.UMLAssociationMetadataCollection = UMLAssociationMetadataCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection semanticMetadataCollection = new java.util.HashSet();
	public java.util.Collection getSemanticMetadataCollection()
	{
		if (semanticMetadataCollection==null || semanticMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata as child, gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as parent  where child in elements(parent.semanticMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata");				 
				semanticMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(UMLClassMetadata.class.getName());
				log.error("UMLClassMetadata:getSemanticMetadataCollection throws exception ... ...",ex);
			}
		}	
		return semanticMetadataCollection;
	}
	
	public void setSemanticMetadataCollection(java.util.Collection semanticMetadataCollection)
	{
		this.semanticMetadataCollection = semanticMetadataCollection;
	}	
		
	
	
	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof UMLClassMetadata) 
		{
			UMLClassMetadata c =(UMLClassMetadata)obj; 			 
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