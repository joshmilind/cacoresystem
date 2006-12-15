

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Form 
    extends gov.nih.nci.cadsr.domain.ws.FormElement
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String displayName;
	   public  java.lang.String getDisplayName(){
	      return displayName;
	   }
	   
	   public void setDisplayName( java.lang.String displayName){
	      this.displayName = displayName;
	   }
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection protocolCollection = new java.util.HashSet();
			public java.util.Collection getProtocolCollection(){
	              return protocolCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setProtocolCollection(java.util.Collection protocolCollection){
	   		this.protocolCollection = protocolCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection moduleCollection = new java.util.HashSet();
			public java.util.Collection getModuleCollection(){
	              return moduleCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setModuleCollection(java.util.Collection moduleCollection){
	   		this.moduleCollection = moduleCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Form) {
				Form c =(Form)obj; 			 
				String thisId = getId();		
				
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
