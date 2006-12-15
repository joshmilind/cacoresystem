

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ClassificationScheme 
    extends gov.nih.nci.cadsr.domain.ws.AdministeredComponent
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String labelTypeFlag;
	   public  java.lang.String getLabelTypeFlag(){
	      return labelTypeFlag;
	   }
	   
	   public void setLabelTypeFlag( java.lang.String labelTypeFlag){
	      this.labelTypeFlag = labelTypeFlag;
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
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection parentClassificationSchemeRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getParentClassificationSchemeRelationshipCollection(){
	              return parentClassificationSchemeRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setParentClassificationSchemeRelationshipCollection(java.util.Collection parentClassificationSchemeRelationshipCollection){
	   		this.parentClassificationSchemeRelationshipCollection = parentClassificationSchemeRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection childClassificationScheme = new java.util.HashSet();
			public java.util.Collection getChildClassificationScheme(){
	              return childClassificationScheme;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChildClassificationScheme(java.util.Collection childClassificationScheme){
	   		this.childClassificationScheme = childClassificationScheme;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection childClassificationSchemeRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getChildClassificationSchemeRelationshipCollection(){
	              return childClassificationSchemeRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChildClassificationSchemeRelationshipCollection(java.util.Collection childClassificationSchemeRelationshipCollection){
	   		this.childClassificationSchemeRelationshipCollection = childClassificationSchemeRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule conceptDerivationRule;
			public gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule getConceptDerivationRule(){
			  return conceptDerivationRule;
                        }
		   
	      
	               
	   
	   
	   
	   public void setConceptDerivationRule(gov.nih.nci.cadsr.domain.ws.ConceptDerivationRule conceptDerivationRule){
		this.conceptDerivationRule = conceptDerivationRule;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ClassificationScheme parentClassificationScheme;
			public gov.nih.nci.cadsr.domain.ws.ClassificationScheme getParentClassificationScheme(){
			  return parentClassificationScheme;
                        }
		   
	      
	               
	   
	   
	   
	   public void setParentClassificationScheme(gov.nih.nci.cadsr.domain.ws.ClassificationScheme parentClassificationScheme){
		this.parentClassificationScheme = parentClassificationScheme;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ClassificationScheme) {
				ClassificationScheme c =(ClassificationScheme)obj; 			 
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
