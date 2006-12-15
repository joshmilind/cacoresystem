

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class FormElement 
    extends gov.nih.nci.cadsr.domain.ws.AdministeredComponent
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection instruction = new java.util.HashSet();
			public java.util.Collection getInstruction(){
	              return instruction;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setInstruction(java.util.Collection instruction){
	   		this.instruction = instruction;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof FormElement) {
				FormElement c =(FormElement)obj; 			 
				String thisId = getId();		
				
					if(thisId != null && thisId.equals(c.getId())) {
					   eq = true;
				    }		
				
			}
			return eq;
		}
		
		public int hashCode(){
			int h = 0;
			
			if(getId() != null) {
				h += getId().hashCode();
			}
			
			return h;
	}
	
	
}
