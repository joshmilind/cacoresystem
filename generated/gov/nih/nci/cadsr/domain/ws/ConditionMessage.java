

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ConditionMessage 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String message;
	   public  java.lang.String getMessage(){
	      return message;
	   }
	   
	   public void setMessage( java.lang.String message){
	      this.message = message;
	   }
	
	   
	   public java.lang.String messageType;
	   public  java.lang.String getMessageType(){
	      return messageType;
	   }
	   
	   public void setMessageType( java.lang.String messageType){
	      this.messageType = messageType;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.QuestionCondition questionCondition;
			public gov.nih.nci.cadsr.domain.ws.QuestionCondition getQuestionCondition(){
			  return questionCondition;
                        }
		   
	      
	               
	   
	   
	   
	   public void setQuestionCondition(gov.nih.nci.cadsr.domain.ws.QuestionCondition questionCondition){
		this.questionCondition = questionCondition;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ConditionMessage) {
				ConditionMessage c =(ConditionMessage)obj; 			 
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
