/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.defaultvalue.CountryDts;
import io.github.linpeilie.me.defaultvalue.CountryEntity;
import io.github.linpeilie.me.defaultvalue.Region;
import io.github.linpeilie.me.defaultvalue.other.Continent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class DefaultValueTest {
    @Autowired
    private Converter converter;

    @Test
    /**
     * Checks:
     * <ul>
     *     <li>On code: Using defaultValue without type conversion</li>
     *     <li>On id: Type conversion of the defaultValue (string expr to int)</li>
     *     <li>On name: Using ConstantExpression instead of defaultValue</li>
     *     <li>On zipcode: Ignoring defaultValue on primitive target types</li>
     *     <li>On region: Using defaultValue before the assignment by an intern method (mapToString)</li>
     * </ul>
     */
    public void shouldDefaultValueAndUseConstantExpression() {
        CountryEntity countryEntity = new CountryEntity();

        CountryDts countryDts = converter.convert(countryEntity, CountryDts.class);

        // id is null so it should fall back to the default value
        assertThat(countryDts.getId()).isEqualTo(42);

        // code is null so it should fall back to the default value
        assertThat(countryDts.getCode()).isEqualTo("DE");
        assertThat(countryDts.getZipcode()).isEqualTo(0);
        assertThat(countryDts.getRegion()).isEqualTo("someRegion");
        assertThat(countryDts.getContinent()).isEqualTo(Continent.EUROPE);
    }

    @Test
    public void shouldIgnoreDefaultValue() {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCode("US");
        Region region = new Region();
        region.setCode("foobar");
        countryEntity.setRegion(region);
        countryEntity.setContinent(Continent.NORTH_AMERICA);

        CountryDts countryDts = converter.convert(countryEntity, CountryDts.class);

        // the source entity had a code set, so the default value shouldn't be used
        assertThat(countryDts.getCode()).isEqualTo("US");
        assertThat(countryDts.getRegion()).isEqualTo("foobar");
        assertThat(countryDts.getContinent()).isEqualTo(Continent.NORTH_AMERICA);
    }

    @Test
    public void shouldHandleUpdateMethodsFromDtsToEntity() {
        CountryEntity countryEntity = new CountryEntity();
        CountryDts countryDts = new CountryDts();

        converter.convert(countryDts, countryEntity);

        assertThat(countryEntity.getId()).isEqualTo(0);
        // no code is set, so fall back to default value
        assertThat(countryEntity.getCode()).isEqualTo("DE");
        assertThat(countryEntity.getZipcode()).isEqualTo(0);
        assertThat(countryEntity.getContinent()).isEqualTo(Continent.EUROPE);
    }

}
