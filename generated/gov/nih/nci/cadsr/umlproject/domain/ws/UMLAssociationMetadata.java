

package gov.nih.nci.cadsr.umlproject.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class UMLAssociationMetadata 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.Boolean isBidirectional;
	   public  java.lang.Boolean getIsBidirectional(){
	      return isBidirectional;
	   }
	   
	   public void setIsBidirectional( java.lang.Boolean isBidirectional){
	      this.isBidirectional = isBidirectional;
	   }
	
	   
	   public java.lang.Integer sourceHighCardinality;
	   public  java.lang.Integer getSourceHighCardinality(){
	      return sourceHighCardinality;
	   }
	   
	   public void setSourceHighCardinality( java.lang.Integer sourceHighCardinality){
	      this.sourceHighCardinality = sourceHighCardinality;
	   }
	
	   
	   public java.lang.Integer sourceLowCardinality;
	   public  java.lang.Integer getSourceLowCardinality(){
	      return sourceLowCardinality;
	   }
	   
	   public void setSourceLowCardinality( java.lang.Integer sourceLowCardinality){
	      this.sourceLowCardinality = sourceLowCardinality;
	   }
	
	   
	   public java.lang.String sourceRoleName;
	   public  java.lang.String getSourceRoleName(){
	      return sourceRoleName;
	   }
	   
	   public void setSourceRoleName( java.lang.String sourceRoleName){
	      this.sourceRoleName = sourceRoleName;
	   }
	
	   
	   public java.lang.Integer targetHighCardinality;
	   public  java.lang.Integer getTargetHighCardinality(){
	      return targetHighCardinality;
	   }
	   
	   public void setTargetHighCardinality( java.lang.Integer targetHighCardinality){
	      this.targetHighCardinality = targetHighCardinality;
	   }
	
	   
	   public java.lang.Integer targetLowCardinality;
	   public  java.lang.Integer getTargetLowCardinality(){
	      return targetLowCardinality;
	   }
	   
	   public void setTargetLowCardinality( java.lang.Integer targetLowCardinality){
	      this.targetLowCardinality = targetLowCardinality;
	   }
	
	   
	   public java.lang.String targetRoleName;
	   public  java.lang.String getTargetRoleName(){
	      return targetRoleName;
	   }
	   
	   public void setTargetRoleName( java.lang.String targetRoleName){
	      this.targetRoleName = targetRoleName;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata targetUMLClassMetadata;
			public gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata getTargetUMLClassMetadata(){
			  return targetUMLClassMetadata;
                        }
		   
	      
	               
	   
	   
	   
	   public void setTargetUMLClassMetadata(gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata targetUMLClassMetadata){
		this.targetUMLClassMetadata = targetUMLClassMetadata;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.Project project;
			public gov.nih.nci.cadsr.umlproject.domain.ws.Project getProject(){
			  return project;
                        }
		   
	      
	               
	   
	   
	   
	   public void setProject(gov.nih.nci.cadsr.umlproject.domain.ws.Project project){
		this.project = project;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ObjectClassRelationship objectClassRelationship;
			public gov.nih.nci.cadsr.domain.ws.ObjectClassRelationship getObjectClassRelationship(){
			  return objectClassRelationship;
                        }
		   
	      
	               
	   
	   
	   
	   public void setObjectClassRelationship(gov.nih.nci.cadsr.domain.ws.ObjectClassRelationship objectClassRelationship){
		this.objectClassRelationship = objectClassRelationship;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection semanticMetadataCollection = new java.util.HashSet();
			public java.util.Collection getSemanticMetadataCollection(){
	              return semanticMetadataCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setSemanticMetadataCollection(java.util.Collection semanticMetadataCollection){
	   		this.semanticMetadataCollection = semanticMetadataCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata sourceUMLClassMetadata;
			public gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata getSourceUMLClassMetadata(){
			  return sourceUMLClassMetadata;
                        }
		   
	      
	               
	   
	   
	   
	   public void setSourceUMLClassMetadata(gov.nih.nci.cadsr.umlproject.domain.ws.UMLClassMetadata sourceUMLClassMetadata){
		this.sourceUMLClassMetadata = sourceUMLClassMetadata;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof UMLAssociationMetadata) {
				UMLAssociationMetadata c =(UMLAssociationMetadata)obj; 			 
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
