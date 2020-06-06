package com.java7.sample.repository.helper;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Domain;
import com.java7.sample.model.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RepositoryServant {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public void updateDomain(Domain pet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(pet);
        transaction.commit();
        session.close();
    }
}
