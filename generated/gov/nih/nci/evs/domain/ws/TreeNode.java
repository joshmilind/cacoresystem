

package gov.nih.nci.evs.domain.ws;


import java.util.*;

  /**
   * The TreeNode class specifies the relationship between a concept and its immediate parent when a 
   * TREE is generated using the getTree method. 
   * 
   */


/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class TreeNode
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifes the isa value for this concept. If a relationship type between this concept and the parent is a "isa" this value is set to true.	   
	 */
	   	
	
	private boolean isA;	   
	   
	   
	/**
	* @deprecated -  Returns the is-a value	   
	* @return - isA
	*/
	
	
	
	
	
	
	public boolean getIsA(){
	      return isA;
	      }  
	     
	       
	   		
	/**
	* @deprecated - Sets the specified isa value	   
	* @param - isA
	*/
	
	public void setIsA(boolean isA){
	      this.isA = isA;
	   }
	       	   
	
	   
	 /**
	 * Specifes the link information used  to generate the concept tree	   
	 */
	   	
		private java.util.ArrayList links = new ArrayList();	   
	   
	   
	/**
	* 	   
	* @return - links
	*/
	
	
	public java.util.ArrayList getLinks(){     
	   return links;
	}
	     
	       
	   		
	/**
	* @deprecated - sets the links for this TreeNode	   
	* @param - links
	*/
	
	public void setLinks(java.util.ArrayList links){
	      this.links = links;
	   }
	       	   
	
	   
	 /**
	 * Specifes the name for this instance	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* @deprecated - Returns the name for this TreeNode	   
	* @return - name
	*/
	
	
	
	
	
	
	public  java.lang.String getName(){
	      return name;
	      }  
	     
	       
	   		
	/**
	* @deprecated - Sets the name for this TreeNode	   
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
	* @deprecated - Returns the traverseDown value	   
	* @return - traverseDown
	*/
	
	
	
	
	
	
	public boolean getTraverseDown(){
	      return traverseDown;
	      }  
	     
	       
	   		
	/**
	* @deprecated - Sets the traverseDown value	   
	* @param - traverseDown
	*/
	
	public void setTraverseDown(boolean traverseDown){
	      this.traverseDown = traverseDown;
	   }
	       	   
	
	
	   
	   	
	   
	   
	   
	
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof TreeNode) {
			TreeNode c =(TreeNode)obj; 
								
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
