

package gov.nih.nci.cadsr.umlproject.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class SemanticMetadata 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String conceptCode;
	   public  java.lang.String getConceptCode(){
	      return conceptCode;
	   }
	   
	   public void setConceptCode( java.lang.String conceptCode){
	      this.conceptCode = conceptCode;
	   }
	
	   
	   public java.lang.String conceptDefinition;
	   public  java.lang.String getConceptDefinition(){
	      return conceptDefinition;
	   }
	   
	   public void setConceptDefinition( java.lang.String conceptDefinition){
	      this.conceptDefinition = conceptDefinition;
	   }
	
	   
	   public java.lang.String conceptName;
	   public  java.lang.String getConceptName(){
	      return conceptName;
	   }
	   
	   public void setConceptName( java.lang.String conceptName){
	      this.conceptName = conceptName;
	   }
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.Boolean isPrimaryConcept;
	   public  java.lang.Boolean getIsPrimaryConcept(){
	      return isPrimaryConcept;
	   }
	   
	   public void setIsPrimaryConcept( java.lang.Boolean isPrimaryConcept){
	      this.isPrimaryConcept = isPrimaryConcept;
	   }
	
	   
	   public java.lang.Integer order;
	   public  java.lang.Integer getOrder(){
	      return order;
	   }
	   
	   public void setOrder( java.lang.Integer order){
	      this.order = order;
	   }
	
	   
	   public java.lang.Integer orderLevel;
	   public  java.lang.Integer getOrderLevel(){
	      return orderLevel;
	   }
	   
	   public void setOrderLevel( java.lang.Integer orderLevel){
	      this.orderLevel = orderLevel;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Concept concept;
			public gov.nih.nci.cadsr.domain.ws.Concept getConcept(){
			  return concept;
                        }
		   
	      
	               
	   
	   
	   
	   public void setConcept(gov.nih.nci.cadsr.domain.ws.Concept concept){
		this.concept = concept;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof SemanticMetadata) {
				SemanticMetadata c =(SemanticMetadata)obj; 			 
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
