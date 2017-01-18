package org.sovas.util.parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sovas.model.Currency;
import org.sovas.model.Matching;
import org.sovas.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvParserTest {

    @Autowired
    private CsvParser parser;

    private List<String> mockCurrencies = Stream.of("GBP,2.4", "EU,2.1", "PLN,1").collect(Collectors.toList());
    private List<String> mockMatchings = Stream.of("1,2", "2,2", "3,3").collect(Collectors.toList());
    private List<String> mockProducts = Stream.of(
            "1,1000,GBP,2,3",
            "2,1050,EU,1,1",
            "3,2000,PLN,1,1"
    ).collect(Collectors.toList());

    @Test
    public void testParseToCurrencies() {
        // given
        final List<Currency> expected = Stream.of(
                new Currency("GBP", 2.4),
                new Currency("EU", 2.1),
                new Currency("PLN", 1.0)
        ).collect(Collectors.toList());

        // when
        List<Currency> actual = parser.toCurrencies(mockCurrencies);

        // then
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    public void testParseToMatchings() {
        // given
        final List<Matching> expected = Stream.of(
                new Matching(1, 2),
                new Matching(2, 2),
                new Matching(3, 3)
        ).collect(Collectors.toList());

        // when
        List<Matching> actual = parser.toMatchings(mockMatchings);

        // then
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    public void testParseToProducts() {
        // given
        final List<Product> expected = Stream.of(
                new Product(1, 1000, new Currency("GBP", 2.4), 2, 3),
                new Product(2, 1050, new Currency("EU", 2.1), 1, 1),
                new Product(3, 2000, new Currency("PLN", 1.0), 1, 1)
        ).collect(Collectors.toList());

        // when
        List<Product> actual = parser.toProducts(mockProducts);

        // then
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

}
