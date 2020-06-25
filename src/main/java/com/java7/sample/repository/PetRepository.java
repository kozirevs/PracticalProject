package com.java7.sample.repository;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;

public class PetRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Pet> findAllPets() {
        Session session = sessionFactory.openSession();
        List<Pet> resultList = session.createQuery("FROM Pet", Pet.class).list();
        session.close();
        return resultList;
    }

    public Pet findById(Long id) {
        Session session = sessionFactory.openSession();
        Pet result = session.find(Pet.class, id);
        session.close();
        return result;
    }

    public List<Pet> findAllPetsByConsults() {
        Session session = sessionFactory.openSession();
        TypedQuery<Pet> query = session.createQuery("SELECT p FROM Consult c JOIN c.pet p", Pet.class);
        List<Pet> resultList = query.getResultList();
        session.close();
        return resultList;
    }
}
