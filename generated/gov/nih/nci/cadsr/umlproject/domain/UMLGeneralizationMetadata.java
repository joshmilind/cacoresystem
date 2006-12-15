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

public  class UMLGeneralizationMetadata 	implements java.io.Serializable 
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
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ObjectClassRelationship objectClassRelationship;
	public gov.nih.nci.cadsr.domain.ObjectClassRelationship getObjectClassRelationship()
	{
			
		if(objectClassRelationship==null ||  objectClassRelationship.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClassRelationship as child where child.id in (select parent.objectClassRelationship.id from gov.nih.nci.cadsr.umlproject.domain.UMLGeneralizationMetadata as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(UMLGeneralizationMetadata.class.getName());
				log.error("UMLGeneralizationMetadata:getObjectClassRelationship throws exception ... ...",ex);
			}
		}
		return objectClassRelationship;	
					
	}

	public void setObjectClassRelationship(gov.nih.nci.cadsr.domain.ObjectClassRelationship objectClassRelationship)
	{
		this.objectClassRelationship = objectClassRelationship;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata superUMLClassMetadata;
	public gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata getSuperUMLClassMetadata()
	{
			
		if(superUMLClassMetadata==null ||  superUMLClassMetadata.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata as child where child.id in (select parent.superUMLClassMetadata.id from gov.nih.nci.cadsr.umlproject.domain.UMLGeneralizationMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata");				 
				if (resultList!=null && resultList.size()>0) 
					superUMLClassMetadata = (gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata)resultList.get(0);
				else
					superUMLClassMetadata = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(UMLGeneralizationMetadata.class.getName());
				log.error("UMLGeneralizationMetadata:getSuperUMLClassMetadata throws exception ... ...",ex);
			}
		}
		return superUMLClassMetadata;	
					
	}

	public void setSuperUMLClassMetadata(gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata superUMLClassMetadata)
	{
		this.superUMLClassMetadata = superUMLClassMetadata;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof UMLGeneralizationMetadata) 
		{
			UMLGeneralizationMetadata c =(UMLGeneralizationMetadata)obj; 			 
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