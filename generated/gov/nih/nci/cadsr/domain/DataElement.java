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
   * A unit of data for which the definition, identification, representation and permissible values 
   * are specified by means of a set of attributes. 
   * 
   */

public  class DataElement  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ValueDomain valueDomain;
	public gov.nih.nci.cadsr.domain.ValueDomain getValueDomain()
	{
			
		if(valueDomain==null ||  valueDomain.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValueDomain as child where child.id in (select parent.valueDomain.id from gov.nih.nci.cadsr.domain.DataElement as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(DataElement.class.getName());
				log.error("DataElement:getValueDomain throws exception ... ...",ex);
			}
		}
		return valueDomain;	
					
	}

	public void setValueDomain(gov.nih.nci.cadsr.domain.ValueDomain valueDomain)
	{
		this.valueDomain = valueDomain;
	}
		
	
	
	
		
		
	private java.util.Collection questionCollection = new java.util.HashSet();
	public java.util.Collection getQuestionCollection()
	{
		if (questionCollection==null || questionCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Question as child, gov.nih.nci.cadsr.domain.DataElement as parent  where child in elements(parent.questionCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Question");				 
				questionCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DataElement.class.getName());
				log.error("DataElement:getQuestionCollection throws exception ... ...",ex);
			}
		}	
		return questionCollection;
	}
	
	public void setQuestionCollection(java.util.Collection questionCollection)
	{
		this.questionCollection = questionCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection dataElementDerivationCollection = new java.util.HashSet();
	public java.util.Collection getDataElementDerivationCollection()
	{
		if (dataElementDerivationCollection==null || dataElementDerivationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementDerivation as child, gov.nih.nci.cadsr.domain.DataElement as parent  where child in elements(parent.dataElementDerivationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementDerivation");				 
				dataElementDerivationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DataElement.class.getName());
				log.error("DataElement:getDataElementDerivationCollection throws exception ... ...",ex);
			}
		}	
		return dataElementDerivationCollection;
	}
	
	public void setDataElementDerivationCollection(java.util.Collection dataElementDerivationCollection)
	{
		this.dataElementDerivationCollection = dataElementDerivationCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection parentDataElementRelationshipsCollection = new java.util.HashSet();
	public java.util.Collection getParentDataElementRelationshipsCollection()
	{
		if (parentDataElementRelationshipsCollection==null || parentDataElementRelationshipsCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementRelationship as child, gov.nih.nci.cadsr.domain.DataElement as parent  where child in elements(parent.parentDataElementRelationshipsCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementRelationship");				 
				parentDataElementRelationshipsCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DataElement.class.getName());
				log.error("DataElement:getParentDataElementRelationshipsCollection throws exception ... ...",ex);
			}
		}	
		return parentDataElementRelationshipsCollection;
	}
	
	public void setParentDataElementRelationshipsCollection(java.util.Collection parentDataElementRelationshipsCollection)
	{
		this.parentDataElementRelationshipsCollection = parentDataElementRelationshipsCollection;
	}	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.DataElementConcept dataElementConcept;
	public gov.nih.nci.cadsr.domain.DataElementConcept getDataElementConcept()
	{
			
		if(dataElementConcept==null ||  dataElementConcept.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementConcept as child where child.id in (select parent.dataElementConcept.id from gov.nih.nci.cadsr.domain.DataElement as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementConcept");				 
				if (resultList!=null && resultList.size()>0) 
					dataElementConcept = (gov.nih.nci.cadsr.domain.DataElementConcept)resultList.get(0);
				else
					dataElementConcept = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElement.class.getName());
				log.error("DataElement:getDataElementConcept throws exception ... ...",ex);
			}
		}
		return dataElementConcept;	
					
	}

	public void setDataElementConcept(gov.nih.nci.cadsr.domain.DataElementConcept dataElementConcept)
	{
		this.dataElementConcept = dataElementConcept;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.DerivedDataElement derivedDataElement;
	public gov.nih.nci.cadsr.domain.DerivedDataElement getDerivedDataElement()
	{
			
		if(derivedDataElement==null ||  derivedDataElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DerivedDataElement as child where child.id in (select parent.derivedDataElement.id from gov.nih.nci.cadsr.domain.DataElement as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DerivedDataElement");				 
				if (resultList!=null && resultList.size()>0) 
					derivedDataElement = (gov.nih.nci.cadsr.domain.DerivedDataElement)resultList.get(0);
				else
					derivedDataElement = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(DataElement.class.getName());
				log.error("DataElement:getDerivedDataElement throws exception ... ...",ex);
			}
		}
		return derivedDataElement;	
					
	}

	public void setDerivedDataElement(gov.nih.nci.cadsr.domain.DerivedDataElement derivedDataElement)
	{
		this.derivedDataElement = derivedDataElement;
	}
		
	
	
	
		
		
	private java.util.Collection childDataElementRelationshipsCollection = new java.util.HashSet();
	public java.util.Collection getChildDataElementRelationshipsCollection()
	{
		if (childDataElementRelationshipsCollection==null || childDataElementRelationshipsCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.DataElementRelationship as child, gov.nih.nci.cadsr.domain.DataElement as parent  where child in elements(parent.childDataElementRelationshipsCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.DataElementRelationship");				 
				childDataElementRelationshipsCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(DataElement.class.getName());
				log.error("DataElement:getChildDataElementRelationshipsCollection throws exception ... ...",ex);
			}
		}	
		return childDataElementRelationshipsCollection;
	}
	
	public void setChildDataElementRelationshipsCollection(java.util.Collection childDataElementRelationshipsCollection)
	{
		this.childDataElementRelationshipsCollection = childDataElementRelationshipsCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof DataElement) 
		{
			DataElement c =(DataElement)obj; 			 
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