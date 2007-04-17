/**
 * 
 */
package gov.nih.nci.system.applicationservice.impl;

import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.ObjectFactory;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

/**
 * @author safrant
 *
 */
public class EVSApplicationServiceBusinessImpl extends
		ApplicationServiceBusinessImpl {
	private static String httpAddress;

	private static EVSApplicationServiceBusinessImpl applicationService = new EVSApplicationServiceBusinessImpl();

//	private static int firstRow;

	private static int maxRecordsCount;

	private static int recordsCount;

//	private boolean inputImplFlag;

	private static Logger log = Logger.getLogger(EVSApplicationServiceBusinessImpl.class.getName());

//	private boolean caseSensitivityFlag; // by default it is case
	
	private static Properties properties;
	static {
	   	String propertyFile = System.getProperty("gov.nih.nci.cacore.cacoreProperties");
	   	log.info("Loading caCORE property file: " + propertyFile);
		properties = new Properties();
		try{
			if(propertyFile != null && propertyFile.length() > 0){
				FileInputStream fis = new FileInputStream(new File(propertyFile));
				properties.load(fis);				
			}
		}catch(Exception ex){	
			ex.printStackTrace();
		}
	}

	/**
	 * Creates a new ApplicationService instance with the HTTP server address
	 * 
	 * @param httpURL -
	 *            Specifies the http address for the server
	 * @return application service instance
	 */
	public static EVSApplicationServiceBusinessImpl getRemoteInstance(String httpURL) {
		httpAddress = httpURL;
		loadProperties();
		return applicationService;

	}

	/**
	 * 
	 * @return application service local instance
	 */
	public static EVSApplicationServiceBusinessImpl getLocalInstance() {
		httpAddress = null;
		loadProperties();
		return applicationService;

	}
	

	/**
	 * @return ApplicationService instance
	 */
	public static EVSApplicationServiceBusinessImpl getApplicationService() {
		return getLocalInstance();

	}

	/**
	 * @return ApplicationService instance
	 */
	public static EVSApplicationServiceBusinessImpl getInstance() {
		return applicationService;
	}


	private static void loadProperties() {

		try {
			Properties _properties = new Properties();

			_properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("CORESystem.properties"));

			String rsPerQuery = _properties.getProperty("RECORDSPERQUERY");
			String maxRsPerQuery = _properties.getProperty("MAXRECORDSPERQUERY");
			if (rsPerQuery != null) {
				recordsCount = new Integer(rsPerQuery).intValue();
			} else {
				recordsCount = Constant.RESULT_COUNT_PER_QUERY;
			}

			if (maxRsPerQuery != null) {
				maxRecordsCount = new Integer(maxRsPerQuery).intValue();

			} else {
				maxRecordsCount = Constant.MAX_RESULT_COUNT_PER_QUERY;
			}

		} catch (IOException e) {
			log.error("IOException: ", e);
		} catch (Exception ex) {
			log.error("Exception: ", ex);
		}
	}

    /*****************LEX GRID INTEGRATION ***********************/
    public CodedNodeSet getCodingSchemeConcepts(String codingScheme, CodingSchemeVersionOrTag versionOrTag)throws LBException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getCodingSchemeConcepts(codingScheme, versionOrTag);
     }

    public  CodedNodeSet getCodingSchemeConcepts(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, boolean activeOnly)throws LBException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getCodingSchemeConcepts(codingScheme, versionOrTag, activeOnly);
    }
    public  CodedNodeSet getCodingSchemeConcepts(ValueDomainEntryNodeSet nodeSet) throws LBException, ApplicationException {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getCodingSchemeConcepts(nodeSet);
    }
    public Filter   getFilter(java.lang.String name)throws LBException, ApplicationException, Exception  {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getFilter(name);
    }
    public  ExtensionDescriptionList    getFilterExtensions() throws Exception{
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getFilterExtensions();
        }
    public GenericExtension   getGenericExtension(java.lang.String name) throws ApplicationException, Exception{
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getGenericExtension(name);
    } 
    public ExtensionDescriptionList   getGenericExtensions() throws Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getGenericExtensions();
    }
    public HistoryService     getHistoryService(java.lang.String codingScheme)throws LBException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getHistoryService(codingScheme);
    } 
    public java.util.Date     getLastUpdateTime() throws LBInvocationException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getLastUpdateTime();
    }
    public ModuleDescriptionList  getMatchAlgorithms() throws ApplicationException, Exception{
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getMatchAlgorithms();
    }
    public CodedNodeGraph     getNodeGraph(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag, java.lang.String relationsName)throws LBException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getNodeGraph(codingScheme,versionOrTag,relationsName);
    } 
    public LexBIGServiceManager   getServiceManager(java.lang.Object credentials)throws LBException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getServiceManager(credentials);
    } 
    public LexBIGServiceMetadata  getServiceMetadata() throws LBException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getServiceMetadata();
    }
    public Sort   getSortAlgorithm(java.lang.String name) throws LBException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getSortAlgorithm(name);
    }
    public SortDescriptionList    getSortAlgorithms(SortContext context) throws ApplicationException, Exception{
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getSortAlgorithms(context);
    } 
    public CodingSchemeRenderingList   getSupportedCodingSchemes()throws LBInvocationException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getSupportedCodingSchemes();
    }
    public ValueDomainRenderingList   getSupportedValueDomains() throws LBInvocationException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getSupportedValueDomains();
    } 
    public ValueDomainEntryNodeSet  getValueDomainEntries(ValueDomainNodeSet nodeSet)  throws LBException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getValueDomainEntries(nodeSet);
    }
    public ValueDomainNodeSet getValueDomains(boolean activeOnly) throws LBException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).getValueDomains(activeOnly);
    }
    public CodingScheme   resolveCodingScheme(java.lang.String codingScheme, CodingSchemeVersionOrTag versionOrTag) throws LBException, ApplicationException,Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).resolveCodingScheme(codingScheme, versionOrTag);
    }
    public ValueDomain   resolveValueDomain(java.lang.String valueDomain, ValueDomainVersionOrTag versionOrTag) throws LBException, ApplicationException, Exception {
        return ((gov.nih.nci.system.dao.impl.externalsystem.LexCOREService)ObjectFactory.getObject("LexService")).resolveValueDomain(valueDomain, versionOrTag);
    }
}
