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
package org.openmrs.module.register;

import org.springframework.transaction.annotation.Transactional;
import org.openmrs.Encounter;
import org.openmrs.annotation.Authorized;

import org.openmrs.module.register.db.hibernate.Register;
import org.openmrs.module.register.db.hibernate.RegisterType;

import java.util.List;

public interface RegisterService {

	@Transactional(readOnly = true)
	@Authorized( { RegisterConstant.VIEW_REGISTERS })
	public List<Register> getRegisters();
	
	@Transactional(readOnly = true)
	@Authorized( { RegisterConstant.VIEW_REGISTERS })	
    List<Register> getRegisters(boolean includeRetired);

	@Transactional(readOnly = true)
	@Authorized( { RegisterConstant.VIEW_REGISTERS })
	public Register getRegister(Integer registerId);
	
	@Transactional
	@Authorized( { RegisterConstant.MANAGE_REGISTERS })
	public Register saveRegister(Register register);

	@Transactional
	@Authorized( { RegisterConstant.MANAGE_REGISTERS })
	public void deleteRegister(Integer registerId);

	@Transactional
	@Authorized( { RegisterConstant.MANAGE_REGISTERS })
	public void deleteRegister(Register register);

	@Transactional(readOnly = true)
	@Authorized( { RegisterConstant.VIEW_REGISTERS })
	public List<RegisterType> getRegisterTypes();

	@Transactional(readOnly = true)
	@Authorized( { RegisterConstant.VIEW_REGISTERS })
	public RegisterType getRegisterType(Integer registerTypeId);
	
	@Transactional(readOnly = true)
	public List<Encounter> getEncountersForRegisterByLocation(Integer registerId ,Integer locationId);

}
