

package gov.nih.nci.evs.domain.ws;


import java.util.*;

  /**
   * AttributeSetDescriptor class specifies the set of concept attributes that should be retrieved 
   * by a given operation. 
   * 
   */


/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class AttributeSetDescriptor
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	/**
	* Default constructor
	*/
	public AttributeSetDescriptor(){}


public AttributeSetDescriptor(java.lang.String name){
	this.name = name;
};		
	
	   
	 /**
	 * Specifies the name for an AttributeSetDescriptor instance	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* @deprecated  - Returns the name of this AttributeSetDescriptor	   
	* @return - name
	*/
	
	
	
	
	
	
	public  java.lang.String getName(){
	      return name;
	      }  
	     
	       
	   		
	/**
	* @deprecated  - Sets the name for this AttributeSetDescriptor	   
	* @param - name
	*/
	
	public void setName( java.lang.String name){
	      this.name = name;
	   }
	       	   
	
	   
	 /**
	 * A special constant that specifies all concept attributes to be fetched.	   
	 */
	   
	public static final  int WITH_ALL_ATTRIBUTES= 1; 
	   
	   
	 /**
	 * A special constant that specifies all properties of a concept to be fetched.	   
	 */
	   
	public static final  int WITH_ALL_PROPERTIES= 3; 
	   
	   
	 /**
	 * A special constant that specifies all roles of a concept to be fetched.	   
	 */
	   
	public static final  int WITH_ALL_ROLES= 2; 
	   
	   
	 /**
	 * A special constant that specifies no concept attributes to be fetched.	   
	 */
	   
	public static final  int WITH_NO_ATTRIBUTES= 0; 
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.ArrayList roleCollection = new java.util.ArrayList();
	/**
	* Returns the roles of this AttributeSetDescriptor	   
	* @return - roleCollection
	*/

	public java.util.ArrayList getRoleCollection(){
		return roleCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified roles for this AttributeSetDescriptor	   
	* @return - roleCollection
	*/

	public void setRoleCollection(java.util.ArrayList roleCollection){
	   	
	   	this.roleCollection = roleCollection;
	        }	
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.ArrayList propertyCollection = new java.util.ArrayList();
	/**
	* Returns the properties of this AttributeSetDescriptor	   
	* @return - propertyCollection
	*/

	public java.util.ArrayList getPropertyCollection(){
		return propertyCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified properties for this AttributeSetDescriptor	   
	* @return - propertyCollection
	*/

	public void setPropertyCollection(java.util.ArrayList propertyCollection){
	   	
	   	this.propertyCollection = propertyCollection;
	        }	
	   
	
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof AttributeSetDescriptor) {
			AttributeSetDescriptor c =(AttributeSetDescriptor)obj; 
								
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
