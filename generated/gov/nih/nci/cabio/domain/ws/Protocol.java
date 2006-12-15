

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Protocol 
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
	

	
	   
	   
	   
	      
			private java.util.Collection libraryCollection = new java.util.HashSet();
			public java.util.Collection getLibraryCollection(){
	              return libraryCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setLibraryCollection(java.util.Collection libraryCollection){
	   		this.libraryCollection = libraryCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection tissueCollection = new java.util.HashSet();
			public java.util.Collection getTissueCollection(){
	              return tissueCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTissueCollection(java.util.Collection tissueCollection){
	   		this.tissueCollection = tissueCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Protocol) {
				Protocol c =(Protocol)obj; 			 
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
