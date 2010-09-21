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
}
