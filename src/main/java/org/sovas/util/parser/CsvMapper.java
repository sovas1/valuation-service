package org.sovas.util.parser;

import org.sovas.exception.CurrencyNotFoundException;
import org.sovas.model.Currency;
import org.sovas.model.Matching;
import org.sovas.model.Product;
import org.sovas.util.matcher.CurrencyMatcher;

import java.util.function.Function;

public class CsvMapper {

    public static Function<String, Currency> toCurrencies() {
        return  (line) -> {
            String[] p = line.split(",");
            return new Currency(p[0], Double.parseDouble(p[1]));
        };
    }

    public static Function<String, Matching> toMatchings() {
        return  (line) -> {
            String[] p = line.split(",");
            return new Matching(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
        };
    }

    public static Function<String, Product> toProducts() {
        return  (String line) -> {

            String[] param = line.split(",");

            Integer id = Integer.valueOf(param[0]);
            Integer price = Integer.valueOf(param[1]);

            // currency matching
            Double ratio = null;
            try {
                ratio = CurrencyMatcher.matchRatio(param[2]);
            } catch (CurrencyNotFoundException e) {
                // TODO: 18.01.2017 logger
                e.printStackTrace();
            }

            Currency currency = new Currency(param[2], ratio);

            Integer quantity = Integer.valueOf(param[3]);
            Integer matchingId = Integer.valueOf(param[4]);

            return new Product(id, price, currency, quantity, matchingId);
        };
    }

}
