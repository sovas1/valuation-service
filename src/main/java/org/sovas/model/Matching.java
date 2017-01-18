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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matching matching = (Matching) o;

        if (id != null ? !id.equals(matching.id) : matching.id != null) return false;
        return topPricedCount != null ? topPricedCount.equals(matching.topPricedCount) : matching.topPricedCount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (topPricedCount != null ? topPricedCount.hashCode() : 0);
        return result;
    }
}
