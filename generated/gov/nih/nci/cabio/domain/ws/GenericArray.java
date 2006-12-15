

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class GenericArray 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String arrayName;
	   public  java.lang.String getArrayName(){
	      return arrayName;
	   }
	   
	   public void setArrayName( java.lang.String arrayName){
	      this.arrayName = arrayName;
	   }
	
	   
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
	
	   
	   public java.lang.String platform;
	   public  java.lang.String getPlatform(){
	      return platform;
	   }
	   
	   public void setPlatform( java.lang.String platform){
	      this.platform = platform;
	   }
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection genericReporterCollection = new java.util.HashSet();
			public java.util.Collection getGenericReporterCollection(){
	              return genericReporterCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGenericReporterCollection(java.util.Collection genericReporterCollection){
	   		this.genericReporterCollection = genericReporterCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof GenericArray) {
				GenericArray c =(GenericArray)obj; 			 
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
