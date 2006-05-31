package gov.nih.nci.system.webservice;

import gov.nih.nci.common.net.Request;
import gov.nih.nci.common.net.Response;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.evs.query.EVSQueryImpl;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;
import gov.nih.nci.system.proxy.*;
import gov.nih.nci.system.servicelocator.ServiceLocator;
import gov.nih.nci.evs.domain.*;

import java.lang.reflect.Field;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 The caBIO Software License, Version 3.1 Copyright 2001-2006 Science Applications International Corporation (SAIC)
 Copyright Notice.  The software subject to this notice and license includes both human readable source code form and machine readable, binary, object code form (the caBIO Software).  The caBIO Software was developed in conjunction with the National Cancer Institute (NCI) by NCI employees and employees of SAIC.  To the extent government employees are authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
 This caBIO Software License (the License) is between NCI and You.  You (or Your) shall mean a person or an entity, and all other entities that control, are controlled by, or are under common control with the entity.  Control for purposes of this definition means (i) the direct or indirect power to cause the direction or management of such entity, whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) beneficial ownership of such entity.
 This License is granted provided that You agree to the conditions described below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up, no-charge, irrevocable, transferable and royalty-free right and license in its rights in the caBIO Software to (i) use, install, access, operate, execute, copy, modify, translate, market, publicly display, publicly perform, and prepare derivative works of the caBIO Software; (ii) distribute and have distributed to and by third parties the caBIO Software and any modifications and derivative works thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to third parties, including the right to license such rights to further third parties.  For sake of clarity, and not by way of limitation, NCI shall have no right of accounting or right of payment from You or Your sublicensees for the rights granted under this License.  This License is granted at no charge to You.
 1.	Your redistributions of the source code for the Software must retain the above copyright notice, this list of conditions and the disclaimer and limitation of liability of Article 6, below.  Your redistributions in object code form must reproduce the above copyright notice, this list of conditions and the disclaimer of Article 6 in the documentation and/or other materials provided with the distribution, if any.
 2.	Your end-user documentation included with the redistribution, if any, must include the following acknowledgment: This product includes software developed by SAIC and the National Cancer Institute.  If You do not include such end-user documentation, You shall include this acknowledgment in the Software itself, wherever such third-party acknowledgments normally appear.
 3.	You may not use the names "The National Cancer Institute", "NCI" Science Applications International Corporation and "SAIC" to endorse or promote products derived from this Software.  This License does not authorize You to use any trademarks, service marks, trade names, logos or product names of either NCI or SAIC, except as required to comply with the terms of this License.
 4.	For sake of clarity, and not by way of limitation, You may incorporate this Software into Your proprietary programs and into any third party proprietary programs.  However, if You incorporate the Software into third party proprietary programs, You agree that You are solely responsible for obtaining any permission from such third parties required to incorporate the Software into such third party proprietary programs and for informing Your sublicensees, including without limitation Your end-users, of their obligation to secure any required permissions from such third parties before incorporating the Software into such third party proprietary software programs.  In the event that You fail to obtain such permissions, You agree to indemnify NCI for any claims against NCI by such third parties, except to the extent prohibited by law, resulting from Your failure to obtain such permissions.
 5.	For sake of clarity, and not by way of limitation, You may add Your own copyright statement to Your modifications and to the derivative works, and You may provide additional or different license terms and conditions in Your sublicenses of modifications of the Software, or any derivative works of the Software as a whole, provided Your use, reproduction, and distribution of the Work otherwise complies with the conditions stated in this License.
 6.	THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 * <!-- LICENSE_TEXT_END -->
 */
/**
 * @author Shaziya Muhsin
 */

/**
 * The EVSWebServices class describes methods implemented to query the
 * Enterprise Vocabulary Service.
 */

public class EVSWebService {

	private static Logger log = Logger.getLogger(EVSWebService.class.getName());
	private String defaultVocabulary = "NCI_Thesaurus";
	private int defaultLimit = 100;
	private String returnClassName;
    private EVSWSTransformer transformer;

	/**
	 * Instanciates an EVSWebService instance
	 * @throws Exception
	 */
	public EVSWebService() throws Exception {
		transformer = new EVSWSTransformer();
	}

