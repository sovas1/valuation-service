package acceptance;

import com.google.common.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sovas.Application;
import org.sovas.model.Currency;
import org.sovas.model.Matching;
import org.sovas.model.Product;
import org.sovas.util.finder.ProductFinder;
import org.sovas.util.io.CsvFileUtils;
import org.sovas.util.limiter.ProductQuantityLimiter;
import org.sovas.util.parser.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.sovas.model.Product.Comparator.byTotalPrice;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReadParseFindLimitTest {

    //      Now, read all the data.
    //      From products with particular matching_id take those with the highest total price (price * quantity),
    //      limit data set by top_priced_count and aggregate prices.
    //      Result save to top_products.csv with four columns: matching_id,total_price,avg_price,currency,ignored_products_count.

    @Autowired
    private CsvParser parser;

    @Autowired
    private CsvFileUtils fileUtils;

    @Test
    public void read_and_Parse_and_Find_by_Highest_Total_Price_then_Limit_and_Aggregate() {
        // -- given
        String pathToCurrencies = Resources.getResource("currencies.csv").getFile();
        String pathToMatchings = Resources.getResource("matchings.csv").getFile();
        String pathToProducts = Resources.getResource("data.csv").getFile();
        Set<Product> expected = Stream.of(
                new Product(5, 1400, new Currency("EU", 2.1), 3, 3),
                new Product(9, 1400, new Currency("GBP", 2.4), 2, 1),
                new Product(6, 7000, new Currency("PLN", 1.0), 2, 2)
        ).collect(Collectors.toSet());


        // -- when
        // read from file
        final List<String> currenciesLines = fileUtils.readLines(pathToCurrencies);
        final List<String> matchingsLines = fileUtils.readLines(pathToMatchings);
        final List<String> productsLines = fileUtils.readLines(pathToProducts);

        // map to objects
        final List<Currency> currencies = parser.toCurrencies(currenciesLines);
        final List<Matching> matchings = parser.toMatchings(matchingsLines);
        final List<Product> products = parser.toProducts(productsLines);

        // group by matching id & total price
        Set<Product> mostExpensives = ProductFinder.groupedByMatchingId(products, byTotalPrice);

        // limit data set by top priced count
        Set<Product> productsWithQuantLimited = ProductQuantityLimiter.limitByTopPricedCount(mostExpensives);


        // -- then
        assertNotNull(productsWithQuantLimited);
        assertEquals(expected.size(), productsWithQuantLimited.size());
        assertEquals(expected, productsWithQuantLimited);

        // TODO: 19.01.2017
        // Result save to top_products.csv with four columns: matching_id,total_price,avg_price,currency,ignored_products_count.
    }

}
