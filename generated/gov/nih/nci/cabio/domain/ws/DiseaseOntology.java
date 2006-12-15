

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class DiseaseOntology 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String bigid;
	   public  java.lang.String getBigid(){
	      return bigid;
	   }
	   
	   public void setBigid( java.lang.String bigid){
	      this.bigid = bigid;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection clinicalTrialProtocolCollection = new java.util.HashSet();
			public java.util.Collection getClinicalTrialProtocolCollection(){
	              return clinicalTrialProtocolCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setClinicalTrialProtocolCollection(java.util.Collection clinicalTrialProtocolCollection){
	   		this.clinicalTrialProtocolCollection = clinicalTrialProtocolCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection histopathologyCollection = new java.util.HashSet();
			public java.util.Collection getHistopathologyCollection(){
	              return histopathologyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setHistopathologyCollection(java.util.Collection histopathologyCollection){
	   		this.histopathologyCollection = histopathologyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection childDiseaseOntologyRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getChildDiseaseOntologyRelationshipCollection(){
	              return childDiseaseOntologyRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChildDiseaseOntologyRelationshipCollection(java.util.Collection childDiseaseOntologyRelationshipCollection){
	   		this.childDiseaseOntologyRelationshipCollection = childDiseaseOntologyRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection parentDiseaseOntologyRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getParentDiseaseOntologyRelationshipCollection(){
	              return parentDiseaseOntologyRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setParentDiseaseOntologyRelationshipCollection(java.util.Collection parentDiseaseOntologyRelationshipCollection){
	   		this.parentDiseaseOntologyRelationshipCollection = parentDiseaseOntologyRelationshipCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof DiseaseOntology) {
				DiseaseOntology c =(DiseaseOntology)obj; 			 
				Long thisId = getId();		
				
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
