/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.casereport.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.openmrs.GlobalProperty;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.casereport.CaseReport;
import org.openmrs.module.casereport.CaseReportForm;
import org.openmrs.module.casereport.api.CaseReportService;
import org.openmrs.util.OpenmrsConstants;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;

public class FhirUtilTest extends BaseModuleWebContextSensitiveTest {
	
	/**
	 * @see FhirUtil#createCdaDocument(CaseReportForm)
	 * @verifies return the generated json
	 */
	@Test
	public void createCdaDocument_shouldReturnTheGeneratedJson() throws Exception {
		executeDataSet("moduleTestData-initialCaseReports.xml");
		executeDataSet("moduleTestData-other.xml");
		final String implId = "Test_Impl";
		final String implName = "Test_Name";
		//set the implementation id for test purposes
		AdministrationService adminService = Context.getAdministrationService();
		String implementationIdGpValue = "<implementationId id=\"1\" implementationId=\"" + implId + "\">\n"
		        + "   <passphrase id=\"2\"><![CDATA[Some passphrase]]></passphrase>\n"
		        + "   <description id=\"3\"><![CDATA[Some descr]]></description>\n" + "   <name id=\"4\"><![CDATA["
		        + implName + "]]></name>\n" + "</implementationId>";
		GlobalProperty gp = new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_IMPLEMENTATION_ID, implementationIdGpValue);
		adminService.saveGlobalProperty(gp);
		
		CaseReportService service = Context.getService(CaseReportService.class);
		CaseReport caseReport = service.getCaseReport(1);
		service.submitCaseReport(caseReport, null, null, null, null);
		CaseReportForm form = new ObjectMapper().readValue(caseReport.getReportForm(), CaseReportForm.class);
		form.setReportUuid(caseReport.getUuid());
		form.setReportDate(caseReport.getDateCreated());
		//TODO assertions
	}
}
