import java.lang.reflect.Field;
import java.net.URL;
import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.utils.Options;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;


import gov.nih.nci.cabio.domain.ws.*;


import java.util.HashMap;

public class WSTestClient
{

	public static void main(String [] args) throws Exception {

		Service  service = new Service();
		Call     call    = (Call) service.createCall();

		/***************************************************************************************************************/

		QName qnGene = new QName("urn:ws.domain.cabio.nci.nih.gov", "Gene");
		call.registerTypeMapping(Gene.class, qnGene,
				new org.apache.axis.encoding.ser.BeanSerializerFactory(Gene.class, qnGene),
				new org.apache.axis.encoding.ser.BeanDeserializerFactory(Gene.class, qnGene));

        QName qnChromosome = new QName("urn:ws.domain.cabio.nci.nih.gov", "Chromosome");
		call.registerTypeMapping(Chromosome.class, qnChromosome,
				new org.apache.axis.encoding.ser.BeanSerializerFactory(Chromosome.class, qnChromosome),
				new org.apache.axis.encoding.ser.BeanDeserializerFactory(Chromosome.class, qnChromosome));

		QName qnTaxon = new QName("urn:ws.domain.cabio.nci.nih.gov", "Taxon");
		call.registerTypeMapping(Taxon.class, qnTaxon,
				new org.apache.axis.encoding.ser.BeanSerializerFactory(Taxon.class, qnTaxon),
				new org.apache.axis.encoding.ser.BeanDeserializerFactory(Taxon.class, qnTaxon));

		QName qnGeneOntology = new QName("urn:ws.domain.cabio.nci.nih.gov", "GeneOntology");
				call.registerTypeMapping(GeneOntology.class, qnGeneOntology,
				new org.apache.axis.encoding.ser.BeanSerializerFactory(GeneOntology.class, qnGeneOntology),
				new org.apache.axis.encoding.ser.BeanDeserializerFactory(GeneOntology.class, qnGeneOntology));

		QName qnGeneOntologyRelationship = new QName("urn:ws.domain.cabio.nci.nih.gov", "GeneOntologyRelationship");
				call.registerTypeMapping(GeneOntologyRelationship.class, qnGeneOntologyRelationship,
				new org.apache.axis.encoding.ser.BeanSerializerFactory(GeneOntologyRelationship.class, qnGeneOntologyRelationship),
				new org.apache.axis.encoding.ser.BeanDeserializerFactory(GeneOntologyRelationship.class, qnGeneOntologyRelationship));

		/****************************************************************************************************************/

		String url = "http://localhost:8080/cacore32/ws/caCOREService";
        System.out.println("Connecting to " + url);
		call.setTargetEndpointAddress(new java.net.URL(url));

        /*
         * Example 1:
         * Get caCORE version number.
         */
        call.setOperationName(new QName("caCoreWebService", "getVersion"));
        System.out.println("===========================================");
        System.out.println("Version number: "+call.invoke(new Object[0]));
        System.out.println("===========================================");

        /*
         * Example 2:
         * Get number of records returned per Query.
         */
        call.setOperationName(new QName("caCoreWebService", "getRecordsPerQuery"));
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_INT);
        System.out.println("Number of records returned per Query: "+call.invoke(new Object[0]));
        System.out.println("===========================================");
        
        /*
         * Example 3:
         * Get total number of Genes in the database
         */

