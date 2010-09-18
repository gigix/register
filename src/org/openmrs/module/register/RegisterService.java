package org.openmrs.module.register;

import org.openmrs.module.register.db.hibernate.Register;

import java.util.List;

public interface RegisterService {
    public List<Register> getRegisters();

    Register saveRegister(Register register);
}
