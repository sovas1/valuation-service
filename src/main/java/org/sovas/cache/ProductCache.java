package org.sovas.cache;

import org.sovas.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masa on 2017-01-12.
 */
public class ProductCache {

    private static List<Product> products = new ArrayList<>();

    public static List<Product> getProducts() {
        return products;
    }

    public static void setProducts(List<Product> products) {
        ProductCache.products = products;
    }

}
