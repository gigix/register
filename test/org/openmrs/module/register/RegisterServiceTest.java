package org.openmrs.module.register;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.register.db.hibernate.Register;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RegisterServiceTest extends BaseModuleContextSensitiveTest {
    @Test
    public void shouldReturnAListOfRegisters() {
        RegisterService service = Context.getService(RegisterService.class);
        List<Register> registers = service.getRegisters();
        assertEquals(registers.size(), 1);
    }

    @Test
    public void testCreatingARegister() throws Exception {
        RegisterService service = Context.getService(RegisterService.class);
        Register register = new Register();
        register.setName("register");
        service.saveRegister(register);
        Assert.assertNotNull(register.getId());
    }
}
