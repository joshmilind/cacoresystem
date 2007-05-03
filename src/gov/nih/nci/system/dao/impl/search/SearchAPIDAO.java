package gov.nih.nci.system.dao.impl.search;

import gov.nih.nci.system.dao.*;
import gov.nih.nci.system.dao.impl.search.service.Searchable;
import gov.nih.nci.search.*;
import gov.nih.nci.common.util.*;
import gov.nih.nci.common.net.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.io.InputStream;

/*
 * Created on Mar 29, 2007
 * Shaziya Muhsin
 *
 */
/**
 * The SearchAPIDAO provides functionality to perform searches on a Lucene based
 * Index
 */
public class SearchAPIDAO implements DAO {
	private static Logger log = Logger.getLogger(SearchAPIDAO.class.getName());
	private final String FILTER_AND = " AND ";
	private final String FILTER_OR = " OR ";
	private final String FILTER_TO = " TO ";
	private final String FILTER_OBRAC = "[";
	private final String FILTER_CBRAC = "]";
	private final String FILTER_COLON = ":";
	private static String[] indexedFields;
	private static String indexPropertyFile = "indexedFields.properties";

	/**
	 * Constructor
	 */
	public SearchAPIDAO() {
		try {
			indexedFields = loadIndexedFields();
		} catch (Exception ex) {
			log.error(ex);
		}
	}

	/**
	 * Performs queries against the lucene indexes
	 * 
	 * @param searchQuery
	 *            specifies the search criteria
	 * @return
	 * @throws DAOException
	 */
	public List query(SearchQuery searchQuery) throws DAOException {
		List resultList = new ArrayList();
		try {
			Searchable searchService = null;
			if (searchQuery.getQueryType() != null) {
				searchService = (Searchable) ObjectFactory
						.getObject(searchQuery.getQueryType());
			}
			resultList = searchService.query(getQueryString(searchQuery));
		} catch (Exception ex) {
			throw new DAOException(ex.getMessage());
		}
		return resultList;
	}

	/**
	 * Performs queries against the lucene indexes
	 * 
	 * @param request -
	 *            specifies the search criteria
	 * @return
	 * @throws DAOException
	 */
	public Response query(Request request) throws DAOException {
		List resultList = null;
		Object searchObject = request.getRequest();
		try {
			SearchQuery searchQuery = null;
			if (searchObject.getClass().getName().equalsIgnoreCase(
					"gov.nih.nci.common.util.NestedCriteria")) {
				NestedCriteria criteria = (NestedCriteria) searchObject;
				searchQuery = (SearchQuery) criteria.getSourceObjectList().get(
						0);
			} else if (searchObject.getClass().getName().equalsIgnoreCase(
					"gov.nih.nci.search.SearchQuery")) {
				searchQuery = (SearchQuery) searchObject;
			}
			resultList = query(searchQuery);
		} catch (Exception ex) {
			log.error("Exception in SearchAPIDAO ", ex);
			throw new DAOException("Exception in SearchAPIDAO " + ex);
		}
		return new Response(resultList);
	}

	/**
	 * Generates the search string for a given SearchQuery
	 * 
	 * @param searchQuery
	 * @return
	 * @throws DAOException
	 */
	private String getQueryString(SearchQuery searchQuery) throws DAOException {
		StringBuffer keyword = new StringBuffer();
		try {
			if (searchQuery.getKeyword() != null) {
				keyword = new StringBuffer(searchQuery.getKeyword());
				if (searchQuery.getFuzzySearch()) {
					keyword.append("~");
				}
			}
			if (searchQuery.getRangeFilter() != null) {
				String filter = "";
				RangeFilter rangeFilter = searchQuery.getRangeFilter();
				if (rangeFilter.getFieldName() != null) {
					filter += rangeFilter.getFieldName() + FILTER_COLON;
				}
				String range = "";
				if (rangeFilter.getStartRange() != null
						&& rangeFilter.getEndRange() != null) {
					range += FILTER_OBRAC + rangeFilter.getStartRange()
							+ FILTER_TO + rangeFilter.getEndRange()
							+ FILTER_CBRAC;
				} else if (rangeFilter.getEndRange() != null
						&& rangeFilter.getEndRange() == null) {
					range += rangeFilter.getStartRange();
				} else if (rangeFilter.getEndRange() == null
						&& rangeFilter.getEndRange() != null) {
					range += rangeFilter.getEndRange();
				}
				if (filter != null && range != null) {
					keyword.append(FILTER_OR + filter + range);
				} else if (filter != null && range == null) {
					filter += keyword.toString();
				} else if (range != null) {
					keyword.append(FILTER_AND + range);
				}
			}
		} catch (Exception ex) {
			log.error(ex);
			throw new DAOException(ex);
		}
		log.info(keyword.toString());
		return keyword.toString();
	}

	/**
	 * Returns the field names that are indexed
	 * 
	 * @return
	 * @throws Exception
	 */
	private static String[] loadIndexedFields() throws Exception {
		Properties properties = new Properties();
		Set<String> fieldList = new HashSet<String>();
		InputStream is = null;
		try {
			 is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(indexPropertyFile);
			properties.load(is);
			if (properties.size() > 0) {
				for (Iterator it = properties.keySet().iterator(); it.hasNext();) {
					String key = (String) it.next();
					StringTokenizer st = new StringTokenizer(properties
							.getProperty(key), ";");
					while (st.hasMoreTokens()) {
						fieldList.add(st.nextToken());
					}
				}
			}

		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		finally {
			if (is != null)
				is.close();
		}

		return (String[])fieldList.toArray(new String[0]);
	}

	/**
	 * Returns the field names that are indexed
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String[] getIndexedFields() throws Exception {
		return indexedFields;
	}
}
