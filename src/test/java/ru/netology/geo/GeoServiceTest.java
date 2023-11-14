package ru.netology.geo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.testng.AssertJUnit.assertEquals;

public class GeoServiceTest {

    @ParameterizedTest
    @MethodSource("locationByIp")
    void testByIp(String ip, Location expected) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(ip);
        assertEquals(expected.getCountry().toString(), location.getCountry().toString());
    }

    private static Stream<Arguments> locationByIp() {
        Location localhost = new Location(null, null, null, 0);
        Location usaAddress = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location russiaAddress = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location russia = new Location("Moscow", Country.RUSSIA, null, 0);
        Location usa = new Location("New York", Country.USA, null, 0);
        return Stream.of(Arguments.of("172.123.12.19", russia),
                Arguments.of("96.125.32.45", usa),
                Arguments.of("172.56.47.26", russia),
                Arguments.of("172.95.123.56", russia),
                Arguments.of("96.81.10.23", usa),
                Arguments.of("96.0.0.5", usa),
                Arguments.of("127.0.0.1", localhost),
                Arguments.of("96.44.183.149", usaAddress),
                Arguments.of("172.0.32.11", russiaAddress));
    }
}
