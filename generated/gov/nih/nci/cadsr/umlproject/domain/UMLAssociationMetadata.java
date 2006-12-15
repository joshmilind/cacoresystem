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

public  class UMLAssociationMetadata 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String id;
	public java.lang.String getId()
	{
		return id;
	}
	public void setId(java.lang.String id)
	{
		this.id = id;
	}
	
		
	public java.lang.Boolean isBidirectional;
	public java.lang.Boolean getIsBidirectional()
	{
		return isBidirectional;
	}
	public void setIsBidirectional(java.lang.Boolean isBidirectional)
	{
		this.isBidirectional = isBidirectional;
	}
	
		
	public java.lang.Integer sourceHighCardinality;
	public java.lang.Integer getSourceHighCardinality()
	{
		return sourceHighCardinality;
	}
	public void setSourceHighCardinality(java.lang.Integer sourceHighCardinality)
	{
		this.sourceHighCardinality = sourceHighCardinality;
	}
	
		
	public java.lang.Integer sourceLowCardinality;
	public java.lang.Integer getSourceLowCardinality()
	{
		return sourceLowCardinality;
	}
	public void setSourceLowCardinality(java.lang.Integer sourceLowCardinality)
	{
		this.sourceLowCardinality = sourceLowCardinality;
	}
	
		
	public java.lang.String sourceRoleName;
	public java.lang.String getSourceRoleName()
	{
		return sourceRoleName;
	}
	public void setSourceRoleName(java.lang.String sourceRoleName)
	{
		this.sourceRoleName = sourceRoleName;
	}
	
		
	public java.lang.Integer targetHighCardinality;
	public java.lang.Integer getTargetHighCardinality()
	{
		return targetHighCardinality;
	}
	public void setTargetHighCardinality(java.lang.Integer targetHighCardinality)
	{
		this.targetHighCardinality = targetHighCardinality;
	}
	
		
	public java.lang.Integer targetLowCardinality;
	public java.lang.Integer getTargetLowCardinality()
	{
		return targetLowCardinality;
	}
	public void setTargetLowCardinality(java.lang.Integer targetLowCardinality)
	{
		this.targetLowCardinality = targetLowCardinality;
	}
	
		
	public java.lang.String targetRoleName;
	public java.lang.String getTargetRoleName()
	{
		return targetRoleName;
	}
	public void setTargetRoleName(java.lang.String targetRoleName)
	{
		this.targetRoleName = targetRoleName;
	}
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata targetUMLClassMetadata;
	public gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata getTargetUMLClassMetadata()
	{
			
		if(targetUMLClassMetadata==null ||  targetUMLClassMetadata.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as child where child.id in (select parent.targetUMLClassMetadata.id from gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata");				 
				if (resultList!=null && resultList.size()>0) 
					targetUMLClassMetadata = (gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata)resultList.get(0);
				else
					targetUMLClassMetadata = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLAssociationMetadata.class.getName());
				log.error("UMLAssociationMetadata:getTargetUMLClassMetadata throws exception ... ...",ex);
			}
		}
		return targetUMLClassMetadata;	
					
	}

	public void setTargetUMLClassMetadata(gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata targetUMLClassMetadata)
	{
		this.targetUMLClassMetadata = targetUMLClassMetadata;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.Project project;
	public gov.nih.nci.cadsr.umlproject.domain.Project getProject()
	{
			
		if(project==null ||  project.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.Project as child where child.id in (select parent.project.id from gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(UMLAssociationMetadata.class.getName());
				log.error("UMLAssociationMetadata:getProject throws exception ... ...",ex);
			}
		}
		return project;	
					
	}

	public void setProject(gov.nih.nci.cadsr.umlproject.domain.Project project)
	{
		this.project = project;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ObjectClassRelationship objectClassRelationship;
	public gov.nih.nci.cadsr.domain.ObjectClassRelationship getObjectClassRelationship()
	{
			
		if(objectClassRelationship==null ||  objectClassRelationship.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClassRelationship as child where child.id in (select parent.objectClassRelationship.id from gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClassRelationship");				 
				if (resultList!=null && resultList.size()>0) 
					objectClassRelationship = (gov.nih.nci.cadsr.domain.ObjectClassRelationship)resultList.get(0);
				else
					objectClassRelationship = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLAssociationMetadata.class.getName());
				log.error("UMLAssociationMetadata:getObjectClassRelationship throws exception ... ...",ex);
			}
		}
		return objectClassRelationship;	
					
	}

	public void setObjectClassRelationship(gov.nih.nci.cadsr.domain.ObjectClassRelationship objectClassRelationship)
	{
		this.objectClassRelationship = objectClassRelationship;
	}
		
	
	
	
		
		
	private java.util.Collection semanticMetadataCollection = new java.util.HashSet();
	public java.util.Collection getSemanticMetadataCollection()
	{
		if (semanticMetadataCollection==null || semanticMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata as child, gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata as parent  where child in elements(parent.semanticMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata");				 
				semanticMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(UMLAssociationMetadata.class.getName());
				log.error("UMLAssociationMetadata:getSemanticMetadataCollection throws exception ... ...",ex);
			}
		}	
		return semanticMetadataCollection;
	}
	
	public void setSemanticMetadataCollection(java.util.Collection semanticMetadataCollection)
	{
		this.semanticMetadataCollection = semanticMetadataCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata sourceUMLClassMetadata;
	public gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata getSourceUMLClassMetadata()
	{
			
		if(sourceUMLClassMetadata==null ||  sourceUMLClassMetadata.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as child where child.id in (select parent.sourceUMLClassMetadata.id from gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata");				 
				if (resultList!=null && resultList.size()>0) 
					sourceUMLClassMetadata = (gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata)resultList.get(0);
				else
					sourceUMLClassMetadata = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLAssociationMetadata.class.getName());
				log.error("UMLAssociationMetadata:getSourceUMLClassMetadata throws exception ... ...",ex);
			}
		}
		return sourceUMLClassMetadata;	
					
	}

	public void setSourceUMLClassMetadata(gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata sourceUMLClassMetadata)
	{
		this.sourceUMLClassMetadata = sourceUMLClassMetadata;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof UMLAssociationMetadata) 
		{
			UMLAssociationMetadata c =(UMLAssociationMetadata)obj; 			 
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