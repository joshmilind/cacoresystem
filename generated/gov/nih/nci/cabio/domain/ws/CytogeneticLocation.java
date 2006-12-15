

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class CytogeneticLocation 
    extends gov.nih.nci.cabio.domain.ws.Location
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
	
	   
	   private java.lang.Long endCytobandLocId;
	   public  java.lang.Long getEndCytobandLocId(){
	      return endCytobandLocId;
	   }
	   
	   public void setEndCytobandLocId( java.lang.Long endCytobandLocId){
	      this.endCytobandLocId = endCytobandLocId;
	   }
	
	   
	   private java.lang.Long startCytobandLocId;
	   public  java.lang.Long getStartCytobandLocId(){
	      return startCytobandLocId;
	   }
	   
	   public void setStartCytobandLocId( java.lang.Long startCytobandLocId){
	      this.startCytobandLocId = startCytobandLocId;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Cytoband endCytoband;
			public gov.nih.nci.cabio.domain.ws.Cytoband getEndCytoband(){
			  return endCytoband;
                        }
		   
	      
	               
	   
	   
	   
	   public void setEndCytoband(gov.nih.nci.cabio.domain.ws.Cytoband endCytoband){
		this.endCytoband = endCytoband;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Cytoband startCytoband;
			public gov.nih.nci.cabio.domain.ws.Cytoband getStartCytoband(){
			  return startCytoband;
                        }
		   
	      
	               
	   
	   
	   
	   public void setStartCytoband(gov.nih.nci.cabio.domain.ws.Cytoband startCytoband){
		this.startCytoband = startCytoband;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof CytogeneticLocation) {
				CytogeneticLocation c =(CytogeneticLocation)obj; 			 
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
