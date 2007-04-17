package gov.nih.nci.system.comm.server;

import java.util.Date;
import java.util.List;

import org.LexGrid.LexBIG.DataModel.Collections.CodingSchemeRenderingList;
import org.LexGrid.LexBIG.DataModel.Collections.ExtensionDescriptionList;
import org.LexGrid.LexBIG.DataModel.Collections.ModuleDescriptionList;
import org.LexGrid.LexBIG.DataModel.Collections.SortDescriptionList;
import org.LexGrid.LexBIG.DataModel.Collections.ValueDomainRenderingList;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.DataModel.Core.ValueDomainVersionOrTag;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.types.SortContext;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Exceptions.LBInvocationException;
import org.LexGrid.LexBIG.Extensions.Generic.GenericExtension;
import org.LexGrid.LexBIG.Extensions.Query.Filter;
import org.LexGrid.LexBIG.Extensions.Query.Sort;
import org.LexGrid.LexBIG.History.HistoryService;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeGraph;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.LexBIGServiceManager;
import org.LexGrid.LexBIG.LexBIGService.LexBIGServiceMetadata;
import org.LexGrid.LexBIG.LexBIGService.ValueDomainEntryNodeSet;
import org.LexGrid.LexBIG.LexBIGService.ValueDomainNodeSet;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.valueDomains.ValueDomain;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import gov.nih.nci.common.util.ClientInfo;
import gov.nih.nci.common.util.ClientInfoThreadVariable;
import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.common.util.SecurityConfiguration;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.EVSApplicationService;
import gov.nih.nci.system.comm.common.EVSApplicationServiceProxy;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.server.mgmt.SecurityEnabler;

public class EVSApplicationServiceServerImpl implements EVSApplicationServiceProxy {

	private EVSApplicationService applicationService;
	private SecurityEnabler securityEnabler;

