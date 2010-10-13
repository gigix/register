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
package org.openmrs.module.register.db.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.register.db.RegisterDAO;

public class HibernateRegisterDAO implements RegisterDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Register> getRegisters(boolean includeRetired) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Register.class);
		criteria.add(Restrictions.eq("retired", includeRetired));
		return criteria.list();
	}

	public Register getRegister(Integer registerId) {
		return (Register) sessionFactory.getCurrentSession().load(Register.class, registerId);
	}

	public Register saveRegister(Register register) {
		getCurrentSession().saveOrUpdate(register);
		return register;
	}
	
	public void deleteRegister(Register register) {
		getCurrentSession().delete(register);
	}

	@SuppressWarnings("unchecked")
	public List<RegisterType> getRegisterTypes() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RegisterType.class);
		return criteria.list();
	}

	public RegisterType getRegisterType(Integer id) {
		return (RegisterType) sessionFactory.getCurrentSession().load(RegisterType.class, id);
	}
}
