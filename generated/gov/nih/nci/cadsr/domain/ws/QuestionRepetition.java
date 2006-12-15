

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class QuestionRepetition 
    extends gov.nih.nci.cadsr.domain.ws.FormElement
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String defaultValue;
	   public  java.lang.String getDefaultValue(){
	      return defaultValue;
	   }
	   
	   public void setDefaultValue( java.lang.String defaultValue){
	      this.defaultValue = defaultValue;
	   }
	
	   
	   public java.lang.String isEditable;
	   public  java.lang.String getIsEditable(){
	      return isEditable;
	   }
	   
	   public void setIsEditable( java.lang.String isEditable){
	      this.isEditable = isEditable;
	   }
	
	   
	   public java.lang.Integer repeatSequenceNumber;
	   public  java.lang.Integer getRepeatSequenceNumber(){
	      return repeatSequenceNumber;
	   }
	   
	   public void setRepeatSequenceNumber( java.lang.Integer repeatSequenceNumber){
	      this.repeatSequenceNumber = repeatSequenceNumber;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ValidValue defaultValidValue;
			public gov.nih.nci.cadsr.domain.ws.ValidValue getDefaultValidValue(){
			  return defaultValidValue;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDefaultValidValue(gov.nih.nci.cadsr.domain.ws.ValidValue defaultValidValue){
		this.defaultValidValue = defaultValidValue;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof QuestionRepetition) {
				QuestionRepetition c =(QuestionRepetition)obj; 			 
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
