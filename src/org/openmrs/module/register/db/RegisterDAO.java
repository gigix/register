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
package org.openmrs.module.register.db;

import org.openmrs.module.register.db.hibernate.Register;
import org.openmrs.module.register.db.hibernate.RegisterType;

import java.util.List;

public interface RegisterDAO {
    List<Register> getRegisters();

	Register getRegister(Integer registerId);

    Register saveRegister(Register register);

    void deleteRegister(Register register);
    
	List<RegisterType> getRegisterTypes();

	RegisterType getRegisterType(Integer registerTypeId);

	List<Register> getActiveRegisters();
}
