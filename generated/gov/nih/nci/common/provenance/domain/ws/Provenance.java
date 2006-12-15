

package gov.nih.nci.common.provenance.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Provenance 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String evidenceCode;
	   public  java.lang.String getEvidenceCode(){
	      return evidenceCode;
	   }
	   
	   public void setEvidenceCode( java.lang.String evidenceCode){
	      this.evidenceCode = evidenceCode;
	   }
	
	   
	   public java.lang.String fullyQualifiedClassName;
	   public  java.lang.String getFullyQualifiedClassName(){
	      return fullyQualifiedClassName;
	   }
	   
	   public void setFullyQualifiedClassName( java.lang.String fullyQualifiedClassName){
	      this.fullyQualifiedClassName = fullyQualifiedClassName;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   private java.lang.Long immediateSourceId;
	   public  java.lang.Long getImmediateSourceId(){
	      return immediateSourceId;
	   }
	   
	   public void setImmediateSourceId( java.lang.Long immediateSourceId){
	      this.immediateSourceId = immediateSourceId;
	   }
	
	   
	   public java.lang.String objectIdentifier;
	   public  java.lang.String getObjectIdentifier(){
	      return objectIdentifier;
	   }
	   
	   public void setObjectIdentifier( java.lang.String objectIdentifier){
	      this.objectIdentifier = objectIdentifier;
	   }
	
	   
	   private java.lang.Long originalSourceId;
	   public  java.lang.Long getOriginalSourceId(){
	      return originalSourceId;
	   }
	   
	   public void setOriginalSourceId( java.lang.Long originalSourceId){
	      this.originalSourceId = originalSourceId;
	   }
	
	   
	   private java.lang.Long supplyingSourceId;
	   public  java.lang.Long getSupplyingSourceId(){
	      return supplyingSourceId;
	   }
	   
	   public void setSupplyingSourceId( java.lang.Long supplyingSourceId){
	      this.supplyingSourceId = supplyingSourceId;
	   }
	
	   
	   public java.lang.String transformation;
	   public  java.lang.String getTransformation(){
	      return transformation;
	   }
	   
	   public void setTransformation( java.lang.String transformation){
	      this.transformation = transformation;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.common.provenance.domain.ws.Source supplyingSource;
			public gov.nih.nci.common.provenance.domain.ws.Source getSupplyingSource(){
			  return supplyingSource;
                        }
		   
	      
	               
	   
	   
	   
	   public void setSupplyingSource(gov.nih.nci.common.provenance.domain.ws.Source supplyingSource){
		this.supplyingSource = supplyingSource;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.common.provenance.domain.ws.SourceReference sourceReference;
			public gov.nih.nci.common.provenance.domain.ws.SourceReference getSourceReference(){
			  return sourceReference;
                        }
		   
	      
	               
	   
	   
	   
	   public void setSourceReference(gov.nih.nci.common.provenance.domain.ws.SourceReference sourceReference){
		this.sourceReference = sourceReference;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.common.provenance.domain.ws.Source immediateSource;
			public gov.nih.nci.common.provenance.domain.ws.Source getImmediateSource(){
			  return immediateSource;
                        }
		   
	      
	               
	   
	   
	   
	   public void setImmediateSource(gov.nih.nci.common.provenance.domain.ws.Source immediateSource){
		this.immediateSource = immediateSource;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.common.provenance.domain.ws.Source originalSource;
			public gov.nih.nci.common.provenance.domain.ws.Source getOriginalSource(){
			  return originalSource;
                        }
		   
	      
	               
	   
	   
	   
	   public void setOriginalSource(gov.nih.nci.common.provenance.domain.ws.Source originalSource){
		this.originalSource = originalSource;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Provenance) {
				Provenance c =(Provenance)obj; 			 
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
