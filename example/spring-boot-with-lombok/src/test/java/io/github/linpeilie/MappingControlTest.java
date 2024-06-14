/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie;

import io.github.linpeilie.me.mappingcontrol.CoolBeerDTO;
import io.github.linpeilie.me.mappingcontrol.CustomerDto;
import io.github.linpeilie.me.mappingcontrol.FridgeDTO;
import io.github.linpeilie.me.mappingcontrol.OrderItemDto;
import io.github.linpeilie.me.mappingcontrol.OrderItemKeyDto;
import io.github.linpeilie.me.mappingcontrol.ShelveDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest(classes = Application.class)
public class MappingControlTest {

    @Autowired
    private Converter converter;

    /**
     * Test the deep cloning annotation
     */
    @Test
    public void testDeepCloning() {

        FridgeDTO in = createFridgeDTO();
        FridgeDTO out = converter.convert(in, FridgeDTO.class);

        assertThat(out).isNotNull();
        assertThat(out.getShelve()).isNotNull();
        assertThat(out.getShelve()).isNotSameAs(in.getShelve());
        assertThat(out.getShelve().getCoolBeer()).isNotSameAs(in.getShelve().getCoolBeer());
        assertThat(out.getShelve().getCoolBeer().getBeerCount()).isEqualTo("5");
    }

    @Test
    public void testDeepCloningViaBeanMapping() {

        FridgeDTO in = createFridgeDTO();
        FridgeDTO out = converter.convert(in, FridgeDTO.class);

        assertThat(out).isNotNull();
        assertThat(out.getShelve()).isNotNull();
        assertThat(out.getShelve()).isNotSameAs(in.getShelve());
        assertThat(out.getShelve().getCoolBeer()).isNotSameAs(in.getShelve().getCoolBeer());
        assertThat(out.getShelve().getCoolBeer().getBeerCount()).isEqualTo("5");
    }

    /**
     * Test the deep cloning annotation with lists
     */
    @Test
    public void testDeepCloningListsAndMaps() {

        CustomerDto in = new CustomerDto();
        in.setId(10L);
        in.setCustomerName("Jaques");
        OrderItemDto order1 = new OrderItemDto();
        order1.setName("Table");
        order1.setQuantity(2L);
        in.setOrders(new ArrayList<>(Collections.singleton(order1)));
        OrderItemKeyDto key = new OrderItemKeyDto();
        key.setStockNumber(5);
        Map<OrderItemKeyDto, OrderItemDto> stock = new HashMap<>();
        stock.put(key, order1);
        in.setStock(stock);

        CustomerDto out = converter.convert(in, CustomerDto.class);

        assertThat(out.getId()).isEqualTo(10);
        assertThat(out.getCustomerName()).isEqualTo("Jaques");
        assertThat(out.getOrders())
            .extracting("name", "quantity")
            .containsExactly(tuple("Table", 2L));
        assertThat(out.getStock()).isNotNull();
        assertThat(out.getStock()).hasSize(1);

        Map.Entry<OrderItemKeyDto, OrderItemDto> entry = out.getStock().entrySet().iterator().next();
        assertThat(entry.getKey().getStockNumber()).isEqualTo(5);
        assertThat(entry.getValue().getName()).isEqualTo("Table");
        assertThat(entry.getValue().getQuantity()).isEqualTo(2L);

        // check mapper really created new objects
        assertThat(out).isNotSameAs(in);
        assertThat(out.getOrders().get(0)).isNotSameAs(order1);
        assertThat(entry.getKey()).isNotSameAs(key);
        assertThat(entry.getValue()).isNotSameAs(order1);
        assertThat(entry.getValue()).isNotSameAs(out.getOrders().get(0));
    }

    private FridgeDTO createFridgeDTO() {
        FridgeDTO fridgeDTO = new FridgeDTO();
        ShelveDTO shelveDTO = new ShelveDTO();
        CoolBeerDTO coolBeerDTO = new CoolBeerDTO();
        fridgeDTO.setShelve(shelveDTO);
        shelveDTO.setCoolBeer(coolBeerDTO);
        coolBeerDTO.setBeerCount("5");
        return fridgeDTO;
    }
}
