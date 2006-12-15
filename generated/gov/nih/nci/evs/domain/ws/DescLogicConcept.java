

package gov.nih.nci.evs.domain.ws;


import java.util.*;

  /**
   * The DescLogicConcept class represents the fundamental vocabulary entity in the NCI Thesaurus. 
   * 
   */


/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class DescLogicConcept
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * This is an unique code associated with a concept within the specified vocabulary. 	   
	 */
	   	
	
	private java.lang.String code;	   
	   
	   
	/**
	* Returns the concept code for this DescLogicConcept	   
	* @return - code
	*/
	
	
	
	
	
	
	public  java.lang.String getCode(){
	      return code;
	      }  
	     
	       
	   		
	/**
	* Sets the specified concept code for this DescLogicConcept	   
	* @param - code
	*/
	
	public void setCode( java.lang.String code){
	      this.code = code;
	   }
	       	   
	
	   
	 /**
	 * Sets this value to 'true' if a concept has children	   
	 */
	   	
	
	private java.lang.Boolean hasChildren;	   
	   
	   
	/**
	* Returns the hasChildren value for this DescLogicConcept	   
	* @return - hasChildren
	*/
	
	
	
	
	
	
	public  java.lang.Boolean getHasChildren(){
	      return hasChildren;
	      }  
	     
	       
	   	  
	
	/**
	* @deprecated  - The preffered way to do this is by using the getHasChildren method
	*/
	public  java.lang.Boolean hasChildren(){
	   	      return hasChildren;
	   	   }	   	   
	   		
	/**
	* Sets the specified hasChildren value for this DescLogicConcept	   
	* @param - hasChildren
	*/
	
	public void setHasChildren( java.lang.Boolean hasChildren){
	      this.hasChildren = hasChildren;
	   }
	       	   
	
	   
	 /**
	 * This value is set to 'true' if a concept has a parent	   
	 */
	   	
	
	private java.lang.Boolean hasParents;	   
	   
	   
	/**
	* Returns the hasParent value for this DescLogicConcept	   
	* @return - hasParents
	*/
	
	
	
	
	
	
	public  java.lang.Boolean getHasParents(){
	      return hasParents;
	      }  
	     
	       
	   	  
	
	/**
	* @deprecated  - The preffered way to do this is by using the getHasParents method
	*/
	public  java.lang.Boolean hasParents(){
	   	      return hasParents;
	   	   }	   	   
	   		
	/**
	* Sets the specified hasParent value for this DescLogicConcept	   
	* @param - hasParents
	*/
	
	public void setHasParents( java.lang.Boolean hasParents){
	      this.hasParents = hasParents;
	   }
	       	   
	
	   
	 /**
	 * This attribute holds association information between concepts	   
	 */
	   	
		private java.util.ArrayList inverseAssociationCollection = new ArrayList();	   
	   
	   
	/**
	* Returns the inverse association information of this DescLogicConcept	   
	* @return - inverseAssociationCollection
	*/
	
	
	public java.util.ArrayList getInverseAssociationCollection(){     
	   return inverseAssociationCollection;
	}
	     
	       
	   		
	/**
	* Sets the specified inverse association value for this DescLogicConcept	   
	* @param - inverseAssociationCollection
	*/
	
	public void setInverseAssociationCollection(java.util.ArrayList inverseAssociationCollection){
	      this.inverseAssociationCollection = inverseAssociationCollection;
	   }
	       	   
	
	   
	 /**
	 * Stores the inverse relationship between two concepts	   
	 */
	   	
		private java.util.ArrayList inverseRoleCollection = new ArrayList();	   
	   
	   
	/**
	* Returns the inverse role value for this DescLogicConcept	   
	* @return - inverseRoleCollection
	*/
	
	
	public java.util.ArrayList getInverseRoleCollection(){     
	   return inverseRoleCollection;
	}
	     
	       
	   		
	/**
	* Sets the specified inverse role value for this DescLogicConcept	   
	* @param - inverseRoleCollection
	*/
	
	public void setInverseRoleCollection(java.util.ArrayList inverseRoleCollection){
	      this.inverseRoleCollection = inverseRoleCollection;
	   }
	       	   
	
	   
	 /**
	 * Sets this value to 'true'  if a concept is retired. 	   
	 */
	   	
	
	private java.lang.Boolean isRetired;	   
	   
	   
	/**
	* Returns the isRetired value for this DescLogicConcept	   
	* @return - isRetired
	*/
	
	
	
	
	
	
	public  java.lang.Boolean getIsRetired(){
	      return isRetired;
	      }  
	     
	       
	   	  
	
	/**
	* @deprecated  - The preffered way to do this is by using the getIsRetired method
	*/
	public  java.lang.Boolean isRetired(){
	   	      return isRetired;
	   	   }	   	   
	   		
	/**
	* Sets the isRetired value for this DescLogicConcept	   
	* @param - isRetired
	*/
	
	public void setIsRetired( java.lang.Boolean isRetired){
	      this.isRetired = isRetired;
	   }
	       	   
	
	   
	 /**
	 * Specifies the name of the concept. 	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* Returns the name for this DescLogicConcept	   
	* @return - name
	*/
	
	
	
	
	
	
	public  java.lang.String getName(){
	      return name;
	      }  
	     
	       
	   		
	/**
	* Sets the specified name for this DescLogicConcept	   
	* @param - name
	*/
	
	public void setName( java.lang.String name){
	      this.name = name;
	   }
	       	   
	
	   
	 /**
	 * The namespaceId is used to identify a set of concepts within a terminology such as the NCI Thesaurus.	   
	 */
	   	
	
	private int namespaceId;	   
	   
	   
	/**
	* @deprecated  - Returns the namespaceId for this DescLogicConcept	   
	* @return - namespaceId
	*/
	
	
	
	
	
	
	public int getNamespaceId(){
	      return namespaceId;
	      }  
	     
	       
	   		
	/**
	* @deprecated  - Sets the specified namespaceId for this DescLogicConcept	   
	* @param - namespaceId
	*/
	
	public void setNamespaceId(int namespaceId){
	      this.namespaceId = namespaceId;
	   }
	       	   
	
	   
	 /**
	 * Stores the Semantic_Type property value for this DescLogicConcept	   
	 */
	   	
		private java.util.ArrayList semanticTypeVector = new ArrayList();	   
	   
	
	/**
	* @deprecated  - The preferred way of getting a semantic type is by using the getPropertyCollection method. 'Semantic_Type' is the name of a property in a DescLogicConcept. 
	*/
	   
	/**
	* @deprecated  - Returns the SemanticTypes of this DescLogicConcept	   
	* @return - semanticTypeVector
	*/
	
	
	public java.util.ArrayList getSemanticTypeVector(){     
	   return semanticTypeVector;
	}
	     
	       
	   		
	/**
	* @deprecated  - Sets the specified Semantic Types for this DescLogicConcept	   
	* @param - semanticTypeVector
	*/
	
	public void setSemanticTypeVector(java.util.ArrayList semanticTypeVector){
	      this.semanticTypeVector = semanticTypeVector;
	   }
	       	   
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	      			
			
	private Vocabulary vocabulary;
	/**
	* Returns the vocabulary of this concept	   
	* @return - vocabulary
	*/

	public Vocabulary getVocabulary(){
		return vocabulary;			
        }		   
	      
	     
		   
	   
	/**
	* Sets the vocabulary for this concept	   
	* @param - vocabulary
	*/
		
	public void setVocabulary(Vocabulary vocabulary){
		this.vocabulary = vocabulary;
	}	
	
	
	   
	   	
	   
	   
	   
	      			
			
	private TreeNode treeNode;
	/**
	* @deprecated - The preferred way of doing this is by using the getEdgeProperties method	   
	* @return - treeNode
	*/

	public TreeNode getTreeNode(){
		return treeNode;			
        }		   
	      
	     
		   
	   
	/**
	* @deprecated - the preferred way of doing this is by using the setEdgeProperties method	   
	* @param - treeNode
	*/
		
	public void setTreeNode(TreeNode treeNode){
		this.treeNode = treeNode;
	}	
	
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.ArrayList roleCollection = new java.util.ArrayList();
	/**
	* Returns the roles of this DescLogicConcept	   
	* @return - roleCollection
	*/

	public java.util.ArrayList getRoleCollection(){
		return roleCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified roles for this DescLogicConcept	   
	* @return - roleCollection
	*/

	public void setRoleCollection(java.util.ArrayList roleCollection){
	   	
	   	this.roleCollection = roleCollection;
	        }	
	   
	
	   
	   	
	   
	   
	   
	      			
			
	private EdgeProperties edgeProperties;
	/**
	* Returns the edgeProperties for this DescLogicConcept.	   
	* @return - edgeProperties
	*/

	public EdgeProperties getEdgeProperties(){
		return edgeProperties;			
        }		   
	      
	     
		   
	   
	/**
	* Sets the specified edgeProperties value for this DescLogicConcept when a tree is generated.	   
	* @param - edgeProperties
	*/
		
	public void setEdgeProperties(EdgeProperties edgeProperties){
		this.edgeProperties = edgeProperties;
	}	
	
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.ArrayList associationCollection = new java.util.ArrayList();
	/**
	* Returns the associations of this DescLogicConcept	   
	* @return - associationCollection
	*/

	public java.util.ArrayList getAssociationCollection(){
		return associationCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified associations for this DescLogicConcept	   
	* @return - associationCollection
	*/

	public void setAssociationCollection(java.util.ArrayList associationCollection){
	   	
	   	this.associationCollection = associationCollection;
	        }	
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.ArrayList propertyCollection = new java.util.ArrayList();
	/**
	* Returns the properties of this DescLogicConcept	   
	* @return - propertyCollection
	*/

	public java.util.ArrayList getPropertyCollection(){
		return propertyCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified properties for this DescLogicConcept	   
	* @return - propertyCollection
	*/

	public void setPropertyCollection(java.util.ArrayList propertyCollection){
	   	
	   	this.propertyCollection = propertyCollection;
	        }	
	   
	
	      
	private String vocabularyName;
    public void setVocabularyName(String vocabularyName){
      this.vocabularyName =vocabularyName;
    }
    
    public String getVocabularyName(){
       return vocabularyName;
    }
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof DescLogicConcept) {
			DescLogicConcept c =(DescLogicConcept)obj; 
								
				String thisKey =  getCode();			
				if(thisKey!= null && thisKey.equals(c.getCode())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getCode() != null) {
			h += getCode().hashCode();
		}
		return h;
	}
	
	
}
