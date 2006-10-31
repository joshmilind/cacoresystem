package gov.nih.nci.system.dao.impl.externalsystem;

import gov.nih.nci.system.dao.DAO;
import gov.nih.nci.system.dao.DAOException;
import gov.nih.nci.common.net.Response;
import gov.nih.nci.common.net.Request;
import gov.nih.nci.common.util.*;


import java.util.*;

/**
 * Created by Panther Informatics
 * User: Brian Gilman
 * Date: Oct 27, 2006
 * Time: 9:55:38 AM
 *
 * @author Brian Gilman
 */
public class DAS2DAOImpl implements DAO{
    public Response query(Request request) throws DAOException, Exception {
    	NestedCriteria nestedObject = (NestedCriteria)request.getRequest();    	
		List resultList = new ArrayList();
		resultList.add(Class.forName(nestedObject.getTargetObjectName()).newInstance());
		return new Response(resultList);
    }
 }
