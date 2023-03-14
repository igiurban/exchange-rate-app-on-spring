package pl.kurs.exchangerateapponspring.dao;

import org.springframework.stereotype.Repository;
import pl.kurs.exchangerateapponspring.model.ExchangeEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Repository
public class ExchangeEventDao implements IExchangeEventDao{

    @PersistenceUnit
    private EntityManagerFactory factory;

    @Override
    public void save(ExchangeEvent exchangeEvent) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(exchangeEvent);
        tx.commit();
        em.close();
    }

    @Override
    public ExchangeEvent getById(long id) {
        EntityManager em = factory.createEntityManager();
        ExchangeEvent exchangeEvent = em.find(ExchangeEvent.class, id);
        em.close();
        return exchangeEvent;
    }
}
