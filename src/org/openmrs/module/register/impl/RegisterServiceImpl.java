package org.openmrs.module.register.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.register.RegisterService;
import org.openmrs.module.register.db.hibernate.Register;
import org.openmrs.module.register.db.RegisterDAO;

import java.util.List;

public class RegisterServiceImpl extends BaseOpenmrsService implements RegisterService{
    private RegisterDAO dao;

    public List<Register> getRegisters() {
        return dao.getRegisters();
    }

    public Register saveRegister(Register register) {
        return dao.saveRegister(register)
    }

    public void setDao(RegisterDAO dao) {
        this.dao = dao;
    }
}
