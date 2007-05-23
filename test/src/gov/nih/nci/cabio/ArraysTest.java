package gov.nih.nci.cabio;

import gov.nih.nci.cabio.domain.ExpressionReporter;
import gov.nih.nci.cabio.domain.GeneRelativeLocation;
import gov.nih.nci.cabio.domain.PopulationFrequency;
import gov.nih.nci.cabio.domain.RelativeLocation;
import gov.nih.nci.cabio.domain.SNP;
import gov.nih.nci.cabio.domain.SNPReporter;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;

import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

public class ArraysTest extends TestCase {

    private ApplicationService appService;
    
    protected void setUp() throws Exception {
        String localUrl = "http://127.0.0.1:8080/cacore40/http/remoteService";     
        appService = ApplicationServiceProvider.getApplicationService();
    }

    public void testAffyHgu133() throws Exception {
        
        ExpressionReporter reporter = new ExpressionReporter();
        reporter.setName("1007_s_at");
        List resultList = appService.search(ExpressionReporter.class, reporter);

        assertEquals("Reporters",1,resultList.size());
        reporter = (ExpressionReporter)resultList.get(0);
        
        assertEquals("Gene symbol","DDR1",reporter.getGene().getSymbol());
        
        assertEquals("Array name",
            "Human Genome U133 Plus 2.0 Array",
            reporter.getMicroarray().getName());
        
        assertNotNull("Target description is null",
            reporter.getTargetDescription());
        
        assertNotNull("Transcript id is null",
            reporter.getTranscriptId());
        
        assertNotNull("Sequence source is null",
            reporter.getSequenceSource());
        
        assertNotNull("Sequence type is null",
            reporter.getSequenceType());
        
        Collection domains = reporter.getProteinDomainCollection();
        
        assertNotNull("Protein domain collection is null", domains);
        assertEquals("Protein domains",15,domains.size());
    }

    public void testAgilentCGH244K() throws Exception {

        ExpressionReporter reporter = new ExpressionReporter();
        reporter.setName("A_16_P00000090");
        List resultList = appService.search(ExpressionReporter.class, reporter);
    
        assertEquals("Reporters",1,resultList.size());
        reporter = (ExpressionReporter)resultList.get(0);

        assertEquals("Array name",
            "Human Genome CGH 244K",
            reporter.getMicroarray().getName());
        
        assertEquals("Gene symbol","SAMD11",
            reporter.getGene().getSymbol());

        assertEquals("Sequence accession","NM_152486",
            reporter.getNucleicAcidSequence().getAccessionNumber());

        assertNull("Target description is not null",
            reporter.getTargetDescription());
        
        assertNull("Transcript id is not null",
            reporter.getTranscriptId());
        
        assertNull("Sequence source is not null",
            reporter.getSequenceSource());
        
        assertNull("Sequence type is not null",
            reporter.getSequenceType());
        
        assertNotNull("Protein domain collection is not null", 
            reporter.getProteinDomainCollection());
    }

    public void testAgilent44K() throws Exception {

        ExpressionReporter reporter = new ExpressionReporter();
        reporter.setName("A_23_P413224");
        List resultList = appService.search(ExpressionReporter.class, reporter);
    
        assertEquals("Reporters",1,resultList.size());
        reporter = (ExpressionReporter)resultList.get(0);

        assertEquals("Array name",
            "Human Genome 44K",
            reporter.getMicroarray().getName());
        
        assertEquals("Gene symbol","NCR2",
            reporter.getGene().getSymbol());

        assertEquals("Sequence accession","NM_004828",
            reporter.getNucleicAcidSequence().getAccessionNumber());

        assertNull("Target description is not null",
            reporter.getTargetDescription());
        
        assertNull("Transcript id is not null",
            reporter.getTranscriptId());
        
        assertNull("Sequence source is not null",
            reporter.getSequenceSource());
        
        assertNull("Sequence type is not null",
            reporter.getSequenceType());
        
        assertNotNull("Protein domain collection is not null", 
            reporter.getProteinDomainCollection());
    }

    public void testAffyHuMapping() throws Exception {

        SNPReporter reporter = new SNPReporter();
        reporter.setName("SNP_A-1756690");
        List resultList = appService.search(SNPReporter.class, reporter);
    
        assertEquals("Reporters",1,resultList.size());
        reporter = (SNPReporter)resultList.get(0);

        assertEquals("Array name",
            "Mapping50K_Hind240",
            reporter.getMicroarray().getName());

        SNP snp = reporter.getSNP();
        assertEquals("SNP","rs2302213",snp.getDBSNPID());

        int c = 0;
        for(Object o : snp.getPopulationFrequencyCollection()) {
            c++;
        }
        assertEquals("PopulationFrequency count",3,c);

        assertNotNull("ChrXPseudoAutosomalRegion is null",
            snp.getChrXPseudoAutosomalRegion());
        
        assertNotNull("Flank is null",snp.getFlank());
        
    }

    public void testIlluminaHumanHap() throws Exception {

        SNPReporter reporter = new SNPReporter();
        reporter.setName("rs1003857");
        List resultList = appService.search(SNPReporter.class, reporter);
    
        assertEquals("Reporters",1,resultList.size());
        reporter = (SNPReporter)resultList.get(0);

        assertEquals("Array name",
            "HumanHap550K",
            reporter.getMicroarray().getName());

        assertEquals("SNP",
            "rs1003857",
            reporter.getSNP().getDBSNPID());

        assertNotNull("Phast conservation is null",
            reporter.getPhastConservation());

        assertNotNull("Amino acid change is null",
            reporter.getSNP().getAminoAcidChange());

        assertNotNull("Coding status is null",
            reporter.getSNP().getCodingStatus());
        
        Collection<RelativeLocation> locations = 
            reporter.getSNP().getRelativeLocationCollection();
        assertEquals("Locations",1,locations.size());
        
        GeneRelativeLocation grl = (GeneRelativeLocation)locations.toArray()[0];
        assertEquals("Orientation","CDS",grl.getOrientation());
    }
}
