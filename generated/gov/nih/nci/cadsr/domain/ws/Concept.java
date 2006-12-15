

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Concept 
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
	
	   
	   public java.lang.String evsSource;
	   public  java.lang.String getEvsSource(){
	      return evsSource;
	   }
	   
	   public void setEvsSource( java.lang.String evsSource){
	      this.evsSource = evsSource;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection valueDomainPermissibleValueCollection = new java.util.HashSet();
			public java.util.Collection getValueDomainPermissibleValueCollection(){
	              return valueDomainPermissibleValueCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setValueDomainPermissibleValueCollection(java.util.Collection valueDomainPermissibleValueCollection){
	   		this.valueDomainPermissibleValueCollection = valueDomainPermissibleValueCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection componentConceptCollection = new java.util.HashSet();
			public java.util.Collection getComponentConceptCollection(){
	              return componentConceptCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setComponentConceptCollection(java.util.Collection componentConceptCollection){
	   		this.componentConceptCollection = componentConceptCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Concept) {
				Concept c =(Concept)obj; 			 
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
