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
   * The derivation rule between one or more concepts.
   */

public  class ConceptDerivationRule 	implements java.io.Serializable 
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
	
	
		
		
	private java.util.Collection classificationSchemeItemCollection = new java.util.HashSet();
	public java.util.Collection getClassificationSchemeItemCollection()
	{
		if (classificationSchemeItemCollection==null || classificationSchemeItemCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationSchemeItem as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.classificationSchemeItemCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationSchemeItem");				 
				classificationSchemeItemCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getClassificationSchemeItemCollection throws exception ... ...",ex);
			}
		}	
		return classificationSchemeItemCollection;
	}
	
	public void setClassificationSchemeItemCollection(java.util.Collection classificationSchemeItemCollection)
	{
		this.classificationSchemeItemCollection = classificationSchemeItemCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection objectClassRelationship = new java.util.HashSet();
	public java.util.Collection getObjectClassRelationship()
	{
		if (objectClassRelationship==null || objectClassRelationship.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClassRelationship as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.objectClassRelationship) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClassRelationship");				 
				objectClassRelationship = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getObjectClassRelationship throws exception ... ...",ex);
			}
		}	
		return objectClassRelationship;
	}
	
	public void setObjectClassRelationship(java.util.Collection objectClassRelationship)
	{
		this.objectClassRelationship = objectClassRelationship;
	}	
		
	
	
	
		
		
	private java.util.Collection valueDomainCollection = new java.util.HashSet();
	public java.util.Collection getValueDomainCollection()
	{
		if (valueDomainCollection==null || valueDomainCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomain as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.valueDomainCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomain");				 
				valueDomainCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getValueDomainCollection throws exception ... ...",ex);
			}
		}	
		return valueDomainCollection;
	}
	
	public void setValueDomainCollection(java.util.Collection valueDomainCollection)
	{
		this.valueDomainCollection = valueDomainCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection valueMeaningCollection = new java.util.HashSet();
	public java.util.Collection getValueMeaningCollection()
	{
		if (valueMeaningCollection==null || valueMeaningCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueMeaning as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.valueMeaningCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueMeaning");				 
				valueMeaningCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getValueMeaningCollection throws exception ... ...",ex);
			}
		}	
		return valueMeaningCollection;
	}
	
	public void setValueMeaningCollection(java.util.Collection valueMeaningCollection)
	{
		this.valueMeaningCollection = valueMeaningCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection sourceRoleConcept = new java.util.HashSet();
	public java.util.Collection getSourceRoleConcept()
	{
		if (sourceRoleConcept==null || sourceRoleConcept.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClassRelationship as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.sourceRoleConcept) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClassRelationship");				 
				sourceRoleConcept = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getSourceRoleConcept throws exception ... ...",ex);
			}
		}	
		return sourceRoleConcept;
	}
	
	public void setSourceRoleConcept(java.util.Collection sourceRoleConcept)
	{
		this.sourceRoleConcept = sourceRoleConcept;
	}	
		
	
	
	
		
	
	
	
		
		
	private java.util.Collection classificationSchemeCollection = new java.util.HashSet();
	public java.util.Collection getClassificationSchemeCollection()
	{
		if (classificationSchemeCollection==null || classificationSchemeCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationScheme as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.classificationSchemeCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationScheme");				 
				classificationSchemeCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getClassificationSchemeCollection throws exception ... ...",ex);
			}
		}	
		return classificationSchemeCollection;
	}
	
	public void setClassificationSchemeCollection(java.util.Collection classificationSchemeCollection)
	{
		this.classificationSchemeCollection = classificationSchemeCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection representationCollection = new java.util.HashSet();
	public java.util.Collection getRepresentationCollection()
	{
		if (representationCollection==null || representationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Representation as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.representationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Representation");				 
				representationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getRepresentationCollection throws exception ... ...",ex);
			}
		}	
		return representationCollection;
	}
	
	public void setRepresentationCollection(java.util.Collection representationCollection)
	{
		this.representationCollection = representationCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection targetRoleConcept = new java.util.HashSet();
	public java.util.Collection getTargetRoleConcept()
	{
		if (targetRoleConcept==null || targetRoleConcept.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClassRelationship as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.targetRoleConcept) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClassRelationship");				 
				targetRoleConcept = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getTargetRoleConcept throws exception ... ...",ex);
			}
		}	
		return targetRoleConcept;
	}
	
	public void setTargetRoleConcept(java.util.Collection targetRoleConcept)
	{
		this.targetRoleConcept = targetRoleConcept;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.DerivationType derivationType;
	public gov.nih.nci.cadsr.domain.DerivationType getDerivationType()
	{
			
		if(derivationType==null ||  derivationType.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DerivationType as child where child.id in (select parent.derivationType.id from gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DerivationType");				 
				if (resultList!=null && resultList.size()>0) 
					derivationType = (gov.nih.nci.cadsr.domain.DerivationType)resultList.get(0);
				else
					derivationType = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getDerivationType throws exception ... ...",ex);
			}
		}
		return derivationType;	
					
	}

	public void setDerivationType(gov.nih.nci.cadsr.domain.DerivationType derivationType)
	{
		this.derivationType = derivationType;
	}
		
	
	
	
		
		
	private java.util.Collection objectClassCollection = new java.util.HashSet();
	public java.util.Collection getObjectClassCollection()
	{
		if (objectClassCollection==null || objectClassCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClass as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.objectClassCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClass");				 
				objectClassCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getObjectClassCollection throws exception ... ...",ex);
			}
		}	
		return objectClassCollection;
	}
	
	public void setObjectClassCollection(java.util.Collection objectClassCollection)
	{
		this.objectClassCollection = objectClassCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection propertyCollection = new java.util.HashSet();
	public java.util.Collection getPropertyCollection()
	{
		if (propertyCollection==null || propertyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Property as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.propertyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Property");				 
				propertyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getPropertyCollection throws exception ... ...",ex);
			}
		}	
		return propertyCollection;
	}
	
	public void setPropertyCollection(java.util.Collection propertyCollection)
	{
		this.propertyCollection = propertyCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection componentConceptCollection = new java.util.HashSet();
	public java.util.Collection getComponentConceptCollection()
	{
		if (componentConceptCollection==null || componentConceptCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ComponentConcept as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.componentConceptCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ComponentConcept");				 
				componentConceptCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getComponentConceptCollection throws exception ... ...",ex);
			}
		}	
		return componentConceptCollection;
	}
	
	public void setComponentConceptCollection(java.util.Collection componentConceptCollection)
	{
		this.componentConceptCollection = componentConceptCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection conceptualDomainCollection = new java.util.HashSet();
	public java.util.Collection getConceptualDomainCollection()
	{
		if (conceptualDomainCollection==null || conceptualDomainCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptualDomain as child, gov.nih.nci.cadsr.domain.ConceptDerivationRule as parent  where child in elements(parent.conceptualDomainCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptualDomain");				 
				conceptualDomainCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptDerivationRule.class.getName());
				log.error("ConceptDerivationRule:getConceptualDomainCollection throws exception ... ...",ex);
			}
		}	
		return conceptualDomainCollection;
	}
	
	public void setConceptualDomainCollection(java.util.Collection conceptualDomainCollection)
	{
		this.conceptualDomainCollection = conceptualDomainCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ConceptDerivationRule) 
		{
			ConceptDerivationRule c =(ConceptDerivationRule)obj; 			 
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