package gov.nih.nci.cabio.domain;

import gov.nih.nci.cabio.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * The protocol associated with a clinical trial; organizes administrative information about the 
   * trial such as Organization ID, participants, phase, etc., and provides access to the administered 
   * Agents. 
   * 
   */

public  class ClinicalTrialProtocol 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String bigid;
	public java.lang.String getBigid()
	{
		return bigid;
	}
	public void setBigid(java.lang.String bigid)
	{
		this.bigid = bigid;
	}
	
		
	public java.lang.String currentStatus;
	public java.lang.String getCurrentStatus()
	{
		return currentStatus;
	}
	public void setCurrentStatus(java.lang.String currentStatus)
	{
		this.currentStatus = currentStatus;
	}
	
		
	public java.util.Date currentStatusDate;
	public java.util.Date getCurrentStatusDate()
	{
		return currentStatusDate;
	}
	public void setCurrentStatusDate(java.util.Date currentStatusDate)
	{
		this.currentStatusDate = currentStatusDate;
	}
	
		
	public java.lang.String documentNumber;
	public java.lang.String getDocumentNumber()
	{
		return documentNumber;
	}
	public void setDocumentNumber(java.lang.String documentNumber)
	{
		this.documentNumber = documentNumber;
	}
	
		
	public java.lang.Long id;
	public java.lang.Long getId()
	{
		return id;
	}
	public void setId(java.lang.Long id)
	{
		this.id = id;
	}
	
		
	public java.lang.String leadOrganizationId;
	public java.lang.String getLeadOrganizationId()
	{
		return leadOrganizationId;
	}
	public void setLeadOrganizationId(java.lang.String leadOrganizationId)
	{
		this.leadOrganizationId = leadOrganizationId;
	}
	
		
	public java.lang.String leadOrganizationName;
	public java.lang.String getLeadOrganizationName()
	{
		return leadOrganizationName;
	}
	public void setLeadOrganizationName(java.lang.String leadOrganizationName)
	{
		this.leadOrganizationName = leadOrganizationName;
	}
	
		
	public java.lang.String NIHAdminCode;
	public java.lang.String getNIHAdminCode()
	{
		return NIHAdminCode;
	}
	public void setNIHAdminCode(java.lang.String NIHAdminCode)
	{
		this.NIHAdminCode = NIHAdminCode;
	}
	
		
	public java.lang.String participationType;
	public java.lang.String getParticipationType()
	{
		return participationType;
	}
	public void setParticipationType(java.lang.String participationType)
	{
		this.participationType = participationType;
	}
	
		
	public java.lang.String PDQIdentifier;
	public java.lang.String getPDQIdentifier()
	{
		return PDQIdentifier;
	}
	public void setPDQIdentifier(java.lang.String PDQIdentifier)
	{
		this.PDQIdentifier = PDQIdentifier;
	}
	
		
	public java.lang.String phase;
	public java.lang.String getPhase()
	{
		return phase;
	}
	public void setPhase(java.lang.String phase)
	{
		this.phase = phase;
	}
	
		
	public java.lang.String PIName;
	public java.lang.String getPIName()
	{
		return PIName;
	}
	public void setPIName(java.lang.String PIName)
	{
		this.PIName = PIName;
	}
	
		
	public java.lang.String title;
	public java.lang.String getTitle()
	{
		return title;
	}
	public void setTitle(java.lang.String title)
	{
		this.title = title;
	}
	
		
	public java.lang.String treatmentFlag;
	public java.lang.String getTreatmentFlag()
	{
		return treatmentFlag;
	}
	public void setTreatmentFlag(java.lang.String treatmentFlag)
	{
		this.treatmentFlag = treatmentFlag;
	}
	
	
		
		
	private java.util.Collection protocolAssociationCollection = new java.util.HashSet();
	public java.util.Collection getProtocolAssociationCollection()
	{
		if (protocolAssociationCollection==null || protocolAssociationCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.ProtocolAssociation as child, gov.nih.nci.cabio.domain.ClinicalTrialProtocol as parent  where child in elements(parent.protocolAssociationCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.ProtocolAssociation");				 
				protocolAssociationCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClinicalTrialProtocol.class.getName());
				log.error("ClinicalTrialProtocol:getProtocolAssociationCollection throws exception ... ...",ex);
			}
		}	
		return protocolAssociationCollection;
	}
	
	public void setProtocolAssociationCollection(java.util.Collection protocolAssociationCollection)
	{
		this.protocolAssociationCollection = protocolAssociationCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection histopathologyCollection = new java.util.HashSet();
	public java.util.Collection getHistopathologyCollection()
	{
		if (histopathologyCollection==null || histopathologyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Histopathology as child, gov.nih.nci.cabio.domain.ClinicalTrialProtocol as parent  where child in elements(parent.histopathologyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Histopathology");				 
				histopathologyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClinicalTrialProtocol.class.getName());
				log.error("ClinicalTrialProtocol:getHistopathologyCollection throws exception ... ...",ex);
			}
		}	
		return histopathologyCollection;
	}
	
	public void setHistopathologyCollection(java.util.Collection histopathologyCollection)
	{
		this.histopathologyCollection = histopathologyCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection agentCollection = new java.util.HashSet();
	public java.util.Collection getAgentCollection()
	{
		if (agentCollection==null || agentCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.Agent as child, gov.nih.nci.cabio.domain.ClinicalTrialProtocol as parent  where child in elements(parent.agentCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Agent");				 
				agentCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClinicalTrialProtocol.class.getName());
				log.error("ClinicalTrialProtocol:getAgentCollection throws exception ... ...",ex);
			}
		}	
		return agentCollection;
	}
	
	public void setAgentCollection(java.util.Collection agentCollection)
	{
		this.agentCollection = agentCollection;
	}	
		
	
	
	
		
		
	private java.util.Collection diseaseOntologyCollection = new java.util.HashSet();
	public java.util.Collection getDiseaseOntologyCollection()
	{
		if (diseaseOntologyCollection==null || diseaseOntologyCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.DiseaseOntology as child, gov.nih.nci.cabio.domain.ClinicalTrialProtocol as parent  where child in elements(parent.diseaseOntologyCollection) and parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.DiseaseOntology");				 
				diseaseOntologyCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(ClinicalTrialProtocol.class.getName());
				log.error("ClinicalTrialProtocol:getDiseaseOntologyCollection throws exception ... ...",ex);
			}
		}	
		return diseaseOntologyCollection;
	}
	
	public void setDiseaseOntologyCollection(java.util.Collection diseaseOntologyCollection)
	{
		this.diseaseOntologyCollection = diseaseOntologyCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ClinicalTrialProtocol) 
		{
			ClinicalTrialProtocol c =(ClinicalTrialProtocol)obj; 			 
			Long thisId = getId();		
			
			if(thisId != null && thisId.equals(c.getId()))
				eq = true;
			
			}
			return eq;
		}
		
	public int hashCode()
	{
		int h = 0;
		
		if(getId() != null)
			h += getId().hashCode();
		
		return h;
	}
}