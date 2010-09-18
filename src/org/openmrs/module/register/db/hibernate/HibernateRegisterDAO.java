package org.openmrs.module.register.db.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.openmrs.module.register.db.RegisterDAO;

import java.util.List;

public class HibernateRegisterDAO implements RegisterDAO {
    private SessionFactory sessionFactory;


    public List<Register> getRegisters() {
        Query query = getCurrentSession().createQuery("from Register register");
        return query.list();  //To change body of implemented methods use File | Settings | File Templates.
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Register saveRegister(Register register) {
        return (Register) getCurrentSession().save(register);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
