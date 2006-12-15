

package gov.nih.nci.evs.domain;

import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Defines a relationship between two concepts. 
   */


public  class Role
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the name of the role	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* Returns the name of this Role	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* Sets the specified name for this role	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	   
	 /**
	 * Specifies the value	   
	 */
	   	
	
	private java.lang.String value;	   
	   
	   
	/**
	* Returns the value of this Role	   
	* @return - value
	*/
	public  java.lang.String getValue(){
	      return value;
	      }   
	   		
	/**
	* Sets the specified value for this Role	   
	* @param - value
	*/
	public void setValue( java.lang.String value){
	      this.value = value;
	   }	   
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Role) {
			Role c =(Role)obj; 
								
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
