

package gov.nih.nci.evs.domain;

import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * The EdgeProperties class specifies the relationship between a concept and its immediate parent 
   * when a TREE is generated using the getTree method. 
   * 
   */


public  class EdgeProperties
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifes the isa value for this concept. If a relationship type between this concept and the parent is a "isa" this value is set to true.	   
	 */
	   	
	
	private boolean isA;	   
	   
	   
	/**
	* @deprecated  - Returns the is-a value for this EdgeProperties	   
	* @return - isA
	*/
	public boolean getIsA(){
	      return isA;
	      }   
	   		
	/**
	* @deprecated  - Sets the specified is-a value	   
	* @param - isA
	*/
	public void setIsA(boolean isA){
	      this.isA = isA;
	   }	   
	
	   
	 /**
	 * Specifes the link information used  to generate the concept tree	   
	 */
	   	
	
	private java.util.HashSet links;	   
	   
	   
	/**
	* @deprecated  - Returns the links for this EdgeProperties	   
	* @return - links
	*/
	public  java.util.HashSet getLinks(){
	      return links;
	      }   
	   		
	/**
	* @deprecated  - Sets the specified links for this EdgeProperties	   
	* @param - links
	*/
	public void setLinks( java.util.HashSet links){
	      this.links = links;
	   }	   
	
	   
	 /**
	 * Specifes the name for this instance	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* @deprecated  - Returns the name of this EdgeProperties	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* @deprecated  - Sets the specified name for this EdgeProperties	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	   
	 /**
	 * The direction specified when the concept tree was generated. 	   
	 */
	   	
	
	private boolean traverseDown;	   
	   
	   
	/**
	* @deprecated  - Returns the traverseDown value for this EdgeProperties	   
	* @return - traverseDown
	*/
	public boolean getTraverseDown(){
	      return traverseDown;
	      }   
	   		
	/**
	* @deprecated  - Sets the specified traverseDown value	   
	* @param - traverseDown
	*/
	public void setTraverseDown(boolean traverseDown){
	      this.traverseDown = traverseDown;
	   }	   
	
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof EdgeProperties) {
			EdgeProperties c =(EdgeProperties)obj; 
								
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
