package io.github.linpeilie;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import io.github.linpeilie.me.array.Scientist;
import io.github.linpeilie.me.array.ScientistDto;
import io.github.linpeilie.me.bool.Person;
import io.github.linpeilie.me.bool.PersonDto;
import io.github.linpeilie.me.bool.YesNo;
import io.github.linpeilie.me.builder.abstractBuilder.ImmutableProduct;
import io.github.linpeilie.me.builder.abstractBuilder.ProductDto;
import io.github.linpeilie.me.builder.abstractGenericTarget.ChildSource;
import io.github.linpeilie.me.builder.abstractGenericTarget.ImmutableChild;
import io.github.linpeilie.me.builder.abstractGenericTarget.ImmutableParent;
import io.github.linpeilie.me.builder.abstractGenericTarget.MutableParent;
import io.github.linpeilie.me.builder.abstractGenericTarget.Parent;
import io.github.linpeilie.me.builder.abstractGenericTarget.ParentSource;
import io.github.linpeilie.me.builder.factory.ImplicitPerson;
import io.github.linpeilie.me.callbacks.ongeneratedmethods.Address;
import io.github.linpeilie.me.callbacks.ongeneratedmethods.Company;
import io.github.linpeilie.me.callbacks.ongeneratedmethods.CompanyDto;
import io.github.linpeilie.me.callbacks.ongeneratedmethods.Employee;
import io.github.linpeilie.me.callbacks.typematching.CarDto;
import io.github.linpeilie.me.callbacks.typematching.CarEntity;
import io.github.linpeilie.me.callbacks.typematching.CarMapper;
import io.github.linpeilie.me.field_mapping.Customer;
import io.github.linpeilie.me.field_mapping.CustomerDto;
import io.github.linpeilie.me.field_mapping.OrderItem;
import io.github.linpeilie.me.field_mapping.OrderItemDto;
import io.github.linpeilie.me.iterable_to_non_iterable.Source;
import io.github.linpeilie.me.iterable_to_non_iterable.Target;
import io.github.linpeilie.me.nested_bean_mappings.dto.FishTankDto;
import io.github.linpeilie.me.nested_bean_mappings.dto.FishTankDto1;
import io.github.linpeilie.me.nested_bean_mappings.dto.FishTankDto2;
import io.github.linpeilie.me.nested_bean_mappings.dto.FishTankWithNestedDocumentDto;
import io.github.linpeilie.me.nested_bean_mappings.model.Fish;
import io.github.linpeilie.me.nested_bean_mappings.model.FishTank;
import io.github.linpeilie.me.nested_bean_mappings.model.Interior;
import io.github.linpeilie.me.nested_bean_mappings.model.MaterialType;
import io.github.linpeilie.me.nested_bean_mappings.model.Ornament;
import io.github.linpeilie.me.nested_bean_mappings.model.WaterPlant;
import io.github.linpeilie.me.nested_bean_mappings.model.WaterQuality;
import io.github.linpeilie.me.nested_bean_mappings.model.WaterQualityReport;
import io.github.linpeilie.model.Car;
import io.github.linpeilie.model.EnglishRelease;
import io.github.linpeilie.model.FrenchRelease;
import io.github.linpeilie.model.Goods;
import io.github.linpeilie.model.GoodsDto;
import io.github.linpeilie.model.GoodsStateEnum;
import io.github.linpeilie.model.GoodsVo;
import io.github.linpeilie.model.InnerClassTarget;
import io.github.linpeilie.model.MapModelA;
import io.github.linpeilie.model.Order;
import io.github.linpeilie.model.OrderVO;
import io.github.linpeilie.model.SDto;
import io.github.linpeilie.model.SVO;
import io.github.linpeilie.model.Sku;
import io.github.linpeilie.model.SysMenu;
import io.github.linpeilie.model.SysMenuVo;
import io.github.linpeilie.model.TreeNode;
import io.github.linpeilie.model.TreeNodeDto;
import io.github.linpeilie.model.User;
import io.github.linpeilie.model.UserDto;
import io.github.linpeilie.model.UserVO;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
public class QuickStartTest {

