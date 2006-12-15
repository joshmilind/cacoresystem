

package gov.nih.nci.cadsr.umlproject.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class AttributeTypeMetadata 
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
	
	   
	   public java.lang.String valueDomainDataType;
	   public  java.lang.String getValueDomainDataType(){
	      return valueDomainDataType;
	   }
	   
	   public void setValueDomainDataType( java.lang.String valueDomainDataType){
	      this.valueDomainDataType = valueDomainDataType;
	   }
	
	   
	   public java.lang.String valueDomainLongName;
	   public  java.lang.String getValueDomainLongName(){
	      return valueDomainLongName;
	   }
	   
	   public void setValueDomainLongName( java.lang.String valueDomainLongName){
	      this.valueDomainLongName = valueDomainLongName;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.ValueDomain valueDomain;
			public gov.nih.nci.cadsr.domain.ws.ValueDomain getValueDomain(){
			  return valueDomain;
                        }
		   
	      
	               
	   
	   
	   
	   public void setValueDomain(gov.nih.nci.cadsr.domain.ws.ValueDomain valueDomain){
		this.valueDomain = valueDomain;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection typeEnumerationCollection = new java.util.HashSet();
			public java.util.Collection getTypeEnumerationCollection(){
	              return typeEnumerationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTypeEnumerationCollection(java.util.Collection typeEnumerationCollection){
	   		this.typeEnumerationCollection = typeEnumerationCollection;
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
			if(obj instanceof AttributeTypeMetadata) {
				AttributeTypeMetadata c =(AttributeTypeMetadata)obj; 			 
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
