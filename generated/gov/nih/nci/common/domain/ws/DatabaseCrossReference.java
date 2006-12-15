

package gov.nih.nci.common.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class DatabaseCrossReference 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String crossReferenceId;
	   public  java.lang.String getCrossReferenceId(){
	      return crossReferenceId;
	   }
	   
	   public void setCrossReferenceId( java.lang.String crossReferenceId){
	      this.crossReferenceId = crossReferenceId;
	   }
	
	   
	   public java.lang.String dataSourceName;
	   public  java.lang.String getDataSourceName(){
	      return dataSourceName;
	   }
	   
	   public void setDataSourceName( java.lang.String dataSourceName){
	      this.dataSourceName = dataSourceName;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String sourceType;
	   public  java.lang.String getSourceType(){
	      return sourceType;
	   }
	   
	   public void setSourceType( java.lang.String sourceType){
	      this.sourceType = sourceType;
	   }
	
	   
	   public java.lang.String summary;
	   public  java.lang.String getSummary(){
	      return summary;
	   }
	   
	   public void setSummary( java.lang.String summary){
	      this.summary = summary;
	   }
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Gene gene;
			public gov.nih.nci.cabio.domain.ws.Gene getGene(){
			  return gene;
                        }
		   
	      
	               
	   
	   
	   
	   public void setGene(gov.nih.nci.cabio.domain.ws.Gene gene){
		this.gene = gene;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.SNP SNP;
			public gov.nih.nci.cabio.domain.ws.SNP getSNP(){
			  return SNP;
                        }
		   
	      
	               
	   
	   
	   
	   public void setSNP(gov.nih.nci.cabio.domain.ws.SNP SNP){
		this.SNP = SNP;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.NucleicAcidSequence nucleicAcidSequence;
			public gov.nih.nci.cabio.domain.ws.NucleicAcidSequence getNucleicAcidSequence(){
			  return nucleicAcidSequence;
                        }
		   
	      
	               
	   
	   
	   
	   public void setNucleicAcidSequence(gov.nih.nci.cabio.domain.ws.NucleicAcidSequence nucleicAcidSequence){
		this.nucleicAcidSequence = nucleicAcidSequence;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof DatabaseCrossReference) {
				DatabaseCrossReference c =(DatabaseCrossReference)obj; 			 
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
