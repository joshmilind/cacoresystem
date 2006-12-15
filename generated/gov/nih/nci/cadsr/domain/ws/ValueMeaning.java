

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ValueMeaning 
    extends gov.nih.nci.cadsr.domain.ws.AdministeredComponent
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String comments;
	   public  java.lang.String getComments(){
	      return comments;
	   }
	   
	   public void setComments( java.lang.String comments){
	      this.comments = comments;
	   }
	
	   
	   public java.lang.String description;
	   public  java.lang.String getDescription(){
	      return description;
	   }
	   
	   public void setDescription( java.lang.String description){
	      this.description = description;
	   }
	
	   
	   public java.lang.String shortMeaning;
	   public  java.lang.String getShortMeaning(){
	      return shortMeaning;
	   }
	   
	   public void setShortMeaning( java.lang.String shortMeaning){
	      this.shortMeaning = shortMeaning;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection permissibleValueCollection = new java.util.HashSet();
			public java.util.Collection getPermissibleValueCollection(){
	              return permissibleValueCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setPermissibleValueCollection(java.util.Collection permissibleValueCollection){
	   		this.permissibleValueCollection = permissibleValueCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule conceptDerivationRule;
			public gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule getConceptDerivationRule(){
			  return conceptDerivationRule;
                        }
		   
	      
	               
	   
	   
	   
	   public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule conceptDerivationRule){
		this.conceptDerivationRule = conceptDerivationRule;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection conceptualDomainCollection = new java.util.HashSet();
			public java.util.Collection getConceptualDomainCollection(){
	              return conceptualDomainCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setConceptualDomainCollection(java.util.Collection conceptualDomainCollection){
	   		this.conceptualDomainCollection = conceptualDomainCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ValueMeaning) {
				ValueMeaning c =(ValueMeaning)obj; 			 
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
