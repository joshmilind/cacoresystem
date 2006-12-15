

package gov.nih.nci.evs.domain.ws;


import java.util.*;
import gov.nih.nci.evs.security.ws.*;

  /**
   * Specifies the vocabulary entity or namespace
   */


/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Vocabulary
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Vocabulary description	   
	 */
	   	
	
	private java.lang.String description;	   
	   
	   
	/**
	* Returns the vocabulary description	   
	* @return - description
	*/
	
	
	
	
	
	
	public  java.lang.String getDescription(){
	      return description;
	      }  
	     
	       
	   		
	/**
	* Sets the description of the vocabulary	   
	* @param - description
	*/
	
	public void setDescription( java.lang.String description){
	      this.description = description;
	   }
	       	   
	
	   
	 /**
	 * Specifies the name of the vocabulary	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* Returns the name of the vocabulary	   
	* @return - name
	*/
	
	
	
	
	
	
	public  java.lang.String getName(){
	      return name;
	      }  
	     
	       
	   		
	/**
	* Sets the vocabulary name	   
	* @param - name
	*/
	
	public void setName( java.lang.String name){
	      this.name = name;
	   }
	       	   
	
	   
	 /**
	 * Specifies the namespace id	   
	 */
	   	
	
	private int namespaceId;	   
	   
	   
	/**
	* @deprecated - Returns the namespace id value for this vocabulary	   
	* @return - namespaceId
	*/
	
	
	
	
	
	
	public int getNamespaceId(){
	      return namespaceId;
	      }  
	     
	       
	   		
	/**
	* @deprecated - Sets the namespace id	   
	* @param - namespaceId
	*/
	
	public void setNamespaceId(int namespaceId){
	      this.namespaceId = namespaceId;
	   }
	       	   
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	      			
			
	private SecurityToken securityToken;
	/**
	* 	   
	* @return - securityToken
	*/

	public SecurityToken getSecurityToken(){
		return securityToken;			
        }		   
	      
	     
		   
	   
	/**
	* 	   
	* @param - securityToken
	*/
		
	public void setSecurityToken(SecurityToken securityToken){
		this.securityToken = securityToken;
	}	
	
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.ArrayList siloCollection = new java.util.ArrayList();
	/**
	* Returns the Silo Collection for this vocabulary	   
	* @return - siloCollection
	*/

	public java.util.ArrayList getSiloCollection(){
		return siloCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the Silo List for this vocabulary	   
	* @return - siloCollection
	*/

	public void setSiloCollection(java.util.ArrayList siloCollection){
	   	
	   	this.siloCollection = siloCollection;
	        }	
	   
	
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Vocabulary) {
			Vocabulary c =(Vocabulary)obj; 
								
				String thisKey =  getName();			
				if(thisKey!= null && thisKey.equals(c.getName())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getName() != null) {
			h += getName().hashCode();
		}
		return h;
	}
	
	
}
