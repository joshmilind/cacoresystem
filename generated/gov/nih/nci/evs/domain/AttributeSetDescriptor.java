

package gov.nih.nci.evs.domain;

import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * AttributeSetDescriptor class specifies the set of concept attributes that should be retrieved 
   * by a given operation. 
   * 
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
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.Vector roleCollection = new java.util.Vector();
	/**
	* Returns the roles of this AttributeSetDescriptor	   
	* @return - roleCollection
	*/

	public java.util.Vector getRoleCollection(){
		return roleCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified roles for this AttributeSetDescriptor	   
	* @return - roleCollection
	*/

	public void setRoleCollection(java.util.Vector roleCollection){
	   	
	   	this.roleCollection = roleCollection;
	        }	
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.Vector propertyCollection = new java.util.Vector();
	/**
	* Returns the properties of this AttributeSetDescriptor	   
	* @return - propertyCollection
	*/

	public java.util.Vector getPropertyCollection(){
		return propertyCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified properties for this AttributeSetDescriptor	   
	* @return - propertyCollection
	*/

	public void setPropertyCollection(java.util.Vector propertyCollection){
	   	
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
