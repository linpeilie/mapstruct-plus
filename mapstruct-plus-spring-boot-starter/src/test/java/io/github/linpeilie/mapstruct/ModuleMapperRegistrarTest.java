package io.github.linpeilie.mapstruct;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.linpeilie.Converter;
import io.github.linpeilie.CycleAvoidingMappingContext;
import io.github.linpeilie.annotations.AutoEnumMapper;
import io.github.linpeilie.annotations.AutoMapMapper;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.module.MapperKind;
import io.github.linpeilie.module.ModuleMapperRecord;
import io.github.linpeilie.module.ModuleMappers;
import java.lang.reflect.Field;
import org.mapstruct.Mapper;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.stereotype.Component;

/**
 * module-mappers 清单注册链路测试（收口为单文件，映射实体一并嵌套收拢；
 * 生成的 mapper 位于本类所在包，可直接同包引用）：
 * <ul>
 *   <li>{@link Registration}：Registrar 单元行为——精确边界、容错红线、注册查重</li>
 *   <li>{@link SpringIntegration}：Spring 上下文集成——注册与转换端到端、
 *       Adapter/枚举依赖完整、清单内容正确性</li>
 *   <li>{@link ConditionalEvaluationBaseline}：条件装配差异基线（提案已知差异）</li>
 * </ul>
 *
 * @author shanhongyu
 */
public class ModuleMapperRegistrarTest {

    private static final String ONLY_LIB_A =
        "/fixture/module-mappers-only-lib-a/META-INF/mapstruct-plus/module-mappers";

    private static final String DUPLICATE_LIB_A =
        "/fixture/module-mappers-duplicate/META-INF/mapstruct-plus/module-mappers";

    private static final String CORRUPT =
        "/fixture/module-mappers-corrupt/META-INF/mapstruct-plus/module-mappers";

    private AnnotationConfigApplicationContext context;

    /**
     * 模拟消费应用：无任何覆盖 mapper 包的组件扫描，注册完全依赖编译期清单
     */
    @Configuration
    @Import(MapstructAutoConfiguration.class)
    static class RegistrarOnlyConfig {
    }

    /**
     * 模拟用户配置类：不含 @Import，先于自动配置解析
     */
    @Configuration
    static class UserConditionalConfig {

        @Bean
        @ConditionalOnBean(LibAToLibADtoMapper.class)
        public String conditionalOnMapperBean() {
            return "on-bean";
        }

        @Bean
        @ConditionalOnClass(LibAToLibADtoMapper.class)
        public String conditionalOnMapperClass() {
            return "on-class";
        }

    }

    @AfterEach
    void tearDown() {
        if (context != null) {
            context.close();
            context = null;
        }
    }

    // ************************ 映射实体（仅本测试类使用，嵌套收拢） ************************

    /**
     * 模拟类库/starter 中的映射对象 A（含嵌套对象 B，验证嵌套 mapper 的延迟注入依赖）
     */
    @AutoMapper(target = LibADto.class)
    public static class LibA {

        private String name;

        private LibB libB;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LibB getLibB() {
            return libB;
        }

        public void setLibB(LibB libB) {
            this.libB = libB;
        }

    }

    public static class LibADto {

        private String name;

        private LibBDto libB;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LibBDto getLibB() {
            return libB;
        }

        public void setLibB(LibBDto libB) {
            this.libB = libB;
        }

    }

    /**
     * 模拟类库/starter 中的映射对象 B；同时充当精确边界测试中
     * “同包存在但未列入清单”的第三方对象（应用侧同包对象同理）
     */
    @AutoMapper(target = LibBDto.class)
    public static class LibB {

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }

    public static class LibBDto {

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }

    /**
     * 枚举 Mapper 为静态方法类，无需注册为 Bean；
     * 枚举字段转换经 Adapter 的静态代理方法完成，依赖 Adapter Bean 注册完整
     */
    @AutoEnumMapper("state")
    public enum Goods2StateEnum {

        ENABLED(1),

