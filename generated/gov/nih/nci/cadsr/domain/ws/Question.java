

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Question 
    extends gov.nih.nci.cadsr.domain.ws.FormElement
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String defaultValidValueId;
	   public  java.lang.String getDefaultValidValueId(){
	      return defaultValidValueId;
	   }
	   
	   public void setDefaultValidValueId( java.lang.String defaultValidValueId){
	      this.defaultValidValueId = defaultValidValueId;
	   }
	
	   
	   public java.lang.String defaultValue;
	   public  java.lang.String getDefaultValue(){
	      return defaultValue;
	   }
	   
	   public void setDefaultValue( java.lang.String defaultValue){
	      this.defaultValue = defaultValue;
	   }
	
	   
	   public java.lang.Integer displayOrder;
	   public  java.lang.Integer getDisplayOrder(){
	      return displayOrder;
	   }
	   
	   public void setDisplayOrder( java.lang.Integer displayOrder){
	      this.displayOrder = displayOrder;
	   }
	
	   
	   public java.lang.String isEditable;
	   public  java.lang.String getIsEditable(){
	      return isEditable;
	   }
	   
	   public void setIsEditable( java.lang.String isEditable){
	      this.isEditable = isEditable;
	   }
	
	   
	   public java.lang.String isMandatory;
	   public  java.lang.String getIsMandatory(){
	      return isMandatory;
	   }
	   
	   public void setIsMandatory( java.lang.String isMandatory){
	      this.isMandatory = isMandatory;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.DataElement dataElement;
			public gov.nih.nci.cadsr.domain.ws.DataElement getDataElement(){
			  return dataElement;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDataElement(gov.nih.nci.cadsr.domain.ws.DataElement dataElement){
		this.dataElement = dataElement;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ValueDomain valueDomain;
			public gov.nih.nci.cadsr.domain.ws.ValueDomain getValueDomain(){
			  return valueDomain;
                        }
		   
	      
	               
	   
	   
	   
	   public void setValueDomain(gov.nih.nci.cadsr.domain.ws.ValueDomain valueDomain){
		this.valueDomain = valueDomain;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.QuestionCondition questionCondition;
			public gov.nih.nci.cadsr.domain.ws.QuestionCondition getQuestionCondition(){
			  return questionCondition;
                        }
		   
	      
	               
	   
	   
	   
	   public void setQuestionCondition(gov.nih.nci.cadsr.domain.ws.QuestionCondition questionCondition){
		this.questionCondition = questionCondition;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection validValueCollection = new java.util.HashSet();
			public java.util.Collection getValidValueCollection(){
	              return validValueCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setValidValueCollection(java.util.Collection validValueCollection){
	   		this.validValueCollection = validValueCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection questionComponentCollection = new java.util.HashSet();
			public java.util.Collection getQuestionComponentCollection(){
	              return questionComponentCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setQuestionComponentCollection(java.util.Collection questionComponentCollection){
	   		this.questionComponentCollection = questionComponentCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Module module;
			public gov.nih.nci.cadsr.domain.ws.Module getModule(){
			  return module;
                        }
		   
	      
	               
	   
	   
	   
	   public void setModule(gov.nih.nci.cadsr.domain.ws.Module module){
		this.module = module;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection questionRepetitionCollection = new java.util.HashSet();
			public java.util.Collection getQuestionRepetitionCollection(){
	              return questionRepetitionCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setQuestionRepetitionCollection(java.util.Collection questionRepetitionCollection){
	   		this.questionRepetitionCollection = questionRepetitionCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Question) {
				Question c =(Question)obj; 			 
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
