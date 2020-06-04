package com.java7.sample.repository;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Consult;
import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ConsultRepository {

    public Consult saveConsult(Consult consult) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(consult);
        consult.setConsultId(id);
        transaction.commit();
        session.close();
        return consult;
    }

    public List<Consult> findAllConsults() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Consult> resultList = session.createQuery("FROM Consult").list();
        session.close();
        return resultList;
    }

    public List<Vet> findAllVetsByConsults() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Vet> resultList = session.createQuery("SELECT v FROM Consult c JOIN c.vet v").list();
        session.close();
        return resultList;
    }

    public List<Pet> findAllPetsByConsults() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Pet> resultList = session.createQuery("SELECT p FROM Consult c JOIN c.pet p").list();
        session.close();
        return resultList;
    }

    public Consult findById(Long consultId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Consult result = session.find(Consult.class, consultId);
        session.close();
        return result;
    }


    public void deleteConsult(Consult consult) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(consult);
        transaction.commit();
        session.close();
    }

    public void updateConsult(Consult consult) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(consult);
        transaction.commit();
        session.close();
    }
}
