import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.system.comm.common.*;
import java.util.*;
import java.lang.reflect.*;

import gov.nih.nci.cabio.domain.*;
import org.LexGrid.LexBIG.DataModel.Core.*;
import org.LexGrid.LexBIG.DataModel.Collections.*;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.*;
import org.LexGrid.commonTypes.*;
import org.LexGrid.concepts.*;
import org.LexGrid.codingSchemes.*;
import org.LexGrid.LexBIG.Utility.*;
import org.LexGrid.naming.*;
import org.LexGrid.LexBIG.LexBIGService.*;
import org.LexGrid.LexBIG.Impl.*;

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
 * @author EVS Team
 * @version 1.0
 */
/**
 * TestDLB.java demonstartes various ways to execute searches with the EVS
 * ApplicationService Distributed LexBIG interface
 */
public class TestDLB {

	public static void main(String[] args) {

		System.out.println("*** TestClient...");
		try {
			String prodUrl = "http://cabio.nci.nih.gov/cacore40/http/remoteService";
			String stageUrl = "http://cabio-stage.nci.nih.gov/cacore40/http/remoteService";
			String qaUrl = "http://cabio-qa.nci.nih.gov/cacore40/http/remoteService";
			String genUrl = "http://cbioapp506.nci.nih.gov:59080/cacore40/http/evsService";
	                String localUrl = "http://localhost:8080/cacore40/http/evsService";
			//ApplicationService appService = ApplicationService.getRemoteInstance(prodUrl);

			EVSApplicationService service = EVSApplicationService.getRemoteInstance(genUrl);

			try {
				System.out.println("LexGrid service search....");
				System.out.println("calling appService.getSupportedCodingSchemes()");
				// CodingSchemeRenderingList schemes =
				// appService.getSupportedCodingSchemes();

				CodingSchemeRenderingList schemes = service
						.getSupportedCodingSchemes();
				CodingSchemeRendering[] csr = schemes
						.getCodingSchemeRendering();
				for (int i = 0; i < csr.length; i++) {
					CodingSchemeSummary css = csr[i].getCodingSchemeSummary();
					System.out.println("\nCodingSchemeSummary Name : "
							+ css.getLocalName());
					System.out.println("CodingSchemeSummary URN  : "
							+ css.getCodingSchemeURN());
					System.out.println("CodingSchemeSummary: Description\n");
					EntityDescription entity = css.getCodingSchemeDescription();
					System.out.println("\t" + entity.getContent());
				}
				System.out
						.println("-----------------------------------------------");

			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
			System.out.println("Properties file location: "
					+ System.getProperty("LG_CONFIG_FILE"));

			System.out.println("query coded entry");
			String vocabName = "NCI_Thesaurus";

			ConceptReferenceList conrefList = ConvenienceMethods
					.createConceptReferenceList(new String[] { "C12434" },
							vocabName);

			ResolvedConceptReferenceList matches = service
					.getCodingSchemeConcepts(vocabName, null, false)
					.restrictToCodes(conrefList).resolveToList(null, null,
							null, 1);
			System.out.println("Number of matches found: "
					+ matches.getResolvedConceptReferenceCount());
			if (matches.getResolvedConceptReferenceCount() > 0) {
				ResolvedConceptReference ref = (ResolvedConceptReference) matches
						.enumerateResolvedConceptReference().nextElement();
				CodedEntry codedEntry = ref.getReferencedEntry();
				System.out
						.println("-----------------------------------------------");
				System.out.println("Coded Entry Code: "
						+ codedEntry.getConceptCode());
				System.out.println("Coded Entry Definition: "
						+ codedEntry.getDefinition(0));
				System.out
						.println("-----------------------------------------------");
			}

			try {
				ExtensionDescriptionList extList = service
						.getFilterExtensions();
				ExtensionDescription[] extDes = extList
						.getExtensionDescription();
				for (int i = 0; i < extDes.length; i++) {
					System.out.println("Extension class: "
							+ extDes[i].getExtensionClass());
					System.out.println("Extension provider: "
							+ extDes[i].getExtensionProvider());
				}
				System.out.println("\nFilters found: " + extDes.length);
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage() + "\n");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Test client throws Exception = " + ex);
		}
	}
}
