

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class CloneRelativeLocation 
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
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Clone clone;
			public gov.nih.nci.cabio.domain.ws.Clone getClone(){
			  return clone;
                        }
		   
	      
	               
	   
	   
	   
	   public void setClone(gov.nih.nci.cabio.domain.ws.Clone clone){
		this.clone = clone;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.cabio.domain.ws.NucleicAcidSequence nucleicAcidSequence;
			public gov.nih.nci.cabio.domain.ws.NucleicAcidSequence getNucleicAcidSequence(){
			  return nucleicAcidSequence;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setNucleicAcidSequence(gov.nih.nci.cabio.domain.ws.NucleicAcidSequence nucleicAcidSequence){
		this.nucleicAcidSequence = nucleicAcidSequence;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof CloneRelativeLocation) {
				CloneRelativeLocation c =(CloneRelativeLocation)obj; 			 
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