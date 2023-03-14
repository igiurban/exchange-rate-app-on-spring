package pl.kurs.exchangerateapponspring.dao;

import pl.kurs.exchangerateapponspring.model.ExchangeEvent;

public interface IExchangeEventDao {
    void save(ExchangeEvent exchangeEvent);

    ExchangeEvent getById(long i);
}
