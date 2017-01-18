package org.sovas.model;

public class Currency {

    private String currency;
    private Double ratio;

    public Currency() {
    }

    public Currency(String currency, Double ratio) {
        this.currency = currency;
        this.ratio = ratio;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getRatio() {
        return ratio;
    }

}
