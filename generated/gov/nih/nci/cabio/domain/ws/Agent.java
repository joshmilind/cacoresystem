

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Agent 
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
	
	   
	   public java.lang.String comment;
	   public  java.lang.String getComment(){
	      return comment;
	   }
	   
	   public void setComment( java.lang.String comment){
	      this.comment = comment;
	   }
	
	   
	   public java.lang.String EVSId;
	   public  java.lang.String getEVSId(){
	      return EVSId;
	   }
	   
	   public void setEVSId( java.lang.String EVSId){
	      this.EVSId = EVSId;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.Boolean isCMAPAgent;
	   public  java.lang.Boolean getIsCMAPAgent(){
	      return isCMAPAgent;
	   }
	   
	   public void setIsCMAPAgent( java.lang.Boolean isCMAPAgent){
	      this.isCMAPAgent = isCMAPAgent;
	   }
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	
	   
	   public java.lang.Long NSCNumber;
	   public  java.lang.Long getNSCNumber(){
	      return NSCNumber;
	   }
	   
	   public void setNSCNumber( java.lang.Long NSCNumber){
	      this.NSCNumber = NSCNumber;
	   }
	
	   
	   public java.lang.String source;
	   public  java.lang.String getSource(){
	      return source;
	   }
	   
	   public void setSource( java.lang.String source){
	      this.source = source;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection clinicalTrialProtocolCollection = new java.util.HashSet();
			public java.util.Collection getClinicalTrialProtocolCollection(){
	              return clinicalTrialProtocolCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setClinicalTrialProtocolCollection(java.util.Collection clinicalTrialProtocolCollection){
	   		this.clinicalTrialProtocolCollection = clinicalTrialProtocolCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection targetCollection = new java.util.HashSet();
			public java.util.Collection getTargetCollection(){
	              return targetCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTargetCollection(java.util.Collection targetCollection){
	   		this.targetCollection = targetCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Agent) {
				Agent c =(Agent)obj; 			 
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
