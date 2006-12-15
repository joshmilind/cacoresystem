

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class DataElementConcept 
    extends gov.nih.nci.cadsr.domain.ws.AdministeredComponent
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Property property;
			public gov.nih.nci.cadsr.domain.ws.Property getProperty(){
			  return property;
                        }
		   
	      
	               
	   
	   
	   
	   public void setProperty(gov.nih.nci.cadsr.domain.ws.Property property){
		this.property = property;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ConceptualDomain conceptualDomain;
			public gov.nih.nci.cadsr.domain.ws.ConceptualDomain getConceptualDomain(){
			  return conceptualDomain;
                        }
		   
	      
	               
	   
	   
	   
	   public void setConceptualDomain(gov.nih.nci.cadsr.domain.ws.ConceptualDomain conceptualDomain){
		this.conceptualDomain = conceptualDomain;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection dataElementCollection = new java.util.HashSet();
			public java.util.Collection getDataElementCollection(){
	              return dataElementCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDataElementCollection(java.util.Collection dataElementCollection){
	   		this.dataElementCollection = dataElementCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection childDataElementConceptRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getChildDataElementConceptRelationshipCollection(){
	              return childDataElementConceptRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChildDataElementConceptRelationshipCollection(java.util.Collection childDataElementConceptRelationshipCollection){
	   		this.childDataElementConceptRelationshipCollection = childDataElementConceptRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ObjectClass objectClass;
			public gov.nih.nci.cadsr.domain.ws.ObjectClass getObjectClass(){
			  return objectClass;
                        }
		   
	      
	               
	   
	   
	   
	   public void setObjectClass(gov.nih.nci.cadsr.domain.ws.ObjectClass objectClass){
		this.objectClass = objectClass;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection parentDataElementConceptRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getParentDataElementConceptRelationshipCollection(){
	              return parentDataElementConceptRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setParentDataElementConceptRelationshipCollection(java.util.Collection parentDataElementConceptRelationshipCollection){
	   		this.parentDataElementConceptRelationshipCollection = parentDataElementConceptRelationshipCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof DataElementConcept) {
				DataElementConcept c =(DataElementConcept)obj; 			 
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
