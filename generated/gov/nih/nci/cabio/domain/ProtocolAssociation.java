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
   * An association class relating between Clinical Trial Protocols to Diseases.
   */

public  class ProtocolAssociation 	implements java.io.Serializable 
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
	
		
	public java.lang.String CTEPNAME;
	public java.lang.String getCTEPNAME()
	{
		return CTEPNAME;
	}
	public void setCTEPNAME(java.lang.String CTEPNAME)
	{
		this.CTEPNAME = CTEPNAME;
	}
	
		
	public java.lang.String diseaseCategory;
	public java.lang.String getDiseaseCategory()
	{
		return diseaseCategory;
	}
	public void setDiseaseCategory(java.lang.String diseaseCategory)
	{
		this.diseaseCategory = diseaseCategory;
	}
	
		
	public java.lang.String diseaseSubCategory;
	public java.lang.String getDiseaseSubCategory()
	{
		return diseaseSubCategory;
	}
	public void setDiseaseSubCategory(java.lang.String diseaseSubCategory)
	{
		this.diseaseSubCategory = diseaseSubCategory;
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
	
		
	public java.lang.Long IMTCODE;
	public java.lang.Long getIMTCODE()
	{
		return IMTCODE;
	}
	public void setIMTCODE(java.lang.Long IMTCODE)
	{
		this.IMTCODE = IMTCODE;
	}
	
	
		
		
	private gov.nih.nci.cabio.domain.DiseaseOntology diseaseOntology;
	public gov.nih.nci.cabio.domain.DiseaseOntology getDiseaseOntology()
	{
			
		if(diseaseOntology==null ||  diseaseOntology.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.DiseaseOntology as child where child.id in (select parent.diseaseOntology.id from gov.nih.nci.cabio.domain.ProtocolAssociation as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.DiseaseOntology");				 
				if (resultList!=null && resultList.size()>0) 
					diseaseOntology = (gov.nih.nci.cabio.domain.DiseaseOntology)resultList.get(0);
				else
					diseaseOntology = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ProtocolAssociation.class.getName());
				log.error("ProtocolAssociation:getDiseaseOntology throws exception ... ...",ex);
			}
		}
		return diseaseOntology;	
					
	}

	public void setDiseaseOntology(gov.nih.nci.cabio.domain.DiseaseOntology diseaseOntology)
	{
		this.diseaseOntology = diseaseOntology;
	}
		
	
	
	
		
		
	private gov.nih.nci.cabio.domain.ClinicalTrialProtocol clinicalTrialProtocol;
	public gov.nih.nci.cabio.domain.ClinicalTrialProtocol getClinicalTrialProtocol()
	{
			
		if(clinicalTrialProtocol==null ||  clinicalTrialProtocol.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.cabio.domain.ClinicalTrialProtocol as child where child.id in (select parent.clinicalTrialProtocol.id from gov.nih.nci.cabio.domain.ProtocolAssociation as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.ClinicalTrialProtocol");				 
				if (resultList!=null && resultList.size()>0) 
					clinicalTrialProtocol = (gov.nih.nci.cabio.domain.ClinicalTrialProtocol)resultList.get(0);
				else
					clinicalTrialProtocol = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(ProtocolAssociation.class.getName());
				log.error("ProtocolAssociation:getClinicalTrialProtocol throws exception ... ...",ex);
			}
		}
		return clinicalTrialProtocol;	
					
	}

	public void setClinicalTrialProtocol(gov.nih.nci.cabio.domain.ClinicalTrialProtocol clinicalTrialProtocol)
	{
		this.clinicalTrialProtocol = clinicalTrialProtocol;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof ProtocolAssociation) 
		{
			ProtocolAssociation c =(ProtocolAssociation)obj; 			 
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