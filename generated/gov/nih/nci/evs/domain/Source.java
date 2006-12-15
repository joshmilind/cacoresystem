

package gov.nih.nci.evs.domain;

import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * The source is a knowledge base.
   */


public  class Source
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the source abbreviation	   
	 */
	   	
	
	private java.lang.String abbreviation;	   
	   
	   
	/**
	* @deprecated  - Returns the abbreviation of this Source	   
	* @return - abbreviation
	*/
	public  java.lang.String getAbbreviation(){
	      return abbreviation;
	      }   
	   		
	/**
	* @deprecated  - Sets the specified abbreviation  for this Source	   
	* @param - abbreviation
	*/
	public void setAbbreviation( java.lang.String abbreviation){
	      this.abbreviation = abbreviation;
	   }	   
	
	   
	 /**
	 * Specifies the source code	   
	 */
	   	
	
	private java.lang.String code;	   
	   
	   
	/**
	* @deprecated  - Returns the code for this source	   
	* @return - code
	*/
	public  java.lang.String getCode(){
	      return code;
	      }   
	   		
	/**
	* @deprecated  - Sets the specified value as the code for this source	   
	* @param - code
	*/
	public void setCode( java.lang.String code){
	      this.code = code;
	   }	   
	
	   
	 /**
	 * Textual description of the source	   
	 */
	   	
	
	private java.lang.String description;	   
	   
	   
	/**
	* @deprecated  - Returns the description of this Source	   
	* @return - description
	*/
	public  java.lang.String getDescription(){
	      return description;
	      }   
	   		
	/**
	* @deprecated  - Sets the specified description for this Source	   
	* @param - description
	*/
	public void setDescription( java.lang.String description){
	      this.description = description;
	   }	   
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Source) {
			Source c =(Source)obj; 
								
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
