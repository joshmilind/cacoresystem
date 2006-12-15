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
   * Instruction for a Form, Module, Question or Valid Value on a Form
   */

public  class Instruction  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String type;
	public java.lang.String getType()
	{
		return type;
	}
	public void setType(java.lang.String type)
	{
		this.type = type;
	}
	
	
		
		
	private gov.nih.nci.cadsr.domain.FormElement formElement;
	public gov.nih.nci.cadsr.domain.FormElement getFormElement()
	{
			
		if(formElement==null ||  formElement.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.FormElement as child where child.id in (select parent.formElement.id from gov.nih.nci.cadsr.domain.Instruction as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.FormElement");				 
				if (resultList!=null && resultList.size()>0) 
					formElement = (gov.nih.nci.cadsr.domain.FormElement)resultList.get(0);
				else
					formElement = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Instruction.class.getName());
				log.error("Instruction:getFormElement throws exception ... ...",ex);
			}
		}
		return formElement;	
					
	}

	public void setFormElement(gov.nih.nci.cadsr.domain.FormElement formElement)
	{
		this.formElement = formElement;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Instruction) 
		{
			Instruction c =(Instruction)obj; 			 
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