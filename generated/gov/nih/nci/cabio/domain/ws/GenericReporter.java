

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class GenericReporter 
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
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
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
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection genericArrayCollection = new java.util.HashSet();
			public java.util.Collection getGenericArrayCollection(){
	              return genericArrayCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGenericArrayCollection(java.util.Collection genericArrayCollection){
	   		this.genericArrayCollection = genericArrayCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof GenericReporter) {
				GenericReporter c =(GenericReporter)obj; 			 
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
