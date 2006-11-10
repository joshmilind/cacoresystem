
import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.NamespaceConstants;
import gov.nih.nci.evs.domain.ws.*;
import gov.nih.nci.evs.security.ws.*;
import java.util.*;

import gov.nih.nci.common.util.*;


/**
 * <!-- LICENSE_TEXT_START -->
* Copyright 2001-2004 SAIC. Copyright 2001-2003 SAIC. This software was developed in conjunction with the National Cancer Institute,
* and so to the extent government employees are co-authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
* Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
* 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the disclaimer of Article 3, below. Redistributions
* in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other
* materials provided with the distribution.
* 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
* "This product includes software developed by the SAIC and the National Cancer Institute."
* If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself,
* wherever such third-party acknowledgments normally appear.
* 3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or promote products derived from this software.
* 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize
* the recipient to use any trademarks owned by either NCI or SAIC-Frederick.
* 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
* MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE,
* SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * @author Shaziya Muhsin  
 */

/**
 * EVSWSClient class demonstrates EVS Web Service calls made to the caCOREService.
 */
public class EVSWSClient{

    /**
     * @param args
     */
    public static void main(String[] args) {
        try{
            System.out.println("\n--------------------");
            System.out.println("EVS WEB SERVICE TEST");
            System.out.println("\n--------------------\n");
            
            String endpointURL = "http://@WEB_SERVER_NAME@:@WEB_SERVER_PORT@/@PROJECT_NAME@/ws/caCOREService";
            String methodName = "queryObject";
            Service service = new Service();            
            Call call = (Call)service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpointURL));
            call.setOperationName(new QName("EVSWebService",methodName));
            call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("arg1",XMLType.XSD_ANYTYPE, ParameterMode.IN);            
            List printList = new ArrayList();
            PrintUtils p = new PrintUtils();

    QName qnMTC = new QName("urn:ws.domain.evs.nci.nih.gov", "MetaThesaurusConcept");
    QName qnSource = new QName("urn:ws.domain.evs.nci.nih.gov", "Source");
    QName qnSemanticType = new QName("urn:ws.domain.evs.nci.nih.gov", "SemanticType");
    QName qnDefinition = new QName("urn:ws.domain.evs.nci.nih.gov", "Definition");
    QName qnAtom = new QName("urn:ws.domain.evs.nci.nih.gov", "Atom");
    QName qnMTCArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_MetaThesaurusConcept");
    QName qnSourceArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_Source");
    QName qnSemanticTypeArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_SemanticType");
    QName qnDefinitionArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_Definition");
    QName qnAtomArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_Atom");
    QName qnRole = new QName("urn:ws.domain.evs.nci.nih.gov", "Role");
    QName qnDLC = new QName("urn:ws.domain.evs.nci.nih.gov","DescLogicConcept");
    QName qnProperty = new QName("urn:ws.domain.evs.nci.nih.gov","Property");
    QName qnTreeNode = new QName("urn:ws.domain.evs.nci.nih.gov","TreeNode");
    QName qnEdgeProperties = new QName("urn:ws.domain.evs.nci.nih.gov","EdgeProperties");
    QName qnQualifier= new QName("urn:ws.domain.evs.nci.nih.gov","Qualifier");
    QName qnAssociation = new QName("urn:ws.domain.evs.nci.nih.gov","Association");
    QName qnSilo = new QName("urn:ws.domain.evs.nci.nih.gov","Silo");
    QName qnVocabulary = new QName("urn:ws.domain.evs.nci.nih.gov","Vocabulary");
    QName qnHistory = new QName("urn:ws.domain.evs.nci.nih.gov","History");
    QName qnHistoryRecord = new QName("urn:ws.domain.evs.nci.nih.gov","HistoryRecord");
    QName qnEditActionDate = new QName("urn:ws.domain.evs.nci.nih.gov","EditActionDate");
    QName qnAttributeSetDescriptor = new QName("urn:ws.domain.evs.nci.nih.gov","AttributeSetDescriptor");
    QName qnRoleArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_Role");
    QName qnDLCArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_DescLogicConcept");
    QName qnPropertyArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_Property");
    QName qnQualifierArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_Qualifier");
    QName qnAssociationArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_Association");
    QName qnHistoryArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_History");
    QName qnHistoryRecordArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_HistoryRecord");
    QName qnSiloArr = new QName("urn:ws.domain.evs.nci.nih.gov","ArrayOf_tns1_Silo");
    QName qnHashSet = new QName("urn:ws.domain.evs.nci.nih.gov", "HashSet");
    QName qnStringArr = new QName("EVSWebService","ArrayOf_xsd_string");
    QName qnAnyTypeArr = new QName("EVSWebService","ArrayOf_xsd_anytype");
    QName qnVocabularyArr = new QName("EVSWebService","ArrayOf_tns1_Vocabulary");
    QName qnSecurityToken = new QName("urn:ws.security.evs.nci.nih.gov", "SecurityToken");


