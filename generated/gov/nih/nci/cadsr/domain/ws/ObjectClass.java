

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ObjectClass 
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
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection targetObjectClassRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getTargetObjectClassRelationshipCollection(){
	              return targetObjectClassRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTargetObjectClassRelationshipCollection(java.util.Collection targetObjectClassRelationshipCollection){
	   		this.targetObjectClassRelationshipCollection = targetObjectClassRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection sourcObjectClassRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getSourcObjectClassRelationshipCollection(){
	              return sourcObjectClassRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setSourcObjectClassRelationshipCollection(java.util.Collection sourcObjectClassRelationshipCollection){
	   		this.sourcObjectClassRelationshipCollection = sourcObjectClassRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection dataElementConceptCollection = new java.util.HashSet();
			public java.util.Collection getDataElementConceptCollection(){
	              return dataElementConceptCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDataElementConceptCollection(java.util.Collection dataElementConceptCollection){
	   		this.dataElementConceptCollection = dataElementConceptCollection;
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
			if(obj instanceof ObjectClass) {
				ObjectClass c =(ObjectClass)obj; 			 
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
