package gov.nih.nci.system.applicationservice.impl;

import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;
import gov.nih.nci.system.applicationservice.AuthorizationException;
import gov.nih.nci.system.dao.WritableDAO;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.server.mgmt.SecurityEnabler;
import gov.nih.nci.common.util.SecurityConfiguration;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.criterion.DetachedCriteria;

import org.apache.log4j.Logger;

import org.LexGrid.LexBIG.LexBIGService.*;
import org.LexGrid.LexBIG.Extensions.Query.*;
import org.LexGrid.LexBIG.DataModel.Collections.*;
import org.LexGrid.LexBIG.Extensions.Generic.*;
import org.LexGrid.LexBIG.History.*;
import org.LexGrid.LexBIG.LexBIGService.*;
import org.LexGrid.codingSchemes.*;
import org.LexGrid.LexBIG.DataModel.Core.*;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.types.*;
import org.LexGrid.LexBIG.Exceptions.*;
import org.LexGrid.valueDomains.ValueDomain;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ApplicationServiceImpl extends ApplicationService
{
	private static Logger log = Logger.getLogger(ApplicationServiceImpl.class.getName());
	private ApplicationServiceBusinessImpl applicationServiceBusinessImpl = null;
	private WritableDAO writableDAO = null;
	private SecurityEnabler securityEnabler = null;

	/**
	 * Default Constructor. It obtains appropriate implementation of the
	 * {@link ApplicationService}interface and caches it. It also instantiates
	 * the instance of writableDAO and caches it.
	 */
	public ApplicationServiceImpl()
	{
		this.applicationServiceBusinessImpl = ApplicationServiceBusinessImpl.getLocalInstance();
		this.writableDAO = new WritableDAO();
		this.securityEnabler = new SecurityEnabler(SecurityConfiguration.getApplicationName());
	}


	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#getBeanInstance()
	 */
	//@Override
	protected ApplicationService getBeanInstance()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#getBeanInstance(java.lang.String)
	 */
	//@Override
	protected ApplicationService getBeanInstance(String URL)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#setRecordsCount(int)
	 */
	//@Override
	public void setRecordsCount(int recordsCount) throws ApplicationException
	{
		try
		{
			this.applicationServiceBusinessImpl.setRecordsCount(recordsCount);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#setSearchCaseSensitivity(boolean)
	 */
	public void setSearchCaseSensitivity(boolean caseSensitivity)
	{
		this.applicationServiceBusinessImpl.setSearchCaseSensitivity(caseSensitivity);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#getQueryRowCount(java.lang.Object,
	 *      java.lang.String)
	 */
	public int getQueryRowCount(Object criteria, String targetClassName) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (!securityEnabler.hasAuthorization(targetClassName, "READ"))
				throw new AuthorizationException("User does not have privilege to perform a READ on " + targetClassName+ " object");
		}
		try
		{
			return this.applicationServiceBusinessImpl.getQueryRowCount(criteria, targetClassName);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(java.lang.Object,
	 *      java.lang.String)
	 */
	public List query(DetachedCriteria detachedcriteria, String targetClassName) throws ApplicationException
	{
		List list = null;
		try
		{
			list = this.applicationServiceBusinessImpl.query(detachedcriteria, targetClassName);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat(Constant.COMMA + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(java.lang.Object,
	 *      java.lang.String)
	 */
	public List query(HQLCriteria hqlcriteria, String targetClassName) throws ApplicationException
	{
		List list = null;
		try
		{
			list = this.applicationServiceBusinessImpl.query(hqlcriteria, targetClassName);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat(Constant.COMMA + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		return list;
	}	

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(gov.nih.nci.query.cql.CQLQuery, java.lang.String)
	 */
	public List query(CQLQuery cqlQuery, String targetClassName) throws ApplicationException
	{
		List list = null;	
		try
		{
			list = applicationServiceBusinessImpl.query(cqlQuery, targetClassName);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat(Constant.COMMA + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		return list;
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(java.lang.Object,
	 *      int, int, java.lang.String)
	 */
	public List query(Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException
	{
		List list = null;
		try
		{
			list = this.applicationServiceBusinessImpl.query(criteria, firstRow, resultsPerQuery, targetClassName);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat(Constant.COMMA + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#evsSearch(gov.nih.nci.evs.query.EVSQuery)
	 */
	public List evsSearch(EVSQuery evsCriterion) throws ApplicationException
	{
		try
		{
			return this.applicationServiceBusinessImpl.evsSearch(evsCriterion);
		}
		catch (Exception e)
		{
			throw new ApplicationException(e.getMessage());
		}
	}

    public boolean exist(String bigId) throws ApplicationException
    {
        try
        {
            return this.applicationServiceBusinessImpl.exist(bigId);
        }
        catch (Exception e)
        {
            throw new ApplicationException(e.getMessage());
        }
    }
    public Object getDataObject(String bigId) throws ApplicationException
    {
        try
        {
            return this.applicationServiceBusinessImpl.getDataObject(bigId);
        }
        catch (Exception e)
        {
            throw new ApplicationException(e.getMessage());
        }
    }


	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.Class,
	 *      java.lang.Object)
	 */
	public List search(Class targetClass, Object obj) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = targetClass.getName();
			if (obj != null)
				newPath = newPath.concat(Constant.COMMA + obj.getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		try
		{
			return this.applicationServiceBusinessImpl.search(targetClass, obj);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.Class,
	 *      java.util.List)
	 */
	public List search(Class targetClass, List objList) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = targetClass.getName();
			if (objList.size() != 0)
				newPath = newPath.concat(Constant.COMMA + objList.get(0).getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}	
		try
		{
			return this.applicationServiceBusinessImpl.search(targetClass, objList);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.String,
	 *      java.lang.Object)
	 */
	public List search(String path, Object obj) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = path;
			if (obj != null)
				newPath = newPath.concat(Constant.COMMA + obj.getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		try
		{
			return this.applicationServiceBusinessImpl.search(path, obj);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.String,
	 *      java.util.List)
	 */
	public List search(String path, List objList) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = path;
			if (objList.size() != 0)
				newPath = newPath.concat(Constant.COMMA + objList.get(0).getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		try
		{
			return this.applicationServiceBusinessImpl.search(path, objList);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#createObject(java.lang.Object)
	 */
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public Object createObject(Object domainobject) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String domainObjectName = domainobject.getClass().getName();
			if (!securityEnabler.hasAuthorization(domainObjectName, "CREATE"))
			{
				throw new AuthorizationException("User does not have privilege to CREATE " + domainObjectName + " object");
			}
		}
		return writableDAO.createObject(domainobject);
	}
	/*@WRITABLE_API_END@*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#updateObject(java.lang.Object)
	 */
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public Object updateObject(Object domainobject) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String domainObjectName = domainobject.getClass().getName();
			if (!securityEnabler.hasAuthorization(domainObjectName, "UPDATE"))
			{
				throw new AuthorizationException("User does not have privilege to UPDATE " + domainObjectName + " object");
			}
		}
		return writableDAO.updateObject(domainobject);
	}
	/*@WRITABLE_API_END@*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#removeObject(java.lang.Object)
	 */
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public void removeObject(Object domainobject) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String domainObjectName = domainobject.getClass().getName();
			if (!securityEnabler.hasAuthorization(domainObjectName, "DELETE"))
			{
				throw new ApplicationException("User does not have privilege to DELETE " + domainObjectName + " object");
			}
		}
		writableDAO.removeObject(domainobject);
	}
	/*@WRITABLE_API_END@*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#getObjects(java.lang.Object)
	 */
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public List getObjects(Object domainobject) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String domainObjectName = domainobject.getClass().getName();
			if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
			{
				throw new ApplicationException("User does not have privilege to READ " + domainObjectName + " object");
			}
		}
		return writableDAO.getObjects(domainobject);
	}
	/*@WRITABLE_API_END@*/
    /*************************/

    public CodedNodeSet getCodingSchemeConcepts(String codingScheme, CodingSchemeVersionOrTag versionOrTag)throws LBException{
        try {
          return this.applicationServiceBusinessImpl.getCodingSchemeConcepts(codingScheme, versionOrTag);
        }
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
     }

    public  CodedNodeSet getCodingSchemeConcepts(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, boolean activeOnly)throws LBException{
        try {
        return this.applicationServiceBusinessImpl.getCodingSchemeConcepts(codingScheme, versionOrTag, activeOnly);
        }
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    }
    public CodedNodeSet  getCodingSchemeConcepts(ValueDomainEntryNodeSet nodeSet) throws LBException{
        try {
        return this.applicationServiceBusinessImpl.getCodingSchemeConcepts(nodeSet);
        }
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    }
    public Filter   getFilter(java.lang.String name) throws LBException {
        try {
        return this.applicationServiceBusinessImpl.getFilter(name);
        }     
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    }
    public  ExtensionDescriptionList    getFilterExtensions() {
        try {
        return this.applicationServiceBusinessImpl.getFilterExtensions();
        }   
        catch (Exception e)
        {
             return null;
        }
        }
    public GenericExtension   getGenericExtension(java.lang.String name) throws LBException {
        try {
        return this.applicationServiceBusinessImpl.getGenericExtension(name);
        }   
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    } 
    public ExtensionDescriptionList   getGenericExtensions() {
        try {
        return this.applicationServiceBusinessImpl.getGenericExtensions();
        }
        catch (Exception e)
        {
             return null;
        }
    }
    public HistoryService     getHistoryService(java.lang.String codingScheme)throws LBException{
        try {
        return this.applicationServiceBusinessImpl.getHistoryService(codingScheme);
        }
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    } 
    public java.util.Date     getLastUpdateTime() throws LBInvocationException{
        try {
        return this.applicationServiceBusinessImpl.getLastUpdateTime();
        }    
        catch (Exception e)
        {
             throw new LBInvocationException(e.getMessage(), null);
        }
    }
    public ModuleDescriptionList  getMatchAlgorithms() {
        try {
        return this.applicationServiceBusinessImpl.getMatchAlgorithms();
        } 
        catch (Exception e)
        {
             return null;
        }
    }
    public CodedNodeGraph     getNodeGraph(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, java.lang.String relationsName)throws LBException{
        try {
        return this.applicationServiceBusinessImpl.getNodeGraph(codingScheme,versionOrTag,relationsName);
        }
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }     
    } 
    public LexBIGServiceManager   getServiceManager(java.lang.Object credentials)throws LBException {
        try {
        return this.applicationServiceBusinessImpl.getServiceManager(credentials);
        }    
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    } 
    public LexBIGServiceMetadata  getServiceMetadata() throws LBException{
        try {
        return this.applicationServiceBusinessImpl.getServiceMetadata();
        }
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    }
    public Sort   getSortAlgorithm(java.lang.String name) throws LBException {
        try {
        return this.applicationServiceBusinessImpl.getSortAlgorithm(name);
        }
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    }
    public SortDescriptionList    getSortAlgorithms(SortContext context) {
        try {
        return this.applicationServiceBusinessImpl.getSortAlgorithms(context);     
        } 
        catch (Exception e)
        {
             return null;
        }
    } 
    public CodingSchemeRenderingList   getSupportedCodingSchemes()throws LBInvocationException{
        try {
        return this.applicationServiceBusinessImpl.getSupportedCodingSchemes();
        }    
        catch (Exception e)
        {
             throw new LBInvocationException(e.getMessage(), null);
        }
    } 
    public ValueDomainRenderingList  getSupportedValueDomains() throws LBInvocationException {
        try {
        return this.applicationServiceBusinessImpl.getSupportedValueDomains();
        }   
        catch (Exception e)
        {
             throw new LBInvocationException(e.getMessage(), null);
        }
    } 
    public ValueDomainEntryNodeSet  getValueDomainEntries(ValueDomainNodeSet nodeSet) throws LBException{
        try {
        return this.applicationServiceBusinessImpl.getValueDomainEntries(nodeSet);
        }    
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    }
    public ValueDomainNodeSet  getValueDomains(boolean activeOnly) throws LBException {
        try {
        return this.applicationServiceBusinessImpl.getValueDomains(activeOnly);
        }     
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    }
    public CodingScheme   resolveCodingScheme(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag) throws LBException{
        try {
        return this.applicationServiceBusinessImpl.resolveCodingScheme(codingScheme, versionOrTag);
        }
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    }
    public ValueDomain   resolveValueDomain(java.lang.String valueDomain, ValueDomainVersionOrTag versionOrTag) throws LBException {
        try {
        return this.applicationServiceBusinessImpl.resolveValueDomain(valueDomain, versionOrTag);
        }   
        catch (Exception e)
        {
             throw new LBException(e.getMessage());
        }
    }


}
