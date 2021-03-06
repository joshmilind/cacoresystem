package gov.nih.nci.system.dao.impl.externalsystem;

import gov.nih.nci.system.dao.properties.EVSProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.LexGrid.LexBIG.DataModel.Collections.CodingSchemeRenderingList;
import org.LexGrid.LexBIG.DataModel.Collections.ConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Collections.ExtensionDescriptionList;
import org.LexGrid.LexBIG.DataModel.Collections.LocalNameList;
import org.LexGrid.LexBIG.DataModel.Collections.ModuleDescriptionList;
import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Collections.SortDescriptionList;
import org.LexGrid.LexBIG.DataModel.Collections.ValueDomainRenderingList;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeSummary;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.DataModel.Core.ValueDomainVersionOrTag;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.CodingSchemeRendering;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.types.SortContext;
import org.LexGrid.LexBIG.Extensions.Generic.GenericExtension;
import org.LexGrid.LexBIG.Extensions.Query.Filter;
import org.LexGrid.LexBIG.Extensions.Query.Sort;
import org.LexGrid.LexBIG.History.HistoryService;
import org.LexGrid.LexBIG.Impl.LexBIGServiceImpl;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeGraph;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.LexBIGService.LexBIGServiceManager;
import org.LexGrid.LexBIG.LexBIGService.LexBIGServiceMetadata;
import org.LexGrid.LexBIG.LexBIGService.ValueDomainEntryNodeSet;
import org.LexGrid.LexBIG.LexBIGService.ValueDomainNodeSet;
import org.LexGrid.LexBIG.Utility.ConvenienceMethods;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.concepts.CodedEntry;
import org.LexGrid.naming.SupportedProperty;
import org.LexGrid.valueDomains.ValueDomain;
import org.apache.log4j.Logger;

/*
 * Created on Jan 31, 2007
 * ShaziyaMuhsin
 *
 */
public class LexCOREService {
    
    private static final Logger log = Logger.getLogger(LexCOREService.class);
    private static final String defaultName = "NCI_Thesaurus";
    private static LexBIGService lbs;
    
    private final HashMap codingSchemeSummaryList = new HashMap();
    private final HashMap codingSchemeList = new HashMap();
    private CodingSchemeSummary codingSchemeSummary = new CodingSchemeSummary();
    private CodingScheme codingScheme = new CodingScheme();
    
