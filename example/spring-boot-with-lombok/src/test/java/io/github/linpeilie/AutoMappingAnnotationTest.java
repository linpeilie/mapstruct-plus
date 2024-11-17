package io.github.linpeilie;

import io.github.linpeilie.me.annotation.am.CarCreate;
import io.github.linpeilie.me.annotation.am.CarDTO;
import io.github.linpeilie.me.annotation.am.CarUpdate;
import io.github.linpeilie.me.annotation.ams.CarCreate2;
import io.github.linpeilie.me.annotation.ams.CarDTO2;
import io.github.linpeilie.me.annotation.ams.CarUpdate2;
import io.github.linpeilie.me.annotation.ram.CarCreate1;
import io.github.linpeilie.me.annotation.ram.CarDTO1;
import io.github.linpeilie.me.annotation.ram.CarUpdate1;
import io.github.linpeilie.me.annotation.rams.CarCreate4;
import io.github.linpeilie.me.annotation.rams.CarDTO4;
import io.github.linpeilie.me.annotation.rams.CarUpdate4;
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
    public void autoMappingTest() {
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

    @Test
    public void reverseAutoMappingTest() {
        CarCreate1 carCreate = new CarCreate1();
        carCreate.setName("create car");
        carCreate.setOperatorUserId("create user id....");

        CarDTO1 car1 = converter.convert(carCreate, CarDTO1.class);
        log.info("create user : {}", car1);
        Assert.isTrue(car1.getName().equals("create car"), "create user name is empty");
        Assert.isTrue(car1.getCreateBy().equals("create user id...."), "create user id is empty");

        CarCreate1 carCreate1 = converter.convert(car1, CarCreate1.class);
        Assert.isTrue(carCreate1.getName().equals("create car"), "create user name is empty");
        Assert.isTrue(carCreate1.getOperatorUserId().equals("create user id...."), "create user id is empty");


        CarUpdate1 carUpdate = new CarUpdate1();
        carUpdate.setName("update car");
        carUpdate.setOperatorUserId("update user id....");

        CarDTO1 car2 = converter.convert(carUpdate, CarDTO1.class);
        log.info("update user : {}", car2);
        Assert.isTrue(car2.getName().equals("update car"), "update user name is empty");
        Assert.isTrue(car2.getUpdateBy().equals("update user id...."), "update user id is empty");

        CarUpdate1 updateCar1 = converter.convert(car2, CarUpdate1.class);
        Assert.isTrue(updateCar1.getName().equals("update car"), "update user name is empty");
        Assert.isTrue(updateCar1.getOperatorUserId().equals("update user id...."), "update user id is empty");
    }
    @Test
    public void autoMappingsTest() {
        CarCreate2 carCreate = new CarCreate2();
        carCreate.setName("create car");
        carCreate.setOperatorUserId("create user id....");

        CarDTO2 car1 = converter.convert(carCreate, CarDTO2.class);
        log.info("create user : {}", car1);
        Assert.isTrue(car1.getName().equals("create car"), "create user name is empty");
        Assert.isTrue(car1.getCreateBy().equals("create user id...."), "create user id is empty");

        CarCreate2 carCreate1 = converter.convert(car1, CarCreate2.class);
        Assert.isTrue(carCreate1.getName().equals("create car"), "create user name is empty");
        Assert.isTrue(carCreate1.getOperatorUserId().equals("create user id...."), "create user id is empty");


        CarUpdate2 carUpdate = new CarUpdate2();
        carUpdate.setName("update car");
        carUpdate.setOperatorUserId("update user id....");

        CarDTO2 car2 = converter.convert(carUpdate, CarDTO2.class);
        log.info("update user : {}", car2);
        Assert.isTrue(car2.getName().equals("update car"), "update user name is empty");
        Assert.isTrue(car2.getUpdateBy().equals("update user id...."), "update user id is empty");

        CarUpdate2 updateCar1 = converter.convert(car2, CarUpdate2.class);
        Assert.isTrue(updateCar1.getName().equals("update car"), "update user name is empty");
        Assert.isTrue(updateCar1.getOperatorUserId().equals("update user id...."), "update user id is empty");
    }
    @Test
    public void reverseAutoMappingsTest() {
        CarCreate4 carCreate = new CarCreate4();
        carCreate.setName("create car");
        carCreate.setOperatorUserId("create user id....");

        CarDTO4 car1 = converter.convert(carCreate, CarDTO4.class);
        log.info("create user : {}", car1);
        Assert.isTrue(car1.getName().equals("create car"), "create user name is empty");
        Assert.isTrue(car1.getCreateBy().equals("create user id...."), "create user id is empty");

        CarCreate4 carCreate1 = converter.convert(car1, CarCreate4.class);
        Assert.isTrue(carCreate1.getName().equals("create car"), "create user name is empty");
        Assert.isTrue(carCreate1.getOperatorUserId().equals("create user id...."), "create user id is empty");


        CarUpdate4 carUpdate = new CarUpdate4();
        carUpdate.setName("update car");
        carUpdate.setOperatorUserId("update user id....");

        CarDTO4 car2 = converter.convert(carUpdate, CarDTO4.class);
        log.info("update user : {}", car2);
        Assert.isTrue(car2.getName().equals("update car"), "update user name is empty");
        Assert.isTrue(car2.getUpdateBy().equals("update user id...."), "update user id is empty");

        CarUpdate4 updateCar1 = converter.convert(car2, CarUpdate4.class);
        Assert.isTrue(updateCar1.getName().equals("update car"), "update user name is empty");
        Assert.isTrue(updateCar1.getOperatorUserId().equals("update user id...."), "update user id is empty");
    }


}
