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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;

/**
 * This class contains the logic that is run every time this module is either
 * started or shutdown
 */
public class RegisterModuleActivator extends BaseModuleActivator {

	private Log log = LogFactory.getLog(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openmrs.module.BaseModuleActivator#started()
	 */
	@Override
	public void started() {
		log.info("Register Module Started");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openmrs.module.BaseModuleActivator#stopped()
	 */
	@Override
	public void stopped() {
		log.info("Register Module Stoped");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openmrs.module.BaseModuleActivator#willStart()
	 */
	@Override
	public void willStart() {
		log.info("Starting Register Module");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openmrs.module.BaseModuleActivator#willStop()
	 */
	@Override
	public void willStop() {
		log.info("Stoping Register Module");
	}
}
