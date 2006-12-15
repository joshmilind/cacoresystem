

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Vocabulary 
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
	
	   
	   public java.lang.String coreTerm;
	   public  java.lang.String getCoreTerm(){
	      return coreTerm;
	   }
	   
	   public void setCoreTerm( java.lang.String coreTerm){
	      this.coreTerm = coreTerm;
	   }
	
	   
	   public java.lang.String generalTerm;
	   public  java.lang.String getGeneralTerm(){
	      return generalTerm;
	   }
	   
	   public void setGeneralTerm( java.lang.String generalTerm){
	      this.generalTerm = generalTerm;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection anomalyCollection = new java.util.HashSet();
			public java.util.Collection getAnomalyCollection(){
	              return anomalyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAnomalyCollection(java.util.Collection anomalyCollection){
	   		this.anomalyCollection = anomalyCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Vocabulary) {
				Vocabulary c =(Vocabulary)obj; 			 
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
