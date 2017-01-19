package org.sovas.util.finder;


import org.sovas.model.Product;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class ProductFinder {

    public static Set<Product> groupedByMatchingId(final List<Product> products, Comparator<Product> by) {

        Collection<List<Product>> groupedProducts = products.stream()
                .collect(Collectors.groupingBy(Product::getMatchingId))
                .values();

        return groupedProducts.stream().map(
                list -> list.parallelStream().max(by).get()
        ).collect(toSet());

    }

}