    @Autowired
    private Converter converter;

    @Test
    public void test() {
        Map<String, Object> mapModel1 = new HashMap<>();
        mapModel1.put("str", "1jkf1ijkj3f");
        mapModel1.put("i1", 111);
        mapModel1.put("l2", 11231);

        Map<String, Object> mapModel2 = new HashMap<>();
        mapModel2.put("date", DateUtil.parse("2023-02-23 01:03:23"));

        mapModel1.put("mapModelB", mapModel2);

        final MapModelA mapModelA = converter.convert(mapModel1, MapModelA.class);
        System.out.println(
            mapModelA);  // MapModelA(str=1jkf1ijkj3f, i1=111, l2=11231, mapModelB=MapModelB(date=2023-02-23 01:03:23))
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
    public void multiClassConvertTest() {
        User user = new User();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        user.setEducationList(list);

        user.setUsername("Nick");
        user.setAge(12);
        user.setYoung(true);
        user.setBirthday(DateUtil.parseDateTime("2023-02-23 02:01:43"));
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

    @Test
    public void treeTest() {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setPath("/api");

        final SysMenu role = new SysMenu();
        role.setPath("/role");

        final SysMenu user = new SysMenu();

        sysMenu.setChildren(Arrays.asList(role, user));

        final SysMenuVo sysMenuVo = converter.convert(sysMenu, SysMenuVo.class);
        System.out.println(sysMenuVo);
    }

    @Test
    public void sourceTest() {
        Goods goods = new Goods();
        Sku sku = new Sku();
        sku.setPrice(134);
        goods.setSku(sku);

        final GoodsVo goodsVo = converter.convert(goods, GoodsVo.class);

        final GoodsVo newGoodsVo = new GoodsVo();
        converter.convert(goods, newGoodsVo);
        System.out.println(goodsVo);
    }

    @Test
    public void reverseMappingTest() {
        Order order = new Order();
        order.setOrderId("394bcab38052404ab404c791cb975596");
        order.setGoods(Arrays.asList("Apple", "Phone"));
        order.setOrderPrice(BigDecimal.valueOf(123.3421));
        order.setGoodsNum(32);
        order.setOrderTime(LocalDateTime.of(2023, 1, 3, 12, 23, 1));
        order.setCreateTime(DateUtil.parseDateTime("2023-01-03 11:06:01"));
        order.setDate("2022-03-01");
        User user = new User();
        user.setUsername("Jack");
        order.setUser(user);

        OrderVO orderVO = converter.convert(order, OrderVO.class);
        System.out.println(orderVO);

        Assert.equals(orderVO.getOrderId(), "394bcab38052404ab404c791cb975596");
        Assert.equals(orderVO.getGoods(), "Apple,Phone");
        Assert.equals(orderVO.getOrderPrice(), "$123.34");
        Assert.isNull(orderVO.getGoodsNum());
        Assert.equals(orderVO.getOrderTime(), "2023-01-03 12:23:01");
        Assert.equals(orderVO.getCreateTime(), "2023_01_03 110601");
        Assert.equals(orderVO.getOrderDate(), LocalDate.parse("2022-03-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Assert.equals(orderVO.getUser(), "Jack");
        Assert.equals(orderVO.getPayStatus(), "True");
    }

    @Test
    public void enumMapTest() {
        Goods goods = new Goods();
        goods.setState(GoodsStateEnum.ENABLED);
        final GoodsDto goodsDto = converter.convert(goods, GoodsDto.class);
        System.out.println(goodsDto);

        final GoodsVo goodsVo = converter.convert(goods, GoodsVo.class);
        System.out.println(goodsVo);
        Assert.equals(goodsVo.getState(), goods.getState().getState());

        final Goods goods2 = converter.convert(goodsVo, Goods.class);
        System.out.println(goods2);
        Assert.equals(goods2.getState(), GoodsStateEnum.ENABLED);

        GoodsDto goodsDto1 = new GoodsDto();
        goodsDto1.setType(1);
        goodsDto1.setState(1);
        final Goods goods1 = converter.convert(goodsDto1, Goods.class);
        System.out.println(goods1);
    }

    @Test
    public void innerClassTest() {
        Car.InnerClass innerClass = new Car.InnerClass();
        innerClass.setF("1111");
        InnerClassTarget innerClassTarget = converter.convert(innerClass, InnerClassTarget.class);
        Assert.equals(innerClassTarget.getF(), "1111");
        System.out.println(innerClassTarget);

        Car.InnerClass innerClass1 = converter.convert(innerClassTarget, Car.InnerClass.class);
        Assert.equals(innerClass1.getF(), "1111");
        System.out.println(innerClass1);
    }

    @Test
    public void extendTest() {
        SDto sDto = new SDto();
        sDto.setId(111111L);
        sDto.setSuccess(true);

        SVO svo = converter.convert(sDto, SVO.class);
        Assert.equals(svo.getId(), sDto.getId());
        Assert.equals(svo.getSuccess(), 1);
        System.out.println(svo);

        SDto sDto1 = converter.convert(svo, SDto.class);
        Assert.equals(sDto1.getId(), sDto.getId());
        Assert.equals(sDto1.getSuccess(), sDto.getSuccess());
        System.out.println(sDto1);
    }

    @Test
    public void testConditionQualifiedByName() {
        TreeNode parent = new TreeNode();
        // children
        List<TreeNode> children = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            TreeNode child = new TreeNode();
            child.setParent(parent);
            children.add(child);
        }
        parent.setChildren(children);
        TreeNodeDto treeNodeDto1 = converter.convert(parent, TreeNodeDto.class);
        // 当 children 为 <2 时不进行转换
        Assert.isNull(treeNodeDto1.getChildren());
        for (int i = 0; i < 99; i++) {
            TreeNode child = new TreeNode();
            child.setParent(parent);
            children.add(child);
        }
        TreeNodeDto treeNodeDto2 = converter.convert(parent, TreeNodeDto.class);
        Assert.equals(treeNodeDto2.getChildren().size(), 100);

        treeNodeDto2.getChildren().forEach(child -> {
            Assert.equals(child.getParent(), treeNodeDto2);
        });
    }

    @Test
    public void testQualifiedByName() {
        EnglishRelease englishRelease = new EnglishRelease();
        englishRelease.setTitle("Algorithms, 4th Edition");
        FrenchRelease frenchRelease1 = converter.convert(englishRelease, FrenchRelease.class);
        Assert.equals(frenchRelease1.getTitle(), "Inconnu et inconnu");
        englishRelease.setTitle("One Hundred Years of Solitude");
        FrenchRelease frenchRelease2 = converter.convert(englishRelease, FrenchRelease.class);
        Assert.equals(frenchRelease2.getTitle(), "Cent ans de solitude");

        EnglishRelease englishRelease1 = converter.convert(frenchRelease1, EnglishRelease.class);
        Assert.equals(englishRelease1.getTitle(), "Unknown");
        frenchRelease2.setTitle("Cent ans de solitude");
        EnglishRelease englishRelease2 = converter.convert(frenchRelease2, EnglishRelease.class);
        Assert.equals(englishRelease2.getTitle(), "One Hundred Years of Solitude");

        EnglishRelease englishRelease3 = new EnglishRelease();
        englishRelease3.setTitle("Default");
        FrenchRelease frenchRelease = converter.convert(englishRelease3, FrenchRelease.class);
        System.out.println(frenchRelease);
    }

    @Test
    public void testMapDtoToEntity() {

        CustomerDto customerDto = new CustomerDto();
        customerDto.id = 10L;
        customerDto.customerName = "Filip";
        OrderItemDto order1 = new OrderItemDto();
        order1.name = "Table";
        order1.quantity = 2L;
        customerDto.orders = new ArrayList<>(Collections.singleton(order1));

        Customer customer = converter.convert(customerDto, Customer.class);

        assertThat(customer.getId()).isEqualTo(10);
        assertThat(customer.getName()).isEqualTo("Filip");
        assertThat(customer.getOrderItems())
            .extracting("name", "quantity")
            .containsExactly(tuple("Table", 2L));
    }

    @Test
    public void testEntityDtoToDto() {

        Customer customer = new Customer();
        customer.setId(10L);
        customer.setName("Filip");
        OrderItem order1 = new OrderItem();
        order1.setName("Table");
        order1.setQuantity(2L);
        customer.setOrderItems(Collections.singleton(order1));

        CustomerDto customerDto = converter.convert(customer, CustomerDto.class);

        assertThat(customerDto.id).isEqualTo(10);
        assertThat(customerDto.customerName).isEqualTo("Filip");
        assertThat(customerDto.orders)
            .extracting("name", "quantity")
            .containsExactly(tuple("Table", 2L));
    }

    @Test
    public void testToTarget() {

        Source s = new Source();
        s.setMyIntegers(Arrays.asList(5, 3, 7));
        s.setMyStrings(Arrays.asList("five", "three", "seven"));

        Target t = converter.convert(s, Target.class);
        assertEquals(new Integer(5), t.getMyInteger());
        assertEquals("seven", t.getMyString());
    }

    @Test
    public void testToTargetWithConstant() {
        Source s = new Source();
        Target t = converter.convert(s, Target.class);
        System.out.println(t);
    }

    @Test
    public void shouldAutomapAndHandleSourceAndTargetPropertyNesting() {

        // -- prepare
        FishTank source = createFishTank();

        // -- action
        FishTankDto target = converter.convert(source, FishTankDto.class);

        // -- result
        assertThat(target.getName()).isEqualTo(source.getName());

        // fish and fishDto can be automapped
        assertThat(target.getFish()).isNotNull();
        assertThat(target.getFish().getKind()).isEqualTo(source.getFish().getType());
        assertThat(target.getFish().getName()).isNull();

        // automapping takes care of mapping property "waterPlant".
        assertThat(target.getPlant()).isNotNull();
        assertThat(target.getPlant().getKind()).isEqualTo(source.getPlant().getKind());

        // ornament (nested asymetric source)
        assertThat(target.getOrnament()).isNotNull();
        assertThat(target.getOrnament().getType()).isEqualTo(source.getInterior().getOrnament().getType());

        // material (nested asymetric target)
        assertThat(target.getMaterial()).isNotNull();
        assertThat(target.getMaterial().getManufacturer()).isNull();
        assertThat(target.getMaterial().getMaterialType()).isNotNull();
        assertThat(target.getMaterial().getMaterialType().getType()).isEqualTo(source.getMaterial().getType());

        //  first symetric then asymetric
        assertThat(target.getQuality()).isNotNull();
        assertThat(target.getQuality().getReport()).isNotNull();
        assertThat(target.getQuality().getReport().getVerdict())
            .isEqualTo(source.getQuality().getReport().getVerdict());
        assertThat(target.getQuality().getReport().getOrganisation().getApproval()).isNull();
        assertThat(target.getQuality().getReport().getOrganisation()).isNotNull();
        assertThat(target.getQuality().getReport().getOrganisation().getName())
            .isEqualTo(source.getQuality().getReport().getOrganisationName());
    }

    @Test
    public void shouldAutomapAndHandleSourceAndTargetPropertyNestingReverse() {

        // -- prepare
        FishTank source = createFishTank();

        // -- action
        FishTankDto target = converter.convert(source, FishTankDto.class);
        FishTank source2 = converter.convert(target, FishTank.class);

        // -- result
        assertThat(source2.getName()).isEqualTo(source.getName());

        // fish
        assertThat(source2.getFish()).isNotNull();
        assertThat(source2.getFish().getType()).isEqualTo(source.getFish().getType());

        // interior, designer will not be mapped (asymetric) to target. Here it shows.
        assertThat(source2.getInterior()).isNotNull();
        assertThat(source2.getInterior().getDesigner()).isNull();
        assertThat(source2.getInterior().getOrnament()).isNotNull();
        assertThat(source2.getInterior().getOrnament().getType())
            .isEqualTo(source.getInterior().getOrnament().getType());

        // material
        assertThat(source2.getMaterial()).isNotNull();
        assertThat(source2.getMaterial().getType()).isEqualTo(source.getMaterial().getType());

        // plant
        assertThat(source2.getPlant().getKind()).isEqualTo(source.getPlant().getKind());

        // quality
        assertThat(source2.getQuality().getReport()).isNotNull();
        assertThat(source2.getQuality().getReport().getOrganisationName())
            .isEqualTo(source.getQuality().getReport().getOrganisationName());
        assertThat(source2.getQuality().getReport().getVerdict())
            .isEqualTo(source.getQuality().getReport().getVerdict());
    }

    @Test
    public void shouldAutomapAndHandleSourceAndTargetPropertyNestingAndConstant() {

        // -- prepare
        FishTank source = createFishTank();

        // -- action
        FishTankDto1 target = converter.convert(source, FishTankDto1.class);

        // -- result

        // fixed value
        assertThat(target.getFish().getName()).isEqualTo("Nemo");

        // automapping takes care of mapping property "waterPlant".
        assertThat(target.getPlant()).isNotNull();
        assertThat(target.getPlant().getKind()).isEqualTo(source.getPlant().getKind());

        // non-nested and constant
        assertThat(target.getMaterial()).isNotNull();
        assertThat(target.getMaterial().getManufacturer()).isEqualTo("MMM");
        assertThat(target.getMaterial().getMaterialType()).isNotNull();
        assertThat(target.getMaterial().getMaterialType().getType()).isEqualTo(source.getMaterial().getType());

        assertThat(target.getOrnament()).isNull();
        assertThat(target.getQuality()).isNull();

    }

    @Test
    public void shouldAutomapAndHandleSourceAndTargetPropertyNestingAndExpresion() {

        // -- prepare
        FishTank source = createFishTank();

        // -- action
        FishTankDto2 target = converter.convert(source, FishTankDto2.class);

        // -- result
        assertThat(target.getFish().getName()).isEqualTo("Jaws");

        assertThat(target.getMaterial()).isNull();
        assertThat(target.getOrnament()).isNull();
        assertThat(target.getPlant()).isNull();

        assertThat(target.getQuality()).isNotNull();
        assertThat(target.getQuality().getReport()).isNotNull();
        assertThat(target.getQuality().getReport().getVerdict())
            .isEqualTo(source.getQuality().getReport().getVerdict());
        assertThat(target.getQuality().getReport().getOrganisation()).isNotNull();
        assertThat(target.getQuality().getReport().getOrganisation().getApproval()).isNull();
        assertThat(target.getQuality().getReport().getOrganisation().getName()).isEqualTo("Dunno");
    }

    @Test
    public void shouldAutomapIntermediateLevelAndMapConstant() {

        // -- prepare
        FishTank source = createFishTank();

        // -- action
        FishTankWithNestedDocumentDto target = converter.convert(source, FishTankWithNestedDocumentDto.class);

        // -- result
        assertThat(target.getFish().getName()).isEqualTo("Jaws");

        assertThat(target.getMaterial()).isNull();
        assertThat(target.getOrnament()).isNull();
        assertThat(target.getPlant()).isNull();

        assertThat(target.getQuality()).isNotNull();
        assertThat(target.getQuality().getDocument()).isNotNull();
        assertThat(target.getQuality().getDocument().getVerdict())
            .isEqualTo(source.getQuality().getReport().getVerdict());
        assertThat(target.getQuality().getDocument().getOrganisation()).isNotNull();
        assertThat(target.getQuality().getDocument().getOrganisation().getApproval()).isNull();
        assertThat(target.getQuality().getDocument().getOrganisation().getName()).isEqualTo("NoIdeaInc");
    }

    private FishTank createFishTank() {
        FishTank fishTank = new FishTank();

        Fish fish = new Fish();
        fish.setType("Carp");

        WaterPlant waterplant = new WaterPlant();
        waterplant.setKind("Water Hyacinth");

        Interior interior = new Interior();
        interior.setDesigner("MrVeryFamous");
        Ornament ornament = new Ornament();
        ornament.setType("castle");
        interior.setOrnament(ornament);

        WaterQuality quality = new WaterQuality();
        WaterQualityReport report = new WaterQualityReport();
        report.setVerdict("PASSED");
        report.setOrganisationName("ACME");
        quality.setReport(report);

        MaterialType materialType = new MaterialType();
        materialType.setType("myMaterialType");

        fishTank.setName("MyLittleFishTank");
        fishTank.setFish(fish);
        fishTank.setPlant(waterplant);
        fishTank.setInterior(interior);
        fishTank.setMaterial(materialType);
        fishTank.setQuality(quality);

        return fishTank;
    }

    @Test
    public void shouldCopyArraysInBean() {

        Scientist source = new Scientist("Bob");
        source.setPublications(new String[] {"the Lancet", "Nature"});
        source.publicPublications = new String[] {"public the Lancet", "public Nature"};

        ScientistDto dto = converter.convert(source, ScientistDto.class);

        assertThat(dto).isNotNull();
        assertThat(dto).isNotEqualTo(source);
        assertThat(dto.getPublications()).containsOnly("the Lancet", "Nature");
        assertThat(dto.publicPublications).containsOnly("public the Lancet", "public Nature");
    }

    @Test
    public void shouldForgeMappingForIntToString() {

        Scientist source = new Scientist("Bob");
        source.setPublicationYears(new String[] {"1993", "1997"});
        source.publicPublicationYears = new String[] {"1994", "1998"};

        ScientistDto dto = converter.convert(source, ScientistDto.class);

        assertThat(dto).isNotNull();
        assertThat(dto.getPublicationYears()).containsOnly(1993, 1997);
        assertThat(dto.publicPublicationYears).containsOnly(1994, 1998);
    }

    @Test
    public void shouldMapArrayToList() {
        List<ScientistDto> dtos =
            converter.convert(CollectionUtil.newArrayList(new Scientist("Bob"), new Scientist("Larry")),
                ScientistDto.class);

        assertThat(dtos).isNotNull();
        assertThat(dtos).extracting("name").containsOnly("Bob", "Larry");
    }

    @Test
    public void shouldMapBooleanPropertyWithIsPrefixedGetter() {
        //given
        Person person = new Person();
        person.setMarried(Boolean.TRUE);

        //when
        PersonDto personDto = converter.convert(person, PersonDto.class);

        //then
        assertThat(personDto.getMarried()).isEqualTo("true");
    }

    @Test
    public void shouldMapBooleanPropertyPreferringGetPrefixedGetterOverIsPrefixedGetter() {
        //given
        Person person = new Person();
        person.setEngaged(Boolean.TRUE);

        //when
        PersonDto personDto = converter.convert(person, PersonDto.class);

        //then
        assertThat(personDto.getEngaged()).isEqualTo("true");
    }

    @Test
    public void shouldMapBooleanPropertyWithPropertyMappingMethod() {
        // given
        Person person = new Person();
        person.setDivorced(new YesNo(true));
        person.setWidowed(new YesNo(true));

        // when
        PersonDto personDto = converter.convert(person, PersonDto.class);

        // then
        assertThat(personDto.getDivorced()).isEqualTo("yes");
        assertThat(personDto.getWidowed()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void testThatAbstractBuilderMapsAllProperties() {
        ImmutableProduct product = converter.convert(new ProductDto("router", 31), ImmutableProduct.class);

        assertThat(product.getPrice()).isEqualTo(31);
        assertThat(product.getName()).isEqualTo("router");
    }

    @Test
    public void testThatAbstractBuilderReverseMapsAllProperties() {
        ProductDto product = converter.convert(ImmutableProduct.builder()
            .price(31000)
            .name("car")
            .build(), ProductDto.class);

        assertThat(product.getPrice()).isEqualTo(31000);
        assertThat(product.getName()).isEqualTo("car");
    }

    @Test
    public void testAbstractTargetMapper() {
        ParentSource parent = new ParentSource();
        parent.setCount(4);
        parent.setChild(new ChildSource("Phineas"));
        parent.setNonGenericChild(new ChildSource("Ferb"));

        // transform
        Parent immutableParent = converter.convert(parent, ImmutableParent.class);
        assertThat(immutableParent.getCount()).isEqualTo(4);
        assertThat(immutableParent.getChild().getName()).isEqualTo("Phineas");
        assertThat(immutableParent.getNonGenericChild())
            .isNotNull()
            .isInstanceOf(ImmutableChild.class);
        assertThat(immutableParent.getNonGenericChild().getName()).isEqualTo("Ferb");

        Parent mutableParent = converter.convert(parent, MutableParent.class);

        assertThat(mutableParent.getCount()).isEqualTo(4);
        assertThat(mutableParent.getChild().getName()).isEqualTo("Phineas");
        assertThat(mutableParent.getNonGenericChild())
            .isNotNull()
            .isInstanceOf(ImmutableChild.class);
        assertThat(mutableParent.getNonGenericChild().getName()).isEqualTo("Ferb");

    }

    @Test
    public void shouldUseBuilderFactory() {
        io.github.linpeilie.me.builder.factory.Person person =
            converter.convert(new io.github.linpeilie.me.builder.factory.PersonDto("Filip"),
                io.github.linpeilie.me.builder.factory.Person.class);

        assertThat(person.getName()).isEqualTo("Filip");
        assertThat(person.getSource()).isEqualTo("Factory with @ObjectFactory");
    }

    @Test
    public void shouldUseImplicitBuilderFactory() {
        ImplicitPerson person =
            converter.convert(new io.github.linpeilie.me.builder.factory.PersonDto("Filip"), ImplicitPerson.class);

        assertThat(person.getName()).isEqualTo("Filip");
        assertThat(person.getSource()).isEqualTo("Implicit Factory");
    }

    @Test
    public void testOngeneratedMethods() {
        // setup
        Address address = new Address();
        address.setAddressLine("RoadToNowhere;5");
        address.setTown("SmallTown");
        Employee employee = new Employee();
        employee.setAddress(address);
        Company company = new Company();
        company.setEmployees(Arrays.asList(employee));

        // test
        CompanyDto companyDto = converter.convert(company, CompanyDto.class);

        // verify
        assertThat(companyDto.getEmployees()).isNotEmpty();
        assertThat(companyDto.getEmployees().size()).isEqualTo(1);
        assertThat(companyDto.getEmployees().get(0).getAddress()).isNotNull();
        assertThat(companyDto.getEmployees().get(0).getAddress().getHouseNumber()).isEqualTo(5);
        assertThat(companyDto.getEmployees().get(0).getAddress().getStreet()).isEqualTo("RoadToNowhere");
        assertThat(companyDto.getEmployees().get(0).getAddress().getTown()).isEqualTo("SmallTown");
    }

    @Test
    public void callbackMethodAreCalled() {
        CarEntity carEntity = converter.convert(new CarDto(), CarEntity.class);

        assertThat(carEntity.getId()).isEqualTo(2);
        assertThat(carEntity.getSeatCount()).isEqualTo(5);
    }

}
