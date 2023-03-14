package pl.kurs.exchangerateapponspring.services;

import pl.kurs.exchangerateapponspring.exceptions.ConnectionProblemsException;
import pl.kurs.exchangerateapponspring.exceptions.InvalidInputDataException;

import java.math.BigDecimal;

public interface IRateService {

    BigDecimal getRate(String currencyFrom, String currencyTo) throws InvalidInputDataException, ConnectionProblemsException;
}
