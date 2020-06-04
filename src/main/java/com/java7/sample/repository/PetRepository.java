package com.java7.sample.repository;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PetRepository {

    public Pet savePet(Pet pet) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(pet);
        pet.setPetId(id);
        transaction.commit();
        session.close();
        return pet;
    }

    public List<Pet> findAllPets() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Pet> resultList = session.createQuery("FROM Pet").list();
        session.close();
        return resultList;
    }

    public Pet findById(Long petId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Pet result = session.find(Pet.class, petId);
        session.close();
        return result;
    }


    public void deletePet(Pet pet) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(pet);
        transaction.commit();
        session.close();
    }

    public void updatePet(Pet pet) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(pet);
        transaction.commit();
        session.close();
    }
}
