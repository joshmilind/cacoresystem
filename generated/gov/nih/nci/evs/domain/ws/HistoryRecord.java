

package gov.nih.nci.evs.domain.ws;


import java.util.*;

  /**
   * The HistoryRecord holds history information for the specified concept
   */


/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class HistoryRecord
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * This is an unique code associated with a concept within the specified vocabulary. 	   
	 */
	   	
	
	private java.lang.String descLogicConceptCode;	   
	   
	   
	/**
	* 	   
	* @return - descLogicConceptCode
	*/
	
	
	
	
	
	
	public  java.lang.String getDescLogicConceptCode(){
	      return descLogicConceptCode;
	      }  
	     
	       
	   		
	/**
	* 	   
	* @param - descLogicConceptCode
	*/
	
	public void setDescLogicConceptCode( java.lang.String descLogicConceptCode){
	      this.descLogicConceptCode = descLogicConceptCode;
	   }
	       	   
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.ArrayList historyCollection = new java.util.ArrayList();
	/**
	* Returns history information of this HistoryRecord	   
	* @return - historyCollection
	*/

	public java.util.ArrayList getHistoryCollection(){
		return historyCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified history information for this HistroryRecord	   
	* @return - historyCollection
	*/

	public void setHistoryCollection(java.util.ArrayList historyCollection){
	   	
	   	this.historyCollection = historyCollection;
	        }	
	   
	
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof HistoryRecord) {
			HistoryRecord c =(HistoryRecord)obj; 
								
				String thisKey =  getDescLogicConceptCode();			
				if(thisKey!= null && thisKey.equals(c.getDescLogicConceptCode())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getDescLogicConceptCode() != null) {
			h += getDescLogicConceptCode().hashCode();
		}
		return h;
	}
	
	
}