    public LexCOREService(){
        try {    
            
            Hashtable hashTable = new Hashtable();
            EVSProperties evsProperties = EVSProperties.getInstance(hashTable);
            String lg_config_file = null;
            if(evsProperties.getConfigFileLocation() != null){
                lg_config_file = evsProperties.getConfigFileLocation();
                System.setProperty("LG_CONFIG_FILE", lg_config_file);
            }
            else {
                log.error("LG_CONFIG_FILE is null");
            }
            
            log.info("Config file location: "+  System.getProperty("LG_CONFIG_FILE"));
            init(defaultName);
            setCodingSchemeSummary(defaultName);
            setCodingScheme(defaultName);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public LexCOREService(String name){
        try{    
            Hashtable hashTable = new Hashtable();
            EVSProperties evsProperties = EVSProperties.getInstance(hashTable);
            String lg_config_file = null;
            if(evsProperties.getConfigFileLocation() != null){
                lg_config_file = evsProperties.getConfigFileLocation();
                System.setProperty("LG_CONFIG_FILE", lg_config_file);
            }
            else {
                log.error("LG_CONFIG_FILE is null");
            }
            
            log.info("Config file location: "+  System.getProperty("LG_CONFIG_FILE"));
            init(name);
            setCodingSchemeSummary(name);
            setCodingScheme(name);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void init(String name) throws Exception{
        lbs = new LexBIGServiceImpl();
        loadCodingSchemeSummaryList();
        loadCodingSchemeList();
    }

    private void loadCodingSchemeSummaryList() throws Exception{
        log.info("loadCodingSchemeSummaryList");
        CodingSchemeRenderingList schemes = lbs.getSupportedCodingSchemes();
        CodingSchemeRendering[] csr = schemes.getCodingSchemeRendering();
        for(int i=0; i< csr.length; i++){
            CodingSchemeSummary css = csr[i].getCodingSchemeSummary();
            codingSchemeSummaryList.put(css.getFormalName(), css);
            log.info("CodingSchemeSumary: "+ css.getFormalName());

        }
     }

    public CodingSchemeSummary getCodingSchemeSummary(String name){
        return (CodingSchemeSummary)codingSchemeSummaryList.get(name);
    }
    public CodingSchemeSummary getCodingSchemeSummary(){
        return codingSchemeSummary;
    }
    public void setCodingSchemeSummary(String name){
        codingSchemeSummary = (CodingSchemeSummary)codingSchemeSummaryList.get(name);
    }

    public List getAllCodingSchemeSummary(){
        List results = new ArrayList();
        for(Iterator it = codingSchemeSummaryList.keySet().iterator(); it.hasNext();){
            String key = (String)it.next();
            results.add(codingSchemeSummaryList.get(key));
        }
        return results;
    }
    public CodingScheme getCodingScheme(){
        return codingScheme;
    }
    public void setCodingScheme(String name){
        codingScheme = (CodingScheme)codingSchemeList.get(name);
    }
    public CodingScheme getCodingScheme(String csName) throws Exception{

        log.info("Name: "+ csName);
        for(Iterator it = codingSchemeList.keySet().iterator(); it.hasNext();){
            String key = (String)codingSchemeList.get(it);
            CodingScheme cs = (CodingScheme)codingSchemeList.get(key);
            log.info("CodingScheme key: "+ key);
        }
        log.info("Size: "+codingSchemeList.size());
        return (CodingScheme)codingSchemeList.get(csName);
    }
    public List getAllCodingSchemes(){
        List results = new ArrayList();
        for(Iterator i = codingSchemeList.keySet().iterator(); i.hasNext();){
            results.add(codingSchemeList.get(i.next()));
        }
        return results;
    }
    
    private void loadCodingSchemeList() throws Exception {
        if(codingSchemeSummaryList.size()>0){
            for(Iterator i = codingSchemeSummaryList.keySet().iterator(); i.hasNext();){
                String key = (String)i.next();
                CodingScheme cs = lbs.resolveCodingScheme(key,null);
                if(cs != null){
                    codingSchemeList.put(key,cs);
                }
            }
        }
    }

    /************************************************************************/


    public List getCodedEntryByName(String name) throws Exception{
        String code = null;
        if(name != null){
            try{
                code = new ConvenienceMethods(lbs).nameToCode(name,defaultName,null);
                log.info("Name: "+name+"\tCode: "+ code);
            }catch(Exception e){
                if(e == null){
                    throw new Exception("ConvenienceMethod exception while getting codedEntryByName");
                }else{
                    throw new Exception("getCodedEntryByName "+ e.getMessage());
                }
            }

        }else{
            throw new Exception("Please specify search string or concept name ");
        }
        return getCodedEntryByCode(code);
    }
    public List getCodedEntryByCode(String code) throws Exception{
        if(code == null){
            throw new Exception("Invalid concept code: "+ code);
        }
        List results = new ArrayList();
        ConceptReferenceList conrefList = ConvenienceMethods.createConceptReferenceList(new String[]{code},defaultName);
        ResolvedConceptReferenceList matches = lbs.getCodingSchemeConcepts(defaultName,null,false).restrictToCodes(conrefList).resolveToList(null,null,null,1);
        log.info("Number of matches found: "+ matches.getResolvedConceptReferenceCount());
        if(matches.getResolvedConceptReferenceCount() > 0){
            ResolvedConceptReference ref = (ResolvedConceptReference)matches.enumerateResolvedConceptReference().nextElement();
            CodedEntry codedEntry = ref.getReferencedEntry();
            results.add(codedEntry);
        }
        return results;
    }

    public List getCodedEntryByProperty(String propertyName)throws Exception {
        List results = new ArrayList();
        CodedNodeSet cns = getCodedNodeSet(propertyName);
        LocalNameList restrictToProperties = new LocalNameList();
        restrictToProperties.addEntry(propertyName);
        ResolvedConceptReferenceList rcrList = cns.resolveToList(null, restrictToProperties,null, 200);
        ResolvedConceptReference[] rcrArray = rcrList.getResolvedConceptReference();
        for(int i=0; i<rcrArray.length; i++){
            ResolvedConceptReference rcr = (ResolvedConceptReference)rcrArray[i];
            results.add((CodedEntry)rcr.getReferencedEntry());
        }
        return results;

    }
    public List getSupportedPropertyNames(String csName)throws Exception{
        List properties = getSupportedProperty(csName);
        List results = new ArrayList();
        for(int i=0; i<properties.size(); i++){
            results.add(((SupportedProperty)properties.get(i)).getContent());
        }
        return results;
    }
    /*
    public List getProperties()throws Exception{
        List results = new ArrayList();
        Property[] properties = codingScheme.getProperty();
        for(int i=0; i<propertites.length; i++){
            results.add(properties[i]);
        }
        return results;
    }*/
    public List getSupportedProperty(String csName)throws Exception{
        List results = new ArrayList();
        CodingScheme csm = getCodingScheme(csName);
        try{
            log.info("CodingScheme: "+ csm.getCodingScheme());
            /*
            SupportedProperty[] properties = csm.getSupportedProperty();
            for(int i=0; i< properties.length; i++){
                results.add(properties[i]);
                log.info(properties[i].getContent());
            }
            */
        }catch(Exception e){
            e.printStackTrace();
        }

        log.info("Properties: "+ results.size());
        return results;
    }
    
    public List getSupportedProperty() throws Exception{
        return getSupportedProperty(defaultName);
    }
    
    public CodedNodeSet getCodedNodeSet(String propertyName) throws Exception{
        return lbs.getCodingSchemeConcepts(defaultName, null);
    }

    public CodedNodeSet getCodingSchemeConcepts(String codingScheme, CodingSchemeVersionOrTag versionOrTag) throws Exception {
        return lbs.getCodingSchemeConcepts(codingScheme, versionOrTag);
    }

    public  CodedNodeSet getCodingSchemeConcepts(ValueDomainEntryNodeSet nodeSet) throws Exception {
       return lbs.getCodingSchemeConcepts(nodeSet);   
    }
    
    public Filter getFilter(java.lang.String name)throws Exception {
       return lbs.getFilter(name);
    }
    
    public  ExtensionDescriptionList getFilterExtensions() throws Exception {
        return lbs.getFilterExtensions();
    }
    
    public GenericExtension getGenericExtension(java.lang.String name)throws Exception{
        return lbs.getGenericExtension(name);
    }
    
    public ExtensionDescriptionList getGenericExtensions()throws Exception {
        return lbs.getGenericExtensions();
    }
    
    public HistoryService getHistoryService(java.lang.String codingScheme)throws Exception{
        return lbs.getHistoryService(codingScheme);
    }
    
    public java.util.Date getLastUpdateTime()throws Exception{
        return lbs.getLastUpdateTime();
    }
    
    public ModuleDescriptionList getMatchAlgorithms() throws Exception{
        return lbs.getMatchAlgorithms();
    }
    
    public CodedNodeGraph getNodeGraph(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, java.lang.String relationsName)throws Exception{
        return lbs.getNodeGraph(codingScheme,versionOrTag,relationsName);
    }
    
    public LexBIGServiceManager getServiceManager(java.lang.Object credentials) throws Exception{
        return lbs.getServiceManager(credentials);
    }
    
    public LexBIGServiceMetadata getServiceMetadata() throws Exception{
        return lbs.getServiceMetadata();
    }
    
    public Sort getSortAlgorithm(java.lang.String name)throws Exception {
        return lbs.getSortAlgorithm(name);
    }
    
    public SortDescriptionList getSortAlgorithms(SortContext context)throws Exception {
        return lbs.getSortAlgorithms(context);
    }
    
    public CodingSchemeRenderingList getSupportedCodingSchemes() throws Exception{
        return lbs.getSupportedCodingSchemes();
    }
    
    public ValueDomainRenderingList getSupportedValueDomains() throws Exception {
        return lbs.getSupportedValueDomains();
    }
    
    public ValueDomainEntryNodeSet getValueDomainEntries(ValueDomainNodeSet nodeSet) throws Exception {
        return lbs.getValueDomainEntries(nodeSet);
    }
    
    public ValueDomainNodeSet getValueDomains(boolean activeOnly) throws Exception {
        return lbs.getValueDomains(activeOnly);
    }
    
    public CodingScheme resolveCodingScheme(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag) throws Exception {
        return lbs.resolveCodingScheme(codingScheme, versionOrTag);
    }
    
    public ValueDomain resolveValueDomain(java.lang.String valueDomain, ValueDomainVersionOrTag versionOrTag) throws Exception {
        return lbs.resolveValueDomain(valueDomain, versionOrTag);
    }
    
    public ConvenienceMethods getConvenienceMethods() throws Exception {
        return new ConvenienceMethods(lbs);
    }
}
