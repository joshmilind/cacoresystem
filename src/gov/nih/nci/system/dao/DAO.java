package gov.nih.nci.system.dao;
/*
 * Created on Jun 27, 2006
 * ShaziyaMuhsin
 * 
 */
import gov.nih.nci.common.net.*;
public interface DAOImpl{
    public Response query(Request request) throws DAOException;
}
