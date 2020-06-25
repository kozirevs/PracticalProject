package com.java7.sample.repository;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Consult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ConsultRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Object[]> findAllConsultsWithVetAndPets() {
        Session session = sessionFactory.openSession();
        String query = "SELECT c.id, c.date, c.description, v.firstName, v.lastName, p.race, p.ownerName FROM Vet v " +
                "JOIN v.consults c JOIN c.pet p";
        List<Object[]> resultList = session.createQuery(query, Object[].class).list();
        session.close();
        return resultList;
    }

    public Consult findById(Long id) {
        Session session = sessionFactory.openSession();
        Consult result = session.find(Consult.class, id);
        session.close();
        return result;
    }
}
