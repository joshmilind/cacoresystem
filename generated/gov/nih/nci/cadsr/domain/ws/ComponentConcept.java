

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ComponentConcept 
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
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String primaryFlag;
	   public  java.lang.String getPrimaryFlag(){
	      return primaryFlag;
	   }
	   
	   public void setPrimaryFlag( java.lang.String primaryFlag){
	      this.primaryFlag = primaryFlag;
	   }
	
	   
	   public java.lang.String value;
	   public  java.lang.String getValue(){
	      return value;
	   }
	   
	   public void setValue( java.lang.String value){
	      this.value = value;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Concept concept;
			public gov.nih.nci.cadsr.domain.ws.Concept getConcept(){
			  return concept;
                        }
		   
	      
	               
	   
	   
	   
	   public void setConcept(gov.nih.nci.cadsr.domain.ws.Concept concept){
		this.concept = concept;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ComponentLevel componentlevel;
			public gov.nih.nci.cadsr.domain.ws.ComponentLevel getComponentlevel(){
			  return componentlevel;
                        }
		   
	      
	               
	   
	   
	   
	   public void setComponentlevel(gov.nih.nci.cadsr.domain.ws.ComponentLevel componentlevel){
		this.componentlevel = componentlevel;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule derivationRule;
			public gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule getDerivationRule(){
			  return derivationRule;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDerivationRule(gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule derivationRule){
		this.derivationRule = derivationRule;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ComponentConcept) {
				ComponentConcept c =(ComponentConcept)obj; 			 
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
