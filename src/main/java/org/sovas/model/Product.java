package org.sovas.model;

import static java.util.Comparator.comparingDouble;

public class Product {

    // TODO: 18.01.2017 builder

    public static class Comparator {
        public static java.util.Comparator<Product> byTotalPrice =
                comparingDouble(p -> (p.getPrice() * p.getCurrency().getRatio()) * p.getQuantity());
    }

    private Integer id;
    private Integer price;
    private Currency currency;
    private Integer quantity;
    private Integer matchingId;

    public Product() {
    }

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setMatchingId(Integer matchingId) {
        this.matchingId = matchingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (currency != null ? !currency.equals(product.currency) : product.currency != null) return false;
        if (quantity != null ? !quantity.equals(product.quantity) : product.quantity != null) return false;
        return matchingId != null ? matchingId.equals(product.matchingId) : product.matchingId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (matchingId != null ? matchingId.hashCode() : 0);
        return result;
    }
}
