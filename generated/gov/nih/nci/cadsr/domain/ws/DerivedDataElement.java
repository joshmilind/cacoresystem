

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class DerivedDataElement 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String concatenationCharacter;
	   public  java.lang.String getConcatenationCharacter(){
	      return concatenationCharacter;
	   }
	   
	   public void setConcatenationCharacter( java.lang.String concatenationCharacter){
	      this.concatenationCharacter = concatenationCharacter;
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
	
	   
	   public java.lang.String methods;
	   public  java.lang.String getMethods(){
	      return methods;
	   }
	   
	   public void setMethods( java.lang.String methods){
	      this.methods = methods;
	   }
	
	   
	   public java.lang.String modifiedBy;
	   public  java.lang.String getModifiedBy(){
	      return modifiedBy;
	   }
	   
	   public void setModifiedBy( java.lang.String modifiedBy){
	      this.modifiedBy = modifiedBy;
	   }
	
	   
	   public java.lang.String rule;
	   public  java.lang.String getRule(){
	      return rule;
	   }
	   
	   public void setRule( java.lang.String rule){
	      this.rule = rule;
	   }
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.cadsr.domain.ws.DataElement dataElement;
			public gov.nih.nci.cadsr.domain.ws.DataElement getDataElement(){
			  return dataElement;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setDataElement(gov.nih.nci.cadsr.domain.ws.DataElement dataElement){
		this.dataElement = dataElement;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection dataElementDerivationCollection = new java.util.HashSet();
			public java.util.Collection getDataElementDerivationCollection(){
	              return dataElementDerivationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDataElementDerivationCollection(java.util.Collection dataElementDerivationCollection){
	   		this.dataElementDerivationCollection = dataElementDerivationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.DerivationType derivationType;
			public gov.nih.nci.cadsr.domain.ws.DerivationType getDerivationType(){
			  return derivationType;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDerivationType(gov.nih.nci.cadsr.domain.ws.DerivationType derivationType){
		this.derivationType = derivationType;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof DerivedDataElement) {
				DerivedDataElement c =(DerivedDataElement)obj; 			 
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
