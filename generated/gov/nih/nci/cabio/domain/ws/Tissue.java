

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Tissue 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String cellLine;
	   public  java.lang.String getCellLine(){
	      return cellLine;
	   }
	   
	   public void setCellLine( java.lang.String cellLine){
	      this.cellLine = cellLine;
	   }
	
	   
	   public java.lang.String cellType;
	   public  java.lang.String getCellType(){
	      return cellType;
	   }
	   
	   public void setCellType( java.lang.String cellType){
	      this.cellType = cellType;
	   }
	
	   
	   public java.lang.String description;
	   public  java.lang.String getDescription(){
	      return description;
	   }
	   
	   public void setDescription( java.lang.String description){
	      this.description = description;
	   }
	
	   
	   public java.lang.String developmentalStage;
	   public  java.lang.String getDevelopmentalStage(){
	      return developmentalStage;
	   }
	   
	   public void setDevelopmentalStage( java.lang.String developmentalStage){
	      this.developmentalStage = developmentalStage;
	   }
	
	   
	   public java.lang.String histology;
	   public  java.lang.String getHistology(){
	      return histology;
	   }
	   
	   public void setHistology( java.lang.String histology){
	      this.histology = histology;
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
	
	   
	   public java.lang.String organ;
	   public  java.lang.String getOrgan(){
	      return organ;
	   }
	   
	   public void setOrgan( java.lang.String organ){
	      this.organ = organ;
	   }
	
	   
	   public java.lang.String sex;
	   public  java.lang.String getSex(){
	      return sex;
	   }
	   
	   public void setSex( java.lang.String sex){
	      this.sex = sex;
	   }
	
	   
	   public java.lang.String supplier;
	   public  java.lang.String getSupplier(){
	      return supplier;
	   }
	   
	   public void setSupplier( java.lang.String supplier){
	      this.supplier = supplier;
	   }
	
	   
	   public java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Protocol protocol;
			public gov.nih.nci.cabio.domain.ws.Protocol getProtocol(){
			  return protocol;
                        }
		   
	      
	               
	   
	   
	   
	   public void setProtocol(gov.nih.nci.cabio.domain.ws.Protocol protocol){
		this.protocol = protocol;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection libraryCollection = new java.util.HashSet();
			public java.util.Collection getLibraryCollection(){
	              return libraryCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setLibraryCollection(java.util.Collection libraryCollection){
	   		this.libraryCollection = libraryCollection;
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
			if(obj instanceof Tissue) {
				Tissue c =(Tissue)obj; 			 
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
