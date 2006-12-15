

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class PopulationFrequency 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String bigid;
	   public  java.lang.String getBigid(){
	      return bigid;
	   }
	   
	   public void setBigid( java.lang.String bigid){
	      this.bigid = bigid;
	   }
	
	   
	   public java.lang.String ethnicity;
	   public  java.lang.String getEthnicity(){
	      return ethnicity;
	   }
	   
	   public void setEthnicity( java.lang.String ethnicity){
	      this.ethnicity = ethnicity;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String majorAllele;
	   public  java.lang.String getMajorAllele(){
	      return majorAllele;
	   }
	   
	   public void setMajorAllele( java.lang.String majorAllele){
	      this.majorAllele = majorAllele;
	   }
	
	   
	   public java.lang.Double majorFrequency;
	   public  java.lang.Double getMajorFrequency(){
	      return majorFrequency;
	   }
	   
	   public void setMajorFrequency( java.lang.Double majorFrequency){
	      this.majorFrequency = majorFrequency;
	   }
	
	   
	   public java.lang.String minorAllele;
	   public  java.lang.String getMinorAllele(){
	      return minorAllele;
	   }
	   
	   public void setMinorAllele( java.lang.String minorAllele){
	      this.minorAllele = minorAllele;
	   }
	
	   
	   public java.lang.Double minorFrequency;
	   public  java.lang.Double getMinorFrequency(){
	      return minorFrequency;
	   }
	   
	   public void setMinorFrequency( java.lang.Double minorFrequency){
	      this.minorFrequency = minorFrequency;
	   }
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.SNP SNP;
			public gov.nih.nci.cabio.domain.ws.SNP getSNP(){
			  return SNP;
                        }
		   
	      
	               
	   
	   
	   
	   public void setSNP(gov.nih.nci.cabio.domain.ws.SNP SNP){
		this.SNP = SNP;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof PopulationFrequency) {
				PopulationFrequency c =(PopulationFrequency)obj; 			 
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
