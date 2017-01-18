package org.sovas.util.io;

import com.google.common.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sovas.Application;
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
public class CsvFileUtilsTest {

    @Autowired
    private CsvFileUtils fileUtils;

    @Test
    public void test_Read_Csv_Currencies_GBP_EU_PLN() {
        // given
        String path = Resources.getResource("currencies.csv").getFile();
        List<String> expected = Stream.of("GBP,2.4", "EU,2.1", "PLN,1").collect(Collectors.toList());

        // when
        List<String> actual = fileUtils.readLines(path);

        // then
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    public void test_Read_Csv_expect_Empty_List_when_Path_is_Wrong_Exception() {
        // given
        String wrongPath = "wrong/path";

        // when
        List<String> actual = fileUtils.readLines(wrongPath);

        // then
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

}

