package gov.nih.nci.cabio;

import gov.nih.nci.common.util.SearchUtils;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;

import java.lang.reflect.Field;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 * Supports tests to ensure all big ids are populated and working. 
 * 
 * @author <a href="mailto:rokickik@mail.nih.gov">Konrad Rokicki</a>
 */
public abstract class GridIdTestBase extends TestCase {

    private ApplicationService appService;
    
    protected void setUp() throws Exception {
        appService = ApplicationServiceProvider.getApplicationService();
    }

    protected void testGridId(Object obj) throws Exception {
        
        // retrieve the first object in the database
        String className = obj.getClass().getName();
        DetachedCriteria criteria = DetachedCriteria.forClass(obj.getClass());
        criteria.add(Restrictions.sqlRestriction("rownum=1"));
        List results = appService.query(criteria, className);
        assertEquals("result size",1,results.size());
        obj = results.get(0);
        assertNotNull(obj);
        
        // get the grid id
        SearchUtils searchUtils = new SearchUtils();
        Field field = searchUtils.getField(Class.forName(className), "bigid");
        String bigid = String.valueOf(field.get(obj));
        
        // ensure the grid id exists
        assertNotNull(bigid);
        assertTrue(appService.exist(bigid));
        
        // ensure the grid id points to the correct object
        Object dataObj = appService.getDataObject(bigid);
        
        // get the interal id
        Field idField = searchUtils.getField(Class.forName(className), "id");
        String id = String.valueOf(idField.get(obj));
        String did = String.valueOf(idField.get(dataObj));
        assertEquals("id",id, did);
        
    }
}