	/**
	 * Returns a list of DescLogicConcepts or MetaThesaurusConcepts beased on a given search criteria
	 * @param parameterList - Return class name with an optional parameter that specifies a vocabularyName (String)
	 * @param wsObject - DescLogicConcept or a MetaThesaurusConcept instance from gov.nih.nci.evs.domain.ws package.
	 * @param startIndex - Starting record number of the result set
	 * @param recordCounter - Total number of records to be returned
	 * @return
	 * @throws Exception
	 */
	public List evsSearch(String parameterList, Object wsObject,
			int startIndex, int recordCounter) throws Exception {
		Object searchObject = null;
		if (wsObject.getClass().getName().indexOf(".ws.") > 0) {
			searchObject = transformer.convertWStoEVS(wsObject);			
		} else {
			searchObject = wsObject;
		}

		String limit = String.valueOf(defaultLimit);
		int total = 0;
		if (startIndex > 0) {
			total = startIndex;
		}
		if (recordCounter > 0) {
			total += recordCounter;
		} else {
			total += defaultLimit;
		}
		defaultLimit = Integer.valueOf(total);

		if (parameterList.indexOf(",") > 0) {
			String[] params = parameterList.split(",");
			if (params.length == 2) {
				parameterList += "," + limit;
			}
		} else {
			parameterList += "," + defaultVocabulary + "," + defaultLimit;
		}

		List results = new ArrayList();
		List evsResults = new ArrayList();
        try{
            evsResults = evsSearch(parameterList, searchObject);
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
		List finalResults = new ArrayList();
		if (wsObject.getClass().getName().indexOf(".ws.") > 0) {
			for (int i = 0; i < evsResults.size(); i++) {
				Object wsResult = transformer.convertEVStoWS(evsResults.get(i));				
				results.add(wsResult);
			}
		} else {
			results = evsResults;
		}

        return results;
	}

   /**
	 * Returns a list of EVS domain objects based on a given criteria
	 * @param parameterList - specifies the return class name and an optional parameter vocabulayName
	 * @param searchObject - an instance of  gov.nih.nci.evs.domain.* object
	 * @return
	 * @throws Exception
	 */
	public List evsSearch(String parameterList, Object searchObject)
			throws Exception {
	    List dtsrpcList = new ArrayList();
        String queryType = getQueryType(searchObject);
        List results = new ArrayList();

        if (parameterList.indexOf(",") > 1) {
            StringTokenizer st = new StringTokenizer(parameterList, ",");
            returnClassName = st.nextToken();
            defaultVocabulary = st.nextToken();

        } else if (parameterList != null) {
            returnClassName = parameterList;
        }

		try {
			if (queryType.equals("dtsrpc")) {
                if(searchObject.getClass().getName().startsWith("History") && returnClassName.startsWith("History")){
                    results = getHistoryRecords(searchObject);
                }else  if(searchObject.getClass().getName().endsWith("Vocabulary") && returnClassName.endsWith("Vocabulary")){
                    results = getVocabularies();
                } if(searchObject.getClass().getName().endsWith("Silo")&& returnClassName.getClass().getName().endsWith("Silo")){
                    results = getSilos();
                }else{
                    results = searchDTSRPC(searchObject);
                }
			} else if(queryType.equals("meta")) {                
                    List metaConcepts = searchMetaphrase(searchObject);                    
                    if(metaConcepts.get(0).getClass().getName().endsWith("MetaThesaurusConcept")){
                        if (returnClassName != null && returnClassName.endsWith("DescLogicConcept")) {
                            results = convertConcepts(metaConcepts);
                        }else if(returnClassName != null    && returnClassName.endsWith("Atom")){
                            results = getAtoms(metaConcepts);
                        }else if(returnClassName != null    && returnClassName.startsWith("History")){
                            results = getHistoryRecords(convertConcepts(metaConcepts));
                        }else {
                            results = metaConcepts;
                        }
                    }
                    else if(metaConcepts.get(0).getClass().getName().endsWith("Source")){
                        results = metaConcepts;
                    }
                    else if(metaConcepts.get(0).getClass().getName().endsWith("SemanticType")){
                        results = metaConcepts;
                    }
 			} else {
				throw new Exception("Invalid search class " + searchObject.getClass().getName());
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return results;
	}

     /**
     * Returns the EVS Query type for the specified object
     * @param searchObject An instance of gov.nih.nci.evs.domain.* domain object
     * @return
     */
    private String getQueryType(Object searchObject){
        String queryType = null;
        List dtsrpcList = new ArrayList();
        dtsrpcList.add("DescLogicConcept");
        dtsrpcList.add("Property");
        dtsrpcList.add("Role");
        dtsrpcList.add("Qualifier");
        dtsrpcList.add("Association");
        dtsrpcList.add("Silo");
        dtsrpcList.add("History");
        dtsrpcList.add("HistoryRecord");
        dtsrpcList.add("Vocabulary");

        List metaList = new ArrayList();
        metaList.add("MetaThesaurusConcept");
        metaList.add("Atom");
        metaList.add("Source");
        metaList.add("SemanticType");

        for(int i=0; i<dtsrpcList.size(); i++){
            if(searchObject.getClass().getName().endsWith((String)dtsrpcList.get(i))){
                queryType = "dtsrpc";
                break;
            }
        }

        for(int i=0; i<metaList.size(); i++){
            if(searchObject.getClass().getName().endsWith((String)metaList.get(i))){
                queryType = "meta";
                break;
            }
        }
        return queryType;
    }

    /**
     * Returns a list of resutls from the Description Logic vocabularies
     * @param criteria - An instance of gov.nih.nci.evs.domain.* object
     * @return
     * @throws Exception
     */
    private List searchDTSRPC(Object criteria) throws Exception {
        List results = new ArrayList();
        String conceptName = null;
        String conceptCode = null;
        int matchOption = 0;
        String matchType = null;
        String searchValue = null;
        String siloName = null;
        String historyStartDate = null;
        String historyEndDate = null;
        Vocabulary vocabulary = null;

        DescLogicConcept concept = new DescLogicConcept();
        if(criteria.getClass().getName().endsWith("DescLogicConcept")){
            concept = (DescLogicConcept)criteria;
            if (concept.getName() != null && concept.getName().length() > 0) {
                conceptName = concept.getName();
            }
            if (concept.getCode() != null && concept.getCode().length() > 0) {
                conceptCode = concept.getCode();
            }
            if(concept.getPropertyCollection().size() > 0) {
                criteria = concept.getPropertyCollection().get(0);
            }
            if (concept.getRoleCollection().size() > 0) {
                criteria = concept.getRoleCollection().get(0);
            }            
            if(concept.getVocabulary()!=null){
                criteria = concept.getVocabulary();
            }
            

        }
        if(conceptCode == null && conceptName == null){
            if(criteria.getClass().getName().endsWith("Property")){
                Property property = (Property) criteria;
                if (property.getName() != null && property.getName().length() > 0) {
                    matchOption = 2;
                    matchType = property.getName();
                    if (property.getValue() != null && property.getValue().length() > 0) {
                        searchValue = property.getValue();
                    }
                }
            }else if(criteria.getClass().getName().endsWith("Association")){
                Association association = (Association) criteria;
                if (association.getName() != null && association.getName().length() > 0) {
                    matchOption = 4;
                    matchType = association.getName();
                    if (association.getValue() != null || association.getValue().length() > 0) {
                        searchValue = association.getValue();
                    }
                }
            }else if(criteria.getClass().getName().endsWith("Silo")){
                matchOption =3 ;
                Silo silo = (Silo) criteria;
                if (silo.getName() != null && silo.getName().length() > 0) {
                    siloName = silo.getName();
                }
            }
            else if(criteria.getClass().getName().endsWith("History")){
                History history = (History)criteria;
                conceptCode = null;
                if(history.getReferenceCode()!=null){
                    conceptCode = history.getReferenceCode();
                }
            }
            else if(criteria.getClass().getName().endsWith("HistoryRecord")){
                HistoryRecord history = (HistoryRecord)criteria;
                conceptCode = null;
                if(history.getDescLogicConceptCode()!=null){
                    conceptCode = history.getDescLogicConceptCode();
                }
            }
            
            else if(criteria.getClass().getName().endsWith("Vocabulary")){
                if(vocabulary.getName()!= null && vocabulary.getName().length()>0){
                    defaultVocabulary = concept.getVocabulary().getName();
                }                 
                if(vocabulary.getSiloCollection().size()>0){
                    Silo silo = (Silo)vocabulary.getSiloCollection().get(0);
                    if(silo.getName()!=null){
                        siloName = silo.getName();
                        matchOption = 4;
                    }
                }

            }
            
        }
        EVSQuery evsQuery = new EVSQueryImpl();

        if (conceptCode != null) {
           evsQuery.getDescLogicConcept(defaultVocabulary, conceptCode, true);
        } else if (conceptName != null && matchOption == 0) {
            evsQuery.searchDescLogicConcepts(defaultVocabulary, conceptName, defaultLimit);
        } else if (matchType != null) {
            if (searchValue == null && conceptName != null) {
                searchValue = conceptName;
            }
            if (searchValue == null) {
                throw new Exception("Search value for " + matchType
                        + " not specified");
            }
            evsQuery.searchDescLogicConcepts(defaultVocabulary, searchValue, defaultLimit,
                    matchOption, matchType, 1);
        } else if(conceptName != null  && siloName != null){
            evsQuery.getConceptWithSiloMatching(defaultVocabulary,conceptName,defaultLimit,siloName);
        }else if(returnClassName.endsWith("Silo")){
            evsQuery.getAllSilos(defaultVocabulary);
        }else{
            throw new Exception("Please specify search term or concept code");
        }


        results = query(evsQuery);
        if (results.size() > 0) {
            if (returnClassName.endsWith("MetaThesaurusConcept")) {
                results = convertConcepts(results);
            } else if (returnClassName.endsWith("HistoryRecord")
                    || returnClassName.endsWith("History")) {
                results = getHistoryRecords(results);
            } else if (returnClassName.endsWith("Atom")){
                results = getAtoms(convertConcepts(results));
            }
         }


        return results;
    }

    /**
     * Returns a List records from the Metathesaurus
     * @param criteria - Instance of gov.nih.nci.evs.domain.* object
     * @return
     * @throws Exception
     */
    private List searchMetaphrase(Object criteria)
            throws Exception {
        String conceptName = null;
        String conceptCode = null;
        List sourceList = new ArrayList();
        String source = null;
        List atomList = new ArrayList();
        try {
            if(criteria.getClass().getName().endsWith("MetaThesaurusConcept")){
                MetaThesaurusConcept metaConcept = (MetaThesaurusConcept)criteria;
                conceptName = metaConcept.getName();
                conceptCode = metaConcept.getCui();
                if (metaConcept.getSourceCollection() != null) {
                    sourceList = metaConcept.getSourceCollection();
                    if (sourceList.size() > 0) {
                        source = ((Source) sourceList.get(0)).getAbbreviation();
                        if (source.length() == 0) {
                            source = "*";
                        }
                    } else {
                        source = "*";
                    }
                } else {
                    source = "*";
                }
                if (metaConcept.getAtomCollection() != null) {
                    atomList = metaConcept.getAtomCollection();                   
                }

            }
            else if(criteria.getClass().getName().endsWith("Atom")){
                atomList.add(criteria);
                Atom atom = (Atom)criteria;
                if(atom.getSource().getAbbreviation() != null){
                    source = atom.getSource().getAbbreviation();
                }
            }
            else if(criteria.getClass().getName().endsWith("Source")){
                source = ((Source)criteria).getAbbreviation();
            }


        } catch (Exception e) {
            throw new Exception("getMetaThesaurus method throws Exception ="
                    + e.getMessage());
        }

        EVSQuery evsQuery = new EVSQueryImpl();
        List results = new ArrayList();

        try {

            if (conceptCode != null && source == null) {
                evsQuery.searchMetaThesaurus(conceptCode);
            } else if (conceptCode != null && source != null) {
                evsQuery.searchMetaThesaurus(conceptCode, defaultLimit, source,
                        true, false, true);
            } else if (conceptName != null) {
                evsQuery.searchMetaThesaurus(conceptName, defaultLimit, source,
                        false, false, true);
            } else if (conceptName == null && conceptCode == null && atomList.size()>0) {
                return searchMetaphraseByAtoms(atomList, source);
            } else if(returnClassName.endsWith("Source")){
                    evsQuery.getMetaSources();
            }else if(returnClassName.endsWith("SemanticType")){
                evsQuery.getSemanticTypes();
            }
            else{
                throw new Exception(
                        "You need to specify either a concept name, code or an Atom code along with the source abbreviation");
            }
            results = query(evsQuery);
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return results;

    }
    
    public List searchMetaphraseByAtoms(List atomList, String sourceAbbr) throws Exception{
        
        List conceptList = new ArrayList();
        for(int i=0; i<atomList.size(); i++){
            Atom atom = (Atom) atomList.get(i); 
            String code = atom.getCode();
            String source = null;
            
            if (code != null) {                
                if(atom.getSource()!= null){
                    try{
                        source = atom.getSource().getAbbreviation();
                    }catch(Exception ex){
                        log.error(ex.getMessage());
                    }
                }
                if(source == null){
                    source = sourceAbbr;
                }
                EVSQuery evsQuery = new EVSQueryImpl();                
                evsQuery.searchSourceByCode(code, source);
                
                List results = query(evsQuery);
                for(int r=0; r<results.size(); r++){
                    conceptList.add(results.get(r));
                }                
            }
        }
        return conceptList;
    }

    /**
     * Returns a list of Description Logic vocabularies
     * @return
     * @throws Exception
     */

    private List getVocabularies() throws Exception{
      EVSQuery evsQuery = new EVSQueryImpl();
      //evsQuery.getVocabularies();
      return query(evsQuery);
    }

    /**
     * Returns a list of Silos in the default vocabulary
     * @return
     * @throws Exception
     */
    private List getSilos() throws Exception{
        EVSQuery evsQuery = new EVSQueryImpl();
        evsQuery.getAllSilos(defaultVocabulary);
        return query(evsQuery);
    }


  /**
     * Gets HistoryRecords for one or more DescLogicConepts specified in the list
     * @param concepts
     * @return
     * @throws Exception
     */
    private List getHistoryRecords(List concepts) throws Exception {
        List results = new ArrayList();
        for (int i = 0; i < concepts.size(); i++) {
                results = getHistoryRecords(concepts.get(i));
          }
        return results;
    }

    /**
     * Returns a list of HistoryRecords for a given EVS criteria
     * @param criteria - instance of gov.nih.nci.evs.domain.* object
     * @return
     * @throws Exception
     */
  private List getHistoryRecords(Object criteria)throws Exception{
      EVSQuery evsQuery = new EVSQueryImpl();
      String conceptCode = null;
      if(criteria.getClass().getName().endsWith("DescLogicConcept")){
              DescLogicConcept dlc = (DescLogicConcept)criteria;
              conceptCode = dlc.getCode();
      }
      else if(criteria.getClass().getName().endsWith("History")){
          History history = (History)criteria;
          if(history.getReferenceCode()!= null){
              conceptCode = history.getReferenceCode();
          }
      }
      else if(criteria.getClass().getName().endsWith("HistoryRecord")){
          HistoryRecord history = (HistoryRecord)criteria;
          if(history.getDescLogicConceptCode()!= null){
              conceptCode = history.getDescLogicConceptCode();
          }
      }
      if(conceptCode != null){
          evsQuery.getHistoryRecords(defaultVocabulary, conceptCode);
      }

      return query(evsQuery);
  }

  /**
   * Returns a list of Atoms for one or more MetaThesaurusConcepts specified in the list
   * @param mtcList List of instance of gov.nih.nci.evs.domain.MetaThesaurusConcepts
   * @return
   */
  private List getAtoms(List mtcList){
      Set list = new HashSet();

      for(int i=0; i<mtcList.size(); i++){
          MetaThesaurusConcept mtc = (MetaThesaurusConcept)mtcList.get(i);
          List atomList = mtc.getAtomCollection();
          for(int a=0; a< atomList.size(); a++){
              list.add(atomList.get(a));
          }
      }
      List atoms = new ArrayList();
      atoms.addAll(list);
      return atoms;
  }

  /**
     * Queries EVS vocabularies  and returns results
     * @param evsQuery - specifies the EVSQuery
     * @return
     * @throws Exception
     */

    private List query(EVSQuery evsQuery) throws Exception {
        List results = new ArrayList();
        try {
            Request request = new Request(evsQuery);
            request.setDomainObjectName(evsQuery.getClass().getName());
            ApplicationService appService = ApplicationServiceProvider.getLocalInstance();
            results = (List)  appService.evsSearch(evsQuery);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
        return results;
    }


    /**
     * This method converts a DescLogicConcept to a matching MetaThesaurusConcept or a MetaThesaurusConcept to a matching
     * DescLogicConcept based on the contents of the conceptList.
     * @param concepts List of DescLogicConcepts or a List of MetaThesaurusConcepts
     * @return
     * @throws Exception
     */
    private List convertConcepts(List concepts) throws Exception {
        List convertedResults = new ArrayList();
        Set results = new HashSet();
        if (concepts.get(0).getClass().getName().endsWith("DescLogicConcept")) {
            for (int i = 0; i < concepts.size(); i++) {
                DescLogicConcept concept = (DescLogicConcept) concepts.get(i);

                String metaConceptCode = null;
                Vector properties = concept.getPropertyCollection();
                for (int p = 0; p < properties.size(); p++) {
                    Property property = (Property) properties.get(p);
                    if (property.getName().equals("NCI_META_CUI")
                            || property.getName().equals("UMLS_CUI")) {
                        metaConceptCode = property.getValue();
                        break;
                    }
                }
                EVSQuery metaSearch = new EVSQueryImpl();
                if (metaConceptCode != null) {
                    metaSearch.searchMetaThesaurus(metaConceptCode);
                    try {
                        List metaConcept = query(metaSearch);
                        if (metaConcept.size() > 0) {
                            results.add((MetaThesaurusConcept) metaConcept
                                    .get(0));
                        }
                    } catch (Exception ex) {
                    }

                } else {
                    EVSQuery q = new EVSQueryImpl();
                    q.getMetaSources();
                    List nciSource = new ArrayList();
                    List sourceList = new ArrayList();
                    sourceList = query(q);

                    for (int s = 0; s < sourceList.size(); s++) {
                        Source source = (Source) sourceList.get(s);
                        if (source.getAbbreviation().startsWith("NCI")) {
                            nciSource.add(source.getAbbreviation());
                        }
                    }
                    for (int x = 0; x < nciSource.size(); x++) {
                        String code = (String) nciSource.get(x);

                        metaSearch.searchSourceByCode(concept.getCode(), code);
                        try {
                            List metaConcept = query(metaSearch);
                            if (metaConcept.size() > 0) {
                                results.add((MetaThesaurusConcept) metaConcept
                                        .get(0));
                            }
                        } catch (Exception ex) {
                            log.info("Exception: " + ex.getMessage());
                        }

                    }

                }

            }
        } else if (concepts.get(0).getClass().getName().endsWith(
                "MetaThesaurusConcept")) {
            for (int i = 0; i < concepts.size(); i++) {
                MetaThesaurusConcept mtc = (MetaThesaurusConcept) concepts
                        .get(i);
                String code = mtc.getCui();
                List dlcConcept = new ArrayList();
                List match = new ArrayList();
                if (code != null) {
                    EVSQuery evsQuery = new EVSQueryImpl();
                    evsQuery.searchDescLogicConcepts(defaultVocabulary, code,
                            1, 2, "NCI_META_CUI", 1);
                    dlcConcept = query(evsQuery);
                    if (dlcConcept.size() < 1) {
                        evsQuery = new EVSQueryImpl();
                        evsQuery.searchDescLogicConcepts(defaultVocabulary,
                                code, 1, 2, "UMLS_CUI", 1);
                        dlcConcept = query(evsQuery);
                    }
                    if (dlcConcept.size() < 1) {
                        ArrayList atomList = mtc.getAtomCollection();
                        for (int a = 0; a < atomList.size(); a++) {
                            Atom atom = (Atom) atomList.get(a);
                            Source source = atom.getSource();

                            if (source.getAbbreviation().startsWith("NCI")) {
                                String atomCode = atom.getCode();
                                evsQuery = new EVSQueryImpl();
                                evsQuery.getDescLogicConcept(defaultVocabulary,
                                        atomCode, true);
                                try {
                                    match = query(evsQuery);

                                } catch (Exception ex) {
                                    log.error("Invalid concept" + atomCode);
                                }
                                if (match.size() > 0) {
                                    dlcConcept.add(match.get(0));
                                }
                            }
                        }
                    }
                    if (dlcConcept.size() > 0) {
                        results.add((DescLogicConcept) dlcConcept.get(0));
                    }
                }
            }
        }
        if (results.size() > 0) {
            for (Iterator i = results.iterator(); i.hasNext();) {
                convertedResults.add(i.next());
            }
        }
        return convertedResults;
    }




//  ===============================================================

}
