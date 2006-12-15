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

public  class TypeEnumerationMetadata 	implements java.io.Serializable 
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
	
		
	public java.lang.String permissibleValue;
	public java.lang.String getPermissibleValue()
	{
		return permissibleValue;
	}
	public void setPermissibleValue(java.lang.String permissibleValue)
	{
		this.permissibleValue = permissibleValue;
	}
	
		
	public java.lang.String valueMeaning;
	public java.lang.String getValueMeaning()
	{
		return valueMeaning;
	}
	public void setValueMeaning(java.lang.String valueMeaning)
	{
		this.valueMeaning = valueMeaning;
	}
	
	
		
	
	
	
		
		
	private java.util.Collection semanticMetadataCollection = new java.util.HashSet();
	public java.util.Collection getSemanticMetadataCollection()
	{
		if (semanticMetadataCollection==null || semanticMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata as child, gov.nih.nci.cadsr.umlproject.domain.TypeEnumerationMetadata as parent  where child in elements(parent.semanticMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata");				 
				semanticMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(TypeEnumerationMetadata.class.getName());
				log.error("TypeEnumerationMetadata:getSemanticMetadataCollection throws exception ... ...",ex);
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
		if(obj instanceof TypeEnumerationMetadata) 
		{
			TypeEnumerationMetadata c =(TypeEnumerationMetadata)obj; 			 
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