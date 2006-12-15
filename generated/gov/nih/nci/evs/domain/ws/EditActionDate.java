

package gov.nih.nci.evs.domain.ws;


import java.util.*;

  /**
   * This class holds the edit action and date information for a given concept
   */


/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class EditActionDate
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the edit action	   
	 */
	   	
	
	private int action;	   
	   
	   
	/**
	* @deprecated  - returns the action	   
	* @return - action
	*/
	
	
	
	
	
	
	public int getAction(){
	      return action;
	      }  
	     
	       
	   		
	/**
	* @deprecated  - Sets the action	   
	* @param - action
	*/
	
	public void setAction(int action){
	      this.action = action;
	   }
	       	   
	
	   
	 /**
	 * Specifies the edit action date.	   
	 */
	   	
	
	private java.util.Date editDate;	   
	   
	   
	/**
	* @deprecated  - returns the edit date	   
	* @return - editDate
	*/
	
	
	
	
	
	
	public  java.util.Date getEditDate(){
	      return editDate;
	      }  
	     
	       
	   		
	/**
	* @deprecated  - Sets the edit date	   
	* @param - editDate
	*/
	
	public void setEditDate( java.util.Date editDate){
	      this.editDate = editDate;
	   }
	       	   
	
	
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof EditActionDate) {
			EditActionDate c =(EditActionDate)obj; 
								
				Date thisKey =  getEditDate();			
				if(thisKey!= null && thisKey.equals(c.getEditDate())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getEditDate() != null) {
			h += getEditDate().hashCode();
		}
		return h;
	}
	
	
}
