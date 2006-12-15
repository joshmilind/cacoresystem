

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ComponentLevel 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String concatenationString;
	   public  java.lang.String getConcatenationString(){
	      return concatenationString;
	   }
	   
	   public void setConcatenationString( java.lang.String concatenationString){
	      this.concatenationString = concatenationString;
	   }
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.Integer level;
	   public  java.lang.Integer getLevel(){
	      return level;
	   }
	   
	   public void setLevel( java.lang.Integer level){
	      this.level = level;
	   }
	

	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ComponentLevel) {
				ComponentLevel c =(ComponentLevel)obj; 			 
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
