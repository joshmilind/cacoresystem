

package gov.nih.nci.cadsr.umlproject.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class UMLAttributeMetadata 
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
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata UMLClassMetadata;
			public gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata getUMLClassMetadata(){
			  return UMLClassMetadata;
                        }
		   
	      
	               
	   
	   
	   
	   public void setUMLClassMetadata(gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata UMLClassMetadata){
		this.UMLClassMetadata = UMLClassMetadata;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.DataElement dataElement;
			public gov.nih.nci.cadsr.domain.ws.DataElement getDataElement(){
			  return dataElement;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDataElement(gov.nih.nci.cadsr.domain.ws.DataElement dataElement){
		this.dataElement = dataElement;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.Project project;
			public gov.nih.nci.cadsr.umlproject.domain.ws.Project getProject(){
			  return project;
                        }
		   
	      
	               
	   
	   
	   
	   public void setProject(gov.nih.nci.cadsr.umlproject.domain.ws.Project project){
		this.project = project;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.AttributeTypeMetadata attributeTypeMetadata;
			public gov.nih.nci.cadsr.umlproject.domain.ws.AttributeTypeMetadata getAttributeTypeMetadata(){
			  return attributeTypeMetadata;
                        }
		   
	      
	               
	   
	   
	   
	   public void setAttributeTypeMetadata(gov.nih.nci.cadsr.umlproject.domain.ws.AttributeTypeMetadata attributeTypeMetadata){
		this.attributeTypeMetadata = attributeTypeMetadata;
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
			if(obj instanceof UMLAttributeMetadata) {
				UMLAttributeMetadata c =(UMLAttributeMetadata)obj; 			 
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
