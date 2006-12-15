

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Module 
    extends gov.nih.nci.cadsr.domain.ws.FormElement
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.Integer displayOrder;
	   public  java.lang.Integer getDisplayOrder(){
	      return displayOrder;
	   }
	   
	   public void setDisplayOrder( java.lang.Integer displayOrder){
	      this.displayOrder = displayOrder;
	   }
	
	   
	   public java.lang.Integer maximumQuestionRepeat;
	   public  java.lang.Integer getMaximumQuestionRepeat(){
	      return maximumQuestionRepeat;
	   }
	   
	   public void setMaximumQuestionRepeat( java.lang.Integer maximumQuestionRepeat){
	      this.maximumQuestionRepeat = maximumQuestionRepeat;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Form form;
			public gov.nih.nci.cadsr.domain.ws.Form getForm(){
			  return form;
                        }
		   
	      
	               
	   
	   
	   
	   public void setForm(gov.nih.nci.cadsr.domain.ws.Form form){
		this.form = form;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection questionCollection = new java.util.HashSet();
			public java.util.Collection getQuestionCollection(){
	              return questionCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setQuestionCollection(java.util.Collection questionCollection){
	   		this.questionCollection = questionCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Module) {
				Module c =(Module)obj; 			 
				String thisId = getId();		
				
					if(thisId != null && thisId.equals(c.getId())) {
					   eq = true;
				    }		
				
			}
			return eq;
		}
		
		public int hashCode(){
			int h = 0;
			
			if(getId() != null) {
				h += getId().hashCode();
			}
			
			return h;
	}
	
	
}
