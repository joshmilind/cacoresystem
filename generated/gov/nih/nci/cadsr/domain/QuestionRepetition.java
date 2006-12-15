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
   * Information about the default valid values everytime the question repeats on a form 
   * 
   */

public  class QuestionRepetition  extends gov.nih.nci.cadsr.domain.FormElement 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String defaultValue;
	public java.lang.String getDefaultValue()
	{
		return defaultValue;
	}
	public void setDefaultValue(java.lang.String defaultValue)
	{
		this.defaultValue = defaultValue;
	}
	
		
	public java.lang.String isEditable;
	public java.lang.String getIsEditable()
	{
		return isEditable;
	}
	public void setIsEditable(java.lang.String isEditable)
	{
		this.isEditable = isEditable;
	}
	
		
	public java.lang.Integer repeatSequenceNumber;
	public java.lang.Integer getRepeatSequenceNumber()
	{
		return repeatSequenceNumber;
	}
	public void setRepeatSequenceNumber(java.lang.Integer repeatSequenceNumber)
	{
		this.repeatSequenceNumber = repeatSequenceNumber;
	}
	
	
		
	
	
	
		
		
	private gov.nih.nci.cadsr.domain.ValidValue defaultValidValue;
	public gov.nih.nci.cadsr.domain.ValidValue getDefaultValidValue()
	{
			
		if(defaultValidValue==null ||  defaultValidValue.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.ValidValue as child where child.id in (select parent.defaultValidValue.id from gov.nih.nci.cadsr.domain.QuestionRepetition as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.ValidValue");				 
				if (resultList!=null && resultList.size()>0) 
					defaultValidValue = (gov.nih.nci.cadsr.domain.ValidValue)resultList.get(0);
				else
					defaultValidValue = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(QuestionRepetition.class.getName());
				log.error("QuestionRepetition:getDefaultValidValue throws exception ... ...",ex);
			}
		}
		return defaultValidValue;	
					
	}

	public void setDefaultValidValue(gov.nih.nci.cadsr.domain.ValidValue defaultValidValue)
	{
		this.defaultValidValue = defaultValidValue;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof QuestionRepetition) 
		{
			QuestionRepetition c =(QuestionRepetition)obj; 			 
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