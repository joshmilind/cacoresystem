

package gov.nih.nci.cadsr.umlproject.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class UMLClassMetadata 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String description;
	   public  java.lang.String getDescription(){
	      return description;
	   }
	   
	   public void setDescription( java.lang.String description){
	      this.description = description;
	   }
	
	   
	   public java.lang.String fullyQualifiedName;
	   public  java.lang.String getFullyQualifiedName(){
	      return fullyQualifiedName;
	   }
	   
	   public void setFullyQualifiedName( java.lang.String fullyQualifiedName){
	      this.fullyQualifiedName = fullyQualifiedName;
	   }
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.Project project;
			public gov.nih.nci.cadsr.umlproject.domain.ws.Project getProject(){
			  return project;
                        }
		   
	      
	               
	   
	   
	   
	   public void setProject(gov.nih.nci.cadsr.umlproject.domain.ws.Project project){
		this.project = project;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.UMLPackageMetadata UMLPackageMetadata;
			public gov.nih.nci.cadsr.umlproject.domain.ws.UMLPackageMetadata getUMLPackageMetadata(){
			  return UMLPackageMetadata;
                        }
		   
	      
	               
	   
	   
	   
	   public void setUMLPackageMetadata(gov.nih.nci.cadsr.umlproject.domain.ws.UMLPackageMetadata UMLPackageMetadata){
		this.UMLPackageMetadata = UMLPackageMetadata;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.UMLGeneralizationMetadata UMLGeneralizationMetadata;
			public gov.nih.nci.cadsr.umlproject.domain.ws.UMLGeneralizationMetadata getUMLGeneralizationMetadata(){
			  return UMLGeneralizationMetadata;
                        }
		   
	      
	               
	   
	   
	   
	   public void setUMLGeneralizationMetadata(gov.nih.nci.cadsr.umlproject.domain.ws.UMLGeneralizationMetadata UMLGeneralizationMetadata){
		this.UMLGeneralizationMetadata = UMLGeneralizationMetadata;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection UMLAttributeMetadataCollection = new java.util.HashSet();
			public java.util.Collection getUMLAttributeMetadataCollection(){
	              return UMLAttributeMetadataCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setUMLAttributeMetadataCollection(java.util.Collection UMLAttributeMetadataCollection){
	   		this.UMLAttributeMetadataCollection = UMLAttributeMetadataCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ObjectClass objectClass;
			public gov.nih.nci.cadsr.domain.ws.ObjectClass getObjectClass(){
			  return objectClass;
                        }
		   
	      
	               
	   
	   
	   
	   public void setObjectClass(gov.nih.nci.cadsr.domain.ws.ObjectClass objectClass){
		this.objectClass = objectClass;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection UMLAssociationMetadataCollection = new java.util.HashSet();
			public java.util.Collection getUMLAssociationMetadataCollection(){
	              return UMLAssociationMetadataCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setUMLAssociationMetadataCollection(java.util.Collection UMLAssociationMetadataCollection){
	   		this.UMLAssociationMetadataCollection = UMLAssociationMetadataCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection semanticMetadataCollection = new java.util.HashSet();
			public java.util.Collection getSemanticMetadataCollection(){
	              return semanticMetadataCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setSemanticMetadataCollection(java.util.Collection semanticMetadataCollection){
	   		this.semanticMetadataCollection = semanticMetadataCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof UMLClassMetadata) {
				UMLClassMetadata c =(UMLClassMetadata)obj; 			 
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
