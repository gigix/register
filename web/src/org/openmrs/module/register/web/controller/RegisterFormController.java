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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.htmlformentry.HtmlForm;
import org.openmrs.module.htmlformentry.HtmlFormEntryService;
import org.openmrs.module.register.RegisterService;
import org.openmrs.module.register.db.hibernate.Register;
import org.openmrs.module.register.db.hibernate.RegisterType;
import org.openmrs.module.register.propertyeditor.HtmlFormEdiotr;
import org.openmrs.module.register.propertyeditor.RegisterTypeEditor;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "module/register/register.form")
public class RegisterFormController {

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	private static final String MANAGE_REGISTER_LIST = "/module/register/manageRegister.list";

	private static final String REGISTER_FORM_VIEW = "/module/register/registerForm";

	
	/**
	 * Initially called after the formBackingObject method to get the landing
	 * form name
	 * 
	 * @return String form view name
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return REGISTER_FORM_VIEW;
	}

	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(HtmlForm.class, new HtmlFormEdiotr());
		binder.registerCustomEditor(RegisterType.class, new RegisterTypeEditor());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletResponse response, HttpSession httpSession, @ModelAttribute("commandMap") CommandMap commandMap, BindingResult errors) {
		Register paramRegister = (Register) commandMap.getMap().get("register");
		validate(paramRegister, errors);
		
		if (errors.hasErrors()) {
			return REGISTER_FORM_VIEW;
		}
		RegisterService registerService = Context.getService(RegisterService.class);
		paramRegister = registerService.saveRegister(paramRegister);
		
		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "register.saved");

		
		return "redirect:" + MANAGE_REGISTER_LIST;
	}
	
	private void validate(Register register, BindingResult errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "map['register'].name", "error.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "map['register'].registerType", "error.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "map['register'].htmlForm", "error.null");
		if(register.getRetired()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "map['register'].retireReason", "error.null");
		}
	}

	@ModelAttribute("commandMap")
	protected CommandMap formBackingObject(@RequestParam(required=false, value="registerId") Integer registerId) throws Exception {
			CommandMap commandMap = new CommandMap();
			Register register = null;
			RegisterService registerService = (RegisterService) Context.getService(RegisterService.class);
			HtmlFormEntryService htmlFormEntryService = Context.getService(HtmlFormEntryService.class);

			List<HtmlForm> allHtmlForms = htmlFormEntryService.getAllHtmlForms();
			List<RegisterType> registerTypes = registerService.getRegisterTypes();

			commandMap.addToMap("registerTypes", registerTypes);
			if (registerId != null){
				register = registerService.getRegister(registerId);
			}
			if (register == null){
				register = new Register();
			}
			commandMap.addToMap("register", register);
			commandMap.addToMap("htmlForms", allHtmlForms);
		
		return commandMap;
	}
}
