

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ValueDomainPermissibleValue 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.util.Date beginDate;
	   public  java.util.Date getBeginDate(){
	      return beginDate;
	   }
	   
	   public void setBeginDate( java.util.Date beginDate){
	      this.beginDate = beginDate;
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
	
	   
	   public java.util.Date endDate;
	   public  java.util.Date getEndDate(){
	      return endDate;
	   }
	   
	   public void setEndDate( java.util.Date endDate){
	      this.endDate = endDate;
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
	
	   
	   public java.lang.String origin;
	   public  java.lang.String getOrigin(){
	      return origin;
	   }
	   
	   public void setOrigin( java.lang.String origin){
	      this.origin = origin;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Concept concept;
			public gov.nih.nci.cadsr.domain.ws.Concept getConcept(){
			  return concept;
                        }
		   
	      
	               
	   
	   
	   
	   public void setConcept(gov.nih.nci.cadsr.domain.ws.Concept concept){
		this.concept = concept;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection validValueCollection = new java.util.HashSet();
			public java.util.Collection getValidValueCollection(){
	              return validValueCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setValidValueCollection(java.util.Collection validValueCollection){
	   		this.validValueCollection = validValueCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.EnumeratedValueDomain enumeratedValueDomain;
			public gov.nih.nci.cadsr.domain.ws.EnumeratedValueDomain getEnumeratedValueDomain(){
			  return enumeratedValueDomain;
                        }
		   
	      
	               
	   
	   
	   
	   public void setEnumeratedValueDomain(gov.nih.nci.cadsr.domain.ws.EnumeratedValueDomain enumeratedValueDomain){
		this.enumeratedValueDomain = enumeratedValueDomain;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.PermissibleValue permissibleValue;
			public gov.nih.nci.cadsr.domain.ws.PermissibleValue getPermissibleValue(){
			  return permissibleValue;
                        }
		   
	      
	               
	   
	   
	   
	   public void setPermissibleValue(gov.nih.nci.cadsr.domain.ws.PermissibleValue permissibleValue){
		this.permissibleValue = permissibleValue;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ValueDomainPermissibleValue) {
				ValueDomainPermissibleValue c =(ValueDomainPermissibleValue)obj; 			 
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
