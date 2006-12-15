

package gov.nih.nci.evs.security.ws;


import java.util.*;

  /**
   * SecurityToken class implements security policy to EVS Vocabularies. Access request information 
   * can be specified in this class. This information will be used to grant or deny permission to access 
   * a specific vocabulary. 
   * 
   */


/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class SecurityToken
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the code for this token	   
	 */
	   	
	
	private java.lang.String accessToken;	   
	   
	   
	/**
	* Returns the code for this token	   
	* @return - accessToken
	*/
	
	
	
	
	
	
	public  java.lang.String getAccessToken(){
	      return accessToken;
	      }  
	     
	       
	   		
	/**
	* Sets the code for this token	   
	* @param - accessToken
	*/
	
	public void setAccessToken( java.lang.String accessToken){
	      this.accessToken = accessToken;
	   }
	       	   
	
	   
	 /**
	 * Specifies the password for this token	   
	 */
	   	
	
	private java.lang.String password;	   
	   
	   
	/**
	* Returns the password for this token	   
	* @return - password
	*/
	
	
	
	
	
	
	public  java.lang.String getPassword(){
	      return password;
	      }  
	     
	       
	   		
	/**
	* Sets the password for this token	   
	* @param - password
	*/
	
	public void setPassword( java.lang.String password){
	      this.password = password;
	   }
	       	   
	
	   
	 /**
	 * Specifies the user name	   
	 */
	   	
	
	private java.lang.String userName;	   
	   
	   
	/**
	* Returns the user name for this token	   
	* @return - userName
	*/
	
	
	
	
	
	
	public  java.lang.String getUserName(){
	      return userName;
	      }  
	     
	       
	   		
	/**
	* Sets the username for this token	   
	* @param - userName
	*/
	
	public void setUserName( java.lang.String userName){
	      this.userName = userName;
	   }
	       	   
	
	
	   
	   	
	   
	   
	   
	
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof SecurityToken) {
			SecurityToken c =(SecurityToken)obj; 
								
				String thisKey =  getUserName();			
				if(thisKey!= null && thisKey.equals(c.getUserName())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getUserName() != null) {
			h += getUserName().hashCode();
		}
		return h;
	}
	
	
}
