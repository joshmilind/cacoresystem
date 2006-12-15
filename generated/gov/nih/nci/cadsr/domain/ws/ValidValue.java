

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ValidValue 
    extends gov.nih.nci.cadsr.domain.ws.FormElement
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String description;
	   public  java.lang.String getDescription(){
	      return description;
	   }
	   
	   public void setDescription( java.lang.String description){
	      this.description = description;
	   }
	
	   
	   public java.lang.Integer displayOrder;
	   public  java.lang.Integer getDisplayOrder(){
	      return displayOrder;
	   }
	   
	   public void setDisplayOrder( java.lang.Integer displayOrder){
	      this.displayOrder = displayOrder;
	   }
	
	   
	   private java.lang.String meaningText;
	   public  java.lang.String getMeaningText(){
	      return meaningText;
	   }
	   
	   public void setMeaningText( java.lang.String meaningText){
	      this.meaningText = meaningText;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Question question;
			public gov.nih.nci.cadsr.domain.ws.Question getQuestion(){
			  return question;
                        }
		   
	      
	               
	   
	   
	   
	   public void setQuestion(gov.nih.nci.cadsr.domain.ws.Question question){
		this.question = question;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ValueDomainPermissibleValue valueDomainPermissibleValue;
			public gov.nih.nci.cadsr.domain.ws.ValueDomainPermissibleValue getValueDomainPermissibleValue(){
			  return valueDomainPermissibleValue;
                        }
		   
	      
	               
	   
	   
	   
	   public void setValueDomainPermissibleValue(gov.nih.nci.cadsr.domain.ws.ValueDomainPermissibleValue valueDomainPermissibleValue){
		this.valueDomainPermissibleValue = valueDomainPermissibleValue;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection conditionComponent = new java.util.HashSet();
			public java.util.Collection getConditionComponent(){
	              return conditionComponent;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setConditionComponent(java.util.Collection conditionComponent){
	   		this.conditionComponent = conditionComponent;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ValidValue) {
				ValidValue c =(ValidValue)obj; 			 
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
