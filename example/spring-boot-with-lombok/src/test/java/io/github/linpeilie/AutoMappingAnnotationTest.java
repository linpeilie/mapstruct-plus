package io.github.linpeilie;

import io.github.linpeilie.me.annotation.CarCreate;
import io.github.linpeilie.me.annotation.CarDTO;
import io.github.linpeilie.me.annotation.CarUpdate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@Slf4j
@SpringBootTest(classes = Application.class)
public class AutoMappingAnnotationTest {

    @Autowired
    private Converter converter;

    @Test
    public void test() {
        CarCreate carCreate = new CarCreate();
        carCreate.setName("create car");
        carCreate.setOperatorUserId("create user id....");

        CarDTO car1 = converter.convert(carCreate, CarDTO.class);
        log.info("create user : {}", car1);
        Assert.isTrue(car1.getName().equals("create car"), "create user name is empty");
        Assert.isTrue(car1.getCreateBy().equals("create user id...."), "create user id is empty");

        CarCreate carCreate1 = converter.convert(car1, CarCreate.class);
        Assert.isTrue(carCreate1.getName().equals("create car"), "create user name is empty");
        Assert.isTrue(carCreate1.getOperatorUserId().equals("create user id...."), "create user id is empty");


        CarUpdate carUpdate = new CarUpdate();
        carUpdate.setName("update car");
        carUpdate.setOperatorUserId("update user id....");

        CarDTO car2 = converter.convert(carUpdate, CarDTO.class);
        log.info("update user : {}", car2);
        Assert.isTrue(car2.getName().equals("update car"), "update user name is empty");
        Assert.isTrue(car2.getUpdateBy().equals("update user id...."), "update user id is empty");

        CarUpdate updateCar1 = converter.convert(car2, CarUpdate.class);
        Assert.isTrue(updateCar1.getName().equals("update car"), "update user name is empty");
        Assert.isTrue(updateCar1.getOperatorUserId().equals("update user id...."), "update user id is empty");



    }


}