        DISABLED(0);

        Goods2StateEnum(final Integer state) {
            this.state = state;
        }

        private final Integer state;

        public Integer getState() {
            return state;
        }

    }

    public static class Goods2 {

        private String name;

        private Goods2StateEnum state;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Goods2StateEnum getState() {
            return state;
        }

        public void setState(Goods2StateEnum state) {
            this.state = state;
        }

    }

    @AutoMapper(target = Goods2.class)
    public static class Goods2Dto {

        private String name;

        private Integer state;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

    }

    @AutoMapMapper
    public static class MapModel2 {

        private String str;

        private Integer count;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

    }

    /**
     * 自引用树形结构，验证 cycleAvoiding mapper（kind=cycle）的注册与循环转换
     */
    @AutoMapper(target = OrderNodeDto.class, cycleAvoiding = true)
    public static class OrderNode {

        private String id;

        private OrderNode parent;

        private List<OrderNode> children;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public OrderNode getParent() {
            return parent;
        }

        public void setParent(OrderNode parent) {
            this.parent = parent;
        }

        public List<OrderNode> getChildren() {
            return children;
        }

        public void setChildren(List<OrderNode> children) {
            this.children = children;
        }

    }

    public static class OrderNodeDto {

        private String id;

        private OrderNodeDto parent;

        private List<OrderNodeDto> children;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public OrderNodeDto getParent() {
            return parent;
        }

        public void setParent(OrderNodeDto parent) {
            this.parent = parent;
        }

        public List<OrderNodeDto> getChildren() {
            return children;
        }

        public void setChildren(List<OrderNodeDto> children) {
            this.children = children;
        }

    }

    /**
     * 自定义 @Mapper（spring-lazy 组件模型）：验证自定义 mapper 的 Impl 入清单并注册；
     * 嵌套接口形态同时验证清单条目按二进制名（ModuleMapperRegistrarTest$CustomCarMapperImpl）记录
     */
    @Mapper(componentModel = "spring-lazy")
    public interface CustomCarMapper {

        CustomCarDto toDto(CustomCar car);

    }

    public static class CustomCar {

        private String model;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

    }

    public static class CustomCarDto {

        private String model;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

    }

    // ************************ 测试分组 ************************

    @Nested
    @DisplayName("Registrar 单元行为")
    class Registration {

        @Test
        @DisplayName("精确边界：清单只注册所列条目，同包未列的类不被误伤")
        void registersOnlyListedEntries() {
            CountingRegistry registry = new CountingRegistry();
            new ModuleMapperRegistrar().registerManifest(
            fixture(ONLY_LIB_A), registry, getClass().getClassLoader());

            assertThat(registry.containsBeanDefinition("libAToLibADtoMapperImpl")).isTrue();
            assertThat(registry.containsBeanDefinition("libBToLibBDtoMapperImpl"))
                .as("同包存在但未列入清单的类（含应用侧同包对象）不应注册")
                .isFalse();
        }

        @Test
        @DisplayName("容错红线：坏行/未知 kind/非法类名/类缺失逐条 WARN 跳过，不阻断其余条目")
        void toleratesCorruptEntriesWithoutBlocking() {
            CountingRegistry registry = new CountingRegistry();
            new ModuleMapperRegistrar().registerManifest(
            fixture(CORRUPT), registry, getClass().getClassLoader());

            assertThat(registry.containsBeanDefinition("libBToLibBDtoMapperImpl"))
                .as("唯一的有效条目应正常注册")
                .isTrue();
            assertThat(registry.containsBeanDefinition("notCompiledAnywhereImpl"))
                .as("类缺失的陈旧条目应被跳过")
                .isFalse();
        }

