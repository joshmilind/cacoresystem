

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Histopathology 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String ageOfOnset;
	   public  java.lang.String getAgeOfOnset(){
	      return ageOfOnset;
	   }
	   
	   public void setAgeOfOnset( java.lang.String ageOfOnset){
	      this.ageOfOnset = ageOfOnset;
	   }
	
	   
	   public java.lang.String comments;
	   public  java.lang.String getComments(){
	      return comments;
	   }
	   
	   public void setComments( java.lang.String comments){
	      this.comments = comments;
	   }
	
	   
	   public java.lang.String grossDescription;
	   public  java.lang.String getGrossDescription(){
	      return grossDescription;
	   }
	   
	   public void setGrossDescription( java.lang.String grossDescription){
	      this.grossDescription = grossDescription;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String microscopicDescription;
	   public  java.lang.String getMicroscopicDescription(){
	      return microscopicDescription;
	   }
	   
	   public void setMicroscopicDescription( java.lang.String microscopicDescription){
	      this.microscopicDescription = microscopicDescription;
	   }
	
	   
	   public java.lang.String relationalOperation;
	   public  java.lang.String getRelationalOperation(){
	      return relationalOperation;
	   }
	   
	   public void setRelationalOperation( java.lang.String relationalOperation){
	      this.relationalOperation = relationalOperation;
	   }
	
	   
	   public java.lang.String survivalInfo;
	   public  java.lang.String getSurvivalInfo(){
	      return survivalInfo;
	   }
	   
	   public void setSurvivalInfo( java.lang.String survivalInfo){
	      this.survivalInfo = survivalInfo;
	   }
	
	   
	   public java.lang.Float tumorIncidenceRate;
	   public  java.lang.Float getTumorIncidenceRate(){
	      return tumorIncidenceRate;
	   }
	   
	   public void setTumorIncidenceRate( java.lang.Float tumorIncidenceRate){
	      this.tumorIncidenceRate = tumorIncidenceRate;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection anomalyCollection = new java.util.HashSet();
			public java.util.Collection getAnomalyCollection(){
	              return anomalyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAnomalyCollection(java.util.Collection anomalyCollection){
	   		this.anomalyCollection = anomalyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.DiseaseOntology diseaseOntology;
			public gov.nih.nci.cabio.domain.ws.DiseaseOntology getDiseaseOntology(){
			  return diseaseOntology;
                        }
		   
	      
	               
	   
	   
	   
	   public void setDiseaseOntology(gov.nih.nci.cabio.domain.ws.DiseaseOntology diseaseOntology){
		this.diseaseOntology = diseaseOntology;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection libraryCollection = new java.util.HashSet();
			public java.util.Collection getLibraryCollection(){
	              return libraryCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setLibraryCollection(java.util.Collection libraryCollection){
	   		this.libraryCollection = libraryCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection clinicalTrialProtocolCollection = new java.util.HashSet();
			public java.util.Collection getClinicalTrialProtocolCollection(){
	              return clinicalTrialProtocolCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setClinicalTrialProtocolCollection(java.util.Collection clinicalTrialProtocolCollection){
	   		this.clinicalTrialProtocolCollection = clinicalTrialProtocolCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneCollection = new java.util.HashSet();
			public java.util.Collection getGeneCollection(){
	              return geneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneCollection(java.util.Collection geneCollection){
	   		this.geneCollection = geneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.OrganOntology organOntology;
			public gov.nih.nci.cabio.domain.ws.OrganOntology getOrganOntology(){
			  return organOntology;
                        }
		   
	      
	               
	   
	   
	   
	   public void setOrganOntology(gov.nih.nci.cabio.domain.ws.OrganOntology organOntology){
		this.organOntology = organOntology;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection metastasisCollection = new java.util.HashSet();
			public java.util.Collection getMetastasisCollection(){
	              return metastasisCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setMetastasisCollection(java.util.Collection metastasisCollection){
	   		this.metastasisCollection = metastasisCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Histopathology) {
				Histopathology c =(Histopathology)obj; 			 
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
