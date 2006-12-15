

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class PermissibleValue 
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
	
	   
	   public java.lang.Long highValueNumber;
	   public  java.lang.Long getHighValueNumber(){
	      return highValueNumber;
	   }
	   
	   public void setHighValueNumber( java.lang.Long highValueNumber){
	      this.highValueNumber = highValueNumber;
	   }
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.Long lowValueNumber;
	   public  java.lang.Long getLowValueNumber(){
	      return lowValueNumber;
	   }
	   
	   public void setLowValueNumber( java.lang.Long lowValueNumber){
	      this.lowValueNumber = lowValueNumber;
	   }
	
	   
	   public java.lang.String modifiedBy;
	   public  java.lang.String getModifiedBy(){
	      return modifiedBy;
	   }
	   
	   public void setModifiedBy( java.lang.String modifiedBy){
	      this.modifiedBy = modifiedBy;
	   }
	
	   
	   public java.lang.String value;
	   public  java.lang.String getValue(){
	      return value;
	   }
	   
	   public void setValue( java.lang.String value){
	      this.value = value;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection valueDomainPermissibleValueCollection = new java.util.HashSet();
			public java.util.Collection getValueDomainPermissibleValueCollection(){
	              return valueDomainPermissibleValueCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setValueDomainPermissibleValueCollection(java.util.Collection valueDomainPermissibleValueCollection){
	   		this.valueDomainPermissibleValueCollection = valueDomainPermissibleValueCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ValueMeaning valueMeaning;
			public gov.nih.nci.cadsr.domain.ws.ValueMeaning getValueMeaning(){
			  return valueMeaning;
                        }
		   
	      
	               
	   
	   
	   
	   public void setValueMeaning(gov.nih.nci.cadsr.domain.ws.ValueMeaning valueMeaning){
		this.valueMeaning = valueMeaning;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof PermissibleValue) {
				PermissibleValue c =(PermissibleValue)obj; 			 
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
