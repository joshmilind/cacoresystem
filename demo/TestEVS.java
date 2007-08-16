import gov.nih.nci.system.applicationservice.*;
import java.util.*;
import java.text.*;



import org.LexGrid.LexBIG.History.HistoryService;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeGraph;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.LexBIGService.LexBIGServiceMetadata;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet.SearchDesignationOption;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.LexBIG.Utility.ConvenienceMethods;
import org.LexGrid.LexBIG.Utility.LBConstants;
import org.LexGrid.LexBIG.DataModel.Collections.SystemReleaseList;
import org.LexGrid.LexBIG.DataModel.Core.AbsoluteCodingSchemeVersionReference;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.DataModel.Core.ConceptReference;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.Exceptions.LBParameterException;
import org.LexGrid.versions.SystemRelease;

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
			String genUrl = "http://@WEB_SERVER_NAME@:@WEB_SERVER_PORT@/@PROJECT_NAME@/http/remoteService";
			
            //ApplicationService appService = ApplicationService.getRemoteInstance(prodUrl);
			EVSApplicationService appService = EVSApplicationServiceProvider.getApplicationService();


		try
		{
		    
			EVSQuery[] evsQuery = new EVSQueryImpl[80];
            for(int i=0; i<80; i++){
                evsQuery[i] = new EVSQueryImpl();
            }
            PrintUtils print = new PrintUtils();
		    String vocabularyName = "NCI_Thesaurus"; 
		    String localName = "40010";
            String searchTerm = "blood*";            
            String conceptName = "Heart";
            String conceptCode = "C12434";   //Blood
            String relatedCode = "C12909";  //Hematopoietic System
            String approxCode = "C48589"; // "Base of the Heart"
            String sourceAbbr = "*";
            String cui = "C0392895";
            Vector roles = new Vector();
            String role = "Anatomic_Structure_Is_Physical_Part_Of";
            roles.add(role);

			//Create Security token
            gov.nih.nci.evs.security.SecurityToken token = new gov.nih.nci.evs.security.SecurityToken();
            token.setAccessToken("xxxxx");  //This is an invalid token. Replace the value with a valid token value.        
            
            //DTSRPC Calls            
            evsQuery[1].getAllVocabularies();
            evsQuery[2].searchDescLogicConcepts(vocabularyName, searchTerm, 2);
            evsQuery[3].searchDescLogicConcepts(vocabularyName, searchTerm, 2, 0, "",1);            
            evsQuery[4].getConceptWithPropertyMatching(vocabularyName,"Synonym", "tobacco smoking",10);
            evsQuery[5].getConceptWithSiloMatching(vocabularyName, searchTerm, 2);
            evsQuery[6].getConceptWithSiloMatching(vocabularyName, searchTerm, 2, "Terms");
            evsQuery[7].getDescLogicConceptNameByCode(vocabularyName,conceptCode);
            evsQuery[8].isSubConcept(vocabularyName,"Gland","Organ");
            evsQuery[9].isRetired( vocabularyName,  "C13262");
            //evsQuery[10].getDescendants( vocabularyName,  "Organ",  false,  "08/22/2003",  "10/31/2003");
            evsQuery[10].getDescendants( vocabularyName,  "C12434",  false,  "08/22/2003",  "10/31/2003");
            evsQuery[11].getPropertyValues( vocabularyName, conceptName,  "Synonym");
           // evsQuery[12].getAncestors( vocabularyName,  "Gland",  Boolean.FALSE,  "01/01/2000",  "01/01/2005");
            evsQuery[12].getAncestors( vocabularyName,  "C54199",  Boolean.FALSE,  "01/01/2000",  "01/01/2005");
            evsQuery[13].getSubConcepts( vocabularyName, "Organ",  Boolean.FALSE, Boolean.FALSE);
            evsQuery[14].getSuperConcepts( vocabularyName,  "Organ",  Boolean.FALSE, Boolean.FALSE);
            evsQuery[15].getRolesByConceptName( vocabularyName, conceptName);
            evsQuery[16].getPropertiesByConceptName( vocabularyName, conceptName);
            evsQuery[17].getVocabularyNames();
            evsQuery[18].getTree(vocabularyName,conceptName,true,true,1,3,roles);
            evsQuery[19].getVocabularyByName( vocabularyName);
            evsQuery[20].getConceptCodeByName( vocabularyName,  conceptName);
            evsQuery[21].getConceptByName( vocabularyName,  conceptName);
            evsQuery[22].getVocabularyHost( vocabularyName);
            evsQuery[23].getVocabularyPort( vocabularyName);
            evsQuery[24].getVocabularyVersion( vocabularyName);
            evsQuery[25].getConceptEditAction( vocabularyName,  conceptCode);
            evsQuery[26].getConceptEditAction( vocabularyName,  conceptCode,  new Date("11/01/2004"));
            evsQuery[27].getConceptEditActionDates( vocabularyName,  conceptCode,  "modify");
            evsQuery[28].getRootConcepts( vocabularyName,  true);            
            evsQuery[29].getCodeActionChildren( vocabularyName,  "C12434",  "11/01/2004",  "modify");
            evsQuery[30].getCodeActionParents( vocabularyName,  "C41067",  "11/01/2004" );   
            evsQuery[31].getHistoryRecords( vocabularyName,  conceptCode);
            evsQuery[32].getHistoryRecords( vocabularyName,  "01/01/2004","01/01/2005",  conceptCode);  
            evsQuery[33].containsInverseRole( vocabularyName, "Disease_Has_Primary_Anatomic_Site" ,"Cardiac_Leiomyosarcoma",  "Heart");
            evsQuery[34].containsRole( vocabularyName, "Anatomic_Structure_Is_Physical_Part_Of", "Cardiovascular_System",  "Heart");
            evsQuery[35].getAllAssociationTypes( vocabularyName);
            evsQuery[36].getAllConceptAssociationQualifierTypes( vocabularyName);
            evsQuery[37].getAllConceptAssociationTypes( vocabularyName);
            evsQuery[38].getAllConceptPropertyQualifierTypes( vocabularyName);
            evsQuery[39].getAllConceptPropertyTypes( vocabularyName);
            evsQuery[40].getAllLicenses( "SNOMED_CT", "");
            evsQuery[41].getAllPropertyTypes( vocabularyName);
            evsQuery[42].getAllSilos( vocabularyName);
            evsQuery[43].getAllQualifierTypes( vocabularyName);
            evsQuery[44].getAllRoleNames( vocabularyName);
            evsQuery[45].getAllSubConceptCodes( vocabularyName,  "C13018");
            evsQuery[46].getAllSubConceptNames( vocabularyName,  "Organ");
            evsQuery[47].getAllSynonymTypes( vocabularyName);
            evsQuery[48].getAllTermAssociationQualifierTypes( vocabularyName);
            evsQuery[49].getAllTermPropertyQualifierTypes( vocabularyName);
            evsQuery[50].getAllTermPropertyTypes( vocabularyName);
            evsQuery[51].getParentConcepts( vocabularyName,  conceptName,  false);
            evsQuery[52].getParentConcepts( vocabularyName,  "Blood",  false,  1);
            evsQuery[53].getChildConcepts( vocabularyName,  "Anatomic_Structure_System_or_Substance",  false);            
            evsQuery[54].hasParents( vocabularyName,  conceptName);
            evsQuery[55].hasChildren( vocabularyName,  "Heart_Block");
            evsQuery[56].getConceptNameByCode( vocabularyName,  conceptCode);
            evsQuery[57].getDescLogicConcept( vocabularyName,  conceptName,  false);               
            evsQuery[58].getHistoryStartDate( vocabularyName);
            evsQuery[59].getHistoryEndDate( vocabularyName);
            evsQuery[60].fetchDTSProperties( vocabularyName,  "Blood");
            evsQuery[61].fetchTermAssociations( vocabularyName,  "Blood_Bank");
            
            //Metapharase calls
            evsQuery[62].searchMetaThesaurus( searchTerm,  2,  sourceAbbr, false,  false, false);
            evsQuery[63].searchMetaThesaurus( cui);
            evsQuery[64].searchByLoincId( "10063229",  sourceAbbr);
            evsQuery[65].searchSourceByCode("10063229",  sourceAbbr);
            evsQuery[66].getSemanticTypes();
            evsQuery[67].getConceptsByCategories( "C0017337",  "Procedures");            
            evsQuery[68].getMetaConceptNameByCode( cui);
            evsQuery[69].getMetaSources();
            evsQuery[70].getChildren( "C0017337",  sourceAbbr);
            evsQuery[71].getParent( cui,  sourceAbbr);
            evsQuery[72].getBroaderConcepts("C0017337",  sourceAbbr);
            evsQuery[73].getNarrowerConcepts( "CL287739",  sourceAbbr);
            evsQuery[74].getRelatedConcepts("CL287739",  sourceAbbr);
            evsQuery[75].getRelatedConcepts("CL287739",  sourceAbbr,  "RN");   
                        
            
            for(int i=1; i<76; i++){                
                evsQuery[i].addSecurityToken("MedDRA", token);
                String method = getMethodName((EVSQueryImpl)evsQuery[i]);
                if(method == null){
                    continue;
                }                
                System.out.println("\n___________________________________________________________________");
                System.out.println("EVSQuery number "+ i + ". "+ method);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                List evsResults = new ArrayList();
                try{
                    evsResults = appService.evsSearch(evsQuery[i]);  
                }catch(Exception ex){
                    System.out.println("\tError: >>>>>>>> "+ ex.getMessage());
                }                
                print.printEVSResults(evsResults);
                System.out.println("Number of records found: "+ evsResults.size());
                System.out.println("\n==================================================================");
            }
            
            
            
            //Distributed LexBIG calls
            LexBIGService lbsi = ServiceHolder.instance().getLexBIGService();            
            
            
            //Test CodedNodeGraph
            CodedNodeGraph cng = lbsi.getNodeGraph(vocabularyName, null, "Relations");
            boolean isTrue = true;
            isTrue=cng.areCodesRelated(Constructors.createNameAndValue(role, null), Constructors.createConceptReference(conceptCode, vocabularyName), Constructors.createConceptReference(relatedCode, vocabularyName),true).booleanValue();
            System.out.println("CodedNodeGraph.areCodesRelated = "+ isTrue);            
            isTrue=cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null), Constructors.createConceptReference(conceptCode, vocabularyName), Constructors.createConceptReference(relatedCode, vocabularyName),true).booleanValue();
            System.out.println("CodedNodeGraph.areCodesRelated should be False = "+ isTrue);            
            ConceptReference[] cr = cng.listCodeRelationships(Constructors.createConceptReference(conceptCode, vocabularyName), Constructors.createConceptReference(relatedCode, vocabularyName), false).getConceptReference();
            if (cr.length == 1)
            {
            	System.out.println("Concept relationships correctly calculated");         	
            }
            for (int i = 0; i < cr.length; i++)
            {
                if (cr[i].getConceptCode().equals(role) && cr[i].getCodingScheme().equals(vocabularyName))
                {
                    System.out.println("Concept relationships contain the correct relation");
                }
            }            
            isTrue = cng.isCodeInGraph(Constructors.createConceptReference(conceptCode, vocabularyName)).booleanValue();
            System.out.println("CodedNodeGraph.isCodeInGraph = "+ isTrue);            
            isTrue = cng.isCodeInGraph(Constructors.createConceptReference(conceptCode, localName)).booleanValue();
            System.out.println("Using localname CodedNodeGraph.isCodeInGraph = "+ isTrue);            
        
            //Test Filter Extension
            ConvenienceMethods cm = new ConvenienceMethods(lbsi);
            try
            {
                TestFilterRM.register();
            }
            catch (LBParameterException e)
            {
                //can happen if is already registered by another test.
            }
            ResolvedConceptReference[] rcr =  cng.resolveAsList(null, true, false, -1, -1, null, null, null, cm.createLocalNameList(TestFilterRM.name_), -1).getResolvedConceptReference();
            if (rcr.length>0)
            {
            	System.out.println("Filtered results returned");
            }
            ResolvedConceptReference ref = null;
            for (int i = 0; i < rcr.length && ref == null; i++)
            {
            	System.out.println(rcr[i].getEntityDescription());
            }

            //Test CodedNodeSet
            CodedNodeSet cns = lbsi.getCodingSchemeConcepts(vocabularyName, null);
            cns.restrictToMatchingProperties(Constructors.createLocalNameList("textualPresentation"), null, conceptName,
                    "contains", null);
            rcr = cns.resolveToList(null, null, null, 50).getResolvedConceptReference();          
            int firstPass = rcr.length;
            System.out.println("Length of contains query = " + firstPass);
            CodedNodeSet cns2 = lbsi.getCodingSchemeConcepts(vocabularyName, null);
            cns2.restrictToMatchingProperties(Constructors.createLocalNameList("textualPresentation"), null, conceptName,
                    "exactMatch", null);
            rcr = cns2.resolveToList(null, null, null, 50).getResolvedConceptReference();          
            int secondPass = rcr.length;
            if (secondPass <firstPass)
            {
            	System.out.println("Length of exactMatch query less than contains = "+ secondPass);
            }
            else if (secondPass == firstPass)
            {
            	System.out.println("Length of exactMatch query equals contains query");
            }
            else
            {
            	System.out.println("Warning: Length of exactMatch query greater than contains = " + secondPass);
            }
            
            // Perform concept lookup by coding scheme name/tag; verify that 'Chrysler' is not included         
            CodingSchemeVersionOrTag production = new CodingSchemeVersionOrTag();
            production.setTag(LBConstants.KnownTags.PRODUCTION.toString());
            cns = ServiceHolder.instance().getLexBIGService().getCodingSchemeConcepts(vocabularyName, production);
            cns.restrictToCodes(Constructors.createConceptReferenceList(new String[]{"Chrysler"}, vocabularyName));
            rcr = cns.resolveToList(null, null, null, 0).getResolvedConceptReference();
            if (rcr.length ==0)
            {
            	System.out.println("Chrysler not found in vocabulary " + vocabularyName);
            }
            else
            {
             	System.out.println("Warning: Chrysler found in vocabulary " + vocabularyName);
            }
            
            //Test vocabulary metadata
            LexBIGServiceMetadata md = ServiceHolder.instance().getLexBIGService().getServiceMetadata();
            AbsoluteCodingSchemeVersionReference[] acsvrl = md.listCodingSchemes()
                    .getAbsoluteCodingSchemeVersionReference();

            if (acsvrl.length >= 2) System.out.println("Metadata returned");
            for (int i = 0; i < acsvrl.length; i++)
            {
            	System.out.println(acsvrl[i].getCodingSchemeURN()+":"+ acsvrl[i].getCodingSchemeVersion());
            }
            
            //Test History.  Only will work if the tested vocabulary has history installed
            HistoryService hs = lbsi.getHistoryService("urn:oid:2.16.840.1.113883.3.26.1.1"); //urn for NCI_Thesaurus
            SystemReleaseList srl = hs.getBaselines(null, null);
            if (srl.getSystemReleaseCount()>0)
            {
            	System.out.println("History records returned");
            	SystemRelease sr = hs.getLatestBaseline();
            	System.out.println("Latest release is " + sr.getReleaseId());
            }

            //Test approximate search
            cns = ServiceHolder.instance().getLexBIGService()
                          .getCodingSchemeConcepts(vocabularyName, null);
            cns.restrictToMatchingDesignations("heaart base", SearchDesignationOption.ALL, "DoubleMetaphoneLuceneQuery", null);
            rcr = cns.resolveToList(null, null, null, 0).getResolvedConceptReference();
            // should have found the concept code C48589 - "Base of the Heart"
            boolean found = false;
            for (int i = 0; i < rcr.length; i++)
            {
            	if (rcr[i].getConceptCode().equals(approxCode))
            	{
            		found = true;
            		System.out.println("Match found = " + rcr[i].getEntityDescription());
            	}
            }
            
            /*******************************************************************/

		}		catch(Exception ex){
			System.out.println("Test client throws Exception = "+ ex);
		}


	 }

    public static String getMethodName(EVSQueryImpl query){        
        String methodName = null;        
        if(query.metaThesaurusValues.size() > 0){
            for(Iterator it = query.metaThesaurusValues.keySet().iterator(); it.hasNext();){                        
                String m = (String)it.next();
                methodName = m.indexOf("$")>1?m.substring(0, m.indexOf("$")):m;                
            }
        }else if(query.descLogicValues.size() > 0){
            for(Iterator it = query.descLogicValues.keySet().iterator(); it.hasNext();){                        
                String m = (String)it.next();
                methodName = m.indexOf("$")>1?m.substring(0, m.indexOf("$")):m;                
            }
        }
        return methodName;
    }

    
}//end class
