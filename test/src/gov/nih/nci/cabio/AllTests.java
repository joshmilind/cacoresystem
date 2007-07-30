package gov.nih.nci.cabio;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for gov.nih.nci.cabio");
        //$JUnit-BEGIN$
        suite.addTestSuite(ArraysTest.class);
        suite.addTestSuite(DefectTest.class);
        suite.addTestSuite(GridIdTest.class);
        //$JUnit-END$
        return suite;
    }

}
