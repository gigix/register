/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.register.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.register.RegisterService;
import org.openmrs.module.register.db.hibernate.Register;
import org.openmrs.web.WebConstants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(value = "module/register/manageRegister.list")
public class ManageRegisterListController {

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	/** Success form view name */
	private final String MANAGE_REGISTER_LIST_VIEW = "/module/register/manageRegisterList";

	/**
	 * Initially called after the formBackingObject method to get the landing
	 * form name
	 * 
	 * @return String form view name
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return MANAGE_REGISTER_LIST_VIEW;
	}

	@ModelAttribute("registers")
	protected List<Register> formBackingObject() throws Exception {
		RegisterService registerService = (RegisterService) Context.getService(RegisterService.class);

		return registerService.getRegisters();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(WebRequest request, HttpSession httpSession, @ModelAttribute("registers") List<Register> registers, BindingResult errors) {
		MessageSourceService mss = Context.getMessageSourceService();

		String success = "";
		String error = "";
		String[] registerIds = request.getParameterValues("registerId");
		if (registerIds != null) {
			RegisterService registerService = (RegisterService) Context.getService(RegisterService.class);

			String deleted = mss.getMessage("general.deleted");
			String notDeleted = mss.getMessage("register.delete.error");

			for (String id : registerIds) {
				try {
					registerService.deleteRegister(new Integer(id));
					if (!success.equals(""))
						success += "<br/>";
					success += id + " " + deleted;
				} catch (DataIntegrityViolationException e) {
					error = handleRegisterIntegrityException(e, error, notDeleted);
				} catch (APIException e) {
					error = handleRegisterIntegrityException(e, error, notDeleted);
				}
			}
		} else {
			httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "register.must.select");
		}

		if (!success.equals(""))
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, success);
		if (!error.equals(""))
			httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, error);

		return MANAGE_REGISTER_LIST_VIEW;
	}

	/**
	 * Logs a Register delete data integrity violation exception and returns a
	 * user friendly message of the problem that occurred.
	 * 
	 * @param e
	 *            the exception.
	 * @param error
	 *            the error message.
	 * @param notDeleted
	 *            the not deleted error message.
	 * @return the formatted error message.
	 */
	private String handleRegisterIntegrityException(Exception e, String error, String notDeleted) {
		log.warn("Error deleting register", e);
		if (!error.equals(""))
			error += "<br/>";
		error += notDeleted;
		return error;
	}
}
