package pl.kurs.exchangerateapponspring.services;

import pl.kurs.exchangerateapp.exceptions.ConnectionProblemsException;
import pl.kurs.exchangerateapp.exceptions.InvalidInputDataException;

import java.math.BigDecimal;

public interface ICurrencyService {

    BigDecimal exchange(String currencyFrom, String currencyTo, BigDecimal amount) throws InvalidInputDataException, ConnectionProblemsException;

}
