

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class OrganOntologyRelationship 
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
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.OrganOntology childOrganOntology;
			public gov.nih.nci.cabio.domain.ws.OrganOntology getChildOrganOntology(){
			  return childOrganOntology;
                        }
		   
	      
	               
	   
	   
	   
	   public void setChildOrganOntology(gov.nih.nci.cabio.domain.ws.OrganOntology childOrganOntology){
		this.childOrganOntology = childOrganOntology;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.OrganOntology parentOrganOntology;
			public gov.nih.nci.cabio.domain.ws.OrganOntology getParentOrganOntology(){
			  return parentOrganOntology;
                        }
		   
	      
	               
	   
	   
	   
	   public void setParentOrganOntology(gov.nih.nci.cabio.domain.ws.OrganOntology parentOrganOntology){
		this.parentOrganOntology = parentOrganOntology;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof OrganOntologyRelationship) {
				OrganOntologyRelationship c =(OrganOntologyRelationship)obj; 			 
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
