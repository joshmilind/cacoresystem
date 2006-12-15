

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class EnumeratedValueDomain 
    extends gov.nih.nci.cadsr.domain.ws.ValueDomain
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	

	
	   
	   
	   
	      
			private java.util.Collection valueDomainPermissibleValueCollection = new java.util.HashSet();
			public java.util.Collection getValueDomainPermissibleValueCollection(){
	              return valueDomainPermissibleValueCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setValueDomainPermissibleValueCollection(java.util.Collection valueDomainPermissibleValueCollection){
	   		this.valueDomainPermissibleValueCollection = valueDomainPermissibleValueCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof EnumeratedValueDomain) {
				EnumeratedValueDomain c =(EnumeratedValueDomain)obj; 			 
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