	/**
	 * Default Constructor it takes in 
	 */
	public EVSApplicationServiceServerImpl()
	{
		securityEnabler = new SecurityEnabler(SecurityConfiguration.getApplicationName());
		ApplicationContext ctx = new ClassPathXmlApplicationContext(Constant.APPLICATION_SERVICE_FILE_NAME);
		applicationService = (EVSApplicationService) ctx.getBean(Constant.APPLICATION_SERVICE);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#authenticate(java.lang.String, java.lang.String)
	 */
	public String authenticate(String userId, String password) throws ApplicationException
	{
		return securityEnabler.authenticate(userId, password);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#logOut(java.lang.String)
	 */
	public void logOut(String sessionKey)
	{
		securityEnabler.logOut(sessionKey);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#setSearchCaseSensitivity(java.lang.String, boolean)
	 */
	public void setSearchCaseSensitivity(ClientInfo clientInfo)
	{
		// do nothing
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#setSearchCaseSensitivity(java.lang.String, boolean)
	 */
	public void setRecordsCount(ClientInfo clientInfo)
	{
		// do nothing
	}	

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(gov.nih.nci.common.util.ClientInfo, java.lang.String, java.util.List)
	 */
	public List search(ClientInfo clientInfo, String path, List objList) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.search(path, objList);

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(gov.nih.nci.common.util.ClientInfo, java.lang.String, java.lang.Object)
	 */
	public List search(ClientInfo clientInfo, String path, Object obj) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);		
		return applicationService.search(path, obj);

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(gov.nih.nci.common.util.ClientInfo, java.lang.Class, java.util.List)
	 */
	public List search(ClientInfo clientInfo, Class targetClass, List objList) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.search(targetClass, objList);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(gov.nih.nci.common.util.ClientInfo, java.lang.Class, java.lang.Object)
	 */
	public List search(ClientInfo clientInfo, Class targetClass, Object obj) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.search(targetClass, obj);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(gov.nih.nci.common.util.ClientInfo, java.lang.Object, int, int, java.lang.String)
	 */
	public List query(ClientInfo clientInfo, Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.query(criteria, firstRow, resultsPerQuery, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(gov.nih.nci.common.util.ClientInfo, org.hibernate.criterion.DetachedCriteria, java.lang.String)
	 */
	public List query(ClientInfo clientInfo, DetachedCriteria detachedCriteria, String targetClassName) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.query(detachedCriteria, targetClassName);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(gov.nih.nci.common.util.ClientInfo, gov.nih.nci.common.util.HQLCriteria, java.lang.String)
	 */
	public List query(ClientInfo clientInfo, HQLCriteria hqlCriteria, String targetClassName) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.query(hqlCriteria, targetClassName);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(gov.nih.nci.common.util.ClientInfo, gov.nih.nci.query.cql.CQLQuery, java.lang.String)
	 */
	public List query(ClientInfo clientInfo, CQLQuery cqlQuery, String targetClassName) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.query(cqlQuery, targetClassName);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#getQueryRowCount(gov.nih.nci.common.util.ClientInfo, java.lang.Object, java.lang.String)
	 */
	public int getQueryRowCount(ClientInfo clientInfo, Object criteria, String targetClassName) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.getQueryRowCount(criteria, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#evsSearch(gov.nih.nci.common.util.ClientInfo, gov.nih.nci.evs.query.EVSQuery)
	 */
	public List evsSearch(ClientInfo clientInfo, EVSQuery evsCriterion) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		
		return applicationService.evsSearch(evsCriterion);
	}
    
    public boolean exist(String bigId) throws ApplicationException
    {           
        return applicationService.exist(bigId);
    }
    
    public Object getDataObject(ClientInfo clientInfo, String bigId) throws ApplicationException
    {
        
        ClientInfoThreadVariable.setClientInfo(clientInfo);        
        return applicationService.getDataObject(bigId);
    }

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#createObject(gov.nih.nci.common.util.ClientInfo, java.lang.Object)
	 */	
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public Object createObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.createObject(domainobject);

	}
	/*@WRITABLE_API_END@*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#updateObject(gov.nih.nci.common.util.ClientInfo, java.lang.Object)
	 */
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public Object updateObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.updateObject(domainobject);
	}
	/*@WRITABLE_API_END@*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#removeObject(gov.nih.nci.common.util.ClientInfo, java.lang.Object)
	 */
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public void removeObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		applicationService.removeObject(domainobject);
	}
	/*@WRITABLE_API_END@*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#getObjects(gov.nih.nci.common.util.ClientInfo, java.lang.Object)
	 */
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public List getObjects(ClientInfo clientInfo, Object domainobject) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.getObjects(domainobject);
	}
	/*@WRITABLE_API_END@*/	
	
    /***********************************/
    public CodedNodeSet getCodingSchemeConcepts(String codingScheme, CodingSchemeVersionOrTag versionOrTag)throws LBException {
        return applicationService.getCodingSchemeConcepts(codingScheme, versionOrTag);
     }

    public  CodedNodeSet getCodingSchemeConcepts(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, boolean activeOnly)throws LBException {
        return applicationService.getCodingSchemeConcepts(codingScheme, versionOrTag, activeOnly);
    }
    public  CodedNodeSet getCodingSchemeConcepts(ValueDomainEntryNodeSet nodeSet)throws LBException {
        return applicationService.getCodingSchemeConcepts(nodeSet);
    }
    public Filter   getFilter(java.lang.String name) throws LBException {
        return applicationService.getFilter(name);
    }
    public  ExtensionDescriptionList    getFilterExtensions(){
        return applicationService.getFilterExtensions();
        }
    public GenericExtension   getGenericExtension(java.lang.String name) throws LBException{
        return applicationService.getGenericExtension(name);
    } 
    public ExtensionDescriptionList   getGenericExtensions(){
        return applicationService.getGenericExtensions();
    }
    public HistoryService     getHistoryService(java.lang.String codingScheme)throws LBException {
        return applicationService.getHistoryService(codingScheme);
    } 
    public java.util.Date     getLastUpdateTime() throws LBInvocationException {
        return applicationService.getLastUpdateTime();
    }
    public ModuleDescriptionList  getMatchAlgorithms(){
        return applicationService.getMatchAlgorithms();
    }
    public CodedNodeGraph     getNodeGraph(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, java.lang.String relationsName)throws LBException {
        return applicationService.getNodeGraph(codingScheme,versionOrTag,relationsName);
    } 
    public LexBIGServiceManager   getServiceManager(java.lang.Object credentials)throws LBException {
        return applicationService.getServiceManager(credentials);
    } 
    public LexBIGServiceMetadata  getServiceMetadata() throws LBException {
        return applicationService.getServiceMetadata();
    }
    public Sort   getSortAlgorithm(java.lang.String name) throws LBException {
        return applicationService.getSortAlgorithm(name);
    }
    public SortDescriptionList    getSortAlgorithms(SortContext context) {
        return applicationService.getSortAlgorithms(context);
    } 
    public CodingSchemeRenderingList   getSupportedCodingSchemes()throws LBInvocationException {
        return applicationService.getSupportedCodingSchemes();
    } 
    public ValueDomainRenderingList   getSupportedValueDomains()throws LBInvocationException {
        return applicationService.getSupportedValueDomains();
    } 
    public ValueDomainEntryNodeSet   getValueDomainEntries(ValueDomainNodeSet nodeSet)throws LBException {
        return applicationService.getValueDomainEntries(nodeSet);
    } 
    public ValueDomainNodeSet   getValueDomains(boolean activeOnly)throws LBException {
        return applicationService.getValueDomains(activeOnly);
    }
    public CodingScheme   resolveCodingScheme(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag) throws LBException {
        return applicationService.resolveCodingScheme(codingScheme, versionOrTag);
    }
    public ValueDomain   resolveValueDomain(java.lang.String valueDomain, ValueDomainVersionOrTag versionOrTag) throws LBException {
        return applicationService.resolveValueDomain(valueDomain, versionOrTag);
    }

}
