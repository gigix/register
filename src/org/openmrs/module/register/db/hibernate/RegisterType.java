package org.openmrs.module.register.db.hibernate;

import org.openmrs.BaseOpenmrsMetadata;

public class RegisterType extends BaseOpenmrsMetadata {
	private Integer registerTypeId;

	public Integer getRegisterTypeId() {
		return registerTypeId;
	}

	public void setRegisterTypeId(Integer registerTypeId) {
		this.registerTypeId = registerTypeId;
	}

	@Override
	public Integer getId() {
		return getRegisterTypeId();
	}

	@Override
	public void setId(Integer registerTypeId) {
		setRegisterTypeId(registerTypeId);
	}

	@Override
	public String toString() {
		return "RegisterType [RegisterTypeId=" + registerTypeId + ", Name=" + getName() + ", Description=" + getDescription()
				+ ", Retired=" + getRetired() + "]";
	}
}
