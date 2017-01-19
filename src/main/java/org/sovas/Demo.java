package org.sovas;

import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sovas.model.Currency;
import org.sovas.model.Matching;
import org.sovas.model.Product;
import org.sovas.util.finder.ProductFinder;
import org.sovas.util.io.CsvFileUtils;
import org.sovas.util.limiter.ProductQuantityLimiter;
import org.sovas.util.parser.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static org.sovas.model.Product.Comparator.byTotalPrice;

@Component
public class Demo {

    @Autowired
    private CsvParser parser;

    @Autowired
    private CsvFileUtils fileUtils;

    private String lineSeparator = "------------------------------------------";

    private Logger logger = LoggerFactory.getLogger(Demo.class);

    private Scanner scanner = new Scanner(System.in);
    private String path;


    public void play() {
        askForResources();

        logger.info("Opening files ...");
        logger.info(lineSeparator);
        final List<String> currenciesLines = fileUtils.readLines(path + "/currencies.csv");
        final List<String> matchingsLines = fileUtils.readLines(path + "/matchings.csv");
        final List<String> productsLines = fileUtils.readLines(path + "/data.csv");

        logger.info("Mapping to objects ...");
        logger.info(lineSeparator);
        final List<Currency> currencies = parser.toCurrencies(currenciesLines);
        final List<Matching> matchings = parser.toMatchings(matchingsLines);
        final List<Product> products = parser.toProducts(productsLines);

        logger.info("Grouping by matching id & total price ...");
        logger.info(lineSeparator);
        Set<Product> mostExpensives = ProductFinder.groupedByMatchingId(products, byTotalPrice);

        logger.info("Limiting data set by top priced count ...");
        logger.info(lineSeparator);
        Set<Product> productsWithQuantLimited = ProductQuantityLimiter.limitByTopPricedCount(mostExpensives);
        if(!productsWithQuantLimited.isEmpty()) {
            displayProducts(productsWithQuantLimited);
        }

    }

    private void displayProducts(Set<Product> products) {
        logger.debug("Success!");
        logger.info(lineSeparator);
        logger.debug("Limited result set:");
        logger.debug(lineSeparator);
        products.forEach(e -> logger.debug(e.toString()));
        logger.debug(lineSeparator);
    }

    private void askForResources() {
        logger.info(lineSeparator);
        logger.info("Paste path to resources:");

        path = scanner.nextLine();

        logger.info(lineSeparator);
        logger.info("Searching for files ...");
    }

}
