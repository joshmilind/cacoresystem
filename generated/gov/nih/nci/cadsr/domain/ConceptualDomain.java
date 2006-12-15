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
   * The set of all possible Valid Value meanings of a Data Element Concept expressed without representation. 
   * 
   */

public  class ConceptualDomain  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String dimensionality;
	public java.lang.String getDimensionality()
	{
		return dimensionality;
	}
	public void setDimensionality(java.lang.String dimensionality)
	{
		this.dimensionality = dimensionality;
	}
	
	
		
		
	private java.util.Collection valueMeaningCollection = new java.util.HashSet();
	public java.util.Collection getValueMeaningCollection()
	{
		if (valueMeaningCollection==null || valueMeaningCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueMeaning as child, gov.nih.nci.cadsr.domain.ConceptualDomain as parent  where child in elements(parent.valueMeaningCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueMeaning");				 
				valueMeaningCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptualDomain.class.getName());
				log.error("ConceptualDomain:getValueMeaningCollection throws exception ... ...",ex);
			}
		}	
		return valueMeaningCollection;
	}
	
	public void setValueMeaningCollection(java.util.Collection valueMeaningCollection)
	{
		this.valueMeaningCollection = valueMeaningCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection valueDomainCollection = new java.util.HashSet();
	public java.util.Collection getValueDomainCollection()
	{
		if (valueDomainCollection==null || valueDomainCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomain as child, gov.nih.nci.cadsr.domain.ConceptualDomain as parent  where child in elements(parent.valueDomainCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValueDomain");				 
				valueDomainCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptualDomain.class.getName());
				log.error("ConceptualDomain:getValueDomainCollection throws exception ... ...",ex);
			}
		}	
		return valueDomainCollection;
	}
	
	public void setValueDomainCollection(java.util.Collection valueDomainCollection)
	{
		this.valueDomainCollection = valueDomainCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection dataElementConceptCollection = new java.util.HashSet();
	public java.util.Collection getDataElementConceptCollection()
	{
		if (dataElementConceptCollection==null || dataElementConceptCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementConcept as child, gov.nih.nci.cadsr.domain.ConceptualDomain as parent  where child in elements(parent.dataElementConceptCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementConcept");				 
				dataElementConceptCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ConceptualDomain.class.getName());
				log.error("ConceptualDomain:getDataElementConceptCollection throws exception ... ...",ex);
			}
		}	
		return dataElementConceptCollection;
	}
	
	public void setDataElementConceptCollection(java.util.Collection dataElementConceptCollection)
	{
		this.dataElementConceptCollection = dataElementConceptCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getConceptDerivationRule()
	{
			
		if(conceptDerivationRule==null ||  conceptDerivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.conceptDerivationRule.id from gov.nih.nci.cadsr.domain.ConceptualDomain as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(ConceptualDomain.class.getName());
				log.error("ConceptualDomain:getConceptDerivationRule throws exception ... ...",ex);
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
		if(obj instanceof ConceptualDomain) 
		{
			ConceptualDomain c =(ConceptualDomain)obj; 			 
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