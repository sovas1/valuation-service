package org.sovas.util.parser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sovas.Application;
import org.sovas.model.Currency;
import org.sovas.model.Matching;
import org.sovas.model.Product;
import org.sovas.util.io.CsvFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvParserTest {

    // TODO: 18.01.2017 deeper tests

    @Autowired
    private CsvParser parser;

    @MockBean
    private CsvFileUtils fileUtils;

    private List<String> currencies = Stream.of("GBP,2.4", "EU,2.1", "PLN,1").collect(Collectors.toList());
    private List<String> matchings = Stream.of("1,2", "2,2", "3,3").collect(Collectors.toList());
    private List<String> products = Stream.of(
            "1,1000,GBP,2,3",
            "2,1050,EU,1,1",
            "3,2000,PLN,1,1",
            "4,1750,EU,2,2",
            "5,1400,EU,4,3",
            "6,7000,PLN,3,2",
            "7,630,GBP,5,3",
            "8,4000,EU,1,3",
            "9,1400,GBP,3,1")
            .collect(Collectors.toList());


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testParseToCurrencies() {
        // given
        given(fileUtils.readLines("currencies")).willReturn(currencies);
        List<String> linesMock = fileUtils.readLines("currencies");

        // when
        List<Currency> currencies = parser.toCurrencies(linesMock);

        // then
        assertNotNull(currencies);
        assertEquals(3, currencies.size());
        assertTrue(currencies.stream().anyMatch(e -> e.getCurrency().equals("GBP")));
    }

    @Test
    public void testParseToMatchings() {
        // given
        given(fileUtils.readLines("matchings")).willReturn(matchings);
        List<String> linesMock = fileUtils.readLines("matchings");

        // when
        List<Matching> matchings = parser.toMatchings(linesMock);

        // then
        assertNotNull(matchings);
        assertEquals(3, matchings.size());
        // TODO: 18.01.2017 assert value
        IntStream.range(0,3)
                .forEach(i -> assertEquals(i, matchings.get(i).getId().intValue()));
    }

    @Test
    public void testParseToProducts() {
        // given
        given(fileUtils.readLines("products")).willReturn(products);
        List<String> linesMock = fileUtils.readLines("products");

        // when
        List<Product> products = parser.toProducts(linesMock);

        // then
        assertNotNull(products);
        assertEquals(9, products.size());
        // TODO: 18.01.2017 deeper tests
    }



}
