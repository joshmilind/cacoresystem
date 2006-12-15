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
   * Information pertaining to the association between Classification Schemes and Classification 
   * Scheme Items. 
   * 
   */

public  class ClassSchemeClassSchemeItem 	implements java.io.Serializable 
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
	
		
	public java.lang.Integer displayOrder;
	public java.lang.Integer getDisplayOrder()
	{
		return displayOrder;
	}
	public void setDisplayOrder(java.lang.Integer displayOrder)
	{
		this.displayOrder = displayOrder;
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
	
		
	public java.lang.String modifiedBy;
	public java.lang.String getModifiedBy()
	{
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}
	
	
		
	
	
	
		
		
	private java.util.Collection AdministeredComponentContact = new java.util.HashSet();
	public java.util.Collection getAdministeredComponentContact()
	{
		if (AdministeredComponentContact==null || AdministeredComponentContact.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.AdministeredComponentContact as child, gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as parent  where child in elements(parent.AdministeredComponentContact) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.AdministeredComponentContact");				 
				AdministeredComponentContact = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassSchemeClassSchemeItem.class.getName());
				log.error("ClassSchemeClassSchemeItem:getAdministeredComponentContact throws exception ... ...",ex);
			}
		}	
		return AdministeredComponentContact;
	}
	
	public void setAdministeredComponentContact(java.util.Collection AdministeredComponentContact)
	{
		this.AdministeredComponentContact = AdministeredComponentContact;
	}	
		
	
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassificationScheme classificationScheme;
	public gov.nih.nci.cadsr.domain.ClassificationScheme getClassificationScheme()
	{
			
		if(classificationScheme==null ||  classificationScheme.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationScheme as child where child.id in (select parent.classificationScheme.id from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(ClassSchemeClassSchemeItem.class.getName());
				log.error("ClassSchemeClassSchemeItem:getClassificationScheme throws exception ... ...",ex);
			}
		}
		return classificationScheme;	
					
	}

	public void setClassificationScheme(gov.nih.nci.cadsr.domain.ClassificationScheme classificationScheme)
	{
		this.classificationScheme = classificationScheme;
	}
		
	
	
	
		
		
	private java.util.Collection administeredComponentClassSchemeItemCollection = new java.util.HashSet();
	public java.util.Collection getAdministeredComponentClassSchemeItemCollection()
	{
		if (administeredComponentClassSchemeItemCollection==null || administeredComponentClassSchemeItemCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem as child, gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as parent  where child in elements(parent.administeredComponentClassSchemeItemCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem");				 
				administeredComponentClassSchemeItemCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassSchemeClassSchemeItem.class.getName());
				log.error("ClassSchemeClassSchemeItem:getAdministeredComponentClassSchemeItemCollection throws exception ... ...",ex);
			}
		}	
		return administeredComponentClassSchemeItemCollection;
	}
	
	public void setAdministeredComponentClassSchemeItemCollection(java.util.Collection administeredComponentClassSchemeItemCollection)
	{
		this.administeredComponentClassSchemeItemCollection = administeredComponentClassSchemeItemCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection referenceDocumentCollection = new java.util.HashSet();
	public java.util.Collection getReferenceDocumentCollection()
	{
		if (referenceDocumentCollection==null || referenceDocumentCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ReferenceDocument as child, gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as parent  where child in elements(parent.referenceDocumentCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ReferenceDocument");				 
				referenceDocumentCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassSchemeClassSchemeItem.class.getName());
				log.error("ClassSchemeClassSchemeItem:getReferenceDocumentCollection throws exception ... ...",ex);
			}
		}	
		return referenceDocumentCollection;
	}
	
	public void setReferenceDocumentCollection(java.util.Collection referenceDocumentCollection)
	{
		this.referenceDocumentCollection = referenceDocumentCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem parentClassSchemeClassSchemeItem;
	public gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem getParentClassSchemeClassSchemeItem()
	{
			
		if(parentClassSchemeClassSchemeItem==null ||  parentClassSchemeClassSchemeItem.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as child where child.id in (select parent.parentClassSchemeClassSchemeItem.id from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem");				 
				if (resultList!=null && resultList.size()>0) 
					parentClassSchemeClassSchemeItem = (gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem)resultList.get(0);
				else
					parentClassSchemeClassSchemeItem = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ClassSchemeClassSchemeItem.class.getName());
				log.error("ClassSchemeClassSchemeItem:getParentClassSchemeClassSchemeItem throws exception ... ...",ex);
			}
		}
		return parentClassSchemeClassSchemeItem;	
					
	}

	public void setParentClassSchemeClassSchemeItem(gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem parentClassSchemeClassSchemeItem)
	{
		this.parentClassSchemeClassSchemeItem = parentClassSchemeClassSchemeItem;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassificationSchemeItem classificationSchemeItem;
	public gov.nih.nci.cadsr.domain.ClassificationSchemeItem getClassificationSchemeItem()
	{
			
		if(classificationSchemeItem==null ||  classificationSchemeItem.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationSchemeItem as child where child.id in (select parent.classificationSchemeItem.id from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationSchemeItem");				 
				if (resultList!=null && resultList.size()>0) 
					classificationSchemeItem = (gov.nih.nci.cadsr.domain.ClassificationSchemeItem)resultList.get(0);
				else
					classificationSchemeItem = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ClassSchemeClassSchemeItem.class.getName());
				log.error("ClassSchemeClassSchemeItem:getClassificationSchemeItem throws exception ... ...",ex);
			}
		}
		return classificationSchemeItem;	
					
	}

	public void setClassificationSchemeItem(gov.nih.nci.cadsr.domain.ClassificationSchemeItem classificationSchemeItem)
	{
		this.classificationSchemeItem = classificationSchemeItem;
	}
		
	
	
	
		
		
	private java.util.Collection definitionClassSchemeItemCollection = new java.util.HashSet();
	public java.util.Collection getDefinitionClassSchemeItemCollection()
	{
		if (definitionClassSchemeItemCollection==null || definitionClassSchemeItemCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DefinitionClassSchemeItem as child, gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as parent  where child in elements(parent.definitionClassSchemeItemCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DefinitionClassSchemeItem");				 
				definitionClassSchemeItemCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassSchemeClassSchemeItem.class.getName());
				log.error("ClassSchemeClassSchemeItem:getDefinitionClassSchemeItemCollection throws exception ... ...",ex);
			}
		}	
		return definitionClassSchemeItemCollection;
	}
	
	public void setDefinitionClassSchemeItemCollection(java.util.Collection definitionClassSchemeItemCollection)
	{
		this.definitionClassSchemeItemCollection = definitionClassSchemeItemCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection childClassSchemeClassSchemeItemCollection = new java.util.HashSet();
	public java.util.Collection getChildClassSchemeClassSchemeItemCollection()
	{
		if (childClassSchemeClassSchemeItemCollection==null || childClassSchemeClassSchemeItemCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as child, gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as parent  where child in elements(parent.childClassSchemeClassSchemeItemCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem");				 
				childClassSchemeClassSchemeItemCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassSchemeClassSchemeItem.class.getName());
				log.error("ClassSchemeClassSchemeItem:getChildClassSchemeClassSchemeItemCollection throws exception ... ...",ex);
			}
		}	
		return childClassSchemeClassSchemeItemCollection;
	}
	
	public void setChildClassSchemeClassSchemeItemCollection(java.util.Collection childClassSchemeClassSchemeItemCollection)
	{
		this.childClassSchemeClassSchemeItemCollection = childClassSchemeClassSchemeItemCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection designationClassSchemeItemCollection = new java.util.HashSet();
	public java.util.Collection getDesignationClassSchemeItemCollection()
	{
		if (designationClassSchemeItemCollection==null || designationClassSchemeItemCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DesignationClassSchemeItem as child, gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as parent  where child in elements(parent.designationClassSchemeItemCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DesignationClassSchemeItem");				 
				designationClassSchemeItemCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassSchemeClassSchemeItem.class.getName());
				log.error("ClassSchemeClassSchemeItem:getDesignationClassSchemeItemCollection throws exception ... ...",ex);
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
		if(obj instanceof ClassSchemeClassSchemeItem) 
		{
			ClassSchemeClassSchemeItem c =(ClassSchemeClassSchemeItem)obj; 			 
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