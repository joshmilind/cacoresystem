

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class DataElement 
    extends gov.nih.nci.cadsr.domain.ws.AdministeredComponent
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ValueDomain valueDomain;
			public gov.nih.nci.cadsr.domain.ws.ValueDomain getValueDomain(){
			  return valueDomain;
                        }
		   
	      
	               
	   
	   
	   
	   public void setValueDomain(gov.nih.nci.cadsr.domain.ws.ValueDomain valueDomain){
		this.valueDomain = valueDomain;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection questionCollection = new java.util.HashSet();
			public java.util.Collection getQuestionCollection(){
	              return questionCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setQuestionCollection(java.util.Collection questionCollection){
	   		this.questionCollection = questionCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection dataElementDerivationCollection = new java.util.HashSet();
			public java.util.Collection getDataElementDerivationCollection(){
	              return dataElementDerivationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDataElementDerivationCollection(java.util.Collection dataElementDerivationCollection){
	   		this.dataElementDerivationCollection = dataElementDerivationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection parentDataElementRelationshipsCollection = new java.util.HashSet();
			public java.util.Collection getParentDataElementRelationshipsCollection(){
	              return parentDataElementRelationshipsCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setParentDataElementRelationshipsCollection(java.util.Collection parentDataElementRelationshipsCollection){
	   		this.parentDataElementRelationshipsCollection = parentDataElementRelationshipsCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.DataElementConcept dataElementConcept;
			public gov.nih.nci.cadsr.domain.ws.DataElementConcept getDataElementConcept(){
			  return dataElementConcept;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDataElementConcept(gov.nih.nci.cadsr.domain.ws.DataElementConcept dataElementConcept){
		this.dataElementConcept = dataElementConcept;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.cadsr.domain.ws.DerivedDataElement derivedDataElement;
			public gov.nih.nci.cadsr.domain.ws.DerivedDataElement getDerivedDataElement(){
			  return derivedDataElement;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setDerivedDataElement(gov.nih.nci.cadsr.domain.ws.DerivedDataElement derivedDataElement){
		this.derivedDataElement = derivedDataElement;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection childDataElementRelationshipsCollection = new java.util.HashSet();
			public java.util.Collection getChildDataElementRelationshipsCollection(){
	              return childDataElementRelationshipsCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChildDataElementRelationshipsCollection(java.util.Collection childDataElementRelationshipsCollection){
	   		this.childDataElementRelationshipsCollection = childDataElementRelationshipsCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof DataElement) {
				DataElement c =(DataElement)obj; 			 
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