        @Test
        @DisplayName("注册前查重：同一清单重复注册、跨清单重复列出，都只注册一次")
        void skipsDuplicateRegistrationWithinAndAcrossManifests() {
            CountingRegistry registry = new CountingRegistry();
            ModuleMapperRegistrar registrar = new ModuleMapperRegistrar();
            registrar.registerManifest(fixture(ONLY_LIB_A), registry, getClass().getClassLoader());
            registrar.registerManifest(fixture(ONLY_LIB_A), registry, getClass().getClassLoader());
            registrar.registerManifest(fixture(DUPLICATE_LIB_A), registry, getClass().getClassLoader());

            assertThat(registry.registrationCount)
                .as("重复条目经 containsBeanDefinition 守卫拦截，不产生重复注册动作")
                .isEqualTo(1);
        }

    }

    @Nested
    @DisplayName("Spring 上下文集成")
    class SpringIntegration {

        @Test
        @DisplayName("清单通道注册（含嵌套 mapper 延迟注入与反向转换），转换语义与扫描注册等价")
        void registersMappersOutsideComponentScanAndConverts() throws Exception {
            refresh();

            // 生成的 Impl 不再携带 @Component（编译期产物，只能反射检查）
            Class<?> implClass = Class.forName("io.github.linpeilie.mapstruct.LibAToLibADtoMapperImpl");
            assertThat(implClass.getAnnotation(Component.class)).isNull();

            assertThat(context.getBean(LibAToLibADtoMapper.class)).isNotNull();
            assertThat(context.containsBean("libAToLibADtoMapperImpl"))
                .as("Bean 命名沿用扫描约定（Impl 类名首字母小写）")
                .isTrue();

            Converter converter = context.getBean(Converter.class);
            LibB libB = new LibB();
            libB.setCode("b1");
            LibA libA = new LibA();
            libA.setName("a1");
            libA.setLibB(libB);

            LibADto libADto = converter.convert(libA, LibADto.class);
            assertThat(libADto.getName()).isEqualTo("a1");
            assertThat(libADto.getLibB().getCode()).isEqualTo("b1");
            // 反向转换默认生成，同样经清单注册
            assertThat(converter.convert(libADto, LibA.class).getLibB().getCode()).isEqualTo("b1");
        }

        @Test
        @DisplayName("Adapter 走完整生命周期：@Autowired 注入生效，枚举字段经 Adapter 代理转换")
        void adapterLifecycleAndEnumConversion() throws Exception {
            refresh();

            Goods2Dto goodsDto = new Goods2Dto();
            goodsDto.setName("goods");
            goodsDto.setState(1);
            Goods2 goods = context.getBean(Converter.class).convert(goodsDto, Goods2.class);
            assertThat(goods.getState()).isEqualTo(Goods2StateEnum.ENABLED);

            String[] adapterBeans = adapterBeanNames();
            assertThat(adapterBeans)
                .as("适配器（含枚举代理方法）应经清单注册为 Bean")
                .isNotEmpty();
            Object adapter = context.getBean(adapterBeans[0]);
            Field converterField = adapter.getClass().getDeclaredField("converter");
            converterField.setAccessible(true);
            assertThat(converterField.get(adapter))
                .as("Registrar 注册的 BeanDefinition 由 Spring 实例化并走完整生命周期")
                .isNotNull();
        }

        @Test
        @DisplayName("Map 转换（kind=map）经清单注册")
        void mapConversionViaManifest() {
            refresh();

            Map<String, Object> map = new HashMap<>();
            map.put("str", "map-str");
            map.put("count", 42);
            MapModel2 model = context.getBean(Converter.class).convert(map, MapModel2.class);
            assertThat(model.getStr()).isEqualTo("map-str");
            assertThat(model.getCount()).isEqualTo(42);
        }

        @Test
        @DisplayName("循环引用转换（kind=cycle）经清单注册，循环引用保持")
        void cycleAvoidingConversionViaManifest() {
            refresh();

            OrderNode root = new OrderNode();
            root.setId("root");
            OrderNode child = new OrderNode();
            child.setId("child");
            child.setParent(root);
            root.setChildren(Collections.singletonList(child));

            OrderNodeDto dto = context.getBean(Converter.class)
                .convert(root, OrderNodeDto.class, new CycleAvoidingMappingContext());
            assertThat(dto.getChildren().get(0).getId()).isEqualTo("child");
            assertThat(dto.getChildren().get(0).getParent()).isSameAs(dto);
        }

