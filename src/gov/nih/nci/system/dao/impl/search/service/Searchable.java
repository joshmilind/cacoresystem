package gov.nih.nci.system.dao.impl.search.service;
import java.util.*;
import gov.nih.nci.search.*;
import gov.nih.nci.system.dao.*;
/*
 * Created on Apr 18, 2007
 * ShaziyaMuhsin
 * 
 */
/**
 * Interface that defines the search functionality
 */
public interface Searchable {
    public List query(String searchString) throws DAOException;
}
