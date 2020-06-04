package com.java7.sample.repository;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PetRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Pet savePet(Pet pet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(pet);
        pet.setPetId(id);
        transaction.commit();
        session.close();
        return pet;
    }

    public List<Pet> findAllPets() {
        Session session = sessionFactory.openSession();
        List<Pet> resultList = session.createQuery("FROM Pet", Pet.class).list();
        session.close();
        return resultList;
    }

    public Pet findById(Long petId) {
        Session session = sessionFactory.openSession();
        Pet result = session.find(Pet.class, petId);
        session.close();
        return result;
    }


    public void deletePet(Pet pet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(pet);
        transaction.commit();
        session.close();
    }

    public void updatePet(Pet pet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(pet);
        transaction.commit();
        session.close();
    }
}
