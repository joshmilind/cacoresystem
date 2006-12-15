

package gov.nih.nci.cadsr.umlproject.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class SubProject 
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
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection UMLPackageMetadataCollection = new java.util.HashSet();
			public java.util.Collection getUMLPackageMetadataCollection(){
	              return UMLPackageMetadataCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setUMLPackageMetadataCollection(java.util.Collection UMLPackageMetadataCollection){
	   		this.UMLPackageMetadataCollection = UMLPackageMetadataCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.Project project;
			public gov.nih.nci.cadsr.umlproject.domain.ws.Project getProject(){
			  return project;
                        }
		   
	      
	               
	   
	   
	   
	   public void setProject(gov.nih.nci.cadsr.umlproject.domain.ws.Project project){
		this.project = project;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem classSchemeClassSchemeItem;
			public gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem getClassSchemeClassSchemeItem(){
			  return classSchemeClassSchemeItem;
                        }
		   
	      
	               
	   
	   
	   
	   public void setClassSchemeClassSchemeItem(gov.nih.nci.cadsr.domain.ws.ClassSchemeClassSchemeItem classSchemeClassSchemeItem){
		this.classSchemeClassSchemeItem = classSchemeClassSchemeItem;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof SubProject) {
				SubProject c =(SubProject)obj; 			 
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
