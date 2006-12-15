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
   * The concept component(s) used for a concept derivation. A unit of knowledge created by a unique combination 
   * of characteristics. (ISO 1087) 
   * 
   */

public  class ComponentConcept 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
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
	
		
	public java.lang.String primaryFlag;
	public java.lang.String getPrimaryFlag()
	{
		return primaryFlag;
	}
	public void setPrimaryFlag(java.lang.String primaryFlag)
	{
		this.primaryFlag = primaryFlag;
	}
	
		
	public java.lang.String value;
	public java.lang.String getValue()
	{
		return value;
	}
	public void setValue(java.lang.String value)
	{
		this.value = value;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.Concept concept;
	public gov.nih.nci.cadsr.domain.Concept getConcept()
	{
			
		if(concept==null ||  concept.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Concept as child where child.id in (select parent.concept.id from gov.nih.nci.cadsr.domain.ComponentConcept as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Concept");				 
				if (resultList!=null && resultList.size()>0) 
					concept = (gov.nih.nci.cadsr.domain.Concept)resultList.get(0);
				else
					concept = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ComponentConcept.class.getName());
				log.error("ComponentConcept:getConcept throws exception ... ...",ex);
			}
		}
		return concept;	
					
	}

	public void setConcept(gov.nih.nci.cadsr.domain.Concept concept)
	{
		this.concept = concept;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ComponentLevel componentlevel;
	public gov.nih.nci.cadsr.domain.ComponentLevel getComponentlevel()
	{
			
		if(componentlevel==null ||  componentlevel.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ComponentLevel as child where child.id in (select parent.componentlevel.id from gov.nih.nci.cadsr.domain.ComponentConcept as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ComponentLevel");				 
				if (resultList!=null && resultList.size()>0) 
					componentlevel = (gov.nih.nci.cadsr.domain.ComponentLevel)resultList.get(0);
				else
					componentlevel = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ComponentConcept.class.getName());
				log.error("ComponentConcept:getComponentlevel throws exception ... ...",ex);
			}
		}
		return componentlevel;	
					
	}

	public void setComponentlevel(gov.nih.nci.cadsr.domain.ComponentLevel componentlevel)
	{
		this.componentlevel = componentlevel;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule derivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getDerivationRule()
	{
			
		if(derivationRule==null ||  derivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.derivationRule.id from gov.nih.nci.cadsr.domain.ComponentConcept as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptDerivationRule");				 
				if (resultList!=null && resultList.size()>0) 
					derivationRule = (gov.nih.nci.cadsr.domain.ConceptDerivationRule)resultList.get(0);
				else
					derivationRule = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ComponentConcept.class.getName());
				log.error("ComponentConcept:getDerivationRule throws exception ... ...",ex);
			}
		}
		return derivationRule;	
					
	}

	public void setDerivationRule(gov.nih.nci.cadsr.domain.ConceptDerivationRule derivationRule)
	{
		this.derivationRule = derivationRule;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ComponentConcept) 
		{
			ComponentConcept c =(ComponentConcept)obj; 			 
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