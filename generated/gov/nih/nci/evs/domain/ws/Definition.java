

package gov.nih.nci.evs.domain.ws;


import java.util.*;

  /**
   * Textual definition from an identified source
   */


/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Definition
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * A textual description of a term	   
	 */
	   	
	
	private java.lang.String definition;	   
	   
	   
	/**
	* @deprecated  - Returns the definition text	   
	* @return - definition
	*/
	
	
	
	
	
	
	public  java.lang.String getDefinition(){
	      return definition;
	      }  
	     
	       
	   		
	/**
	* @deprecated  - Sets the specified definition text  for this Definition	   
	* @param - definition
	*/
	
	public void setDefinition( java.lang.String definition){
	      this.definition = definition;
	   }
	       	   
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	      			
			
	private Source source;
	/**
	* Returns the Source of this Definition	   
	* @return - source
	*/

	public Source getSource(){
		return source;			
        }		   
	      
	     
		   
	   
	/**
	* Sets the specified Source for this Definition	   
	* @param - source
	*/
		
	public void setSource(Source source){
		this.source = source;
	}	
	
	
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Definition) {
			Definition c =(Definition)obj; 
								
				String thisKey =  getDefinition();			
				if(thisKey!= null && thisKey.equals(c.getDefinition())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getDefinition() != null) {
			h += getDefinition().hashCode();
		}
		return h;
	}
	
	
}
