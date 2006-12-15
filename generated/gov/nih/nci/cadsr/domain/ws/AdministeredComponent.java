

package gov.nih.nci.cadsr.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class AdministeredComponent 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.util.Date beginDate;
	   public  java.util.Date getBeginDate(){
	      return beginDate;
	   }
	   
	   public void setBeginDate( java.util.Date beginDate){
	      this.beginDate = beginDate;
	   }
	
	   
	   public java.lang.String changeNote;
	   public  java.lang.String getChangeNote(){
	      return changeNote;
	   }
	   
	   public void setChangeNote( java.lang.String changeNote){
	      this.changeNote = changeNote;
	   }
	
	   
	   public java.lang.String createdBy;
	   public  java.lang.String getCreatedBy(){
	      return createdBy;
	   }
	   
	   public void setCreatedBy( java.lang.String createdBy){
	      this.createdBy = createdBy;
	   }
	
	   
	   public java.util.Date dateCreated;
	   public  java.util.Date getDateCreated(){
	      return dateCreated;
	   }
	   
	   public void setDateCreated( java.util.Date dateCreated){
	      this.dateCreated = dateCreated;
	   }
	
	   
	   public java.util.Date dateModified;
	   public  java.util.Date getDateModified(){
	      return dateModified;
	   }
	   
	   public void setDateModified( java.util.Date dateModified){
	      this.dateModified = dateModified;
	   }
	
	   
	   public java.lang.String deletedIndicator;
	   public  java.lang.String getDeletedIndicator(){
	      return deletedIndicator;
	   }
	   
	   public void setDeletedIndicator( java.lang.String deletedIndicator){
	      this.deletedIndicator = deletedIndicator;
	   }
	
	   
	   public java.util.Date endDate;
	   public  java.util.Date getEndDate(){
	      return endDate;
	   }
	   
	   public void setEndDate( java.util.Date endDate){
	      this.endDate = endDate;
	   }
	
	   
	   public java.lang.String id;
	   public  java.lang.String getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.String id){
	      this.id = id;
	   }
	
	   
	   public java.lang.String latestVersionIndicator;
	   public  java.lang.String getLatestVersionIndicator(){
	      return latestVersionIndicator;
	   }
	   
	   public void setLatestVersionIndicator( java.lang.String latestVersionIndicator){
	      this.latestVersionIndicator = latestVersionIndicator;
	   }
	
	   
	   public java.lang.String longName;
	   public  java.lang.String getLongName(){
	      return longName;
	   }
	   
	   public void setLongName( java.lang.String longName){
	      this.longName = longName;
	   }
	
	   
	   public java.lang.String modifiedBy;
	   public  java.lang.String getModifiedBy(){
	      return modifiedBy;
	   }
	   
	   public void setModifiedBy( java.lang.String modifiedBy){
	      this.modifiedBy = modifiedBy;
	   }
	
	   
	   public java.lang.String origin;
	   public  java.lang.String getOrigin(){
	      return origin;
	   }
	   
	   public void setOrigin( java.lang.String origin){
	      this.origin = origin;
	   }
	
	   
	   public java.lang.String preferredDefinition;
	   public  java.lang.String getPreferredDefinition(){
	      return preferredDefinition;
	   }
	   
	   public void setPreferredDefinition( java.lang.String preferredDefinition){
	      this.preferredDefinition = preferredDefinition;
	   }
	
	   
	   public java.lang.String preferredName;
	   public  java.lang.String getPreferredName(){
	      return preferredName;
	   }
	   
	   public void setPreferredName( java.lang.String preferredName){
	      this.preferredName = preferredName;
	   }
	
	   
	   public java.lang.Long publicID;
	   public  java.lang.Long getPublicID(){
	      return publicID;
	   }
	   
	   public void setPublicID( java.lang.Long publicID){
	      this.publicID = publicID;
	   }
	
	   
	   public java.lang.String registrationStatus;
	   public  java.lang.String getRegistrationStatus(){
	      return registrationStatus;
	   }
	   
	   public void setRegistrationStatus( java.lang.String registrationStatus){
	      this.registrationStatus = registrationStatus;
	   }
	
	   
	   public java.lang.String unresolvedIssue;
	   public  java.lang.String getUnresolvedIssue(){
	      return unresolvedIssue;
	   }
	   
	   public void setUnresolvedIssue( java.lang.String unresolvedIssue){
	      this.unresolvedIssue = unresolvedIssue;
	   }
	
	   
	   public java.lang.Float version;
	   public  java.lang.Float getVersion(){
	      return version;
	   }
	   
	   public void setVersion( java.lang.Float version){
	      this.version = version;
	   }
	
	   
	   public java.lang.String workflowStatusDescription;
	   public  java.lang.String getWorkflowStatusDescription(){
	      return workflowStatusDescription;
	   }
	   
	   public void setWorkflowStatusDescription( java.lang.String workflowStatusDescription){
	      this.workflowStatusDescription = workflowStatusDescription;
	   }
	
	   
	   public java.lang.String workflowStatusName;
	   public  java.lang.String getWorkflowStatusName(){
	      return workflowStatusName;
	   }
	   
	   public void setWorkflowStatusName( java.lang.String workflowStatusName){
	      this.workflowStatusName = workflowStatusName;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cadsr.domain.ws.Context context;
			public gov.nih.nci.cadsr.domain.ws.Context getContext(){
			  return context;
                        }
		   
	      
	               
	   
	   
	   
	   public void setContext(gov.nih.nci.cadsr.domain.ws.Context context){
		this.context = context;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection administeredComponentClassSchemeItemCollection = new java.util.HashSet();
			public java.util.Collection getAdministeredComponentClassSchemeItemCollection(){
	              return administeredComponentClassSchemeItemCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAdministeredComponentClassSchemeItemCollection(java.util.Collection administeredComponentClassSchemeItemCollection){
	   		this.administeredComponentClassSchemeItemCollection = administeredComponentClassSchemeItemCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection designationCollection = new java.util.HashSet();
			public java.util.Collection getDesignationCollection(){
	              return designationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDesignationCollection(java.util.Collection designationCollection){
	   		this.designationCollection = designationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection referenceDocumentCollection = new java.util.HashSet();
			public java.util.Collection getReferenceDocumentCollection(){
	              return referenceDocumentCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setReferenceDocumentCollection(java.util.Collection referenceDocumentCollection){
	   		this.referenceDocumentCollection = referenceDocumentCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection administeredComponentContactCollection = new java.util.HashSet();
			public java.util.Collection getAdministeredComponentContactCollection(){
	              return administeredComponentContactCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAdministeredComponentContactCollection(java.util.Collection administeredComponentContactCollection){
	   		this.administeredComponentContactCollection = administeredComponentContactCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection definitionCollection = new java.util.HashSet();
			public java.util.Collection getDefinitionCollection(){
	              return definitionCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setDefinitionCollection(java.util.Collection definitionCollection){
	   		this.definitionCollection = definitionCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AdministeredComponent) {
				AdministeredComponent c =(AdministeredComponent)obj; 			 
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
