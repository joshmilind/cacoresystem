

package gov.nih.nci.common.provenance.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ResearchInstitutionSource 
    extends gov.nih.nci.common.provenance.domain.ws.Source
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.String institutionAddress;
	   public  java.lang.String getInstitutionAddress(){
	      return institutionAddress;
	   }
	   
	   public void setInstitutionAddress( java.lang.String institutionAddress){
	      this.institutionAddress = institutionAddress;
	   }
	
	   
	   public java.lang.String institutionDepartment;
	   public  java.lang.String getInstitutionDepartment(){
	      return institutionDepartment;
	   }
	   
	   public void setInstitutionDepartment( java.lang.String institutionDepartment){
	      this.institutionDepartment = institutionDepartment;
	   }
	
	   
	   public java.lang.String institutionName;
	   public  java.lang.String getInstitutionName(){
	      return institutionName;
	   }
	   
	   public void setInstitutionName( java.lang.String institutionName){
	      this.institutionName = institutionName;
	   }
	
	   
	   public java.lang.String institutionPersons;
	   public  java.lang.String getInstitutionPersons(){
	      return institutionPersons;
	   }
	   
	   public void setInstitutionPersons( java.lang.String institutionPersons){
	      this.institutionPersons = institutionPersons;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ResearchInstitutionSource) {
				ResearchInstitutionSource c =(ResearchInstitutionSource)obj; 			 
				Long thisId = getId();		
				
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
