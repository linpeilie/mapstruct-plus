/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import cn.hutool.core.collection.CollectionUtil;
import io.github.linpeilie.me.conversion.currency.CurrencySource;
import io.github.linpeilie.me.conversion.currency.CurrencyTarget;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class CurrencyConversionTest {

    @Autowired
    private Converter converter;

    @Test
    public void shouldApplyCurrencyConversions() {
        final CurrencySource source = new CurrencySource();
        source.setCurrencyA(Currency.getInstance("USD"));
        Set<Currency> currencies = new HashSet<>();
        currencies.add(Currency.getInstance("EUR"));
        currencies.add(Currency.getInstance("CHF"));
        source.setUniqueCurrencies(currencies);

        CurrencyTarget target = converter.convert(source, CurrencyTarget.class);

        assertThat(target).isNotNull();
        assertThat(target.getCurrencyA()).isEqualTo("USD");
        assertThat(target.getUniqueCurrencies()).isNotEmpty().containsExactlyInAnyOrder("EUR", "CHF");
    }

    @Test
    public void shouldApplyReverseConversions() {
        final CurrencyTarget target = new CurrencyTarget();
        target.setCurrencyA("USD");
        target.setUniqueCurrencies(CollectionUtil.newHashSet("JPY"));

        CurrencySource source = converter.convert(target, CurrencySource.class);

        assertThat(source).isNotNull();
        assertThat(source.getCurrencyA().getCurrencyCode()).isEqualTo(Currency.getInstance("USD").getCurrencyCode());
        assertThat(source.getUniqueCurrencies()).containsExactlyInAnyOrder(Currency.getInstance("JPY"));
    }
}
