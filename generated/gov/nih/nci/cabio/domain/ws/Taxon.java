

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Taxon 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String abbreviation;
	   public  java.lang.String getAbbreviation(){
	      return abbreviation;
	   }
	   
	   public void setAbbreviation( java.lang.String abbreviation){
	      this.abbreviation = abbreviation;
	   }
	
	   
	   public java.lang.String bigid;
	   public  java.lang.String getBigid(){
	      return bigid;
	   }
	   
	   public void setBigid( java.lang.String bigid){
	      this.bigid = bigid;
	   }
	
	   
	   public java.lang.String commonName;
	   public  java.lang.String getCommonName(){
	      return commonName;
	   }
	   
	   public void setCommonName( java.lang.String commonName){
	      this.commonName = commonName;
	   }
	
	   
	   public java.lang.String ethnicityStrain;
	   public  java.lang.String getEthnicityStrain(){
	      return ethnicityStrain;
	   }
	   
	   public void setEthnicityStrain( java.lang.String ethnicityStrain){
	      this.ethnicityStrain = ethnicityStrain;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String scientificName;
	   public  java.lang.String getScientificName(){
	      return scientificName;
	   }
	   
	   public void setScientificName( java.lang.String scientificName){
	      this.scientificName = scientificName;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection cloneCollection = new java.util.HashSet();
			public java.util.Collection getCloneCollection(){
	              return cloneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setCloneCollection(java.util.Collection cloneCollection){
	   		this.cloneCollection = cloneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection tissueCollection = new java.util.HashSet();
			public java.util.Collection getTissueCollection(){
	              return tissueCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTissueCollection(java.util.Collection tissueCollection){
	   		this.tissueCollection = tissueCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneCollection = new java.util.HashSet();
			public java.util.Collection getGeneCollection(){
	              return geneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneCollection(java.util.Collection geneCollection){
	   		this.geneCollection = geneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection pathwayCollection = new java.util.HashSet();
			public java.util.Collection getPathwayCollection(){
	              return pathwayCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setPathwayCollection(java.util.Collection pathwayCollection){
	   		this.pathwayCollection = pathwayCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection chromosomeCollection = new java.util.HashSet();
			public java.util.Collection getChromosomeCollection(){
	              return chromosomeCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChromosomeCollection(java.util.Collection chromosomeCollection){
	   		this.chromosomeCollection = chromosomeCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection proteinCollection = new java.util.HashSet();
			public java.util.Collection getProteinCollection(){
	              return proteinCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setProteinCollection(java.util.Collection proteinCollection){
	   		this.proteinCollection = proteinCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Taxon) {
				Taxon c =(Taxon)obj; 			 
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
