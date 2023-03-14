package pl.kurs.exchangerateapponspring.services;

import pl.kurs.exchangerateapponspring.model.ExchangeEvent;

public interface IExchangeEventService {
    void saveEvent(ExchangeEvent exchangeEvent);
}
