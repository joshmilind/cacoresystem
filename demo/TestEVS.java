import gov.nih.nci.system.applicationservice.*;
import java.util.*;
import java.text.*;
import gov.nih.nci.common.util.*;
import gov.nih.nci.evs.domain.*;
import gov.nih.nci.evs.query.*;

public class TestEVS {



    public static void main(String[] args) {

        System.out.println("*** TestEVS...");

            String prodUrl  = "http://cabio.nci.nih.gov/@PROJECT_NAME@/http/remoteService";
            String stageUrl = "http://cabio-stage.nci.nih.gov/@PROJECT_NAME@/http/remoteService";
            String qaUrl    = "http://cabio-qa.nci.nih.gov/@PROJECT_NAME@/http/remoteService";
            String localUrl = "http://localhost:8080/@PROJECT_NAME@/http/remoteService";
			//String genUrl = "http://@WEB_SERVER_NAME@:@WEB_SERVER_PORT@/@PROJECT_NAME@/http/remoteService";
			//ApplicationService appService = ApplicationService.getRemoteInstance(prodUrl);

			ApplicationService appService = ApplicationServiceProvider.getApplicationService();


		try
		{
		    String vocabularyName = "NCI_Thesaurus";
			EVSQuery evsQuery = new EVSQueryImpl();
			List evsResults = new ArrayList();
			System.out.println("\n\nEVS RESULTS......");
			PrintUtils print = new PrintUtils();


/*********************************************************************
* 1. searchDescLogicConcepts
* ********************************************************************/
			//evsQuery.searchDescLogicConcepts("NCI_Thesaurus","Anatomic_Structure_System_or_Substance",10);

/*********************************************************************
* 2. getTree
* ********************************************************************/

			Vector v = new Vector();
			v.add("Anatomic_Structure_Has_Location");
			v.add("Anatomic_Structure_is_Physical_Part_of");
			v.add("part_of");
			//When isFlag is true system exception
        	//evsQuery.getTree("NCI_Thesaurus", "Heart", true, false, 1, "4", v);
			//evsQuery.getTree("GO", "Gene_Ontology", true, false, 1, "4", v);
			//evsQuery.getTree("GO", "Gene_Ontology", true, true, 1, "4", v);
/****************************************************************************
* 3. getConceptWithPropertyMatchin
****************************************************************************/
     	//evsQuery.getConceptWithPropertyMatching("NCI_Thesaurus", "Synonym", "protocol", 10);
		//evsQuery.getConceptWithPropertyMatching("NCI_Thesaurus", "Synonym", "Tobacco Smoking", 10);


/****************************************************************************
* 4. getDescLogicConceptNameByCode
****************************************************************************/

		//evsQuery.getDescLogicConceptNameByCode("NCI_Thesaurus", "C12932");

/****************************************************************************
* 5. isSubConcep
*****************************************************************************/
		//Test
			//evsQuery.isSubConcept("NCI_Thesaurus", "TP73_Gene", "Apoptosis_Regulation_Gene");


/****************************************************************************
* 6. isRetired
*****************************************************************************/

		//evsQuery.isRetired("NCI_Thesaurus", "C12756");

/****************************************************************************
* 7. getDescendants
*****************************************************************************/
		//evsQuery.getDescendants("NCI_Thesaurus", "C16612", false, "8/22/2003", "10/31/2003");

/*****************************************************
 * 8. getRootConcepts
 * ***************************************************/
		//evsQuery.getRootConcepts("NCI_Thesaurus",true);

/*****************************************************
 * 9. getPropertyValues
 * ***************************************************/
	 //evsQuery.getPropertyValues("NCI_Thesaurus", "Gene", "DEFINITION");

/*****************************************************
 * 10. getAncestors
 * ***************************************************/
	//evsQuery.getAncestors("NCI_Thesaurus", "C16612",false,"10/31/2003" ,"03/16/2005" );

/*****************************************************
* 11. getSubConcepts
*****************************************************/
	//evsQuery.getSubConcepts("NCI_Thesaurus", "Organ", Boolean.FALSE, Boolean.FALSE);

/*****************************************************
 * 12. getSuperConcepts
 * ***************************************************/
	//evsQuery.getSuperConcepts("NCI_Thesaurus", "Organ", Boolean.FALSE, Boolean.FALSE);

/*****************************************************
* 13. getRolesByConceptName
* ***************************************************/
			//evsQuery.getRolesByConceptName("NCI_Thesaurus", "Oncogene_MYC");

/*****************************************************
 * 14. getPropertiesByConceptName
 *****************************************************/
			//evsQuery.getPropertiesByConceptName("NCI_Thesaurus", "Gene");

/*****************************************************
 * 15. evsQuery.getVocabularyNames
 *****************************************************/
			//evsQuery.getVocabularyNames();

/*****************************************************
 * 16. getConceptCodeByName
 *****************************************************/
			//evsQuery.getConceptCodeByName("NCI_Thesaurus", "Chromosome");

/******************************************************
 * 17. getVocabularyHost
 ******************************************************/
			//evsQuery.getVocabularyHost("NCI_Thesaurus");

/*******************************************************
 * 18. getVocabularyPort
 ******************************************************/
			//evsQuery.getVocabularyPort("NCI_Thesaurus");

/*******************************************************
 * 19. getConceptEditAction
 *******************************************************/
			//evsQuery.getConceptEditAction("NCI_Thesaurus", "C16612");

/*******************************************************
 * 20. containsInverseRole
 *******************************************************/
			//evsQuery.containsInverseRole("NCI_Thesaurus","Anatomic_Structure_is_Physical_Part_of","Chromatin","Chromosome");

/********************************************************
 * 21. evsQuery.containsRole
 *******************************************************/
			//evsQuery.containsRole("NCI_Thesaurus","Anatomic_Structure_is_Physical_Part_of","Chromatin","Chromosome");

/********************************************************
 * 22. getConceptEditActionDates
 ********************************************************/
			//evsQuery.getConceptEditActionDates("NCI_Thesaurus", "C16612", "modify");

/*********************************************************
 * 23. evsQuery.getConceptEditAction
 ********************************************************/
			//evsQuery.getConceptEditAction(vocabularyName, "C16612");

/****************************************************************
 *  METHODS *
 ****************************************************************/

			//evsQuery.searchDescLogicConcepts("NCI_Thesaurus","blood*",10,0,"",1);
			//evsQuery.hasChildren(vocabularyName, "Anatomic_Structure_System_or_Substance");
			//evsQuery.hasParents(vocabularyName, "Blood");
			//evsQuery.getChildConcepts("NCI_Thesaurus","Anatomic_Structure_System_or_Substance",false);
			//evsQuery.getParentConcepts("NCI_Thesaurus","Blood",false);
			//evsQuery.searchSourceByCode("N035552","CADSR-04");
			//evsQuery.getConceptNameByCode("NCI_Thesaurus", "C12408");
			//evsQuery.getDescLogicConcept("NCI_Thesaurus","Organ",false);

/****************************************************************
*  NEW METHODS *
****************************************************************/

			evsQuery.getHistoryRecords(vocabularyName, "C16612");
			//evsQuery.getAllSilos("NCI_Thesaurus");
            //evsQuery.getConceptWithSiloMatching("NCI_Thesaurus","brain*",5,"Terms");
			//evsQuery.getConceptWithSiloMatching("NCI_Thesaurus","Blood",5,"2ND NCI SILO");
			//evsQuery.getConceptWithSiloMatching("NCI_Thesaurus","Blood",5);
            //evsQuery.getTree("NCI_Thesaurus", "Liver", true, false, 1, 4, v);

/*************  PRINT DLC RESULTS       *********************************/


			long startTime = System.currentTimeMillis(),endTime =0;
    		evsResults = (List)appService.evsSearch(evsQuery);
    		endTime = System.currentTimeMillis();

    		print.printEVSResults(evsResults);

    		System.out.println("\n\n"+evsResults.size() + " records found");
    		System.out.println("latency in miliseconds = "+ (endTime - startTime));




/**********************************************************************/
			/** METATHESAURUS **/

    		EVSQuery metaSearch = new EVSQueryImpl();
			List metaResults 	= new ArrayList();
			System.out.println("\n\nEVS METAPHRASE RESULTS......");


/************* NCI Metathesaurus Search ******************************************/
//MetaThesaurusConcept now supports Atoms.

			metaSearch.searchMetaThesaurus("lung", 2, "*", false, false, false);

//			metaSearch.searchByLoincId("10834-0", "LNC210");
//			metaSearch.getSemanticTypes();
//			metaSearch.getConceptsBySource("NCI");
//			metaSearch.getMetaConceptNameByCode("C0017337");
//			metaSearch.getMetaSources();
//			metaSearch.getChildren("C0017337");
//			metaSearch.getParent("C0017337");
//    		metaSearch.getParent("C0017337", "NCI04");
//    		metaSearch.getBroaderConcepts("C0017337", "SNOMEDCT_2004_01_31");
//    		metaSearch.getNarrowerConcepts("C0017337", "SNOMEDCT_2004_01_31");

//    		metaSearch.getRelatedConcepts("C0017337");
//    		metaSearch.getRelatedConcepts("C0017337", "SNOMEDCT_2004_01_31");
//			metaSearch.getRelatedConcepts("CL287739","SNOMEDCT_2004_01_31","RN");
			//Categories "Medications", "Procedures", "Laboratory", "Diagnosis"
//			metaSearch.getConceptsByCategories("C0017337","Procedures");
//  		metaSearch.getMetaConceptNameByCode("C0024109");
//   		metaSearch.getMetaSources();
//    		metaSearch.getConceptsByCategories("C0017337", "Diagnosis");
//    		metaSearch.searchByLoincId("10834-0", "LNC210");
//   		metaSearch.getSemanticTypes();
//    		metaSearch.getConceptsBySource("SNOMEDCT_2004_01_31");
//			metaSearch.getChildren("C0017337", "NCI04");

			startTime = System.currentTimeMillis();
			metaResults = (List)appService.evsSearch(metaSearch);
			endTime = System.currentTimeMillis();
    		print.printEVSResults(metaResults);

			System.out.println(metaResults.size()+"\trecords found");
			System.out.println("Latency in miliseconds = "+ (endTime - startTime));

/*******************************************************************/

		}		catch(Exception ex){
			System.out.println("Test client throws Exception = "+ ex);
		}


	 }


}//end class
