

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ProtocolAssociation 
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
	
	   
	   public java.lang.String CTEPNAME;
	   public  java.lang.String getCTEPNAME(){
	      return CTEPNAME;
	   }
	   
	   public void setCTEPNAME( java.lang.String CTEPNAME){
	      this.CTEPNAME = CTEPNAME;
	   }
	
	   
	   public java.lang.String diseaseCategory;
	   public  java.lang.String getDiseaseCategory(){
	      return diseaseCategory;
	   }
	   
	   public void setDiseaseCategory( java.lang.String diseaseCategory){
	      this.diseaseCategory = diseaseCategory;
	   }
	
	   
	   public java.lang.String diseaseSubCategory;
	   public  java.lang.String getDiseaseSubCategory(){
	      return diseaseSubCategory;
	   }
	   
	   public void setDiseaseSubCategory( java.lang.String diseaseSubCategory){
	      this.diseaseSubCategory = diseaseSubCategory;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.Long IMTCODE;
	   public  java.lang.Long getIMTCODE(){
	      return IMTCODE;
	   }
	   
	   public void setIMTCODE( java.lang.Long IMTCODE){
	      this.IMTCODE = IMTCODE;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.DiseaseOntology diseaseOntology;
			public gov.nih.nci.cabio.domain.ws.DiseaseOntology getDiseaseOntology(){
			  return diseaseOntology;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDiseaseOntology(gov.nih.nci.cabio.domain.ws.DiseaseOntology diseaseOntology){
		this.diseaseOntology = diseaseOntology;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.ClinicalTrialProtocol clinicalTrialProtocol;
			public gov.nih.nci.cabio.domain.ws.ClinicalTrialProtocol getClinicalTrialProtocol(){
			  return clinicalTrialProtocol;
                        }
		   
	      
	               
	   
	   
	   
	   public void setClinicalTrialProtocol(gov.nih.nci.cabio.domain.ws.ClinicalTrialProtocol clinicalTrialProtocol){
		this.clinicalTrialProtocol = clinicalTrialProtocol;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ProtocolAssociation) {
				ProtocolAssociation c =(ProtocolAssociation)obj; 			 
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
