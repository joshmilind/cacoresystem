

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Context 
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
	
	   
	   public java.lang.String languageName;
	   public  java.lang.String getLanguageName(){
	      return languageName;
	   }
	   
	   public void setLanguageName( java.lang.String languageName){
	      this.languageName = languageName;
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
	
	   
	   public java.lang.Float version;
	   public  java.lang.Float getVersion(){
	      return version;
	   }
	   
	   public void setVersion( java.lang.Float version){
	      this.version = version;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection administeredComponentCollection = new java.util.HashSet();
			public java.util.Collection getAdministeredComponentCollection(){
	              return administeredComponentCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAdministeredComponentCollection(java.util.Collection administeredComponentCollection){
	   		this.administeredComponentCollection = administeredComponentCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection designationCollection = new java.util.HashSet();
			public java.util.Collection getDesignationCollection(){
	              return designationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDesignationCollection(java.util.Collection designationCollection){
	   		this.designationCollection = designationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection referenceDocumentCollection = new java.util.HashSet();
			public java.util.Collection getReferenceDocumentCollection(){
	              return referenceDocumentCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setReferenceDocumentCollection(java.util.Collection referenceDocumentCollection){
	   		this.referenceDocumentCollection = referenceDocumentCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection definitionCollection = new java.util.HashSet();
			public java.util.Collection getDefinitionCollection(){
	              return definitionCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDefinitionCollection(java.util.Collection definitionCollection){
	   		this.definitionCollection = definitionCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Context) {
				Context c =(Context)obj; 			 
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
