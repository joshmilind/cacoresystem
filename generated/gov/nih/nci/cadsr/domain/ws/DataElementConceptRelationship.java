

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class DataElementConceptRelationship 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String createdBy;
	   public  java.lang.String getCreatedBy(){
	      return createdBy;
	   }
	   
	   public void setCreatedBy( java.lang.String createdBy){
	      this.createdBy = createdBy;
	   }
	
	   
	   public java.util.Date dateCreated;
	   public  java.util.Date getDateCreated(){
	      return dateCreated;
	   }
	   
	   public void setDateCreated( java.util.Date dateCreated){
	      this.dateCreated = dateCreated;
	   }
	
	   
	   public java.util.Date dateModified;
	   public  java.util.Date getDateModified(){
	      return dateModified;
	   }
	   
	   public void setDateModified( java.util.Date dateModified){
	      this.dateModified = dateModified;
	   }
	
	   
	   public java.lang.String description;
	   public  java.lang.String getDescription(){
	      return description;
	   }
	   
	   public void setDescription( java.lang.String description){
	      this.description = description;
	   }
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String modifiedBy;
	   public  java.lang.String getModifiedBy(){
	      return modifiedBy;
	   }
	   
	   public void setModifiedBy( java.lang.String modifiedBy){
	      this.modifiedBy = modifiedBy;
	   }
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.DataElementConcept parentDataElementConcept;
			public gov.nih.nci.cadsr.domain.ws.DataElementConcept getParentDataElementConcept(){
			  return parentDataElementConcept;
                        }
		   
	      
	               
	   
	   
	   
	   public void setParentDataElementConcept(gov.nih.nci.cadsr.domain.ws.DataElementConcept parentDataElementConcept){
		this.parentDataElementConcept = parentDataElementConcept;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.DataElementConcept childDataElementConcept;
			public gov.nih.nci.cadsr.domain.ws.DataElementConcept getChildDataElementConcept(){
			  return childDataElementConcept;
                        }
		   
	      
	               
	   
	   
	   
	   public void setChildDataElementConcept(gov.nih.nci.cadsr.domain.ws.DataElementConcept childDataElementConcept){
		this.childDataElementConcept = childDataElementConcept;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof DataElementConceptRelationship) {
				DataElementConceptRelationship c =(DataElementConceptRelationship)obj; 			 
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
