package org.sovas.model;

public class Matching {

    private Integer id;
    private Integer topPricedCount;

    public Matching() {
    }

    public Matching(Integer id, Integer topPricedCount) {
        this.id = id;
        this.topPricedCount = topPricedCount;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTopPricedCount() {
        return topPricedCount;
    }
}
