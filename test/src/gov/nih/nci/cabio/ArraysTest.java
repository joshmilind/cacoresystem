package gov.nih.nci.cabio;

import gov.nih.nci.cabio.domain.ExonArrayReporter;
import gov.nih.nci.cabio.domain.ExpressionArrayReporter;
import gov.nih.nci.cabio.domain.Gene;
import gov.nih.nci.cabio.domain.GeneRelativeLocation;
import gov.nih.nci.cabio.domain.PhysicalLocation;
import gov.nih.nci.cabio.domain.RelativeLocation;
import gov.nih.nci.cabio.domain.SNP;
import gov.nih.nci.cabio.domain.SNPArrayReporter;
import gov.nih.nci.cabio.domain.Transcript;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;

import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

public class ArraysTest extends TestCase {

    private ApplicationService appService;
    
    protected void setUp() throws Exception {    
        appService = ApplicationServiceProvider.getApplicationService();
    }

    public void testAffyHgu133() throws Exception {
        
        ExpressionArrayReporter reporter = new ExpressionArrayReporter();
        reporter.setName("1007_s_at");
        List resultList = appService.search(ExpressionArrayReporter.class, reporter);

        assertEquals("Reporters",1,resultList.size());
        reporter = (ExpressionArrayReporter)resultList.get(0);
        
        assertEquals("Gene symbol","DDR1",reporter.getGene().getSymbol());
        
        assertEquals("Array name",
            "Human Genome U133 Plus 2.0 Array",
            reporter.getMicroarray().getName());
        
        assertNotNull("Target description is null",
            reporter.getTargetDescription());
        
        assertNotNull("Target id is null",
            reporter.getTargetId());
        
        assertNotNull("Sequence source is null",
            reporter.getSequenceSource());
        
        assertNotNull("Sequence type is null",
            reporter.getSequenceType());
        
        Collection domains = reporter.getProteinDomainCollection();
        
        assertNotNull("Protein domain collection is null", domains);
        assertEquals("Protein domains",5,domains.size());
    }

    public void testAgilentCGH244K() throws Exception {

        ExpressionArrayReporter reporter = new ExpressionArrayReporter();
        reporter.setName("A_16_P00000090");
        List resultList = appService.search(ExpressionArrayReporter.class, reporter);
    
        assertEquals("Reporters",1,resultList.size());
        reporter = (ExpressionArrayReporter)resultList.get(0);

        assertEquals("Array name",
            "Human Genome CGH 244K",
            reporter.getMicroarray().getName());
        
        assertNotNull("Gene is null",reporter.getGene());
        assertEquals("Gene symbol","SAMD11",
            reporter.getGene().getSymbol());

        assertNotNull("Sequence is null",reporter.getNucleicAcidSequence());
        assertEquals("Sequence accession","NM_152486",
            reporter.getNucleicAcidSequence().getAccessionNumber());

        assertNull("Target description is not null",
            reporter.getTargetDescription());
        
        assertNull("Transcript id is not null",
            reporter.getTargetId());
        
        assertNull("Sequence source is not null",
            reporter.getSequenceSource());
        
        assertNull("Sequence type is not null",
            reporter.getSequenceType());
        
        assertNotNull("Protein domain collection is not null", 
            reporter.getProteinDomainCollection());
    }

    public void testAgilent44K() throws Exception {

        ExpressionArrayReporter reporter = new ExpressionArrayReporter();
        reporter.setName("A_23_P413224");
        List resultList = appService.search(ExpressionArrayReporter.class, reporter);
    
        assertEquals("Reporters",1,resultList.size());
        reporter = (ExpressionArrayReporter)resultList.get(0);

        assertEquals("Array name",
            "Human Genome 44K",
            reporter.getMicroarray().getName());

        assertNotNull("Gene is null",reporter.getGene());
        assertEquals("Gene symbol","NCR2",
            reporter.getGene().getSymbol());

        assertNotNull("Sequence is null",reporter.getNucleicAcidSequence());
        assertEquals("Sequence accession","NM_004828",
            reporter.getNucleicAcidSequence().getAccessionNumber());

        assertNull("Target description is not null",
            reporter.getTargetDescription());
        
        assertNull("Transcript id is not null",
            reporter.getTargetId());
        
        assertNull("Sequence source is not null",
            reporter.getSequenceSource());
        
        assertNull("Sequence type is not null",
            reporter.getSequenceType());
        
        assertNotNull("Protein domain collection is not null", 
            reporter.getProteinDomainCollection());
    }

    public void testAffyHuMapping() throws Exception {

        SNPArrayReporter reporter = new SNPArrayReporter();
        reporter.setName("SNP_A-1756690");
        List resultList = appService.search(SNPArrayReporter.class, reporter);
    
        assertEquals("Reporters",1,resultList.size());
        reporter = (SNPArrayReporter)resultList.get(0);

        assertEquals("Array name",
            "Mapping50K_Hind240",
            reporter.getMicroarray().getName());

        SNP snp = reporter.getSNP();
        assertNotNull("SNP is null",snp); 
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

        SNPArrayReporter reporter = new SNPArrayReporter();
        reporter.setName("rs1003857");
        List resultList = appService.search(SNPArrayReporter.class, reporter);
    
        assertEquals("Reporters",1,resultList.size());
        reporter = (SNPArrayReporter)resultList.get(0);

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
        assertNotNull("Locations null",locations);
        assertEquals("Locations",1,locations.size());
        
        GeneRelativeLocation grl = (GeneRelativeLocation)locations.toArray()[0];
        assertEquals("Orientation","CDS",grl.getOrientation());
    }
    

    public void testAffyExon() throws Exception {

        ExonArrayReporter reporter = new ExonArrayReporter();
        reporter.setName("2315101");
        List resultList = appService.search(ExonArrayReporter.class, reporter);
    
        assertEquals("Reporters",1,resultList.size());
        reporter = (ExonArrayReporter)resultList.get(0);

        assertEquals("Microarray name","HuEx-1_0-st-v2", reporter.getMicroarray().getName());

        assertEquals("Strand","+",reporter.getStrand());
        
        assertNotNull("PSR id is null",reporter.getProbeSelectionRegionId());
        assertEquals("PSR id",1,reporter.getProbeSelectionRegionId().intValue());
        
        assertNotNull("Exon is null",reporter.getExon());
        assertEquals("Source","Affymetrix",reporter.getExon().getSource());
                
        Collection<Gene> genes = reporter.getGeneCollection();
        assertNotNull("Genes null",genes);
        assertEquals("Genes",2,genes.size());
        
        Collection<PhysicalLocation> locations = 
            reporter.getPhysicalLocationCollection();
        assertEquals("Locations",1,locations.size());
        
        PhysicalLocation pl = (PhysicalLocation)locations.toArray()[0];
        assertNotNull("PhysicalLocations null",pl);
        assertEquals("Chromosome","1",pl.getChromosome().getNumber());
        assertEquals("Start position",1788,pl.getChromosomalStartPosition().intValue());
        assertEquals("End position",2030,pl.getChromosomalEndPosition().intValue());

        Transcript transcript = reporter.getTranscript();
        assertEquals("Affymetrix",transcript.getSource());
        
        assertEquals("Transcript","2315100",transcript.getSourceId());
        
        Collection<PhysicalLocation> tlocations = 
            reporter.getPhysicalLocationCollection();
        assertEquals("Transcript Locations",1,tlocations.size());
        
        PhysicalLocation tpl = (PhysicalLocation)tlocations.toArray()[0];
        assertNotNull("Transcript Location null",tpl);
        
    }
}
