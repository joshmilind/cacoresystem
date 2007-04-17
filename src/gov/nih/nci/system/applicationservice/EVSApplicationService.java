/**
 * 
 */
package gov.nih.nci.system.applicationservice;

import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.system.query.cql.CQLQuery;

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

/**
 * @author safrant
 *
 */
public abstract class EVSApplicationService extends ApplicationService
		implements LexBIGService {

	/**
	 * 
	 */
	public EVSApplicationService() {
		// TODO Auto-generated constructor stub
	}

	public static EVSApplicationService getRemoteInstance()
	{
		return EVSApplicationServiceProvider.getRemoteInstance();
	}
	
	public static EVSApplicationService getRemoteInstance(String URL)
	{
		return EVSApplicationServiceProvider.getRemoteInstance(URL);
	}
	
	public static EVSApplicationService getLocalInstance()
	{
		return EVSApplicationServiceProvider.getLocalInstance();
	}

	public static EVSApplicationService getInstance()
	{
		return EVSApplicationServiceProvider.getApplicationService();
	}
	
	protected abstract EVSApplicationService getBeanInstance();
	
	protected abstract EVSApplicationService getBeanInstance(String URL);
	
//	public abstract void setSearchCaseSensitivity(boolean caseSensitivity);
//
//	public abstract void setRecordsCount(int recordsCount) throws ApplicationException;
//	
//	public abstract int getQueryRowCount(Object criteria, String targetClassName) throws ApplicationException;
//
//	public abstract List query(DetachedCriteria detachedCriteria, String targetClassName) throws ApplicationException;
//
//	public abstract List query(HQLCriteria hqlCriteria, String targetClassName) throws ApplicationException;
//
//	public abstract List query(CQLQuery cqlQuery, String targetClassName) throws ApplicationException;
//	
//	public abstract List query(Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException;
//
//	public abstract List evsSearch(EVSQuery evsCriterion) throws ApplicationException;
//    
//    public abstract boolean exist(String bigId) throws ApplicationException;
//    public abstract Object getDataObject(String bigId) throws ApplicationException;
//
//	public abstract List search(Class targetClass, Object obj) throws ApplicationException;
//
//	public abstract List search(Class targetClass, List objList) throws ApplicationException;
//
//	public abstract List search(String path, Object obj) throws ApplicationException;
//
//	public abstract List search(String path, List objList) throws ApplicationException;
//
//	/*@WRITABLE_API_START@*/
//	public abstract Object createObject(Object object) throws ApplicationException;
//	/*@WRITABLE_API_END@*/
//
//	/*@WRITABLE_API_START@*/
//	public abstract Object updateObject(Object object) throws ApplicationException;
//	/*@WRITABLE_API_END@*/
//
//	/*@WRITABLE_API_START@*/
//	public abstract void removeObject(Object object) throws ApplicationException;
//	/*@WRITABLE_API_END@*/
//
//	/*@WRITABLE_API_START@*/
//	public abstract List getObjects(Object object) throws ApplicationException;
//	/*@WRITABLE_API_END@*/
	
	
        public abstract CodedNodeSet getCodingSchemeConcepts(String codingScheme, CodingSchemeVersionOrTag versionOrTag)throws LBException ;
        public abstract CodedNodeSet getCodingSchemeConcepts(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, boolean activeOnly)throws LBException ;
        public abstract CodedNodeSet getCodingSchemeConcepts(ValueDomainEntryNodeSet nodeSet) throws LBException;
        public abstract Filter   getFilter(java.lang.String name)throws LBException ;
        public abstract  ExtensionDescriptionList    getFilterExtensions() ;
        public abstract GenericExtension   getGenericExtension(java.lang.String name)throws LBException ; 
        public abstract ExtensionDescriptionList   getGenericExtensions() ; 
        public abstract HistoryService     getHistoryService(java.lang.String codingScheme)throws LBException ; 
        public abstract java.util.Date     getLastUpdateTime()throws LBInvocationException ; 
        public abstract ModuleDescriptionList  getMatchAlgorithms() ;
        public abstract CodedNodeGraph     getNodeGraph(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, java.lang.String relationsName)throws LBException ; 
        public abstract LexBIGServiceManager   getServiceManager(java.lang.Object credentials)throws LBException ; 
        public abstract LexBIGServiceMetadata  getServiceMetadata()throws LBException ; 
        public abstract Sort   getSortAlgorithm(java.lang.String name)throws LBException ;
        public abstract SortDescriptionList    getSortAlgorithms(SortContext context) ; 
        public abstract CodingSchemeRenderingList   getSupportedCodingSchemes()throws LBInvocationException ; 
        public abstract ValueDomainRenderingList   getSupportedValueDomains()throws LBInvocationException ; 
        public abstract ValueDomainEntryNodeSet   getValueDomainEntries(ValueDomainNodeSet nodeSet) throws LBException ;
        public abstract ValueDomainNodeSet   getValueDomains(boolean activeOnly) throws LBException;
        public abstract CodingScheme   resolveCodingScheme(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag)throws LBException ; 
        public abstract ValueDomain   resolveValueDomain(java.lang.String valueDomain, ValueDomainVersionOrTag versionOrTag)throws LBException ; 


}
