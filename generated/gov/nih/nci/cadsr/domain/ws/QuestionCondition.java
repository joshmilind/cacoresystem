

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class QuestionCondition 
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
	

	
	   
	   
	   
	      
			private java.util.Collection question = new java.util.HashSet();
			public java.util.Collection getQuestion(){
	              return question;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setQuestion(java.util.Collection question){
	   		this.question = question;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection triggeredActionCollection = new java.util.HashSet();
			public java.util.Collection getTriggeredActionCollection(){
	              return triggeredActionCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTriggeredActionCollection(java.util.Collection triggeredActionCollection){
	   		this.triggeredActionCollection = triggeredActionCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection questionCondition = new java.util.HashSet();
			public java.util.Collection getQuestionCondition(){
	              return questionCondition;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setQuestionCondition(java.util.Collection questionCondition){
	   		this.questionCondition = questionCondition;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection conditionComponentCollection = new java.util.HashSet();
			public java.util.Collection getConditionComponentCollection(){
	              return conditionComponentCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setConditionComponentCollection(java.util.Collection conditionComponentCollection){
	   		this.conditionComponentCollection = conditionComponentCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection condtionMessage = new java.util.HashSet();
			public java.util.Collection getCondtionMessage(){
	              return condtionMessage;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setCondtionMessage(java.util.Collection condtionMessage){
	   		this.condtionMessage = condtionMessage;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection forcedConditionTriggeredActionCollection = new java.util.HashSet();
			public java.util.Collection getForcedConditionTriggeredActionCollection(){
	              return forcedConditionTriggeredActionCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setForcedConditionTriggeredActionCollection(java.util.Collection forcedConditionTriggeredActionCollection){
	   		this.forcedConditionTriggeredActionCollection = forcedConditionTriggeredActionCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof QuestionCondition) {
				QuestionCondition c =(QuestionCondition)obj; 			 
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
