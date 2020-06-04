package com.java7.sample.repository;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Consult;
import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class ConsultRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Consult saveConsult(Consult consult) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(consult);
        consult.setConsultId(id);
        transaction.commit();
        session.close();
        return consult;
    }

    public List<Consult> findAllConsults() {
        Session session = sessionFactory.openSession();
        List<Consult> resultList = session.createQuery("FROM Consult", Consult.class).list();
        session.close();
        return resultList;
    }

    public List<Vet> findAllVetsByConsults() {
        Session session = sessionFactory.openSession();
        List<Vet> resultList = session.createQuery("SELECT v FROM Consult c JOIN c.vet v", Vet.class).list();
        session.close();
        return resultList;
    }

    public List<Pet> findAllPetsByConsults() {
        Session session = sessionFactory.openSession();
        TypedQuery<Pet> query = session.createQuery("SELECT p FROM Consult c JOIN c.pet p", Pet.class);
        List<Pet> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    public Consult findById(Long consultId) {
        Session session = sessionFactory.openSession();
        Consult result = session.find(Consult.class, consultId);
        session.close();
        return result;
    }


    public void deleteConsult(Consult consult) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(consult);
        transaction.commit();
        session.close();
    }

    public void updateConsult(Consult consult) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(consult);
        transaction.commit();
        session.close();
    }
}
