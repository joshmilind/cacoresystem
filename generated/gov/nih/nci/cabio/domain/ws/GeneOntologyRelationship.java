

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class GeneOntologyRelationship 
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
	
	   
	   public java.lang.String relationshipType;
	   public  java.lang.String getRelationshipType(){
	      return relationshipType;
	   }
	   
	   public void setRelationshipType( java.lang.String relationshipType){
	      this.relationshipType = relationshipType;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.GeneOntology childGeneOntology;
			public gov.nih.nci.cabio.domain.ws.GeneOntology getChildGeneOntology(){
			  return childGeneOntology;
                        }
		   
	      
	               
	   
	   
	   
	   public void setChildGeneOntology(gov.nih.nci.cabio.domain.ws.GeneOntology childGeneOntology){
		this.childGeneOntology = childGeneOntology;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.GeneOntology parentGeneOntology;
			public gov.nih.nci.cabio.domain.ws.GeneOntology getParentGeneOntology(){
			  return parentGeneOntology;
                        }
		   
	      
	               
	   
	   
	   
	   public void setParentGeneOntology(gov.nih.nci.cabio.domain.ws.GeneOntology parentGeneOntology){
		this.parentGeneOntology = parentGeneOntology;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof GeneOntologyRelationship) {
				GeneOntologyRelationship c =(GeneOntologyRelationship)obj; 			 
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
