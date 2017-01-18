package org.sovas.util.matcher;

import org.sovas.cache.CurrencyCache;
import org.sovas.exception.CurrencyNotFoundException;
import org.sovas.model.Currency;

public class CurrencyMatcher {

    public static Double matchRatio(final String currency) throws CurrencyNotFoundException {
        Currency cur = CurrencyCache.getCurrencies().stream()
                .filter(e -> e.getCurrency().equals(currency))
                .findFirst()
                .orElseThrow(() -> new CurrencyNotFoundException(currency));
        return cur.getRatio();
    }

}
