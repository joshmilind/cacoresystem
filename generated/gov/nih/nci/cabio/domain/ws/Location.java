

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Location 
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
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Gene gene;
			public gov.nih.nci.cabio.domain.ws.Gene getGene(){
			  return gene;
                        }
		   
	      
	               
	   
	   
	   
	   public void setGene(gov.nih.nci.cabio.domain.ws.Gene gene){
		this.gene = gene;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Chromosome chromosome;
			public gov.nih.nci.cabio.domain.ws.Chromosome getChromosome(){
			  return chromosome;
                        }
		   
	      
	               
	   
	   
	   
	   public void setChromosome(gov.nih.nci.cabio.domain.ws.Chromosome chromosome){
		this.chromosome = chromosome;
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
			if(obj instanceof Location) {
				Location c =(Location)obj; 			 
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
