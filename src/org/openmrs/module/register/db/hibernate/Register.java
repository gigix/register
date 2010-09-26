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
package org.openmrs.module.register.db.hibernate;

import java.util.Date;

import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.module.htmlformentry.HtmlForm;

public class Register extends BaseOpenmrsMetadata {
	private Integer registerId;
	private Date startDate;
	private Date endDate;
	private RegisterType registerType;
	private HtmlForm htmlForm;

	@Override
	public Integer getId() {
		return getRegisterId();
	}

	@Override
	public void setId(Integer registerId) {
		setRegisterId(registerId);
	}

	public Integer getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Integer registerId) {
		this.registerId = registerId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public RegisterType getRegisterType() {
		return registerType;
	}

	public void setRegisterType(RegisterType registerType) {
		this.registerType = registerType;
	}

	public HtmlForm getHtmlForm() {
		return htmlForm;
	}

	public void setHtmlForm(HtmlForm htmlForm) {
		this.htmlForm = htmlForm;
	}

	@Override
	public String toString() {
		return "Register [RegisterId=" + registerId + ", Name=" + getName() + ", Description=" + getDescription() + ", registerTypeId="
				+ registerType.getRegisterTypeId() + ", htmlFormId=" + htmlForm.getId() + ", StartDate=" + startDate + ", EndDate=" + endDate
				+ ", Retired=" + getRetired() + "]";
	}
}
