package gov.nih.nci.common.provenance.domain;

import gov.nih.nci.common.provenance.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A record describing the source of an assertion (datum) contained in an object.
   */

public  class Provenance 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String evidenceCode;
	public java.lang.String getEvidenceCode()
	{
		return evidenceCode;
	}
	public void setEvidenceCode(java.lang.String evidenceCode)
	{
		this.evidenceCode = evidenceCode;
	}
	
		
	public java.lang.String fullyQualifiedClassName;
	public java.lang.String getFullyQualifiedClassName()
	{
		return fullyQualifiedClassName;
	}
	public void setFullyQualifiedClassName(java.lang.String fullyQualifiedClassName)
	{
		this.fullyQualifiedClassName = fullyQualifiedClassName;
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
	
		
	private java.lang.Long immediateSourceId;
	public java.lang.Long getImmediateSourceId()
	{
		return immediateSourceId;
	}
	public void setImmediateSourceId(java.lang.Long immediateSourceId)
	{
		this.immediateSourceId = immediateSourceId;
	}
	
		
	public java.lang.String objectIdentifier;
	public java.lang.String getObjectIdentifier()
	{
		return objectIdentifier;
	}
	public void setObjectIdentifier(java.lang.String objectIdentifier)
	{
		this.objectIdentifier = objectIdentifier;
	}
	
		
	private java.lang.Long originalSourceId;
	public java.lang.Long getOriginalSourceId()
	{
		return originalSourceId;
	}
	public void setOriginalSourceId(java.lang.Long originalSourceId)
	{
		this.originalSourceId = originalSourceId;
	}
	
		
	private java.lang.Long supplyingSourceId;
	public java.lang.Long getSupplyingSourceId()
	{
		return supplyingSourceId;
	}
	public void setSupplyingSourceId(java.lang.Long supplyingSourceId)
	{
		this.supplyingSourceId = supplyingSourceId;
	}
	
		
	public java.lang.String transformation;
	public java.lang.String getTransformation()
	{
		return transformation;
	}
	public void setTransformation(java.lang.String transformation)
	{
		this.transformation = transformation;
	}
	
	
		
		
	private gov.nih.nci.common.provenance.domain.Source supplyingSource;
	public gov.nih.nci.common.provenance.domain.Source getSupplyingSource()
	{
			
		if(supplyingSource==null ||  supplyingSource.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.common.provenance.domain.Source as child where child.id in (select parent.supplyingSource.id from gov.nih.nci.common.provenance.domain.Provenance as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.common.provenance.domain.Source");				 
				if (resultList!=null && resultList.size()>0) 
					supplyingSource = (gov.nih.nci.common.provenance.domain.Source)resultList.get(0);
				else
					supplyingSource = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Provenance.class.getName());
				log.error("Provenance:getSupplyingSource throws exception ... ...",ex);
			}
		}
		return supplyingSource;	
					
	}

	public void setSupplyingSource(gov.nih.nci.common.provenance.domain.Source supplyingSource)
	{
		this.supplyingSource = supplyingSource;
	}
		
	
	
	
		
		
	private gov.nih.nci.common.provenance.domain.SourceReference sourceReference;
	public gov.nih.nci.common.provenance.domain.SourceReference getSourceReference()
	{
			
		if(sourceReference==null ||  sourceReference.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.common.provenance.domain.SourceReference as child where child.id in (select parent.sourceReference.id from gov.nih.nci.common.provenance.domain.Provenance as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.common.provenance.domain.SourceReference");				 
				if (resultList!=null && resultList.size()>0) 
					sourceReference = (gov.nih.nci.common.provenance.domain.SourceReference)resultList.get(0);
				else
					sourceReference = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Provenance.class.getName());
				log.error("Provenance:getSourceReference throws exception ... ...",ex);
			}
		}
		return sourceReference;	
					
	}

	public void setSourceReference(gov.nih.nci.common.provenance.domain.SourceReference sourceReference)
	{
		this.sourceReference = sourceReference;
	}
		
	
	
	
		
		
	private gov.nih.nci.common.provenance.domain.Source immediateSource;
	public gov.nih.nci.common.provenance.domain.Source getImmediateSource()
	{
			
		if(immediateSource==null ||  immediateSource.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.common.provenance.domain.Source as child where child.id in (select parent.immediateSource.id from gov.nih.nci.common.provenance.domain.Provenance as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.common.provenance.domain.Source");				 
				if (resultList!=null && resultList.size()>0) 
					immediateSource = (gov.nih.nci.common.provenance.domain.Source)resultList.get(0);
				else
					immediateSource = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Provenance.class.getName());
				log.error("Provenance:getImmediateSource throws exception ... ...",ex);
			}
		}
		return immediateSource;	
					
	}

	public void setImmediateSource(gov.nih.nci.common.provenance.domain.Source immediateSource)
	{
		this.immediateSource = immediateSource;
	}
		
	
	
	
		
		
	private gov.nih.nci.common.provenance.domain.Source originalSource;
	public gov.nih.nci.common.provenance.domain.Source getOriginalSource()
	{
			
		if(originalSource==null ||  originalSource.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select child from gov.nih.nci.common.provenance.domain.Source as child where child.id in (select parent.originalSource.id from gov.nih.nci.common.provenance.domain.Provenance as parent where parent.id="+idString+")";
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.common.provenance.domain.Source");				 
				if (resultList!=null && resultList.size()>0) 
					originalSource = (gov.nih.nci.common.provenance.domain.Source)resultList.get(0);
				else
					originalSource = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Provenance.class.getName());
				log.error("Provenance:getOriginalSource throws exception ... ...",ex);
			}
		}
		return originalSource;	
					
	}

	public void setOriginalSource(gov.nih.nci.common.provenance.domain.Source originalSource)
	{
		this.originalSource = originalSource;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Provenance) 
		{
			Provenance c =(Provenance)obj; 			 
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