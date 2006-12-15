

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Clone 
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
	
	   
	   public java.lang.Long insertSize;
	   public  java.lang.Long getInsertSize(){
	      return insertSize;
	   }
	   
	   public void setInsertSize( java.lang.Long insertSize){
	      this.insertSize = insertSize;
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
	

	
	   
	   
	   
	      
			private java.util.Collection cloneRelativeLocationCollection = new java.util.HashSet();
			public java.util.Collection getCloneRelativeLocationCollection(){
	              return cloneRelativeLocationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setCloneRelativeLocationCollection(java.util.Collection cloneRelativeLocationCollection){
	   		this.cloneRelativeLocationCollection = cloneRelativeLocationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Library library;
			public gov.nih.nci.cabio.domain.ws.Library getLibrary(){
			  return library;
                        }
		   
	      
	               
	   
	   
	   
	   public void setLibrary(gov.nih.nci.cabio.domain.ws.Library library){
		this.library = library;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection nucleicAcidSequenceCollection = new java.util.HashSet();
			public java.util.Collection getNucleicAcidSequenceCollection(){
	              return nucleicAcidSequenceCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setNucleicAcidSequenceCollection(java.util.Collection nucleicAcidSequenceCollection){
	   		this.nucleicAcidSequenceCollection = nucleicAcidSequenceCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection taxonCollection = new java.util.HashSet();
			public java.util.Collection getTaxonCollection(){
	              return taxonCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTaxonCollection(java.util.Collection taxonCollection){
	   		this.taxonCollection = taxonCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Clone) {
				Clone c =(Clone)obj; 			 
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
