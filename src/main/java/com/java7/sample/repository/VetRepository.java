package com.java7.sample.repository;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Vet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class VetRepository {

    public Vet saveVet(Vet vet) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(vet);
        vet.setVetId(id);
        transaction.commit();
        session.close();
        return vet;
    }

    public List<Vet> findAllVets() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Vet> resultList = session.createQuery("FROM Vet").list();
        session.close();
        return resultList;
    }

    public Vet findById(Long vetId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Vet result = session.find(Vet.class, vetId);
        session.close();
        return result;
    }


    public void deleteVet(Vet vet) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(vet);
        transaction.commit();
        session.close();
    }

    public void updateVet(Vet vet) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(vet);
        transaction.commit();
        session.close();
    }
}
