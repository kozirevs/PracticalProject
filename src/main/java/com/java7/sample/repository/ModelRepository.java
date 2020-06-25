package com.java7.sample.repository;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ModelRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Long saveModel(Model model) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(model);
        transaction.commit();
        session.close();
        return id;
    }

    public void deleteModel(Model model) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(model);
        transaction.commit();
        session.close();
    }

    public void updateModel(Model model) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(model);
        transaction.commit();
        session.close();
    }
}
