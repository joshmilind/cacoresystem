

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Gene 
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
	
	   
	   public java.lang.Long clusterId;
	   public  java.lang.Long getClusterId(){
	      return clusterId;
	   }
	   
	   public void setClusterId( java.lang.Long clusterId){
	      this.clusterId = clusterId;
	   }
	
	   
	   public java.lang.String fullName;
	   public  java.lang.String getFullName(){
	      return fullName;
	   }
	   
	   public void setFullName( java.lang.String fullName){
	      this.fullName = fullName;
	   }
	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String symbol;
	   public  java.lang.String getSymbol(){
	      return symbol;
	   }
	   
	   public void setSymbol( java.lang.String symbol){
	      this.symbol = symbol;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Taxon taxon;
			public gov.nih.nci.cabio.domain.ws.Taxon getTaxon(){
			  return taxon;
                        }
		   
	      
	               
	   
	   
	   
	   public void setTaxon(gov.nih.nci.cabio.domain.ws.Taxon taxon){
		this.taxon = taxon;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection pathwayCollection = new java.util.HashSet();
			public java.util.Collection getPathwayCollection(){
	              return pathwayCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setPathwayCollection(java.util.Collection pathwayCollection){
	   		this.pathwayCollection = pathwayCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection nucleicAcidSequenceCollection = new java.util.HashSet();
			public java.util.Collection getNucleicAcidSequenceCollection(){
	              return nucleicAcidSequenceCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setNucleicAcidSequenceCollection(java.util.Collection nucleicAcidSequenceCollection){
	   		this.nucleicAcidSequenceCollection = nucleicAcidSequenceCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection locationCollection = new java.util.HashSet();
			public java.util.Collection getLocationCollection(){
	              return locationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setLocationCollection(java.util.Collection locationCollection){
	   		this.locationCollection = locationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneRelativeLocationCollection = new java.util.HashSet();
			public java.util.Collection getGeneRelativeLocationCollection(){
	              return geneRelativeLocationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneRelativeLocationCollection(java.util.Collection geneRelativeLocationCollection){
	   		this.geneRelativeLocationCollection = geneRelativeLocationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection homologousAssociationCollection = new java.util.HashSet();
			public java.util.Collection getHomologousAssociationCollection(){
	              return homologousAssociationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setHomologousAssociationCollection(java.util.Collection homologousAssociationCollection){
	   		this.homologousAssociationCollection = homologousAssociationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection genericReporterCollection = new java.util.HashSet();
			public java.util.Collection getGenericReporterCollection(){
	              return genericReporterCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGenericReporterCollection(java.util.Collection genericReporterCollection){
	   		this.genericReporterCollection = genericReporterCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection databaseCrossReferenceCollection = new java.util.HashSet();
			public java.util.Collection getDatabaseCrossReferenceCollection(){
	              return databaseCrossReferenceCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDatabaseCrossReferenceCollection(java.util.Collection databaseCrossReferenceCollection){
	   		this.databaseCrossReferenceCollection = databaseCrossReferenceCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection libraryCollection = new java.util.HashSet();
			public java.util.Collection getLibraryCollection(){
	              return libraryCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setLibraryCollection(java.util.Collection libraryCollection){
	   		this.libraryCollection = libraryCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Chromosome chromosome;
			public gov.nih.nci.cabio.domain.ws.Chromosome getChromosome(){
			  return chromosome;
                        }
		   
	      
	               
	   
	   
	   
	   public void setChromosome(gov.nih.nci.cabio.domain.ws.Chromosome chromosome){
		this.chromosome = chromosome;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection histopathologyCollection = new java.util.HashSet();
			public java.util.Collection getHistopathologyCollection(){
	              return histopathologyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setHistopathologyCollection(java.util.Collection histopathologyCollection){
	   		this.histopathologyCollection = histopathologyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneAliasCollection = new java.util.HashSet();
			public java.util.Collection getGeneAliasCollection(){
	              return geneAliasCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneAliasCollection(java.util.Collection geneAliasCollection){
	   		this.geneAliasCollection = geneAliasCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection organOntologyCollection = new java.util.HashSet();
			public java.util.Collection getOrganOntologyCollection(){
	              return organOntologyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setOrganOntologyCollection(java.util.Collection organOntologyCollection){
	   		this.organOntologyCollection = organOntologyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection proteinCollection = new java.util.HashSet();
			public java.util.Collection getProteinCollection(){
	              return proteinCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setProteinCollection(java.util.Collection proteinCollection){
	   		this.proteinCollection = proteinCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneOntologyCollection = new java.util.HashSet();
			public java.util.Collection getGeneOntologyCollection(){
	              return geneOntologyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneOntologyCollection(java.util.Collection geneOntologyCollection){
	   		this.geneOntologyCollection = geneOntologyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection targetCollection = new java.util.HashSet();
			public java.util.Collection getTargetCollection(){
	              return targetCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTargetCollection(java.util.Collection targetCollection){
	   		this.targetCollection = targetCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Gene) {
				Gene c =(Gene)obj; 			 
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
