

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ClassSchemeClassSchemeItem 
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
	
	   
	   public java.lang.String modifiedBy;
	   public  java.lang.String getModifiedBy(){
	      return modifiedBy;
	   }
	   
	   public void setModifiedBy( java.lang.String modifiedBy){
	      this.modifiedBy = modifiedBy;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection AdministeredComponentContact = new java.util.HashSet();
			public java.util.Collection getAdministeredComponentContact(){
	              return AdministeredComponentContact;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAdministeredComponentContact(java.util.Collection AdministeredComponentContact){
	   		this.AdministeredComponentContact = AdministeredComponentContact;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ClassificationScheme classificationScheme;
			public gov.nih.nci.cadsr.domain.ws.ClassificationScheme getClassificationScheme(){
			  return classificationScheme;
                        }
		   
	      
	               
	   
	   
	   
	   public void setClassificationScheme(gov.nih.nci.cadsr.domain.ws.ClassificationScheme classificationScheme){
		this.classificationScheme = classificationScheme;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection administeredComponentClassSchemeItemCollection = new java.util.HashSet();
			public java.util.Collection getAdministeredComponentClassSchemeItemCollection(){
	              return administeredComponentClassSchemeItemCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAdministeredComponentClassSchemeItemCollection(java.util.Collection administeredComponentClassSchemeItemCollection){
	   		this.administeredComponentClassSchemeItemCollection = administeredComponentClassSchemeItemCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection referenceDocumentCollection = new java.util.HashSet();
			public java.util.Collection getReferenceDocumentCollection(){
	              return referenceDocumentCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setReferenceDocumentCollection(java.util.Collection referenceDocumentCollection){
	   		this.referenceDocumentCollection = referenceDocumentCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem parentClassSchemeClassSchemeItem;
			public gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem getParentClassSchemeClassSchemeItem(){
			  return parentClassSchemeClassSchemeItem;
                        }
		   
	      
	               
	   
	   
	   
	   public void setParentClassSchemeClassSchemeItem(gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem parentClassSchemeClassSchemeItem){
		this.parentClassSchemeClassSchemeItem = parentClassSchemeClassSchemeItem;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ClassificationSchemeItem classificationSchemeItem;
			public gov.nih.nci.cadsr.domain.ws.ClassificationSchemeItem getClassificationSchemeItem(){
			  return classificationSchemeItem;
                        }
		   
	      
	               
	   
	   
	   
	   public void setClassificationSchemeItem(gov.nih.nci.cadsr.domain.ws.ClassificationSchemeItem classificationSchemeItem){
		this.classificationSchemeItem = classificationSchemeItem;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection definitionClassSchemeItemCollection = new java.util.HashSet();
			public java.util.Collection getDefinitionClassSchemeItemCollection(){
	              return definitionClassSchemeItemCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDefinitionClassSchemeItemCollection(java.util.Collection definitionClassSchemeItemCollection){
	   		this.definitionClassSchemeItemCollection = definitionClassSchemeItemCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection childClassSchemeClassSchemeItemCollection = new java.util.HashSet();
			public java.util.Collection getChildClassSchemeClassSchemeItemCollection(){
	              return childClassSchemeClassSchemeItemCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChildClassSchemeClassSchemeItemCollection(java.util.Collection childClassSchemeClassSchemeItemCollection){
	   		this.childClassSchemeClassSchemeItemCollection = childClassSchemeClassSchemeItemCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection designationClassSchemeItemCollection = new java.util.HashSet();
			public java.util.Collection getDesignationClassSchemeItemCollection(){
	              return designationClassSchemeItemCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDesignationClassSchemeItemCollection(java.util.Collection designationClassSchemeItemCollection){
	   		this.designationClassSchemeItemCollection = designationClassSchemeItemCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ClassSchemeClassSchemeItem) {
				ClassSchemeClassSchemeItem c =(ClassSchemeClassSchemeItem)obj; 			 
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
