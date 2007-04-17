/**
 * 
 */
package gov.nih.nci.system.comm.client;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.InputStreamResource;

import gov.nih.nci.common.util.ClientInfo;
import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.EVSApplicationService;
import gov.nih.nci.system.comm.common.EVSApplicationServiceProxy;
import gov.nih.nci.system.query.cql.CQLQuery;

/**
 * @author safrant
 *
 */
public class EVSApplicationServiceClientImpl extends EVSApplicationService {
	private static EVSApplicationServiceProxy	applicationServiceProxy;
	private static EVSApplicationService applicationService;
	private static int recordsCount = 0;
	private static int maxRecordsCount = 0;
	private static boolean caseSensitivity = false;	

	private static Logger log= Logger.getLogger(ApplicationServiceClientImpl.class.getName());	

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#createObject(java.lang.Object)
	 */
	@Override
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public Object createObject(Object object) throws ApplicationException {
		return applicationServiceProxy.createObject(getClientInfo(), object);
	}
	/*@WRITABLE_API_END@*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#evsSearch(gov.nih.nci.evs.query.EVSQuery)
	 */
	@Override
	public List evsSearch(EVSQuery evsCriterion) throws ApplicationException {
		return applicationServiceProxy.evsSearch(getClientInfo(), evsCriterion);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#exist(java.lang.String)
	 */
	@Override
	public boolean exist(String bigId) throws ApplicationException {
        return applicationServiceProxy.exist(bigId);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#getBeanInstance()
	 */
	@Override
	protected EVSApplicationService getBeanInstance() {
		applicationServiceProxy = getRemoteServiceFromClassPath();
		applicationService = new EVSApplicationServiceClientImpl();
		return applicationService;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#getBeanInstance(java.lang.String)
	 */
	@Override
	protected EVSApplicationService getBeanInstance(String URL) {
		applicationServiceProxy = getRemoteServiceFromPath(URL);
		ClientSession.getInstance(applicationServiceProxy);
		return new EVSApplicationServiceClientImpl();
	}
	
	private static EVSApplicationServiceProxy getRemoteServiceFromClassPath()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext(Constant.REMOTE_SERVICE_FILE_NAME);
		EVSApplicationServiceProxy applicationServiceProxy = (EVSApplicationServiceProxy) ctx.getBean(Constant.REMOTE_APPLICATION_SERVICE);
		return applicationServiceProxy;
	}

	private static EVSApplicationServiceProxy getRemoteServiceFromPath(String URL)
	{
		String xmlFileString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\"><beans><bean id=\"remoteService\" class=\"org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean\"><property name=\"serviceUrl\"><value>" + URL + "</value></property><property name=\"serviceInterface\"><value>gov.nih.nci.system.comm.common.ApplicationServiceProxy</value></property></bean></beans>";
		GenericApplicationContext ctx = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		InputStream inputStream = new ByteArrayInputStream(xmlFileString.getBytes());
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
		xmlReader.loadBeanDefinitions(inputStreamResource);
		ctx.refresh();
		EVSApplicationServiceProxy applicationServiceProxy = (EVSApplicationServiceProxy) ctx.getBean(Constant.REMOTE_APPLICATION_SERVICE);
		return applicationServiceProxy;
	}	

	private ClientInfo getClientInfo()
	{
		ClientSession cs = ClientSession.getInstance();
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setUserName(cs.getSessionKey());
		clientInfo.setRecordsCount(EVSApplicationServiceClientImpl.recordsCount);
		clientInfo.setCaseSensitivity(EVSApplicationServiceClientImpl.caseSensitivity);
		return clientInfo;
	}	

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#getDataObject(java.lang.String)
	 */
	@Override
	public Object getDataObject(String bigId) throws ApplicationException {
        return applicationServiceProxy.getDataObject(getClientInfo(), bigId);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#getObjects(java.lang.Object)
	 */
	@Override
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public List getObjects(Object object) throws ApplicationException {
		return applicationServiceProxy.getObjects(getClientInfo(), object);
	}
	/*@WRITABLE_API_END@*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#getQueryRowCount(java.lang.Object, java.lang.String)
	 */
	@Override
	public int getQueryRowCount(Object criteria, String targetClassName)
			throws ApplicationException {
		return applicationServiceProxy.getQueryRowCount(getClientInfo(), criteria, targetClassName);
	} 	

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#query(org.hibernate.criterion.DetachedCriteria, java.lang.String)
	 */
	@Override
	public List query(DetachedCriteria detachedCriteria, String targetClassName)
			throws ApplicationException {
		return applicationServiceProxy.query(getClientInfo(), detachedCriteria, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#query(gov.nih.nci.common.util.HQLCriteria, java.lang.String)
	 */
	@Override
	public List query(HQLCriteria hqlCriteria, String targetClassName)
			throws ApplicationException {
		return applicationServiceProxy.query(getClientInfo(), hqlCriteria, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#query(gov.nih.nci.system.query.cql.CQLQuery, java.lang.String)
	 */
	@Override
	public List query(CQLQuery cqlQuery, String targetClassName)
			throws ApplicationException {
		return applicationServiceProxy.query(getClientInfo(), cqlQuery, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#query(java.lang.Object, int, int, java.lang.String)
	 */
	@Override
	public List query(Object criteria, int firstRow, int resultsPerQuery,
			String targetClassName) throws ApplicationException {
		return applicationServiceProxy.query(getClientInfo(), criteria, firstRow, resultsPerQuery, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#removeObject(java.lang.Object)
	 */
	@Override
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public void removeObject(Object object) throws ApplicationException {
		applicationServiceProxy.removeObject(getClientInfo(), object);
	}
	/*@WRITABLE_API_END@*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#search(java.lang.Class, java.lang.Object)
	 */
	@Override
	public List search(Class targetClass, Object obj)
			throws ApplicationException {
		return applicationServiceProxy.search(getClientInfo(), targetClass, obj);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#search(java.lang.Class, java.util.List)
	 */
	@Override
	public List search(Class targetClass, List objList)
			throws ApplicationException {
		return applicationServiceProxy.search(getClientInfo(), targetClass, objList);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#search(java.lang.String, java.lang.Object)
	 */
	@Override
	public List search(String path, Object obj) throws ApplicationException {
		return applicationServiceProxy.search(getClientInfo(), path, obj);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#search(java.lang.String, java.util.List)
	 */
	@Override
	public List search(String path, List objList) throws ApplicationException {
		return applicationServiceProxy.search(getClientInfo(), path, objList);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#setRecordsCount(int)
	 */
	@Override
	public void setRecordsCount(int recordsCount) throws ApplicationException {
		if (recordsCount > maxRecordsCount)
			throw new ApplicationException("Illegal Value for RecordsCount: RECORDSPERQUERY cannot be greater than MAXRECORDSPERQUERY. RECORDSPERQUERY = " + recordsCount + " MAXRECORDSPERQUERY = " + maxRecordsCount);
		else
			EVSApplicationServiceClientImpl.recordsCount = recordsCount;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#setSearchCaseSensitivity(boolean)
	 */
	@Override
	public void setSearchCaseSensitivity(boolean caseSensitivity) {
		EVSApplicationServiceClientImpl.caseSensitivity = caseSensitivity;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.EVSApplicationService#updateObject(java.lang.Object)
	 */
	@Override
	/*@WRITABLE_API_START@*/
	// NOTE: Use only "//" for comments in the following method
	public Object updateObject(Object object) throws ApplicationException {
		return applicationServiceProxy.updateObject(getClientInfo(), object);
	}
	/*@WRITABLE_API_END@*/
	/*****************************/
    
    public CodedNodeSet getCodingSchemeConcepts(String codingScheme, CodingSchemeVersionOrTag versionOrTag)throws LBException {
         return applicationServiceProxy.getCodingSchemeConcepts(codingScheme, versionOrTag);
      }

     public  CodedNodeSet getCodingSchemeConcepts(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, boolean activeOnly)throws LBException {
         return applicationServiceProxy.getCodingSchemeConcepts(codingScheme, versionOrTag, activeOnly);
     }
     public  CodedNodeSet getCodingSchemeConcepts(ValueDomainEntryNodeSet nodeSet) throws LBException {
         return applicationServiceProxy.getCodingSchemeConcepts(nodeSet);
     }
     public Filter   getFilter(java.lang.String name) throws LBException {
         return applicationServiceProxy.getFilter(name);
     }
     public  ExtensionDescriptionList    getFilterExtensions(){
         return applicationServiceProxy.getFilterExtensions();
         }
     public GenericExtension   getGenericExtension(java.lang.String name)throws LBException {
         return applicationServiceProxy.getGenericExtension(name);
     } 
     public ExtensionDescriptionList   getGenericExtensions(){
         return applicationServiceProxy.getGenericExtensions();
     }
     public HistoryService     getHistoryService(java.lang.String codingScheme)throws LBException {
         return applicationServiceProxy.getHistoryService(codingScheme);
     } 
     public java.util.Date     getLastUpdateTime() throws LBInvocationException {
         return applicationServiceProxy.getLastUpdateTime();
     }
     public ModuleDescriptionList  getMatchAlgorithms(){
         return applicationServiceProxy.getMatchAlgorithms();
     }
     public CodedNodeGraph     getNodeGraph(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, java.lang.String relationsName)throws LBException {
         return applicationServiceProxy.getNodeGraph(codingScheme,versionOrTag,relationsName);
     } 
     public LexBIGServiceManager   getServiceManager(java.lang.Object credentials)throws LBException {
         return applicationServiceProxy.getServiceManager(credentials);
     } 
     public LexBIGServiceMetadata  getServiceMetadata() throws LBException {
         return applicationServiceProxy.getServiceMetadata();
     }
     public Sort   getSortAlgorithm(java.lang.String name) throws LBException {
         return applicationServiceProxy.getSortAlgorithm(name);
     }
     public SortDescriptionList    getSortAlgorithms(SortContext context) {
         return applicationServiceProxy.getSortAlgorithms(context);
     } 
     public CodingSchemeRenderingList   getSupportedCodingSchemes()throws LBInvocationException {
         return applicationServiceProxy.getSupportedCodingSchemes();
     } 
     public ValueDomainRenderingList  getSupportedValueDomains() throws LBInvocationException {
         return applicationServiceProxy.getSupportedValueDomains();
     }
     public ValueDomainEntryNodeSet  getValueDomainEntries(ValueDomainNodeSet nodeSet) throws LBException {
         return applicationServiceProxy.getValueDomainEntries(nodeSet);
     }
     public ValueDomainNodeSet  getValueDomains(boolean activeOnly) throws LBException{
         return applicationServiceProxy.getValueDomains(activeOnly);
     }
     public CodingScheme   resolveCodingScheme(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag) throws LBException {
         return applicationServiceProxy.resolveCodingScheme(codingScheme, versionOrTag);
     }
     public ValueDomain   resolveValueDomain(java.lang.String valueDomain, ValueDomainVersionOrTag versionOrTag) throws LBException {
         return applicationServiceProxy.resolveValueDomain(valueDomain, versionOrTag);
     }	

}