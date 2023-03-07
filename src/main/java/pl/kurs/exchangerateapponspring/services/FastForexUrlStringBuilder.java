package pl.kurs.exchangerateapponspring.services;

import org.springframework.stereotype.Service;
import pl.kurs.exchangerateapp.config.AppConfig;

@Service
public class FastForexUrlStringBuilder implements IUrlStringBuilder{


    @Override
    public String buildString(String currencyFrom) {
        return AppConfig.API_PAGE + AppConfig.ENDPOINT + currencyFrom + AppConfig.API_PRIVATE_KEY;
    }
}
