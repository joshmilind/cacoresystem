

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Person 
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
	
	   
	   public java.lang.String firstName;
	   public  java.lang.String getFirstName(){
	      return firstName;
	   }
	   
	   public void setFirstName( java.lang.String firstName){
	      this.firstName = firstName;
	   }
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String lastName;
	   public  java.lang.String getLastName(){
	      return lastName;
	   }
	   
	   public void setLastName( java.lang.String lastName){
	      this.lastName = lastName;
	   }
	
	   
	   public java.lang.String middleInitial;
	   public  java.lang.String getMiddleInitial(){
	      return middleInitial;
	   }
	   
	   public void setMiddleInitial( java.lang.String middleInitial){
	      this.middleInitial = middleInitial;
	   }
	
	   
	   public java.lang.String modifiedBy;
	   public  java.lang.String getModifiedBy(){
	      return modifiedBy;
	   }
	   
	   public void setModifiedBy( java.lang.String modifiedBy){
	      this.modifiedBy = modifiedBy;
	   }
	
	   
	   public java.lang.String position;
	   public  java.lang.String getPosition(){
	      return position;
	   }
	   
	   public void setPosition( java.lang.String position){
	      this.position = position;
	   }
	
	   
	   public java.lang.Integer rank;
	   public  java.lang.Integer getRank(){
	      return rank;
	   }
	   
	   public void setRank( java.lang.Integer rank){
	      this.rank = rank;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection addressCollection = new java.util.HashSet();
			public java.util.Collection getAddressCollection(){
	              return addressCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAddressCollection(java.util.Collection addressCollection){
	   		this.addressCollection = addressCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection administeredComponentContact = new java.util.HashSet();
			public java.util.Collection getAdministeredComponentContact(){
	              return administeredComponentContact;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAdministeredComponentContact(java.util.Collection administeredComponentContact){
	   		this.administeredComponentContact = administeredComponentContact;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection contactCommunication = new java.util.HashSet();
			public java.util.Collection getContactCommunication(){
	              return contactCommunication;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setContactCommunication(java.util.Collection contactCommunication){
	   		this.contactCommunication = contactCommunication;
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
			if(obj instanceof Person) {
				Person c =(Person)obj; 			 
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
