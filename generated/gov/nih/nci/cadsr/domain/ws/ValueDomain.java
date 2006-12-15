

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ValueDomain 
    extends gov.nih.nci.cadsr.domain.ws.AdministeredComponent
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String characterSetName;
	   public  java.lang.String getCharacterSetName(){
	      return characterSetName;
	   }
	   
	   public void setCharacterSetName( java.lang.String characterSetName){
	      this.characterSetName = characterSetName;
	   }
	
	   
	   public java.lang.String datatypeAnnotation;
	   public  java.lang.String getDatatypeAnnotation(){
	      return datatypeAnnotation;
	   }
	   
	   public void setDatatypeAnnotation( java.lang.String datatypeAnnotation){
	      this.datatypeAnnotation = datatypeAnnotation;
	   }
	
	   
	   public java.lang.String datatypeDescription;
	   public  java.lang.String getDatatypeDescription(){
	      return datatypeDescription;
	   }
	   
	   public void setDatatypeDescription( java.lang.String datatypeDescription){
	      this.datatypeDescription = datatypeDescription;
	   }
	
	   
	   public java.lang.String datatypeIsCodegenCompatible;
	   public  java.lang.String getDatatypeIsCodegenCompatible(){
	      return datatypeIsCodegenCompatible;
	   }
	   
	   public void setDatatypeIsCodegenCompatible( java.lang.String datatypeIsCodegenCompatible){
	      this.datatypeIsCodegenCompatible = datatypeIsCodegenCompatible;
	   }
	
	   
	   public java.lang.String datatypeName;
	   public  java.lang.String getDatatypeName(){
	      return datatypeName;
	   }
	   
	   public void setDatatypeName( java.lang.String datatypeName){
	      this.datatypeName = datatypeName;
	   }
	
	   
	   public java.lang.String datatypeSchemeReference;
	   public  java.lang.String getDatatypeSchemeReference(){
	      return datatypeSchemeReference;
	   }
	   
	   public void setDatatypeSchemeReference( java.lang.String datatypeSchemeReference){
	      this.datatypeSchemeReference = datatypeSchemeReference;
	   }
	
	   
	   public java.lang.Integer decimalPlace;
	   public  java.lang.Integer getDecimalPlace(){
	      return decimalPlace;
	   }
	   
	   public void setDecimalPlace( java.lang.Integer decimalPlace){
	      this.decimalPlace = decimalPlace;
	   }
	
	   
	   public java.lang.String formatName;
	   public  java.lang.String getFormatName(){
	      return formatName;
	   }
	   
	   public void setFormatName( java.lang.String formatName){
	      this.formatName = formatName;
	   }
	
	   
	   public java.lang.String highValueNumber;
	   public  java.lang.String getHighValueNumber(){
	      return highValueNumber;
	   }
	   
	   public void setHighValueNumber( java.lang.String highValueNumber){
	      this.highValueNumber = highValueNumber;
	   }
	
	   
	   public java.lang.String lowValueNumber;
	   public  java.lang.String getLowValueNumber(){
	      return lowValueNumber;
	   }
	   
	   public void setLowValueNumber( java.lang.String lowValueNumber){
	      this.lowValueNumber = lowValueNumber;
	   }
	
	   
	   public java.lang.Integer maximumLengthNumber;
	   public  java.lang.Integer getMaximumLengthNumber(){
	      return maximumLengthNumber;
	   }
	   
	   public void setMaximumLengthNumber( java.lang.Integer maximumLengthNumber){
	      this.maximumLengthNumber = maximumLengthNumber;
	   }
	
	   
	   public java.lang.Integer minimumLengthNumber;
	   public  java.lang.Integer getMinimumLengthNumber(){
	      return minimumLengthNumber;
	   }
	   
	   public void setMinimumLengthNumber( java.lang.Integer minimumLengthNumber){
	      this.minimumLengthNumber = minimumLengthNumber;
	   }
	
	   
	   public java.lang.String UOMName;
	   public  java.lang.String getUOMName(){
	      return UOMName;
	   }
	   
	   public void setUOMName( java.lang.String UOMName){
	      this.UOMName = UOMName;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection parentValueDomainRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getParentValueDomainRelationshipCollection(){
	              return parentValueDomainRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setParentValueDomainRelationshipCollection(java.util.Collection parentValueDomainRelationshipCollection){
	   		this.parentValueDomainRelationshipCollection = parentValueDomainRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection questionCollection = new java.util.HashSet();
			public java.util.Collection getQuestionCollection(){
	              return questionCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setQuestionCollection(java.util.Collection questionCollection){
	   		this.questionCollection = questionCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ConceptualDomain conceptualDomain;
			public gov.nih.nci.cadsr.domain.ws.ConceptualDomain getConceptualDomain(){
			  return conceptualDomain;
                        }
		   
	      
	               
	   
	   
	   
	   public void setConceptualDomain(gov.nih.nci.cadsr.domain.ws.ConceptualDomain conceptualDomain){
		this.conceptualDomain = conceptualDomain;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection dataElementCollection = new java.util.HashSet();
			public java.util.Collection getDataElementCollection(){
	              return dataElementCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDataElementCollection(java.util.Collection dataElementCollection){
	   		this.dataElementCollection = dataElementCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection childValueDomainRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getChildValueDomainRelationshipCollection(){
	              return childValueDomainRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChildValueDomainRelationshipCollection(java.util.Collection childValueDomainRelationshipCollection){
	   		this.childValueDomainRelationshipCollection = childValueDomainRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Representation represention;
			public gov.nih.nci.cadsr.domain.ws.Representation getRepresention(){
			  return represention;
                        }
		   
	      
	               
	   
	   
	   
	   public void setRepresention(gov.nih.nci.cadsr.domain.ws.Representation represention){
		this.represention = represention;
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
			if(obj instanceof ValueDomain) {
				ValueDomain c =(ValueDomain)obj; 			 
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
