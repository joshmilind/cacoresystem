

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class PhysicalLocation 
    extends gov.nih.nci.cabio.domain.ws.Location
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.Long chromosomalEndPosition;
	   public  java.lang.Long getChromosomalEndPosition(){
	      return chromosomalEndPosition;
	   }
	   
	   public void setChromosomalEndPosition( java.lang.Long chromosomalEndPosition){
	      this.chromosomalEndPosition = chromosomalEndPosition;
	   }
	
	   
	   public java.lang.Long chromosomalStartPosition;
	   public  java.lang.Long getChromosomalStartPosition(){
	      return chromosomalStartPosition;
	   }
	   
	   public void setChromosomalStartPosition( java.lang.Long chromosomalStartPosition){
	      this.chromosomalStartPosition = chromosomalStartPosition;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection cytobandCollection = new java.util.HashSet();
			public java.util.Collection getCytobandCollection(){
	              return cytobandCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setCytobandCollection(java.util.Collection cytobandCollection){
	   		this.cytobandCollection = cytobandCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof PhysicalLocation) {
				PhysicalLocation c =(PhysicalLocation)obj; 			 
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