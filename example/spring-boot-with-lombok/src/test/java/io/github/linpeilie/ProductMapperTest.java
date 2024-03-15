package io.github.linpeilie;

import io.github.linpeilie.model.Product;
import io.github.linpeilie.model.ProductDto;
import io.github.linpeilie.model.ProductProperty;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private Converter converter;

    @Test
    public void test() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product");

        ProductProperty productProperty1 = new ProductProperty();
        productProperty1.setId(1L);
        productProperty1.setName("ProductProperty");
        productProperty1.setProduct(product);

        ProductProperty productProperty2 = new ProductProperty();
        productProperty2.setId(1L);
        productProperty2.setName("ProductProperty");
        productProperty2.setProduct(product);

        product.setProductPropertyList(Arrays.asList(productProperty1, productProperty2));

        ProductDto productDto = converter.convert(product, ProductDto.class);

        System.out.println(productDto);
    }

}
