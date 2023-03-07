package pl.kurs.exchangerateapponspring.services;

import org.springframework.stereotype.Service;
import pl.kurs.exchangerateapp.exceptions.ConnectionProblemsException;
import pl.kurs.exchangerateapp.exceptions.InvalidInputDataException;
import pl.kurs.exchangerateapp.model.ExchangeEvent;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class CurrencyService implements ICurrencyService {

    private final IRateService rateService;
    private final IExchangeEventService exchangeEventService;


    public CurrencyService(IRateService rateService, IExchangeEventService exchangeEventService) {
        this.rateService = rateService;
        this.exchangeEventService = exchangeEventService;
    }

    @Override
    public BigDecimal exchange(String currencyFrom, String currencyTo, BigDecimal amount) throws InvalidInputDataException, ConnectionProblemsException {
        if(amount.doubleValue() < 0)
            throw new InvalidInputDataException("Wartość nie może być ujemna");

        BigDecimal rate = rateService.getRate(currencyFrom, currencyTo);


        BigDecimal exchangeResult = amount.multiply(rate);

        exchangeEventService.saveEvent(
                new ExchangeEvent(
                        Timestamp.from(Instant.now()),
                        currencyFrom,
                        amount,
                        currencyTo,
                        exchangeResult,
                        rate
                )
        );
        return exchangeResult;
    }
}
