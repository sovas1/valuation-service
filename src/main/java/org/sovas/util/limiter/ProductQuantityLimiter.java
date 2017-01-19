package org.sovas.util.limiter;

import org.sovas.cache.MatchingCache;
import org.sovas.model.Matching;
import org.sovas.model.Product;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProductQuantityLimiter {

    public static Set<Product> limitByTopPricedCount(Set<Product> toLimit) {

        Set<Product> limited = new HashSet<>();

        toLimit.forEach(p -> {
            Optional<Matching> optMatchForProduct = MatchingCache.getByMatchingId(p.getMatchingId());
            optMatchForProduct.ifPresent(match -> p.setQuantity(match.getTopPricedCount()));
            limited.add(p);
        });

        return limited;
    }

}
