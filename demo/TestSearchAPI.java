import gov.nih.nci.system.applicationservice.*;
import java.util.*;

import gov.nih.nci.cadsr.domain.*;
import gov.nih.nci.cabio.domain.*;
import gov.nih.nci.common.util.*;
import gov.nih.nci.common.provenance.domain.*;
import org.hibernate.criterion.*;
import org.w3c.dom.Document;

/**
 * <!-- LICENSE_TEXT_START -->
 * Copyright 2001-2007 SAIC. Copyright 2001-2007 SAIC. This software was developed in conjunction with the National Cancer Institute,
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
 * @author caBIO Team
 * @version 1.0
 */

/**
 * TestSearchAPI.java demonstartes various ways to execute searches "freestyle"
 * caCORE domain objects searches.
 */

public class TestSearchAPI {

	public static void main(String[] args) {

		System.out.println("*** TestClient...");
		try {
			int tstCase = 0;
			String prodUrl = "http://cabio.nci.nih.gov/cacore40/http/remoteService";
			String stageUrl = "http://cabio-stage.nci.nih.gov/cacore40/http/remoteService";
			String qaUrl = "http://cabio-qa.nci.nih.gov/cacore40/http/remoteService";
			String localUrl = "http://localhost:8080/cacore40/http/remoteService";
			// String genUrl =
			// "http://localhost:8080/cacore40/http/remoteService";
			// ApplicationService appService =
			// ApplicationService.getRemoteInstance(prodUrl);

			ApplicationService appService = ApplicationServiceProvider
					.getApplicationService();

			System.out
					.println("\n___________________________________________________________________");
			System.out.println("Test Case " + tstCase++
					+ ": Query for a \"cancer\" across caCORE Domain Objects ");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			try {
				gov.nih.nci.search.SearchQuery query = new gov.nih.nci.search.SearchQuery();
				query.setKeyword("cancer");

				List results = appService.search(
						"gov.nih.nci.search.SearchQuery", query);
				System.out
						.println("Number of records found: " + results.size());
				PrintUtils p = new PrintUtils();
				p.printResults(results);
			} catch (Exception ex) {
				System.out.println("\tError: >>>>>>>> " + ex.getMessage());
			}

			System.out
					.println("\n___________________________________________________________________");
			System.out
					.println("Test Case "
							+ tstCase++
							+ ": Query for a \"cancer AND TP53\" across caCORE Domain Objects ");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			try {
				gov.nih.nci.search.SearchQuery query = new gov.nih.nci.search.SearchQuery();
				query.setKeyword("cancer AND TP53");

				List results = appService.search(
						"gov.nih.nci.search.SearchQuery", query);
				System.out
						.println("Number of records found: " + results.size());
				PrintUtils p = new PrintUtils();
				p.printResults(results);
			} catch (Exception ex) {
				System.out.println("\tError: >>>>>>>> " + ex.getMessage());
			}

			System.out
					.println("\n___________________________________________________________________");
			System.out
					.println("Test Case "
							+ tstCase++
							+ ": Query for a \"cancer OR TP53\" across caCORE Domain Objects ");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			try {
				gov.nih.nci.search.SearchQuery query = new gov.nih.nci.search.SearchQuery();
				query.setKeyword("cancer OR TP53");

				List results = appService.search(
						"gov.nih.nci.search.SearchQuery", query);
				System.out
						.println("Number of records found: " + results.size());
				PrintUtils p = new PrintUtils();
				p.printResults(results);
			} catch (Exception ex) {
				System.out.println("\tError: >>>>>>>> " + ex.getMessage());
			}

			System.out
					.println("\n___________________________________________________________________");
			System.out
					.println("Test Case "
							+ tstCase++
							+ ": Query for a \"cancer NOT TP53\" across caCORE Domain Objects ");
			System.out.println("Description:\n The NOT operator excludes documents that contain the term after NOT. NOTE: The symbol ! can be used in place of the word NOT.  In the example, find objects containing 'cancer' and does not contain 'TP53'");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			try {
				gov.nih.nci.search.SearchQuery query = new gov.nih.nci.search.SearchQuery();
				query.setKeyword("cancer NOT TP53");

				List results = appService.search(
						"gov.nih.nci.search.SearchQuery", query);
				System.out
						.println("Number of records found: " + results.size());
				PrintUtils p = new PrintUtils();
				p.printResults(results);
			} catch (Exception ex) {
				System.out.println("\tError: >>>>>>>> " + ex.getMessage());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Test client throws Exception = " + ex);
		}
	}
}