    call.registerTypeMapping(SecurityToken.class, qnSecurityToken, new org.apache.axis.encoding.ser.BeanSerializerFactory(SecurityToken.class, qnSecurityToken), new org.apache.axis.encoding.ser.BeanDeserializerFactory(SecurityToken.class, qnSecurityToken));
    call.registerTypeMapping(MetaThesaurusConcept.class, qnMTC, new org.apache.axis.encoding.ser.BeanSerializerFactory(MetaThesaurusConcept.class, qnMTC), new org.apache.axis.encoding.ser.BeanDeserializerFactory(MetaThesaurusConcept.class, qnMTC));
    call.registerTypeMapping(Source.class, qnSource, new org.apache.axis.encoding.ser.BeanSerializerFactory(Source.class, qnSource), new org.apache.axis.encoding.ser.BeanDeserializerFactory(Source.class, qnSource));
    call.registerTypeMapping(Definition.class, qnDefinition, new org.apache.axis.encoding.ser.BeanSerializerFactory(Definition.class, qnDefinition), new org.apache.axis.encoding.ser.BeanDeserializerFactory(Definition.class, qnDefinition));
    call.registerTypeMapping(Atom.class, qnAtom, new org.apache.axis.encoding.ser.BeanSerializerFactory(Atom.class, qnAtom), new org.apache.axis.encoding.ser.BeanDeserializerFactory(Atom.class, qnAtom));
    call.registerTypeMapping(SemanticType.class, qnSemanticType, new org.apache.axis.encoding.ser.BeanSerializerFactory(SemanticType.class, qnSemanticType), new org.apache.axis.encoding.ser.BeanDeserializerFactory(SemanticType.class, qnSemanticType));
    call.registerTypeMapping(MetaThesaurusConcept[].class,qnMTCArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(Source[].class,qnSourceArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(Definition[].class,qnDefinitionArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(Atom[].class,qnAtomArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(SemanticType[].class,qnSemanticTypeArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(Role.class, qnRole, new org.apache.axis.encoding.ser.BeanSerializerFactory(Role.class, qnRole), new org.apache.axis.encoding.ser.BeanDeserializerFactory(Role.class, qnRole));
    call.registerTypeMapping(Vocabulary.class, qnVocabulary, new org.apache.axis.encoding.ser.BeanSerializerFactory(Vocabulary.class, qnVocabulary), new org.apache.axis.encoding.ser.BeanDeserializerFactory(Vocabulary.class, qnVocabulary));
    call.registerTypeMapping(Property.class, qnProperty, new org.apache.axis.encoding.ser.BeanSerializerFactory(Property.class, qnProperty), new org.apache.axis.encoding.ser.BeanDeserializerFactory(Property.class, qnProperty));
    call.registerTypeMapping(DescLogicConcept.class, qnDLC, new org.apache.axis.encoding.ser.BeanSerializerFactory(DescLogicConcept.class, qnDLC), new org.apache.axis.encoding.ser.BeanDeserializerFactory(DescLogicConcept.class, qnDLC));
    call.registerTypeMapping(TreeNode.class, qnTreeNode, new org.apache.axis.encoding.ser.BeanSerializerFactory(TreeNode.class, qnTreeNode), new org.apache.axis.encoding.ser.BeanDeserializerFactory(TreeNode.class, qnTreeNode));
    call.registerTypeMapping(EdgeProperties.class, qnEdgeProperties, new org.apache.axis.encoding.ser.BeanSerializerFactory(EdgeProperties.class, qnEdgeProperties), new org.apache.axis.encoding.ser.BeanDeserializerFactory(EdgeProperties.class, qnEdgeProperties));
    call.registerTypeMapping(Qualifier.class, qnQualifier, new org.apache.axis.encoding.ser.BeanSerializerFactory(Qualifier.class, qnQualifier), new org.apache.axis.encoding.ser.BeanDeserializerFactory(Qualifier.class, qnQualifier));
    call.registerTypeMapping(Association.class, qnAssociation, new org.apache.axis.encoding.ser.BeanSerializerFactory(Association.class, qnAssociation), new org.apache.axis.encoding.ser.BeanDeserializerFactory(Association.class, qnAssociation));
    call.registerTypeMapping(Silo.class, qnSilo, new org.apache.axis.encoding.ser.BeanSerializerFactory(Silo.class, qnSilo), new org.apache.axis.encoding.ser.BeanDeserializerFactory(Silo.class, qnSilo));
    call.registerTypeMapping(History.class, qnHistory, new org.apache.axis.encoding.ser.BeanSerializerFactory(History.class, qnHistory), new org.apache.axis.encoding.ser.BeanDeserializerFactory(History.class, qnHistory));
    call.registerTypeMapping(HistoryRecord.class, qnHistoryRecord, new org.apache.axis.encoding.ser.BeanSerializerFactory(HistoryRecord.class, qnHistoryRecord), new org.apache.axis.encoding.ser.BeanDeserializerFactory(HistoryRecord.class, qnHistoryRecord));
    call.registerTypeMapping(AttributeSetDescriptor.class, qnAttributeSetDescriptor, new org.apache.axis.encoding.ser.BeanSerializerFactory(AttributeSetDescriptor.class, qnAttributeSetDescriptor), new org.apache.axis.encoding.ser.BeanDeserializerFactory(AttributeSetDescriptor.class, qnAttributeSetDescriptor));
    call.registerTypeMapping(EditActionDate.class, qnEditActionDate, new org.apache.axis.encoding.ser.BeanSerializerFactory(EditActionDate.class, qnEditActionDate), new org.apache.axis.encoding.ser.BeanDeserializerFactory(EditActionDate.class, qnEditActionDate));
    call.registerTypeMapping(DescLogicConcept[].class,qnDLCArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(Property[].class,qnPropertyArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(Qualifier[].class,qnQualifierArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(Role[].class,qnRoleArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(Association[].class,qnAssociationArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(History[].class,qnHistoryArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(HistoryRecord[].class,qnHistoryRecordArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(Silo[].class,qnSiloArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(HashSet.class, qnHashSet, new org.apache.axis.encoding.ser.BeanSerializerFactory(HashSet.class, qnHashSet), new org.apache.axis.encoding.ser.BeanDeserializerFactory(HashSet.class, qnHashSet));
    call.registerTypeMapping(java.lang.String[].class,qnStringArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());
    call.registerTypeMapping(java.lang.Object[].class,qnAnyTypeArr, new org.apache.axis.encoding.ser.ArraySerializerFactory(), new org.apache.axis.encoding.ser.ArrayDeserializerFactory());



/************* Metaphrase Test **********************************************************/
//1. Search a MetaThesaurusConcept by Atom
/***************************************************************************************/
           
    System.out.println("\n----------------------------------------------------------------");
            System.out.println("1.Search Metaphrase by Atom");
            System.out.println("\n----------------------------------------------------------------\n");

            MetaThesaurusConcept mtc = new MetaThesaurusConcept();
            //mtc.setCui("C0017337");
            ArrayList atoms = new ArrayList();
            Atom atom = new Atom();
            atom.setCode("1256-5501");
            atoms.add(atom);            
            mtc.setAtomCollection(atoms);
            call.setReturnType(qnMTCArr);
            Object[] metaParams = new Object[]{"MetaThesaurusConcept",mtc};
            System.out.println("Mtc: "+ mtc.getClass().getName());
            MetaThesaurusConcept[] meta = (MetaThesaurusConcept[])call.invoke(metaParams);

            if(meta.length>0){
                System.out.println("Size: "+ meta.length);
                
                for(int m=0; m<meta.length; m++){
                   
                        MetaThesaurusConcept concept = (MetaThesaurusConcept)meta[m];
                        System.out.println("\nConcept code: "+concept.getCui() +"\n\t"+concept.getName());
                        List sList =  concept.getSourceCollection();
                        System.out.println("\tSource-->" + sList.size());
                        for(int y=0; y<sList.size(); y++){
                            Source s = (Source)sList.get(y);
                            System.out.println("\t - "+s.getAbbreviation());
                            }
                        List semanticList = concept.getSemanticTypeCollection();
                        System.out.println("\tSemanticType---> count ="+ semanticList.size());
                        for(int z=0; z<semanticList.size(); z++){
                            SemanticType sType = (SemanticType) semanticList.get(z);
                            System.out.println("\t- Id: "+sType.getId()+"\n\t- Name : "+sType.getName());
                            }
                        List atomList = concept.getAtomCollection();
                        System.out.println("\tAtoms -----> count = "+ atomList.size());
                        for(int i=0;i<atomList.size(); i++){
                            Atom at = (Atom)atomList.get(i);
                            System.out.println("\t -Code: "+ at.getCode()+" -Name: "+ at.getName() +" -LUI: "+ at.getLui()+" -Source: "+ at.getSource().getAbbreviation());
                            }
                        List synList = concept.getSynonymCollection();
                        System.out.println("\tSynonyms -----> count = "+ synList.size());
                        for(int i=0; i< synList.size(); i++){
                            System.out.println("\t - "+ (String) synList.get(i));
                            }
                    }
                       
            }           
            
           
            System.out.println("\n----------------------------------------------------------------\n");

/************* Metaphrase and Thesaurus Test *****************************************/
//2. Search for a matching DescLogicConcept based on a MetaThesaurusConcept. 
/***************************************************************************************/

            System.out.println("2. Search matching DescLogicConcepts by a MetaThesaurusConcept.");
            System.out.println("\n----------------------------------------------------------------\n");
            call.setReturnType(qnDLCArr);
            Object[] metaParams1 = new Object[]{"DescLogicConcept",mtc};
            DescLogicConcept[] dlconcepts = (DescLogicConcept[])call.invoke(metaParams1);

                printList = new ArrayList();
                if(dlconcepts.length>0){
                    
                    for(int i=0; i<dlconcepts.length; i++){
                        DescLogicConcept concept = dlconcepts[i];
                        System.out.println("\n"+(i+1)+".Concept: "+ concept.getName()+"\t"+ concept.getCode());
                        List pList = new ArrayList();
                        pList = concept.getPropertyCollection();
                        for(int x=0; x<pList.size(); x++){
                            Property prop = (Property)pList.get(x);
                            System.out.println("\tProperty :"+ prop.getName()+"\t"+ prop.getValue());
                            List qList = prop.getQualifierCollection();
                            for(int q=0; q< qList.size(); q++){
                                Qualifier qual = (Qualifier)qList.get(q);
                                System.out.println("\t\tQualifer "+ qual.getName()+"\t"+ qual.getValue());
                            }
                        }
                        pList = concept.getAssociationCollection();
                        System.out.println("Number of Associations: "+ pList.size());
                        for(int x=0; x<pList.size(); x++){
                            Association ass = (Association)pList.get(x);
                            System.out.println("\tAssociation :"+ ass.getName()+"\t"+ ass.getValue());
                            List qList = ass.getQualifierCollection();
                            for(int q=0; q< qList.size(); q++){
                                Qualifier qual = (Qualifier)qList.get(q);
                                System.out.println("\t\tQualifer "+ qual.getName()+"\t"+ qual.getValue());
                            }
                        }
                        pList = concept.getInverseAssociationCollection();
                        for(int x=0; x<pList.size(); x++){
                            Association ass = (Association)pList.get(x);
                            System.out.println("\tAssociation :"+ ass.getName()+"\t"+ ass.getValue());
                            List qList = ass.getQualifierCollection();
                            for(int q=0; q< qList.size(); q++){
                                Qualifier qual = (Qualifier)qList.get(q);
                                System.out.println("\t\tQualifer "+ qual.getName()+"\t"+ qual.getValue());
                            }
                        }
                        pList = concept.getRoleCollection();
                        System.out.println("Number of roles: "+ pList.size());
                        for(int x=0; x<pList.size(); x++){
                            Role role = (Role)pList.get(x);
                            System.out.println("\tRole :"+ role.getName()+"\t"+ role.getValue());                   
                        }
                        pList = concept.getInverseRoleCollection();
                        for(int x=0; x<pList.size(); x++){
                            Role role = (Role)pList.get(x);
                            System.out.println("\tRole :"+ role.getName()+"\t"+ role.getValue());                   
                        }

                        
                        }
                        
                        
                }
            System.out.println("Number of items returned from Metaphrase = "+ dlconcepts.length);
            System.out.println("\n----------------------------------------------------------------\n");

                
/************* Thesaurus Test **********************************************************/
// 3. Search for a DescLogicConcept by code
/***************************************************************************************/

            System.out.println("3. Search DescLogicConcept by code");
            System.out.println("\n----------------------------------------------------------------\n");
            DescLogicConcept dlc = new DescLogicConcept();
            dlc.setCode("C12756");
            //dlc.setCode("C43782");
            //dlc.setName("organ*");


            call.setReturnType(qnDLCArr);
            Object[] thesaurusParams = new Object[]{"DescLogicConcept",dlc};
            DescLogicConcept[] dlcs = (DescLogicConcept[])call.invoke(thesaurusParams);

            printList = new ArrayList();
            for(int i=0; i<dlcs.length; i++){
                DescLogicConcept concept = dlcs[i];
                System.out.println("\nConcept: "+ concept.getName()+"\t"+ concept.getCode());
                List pList = new ArrayList();
                pList = concept.getPropertyCollection();
                for(int x=0; x<pList.size(); x++){
                    Property prop = (Property)pList.get(x);
                    System.out.println("\tProperty :"+ prop.getName()+"\t"+ prop.getValue());
                    List qList = prop.getQualifierCollection();
                    for(int q=0; q< qList.size(); q++){
                        Qualifier qual = (Qualifier)qList.get(q);
                        System.out.println("\t\tQualifer "+ qual.getName()+"\t"+ qual.getValue());
                    }
                }
                pList = concept.getAssociationCollection();
                for(int x=0; x<pList.size(); x++){
                    Association ass = (Association)pList.get(x);
                    System.out.println("\tAssociation :"+ ass.getName()+"\t"+ ass.getValue());
                    List qList = ass.getQualifierCollection();
                    for(int q=0; q< qList.size(); q++){
                        Qualifier qual = (Qualifier)qList.get(q);
                        System.out.println("\t\tQualifer "+ qual.getName()+"\t"+ qual.getValue());
                    }
                }
                pList = concept.getInverseAssociationCollection();
                for(int x=0; x<pList.size(); x++){
                    Association ass = (Association)pList.get(x);
                    System.out.println("\tAssociation :"+ ass.getName()+"\t"+ ass.getValue());
                    List qList = ass.getQualifierCollection();
                    for(int q=0; q< qList.size(); q++){
                        Qualifier qual = (Qualifier)qList.get(q);
                        System.out.println("\t\tQualifer "+ qual.getName()+"\t"+ qual.getValue());
                    }
                }
                pList = concept.getRoleCollection();
                for(int x=0; x<pList.size(); x++){
                    Role role = (Role)pList.get(x);
                    System.out.println("\tRole :"+ role.getName()+"\t"+ role.getValue());                   
                }
                pList = concept.getInverseRoleCollection();
                for(int x=0; x<pList.size(); x++){
                    Role role = (Role)pList.get(x);
                    System.out.println("\tRole :"+ role.getName()+"\t"+ role.getValue());                   
                }
            }

            
            System.out.println("Number of items returned from Thesaurus = "+ dlcs.length);
            System.out.println("\n----------------------------------------------------------------\n");
            
            
/************* Thesaurus and Metaphrase Test **********************************************************/
//4. Search for a matching MetaThesaurusConcept based on a DescLogicConcept
/***************************************************************************************/

            System.out.println  ("4. Search matching MetaThesaurusConcepts by a DescLogicConcept");
            System.out.println("\n----------------------------------------------------------------\n");

            call.setReturnType(qnMTCArr);
            Object[] thesaurusParams1 = new Object[]{"MetaThesaurusConcept",dlc};
            MetaThesaurusConcept[] mtcs = (MetaThesaurusConcept[])call.invoke(thesaurusParams1);

            printList = new ArrayList();
            if(mtcs.length > 0){
                for(int m=0; m<mtcs.length; m++){
                    MetaThesaurusConcept concept = mtcs[m];                   
                    System.out.println("\nConcept code: "+concept.getCui() +"\n\t"+concept.getName());
                    List sList =  concept.getSourceCollection();
                    System.out.println("\tSource-->" + sList.size());
                    for(int y=0; y<sList.size(); y++){
                        Source s = (Source)sList.get(y);
                        System.out.println("\t - "+s.getAbbreviation());
                        }
                    List semanticList = concept.getSemanticTypeCollection();
                    System.out.println("\tSemanticType---> count ="+ semanticList.size());
                    for(int z=0; z<semanticList.size(); z++){
                        SemanticType sType = (SemanticType) semanticList.get(z);
                        System.out.println("\t- Id: "+sType.getId()+"\n\t- Name : "+sType.getName());
                        }
                    List atomList = concept.getAtomCollection();
                    System.out.println("\tAtoms -----> count = "+ atomList.size());
                    for(int i=0;i<atomList.size(); i++){
                        Atom at = (Atom)atomList.get(i);
                        System.out.println("\t -Code: "+ at.getCode()+" -Name: "+ at.getName() +" -LUI: "+ at.getLui()+" -Source: "+ at.getSource().getAbbreviation());
                        }
                    List synList = concept.getSynonymCollection();
                    System.out.println("\tSynonyms -----> count = "+ synList.size());
                    for(int i=0; i< synList.size(); i++){
                        System.out.println("\t - "+ (String) synList.get(i));
                        }
                    }
                
            }

            System.out.println("Number of items returned from Thesaurus = "+ mtcs.length);
            System.out.println("\n----------------------------------------------------------------\n");

/************* Thesaurus Test **********************************************************/
//          5. Search MedDRA 
/***************************************************************************************/

                     System.out.println("5. Search MedDRA");
                     System.out.println("\n----------------------------------------------------------------\n");
                     DescLogicConcept dlc = new DescLogicConcept();
                     //dlc.setCode("C12756");
                     //dlc.setCode("C43782");
                     dlc.setName("Organ*");
                     Vocabulary vocab = new Vocabulary();
                     vocab.setName("MedDRA");
                     gov.nih.nci.evs.security.ws.SecurityToken token = new gov.nih.nci.evs.security.ws.SecurityToken();
                     //Note: Use a valid security token to access MedDRA
                     token.setAccessToken("xxxxx");
                     vocab.setSecurityToken(token);
                     dlc.setVocabulary(vocab);


                     call.setReturnType(qnDLCArr);
                     Object[] thesaurusParams = new Object[]{"DescLogicConcept",dlc};
                     DescLogicConcept[] dlcs = (DescLogicConcept[])call.invoke(thesaurusParams);

                     printList = new ArrayList();
                     for(int i=0; i<dlcs.length; i++){
                         DescLogicConcept concept = dlcs[i];
                         System.out.println("\nConcept: "+ concept.getName()+"\t"+ concept.getCode());
                         List pList = new ArrayList();
                         pList = concept.getPropertyCollection();
                         for(int x=0; x<pList.size(); x++){
                             Property prop = (Property)pList.get(x);
                             System.out.println("\tProperty :"+ prop.getName()+"\t"+ prop.getValue());
                             List qList = prop.getQualifierCollection();
                             for(int q=0; q< qList.size(); q++){
                                 Qualifier qual = (Qualifier)qList.get(q);
                                 System.out.println("\t\tQualifer "+ qual.getName()+"\t"+ qual.getValue());
                             }
                         }
                         pList = concept.getAssociationCollection();
                         for(int x=0; x<pList.size(); x++){
                             Association ass = (Association)pList.get(x);
                             System.out.println("\tAssociation :"+ ass.getName()+"\t"+ ass.getValue());
                             List qList = ass.getQualifierCollection();
                             for(int q=0; q< qList.size(); q++){
                                 Qualifier qual = (Qualifier)qList.get(q);
                                 System.out.println("\t\tQualifer "+ qual.getName()+"\t"+ qual.getValue());
                             }
                         }
                         pList = concept.getInverseAssociationCollection();
                         for(int x=0; x<pList.size(); x++){
                             Association ass = (Association)pList.get(x);
                             System.out.println("\tAssociation :"+ ass.getName()+"\t"+ ass.getValue());
                             List qList = ass.getQualifierCollection();
                             for(int q=0; q< qList.size(); q++){
                                 Qualifier qual = (Qualifier)qList.get(q);
                                 System.out.println("\t\tQualifer "+ qual.getName()+"\t"+ qual.getValue());
                             }
                         }
                         pList = concept.getRoleCollection();
                         for(int x=0; x<pList.size(); x++){
                             Role role = (Role)pList.get(x);
                             System.out.println("\tRole :"+ role.getName()+"\t"+ role.getValue());                   
                         }
                         pList = concept.getInverseRoleCollection();
                         for(int x=0; x<pList.size(); x++){
                             Role role = (Role)pList.get(x);
                             System.out.println("\tRole :"+ role.getName()+"\t"+ role.getValue());                   
                         }
                     }

                     
                     System.out.println("Number of items returned from Thesaurus = "+ dlcs.length);
                     System.out.println("\n----------------------------------------------------------------\n");
                     


/****************************************************************************************/


            }catch(Exception ex){
                ex.printStackTrace();
            System.out.println(ex.getMessage());
            }

    
    }

}
