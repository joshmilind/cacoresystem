

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Library 
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
	
	   
	   public java.lang.String cloneProducer;
	   public  java.lang.String getCloneProducer(){
	      return cloneProducer;
	   }
	   
	   public void setCloneProducer( java.lang.String cloneProducer){
	      this.cloneProducer = cloneProducer;
	   }
	
	   
	   public java.lang.Long clonesToDate;
	   public  java.lang.Long getClonesToDate(){
	      return clonesToDate;
	   }
	   
	   public void setClonesToDate( java.lang.Long clonesToDate){
	      this.clonesToDate = clonesToDate;
	   }
	
	   
	   public java.lang.String cloneVector;
	   public  java.lang.String getCloneVector(){
	      return cloneVector;
	   }
	   
	   public void setCloneVector( java.lang.String cloneVector){
	      this.cloneVector = cloneVector;
	   }
	
	   
	   public java.lang.String cloneVectorType;
	   public  java.lang.String getCloneVectorType(){
	      return cloneVectorType;
	   }
	   
	   public void setCloneVectorType( java.lang.String cloneVectorType){
	      this.cloneVectorType = cloneVectorType;
	   }
	
	   
	   public java.util.Date creationDate;
	   public  java.util.Date getCreationDate(){
	      return creationDate;
	   }
	   
	   public void setCreationDate( java.util.Date creationDate){
	      this.creationDate = creationDate;
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
	
	   
	   public java.lang.String keyword;
	   public  java.lang.String getKeyword(){
	      return keyword;
	   }
	   
	   public void setKeyword( java.lang.String keyword){
	      this.keyword = keyword;
	   }
	
	   
	   public java.lang.String labHost;
	   public  java.lang.String getLabHost(){
	      return labHost;
	   }
	   
	   public void setLabHost( java.lang.String labHost){
	      this.labHost = labHost;
	   }
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	
	   
	   public java.lang.String rsite1;
	   public  java.lang.String getRsite1(){
	      return rsite1;
	   }
	   
	   public void setRsite1( java.lang.String rsite1){
	      this.rsite1 = rsite1;
	   }
	
	   
	   public java.lang.String rsite2;
	   public  java.lang.String getRsite2(){
	      return rsite2;
	   }
	   
	   public void setRsite2( java.lang.String rsite2){
	      this.rsite2 = rsite2;
	   }
	
	   
	   public java.lang.Long sequencesToDate;
	   public  java.lang.Long getSequencesToDate(){
	      return sequencesToDate;
	   }
	   
	   public void setSequencesToDate( java.lang.Long sequencesToDate){
	      this.sequencesToDate = sequencesToDate;
	   }
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	
	   
	   public java.lang.Long uniGeneId;
	   public  java.lang.Long getUniGeneId(){
	      return uniGeneId;
	   }
	   
	   public void setUniGeneId( java.lang.Long uniGeneId){
	      this.uniGeneId = uniGeneId;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection cloneCollection = new java.util.HashSet();
			public java.util.Collection getCloneCollection(){
	              return cloneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setCloneCollection(java.util.Collection cloneCollection){
	   		this.cloneCollection = cloneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Protocol protocol;
			public gov.nih.nci.cabio.domain.ws.Protocol getProtocol(){
			  return protocol;
                        }
		   
	      
	               
	   
	   
	   
	   public void setProtocol(gov.nih.nci.cabio.domain.ws.Protocol protocol){
		this.protocol = protocol;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneCollection = new java.util.HashSet();
			public java.util.Collection getGeneCollection(){
	              return geneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneCollection(java.util.Collection geneCollection){
	   		this.geneCollection = geneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection histopathologyCollection = new java.util.HashSet();
			public java.util.Collection getHistopathologyCollection(){
	              return histopathologyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setHistopathologyCollection(java.util.Collection histopathologyCollection){
	   		this.histopathologyCollection = histopathologyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Tissue tissue;
			public gov.nih.nci.cabio.domain.ws.Tissue getTissue(){
			  return tissue;
                        }
		   
	      
	               
	   
	   
	   
	   public void setTissue(gov.nih.nci.cabio.domain.ws.Tissue tissue){
		this.tissue = tissue;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Library) {
				Library c =(Library)obj; 			 
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
