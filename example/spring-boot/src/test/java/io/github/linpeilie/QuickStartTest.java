package io.github.linpeilie;

import io.github.linpeilie.model.Goods;
import io.github.linpeilie.model.GoodsDto;
import io.github.linpeilie.model.MapModelA;
import io.github.linpeilie.model.User;
import io.github.linpeilie.model.UserDto;
import io.github.linpeilie.model.UserVO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class QuickStartTest {

    @Autowired
    private Converter converter;

    @Test
    public void test() throws ParseException {
        Map<String, Object> mapModel1 = new HashMap<>();
        mapModel1.put("str", "1jkf1ijkj3f");
        mapModel1.put("i1", 111);
        mapModel1.put("l2", 11231);

        Map<String, Object> mapModel2 = new HashMap<>();
        mapModel2.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-23 01:03:23"));

        mapModel1.put("mapModelB", mapModel2);

        final MapModelA mapModelA = converter.convert(mapModel1, MapModelA.class);
        System.out.println(mapModelA);  // MapModelA(str=1jkf1ijkj3f, i1=111, l2=11231, mapModelB=MapModelB(date=2023-02-23 01:03:23))
    }


    @Test
    public void ueseTest() {
        UserDto userDto = new UserDto();
        userDto.setEducations("1,2,3");

        final User user = converter.convert(userDto, User.class);
        System.out.println(user.getEducationList());    // [1, 2, 3]

        assert user.getEducationList().size() == 3;
    }

    @Test
    public void numberFormatTest() {
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setPrice(9);

        final Goods goods = converter.convert(goodsDto, Goods.class);

        System.out.println(goods.getPrice());   // $9.00

        assert "$9.00".equals(goods.getPrice());
    }

    @Test
    public void dateFormatTest() throws ParseException {
        final GoodsDto goodsDto = new GoodsDto();

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String dateString = "2023-02-23 23:27:37";
        final Date date = simpleDateFormat.parse(dateString);

        goodsDto.setTakeDownTime(date);

        final Goods goods = converter.convert(goodsDto, Goods.class);

        System.out.println(goods.getTakeDownTime());    // 2023-02-23 23:27:37

        assert dateString.equals(goods.getTakeDownTime());
    }

    @Test
    public void expressionFormatTest() {
        User user = new User();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        user.setEducationList(list);

        final UserDto userDto = converter.convert(user, UserDto.class);
        System.out.println(userDto.getEducations());

        assert "1,2,3".equals(userDto.getEducations());
    }

    @Test
    public void multiClassConvertTest() throws ParseException {
        User user = new User();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        user.setEducationList(list);

        user.setUsername("Nick");
        user.setAge(12);
        user.setYoung(true);
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-23 02:01:43"));
        user.setAssets(123.234);
        user.setVoField("vofieldfff");
        user.setMoney(12543.123);

        final UserVO userVo = converter.convert(user, UserVO.class);
        System.out.println(userVo);
        assert user.getUsername().equals(userVo.getUsername());
        assert user.getAge() == userVo.getAge();
        assert user.isYoung() == userVo.isYoung();
        assert userVo.getBirthday() == null;
        assert user.getAssets() == userVo.getAssets();
        assert user.getVoField().equals(userVo.getVoField());
        assert userVo.getMoney().equals("$12543.12");

        final UserDto userDto = converter.convert(user, UserDto.class);
        System.out.println(userDto);

        assert user.getUsername().equals(userDto.getUsername());
        assert user.getAge() == userDto.getAge();
        assert user.isYoung() == userDto.isYoung();
        assert userDto.getEducations().equals("1,2,3");
        assert userDto.getBirthday().equals("2023-02-23 02:01:43");
        assert userDto.getAssets().equals("$123.23");
        assert userDto.getMoney().equals("$12543.12");
    }

}
