

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ConceptDerivationRule 
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
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection classificationSchemeItemCollection = new java.util.HashSet();
			public java.util.Collection getClassificationSchemeItemCollection(){
	              return classificationSchemeItemCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setClassificationSchemeItemCollection(java.util.Collection classificationSchemeItemCollection){
	   		this.classificationSchemeItemCollection = classificationSchemeItemCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection objectClassRelationship = new java.util.HashSet();
			public java.util.Collection getObjectClassRelationship(){
	              return objectClassRelationship;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setObjectClassRelationship(java.util.Collection objectClassRelationship){
	   		this.objectClassRelationship = objectClassRelationship;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection valueDomainCollection = new java.util.HashSet();
			public java.util.Collection getValueDomainCollection(){
	              return valueDomainCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setValueDomainCollection(java.util.Collection valueDomainCollection){
	   		this.valueDomainCollection = valueDomainCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection valueMeaningCollection = new java.util.HashSet();
			public java.util.Collection getValueMeaningCollection(){
	              return valueMeaningCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setValueMeaningCollection(java.util.Collection valueMeaningCollection){
	   		this.valueMeaningCollection = valueMeaningCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection sourceRoleConcept = new java.util.HashSet();
			public java.util.Collection getSourceRoleConcept(){
	              return sourceRoleConcept;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setSourceRoleConcept(java.util.Collection sourceRoleConcept){
	   		this.sourceRoleConcept = sourceRoleConcept;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection classificationSchemeCollection = new java.util.HashSet();
			public java.util.Collection getClassificationSchemeCollection(){
	              return classificationSchemeCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setClassificationSchemeCollection(java.util.Collection classificationSchemeCollection){
	   		this.classificationSchemeCollection = classificationSchemeCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection representationCollection = new java.util.HashSet();
			public java.util.Collection getRepresentationCollection(){
	              return representationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setRepresentationCollection(java.util.Collection representationCollection){
	   		this.representationCollection = representationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection targetRoleConcept = new java.util.HashSet();
			public java.util.Collection getTargetRoleConcept(){
	              return targetRoleConcept;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTargetRoleConcept(java.util.Collection targetRoleConcept){
	   		this.targetRoleConcept = targetRoleConcept;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.DerivationType derivationType;
			public gov.nih.nci.cadsr.domain.ws.DerivationType getDerivationType(){
			  return derivationType;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDerivationType(gov.nih.nci.cadsr.domain.ws.DerivationType derivationType){
		this.derivationType = derivationType;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection objectClassCollection = new java.util.HashSet();
			public java.util.Collection getObjectClassCollection(){
	              return objectClassCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setObjectClassCollection(java.util.Collection objectClassCollection){
	   		this.objectClassCollection = objectClassCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection propertyCollection = new java.util.HashSet();
			public java.util.Collection getPropertyCollection(){
	              return propertyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setPropertyCollection(java.util.Collection propertyCollection){
	   		this.propertyCollection = propertyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection componentConceptCollection = new java.util.HashSet();
			public java.util.Collection getComponentConceptCollection(){
	              return componentConceptCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setComponentConceptCollection(java.util.Collection componentConceptCollection){
	   		this.componentConceptCollection = componentConceptCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection conceptualDomainCollection = new java.util.HashSet();
			public java.util.Collection getConceptualDomainCollection(){
	              return conceptualDomainCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setConceptualDomainCollection(java.util.Collection conceptualDomainCollection){
	   		this.conceptualDomainCollection = conceptualDomainCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ConceptDerivationRule) {
				ConceptDerivationRule c =(ConceptDerivationRule)obj; 			 
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
