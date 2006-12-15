

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Protein 
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
	
	   
	   public java.lang.String copyrightStatement;
	   public  java.lang.String getCopyrightStatement(){
	      return copyrightStatement;
	   }
	   
	   public void setCopyrightStatement( java.lang.String copyrightStatement){
	      this.copyrightStatement = copyrightStatement;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.util.Collection keywords;
	   public  java.util.Collection getKeywords(){
	      return keywords;
	   }
	   
	   public void setKeywords( java.util.Collection keywords){
	      this.keywords = keywords;
	   }
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	
	   
	   public java.lang.String primaryAccession;
	   public  java.lang.String getPrimaryAccession(){
	      return primaryAccession;
	   }
	   
	   public void setPrimaryAccession( java.lang.String primaryAccession){
	      this.primaryAccession = primaryAccession;
	   }
	
	   
	   public java.util.Collection secondaryAccession;
	   public  java.util.Collection getSecondaryAccession(){
	      return secondaryAccession;
	   }
	   
	   public void setSecondaryAccession( java.util.Collection secondaryAccession){
	      this.secondaryAccession = secondaryAccession;
	   }
	
	   
	   public java.lang.String uniProtCode;
	   public  java.lang.String getUniProtCode(){
	      return uniProtCode;
	   }
	   
	   public void setUniProtCode( java.lang.String uniProtCode){
	      this.uniProtCode = uniProtCode;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection proteinAliasCollection = new java.util.HashSet();
			public java.util.Collection getProteinAliasCollection(){
	              return proteinAliasCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setProteinAliasCollection(java.util.Collection proteinAliasCollection){
	   		this.proteinAliasCollection = proteinAliasCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneCollection = new java.util.HashSet();
			public java.util.Collection getGeneCollection(){
	              return geneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneCollection(java.util.Collection geneCollection){
	   		this.geneCollection = geneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection taxonCollection = new java.util.HashSet();
			public java.util.Collection getTaxonCollection(){
	              return taxonCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTaxonCollection(java.util.Collection taxonCollection){
	   		this.taxonCollection = taxonCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.cabio.domain.ws.ProteinSequence proteinSequence;
			public gov.nih.nci.cabio.domain.ws.ProteinSequence getProteinSequence(){
			  return proteinSequence;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setProteinSequence(gov.nih.nci.cabio.domain.ws.ProteinSequence proteinSequence){
		this.proteinSequence = proteinSequence;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Protein) {
				Protein c =(Protein)obj; 			 
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
