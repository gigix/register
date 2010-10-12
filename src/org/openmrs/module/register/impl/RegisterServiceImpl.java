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
package org.openmrs.module.register.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.register.RegisterService;
import org.openmrs.module.register.db.hibernate.Register;
import org.openmrs.module.register.db.hibernate.RegisterType;
import org.openmrs.module.register.db.RegisterDAO;

import java.util.List;

public class RegisterServiceImpl extends BaseOpenmrsService implements RegisterService{
    private RegisterDAO dao;

    public void setDao(RegisterDAO dao) {
        this.dao = dao;
    }

    public List<Register> getRegisters() {
        return dao.getRegisters();
    }
    
    public List<Register> getActiveRegisters() {
        return dao.getActiveRegisters();
    }

	public Register getRegister(Integer registerId) {
		return dao.getRegister(registerId);
	}

    public Register saveRegister(Register register) {
        return dao.saveRegister(register);
    }

    public void deleteRegister(Integer registerId) {
    	deleteRegister(getRegister(registerId));
	}
    public void deleteRegister(Register register) {
		dao.deleteRegister(register);		
	}
    
	public List<RegisterType> getRegisterTypes() {
		return dao.getRegisterTypes();
	}

	public RegisterType getRegisterType(Integer id) {
		return dao.getRegisterType(id);
	}
}
