

package gov.nih.nci.cadsr.umlproject.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Project 
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
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String longName;
	   public  java.lang.String getLongName(){
	      return longName;
	   }
	   
	   public void setLongName( java.lang.String longName){
	      this.longName = longName;
	   }
	
	   
	   public java.lang.String shortName;
	   public  java.lang.String getShortName(){
	      return shortName;
	   }
	   
	   public void setShortName( java.lang.String shortName){
	      this.shortName = shortName;
	   }
	
	   
	   public java.lang.String version;
	   public  java.lang.String getVersion(){
	      return version;
	   }
	   
	   public void setVersion( java.lang.String version){
	      this.version = version;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection subProjectCollection = new java.util.HashSet();
			public java.util.Collection getSubProjectCollection(){
	              return subProjectCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setSubProjectCollection(java.util.Collection subProjectCollection){
	   		this.subProjectCollection = subProjectCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection UMLPackageMetadataCollection = new java.util.HashSet();
			public java.util.Collection getUMLPackageMetadataCollection(){
	              return UMLPackageMetadataCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setUMLPackageMetadataCollection(java.util.Collection UMLPackageMetadataCollection){
	   		this.UMLPackageMetadataCollection = UMLPackageMetadataCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ClassificationScheme classificationScheme;
			public gov.nih.nci.cadsr.domain.ws.ClassificationScheme getClassificationScheme(){
			  return classificationScheme;
                        }
		   
	      
	               
	   
	   
	   
	   public void setClassificationScheme(gov.nih.nci.cadsr.domain.ws.ClassificationScheme classificationScheme){
		this.classificationScheme = classificationScheme;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection UMLClassMetadataCollection = new java.util.HashSet();
			public java.util.Collection getUMLClassMetadataCollection(){
	              return UMLClassMetadataCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setUMLClassMetadataCollection(java.util.Collection UMLClassMetadataCollection){
	   		this.UMLClassMetadataCollection = UMLClassMetadataCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection UMLAttributeMetadataCollection = new java.util.HashSet();
			public java.util.Collection getUMLAttributeMetadataCollection(){
	              return UMLAttributeMetadataCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setUMLAttributeMetadataCollection(java.util.Collection UMLAttributeMetadataCollection){
	   		this.UMLAttributeMetadataCollection = UMLAttributeMetadataCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection UMLAssociationMetadataCollection = new java.util.HashSet();
			public java.util.Collection getUMLAssociationMetadataCollection(){
	              return UMLAssociationMetadataCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setUMLAssociationMetadataCollection(java.util.Collection UMLAssociationMetadataCollection){
	   		this.UMLAssociationMetadataCollection = UMLAssociationMetadataCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Project) {
				Project c =(Project)obj; 			 
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
