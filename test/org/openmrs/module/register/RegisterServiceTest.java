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

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.register.db.hibernate.Register;
import org.openmrs.test.BaseContextSensitiveTest;
import org.openmrs.test.BaseModuleContextSensitiveTest;

public class RegisterServiceTest extends BaseModuleContextSensitiveTest {
	
	protected static final String INITIAL_DATA_XML = "resources/org/openmrs/module/register/RegisterServiceTest-initialData.xml";
	
	/**
	 * Run this before each unit test in this class. This adds a bit more data to the base data that
	 * is done in the "@Before" method in {@link BaseContextSensitiveTest} (which is run right
	 * before this method).
	 * 
	 * @throws Exception
	 */
	@Before
	public void runBeforeEachTest() throws Exception {
		executeDataSet(INITIAL_DATA_XML);
	}
	
    @Test
    public void shouldReturnAListOfRegisters() {
        RegisterService service = Context.getService(RegisterService.class);
        List<Register> registers = service.getRegisters(true);
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
