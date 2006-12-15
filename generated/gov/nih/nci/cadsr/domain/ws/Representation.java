

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Representation 
    extends gov.nih.nci.cadsr.domain.ws.AdministeredComponent
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String definitionSource;
	   public  java.lang.String getDefinitionSource(){
	      return definitionSource;
	   }
	   
	   public void setDefinitionSource( java.lang.String definitionSource){
	      this.definitionSource = definitionSource;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection valueDomainCollection = new java.util.HashSet();
			public java.util.Collection getValueDomainCollection(){
	              return valueDomainCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setValueDomainCollection(java.util.Collection valueDomainCollection){
	   		this.valueDomainCollection = valueDomainCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule conceptDerivationRule;
			public gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule getConceptDerivationRule(){
			  return conceptDerivationRule;
                        }
		   
	      
	               
	   
	   
	   
	   public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule conceptDerivationRule){
		this.conceptDerivationRule = conceptDerivationRule;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Representation) {
				Representation c =(Representation)obj; 			 
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
