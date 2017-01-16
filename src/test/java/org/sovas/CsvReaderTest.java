package org.sovas;

import com.google.common.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sovas.util.io.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CsvReaderTest {

    @Autowired
    private CsvReader reader;

    private final String PATH_CURRENCIES = Resources.getResource("currencies.csv").getFile();

    @Test
    public void test_Read_Csv_Currencies_GBP_EU_PLN() {
        List<String> actual = reader.readLines(PATH_CURRENCIES);

        assertNotNull(actual);

        final int expectedSize = 3;
        assertEquals(expectedSize, actual.size());

        List<String> expected = Stream.of("GBP,2.4", "EU,2.1", "PLN,1").collect(Collectors.toList());

        IntStream.range(0, expectedSize).forEach(i -> assertEquals(expected.get(i), actual.get(i)));
    }

    @Test
    public void test_Read_Csv_expect_Empty_List_when_Path_is_Wrong_Exception() {
        List<String> actual = reader.readLines("wrong/path");
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

}

