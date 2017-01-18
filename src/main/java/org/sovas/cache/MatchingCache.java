package org.sovas.cache;

import org.sovas.model.Matching;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by masa on 2017-01-12.
 */
public class MatchingCache {

    private static List<Matching> matchings = new ArrayList<>();

    public static List<Matching> getMatchings() {
        return matchings;
    }

    public static void setMatchings(List<Matching> matchings) {
        MatchingCache.matchings = matchings;
    }

    public static Optional<Matching> getByMatchingId(Integer matchingId) {
        return matchings.stream()
                .filter(e -> e.getId().equals(matchingId))
                .findFirst();
    }

}
