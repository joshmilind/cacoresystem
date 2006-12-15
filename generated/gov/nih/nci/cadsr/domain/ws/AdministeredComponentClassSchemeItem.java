

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class AdministeredComponentClassSchemeItem 
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
	

	
	   
	   
	   
	      
			private java.util.Collection sourceObjectClass = new java.util.HashSet();
			public java.util.Collection getSourceObjectClass(){
	              return sourceObjectClass;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setSourceObjectClass(java.util.Collection sourceObjectClass){
	   		this.sourceObjectClass = sourceObjectClass;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem classSchemeClassSchemeItem;
			public gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem getClassSchemeClassSchemeItem(){
			  return classSchemeClassSchemeItem;
                        }
		   
	      
	               
	   
	   
	   
	   public void setClassSchemeClassSchemeItem(gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem classSchemeClassSchemeItem){
		this.classSchemeClassSchemeItem = classSchemeClassSchemeItem;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection targetObjectClass = new java.util.HashSet();
			public java.util.Collection getTargetObjectClass(){
	              return targetObjectClass;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTargetObjectClass(java.util.Collection targetObjectClass){
	   		this.targetObjectClass = targetObjectClass;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AdministeredComponentClassSchemeItem) {
				AdministeredComponentClassSchemeItem c =(AdministeredComponentClassSchemeItem)obj; 			 
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
