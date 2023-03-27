package io.github.linpeilie;

import io.github.linpeilie.model.Goods;
import io.github.linpeilie.model.GoodsDto;
import io.github.linpeilie.model.GoodsStateEnum;
import io.github.linpeilie.model.User;
import io.github.linpeilie.model.UserDto;

public class QuickStart {

    private static Converter converter = new Converter();

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("jack");
        user.setAge(23);
        user.setYoung(false);

        UserDto userDto = converter.convert(user, UserDto.class);
        System.out.println(userDto);    // UserDto{username='jack', age=23, young=false}
    }


}