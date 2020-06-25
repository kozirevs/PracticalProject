package com.java7.sample.repository;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Vet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class VetRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Vet> findAllVets() {
        Session session = sessionFactory.openSession();
        List<Vet> resultList = session.createQuery("FROM Vet", Vet.class).list();
        session.close();
        return resultList;
    }

    public Vet findById(Long id) {
        Session session = sessionFactory.openSession();
        Vet result = session.find(Vet.class, id);
        session.close();
        return result;
    }

    public List<Vet> findAllVetsByConsults() {
        Session session = sessionFactory.openSession();
        List<Vet> resultList = session.createQuery("SELECT v FROM Consult c JOIN c.vet v", Vet.class).list();
        session.close();
        return resultList;
    }

}
