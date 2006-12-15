

package gov.nih.nci.common.provenance.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class SourceReference 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String reference;
	   public  java.lang.String getReference(){
	      return reference;
	   }
	   
	   public void setReference( java.lang.String reference){
	      this.reference = reference;
	   }
	
	   
	   public java.lang.String sourceReferenceType;
	   public  java.lang.String getSourceReferenceType(){
	      return sourceReferenceType;
	   }
	   
	   public void setSourceReferenceType( java.lang.String sourceReferenceType){
	      this.sourceReferenceType = sourceReferenceType;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection provenanceCollection = new java.util.HashSet();
			public java.util.Collection getProvenanceCollection(){
	              return provenanceCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setProvenanceCollection(java.util.Collection provenanceCollection){
	   		this.provenanceCollection = provenanceCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof SourceReference) {
				SourceReference c =(SourceReference)obj; 			 
				Long thisId = getId();		
				
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
