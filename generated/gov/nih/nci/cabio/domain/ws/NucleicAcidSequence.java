

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class NucleicAcidSequence 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String accessionNumber;
	   public  java.lang.String getAccessionNumber(){
	      return accessionNumber;
	   }
	   
	   public void setAccessionNumber( java.lang.String accessionNumber){
	      this.accessionNumber = accessionNumber;
	   }
	
	   
	   public java.lang.String accessionNumberVersion;
	   public  java.lang.String getAccessionNumberVersion(){
	      return accessionNumberVersion;
	   }
	   
	   public void setAccessionNumberVersion( java.lang.String accessionNumberVersion){
	      this.accessionNumberVersion = accessionNumberVersion;
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
	
	   
	   public java.lang.Long length;
	   public  java.lang.Long getLength(){
	      return length;
	   }
	   
	   public void setLength( java.lang.Long length){
	      this.length = length;
	   }
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	
	   
	   public java.lang.String value;
	   public  java.lang.String getValue(){
	      return value;
	   }
	   
	   public void setValue( java.lang.String value){
	      this.value = value;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection databaseCrossReferenceCollection = new java.util.HashSet();
			public java.util.Collection getDatabaseCrossReferenceCollection(){
	              return databaseCrossReferenceCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDatabaseCrossReferenceCollection(java.util.Collection databaseCrossReferenceCollection){
	   		this.databaseCrossReferenceCollection = databaseCrossReferenceCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.cabio.domain.ws.CloneRelativeLocation cloneRelativeLocation;
			public gov.nih.nci.cabio.domain.ws.CloneRelativeLocation getCloneRelativeLocation(){
			  return cloneRelativeLocation;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setCloneRelativeLocation(gov.nih.nci.cabio.domain.ws.CloneRelativeLocation cloneRelativeLocation){
		this.cloneRelativeLocation = cloneRelativeLocation;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection geneCollection = new java.util.HashSet();
			public java.util.Collection getGeneCollection(){
	              return geneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneCollection(java.util.Collection geneCollection){
	   		this.geneCollection = geneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection locationCollection = new java.util.HashSet();
			public java.util.Collection getLocationCollection(){
	              return locationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setLocationCollection(java.util.Collection locationCollection){
	   		this.locationCollection = locationCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof NucleicAcidSequence) {
				NucleicAcidSequence c =(NucleicAcidSequence)obj; 			 
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
