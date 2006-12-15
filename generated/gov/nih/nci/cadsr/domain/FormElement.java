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
   * The element of a Form
   */

public  class FormElement  extends gov.nih.nci.cadsr.domain.AdministeredComponent 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
	
		
	
	
	
		
		
	private java.util.Collection instruction = new java.util.HashSet();
	public java.util.Collection getInstruction()
	{
		if (instruction==null || instruction.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cadsr.domain.Instruction as child, gov.nih.nci.cadsr.domain.FormElement as parent  where child in elements(parent.instruction) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cadsr.domain.Instruction");				 
				instruction = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(FormElement.class.getName());
				log.error("FormElement:getInstruction throws exception ... ...",ex);
			}
		}	
		return instruction;
	}
	
	public void setInstruction(java.util.Collection instruction)
	{
		this.instruction = instruction;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof FormElement) 
		{
			FormElement c =(FormElement)obj; 			 
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