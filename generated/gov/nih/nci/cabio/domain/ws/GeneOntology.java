

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class GeneOntology 
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
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection parentGeneOntologyRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getParentGeneOntologyRelationshipCollection(){
	              return parentGeneOntologyRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setParentGeneOntologyRelationshipCollection(java.util.Collection parentGeneOntologyRelationshipCollection){
	   		this.parentGeneOntologyRelationshipCollection = parentGeneOntologyRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneCollection = new java.util.HashSet();
			public java.util.Collection getGeneCollection(){
	              return geneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneCollection(java.util.Collection geneCollection){
	   		this.geneCollection = geneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection childGeneOntologyRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getChildGeneOntologyRelationshipCollection(){
	              return childGeneOntologyRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChildGeneOntologyRelationshipCollection(java.util.Collection childGeneOntologyRelationshipCollection){
	   		this.childGeneOntologyRelationshipCollection = childGeneOntologyRelationshipCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof GeneOntology) {
				GeneOntology c =(GeneOntology)obj; 			 
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
