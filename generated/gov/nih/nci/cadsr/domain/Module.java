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
   * A collection of data elements, or Common Data Elements, logically grouped on a case report form. 
   * 
   */

public  class Module  extends gov.nih.nci.cadsr.domain.FormElement 	implements java.io.Serializable 
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
	
		
	public java.lang.Integer maximumQuestionRepeat;
	public java.lang.Integer getMaximumQuestionRepeat()
	{
		return maximumQuestionRepeat;
	}
	public void setMaximumQuestionRepeat(java.lang.Integer maximumQuestionRepeat)
	{
		this.maximumQuestionRepeat = maximumQuestionRepeat;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.Form form;
	public gov.nih.nci.cadsr.domain.Form getForm()
	{
			
		if(form==null ||  form.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Form as child where child.id in (select parent.form.id from gov.nih.nci.cadsr.domain.Module as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Form");				 
				if (resultList!=null && resultList.size()>0) 
					form = (gov.nih.nci.cadsr.domain.Form)resultList.get(0);
				else
					form = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Module.class.getName());
				log.error("Module:getForm throws exception ... ...",ex);
			}
		}
		return form;	
					
	}

	public void setForm(gov.nih.nci.cadsr.domain.Form form)
	{
		this.form = form;
	}
		
	
	
	
		
		
	private java.util.Collection questionCollection = new java.util.HashSet();
	public java.util.Collection getQuestionCollection()
	{
		if (questionCollection==null || questionCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Question as child, gov.nih.nci.cadsr.domain.Module as parent  where child in elements(parent.questionCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Question");				 
				questionCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Module.class.getName());
				log.error("Module:getQuestionCollection throws exception ... ...",ex);
			}
		}	
		return questionCollection;
	}
	
	public void setQuestionCollection(java.util.Collection questionCollection)
	{
		this.questionCollection = questionCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Module) 
		{
			Module c =(Module)obj; 			 
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