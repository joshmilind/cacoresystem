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

public  class UMLAttributeMetadata 	implements java.io.Serializable 
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
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata UMLClassMetadata;
	public gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata getUMLClassMetadata()
	{
			
		if(UMLClassMetadata==null ||  UMLClassMetadata.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as child where child.id in (select parent.UMLClassMetadata.id from gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata");				 
				if (resultList!=null && resultList.size()>0) 
					UMLClassMetadata = (gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata)resultList.get(0);
				else
					UMLClassMetadata = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLAttributeMetadata.class.getName());
				log.error("UMLAttributeMetadata:getUMLClassMetadata throws exception ... ...",ex);
			}
		}
		return UMLClassMetadata;	
					
	}

	public void setUMLClassMetadata(gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata UMLClassMetadata)
	{
		this.UMLClassMetadata = UMLClassMetadata;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.DataElement dataElement;
	public gov.nih.nci.cadsr.domain.DataElement getDataElement()
	{
			
		if(dataElement==null ||  dataElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElement as child where child.id in (select parent.dataElement.id from gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElement");				 
				if (resultList!=null && resultList.size()>0) 
					dataElement = (gov.nih.nci.cadsr.domain.DataElement)resultList.get(0);
				else
					dataElement = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLAttributeMetadata.class.getName());
				log.error("UMLAttributeMetadata:getDataElement throws exception ... ...",ex);
			}
		}
		return dataElement;	
					
	}

	public void setDataElement(gov.nih.nci.cadsr.domain.DataElement dataElement)
	{
		this.dataElement = dataElement;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.Project project;
	public gov.nih.nci.cadsr.umlproject.domain.Project getProject()
	{
			
		if(project==null ||  project.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.Project as child where child.id in (select parent.project.id from gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(UMLAttributeMetadata.class.getName());
				log.error("UMLAttributeMetadata:getProject throws exception ... ...",ex);
			}
		}
		return project;	
					
	}

	public void setProject(gov.nih.nci.cadsr.umlproject.domain.Project project)
	{
		this.project = project;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata attributeTypeMetadata;
	public gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata getAttributeTypeMetadata()
	{
			
		if(attributeTypeMetadata==null ||  attributeTypeMetadata.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata as child where child.id in (select parent.attributeTypeMetadata.id from gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata");				 
				if (resultList!=null && resultList.size()>0) 
					attributeTypeMetadata = (gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata)resultList.get(0);
				else
					attributeTypeMetadata = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLAttributeMetadata.class.getName());
				log.error("UMLAttributeMetadata:getAttributeTypeMetadata throws exception ... ...",ex);
			}
		}
		return attributeTypeMetadata;	
					
	}

	public void setAttributeTypeMetadata(gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata attributeTypeMetadata)
	{
		this.attributeTypeMetadata = attributeTypeMetadata;
	}
		
	
	
	
		
		
	private java.util.Collection semanticMetadataCollection = new java.util.HashSet();
	public java.util.Collection getSemanticMetadataCollection()
	{
		if (semanticMetadataCollection==null || semanticMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata as child, gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata as parent  where child in elements(parent.semanticMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata");				 
				semanticMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(UMLAttributeMetadata.class.getName());
				log.error("UMLAttributeMetadata:getSemanticMetadataCollection throws exception ... ...",ex);
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
		if(obj instanceof UMLAttributeMetadata) 
		{
			UMLAttributeMetadata c =(UMLAttributeMetadata)obj; 			 
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