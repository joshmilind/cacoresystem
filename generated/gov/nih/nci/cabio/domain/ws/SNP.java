

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class SNP 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String alleleA;
	   public  java.lang.String getAlleleA(){
	      return alleleA;
	   }
	   
	   public void setAlleleA( java.lang.String alleleA){
	      this.alleleA = alleleA;
	   }
	
	   
	   public java.lang.String alleleB;
	   public  java.lang.String getAlleleB(){
	      return alleleB;
	   }
	   
	   public void setAlleleB( java.lang.String alleleB){
	      this.alleleB = alleleB;
	   }
	
	   
	   public java.lang.String bigid;
	   public  java.lang.String getBigid(){
	      return bigid;
	   }
	   
	   public void setBigid( java.lang.String bigid){
	      this.bigid = bigid;
	   }
	
	   
	   public java.lang.String DBSNPID;
	   public  java.lang.String getDBSNPID(){
	      return DBSNPID;
	   }
	   
	   public void setDBSNPID( java.lang.String DBSNPID){
	      this.DBSNPID = DBSNPID;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String validationStatus;
	   public  java.lang.String getValidationStatus(){
	      return validationStatus;
	   }
	   
	   public void setValidationStatus( java.lang.String validationStatus){
	      this.validationStatus = validationStatus;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection databaseCrossReferenceCollection = new java.util.HashSet();
			public java.util.Collection getDatabaseCrossReferenceCollection(){
	              return databaseCrossReferenceCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDatabaseCrossReferenceCollection(java.util.Collection databaseCrossReferenceCollection){
	   		this.databaseCrossReferenceCollection = databaseCrossReferenceCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection locationCollection = new java.util.HashSet();
			public java.util.Collection getLocationCollection(){
	              return locationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setLocationCollection(java.util.Collection locationCollection){
	   		this.locationCollection = locationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection populationFrequencyCollection = new java.util.HashSet();
			public java.util.Collection getPopulationFrequencyCollection(){
	              return populationFrequencyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setPopulationFrequencyCollection(java.util.Collection populationFrequencyCollection){
	   		this.populationFrequencyCollection = populationFrequencyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneRelativeLocationCollection = new java.util.HashSet();
			public java.util.Collection getGeneRelativeLocationCollection(){
	              return geneRelativeLocationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneRelativeLocationCollection(java.util.Collection geneRelativeLocationCollection){
	   		this.geneRelativeLocationCollection = geneRelativeLocationCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof SNP) {
				SNP c =(SNP)obj; 			 
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
