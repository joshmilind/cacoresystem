

package gov.nih.nci.evs.domain;

import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * The History class stores the concept history information.
   */


public  class History
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the edit action.	   
	 */
	   	
	
	private java.lang.String editAction;	   
	   
	   
	/**
	* Returns the edit action for this History	   
	* @return - editAction
	*/
	public  java.lang.String getEditAction(){
	      return editAction;
	      }   
	   		
	/**
	* Sets the specifed edit action 	   
	* @param - editAction
	*/
	public void setEditAction( java.lang.String editAction){
	      this.editAction = editAction;
	   }	   
	
	   
	 /**
	 * Specifies the edit action date	   
	 */
	   	
	
	private java.util.Date editActionDate;	   
	   
	   
	/**
	* Returns the edit action date 	   
	* @return - editActionDate
	*/
	public  java.util.Date getEditActionDate(){
	      return editActionDate;
	      }   
	   		
	/**
	* Sets the specified edit action date	   
	* @param - editActionDate
	*/
	public void setEditActionDate( java.util.Date editActionDate){
	      this.editActionDate = editActionDate;
	   }	   
	
	   
	 /**
	 * The namespaceId is used to identify a set of concepts within a terminology such as the NCI Thesaurus.	   
	 */
	   	
	
	private int namespaceId;	   
	   
	   
	/**
	* @deprecated  - returns the namespace id	   
	* @return - namespaceId
	*/
	public int getNamespaceId(){
	      return namespaceId;
	      }   
	   		
	/**
	* @deprecated  - sets the namespace id 	   
	* @param - namespaceId
	*/
	public void setNamespaceId(int namespaceId){
	      this.namespaceId = namespaceId;
	   }	   
	
	   
	 /**
	 * Specifies the concept code that was effected due to this edit action	   
	 */
	   	
	
	private java.lang.String referenceCode;	   
	   
	   
	/**
	* Returns the referance code	   
	* @return - referenceCode
	*/
	public  java.lang.String getReferenceCode(){
	      return referenceCode;
	      }   
	   		
	/**
	* Sets the specified reference code value	   
	* @param - referenceCode
	*/
	public void setReferenceCode( java.lang.String referenceCode){
	      this.referenceCode = referenceCode;
	   }	   
	
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof History) {
			History c =(History)obj; 
								
				String thisKey =  getReferenceCode();			
				if(thisKey!= null && thisKey.equals(c.getReferenceCode())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getReferenceCode() != null) {
			h += getReferenceCode().hashCode();
		}
		return h;
	}
	
	
}
