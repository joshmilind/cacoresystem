package gov.nih.nci.system.comm.common;

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
import org.LexGrid.LexBIG.Utility.ConvenienceMethods;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.valueDomains.ValueDomain;

public interface EVSApplicationServiceProxy extends ApplicationServiceProxy {
    
    public abstract CodedNodeSet getCodingSchemeConcepts(String codingScheme, CodingSchemeVersionOrTag versionOrTag)throws LBException;
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
    public abstract Object executeRemotely(Object object, String methodName, String[] parameterClasses, Object[] args) throws Exception;
}
