package org.openmrs.module.register.db;

import org.openmrs.module.register.db.hibernate.Register;

import java.util.List;

public interface RegisterDAO {
    List<Register> getRegisters();

    Register saveRegister(Register register);
}
