package org.sovas.util.parser;

import org.sovas.cache.CurrencyCache;
import org.sovas.cache.MatchingCache;
import org.sovas.cache.ProductCache;
import org.sovas.model.Currency;
import org.sovas.model.Matching;
import org.sovas.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CsvParser {

    public List<Currency> toCurrencies(List<String> lines) {
        List<Currency> currencies = lines.stream()
                .map(CsvMapper.toCurrencies())
                .collect(toList());
        CurrencyCache.setCurrencies(currencies);
        return currencies;
    }

    public List<Matching> toMatchings(List<String> lines) {
        List<Matching> matchings = lines.stream()
                .map(CsvMapper.toMatchings())
                .collect(toList());
        MatchingCache.setMatchings(matchings);
        return matchings;
    }

    public List<Product> toProducts(List<String> lines) {
        List<Product> products = lines.stream()
                .map(CsvMapper.toProducts())
                .collect(toList());
        ProductCache.setProducts(products);
        return products;
    }

}
