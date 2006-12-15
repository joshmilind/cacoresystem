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
   * A description of the affiliation between two occurrences of Object  Classes
   */

public  class ObjectClassRelationship  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.Integer dimensionality;
	public java.lang.Integer getDimensionality()
	{
		return dimensionality;
	}
	public void setDimensionality(java.lang.Integer dimensionality)
	{
		this.dimensionality = dimensionality;
	}
	
		
	public java.lang.String direction;
	public java.lang.String getDirection()
	{
		return direction;
	}
	public void setDirection(java.lang.String direction)
	{
		this.direction = direction;
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
	
		
	public java.lang.String isArray;
	public java.lang.String getIsArray()
	{
		return isArray;
	}
	public void setIsArray(java.lang.String isArray)
	{
		this.isArray = isArray;
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
	
		
	public java.lang.Integer sourceHighMultiplicity;
	public java.lang.Integer getSourceHighMultiplicity()
	{
		return sourceHighMultiplicity;
	}
	public void setSourceHighMultiplicity(java.lang.Integer sourceHighMultiplicity)
	{
		this.sourceHighMultiplicity = sourceHighMultiplicity;
	}
	
		
	public java.lang.Integer sourceLowMultiplicity;
	public java.lang.Integer getSourceLowMultiplicity()
	{
		return sourceLowMultiplicity;
	}
	public void setSourceLowMultiplicity(java.lang.Integer sourceLowMultiplicity)
	{
		this.sourceLowMultiplicity = sourceLowMultiplicity;
	}
	
		
	public java.lang.String sourceRole;
	public java.lang.String getSourceRole()
	{
		return sourceRole;
	}
	public void setSourceRole(java.lang.String sourceRole)
	{
		this.sourceRole = sourceRole;
	}
	
		
	public java.lang.Integer targetHighMultiplicity;
	public java.lang.Integer getTargetHighMultiplicity()
	{
		return targetHighMultiplicity;
	}
	public void setTargetHighMultiplicity(java.lang.Integer targetHighMultiplicity)
	{
		this.targetHighMultiplicity = targetHighMultiplicity;
	}
	
		
	public java.lang.Integer targetLowMultiplicity;
	public java.lang.Integer getTargetLowMultiplicity()
	{
		return targetLowMultiplicity;
	}
	public void setTargetLowMultiplicity(java.lang.Integer targetLowMultiplicity)
	{
		this.targetLowMultiplicity = targetLowMultiplicity;
	}
	
		
	public java.lang.String targetRole;
	public java.lang.String getTargetRole()
	{
		return targetRole;
	}
	public void setTargetRole(java.lang.String targetRole)
	{
		this.targetRole = targetRole;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule targetConceptDerivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getTargetConceptDerivationRule()
	{
			
		if(targetConceptDerivationRule==null ||  targetConceptDerivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.targetConceptDerivationRule.id from gov.nih.nci.cadsr.domain.ObjectClassRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptDerivationRule");				 
				if (resultList!=null && resultList.size()>0) 
					targetConceptDerivationRule = (gov.nih.nci.cadsr.domain.ConceptDerivationRule)resultList.get(0);
				else
					targetConceptDerivationRule = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ObjectClassRelationship.class.getName());
				log.error("ObjectClassRelationship:getTargetConceptDerivationRule throws exception ... ...",ex);
			}
		}
		return targetConceptDerivationRule;	
					
	}

	public void setTargetConceptDerivationRule(gov.nih.nci.cadsr.domain.ConceptDerivationRule targetConceptDerivationRule)
	{
		this.targetConceptDerivationRule = targetConceptDerivationRule;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem targetObjectClassClassification;
	public gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem getTargetObjectClassClassification()
	{
			
		if(targetObjectClassClassification==null ||  targetObjectClassClassification.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem as child where child.id in (select parent.targetObjectClassClassification.id from gov.nih.nci.cadsr.domain.ObjectClassRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem");				 
				if (resultList!=null && resultList.size()>0) 
					targetObjectClassClassification = (gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem)resultList.get(0);
				else
					targetObjectClassClassification = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ObjectClassRelationship.class.getName());
				log.error("ObjectClassRelationship:getTargetObjectClassClassification throws exception ... ...",ex);
			}
		}
		return targetObjectClassClassification;	
					
	}

	public void setTargetObjectClassClassification(gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem targetObjectClassClassification)
	{
		this.targetObjectClassClassification = targetObjectClassClassification;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ObjectClass targetObjectClass;
	public gov.nih.nci.cadsr.domain.ObjectClass getTargetObjectClass()
	{
			
		if(targetObjectClass==null ||  targetObjectClass.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClass as child where child.id in (select parent.targetObjectClass.id from gov.nih.nci.cadsr.domain.ObjectClassRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClass");				 
				if (resultList!=null && resultList.size()>0) 
					targetObjectClass = (gov.nih.nci.cadsr.domain.ObjectClass)resultList.get(0);
				else
					targetObjectClass = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ObjectClassRelationship.class.getName());
				log.error("ObjectClassRelationship:getTargetObjectClass throws exception ... ...",ex);
			}
		}
		return targetObjectClass;	
					
	}

	public void setTargetObjectClass(gov.nih.nci.cadsr.domain.ObjectClass targetObjectClass)
	{
		this.targetObjectClass = targetObjectClass;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem sourceObjectClassClassification;
	public gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem getSourceObjectClassClassification()
	{
			
		if(sourceObjectClassClassification==null ||  sourceObjectClassClassification.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem as child where child.id in (select parent.sourceObjectClassClassification.id from gov.nih.nci.cadsr.domain.ObjectClassRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem");				 
				if (resultList!=null && resultList.size()>0) 
					sourceObjectClassClassification = (gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem)resultList.get(0);
				else
					sourceObjectClassClassification = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ObjectClassRelationship.class.getName());
				log.error("ObjectClassRelationship:getSourceObjectClassClassification throws exception ... ...",ex);
			}
		}
		return sourceObjectClassClassification;	
					
	}

	public void setSourceObjectClassClassification(gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem sourceObjectClassClassification)
	{
		this.sourceObjectClassClassification = sourceObjectClassClassification;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule sourceConceptDerivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getSourceConceptDerivationRule()
	{
			
		if(sourceConceptDerivationRule==null ||  sourceConceptDerivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.sourceConceptDerivationRule.id from gov.nih.nci.cadsr.domain.ObjectClassRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptDerivationRule");				 
				if (resultList!=null && resultList.size()>0) 
					sourceConceptDerivationRule = (gov.nih.nci.cadsr.domain.ConceptDerivationRule)resultList.get(0);
				else
					sourceConceptDerivationRule = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ObjectClassRelationship.class.getName());
				log.error("ObjectClassRelationship:getSourceConceptDerivationRule throws exception ... ...",ex);
			}
		}
		return sourceConceptDerivationRule;	
					
	}

	public void setSourceConceptDerivationRule(gov.nih.nci.cadsr.domain.ConceptDerivationRule sourceConceptDerivationRule)
	{
		this.sourceConceptDerivationRule = sourceConceptDerivationRule;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getConceptDerivationRule()
	{
			
		if(conceptDerivationRule==null ||  conceptDerivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.conceptDerivationRule.id from gov.nih.nci.cadsr.domain.ObjectClassRelationship as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(ObjectClassRelationship.class.getName());
				log.error("ObjectClassRelationship:getConceptDerivationRule throws exception ... ...",ex);
			}
		}
		return conceptDerivationRule;	
					
	}

	public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule)
	{
		this.conceptDerivationRule = conceptDerivationRule;
	}
		
	
	
	
		
	
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ObjectClass sourceObjectClass;
	public gov.nih.nci.cadsr.domain.ObjectClass getSourceObjectClass()
	{
			
		if(sourceObjectClass==null ||  sourceObjectClass.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ObjectClass as child where child.id in (select parent.sourceObjectClass.id from gov.nih.nci.cadsr.domain.ObjectClassRelationship as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ObjectClass");				 
				if (resultList!=null && resultList.size()>0) 
					sourceObjectClass = (gov.nih.nci.cadsr.domain.ObjectClass)resultList.get(0);
				else
					sourceObjectClass = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ObjectClassRelationship.class.getName());
				log.error("ObjectClassRelationship:getSourceObjectClass throws exception ... ...",ex);
			}
		}
		return sourceObjectClass;	
					
	}

	public void setSourceObjectClass(gov.nih.nci.cadsr.domain.ObjectClass sourceObjectClass)
	{
		this.sourceObjectClass = sourceObjectClass;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ObjectClassRelationship) 
		{
			ObjectClassRelationship c =(ObjectClassRelationship)obj; 			 
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