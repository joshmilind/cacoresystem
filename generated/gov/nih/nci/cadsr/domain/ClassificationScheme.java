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
   * The descriptive information for an arrangement or division of objects into groups based on characteristics, 
   * which the objects have in common. e.g., origin, composition, structure, application, function, 
   * etc. Adds information not easily included in defini 
   * 
   */

public  class ClassificationScheme  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String labelTypeFlag;
	public java.lang.String getLabelTypeFlag()
	{
		return labelTypeFlag;
	}
	public void setLabelTypeFlag(java.lang.String labelTypeFlag)
	{
		this.labelTypeFlag = labelTypeFlag;
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
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem as child, gov.nih.nci.cadsr.domain.ClassificationScheme as parent  where child in elements(parent.classSchemeClassSchemeItemCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem");				 
				classSchemeClassSchemeItemCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassificationScheme.class.getName());
				log.error("ClassificationScheme:getClassSchemeClassSchemeItemCollection throws exception ... ...",ex);
			}
		}	
		return classSchemeClassSchemeItemCollection;
	}
	
	public void setClassSchemeClassSchemeItemCollection(java.util.Collection classSchemeClassSchemeItemCollection)
	{
		this.classSchemeClassSchemeItemCollection = classSchemeClassSchemeItemCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection parentClassificationSchemeRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getParentClassificationSchemeRelationshipCollection()
	{
		if (parentClassificationSchemeRelationshipCollection==null || parentClassificationSchemeRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationSchemeRelationship as child, gov.nih.nci.cadsr.domain.ClassificationScheme as parent  where child in elements(parent.parentClassificationSchemeRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationSchemeRelationship");				 
				parentClassificationSchemeRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassificationScheme.class.getName());
				log.error("ClassificationScheme:getParentClassificationSchemeRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return parentClassificationSchemeRelationshipCollection;
	}
	
	public void setParentClassificationSchemeRelationshipCollection(java.util.Collection parentClassificationSchemeRelationshipCollection)
	{
		this.parentClassificationSchemeRelationshipCollection = parentClassificationSchemeRelationshipCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection childClassificationScheme = new java.util.HashSet();
	public java.util.Collection getChildClassificationScheme()
	{
		if (childClassificationScheme==null || childClassificationScheme.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationScheme as child, gov.nih.nci.cadsr.domain.ClassificationScheme as parent  where child in elements(parent.childClassificationScheme) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationScheme");				 
				childClassificationScheme = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassificationScheme.class.getName());
				log.error("ClassificationScheme:getChildClassificationScheme throws exception ... ...",ex);
			}
		}	
		return childClassificationScheme;
	}
	
	public void setChildClassificationScheme(java.util.Collection childClassificationScheme)
	{
		this.childClassificationScheme = childClassificationScheme;
	}	
		
	
	
	
		
		
	private java.util.Collection childClassificationSchemeRelationshipCollection = new java.util.HashSet();
	public java.util.Collection getChildClassificationSchemeRelationshipCollection()
	{
		if (childClassificationSchemeRelationshipCollection==null || childClassificationSchemeRelationshipCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationSchemeRelationship as child, gov.nih.nci.cadsr.domain.ClassificationScheme as parent  where child in elements(parent.childClassificationSchemeRelationshipCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationSchemeRelationship");				 
				childClassificationSchemeRelationshipCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClassificationScheme.class.getName());
				log.error("ClassificationScheme:getChildClassificationSchemeRelationshipCollection throws exception ... ...",ex);
			}
		}	
		return childClassificationSchemeRelationshipCollection;
	}
	
	public void setChildClassificationSchemeRelationshipCollection(java.util.Collection childClassificationSchemeRelationshipCollection)
	{
		this.childClassificationSchemeRelationshipCollection = childClassificationSchemeRelationshipCollection;
	}	
		
	
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule;
	public gov.nih.nci.cadsr.domain.ConceptDerivationRule getConceptDerivationRule()
	{
			
		if(conceptDerivationRule==null ||  conceptDerivationRule.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ConceptDerivationRule as child where child.id in (select parent.conceptDerivationRule.id from gov.nih.nci.cadsr.domain.ClassificationScheme as parent where parent.id="+idString+")";
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
				Logger log = Logger.getLogger(ClassificationScheme.class.getName());
				log.error("ClassificationScheme:getConceptDerivationRule throws exception ... ...",ex);
			}
		}
		return conceptDerivationRule;	
					
	}

	public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ConceptDerivationRule conceptDerivationRule)
	{
		this.conceptDerivationRule = conceptDerivationRule;
	}
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ClassificationScheme parentClassificationScheme;
	public gov.nih.nci.cadsr.domain.ClassificationScheme getParentClassificationScheme()
	{
			
		if(parentClassificationScheme==null ||  parentClassificationScheme.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ClassificationScheme as child where child.id in (select parent.parentClassificationScheme.id from gov.nih.nci.cadsr.domain.ClassificationScheme as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ClassificationScheme");				 
				if (resultList!=null && resultList.size()>0) 
					parentClassificationScheme = (gov.nih.nci.cadsr.domain.ClassificationScheme)resultList.get(0);
				else
					parentClassificationScheme = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ClassificationScheme.class.getName());
				log.error("ClassificationScheme:getParentClassificationScheme throws exception ... ...",ex);
			}
		}
		return parentClassificationScheme;	
					
	}

	public void setParentClassificationScheme(gov.nih.nci.cadsr.domain.ClassificationScheme parentClassificationScheme)
	{
		this.parentClassificationScheme = parentClassificationScheme;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ClassificationScheme) 
		{
			ClassificationScheme c =(ClassificationScheme)obj; 			 
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