

package gov.nih.nci.cadsr.umlproject.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class UMLGeneralizationMetadata 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ObjectClassRelationship objectClassRelationship;
			public gov.nih.nci.cadsr.domain.ws.ObjectClassRelationship getObjectClassRelationship(){
			  return objectClassRelationship;
                        }
		   
	      
	               
	   
	   
	   
	   public void setObjectClassRelationship(gov.nih.nci.cadsr.domain.ws.ObjectClassRelationship objectClassRelationship){
		this.objectClassRelationship = objectClassRelationship;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata superUMLClassMetadata;
			public gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata getSuperUMLClassMetadata(){
			  return superUMLClassMetadata;
                        }
		   
	      
	               
	   
	   
	   
	   public void setSuperUMLClassMetadata(gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata superUMLClassMetadata){
		this.superUMLClassMetadata = superUMLClassMetadata;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof UMLGeneralizationMetadata) {
				UMLGeneralizationMetadata c =(UMLGeneralizationMetadata)obj; 			 
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
