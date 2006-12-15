

package gov.nih.nci.evs.domain;

import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Atom is an occurrence of a term in a source.
   */


public  class Atom
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * The code is a unique identifer within a source associated to the atom	   
	 */
	   	
	
	private java.lang.String code;	   
	   
	   
	/**
	* @deprecated  - Returns the code for this Atom	   
	* @return - code
	*/
	public  java.lang.String getCode(){
	      return code;
	      }   
	   		
	/**
	* @deprecated  - Sets the specified code for this Atom	   
	* @param - code
	*/
	public void setCode( java.lang.String code){
	      this.code = code;
	   }	   
	
	   
	 /**
	 * The lui holds the Lexical unique identifier for the atom	   
	 */
	   	
	
	private java.lang.String lui;	   
	   
	   
	/**
	* @deprecated  - Returns the Lexical Unique ID of this Atom	   
	* @return - lui
	*/
	public  java.lang.String getLui(){
	      return lui;
	      }   
	   		
	/**
	* @deprecated  - Sets the Lexical Unique ID for this Atom	   
	* @param - lui
	*/
	public void setLui( java.lang.String lui){
	      this.lui = lui;
	   }	   
	
	   
	 /**
	 * The name attribute stores the name of the term 	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* @deprecated  - Returns the name of this Atom	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* @deprecated  - Sets the name for this Atom	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	   
	 /**
	 * The origin attribute holds the origin of the atom	   
	 */
	   	
	
	private java.lang.String origin;	   
	   
	   
	/**
	* @deprecated  - Returns the origin of this Atom	   
	* @return - origin
	*/
	public  java.lang.String getOrigin(){
	      return origin;
	      }   
	   		
	/**
	* @deprecated  - Sets the specified origin for this Atom	   
	* @param - origin
	*/
	public void setOrigin( java.lang.String origin){
	      this.origin = origin;
	   }	   
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	      			
			
	private gov.nih.nci.evs.domain.Source source;
	/**
	* Returns the Source of this Atom	   
	* @return - source
	*/

	public gov.nih.nci.evs.domain.Source getSource(){
		return source;			
        }		   
	      
	     
		   
	   
	/**
	* Sets the specified Source for this Atom	   
	* @param - source
	*/
		
	public void setSource(gov.nih.nci.evs.domain.Source source){
		this.source = source;
	}	
	
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Atom) {
			Atom c =(Atom)obj; 
								
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
