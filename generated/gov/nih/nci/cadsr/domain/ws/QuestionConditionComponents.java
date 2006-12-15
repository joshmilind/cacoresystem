

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class QuestionConditionComponents 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String constantValue;
	   public  java.lang.String getConstantValue(){
	      return constantValue;
	   }
	   
	   public void setConstantValue( java.lang.String constantValue){
	      this.constantValue = constantValue;
	   }
	
	   
	   public java.lang.Integer displayOrder;
	   public  java.lang.Integer getDisplayOrder(){
	      return displayOrder;
	   }
	   
	   public void setDisplayOrder( java.lang.Integer displayOrder){
	      this.displayOrder = displayOrder;
	   }
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String logicalOperand;
	   public  java.lang.String getLogicalOperand(){
	      return logicalOperand;
	   }
	   
	   public void setLogicalOperand( java.lang.String logicalOperand){
	      this.logicalOperand = logicalOperand;
	   }
	
	   
	   public java.lang.String operand;
	   public  java.lang.String getOperand(){
	      return operand;
	   }
	   
	   public void setOperand( java.lang.String operand){
	      this.operand = operand;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Question question;
			public gov.nih.nci.cadsr.domain.ws.Question getQuestion(){
			  return question;
                        }
		   
	      
	               
	   
	   
	   
	   public void setQuestion(gov.nih.nci.cadsr.domain.ws.Question question){
		this.question = question;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.QuestionCondition questionCondition;
			public gov.nih.nci.cadsr.domain.ws.QuestionCondition getQuestionCondition(){
			  return questionCondition;
                        }
		   
	      
	               
	   
	   
	   
	   public void setQuestionCondition(gov.nih.nci.cadsr.domain.ws.QuestionCondition questionCondition){
		this.questionCondition = questionCondition;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.QuestionCondition parentQuestionCondition;
			public gov.nih.nci.cadsr.domain.ws.QuestionCondition getParentQuestionCondition(){
			  return parentQuestionCondition;
                        }
		   
	      
	               
	   
	   
	   
	   public void setParentQuestionCondition(gov.nih.nci.cadsr.domain.ws.QuestionCondition parentQuestionCondition){
		this.parentQuestionCondition = parentQuestionCondition;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Function function;
			public gov.nih.nci.cadsr.domain.ws.Function getFunction(){
			  return function;
                        }
		   
	      
	               
	   
	   
	   
	   public void setFunction(gov.nih.nci.cadsr.domain.ws.Function function){
		this.function = function;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ValidValue validValue;
			public gov.nih.nci.cadsr.domain.ws.ValidValue getValidValue(){
			  return validValue;
                        }
		   
	      
	               
	   
	   
	   
	   public void setValidValue(gov.nih.nci.cadsr.domain.ws.ValidValue validValue){
		this.validValue = validValue;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof QuestionConditionComponents) {
				QuestionConditionComponents c =(QuestionConditionComponents)obj; 			 
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
