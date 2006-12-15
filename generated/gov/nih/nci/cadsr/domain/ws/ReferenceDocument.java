

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ReferenceDocument 
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
	
	   
	   public java.lang.Long displayOrder;
	   public  java.lang.Long getDisplayOrder(){
	      return displayOrder;
	   }
	   
	   public void setDisplayOrder( java.lang.Long displayOrder){
	      this.displayOrder = displayOrder;
	   }
	
	   
	   public java.lang.String doctext;
	   public  java.lang.String getDoctext(){
	      return doctext;
	   }
	   
	   public void setDoctext( java.lang.String doctext){
	      this.doctext = doctext;
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
	
	   
	   public java.lang.String organizationId;
	   public  java.lang.String getOrganizationId(){
	      return organizationId;
	   }
	   
	   public void setOrganizationId( java.lang.String organizationId){
	      this.organizationId = organizationId;
	   }
	
	   
	   public java.lang.String rdtlName;
	   public  java.lang.String getRdtlName(){
	      return rdtlName;
	   }
	   
	   public void setRdtlName( java.lang.String rdtlName){
	      this.rdtlName = rdtlName;
	   }
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	
	   
	   public java.lang.String URL;
	   public  java.lang.String getURL(){
	      return URL;
	   }
	   
	   public void setURL( java.lang.String URL){
	      this.URL = URL;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Context context;
			public gov.nih.nci.cadsr.domain.ws.Context getContext(){
			  return context;
                        }
		   
	      
	               
	   
	   
	   
	   public void setContext(gov.nih.nci.cadsr.domain.ws.Context context){
		this.context = context;
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
	   
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ReferenceDocument) {
				ReferenceDocument c =(ReferenceDocument)obj; 			 
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
