package gov.nih.nci.system.comm.common;

import gov.nih.nci.common.util.ClientInfo;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

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


public interface ApplicationServiceProxy
{

	public abstract String authenticate(String userId, String password) throws ApplicationException;

	public abstract void logOut(String sessionKey);

	public abstract void setSearchCaseSensitivity(ClientInfo clientInfo);

	public abstract int getQueryRowCount(ClientInfo clientInfo, Object criteria, String targetClassName) throws ApplicationException;

	public abstract void setRecordsCount (ClientInfo clientInfo);

	public abstract List search(ClientInfo clientInfo, String path, List objList) throws ApplicationException;

	public abstract List search(ClientInfo clientInfo, String path, Object obj) throws ApplicationException;

	public abstract List search(ClientInfo clientInfo, Class targetClass, List objList) throws ApplicationException;

	public abstract List search(ClientInfo clientInfo, Class targetClass, Object obj) throws ApplicationException;

	public abstract List query(ClientInfo clientInfo, Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException;

	public abstract List query(ClientInfo clientInfo, DetachedCriteria detachedCriteria, String targetClassName) throws ApplicationException;

	public abstract List query(ClientInfo clientInfo, HQLCriteria hqlCriteria, String targetClassName) throws ApplicationException;

	public abstract List query(ClientInfo clientInfo, CQLQuery cqlQuery, String targetClassName) throws ApplicationException;
	
	public abstract List evsSearch(ClientInfo clientInfo, EVSQuery evsCriterion) throws ApplicationException;
    
    public abstract boolean exist(String bigId) throws ApplicationException;
    
    public abstract Object getDataObject(ClientInfo clientInfo, String bigId) throws ApplicationException;

	/*@WRITABLE_API_START@*/
	public abstract Object createObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException;
	/*@WRITABLE_API_END@*/
	
	/*@WRITABLE_API_START@*/
	public abstract Object updateObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException;
	/*@WRITABLE_API_END@*/
	
	/*@WRITABLE_API_START@*/
	public abstract void removeObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException;
	/*@WRITABLE_API_END@*/
	
	/*@WRITABLE_API_START@*/
	public abstract List getObjects(ClientInfo clientInfo, Object domainobject) throws ApplicationException;
	/*@WRITABLE_API_END@*/
	/****************************/
    
    public abstract CodedNodeSet getCodingSchemeConcepts(String codingScheme, CodingSchemeVersionOrTag versionOrTag)throws LBException ;
    public abstract CodedNodeSet getCodingSchemeConcepts(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, boolean activeOnly)throws LBException ;
    public abstract CodedNodeSet getCodingSchemeConcepts(ValueDomainEntryNodeSet nodeSet) throws LBException;
    public abstract Filter   getFilter(java.lang.String name)throws LBException ;
    public abstract  ExtensionDescriptionList    getFilterExtensions();
    public abstract GenericExtension   getGenericExtension(java.lang.String name) throws LBException; 
    public abstract ExtensionDescriptionList   getGenericExtensions() ; 
    public abstract HistoryService     getHistoryService(java.lang.String codingScheme)throws LBException ; 
    public abstract java.util.Date     getLastUpdateTime()throws LBInvocationException ; 
    public abstract ModuleDescriptionList  getMatchAlgorithms();
    public abstract CodedNodeGraph     getNodeGraph(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, java.lang.String relationsName)throws LBException ; 
    public abstract LexBIGServiceManager   getServiceManager(java.lang.Object credentials)throws LBException ; 
    public abstract LexBIGServiceMetadata  getServiceMetadata()throws LBException ; 
    public abstract Sort   getSortAlgorithm(java.lang.String name)throws LBException ;
    public abstract SortDescriptionList    getSortAlgorithms(SortContext context); 
    public abstract CodingSchemeRenderingList   getSupportedCodingSchemes()throws LBInvocationException ; 
    public abstract ValueDomainRenderingList  getSupportedValueDomains() throws LBInvocationException ;
    public abstract ValueDomainEntryNodeSet  getValueDomainEntries(ValueDomainNodeSet nodeSet) throws LBException;
    public abstract ValueDomainNodeSet  getValueDomains(boolean activeOnly) throws LBException;
    public abstract CodingScheme   resolveCodingScheme(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag)throws LBException ; 
    public abstract ValueDomain   resolveValueDomain(java.lang.String valueDomain, ValueDomainVersionOrTag versionOrTag)throws LBException ; 
	
}
