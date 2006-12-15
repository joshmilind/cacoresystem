

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ClassificationSchemeItem 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String comments;
	   public  java.lang.String getComments(){
	      return comments;
	   }
	   
	   public void setComments( java.lang.String comments){
	      this.comments = comments;
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
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection classSchemeClassSchemeItemCollection = new java.util.HashSet();
			public java.util.Collection getClassSchemeClassSchemeItemCollection(){
	              return classSchemeClassSchemeItemCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setClassSchemeClassSchemeItemCollection(java.util.Collection classSchemeClassSchemeItemCollection){
	   		this.classSchemeClassSchemeItemCollection = classSchemeClassSchemeItemCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection parentClassificationSchemeItemRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getParentClassificationSchemeItemRelationshipCollection(){
	              return parentClassificationSchemeItemRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setParentClassificationSchemeItemRelationshipCollection(java.util.Collection parentClassificationSchemeItemRelationshipCollection){
	   		this.parentClassificationSchemeItemRelationshipCollection = parentClassificationSchemeItemRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection referenceDocumentCollection = new java.util.HashSet();
			public java.util.Collection getReferenceDocumentCollection(){
	              return referenceDocumentCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setReferenceDocumentCollection(java.util.Collection referenceDocumentCollection){
	   		this.referenceDocumentCollection = referenceDocumentCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection childClassificationSchemeItemRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getChildClassificationSchemeItemRelationshipCollection(){
	              return childClassificationSchemeItemRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChildClassificationSchemeItemRelationshipCollection(java.util.Collection childClassificationSchemeItemRelationshipCollection){
	   		this.childClassificationSchemeItemRelationshipCollection = childClassificationSchemeItemRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection administeredComponentContact = new java.util.HashSet();
			public java.util.Collection getAdministeredComponentContact(){
	              return administeredComponentContact;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAdministeredComponentContact(java.util.Collection administeredComponentContact){
	   		this.administeredComponentContact = administeredComponentContact;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule conceptDerivationRule;
			public gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule getConceptDerivationRule(){
			  return conceptDerivationRule;
                        }
		   
	      
	               
	   
	   
	   
	   public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule conceptDerivationRule){
		this.conceptDerivationRule = conceptDerivationRule;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ClassificationSchemeItem) {
				ClassificationSchemeItem c =(ClassificationSchemeItem)obj; 			 
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
