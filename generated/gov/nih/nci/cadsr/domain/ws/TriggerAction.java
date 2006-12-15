

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class TriggerAction 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String action;
	   public  java.lang.String getAction(){
	      return action;
	   }
	   
	   public void setAction( java.lang.String action){
	      this.action = action;
	   }
	
	   
	   public java.lang.String createdBy;
	   public  java.lang.String getCreatedBy(){
	      return createdBy;
	   }
	   
	   public void setCreatedBy( java.lang.String createdBy){
	      this.createdBy = createdBy;
	   }
	
	   
	   public java.lang.String criterionValue;
	   public  java.lang.String getCriterionValue(){
	      return criterionValue;
	   }
	   
	   public void setCriterionValue( java.lang.String criterionValue){
	      this.criterionValue = criterionValue;
	   }
	
	   
	   public java.util.Date dateCreated;
	   public  java.util.Date getDateCreated(){
	      return dateCreated;
	   }
	   
	   public void setDateCreated( java.util.Date dateCreated){
	      this.dateCreated = dateCreated;
	   }
	
	   
	   public java.util.Date dateModified;
	   public  java.util.Date getDateModified(){
	      return dateModified;
	   }
	   
	   public void setDateModified( java.util.Date dateModified){
	      this.dateModified = dateModified;
	   }
	
	   
	   public java.lang.String forcedValue;
	   public  java.lang.String getForcedValue(){
	      return forcedValue;
	   }
	   
	   public void setForcedValue( java.lang.String forcedValue){
	      this.forcedValue = forcedValue;
	   }
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String instruction;
	   public  java.lang.String getInstruction(){
	      return instruction;
	   }
	   
	   public void setInstruction( java.lang.String instruction){
	      this.instruction = instruction;
	   }
	
	   
	   public java.lang.String modifiedBy;
	   public  java.lang.String getModifiedBy(){
	      return modifiedBy;
	   }
	   
	   public void setModifiedBy( java.lang.String modifiedBy){
	      this.modifiedBy = modifiedBy;
	   }
	
	   
	   public java.lang.String triggerRelationship;
	   public  java.lang.String getTriggerRelationship(){
	      return triggerRelationship;
	   }
	   
	   public void setTriggerRelationship( java.lang.String triggerRelationship){
	      this.triggerRelationship = triggerRelationship;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection protocolCollection = new java.util.HashSet();
			public java.util.Collection getProtocolCollection(){
	              return protocolCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setProtocolCollection(java.util.Collection protocolCollection){
	   		this.protocolCollection = protocolCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection administeredComponentClassSchemeItemCollection = new java.util.HashSet();
			public java.util.Collection getAdministeredComponentClassSchemeItemCollection(){
	              return administeredComponentClassSchemeItemCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAdministeredComponentClassSchemeItemCollection(java.util.Collection administeredComponentClassSchemeItemCollection){
	   		this.administeredComponentClassSchemeItemCollection = administeredComponentClassSchemeItemCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.QuestionCondition questionCondition;
			public gov.nih.nci.cadsr.domain.ws.QuestionCondition getQuestionCondition(){
			  return questionCondition;
                        }
		   
	      
	               
	   
	   
	   
	   public void setQuestionCondition(gov.nih.nci.cadsr.domain.ws.QuestionCondition questionCondition){
		this.questionCondition = questionCondition;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.FormElement targetFormElement;
			public gov.nih.nci.cadsr.domain.ws.FormElement getTargetFormElement(){
			  return targetFormElement;
                        }
		   
	      
	               
	   
	   
	   
	   public void setTargetFormElement(gov.nih.nci.cadsr.domain.ws.FormElement targetFormElement){
		this.targetFormElement = targetFormElement;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.QuestionCondition enforcedCondition;
			public gov.nih.nci.cadsr.domain.ws.QuestionCondition getEnforcedCondition(){
			  return enforcedCondition;
                        }
		   
	      
	               
	   
	   
	   
	   public void setEnforcedCondition(gov.nih.nci.cadsr.domain.ws.QuestionCondition enforcedCondition){
		this.enforcedCondition = enforcedCondition;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.FormElement sourceFormElement;
			public gov.nih.nci.cadsr.domain.ws.FormElement getSourceFormElement(){
			  return sourceFormElement;
                        }
		   
	      
	               
	   
	   
	   
	   public void setSourceFormElement(gov.nih.nci.cadsr.domain.ws.FormElement sourceFormElement){
		this.sourceFormElement = sourceFormElement;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof TriggerAction) {
				TriggerAction c =(TriggerAction)obj; 			 
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
