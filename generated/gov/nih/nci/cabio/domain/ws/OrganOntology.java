

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class OrganOntology 
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
	

	
	   
	   
	   
	      
			private java.util.Collection anomalyCollection = new java.util.HashSet();
			public java.util.Collection getAnomalyCollection(){
	              return anomalyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAnomalyCollection(java.util.Collection anomalyCollection){
	   		this.anomalyCollection = anomalyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection childOrganOntologyRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getChildOrganOntologyRelationshipCollection(){
	              return childOrganOntologyRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChildOrganOntologyRelationshipCollection(java.util.Collection childOrganOntologyRelationshipCollection){
	   		this.childOrganOntologyRelationshipCollection = childOrganOntologyRelationshipCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection parentOrganOntologyRelationshipCollection = new java.util.HashSet();
			public java.util.Collection getParentOrganOntologyRelationshipCollection(){
	              return parentOrganOntologyRelationshipCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setParentOrganOntologyRelationshipCollection(java.util.Collection parentOrganOntologyRelationshipCollection){
	   		this.parentOrganOntologyRelationshipCollection = parentOrganOntologyRelationshipCollection;
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
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof OrganOntology) {
				OrganOntology c =(OrganOntology)obj; 			 
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