        call.setOperationName(new QName("caCoreWebService", "getTotalNumberOfRecords"));
        call.addParameter("arg1",org.apache.axis.encoding.XMLType.XSD_STRING,ParameterMode.IN);
        call.addParameter("arg2", org.apache.axis.encoding.XMLType.XSD_ANYTYPE, ParameterMode.IN);        
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_INT);
        System.out.println("Total number of Genes found: " + call.invoke(new Object[] { "gov.nih.nci.cabio.domain.ws.Gene", new Gene() }));
        System.out.println("===============================");
        
        /*
         * Example 4:
         * Query for Genes, where Gene symbol like nat*, taxon id = 6 and chromosome id = 37.
         * (Nested criteria test)
         */
		call.setOperationName(new QName("caCoreWebService", "queryObject"));
		call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_ARRAY);
		gov.nih.nci.cabio.domain.ws.Gene gene = new gov.nih.nci.cabio.domain.ws.Gene();
		gene.setSymbol("NAT*");
		Taxon tax = new Taxon();
		tax.setId(new Long(6));
		gene.setTaxon(tax);
		Chromosome ch = new Chromosome();
		ch.setId(new Long(37));
		gene.setChromosome(ch);

		long start = System.currentTimeMillis();
		Object[] resultList = (Object[])call.invoke(new Object[] { "gov.nih.nci.cabio.domain.ws.Gene", gene });
		long end = System.currentTimeMillis();
		System.out.println("Get Genes where gene symbol='NAT*', Taxon id=5 and Chromosome id=37\n\tTotal Genes Found: " + resultList.length );
		if (resultList.length > 0) {
			for(int resultIndex = 0; resultIndex < resultList.length; resultIndex++) {
				Gene g = (Gene)resultList[resultIndex];
				System.out.println("\tGene id: " + g.getId()+"\t"+ g.getSymbol());
			}
		}
		System.out.println("TIME LAG: MS - "+ (end - start));
		System.out.println("===============================");

        

        /*
         * Example 5:
         * Query for Parent GeneOntologyRelationship by setting childGeneOntology id =  5125
         */
        call = (Call)service.createCall();
        call.setTargetEndpointAddress(new java.net.URL(url));
        call.setOperationName(new QName("caCoreWebService", "queryObject"));
        call.addParameter("arg1",org.apache.axis.encoding.XMLType.XSD_STRING,ParameterMode.IN);
        call.addParameter("arg2", org.apache.axis.encoding.XMLType.XSD_ANYTYPE, ParameterMode.IN);
        call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_ARRAY);

		GeneOntology geneOntology = new GeneOntology();
		geneOntology.setId(new Long(5125));
		GeneOntologyRelationship geneor = new GeneOntologyRelationship();
		geneor.setChildGeneOntology(geneOntology);
		long startTime = System.currentTimeMillis();
		Object[] parentGOCollection = (Object[])call.invoke(new Object[] { "gov.nih.nci.cabio.domain.ws.GeneOntologyRelationship", geneor });
		long endTime = System.currentTimeMillis();
		System.out.println("Query Parent GeneOntologyRelationship by setting childGeneOntology id =  5125");
        System.out.println("tNumber of parent Gene Ontologies found: "+ parentGOCollection.length);
		for(int i=0; i<parentGOCollection.length; i++){
			GeneOntologyRelationship data = new GeneOntologyRelationship();
			data = (GeneOntologyRelationship)parentGOCollection[i];
			System.out.println("Parent Gene Ontology Relationship id: " + data.getId());
		}
		System.out.println("TIME LAG: MS - "+ (endTime - startTime));
		System.out.println("================================");

         /*
         * Example 6:
         * Query GeneOntology by Gene where symbol=brca. Using the result Gene Ontologies get the Child GeneOntologyRelationship
         */
        
        Gene brca = new Gene();
        brca.setSymbol("brca*");
		Object[] ontologyCollection = (Object[])call.invoke(new Object[] { "gov.nih.nci.cabio.domain.ws.GeneOntology", brca });        
		System.out.println("\nGet GeneOntology for gene symbol='brca*'");
        System.out.println(ontologyCollection.length + " GeneOntologies found");
        
		for(int i=0; i<ontologyCollection.length; i++){
			GeneOntology geneOnt = new GeneOntology();
			geneOnt = (GeneOntology)ontologyCollection[i];
            System.out.println("======================================================================");
            System.out.println("\tGet Child GeneOntologyRelationship for GeneOntology: "+ geneOnt.getId() +"\t"+ geneOnt.getName());
           
			GeneOntologyRelationship gor = new GeneOntologyRelationship();
			java.util.List list = new java.util.ArrayList();
			list.add(geneOnt);
			gor.setParentGeneOntology(geneOnt);
			Object[] childGOCollection = (Object[])call.invoke(new Object[] { "gov.nih.nci.cabio.domain.ws.GeneOntologyRelationship", gor });
			System.out.println("\t"+ childGOCollection.length +" ChildGeneOntologyRelationships found");
			for(int o=0; o<childGOCollection.length; o++){
				GeneOntologyRelationship childGOR = (GeneOntologyRelationship)childGOCollection[o];
				System.out.println("\t\tid: "+ childGOR.getId());
			}

		}



	}

}
