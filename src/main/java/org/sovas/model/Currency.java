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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency1 = (Currency) o;

        if (currency != null ? !currency.equals(currency1.currency) : currency1.currency != null) return false;
        return ratio != null ? ratio.equals(currency1.ratio) : currency1.ratio == null;
    }

    @Override
    public int hashCode() {
        int result = currency != null ? currency.hashCode() : 0;
        result = 31 * result + (ratio != null ? ratio.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currency='" + currency + '\'' +
                ", ratio=" + ratio +
                '}';
    }

}
