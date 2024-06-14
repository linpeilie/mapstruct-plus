package io.github.linpeilie.me.conditional.qualifier;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import io.github.linpeilie.annotations.AutoMappings;

@AutoMappers({
    @AutoMapper(target = Order.class, reverseConvertGenerate = false, uses = ConditionalMethodWithSourceToTargetMapper.class),
    @AutoMapper(target = Customer.class, reverseConvertGenerate = false, uses = ConditionalMethodWithSourceToTargetMapper.class),
    @AutoMapper(target = Address.class, reverseConvertGenerate = false, uses = ConditionalMethodWithSourceToTargetMapper.class)})
public class OrderDTO {
    @AutoMappings({
        @AutoMapping(targetClass = Order.class, target = "customer", source = "source", conditionQualifiedByName = "mapCustomerFromOrder"),
        @AutoMapping(targetClass = Customer.class, target = "name"),
        @AutoMapping(targetClass = Customer.class, target = "address", source = "source", conditionQualifiedByName = "mapAddressFromOrder"),
    })
    private String customerName;
    private String line1;
    private String line2;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }
}
