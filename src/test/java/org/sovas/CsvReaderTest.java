package org.sovas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sovas.util.io.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CsvReaderTest {

    @Autowired
    private CsvReader reader;

    private final String PATH_CURRENCIES = "currencies (1) (3) (2).csv";

    @Test
    public void test_Read_Csv_Currencies_GBP_EU_PLN() {
        List<String> actual = reader.readLines(PATH_CURRENCIES);

        assertNotNull(actual);

        final int expectedSize = 3;
        assertEquals(expectedSize, actual.size());

        List<String> expected = Stream.of("GBP,2.4", "EU,2.1", "PLN,1").collect(Collectors.toList());

        IntStream.range(0, expectedSize).forEach(i -> assertEquals(expected.get(i), actual.get(i)));
    }

    @Test(expected = IOException.class)
    public void test_Read_Csv_expect_IO_Exception() {
        reader.readLines("wrong/path");
    }

}

