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

public  class AttributeTypeMetadata 	implements java.io.Serializable 
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
	
		
	public java.lang.String valueDomainDataType;
	public java.lang.String getValueDomainDataType()
	{
		return valueDomainDataType;
	}
	public void setValueDomainDataType(java.lang.String valueDomainDataType)
	{
		this.valueDomainDataType = valueDomainDataType;
	}
	
		
	public java.lang.String valueDomainLongName;
	public java.lang.String getValueDomainLongName()
	{
		return valueDomainLongName;
	}
	public void setValueDomainLongName(java.lang.String valueDomainLongName)
	{
		this.valueDomainLongName = valueDomainLongName;
	}
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ValueDomain valueDomain;
	public gov.nih.nci.cadsr.domain.ValueDomain getValueDomain()
	{
			
		if(valueDomain==null ||  valueDomain.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomain as child where child.id in (select parent.valueDomain.id from gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomain");				 
				if (resultList!=null && resultList.size()>0) 
					valueDomain = (gov.nih.nci.cadsr.domain.ValueDomain)resultList.get(0);
				else
					valueDomain = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(AttributeTypeMetadata.class.getName());
				log.error("AttributeTypeMetadata:getValueDomain throws exception ... ...",ex);
			}
		}
		return valueDomain;	
					
	}

	public void setValueDomain(gov.nih.nci.cadsr.domain.ValueDomain valueDomain)
	{
		this.valueDomain = valueDomain;
	}
		
	
	
	
		
		
	private java.util.Collection typeEnumerationCollection = new java.util.HashSet();
	public java.util.Collection getTypeEnumerationCollection()
	{
		if (typeEnumerationCollection==null || typeEnumerationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.TypeEnumerationMetadata as child, gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata as parent  where child in elements(parent.typeEnumerationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.TypeEnumerationMetadata");				 
				typeEnumerationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(AttributeTypeMetadata.class.getName());
				log.error("AttributeTypeMetadata:getTypeEnumerationCollection throws exception ... ...",ex);
			}
		}	
		return typeEnumerationCollection;
	}
	
	public void setTypeEnumerationCollection(java.util.Collection typeEnumerationCollection)
	{
		this.typeEnumerationCollection = typeEnumerationCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection semanticMetadataCollection = new java.util.HashSet();
	public java.util.Collection getSemanticMetadataCollection()
	{
		if (semanticMetadataCollection==null || semanticMetadataCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata as child, gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata as parent  where child in elements(parent.semanticMetadataCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata");				 
				semanticMetadataCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(AttributeTypeMetadata.class.getName());
				log.error("AttributeTypeMetadata:getSemanticMetadataCollection throws exception ... ...",ex);
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
		if(obj instanceof AttributeTypeMetadata) 
		{
			AttributeTypeMetadata c =(AttributeTypeMetadata)obj; 			 
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