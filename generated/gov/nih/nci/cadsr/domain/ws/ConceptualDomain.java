

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ConceptualDomain 
    extends gov.nih.nci.cadsr.domain.ws.AdministeredComponent
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String dimensionality;
	   public  java.lang.String getDimensionality(){
	      return dimensionality;
	   }
	   
	   public void setDimensionality( java.lang.String dimensionality){
	      this.dimensionality = dimensionality;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection valueMeaningCollection = new java.util.HashSet();
			public java.util.Collection getValueMeaningCollection(){
	              return valueMeaningCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setValueMeaningCollection(java.util.Collection valueMeaningCollection){
	   		this.valueMeaningCollection = valueMeaningCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection valueDomainCollection = new java.util.HashSet();
			public java.util.Collection getValueDomainCollection(){
	              return valueDomainCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setValueDomainCollection(java.util.Collection valueDomainCollection){
	   		this.valueDomainCollection = valueDomainCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection dataElementConceptCollection = new java.util.HashSet();
			public java.util.Collection getDataElementConceptCollection(){
	              return dataElementConceptCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDataElementConceptCollection(java.util.Collection dataElementConceptCollection){
	   		this.dataElementConceptCollection = dataElementConceptCollection;
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
			if(obj instanceof ConceptualDomain) {
				ConceptualDomain c =(ConceptualDomain)obj; 			 
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
