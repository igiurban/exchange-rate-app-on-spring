package pl.kurs.exchangerateapponspring.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
public class ExchangeEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Timestamp date;
    private String currencyFrom;
    private BigDecimal amountFrom;
    private String currencyTo;
    private BigDecimal amountTo;
    private BigDecimal exchangeRate;

    public ExchangeEvent() {
    }

    public ExchangeEvent(Timestamp date, String currencyFrom, BigDecimal amountFrom, String currencyTo, BigDecimal amountTo, BigDecimal exchangeRate) {
        this.date = date;
        this.currencyFrom = currencyFrom;
        this.amountFrom = amountFrom;
        this.currencyTo = currencyTo;
        this.amountTo = amountTo;
        this.exchangeRate = exchangeRate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }
}
