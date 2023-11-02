package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class MessageSenderTest {

   @Test
    public void messageFromRussia(){
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.contains("172.")))
                .thenReturn(new Location(null, Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.contains("96.")))
                .thenReturn(new Location(null,Country.USA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        //Mockito.when(localizationService.locale(Country.USA))
               // .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.0.0.0");
        String testResult = messageSender.send(headers);
        Assertions.assertEquals("Добро пожаловать", testResult);
   }

    @Test
    public void messageFromUsa(){
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.contains("172.")))
                .thenReturn(new Location(null, Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.contains("96.")))
                .thenReturn(new Location(null,Country.USA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
       // Mockito.when(localizationService.locale(Country.RUSSIA))
          //      .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.0.0.0.0");
        String testResult = messageSender.send(headers);
        Assertions.assertEquals("Welcome", testResult);

    }
}
