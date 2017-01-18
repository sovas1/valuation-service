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
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvParserTest {

    @Autowired
    private CsvParser parser;

    @MockBean
    private CsvFileUtils fileUtils;

    private List<String> currencies = Stream.of("GBP,2.4", "EU,2.1", "PLN,1").collect(Collectors.toList());

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseToCurrencies() {
        given(fileUtils.readLines("currencies")).willReturn(currencies);
        List<String> linesMock = fileUtils.readLines("currencies");

        List<Currency> currencies = parser.toCurrencies(linesMock);

        System.out.println();
    }

}
