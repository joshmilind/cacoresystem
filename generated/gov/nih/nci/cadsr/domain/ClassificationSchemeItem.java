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
   * A component of content in a Classification Scheme. This may be a node in a taxonomy or ontology or a 
   * term in a thesaurus, etc. 
   * 
   */

public  class ClassificationSchemeItem 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String comments;
	public java.lang.String getComments()
	{
		return comments;
	}
	public void setComments(java.lang.String comments)
	{
		this.comments = comments;
	}
	
		
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
	
		
	public java.lang.String modifiedBy;
	public java.lang.String getModifiedBy()
	{
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
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
	
		
	public java.lang.String type;
	public java.lang.String getType()
	{
		return type;
	}
	public void setType(java.lang.String type)
	{
		this.type = type;
	}
	
	
		
		
	private java.util.Collection classSchemeClassSchemeItemCollection = new java.util.HashSet();
	public java.util.Collection getClassSchemeClassSchemeItemCollection()
	{
		if (classSchemeClassSchemeItemCollection==null || classSchemeClassSchemeItemCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as child, gov.nih.nci.cadsr.domain.ClassificationSchemeItem as parent  where child in elements(parent.classSchemeClassSchemeItemCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem");				 
				classSchemeClassSchemeItemCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassificationSchemeItem.class.getName());
				log.error("ClassificationSchemeItem:getClassSchemeClassSchemeItemCollection throws exception ... ...",ex);
			}
		}	
		return classSchemeClassSchemeItemCollection;
	}
	
	public void setClassSchemeClassSchemeItemCollection(java.util.Collection classSchemeClassSchemeItemCollection)
	{
		this.classSchemeClassSchemeItemCollection = classSchemeClassSchemeItemCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection parentClassificationSchemeItemRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getParentClassificationSchemeItemRelationshipCollection()
	{
		if (parentClassificationSchemeItemRelationshipCollection==null || parentClassificationSchemeItemRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationSchemeItemRelationship as child, gov.nih.nci.cadsr.domain.ClassificationSchemeItem as parent  where child in elements(parent.parentClassificationSchemeItemRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationSchemeItemRelationship");				 
				parentClassificationSchemeItemRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassificationSchemeItem.class.getName());
				log.error("ClassificationSchemeItem:getParentClassificationSchemeItemRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return parentClassificationSchemeItemRelationshipCollection;
	}
	
	public void setParentClassificationSchemeItemRelationshipCollection(java.util.Collection parentClassificationSchemeItemRelationshipCollection)
	{
		this.parentClassificationSchemeItemRelationshipCollection = parentClassificationSchemeItemRelationshipCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection referenceDocumentCollection = new java.util.HashSet();
	public java.util.Collection getReferenceDocumentCollection()
	{
		if (referenceDocumentCollection==null || referenceDocumentCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ReferenceDocument as child, gov.nih.nci.cadsr.domain.ClassificationSchemeItem as parent  where child in elements(parent.referenceDocumentCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ReferenceDocument");				 
				referenceDocumentCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassificationSchemeItem.class.getName());
				log.error("ClassificationSchemeItem:getReferenceDocumentCollection throws exception ... ...",ex);
			}
		}	
		return referenceDocumentCollection;
	}
	
	public void setReferenceDocumentCollection(java.util.Collection referenceDocumentCollection)
	{
		this.referenceDocumentCollection = referenceDocumentCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection childClassificationSchemeItemRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getChildClassificationSchemeItemRelationshipCollection()
	{
		if (childClassificationSchemeItemRelationshipCollection==null || childClassificationSchemeItemRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationSchemeItemRelationship as child, gov.nih.nci.cadsr.domain.ClassificationSchemeItem as parent  where child in elements(parent.childClassificationSchemeItemRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationSchemeItemRelationship");				 
				childClassificationSchemeItemRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassificationSchemeItem.class.getName());
				log.error("ClassificationSchemeItem:getChildClassificationSchemeItemRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return childClassificationSchemeItemRelationshipCollection;
	}
	
	public void setChildClassificationSchemeItemRelationshipCollection(java.util.Collection childClassificationSchemeItemRelationshipCollection)
	{
		this.childClassificationSchemeItemRelationshipCollection = childClassificationSchemeItemRelationshipCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection administeredComponentContact = new java.util.HashSet();
	public java.util.Collection getAdministeredComponentContact()
	{
		if (administeredComponentContact==null || administeredComponentContact.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.AdministeredComponentContact as child, gov.nih.nci.cadsr.domain.ClassificationSchemeItem as parent  where child in elements(parent.administeredComponentContact) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.AdministeredComponentContact");				 
				administeredComponentContact = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassificationSchemeItem.class.getName());
				log.error("ClassificationSchemeItem:getAdministeredComponentContact throws exception ... ...",ex);
			}
		}	
		return administeredComponentContact;
	}
	
	public void setAdministeredComponentContact(java.util.Collection administeredComponentContact)
	{
		this.administeredComponentContact = administeredComponentContact;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getConceptDerivationRule()
	{
			
		if(conceptDerivationRule==null ||  conceptDerivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.conceptDerivationRule.id from gov.nih.nci.cadsr.domain.ClassificationSchemeItem as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptDerivationRule");				 
				if (resultList!=null && resultList.size()>0) 
					conceptDerivationRule = (gov.nih.nci.cadsr.domain.ConceptDerivationRule)resultList.get(0);
				else
					conceptDerivationRule = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ClassificationSchemeItem.class.getName());
				log.error("ClassificationSchemeItem:getConceptDerivationRule throws exception ... ...",ex);
			}
		}
		return conceptDerivationRule;	
					
	}

	public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule)
	{
		this.conceptDerivationRule = conceptDerivationRule;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ClassificationSchemeItem) 
		{
			ClassificationSchemeItem c =(ClassificationSchemeItem)obj; 			 
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