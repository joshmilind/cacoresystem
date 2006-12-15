

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Pathway 
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
	
	   
	   public java.lang.String diagram;
	   public  java.lang.String getDiagram(){
	      return diagram;
	   }
	   
	   public void setDiagram( java.lang.String diagram){
	      this.diagram = diagram;
	   }
	
	   
	   public java.lang.String displayValue;
	   public  java.lang.String getDisplayValue(){
	      return displayValue;
	   }
	   
	   public void setDisplayValue( java.lang.String displayValue){
	      this.displayValue = displayValue;
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
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Taxon taxon;
			public gov.nih.nci.cabio.domain.ws.Taxon getTaxon(){
			  return taxon;
                        }
		   
	      
	               
	   
	   
	   
	   public void setTaxon(gov.nih.nci.cabio.domain.ws.Taxon taxon){
		this.taxon = taxon;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Pathway) {
				Pathway c =(Pathway)obj; 			 
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