        @Test
        @DisplayName("编译期清单内容正确：kinds 齐全，枚举 Mapper 不入清单")
        void generatedManifestContainsExpectedEntries() {
            List<ModuleMapperRecord> records = ModuleMappers.load(getClass().getClassLoader());

            assertThat(records).contains(
                new ModuleMapperRecord(MapperKind.BEAN, "io.github.linpeilie.mapstruct.LibAToLibADtoMapperImpl"),
                new ModuleMapperRecord(MapperKind.MAP, "io.github.linpeilie.mapstruct.MapToMapModel2MapperImpl"),
                new ModuleMapperRecord(MapperKind.CYCLE, "io.github.linpeilie.mapstruct.OrderNodeToOrderNodeDtoMapperImpl"));
            assertThat(records.stream().filter(record -> record.getKind() == MapperKind.ADAPTER).count())
                .as("bean/map/cycle 三类适配器均应记录")
                .isGreaterThanOrEqualTo(3);
            assertThat(records)
                .as("枚举 Mapper 为静态方法接口，无需注册")
                .noneMatch(record -> record.getMapperClassName().contains("Goods2StateEnumMapper"));
        }

        @Test
        @DisplayName("自定义 @Mapper（spring-lazy）入清单并按嵌套二进制名注册")
        void customSpringLazyMapperRegisteredViaManifest() {
            refresh();

            assertThat(ModuleMappers.load(getClass().getClassLoader()))
                .contains(new ModuleMapperRecord(MapperKind.BEAN,
                    "io.github.linpeilie.mapstruct.ModuleMapperRegistrarTest$CustomCarMapperImpl"));
            CustomCar car = new CustomCar();
            car.setModel("custom");
            assertThat(context.getBean(CustomCarMapper.class).toDto(car).getModel()).isEqualTo("custom");
        }

        private void refresh() {
            context = new AnnotationConfigApplicationContext();
            context.register(RegistrarOnlyConfig.class);
            context.refresh();
        }

        private String[] adapterBeanNames() {
            return Arrays.stream(context.getBeanDefinitionNames())
                .filter(name -> name.startsWith("converterMapperAdapter")
                                || name.startsWith("mapConvertMapperAdapter"))
                .toArray(String[]::new);
        }

    }

    @Nested
    @DisplayName("条件装配差异基线（提案已知差异）")
    class ConditionalEvaluationBaseline {

        @Test
        @DisplayName("@ConditionalOnBean 不命中（IBDR 注册晚于条件求值），@ConditionalOnClass 仍命中")
        void conditionalOnBeanMissesWhileConditionalOnClassMatches() {
            context = new AnnotationConfigApplicationContext();
            context.register(UserConditionalConfig.class, RegistrarOnlyConfig.class);
            context.refresh();

            assertThat(context.containsBean("conditionalOnMapperBean"))
                .as("IBDR 注册晚于用户配置解析，@ConditionalOnBean 不再命中")
                .isFalse();
            assertThat(context.containsBean("conditionalOnMapperClass")).isTrue();
            // 条件装配差异不影响 mapper 本身的注册
            assertThat(context.getBean(LibAToLibADtoMapper.class)).isNotNull();
        }

    }

    private URL fixture(String path) {
        URL url = getClass().getResource(path);
        assertThat(url).as("fixture manifest should exist: %s", path).isNotNull();
        return url;
    }

    /**
     * 计数包装：验证注册守卫真的拦截了重复的 registerBeanDefinition 调用，
     * 避免仅断言 bean 名集合恒真的无效测试
     */
    private static final class CountingRegistry extends SimpleBeanDefinitionRegistry {

        private int registrationCount;

        @Override
        public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
            registrationCount++;
            super.registerBeanDefinition(beanName, beanDefinition);
        }

    }

}
