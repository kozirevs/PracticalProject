package com.java7.sample.repository;

import com.java7.sample.HibernateUtil;
import com.java7.sample.model.Vet;
import com.java7.sample.repository.helper.RepositoryServant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class VetRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    RepositoryServant repositoryServant = new RepositoryServant();
    public Vet saveVet(Vet vet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(vet);
        vet.setVetId(id);
        transaction.commit();
        session.close();
        return vet;
    }

    public List<Vet> findAllVets() {
        Session session = sessionFactory.openSession();
        List<Vet> resultList = session.createQuery("FROM Vet", Vet.class).list();
        session.close();
        return resultList;
    }

    public Vet findById(Long vetId) {
        Session session = sessionFactory.openSession();
        Vet result = session.find(Vet.class, vetId);
        session.close();
        return result;
    }


    public void deleteVet(Vet vet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(vet);
        transaction.commit();
        session.close();
    }

    public void updateVet(Vet vet) {
        repositoryServant.updateDomain(vet);
    }
}
