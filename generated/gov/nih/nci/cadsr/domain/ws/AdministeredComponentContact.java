

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class AdministeredComponentContact 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String contactRole;
	   public  java.lang.String getContactRole(){
	      return contactRole;
	   }
	   
	   public void setContactRole( java.lang.String contactRole){
	      this.contactRole = contactRole;
	   }
	
	   
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
	
	   
	   public java.lang.Integer rank;
	   public  java.lang.Integer getRank(){
	      return rank;
	   }
	   
	   public void setRank( java.lang.Integer rank){
	      this.rank = rank;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem classSchemeClassSchemeItem;
			public gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem getClassSchemeClassSchemeItem(){
			  return classSchemeClassSchemeItem;
                        }
		   
	      
	               
	   
	   
	   
	   public void setClassSchemeClassSchemeItem(gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem classSchemeClassSchemeItem){
		this.classSchemeClassSchemeItem = classSchemeClassSchemeItem;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ClassificationSchemeItem classificationSchemeItem;
			public gov.nih.nci.cadsr.domain.ws.ClassificationSchemeItem getClassificationSchemeItem(){
			  return classificationSchemeItem;
                        }
		   
	      
	               
	   
	   
	   
	   public void setClassificationSchemeItem(gov.nih.nci.cadsr.domain.ws.ClassificationSchemeItem classificationSchemeItem){
		this.classificationSchemeItem = classificationSchemeItem;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Person person;
			public gov.nih.nci.cadsr.domain.ws.Person getPerson(){
			  return person;
                        }
		   
	      
	               
	   
	   
	   
	   public void setPerson(gov.nih.nci.cadsr.domain.ws.Person person){
		this.person = person;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Organization organization;
			public gov.nih.nci.cadsr.domain.ws.Organization getOrganization(){
			  return organization;
                        }
		   
	      
	               
	   
	   
	   
	   public void setOrganization(gov.nih.nci.cadsr.domain.ws.Organization organization){
		this.organization = organization;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AdministeredComponentContact) {
				AdministeredComponentContact c =(AdministeredComponentContact)obj; 			 
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
