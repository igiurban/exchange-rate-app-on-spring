package pl.kurs.exchangerateapponspring.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.kurs.exchangerateapponspring.exceptions.ConnectionProblemsException;
import pl.kurs.exchangerateapponspring.exceptions.InvalidInputDataException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Objects;

@Service
public class FastForexRateService implements IRateService{

    private final ObjectMapper mapper;
    private final IUrlStringBuilder urlStringBuilder;

    public FastForexRateService(ObjectMapper mapper, IUrlStringBuilder urlStringBuilder) {
        this.mapper = mapper;
        this.urlStringBuilder = urlStringBuilder;
    }

    @Override
    public BigDecimal getRate(String currencyFrom, String currencyTo) throws InvalidInputDataException, ConnectionProblemsException {
        String preparedUrl = urlStringBuilder.buildString(currencyFrom);


        JsonNode mainNode = null;
        try {
            mainNode = mapper.readTree(new URL(preparedUrl));
        } catch (UnknownHostException | MalformedURLException e) {
            throw new ConnectionProblemsException("Problemy z siecią, spróbuj ponownie!", e);
        } catch (IOException e) {
            throw new InvalidInputDataException("Przekazano błędne dane", e);
        }

        JsonNode resultsNode = mainNode.get("results");
        JsonNode specificRateNode = resultsNode.get(currencyTo);

        if (Objects.isNull(specificRateNode))
            throw new InvalidInputDataException("Błąd API");

        BigDecimal specificRate = new BigDecimal(specificRateNode.asText());

        return specificRate;

    }
}
