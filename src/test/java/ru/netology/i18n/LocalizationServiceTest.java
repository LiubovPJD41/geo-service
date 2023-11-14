package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static org.testng.AssertJUnit.assertEquals;

public class LocalizationServiceTest {
    @ParameterizedTest
    @MethodSource("messagesForCountries")
    public void localeTest(String expected, Country country) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(country);
        assertEquals(expected, result);
    }

    public static Stream<Arguments> messagesForCountries() {
        String messageInRussian = "Добро пожаловать";
        String messageInEnglish = "Welcome";
        return Stream.of(
                Arguments.of(messageInRussian, Country.RUSSIA),
                Arguments.of(messageInEnglish, Country.GERMANY),
                Arguments.of(messageInEnglish, Country.USA),
                Arguments.of(messageInEnglish, Country.BRAZIL)
        );
    }

}
