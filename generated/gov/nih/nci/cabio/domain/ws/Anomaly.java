

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Anomaly 
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
	
	   
	   public java.lang.String description;
	   public  java.lang.String getDescription(){
	      return description;
	   }
	   
	   public void setDescription( java.lang.String description){
	      this.description = description;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Histopathology histopathology;
			public gov.nih.nci.cabio.domain.ws.Histopathology getHistopathology(){
			  return histopathology;
                        }
		   
	      
	               
	   
	   
	   
	   public void setHistopathology(gov.nih.nci.cabio.domain.ws.Histopathology histopathology){
		this.histopathology = histopathology;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection organOntologyCollection = new java.util.HashSet();
			public java.util.Collection getOrganOntologyCollection(){
	              return organOntologyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setOrganOntologyCollection(java.util.Collection organOntologyCollection){
	   		this.organOntologyCollection = organOntologyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection vocabularyCollection = new java.util.HashSet();
			public java.util.Collection getVocabularyCollection(){
	              return vocabularyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setVocabularyCollection(java.util.Collection vocabularyCollection){
	   		this.vocabularyCollection = vocabularyCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Anomaly) {
				Anomaly c =(Anomaly)obj; 			 
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
