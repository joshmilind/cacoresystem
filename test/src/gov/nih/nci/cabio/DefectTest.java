package gov.nih.nci.cabio;

import gov.nih.nci.cabio.domain.SNP;
import gov.nih.nci.cabio.domain.SNPArrayReporter;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;

import java.util.List;

import junit.framework.TestCase;

/**
 * Each of the test cases here represents one caBIO defect in GForge. The
 * tests will succeed if the bug has been fixed.  
 * 
 * @author <a href="mailto:rokickik@mail.nih.gov">Konrad Rokicki</a>
 */
public class DefectTest extends TestCase {

    private ApplicationService appService;
    
    protected void setUp() throws Exception {   
        appService = ApplicationServiceProvider.getApplicationService();
    }

    /**
     * Test for SNPs with 2 locations. 
     * Until bug has been fixed, fails with "SNPs expected:<1> but was:<2>"
     * @throws Exception
     */
    public void testGF6293() throws Exception {
        
        SNP snp = new SNP();
        snp.setDBSNPID("rs10049659");
        List resultList = appService.search(SNP.class, snp);

        assertEquals("SNPs",1,resultList.size());
        snp = (SNP)resultList.get(0);
        
        assertEquals("locations",1,snp.getLocationCollection().size());
    }

    /**
     * Test for merged SNPs.
     * Until bug has been fixed, fails with "SNP is null"
     * @throws Exception
     */
    public void testGF6418() throws Exception {

        SNPArrayReporter reporter = new SNPArrayReporter();
        reporter.setName("SNP_A-1705928");
        List resultList = appService.search(SNPArrayReporter.class, reporter);
    
        assertEquals("Reporters",1,resultList.size());
        reporter = (SNPArrayReporter)resultList.get(0);

        assertNotNull("SNP is null",reporter.getSNP());
    }

}
