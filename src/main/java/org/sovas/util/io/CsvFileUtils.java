package org.sovas.util.io;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvFileUtils {

    public List<String> readLines(final String path) {
        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path))
        )) {

            data = reader.lines()
                    .skip(1)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            // TODO: 16.01.2017 logger
            e.printStackTrace();
        }

        return data;
    }

}
