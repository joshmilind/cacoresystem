

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class DataElementDerivation 
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
	
	   
	   public java.lang.Integer displayOrder;
	   public  java.lang.Integer getDisplayOrder(){
	      return displayOrder;
	   }
	   
	   public void setDisplayOrder( java.lang.Integer displayOrder){
	      this.displayOrder = displayOrder;
	   }
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String leadingCharacters;
	   public  java.lang.String getLeadingCharacters(){
	      return leadingCharacters;
	   }
	   
	   public void setLeadingCharacters( java.lang.String leadingCharacters){
	      this.leadingCharacters = leadingCharacters;
	   }
	
	   
	   public java.lang.String modifiedBy;
	   public  java.lang.String getModifiedBy(){
	      return modifiedBy;
	   }
	   
	   public void setModifiedBy( java.lang.String modifiedBy){
	      this.modifiedBy = modifiedBy;
	   }
	
	   
	   public java.lang.String trailingCharacters;
	   public  java.lang.String getTrailingCharacters(){
	      return trailingCharacters;
	   }
	   
	   public void setTrailingCharacters( java.lang.String trailingCharacters){
	      this.trailingCharacters = trailingCharacters;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.DataElement dataElement;
			public gov.nih.nci.cadsr.domain.ws.DataElement getDataElement(){
			  return dataElement;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDataElement(gov.nih.nci.cadsr.domain.ws.DataElement dataElement){
		this.dataElement = dataElement;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Function leftOperand;
			public gov.nih.nci.cadsr.domain.ws.Function getLeftOperand(){
			  return leftOperand;
                        }
		   
	      
	               
	   
	   
	   
	   public void setLeftOperand(gov.nih.nci.cadsr.domain.ws.Function leftOperand){
		this.leftOperand = leftOperand;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.DerivedDataElement derivedDataElement;
			public gov.nih.nci.cadsr.domain.ws.DerivedDataElement getDerivedDataElement(){
			  return derivedDataElement;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDerivedDataElement(gov.nih.nci.cadsr.domain.ws.DerivedDataElement derivedDataElement){
		this.derivedDataElement = derivedDataElement;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof DataElementDerivation) {
				DataElementDerivation c =(DataElementDerivation)obj; 			 
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
