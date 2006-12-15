

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ObjectClassRelationship 
    extends gov.nih.nci.cadsr.domain.ws.AdministeredComponent
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.Integer dimensionality;
	   public  java.lang.Integer getDimensionality(){
	      return dimensionality;
	   }
	   
	   public void setDimensionality( java.lang.Integer dimensionality){
	      this.dimensionality = dimensionality;
	   }
	
	   
	   public java.lang.String direction;
	   public  java.lang.String getDirection(){
	      return direction;
	   }
	   
	   public void setDirection( java.lang.String direction){
	      this.direction = direction;
	   }
	
	   
	   public java.lang.Integer displayOrder;
	   public  java.lang.Integer getDisplayOrder(){
	      return displayOrder;
	   }
	   
	   public void setDisplayOrder( java.lang.Integer displayOrder){
	      this.displayOrder = displayOrder;
	   }
	
	   
	   public java.lang.String isArray;
	   public  java.lang.String getIsArray(){
	      return isArray;
	   }
	   
	   public void setIsArray( java.lang.String isArray){
	      this.isArray = isArray;
	   }
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	
	   
	   public java.lang.Integer sourceHighMultiplicity;
	   public  java.lang.Integer getSourceHighMultiplicity(){
	      return sourceHighMultiplicity;
	   }
	   
	   public void setSourceHighMultiplicity( java.lang.Integer sourceHighMultiplicity){
	      this.sourceHighMultiplicity = sourceHighMultiplicity;
	   }
	
	   
	   public java.lang.Integer sourceLowMultiplicity;
	   public  java.lang.Integer getSourceLowMultiplicity(){
	      return sourceLowMultiplicity;
	   }
	   
	   public void setSourceLowMultiplicity( java.lang.Integer sourceLowMultiplicity){
	      this.sourceLowMultiplicity = sourceLowMultiplicity;
	   }
	
	   
	   public java.lang.String sourceRole;
	   public  java.lang.String getSourceRole(){
	      return sourceRole;
	   }
	   
	   public void setSourceRole( java.lang.String sourceRole){
	      this.sourceRole = sourceRole;
	   }
	
	   
	   public java.lang.Integer targetHighMultiplicity;
	   public  java.lang.Integer getTargetHighMultiplicity(){
	      return targetHighMultiplicity;
	   }
	   
	   public void setTargetHighMultiplicity( java.lang.Integer targetHighMultiplicity){
	      this.targetHighMultiplicity = targetHighMultiplicity;
	   }
	
	   
	   public java.lang.Integer targetLowMultiplicity;
	   public  java.lang.Integer getTargetLowMultiplicity(){
	      return targetLowMultiplicity;
	   }
	   
	   public void setTargetLowMultiplicity( java.lang.Integer targetLowMultiplicity){
	      this.targetLowMultiplicity = targetLowMultiplicity;
	   }
	
	   
	   public java.lang.String targetRole;
	   public  java.lang.String getTargetRole(){
	      return targetRole;
	   }
	   
	   public void setTargetRole( java.lang.String targetRole){
	      this.targetRole = targetRole;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule targetConceptDerivationRule;
			public gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule getTargetConceptDerivationRule(){
			  return targetConceptDerivationRule;
                        }
		   
	      
	               
	   
	   
	   
	   public void setTargetConceptDerivationRule(gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule targetConceptDerivationRule){
		this.targetConceptDerivationRule = targetConceptDerivationRule;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.AdministeredComponentClassSchemeItem targetObjectClassClassification;
			public gov.nih.nci.cadsr.domain.ws.AdministeredComponentClassSchemeItem getTargetObjectClassClassification(){
			  return targetObjectClassClassification;
                        }
		   
	      
	               
	   
	   
	   
	   public void setTargetObjectClassClassification(gov.nih.nci.cadsr.domain.ws.AdministeredComponentClassSchemeItem targetObjectClassClassification){
		this.targetObjectClassClassification = targetObjectClassClassification;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ObjectClass targetObjectClass;
			public gov.nih.nci.cadsr.domain.ws.ObjectClass getTargetObjectClass(){
			  return targetObjectClass;
                        }
		   
	      
	               
	   
	   
	   
	   public void setTargetObjectClass(gov.nih.nci.cadsr.domain.ws.ObjectClass targetObjectClass){
		this.targetObjectClass = targetObjectClass;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.AdministeredComponentClassSchemeItem sourceObjectClassClassification;
			public gov.nih.nci.cadsr.domain.ws.AdministeredComponentClassSchemeItem getSourceObjectClassClassification(){
			  return sourceObjectClassClassification;
                        }
		   
	      
	               
	   
	   
	   
	   public void setSourceObjectClassClassification(gov.nih.nci.cadsr.domain.ws.AdministeredComponentClassSchemeItem sourceObjectClassClassification){
		this.sourceObjectClassClassification = sourceObjectClassClassification;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule sourceConceptDerivationRule;
			public gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule getSourceConceptDerivationRule(){
			  return sourceConceptDerivationRule;
                        }
		   
	      
	               
	   
	   
	   
	   public void setSourceConceptDerivationRule(gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule sourceConceptDerivationRule){
		this.sourceConceptDerivationRule = sourceConceptDerivationRule;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule conceptDerivationRule;
			public gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule getConceptDerivationRule(){
			  return conceptDerivationRule;
                        }
		   
	      
	               
	   
	   
	   
	   public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule conceptDerivationRule){
		this.conceptDerivationRule = conceptDerivationRule;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ObjectClass sourceObjectClass;
			public gov.nih.nci.cadsr.domain.ws.ObjectClass getSourceObjectClass(){
			  return sourceObjectClass;
                        }
		   
	      
	               
	   
	   
	   
	   public void setSourceObjectClass(gov.nih.nci.cadsr.domain.ws.ObjectClass sourceObjectClass){
		this.sourceObjectClass = sourceObjectClass;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ObjectClassRelationship) {
				ObjectClassRelationship c =(ObjectClassRelationship)obj; 			 
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
