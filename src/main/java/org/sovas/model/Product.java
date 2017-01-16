package org.sovas.model;

import static java.util.Comparator.comparingDouble;

public class Product {

    public static class Comparator {
        public static java.util.Comparator<Product> byTotalPrice =
                comparingDouble(p -> (p.getPrice() * p.getCurrency().getRatio()) * p.getQuantity());
    }

    private Integer id;
    private Integer price;
    private Currency currency;
    private Integer quantity;
    private Integer matchingId;

    public Product(Integer id, Integer price, Currency currency, Integer quantity, Integer matchingId) {
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.quantity = quantity;
        this.matchingId = matchingId;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Integer getMatchingId() {
        return matchingId;
    }

    public Integer getId() {
        return id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
