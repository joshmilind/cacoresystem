import gov.nih.nci.system.applicationservice.*;
import java.util.*;
import java.text.*;
import gov.nih.nci.common.util.*;
import gov.nih.nci.evs.domain.*;
import gov.nih.nci.evs.query.*;

public class TestEVS {



    public static void main(String[] args) {

        System.out.println("*** TestEVS...");

            String prodUrl  = "http://cabio.nci.nih.gov/cacore32/http/remoteService";
            String stageUrl = "http://cabio-stage.nci.nih.gov/cacore32/http/remoteService";
            String qaUrl    = "http://cabio-qa.nci.nih.gov/cacore32/http/remoteService";
            String localUrl = "http://localhost:8080/cacore32/http/remoteService";
			//String genUrl = "http://localhost:8080/cacore32/http/remoteService";
			//ApplicationService appService = ApplicationService.getRemoteInstance(prodUrl);

			ApplicationService appService = ApplicationServiceProvider.getApplicationService();


		try
		{
		    
			EVSQuery[] evsQuery = new EVSQueryImpl[85];
            for(int i=0; i<85; i++){
                evsQuery[i] = new EVSQueryImpl();
            }
			List evsResults = new ArrayList();
			System.out.println("\n\nEVS RESULTS......");
			PrintUtils print = new PrintUtils();
            
            
            String vocabularyName = "NCI_Thesaurus";
            String rootName = "Heart";           
            int levels = 3;
            Vector roles = new Vector();
            roles.add("Anatomic_Structure_Is_Physical_Part_Of");
            String searchTerm = "blood*";
            int matchLimit = 20;
            int matchOption = 0;
            String matchType = "";
            int ASDIndex = 1;
            String conceptName = "Heart";
            String conceptCode = "C12434";
            String baseLineDate = "01/01/1999";
            String action = "modify";
            String initialDate = "01/01/1999";
            String finalDate = "01/01/2006";           
            String roleName = "Anatomic_Structure_Is_Physical_Part_Of";
            String roleValue = "Cardiac_Vein";
            String sourceAbbr = "*";
            String cui = "C0392895";
            String metaCui = "C0927225";
            String propertyName = "Synonym";
            String conceptCode1 ="C33557";
            String conceptName1 ="Gland";
            String conceptCode2 = "C12805";
            String conceptName2 = "Organ";
            String atomCode = "10063229";
           
         
            
            Date editActionDate = new Date("01/01/2004");
           

			gov.nih.nci.evs.security.SecurityToken token = new gov.nih.nci.evs.security.SecurityToken();
            token.setAccessToken("10832");          
            
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
            evsQuery[10].getDescendants( vocabularyName,  "Organ",  false,  "08/22/2003",  "10/31/2003");
            evsQuery[11].getPropertyValues( vocabularyName, conceptName,  propertyName);
            evsQuery[12].getAncestors( vocabularyName,  conceptName1,  Boolean.FALSE,  initialDate,  baseLineDate);
            evsQuery[13].getSubConcepts( vocabularyName, "Organ",  Boolean.FALSE, Boolean.FALSE);
            evsQuery[14].getSuperConcepts( vocabularyName,  "Organ",  Boolean.FALSE, Boolean.FALSE);
            evsQuery[15].getRolesByConceptName( vocabularyName, conceptName);
            evsQuery[16].getPropertiesByConceptName( vocabularyName, conceptName);
            evsQuery[17].getVocabularyNames();
            evsQuery[18].getTree(vocabularyName,rootName,true,true,1,3,roles);
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
            evsQuery[29].getCodeActionChildren( vocabularyName,  "C12434",  "11/01/2004",  action);
            evsQuery[30].getCodeActionParents( vocabularyName,  "C41067",  "11/01/2004" );   
            evsQuery[31].getHistoryRecords( vocabularyName,  conceptCode);
            evsQuery[32].getHistoryRecords( vocabularyName,  "01/01/2004","01/01/2005",  conceptCode);  
            evsQuery[33].containsInverseRole( vocabularyName, "Anatomic_Structure_Is_Physical_Part_Of", "Coronary_Valve ",  "Heart");
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
            //evsQuery[60].fetchDTSProperties( vocabularyName,  "Blood");
            //evsQuery[61].fetchTermAssociations( vocabularyName,  conceptName);
            
            //Methods calling the Metapharase Server
            evsQuery[62].searchMetaThesaurus( searchTerm,  2,  sourceAbbr, false,  false, false);
            evsQuery[63].searchMetaThesaurus( cui);
            evsQuery[64].searchByLoincId( atomCode,  sourceAbbr);
            evsQuery[65].searchSourceByCode( atomCode,  sourceAbbr);
            evsQuery[66].getSemanticTypes();
            evsQuery[67].getMetaConceptCodeForSource(sourceAbbr);
            evsQuery[68].getMetaConceptNameByCode( cui);
            evsQuery[69].getMetaSources();
            evsQuery[70].getChildren( "C0017337",  sourceAbbr);
            evsQuery[71].getParent( cui,  sourceAbbr);
            evsQuery[72].getBroaderConcepts("C0017337",  sourceAbbr);
            evsQuery[73].getNarrowerConcepts( "CL287739",  sourceAbbr);
            evsQuery[74].getRelatedConcepts("CL287739",  sourceAbbr);
            evsQuery[75].getRelatedConcepts("CL287739",  sourceAbbr,  "RN");            
            evsQuery[76].getConceptsByCategories( "C0017337",  "Procedures");
                        
            
            for(int i=60; i<77; i++){               
                evsQuery[i].addSecurityToken("MedDRA", token);
                String method = getMethodName((EVSQueryImpl)evsQuery[i]);
                if(method == null){
                    continue;
                }
                
                System.out.println("\n___________________________________________________________________");
                System.out.println("EVSQuery number "+ i + ". "+ method);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                List results = new ArrayList();
                try{
                    results = appService.evsSearch(evsQuery[i]);  
                }catch(Exception ex){
                    System.out.println("\tError: "+ ex.getMessage());
                }
                
                //print.printEVSResults(results);
                System.out.println("Number of records found: "+ results.size());
                System.out.println("\n==================================================================");
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
