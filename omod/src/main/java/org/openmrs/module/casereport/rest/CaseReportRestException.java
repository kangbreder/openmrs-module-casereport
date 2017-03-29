/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.casereport.rest;

import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Super class for case report related exceptions in the rest api
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CaseReportRestException extends ResponseException {
	
	public CaseReportRestException(String message) {
		super(message);
	}
	
	public CaseReportRestException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CaseReportRestException(String messageKey, Object[] parameters) {
		super(Context.getMessageSourceService().getMessage(messageKey, parameters, Context.getLocale()));
	}
}