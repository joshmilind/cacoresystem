

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Chromosome 
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
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String number;
	   public  java.lang.String getNumber(){
	      return number;
	   }
	   
	   public void setNumber( java.lang.String number){
	      this.number = number;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection geneCollection = new java.util.HashSet();
			public java.util.Collection getGeneCollection(){
	              return geneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneCollection(java.util.Collection geneCollection){
	   		this.geneCollection = geneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Taxon taxon;
			public gov.nih.nci.cabio.domain.ws.Taxon getTaxon(){
			  return taxon;
                        }
		   
	      
	               
	   
	   
	   
	   public void setTaxon(gov.nih.nci.cabio.domain.ws.Taxon taxon){
		this.taxon = taxon;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection locationCollection = new java.util.HashSet();
			public java.util.Collection getLocationCollection(){
	              return locationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setLocationCollection(java.util.Collection locationCollection){
	   		this.locationCollection = locationCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Chromosome) {
				Chromosome c =(Chromosome)obj; 			 
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
