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
   * A set of permissible values for a data element.
   */

public  class ValueDomain  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String characterSetName;
	public java.lang.String getCharacterSetName()
	{
		return characterSetName;
	}
	public void setCharacterSetName(java.lang.String characterSetName)
	{
		this.characterSetName = characterSetName;
	}
	
		
	public java.lang.String datatypeAnnotation;
	public java.lang.String getDatatypeAnnotation()
	{
		return datatypeAnnotation;
	}
	public void setDatatypeAnnotation(java.lang.String datatypeAnnotation)
	{
		this.datatypeAnnotation = datatypeAnnotation;
	}
	
		
	public java.lang.String datatypeDescription;
	public java.lang.String getDatatypeDescription()
	{
		return datatypeDescription;
	}
	public void setDatatypeDescription(java.lang.String datatypeDescription)
	{
		this.datatypeDescription = datatypeDescription;
	}
	
		
	public java.lang.String datatypeIsCodegenCompatible;
	public java.lang.String getDatatypeIsCodegenCompatible()
	{
		return datatypeIsCodegenCompatible;
	}
	public void setDatatypeIsCodegenCompatible(java.lang.String datatypeIsCodegenCompatible)
	{
		this.datatypeIsCodegenCompatible = datatypeIsCodegenCompatible;
	}
	
		
	public java.lang.String datatypeName;
	public java.lang.String getDatatypeName()
	{
		return datatypeName;
	}
	public void setDatatypeName(java.lang.String datatypeName)
	{
		this.datatypeName = datatypeName;
	}
	
		
	public java.lang.String datatypeSchemeReference;
	public java.lang.String getDatatypeSchemeReference()
	{
		return datatypeSchemeReference;
	}
	public void setDatatypeSchemeReference(java.lang.String datatypeSchemeReference)
	{
		this.datatypeSchemeReference = datatypeSchemeReference;
	}
	
		
	public java.lang.Integer decimalPlace;
	public java.lang.Integer getDecimalPlace()
	{
		return decimalPlace;
	}
	public void setDecimalPlace(java.lang.Integer decimalPlace)
	{
		this.decimalPlace = decimalPlace;
	}
	
		
	public java.lang.String formatName;
	public java.lang.String getFormatName()
	{
		return formatName;
	}
	public void setFormatName(java.lang.String formatName)
	{
		this.formatName = formatName;
	}
	
		
	public java.lang.String highValueNumber;
	public java.lang.String getHighValueNumber()
	{
		return highValueNumber;
	}
	public void setHighValueNumber(java.lang.String highValueNumber)
	{
		this.highValueNumber = highValueNumber;
	}
	
		
	public java.lang.String lowValueNumber;
	public java.lang.String getLowValueNumber()
	{
		return lowValueNumber;
	}
	public void setLowValueNumber(java.lang.String lowValueNumber)
	{
		this.lowValueNumber = lowValueNumber;
	}
	
		
	public java.lang.Integer maximumLengthNumber;
	public java.lang.Integer getMaximumLengthNumber()
	{
		return maximumLengthNumber;
	}
	public void setMaximumLengthNumber(java.lang.Integer maximumLengthNumber)
	{
		this.maximumLengthNumber = maximumLengthNumber;
	}
	
		
	public java.lang.Integer minimumLengthNumber;
	public java.lang.Integer getMinimumLengthNumber()
	{
		return minimumLengthNumber;
	}
	public void setMinimumLengthNumber(java.lang.Integer minimumLengthNumber)
	{
		this.minimumLengthNumber = minimumLengthNumber;
	}
	
		
	public java.lang.String UOMName;
	public java.lang.String getUOMName()
	{
		return UOMName;
	}
	public void setUOMName(java.lang.String UOMName)
	{
		this.UOMName = UOMName;
	}
	
	
		
		
	private java.util.Collection parentValueDomainRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getParentValueDomainRelationshipCollection()
	{
		if (parentValueDomainRelationshipCollection==null || parentValueDomainRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomainRelationship as child, gov.nih.nci.cadsr.domain.ValueDomain as parent  where child in elements(parent.parentValueDomainRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomainRelationship");				 
				parentValueDomainRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ValueDomain.class.getName());
				log.error("ValueDomain:getParentValueDomainRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return parentValueDomainRelationshipCollection;
	}
	
	public void setParentValueDomainRelationshipCollection(java.util.Collection parentValueDomainRelationshipCollection)
	{
		this.parentValueDomainRelationshipCollection = parentValueDomainRelationshipCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection questionCollection = new java.util.HashSet();
	public java.util.Collection getQuestionCollection()
	{
		if (questionCollection==null || questionCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Question as child, gov.nih.nci.cadsr.domain.ValueDomain as parent  where child in elements(parent.questionCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Question");				 
				questionCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ValueDomain.class.getName());
				log.error("ValueDomain:getQuestionCollection throws exception ... ...",ex);
			}
		}	
		return questionCollection;
	}
	
	public void setQuestionCollection(java.util.Collection questionCollection)
	{
		this.questionCollection = questionCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptualDomain conceptualDomain;
	public gov.nih.nci.cadsr.domain.ConceptualDomain getConceptualDomain()
	{
			
		if(conceptualDomain==null ||  conceptualDomain.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptualDomain as child where child.id in (select parent.conceptualDomain.id from gov.nih.nci.cadsr.domain.ValueDomain as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ConceptualDomain");				 
				if (resultList!=null && resultList.size()>0) 
					conceptualDomain = (gov.nih.nci.cadsr.domain.ConceptualDomain)resultList.get(0);
				else
					conceptualDomain = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ValueDomain.class.getName());
				log.error("ValueDomain:getConceptualDomain throws exception ... ...",ex);
			}
		}
		return conceptualDomain;	
					
	}

	public void setConceptualDomain(gov.nih.nci.cadsr.domain.ConceptualDomain conceptualDomain)
	{
		this.conceptualDomain = conceptualDomain;
	}
		
	
	
	
		
		
	private java.util.Collection dataElementCollection = new java.util.HashSet();
	public java.util.Collection getDataElementCollection()
	{
		if (dataElementCollection==null || dataElementCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElement as child, gov.nih.nci.cadsr.domain.ValueDomain as parent  where child in elements(parent.dataElementCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElement");				 
				dataElementCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ValueDomain.class.getName());
				log.error("ValueDomain:getDataElementCollection throws exception ... ...",ex);
			}
		}	
		return dataElementCollection;
	}
	
	public void setDataElementCollection(java.util.Collection dataElementCollection)
	{
		this.dataElementCollection = dataElementCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection childValueDomainRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getChildValueDomainRelationshipCollection()
	{
		if (childValueDomainRelationshipCollection==null || childValueDomainRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomainRelationship as child, gov.nih.nci.cadsr.domain.ValueDomain as parent  where child in elements(parent.childValueDomainRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomainRelationship");				 
				childValueDomainRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ValueDomain.class.getName());
				log.error("ValueDomain:getChildValueDomainRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return childValueDomainRelationshipCollection;
	}
	
	public void setChildValueDomainRelationshipCollection(java.util.Collection childValueDomainRelationshipCollection)
	{
		this.childValueDomainRelationshipCollection = childValueDomainRelationshipCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.Representation represention;
	public gov.nih.nci.cadsr.domain.Representation getRepresention()
	{
			
		if(represention==null ||  represention.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Representation as child where child.id in (select parent.represention.id from gov.nih.nci.cadsr.domain.ValueDomain as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Representation");				 
				if (resultList!=null && resultList.size()>0) 
					represention = (gov.nih.nci.cadsr.domain.Representation)resultList.get(0);
				else
					represention = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ValueDomain.class.getName());
				log.error("ValueDomain:getRepresention throws exception ... ...",ex);
			}
		}
		return represention;	
					
	}

	public void setRepresention(gov.nih.nci.cadsr.domain.Representation represention)
	{
		this.represention = represention;
	}
		
	
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getConceptDerivationRule()
	{
			
		if(conceptDerivationRule==null ||  conceptDerivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.conceptDerivationRule.id from gov.nih.nci.cadsr.domain.ValueDomain as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(ValueDomain.class.getName());
				log.error("ValueDomain:getConceptDerivationRule throws exception ... ...",ex);
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
		if(obj instanceof ValueDomain) 
		{
			ValueDomain c =(ValueDomain)obj; 			 
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